/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.button;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 */
public class ButtonGroupParser implements ElementParser {
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		String columns = elem.consumeRequiredIntAttribute("columns");
		writer.setFieldInitializerAsConstructor(fieldName, type, columns);
	}
}
