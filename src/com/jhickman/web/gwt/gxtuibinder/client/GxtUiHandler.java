/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hickman
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GxtUiHandler {
    String[] uiField();
    GxtEvent eventType();
}
