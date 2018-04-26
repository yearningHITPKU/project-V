package com.internetware.entity.api;

import java.io.Serializable;
import java.util.ArrayList;

public class DZDPdiscountInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String value;
	
	private String price;
	
	private String salesDesc;
	
	private String title;
	
	private ArrayList<String> tagList;
	
	private String id;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSalesDesc() {
		return salesDesc;
	}

	public void setSalesDesc(String salesDesc) {
		this.salesDesc = salesDesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<String> tagList) {
		this.tagList = tagList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DZDPdiscountInfo [value=" + value + ", price=" + price + ", salesDesc=" + salesDesc + ", title=" + title
				+ ", tagList=" + tagList + ", id=" + id + "]";
	}

}
