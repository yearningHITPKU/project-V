package com.internetware.entity.api;

import java.util.HashMap;

public class ResBody {
	String versionid;
	boolean is_end;
	String sequence;
	Long timestamp;
	//String need_slot;
	Directive directive;
	//Card push_to_app;
	//Directive repeat_directive;
	//HashMap<String,String> extend;
	public String getVersionid() {
		return versionid;
	}
	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}
	public boolean isIs_end() {
		return is_end;
	}
	public void setIs_end(boolean is_end) {
		this.is_end = is_end;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Directive getDirective() {
		return directive;
	}
	public void setDirective(Directive directive) {
		this.directive = directive;
	}
	@Override
	public String toString() {
		return "ResBody [versionid=" + versionid + ", is_end=" + is_end + ", sequence=" + sequence + ", timestamp="
				+ timestamp + ", directive=" + directive + "]";
	}
	
}
