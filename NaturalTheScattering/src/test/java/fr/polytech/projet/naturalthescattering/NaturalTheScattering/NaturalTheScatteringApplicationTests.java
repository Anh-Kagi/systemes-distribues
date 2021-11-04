package fr.polytech.projet.naturalthescattering.NaturalTheScattering;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.Joueur;

@SpringBootTest
class NaturalTheScatteringApplicationTests {
	@Autowired
	Repository repo;

	@Test
	public void db_test() {
		Joueur pedro = new Joueur("XxxPedroLePloxxX", "NotAPlo", 1000000000);
		Joueur pablo = new Joueur("XxxPabloLePloxxX", "IsAPlo", -1000000000);
			
		pedro.createGuilde("Ordre");
		repo.joueurs.save(pedro);
		repo.joueurs.save(pablo);
	}
}
