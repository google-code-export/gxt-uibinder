/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.grids;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.CellSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Stock;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class BasicGridView extends Composite implements View {

	static interface Binder extends UiBinder<Component, BasicGridView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField SimpleComboBox<String> selectionModeComboBox;
	@UiField Grid<Stock> grid;
	
	private static final NumberFormat CURRENCY_NUMBERFORMAT = NumberFormat.getCurrencyFormat();  
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getFormat("0.00");  
    private static final NumberCellRenderer<Grid<Stock>> NUMBER_RENDERER = new NumberCellRenderer<Grid<Stock>>(CURRENCY_NUMBERFORMAT);
	
	public BasicGridView() {
		initComponent(BINDER.createAndBindUi(this));
		
		selectionModeComboBox.add("Row");
		selectionModeComboBox.add("Cell");
		selectionModeComboBox.setSimpleValue("Row");
	}
	
	@UiFactory
	public ListStore<Stock> provideStore() {
		ListStore<Stock> store = new ListStore<Stock>();  
	    store.add(ExamplesModel.getStocks());
	    return store;
	}
	
	@UiFactory
	public DateTimeFormat provideDateFormat() {
		return DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
	}
	
	@GxtUiHandler(uiField="selectionModeComboBox", eventType=GxtEvent.Change)
	public void onSelectionModeValueChange(FieldEvent event) {
		boolean cell = selectionModeComboBox.getSimpleValue().equals("Cell");
		grid.getSelectionModel().deselectAll();
		if (cell) {
			grid.setSelectionModel(new CellSelectionModel<Stock>());  
		} else {
			grid.setSelectionModel(new GridSelectionModel<Stock>());  
		}
	}
	
	
	public static final class ChangeCellRenderer implements GridCellRenderer<Stock> {  
		public String render(Stock model, String property, ColumnData config, int rowIndex, int colIndex,  
				ListStore<Stock> store, Grid<Stock> grid) {  
			double val = (Double) model.get(property);  
			String style = val < 0 ? "red" : GXT.isHighContrastMode ? "#00ff5a" : "green";  
			String v = NUMBER_FORMAT.format(val);  
			
			return "<span qtitle='" + grid.getColumnModel().getColumnById(property).getHeader() + "' qtip='" + v  
			+ "' style='font-weight: bold;color:" + style + "'>" + v + "</span>";  
		}  
	}
	
	public static final class GridNumberRenderer implements GridCellRenderer<Stock> {  
		public String render(Stock model, String property, ColumnData config, int rowIndex, int colIndex,  
				ListStore<Stock> store, Grid<Stock> grid) {  
			return NUMBER_RENDERER.render(null, property, model.get(property));  
		}  
	}  
}
