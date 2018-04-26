package com.internetware.entity.mi;

import java.io.Serializable;

public class MiResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String version;
	
	private MiAttributes session_attributes;
	
	private boolean is_session_end;
	
	private MiResponseBody response;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public MiAttributes getSession_attributes() {
		return session_attributes;
	}

	public void setSession_attributes(MiAttributes session_attributes) {
		this.session_attributes = session_attributes;
	}

	public boolean isIs_session_end() {
		return is_session_end;
	}

	public void setIs_session_end(boolean is_session_end) {
		this.is_session_end = is_session_end;
	}

	public MiResponseBody getResponse() {
		return response;
	}

	public void setResponse(MiResponseBody response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "MiResponse [version=" + version + ", session_attributes=" + session_attributes + ", is_session_end="
				+ is_session_end + ", response=" + response + "]";
	}
}
