package com.internetware.entity.api;

import java.io.Serializable;

public class LocalLifeResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String androidId;
	
	String taskId;
	
	String status;
	
	String returnJSONStr;
	
	String errMsg;
	
	LocalLifeRequest spec;

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnJSONStr() {
		return returnJSONStr;
	}

	public void setReturnJSONStr(String returnJSONStr) {
		this.returnJSONStr = returnJSONStr;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public LocalLifeRequest getSpec() {
		return spec;
	}

	public void setSpec(LocalLifeRequest spec) {
		this.spec = spec;
	}

	@Override
	public String toString() {
		return "LocalLifeResponse [androidId=" + androidId + ", taskId=" + taskId + ", status=" + status
				+ ", returnJSONStr=" + returnJSONStr + ", errMsg=" + errMsg + ", spec=" + spec + "]";
	}

}
