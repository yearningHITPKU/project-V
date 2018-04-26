package com.internetware.entity.adapter;

public class User {
	
	private String userId;
	
	private String accessToken;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", accessToken=" + accessToken + "]";
	}
}
