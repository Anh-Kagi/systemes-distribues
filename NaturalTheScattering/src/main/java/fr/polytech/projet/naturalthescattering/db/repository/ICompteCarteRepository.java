package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Carte;
import fr.polytech.projet.naturalthescattering.db.Compte;
import fr.polytech.projet.naturalthescattering.db.CompteCarte;

public interface ICompteCarteRepository extends JpaRepository<CompteCarte, Long> {
	public CompteCarte findByProprietaire(Compte proprietaire);
	public CompteCarte findByProprietaireAndCarte(Compte proprietaire, Carte carte);
	public void removeByProprietaire(Compte proprietaire);
}