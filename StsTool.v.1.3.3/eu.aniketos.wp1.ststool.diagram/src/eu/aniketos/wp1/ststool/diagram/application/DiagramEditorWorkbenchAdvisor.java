/*
* DiagramEditorWorkbenchAdvisor.java
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

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.osgi.framework.Bundle;

import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramEditorWorkbenchAdvisor extends WorkbenchAdvisor {

	/**
	 * @generated
	 */
	public static final String PERSPECTIVE_ID = "eu.aniketos.wp1.ststool.diagram.StsToolPerspective"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	@Override
	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void initialize(IWorkbenchConfigurer configurer) {

		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
		// configurer.setSaveAndRestore(false);
	}

	/**
	 * @generated
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		return new DiagramEditorWorkbenchWindowAdvisor(configurer);
	}

//	@Override
//	public void preStartup() {
//		super.preStartup();
//		System.out.println("PRE");
//		for (Bundle b : StsToolDiagramEditorPlugin.getInstance().getBundle().getBundleContext().getBundles()) {
//			if(b.getState()==Bundle.INSTALLED){
//				System.out.println(getStateText(b.getState())+"  -  " + b.getSymbolicName());
//			}
//		}
//	}

	private String getStateText(int state) {
		switch (state) {
		case Bundle.INSTALLED:
			return "INSTALLED";
		case Bundle.RESOLVED:
			return "RESOLVED";
		case Bundle.STARTING:
			return "STARTING";
		case Bundle.ACTIVE:
			return "ACTIVE";
		case Bundle.STOPPING:
			return "STOPPING";
		case Bundle.UNINSTALLED:
			return "UNINSTALLED";
		default: return "?"+state;
		}
	}
}
