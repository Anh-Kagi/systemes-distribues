package fr.polytech.projet.naturalthescattering.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.controller.results.GuildeResult;
import fr.polytech.projet.naturalthescattering.db.Guilde;
import fr.polytech.projet.naturalthescattering.db.Joueur;
import fr.polytech.projet.naturalthescattering.db.repository.IGuildeRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IJoueurRepository;

@RestController
@RequestMapping(path="/api/guilde")
public class GuildeController {
	@Autowired
	private IJoueurRepository joueurs;
	
	@Autowired
	private IGuildeRepository guildes;
	
	@PostMapping(path={"", "/"})
	public ResponseEntity<GuildeResult.Create> create(Authentication auth, @RequestParam(name="nom") String nom) {
		Joueur joueur  = joueurs.findByPseudo(auth.getName());
		GuildeResult.Create result = new GuildeResult.Create();
		
		if (joueur == null) {
			result.setReason("user doesn't exists");
			return new ResponseEntity<GuildeResult.Create>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (joueur.getGuilde() != null) {
			result.setReason("user already joined a guilde");
			return new ResponseEntity<GuildeResult.Create>(result, HttpStatus.FORBIDDEN);
		}
		
		Guilde guilde = new Guilde(nom, joueur);
		joueur.setGuilde(guilde);
		
		guildes.save(guilde);
		joueurs.save(joueur);
		
		result.setSuccess(true);
		result.setId(guilde.getId());
		return new ResponseEntity<GuildeResult.Create>(result, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GuildeResult.Read> read(@PathVariable(name="id") Long id) {
		Guilde guilde = guildes.findById(id).orElse(null);
		GuildeResult.Read result = new GuildeResult.Read();
		
		if (guilde == null) {
			result.setReason("guilde doesn't exists");
			return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		result.setSuccess(true);
		result.fromGuilde(guilde);
		return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<GuildeResult.Read> update(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="chef", required=false) Long newchef, @RequestParam(name="name", required=false) String newnom) {
		GuildeResult.Read result = new GuildeResult.Read();
		
		Joueur joueur = joueurs.findByPseudo(auth.getName());
		if (joueur == null) {
			result.setReason("joueur not found");
			return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Guilde guilde = guildes.findById(id).orElse(null);
		if (guilde == null) {
			result.setReason("guilde doesn't exists");
			return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		if (joueur.getId() != guilde.getChef().getId()) {
			result.setReason("guilde doesn't belong to joueur");
			return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.FORBIDDEN);
		}
		
		Optional<Joueur> chef = Optional.of(joueur);
		if (newchef != null) {
			chef = joueurs.findById(newchef);
			if (!chef.isPresent()) {
				result.setReason("new chef doesn't exists");
				return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.NOT_FOUND);
			}
		}
		
		if (newnom != null) {
			if (guildes.existsByNom(newnom)) {
				result.setReason("another guilde with the same name exists");
				return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.FORBIDDEN);
			}
		} else
			newnom = guilde.getNom();
		
		guilde.setChef(chef.get());
		guilde.setNom(newnom);
		guilde = guildes.save(guilde);
		
		result.setSuccess(true);
		result.fromGuilde(guilde);
		return new ResponseEntity<GuildeResult.Read>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<GenericResult> delete(Authentication auth, @PathVariable(name="id") Long id) {
		
		GenericResult result = new GenericResult();
		
		Joueur joueur = joueurs.findByPseudo(auth.getName());
		if (joueur == null) {
			result.setReason("joueur not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Guilde guilde = guildes.findById(id).orElse(null);
		if (guilde == null) {
			result.setReason("guilde doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (joueur.getId() != guilde.getChef().getId()) {
			result.setReason("guilde doesn't belong to joueur");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		guildes.delete(guilde);
		// delete guilde
		// user quit guilde (?)
		// users quit guilde (?)
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	@PostMapping(path="/{id}/join")
	public ResponseEntity<GenericResult> join(Authentication auth, @PathVariable(name="id") Long id) {
		GenericResult result = new GenericResult();
		
		Joueur joueur = joueurs.findByPseudo(auth.getName());
		if (joueur == null) {
			result.setReason("joueur not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (joueur.getGuilde() != null) {
			result.setReason("joueur already joined a guilde");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		Guilde guilde = guildes.findById(id).orElse(null);
		if (guilde == null) {
			result.setReason("guilde doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		joueur.setGuilde(guilde);
		joueurs.save(joueur);
		
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	@PostMapping(path="/{id}/leave")
	public ResponseEntity<GenericResult> leave(Authentication auth, @PathVariable(name="id") Long id) {
		GenericResult result = new GenericResult();
		
		Joueur joueur = joueurs.findByPseudo(auth.getName());
		if (joueur == null) {
			result.setReason("joueur not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (joueur.getGuilde() == null) {
			result.setReason("joueur hasn't joined a guilde");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		Guilde guilde = guildes.findById(id).orElse(null);
		if (guilde == null) {
			result.setReason("guilde not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (guilde.getChef().getId() == joueur.getId()) {
			result.setReason("guilde's chef cannot leave");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		joueur.setGuilde(null);
		joueurs.save(joueur);
		
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
}
