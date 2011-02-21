/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.form;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 *
 */
public class SliderFieldParser implements ElementParser {
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		boolean seen = false;
		for (XMLElement child : elem.consumeChildElements()) {
			if (seen) {
				writer.die("SliderField can contain exactly one child element of type Slider.");
			}
			
			// single child must be of type "Slider"
			String childField = writer.parseElementToField(child);
			writer.setFieldInitializerAsConstructor(fieldName, type, childField);
			
			seen = true;
		}
	}
}
