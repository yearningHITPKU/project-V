package com.internetware.entity.mi;

import java.io.Serializable;

public class MiUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String user_id;
	
	private String access_token;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	@Override
	public String toString() {
		return "MiUser [user_id=" + user_id + ", access_token=" + access_token + "]";
	}
}
