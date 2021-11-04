package fr.polytech.projet.naturalthescattering.NaturalTheScattering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NaturalTheScatteringApplication {
	@Autowired
	Repository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(NaturalTheScatteringApplication.class, args);
	}
}