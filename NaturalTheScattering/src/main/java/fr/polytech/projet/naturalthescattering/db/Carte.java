package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Carte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	private int rarete = 0;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
	private Booster booster;
	
	@SuppressWarnings("unused")
	private Carte() {}
	
	public Carte(String nom, int rarete, Booster booster) {
		this.nom = nom;
		this.rarete = rarete;
		this.booster = booster;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getRarete() {
		return this.rarete;
	}
	
	public Booster getBooster() {
		return this.booster;
	}
}
