package com.internetware.entity.api;

import java.io.Serializable;

public class MTdescItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String iconUrl;
	
	private String description;

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
		return "MTdescItem [iconUrl=" + iconUrl + ", description=" + description + "]";
	}
}
