package com.internetware.entity.mi;

import java.io.Serializable;

public class MiRequestBody implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int type;
	
	private String request_id;
	
	private long timestamp;
	
	private String locale;
	
	private MiIntent intent;
	
	private boolean no_response;
	
	private String event_type;
	
	private MiEventProperty event_property;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public MiIntent getIntent() {
		return intent;
	}

	public void setIntent(MiIntent intent) {
		this.intent = intent;
	}

	public boolean isNo_response() {
		return no_response;
	}

	public void setNo_response(boolean no_response) {
		this.no_response = no_response;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public MiEventProperty getEvent_property() {
		return event_property;
	}

	public void setEvent_property(MiEventProperty event_property) {
		this.event_property = event_property;
	}

	@Override
	public String toString() {
		return "MiRequestBody [type=" + type + ", request_id=" + request_id + ", timestamp=" + timestamp + ", locale="
				+ locale + ", intent=" + intent + ", no_response=" + no_response + ", event_type=" + event_type
				+ ", event_property=" + event_property + "]";
	}
}
