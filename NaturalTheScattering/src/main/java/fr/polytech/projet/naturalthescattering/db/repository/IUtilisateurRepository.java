package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Utilisateur;

public interface IUtilisateurRepository extends CrudRepository<Utilisateur, Long> {
	public Utilisateur findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
