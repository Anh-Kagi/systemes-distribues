package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Joueur extends Compte {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String mdp;
	
	private int argent = 0;
	
	@OneToOne
	private Guilde guilde;
	
	@SuppressWarnings("unused")
	private Joueur() {}
	
	public Joueur(String pseudo, String mdp, int argent) {
		super(pseudo);
		setMdp(mdp);
		setArgent(argent);
	}
	
	public Joueur(String pseudo, String mdp, int argent, Guilde guilde) {
		this(pseudo, mdp, argent);
		setGuilde(guilde);
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String getMdp() {
		return this.mdp;
	}
	
	public void setArgent(int argent) {
		this.argent = argent;
	}
	
	public int getArgent() {
		return this.argent;
	}
	
	public void setGuilde(Guilde guilde) {
		this.guilde = guilde;
	}
	
	public Guilde getGuilde() {
		return this.guilde;
	}
}
