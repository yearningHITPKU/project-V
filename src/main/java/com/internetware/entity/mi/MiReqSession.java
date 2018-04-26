package com.internetware.entity.mi;

import java.io.Serializable;
import java.util.HashMap;

public class MiReqSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String session_id;
	
	private MiApplication application;
	
	private MiUser user;
	
	private MiAttributes attributes;

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public MiApplication getApplication() {
		return application;
	}

	public void setApplication(MiApplication application) {
		this.application = application;
	}

	public MiUser getUser() {
		return user;
	}

	public void setUser(MiUser user) {
		this.user = user;
	}
	
	public MiAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(MiAttributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "MiSession [session_id=" + session_id + ", application=" + application + ", user=" + user
				+ ", attributes=" + attributes + "]";
	}
}

