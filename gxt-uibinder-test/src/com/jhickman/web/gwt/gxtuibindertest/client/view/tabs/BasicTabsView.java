/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.tabs;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class BasicTabsView extends Composite implements View {

	static interface Binder extends UiBinder<Component, BasicTabsView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public BasicTabsView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@GxtUiHandler(uiField="eventTab", eventType=GxtEvent.Select)
	public void handleEvent(ComponentEvent be) {
		Window.alert("Event Tab was Selected");
	}

}
