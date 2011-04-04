/**
 * Copyright 2011 hickman
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
package com.jhickman.web.gwt.gxtuibinder.elementparsers.custom;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * @author hickman
 *
 */
public class PortalParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		JClassType integerType = writer.getOracle().findType("java.lang.Integer");
		
		
		JClassType doubleType = writer.getOracle().findType("java.lang.Double");
		
		String columnCountAttribute = elem.consumeRequiredAttribute("numColumns", integerType);
		Integer columnCount = Integer.valueOf(columnCountAttribute);
		
		Interpreter<Boolean> interpreter = new SimpleInterpreter(elem.getNamespaceUri(), "column");
		int columnIndex = 0;
		for(XMLElement column : elem.consumeChildElements(interpreter)) {
			String width = column.consumeAttribute("width", doubleType);
			if (width != null) {
				writer.addStatement("%s.setColumnWidth(%d, %s);", fieldName, columnIndex, width);
			}
			
			for(XMLElement columnChild : column.consumeChildElements()) {
				String childField = writer.parseElementToField(columnChild);
				writer.addStatement("%s.add(%s, %d);", fieldName, childField, columnIndex);
			}
			
			
			columnIndex++;
		}
		
		if (columnIndex > columnCount) {
			writer.die(elem, "numColumns set to %s, but found %s", columnCountAttribute, columnIndex);
		}
		
		writer.setFieldInitializerAsConstructor(fieldName, type, "" + columnIndex);
	}

}
