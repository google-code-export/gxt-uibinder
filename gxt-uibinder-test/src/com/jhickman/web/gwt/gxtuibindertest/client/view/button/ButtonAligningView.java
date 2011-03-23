/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.button;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class ButtonAligningView extends Composite implements View {

	static interface Binder extends UiBinder<Component, ButtonAligningView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public ButtonAligningView() {
		initComponent(BINDER.createAndBindUi(this));
	}

	
	@GxtUiHandler(uiField={"button1","button2","button3","button4","button5","button6","button7","button8","button9"},
			eventType=GxtEvent.Select) 
	public void onButtonClicked(ButtonEvent ce){
		Info.display("Click", ce.getButton().getText() + " clicked");
	}
}
