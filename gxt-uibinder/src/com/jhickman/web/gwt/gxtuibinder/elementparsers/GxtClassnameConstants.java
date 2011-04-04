/**
 * Copyright 2011 hickman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * 
 */
package com.jhickman.web.gwt.gxtuibinder.elementparsers;



/**
 * Provides class names of all used GXT components.  This avoids GXT dependencies.
 * 
 * @author hickman
 */
public interface GxtClassnameConstants {
	
	public static final String GXT_BASE_PACKAGE = "com.extjs.gxt.ui.client.";
	
	public static final String COMPONENT = GXT_BASE_PACKAGE + "widget.Component";
	
	
	public static final String LAYOUTREGION =GXT_BASE_PACKAGE + "Style.LayoutRegion";
	public static final String MARGINS = GXT_BASE_PACKAGE + "util.Margins";
	public static final String PADDING = GXT_BASE_PACKAGE + "util.Padding";
	public static final String LAYOUTCONTAINER = GXT_BASE_PACKAGE + "widget.LayoutContainer";
	public static final String FORMPANEL = GXT_BASE_PACKAGE + "widget.form.FormPanel";
	
	public static final String FORMBUTTONBINDING = GXT_BASE_PACKAGE + "widget.form.FormButtonBinding";
	
	
	public static final String LAYOUT_BASE_PACKAGE = GXT_BASE_PACKAGE + "widget.layout.";
	
	public static final String ACCORDIONLAYOUT= LAYOUT_BASE_PACKAGE + "AccordionLayout";
	public static final String BORDERLAYOUT = LAYOUT_BASE_PACKAGE + "BorderLayout";
	public static final String FITLAYOUT = LAYOUT_BASE_PACKAGE + "FitLayout";
	public static final String FLOWLAYOUT = LAYOUT_BASE_PACKAGE + "FlowLayout";
	public static final String CARDLAYOUT = LAYOUT_BASE_PACKAGE + "CardLayout";
	public static final String CENTERLAYOUT = LAYOUT_BASE_PACKAGE + "CenterLayout";
	public static final String ROWLAYOUT = LAYOUT_BASE_PACKAGE + "RowLayout";
	public static final String VBOXLAYOUT= LAYOUT_BASE_PACKAGE + "VBoxLayout";
	public static final String HBOXLAYOUT= LAYOUT_BASE_PACKAGE + "HBoxLayout";
	public static final String FORMLAYOUT = LAYOUT_BASE_PACKAGE + "FormLayout";
	public static final String ABSOLUTELAYOUT = LAYOUT_BASE_PACKAGE + "AbsoluteLayout";
	public static final String FILLLAYOUT = LAYOUT_BASE_PACKAGE + "FillLayout";
	
	public static final String BORDERLAYOUTDATA = LAYOUT_BASE_PACKAGE + "BorderLayoutData";	
	public static final String ABSOLUTEDATA = LAYOUT_BASE_PACKAGE + "AbsoluteData";
	public static final String MARGINDATA = LAYOUT_BASE_PACKAGE + "MarginData";
	public static final String ROWDATA = LAYOUT_BASE_PACKAGE + "RowData";
	public static final String FLOWDATA = LAYOUT_BASE_PACKAGE + "FlowData";
	public static final String FILLDATA = LAYOUT_BASE_PACKAGE + "FillData";
	public static final String FORMDATA = LAYOUT_BASE_PACKAGE + "FormData";
	public static final String HBOXLAYOUTDATA = LAYOUT_BASE_PACKAGE + "HBoxLayoutData";
	public static final String VBOXLAYOUTDATA = LAYOUT_BASE_PACKAGE + "VBoxLayoutData";
	
	public static final String MENU = GXT_BASE_PACKAGE + "widget.menu.Menu";
	
	public static final String BUTTON = GXT_BASE_PACKAGE + "widget.button.Button";
	
	
	public static final String EVENTTYPE = GXT_BASE_PACKAGE + "event.EventType";
	public static final String EVENTS = GXT_BASE_PACKAGE + "event.Events";
	public static final String LISTENER = GXT_BASE_PACKAGE + "event.Listener";

	public static final String TREESTORE = GXT_BASE_PACKAGE + "store.TreeStore";

	public static final String LISTSTORE = GXT_BASE_PACKAGE + "store.ListStore";
	public static final String COLUMNMODEL = GXT_BASE_PACKAGE + "widget.grid.ColumnModel";
	public static final String COLUMNCONFIG = GXT_BASE_PACKAGE + "widget.grid.ColumnConfig";

	public static final String TOOLTIPCONFIG = GXT_BASE_PACKAGE + "widget.tips.ToolTipConfig";


	public static final String STYLEORIENTATION = GXT_BASE_PACKAGE + "Style.Orientation";


	public static final String TREEPANEL = GXT_BASE_PACKAGE + "widget.treepanel.TreePanel";

	public static final String HEADER = GXT_BASE_PACKAGE + "widget.Header";
}
