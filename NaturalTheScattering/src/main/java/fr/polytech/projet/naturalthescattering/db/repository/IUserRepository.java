package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	public User findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
