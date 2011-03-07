/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.CardLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class CardLayoutView extends Composite implements View {

	static interface Binder extends UiBinder<Component, CardLayoutView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField ContentPanel panel;
	private final CardLayout layout;
	
	public CardLayoutView() {
		initComponent(BINDER.createAndBindUi(this));
		
		layout = (CardLayout) panel.getLayout();
		
		layout.setActiveItem(panel.getItem(0));
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="card1Button")
	public void card1ButtonClicked(ButtonEvent event) {
		layout.setActiveItem(panel.getItem(0));
	}

	@GxtUiHandler(eventType=GxtEvent.Select, uiField="card2Button")
	public void card2ButtonClicked(ButtonEvent event) {
		layout.setActiveItem(panel.getItem(1));
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="card3Button")
	public void card3ButtonClicked(ButtonEvent event) {
		layout.setActiveItem(panel.getItem(2));
	}

	@GxtUiHandler(eventType=GxtEvent.Select, uiField="card4Button")
	public void card4ButtonClicked(ButtonEvent event) {
		layout.setActiveItem(panel.getItem(3));
	}
}
