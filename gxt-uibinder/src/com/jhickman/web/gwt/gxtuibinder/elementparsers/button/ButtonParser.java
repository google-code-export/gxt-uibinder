/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.button;

import java.util.Collection;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * @author hickman
 *
 */
public class ButtonParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
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
