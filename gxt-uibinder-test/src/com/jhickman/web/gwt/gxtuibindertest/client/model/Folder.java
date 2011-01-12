/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.model;

import java.io.Serializable;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;

/**
 * @author hickman
 *
 */
public class Folder extends BaseTreeModel implements Serializable {
	private static final long serialVersionUID = -3100932286728420736L;
	private static int ID = 0;
	
	public Folder() {
		set("id", ID++);
	}
	
	public Folder(String name) {
		this();
		set("name", name);
	}

	public Folder(String name, BaseTreeModel[] children) {
		this(name);
		if (children != null) { 
			for (BaseTreeModel child : children) {
				add(child);
			}
		}
	}
	
	public Integer getId() {
		return get("id");
	}
	
	public String getName() {
		return get("name");
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
