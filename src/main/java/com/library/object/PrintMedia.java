package com.library.object;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="PRINT_MEDIA")
public abstract class PrintMedia extends ObjectInformation {
	
	@Column(name="PAGE_NUMBERS")
	private int pageNumbers;

	public int getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(int pageNumbers) {
		this.pageNumbers = pageNumbers;
	}
	
	

}
