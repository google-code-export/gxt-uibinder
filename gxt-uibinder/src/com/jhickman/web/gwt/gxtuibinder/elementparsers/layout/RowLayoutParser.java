/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.ElementParserUtil;

/**
 * @author hickman
 *
 */
public class RowLayoutParser extends GenericLayoutParser {

	public RowLayoutParser(String layoutClassName) {
		super(layoutClassName);
	}
	
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {

		String layout = writer.declareField(layoutClassName, elem);
		writer.addStatement("%s.setLayout(%s);", fieldName, layout);
		
		JClassType orientationType = writer.getOracle().findType(GxtClassnameConstants.STYLEORIENTATION);
		
		String layoutOrientation = elem.consumeAttribute("rowLayoutOrientation", orientationType);
		if (layoutOrientation != null) {
			writer.warn("rowLayoutOrientation has been deprecated. Please use layoutOrientation instead.");
			writer.addStatement("%s.setOrientation(%s);", layout, layoutOrientation);
		}
		
		// override with layoutOrientation
		layoutOrientation = elem.consumeAttribute("layoutOrientation", orientationType);
		if (layoutOrientation != null) {
			writer.addStatement("%s.setOrientation(%s);", layout, layoutOrientation);
		}
		
		handleLayoutDataChildren(elem, fieldName, writer);
	}

}
