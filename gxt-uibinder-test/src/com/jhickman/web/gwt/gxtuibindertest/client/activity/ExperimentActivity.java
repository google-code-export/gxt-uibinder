/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;
import com.jhickman.web.gwt.gxtuibindertest.client.view.work.ExperimentView;

/**
 * @author hickman
 *
 */
public class ExperimentActivity extends AbstractActivity {
	
	private final ExperimentView view;

	public ExperimentActivity(MyPlace place) {
		View theView = place.getToken().getView();
		this.view = (ExperimentView) theView;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}
}
