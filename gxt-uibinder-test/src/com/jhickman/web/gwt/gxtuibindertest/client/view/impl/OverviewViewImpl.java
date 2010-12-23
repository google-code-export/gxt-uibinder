/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.impl;

import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Label;

import com.jhickman.web.gwt.gxtuibindertest.client.view.OverviewView;

/**
 * @author hickman
 *
 */
public class OverviewViewImpl extends Composite implements OverviewView {

	public OverviewViewImpl() {
		initComponent(new Label("test"));
	}
}
