/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class AccordionLayoutView extends Composite implements View {

	static interface Binder extends UiBinder<Component, AccordionLayoutView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public AccordionLayoutView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@UiFactory
	public TreeStore<ModelData> provideTreeStore() {
		TreeStore<ModelData> store = new TreeStore<ModelData>();
		
		ModelData m = newItem("Family", null);  
		store.add(m, false);  
		
		store.add(m, newItem("Darrell", "user"), false);  
		store.add(m, newItem("Maro", "user-girl"), false);  
		store.add(m, newItem("Lia", "user-kid"), false);  
		store.add(m, newItem("Alec", "user-kid"), false);  
		store.add(m, newItem("Andrew", "user-kid"), false);  
		
		m = newItem("Friends", null);  
		store.add(m, false);  
		
		store.add(m, newItem("Bob", "user"), false);  
		store.add(m, newItem("Mary", "user-girl"), false);  
		store.add(m, newItem("Sally", "user-girl"), false);  
		store.add(m, newItem("Jack", "user"), false);
		
		return store;
	}
	
	@UiFactory
	public ModelIconProvider<ModelData> createModelIconProvider() {
		return new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				if (model.get("icon") != null) {
					return IconHelper.createStyle((String) model.get("icon"));
				}
				return null;	
			}
		};
	}

	private ModelData newItem(String text, String iconStyle) {  
		ModelData m = new BaseModelData();  
		m.set("name", text);  
		m.set("icon", iconStyle);  
		return m;  
	}   
}
