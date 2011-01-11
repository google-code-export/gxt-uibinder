/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class AbsoluteLayoutView extends Composite implements View {
	static interface Binder extends UiBinder<Component, AbsoluteLayoutView> {}
	private static Binder UIBINDER = GWT.create(Binder.class);
	
	@UiField LayoutContainer item1;

	public AbsoluteLayoutView() {
		initComponent(UIBINDER.createAndBindUi(this));
	}
	
	/**
	 * @return the item1
	 */
	public LayoutContainer getItem1() {
		return item1;
	}
}
