package fr.polytech.projet.naturalthescattering.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.BoosterResult;
import fr.polytech.projet.naturalthescattering.db.Account;
import fr.polytech.projet.naturalthescattering.db.AccountCard;
import fr.polytech.projet.naturalthescattering.db.Card;
import fr.polytech.projet.naturalthescattering.db.repository.IAccountCardRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IAccountRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICardRepository;

@RestController
@RequestMapping(path="/api/booster")
public class BoosterController {
	@Autowired
	private ICardRepository cards;
	
	@Autowired
	private IAccountCardRepository accountcards;
	
	@Autowired
	private IAccountRepository accounts;
	
	@PostMapping(path="/open")
	public ResponseEntity<BoosterResult.Open> open(HttpServletResponse res, Authentication auth) {
		Account account = accounts.findByPseudo(auth.getName());
		
		// choose 5 random carte
		List<Card> cardsList = cards.findAll();

		BoosterResult.Open result = new BoosterResult.Open();
		
		// TODO: spend points to open
		
		if (cardsList.size() >= 5) {
			Collections.shuffle(cardsList);
			List<Card> booster = cardsList.subList(0, 5);
			
			for (Card c : booster) {
				AccountCard cc = accountcards.findByOwnerAndCard(account, c);
				if (cc == null)
					accountcards.save(new AccountCard(account, c, 1));
				else {
					cc.incQuantity();
					accountcards.save(cc);
				}
			}
			
			result.setSuccess(true);
			result.setReason("");
			result.setBooster(booster);
			return new ResponseEntity<BoosterResult.Open>(result, HttpStatus.CREATED);
		} else {
			res.setStatus(500);
			result.setSuccess(false);
			result.setReason("Not enough cards found in database");
			return new ResponseEntity<BoosterResult.Open>(result, HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
