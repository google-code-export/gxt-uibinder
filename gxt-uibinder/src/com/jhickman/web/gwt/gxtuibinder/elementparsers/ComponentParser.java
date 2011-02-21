/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import java.util.Collection;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.ElementParserUtil;

/**
 * @author hickman
 *
 */
public class ComponentParser implements ElementParser {

	/* (non-Javadoc)
	 * @see com.google.gwt.uibinder.elementparsers.ElementParser#parse(com.google.gwt.uibinder.rebind.XMLElement, java.lang.String, com.google.gwt.core.ext.typeinfo.JClassType, com.google.gwt.uibinder.rebind.UiBinderWriter)
	 */
	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type,UiBinderWriter writer) throws UnableToCompleteException {
		
		ElementParserUtil.consumeStyleAttribute(elem, fieldName, writer);
		ElementParserUtil.applyAttributes(elem, fieldName, type, writer);
		
		handleToolTips(elem, fieldName, writer);
	}
	
	protected void handleToolTips(XMLElement elem, String fieldName,
			UiBinderWriter writer) throws UnableToCompleteException {
		Interpreter<Boolean> toolTipConfigInterpreter = new ToolTipConfigInterpreter(elem.getNamespaceUri());
		Collection<XMLElement> toolTipConfigs = elem.consumeChildElements(toolTipConfigInterpreter);
		if (toolTipConfigs.isEmpty()) return;
		if (toolTipConfigs.size() > 1) {
			writer.die(elem, "tooltipconfig can only be used once");
		}
		
		XMLElement toolTipConfigElem = toolTipConfigs.iterator().next();
		
		String toolTipConfig = writer.declareField(GxtClassnameConstants.TOOLTIPCONFIG, toolTipConfigElem);
		
		JClassType toolTipConfigType = writer.getOracle().findType(GxtClassnameConstants.TOOLTIPCONFIG);
		ElementParserUtil.applyAttributes(toolTipConfigElem, toolTipConfig, toolTipConfigType, writer);
		
		writer.addStatement("%s.setToolTip(%s);", fieldName, toolTipConfig);
	}

	
	private static final class ToolTipConfigInterpreter implements Interpreter<Boolean> {
		private final String namespaceUri;
		public  ToolTipConfigInterpreter(String namespaceUri) {
			this.namespaceUri = namespaceUri;
		}
		
		@Override
		public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
			if (namespaceUri == null) return false;
			return "tooltipconfig".equals(elem.getLocalName());
		}
	}
}
