package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(indexes=@Index(name="pseudo_idx", columnList="pseudo"),
	uniqueConstraints=@UniqueConstraint(name="pseudo_unq", columnNames={"pseudo"}))
public abstract class Compte {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, length=16)
	private Long id;
	
	@Column(nullable=false)
	private String pseudo;
	
	@OneToMany(cascade=CascadeType.MERGE)
	@Transient
	private CompteCarte[] comptecartes;
	
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
