package com.internetware.entity.mi;

import java.io.Serializable;

public class MiWidgetInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private String category;
	
	private String package_name;
	
	private int min_version_code;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}

	public int getMin_version_code() {
		return min_version_code;
	}

	public void setMin_version_code(int min_version_code) {
		this.min_version_code = min_version_code;
	}

	@Override
	public String toString() {
		return "MiWidgetsInfo [category=" + category + ", package_name=" + package_name + ", min_version_code="
				+ min_version_code + "]";
	}
}
