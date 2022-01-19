package fr.polytech.projet.naturalthescattering.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.projet.naturalthescattering.service.result.BoosterResult;
import fr.polytech.projet.naturalthescattering.service.result.BoosterResult.OpenStatus;
import fr.polytech.projet.naturalthescattering.db.Account;
import fr.polytech.projet.naturalthescattering.db.AccountCard;
import fr.polytech.projet.naturalthescattering.db.Card;
import fr.polytech.projet.naturalthescattering.db.repository.IAccountCardRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICardRepository;

@Service
public class BoosterService {
	@Autowired
	private ICardRepository cards;
	
	@Autowired
	private IAccountCardRepository accountcards;
	
	public BoosterResult.Open open(Account account) {
		BoosterResult.Open result = new BoosterResult.Open();
		
		List<Card> cardsList = cards.findAll();
		if (cardsList.size() < 5) {
			result.setStatus(OpenStatus.NOT_ENOUGH_CARDS_IN_DB);
			return result;
		}

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
		
		result.setStatus(OpenStatus.OK);
		result.setBooster(booster);
		return result;
	}
}
