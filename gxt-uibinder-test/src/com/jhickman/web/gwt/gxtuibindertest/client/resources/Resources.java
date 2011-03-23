/**
 * 
 */
package com.jhickman.web.gwt.gxtuibindertest.client.resources;

import com.google.gwt.core.client.GWT;
import com.jhickman.web.gwt.gxtuibindertest.client.resources.icons.ExampleIcons;
import com.jhickman.web.gwt.gxtuibindertest.client.resources.images.ExampleImages;

/**
 * @author hickman
 *
 */
public class Resources {
	public static final ExampleImages IMAGES = GWT.create(ExampleImages.class);
	public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
}
