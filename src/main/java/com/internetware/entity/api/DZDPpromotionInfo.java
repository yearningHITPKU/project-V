package com.internetware.entity.api;

import java.io.Serializable;

public class DZDPpromotionInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String interest;
	
	private String validity;
	
	private String title;

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "DZDPpromotionInfo [interest=" + interest + ", validity=" + validity + ", title=" + title + "]";
	}
}
