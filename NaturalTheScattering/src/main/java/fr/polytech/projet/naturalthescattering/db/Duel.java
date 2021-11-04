package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Duel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	@CreationTimestamp
	private Date date;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
	private Compte p1;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.ALL})
	private Compte p2;
	
	@SuppressWarnings("unused")
	private Duel() {}
	
	public Duel(Compte p1) {
		setP1(p1);
	}
	
	public Duel(Compte p1, Compte p2) {
		setP1(p1);
		setP2(p2);
	}
	
	public void setP1(Compte p1) {
		this.p1 = p1;
	}
	
	public Compte getP1() {
		return this.p1;
	}
	
	public void setP2(Compte p2) {
		this.p2 = p2;
	}
	
	public Compte getP2() {
		return this.p2;
	}
}
