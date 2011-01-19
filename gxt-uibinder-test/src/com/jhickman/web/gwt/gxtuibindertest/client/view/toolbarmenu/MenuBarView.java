/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.toolbarmenu;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class MenuBarView extends Composite implements View {

	static interface Binder extends UiBinder<Component, MenuBarView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public MenuBarView() {
		initComponent(BINDER.createAndBindUi(this));
	}

}
