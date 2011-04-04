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
package com.jhickman.web.gwt.gxtuibinder.elementparsers;

import com.google.gwt.uibinder.elementparsers.HasWidgetsParser;

/**
 * Provides parser for GXT Container widgets.
 * 
 * <p>Direct extension of HasWidgetsParser.  The only difference
 * between HasWidgets and Container add method is that
 * the Container.add() method returns boolean whereas 
 * HasWidgets.add() has a void return type.
 * 
 * @author hickman
 */
public class ContainerParser extends HasWidgetsParser {
}
