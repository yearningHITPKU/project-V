package com.internetware.entity.api;

import java.io.Serializable;

public class ReturnJSONStr<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	private String errorCode;
	
	private String errorMessage;
	
	private T result;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ReturnJSONStr [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", result=" + result + "]";
	}

}
