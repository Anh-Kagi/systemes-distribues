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
import fr.polytech.projet.naturalthescattering.db.Card;
import fr.polytech.projet.naturalthescattering.db.Player;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class NaturalTheScatteringApplication extends WebSecurityConfigurerAdapter {
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
	public CommandLineRunner db_init() {
		return (args) -> {
			Repository.players.save(new Player("user", "user", 0));
			Repository.admins.save(new Admin("root", "root"));

			for (int i=1; i<=10; i++)
				Repository.cards.save(new Card("Carte " + i, i));
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
			.antMatchers("/api/auth/*").permitAll()
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
