package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Entity
public class Joueur extends Compte {
	private static final String pbkdf2Secret = "ThisIsASuperDuperEffectiveSecret";
	private static final int pbkdf2SaltSize = 64;
	private static final int pbkdf2Iterations = 1000000;
	private static final int pbkdf2HashWidth = 512;
	private static final Pbkdf2PasswordEncoder pbkdf2 = new Pbkdf2PasswordEncoder(pbkdf2Secret, pbkdf2SaltSize, pbkdf2Iterations, pbkdf2HashWidth);
	
	@Column(nullable=false, length=(pbkdf2HashWidth+pbkdf2SaltSize*8)/4)
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
			this.mdp = pbkdf2.encode(newMdp);
			return true;
		} else
			return false;
	}
	
	public boolean verifyMdp(String mdp) {
		return pbkdf2.matches(mdp, this.mdp);
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
