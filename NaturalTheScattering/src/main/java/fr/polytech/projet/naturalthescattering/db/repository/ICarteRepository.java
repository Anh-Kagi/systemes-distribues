package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Carte;

public interface ICarteRepository extends CrudRepository<Carte, Long> {
}
