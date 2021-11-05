package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(indexes=@Index(name="chef_idx", columnList="chef_id"),
	uniqueConstraints=@UniqueConstraint(name="chef_unq", columnNames="chef_id"))
public class Guilde {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nom;
	
	@OneToOne(optional=false)
	@Cascade({CascadeType.ALL})
	@JoinColumn(foreignKey=@ForeignKey(name="guilde_chef_ref"))
	private Joueur chef;
	
	protected Guilde() {}
	
	public Guilde(String nom, Joueur chef) {
		setNom(nom);
		setChef(chef);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setChef(Joueur chef) {
		this.chef = chef;
	}
	
	public Joueur getChef() {
		return this.chef;
	}
	
	@Override
	public String toString() {
		return "[Guilde(id=" + getId() + " | nom=" + getNom() + " | chef=" + (getChef() == null ? null : getChef().getId()) + ")]";
	}
}
