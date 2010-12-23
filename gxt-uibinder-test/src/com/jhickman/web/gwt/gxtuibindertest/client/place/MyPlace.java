/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

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
		overview,
		absolutelayout,
		buttons
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
