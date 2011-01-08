/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.jhickman.web.gwt.gxtuibindertest.client.gin.AppGinjector;


/**
 * @author hickman
 *
 */
public class GxtUiBinderGwtTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.jhickman.web.gwt.gxtuibindertest.GxtUiBinderTest";
	}

	private AppGinjector ginjector;
	
	
	protected AppGinjector getAppGinjector() {
		if (ginjector == null) {
			ginjector = GWT.create(AppGinjector.class);
		}
		return ginjector;
	}
}
