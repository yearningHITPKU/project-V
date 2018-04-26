package com.internetware.entity.api;

import java.io.Serializable;

public class DZDPCity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String cityId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", cityId=" + cityId + "]";
	}

}
