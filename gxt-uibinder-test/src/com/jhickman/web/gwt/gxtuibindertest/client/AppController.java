/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlaceHistoryMapper;
import com.jhickman.web.gwt.gxtuibindertest.client.view.impl.GxtUiBinderTestShell;

/**
 * @author hickman
 *
 */
public class AppController {
	private final GxtUiBinderTestShell shell;
	
	
	@Inject
	public AppController(
			EventBus eventBus,
			GxtUiBinderTestShell shell,
			ActivityManager activityManager,
			MyPlaceHistoryMapper historyMapper,
			PlaceHistoryHandler historyHandler
			) {
		this.shell = shell;
		
		activityManager.setDisplay(shell.getDisplay());
		
		historyHandler.handleCurrentHistory();
	}

	public void go() {
		RootPanel.get().add(shell);
		shell.show();
	}

}
