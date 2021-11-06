package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Carte;
import fr.polytech.projet.naturalthescattering.db.Vente;
import fr.polytech.projet.naturalthescattering.db.VenteCarte;

public interface IVenteCarteRepository extends CrudRepository<VenteCarte, Long> {
	public List<VenteCarte> findByVenteAndCarte(Vente vente, Carte carte);
}
