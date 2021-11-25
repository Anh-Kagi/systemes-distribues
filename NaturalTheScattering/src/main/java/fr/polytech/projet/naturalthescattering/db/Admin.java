package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;

import fr.polytech.projet.naturalthescattering.auth.Role;

@Entity
public class Admin extends User {
	protected Admin() {}
	
	public Admin(String pseudo, String mdp) {
		super(pseudo, mdp);
	}
	
	@Override
	public String toString() {
		return "[Admin(id=" + getId() + " | pseudo=" + getPseudo() + ")]";
	}
	
	@Override
	public Role getRole() {
		return Role.ADMIN;
	}
}
