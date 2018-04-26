package com.internetware.entity.adapter;

public class DireItem {
	
	private String type = "audio";
	
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "DireItem [type=" + type + ", url=" + url + "]";
	}
}
