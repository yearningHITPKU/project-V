package com.internetware.entity.mi;

import java.io.Serializable;

public class MiApplication implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String app_id;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	@Override
	public String toString() {
		return "MiApplication [app_id=" + app_id + "]";
	}	
}
