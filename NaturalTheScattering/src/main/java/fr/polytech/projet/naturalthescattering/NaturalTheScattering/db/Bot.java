package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bot extends Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
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
}
