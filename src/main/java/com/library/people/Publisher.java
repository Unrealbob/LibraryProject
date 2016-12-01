package com.library.people;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.library.object.ObjectInformation;

@Entity(name="PUBLISHER")
public class Publisher {
	
	@ManyToMany(targetEntity = ObjectInformation.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="OBJECTINFO_PUBLISHER",
			joinColumns=@JoinColumn(name="PUBLISHER_ID", referencedColumnName="PUBLISHER_ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"))
	private List<ObjectInformation> objectInfos = new ArrayList<>();
	
	@Id
	@Column(name="PUBLISHER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="NAME")
	private String name;

	public List<ObjectInformation> getObjectInfos() {
		return objectInfos;
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
