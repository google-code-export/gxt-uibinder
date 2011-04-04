/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.menu;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 *
 */
public class AdapterMenuItemParser implements ElementParser {
	
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		XMLElement childElement = elem.consumeSingleChildElement();
		if ( ! writer.isWidgetElement(childElement) ) {
			writer.die(elem, "Child element must be a widget type. Found: %s", childElement);
		}
		
		String widget = writer.parseElementToField(childElement);
		writer.setFieldInitializerAsConstructor(fieldName, type, widget);
	}
	
}
