/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.jhickman.web.gwt.gxtuibindertest.client.gin.AppGinjector;

/**
 * @author hickman
 *
 */
public class GxtUiBinderTest implements EntryPoint {

	@Override
	public void onModuleLoad() {
		AppGinjector ginjector = GWT.create(AppGinjector.class);
		ginjector.getAppController().go();
	}
}
