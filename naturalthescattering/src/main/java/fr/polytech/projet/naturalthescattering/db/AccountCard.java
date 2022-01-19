package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(indexes= {@Index(name="owner_idx", columnList="owner_id"), @Index(name="card_idx", columnList="card_id")},
	uniqueConstraints=@UniqueConstraint(name="owner_card_unq", columnNames={"owner_id", "card_id"}))
public class AccountCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="accountcard_owner_ref"))
	private Account owner;
	
	@ManyToOne(cascade=CascadeType.MERGE, optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="accountcard_card_ref"))
	private Card card;
	
	protected AccountCard() {}
	
	public AccountCard(Account owner, Card card, int quantity) {
		setOwner(owner);
		setCard(card);
		setQuantity(quantity);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setOwner(Account owner) {
		this.owner = owner;
	}
	
	public Account getOwner() {
		return this.owner;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public Card getCard() {
		return this.card;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void incQuantity() {
		this.quantity++;
	}
	
	@Override
	public String toString() {
		return "[AccountCard(id=" + getId() + " | owner=" + (getOwner() == null ? null : getOwner().getId()) + " | card=" + (getCard() == null ? null : getCard().getId()) + " | quantity=" + getQuantity() + ")]";
	}
}
