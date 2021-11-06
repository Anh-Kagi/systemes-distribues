package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Carte;

public interface ICarteRepository extends CrudRepository<Carte, Long> {
	@Override
	public List<Carte> findAll();
}
