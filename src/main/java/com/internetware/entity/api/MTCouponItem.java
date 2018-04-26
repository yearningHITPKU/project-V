package com.internetware.entity.api;

import java.io.Serializable;
import java.util.ArrayList;

public class MTCouponItem implements Serializable{

	private static final long serialVersionUID = 1L;

	private String imageUrl;
	
	private String shopId;
	
	private String overallRating;
	
	private String distance;
	
	private String shopName;
	
	private String shortName;
	
	private ArrayList<MTdescItem> descList;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(String overallRating) {
		this.overallRating = overallRating;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public ArrayList<MTdescItem> getDescList() {
		return descList;
	}

	public void setDescList(ArrayList<MTdescItem> descList) {
		this.descList = descList;
	}

	@Override
	public String toString() {
		return "MTCouponItem [imageUrl=" + imageUrl + ", shopId=" + shopId + ", overallRating=" + overallRating
				+ ", distance=" + distance + ", shopName=" + shopName + ", shortName=" + shortName + ", descList="
				+ descList + "]";
	}
	
}
