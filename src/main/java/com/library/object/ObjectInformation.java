package com.library.object;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.library.lending.LendingObject;
import com.library.people.Publisher;

@Entity(name="OBJECT_INFORMATION")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ObjectInformation {
	
	@OneToMany(mappedBy="objectInformation", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LendingObject> lendingObjects = new ArrayList<LendingObject>();
	
	@ManyToMany(targetEntity = Publisher.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
			name="OBJECTINFO_PUBLISHER",
			joinColumns=@JoinColumn(name="OBJECT_INFORMATION_ID", referencedColumnName="OBJECT_INFORMATION_ID"),
			inverseJoinColumns=@JoinColumn(name="PUBLISHER_ID", referencedColumnName="PUBLISHER_ID"))
	private List<Publisher> publishers = new ArrayList<>();
	
	@Id
	@Column(name="OBJECT_INFORMATION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="OBJECT_INFORMATION_NAME")
	private String name;
	
	public List<LendingObject> getLendingObjects() {
		return this.lendingObjects;
	}
	
	public void addLendingObject(LendingObject lendingObject) {
		this.lendingObjects.add(lendingObject);
		lendingObject.setObjectInformation(this);
	}
	
	public void removeLendingObject(LendingObject lendingObject) {
		lendingObject.setObjectInformation(null);
		this.lendingObjects.remove(lendingObject);
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void addPublisher(Publisher publisher) {
		this.publishers.add(publisher);
		publisher.getObjectInfos().add(this);
	}

	public void removePublisher(Publisher publisher) {
		publisher.getObjectInfos().remove(this);
		this.publishers.remove(publisher);
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
