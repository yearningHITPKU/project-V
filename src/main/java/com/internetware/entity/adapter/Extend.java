package com.internetware.entity.adapter;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Extend {

	@JsonProperty(value = "POI")
	private ArrayList<VBLocation> poi = new ArrayList<VBLocation>();

	public ArrayList<VBLocation> getPoi() {
		return poi;
	}

	public void setPoi(ArrayList<VBLocation> poi) {
		this.poi = poi;
	}

	@Override
	public String toString() {
		return "Extend [poi=" + poi + "]";
	}
}
