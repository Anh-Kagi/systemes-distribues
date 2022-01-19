package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.util.Response;
import fr.polytech.projet.naturalthescattering.db.Account;
import fr.polytech.projet.naturalthescattering.db.repository.IAccountRepository;
import fr.polytech.projet.naturalthescattering.service.BoosterService;
import fr.polytech.projet.naturalthescattering.service.result.BoosterResult;

@RestController
@RequestMapping(path="/api/booster")
public class BoosterController {
	
	@Autowired
	private IAccountRepository accounts;
	
	@Autowired
	private BoosterService srv;
	
	@PostMapping(path="/open")
	public ResponseEntity<BoosterResult.Open> open(HttpServletResponse res, Authentication auth) {
		Account account = accounts.findByPseudo(auth.getName()); // should never be false
		
		return Response.create(srv.open(account));
	}
}
