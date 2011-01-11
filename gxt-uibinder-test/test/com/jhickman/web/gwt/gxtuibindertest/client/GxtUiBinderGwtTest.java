/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.jhickman.web.gwt.gxtuibindertest.client.factory.AppFactory;


/**
 * @author hickman
 *
 */
public class GxtUiBinderGwtTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.jhickman.web.gwt.gxtuibindertest.GxtUiBinderTest";
	}

	private AppFactory factory;
	protected AppFactory getAppFactory() {
		if (factory == null) {
			factory = new AppFactory();
		}
		return factory;
	}
}
