package fr.polytech.projet.naturalthescattering.service.result;

import org.springframework.http.HttpStatus;

import fr.polytech.projet.naturalthescattering.db.Guild;

public abstract class GuildResult {
	public static enum CreateStatus implements GenericStatus {
		OK(HttpStatus.CREATED),
		PLAYER_ALREADY_JOINED_A_GUILD(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;
		
		private CreateStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	public static class Create extends GenericResult<CreateStatus> {
		private Long id;
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public Long getId() {
			return this.id;
		}
	}
	
	public static enum ReadStatus implements GenericStatus {
		OK(HttpStatus.OK),
		GUILD_DOESNT_EXISTS(HttpStatus.NOT_FOUND);
		
		private HttpStatus status;
		
		private ReadStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Read extends GenericResult<ReadStatus> {
		private String name;
		private Long leader;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setLeader(Long leader) {
			this.leader = leader;
		}
		
		public Long getLeader() {
			return this.leader;
		}
		
		public void fromGuild(Guild guild) {
			setName(guild.getName());
			setLeader(guild.getLeader().getId());
		}
	}
	
	public static enum UpdateStatus implements GenericStatus {
		OK(HttpStatus.OK),
		GUILD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
		GUILD_DOESNT_BELONG_TO_PLAYER(HttpStatus.UNAUTHORIZED),
		NEW_LEADER_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
		GUILD_NAME_ALREADY_TAKEN(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;
		
		private UpdateStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Update extends GenericResult<UpdateStatus> {
		private String name;
		private Long leader;
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setLeader(Long leader) {
			this.leader = leader;
		}
		
		public Long getLeader() {
			return this.leader;
		}
		
		public void fromGuild(Guild guild) {
			setName(guild.getName());
			setLeader(guild.getLeader().getId());
		}
	}
	
	public static enum DeleteStatus implements GenericStatus {
		OK(HttpStatus.OK),
		GUILD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
		GUILD_DOESNT_BELONG_TO_PLAYER(HttpStatus.UNAUTHORIZED);
		
		private HttpStatus status;
		
		private DeleteStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Delete extends GenericResult<DeleteStatus> {}
	
	public static enum JoinStatus implements GenericStatus {
		OK(HttpStatus.OK),
		GUILD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
		PLAYER_ALREADY_JOINED_A_GUILD(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;
		
		private JoinStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Join extends GenericResult<JoinStatus> {}
	
	public static enum LeaveStatus implements GenericStatus {
		OK(HttpStatus.OK),
		GUILD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
		GUILD_LEADER_CANNOT_LEAVE(HttpStatus.FORBIDDEN),
		PLAYER_DIDNT_JOIN_A_GUILD(HttpStatus.FORBIDDEN),
		PLAYER_JOINED_ANOTHER_GUILD(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;
		
		private LeaveStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Leave extends GenericResult<LeaveStatus> {}
}
