package com.internetware.entity.api;

import java.io.Serializable;
import java.util.Arrays;

public class Card implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String title;
	
	String type;
	
	String text;
	
	RichTextFormat[] rich_contents;
	
	String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public RichTextFormat[] getRich_contents() {
		return rich_contents;
	}

	public void setRich_contents(RichTextFormat[] rich_contents) {
		this.rich_contents = rich_contents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Card [title=" + title + ", type=" + type + ", text=" + text + ", rich_contents="
				+ Arrays.toString(rich_contents) + ", url=" + url + "]";
	}

}
