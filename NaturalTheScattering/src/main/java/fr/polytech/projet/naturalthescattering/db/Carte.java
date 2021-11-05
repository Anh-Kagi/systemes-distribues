package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public enum Carte {
	Test1("Carte_test1", 1000),
	Test2("Carte_test2", 9000);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	private int rarete = 0;
	
	//@SuppressWarnings("unused")
	//private Carte() {}
	
	private Carte(String nom, int rarete) {
		this.nom = nom;
		this.rarete = rarete;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getRarete() {
		return this.rarete;
	}
	
	@Override
	public String toString() {
		return "[Carte(id=" + getId() + " | nom=" + getNom() + " | rarete=" + getRarete() + ")]"; 
	}
}
