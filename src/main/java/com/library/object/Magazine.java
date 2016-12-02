package com.library.object;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="MAGAZINE")
public class Magazine extends PrintMedia{

	@Column(name="VERSION")
	private int version;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	

	@Override
	public String toString() {
		return "Magazine " + this.getName();
	}

}
