package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Deck;

public interface IDeckRepository extends JpaRepository<Deck, Long> {
}
