package com.internetware.entity.api;

import java.io.Serializable;

public class DZDPCustomerRecommend implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String recommendNum;
	
	private String price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecommendNum() {
		return recommendNum;
	}

	public void setRecommendNum(String recommendNum) {
		this.recommendNum = recommendNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DZDPCustomerRecommend [name=" + name + ", recommendNum=" + recommendNum + ", price=" + price + "]";
	}
}
