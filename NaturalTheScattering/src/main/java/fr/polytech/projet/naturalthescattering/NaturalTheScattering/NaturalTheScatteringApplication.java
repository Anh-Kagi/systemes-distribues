package fr.polytech.projet.naturalthescattering.NaturalTheScattering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.Joueur;

@SpringBootApplication
public class NaturalTheScatteringApplication {
	@Autowired
	Repository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(NaturalTheScatteringApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dbdemo() {
		return (args) -> {
			Joueur pedro = new Joueur("XxxPedroLePloxxX", "NotAPlo", 1000000000);
			Joueur pablo = new Joueur("XxxPabloLePloxxX", "IsAPlo", -1000000000);
			//repo.joueurs.save(pedro);
			repo.joueurs.save(pablo);
			
			pedro.createGuilde("Ordre");
			repo.joueurs.save(pedro);
		};
	}
}