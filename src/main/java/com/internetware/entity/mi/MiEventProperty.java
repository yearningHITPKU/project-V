package com.internetware.entity.mi;

import java.io.Serializable;

public class MiEventProperty implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String msg_file_id;

	public String getMsg_file_id() {
		return msg_file_id;
	}

	public void setMsg_file_id(String msg_file_id) {
		this.msg_file_id = msg_file_id;
	}

	@Override
	public String toString() {
		return "MiEventProperty [msg_file_id=" + msg_file_id + "]";
	}
}
