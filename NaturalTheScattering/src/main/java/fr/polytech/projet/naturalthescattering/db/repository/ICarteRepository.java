package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Carte;

public interface ICarteRepository extends JpaRepository<Carte, Long> {
	@Override
	public List<Carte> findAll();
}
