/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.button;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class ButtonsView extends Composite implements View {
	
	static interface Binder extends UiBinder<Component, ButtonsView> {}
	private static Binder UIBINDER = GWT.create(Binder.class);

	public ButtonsView() {
		initComponent(UIBINDER.createAndBindUi(this));
	}
}
