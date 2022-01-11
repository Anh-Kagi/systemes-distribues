package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(indexes= {@Index(name="tournament_idx", columnList="tournament_id"),
		@Index(name="p1_idx", columnList="p1_id"),
		@Index(name="p2_idx", columnList="p2_id")})
public class Duel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	@CreationTimestamp
	private Date date;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="duel_p1_ref"))
	private Account p1;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="duel_p2_ref"))
	private Account p2;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="duel_tournoi_ref"))
	private Tournament tournament;
	
	protected Duel() {}
	
	public Duel(Account p1) {
		setP1(p1);
	}
	
	public Duel(Account p1, Account p2) {
		setP1(p1);
		setP2(p2);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setP1(Account p1) {
		this.p1 = p1;
	}
	
	public Account getP1() {
		return this.p1;
	}
	
	public void setP2(Account p2) {
		this.p2 = p2;
	}
	
	public Account getP2() {
		return this.p2;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}
	
	public Tournament getTournament() {
		return this.tournament;
	}
	
	@Override
	public String toString() {
		return "[Duel(id=" + getId() + " | p1=" + (getP1() == null ? null : getP1().getId()) + " | p2=" + (getP2() == null ? null : getP2().getId()) + " | date=" + getDate() + " | tournament=" + (getTournament() == null ? null : getTournament().getId()) + ")]";
	}
}
