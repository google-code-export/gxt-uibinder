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
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.BorderLayoutParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.GenericLayoutParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.LayoutParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.layout.RowLayoutParser;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;


/**
 * Gives ability for any GXT Widget that extends LayoutContainer to
 * behave as if it implemented the HasWidgets interface.
 * 
 * @author hickman
 */
public class LayoutContainerParser implements ElementParser {
    private static enum Layout {
        BorderLayout(new BorderLayoutParser()),
        
        AccordionLayout(new GenericLayoutParser(GxtClassnameConstants.ACCORDIONLAYOUT)),
        FitLayout(new GenericLayoutParser(GxtClassnameConstants.FITLAYOUT)),
        FlowLayout(new GenericLayoutParser(GxtClassnameConstants.FLOWLAYOUT)),
        CardLayout(new GenericLayoutParser(GxtClassnameConstants.CARDLAYOUT)),
        CenterLayout(new GenericLayoutParser(GxtClassnameConstants.CENTERLAYOUT)),
        VBoxLayout(new GenericLayoutParser(GxtClassnameConstants.VBOXLAYOUT)),
        HBoxLayout(new GenericLayoutParser(GxtClassnameConstants.HBOXLAYOUT)),
        FormLayout(new GenericLayoutParser(GxtClassnameConstants.FORMLAYOUT)),
        AbsoluteLayout(new GenericLayoutParser(GxtClassnameConstants.ABSOLUTELAYOUT)),
        RowLayout(new RowLayoutParser(GxtClassnameConstants.ROWLAYOUT)),
        FillLayout(new RowLayoutParser(GxtClassnameConstants.FILLLAYOUT))
        ;
        private final LayoutParser layoutParser;
        private Layout(LayoutParser layoutParser) {
            this.layoutParser = layoutParser;
        }
        
        // no need for optimization here
        public static boolean contains(String name) {
            for(Layout l : values()) {
                if (l.toString().equals(name)) {
                    return true;
                }
            }
            return false;
        }
        
        public static Layout valueOf(XMLElement elem, String type, UiBinderWriter writer) throws UnableToCompleteException {
        	if ( ! Layout.contains(type)) {
        		StringBuilder sb = new StringBuilder();
        		sb.append("'layout' must be one of ");
                for(Layout allowed : Layout.values()) {
                    sb.append(allowed.toString()).append(", ");
                }
                sb.append("but found ").append(type);
                writer.die(elem, sb.toString());
        	}
        	
        	return Layout.valueOf(type);
        }
    }
    
    
    @Override
    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	
    	if (consumeLayout(elem, fieldName, type, writer)) {
    		// no need to proceed with "default"s since developer set an explicit Layout
    		return;
    	}
    	
    	if (GxtClassnameConstants.FORMPANEL.equals(type.getQualifiedSourceName())) {
        	// default layout for FormPanel
        	Layout.FormLayout.layoutParser.parse(elem, fieldName, type, writer);
        } else {
        	// FlowLayout is the default as per Sencha Javadoc
        	Layout.FlowLayout.layoutParser.parse(elem, fieldName, type, writer);
        }
    }
    
    
    private boolean consumeLayout(XMLElement elem, String layoutContainerFieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
    	boolean layoutFound = false;
    	// locate child Element
    	for (XMLElement layoutChild : elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "layout"))) {
    		if (layoutFound) {
    			writer.die(elem, "LayoutContainer's and subclasses can contain only a single <%s:layout /> child.  Found multiple.", elem.getPrefix());
    		}
    		
    		String layoutType = layoutChild.consumeRequiredRawAttribute("type");
    		Layout layout = Layout.valueOf(elem, layoutType, writer);
    		layout.layoutParser.parse(layoutChild, elem, layoutContainerFieldName, type, writer);
    		
    		layoutFound = true;
    	}
    	
    	
    	// first, check attribute
    	XMLAttribute layoutAttribute = elem.getAttribute("layout");
    	if (layoutAttribute != null) {
    		if (layoutFound) {
    			writer.die(elem, "LayoutContainer can contain either a layout attribute or a single nested <%s:layout /> child.  Not both.", elem.getPrefix());
    		}
    		String layoutType = layoutAttribute.consumeRawValue();
    		Layout layout = Layout.valueOf(elem, layoutType, writer);
    		layout.layoutParser.parse(elem, layoutContainerFieldName, type, writer);
    		layoutFound = true;
    	}
    	
    	return layoutFound;
    }
}
