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
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Sale {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private double price;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="sale_seller_ref"))
	private Player seller;
	
	@ManyToOne(optional=true)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="sale_buyer_ref"))
	private Player buyer;
	
	@ManyToMany
	@OrderColumn
	@Transient
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="sale_salecards_ref"))
	private List<SaleCard> salecards;
	
	protected Sale() {}
	
	public Sale(Player seller, double price, List<SaleCard> salecards) {
		this.seller = seller;
		setPrice(price);
		setSaleCards(salecards);
	}
	
	public Sale(Player seller, Player buyer, double price, List<SaleCard> salecards) {
		this(seller, price, salecards);
		setBuyer(buyer);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setBuyer(Player buyer) {
		this.buyer = buyer;
	}
	
	public Player getBuyer() {
		return this.buyer;
	}
	
	public Player getSeller() {
		return this.seller;
	}
	
	public void setSaleCards(List<SaleCard> salecards) {
		this.salecards = salecards;
	}
	
	public List<SaleCard> getSaleCards() {
		return this.salecards;
	}
	
	@Override
	public String toString() {
		String cards_ids = "[";
		for (SaleCard c : getSaleCards())
			cards_ids += " " + c.getId();
		cards_ids += " ]";
		return "[Sale(id=" + getId() + " | price=" + getPrice() + " | seller=" + (getSeller() == null ? null : getSeller().getId()) + " | buyer=" + (getBuyer() == null ? null : getBuyer().getId()) + " | cards=" + cards_ids + ")]";
	}
}
