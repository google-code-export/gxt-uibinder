/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.model;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 * Example from GXT Showcase
 */
public class Music extends BaseTreeModel {

	public Music() {}
	
	public Music(String name) {
		set("name", name);
	}
	
	public Music(String name, String author, String genre) {
		set("name", name);
		set("author", author);
		set("genre", genre);
	}
	
	public String getName() {
		return (String) get("name");
	}
	
	public String getAuthor() {
		return (String) get("author");
	}
	
	public String getGenre() {
		return (String) get("genre");
	}
	
	public String toString() {
		return getName();
	}
}
