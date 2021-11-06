package fr.polytech.projet.naturalthescattering.auth;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.Utilisateur;
import fr.polytech.projet.naturalthescattering.db.repository.IUtilisateurRepository;

@Component
public class AuthVerifier {
	@Autowired
	private IUtilisateurRepository utilisateurs;
	
	@Autowired
	private Logger log;
	
	public boolean isAdmin(String pseudo) {
		log.info("verifying user for admin rights: " + pseudo);
		Utilisateur utilisateur = utilisateurs.findByPseudo(pseudo);
		if (utilisateur != null) {
			return utilisateur.getRole().level() <= Role.ADMIN.level();
		}
		return false;
	}
	
	public boolean isPlayer(String pseudo) {
		log.info("verifying user for admin rights: " + pseudo);
		Utilisateur utilisateur = utilisateurs.findByPseudo(pseudo);
		if (utilisateur != null) {
			return utilisateur.getRole().level() <= Role.PLAYER.level();
		}
		return false;
	}
	
	public boolean isGuest(String pseudo) {
		log.info("verifying user for admin rights: " + pseudo);
		Utilisateur utilisateur = utilisateurs.findByPseudo(pseudo);
		if (utilisateur != null) {
			return utilisateur.getRole().level() <= Role.GUEST.level();
		}
		return false;
	}
}
