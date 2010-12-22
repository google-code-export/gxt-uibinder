/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.menu;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * Parses gxt CheckMenuItem components.
 * 
 * <p>setChecked method is overloaded, and UiBinder had an issue
 * with this and declared it as ambiguous.
 * 
 * @author hickman
 */
public class CheckMenuItemParser implements ElementParser {
    public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
        String checkedValue = elem.consumeBooleanAttribute("checked", false);
        writer.addStatement("%s.setChecked(%s);", fieldName, checkedValue);
    }
}
