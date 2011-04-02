/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.ElementParserUtil;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * @author hickman
 *
 */
public class GenericLayoutParser implements LayoutParser {
	
	protected final String layoutClassName;

	public GenericLayoutParser(String layoutClassName) {
		this.layoutClassName = layoutClassName;
	}
	
    public void parse(XMLElement layoutElem, XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	createAndSetLayout(layoutElem, elem, fieldName, writer);
    	
		handleChildren(elem, fieldName, writer);
    }


	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		parse(null, elem, fieldName, type, writer);
	}

	protected String createAndSetLayout(XMLElement layoutElem, XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		String layoutField = writer.declareField(layoutClassName, elem);
    	writer.addStatement("%s.setLayout(%s);", fieldName, layoutField);
    	
    	if (layoutElem != null) {
    		JClassType layoutType = writer.getOracle().findType(layoutClassName);
			ElementParserUtil.applyAttributes(layoutElem, layoutField, layoutType , writer);
    	}
    	
    	return layoutField;
	}
	
	
	protected void handleChildren(XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		SimpleInterpreter layoutDataInterpreter = new SimpleInterpreter(elem.getNamespaceUri(), "layoutdata");
		
		for(XMLElement child : elem.consumeChildElements()) {
			if (layoutDataInterpreter.interpretElement(child)) {
				handleLayoutData(child, fieldName, writer);
			} else {
				String childField = writer.parseElementToField(child);
				writer.addStatement("%s.add(%s);", fieldName, childField);
			}
		}
	}
	
	protected void handleLayoutData(XMLElement layoutDataElem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		XMLAttribute typeAttribute = layoutDataElem.getAttribute("type");
		if (typeAttribute != null) {
			String layoutDataField = LayoutDataFieldFactory.declareField(layoutDataElem, typeAttribute.consumeRawValue(), writer);

			for(XMLElement child : layoutDataElem.consumeChildElements()) {
				String childField = writer.parseElementToField(child);
				writer.addStatement("%s.add(%s, %s);", fieldName, childField, layoutDataField);
			}
		} else {
			writer.die(layoutDataElem, "layoutdata missing type attribute");
		}
	}

}
