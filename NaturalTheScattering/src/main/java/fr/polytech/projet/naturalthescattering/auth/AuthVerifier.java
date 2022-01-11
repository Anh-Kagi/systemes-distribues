package fr.polytech.projet.naturalthescattering.auth;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.User;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;

@Component
public class AuthVerifier {
	@Autowired
	private IUserRepository users;
	
	@Autowired
	private Logger log;
	
	public boolean isAdmin(String pseudo) {
		log.info("verifying user for admin rights: " + pseudo);
		User user = users.findByPseudo(pseudo);
		if (user != null) {
			return user.getRole().level() <= Role.ADMIN.level();
		}
		return false;
	}
	
	public boolean isPlayer(String pseudo) {
		log.info("verifying user for player rights: " + pseudo);
		User user = users.findByPseudo(pseudo);
		if (user != null) {
			return user.getRole().level() <= Role.PLAYER.level();
		}
		return false;
	}
	
	public boolean isGuest(String pseudo) {
		log.info("verifying user for admin rights: " + pseudo);
		User user = users.findByPseudo(pseudo);
		if (user != null) {
			return user.getRole().level() <= Role.GUEST.level();
		}
		return false;
	}
}
