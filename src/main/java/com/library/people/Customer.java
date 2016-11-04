package com.library.people;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.library.Pass;
import com.library.lending.LendingInformation;

@Entity(name="CUSTOMER")
public class Customer extends Human{
	
	@Column(name="STATUS")
	private CustomerStatus status;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PASS_ID")
	private Pass pass;
	
	@OneToMany(mappedBy="customer")
	private List<LendingInformation> lendingInfos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee;
	

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public Pass getPass() {
		return pass;
	}

	public void setPass(Pass pass) {
		this.pass = pass;
	}

	public List<LendingInformation> getLendingInfos() {
		return lendingInfos;
	}

	public void setLendingInfos(List<LendingInformation> lendingInfos) {
		this.lendingInfos = lendingInfos;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
