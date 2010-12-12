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
