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
		String rowLayoutOrientation = elem.consumeAttribute("rowLayoutOrientation", orientationType);
		if (rowLayoutOrientation != null) {
			writer.addStatement("%s.setOrientation(%s);", layout, rowLayoutOrientation);
		}
		
		JClassType rowDataType = writer.getOracle().findType(GxtClassnameConstants.ROWDATA);
		
		for(XMLElement layoutdata : consumeChildLayoutData(elem)) {
			String layoutData = writer.declareField(GxtClassnameConstants.ROWDATA, layoutdata);
			ElementParserUtil.applyAttributes(layoutdata, layoutData, rowDataType, writer);
			
			for(XMLElement child : layoutdata.consumeChildElements()) {
				String childName = writer.parseElementToField(child);
				
				writer.addStatement("%s.add(%s, %s);", fieldName, childName, layoutData);
			}			
		}
	}

}
