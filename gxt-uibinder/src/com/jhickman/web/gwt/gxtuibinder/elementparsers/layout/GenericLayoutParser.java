/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import java.util.Collection;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;

/**
 * @author hickman
 *
 */
public class GenericLayoutParser implements LayoutParser {
	
	protected final String layoutClassName;

	public GenericLayoutParser(String layoutClassName) {
		this.layoutClassName = layoutClassName;
	}
	
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		writer.addStatement("%s.setLayout(new %s());", fieldName, layoutClassName);
	}
	
	protected Collection<XMLElement> consumeChildLayoutData(XMLElement elem) throws UnableToCompleteException {
		Interpreter<Boolean> interpreter = new LayoutDataInterpreter(elem.getNamespaceUri());
		return elem.consumeChildElements(interpreter);
	}
	
	private static final class LayoutDataInterpreter implements Interpreter<Boolean> {
		private final String namespaceUri;
		public LayoutDataInterpreter(String namespaceUri) {
			this.namespaceUri = namespaceUri;
		}
		@Override
		public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
			return namespaceUri.equals(elem.getNamespaceUri())
				&& "layoutdata".equals(elem.getLocalName());
		}
	}
}
