/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.treepanel;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;

/**
 * @author hickman
 *
 */
public class TreePanelParser implements ElementParser {

	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		JClassType treePanelType = writer.getOracle().findType(GxtClassnameConstants.TREEPANEL);

		JClassType treeStoreType = writer.getOracle().findType(GxtClassnameConstants.TREESTORE);
		String store = elem.consumeRequiredAttribute("store", treeStoreType);
		
		writer.setFieldInitializerAsConstructor(fieldName, treePanelType, store);
	}
}
