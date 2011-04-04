/**
 * Copyright 2010 Justin Hickman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.form;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.elementparsers.TextInterpreter;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 *
 */
public class SimpleComboBoxParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		String parameterizedType = elem.consumeRawAttribute("type", "java.lang.String");
		JClassType valueType = writer.getOracle().findType(parameterizedType);
		if (valueType == null) {
			writer.die(elem, "Found type attribute, but unable to resolve the value: %s", parameterizedType);
		}
		
		for(XMLElement child : elem.consumeChildElements()) {
			if ( ! child.getNamespaceUri().equals(elem.getNamespaceUri())) {
				writer.die(elem, "Children of SimpleComboBox must be in the same namespace.  Expected '%s' but found '%s'", elem.getPrefix(), child.getPrefix());
			}
				
			String data = parseChildElement(child, valueType, writer);
			
			writer.addStatement("%s.add(%s);", fieldName, data);
		}
		
		if (elem.getAttribute("simpleValue") != null) {
			writer.addStatement("%s.setSimpleValue(%s);", fieldName, elem.consumeAttribute("simpleValue", valueType));
		}
	}
	
	
	private String parseChildElement(XMLElement elem, JClassType valueType, UiBinderWriter writer) throws UnableToCompleteException {
		if ("value".equals(elem.getLocalName())) {
			return String.format("\"%s\"", elem.consumeInnerTextEscapedAsHtmlStringLiteral(new TextInterpreter(writer)));
		} else if ("item".equals(elem.getLocalName())) {
			return elem.consumeRequiredAttribute("value", valueType);
		}
		
		writer.die(elem, "Unknown child element of SimpleComboBox");
		
		return null; // will never get here
	}
}
