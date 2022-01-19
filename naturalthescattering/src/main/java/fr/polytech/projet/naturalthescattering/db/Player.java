package fr.polytech.projet.naturalthescattering.db;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.Repository;
import fr.polytech.projet.naturalthescattering.auth.Role;

@Entity
@Component
public class Player extends User {
	private int money = 0;
	
	@ManyToOne
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="player_guild_ref"))
	private Guild guild;
	
	protected Player() {}
	
	public Player(String pseudo, String passwd, int money) {
		super(pseudo, passwd);
		setMoney(money);
	}
	
	public Player(String pseudo, String passwd, int money, Guild guild) {
		this(pseudo, passwd, money);
		setGuild(guild);
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setGuild(Guild guild) {
		this.guild = guild;
	}
	
	public Guild getGuild() {
		return this.guild;
	}
	
	public Guild createGuild(String name) {
		if (getGuild() == null) {
			Guild guild = new Guild(name, this);
			setGuild(guild);
			return guild;
		} else
			return null;
	}
	
	@Override
	public Role getRole() {
		return Role.PLAYER;
	}
	
	@Override
	public String toString() {
		return "[Player(id=" + getId() + " | pseudo=" + getPseudo() + " | money=" + getMoney() + " | guild=" + (getGuild() == null ? null : getGuild().getId()) + ")]";
	}
	
	@PreRemove
	private void onDelete() {
		if (getGuild() != null)
			if (getGuild().getLeader().getId() == getId())
				Repository.guilds.delete(getGuild());
	}
}
