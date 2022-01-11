package fr.polytech.projet.naturalthescattering.db;

import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Deck {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToOne
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="deck_owner_ref"))
	private Account owner;
	
	@ManyToMany
	@OrderColumn(nullable=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="deck_cards_ref"))
	private List<Card> cards;
	
	protected Deck() {}
	
	public Deck(String name, Account owner, List<Card> cards) {
		this.name = name;
		this.owner = owner;
		this.cards = cards;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Account getOner() {
		return this.owner;
	}
	
	public List<Card> getCards() {
		return this.cards;
	}
	
	@Override
	public String toString() {
		String cards_ids = "[";
		for (Card c : getCards())
			cards_ids += " " + c.getId();
		cards_ids += " ]";
		return "[Deck(id=" + getId() + " | name=" + getName() + " | cards=" + cards_ids + ")]";
	}
}
