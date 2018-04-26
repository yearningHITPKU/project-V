package com.internetware.entity.mi;

import java.io.Serializable;

public class MiToSpeak implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * tts需要说的类型:
	 * 0：TTS
	 * 1：Audio
	 * 2：ssml,目前只支持TTS
	 * */
	private int type;
	
	//tts需要说的文字
	private String text;

	public int getType() {
		return type;
	}

	public void setType(int type) {
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
		return "MiToSpeak [type=" + type + ", text=" + text + "]";
	}
}
