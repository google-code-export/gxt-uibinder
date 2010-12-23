/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class GenericActivity extends AbstractActivity {

	private final View view;

	public GenericActivity(View view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
