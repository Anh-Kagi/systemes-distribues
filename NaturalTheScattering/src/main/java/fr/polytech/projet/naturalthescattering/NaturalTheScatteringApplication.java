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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import fr.polytech.projet.naturalthescattering.auth.NaturalTheScatteringAuthProvider;
import fr.polytech.projet.naturalthescattering.db.Joueur;

@SpringBootApplication
@EnableWebSecurity
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
		};
	}
	
	@Autowired
	NaturalTheScatteringAuthProvider authenticationProvider;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			//.antMatchers("/admin/**").hasRole("ADMIN")
			//.antMatchers("/anonymous*").anonymous()
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
			.deleteCookies("JSESSIONID");
			//.logoutSuccessHandler(logoutSuccessHandler());
	}
}