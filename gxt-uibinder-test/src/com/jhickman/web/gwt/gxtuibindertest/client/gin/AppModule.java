/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.gin;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.jhickman.web.gwt.gxtuibindertest.client.activity.ButtonsActivity;
import com.jhickman.web.gwt.gxtuibindertest.client.activity.GenericActivity;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlaceHistoryMapper;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace.Token;
import com.jhickman.web.gwt.gxtuibindertest.client.view.impl.ButtonsViewImpl;

/**
 * @author hickman
 *
 */
public class AppModule extends AbstractGinModule {
	
	private static final MyPlace DEFAULT_PLACE = new MyPlace(Token.overview);

	@Override
	protected void configure() {
	}
	
	@Provides @Singleton
	public EventBus provideEventBus() {
		return new SimpleEventBus();
	}
	
	@Provides @Singleton
	public ActivityManager provideActivityManager(EventBus eventBus, final AppGinjector ginjector) {
		ActivityMapper activityMapper = new ActivityMapper() {
			public Activity getActivity(Place place) {
				if (place instanceof MyPlace) {
					Token token = ((MyPlace) place).getToken();
					switch (token) {
						case overview:
							return new GenericActivity(ginjector.getOverviewView());
						case buttons:
							return new ButtonsActivity(new ButtonsViewImpl());
					}
				}
				return null;
			}
		};
		
		return new ActivityManager(activityMapper, eventBus);
	}
	
	@Provides @Singleton
	public PlaceController providePlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}
	
	@Provides @Singleton
	public PlaceHistoryHandler providePlaceHistoryHandler(
			EventBus eventBus,
			PlaceController placeController,
			MyPlaceHistoryMapper mapper) {
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(mapper);
		historyHandler.register(placeController, eventBus, DEFAULT_PLACE);
		
		return historyHandler;
	}

}
