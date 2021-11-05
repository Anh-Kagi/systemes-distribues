package fr.polytech.projet.naturalthescattering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.polytech.projet.naturalthescattering.db.repository.IBotRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICarteRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICompteCarteRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ICompteRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IDeckRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IDuelRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IGuildeRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IJoueurRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IMessageRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IThreadRepository;
import fr.polytech.projet.naturalthescattering.db.repository.ITournoiRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IVenteRepository;

@Component
public class Repository {
	@Autowired(required=true)
	public IBotRepository bots;
	
	@Autowired(required=true)
	public ICarteRepository cartes;
	
	@Autowired(required=true)
	public ICompteRepository comptes;
	
	@Autowired(required=true)
	public ICompteCarteRepository comptecartes;
	
	@Autowired(required=true)
	public IDeckRepository decks;
	
	@Autowired(required=true)
	public IDuelRepository duels;
	
	@Autowired(required=true)
	public IGuildeRepository guildes;
	
	@Autowired(required=true)
	public IJoueurRepository joueurs;
	
	@Autowired(required=true)
	public IMessageRepository messages;
	
	@Autowired(required=true)
	public IThreadRepository threads;
	
	@Autowired(required=true)
	public ITournoiRepository tournois;
	
	@Autowired(required=true)
	public IVenteRepository ventes;
}
