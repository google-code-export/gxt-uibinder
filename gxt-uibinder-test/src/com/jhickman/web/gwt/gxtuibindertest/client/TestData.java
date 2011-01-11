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
				new Folder("Layouts",
						new Navigation[] {
						new Navigation("AbsoluteLayout", new MyPlace(Token.absolutelayout)),
						new Navigation("BorderLayout", new MyPlace(Token.borderlayout))
				}),
				new Folder("Button", 
						new Navigation[] {
						new Navigation("Buttons", new MyPlace(Token.buttons))
				})
		};
		
		Folder root = new Folder("root");
		for (ModelData navigation : items) {
			root.add(navigation);
		}
		return root;
	}
}
