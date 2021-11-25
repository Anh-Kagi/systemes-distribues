package fr.polytech.projet.naturalthescattering.controller.results;

import fr.polytech.projet.naturalthescattering.db.Guild;

public abstract class GuildResult {
	public static class Create extends GenericResult {
		private Long id;
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public Long getId() {
			return this.id;
		}
	}
	
	public static class Read extends GenericResult {
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
}
