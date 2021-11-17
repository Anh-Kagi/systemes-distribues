package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Utilisateur;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	public Utilisateur findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
