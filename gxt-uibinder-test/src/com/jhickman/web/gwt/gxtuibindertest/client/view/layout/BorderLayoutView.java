/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.layout;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class BorderLayoutView extends Composite implements View {
	
	static interface Binder extends UiBinder<Component, BorderLayoutView> {}
	private static Binder UIBINDER = GWT.create(Binder.class);
	
	@UiField LayoutContainer container;
	@UiField FlexTable table;
	
	public BorderLayoutView() {
		initComponent(UIBINDER.createAndBindUi(this));
		
		final BorderLayout layout = (BorderLayout) container.getLayout();
	    for (int i = 0; i < LayoutRegion.values().length; i++) {  
	      final LayoutRegion r = LayoutRegion.values()[i];  
	      if (r == LayoutRegion.CENTER) {  
	        continue;  
	      }  
	      SelectionListener<ButtonEvent> sl = new SelectionListener<ButtonEvent>() {  
	  
	        @Override  
	        public void componentSelected(ButtonEvent ce) {  
	          String txt = ce.getButton().getText();  
	          if (txt.equals("Expand")) {  
	            layout.expand(r);  
	          } else if (txt.equals("Collapse")) {  
	            layout.collapse(r);  
	          } else if (txt.equals("Show")) {  
	            layout.show(r);  
	          } else {  
	            layout.hide(r);  
	          }  
	  
	        }  
	      };  
	      table.setHTML(i, 0, "<div style='font-size: 12px; width: 100px'>" + r.name() + ":</span>");  
	      table.setWidget(i, 1, new Button("Expand", sl));  
	      table.setWidget(i, 2, new Button("Collapse", sl));  
	      table.setWidget(i, 3, new Button("Show", sl));  
	      table.setWidget(i, 4, new Button("Hide", sl));  
	    }  
	}
}
