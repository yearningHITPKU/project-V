package com.internetware.entity.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yearning
 *
 */
public class ReqBody implements Serializable{
	
	/**
	 * 请求包体
	 */
	private static final long serialVersionUID = 1L;

	private String versionid;//版本号，格式:X.X, 目前默认为：1.0
	
	private String status; //状态，LAUNCH、INTENT、END、NOTICE
	
	private String sequence; //交互流水号，唯一标识一次平台对开发者服务的调用
	
	private Long timestamp; //平台发送请求到开发者服务的的时间，格式为：当前时间的毫秒值
	
	private ApplicationInfo application_info;//本次用户输入的内容被理解为某个应用时的应用信息，用于一个开发者多个应用服务地址只有一个的时候的应用区分
	
	private Session session;//会话相关的数据，包含：本次会话的ID、用户历史输入内容、会话中保持的槽值等
	
	private User user;//用户相关的数据，包含：用户的ID、用户正对当前应用的一个基础设置
	
	private String input_text;//用户本次内容
	
	//个性化语义识别出来的用户当前输入对应的语义结果（槽值），当status为INTENT时不为空
	//注：槽值的key以及取值，与开发者当前的应用文法是一一对应的
	private Map<String, String> slots;
	
	private String ended_reason;//会话结束原因,当status为END时不为空，其它情况为空。
	
	private String notice_type;
	
	private HashMap<String, String> extend;

	public HashMap<String, String> getExtend() {
		return extend;
	}

	public void setExtend(HashMap<String, String> extend) {
		this.extend = extend;
	}

	public String getVersionid() {
		return versionid;
	}

	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public ApplicationInfo getApplication_info() {
		return application_info;
	}

	public void setApplication_info(ApplicationInfo application_info) {
		this.application_info = application_info;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getInput_text() {
		return input_text;
	}

	public void setInput_text(String input_text) {
		this.input_text = input_text;
	}

	public Map<String, String> getSlots() {
		return slots;
	}

	public void setSlots(Map<String, String> slots) {
		this.slots = slots;
	}

	public String getEnded_reason() {
		return ended_reason;
	}

	public void setEnded_reason(String ended_reason) {
		this.ended_reason = ended_reason;
	}

	public String getNotice_type() {
		return notice_type;
	}

	public void setNotice_type(String notice_type) {
		this.notice_type = notice_type;
	}

	@Override
	public String toString() {
		return "ReqBody [versionid=" + versionid + ", status=" + status + ", sequence=" + sequence + ", timestamp="
				+ timestamp + ", application_info=" + application_info + ", session=" + session + ", user=" + user
				+ ", input_text=" + input_text + ", slots=" + slots + ", ended_reason=" + ended_reason
				+ ", notice_type=" + notice_type + ", extend=" + extend + "]";
	}
}
