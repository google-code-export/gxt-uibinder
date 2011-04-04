/**
 * Copyright 2010 Justin Hickman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public class LayoutDataFieldFactory {

	/**
	 * @param layoutDataElem
	 * @param layoutDataSimpleName
	 * @param writer
	 * @return
	 */
	public static String declareField(XMLElement layoutDataElem, String layoutDataSimpleName, UiBinderWriter writer) throws UnableToCompleteException {
		
		if (SpecialHandlingLayoutDataType.contains(layoutDataSimpleName)) {
			return SpecialHandlingLayoutDataType.valueOf(layoutDataSimpleName).declareField(writer, layoutDataElem);
		}
		
		// not one that requires special handling
		JClassType layoutDataType = writer.getOracle().findType(GxtClassnameConstants.LAYOUT_BASE_PACKAGE + layoutDataSimpleName);
		if (layoutDataType == null) {
			writer.die(layoutDataElem, "Unable to find specified LayoutData type: %s", layoutDataSimpleName);
		}
		
		return declareField(layoutDataElem, layoutDataType, writer);
	}
	
	/**
	 * @param layoutDataElem
	 * @param layoutDataType
	 * @param writer
	 * @return
	 */
	private static String declareField(XMLElement layoutDataElem, JClassType layoutDataType, UiBinderWriter writer) throws UnableToCompleteException {
		String marginsAttribute = ElementParserUtil.parseMarginsAttribute(layoutDataElem, writer);
		
		String field = writer.declareField(layoutDataType.getQualifiedSourceName(), layoutDataElem);

		if (marginsAttribute != null) {
			writer.setFieldInitializerAsConstructor(field, layoutDataType, marginsAttribute);
		}
		
		ElementParserUtil.applyAttributes(layoutDataElem, field, layoutDataType, writer);
		return field;
	}
	

	private enum SpecialHandlingLayoutDataType {
		RowData {
			@Override
			public String declareField(UiBinderWriter writer, XMLElement layoutDataElem) throws UnableToCompleteException {
				JClassType rowDataType = writer.getOracle().findType(GxtClassnameConstants.ROWDATA);
				String layoutData = writer.declareField(GxtClassnameConstants.ROWDATA, layoutDataElem);
				ElementParserUtil.applyAttributes(layoutDataElem, layoutData, rowDataType, writer);
				
				return layoutData;
			}
		},
		BorderLayoutData {
			@Override
			public String declareField(UiBinderWriter writer, XMLElement layoutDataElem) throws UnableToCompleteException {
				JClassType borderLayoutDataType = writer.getOracle().findType(GxtClassnameConstants.BORDERLAYOUTDATA);
				String layoutData = writer.declareField(GxtClassnameConstants.BORDERLAYOUTDATA, layoutDataElem);
				
				String region = layoutDataElem.getLocalName().toUpperCase();
				String parameter = GxtClassnameConstants.LAYOUTREGION + "." + region; 
				writer.setFieldInitializerAsConstructor(layoutData, borderLayoutDataType, parameter);
				
				ElementParserUtil.applyAttributes(layoutDataElem, layoutData, GxtClassnameConstants.BORDERLAYOUTDATA, writer);

				return layoutData;
			}
		}
		;
		
		public static boolean contains(String layoutSimpleName) {
			for (SpecialHandlingLayoutDataType type : values()) {
				if (type.name().equals(layoutSimpleName)) {
					return true;
				}
			}
			return false;
		}
		
		abstract String declareField(UiBinderWriter writer, XMLElement layoutDataElem) throws UnableToCompleteException;
	}

}
