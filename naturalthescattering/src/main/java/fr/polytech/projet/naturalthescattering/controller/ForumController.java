package fr.polytech.projet.naturalthescattering.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.polytech.projet.naturalthescattering.controller.util.Response;
import fr.polytech.projet.naturalthescattering.service.ForumService;
import fr.polytech.projet.naturalthescattering.service.result.ForumResult;

@RestController
@RequestMapping(path="/api/forum")
public class ForumController {
	@Autowired
	private ForumService srv;
	
	// Create Thread
	@PostMapping(path="/thread")
	public ResponseEntity<ForumResult.Thread.Create> createThread(Authentication auth, @RequestParam(name="name") String name, @RequestParam(name="content") String content) {
		return Response.create(srv.createThread(auth.getName(), name, content));
	}
	
	// Read Thread infos
	@GetMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Thread.Read> readThread(@PathVariable(name="id") Long id) {
		return Response.create(srv.readThread(id));
	}
	
	// Update Thread infos
	@PutMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Thread.Update> updateThread(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="open", required=false) Boolean open) {
		return Response.create(srv.updateThread(auth.getName(), id, open));
	}
	
	// Delete Thread
	@DeleteMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Thread.Delete> deleteThread(Authentication auth, @PathVariable(name="id") Long id) {
		return Response.create(srv.deleteThread(auth.getName(), id));
	}
	
	// List thread messages
	@GetMapping(path="/thread/{id}/msgs")
	public ResponseEntity<ForumResult.Thread.List> readMsgList(@PathVariable(name="id") Long id, @RequestParam(name="offset", required=false) Long offset, @RequestParam(name="limit", required=false) Long limit) {
		return Response.create(srv.readMsgList(id, offset, limit));
	}
	
	// Create Message
	@PostMapping(path="/thread/{id}")
	public ResponseEntity<ForumResult.Message.Create> createMsg(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="content") String content) {
		return Response.create(srv.createMessage(auth.getName(), id, content));
	}
	
	// Read Message infos
	@GetMapping(path="/message/{id}")
	public ResponseEntity<ForumResult.Message.Read> readMsg(@PathVariable(name="id") Long id) {
		return Response.create(srv.readMsg(id));
	}
}
