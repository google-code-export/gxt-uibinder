/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.resourceparsers;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.TextInterpreter;
import com.google.gwt.uibinder.rebind.FieldManager;
import com.google.gwt.uibinder.rebind.FieldWriter;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.jhickman.web.gwt.customuibinder.resourceparsers.ResourceParser;

/**
 * Experimental implementation of XTemplate parser in UiBinder XML files.
 * 
 * <p>
 * Sample Usage:
 * </p>
 * <p>
 * <pre>
 * &lt;gxtui:xtemplate name="basicTemplate">
 *   &lt;p>Name: {name}&lt;/p>
 *   &lt;p>Company: {company}&lt;/p>
 *   &lt;p>Location: {location}&lt;/p>
 * &lt;/gxtui:xtemplate>
 * </pre>
 * </p>
 * @author hickman
 */
public class XTemplateParser implements ResourceParser {

	/* (non-Javadoc)
	 * @see com.jhickman.web.gwt.customuibinder.resourceparsers.ResourceParser#supports(com.google.gwt.uibinder.rebind.XMLElement)
	 */
	@Override
	public boolean supports(XMLElement elem) {
		return "urn:ui:com.jhickman.web.gwt.gxtuibinder".equals(elem.getNamespaceUri())
			&& "xtemplate".equals(elem.getLocalName());
	}

	/* (non-Javadoc)
	 * @see com.jhickman.web.gwt.customuibinder.resourceparsers.ResourceParser#parse(com.google.gwt.uibinder.rebind.XMLElement, com.google.gwt.uibinder.rebind.FieldManager, com.google.gwt.uibinder.rebind.UiBinderWriter)
	 */
	@Override
	public void parse(XMLElement elem, FieldManager fieldManager, UiBinderWriter writer) throws UnableToCompleteException {
		String resourceName = elem.consumeRequiredRawAttribute("name");
				
		//fieldManager.registerField(fieldType, fieldName);
		JClassType xtemplateType = writer.getOracle().findType("com.extjs.gxt.ui.client.core.XTemplate");
		
		String innerHtml = elem.consumeInnerHtml(new TextInterpreter(writer));
		String text = UiBinderWriter.escapeTextForJavaStringLiteral(innerHtml);
		
		FieldWriter fieldWriter = fieldManager.registerField(xtemplateType, resourceName);
		fieldWriter.setInitializer("com.extjs.gxt.ui.client.core.XTemplate.create(\"" + text + "\")");
	}
}
