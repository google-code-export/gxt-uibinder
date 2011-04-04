/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.toolbarmenu;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class BasicToolbarView extends Composite implements View {

	static interface Binder extends UiBinder<Component, BasicToolbarView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField Menu scrollingMenu;
	
	public BasicToolbarView() {
		initComponent(BINDER.createAndBindUi(this));
		
		for (int i = 0; i < 40; i++) {
			scrollingMenu.add(new MenuItem("Item " + i));
		}
	}
	
	@UiFactory
	public ListStore<ModelData> provideStore() {
		ListStore<ModelData> store = new ListStore<ModelData>();
		store.add(ExamplesModel.getStocks());
		return store;
	}

}
