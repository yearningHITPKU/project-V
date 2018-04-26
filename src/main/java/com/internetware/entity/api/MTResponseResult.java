package com.internetware.entity.api;

import java.io.Serializable;
import java.util.ArrayList;

public class MTResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<T> list;
	
	private String totalCount;
	
	private String count;
	
	private String title;

	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "MTResponseResult [list=" + list + ", totalCount=" + totalCount + ", count=" + count + ", title=" + title
				+ "]";
	}
}
