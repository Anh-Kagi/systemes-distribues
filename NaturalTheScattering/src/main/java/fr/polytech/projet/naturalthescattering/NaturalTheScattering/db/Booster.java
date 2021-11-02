package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Booster {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private double prix = 0;
	
	@Temporal(TemporalType.TIME)
	@GeneratedValue
	private Date date;
	
	@ManyToOne(optional=false)
	private Joueur proprietaire;
	
	@SuppressWarnings("unused")
	private Booster() {}
	
	public Booster(Joueur proprietaire, double prix) {
		this.proprietaire = proprietaire;
		this.prix = prix;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
}