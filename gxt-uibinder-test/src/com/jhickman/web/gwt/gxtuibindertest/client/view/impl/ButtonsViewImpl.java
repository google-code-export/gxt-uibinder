/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.impl;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.view.ButtonsView;

/**
 * @author hickman
 *
 */
public class ButtonsViewImpl extends Composite implements ButtonsView {
	
	static interface Binder extends UiBinder<Component, ButtonsViewImpl> {}
	private static Binder UIBINDER = GWT.create(Binder.class);

	public ButtonsViewImpl() {
		initComponent(UIBINDER.createAndBindUi(this));
	}
}
