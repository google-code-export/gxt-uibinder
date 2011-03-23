/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.misc;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Slider;
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
public class SliderView extends Composite implements View {

	static interface Binder extends UiBinder<Component, SliderView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField Slider topSlider;
	@UiField Slider bottomSlider;
	
	public SliderView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@GxtUiHandler(uiField="topSliderButton", eventType=GxtEvent.Select) 
	public void onTopSliderButtonClicked(ButtonEvent event) {
		topSlider.setValue(40);
	}
	
	@GxtUiHandler(uiField="bottomSliderButton", eventType=GxtEvent.Select) 
	public void onBottomSliderButtonClicked(ButtonEvent event) {
		bottomSlider.setValue(40);
	}
}
