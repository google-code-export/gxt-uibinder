/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.menu;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * MenuItems can contain a single child of type Menu.
 * This menu is added to the MenuItem by calling setSubMenu()
 * 
 * 
 * @author hickman
  */
public class MenuItemParser implements ElementParser {

    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
        boolean found = false;
        for (XMLElement child : elem.consumeChildElements()) {
            if (found) {
                writer.die(elem, "element only supports 0 or 1 child elements.");
            }
            found = true;
            
            JClassType menuType = writer.getOracle().findType(GxtClassnameConstants.MENU);
            JClassType childType = writer.findFieldType(child);
            if ( ! menuType.isAssignableFrom(childType)) {
                writer.die(child, "%s only supports children type Menu", elem.getLocalName());
            }
            
            String menuVarName = writer.parseElementToField(child);
            writer.addStatement("%s.setSubMenu(%s);", fieldName, menuVarName);
        }
    }
}
