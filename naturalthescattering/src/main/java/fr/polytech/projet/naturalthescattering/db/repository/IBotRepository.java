package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Bot;

public interface IBotRepository extends JpaRepository<Bot, Long> {
}
