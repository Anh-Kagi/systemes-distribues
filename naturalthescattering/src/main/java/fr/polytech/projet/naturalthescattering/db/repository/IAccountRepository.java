package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Account;

public interface IAccountRepository extends JpaRepository<Account, Long> {
	public Account findByPseudo(String pseudo);
	public boolean existsByPseudo(String pseudo);
}
