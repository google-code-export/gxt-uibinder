/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.jhickman.web.gwt.gxtuibindertest.client.view.OverviewViewImpl;
import com.jhickman.web.gwt.gxtuibindertest.client.view.button.ButtonsView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.layout.AbsoluteLayoutView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.layout.BorderLayoutView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.misc.DatePickerView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.misc.ToolTipsView;

/**
 * @author hickman
 *
 */
public class MyPlace extends Place {
	private Token token;
	
	public MyPlace(Token token) {
		this.token = token;
	}
	
	public Token getToken() {
		return token;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if ( ! (obj instanceof MyPlace)) return false;
		
		MyPlace that = (MyPlace) obj;
		
		return this.token.equals(that.token);
	}
	
	
	public static enum Token {
		overview(OverviewViewImpl.class),
		absolutelayout(AbsoluteLayoutView.class),
		borderlayout(BorderLayoutView.class),
		buttons(ButtonsView.class), 
		tooltips(ToolTipsView.class), 
		datepicker(DatePickerView.class),
		;

		private final Class<?> viewClass;
		private Token(Class<?> viewClass) {
			this.viewClass = viewClass;
		}
		
		public Class<?> getViewClass() {
			return viewClass;
		}
	}

	
	public static class Tokenizer implements PlaceTokenizer<MyPlace> {
		@Override
		public MyPlace getPlace(String token) {
			token = token.toLowerCase();
			return new MyPlace(Token.valueOf(token));
		}
		@Override
		public String getToken(MyPlace place) {
			return place.getToken().name();
		}
	}
}
