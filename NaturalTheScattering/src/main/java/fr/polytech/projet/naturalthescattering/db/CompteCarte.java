package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(indexes= {@Index(name="proprietaire_idx", columnList="proprietaire_id"), @Index(name="carte_idx", columnList="carte_id")},
	uniqueConstraints=@UniqueConstraint(name="proprietaire_carte_unq", columnNames={"proprietaire_id", "carte_id"}))
public class CompteCarte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int quantite;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="comptecarte_proprietaire_ref"))
	private Compte proprietaire;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="comptecarte_carte_ref"))
	private Carte carte;
	
	protected CompteCarte() {}
	
	public CompteCarte(Compte proprietaire, Carte carte, int quantite) {
		setProprietaire(proprietaire);
		setCarte(carte);
		setQuantite(quantite);
	}
	
	public Long getId() {
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
	
	public void incQuantite() {
		this.quantite++;
	}
	
	@Override
	public String toString() {
		return "[CompteCarte(id=" + getId() + " | proprietaire=" + (getProprietaire() == null ? null : getProprietaire().getId()) + " | carte=" + (getCarte() == null ? null : getCarte().getId()) + " | quantite=" + getQuantite() + ")]";
	}
}
