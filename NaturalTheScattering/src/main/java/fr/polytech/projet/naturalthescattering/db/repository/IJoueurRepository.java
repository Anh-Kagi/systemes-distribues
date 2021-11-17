package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Joueur;

public interface IJoueurRepository extends JpaRepository<Joueur, Long> {
	Joueur findByPseudo(String pseudo);
	boolean existsByPseudo(String pseudo);
}
