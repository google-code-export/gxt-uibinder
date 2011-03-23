/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.factory;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.jhickman.web.gwt.gxtuibindertest.client.AppController;
import com.jhickman.web.gwt.gxtuibindertest.client.activity.GenericActivity;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace.Token;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlaceHistoryMapper;
import com.jhickman.web.gwt.gxtuibindertest.client.view.GxtUiBinderTestShell;

/**
 * @author hickman
 *
 */
public final class AppFactory {
	
	private static final MyPlace DEFAULT_PLACE = new MyPlace(Token.overview);
	public static final AppFactory INSTANCE = new AppFactory();
	private AppController appController;
	private EventBus eventBus;
	private ActivityManager activityManager;
	private PlaceController placeController;
	private PlaceHistoryHandler placeHistoryHandler;
	
	private AppFactory() {}
	

	public AppController getAppController() {
		if (appController == null) {
			appController = new AppController(getEventBus(),
					getShell(), 
					getActivityManager(), 
					getHistoryMapper(),
					getPlaceHistoryHandler());
		}
		return appController;
	}
	
	/**
	 * @return
	 */
	private MyPlaceHistoryMapper getHistoryMapper() {
		return GWT.create(MyPlaceHistoryMapper.class);
	}

	/**
	 * @return
	 */
	private GxtUiBinderTestShell getShell() {
		return new GxtUiBinderTestShell(getEventBus(), getPlaceController());
	}

	public EventBus getEventBus() {
		if (eventBus == null) {
			eventBus = new SimpleEventBus();
		}
		return eventBus;
	}
	
	public ActivityManager getActivityManager() {
		if (activityManager == null) {
			ActivityMapper activityMapper = new ActivityMapper() {
				public Activity getActivity(Place place) {
					if (place instanceof MyPlace) {
						return new GenericActivity((MyPlace) place);
					}
					return null;
				}
			};
			activityManager = new ActivityManager(activityMapper, getEventBus());
		}
		return activityManager;
	}
	
	public PlaceController getPlaceController() {
		if (placeController == null) {
			placeController = new PlaceController(getEventBus());
		}
		return placeController;
	}
	
	public PlaceHistoryHandler getPlaceHistoryHandler() {
		if (placeHistoryHandler == null) {
			placeHistoryHandler = new PlaceHistoryHandler(getHistoryMapper());
			placeHistoryHandler.register(getPlaceController(), getEventBus(), DEFAULT_PLACE);
		}
		
		return placeHistoryHandler;
	}
	
	
}
