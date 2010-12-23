package com.jhickman.web.gwt.gxtuibindertest.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers(MyPlace.Tokenizer.class)
public interface MyPlaceHistoryMapper extends PlaceHistoryMapper {
}
