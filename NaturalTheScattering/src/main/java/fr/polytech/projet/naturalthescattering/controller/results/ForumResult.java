package fr.polytech.projet.naturalthescattering.controller.results;

import java.util.Date;

public abstract class ForumResult {
	public static abstract class Thread {
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
			private String nom;
			private Date date;
			private boolean ouvert;
			private String contenu;
			private Long auteur;
			
			public void setNom(String nom) {
				this.nom = nom;
			}
			
			public String getNom() {
				return this.nom;
			}
			
			public void setDate(Date date) {
				this.date = date;
			}
			
			public Date getDate() {
				return this.date;
			}
			
			public void setOuvert(boolean ouvert) {
				this.ouvert = ouvert;
			}
			
			public boolean getOuvert() {
				return this.ouvert;
			}
			
			public void setContenu(String contenu) {
				this.contenu = contenu;
			}
			
			public String getContenu() {
				return this.contenu;
			}
			
			public void setAuteur(Long auteur) {
				this.auteur = auteur;
			}
			
			public Long getAuteur() {
				return this.auteur;
			}
			
			public void fromThread(fr.polytech.projet.naturalthescattering.db.Thread thread) {
				setNom(thread.getNom());
				setDate(thread.getDate());
				setOuvert(thread.getOuvert());
				setContenu(thread.getContenu());
				setAuteur(thread.getAuteur().getId());
			}
		}
		
		public static class List extends GenericResult {
			private long[] ids;
			private Long offset;
			private Long limit;
			
			public void setLimit(Long limit) {
				this.limit = limit;
			}
			
			public Long getLimit() {
				return this.limit;
			}
			
			public void setOffset(Long offset) {
				this.offset = offset;
			}
			
			public Long getOffset() {
				return this.offset;
			}
			
			public void setIds(long[] ids) {
				this.ids = ids;
			}
			
			public long[] getIds() {
				return this.ids;
			}
		}
	}
	
	public static abstract class Message {
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
			private Date date;
			private String contenu;
			private Long auteur;
			private Long thread;
			
			public void setDate(Date date) {
				this.date = date;
			}
			
			public Date getDate() {
				return this.date;
			}
			
			public void setContenu(String contenu) {
				this.contenu = contenu;
			}
			
			public String getContenu() {
				return this.contenu;
			}
			
			public void setAuteur(Long auteur) {
				this.auteur = auteur;
			}
			
			public Long getAuteur() {
				return this.auteur;
			}
			
			public void setThread(Long thread) {
				this.thread = thread;
			}
			
			public Long getThread() {
				return this.thread;
			}
			
			public void fromMessage(fr.polytech.projet.naturalthescattering.db.Message message) {
				setDate(message.getDate());
				setContenu(message.getContenu());
				setAuteur(message.getAuteur().getId());
				setThread(message.getThread().getId());
			}
		}
	}
}
