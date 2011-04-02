/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

public class LayoutParserFactory {

	public static LayoutParser findLayoutParserBySimpleName(XMLElement elem, String layoutSimpleName, UiBinderWriter writer) throws UnableToCompleteException {
		String layoutClassName = GxtClassnameConstants.LAYOUT_BASE_PACKAGE + layoutSimpleName;
		return findLayoutParser(elem, layoutClassName, writer);
	}

	public static LayoutParser findLayoutParser(XMLElement elem, String layoutClassName, UiBinderWriter writer) throws UnableToCompleteException {
    	if (null == writer.getOracle().findType(layoutClassName)) {
    		// type not found.  die
    		writer.die(elem, "Unable to find Layout type: %s", layoutClassName);
    	}
 
    	
    	// ones with custom parsers
    	if (GxtClassnameConstants.BORDERLAYOUT.equals(layoutClassName)) {
    		return new BorderLayoutParser();
    	}
    	
    	if (       GxtClassnameConstants.ROWLAYOUT.equals(layoutClassName)
    			|| GxtClassnameConstants.FILLLAYOUT.equals(layoutClassName)) {
    		return new RowLayoutParser(layoutClassName);
    	}
    	
    	return new GenericLayoutParser(layoutClassName);
    }
}