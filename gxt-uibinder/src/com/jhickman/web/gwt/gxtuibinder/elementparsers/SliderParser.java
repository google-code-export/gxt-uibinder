/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 */
public class SliderParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		// need to grab the "value" attribute as it's ambiguous
		XMLAttribute attribute = elem.getAttribute("value");
		if (attribute != null) {
			String value = attribute.consumeRawValue();
			writer.addStatement("%s.setValue(%s);", fieldName, value);
		}
	}

}
