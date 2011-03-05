/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class RowLayoutView extends Composite implements View {

	static interface Binder extends UiBinder<Component, RowLayoutView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public RowLayoutView() {
		initComponent(BINDER.createAndBindUi(this));
	}

}
