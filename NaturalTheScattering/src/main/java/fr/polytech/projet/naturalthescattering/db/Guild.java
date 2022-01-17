package fr.polytech.projet.naturalthescattering.db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(indexes={@Index(name="leader_idx", columnList="leader_id"), @Index(name="name_idx", columnList="name")},
	uniqueConstraints={@UniqueConstraint(name="leader_unq", columnNames="leader_id"), @UniqueConstraint(name="name_unq", columnNames="name")})
public class Guild {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="guild_leader_ref"))
	private Player leader;
	
	@OneToMany(targetEntity=Player.class, mappedBy="id")
	private List<Player> players;
	
	protected Guild() {}
	
	public Guild(String name, Player leader) {
		setName(name);
		setLeader(leader);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setLeader(Player leader) {
		this.leader = leader;
	}
	
	public Player getLeader() {
		return this.leader;
	}
	
	@Override
	public String toString() {
		return "[Guild(id=" + getId() + " | name=" + getName() + " | leader=" + (getLeader() == null ? null : getLeader().getId()) + ")]";
	}
}
