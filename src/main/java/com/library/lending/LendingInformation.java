package com.library.lending;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.library.people.Customer;
import com.library.people.Employee;

@Entity(name="LENDING_INFORMATION")
public class LendingInformation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="LENDING_OBJECTS_ID")
	private LendingObject lendingObjects;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="EMPLOYEE_ID")
	private Employee employee;
	

	@Column(name="LENDING_DATE")
	private Date lendingDate;
	
	@Column(name="PLANNED_RETURN")
	private Date plannedReturn;
	
	@Column(name="RETURN_DATE")
	private Date returnDate;
	
	@Column(name="PENALTY_PAYMENT")
	private int penaltyPayment;
	
	@Column(name="status")
	private ObjectStatus status;

	public LendingObject getLendingObjects() {
		return lendingObjects;
	}

	public void setLendingObjects(LendingObject lendingObjects) {
		this.lendingObjects = lendingObjects;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getLendingDate() {
		return lendingDate;
	}

	public void setLendingDate(Date lendingDate) {
		this.lendingDate = lendingDate;
	}

	public Date getPlannedReturn() {
		return plannedReturn;
	}

	public void setPlannedReturn(Date plannedReturn) {
		this.plannedReturn = plannedReturn;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getPenaltyPayment() {
		return penaltyPayment;
	}

	public void setPenaltyPayment(int penaltyPayment) {
		this.penaltyPayment = penaltyPayment;
	}

	public ObjectStatus getStatus() {
		return status;
	}

	public void setStatus(ObjectStatus status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
