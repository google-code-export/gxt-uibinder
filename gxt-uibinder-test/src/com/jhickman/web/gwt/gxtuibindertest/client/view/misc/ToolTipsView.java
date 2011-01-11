/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.misc;

import com.extjs.gxt.ui.client.core.Template;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class ToolTipsView extends Composite implements View {

	static interface Binder extends UiBinder<Component, ToolTipsView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	public ToolTipsView() {
		initComponent(BINDER.createAndBindUi(this));
	}
	
	
	@UiFactory
	public Template getTemplate() {
		return new Template(getTemplateText());
	}
	
	private native String getTemplateText() /*-{
    	var html = [ 
    		'<div><ul style="list-style: disc; margin: 0px 0px 5px 15px">', 
    		'<li>5 bedrooms</li>', 
    		'<li>2 baths</li>', 
    		'<li>Large backyard</li>', 
    		'<li>Close to metro</li>', 
    		'</ul>', 
    		'<img width="400" height="300" src="../../samples/images/examples/house.jpg" style="border: 1px solid #ddd">', 
    		'</div>' 
    	]; 
    	return html.join(""); 
	}-*/;
}
