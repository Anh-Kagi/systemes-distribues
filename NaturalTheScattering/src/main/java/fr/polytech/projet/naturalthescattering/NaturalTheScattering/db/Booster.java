package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Booster {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private double prix = 0;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
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