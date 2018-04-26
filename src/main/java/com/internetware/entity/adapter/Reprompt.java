package com.internetware.entity.adapter;

public class Reprompt {
	
	private String outputSpeech;

	public String getOutputSpeech() {
		return outputSpeech;
	}

	public void setOutputSpeech(String outputSpeech) {
		this.outputSpeech = outputSpeech;
	}

	@Override
	public String toString() {
		return "Reprompt [outputSpeech=" + outputSpeech + "]";
	}
}
