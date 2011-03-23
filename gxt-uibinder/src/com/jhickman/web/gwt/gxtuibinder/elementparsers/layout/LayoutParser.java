/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 */
public interface LayoutParser extends ElementParser {

	/**
	 * @param layoutElem
	 * @param elem
	 * @param layoutContainerFieldName
	 * @param type
	 * @param writer
	 * @throws UnableToCompleteException
	 */
	void parse(XMLElement layoutElem, XMLElement elem,
			String layoutContainerFieldName, JClassType type,
			UiBinderWriter writer) throws UnableToCompleteException;

}
