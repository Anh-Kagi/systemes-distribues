package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Thread {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	private boolean ouvert = true;
	
	@ManyToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="thread_proprietaire_ref"))
	private Utilisateur proprietaire;
	
	protected Thread() {}
	
	public Thread(String nom, Utilisateur proprietaire) {
		this.nom = nom;
		this.proprietaire = proprietaire;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Utilisateur getProprietaire() {
		return this.proprietaire;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean getOuvert() {
		return this.ouvert;
	}
	
	@Override
	public String toString() {
		return "[Thread(id=" + getId() + " | nom=" + getNom() + " | date=" + getDate() + " | ouvert=" + getOuvert() + " | proprietaire=" + (getProprietaire() == null ? null : getProprietaire().getId()) + ")]";
	}
}
