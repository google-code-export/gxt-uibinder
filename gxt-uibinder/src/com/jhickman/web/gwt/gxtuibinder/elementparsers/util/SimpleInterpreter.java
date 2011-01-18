/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.util;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;

/**
 * @author hickman
 *
 */
public class SimpleInterpreter implements Interpreter<Boolean> {

	private final String namespaceUri;
	private final String localName;

	public SimpleInterpreter(String namespaceUri, String localName) {
		this.namespaceUri = namespaceUri;
		this.localName = localName;
	}
	
	@Override
	public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
		return (namespaceUri != null && namespaceUri.equals(elem.getNamespaceUri())) 
				&& (localName != null && localName.equals(elem.getLocalName()));
	}
}
