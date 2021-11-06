package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="deck_proprietaire_ref"))
	private Compte proprietaire;
	
	@ManyToMany
	@OrderColumn(nullable=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="deck_cartes_ref"))
	private Carte[] cartes;
	
	protected Deck() {}
	
	public Deck(String nom, Compte proprietaire, Carte[] cartes) {
		this.nom = nom;
		this.proprietaire = proprietaire;
		this.cartes = cartes;
	}
	
	public Long getId() {
		return this.id;
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
	
	@Override
	public String toString() {
		String cartes_ids = "[";
		for (Carte c : getCartes())
			cartes_ids += " " + c.getId();
		cartes_ids += " ]";
		return "[Deck(id=" + getId() + " | nom=" + getNom() + " | cartes=" + cartes_ids + ")]";
	}
}
