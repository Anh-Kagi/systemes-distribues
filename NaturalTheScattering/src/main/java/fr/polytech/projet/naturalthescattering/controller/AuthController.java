package fr.polytech.projet.naturalthescattering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.util.Response;
import fr.polytech.projet.naturalthescattering.service.result.AuthResult;
import fr.polytech.projet.naturalthescattering.service.AuthService;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@Autowired
	private AuthService srv;
	
	@PostMapping(path="/register")
	public ResponseEntity<AuthResult.Register> register(HttpServletRequest req, HttpServletResponse res, @RequestParam(name="username", required=true) String username, @RequestParam(name="password", required=true) String password) {
		return Response.create(srv.register(req.getSession(), username, password));
	}
}
