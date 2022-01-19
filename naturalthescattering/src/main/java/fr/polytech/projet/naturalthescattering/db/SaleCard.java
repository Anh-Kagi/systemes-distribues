package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SaleCard {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="salecard_card_ref"))
	private Card card;
	
	@ManyToOne(optional=false)
	@JoinColumn(foreignKey=@ForeignKey(name="salecard_sale_ref"))
	private Sale sale;
	
	private int quantity;
	
	protected SaleCard() {}
	
	public SaleCard(Sale sale, Card card, int quantity) {
		setSale(sale);
		setCard(card);
		setQuantity(quantity);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public Card getCard() {
		return this.card;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
}
