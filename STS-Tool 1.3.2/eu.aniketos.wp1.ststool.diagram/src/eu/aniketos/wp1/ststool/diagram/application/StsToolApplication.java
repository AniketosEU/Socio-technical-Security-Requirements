/*
* StsToolApplication.java
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
package eu.aniketos.wp1.ststool.diagram.application;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkEvent;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.FrameworkUtil;

/**
 * @generated
 */
public class StsToolApplication implements IApplication {

	/**
	 * @generated NOT
	 */
	public Object start(IApplicationContext context) throws Exception{
		
		BundleContext bundleContext = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		bundleContext.addFrameworkListener(new FrameworkListener() {
			@Override
			public void frameworkEvent(FrameworkEvent e) {
			//System.out.println(e.getBundle().getBundleId()+ " "+getType(e.getType()));
				
			}
		});
		
		PlatformUI.getPreferenceStore().setDefault(IWorkbenchPreferenceConstants.KEY_CONFIGURATION_ID, "eu.aniketos.wp1.ststool.diagram.keybinding.scheme");
		for (Bundle b : bundleContext.getBundles()) {
			if (b.getState() == Bundle.INSTALLED) {
				Platform.getLog(context.getBrandingBundle()).log(new Status(IStatus.WARNING, b.getSymbolicName(), "unresolved plugin \"" + b.getSymbolicName() + "\" : maybe the pugin could not work"));
				System.err.println("Found Unresolved plugin \"" + b.getSymbolicName() + "\" : maybe the pugin could not work");
			}
		}
		return startGen(context);
	}

	@SuppressWarnings("unused")
	private String getState(Bundle b){

		int state = b.getState();
		switch (state) {
			case Bundle.INSTALLED:
				return "INSTALLED";
			case Bundle.RESOLVED:
				return "RESOLVED";
			case Bundle.UNINSTALLED:
				return "UNINSTALLED";
			case Bundle.STARTING:
				return "STARTING";
			case Bundle.ACTIVE:
				return "ACTIVE";
			case Bundle.STOPPING:
				return "STOPPING";
			default:
				return "unknown state " + state;
		}
	}
	
	@SuppressWarnings("unused")
	private String getType(int type){
		switch (type) {
		case FrameworkEvent.STARTED:return "STARTED";
		case FrameworkEvent.ERROR:return "ERROR";
		case FrameworkEvent.WARNING:return "WARNING";
		case FrameworkEvent.INFO:return "INFO";
		case FrameworkEvent.PACKAGES_REFRESHED:return "PACKAGES_REFRESHED";
		case FrameworkEvent.STARTLEVEL_CHANGED:return "STARTLEVEL_CHANGED";
		case FrameworkEvent.STOPPED:return "STOPPED"; 
		case FrameworkEvent.STOPPED_BOOTCLASSPATH_MODIFIED:return "STOPPED_BOOTCLASSPATH_MODIFIED"; 
		case FrameworkEvent.STOPPED_UPDATE:return "STOPPED_UPDATE";
		case FrameworkEvent.WAIT_TIMEDOUT:return "WAIT_TIMEDOUT";
		}
		return "UNKNOWN STATE";
	}

	

	
	
	/**
	 * @generated
	 */
	public Object startGen(IApplicationContext context) throws Exception{
		Display display = PlatformUI.createDisplay();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new DiagramEditorWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) { return IApplication.EXIT_RESTART; }
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	/**
	 * @generated NOT
	 */
	public void stop(){

		STSPluginManager.getInstance().stop();
		stopGen();
	}

	/**
	 * @generated
	 */
	public void stopGen(){
	}
}
