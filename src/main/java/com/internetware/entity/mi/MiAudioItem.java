package com.internetware.entity.mi;

import java.io.Serializable;

public class MiAudioItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private MiStream stream;

	public MiStream getStream() {
		return stream;
	}

	public void setStream(MiStream stream) {
		this.stream = stream;
	}

	@Override
	public String toString() {
		return "MiAudioItem [stream=" + stream + "]";
	}	
}
