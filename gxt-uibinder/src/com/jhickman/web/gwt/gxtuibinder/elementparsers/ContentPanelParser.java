/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.ElementParserUtil;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * Provides custom parsing for ContentPanel components.
 * 
 * <p>Provides detection of child elements: gxt:topcomponent 
 * and gxt:bottomcomponent.   Calls the appropriate setter method
 * for each.
 * 
 * @author hickman
 */
public class ContentPanelParser implements ElementParser {
    
    

    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
        handleTopBottomComponents(elem, fieldName, writer);
        
        for(XMLElement buttonsElem : elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "buttons"))) {
        	for(XMLElement button : buttonsElem.consumeChildElements()) {
        		if ( ! ElementParserUtil.isElementOfType(writer, button, GxtClassnameConstants.BUTTON)) {
        			writer.die("Element buttons can only contain Button children.", elem);
        		}
        		String buttonField = writer.parseElementToField(button);
        		writer.addStatement("%s.addButton(%s);", fieldName, buttonField);
        	}
        }
        

        for(XMLElement header : elem.consumeChildElements(new SimpleInterpreter(elem.getNamespaceUri(), "header"))) {
        	String headerFieldName = writer.declareField(GxtClassnameConstants.HEADER, header);
        	// set to null at first.  assign with addStatement
        	writer.setFieldInitializer(headerFieldName, "null");
        	writer.addStatement("%s = %s.getHeader();", headerFieldName, fieldName);
			ElementParserUtil.applyAttributes(header, headerFieldName , GxtClassnameConstants.HEADER, writer);
			
			for(XMLElement headerChild : header.consumeChildElements()) {
				String childField = writer.parseElementToField(headerChild);
				writer.addStatement("%s.addTool(%s);", headerFieldName, childField);
			}
        }
    }

    
    
	protected void handleTopBottomComponents(XMLElement elem, String fieldName,
			UiBinderWriter writer) throws UnableToCompleteException {
		Interpreter<Boolean> topBottomComponentInterpreter = new TopBottomComponentInterpreter(elem.getNamespaceUri());
        
        for(XMLElement child : elem.consumeChildElements(topBottomComponentInterpreter)) {
            XMLElement widget = child.consumeSingleChildElement();
            
            if ( ! isComponentElement(writer, widget)) {
                writer.die(elem, "%s must contain a GXT Component, but found %s", child, widget);
            }
            
            
            
            String widgetName = writer.parseElementToField(widget);
            String methodName = SupportedChildLocalNames.valueOf(child.getLocalName()).getMethodName();
            writer.addStatement("%s.%s(%s);", fieldName, methodName, widgetName);
        }
	}

    private boolean isComponentElement(UiBinderWriter writer, XMLElement widget) throws UnableToCompleteException {
        JClassType fieldType = writer.findFieldType(widget);
        JClassType componentType;
        try {
            componentType = writer.getOracle().getType(GxtClassnameConstants.COMPONENT);
        } catch (NotFoundException e) {
            throw new UnableToCompleteException();
        }
        
        return componentType.isAssignableFrom(fieldType);
    }

    private static final class TopBottomComponentInterpreter implements Interpreter<Boolean> {
        private final String namespaceUri;

        public TopBottomComponentInterpreter(String namespaceUri) {
            this.namespaceUri = namespaceUri;
        }

        public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
            if ( namespaceUri != null && namespaceUri.equals(elem.getNamespaceUri())) {
                for(SupportedChildLocalNames supported : SupportedChildLocalNames.values()) {
                    if (supported.toString().equals(elem.getLocalName())) {
                        return true;
                    }
                }
            }
            
            return false;
        }
    }
    
    private static enum SupportedChildLocalNames {
        topcomponent("setTopComponent"),
        bottomcomponent("setBottomComponent");
        
        private final String methodName;

        private SupportedChildLocalNames(String methodName) {
            this.methodName = methodName;
        }
        
        public String getMethodName() {
            return methodName;
        }
    }
}
