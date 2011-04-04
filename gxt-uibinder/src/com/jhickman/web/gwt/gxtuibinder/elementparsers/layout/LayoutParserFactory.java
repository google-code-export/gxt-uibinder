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