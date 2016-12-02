package com.library.object;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="CD")
public class CD extends Media{
	
	@Column(name="TITLES")
	private int amoundOfTitles;
	
	@ManyToMany(targetEntity = Title.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="CD_TITLES",
			joinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"),
			inverseJoinColumns=@JoinColumn(name="TITLE_ID", referencedColumnName="TITLE_ID"))
	private List<Title> cdTitles = new ArrayList<Title>();
	
	//TODO: Reconsider AmoundOfTitles - it can be computed and makes no sense to store

	public int getAmoundOfTitles() {
		return amoundOfTitles;
	}

	public void setAmoundOfTitles(int amoundOfTitles) {
		this.amoundOfTitles = amoundOfTitles;
	}

	public List<Title> getTitles() {
		return cdTitles;
	}

	public void addTitle(Title title) {
		this.cdTitles.add(title);
		title.getCds().add(this);
	}

	public void removeTitle(Title title) {
		this.cdTitles.remove(title);
		title.getCds().remove(this);
	}
	
	@Override
	public String toString() {
		return "CD " + this.getName();
	}

}
