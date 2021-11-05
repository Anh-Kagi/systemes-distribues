package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;

@Entity
public class Bot extends Compte {
	private int difficulte = 1;
	
	@SuppressWarnings("unused")
	private Bot() {}
	
	public Bot(String pseudo, int difficulte) {
		super(pseudo);
		this.difficulte = difficulte;
	}
	
	public int getDifficulte() {
		return this.difficulte;
	}
	
	@Override
	public String toString() {
		return "[Bot(id=" + getId() + " | pseudo=" + getPseudo() + " | difficulte=" + getDifficulte() + ")]";
	}
}
