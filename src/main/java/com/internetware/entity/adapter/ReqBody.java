package com.internetware.entity.adapter;

public class ReqBody {
	
	private String type;
	
	private String requestId;
	
	private long timestamp;
	
	private String utterance;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUtterance() {
		return utterance;
	}

	public void setUtterance(String utterance) {
		this.utterance = utterance;
	}

	@Override
	public String toString() {
		return "ReqBody [type=" + type + ", requestId=" + requestId + ", timestamp=" + timestamp + ", utterance="
				+ utterance + "]";
	}
}
