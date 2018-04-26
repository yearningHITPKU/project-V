package com.internetware.entity.adapter;

public class ResAdapter {
	
	private String version;
	
	private String requestId;
	
	private ResBody response;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ResBody getResponse() {
		return response;
	}

	public void setResponse(ResBody response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "ResAdapter [version=" + version + ", requestId=" + requestId + ", response=" + response + "]";
	}
}
