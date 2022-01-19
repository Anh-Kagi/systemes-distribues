package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Account;
import fr.polytech.projet.naturalthescattering.db.AccountCard;
import fr.polytech.projet.naturalthescattering.db.Card;

public interface IAccountCardRepository extends JpaRepository<AccountCard, Long> {
	public AccountCard findByOwner(Account owner);
	public AccountCard findByOwnerAndCard(Account owner, Card card);
	public void removeByOwner(Account owner);
}