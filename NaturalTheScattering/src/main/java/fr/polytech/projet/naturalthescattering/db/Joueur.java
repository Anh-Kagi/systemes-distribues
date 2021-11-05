package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import fr.polytech.projet.naturalthescattering.auth.Role;

@Entity
public class Joueur extends Utilisateur {
	private int argent = 0;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="joueur_guilde_ref"))
	private Guilde guilde;
	
	protected Joueur() {}
	
	public Joueur(String pseudo, String mdp, int argent) {
		super(pseudo, mdp);
		setArgent(argent);
	}
	
	public Joueur(String pseudo, String mdp, int argent, Guilde guilde) {
		this(pseudo, mdp, argent);
		setGuilde(guilde);
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
	
	public Guilde createGuilde(String nom) {
		if (getGuilde() == null) {
			Guilde guilde = new Guilde(nom, this);
			setGuilde(guilde);
			return guilde;
		} else
			return null;
	}
	
	@Override
	public Role getRole() {
		return Role.PLAYER;
	}
	
	@Override
	public String toString() {
		return "[Joueur(id=" + getId() + " | pseudo=" + getPseudo() + " | argent=" + getArgent() + " | guilde=" + (getGuilde() == null ? null : getGuilde().getId()) + ")]";
	}
}
