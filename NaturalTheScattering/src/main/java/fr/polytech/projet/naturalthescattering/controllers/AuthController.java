package fr.polytech.projet.naturalthescattering.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/auth")
public class AuthController {
	@PostMapping(path="/login")
	public void login(@RequestParam String pseudo, @RequestParam String mdp) {
		System.out.println("[POST:/api/auth/login] User tried to connect ('" + pseudo + "':'" + mdp + "')");
	}
}
