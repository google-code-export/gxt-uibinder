/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import com.extjs.gxt.ui.client.data.ModelData;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Folder;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Navigation;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace.Token;

/**
 * @author hickman
 *
 */
public class TestData {

	public static Folder getNavigationModel() {
		ModelData[] items = new ModelData[] {
				new Navigation("Overview", new MyPlace(Token.overview)),
				new Folder("Tabs",
						new Navigation[] {
						new Navigation("Basic Tabs", new MyPlace(Token.basictabs))
						
				}),
				new Folder("Layouts",
						new Navigation[] {
						new Navigation("AbsoluteLayout", new MyPlace(Token.absolutelayout)),
						new Navigation("BorderLayout", new MyPlace(Token.borderlayout))
				}),
				new Folder("Button", 
						new Navigation[] {
						new Navigation("Buttons", new MyPlace(Token.buttons))
				}),
				new Folder("Misc",
						new Navigation[] {
						new Navigation("ToolTips", new MyPlace(Token.tooltips)),
						new Navigation("DatePicker", new MyPlace(Token.datepicker))
				}),
		};
		
		Folder root = new Folder("root");
		for (ModelData navigation : items) {
			root.add(navigation);
		}
		return root;
	}
}
