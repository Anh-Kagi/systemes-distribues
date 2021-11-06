package fr.polytech.projet.naturalthescattering.auth;

public enum Role {
	ADMIN(0),
	PLAYER(1),
	GUEST(2);
	
	private int level;
	
	private Role(int level) {
		this.level = level;
	}
	
	public int level() {
		return this.level;
	}
}
