package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.Joueur;

public interface IJoueurRepository extends CrudRepository<Joueur, Long> {
	Joueur findByPseudo(String pseudo);
}
