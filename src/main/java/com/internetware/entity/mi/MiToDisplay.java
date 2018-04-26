package com.internetware.entity.mi;

import java.io.Serializable;

public class MiToDisplay implements Serializable{

	private static final long serialVersionUID = 1L;

	private int type;
	
	private MiWidget widget;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public MiWidget getWidget() {
		return widget;
	}

	public void setWidget(MiWidget widget) {
		this.widget = widget;
	}

	@Override
	public String toString() {
		return "MiToDisplay [type=" + type + ", widget=" + widget + "]";
	}
}
