package com.library.people;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.library.object.Title;

@Entity(name="ARTIST")
public class Artist {
	
	@ManyToMany
	@JoinTable(
			name="TITLES_ARTIST",
			joinColumns=@JoinColumn(name="ARTIST_ID", referencedColumnName="ARTIST_ID"),
			inverseJoinColumns=@JoinColumn(name="TITLE_ID", referencedColumnName="TITLE_ID"))
	private List<Title> titles;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ARTIST_ID")
	private long id;
	
	@Column(name="NAME")
	private String name;

	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
