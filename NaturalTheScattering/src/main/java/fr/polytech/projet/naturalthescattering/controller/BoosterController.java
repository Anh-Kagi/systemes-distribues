package fr.polytech.projet.naturalthescattering.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.db.Carte;
import fr.polytech.projet.naturalthescattering.db.Compte;
import fr.polytech.projet.naturalthescattering.db.CompteCarte;
import fr.polytech.projet.naturalthescattering.db.repository.ICarteRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICompteCarteRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICompteRepository;

@RestController
@RequestMapping(path="/api/booster")
@PreAuthorize("@authVerifier.isPlayer(authentication.name)")
public class BoosterController {
	@Autowired
	private ICarteRepository cartes;
	
	@Autowired
	private ICompteCarteRepository comptecartes;
	
	@Autowired
	private ICompteRepository comptes;
	
	@PostMapping(path="/open")
	public String open(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
		Compte compte = comptes.findByPseudo(auth.getName());
		
		// choose 5 randomly carte
		List<Carte> cartesList = cartes.findAll();
		
		// TODO: spend points to open
		
		if (cartesList.size() >= 5) {
			Collections.shuffle(cartesList);
			List<Carte> booster = cartesList.subList(0, 5);
			
			// TODO: change reponse creation method
			String jsonRes = "{status: 'success', booster: [";
			
			for (Carte c : booster) {
				jsonRes += c.getId() + ", ";
				CompteCarte cc = comptecartes.findByProprietaireAndCarte(compte, c);
				if (cc == null)
					comptecartes.save(new CompteCarte(compte, c, 1));
				else {
					cc.incQuantite();
					comptecartes.save(cc);
				}
			}
			jsonRes += "]}";
			return jsonRes;
		} else {
			res.setStatus(500);
			return "{status: 'error', reason: 'no card found in database'}";
		}
	}
}
