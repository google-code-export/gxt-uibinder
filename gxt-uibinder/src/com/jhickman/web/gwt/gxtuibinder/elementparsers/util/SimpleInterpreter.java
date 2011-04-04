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
package com.jhickman.web.gwt.gxtuibinder.elementparsers.util;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.uibinder.rebind.XMLElement;
import com.google.gwt.uibinder.rebind.XMLElement.Interpreter;

/**
 * @author hickman
 *
 */
public class SimpleInterpreter implements Interpreter<Boolean> {

	private final String namespaceUri;
	private final String localName;

	public SimpleInterpreter(String namespaceUri, String localName) {
		this.namespaceUri = namespaceUri;
		this.localName = localName;
	}
	
	@Override
	public Boolean interpretElement(XMLElement elem) throws UnableToCompleteException {
		return (namespaceUri != null && namespaceUri.equals(elem.getNamespaceUri())) 
				&& (localName != null && localName.equals(elem.getLocalName()));
	}
}
