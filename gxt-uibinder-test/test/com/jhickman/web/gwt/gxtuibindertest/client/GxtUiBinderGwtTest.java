/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.jhickman.web.gwt.gxtuibindertest.client.gin.AppGinjector;
import com.jhickman.web.gwt.gxtuibindertest.client.view.AbsoluteLayoutView;


/**
 * @author hickman
 *
 */
public class GxtUiBinderGwtTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.jhickman.web.gwt.gxtuibindertest.GxtUiBinderTest";
	}
	
	public void testSomething() {
		AppGinjector ginjector = GWT.create(AppGinjector.class);
		
		AbsoluteLayoutView absoluteLayoutView = ginjector.getAbsoluteLayoutView();
		
		assertTrue(absoluteLayoutView.getItem1().getLayout() instanceof AbsoluteLayout);
	}

}
