package com.internetware.entity.mi;

import java.io.Serializable;

public class MiContext implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object passport;

	public Object getPassport() {
		return passport;
	}

	public void setPassport(Object passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "MiContext [passport=" + passport + "]";
	}
}
