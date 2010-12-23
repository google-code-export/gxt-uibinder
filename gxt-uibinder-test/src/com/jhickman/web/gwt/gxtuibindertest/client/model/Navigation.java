/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.place.shared.Place;

/**
 * @author hickman
 *
 */
public class Navigation extends BaseTreeModel implements Serializable {
	private static final long serialVersionUID = -3100932286728420736L;
	
	public Navigation() {
	}
	
	public Navigation(String name) {
		this();
		set("name", name);
	}

	public Navigation(String name, Place place) {
		this(name);
		set("place", place);
	}
	
	public String getName() {
		return get("name");
	}
	
	public Place getPlace() {
		return get("place");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if ( ! (obj instanceof Navigation)) return false;
		
		Navigation that = (Navigation) obj;
		
		return this.getPlace().equals(that.getPlace()); 
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
