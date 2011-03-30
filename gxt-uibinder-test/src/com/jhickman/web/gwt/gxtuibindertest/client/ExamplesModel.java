/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.google.gwt.user.client.Random;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Country;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Folder;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Music;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Navigation;
import com.jhickman.web.gwt.gxtuibindertest.client.model.State;
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
				new Folder("TreePanel",
						new Navigation[] {
						new Navigation("Basic Tree", new MyPlace(Token.basictree), g.basictree().getHTML()),
						new Navigation("Checkbox Tree", new MyPlace(Token.checkboxtree), g.checkboxtree().getHTML()),
				}),
				new Folder("Tabs",
						new Navigation[] {
						new Navigation("Basic Tabs", new MyPlace(Token.basictabs), g.basictabs().getHTML()),
						
				}),
				new Folder("Layouts",
						new Navigation[] {
						//new Navigation("AbsoluteLayout", new MyPlace(Token.absolutelayout), g.centerlayout().getHTML()),
						new Navigation("AccordionLayout", new MyPlace(Token.accordionlayout), g.accordionlayout().getHTML()),
						//new Navigation("AnchorLayout", new MyPlace(Token.anchorlayout), g.anchorlayout().getHTML()),
						new Navigation("BorderLayout", new MyPlace(Token.borderlayout), g.borderlayout().getHTML()),
						new Navigation("CardLayout", new MyPlace(Token.cardlayout), g.cardlayout().getHTML()),
						new Navigation("CenterLayout", new MyPlace(Token.centerlayout), g.centerlayout().getHTML()),
						new Navigation("RowLayout", new MyPlace(Token.rowlayout), g.rowlayout().getHTML()),
						new Navigation("Portal", new MyPlace(Token.portal), g.portal().getHTML()),
						new Navigation("VBoxLayout", new MyPlace(Token.vboxlayout), g.vboxlayout().getHTML()),
						new Navigation("HBoxLayout", new MyPlace(Token.hboxlayout), g.hboxlayout().getHTML()),
				}),
				new Folder("Combos",
						new Navigation[] {
						new Navigation("ComboBox", new MyPlace(Token.combobox), g.combobox().getHTML()),
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

	/**
	 * @return
	 */
	public static Folder getTreeModel() {
		Folder[] folders = new Folder[] {
				new Folder("Beethoven", new Folder[] {

						new Folder("Quartets", new Music[] {
								new Music("Six String Quartets", "Beethoven",
										"Quartets"),
								new Music("Three String Quartets", "Beethoven",
										"Quartets"),
								new Music("Grosse Fugue for String Quartets",
										"Beethoven", "Quartets"), }),
						new Folder("Sonatas", new Music[] {
								new Music("Sonata in A Minor", "Beethoven",
										"Sonatas"),
								new Music("Sonata in F Major", "Beethoven",
										"Sonatas"), }),

						new Folder("Concertos",
								new Music[] {
										new Music("No. 1 - C", "Beethoven",
												"Concertos"),
										new Music("No. 2 - B-Flat Major",
												"Beethoven", "Concertos"),
										new Music("No. 3 - C Minor",
												"Beethoven", "Concertos"),
										new Music("No. 4 - G Major",
												"Beethoven", "Concertos"),
										new Music("No. 5 - E-Flat Major",
												"Beethoven", "Concertos"), }),

						new Folder("Symphonies", new Music[] {
								new Music("No. 1 - C Major", "Beethoven",
										"Symphonies"),
								new Music("No. 2 - D Major", "Beethoven",
										"Symphonies"),
								new Music("No. 3 - E-Flat Major", "Beethoven",
										"Symphonies"),
								new Music("No. 4 - B-Flat Major", "Beethoven",
										"Symphonies"),
								new Music("No. 5 - C Minor", "Beethoven",
										"Symphonies"),
								new Music("No. 6 - F Major", "Beethoven",
										"Symphonies"),
								new Music("No. 7 - A Major", "Beethoven",
										"Symphonies"),
								new Music("No. 8 - F Major", "Beethoven",
										"Symphonies"),
								new Music("No. 9 - D Minor", "Beethoven",
										"Symphonies"), }), }),
				new Folder(
						"Brahms",
						new Folder[] {
								new Folder(
										"Concertos",
										new Music[] {
												new Music("Violin Concerto",
														"Brahms", "Concertos"),
												new Music(
														"Double Concerto - A Minor",
														"Brahms", "Concertos"),
												new Music(
														"Piano Concerto No. 1 - D Minor",
														"Brahms", "Concertos"),
												new Music(
														"Piano Concerto No. 2 - B-Flat Major",
														"Brahms", "Concertos"), }),
								new Folder(
										"Quartets",
										new Music[] {
												new Music(
														"Piano Quartet No. 1 - G Minor",
														"Brahms", "Quartets"),
												new Music(
														"Piano Quartet No. 2 - A Major",
														"Brahms", "Quartets"),
												new Music(
														"Piano Quartet No. 3 - C Minor",
														"Brahms", "Quartets"),
												new Music(
														"String Quartet No. 3 - B-Flat Minor",
														"Brahms", "Quartets"), }),
								new Folder(
										"Sonatas",
										new Music[] {
												new Music(
														"Two Sonatas for Clarinet - F Minor",
														"Brahms", "Sonatas"),
												new Music(
														"Two Sonatas for Clarinet - E-Flat Major",
														"Brahms", "Sonatas"), }),
								new Folder("Symphonies", new Music[] {
										new Music("No. 1 - C Minor", "Brahms",
												"Symphonies"),
										new Music("No. 2 - D Minor", "Brahms",
												"Symphonies"),
										new Music("No. 3 - F Major", "Brahms",
												"Symphonies"),
										new Music("No. 4 - E Minor", "Brahms",
												"Symphonies"), }), }),
				new Folder("Mozart", new Folder[] { new Folder("Concertos",
						new Music[] {
								new Music("Piano Concerto No. 12", "Mozart",
										"Concertos"),
								new Music("Piano Concerto No. 17", "Mozart",
										"Concertos"),
								new Music("Clarinet Concerto", "Mozart",
										"Concertos"),
								new Music("Violin Concerto No. 5", "Mozart",
										"Concertos"),
								new Music("Violin Concerto No. 4", "Mozart",
										"Concertos"), }), }), };
		Folder root = new Folder("root");
		for (int i = 0; i < folders.length; i++) {
			root.add((Folder) folders[i]);
		}

		return root;
	}
	
	public static List<State> getStates() {
		List<State> states = new ArrayList<State>();
		states.add(new State("AL", "Alabama", "The Heart of Dixie"));
		states.add(new State("AK", "Alaska", "The Land of the Midnight Sun"));
		states.add(new State("AZ", "Arizona", "The Grand Canyon State"));
		states.add(new State("AR", "Arkansas", "The Natural State"));
		states.add(new State("CA", "California", "The Golden State"));
		states.add(new State("CO", "Colorado", "The Mountain State"));
		states.add(new State("CT", "Connecticut", "The Constitution State"));
		states.add(new State("DE", "Delaware", "The First State"));
		states.add(new State("DC", "District of Columbia", "The Nations Capital"));
		states.add(new State("FL", "Florida", "The Sunshine State"));
		states.add(new State("GA", "Georgia", "The Peach State"));
		states.add(new State("HI", "Hawaii", "The Aloha State"));
		states.add(new State("ID", "Idaho", "Famous Potatoes"));
		states.add(new State("IL", "Illinois", "The Prairie State"));
		states.add(new State("IN", "Indiana", "The Hospitality State"));
		states.add(new State("IA", "Iowa", "The Corn State"));
		states.add(new State("KS", "Kansas", "The Sunflower State"));
		states.add(new State("KY", "Kentucky", "The Bluegrass State"));
		states.add(new State("LA", "Louisiana", "The Bayou State"));
		states.add(new State("ME", "Maine", "The Pine Tree State"));
		states.add(new State("MD", "Maryland", "Chesapeake State"));
		states.add(new State("MA", "Massachusetts", "The Spirit of America"));
		states.add(new State("MI", "Michigan", "Great Lakes State"));
		states.add(new State("MN", "Minnesota", "North Star State"));
		states.add(new State("MS", "Mississippi", "Magnolia State"));
		states.add(new State("MO", "Missouri", "Show Me State"));
		states.add(new State("MT", "Montana", "Big Sky Country"));
		states.add(new State("NE", "Nebraska", "Beef State"));
		states.add(new State("NV", "Nevada", "Silver State"));
		states.add(new State("NH", "New Hampshire", "Granite State"));
		states.add(new State("NJ", "New Jersey", "Garden State"));
		states.add(new State("NM", "New Mexico", "Land of Enchantment"));
		states.add(new State("NY", "New York", "Empire State"));
		states.add(new State("NC", "North Carolina", "First in Freedom"));
		states.add(new State("ND", "North Dakota", "Peace Garden State"));
		states.add(new State("OH", "Ohio", "The Heart of it All"));
		states.add(new State("OK", "Oklahoma", "Oklahoma is OK"));
		states.add(new State("OR", "Oregon", "Pacific Wonderland"));
		states.add(new State("PA", "Pennsylvania", "Keystone State"));
		states.add(new State("RI", "Rhode Island", "Ocean State"));
		states.add(new State("SC", "South Carolina", "Nothing Could be Finer"));
		states.add(new State("SD", "South Dakota", "Great Faces, Great Places"));
		states.add(new State("TN", "Tennessee", "Volunteer State"));
		states.add(new State("TX", "Texas", "Lone Star State"));
		states.add(new State("UT", "Utah", "Salt Lake State"));
		states.add(new State("VT", "Vermont", "Green Mountain State"));
		states.add(new State("VA", "Virginia", "Mother of States"));
		states.add(new State("WA", "Washington", "Green Tree State"));
		states.add(new State("WV", "West Virginia", "Mountain State"));
		states.add(new State("WI", "Wisconsin", "Americas Dairyland"));
		states.add(new State("WY", "Wyoming", "Like No Place on Earth"));
		return states;
	}

	public static List<Country> getCountries() {
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country("ad", "Andora", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("ae", "Arab Emirates", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("ag", "Antigua And Barbuda", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("ai", "Anguilla", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("al", "Albania", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("am", "Armenia", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("an", "Neth. Antilles", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("ao", "Angola", 100 + (Random.nextInt(110) * 100)));
		countries.add(new Country("ar", "Argentina", 100 + (Random.nextInt(110) * 100)));
		
		return countries;
	}
}
