/*
* Activator.java
*
* This file is part of the STS-Tool project.
* Copyright (c) 2011-2012 "University of Trento - DISI" All rights reserved.
*
* Is strictly forbidden to remove this copyright notice from this source code.
*
* Disclaimer of Warranty:
* STS-Tool (this software) is provided "as-is" and without warranty of any kind, 
* express, implied or otherwise, including without limitation, any warranty of 
* merchantability or fitness for a particular purpose.
* In no event shall the copyright holder or contributors be liable for any direct,
* indirect, incidental, special, exemplary, or consequential damages
* including, but not limited to, procurement of substitute goods or services;
* loss of use, data, or profits; or business interruption) however caused and on
* any theory of liability, whether in contract, strict liability, or tort (including
* negligence or otherwise) arising in any way out of the use of this software, even 
* if advised of the possibility of such damage.
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License version 3
* as published by the Free Software Foundation with the addition of the
* following permission added to Section 15 as permitted in Section 7(a):
* FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY 
* "University of Trento - DISI","University of Trento - DISI" DISCLAIMS THE
* WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
*
* See the GNU Affero General Public License for more details.
* You should have received a copy of the GNU Affero General Public License
* along with this program; if not, see http://www.gnu.org/licenses or write to
* the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
* Boston, MA, 02110-1301 USA, or download the license from the following URL:
* http://www.sts-tool.eu/License.php
*
* For more information, please contact STS-Tool group at this
* address: ststool@disi.unitn.it
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
