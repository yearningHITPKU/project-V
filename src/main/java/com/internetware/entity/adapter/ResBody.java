package com.internetware.entity.adapter;

import java.util.ArrayList;

public class ResBody {
	
	private String outputSpeech;
	
	private Reprompt reprompt;
	
	private ArrayList<DireItem> directives;
	
	private boolean shouldEndSession;

	public String getOutputSpeech() {
		return outputSpeech;
	}

	public void setOutputSpeech(String outputSpeech) {
		this.outputSpeech = outputSpeech;
	}

	public Reprompt getReprompt() {
		return reprompt;
	}

	public void setReprompt(Reprompt reprompt) {
		this.reprompt = reprompt;
	}

	public ArrayList<DireItem> getDirectives() {
		return directives;
	}

	public void setDirectives(ArrayList<DireItem> directives) {
		this.directives = directives;
	}

	public boolean isShouldEndSession() {
		return shouldEndSession;
	}

	public void setShouldEndSession(boolean shouldEndSession) {
		this.shouldEndSession = shouldEndSession;
	}

	@Override
	public String toString() {
		return "ResBody [outputSpeech=" + outputSpeech + ", reprompt=" + reprompt + ", directives=" + directives
				+ ", shouldEndSession=" + shouldEndSession + "]";
	}
}
