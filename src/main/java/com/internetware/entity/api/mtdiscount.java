package com.internetware.entity.api;

import java.util.List;

public class mtdiscount {
	String imageUrl;
	String shopId;
	String overallRating;
	String distance;
	String shopName;
	List<descList> descList;
	public class descList{
	String iconUrl;
	String description;
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "descList [iconUrl=" + iconUrl + ", description=" + description + ", getIconUrl()=" + getIconUrl()
				+ ", getDescription()=" + getDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	}
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
	public List<descList> getDescList() {
		return descList;
	}
	public void setDescList(List<descList> descList) {
		this.descList = descList;
	}
	@Override
	public String toString() {
		return "mtdiscount [imageUrl=" + imageUrl + ", shopId=" + shopId + ", overallRating=" + overallRating
				+ ", distance=" + distance + ", shopName=" + shopName + ", descList=" + descList + "]";
	}


}