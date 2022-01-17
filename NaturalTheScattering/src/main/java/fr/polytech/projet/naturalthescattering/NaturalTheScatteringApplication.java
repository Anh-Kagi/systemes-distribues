package fr.polytech.projet.naturalthescattering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import fr.polytech.projet.naturalthescattering.db.Admin;
import fr.polytech.projet.naturalthescattering.db.Card;
import fr.polytech.projet.naturalthescattering.db.Player;

@SpringBootApplication
public class NaturalTheScatteringApplication {
	public static void main(String[] args) {
		SpringApplication.run(NaturalTheScatteringApplication.class, args);
	}
	
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(NaturalTheScatteringApplication.class);
	}
	
	@Bean
	@DependsOn("pbkdf2")
	public CommandLineRunner db_init() {
		return (args) -> {
			Repository.players.save(new Player("tmp", "tmp", 0));
			Repository.admins.save(new Admin("root", "root"));
			
			for (int i=1; i<=10; i++)
				Repository.cards.save(new Card("Carte " + i, i, 10, 10, 10));
		};
	}
}