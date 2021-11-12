package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.db.Joueur;
import fr.polytech.projet.naturalthescattering.db.repository.IJoueurRepository;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@Autowired
	IJoueurRepository joueurs;
	
	private static class RegisterResult {
		private boolean success;
		private String reason;
		
		public RegisterResult() {}
		
		@SuppressWarnings("unused")
		public RegisterResult(boolean success, String reason) {
			setSuccess(success);
			setReason(reason);
		}
		
		public void setSuccess(boolean success) {
			this.success = success;
		}
		
		@SuppressWarnings("unused")
		public boolean getSuccess() {
			return this.success;
		}
		
		public void setReason(String reason) {
			this.reason = reason;
		}
		
		@SuppressWarnings("unused")
		public String getReason() {
			return this.reason;
		}
	}
	
	@PostMapping(path="/register")
	public RegisterResult register(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		RegisterResult result = new RegisterResult();
		if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			if (!joueurs.existsByPseudo(username)) {
				joueurs.save(new Joueur(username, password, 0));
				
				result.setSuccess(true);
				result.setReason("");
				return result;
			} else {
				result.setSuccess(false);
				result.setReason("Username already taken");
				return result;
			}
		}
		result.setSuccess(false);
		result.setReason("missing " + (username == null || username.isEmpty() ? "username" + (password == null || password.isEmpty() ? " & password" : "") : "password"));
		return result;
	}
}
