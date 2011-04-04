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
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.LayoutParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.LayoutParserFactory;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;


/**
 * Gives ability for any GXT Widget that extends LayoutContainer to
 * behave as if it implemented the HasWidgets interface.
 * 
 * @author hickman
 */
public class LayoutContainerParser implements ElementParser {

    @Override
    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	
    	if (consumeLayout(elem, fieldName, type, writer)) {
    		// no need to proceed with "default"s since developer set an explicit Layout
    		return;
    	}
    	
    	LayoutParser layoutParser = null;
    	
    	if (GxtClassnameConstants.FORMPANEL.equals(type.getQualifiedSourceName())) {
        	// default layout for FormPanel
    		layoutParser = LayoutParserFactory.findLayoutParser(elem, GxtClassnameConstants.FORMLAYOUT, writer);
        } else {
        	// FlowLayout is the default as per Sencha Javadoc
        	layoutParser = LayoutParserFactory.findLayoutParser(elem, GxtClassnameConstants.FLOWLAYOUT, writer);
        }
    	
    	layoutParser.parse(elem, fieldName, type, writer);
    	
    	/*
    	 * having problem with TabItem.setText because of this..  Removing

    	XMLAttribute attribute = elem.getAttribute("text");
    	if (attribute != null) {
    		writer.addStatement("%s.addText(%s);", fieldName, attribute.consumeStringValue());
    	}
    	*/
    }
    
    
    private boolean consumeLayout(XMLElement elem, String layoutContainerFieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	boolean layoutFound = false;
    	// locate child Element
    	for (XMLElement layoutChild : elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "layout"))) {
    		if (layoutFound) {
    			writer.die(elem, "LayoutContainer's and subclasses can contain only a single <%s:layout /> child.  Found multiple.", elem.getPrefix());
    		}
    		
    		String layoutType = layoutChild.consumeRequiredRawAttribute("type");
    		
    		LayoutParser layoutParser = LayoutParserFactory.findLayoutParserBySimpleName(elem, layoutType, writer);
    		layoutParser.parse(layoutChild, elem, layoutContainerFieldName, type, writer);
    		
    		layoutFound = true;
    	}
    	
    	
    	// first, check attribute
    	XMLAttribute layoutAttribute = elem.getAttribute("layout");
    	if (layoutAttribute != null) {
    		if (layoutFound) {
    			writer.die(elem, "LayoutContainer can contain either a layout attribute or a single nested <%s:layout /> child.  Not both.", elem.getPrefix());
    		}
    		String layoutType = layoutAttribute.consumeRawValue();
    		
    		LayoutParser layoutParser = LayoutParserFactory.findLayoutParserBySimpleName(elem, layoutType, writer);
    		layoutParser.parse(elem, layoutContainerFieldName, type, writer);
    		layoutFound = true;
    	}
    	
    	return layoutFound;
    }
    
    
}
