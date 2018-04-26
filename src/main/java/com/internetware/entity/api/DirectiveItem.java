package com.internetware.entity.api;

import java.io.Serializable;

public class DirectiveItem implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String content;//TTS播报内容,AUDIO 的url连接
	
	String type;//类型：1.TTS 2.AUDIO

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
		return "DirectiveItem [type=" + type + ", content=" + content + "]";
	}
	
}
