package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Tournoi {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	private boolean ouvert = true;
	
	@OneToMany(cascade=CascadeType.ALL)
	@OrderColumn
	private Duel[] duels;
	
	@SuppressWarnings("unused")
	private Tournoi() {}
	
	public Tournoi(String nom) {
		this.nom = nom;
	}
	
	public long getId() {
		return this.id;
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
	
	public Duel[] getDuels() {
		return this.duels;
	}
	
	@Override
	public String toString() {
		return "[Tournoi(id=" + getId() + " | nom=" + getNom() + " | date=" + getDate() + " | ouvert=" + getOuvert() + ")]";
	}
}
