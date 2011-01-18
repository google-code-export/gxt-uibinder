/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import java.util.Collection;

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
	
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		writer.addStatement("%s.setLayout(new %s());", fieldName, layoutClassName);
		
		for(XMLElement layoutDataElem : elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "layoutdata"))) {
			XMLAttribute typeAttribute = layoutDataElem.getAttribute("type");
			if (typeAttribute != null) {
				LayoutDataType layoutDataType = LayoutDataType.valueOf(typeAttribute.consumeRawValue());
				String layoutData = layoutDataType.declareField(writer, layoutDataElem);

				for(XMLElement child : layoutDataElem.consumeChildElements()) {
					String childField = writer.parseElementToField(child);
					writer.addStatement("%s.add(%s, %s);", fieldName, childField, layoutData);
				}
			}
		}
	}
	
	protected Collection<XMLElement> consumeChildLayoutData(XMLElement elem) throws UnableToCompleteException {
		return elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "layoutdata"));
	}

	private enum LayoutDataType {
		FlowData(GxtClassnameConstants.FLOWDATA) {
			@Override
			public String declareField(UiBinderWriter writer, XMLElement layoutDataElem) throws UnableToCompleteException {
				String marginsAttribute = ElementParserUtil.parseMarginsAttribute(layoutDataElem, writer);
				if (marginsAttribute == null) {
					writer.die("For FlowData layoutdata object, attribute 'margins' is required.", layoutDataElem);
				}
				
				String field = writer.declareField(this.className, layoutDataElem);
				JClassType flowDataType = writer.getOracle().findType(GxtClassnameConstants.FLOWDATA);
				writer.setFieldInitializerAsConstructor(field, flowDataType, marginsAttribute);
				
				ElementParserUtil.applyAttributes(layoutDataElem, field, flowDataType, writer);
				return field;
			}
		},
		RowData(GxtClassnameConstants.ROWDATA) {
			@Override
			public String declareField(UiBinderWriter writer,
					XMLElement layoutDataElem) {
				// TODO Auto-generated method stub
				return null;
			}
		}
		;
		
		protected final String className;

		private LayoutDataType(String className) {
			this.className = className;
		}

		public abstract String declareField(UiBinderWriter writer, XMLElement layoutDataElem) throws UnableToCompleteException;
	}
}
