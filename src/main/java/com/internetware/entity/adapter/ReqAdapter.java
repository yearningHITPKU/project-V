package com.internetware.entity.adapter;

public class ReqAdapter {
	
	private String platform;
	
	private String version;
	
	private Session session;
	
	private ReqBody request;
	
	private Extend extend;
	
	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ReqBody getRequest() {
		return request;
	}

	public void setRequest(ReqBody request) {
		this.request = request;
	}

	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}

	@Override
	public String toString() {
		return "ReqAdapter [platform=" + platform + ", version=" + version + ", session=" + session + ", request="
				+ request + ", extend=" + extend + "]";
	}
}
