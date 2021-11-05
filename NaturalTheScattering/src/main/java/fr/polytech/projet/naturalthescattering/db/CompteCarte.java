package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(indexes=@Index(name="uniqueproprietaire", columnList="proprietaire_id", unique=true))
public class CompteCarte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int quantite;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Compte proprietaire;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Carte carte;
	
	@SuppressWarnings("unused")
	private CompteCarte() {}
	
	public CompteCarte(Compte proprietaire, Carte carte, int quantite) {
		setProprietaire(proprietaire);
		setCarte(carte);
		setQuantite(quantite);
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setProprietaire(Compte proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public Compte getProprietaire() {
		return this.proprietaire;
	}
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public int getQuantite() {
		return this.quantite;
	}
	
	@Override
	public String toString() {
		return "[CompteCarte(id=" + getId() + " | proprietaire=" + (getProprietaire() == null ? null : getProprietaire().getId()) + " | carte=" + (getCarte() == null ? null : getCarte().getId()) + " | quantite=" + getQuantite() + ")]";
	}
}
