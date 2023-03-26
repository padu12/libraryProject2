package ru.alishev.springcourse.models;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Book")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "year")
	private int year;
	
	@ManyToOne
	@JoinColumn(name = "person_id", referencedColumnName = "person_id")
	private Person owner;

	@Column(name = "took_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar took_at;
	
	@Transient
	private boolean isOverdue;
	
	public Book(String name, String author, int year, Date took_at) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
	}

	public boolean getOverdue() {
		return isOverdue;
	}

	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}

	public Calendar getTook_at() {
		return took_at;
	}

	public void setTook_at(Calendar took_at) {
		this.took_at = took_at;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Book() {
		super();
	}

	public int getBook_id() {
		return book_id;
	}


	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
