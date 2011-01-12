/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import java.util.List;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.jhickman.web.gwt.gxtuibindertest.client.ExamplesModel;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Folder;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Navigation;

/**
 * @author hickman
 *
 */
public class GxtUiBinderTestShell extends Composite {
	
	static interface Binder extends UiBinder<Component, GxtUiBinderTestShell> {}
	static Binder BINDER = GWT.create(Binder.class);
	
	@UiField ContentPanel navigationContainer;
	@UiField LayoutContainer contentContainer;
	
	public GxtUiBinderTestShell(EventBus eventBus, final PlaceController placeController) {
		
		initComponent(BINDER.createAndBindUi(this));
		
		
		final Folder model = ExamplesModel.getNavigationModel();
		
		TreeStore<ModelData> store = new TreeStore<ModelData>();
		store.add(model.getChildren(), true);

		
		final TreePanel<ModelData> tree = new TreePanel<ModelData>(store);
		tree.setWidth(300);
		tree.setDisplayProperty("name");
		tree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tree.getStyle().setLeafIcon(IconHelper.createStyle("icon-list"));
		tree.setAutoLoad(true);
		
		
		tree.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>() {
			public void handleEvent(TreePanelEvent<ModelData> event) {
				if (event.getItem() instanceof Navigation) {
					Navigation navigation = (Navigation) event.getItem();
					placeController.goTo(navigation.getPlace());
				}
			}
		});
		
		navigationContainer.add(tree);
		
		eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler(){
			public void onPlaceChange(PlaceChangeEvent event) {
				Place newPlace = event.getNewPlace();
				Navigation navigation = new Navigation("", newPlace, null);
				List<ModelData> currentSelection = tree.getSelectionModel().getSelection();
				if (currentSelection.isEmpty() || ! currentSelection.get(0).equals(navigation)) {
					tree.getSelectionModel().select(navigation, false);	
				}
			};
		});
	}

	/**
	 * @return the contentContainer
	 */
	public LayoutContainer getContentContainer() {
		return contentContainer;
	}
	
	
	public AcceptsOneWidget getDisplay() {
		return new ViewArea(contentContainer);
	}
	

	private static final class ViewArea implements AcceptsOneWidget {
		private final LayoutContainer container;
		public ViewArea(LayoutContainer container) {
			this.container = container;
		}
		public void setWidget(IsWidget w) {
			container.removeAll();
			if (w != null) {
				container.add(w.asWidget());
			}
			container.layout(true);
		}
	}

}
