/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.impl;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibindertest.client.view.AbsoluteLayoutView;

/**
 * @author hickman
 *
 */
public class AbsoluteLayoutViewImpl extends Composite implements AbsoluteLayoutView {
	static interface Binder extends UiBinder<Component, AbsoluteLayoutViewImpl> {}
	private static Binder UIBINDER = GWT.create(Binder.class);
	
	@UiField LayoutContainer item1;

	public AbsoluteLayoutViewImpl() {
		initComponent(UIBINDER.createAndBindUi(this));
	}
	
	/**
	 * @return the item1
	 */
	@Override
	public LayoutContainer getItem1() {
		return item1;
	}
}
