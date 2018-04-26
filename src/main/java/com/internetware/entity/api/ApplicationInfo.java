package com.internetware.entity.api;

import java.io.Serializable;

public class ApplicationInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String application_name;
	
	String application_id;

	public String getApplication_name() {
		return application_name;
	}

	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	@Override
	public String toString() {
		return "ApplicationInfo [application_name=" + application_name + ", application_id=" + application_id + "]";
	}
}
