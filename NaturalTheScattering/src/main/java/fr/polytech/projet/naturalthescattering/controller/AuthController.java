package fr.polytech.projet.naturalthescattering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.db.Joueur;
import fr.polytech.projet.naturalthescattering.db.repository.IJoueurRepository;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@Autowired
	IJoueurRepository joueurs;
	
	@PostMapping(path="/register")
	public GenericResult register(Authentication auth, @RequestParam(name="username", required=true) String username, @RequestParam(name="password", required=true) String password) {
		GenericResult result = new GenericResult();
		if (!username.isEmpty() && !password.isEmpty()) {
			if (!joueurs.existsByPseudo(username)) {
				joueurs.save(new Joueur(username, password, 0));
				
				result.setSuccess(true);
				result.setReason("");
				return result;
			} else {
				result.setSuccess(false);
				result.setReason("Username already taken");
				return result;
			}
		}
		result.setSuccess(false);
		result.setReason("empty " + (username == null || username.isEmpty() ? "username" + (password == null || password.isEmpty() ? " & password" : "") : "password"));
		return result;
	}
}
