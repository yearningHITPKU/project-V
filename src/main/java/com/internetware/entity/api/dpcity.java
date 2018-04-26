package com.internetware.entity.api;

public class dpcity {
	String name;
	String cityId;
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
		return "dpcity [name=" + name + ", cityId=" + cityId + "]";
	}
	
}
