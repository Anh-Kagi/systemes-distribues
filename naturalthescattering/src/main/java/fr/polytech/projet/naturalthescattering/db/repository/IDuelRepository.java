package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Duel;

public interface IDuelRepository extends JpaRepository<Duel, Long> {
}
