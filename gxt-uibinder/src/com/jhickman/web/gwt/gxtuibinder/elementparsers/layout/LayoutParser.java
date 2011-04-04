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
package com.jhickman.web.gwt.gxtuibinder.elementparsers.layout;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.uibinder.elementparsers.ElementParser;
import com.google.gwt.uibinder.rebind.UiBinderWriter;
import com.google.gwt.uibinder.rebind.XMLElement;

/**
 * @author hickman
 */
public interface LayoutParser extends ElementParser {

	/**
	 * @param layoutElem
	 * @param elem
	 * @param layoutContainerFieldName
	 * @param type
	 * @param writer
	 * @throws UnableToCompleteException
	 */
	void parse(XMLElement layoutElem, XMLElement elem,
			String layoutContainerFieldName, JClassType type,
			UiBinderWriter writer) throws UnableToCompleteException;

}
