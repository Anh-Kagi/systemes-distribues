package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Guild;

public interface IGuildRepository extends JpaRepository<Guild, Long> {
	boolean existsByName(String name);
}
