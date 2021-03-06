package com.library;
/*
 * This Java source file was auto generated by running 'gradle buildInit --type java-library'
 * by 'unrea' at '28.10.16 14:46' with Gradle 3.0
 *
 * @author unrea, @date 28.10.16 14:46
 */

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
import javax.persistence.OneToMany;

import com.library.lending.LendingObject;
import com.library.people.Employee;

@Entity(name="LIBRARY")
public class Library {
	
	//This was ManyToMany in the model, but one physical book can only be at one physical location
	@OneToMany(targetEntity = LendingObject.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "library")
	List<LendingObject> lendingObjects = new ArrayList<>();
	
	@OneToMany(targetEntity= Employee.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "library")
	List<Employee> employees = new ArrayList<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LIBRARY_ID")
	private long id;
	
	@Column(name="NAME")
	private String name;
	@Column(name="STREET")
	private String street;
	@Column(name="HOUSENUMBER")
	private int houseNumber;
	@Column(name="PLZ")
	private int plz;
	@Column(name="CITY")
	private String city;
	@Column(name="TELEPHONENUMBER")
	private String telephoneNumber;
	
	
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public List<LendingObject> getLendingObjects() {
		return lendingObjects;
	}
	
	public void addLendingObject(LendingObject lendingObject) {
		this.lendingObjects.add(lendingObject);
		lendingObject.setLibrary(this);
	}
	
	public void removeLendingObject(LendingObject lendingObject) {
		lendingObject.setLibrary(null);
		this.lendingObjects.remove(lendingObject);
	}
	
	public List<Employee> getEmployees() {
		return this.employees;
	}
	
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
		employee.setLibrary(this);
	}
	
	public void removeEmployee(Employee employee) {
		employee.setLibrary(null);
		this.employees.remove(employee);
	}
	
	
}
