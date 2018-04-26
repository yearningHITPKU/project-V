package com.internetware.entity.api;

import java.io.Serializable;
import java.util.Map;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String user_id;
	
	Map<String, String> attributes;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", attributes=" + attributes + "]";
	}

}
