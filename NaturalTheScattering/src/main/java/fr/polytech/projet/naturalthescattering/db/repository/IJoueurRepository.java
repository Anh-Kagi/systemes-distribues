package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Joueur;

public interface IJoueurRepository extends CrudRepository<Joueur, Long> {
	Joueur findByPseudo(String pseudo);
}
