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
import com.google.gwt.core.ext.typeinfo.NotFoundException;
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
	 * @param elem
	 * @param fieldName
	 * @param typeName
	 * @param writer
	 */
	public static void applyAttributes(XMLElement elem, String fieldName, String typeName, UiBinderWriter writer) throws UnableToCompleteException {
		JClassType type = writer.getOracle().findType(typeName);
		applyAttributes(elem, fieldName, type, writer);
	}

	
	/**
	 * Consumes and applies attributes to given element
	 * 
	 * @param elem
	 * @param fieldName
	 * @param type
	 * @param writer
	 * @throws UnableToCompleteException
	 */
	public static void applyAttributes(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {

		// first get the special Margin attribute
		ElementParserUtil.consumeRegionAttributes(elem, fieldName, writer);

		Map<String, JType> setterMethods = fetchSetterMethods(type);
		int attributeCount = elem.getAttributeCount();
		
		// count backwards since we're consuming as we go
		for (int i = attributeCount-1; i >= 0; i--) {
			// always get 0 because we're consuming them
			XMLAttribute attribute = elem.getAttribute(i);
			
			String setterMethod = "set" + initialCap(attribute.getName());
			
			if (setterMethods.containsKey(setterMethod)) {
				JType setterType = setterMethods.get(setterMethod);
				String value;
				
				if ("float".equals(setterType.getQualifiedSourceName())) {
					value = attribute.consumeRawValue();
					float floatValue = 0;
					try {
						floatValue = Float.parseFloat(value);
					} catch (NumberFormatException e) {
						writer.die(elem, "Cannot parse float value for attribute '%s'.  Found %s.", attribute.getName(), value);
					}
					// assuming that we didn't die due to a NumberFormatException, we can use the value
					value = floatValue + "f";
				} else if ("java.lang.Number".equals(setterType.getQualifiedSourceName())) {
					value = elem.consumeRawAttribute(attribute.getName());
				} else {
					value = elem.consumeAttribute(attribute.getName(), setterType);
				}
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
		if (type.getSuperclass() != null) {
			columnConfigSetterTypes.putAll(fetchSetterMethods(type.getSuperclass()));
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
	
	
	public static void consumeRegionAttributes(XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		consumeMarginAttribute(elem, fieldName, writer);
		consumePaddingAttribute(elem, fieldName, writer);
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
		String marginsField = parseMarginsAttribute(elem, writer);
		if (marginsField != null) {
			writer.addStatement("%s.setMargins(%s);", fieldName, marginsField);
			
			return marginsField;
		}
		return null;
	}
	
	public static String consumePaddingAttribute(XMLElement elem, String fieldName, UiBinderWriter writer) throws UnableToCompleteException {
		String paddingField = parsePaddingAttribute(elem, writer);
		if (paddingField != null) {
			writer.addStatement("%s.setPadding(%s);", fieldName, paddingField);
			
			return paddingField;
		}
		return null;
	}
	
	public static String parseMarginsAttribute(XMLElement elem, UiBinderWriter writer) throws UnableToCompleteException {
		return parseAttributeWithArrayConstructor(elem, "margins", GxtClassnameConstants.MARGINS, writer);
	}
	
	public static String parsePaddingAttribute(XMLElement elem, UiBinderWriter writer) throws UnableToCompleteException {
		return parseAttributeWithArrayConstructor(elem, "padding", GxtClassnameConstants.PADDING, writer);
	}
	
	
	
	public static String parseAttributeWithArrayConstructor(XMLElement elem, String attributeName, String typeName, UiBinderWriter writer) throws UnableToCompleteException {
		String[] arrayData = elem.consumeRawArrayAttribute(attributeName);
		
		if (arrayData != null && arrayData.length > 0) {
			JClassType type = writer.getOracle().findType(typeName);
			String fieldName = writer.declareField(typeName, elem);
			writer.setFieldInitializerAsConstructor(fieldName, type, arrayData);
			return fieldName;
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

	/**
	 * @param button
	 * @param button2
	 * @return
	 */
	public static boolean isElementOfType(UiBinderWriter writer, XMLElement elem, String typeName) throws UnableToCompleteException {
		JClassType elemType = writer.findFieldType(elem);
		JClassType componentType;
		try {
			componentType = writer.getOracle().getType(typeName);
		} catch (NotFoundException e) {
			throw new UnableToCompleteException();
		}

		return componentType.isAssignableFrom(elemType);
	}
}
