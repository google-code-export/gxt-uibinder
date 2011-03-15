/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Folder;
import com.jhickman.web.gwt.gxtuibindertest.client.resources.Resources;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class BasicTreeView extends Composite implements View {

	static interface Binder extends UiBinder<Component, BasicTreeView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField TreePanel<ModelData> tree;
	
	public BasicTreeView() {
		initComponent(BINDER.createAndBindUi(this));
		
		// FIXME.  need a better way to do this
		tree.getStyle().setLeafIcon(Resources.ICONS.music());
	}
	
	@UiFactory
	public TreeStore<ModelData> provideStore() {
		Folder model = ExamplesModel.getTreeModel();
		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);
		return store;
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="expand")
	public void expandButtonClicked(ButtonEvent event) {
		tree.expandAll();
	}

	@GxtUiHandler(eventType=GxtEvent.Select, uiField="collapse")
	public void collapseButtonClicked(ButtonEvent event) {
		tree.collapseAll();
	}
}
