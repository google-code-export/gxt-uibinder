/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.CheckChangedEvent;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Format;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckCascade;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.CheckNodes;
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
public class CheckboxTreeView extends Composite implements View {

	static interface Binder extends UiBinder<Component, CheckboxTreeView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField TreePanel<ModelData> tree;
	@UiField SimpleComboBox<String> cascade;
	@UiField SimpleComboBox<String> checkNodes;
	
	public CheckboxTreeView() {
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
	
	
	// overall check state changes
	@GxtUiHandler(eventType=GxtEvent.CheckChanged, uiField="tree")
	public void treeCheckChanged(CheckChangedEvent<ModelData> event) {
		
	}
	
	// change in node check state
	@GxtUiHandler(eventType=GxtEvent.CheckChange, uiField="tree")
	public void treePanelEventHandler(TreePanelEvent<ModelData> be) {

	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="getCheckedButton")
	public void getCheckedButtonClicked(ButtonEvent event) {
		StringBuilder sb = new StringBuilder();
		for(ModelData item : tree.getCheckedSelection()) {
			sb.append(", ").append(item.get("name"));
		}
		String s = sb.toString();
		if (s.length() > 1) s = s.substring(2);
		
		Info.display("Checked Items", Format.ellipse(s, 100), "");
	}
	
	@GxtUiHandler(eventType=GxtEvent.Change, uiField="cascade")
	public void onCascadeChange(FieldEvent be) {
		String val = cascade.getSimpleValue();
		if ("Parent".equals(val)) {
			tree.setCheckStyle(CheckCascade.PARENTS);
		} else if ("Children".equals(val)) {
			tree.setCheckStyle(CheckCascade.CHILDREN);
		} else {
			tree.setCheckStyle(CheckCascade.NONE);
		}
	}
	
	@GxtUiHandler(eventType=GxtEvent.Change, uiField="checkNodes")
	public void onCheckNodesChange(FieldEvent be) {
        String val = checkNodes.getSimpleValue();  
        if ("Parent".equals(val)) {  
          tree.setCheckNodes(CheckNodes.PARENT);  
        } else if ("Leaf".equals(val)) {  
          tree.setCheckNodes(CheckNodes.LEAF);  
        } else {  
          tree.setCheckNodes(CheckNodes.BOTH);  
        }  
	}
}
