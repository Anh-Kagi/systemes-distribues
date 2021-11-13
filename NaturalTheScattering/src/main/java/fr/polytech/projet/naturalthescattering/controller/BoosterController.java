package fr.polytech.projet.naturalthescattering.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.BoosterOpenResult;
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
	public BoosterOpenResult open(HttpServletResponse res, Authentication auth) {
		Compte compte = comptes.findByPseudo(auth.getName());
		
		// choose 5 random carte
		List<Carte> cartesList = cartes.findAll();

		BoosterOpenResult result = new BoosterOpenResult();
		
		// TODO: spend points to open
		
		if (cartesList.size() >= 5) {
			Collections.shuffle(cartesList);
			List<Carte> booster = cartesList.subList(0, 5);
			
			for (Carte c : booster) {
				CompteCarte cc = comptecartes.findByProprietaireAndCarte(compte, c);
				if (cc == null)
					comptecartes.save(new CompteCarte(compte, c, 1));
				else {
					cc.incQuantite();
					comptecartes.save(cc);
				}
			}
			
			result.setSuccess(true);
			result.setReason("");
			result.setBooster(booster);
			return result;
		} else {
			res.setStatus(500);
			result.setSuccess(false);
			result.setReason("Not enough cards found in database");
			return result;
		}
	}
}
