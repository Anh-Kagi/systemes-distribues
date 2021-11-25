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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.db.User;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;

@RestController
@RequestMapping(path="/api/self")
public class SelfController {
	@Autowired
	private IUserRepository users;
	
	@PutMapping(path="/password")
	public ResponseEntity<GenericResult> mdp(HttpServletRequest req, Authentication auth, @RequestParam(name="old", required=true) String oldpasswd, @RequestParam(name="new", required=true) String newpasswd) {
		GenericResult result = new GenericResult();
		User user = users.findByPseudo(auth.getName());
		if (user == null) {
			result.setSuccess(false);
			result.setReason("User not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		} else {
			if (user.setPasswd(oldpasswd, newpasswd)) {
				users.save(user);
			    Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getName(), newpasswd); // create new Authentication with new password
			    SecurityContext sc = SecurityContextHolder.getContext();
			    sc.setAuthentication(newAuth); // change Authentication
			    HttpSession session = req.getSession(); // should not return false (user should be already authenticated)
			    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc); // update context
			    
			    result.setSuccess(true);
			    result.setReason("");
			    return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
			} else {
				result.setSuccess(false);
				result.setReason("old password doesn't match");
				return new ResponseEntity<GenericResult>(result, HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping(path={"/", ""})
	public ResponseEntity<GenericResult> delete(HttpServletRequest req, HttpServletResponse res, Authentication auth, @RequestParam(name="password", required=true) String password) {
		User user = users.findByPseudo(auth.getName());
		GenericResult result = new GenericResult();
		if (user == null) {
			result.setReason("user not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		} else {
			if (user.verifyPasswd(password)) {
				users.deleteById(user.getId());
				
			    HttpSession session = req.getSession(); // should not return false (user should be already authenticated)
				session.invalidate(); // delete session (and authentication)
				
				result.setSuccess(true);
				return new ResponseEntity<GenericResult>(result, HttpStatus.RESET_CONTENT);
			} else {
				result.setReason("password doesn't match");
				return new ResponseEntity<GenericResult>(result, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
