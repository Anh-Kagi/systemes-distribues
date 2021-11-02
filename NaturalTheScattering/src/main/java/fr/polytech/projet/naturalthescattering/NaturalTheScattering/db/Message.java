package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIME)
	@GeneratedValue
	private Date date;
	
	@Lob
	@Column(nullable=false)
	private String contenu;
	
	@ManyToOne(optional=false)
	private Joueur auteur;
	
	@SuppressWarnings("unused")
	private Message() {}
	
	public Message(Joueur auteur, String contenu) {
		this.auteur = auteur;
		setContenu(contenu);
	}
	
	public Joueur getAuteur() {
		return this.auteur;
	}
	
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	public String getContenu() {
		return this.contenu;
	}
}
