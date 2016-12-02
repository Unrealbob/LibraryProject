package com.library.people;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	
	@OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="PASS_ID")
	private Pass pass;
	
	//Don't remove past lendings if a customer is removed
	@OneToMany(mappedBy="customer", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = false)
	private List<LendingInformation> lendingInfos = new ArrayList<>();
	
	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public Pass getPass() {
		return pass;
	}

	public void addPass(Pass pass) {
		this.pass = pass;
		pass.setCustomer(this);
	}
	
	public void removePass() {
		if(this.pass != null) {
			this.pass.setCustomer(null);
		}
		this.pass = null;
	}

	public List<LendingInformation> getLendingInfos() {
		return lendingInfos;
	}

	public void addLendingInformation(LendingInformation lendingInformation) {
		this.lendingInfos.add(lendingInformation);
		lendingInformation.setCustomer(this);
	}

	public void removeLendingInformation(LendingInformation lendingInformation) {
		lendingInformation.setCustomer(null);
		this.lendingInfos.remove(lendingInformation);
	}
	
	@Override
	public String toString() {
		return String.format("Customer %s %s", getFirstName(), getSecondName());
	}
}
