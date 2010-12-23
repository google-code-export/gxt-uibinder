/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.jhickman.web.gwt.gxtuibindertest.client.view.ButtonsView;

/**
 * @author hickman
 *
 */
public class ButtonsActivity extends AbstractActivity {
	
	private final ButtonsView view;

	@Inject
	public ButtonsActivity(ButtonsView view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
