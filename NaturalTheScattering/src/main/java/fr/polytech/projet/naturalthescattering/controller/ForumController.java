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
	
	// Create Thread
	@PostMapping(path="/thread")
	public ResponseEntity<ForumResult.Thread.Create> createThread(Authentication auth, @RequestParam(name="nom") String nom, @RequestParam(name="contenu") String contenu) {
		Utilisateur utilisateur = utilisateurs.findByPseudo(auth.getName());
		ForumResult.Thread.Create result = new ForumResult.Thread.Create();
		
		if (utilisateur == null) {
			result.setReason("user not found");
			return new ResponseEntity<ForumResult.Thread.Create>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Thread thread = new Thread(nom, utilisateur, contenu);
		threads.save(thread);
		
		result.setSuccess(true);
		result.setId(thread.getId());
		return new ResponseEntity<ForumResult.Thread.Create>(result, HttpStatus.CREATED);
	}
	
	// Read Thread infos
	@GetMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Thread.Read> readThread(@PathVariable(name="id") Long id) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Read result = new ForumResult.Thread.Read();
		
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		result.setSuccess(true);
		result.fromThread(thread);
		return new ResponseEntity<ForumResult.Thread.Read>(result, HttpStatus.OK);
	}
	
	// Update Thread infos
	@PutMapping(path="/thread/{id}")
	public ResponseEntity<GenericResult> updateThread(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="ouvert", required=false) Boolean ouvert) {
		Thread thread = threads.findById(id).orElse(null);
		GenericResult result = new GenericResult();
		
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (!thread.getAuteur().getPseudo().equals(auth.getName())) {
			result.setReason("this thread doesn't belong to you");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}

		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	// Delete Thread
	@DeleteMapping(path="/thread/{id}")
	public ResponseEntity<GenericResult> deleteThread(Authentication auth, @PathVariable(name="id") Long id) {
		Thread thread = threads.findById(id).orElse(null);
		GenericResult result = new GenericResult();
		
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (!thread.getAuteur().getPseudo().equals(auth.getName())) {
			result.setReason("this thread doesn't belong to you");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}

		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	// List thread messages
	@GetMapping(path="/thread/{id}/msgs")
	public ResponseEntity<ForumResult.Thread.List> readMsgList(@PathVariable(name="id") Long id, @RequestParam(name="offset", required=false) Long offset, @RequestParam(name="limit", required=false) Long limit) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.List result = new ForumResult.Thread.List();
		
		if (thread == null) {
			result.setReason("thread not found");
			return new ResponseEntity<ForumResult.Thread.List>(result, HttpStatus.NOT_FOUND);
		}
		
		Stream<Message> stream = messages.findByThreadOrderByDateAsc(thread);
		if (offset != null)
			stream = stream.skip(offset);
		if (limit != null)
			stream = stream.limit(limit);
		
		long messages[] = stream.mapToLong((m) -> m.getId()).toArray();
		
		result.setSuccess(true);
		result.setIds(messages);
		return new ResponseEntity<ForumResult.Thread.List>(result, HttpStatus.OK);
	}
	
	// Create Message
	@PostMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Message.Create> createMsg(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="contenu") String contenu) {
		Thread thread = threads.findById(id).orElse(null);
		Utilisateur utilisateur = utilisateurs.findByPseudo(auth.getName());
		ForumResult.Message.Create result = new ForumResult.Message.Create();
		
		if (utilisateur == null) {
			result.setReason("you are not logged in");
			return new ResponseEntity<ForumResult.Message.Create>(result, HttpStatus.FORBIDDEN);
		}
		
		if (thread == null) {
			result.setReason("thread doesn't exists");
			return new ResponseEntity<ForumResult.Message.Create>(result, HttpStatus.NOT_FOUND);
		}
		
		if (!thread.getOuvert()) {
			result.setReason("thread is closed");
			return new ResponseEntity<ForumResult.Message.Create>(result, HttpStatus.FORBIDDEN);
		}
		
		Message message = new Message(thread, utilisateur, contenu);
		message = messages.save(message);
		
		result.setSuccess(true);
		result.setId(message.getId());
		return new ResponseEntity<ForumResult.Message.Create>(result, HttpStatus.OK);
	}
	
	// Read Message infos
	@GetMapping(path="/message/{id}")
	public ResponseEntity<ForumResult.Message.Read> readMsg(@PathVariable(name="id") Long id) {
		Message message = messages.findById(id).orElse(null);
		ForumResult.Message.Read result = new ForumResult.Message.Read();
		
		if (message == null) {
			result.setReason("message not found");
			return new ResponseEntity<ForumResult.Message.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		result.setSuccess(true);
		result.fromMessage(message);
		return new ResponseEntity<ForumResult.Message.Read>(result, HttpStatus.OK);
	}
}
