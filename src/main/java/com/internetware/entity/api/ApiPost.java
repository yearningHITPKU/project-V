package com.internetware.entity.api;

import java.util.HashMap;

public class ApiPost {
	String pkgName;
	String versionName;
	String methodName;
	HashMap<String,String> argsJSONStr;
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public HashMap<String, String> getArgsJSONStr() {
		return argsJSONStr;
	}
	public void setArgsJSONStr(HashMap<String, String> argsJSONStr) {
		this.argsJSONStr = argsJSONStr;
	}

}
