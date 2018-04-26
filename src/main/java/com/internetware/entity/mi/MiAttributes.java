package com.internetware.entity.mi;

import java.io.Serializable;

import com.internetware.entity.api.VoiceBoxLocation;

public class MiAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int noResponseCount = 0;
	
	private int notUnderstandCount = 0;

	private String lastsaying = "";//音箱返回给用户的话
	
	private VoiceBoxLocation boxlocation = new VoiceBoxLocation();//音箱的位置保存
	
	private String foodname = "";//用户想吃的东西
	
	private String shopname = "";//优惠的店铺
	
	private String mtCityID = "-1";
	
	private String dzdpCityID = "-1";
	
	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public VoiceBoxLocation getBoxlocation() {
		return boxlocation;
	}

	public void setBoxlocation(VoiceBoxLocation boxlocation) {
		this.boxlocation = boxlocation;
	}

	public String getLastsaying() {
		return lastsaying;
	}

	public void setLastsaying(String lastsaying) {
		this.lastsaying = lastsaying;
	}

	public int getNoResponseCount() {
		return noResponseCount;
	}

	public void setNoResponseCount(int noResponseCount) {
		this.noResponseCount = noResponseCount;
	}

	public int getNotUnderstandCount() {
		return notUnderstandCount;
	}

	public void setNotUnderstandCount(int notUnderstandCount) {
		this.notUnderstandCount = notUnderstandCount;
	}

	public String getMtCityID() {
		return mtCityID;
	}

	public void setMtCityID(String mtCityID) {
		this.mtCityID = mtCityID;
	}

	public String getDzdpCityID() {
		return dzdpCityID;
	}

	public void setDzdpCityID(String dzdpCityID) {
		this.dzdpCityID = dzdpCityID;
	}

	@Override
	public String toString() {
		return "MiAttributes [noResponseCount=" + noResponseCount + ", notUnderstandCount=" + notUnderstandCount
				+ ", lastsaying=" + lastsaying + ", boxlocation=" + boxlocation + ", foodname=" + foodname
				+ ", shopname=" + shopname + ", mtCityID=" + mtCityID + ", dzdpCityID=" + dzdpCityID + "]";
	}
}
