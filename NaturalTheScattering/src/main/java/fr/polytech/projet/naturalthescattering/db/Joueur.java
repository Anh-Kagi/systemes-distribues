package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.context.annotation.DependsOn;
import fr.polytech.projet.naturalthescattering.Config;

@Entity
@DependsOn(value="pbkdf2")
public class Joueur extends Compte {
	@Column(nullable=false, length=(Config.pbkdf2HashWidth+Config.pbkdf2SaltSize*8)/4)
	private String mdp;
	
	private int argent = 0;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
	private Guilde guilde;
	
	@SuppressWarnings("unused")
	private Joueur() {}
	
	public Joueur(String pseudo, String mdp, int argent) {
		super(pseudo);
		setMdp(null, mdp);
		setArgent(argent);
	}
	
	public Joueur(String pseudo, String mdp, int argent, Guilde guilde) {
		this(pseudo, mdp, argent);
		setGuilde(guilde);
	}
	
	public boolean setMdp(String oldMdp, String newMdp) {
		if (this.mdp == null || verifyMdp(oldMdp)) {
			this.mdp = Config.pbkdf2.encode(newMdp);
			return true;
		} else
			return false;
	}
	
	public boolean verifyMdp(String mdp) {
		return Config.pbkdf2.matches(mdp, this.mdp);
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
}
