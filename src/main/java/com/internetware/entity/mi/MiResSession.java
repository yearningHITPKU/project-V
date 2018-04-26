package com.internetware.entity.mi;

import java.io.Serializable;
import java.util.HashMap;

public class MiResSession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private MiAttributes attributes;

	public MiAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(MiAttributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public String toString() {
		return "MiResSession [attributes=" + attributes + "]";
	}
}
