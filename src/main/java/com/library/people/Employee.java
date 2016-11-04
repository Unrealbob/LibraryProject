package com.library.people;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.library.Library;
import com.library.lending.LendingInformation;

@Entity(name="EMPLOYEE")
public class Employee extends Human {
	
	@Column(name="EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	
	@Column(name="SALARY")
	private long salary;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LIBRARY_ID")
	private Library library;
	
	@OneToMany(mappedBy="employee")
	List<LendingInformation> lendingInfos;
	
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
	public void setLendingInfos(List<LendingInformation> lendingInfos) {
		this.lendingInfos = lendingInfos;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
	

}
