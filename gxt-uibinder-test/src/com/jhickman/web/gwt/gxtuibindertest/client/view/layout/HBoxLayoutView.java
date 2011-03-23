/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
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
public class HBoxLayoutView extends Composite implements View {

	static interface Binder extends UiBinder<Component, HBoxLayoutView> {}
	private static Binder BINDER = GWT.create(Binder.class);

	@UiField ContentPanel buttonBox;
	@UiField ContentPanel panel;
	private final CardLayout layout;
	
	public HBoxLayoutView() {
		initComponent(BINDER.createAndBindUi(this));
		
		layout = (CardLayout) panel.getLayout();
		layout.setActiveItem(panel.getItem(0));
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField={"spaced",	"multiSpaced", "alignTop", "alignMiddle",
			"alignBottom","alignStretch","alignStretchmax",	"flexAllEven","flexRatio","flexStretch","packStart",
			"packCenter", "packEnd"	})
	public void layoutButtonClicked(ButtonEvent event) {
		ToggleButton button = event.<ToggleButton> getComponent();
		
		if ( ! button.isPressed()) {  
			return;  
		}

		int index = buttonBox.indexOf(button);
		layout.setActiveItem(panel.getItem(index));
	}
}
