package com.library.lending;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.library.Library;
import com.library.object.ObjectInformation;

@Entity(name="LENDING_OBJECT")
public class LendingObject {
	
	@ManyToMany
	@JoinTable(
			name="LIBRARY_LENDINGOBJECTS",
			joinColumns=@JoinColumn(name="LENDING_OBJECT_ID", referencedColumnName="LENDING_OBJECT_ID"),
			inverseJoinColumns=@JoinColumn(name="LIBRARY_ID", referencedColumnName="LIBRARY_ID"))
	List<Library> librarys = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OBJECT_INFORMATION_ID")
	private ObjectInformation objectInformation;
	
	@OneToMany(mappedBy="lendingObject", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LendingInformation> lendingInfo = new ArrayList<>();
	
	@Id
	@Column(name="LENDING_OBJECT_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public LendingObject(ObjectInformation objectInformation) {
		this.objectInformation = objectInformation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Library> getLibrarys() {
		return librarys;
	}

	public void setLibrarys(List<Library> librarys) {
		this.librarys = librarys;
	}

	public ObjectInformation getObjectInformation() {
		return objectInformation;
	}

	public void setObjectInformation(ObjectInformation object) {
		this.objectInformation = object;
	}
	
	public List<LendingInformation> getLendingInformation() {
		return this.lendingInfo;
	}

	public void addLendingInformation(LendingInformation lendingInformation) {
		this.lendingInfo.add(lendingInformation);
		lendingInformation.setLendingObject(this);
	}
	
	public void removeLendingInformation(LendingInformation lendingInformation) {
		lendingInformation.setLendingObject(null);
		this.lendingInfo.remove(lendingInformation);
	}
	
	

}
