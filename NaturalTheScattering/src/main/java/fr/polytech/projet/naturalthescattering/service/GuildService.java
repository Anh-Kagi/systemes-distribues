package fr.polytech.projet.naturalthescattering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.projet.naturalthescattering.db.Guild;
import fr.polytech.projet.naturalthescattering.db.Player;
import fr.polytech.projet.naturalthescattering.db.repository.IGuildRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IPlayerRepository;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.CreateStatus;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.DeleteStatus;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.JoinStatus;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.LeaveStatus;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.ReadStatus;
import fr.polytech.projet.naturalthescattering.service.result.GuildResult.UpdateStatus;

@Service
public class GuildService {
	@Autowired
	private IPlayerRepository players;
	
	@Autowired
	private IGuildRepository guilds;
	
	public GuildResult.Create create(String pseudo, String name) {
		GuildResult.Create result = new GuildResult.Create();
		
		Player player  = players.findByPseudo(pseudo);
		
		if (player.getGuild() != null) {
			result.setStatus(CreateStatus.PLAYER_ALREADY_JOINED_A_GUILD);
			return result;
		}
		
		Guild guild = new Guild(name, player);
		player.setGuild(guild);
		
		guilds.save(guild);
		players.save(player);
		
		result.setStatus(CreateStatus.OK);
		result.setId(guild.getId());
		return result;
	}
	
	public GuildResult.Read read(Long id) {
		GuildResult.Read result = new GuildResult.Read();

		Guild guild = guilds.findById(id).orElse(null);
		
		if (guild == null) {
			result.setStatus(ReadStatus.GUILD_DOESNT_EXISTS);
			return result;
		}
		
		result.setStatus(ReadStatus.OK);
		result.fromGuild(guild);
		return result;
	}
	
	public GuildResult.Update update(String pseudo, Long id, Long newleader, String newname) {
		GuildResult.Update result = new GuildResult.Update();
		
		Player player = players.findByPseudo(pseudo);

		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setStatus(UpdateStatus.GUILD_DOESNT_EXISTS);
			return result;
		}
		
		if (player.getId() != guild.getLeader().getId()) {
			result.setStatus(UpdateStatus.GUILD_DOESNT_BELONG_TO_PLAYER);
			return result;
		}
		
		Optional<Player> leader = Optional.of(player);
		if (newleader != null) {
			leader = players.findById(newleader);
			if (!leader.isPresent()) {
				result.setStatus(UpdateStatus.NEW_LEADER_DOESNT_EXISTS);
				return result;
			}
		}
		
		if (newname != null) {
			if (guilds.existsByName(newname)) {
				result.setStatus(UpdateStatus.GUILD_NAME_ALREADY_TAKEN);
				return result;
			}
		} else
			newname = guild.getName();
		
		guild.setLeader(leader.get());
		guild.setName(newname);
		guild = guilds.save(guild);
		
		result.setStatus(UpdateStatus.OK);
		result.fromGuild(guild);
		return result;
	}
	
	public GuildResult.Delete delete(String pseudo, Long id) {
		GuildResult.Delete result = new GuildResult.Delete();
		
		Player player = players.findByPseudo(pseudo);
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setStatus(DeleteStatus.GUILD_DOESNT_EXISTS);
			return result;
		}
		
		if (player.getId() != guild.getLeader().getId()) {
			result.setStatus(DeleteStatus.GUILD_DOESNT_BELONG_TO_PLAYER);
			return result;
		}
		
		guilds.delete(guild);
		// TODO
		// user quit guilde (?)
		// users quit guilde (?)
		result.setStatus(DeleteStatus.OK);
		return result;
	}
	
	public GuildResult.Join join(String pseudo, Long id) {
		GuildResult.Join result = new GuildResult.Join();
		
		Player player = players.findByPseudo(pseudo);
		
		if (player.getGuild() != null) {
			result.setStatus(JoinStatus.PLAYER_ALREADY_JOINED_A_GUILD);
			return result;
		}
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setStatus(JoinStatus.GUILD_DOESNT_EXISTS);
			return result;
		}
		
		player.setGuild(guild);
		players.save(player);
		
		result.setStatus(JoinStatus.OK);
		return result;
	}
	
	public GuildResult.Leave leave(String pseudo, Long id) {
		GuildResult.Leave result = new GuildResult.Leave();
		
		Player player = players.findByPseudo(pseudo);
		if (player.getGuild() == null) {
			result.setStatus(LeaveStatus.PLAYER_DIDNT_JOIN_A_GUILD);
			return result;
		}
		if (player.getGuild().getId() != id) {
			result.setStatus(LeaveStatus.PLAYER_JOINED_ANOTHER_GUILD);
			return result;
		}
		
		Guild guild = guilds.findById(id).orElse(null);
		if (guild == null) {
			result.setStatus(LeaveStatus.GUILD_DOESNT_EXISTS);
			return result;
		}
		
		if (guild.getLeader().getId() == player.getId()) {
			result.setStatus(LeaveStatus.GUILD_LEADER_CANNOT_LEAVE);
			return result;
		}
		
		player.setGuild(null);
		players.save(player);
		
		result.setStatus(LeaveStatus.OK);
		return result;
	}
}