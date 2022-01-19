package fr.polytech.projet.naturalthescattering.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import fr.polytech.projet.naturalthescattering.db.Player;
import fr.polytech.projet.naturalthescattering.db.repository.IPlayerRepository;
import fr.polytech.projet.naturalthescattering.service.result.AuthResult;
import fr.polytech.projet.naturalthescattering.service.result.AuthResult.RegisterStatus;

@Service
public class AuthService {
	@Autowired
	private IPlayerRepository players;
	
	public AuthResult.Register register(HttpSession session, String username, String password) {
		AuthResult.Register result = new AuthResult.Register();
		
		if ((username == null || username.isEmpty()) || (password == null || password.isEmpty())) {
			if (!(password == null || password.isEmpty()))
				result.setStatus(RegisterStatus.EMPTY_USERNAME);
			if (!(username == null || username.isEmpty()))
				result.setStatus(RegisterStatus.EMPTY_PASSWORD);
			if ((username == null || username.isEmpty()) && (password == null || password.isEmpty()))
				result.setStatus(RegisterStatus.EMPTY_USERNAME_AND_PASSWORD);
			return result;
		}
		
		if (players.existsByPseudo(username)) {
			result.setStatus(RegisterStatus.USERNAME_TAKEN);
			return result;
		}
		
		players.save(new Player(username, password, 0));
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(username, password); // create new Authentication with new password
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(newAuth); // change Authentication
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc); // update context
				
		result.setStatus(RegisterStatus.OK);
		return result;
	}
}
