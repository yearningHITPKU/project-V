package com.internetware.entity.api;

import java.io.Serializable;

public class MTrecommondationOItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MTrecommondationOItem [name=" + name + "]";
	}
}
