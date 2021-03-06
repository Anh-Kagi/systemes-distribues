package fr.polytech.projet.naturalthescattering.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Card {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	private int rarity = 0;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="card_accountcards_ref"))
	@Transient
	private List<AccountCard> accountcards;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="card_decks_ref"))
	@Transient
	private List<Deck> decks;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="card_salecards_ref"))
	@Transient
	private List<SaleCard> salecards;
	
	protected Card() {}
	
	public Card(String name, int rarity) {
		this.name = name;
		this.rarity = rarity;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getRarity() {
		return this.rarity;
	}
	
	@Override
	public String toString() {
		return "[Card(id=" + getId() + " | name=" + getName() + " | rarity=" + getRarity() + ")]"; 
	}
}
