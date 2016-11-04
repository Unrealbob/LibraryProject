package com.library.object;

import javax.persistence.Entity;

@Entity(name="PRINT_MEDIA")
public abstract class PrintMedia extends ObjectInformation {
	
	private int pageNumbers;

	public int getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(int pageNumbers) {
		this.pageNumbers = pageNumbers;
	}
	
	

}
