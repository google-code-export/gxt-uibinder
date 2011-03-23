/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.forms;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Stock;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class FormsView extends Composite implements View {

	static interface Binder extends UiBinder<Component, FormsView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField SpinnerField durationSpinner;
	
	public FormsView() {
		initComponent(BINDER.createAndBindUi(this));
		
		durationSpinner.getPropertyEditor().setType(Double.class);
		durationSpinner.getPropertyEditor().setFormat(NumberFormat.getFormat("00.0"));
	}
	
	@UiFactory
	public ListStore<Stock> provideCompanyStore() {
		List<Stock> stocks = ExamplesModel.getStocks();  
		Collections.sort(stocks, new Comparator<Stock>() {  
			public int compare(Stock arg0, Stock arg1) {  
				return arg0.getName().compareTo(arg1.getName());  
			}  
		});  
		
		ListStore<Stock> store = new ListStore<Stock>();  
		store.add(stocks);  
		return store;
	}
}
