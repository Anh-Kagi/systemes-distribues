package fr.polytech.projet.naturalthescattering.db;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Thread {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date date;
	
	private boolean open = true;
	
	@Lob
	private String content;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(foreignKey=@ForeignKey(name="thread_author_ref"))
	private User author;
	
	@OneToMany(cascade={CascadeType.MERGE, CascadeType.REMOVE}, targetEntity=Message.class, mappedBy="thread")
	private List<AccountCard> accountcards;
	
	protected Thread() {}
	
	public Thread(String name, User author, String content) {
		this.name = name;
		this.author = author;
		this.content = content;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public User getAuthor() {
		return this.author;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public boolean getOpen() {
		return this.open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public String getContent() {
		return this.content;
	}
	
	@Override
	public String toString() {
		return "[Thread(id=" + getId() + " | nom=" + getName() + " | date=" + getDate() + " | open=" + getOpen() + " | auteur=" + (getAuthor() == null ? null : getAuthor().getId()) + ")]";
	}
}
