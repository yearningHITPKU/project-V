package com.internetware.entity.api;

import java.util.HashMap;

public class ApiReturn {
	String taskId;
	String status;
	String returnJSONStr;
	String errMsg;
	String androidId;
	HashMap<String,String> spec;
	int runningTime;
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
	public String getAndroidId() {
		return androidId;
	}
	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}
	public HashMap<String, String> getSpec() {
		return spec;
	}
	public void setSpec(HashMap<String, String> spec) {
		this.spec = spec;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	@Override
	public String toString() {
		return "ApiReturn [taskId=" + taskId + ", status=" + status + ", returnJSONStr=" + returnJSONStr + ", errMsg="
				+ errMsg + ", androidId=" + androidId + ", spec=" + spec + ", runningTime=" + runningTime + "]";
	}

	
}
