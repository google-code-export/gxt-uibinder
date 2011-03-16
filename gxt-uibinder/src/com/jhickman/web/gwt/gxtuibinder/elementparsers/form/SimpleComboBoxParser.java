/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.form;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.elementparsers.TextInterpreter;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 *
 */
public class SimpleComboBoxParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		for(XMLElement child : elem.consumeChildElements()) {
			
			if ( ! ("value".equals(child.getLocalName()) &&	elem.getNamespaceUri().equals(child.getNamespaceUri()))) {
				writer.die(elem, "SimpleComboBox currently on supports nested <form:value> children.  Found '%s'.", child);
			}
			//child.consume
			String data = child.consumeInnerTextEscapedAsHtmlStringLiteral(new TextInterpreter(writer));
			writer.addStatement("%s.add(\"%s\");", fieldName, data);
		}
		
		
		XMLAttribute simpleValue = elem.getAttribute("simpleValue");
		if (simpleValue != null) {
			writer.addStatement("%s.setSimpleValue(%s);", fieldName, simpleValue.consumeStringValue());
		}
	}
}
