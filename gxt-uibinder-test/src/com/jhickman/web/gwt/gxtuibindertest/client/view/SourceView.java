/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author hickman
 *
 */
public class SourceView extends Composite implements View {
	
	static interface Binder extends UiBinder<Component, SourceView> {}
	private static final Binder BINDER = GWT.create(Binder.class);
	
	@UiField LayoutContainer uiBinderSourceContainer;
	@UiField LayoutContainer javaSourceContainer;

	public SourceView(Class<?> viewClass) {
		initComponent(BINDER.createAndBindUi(this));
		
		
		
		
		String className = viewClass.getName();
		String[] parts = className.split("\\.");
		
		String category = parts[parts.length-2];
		String file = parts[parts.length-1];
		
		String javaSourceUrlString = GWT.getModuleBaseURL() + "view/" + category + "/" + file + ".java";
		String uiBinderSourceUrlString = GWT.getModuleBaseURL() + "view/" + category + "/" + file + ".ui.xml";		
		
		getSource(javaSourceUrlString, "java", javaSourceContainer);
		getSource(uiBinderSourceUrlString, "xml", uiBinderSourceContainer);
	}
	
	
	private void getSource(String url, final String language, final LayoutContainer container) {
		try {
			new RequestBuilder(RequestBuilder.GET, url).sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					SafeHtml safeHtml = new SafeHtmlBuilder()
						.appendHtmlConstant("<pre class='brush: " + language + "; tab-size: 2; toolbar: false; class-name: \"no_y_scroll\"'>")
						.appendEscaped(response.getText())
						.appendHtmlConstant("</pre>")
						.toSafeHtml();
					
					
					HTML content = new HTML(safeHtml);
					container.add(content);
					container.addListener(Events.AfterLayout, new Listener<ComponentEvent>() {
						@Override
						public void handleEvent(ComponentEvent be) {
							syntaxHighlight();
						}
					});
					syntaxHighlight();
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					GWT.log("error with RequestBuilder call", exception);
					container.add(new Label("Unable to retrive source"));
				}
			});
		} catch (RequestException e) {
			GWT.log("error with RequestBuilder call", e);
			container.add(new Label("Unable to retrive source"));
		}
		
	}
	
	private native void syntaxHighlight() /*-{
		$wnd.SyntaxHighlighter.highlight();
	}-*/; 
}
