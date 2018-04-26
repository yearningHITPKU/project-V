package com.internetware.entity.api;

import java.io.Serializable;

public class RichTextFormat implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String type;
	
	String content;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "RichTextFormat [type=" + type + ", content=" + content + "]";
	}
	
}
