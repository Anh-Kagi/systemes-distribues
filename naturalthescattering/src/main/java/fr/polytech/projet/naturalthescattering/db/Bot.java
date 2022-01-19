package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;

@Entity
public class Bot extends Account {
	private int difficulty = 1;
	
	protected Bot() {}
	
	public Bot(String pseudo, int difficulty) {
		super(pseudo);
		this.difficulty = difficulty;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	@Override
	public String toString() {
		return "[Bot(id=" + getId() + " | pseudo=" + getPseudo() + " | difficulty=" + getDifficulty() + ")]";
	}
}
