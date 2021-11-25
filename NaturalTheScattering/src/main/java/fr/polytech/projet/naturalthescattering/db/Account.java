package fr.polytech.projet.naturalthescattering.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(indexes=@Index(name="pseudo_idx", columnList="pseudo"),
	uniqueConstraints=@UniqueConstraint(name="pseudo_unq", columnNames={"pseudo"}))
public abstract class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(nullable=false, length=16)
	private String pseudo;
	
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, targetEntity=AccountCard.class, mappedBy="owner")
	private List<AccountCard> accountcards;
	
	protected Account() {}
	
	public Account(String pseudo) {
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
		return "[Account(id=" + getId() + " | pseudo=" + getPseudo() + ")]";
	}
	
	public List<AccountCard> getAccountCards() {
		return this.accountcards;
	}
}
