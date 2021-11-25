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
			private String name;
			private Date date;
			private boolean open;
			private String content;
			private Long author;
			
			public void setName(String name) {
				this.name = name;
			}
			
			public String getName() {
				return this.name;
			}
			
			public void setDate(Date date) {
				this.date = date;
			}
			
			public Date getDate() {
				return this.date;
			}
			
			public void setOpen(boolean open) {
				this.open = open;
			}
			
			public boolean getOpen() {
				return this.open;
			}
			
			public void setContent(String content) {
				this.content = content;
			}
			
			public String getContent() {
				return this.content;
			}
			
			public void setAuthor(Long author) {
				this.author = author;
			}
			
			public Long getAuthor() {
				return this.author;
			}
			
			public void fromThread(fr.polytech.projet.naturalthescattering.db.Thread thread) {
				setName(thread.getName());
				setDate(thread.getDate());
				setOpen(thread.getOpen());
				setContent(thread.getContent());
				setAuthor(thread.getAuthor().getId());
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
			private String content;
			private Long author;
			private Long thread;
			
			public void setDate(Date date) {
				this.date = date;
			}
			
			public Date getDate() {
				return this.date;
			}
			
			public void setContent(String content) {
				this.content = content;
			}
			
			public String getContent() {
				return this.content;
			}
			
			public void setAuthor(Long author) {
				this.author = author;
			}
			
			public Long getAuthor() {
				return this.author;
			}
			
			public void setThread(Long thread) {
				this.thread = thread;
			}
			
			public Long getThread() {
				return this.thread;
			}
			
			public void fromMessage(fr.polytech.projet.naturalthescattering.db.Message message) {
				setDate(message.getDate());
				setContent(message.getContent());
				setAuthor(message.getAuthor().getId());
				setThread(message.getThread().getId());
			}
		}
	}
}
