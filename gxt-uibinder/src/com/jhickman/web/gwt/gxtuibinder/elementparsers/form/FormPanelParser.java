/**
 * Copyright 2010 Justin Hickman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers.form;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.GxtClassnameConstants;
import com.jhickman.web.gwt.gxtuibinder.elementparsers.util.SimpleInterpreter;

/**
 * @author hickman
 *
 */
public class FormPanelParser implements ElementParser {

	@Override
	public void parse(XMLElement elem, String fieldName, JClassType type, UiBinderWriter writer) throws UnableToCompleteException {
		
		Interpreter<Boolean> formButtonBindingInterpreter = new SimpleInterpreter(elem.getNamespaceUri(), "formButtonBinding");
		for(XMLElement child : elem.consumeChildElements(formButtonBindingInterpreter)) {
			String buttonField = child.consumeRequiredRawAttribute("buttonField");
			
			String bindingField = writer.declareField(GxtClassnameConstants.FORMBUTTONBINDING, child);
			// set to null temporarily.  We'll initialize it as a statement. 
			//Need to make sure the FormPanel is initialized first.
			writer.setFieldInitializer(bindingField, "null");
			
			writer.addStatement("%s = new %s(%s);", bindingField, GxtClassnameConstants.FORMBUTTONBINDING, fieldName);
			writer.addStatement("%s.addButton(%s);", bindingField, buttonField);
		}
		
	}

}
