package com.library.object;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.library.people.Artist;

@Entity(name="TITLE")
public class Title {
	
	@Id
	@Column(name="TITLE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DURATION")
	private int duration;
	
	@ManyToMany(targetEntity = CD.class)
	@JoinTable(
			name="CD_TITLES",
			joinColumns=@JoinColumn(name="TITLE_ID", referencedColumnName="TITLE_ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"))
	private List<CD> cds;
	
	@ManyToMany
	@JoinTable(
			name="TITLES_ARTIST",
			joinColumns=@JoinColumn(name="TITLE_ID", referencedColumnName="TITLE_ID"),
			inverseJoinColumns=@JoinColumn(name="ARTIST_ID", referencedColumnName="ARTIST_ID"))
	private List<Artist> artists;

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<CD> getCds() {
		return cds;
	}

	public void setCds(List<CD> cds) {
		this.cds = cds;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	
	

}
