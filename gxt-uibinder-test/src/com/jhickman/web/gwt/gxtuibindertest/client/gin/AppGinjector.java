/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.jhickman.web.gwt.gxtuibindertest.client.AppController;
import com.jhickman.web.gwt.gxtuibindertest.client.view.AbsoluteLayoutView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.ButtonsView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.OverviewView;
import com.jhickman.web.gwt.gxtuibindertest.client.view.View;

/**
 * @author hickman
 *
 */
@GinModules(AppModule.class)
public interface AppGinjector extends Ginjector {
	AppController getAppController();
	
	OverviewView getOverviewView();

	/**
	 * @return
	 */
	ButtonsView getButtonsView();

	/**
	 * @return
	 */
	AbsoluteLayoutView getAbsoluteLayoutView();
}
