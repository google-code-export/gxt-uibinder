/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.inject.ImplementedBy;
import com.jhickman.web.gwt.gxtuibindertest.client.view.impl.AbsoluteLayoutViewImpl;

/**
 * @author hickman
 *
 */
@ImplementedBy(AbsoluteLayoutViewImpl.class)
public interface AbsoluteLayoutView extends View {

	public abstract LayoutContainer getItem1();

}
