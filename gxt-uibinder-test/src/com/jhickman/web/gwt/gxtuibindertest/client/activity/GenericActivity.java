/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace.Token;
import com.jhickman.web.gwt.gxtuibindertest.client.view.DemoView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.SourceView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class GenericActivity extends AbstractActivity {

	private final View view;
	private final MyPlace place;
	
	/**
	 * @param place
	 */
	public GenericActivity(MyPlace place) {
		this.place = place;
		
		Token placeToken = place.getToken();
		View demoView = placeToken.getView();
		
		if (Token.overview.equals(placeToken)) {
			this.view = placeToken.getView();
		} else {
			this.view = new DemoView(
					demoView,
					new SourceView(demoView.getClass()));
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
