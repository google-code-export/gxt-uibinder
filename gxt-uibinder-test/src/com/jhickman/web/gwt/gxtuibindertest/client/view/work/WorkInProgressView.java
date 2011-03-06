/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.work;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class WorkInProgressView extends Composite implements View {

	static interface Binder extends UiBinder<Component, WorkInProgressView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public WorkInProgressView() {
		initComponent(BINDER.createAndBindUi(this));
	}

}
