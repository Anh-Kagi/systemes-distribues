package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

@Entity
public class Deck {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@ManyToOne
	private Compte proprietaire;
	
	@ManyToMany
	@OrderColumn(nullable=false)
	private Carte[] cartes;
	
	@SuppressWarnings("unused")
	private Deck() {}
	
	public Deck(String nom, Compte proprietaire, Carte[] cartes) {
		this.nom = nom;
		this.proprietaire = proprietaire;
		this.cartes = cartes;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Compte getProprietaire() {
		return this.proprietaire;
	}
	
	public Carte[] getCartes() {
		return this.cartes;
	}
}
