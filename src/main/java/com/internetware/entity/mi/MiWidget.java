package com.internetware.entity.mi;

import java.io.Serializable;

public class MiWidget implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private MiWidgetInfo info;//APP希望显示界面时提供的，此处为界面的标识信息

	public MiWidgetInfo getInfo() {
		return info;
	}

	public void setInfo(MiWidgetInfo info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "MiWidget [info=" + info + "]";
	}
}
