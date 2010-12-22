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
public class RadioGroupParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		for (XMLElement child : elem.consumeChildElements()) {
			if ( ! isValidElement(elem, child)) {
				writer.die(elem, "RadioGroup can only contain Radio children, but found '%s'.", child);
			}
			
			String childFieldName = writer.parseElementToField(child);
			
			writer.addStatement("%s.add(%s);", fieldName, childFieldName);
		}
		
	}

	private boolean isValidElement(XMLElement parent, XMLElement child) {
		if ( ! parent.getNamespaceUri().equals(child.getNamespaceUri())) {
			return false;
		}
		
		return "Radio".equals(child.getLocalName());
	}
}
