package fr.polytech.projet.naturalthescattering.db;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import fr.polytech.projet.naturalthescattering.Config;
import fr.polytech.projet.naturalthescattering.auth.Role;

@Entity
public abstract class User extends Account {
	@Column(nullable=false, length=(Config.pbkdf2HashWidth+Config.pbkdf2SaltSize*8)/4)
	private String passwd;
	
	@OneToMany(cascade={CascadeType.MERGE,CascadeType.REMOVE}, targetEntity=Thread.class, mappedBy="author")
	private List<Thread> threads;
	
	protected User() {}
	
	public User(String pseudo, String password) {
		super(pseudo);
		setPasswd(null, password);
	}

	public boolean setPasswd(String oldPasswd, String newPasswd) {
		if (this.passwd == null || verifyPasswd(oldPasswd)) {
			this.passwd = Config.pbkdf2.encode(newPasswd);
			return true;
		} else
			return false;
	}
	
	public boolean verifyPasswd(String passwd) {
		return Config.pbkdf2.matches(passwd, this.passwd);
	}
	
	public String getPasswd() {
		return this.passwd;
	}
	
	public Role getRole() {
		return Role.GUEST;
	}
	
	@Override
	public String toString() {
		return "[User(id=" + getId() + " | pseudo=" + getPseudo() + ")]";
	}
}
