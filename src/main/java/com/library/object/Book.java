package com.library.object;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="BOOK")
public class Book extends PrintMedia{
	
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="EDITION")
	private int edition;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	
	@Override
	public String toString() {
		return "Book " + this.getName();
	}

}
