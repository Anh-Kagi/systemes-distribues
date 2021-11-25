package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Tournament {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	private boolean open = true;
	
	@OneToMany(cascade=CascadeType.MERGE, mappedBy="tournament_id")
	@Transient
	@JoinColumn(foreignKey=@ForeignKey(name="tournament_duels_ref"))
	private Duel[] duels;
	
	protected Tournament() {}
	
	public Tournament(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Date getDate() {
		return this.date;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean getOpen() {
		return this.open;
	}
	
	public Duel[] getDuels() {
		return this.duels;
	}
	
	@Override
	public String toString() {
		return "[Tournament(id=" + getId() + " | name=" + getName() + " | date=" + getDate() + " | open=" + getOpen() + ")]";
	}
}
