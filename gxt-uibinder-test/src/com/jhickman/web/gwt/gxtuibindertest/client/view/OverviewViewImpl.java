/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import java.util.Iterator;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.ListView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.factory.AppFactory;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Navigation;

/**
 * @author hickman
 *
 */
public class OverviewViewImpl extends Composite implements View {

	public OverviewViewImpl() {
		List<Navigation> navigationItems = ExamplesModel.getNavigationItems();

		for (Iterator<Navigation> iterator = navigationItems.iterator(); iterator.hasNext();) {
			Navigation next = iterator.next();
			if ("Overview".equals(next.getName())) {
				iterator.remove();
			}
		}

		ListStore<ModelData> store = new ListStore<ModelData>();
		store.add(navigationItems);
		
	    StringBuffer sb = new StringBuffer();
	    sb.append("<tpl for=\".\">");
	    sb.append("<div class='sample-box' style='padding-top: 4px'>");
	    sb.append("<div class='thumbd'>{image}</div>");
	    sb.append("<div>{name}</div>");
	    sb.append("</div></tpl>");

		
		final ListView<ModelData> dataView = new ListView<ModelData>();
		dataView.addStyleName("overview-page");
	    dataView.setItemSelector(".sample-box");
	    dataView.setOverStyle("sample-over");
	    dataView.setSelectStyle("none");
	    dataView.setBorders(false);
	    dataView.setStore(store);
	    dataView.setTemplate(sb.toString());
	    dataView.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ModelData>() {
	    
	      @Override
	      public void selectionChanged(SelectionChangedEvent<ModelData> se) {
	        if (se.getSelectedItem() != null) {
	          ModelData record = se.getSelectedItem();
	          Navigation navigation = (Navigation) record;
	          dataView.getSelectionModel().deselectAll();
	          
	          AppFactory.INSTANCE.getPlaceController().goTo(navigation.getPlace());
	        }
	      }
	    });

		
		
		LayoutContainer container = new LayoutContainer();
		container.setScrollMode(Scroll.AUTO);
		container.add(dataView);
		
		initComponent(container);
	}
}
