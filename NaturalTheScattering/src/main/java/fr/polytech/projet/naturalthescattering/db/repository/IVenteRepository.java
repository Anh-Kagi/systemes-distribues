package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Vente;

public interface IVenteRepository extends JpaRepository<Vente, Long> {
}
