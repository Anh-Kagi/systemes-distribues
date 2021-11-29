package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.util.Response;
import fr.polytech.projet.naturalthescattering.service.SelfService;
import fr.polytech.projet.naturalthescattering.service.result.SelfResult;

@RestController
@RequestMapping(path="/api/self")
public class SelfController {	
	@Autowired
	private SelfService srv;
	
	@PutMapping(path="/password")
	public ResponseEntity<SelfResult.ChMdp> mdp(HttpServletRequest req, Authentication auth, @RequestParam(name="old", required=true) String oldpasswd, @RequestParam(name="new", required=true) String newpasswd) {
		return Response.create(srv.setMdp(req.getSession(), auth.getName(), oldpasswd, newpasswd));
	}
	
	@DeleteMapping(path={"/", ""})
	public ResponseEntity<SelfResult.Delete> delete(HttpServletRequest req, HttpServletResponse res, Authentication auth, @RequestParam(name="password", required=true) String password) {
		return Response.create(srv.delete(req.getSession(), auth.getName(), password));
	}
}
