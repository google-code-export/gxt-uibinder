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
public class AbsoluteLayoutParser extends GenericLayoutParser {

	public AbsoluteLayoutParser(String layoutClassName) {
		super(layoutClassName);
	}

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		super.parse(elem, fieldName, type, writer);
		
		JClassType absoluteDataType = writer.getOracle().findType(GxtClassnameConstants.ABSOLUTEDATA);
		
		for (XMLElement layoutdata : consumeChildLayoutData(elem)) {
			String layoutData = writer.declareField(GxtClassnameConstants.ABSOLUTEDATA, layoutdata);
			ElementParserUtil.applyAttributes(layoutdata, layoutData, absoluteDataType, writer);
			
			for(XMLElement child : layoutdata.consumeChildElements()) {
				String childName = writer.parseElementToField(child);
				
				writer.addStatement("%s.add(%s, %s);", fieldName, childName, layoutData);
			}
		}
	}

}