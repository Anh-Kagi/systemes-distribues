package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Long> {
	public Admin findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
