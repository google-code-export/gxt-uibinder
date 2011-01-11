/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.misc;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class DatePickerView extends Composite implements View {

	static interface Binder extends UiBinder<Component, DatePickerView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField DatePicker datePicker;
	
	public DatePickerView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@GxtUiHandler(uiField="datePicker", eventType=GxtEvent.Select)
	public void handleEvent(ComponentEvent be) {
		String d = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(datePicker.getValue());
		Info.display("Date Selected", "You selected {0}.", new Params(d));
	}
}
