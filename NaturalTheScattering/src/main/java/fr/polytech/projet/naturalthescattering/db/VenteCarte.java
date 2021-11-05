package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VenteCarte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="ventecarte_carte_ref"))
	private Carte carte;
	
	@ManyToOne(optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="ventecarte_vente_ref"))
	private Vente vente;
	
	private int quantite;
	
	protected VenteCarte() {}
	
	public VenteCarte(Vente vente, Carte carte, int quantite) {
		setVente(vente);
		setCarte(carte);
		setQuantite(quantite);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setVente(Vente vente) {
		this.vente = vente;
	}
	
	public Vente getVente() {
		return this.vente;
	}
	
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public int getQuantite() {
		return this.quantite;
	}
}
