package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	@Lob
	@Column(nullable=false)
	private String contenu;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
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
