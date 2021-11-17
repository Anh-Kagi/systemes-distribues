package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Thread {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nom;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	private boolean ouvert = true;
	
	@Lob
	private String contenu;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(foreignKey=@ForeignKey(name="thread_auteur_ref"))
	private Utilisateur auteur;
	
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, targetEntity=Message.class, mappedBy="thread")
	private List<CompteCarte> comptecartes;
	
	protected Thread() {}
	
	public Thread(String nom, Utilisateur auteur, String contenu) {
		this.nom = nom;
		this.auteur = auteur;
		this.contenu = contenu;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Utilisateur getAuteur() {
		return this.auteur;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean getOuvert() {
		return this.ouvert;
	}
	
	public void setOuvert(boolean ouvert) {
		this.ouvert = ouvert;
	}
	
	public String getContenu() {
		return this.contenu;
	}
	
	@Override
	public String toString() {
		return "[Thread(id=" + getId() + " | nom=" + getNom() + " | date=" + getDate() + " | ouvert=" + getOuvert() + " | auteur=" + (getAuteur() == null ? null : getAuteur().getId()) + ")]";
	}
}
