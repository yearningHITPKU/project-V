package com.internetware.entity.api;

import java.io.Serializable;

public class LocalLifeRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pkgName;
	
	private String versionName;
	
	private String methodName;
	
	private String argsJSONStr;

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

	public String getArgsJSONStr() {
		return argsJSONStr;
	}

	public void setArgsJSONStr(String argsJSONStr) {
		this.argsJSONStr = argsJSONStr;
	}

	@Override
	public String toString() {
		return "LocalLifeRequest [pkgName=" + pkgName + ", versionName=" + versionName + ", methodName=" + methodName
				+ ", argsJSONStr=" + argsJSONStr + "]";
	}

}
