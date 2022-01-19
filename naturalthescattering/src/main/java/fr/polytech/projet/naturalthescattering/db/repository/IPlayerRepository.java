package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Player;

public interface IPlayerRepository extends JpaRepository<Player, Long> {
	Player findByPseudo(String pseudo);
	boolean existsByPseudo(String pseudo);
}
