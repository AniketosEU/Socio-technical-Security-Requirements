/*
* Activator.java
* Copyright (C) 2013 SINTEF (http://www.sintef.no)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
* The MIT License (MIT)
* http://opensource.org/licenses/mit-license.php
*
*/
/*
* Activator.java
*/
package eu.aniketos.wp1.ststool.threats;

import java.io.InputStream;
import java.net.URL;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.aniketos.wp1.ststool.threats"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		Bundle bundle = context.getBundle();
		
		try {
			
			// TODO This code block should install the attached bundles ONLY when run for the first time!

			URL jarUrl = bundle.getEntry("lib/threatrepository-interface-0.1-SNAPSHOT.jar");
			InputStream input = jarUrl.openStream();
			Bundle interfaces = context.installBundle(jarUrl.getPath(), input);
			input.close();

			jarUrl = bundle.getEntry("lib/threatrepository-impl-1.0-SNAPSHOT-uber.jar");
			input = jarUrl.openStream();
			Bundle impl = context.installBundle(jarUrl.getPath(), input);
			impl.start();
			input.close();

//			System.out.println(interfaces.getSymbolicName() + ": " + getBundleState(interfaces.getState()));
//			System.out.println(impl.getSymbolicName() + ": " + getBundleState(impl.getState()));
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		super.start(context);
		plugin = this;
	}
	
	
	private String getBundleState(int stateInput) {
		String state;
		switch (stateInput) {
			case Bundle.ACTIVE : state = "ACTIVE"; break;
			case Bundle.INSTALLED : state = "INSTALLED"; break;
			case Bundle.RESOLVED : state = "RESOLVED"; break;
			case Bundle.STARTING : state = "STARTING"; break;
			case Bundle.STOPPING : state = "STOPPING"; break;
			case Bundle.UNINSTALLED : state = "UNINSTALLED"; break;
			default: state = "UNKNOWN";
		}
		return state;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		
		plugin = null;
		super.stop(context);		
	}
	
	

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		
		return plugin;
	}
}
