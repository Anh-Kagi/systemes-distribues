package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Deck {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
	private Compte proprietaire;
	
	@ManyToMany
	@OrderColumn(nullable=false)
	@Cascade({CascadeType.ALL})
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
