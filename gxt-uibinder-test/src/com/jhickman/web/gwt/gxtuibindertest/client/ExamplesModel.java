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
import com.jhickman.web.gwt.gxtuibindertest.client.model.Stock;
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
				new Folder("Grids",
						new Navigation[] {
						new Navigation("Basic Grid", new MyPlace(Token.basicgrid), g.basicgrid().getHTML()),
				}),
				new Folder("Tabs",
						new Navigation[] {
						new Navigation("Basic Tabs", new MyPlace(Token.basictabs), g.basictabs().getHTML()),
						
				}),
				new Folder("Layouts",
						new Navigation[] {
						//new Navigation("AbsoluteLayout", new MyPlace(Token.absolutelayout), g.centerlayout().getHTML()),
						new Navigation("BorderLayout", new MyPlace(Token.borderlayout), g.borderlayout().getHTML()),
						new Navigation("CardLayout", new MyPlace(Token.cardlayout), g.cardlayout().getHTML()),
						new Navigation("CenterLayout", new MyPlace(Token.centerlayout), g.centerlayout().getHTML()),
						new Navigation("RowLayout", new MyPlace(Token.rowlayout), g.rowlayout().getHTML()),
				}),
				new Folder("Forms",
						new Navigation[] {
						new Navigation("Forms", new MyPlace(Token.forms), g.forms().getHTML()),
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
	
	
	
	public static List<Stock> getStocks() {
		List<Stock> stocks = new ArrayList<Stock>();

		stocks.add(new Stock("Apple Inc.", "AAPL", 125.64, 123.43));
		stocks.add(new Stock("Cisco Systems, Inc.", "CSCO", 25.84, 26.3));
		stocks.add(new Stock("Google Inc.", "GOOG", 516.2, 512.6));
		stocks.add(new Stock("Intel Corporation", "INTC", 21.36, 21.53));
		stocks.add(new Stock("Level 3 Communications, Inc.", "LVLT", 5.55, 5.54));
		stocks.add(new Stock("Microsoft Corporation", "MSFT", 29.56, 29.72));
		stocks.add(new Stock("Nokia Corporation (ADR)", "NOK", 27.83, 27.93));
		stocks.add(new Stock("Oracle Corporation", "ORCL", 18.73, 18.98));
		stocks.add(new Stock("Starbucks Corporation", "SBUX", 27.33, 27.36));
		stocks.add(new Stock("Yahoo! Inc.", "YHOO", 26.97, 27.29));
		stocks.add(new Stock("Applied Materials, Inc.", "AMAT", 18.4, 18.66));
		stocks.add(new Stock("Comcast Corporation", "CMCSA", 25.9, 26.4));
		stocks.add(new Stock("Sirius Satellite", "SIRI", 2.77, 2.74));
		
		stocks.add(new Stock("Tellabs, Inc.", "TLAB", 10.64, 10.75));
		stocks.add(new Stock("eBay Inc.", "EBAY", 30.43, 31.21));
		stocks.add(new Stock("Broadcom Corporation", "BRCM", 30.88, 30.48));
		stocks.add(new Stock("CMGI Inc.", "CMGI", 2.14, 2.13));
		stocks.add(new Stock("Amgen, Inc.", "AMGN", 56.22, 57.02));
		stocks.add(new Stock("Limelight Networks", "LLNW", 23, 22.11));
		stocks.add(new Stock("Amazon.com, Inc.", "AMZN", 72.47, 72.23));
		
		stocks.add(new Stock("E TRADE Financial Corporation", "ETFC", 24.32, 24.58));
		stocks.add(new Stock("AVANIR Pharmaceuticals", "AVNR", 3.7, 3.52));
		stocks.add(new Stock("Gemstar-TV Guide, Inc.", "GMST", 4.41, 4.55));
		stocks.add(new Stock("Akamai Technologies, Inc.", "AKAM", 43.08, 45.32));
		stocks.add(new Stock("Motorola, Inc.", "MOT", 17.74, 17.69));
		stocks.add(new Stock("Advanced Micro Devices, Inc.", "AMD", 13.77, 13.98));
		stocks.add(new Stock("General Electric Company", "GE", 36.8, 36.91));
		stocks.add(new Stock("Texas Instruments Incorporated", "TXN", 35.02, 35.7));
		stocks.add(new Stock("Qwest Communications", "Q", 9.9, 10.03));
		stocks.add(new Stock("Tyco International Ltd.", "TYC", 33.48, 33.26));
		stocks.add(new Stock("Pfizer Inc.", "PFE", 26.21, 26.19));
		stocks.add(new Stock("Time Warner Inc.", "TWX", 20.3, 20.45));
		stocks.add(new Stock("Sprint Nextel Corporation", "S", 21.85, 21.76));
		stocks.add(new Stock("Bank of America Corporation", "BAC", 49.92, 49.73));
		stocks.add(new Stock("Taiwan Semiconductor", "TSM", 10.4, 10.52));
		stocks.add(new Stock("AT&T Inc.", "T", 39.7, 39.66));
		stocks.add(new Stock("United States Steel Corporation", "X", 115.81, 114.62));
		stocks.add(new Stock("Exxon Mobil Corporation", "XOM", 81.77, 81.86));
		stocks.add(new Stock("Valero Energy Corporation", "VLO", 72.46, 72.6));
		stocks.add(new Stock("Micron Technology, Inc.", "MU", 12.02, 12.27));
		stocks.add(new Stock("Verizon Communications Inc.", "VZ", 42.5, 42.61));
		stocks.add(new Stock("Avaya Inc.", "AV", 16.96, 16.96));
		stocks.add(new Stock("The Home Depot, Inc.", "HD", 37.66, 37.79));
		
		stocks.add(new Stock("First Data Corporation", "FDC", 32.7, 32.65));
		return stocks;
	}
}
