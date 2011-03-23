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
    	
    	

        XMLAttribute styleAttribute = elem.getAttribute("styleAttribute");
        if (styleAttribute != null) {
            String value = styleAttribute.consumeRawValue();
            String[] parts = value.split(",");
            if (parts.length == 2) {
                writer.addStatement("%s.setStyleAttribute(\"%s\", \"%s\");", fieldName, parts[0].trim(), parts[1].trim());
            }
        }
        
        
        
        Set<String> layoutRegion = new HashSet<String>();
        
        for (XMLElement child : elem.consumeChildElements()) {
            if ( ! isValidChildElement(elem, child)) {
                writer.die(elem,
                        "Child must be one of "
                        + "<%1$s:north>, <%1$s:south>, <%1$s:east>, <%1$s:west> or <%1$s:center>, "
                        + "but found %2$s", elem.getPrefix(), child);
            }
            
            
            XMLElement widget = child.consumeSingleChildElement();
            if ( ! writer.isWidgetElement(widget)) {
                writer.die(elem, "%s must contain a widget, but found %s", child, widget);
            }
            
            // have we already added this region?
            String region = child.getLocalName();
            if (layoutRegion.contains(region)) {
                writer.die(elem, "Only one <%s:%s> is allowed", elem.getPrefix(), region);
            }
            layoutRegion.add(region);
            
            String widgetName = writer.parseElementToField(widget);            
            String layoutDataName = widgetName + "Data";

            if ( "center".equals(region)) {
                writer.addStatement("%1$s %2$s = new %1$s(%3$s.%4$s);", GxtClassnameConstants.BORDERLAYOUTDATA, layoutDataName, GxtClassnameConstants.LAYOUTREGION, region.toUpperCase());                
            } else {
                String size = child.consumeRequiredIntAttribute("size");
                writer.addStatement("%1$s %2$s = new %1$s(%3$s.%4$s, %5$s);", GxtClassnameConstants.BORDERLAYOUTDATA, layoutDataName, GxtClassnameConstants.LAYOUTREGION, region.toUpperCase(), size);
            }
            handleLayoutDataAttributes(layoutDataName, child, writer);
            
            writer.addStatement("%s.add(%s, %s);", fieldName, widgetName, layoutDataName);
        }
    }
    
    private void handleLayoutDataAttributes(String fieldName, XMLElement child, UiBinderWriter writer) throws UnableToCompleteException {
        String split = child.consumeBooleanAttribute("split");
        String collapsible = child.consumeBooleanAttribute("collapsible");
        String[] margins = child.consumeRawArrayAttribute("margins");
        
        if (split != null) {
            writer.addStatement("%s.setSplit(%s);", fieldName, split); 
        }
        
        if (collapsible != null) {
            writer.addStatement("%s.setCollapsible(%s);", fieldName, collapsible);
        }
        
        if (margins != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < margins.length; i++) {
                sb.append(margins[i]);
                if ((i+1) != margins.length) {
                    sb.append(",");
                }
            }
            writer.addStatement("%s.setMargins(new %s(%s));", fieldName, GxtClassnameConstants.MARGINS, sb.toString());
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
