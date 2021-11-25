package fr.polytech.projet.naturalthescattering.auth;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.User;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;

@Component
public class AuthProvider implements AuthenticationProvider {
	@Autowired
	IUserRepository users;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = users.findByPseudo(name);
		if (user != null) {
			if (user.verifyPasswd(password))
				return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
		}
		return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}