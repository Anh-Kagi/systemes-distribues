package fr.polytech.projet.naturalthescattering;

import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class Config {
	public static final String pbkdf2Secret = "ThisIsASuperDuperEffectiveSecret";
	public static final int pbkdf2SaltSize = 64;
	public static final int pbkdf2Iterations = 1000000;
	public static final int pbkdf2HashWidth = 512;
	public static PasswordEncoder pbkdf2; // autowiring inside Joueur doesn't work :rage:
}
