/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.view.work;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.jhickman.web.gwt.gxtuibindertest.client.model.Music;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
public class WorkInProgressView extends Composite implements View {

	static interface Binder extends UiBinder<Component, WorkInProgressView> {}
	private static Binder BINDER = GWT.create(Binder.class);
	
	
	
	public WorkInProgressView() {
		initComponent(BINDER.createAndBindUi(this));
	}

	
	public static final class Issue20Constants {
		public String before() {
			return "before";
		}
		public String after() {
			return "after";
		}
		
		public Person item1() {
			return new Person("Beethoven");
		}
		
		public Person item2() {
			return new Person("Brahms");
		}

		public Person item3() {
			return new Person("Mozart");
		}

	}
	
	public static final class Person {
		private String name;
		
		public Person(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
}
