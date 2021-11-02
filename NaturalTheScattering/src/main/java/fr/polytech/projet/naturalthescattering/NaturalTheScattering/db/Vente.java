package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

@Entity
public class Vente {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private double prix;
	
	@ManyToOne(optional=false)
	private Joueur vendeur;
	
	@ManyToOne(optional=true)
	private Joueur acheteur;
	
	@ManyToMany
	@OrderColumn
	private Carte[] cartes;
	
	@SuppressWarnings("unused")
	private Vente() {}
	
	public Vente(Joueur vendeur, double prix, Carte[] cartes) {
		this.vendeur = vendeur;
		setPrix(prix);
		setCartes(cartes);
	}
	
	public Vente(Joueur vendeur, Joueur acheteur, double prix, Carte[] cartes) {
		this(vendeur, prix, cartes);
		setAcheteur(acheteur);
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public void setAcheteur(Joueur acheteur) {
		this.acheteur = acheteur;
	}
	
	public Joueur getAcheteur() {
		return this.acheteur;
	}
	
	public Joueur getVendeur() {
		return this.vendeur;
	}
	
	public void setCartes(Carte[] cartes) {
		this.cartes = cartes;
	}
	
	public Carte[] getCartes() {
		return this.cartes;
	}
}
