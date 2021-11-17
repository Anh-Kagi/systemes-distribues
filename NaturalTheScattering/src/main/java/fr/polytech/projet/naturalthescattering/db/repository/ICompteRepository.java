package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Compte;

public interface ICompteRepository extends JpaRepository<Compte, Long> {
	public Compte findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
