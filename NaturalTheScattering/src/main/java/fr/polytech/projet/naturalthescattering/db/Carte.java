package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Carte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	private int rarete = 0;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="carte_comptecarte_ref"))
	@Transient
	private CompteCarte[] comptecartes;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="carte_decks_ref"))
	@Transient
	private Deck[] decks;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="carte_ventecartes_ref"))
	@Transient
	private VenteCarte[] ventecartes;
	
	protected Carte() {}
	
	public Carte(String nom, int rarete) {
		this.nom = nom;
		this.rarete = rarete;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getRarete() {
		return this.rarete;
	}
	
	@Override
	public String toString() {
		return "[Carte(id=" + getId() + " | nom=" + getNom() + " | rarete=" + getRarete() + ")]"; 
	}
}
