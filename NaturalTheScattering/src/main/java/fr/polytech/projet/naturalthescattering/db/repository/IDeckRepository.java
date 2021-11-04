package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Deck;

public interface IDeckRepository extends CrudRepository<Deck, Long> {
}
