package com.library.object;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="CD")
public class CD extends Media{
	
	@Column(name="TITLES")
	private int amoundOfTitles;
	
	@ManyToMany(targetEntity = Title.class)
	@JoinTable(
			name="CD_TITLES",
			joinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"),
			inverseJoinColumns=@JoinColumn(name="TITLE_ID", referencedColumnName="TITLE_ID"))
	private List<Title> cdTitles;
	
	

	public int getAmoundOfTitles() {
		return amoundOfTitles;
	}

	public void setAmoundOfTitles(int amoundOfTitles) {
		this.amoundOfTitles = amoundOfTitles;
	}

	public List<Title> getTitles() {
		return cdTitles;
	}

	public void setTitles(List<Title> titles) {
		this.cdTitles = titles;
	}
	
	

}
