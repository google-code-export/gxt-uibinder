/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 *
 */
public class GridParser implements ElementParser {
	
	private static final AtomicInteger COUNTER = new AtomicInteger();
	private static final String COLUMN_MODEL_FIELD_BASENAME = "uniqueVariableNameForItemsInGridParser_";
	
	
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		JClassType listStoreType = writer.getOracle().findType(GxtClassnameConstants.LISTSTORE);
		String store = elem.consumeAttribute("store", listStoreType);
		
		if (store == null) {
			writer.die(elem, "Attribute 'store' is required");
		}
		
		
		
		JClassType arrayListType = writer.getOracle().findType(ArrayList.class.getName());
		JClassType columnConfigType = writer.getOracle().findType(GxtClassnameConstants.COLUMNCONFIG);
		JParameterizedType parameterizedArrayListType = writer.getOracle().getParameterizedType(arrayListType.isGenericType(), new JClassType[] {columnConfigType});
		
		
		
		Map<String, JType> columnConfigSetterTypes = fetchColumnConfigProperties(columnConfigType);

		
		// List<ColumnConfig>
		String columnConfigList = writer.declareField(List.class.getName(), elem);
		writer.setFieldInitializerAsConstructor(columnConfigList, parameterizedArrayListType);
		
		// new ColumnModel(columnConfig)
		String columnModel = writer.declareField(GxtClassnameConstants.COLUMNMODEL, elem);
		writer.setFieldInitializer(columnModel, String.format("new %s((java.util.List<%s>) %s);", GxtClassnameConstants.COLUMNMODEL, GxtClassnameConstants.COLUMNCONFIG, columnConfigList));

		// new Grid(store, columnModel);
		writer.setFieldInitializerAsConstructor(fieldName, type, store, columnModel);
		
		

		for(XMLElement child : elem.consumeChildElements()) {
			if ( ! elem.getPrefix().equals(child.getPrefix())) {
				writer.die(child, "Child node of %s must use the same prefix.  Expecting %s, but found %s", elem, elem.getPrefix(), child.getPrefix());
			}
			
			if ( ! "column".equals(child.getLocalName())) {
				writer.die(child, "Only <%s:column> children are allowed. Found %s.", elem.getPrefix(), child);
			}
			
			String columnConfig = writer.declareField(GxtClassnameConstants.COLUMNCONFIG, elem);
			
			applyColumnConfigProperties(writer, columnConfigSetterTypes, child,	columnConfig);
			
			writer.addStatement("%s.add(%s);", columnConfigList, columnConfig);
		}
	}


	protected void applyColumnConfigProperties(UiBinderWriter writer,
			Map<String, JType> columnConfigSetterTypes, XMLElement child,
			String columnConfig) throws UnableToCompleteException {
		
		for(int i = 0; i < child.getAttributeCount(); i++) {
			XMLAttribute attribute = child.getAttribute(i);
			
			String setterMethod = "set" + initialCap(attribute.getName());
			String value = child.consumeAttribute(attribute.getName(), columnConfigSetterTypes.get(setterMethod));
			writer.addStatement("%s.%s(%s);", columnConfig, setterMethod, value);
		}
	}


	protected Map<String, JType> fetchColumnConfigProperties(
			JClassType columnConfigType) {
		Map<String, JType> columnConfigSetterTypes = new HashMap<String, JType>();
		JMethod[] columnConfigTypeMethods = columnConfigType.getMethods();
		for (JMethod jMethod : columnConfigTypeMethods) {
			if (jMethod.getName().startsWith("set") && jMethod.getParameters().length == 1) {
				columnConfigSetterTypes.put(jMethod.getName(), jMethod.getParameters()[0].getType());
			}
		}
		return columnConfigSetterTypes;
	}
	
	
	private String initialCap(String propertyName) {
		return propertyName.substring(0, 1).toUpperCase()
			+ propertyName.substring(1);
	}


	private static String nextVariableName() {
		return COLUMN_MODEL_FIELD_BASENAME + COUNTER.addAndGet(1);
	}

}
