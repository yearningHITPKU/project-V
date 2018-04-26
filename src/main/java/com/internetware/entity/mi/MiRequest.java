package com.internetware.entity.mi;

import java.io.Serializable;

public class MiRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String version;
	
	private String query;
	
	private MiReqSession session;
	
	private MiContext context;
	
	private MiRequestBody request;
		
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public MiReqSession getSession() {
		return session;
	}

	public void setSession(MiReqSession session) {
		this.session = session;
	}

	public MiRequestBody getRequest() {
		return request;
	}

	public void setRequest(MiRequestBody request) {
		this.request = request;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public MiContext getContext() {
		return context;
	}

	public void setContext(MiContext context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "MiRequest [version=" + version + ", session=" + session + ", request=" + request + ", query=" + query
				+ ", context=" + context + "]";
	}

}
