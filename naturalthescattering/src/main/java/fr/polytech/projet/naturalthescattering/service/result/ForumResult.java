package fr.polytech.projet.naturalthescattering.service.result;

import java.util.Date;

import org.springframework.http.HttpStatus;

public abstract class ForumResult {
	public static abstract class Thread {
		public static enum CreateStatus implements GenericStatus {
			OK(HttpStatus.OK);
			
			private HttpStatus status;
			
			private CreateStatus(HttpStatus status) {
				this.status = status;
			}
			
			@Override
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
			THREAD_DOESNT_EXISTS(HttpStatus.NOT_FOUND);
			
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
		
		public static enum UpdateStatus implements GenericStatus {
			OK(HttpStatus.OK),
			THREAD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
			THREAD_DOESNT_BELONG_TO_USER(HttpStatus.UNAUTHORIZED);
			
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
		
		public static enum DeleteStatus implements GenericStatus {
			OK(HttpStatus.OK),
			THREAD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
			THREAD_DOESNT_BELONG_TO_USER(HttpStatus.UNAUTHORIZED);
			
			private HttpStatus status;
			
			private DeleteStatus(HttpStatus status) {
				this.status = status;
			}
			
			public HttpStatus getStatus() {
				return this.status;
			}
		}
		
		public static class Delete extends GenericResult<DeleteStatus> {}
		
		public static enum ListStatus implements GenericStatus {
			OK(HttpStatus.OK),
			THREAD_DOESNT_EXISTS(HttpStatus.NOT_FOUND);
			
			private HttpStatus status;
			
			private ListStatus(HttpStatus status) {
				this.status = status;
			}
			
			public HttpStatus getStatus() {
				return this.status;
			}
		}
		
		public static class List extends GenericResult<ListStatus> {
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
		public static enum CreateStatus implements GenericStatus {
			OK(HttpStatus.OK),
			THREAD_DOESNT_EXISTS(HttpStatus.NOT_FOUND),
			THREAD_CLOSED(HttpStatus.FORBIDDEN);

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
			MESSAGE_DOESNT_EXISTS(HttpStatus.NOT_FOUND);
			
			private HttpStatus status;
			
			private ReadStatus(HttpStatus status) {
				this.status = status;
			}
			
			public HttpStatus getStatus() {
				return this.status;
			}
		}
		public static class Read extends GenericResult<ReadStatus> {
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
