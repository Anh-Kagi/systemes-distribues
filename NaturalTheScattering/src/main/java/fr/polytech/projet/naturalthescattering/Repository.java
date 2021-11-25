package fr.polytech.projet.naturalthescattering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.repository.IAccountCardRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IAccountRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IAdminRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IBotRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICardRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IDeckRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IDuelRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IGuildRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IMessageRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IPlayerRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ISaleCardRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ISaleRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IThreadRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ITournamentRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;

@Component
public class Repository {
	public static IAdminRepository admins;
	
	public static IBotRepository bots;
	
	public static ICardRepository cards;
	
	public static IAccountRepository accounts;
	
	public static IAccountCardRepository accountcards;
	
	public static IDeckRepository decks;
	
	public static IDuelRepository duels;
	
	public static IGuildRepository guilds;
	
	public static IPlayerRepository players;
	
	public static IMessageRepository messages;
	
	public static IThreadRepository threads;
	
	public static ITournamentRepository tournaments;
	
	public static IUserRepository users;
	
	public static ISaleRepository sales;
	
	public static ISaleCardRepository salecards;
	
	@Autowired
	public Repository(IAdminRepository admins, IBotRepository bots, ICardRepository cards, IAccountRepository accounts, IAccountCardRepository accountcards, IDeckRepository decks, IDuelRepository duels, IGuildRepository guilds, IPlayerRepository players, IMessageRepository messages, IThreadRepository threads, ITournamentRepository tournaments, IUserRepository users, ISaleRepository sales, ISaleCardRepository salecards) {
		Repository.admins = admins;
		Repository.bots = bots;
		Repository.cards = cards;
		Repository.accounts = accounts;
		Repository.accountcards = accountcards;
		Repository.decks = decks;
		Repository.duels = duels;
		Repository.guilds = guilds;
		Repository.players = players;
		Repository.messages = messages;
		Repository.threads = threads;
		Repository.tournaments = tournaments;
		Repository.users = users;
		Repository.sales = sales;
		Repository.salecards = salecards;
	}
}
