/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
 *
 */
public final class ElementParserUtil {
	
	/**
	 * Consumes and applys attributes to given element
	 * 
	 * @param elem
	 * @param fieldName
	 * @param type
	 * @param writer
	 * @throws UnableToCompleteException
	 */
	public static void applyAttributes(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {

		// first get the special Margin attribute
		ElementParserUtil.consumeMarginAttribute(elem, fieldName, writer);

		
		Map<String, JType> setterMethods = fetchSetterMethods(type);
		writer.addStatement("// %s", setterMethods);
		int attributeCount = elem.getAttributeCount();
		
		
		// count backwards since we're consuming as we go
		for (int i = attributeCount-1; i >= 0; i--) {
			// always get 0 because we're consuming them
			XMLAttribute attribute = elem.getAttribute(i);
			
			String setterMethod = "set" + initialCap(attribute.getName());
			
			if (setterMethods.containsKey(setterMethod)) {
				String value = elem.consumeAttribute(attribute.getName(), setterMethods.get(setterMethod));
				writer.addStatement("%s.%s(%s);", fieldName, setterMethod, value);
			} else {
				writer.warn(elem, "Found attribute without associated setter method: %s.  IGNORING", attribute.getName());
			}
		}
	}
	
	/**
	 * Grabs all write-property names with associated JType
	 * 
	 * @param type
	 * @return map of write-property name to JType
	 */
	public static Map<String, JType> fetchSetterMethods(JClassType type) {
		Map<String, JType> columnConfigSetterTypes = new HashMap<String, JType>();
		JMethod[] columnConfigTypeMethods = type.getMethods();
		for (JMethod jMethod : columnConfigTypeMethods) {
			if (jMethod.getName().startsWith("set") && jMethod.getParameters().length == 1) {
				columnConfigSetterTypes.put(jMethod.getName(), jMethod.getParameters()[0].getType());
			}
		}
		return columnConfigSetterTypes;
	}
	
	/**
	 * Capitalizes the first character of provided String
	 * @param text
	 * @return same String, but first character capitalized
	 */
	public static String initialCap(String text) {
		return text.substring(0, 1).toUpperCase()
			+ text.substring(1);
	}
	
	
	/**
	 * Consumes the margins attribute from provided element and constructs
	 * a proper Margins object from the comma separated data.
	 * @param elem - Element that contains the margins attribute
	 * @param fieldName - variable name of the object to receive the new Margins object
	 * @param writer
	 * @return fieldName of Margins object
	 * @throws UnableToCompleteException
	 */
	public static String consumeMarginAttribute(XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		String[] margins = elem.consumeRawArrayAttribute("margins");
		
		if (margins != null && margins.length > 0) {
			JClassType marginsType = writer.getOracle().findType(GxtClassnameConstants.MARGINS);
			String marginsField = writer.declareField(GxtClassnameConstants.MARGINS, elem);
			writer.setFieldInitializerAsConstructor(marginsField, marginsType, margins);
			
			writer.addStatement("%s.setMargins(%s);", fieldName, marginsField);
			
			return marginsField;
		}
		return null;
	}

	/**
	 * Consumes the styleAttribute attribute from the provided elements.
	 * 
	 * Allowed value format is:
	 * styleName:value;styleName:value
	 * 
	 * 
	 * @param elem
	 * @param fieldName
	 * @param writer
	 * @return
	 * @throws UnableToCompleteException
	 */
	public static void consumeStyleAttribute(XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		String styleAttributes = elem.consumeRawAttribute("styleAttribute");
		if (styleAttributes == null) return;
		
		String[] styles = styleAttributes.split("\\s*;\\s*");
		for (String style : styles) {
			String[] keyValue = style.split("\\s*:\\s*");
			if (keyValue.length != 2) {
				writer.die(elem, "styleAttribute should be in format: styleName:styleValue;styleName:styleValue  but found %s", styleAttributes);
			}
			
			writer.addStatement("%s.setStyleAttribute(\"%s\", \"%s\");", fieldName, keyValue[0], keyValue[1]);
		}
		
		
		
	}	
	
	private ElementParserUtil() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}
}
