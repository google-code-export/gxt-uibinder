/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
 *
 */
@Deprecated
public class RowLayoutParser extends GenericLayoutParser {

	public RowLayoutParser(String layoutClassName) {
		super(layoutClassName);
	}
	
	@Override
	public void parse(XMLElement layoutElem, XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		String layout = createAndSetLayout(layoutElem, elem, fieldName, writer);
		
		JClassType orientationType = writer.getOracle().findType(GxtClassnameConstants.STYLEORIENTATION);
		
		String layoutOrientation = elem.consumeAttribute("rowLayoutOrientation", orientationType);
		if (layoutOrientation != null) {
			writer.warn(elem, "rowLayoutOrientation has been deprecated. Please use nested <%s:layout orientation='%s'> instead.", elem.getPrefix(), layoutOrientation);
			writer.addStatement("%s.setOrientation(%s);", layout, layoutOrientation);
		}
		
		// override with layoutOrientation
		layoutOrientation = elem.consumeAttribute("layoutOrientation", orientationType);
		if (layoutOrientation != null) {
			writer.warn(elem, "layoutOrientation has been deprecated. Please use nested <%s:layout orientation='%s'> instead.", elem.getPrefix(), layoutOrientation);
			writer.addStatement("%s.setOrientation(%s);", layout, layoutOrientation);
		}
		
		handleChildren(elem, fieldName, writer);
	}

}
