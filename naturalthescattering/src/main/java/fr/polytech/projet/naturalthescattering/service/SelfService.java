package fr.polytech.projet.naturalthescattering.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import fr.polytech.projet.naturalthescattering.db.User;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;
import fr.polytech.projet.naturalthescattering.service.result.SelfResult;
import fr.polytech.projet.naturalthescattering.service.result.SelfResult.ChMdpStatus;
import fr.polytech.projet.naturalthescattering.service.result.SelfResult.DeleteStatus;

@Service
public class SelfService {
	@Autowired
	private IUserRepository users;
	
	public SelfResult.ChMdp setMdp(HttpSession session, String pseudo, String oldpasswd, String newpasswd) {
		SelfResult.ChMdp result = new SelfResult.ChMdp();
		User user = users.findByPseudo(pseudo);
		
		if (!user.setPasswd(oldpasswd, newpasswd)) {
			result.setStatus(ChMdpStatus.PASSWORDS_DONT_MATCH);
			return result;
		}
		
		users.save(user);
		Authentication newAuth = new UsernamePasswordAuthenticationToken(pseudo, newpasswd); // create new Authentication with new password
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(newAuth); // change Authentication
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc); // update context
		
		result.setStatus(ChMdpStatus.OK);
		return result;
	}
	
	public SelfResult.Delete delete(HttpSession session, String pseudo, String password) {
		SelfResult.Delete result  = new SelfResult.Delete();
		
		User user = users.findByPseudo(pseudo);
		
		if (!user.verifyPasswd(password)) {
			result.setStatus(DeleteStatus.PASSWORD_INVALID);
			return result;
		}
		
		users.deleteById(user.getId());
		
		session.invalidate(); // delete session (and authentication)
			
		result.setStatus(DeleteStatus.OK);
		return result;
	}
}
