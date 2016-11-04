package com.library.people;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="HUMAN")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Human {
	
	@Id 
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
	
	@Column(name="BIRTH_DATE")
	private Date bDate;
	@Column(name="SECOND_NAME")
	private String secondName;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="STREET")
	private String street;
	@Column(name="PLZ")
	private int plz;
	@Column(name="CITY")
	private String city;
	@Column(name="SEX")
	private Sexuality sex;
	
	
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public Sexuality getSex() {
		return sex;
	}
	public void setSex(Sexuality sex) {
		this.sex = sex;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
