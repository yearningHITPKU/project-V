package com.internetware.entity.api;

import java.io.Serializable;

public class MTCity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cityAcronym;
	
	private String cityId;
	
	private String cityName;
	
	private String districtId;
	
	private String districtName;
	
	private String name;

	public String getCityAcronym() {
		return cityAcronym;
	}

	public void setCityAcronym(String cityAcronym) {
		this.cityAcronym = cityAcronym;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MTCity [cityAcronym=" + cityAcronym + ", cityId=" + cityId + ", cityName=" + cityName + ", districtId="
				+ districtId + ", districtName=" + districtName + ", name=" + name + "]";
	}

}
