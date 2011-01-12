/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author hickman
 *
 */
public class SourceView extends Composite implements View {

	public SourceView(Class<?> viewClass) {
		
		String className = viewClass.getName();
		String[] parts = className.split("\\.");
		
		String category = parts[parts.length-2];
		String file = parts[parts.length-1];
		
		String javaSourceUrlString = GWT.getModuleBaseURL() + "view/" + category + "/" + file + ".java";
		String uiBinderSourceUrlString = GWT.getModuleBaseURL() + "view/" + category + "/" + file + ".ui.xml";		
		
		
		
		LayoutContainer outerContainer = new LayoutContainer();
		outerContainer.setLayout(new FlowLayout());
		outerContainer.setScrollMode(Scroll.AUTOY);
		
		
		ContentPanel container = new ContentPanel();
		container.setHeaderVisible(false);
		outerContainer.add(container, new MarginData(10));
		
		container.add(new Label("<h1>UiBinder Source</h1>"));
		final LayoutContainer uiBinderSourceContainer = new LayoutContainer();
		container.add(uiBinderSourceContainer);
		
		container.add(new Label("<br/><br/><h1>Java Source</h1>"));
		final LayoutContainer javaSourceContainer = new LayoutContainer();
		container.add(javaSourceContainer);
		
		initComponent(outerContainer);
		
		
		getSource(javaSourceUrlString, "java", javaSourceContainer);
		getSource(uiBinderSourceUrlString, "xml", uiBinderSourceContainer);
	}
	
	
	private void getSource(String url, final String language, final LayoutContainer container) {
		try {
			new RequestBuilder(RequestBuilder.GET, url).sendRequest(null, new RequestCallback() {
				@Override
				public void onResponseReceived(Request request, Response response) {
					SafeHtml safeHtml = new SafeHtmlBuilder()
						.appendHtmlConstant("<pre class='brush: " + language + "; toolbar: false; class-name: \"no_y_scroll\"'>")
						.appendEscaped(response.getText())
						.appendHtmlConstant("</pre>")
						.toSafeHtml();
					
					
					HTMLPanel content = new HTMLPanel(safeHtml);
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
