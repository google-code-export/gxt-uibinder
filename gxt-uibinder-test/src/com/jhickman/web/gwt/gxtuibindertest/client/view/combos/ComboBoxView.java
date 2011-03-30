/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.combos;

import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Country;
import com.jhickman.web.gwt.gxtuibindertest.client.model.State;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */

public class ComboBoxView extends Composite implements View {

	static interface Binder extends UiBinder<Component, ComboBoxView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public ComboBoxView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@UiFactory
	ViewDataProvider getData() {
		return new ViewDataProvider() {
			public ListStore<?> stateStore() {
				ListStore<State> stateStore = new ListStore<State>();
				stateStore.add(ExamplesModel.getStates());
				return stateStore;
			}
			
			public ListStore<Country> countryStore() {
				ListStore<Country> countryStore = new ListStore<Country>();
				countryStore.add(ExamplesModel.getCountries());
				return countryStore;
			}

			public XTemplate template() {
				return XTemplate.create(getTemplate());
			}

			public XTemplate flagTemplate() {
				return XTemplate.create(getFlagTemplate(GWT.getHostPageBaseURL()));
			}
			
			private native String getTemplate() /*-{ 
		    	return  [ 
		    		'<tpl for=".">', 
		    		'<div class="x-combo-list-item" qtip="{slogan}" qtitle="State Slogan">{name}</div>', 
		    		'</tpl>' 
		    	].join(""); 
		  	}-*/;  
		  
			private native String getFlagTemplate(String base) /*-{ 
		    	return  [ 
		    		'<tpl for=".">', 
		    		'<div class="x-combo-list-item"><img width="16px" height="11px" src="' + base + 'samples/images/icons/fam/flags/{[values.abbr]}.png"> {[values.name]}</div>', 
		    		'</tpl>' 
		    	].join(""); 
		  	}-*/;  
		};
	}
	
	@SuppressWarnings("rawtypes")
	public static interface ViewDataProvider {
		ListStore stateStore();
		ListStore countryStore();
		XTemplate template();
		XTemplate flagTemplate();
	}
}
