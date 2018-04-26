package com.internetware.entity.api;

import java.util.List;

public class Psession {
/**
 * 个人的对话数据
 */
	String sessionid;
	List<String> histinput;//历史对话信息
	long timestamp;
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public List<String> getHistinput() {
		return histinput;
	}
	public void setHistinput(List<String> histinput) {
		this.histinput = histinput;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	
}
