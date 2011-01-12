/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.TabPanel.TabPosition;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

/**
 * @author hickman
 *
 */
public class DemoView extends Composite implements View {

	private final View demoTabContentView;

	public DemoView(View demoView, SourceView sourceView) {
		this.demoTabContentView = demoView;
		TabPanel tabPanel = new TabPanel();
		
		TabItem demo = new TabItem("Demo");
		demo.setLayout(new FitLayout());
		demo.add(demoView.asWidget());
		
		TabItem source = new TabItem("Source");
		//source.setUrl(GWT.getModuleBaseURL() + "/view/button/ButtonsView.java");
		source.setLayout(new FitLayout());
		source.add(sourceView.asWidget());
		
		
		tabPanel.add(demo);
		tabPanel.add(source);
		tabPanel.setTabPosition(TabPosition.BOTTOM);
		
		initComponent(tabPanel);
	}
}
