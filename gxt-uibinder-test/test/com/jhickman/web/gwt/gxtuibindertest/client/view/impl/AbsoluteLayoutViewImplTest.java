/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.impl;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.jhickman.web.gwt.gxtuibindertest.client.GxtUiBinderGwtTest;


/**
 * @author hickman
 *
 */
public class AbsoluteLayoutViewImplTest extends GxtUiBinderGwtTest {

	public void testAbsoluteLayout() {
		AbsoluteLayoutViewImpl absoluteLayoutViewImpl = new AbsoluteLayoutViewImpl();	
		
		assertNotNull(absoluteLayoutViewImpl.item1);

		LayoutContainer container = absoluteLayoutViewImpl.item1;
		assertTrue(container.getLayout() instanceof AbsoluteLayout);
		
		Component item = container.getItem(0);
		Object layoutData = item.getData("layoutData");
		assertTrue(layoutData instanceof AbsoluteData);
		assertEquals(67, ((AbsoluteData)layoutData).getLeft());
		assertEquals(101, ((AbsoluteData)layoutData).getTop());
	}
}
