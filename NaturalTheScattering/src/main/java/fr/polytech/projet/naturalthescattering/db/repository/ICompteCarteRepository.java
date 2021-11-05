package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Compte;
import fr.polytech.projet.naturalthescattering.db.CompteCarte;

public interface ICompteCarteRepository extends CrudRepository<CompteCarte, Long> {
	public CompteCarte findByProprietaire(Compte proprietaire);
}