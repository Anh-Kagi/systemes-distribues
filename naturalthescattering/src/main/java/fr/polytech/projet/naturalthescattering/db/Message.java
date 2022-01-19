package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	@Lob
	@Column(nullable=false)
	private String content;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="message_author_ref"))
	private User author;
	
	@ManyToOne(optional=false)
	@Cascade({CascadeType.MERGE})
	@JoinColumn(foreignKey=@ForeignKey(name="message_thread_ref"))
	private Thread thread;
	
	protected Message() {}
	
	public Message(Thread thread, User author, String content) {
		this.author = author;
		this.thread = thread;
		setContent(content);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public User getAuthor() {
		return this.author;
	}
	
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
	
	public Thread getThread() {
		return this.thread;
	}
	
	@Override
	public String toString() {
		return "[Message(id=" + getId() + " | date=" + getDate() + " | contenu=" + getContent().substring(0, 10) + " | auteur=" + (getAuthor() == null ? null : getAuthor().getId()) + ")]";
	}
}
