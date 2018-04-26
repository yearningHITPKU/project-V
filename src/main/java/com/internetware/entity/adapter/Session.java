package com.internetware.entity.adapter;

import java.util.HashMap;

public class Session {

	private boolean isNew;
	
	private String sessionId;
	
	private Application application;
	
	private HashMap<String, String> attributes;
	
	private User user;

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashMap<String, String> attributes) {
		this.attributes = attributes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Session [isNew=" + isNew + ", sessionId=" + sessionId + ", application=" + application + ", attributes="
				+ attributes + ", user=" + user + "]";
	}
}
