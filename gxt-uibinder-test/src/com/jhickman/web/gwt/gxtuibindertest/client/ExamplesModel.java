/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Folder;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Navigation;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace;
import com.jhickman.web.gwt.gxtuibindertest.client.place.MyPlace.Token;
import com.jhickman.web.gwt.gxtuibindertest.client.resources.Resources;
import com.jhickman.web.gwt.gxtuibindertest.client.resources.images.ExampleImages;

/**
 * @author hickman
 *
 */
public class ExamplesModel {
	
	private static Folder rootFolder;
	private static List<Navigation> navigations = new ArrayList<Navigation>();
	
	static {
		ExampleImages g = Resources.IMAGES;
		
		ModelData[] items = new ModelData[] {
				new Navigation("Overview", new MyPlace(Token.overview), null),
				new Folder("Tabs",
						new Navigation[] {
						new Navigation("Basic Tabs", new MyPlace(Token.basictabs), g.basictabs().getHTML()),
						
				}),
				new Folder("Layouts",
						new Navigation[] {
						new Navigation("AbsoluteLayout", new MyPlace(Token.absolutelayout), g.centerlayout().getHTML()),
						new Navigation("BorderLayout", new MyPlace(Token.borderlayout), g.borderlayout().getHTML()),
				}),
				new Folder("ToolBar & Menus",
						new Navigation[] {
						new Navigation("MenuBar", new MyPlace(Token.menubar), g.menubar().getHTML()),
				}),
				new Folder("Button", 
						new Navigation[] {
						new Navigation("Buttons", new MyPlace(Token.buttons), g.buttons().getHTML()),
						new Navigation("Button Aligning", new MyPlace(Token.buttonaligning), g.buttonaligning().getHTML()),
				}),
				new Folder("Misc",
						new Navigation[] {
						new Navigation("ToolTips", new MyPlace(Token.tooltips), g.tooltips().getHTML()),
						new Navigation("DatePicker", new MyPlace(Token.datepicker), g.datepicker().getHTML()),
						new Navigation("Slider", new MyPlace(Token.slider), g.slider().getHTML()),
				}),
		};
		
		rootFolder = new Folder("root");
		for (ModelData navigation : items) {
			rootFolder.add(navigation);
		}
		
		loadNavigations(rootFolder);
	}
	
	
	public static Folder getNavigationModel() {
		return rootFolder;
	}
	
	public static List<Navigation> getNavigationItems() {
		return new ArrayList<Navigation>(navigations);
	}
	
	private static void loadNavigations(TreeModel model) {
		for(ModelData child : model.getChildren()) {
			if (child instanceof Navigation) {
				navigations.add((Navigation) child);
			} else if (child instanceof Folder) {
				loadNavigations((Folder) child);
			}
		}
	}
}
