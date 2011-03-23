/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.menu;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
 */
public class MenuBarItemParser implements ElementParser {
    
    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
        XMLElement menu = elem.consumeSingleChildElement();
        
        if ( ! isMenuElement(writer, menu)) {
            writer.die(elem, "%s must contain a Menu, but found %s", elem, menu);
        }
        
        
        String menuItemText = elem.consumeStringAttribute("text");
        String menuFieldName = writer.parseElementToField(menu);

        writer.setFieldInitializerAsConstructor(fieldName, type, menuItemText, menuFieldName);
    }

    private boolean isMenuElement(UiBinderWriter writer, XMLElement menu) throws UnableToCompleteException {
        JClassType fieldType = writer.findFieldType(menu);
        JClassType menuType;
        try {
            menuType = writer.getOracle().getType(GxtClassnameConstants.MENU);
        } catch (NotFoundException e) {
            throw new UnableToCompleteException();
        }
        return menuType.isAssignableFrom(fieldType);
    }
}
