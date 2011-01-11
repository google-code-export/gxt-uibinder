/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.jhickman.web.gwt.gxtuibindertest.client.view.button.ButtonsViewTest;
import com.jhickman.web.gwt.gxtuibindertest.client.view.layout.AbsoluteLayoutViewTest;

/**
 * @author hickman
 *
 */
public class GxtUiBinderTestSuite extends GWTTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gxt-uibinder.");
		
		suite.addTestSuite(AbsoluteLayoutViewTest.class);
		suite.addTestSuite(ButtonsViewTest.class);
		
		return suite;
	}
}
