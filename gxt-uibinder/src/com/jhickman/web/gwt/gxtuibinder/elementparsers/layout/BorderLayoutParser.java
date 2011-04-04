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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLAttribute;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
  */
public class BorderLayoutParser extends GenericLayoutParser {
    
    private static final List<String> DOCK_NAMES = new ArrayList<String>();
    static {
        DOCK_NAMES.add("north");
        DOCK_NAMES.add("south");
        DOCK_NAMES.add("east");
        DOCK_NAMES.add("west");
        DOCK_NAMES.add("center");
    }
    
    public BorderLayoutParser() {
    	super(GxtClassnameConstants.BORDERLAYOUT);
    }
    
    public void parse(XMLElement layoutElem, XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	createAndSetLayout(layoutElem, elem, fieldName, writer);
    	
    	// FIXME - this should use the same method used in ElementParserUtil.  Can't change until major version due to backwards compatibility
        XMLAttribute styleAttribute = elem.getAttribute("styleAttribute");
        if (styleAttribute != null) {
            String value = styleAttribute.consumeRawValue();
            String[] parts = value.split(",");
            if (parts.length == 2) {
                writer.addStatement("%s.setStyleAttribute(\"%s\", \"%s\");", fieldName, parts[0].trim(), parts[1].trim());
            }
        }
        
        Set<String> layoutRegionsSeen = new HashSet<String>();
        
        for (XMLElement layoutDataElem : elem.consumeChildElements()) {
            if ( ! isValidChildElement(elem, layoutDataElem)) {
            	StringBuilder sb = new StringBuilder("Child must be one of ");
            	for(String name : DOCK_NAMES) {
            		sb.append("<%1$s:").append(name).append(", ");
            	}
            	sb.append("but found %2$s");
                writer.die(elem, sb.toString(), elem.getPrefix(), layoutDataElem);
            }
            
            // have we already added this region?
            String region = layoutDataElem.getLocalName();
            if (layoutRegionsSeen.contains(region)) {
                writer.die(elem, "Only one <%s:%s> is allowed", elem.getPrefix(), region);
            }
            layoutRegionsSeen.add(region);

            // delegate to super class
            layoutDataElem.setAttribute("type", "BorderLayoutData");
            handleLayoutData(layoutDataElem, fieldName, writer);
        }
    }

    private boolean isValidChildElement(XMLElement parent, XMLElement child) {
        if ( ! parent.getNamespaceUri().equals(child.getNamespaceUri())) {
            return false;
        }
        
        if ( ! DOCK_NAMES.contains(child.getLocalName())) {
            return false;
        }
            
        return true;
    }
}
