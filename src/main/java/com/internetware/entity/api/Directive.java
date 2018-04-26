package com.internetware.entity.api;

import java.io.Serializable;
import java.util.Arrays;

public class Directive implements Serializable{

	private static final long serialVersionUID = 1L;
	
	DirectiveItem[] directive_items;

	public DirectiveItem[] getDirective_items() {
		return directive_items;
	}

	public void setDirective_items(DirectiveItem[] directive_items) {
		this.directive_items = directive_items;
	}

	@Override
	public String toString() {
		return "Directive [directive_items=" + Arrays.toString(directive_items) + "]";
	}

}
