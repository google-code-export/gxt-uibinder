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
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 *
 */
public class CheckBoxGroupParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		for (XMLElement child : elem.consumeChildElements()) {
			if ( ! isValidElement(elem, child)) {
				writer.die(elem, "CheckBoxGroup can only contain CheckBox children, but found '%s'.", child);
			}
			
			String childFieldName = writer.parseElementToField(child);
			
			writer.addStatement("%s.add(%s);", fieldName, childFieldName);
		}
		
	}

	private boolean isValidElement(XMLElement parent, XMLElement child) {
		if ( ! parent.getNamespaceUri().equals(child.getNamespaceUri())) {
			return false;
		}
		
		return "CheckBox".equals(child.getLocalName());
	}
}
