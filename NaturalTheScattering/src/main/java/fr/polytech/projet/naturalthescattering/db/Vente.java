package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Vente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private double prix;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="vente_vendeur_ref"))
	private Joueur vendeur;
	
	@ManyToOne(optional=true)
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="vente_acheteur_ref"))
	private Joueur acheteur;
	
	@ManyToMany
	@OrderColumn
	@Transient
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="vente_ventecartes_ref"))
	private VenteCarte[] ventecartes;
	
	protected Vente() {}
	
	public Vente(Joueur vendeur, double prix, VenteCarte[] ventecartes) {
		this.vendeur = vendeur;
		setPrix(prix);
		setVenteCartes(ventecartes);
	}
	
	public Vente(Joueur vendeur, Joueur acheteur, double prix, VenteCarte[] ventecartes) {
		this(vendeur, prix, ventecartes);
		setAcheteur(acheteur);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	public void setAcheteur(Joueur acheteur) {
		this.acheteur = acheteur;
	}
	
	public Joueur getAcheteur() {
		return this.acheteur;
	}
	
	public Joueur getVendeur() {
		return this.vendeur;
	}
	
	public void setVenteCartes(VenteCarte[] ventecartes) {
		this.ventecartes = ventecartes;
	}
	
	public VenteCarte[] getVenteCartes() {
		return this.ventecartes;
	}
	
	@Override
	public String toString() {
		String cartes_ids = "[";
		for (VenteCarte c : getVenteCartes())
			cartes_ids += " " + c.getId();
		cartes_ids += " ]";
		return "[Vente(id=" + getId() + " | prix=" + getPrix() + " | vendeur=" + (getVendeur() == null ? null : getVendeur().getId()) + " | acheteur=" + (getAcheteur() == null ? null : getAcheteur().getId()) + " | cartes=" + cartes_ids + ")]";
	}
}
