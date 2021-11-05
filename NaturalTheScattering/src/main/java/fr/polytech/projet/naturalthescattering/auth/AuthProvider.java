package fr.polytech.projet.naturalthescattering.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.Utilisateur;
import fr.polytech.projet.naturalthescattering.db.repository.IUtilisateurRepository;

@Component
public class AuthProvider implements AuthenticationProvider {
	@Autowired
	IUtilisateurRepository utilisateurs;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Utilisateur user = utilisateurs.findByPseudo(name);
		if (user != null) {
			if (user.verifyMdp(password))
				return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
		}
		return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}