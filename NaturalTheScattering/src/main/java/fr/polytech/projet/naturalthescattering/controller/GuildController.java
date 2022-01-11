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
import fr.polytech.projet.naturalthescattering.service.GuildService;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult;

@RestController
@RequestMapping(path="/api/guild")
public class GuildController {
	@Autowired
	private GuildService srv;
	
	@PostMapping(path={"", "/"})
	public ResponseEntity<GuildResult.Create> create(Authentication auth, @RequestParam(name="name") String name) {
		return Response.create(srv.create(auth.getName(), name));
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GuildResult.Read> read(@PathVariable(name="id") Long id) {
		return Response.create(srv.read(id));
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<GuildResult.Update> update(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="leader", required=false) Long newleader, @RequestParam(name="name", required=false) String newname) {
		return Response.create(srv.update(auth.getName(), id, newleader, newname));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<GuildResult.Delete> delete(Authentication auth, @PathVariable(name="id") Long id) {
		return Response.create(srv.delete(auth.getName(), id));
	}
	
	@PostMapping(path="/{id}/join")
	public ResponseEntity<GuildResult.Join> join(Authentication auth, @PathVariable(name="id") Long id) {
		return Response.create(srv.join(auth.getName(), id));
	}
	
	@PostMapping(path="/{id}/leave")
	public ResponseEntity<GuildResult.Leave> leave(Authentication auth, @PathVariable(name="id") Long id) {
		return Response.create(srv.leave(auth.getName(), id));
	}
}
