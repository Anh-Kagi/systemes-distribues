package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Card;
import fr.polytech.projet.naturalthescattering.db.Sale;
import fr.polytech.projet.naturalthescattering.db.SaleCard;

public interface ISaleCardRepository extends JpaRepository<SaleCard, Long> {
	public List<SaleCard> findBySaleAndCard(Sale sale, Card card);
}
