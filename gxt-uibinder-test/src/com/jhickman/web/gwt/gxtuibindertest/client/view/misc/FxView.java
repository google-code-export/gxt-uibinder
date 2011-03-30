/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.misc;

import com.extjs.gxt.ui.client.Style.Direction;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.fx.FxConfig;
import com.extjs.gxt.ui.client.util.Rectangle;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.jhickman.web.gwt.gxtuibinder.client.GxtEvent;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class FxView extends Composite implements View {

	static interface Binder extends UiBinder<Component, FxView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	@UiField ContentPanel cp;
	
	public FxView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	@UiFactory
	public String provideShortText() {
		return ExamplesModel.DUMMY_TEXT_SHORT;
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="slideInOut")
	public void onSlideInOutClicked(ButtonEvent ce) {
		if (cp.isVisible()) {
			cp.el().slideOut(Direction.UP, FxConfig.NONE);
		} else {
			cp.el().slideIn(Direction.DOWN, FxConfig.NONE);
		}
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="fadeInOut")
	public void onFadeInOutClicked(ButtonEvent ce) {
		cp.el().fadeToggle(FxConfig.NONE);
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="move")
	public void onMoveClicked(ButtonEvent ce) {
		Rectangle rect = cp.el().getBounds();
		cp.el().setXY(rect.x + 50, rect.y + 50, FxConfig.NONE);
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="blink")
	public void onBlinkClicked(ButtonEvent ce) {
		cp.el().blink(FxConfig.NONE);
	}
	
	@GxtUiHandler(eventType=GxtEvent.Select, uiField="reset")
	public void onResetClicked(ButtonEvent ce) {
		cp.setPosition(10, 10);
	}
}
