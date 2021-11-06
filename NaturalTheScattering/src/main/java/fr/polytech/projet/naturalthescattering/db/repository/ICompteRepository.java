package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Compte;

public interface ICompteRepository extends CrudRepository<Compte, Long> {
	public Compte findByPseudo(String pseudo);
}
