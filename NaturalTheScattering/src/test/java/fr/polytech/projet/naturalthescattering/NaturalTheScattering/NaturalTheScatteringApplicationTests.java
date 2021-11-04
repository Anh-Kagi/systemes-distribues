package fr.polytech.projet.naturalthescattering.NaturalTheScattering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.Joueur;

@SpringBootTest
class NaturalTheScatteringApplicationTests {
	@Autowired
	Repository repo;

	@Test
	public void user_test() {
		Joueur pedro = new Joueur("user_test1", "user_test1_password", 0);
		Joueur pablo = new Joueur("user_test2", "user_test2_password", 0);
		repo.joueurs.save(pedro);
		repo.joueurs.save(pablo);
	}
	
	@Test
	public void createGuilde_test() {
		Joueur j = new Joueur("createguilde_test", "test", 0);
		j.createGuilde("createguilde_test");
		repo.joueurs.save(j);
	}
	
	@Test
	public void password_test() {
		String oldMdp = "this is an old password";
		String newMdp = "this is a new password";
		Joueur j = new Joueur("joueur_test", oldMdp, 42);
		repo.joueurs.save(j);
		
		Assert.isTrue(j.verifyMdp(oldMdp), "First password could not be verified");
		j.setMdp(oldMdp, newMdp);
		Assert.isTrue(j.verifyMdp(newMdp), "Second password could not be verified");
	}
}
