package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;

import fr.polytech.projet.naturalthescattering.Config;
import fr.polytech.projet.naturalthescattering.auth.Role;

@Entity
public abstract class Utilisateur extends Compte {
	@Column(nullable=false, length=(Config.pbkdf2HashWidth+Config.pbkdf2SaltSize*8)/4)
	private String mdp;
	
	protected Utilisateur() {}
	
	public Utilisateur(String pseudo, String password) {
		super(pseudo);
		setMdp(null, password);
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
	
	public Role getRole() {
		return Role.GUEST;
	}
	
	@Override
	public String toString() {
		return "[Utilisateur(id=" + getId() + " | pseudo=" + getPseudo() + ")]";
	}
}
