package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes=@Index(name="uniquePseudo", columnList="pseudo", unique=true))
public class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, length=16)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String pseudo;
	
	protected Compte() {}
	
	public Compte(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getPseudo() {
		return this.pseudo;
	}
	
	@Override
	public String toString() {
		return "[Compte(id=" + getId() + " | pseudo=" + getPseudo() + ")]";
	}
}
