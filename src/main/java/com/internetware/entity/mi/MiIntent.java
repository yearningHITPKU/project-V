package com.internetware.entity.mi;

import java.io.Serializable;

public class MiIntent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String query;
	
	private String score;
	
	private String complete;
	
	private String domain;
	
	private String confidence;
	
	private String skillType;
	
	private String sub_domain;
	
	private String app_id;
	
	private String request_type;
	
	private String need_fetch_token;
	
	private String is_direct_wakeup;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getComplete() {
		return complete;
	}

	public void setComplete(String complete) {
		this.complete = complete;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getSkillType() {
		return skillType;
	}

	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}

	public String getSub_domain() {
		return sub_domain;
	}

	public void setSub_domain(String sub_domain) {
		this.sub_domain = sub_domain;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public String getNeed_fetch_token() {
		return need_fetch_token;
	}

	public void setNeed_fetch_token(String need_fetch_token) {
		this.need_fetch_token = need_fetch_token;
	}

	public String getIs_direct_wakeup() {
		return is_direct_wakeup;
	}

	public void setIs_direct_wakeup(String is_direct_wakeup) {
		this.is_direct_wakeup = is_direct_wakeup;
	}

	@Override
	public String toString() {
		return "MiIntent [query=" + query + ", score=" + score + ", complete=" + complete + ", domain=" + domain
				+ ", confidence=" + confidence + ", skillType=" + skillType + ", sub_domain=" + sub_domain + ", app_id="
				+ app_id + ", request_type=" + request_type + ", need_fetch_token=" + need_fetch_token
				+ ", is_direct_wakeup=" + is_direct_wakeup + "]";
	}

}
