package com.internetware.entity.api;

public class dpdiscount {
	String address;
	String consumptionAverage;
	String evaluate;
	String tag;
	String name;
	String shopId;
	String distance;
	String commentNum;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConsumptionAverage() {
		return consumptionAverage;
	}
	public void setConsumptionAverage(String consumptionAverage) {
		this.consumptionAverage = consumptionAverage;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	@Override
	public String toString() {
		return "dpdiscount [address=" + address + ", consumptionAverage=" + consumptionAverage + ", evaluate="
				+ evaluate + ", tag=" + tag + ", name=" + name + ", shopId=" + shopId + ", distance=" + distance
				+ ", commentNum=" + commentNum + "]";
	}
	
}
