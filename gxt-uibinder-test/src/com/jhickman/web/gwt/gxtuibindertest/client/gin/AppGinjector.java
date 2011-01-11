/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.jhickman.web.gwt.gxtuibindertest.client.AppController;

/**
 * @author hickman
 *
 */
@GinModules(AppModule.class)
public interface AppGinjector extends Ginjector {
	AppController getAppController();
}
