package fr.polytech.projet.naturalthescattering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import fr.polytech.projet.naturalthescattering.auth.AuthProvider;
import fr.polytech.projet.naturalthescattering.db.Admin;
import fr.polytech.projet.naturalthescattering.db.Bot;
import fr.polytech.projet.naturalthescattering.db.Carte;
import fr.polytech.projet.naturalthescattering.db.Compte;
import fr.polytech.projet.naturalthescattering.db.CompteCarte;
import fr.polytech.projet.naturalthescattering.db.Deck;
import fr.polytech.projet.naturalthescattering.db.Duel;
import fr.polytech.projet.naturalthescattering.db.Guilde;
import fr.polytech.projet.naturalthescattering.db.Joueur;
import fr.polytech.projet.naturalthescattering.db.Message;
import fr.polytech.projet.naturalthescattering.db.Thread;
import fr.polytech.projet.naturalthescattering.db.Tournoi;
import fr.polytech.projet.naturalthescattering.db.Utilisateur;
import fr.polytech.projet.naturalthescattering.db.Vente;
import fr.polytech.projet.naturalthescattering.db.VenteCarte;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class NaturalTheScatteringApplication extends WebSecurityConfigurerAdapter {
	@Autowired
	Repository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(NaturalTheScatteringApplication.class, args);
	}
	
	@Bean
	public Logger getLogger() {
		return LoggerFactory.getLogger(NaturalTheScatteringApplication.class);
	}
	
	@Bean("pbkdf2")
	public PasswordEncoder passwordEncoder() {
		Config.pbkdf2 = new Pbkdf2PasswordEncoder(Config.pbkdf2Secret, Config.pbkdf2SaltSize, Config.pbkdf2Iterations, Config.pbkdf2HashWidth);
		return Config.pbkdf2;
	}
	
	@Bean
	@DependsOn("pbkdf2")
	public CommandLineRunner temp_user() {
		return (args) -> {
			repo.joueurs.save(new Joueur("tmp", "tmp", 0));
			repo.admins.save(new Admin("root", "root"));
			
			for (Admin a : repo.admins.findAll())
				getLogger().info(a.toString());
			for (Bot b : repo.bots.findAll())
				getLogger().info(b.toString());
			for (Carte c : repo.cartes.findAll())
				getLogger().info(c.toString());
			for (Compte c : repo.comptes.findAll())
				getLogger().info(c.toString());
			for (CompteCarte c : repo.comptecartes.findAll())
				getLogger().info(c.toString());
			for (Deck d : repo.decks.findAll())
				getLogger().info(d.toString());
			for (Duel d : repo.duels.findAll())
				getLogger().info(d.toString());
			for (Guilde g : repo.guildes.findAll())
				getLogger().info(g.toString());
			for (Joueur j : repo.joueurs.findAll())
				getLogger().info(j.toString());
			for (Message m : repo.messages.findAll())
				getLogger().info(m.toString());
			for (Thread t : repo.threads.findAll())
				getLogger().info(t.toString());
			for (Tournoi t : repo.tournois.findAll())
				getLogger().info(t.toString());
			for (Utilisateur u : repo.utilisateurs.findAll())
				getLogger().info(u.toString());
			for (Vente v : repo.ventes.findAll())
				getLogger().info(v.toString());
			for (VenteCarte v : repo.ventecartes.findAll())
				getLogger().info(v.toString());
		};
	}
	
	@Autowired
	AuthProvider authenticationProvider;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/auth/login").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			//.loginPage("/web/login.html")
			.loginProcessingUrl("/api/auth/login")
			//.defaultSuccessUrl("/web/profile", true)
			//.failureUrl("/web/auth/login.html?error=true")
			//.failureHandler(authenticationFailureHandler())
			.and()
			.logout()
			.logoutUrl("/api/auth/logout")
			.deleteCookies("JSESSIONID")
			//.logoutSuccessHandler(logoutSuccessHandler())
			.and()
			.headers()
			.frameOptions().sameOrigin();
	}
}