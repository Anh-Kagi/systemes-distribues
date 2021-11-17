package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Tournoi;

public interface ITournoiRepository extends JpaRepository<Tournoi, Long> {
}
