package com.internetware.entity.mi;

import java.io.Serializable;
import java.util.ArrayList;

public class MiResponseBody implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private boolean open_mic;
	
	private boolean not_understand;
	
	private MiToSpeak to_speak;
	
	private MiToDisplay to_display;
	
	private ArrayList<MiDirective> directives;

	public boolean isOpen_mic() {
		return open_mic;
	}

	public void setOpen_mic(boolean open_mic) {
		this.open_mic = open_mic;
	}

	public boolean isNot_understand() {
		return not_understand;
	}

	public void setNot_understand(boolean not_understand) {
		this.not_understand = not_understand;
	}

	public MiToSpeak getTo_speak() {
		return to_speak;
	}

	public void setTo_speak(MiToSpeak to_speak) {
		this.to_speak = to_speak;
	}

	public MiToDisplay getTo_display() {
		return to_display;
	}

	public void setTo_display(MiToDisplay to_display) {
		this.to_display = to_display;
	}

	public ArrayList<MiDirective> getDirectives() {
		return directives;
	}

	public void setDirectives(ArrayList<MiDirective> directives) {
		this.directives = directives;
	}

	@Override
	public String toString() {
		return "MiResponseBody [open_mic=" + open_mic + ", not_understand=" + not_understand + ", to_speak=" + to_speak
				+ ", to_display=" + to_display + ", directives=" + directives + "]";
	}
}
