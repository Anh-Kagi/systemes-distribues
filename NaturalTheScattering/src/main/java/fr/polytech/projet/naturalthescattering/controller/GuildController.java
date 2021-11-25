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
import fr.polytech.projet.naturalthescattering.controller.results.GuildResult;
import fr.polytech.projet.naturalthescattering.db.Guild;
import fr.polytech.projet.naturalthescattering.db.Player;
import fr.polytech.projet.naturalthescattering.db.repository.IGuildRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IPlayerRepository;

@RestController
@RequestMapping(path="/api/guild")
public class GuildController {
	@Autowired
	private IPlayerRepository players;
	
	@Autowired
	private IGuildRepository guilds;
	
	@PostMapping(path={"", "/"})
	public ResponseEntity<GuildResult.Create> create(Authentication auth, @RequestParam(name="name") String name) {
		Player player  = players.findByPseudo(auth.getName());
		GuildResult.Create result = new GuildResult.Create();
		
		if (player == null) {
			result.setReason("user doesn't exists");
			return new ResponseEntity<GuildResult.Create>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (player.getGuild() != null) {
			result.setReason("user already joined a guild");
			return new ResponseEntity<GuildResult.Create>(result, HttpStatus.FORBIDDEN);
		}
		
		Guild guild = new Guild(name, player);
		player.setGuild(guild);
		
		guilds.save(guild);
		players.save(player);
		
		result.setSuccess(true);
		result.setId(guild.getId());
		return new ResponseEntity<GuildResult.Create>(result, HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GuildResult.Read> read(@PathVariable(name="id") Long id) {
		Guild guild = guilds.findById(id).orElse(null);
		GuildResult.Read result = new GuildResult.Read();
		
		if (guild == null) {
			result.setReason("guild doesn't exists");
			return new ResponseEntity<GuildResult.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		result.setSuccess(true);
		result.fromGuild(guild);
		return new ResponseEntity<GuildResult.Read>(result, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<GuildResult.Read> update(Authentication auth, @PathVariable(name="id") Long id, @RequestParam(name="leader", required=false) Long newleader, @RequestParam(name="name", required=false) String newname) {
		GuildResult.Read result = new GuildResult.Read();
		
		Player player = players.findByPseudo(auth.getName());
		if (player == null) {
			result.setReason("player not found");
			return new ResponseEntity<GuildResult.Read>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setReason("guild doesn't exists");
			return new ResponseEntity<GuildResult.Read>(result, HttpStatus.NOT_FOUND);
		}
		
		if (player.getId() != guild.getLeader().getId()) {
			result.setReason("guild doesn't belong to player");
			return new ResponseEntity<GuildResult.Read>(result, HttpStatus.FORBIDDEN);
		}
		
		Optional<Player> leader = Optional.of(player);
		if (newleader != null) {
			leader = players.findById(newleader);
			if (!leader.isPresent()) {
				result.setReason("new leader doesn't exists");
				return new ResponseEntity<GuildResult.Read>(result, HttpStatus.NOT_FOUND);
			}
		}
		
		if (newname != null) {
			if (guilds.existsByName(newname)) {
				result.setReason("another guild with the same name exists");
				return new ResponseEntity<GuildResult.Read>(result, HttpStatus.FORBIDDEN);
			}
		} else
			newname = guild.getName();
		
		guild.setLeader(leader.get());
		guild.setName(newname);
		guild = guilds.save(guild);
		
		result.setSuccess(true);
		result.fromGuild(guild);
		return new ResponseEntity<GuildResult.Read>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<GenericResult> delete(Authentication auth, @PathVariable(name="id") Long id) {
		
		GenericResult result = new GenericResult();
		
		Player player = players.findByPseudo(auth.getName());
		if (player == null) {
			result.setReason("player not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setReason("guild doesn't exists");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (player.getId() != guild.getLeader().getId()) {
			result.setReason("guild doesn't belong to player");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		guilds.delete(guild);
		// TODO
		// user quit guilde (?)
		// users quit guilde (?)
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	@PostMapping(path="/{id}/join")
	public ResponseEntity<GenericResult> join(Authentication auth, @PathVariable(name="id") Long id) {
		GenericResult result = new GenericResult();
		
		Player player = players.findByPseudo(auth.getName());
		if (player == null) {
			result.setReason("player not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (player.getGuild() != null) {
			result.setReason("player already joined a guild");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		player.setGuild(guild);
		players.save(player);
		
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
	
	@PostMapping(path="/{id}/leave")
	public ResponseEntity<GenericResult> leave(Authentication auth, @PathVariable(name="id") Long id) {
		GenericResult result = new GenericResult();
		
		Player player = players.findByPseudo(auth.getName());
		if (player == null) {
			result.setReason("player not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (player.getGuild() == null) {
			result.setReason("player hasn't joined a guild");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setReason("guild not found");
			return new ResponseEntity<GenericResult>(result, HttpStatus.NOT_FOUND);
		}
		
		if (guild.getLeader().getId() == player.getId()) {
			result.setReason("guild's leader cannot leave");
			return new ResponseEntity<GenericResult>(result, HttpStatus.FORBIDDEN);
		}
		
		player.setGuild(null);
		players.save(player);
		
		result.setSuccess(true);
		return new ResponseEntity<GenericResult>(result, HttpStatus.OK);
	}
}
