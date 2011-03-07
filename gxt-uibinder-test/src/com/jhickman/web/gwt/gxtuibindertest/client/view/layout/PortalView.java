/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Stock;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class PortalView extends Composite implements View {

	static interface Binder extends UiBinder<Component, PortalView> {}
	private static Binder BINDER = GWT.create(Binder.class);

	public PortalView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@UiFactory
	public ListStore<Stock> provideStore() {
		ListStore<Stock> store = new ListStore<Stock>();  
		store.add(ExamplesModel.getStocks());
		return store;
	}
}
