package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Guilde {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@OneToOne(optional=false)
	@Cascade({CascadeType.ALL})
	private Joueur chef;
	
	@SuppressWarnings("unused")
	private Guilde() {}
	
	public Guilde(String nom, Joueur chef) {
		setNom(nom);
		setChef(chef);
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setChef(Joueur chef) {
		this.chef = chef;
	}
	
	public Joueur getChef() {
		return this.chef;
	}
	
	@Override
	public String toString() {
		return "[Guilde(id=" + getId() + " | nom=" + getNom() + " | chef=" + (getChef() == null ? null : getChef().getId()) + ")]";
	}
}
