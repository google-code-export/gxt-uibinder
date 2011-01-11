/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteData;
import com.extjs.gxt.ui.client.widget.layout.AbsoluteLayout;
import com.jhickman.web.gwt.gxtuibindertest.client.GxtUiBinderGwtTest;
import com.jhickman.web.gwt.gxtuibindertest.client.view.layout.AbsoluteLayoutView;


/**
 * @author hickman
 *
 */
public class AbsoluteLayoutViewTest extends GxtUiBinderGwtTest {

	public void testAbsoluteLayout() {
		AbsoluteLayoutView absoluteLayoutViewImpl = new AbsoluteLayoutView();	
		
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
