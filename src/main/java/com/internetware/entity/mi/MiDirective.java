package com.internetware.entity.mi;

import java.io.Serializable;

public class MiDirective implements Serializable{

	private static final long serialVersionUID = 1L;

	private String type;
	
	private MiAudioItem audio_item;
	
	private MiTTSItem tts_item;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MiAudioItem getAudio_item() {
		return audio_item;
	}

	public void setAudio_item(MiAudioItem audio_item) {
		this.audio_item = audio_item;
	}

	public MiTTSItem getTts_item() {
		return tts_item;
	}

	public void setTts_item(MiTTSItem tts_item) {
		this.tts_item = tts_item;
	}

	@Override
	public String toString() {
		return "MiDirective [type=" + type + ", audio_item=" + audio_item + ", tts_item=" + tts_item + "]";
	}
}
