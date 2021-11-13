package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.db.Utilisateur;
import fr.polytech.projet.naturalthescattering.db.repository.IUtilisateurRepository;

@RestController
@RequestMapping(path="/api/self")
@PreAuthorize("@authVerifier.isPlayer(authentication.name)")
public class UtilisateurController {
	@Autowired
	private IUtilisateurRepository utilisateurs;
	
	@PutMapping(path="/mdp")
	public GenericResult mdp(HttpServletRequest req, Authentication auth, @RequestParam(name="old", required=true) String oldmdp, @RequestParam(name="new", required=true) String newmdp) {
		GenericResult result = new GenericResult();
		Utilisateur utilisateur = utilisateurs.findByPseudo(auth.getName());
		if (utilisateur == null) {
			result.setSuccess(false);
			result.setReason("User not found");
			return result;
		} else {
			if (utilisateur.setMdp(oldmdp, newmdp)) {
				utilisateurs.save(utilisateur);
			    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getName(), newmdp); // create new Authentication with new password
			    SecurityContext sc = SecurityContextHolder.getContext();
			    sc.setAuthentication(newAuth); // change Authentication
			    HttpSession session = req.getSession(); // should not return false (user should be already authenticated)
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc); // update context
			    
			    result.setSuccess(true);
			    result.setReason("");
			    return result;
			} else {
				result.setSuccess(false);
				result.setReason("old password doesn't match");
				return result;
			}
		}
	}	
	@DeleteMapping(path={"/", ""})
	public GenericResult delete(HttpServletRequest req, HttpServletResponse res, Authentication auth, @RequestParam(name="mdp", required=true) String mdp) {
		Utilisateur utilisateur = utilisateurs.findByPseudo(auth.getName());
		GenericResult result = new GenericResult();
		if (utilisateur == null) {
			result.setReason("user not found");
			return result;
		} else {
			if (utilisateur.verifyMdp(mdp)) {
				utilisateurs.deleteById(utilisateur.getId());
				
			    HttpSession session = req.getSession(); // should not return false (user should be already authenticated)
				session.invalidate(); // delete session (and authentication)
				
				result.setSuccess(true);
				return result;
			} else {
				result.setReason("password doesn't match");
				return result;
			}
		}
	}
}
