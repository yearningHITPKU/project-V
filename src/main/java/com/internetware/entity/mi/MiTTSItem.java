package com.internetware.entity.mi;

import java.io.Serializable;

public class MiTTSItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String text;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "MiTTSItem [type=" + type + ", text=" + text + "]";
	}
}
