/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.rebind;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.uibinder.rebind.FieldManager;
import com.google.gwt.uibinder.rebind.IndentedWriter;
import com.google.gwt.uibinder.rebind.MortalLogger;
import com.google.gwt.uibinder.rebind.model.OwnerClass;
import com.jhickman.web.gwt.customuibinder.rebind.CustomHandlerEvaluator;
import com.jhickman.web.gwt.gxtuibinder.client.GxtUiHandler;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
 */
public class GxtHandlerEvaluator implements CustomHandlerEvaluator {
    
    private static final String HANDLER_BASE_NAME = "handlerMethodWithLongNameSimilarToTheOfficialHandlerEvaluatorImplementation";

    private int varCounter = 0;
    
    public void run(IndentedWriter writer, FieldManager fieldManager, String uiOwner, OwnerClass ownerClass, MortalLogger logger, TypeOracle oracle) throws UnableToCompleteException {

        for(JMethod method : getUiHandlers(ownerClass.getOwnerType())) {
            String boundMethod = method.getName();
            if(method.isPrivate()) {
                logger.die("Method '%s' cannot be private.", boundMethod);
            }

            // Retrieves both event and handler types.
            JParameter[] parameters = method.getParameters();
            if (parameters.length != 1) {
                logger.die("Method '%s' must have a single event parameter defined.",
                        boundMethod);
            }
            JClassType eventType = parameters[0].getType().isClass();
            
            GxtUiHandler annotation = method.getAnnotation(GxtUiHandler.class);
            String eventTypeName = annotation.eventType().toString();
            if (eventTypeName == null) {
              logger.die("Parameter '%s' cannot be null.", "eventType");
            }

            String handlerVarName = HANDLER_BASE_NAME + (++varCounter);
            writeHandler(writer, uiOwner, handlerVarName, eventType, boundMethod, oracle);
            
            
            JClassType events = oracle.findType(GxtClassnameConstants.EVENTS);
            for (String objectName : annotation.uiField()) {
                writer.write("%s.addListener(%s.%s, %s);", 
                        objectName,
                        events.getQualifiedSourceName(),
                        eventTypeName,
                        handlerVarName
                        );
            }
        }   
    }
    

    protected void writeHandler(
            IndentedWriter writer,
            String uiOwner,
            String handlerVarName, 
            JClassType eventType,
            String boundMethod, TypeOracle oracle) throws UnableToCompleteException {

        JClassType handlerType = oracle.findType(GxtClassnameConstants.LISTENER);
        
        writer.newline();
        writer.write("final %1$s<%2$s> %3$s = new %1$s<%2$s>() {", 
                handlerType.getQualifiedSourceName(),
                eventType.getQualifiedSourceName(),
                handlerVarName);
        writer.indent();
        
        writer.write("public void handleEvent(%s event) {", eventType.getQualifiedSourceName());
        writer.indent();
        writer.write("%s.%s(event);", uiOwner, boundMethod);
        writer.outdent();
        writer.write("}");
        writer.outdent();
        writer.write("};");
    }
    
    private List<JMethod> getUiHandlers(JClassType ownerClass) {
        List<JMethod> uiHandlerMethods = new ArrayList<JMethod>();
        JMethod[] methods = ownerClass.getMethods();
        for (JMethod method : methods) {
            if(method.isAnnotationPresent(GxtUiHandler.class)) {
                uiHandlerMethods.add(method);
            }
        }

        JClassType superclass = ownerClass.getSuperclass();
        if (superclass != null) {
            uiHandlerMethods.addAll(getUiHandlers(superclass));
        }
        
        return uiHandlerMethods;
    }
}
