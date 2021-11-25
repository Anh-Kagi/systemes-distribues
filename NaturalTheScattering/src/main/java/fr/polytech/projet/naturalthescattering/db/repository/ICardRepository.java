package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Card;

public interface ICardRepository extends JpaRepository<Card, Long> {
	@Override
	public List<Card> findAll();
}
