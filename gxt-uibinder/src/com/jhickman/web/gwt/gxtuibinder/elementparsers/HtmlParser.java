/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.elementparsers.HtmlInterpreter;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * Parser for the Html widget.  Children of element will be passed to constructor
 * @author hickman
 */
public class HtmlParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		HtmlInterpreter interpreter =
			HtmlInterpreter.newInterpreterForUiObject(writer, fieldName);

		String html = elem.consumeInnerHtml(interpreter);
		
		if (html.trim().length() > 0) {
			writer.genStringPropertySet(fieldName, "Html", html);
		}
	}
}
