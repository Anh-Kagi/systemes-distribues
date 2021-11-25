package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.db.Player;
import fr.polytech.projet.naturalthescattering.db.repository.IPlayerRepository;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@Autowired
	IPlayerRepository players;
	
	@PostMapping(path="/register")
	public ResponseEntity<GenericResult> register(HttpServletRequest req, HttpServletResponse res, Authentication auth, @RequestParam(name="username", required=true) String username, @RequestParam(name="password", required=true) String password) {
		GenericResult result = new GenericResult();
		if (!username.isEmpty() && !password.isEmpty()) {
			if (!players.existsByPseudo(username)) {
				players.save(new Player(username, password, 0));
				
			    Authentication newAuth = new UsernamePasswordAuthenticationToken(username, password); // create new Authentication with new password
			    SecurityContext sc = SecurityContextHolder.getContext();
			    sc.setAuthentication(newAuth); // change Authentication
			    HttpSession session = req.getSession(true); // should create a session (as user is supposed to be logged out)
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc); // update context
				
				result.setSuccess(true);
				return new ResponseEntity<GenericResult>(result, HttpStatus.CREATED);
			} else {
				result.setReason("Username already taken");
				return new ResponseEntity<GenericResult>(result, HttpStatus.UNAUTHORIZED);
			}
		}
		result.setReason("empty " + (username == null || username.isEmpty() ? "username" + (password == null || password.isEmpty() ? " & password" : "") : "password"));
		return new ResponseEntity<GenericResult>(result, HttpStatus.BAD_REQUEST);
	}
}
