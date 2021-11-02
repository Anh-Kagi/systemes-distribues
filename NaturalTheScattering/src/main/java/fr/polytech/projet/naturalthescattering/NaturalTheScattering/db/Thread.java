package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Thread {
	@Id
	@GeneratedValue
	private Long id;
	
	private String nom;
	
	@Temporal(TemporalType.TIME)
	private Date date;
	
	private boolean ouvert = true;
	
	@ManyToOne
	private Joueur proprietaire;
	
	@SuppressWarnings("unused")
	private Thread() {}
	
	public Thread(String nom, Joueur proprietaire) {
		this.nom = nom;
		this.proprietaire = proprietaire;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Joueur getProprietaire() {
		return this.proprietaire;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean getOuvert() {
		return this.ouvert;
	}
}
