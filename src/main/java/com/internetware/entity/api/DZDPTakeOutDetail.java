package com.internetware.entity.api;

import java.io.Serializable;
import java.util.ArrayList;

public class DZDPTakeOutDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String deliveryTime;
	
	private String duringTime;
	
	private ArrayList<String> discount;
	
	private String deliverMoney;
	
	private String startMoney;

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDuringTime() {
		return duringTime;
	}

	public void setDuringTime(String duringTime) {
		this.duringTime = duringTime;
	}

	public ArrayList<String> getDiscount() {
		return discount;
	}

	public void setDiscount(ArrayList<String> discount) {
		this.discount = discount;
	}

	public String getDeliverMoney() {
		return deliverMoney;
	}

	public void setDeliverMoney(String deliverMoney) {
		this.deliverMoney = deliverMoney;
	}

	public String getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(String startMoney) {
		this.startMoney = startMoney;
	}

	@Override
	public String toString() {
		return "DZDPTakeOutDetail [deliveryTime=" + deliveryTime + ", duringTime=" + duringTime + ", discount="
				+ discount + ", deliverMoney=" + deliverMoney + ", startMoney=" + startMoney + "]";
	}
}
