package com.internetware.entity.mi;

import java.io.Serializable;

public class MiStream implements Serializable{

	private static final long serialVersionUID = 1L;

	private String token;
	
	private String url;
	
	private String offset_in_milliseconds;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOffset_in_milliseconds() {
		return offset_in_milliseconds;
	}

	public void setOffset_in_milliseconds(String offset_in_milliseconds) {
		this.offset_in_milliseconds = offset_in_milliseconds;
	}

	@Override
	public String toString() {
		return "MiStream [token=" + token + ", url=" + url + ", offset_in_milliseconds=" + offset_in_milliseconds + "]";
	}
}
