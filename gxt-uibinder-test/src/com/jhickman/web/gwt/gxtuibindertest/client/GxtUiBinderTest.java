/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.google.gwt.core.client.EntryPoint;
import com.jhickman.web.gwt.gxtuibindertest.client.factory.AppFactory;

/**
 * @author hickman
 *
 */
public class GxtUiBinderTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		AppFactory factory = AppFactory.INSTANCE;
		factory.getAppController().go();
	}
}
