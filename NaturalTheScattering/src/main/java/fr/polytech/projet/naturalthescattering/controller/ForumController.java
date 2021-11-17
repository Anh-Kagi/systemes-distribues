package fr.polytech.projet.naturalthescattering.controller;

import java.util.stream.Stream;

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

import fr.polytech.projet.naturalthescattering.controller.results.ForumResult;
import fr.polytech.projet.naturalthescattering.controller.results.GenericResult;
import fr.polytech.projet.naturalthescattering.db.Message;
import fr.polytech.projet.naturalthescattering.db.Thread;
import fr.polytech.projet.naturalthescattering.db.Utilisateur;
import fr.polytech.projet.naturalthescattering.db.repository.IMessageRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IThreadRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IUtilisateurRepository;

@RestController
@RequestMapping(path="/api/forum")
public class ForumController {
	@Autowired
	private IThreadRepository threads;

	@Autowired
	private IMessageRepository messages;
	
	@Autowired
	private IUtilisateurRepository utilisateurs;
	
	//// CRUD thread
	@PostMapping(path={"", "/"})
	public ResponseEntity<ForumResult.Thread.Create> createThread(Authentication auth, @RequestParam(name="nom") String nom, @RequestParam(name="contenu") String contenu) {
		Utilisateur utilisateur = utilisateurs.findByPseudo(auth.getName());
		ForumResult.Thread.Create result = new ForumResult.Thread.Create();
		if (utilisateur == null) {
			result.setReason("user not found");
			return new ResponseEntity<ForumResult.Thread.Create>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			Thread thread = new Thread(nom, utilisateur, contenu);
			threads.save(thread);
			
			result.setSuccess(true);
			result.setId(thread.getId());
			return new ResponseEntity<ForumResult.Thread.Create>(result, HttpStatus.CREATED);
		}
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ForumResult.Thread.Read> readThread(@PathVariable(name="id") Long id) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Read result = new ForumResult.Thread.Read();
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.NOT_FOUND);
		} else {
			result.setSuccess(true);
			result.fromThread(thread);
			return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.OK);
		}
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<ForumResult.Thread.Read> updateThread(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="ouvert", required=false) Boolean ouvert) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Read result = new ForumResult.Thread.Read();
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.NOT_FOUND);
		} else {
			if (thread.getAuteur().getPseudo() == auth.getName()) {
				result.setSuccess(true);
				result.fromThread(thread);
				return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.OK);
			} else {
				result.setReason("this thread doesn't belong to you");
				return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.FORBIDDEN);
			}
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<GenericResult> deleteThread(Authentication auth, @PathVariable(name="id") Long id) {
		Thread thread = threads.findById(id).orElse(null);
		GenericResult result = new GenericResult();
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		} else {
			if (thread.getAuteur().getPseudo() == auth.getName()) {
				result.setSuccess(true);
				return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
			} else {
				result.setReason("this thread doesn't belong to you");
				return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
			}
		}
	}
	
	//// CRUD message
	@GetMapping(path="/{id}/msgs")
	public ResponseEntity<ForumResult.Message.List> readMsgList(@PathVariable(name="id") Long id, @RequestParam(name="offset", required=false) Long offset, @RequestParam(name="limit", required=false) Long limit) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Message.List result = new ForumResult.Message.List();
		if (thread == null) {
			result.setReason("thread not found");
			return new ResponseEntity<ForumResult.Message.List>(result, HttpStatus.NOT_FOUND);
		} else {
			Stream<Message> stream = messages.findByThreadOrderByDateAsc(thread);
			if (offset != null)
				stream = stream.skip(offset);
			if (limit != null)
				stream = stream.limit(limit);
			
			long messages[] = stream.mapToLong((m) -> m.getId()).toArray();
			
			result.setSuccess(true);
			result.setIds(messages);
			return new ResponseEntity<ForumResult.Message.List>(result, HttpStatus.OK);
		}
	}
}
