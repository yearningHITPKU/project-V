package com.internetware.entity.api;

import java.io.Serializable;
import java.util.Map;

public class Session implements Serializable{
	
	private static final long serialVersionUID = 1L;

	boolean is_new;
	
	String session_id;
	
	Map<String, String> attributes;

	public boolean isIs_new() {
		return is_new;
	}

	public void setIs_new(boolean is_new) {
		this.is_new = is_new;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "Session [is_new=" + is_new + ", session_id=" + session_id + ", attributes=" + attributes + "]";
	}

}
