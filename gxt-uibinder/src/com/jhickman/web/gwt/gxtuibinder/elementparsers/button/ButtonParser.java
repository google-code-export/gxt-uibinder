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

/**
 * @author hickman
 *
 */
public class ButtonParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		Interpreter<Boolean> menuInterpreter = new MenuInterpreter(elem.getNamespaceUri());
		
		Collection<XMLElement> menuChildElements = elem.consumeChildElements(menuInterpreter);
		if (menuChildElements.isEmpty()) {
			return;
		}
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
	
	private static final class MenuInterpreter implements Interpreter<Boolean> {
		private final String namespaceUri;
		private static final String MENU_LOCALNAME = "menu";
		
		public MenuInterpreter(String namespaceUri) {
			this.namespaceUri = namespaceUri;
		}

		@Override
		public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
			if (namespaceUri != null && namespaceUri.equals(elem.getNamespaceUri())) {
				return MENU_LOCALNAME.equals(elem.getLocalName());
			}
			return false;
		}
	}
}
