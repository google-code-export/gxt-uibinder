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
		
		Map<String, JType> setterMethods = fetchSetterMethods(type);
		writer.addStatement("// %s", setterMethods);
		int attributeCount = elem.getAttributeCount();
		for (int i = 0; i < attributeCount; i++) {
			// always get 0 because we're consuming them
			XMLAttribute attribute = elem.getAttribute(0);
			
			String setterMethod = "set" + initialCap(attribute.getName());
			
			if (setterMethods.containsKey(setterMethod)) {
				String value = elem.consumeAttribute(attribute.getName(), setterMethods.get(setterMethod));
				writer.addStatement("%s.%s(%s);", fieldName, setterMethod, value);
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
	
	
	private ElementParserUtil() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}
}
