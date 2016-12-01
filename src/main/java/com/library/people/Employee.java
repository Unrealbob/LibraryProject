package com.library.people;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.library.Library;
import com.library.lending.LendingInformation;

@Entity(name = "EMPLOYEE")
public class Employee extends Human {

	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;

	@Column(name = "SALARY")
	private long salary;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "LIBRARY_ID", referencedColumnName = "LIBRARY_ID")
	private Library library;

	//Don't remove past lendings if an employee is removed
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = false)
	List<LendingInformation> lendingInfos = new ArrayList<LendingInformation>();

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public List<LendingInformation> getLendingInfos() {
		return lendingInfos;
	}

	public void addLendingInformation(LendingInformation lendingInformation) {
		this.lendingInfos.add(lendingInformation);
		lendingInformation.setEmployee(this);
	}

	public void removeLendingInformation(LendingInformation lendingInformation) {
		lendingInformation.setEmployee(null);
		this.lendingInfos.remove(lendingInformation);
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return String.format("Employee %s %s", this.getFirstName(), this.getSecondName());
	}
}
