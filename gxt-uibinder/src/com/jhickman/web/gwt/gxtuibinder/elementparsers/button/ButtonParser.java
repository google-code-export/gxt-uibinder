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
package com.jhickman.web.gwt.gxtuibinder.elementparsers.button;

import java.util.Collection;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.ComponentParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * @author hickman
 *
 */
public class ButtonParser extends ComponentParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		// parse as component first.  FIXME figure out the parser order on class hierarchy. should the superclass ones should parse first?
		super.parse(elem, fieldName, type, writer);
		
		handleBackwardsCompatibleMenu(elem, fieldName, writer);
		
		JClassType menuType = writer.getOracle().findType(GxtClassnameConstants.MENU);
		boolean foundSingleMenu = false;
		for (XMLElement child : elem.consumeChildElements()) {
			if (foundSingleMenu) {
				writer.die(elem, "Buttons only support a single Menu.  Found more than one.");
			}
			
			JClassType childType = writer.findFieldType(child);
			if ( ! childType.isAssignableTo(menuType)) {
				writer.die(elem, "Buttons only support nested Menu Components.  Found %s.", child);
			}
			foundSingleMenu = true;
			
			String menu = writer.parseElementToField(child);
			writer.addStatement("%s.setMenu(%s);", fieldName, menu);
		}
	}

	@Deprecated
	private void handleBackwardsCompatibleMenu(XMLElement elem,	String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		Interpreter<Boolean> menuInterpreter = new SimpleInterpreter(elem.getNamespaceUri(), "menu");
		
		Collection<XMLElement> menuChildElements = elem.consumeChildElements(menuInterpreter);
		if (menuChildElements.isEmpty()) {
			return;
		}
		writer.warn(elem, "Use of <%s:menu> has been deprecated.  Nesting a Menu widget is the new preferred approach.", elem.getPrefix());
		if (menuChildElements.size() > 1) {
			writer.die(elem, "Buttons can contain only a single menu.");
		}

		String menu = writer.declareField(GxtClassnameConstants.MENU, elem);
		
		XMLElement menuNode = menuChildElements.iterator().next();
		for(XMLElement child : menuNode.consumeChildElements()) {
			String childField = writer.parseElementToField(child);
			writer.addStatement("%s.add(%s);", menu, childField);
		}
		

		writer.addStatement("%s.setMenu(%s);", fieldName, menu);
	}
}
