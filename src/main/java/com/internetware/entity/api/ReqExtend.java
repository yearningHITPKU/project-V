package com.internetware.entity.api;

import java.io.Serializable;
import java.util.ArrayList;

public class ReqExtend implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<VoiceBoxLocation> POI;

	public ArrayList<VoiceBoxLocation> getPOI() {
		return POI;
	}

	public void setPOI(ArrayList<VoiceBoxLocation> pOI) {
		POI = pOI;
	}

	@Override
	public String toString() {
		return "ReqExtend [POI=" + POI + "]";
	}

}
