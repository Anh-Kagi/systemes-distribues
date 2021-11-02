package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tournoi {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@Temporal(TemporalType.TIME)
	@GeneratedValue
	private Date date;
	
	private boolean ouvert = true;
	
	@SuppressWarnings("unused")
	private Tournoi() {}
	
	public Tournoi(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}
	
	public boolean getOuvert() {
		return this.ouvert;
	}
}
