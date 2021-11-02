package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Compte {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String pseudo;
	
	protected Compte() {}
	
	public Compte(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
}
