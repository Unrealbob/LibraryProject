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

import com.library.object.ObjectInformation;

@Entity(name="PUBLISHER")
public class Publisher {
	
	@ManyToMany
	@JoinTable(
			name="OBJECTINFO_PUBLISHER",
			joinColumns=@JoinColumn(name="PUBLISHER_ID", referencedColumnName="PUBLISHER_ID"),
			inverseJoinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"))
	private List<ObjectInformation> objectInfos;
	
	@Id
	@Column(name="PUBLISHER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

	public List<ObjectInformation> getObjectInfos() {
		return objectInfos;
	}

	public void setObjectInfos(List<ObjectInformation> objectInfos) {
		this.objectInfos = objectInfos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
