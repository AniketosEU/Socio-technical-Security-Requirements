/*
* DiagramEditorPerspective.java
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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * @generated
 */
public class DiagramEditorPerspective implements IPerspectiveFactory {


	/**
	 * Define the stsTool Default Perspective
	 */
	public void createInitialLayout(IPageLayout layout){

		layout.setEditorAreaVisible(true);
		String editorArea = layout.getEditorArea();
		//layout.addView(IPageLayout.ID_PROJECT_EXPLORER,IPageLayout.LEFT,0.20f,editorArea);
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, 0.7f, editorArea); //$NON-NLS-1$	 //$NON-NLS-2$
		//layout.addView(IPageLayout.ID_PROP_SHEET,IPageLayout.BOTTOM,0.70f,editorArea);
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
		//bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
		//bottomRight.addView("org.eclipse.pde.runtime.LogView");
		//bottomRight.addView("eu.anuketos.wp1.ststool.view.commitmentview");
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.15f, IPageLayout.ID_PROP_SHEET);


		layout.getViewLayout(editorArea).setCloseable(false);
		layout.getViewLayout(IPageLayout.ID_PROP_SHEET).setCloseable(false);
		layout.getViewLayout(IPageLayout.ID_OUTLINE).setCloseable(false);
		//layout.getViewLayout("eu.anuketos.wp1.ststool.view.commitmentview").setCloseable(false);


		String STS_EXTENSIONPOINT_ID = "eu.aniketos.wp1.ststool.diagram.extensionpoint_plugin";


		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(STS_EXTENSIONPOINT_ID);

		for (IConfigurationElement e : config) {
			String viewID = e.getAttribute("viewID");
			if (viewID != null) {
				bottomRight.addView(viewID);
				layout.getViewLayout(viewID).setCloseable(false);
			}
		}


	}

	/**
	 * @generated
	 */
	public void createInitialLayoutGen(IPageLayout layout){
		layout.setEditorAreaVisible(true);
		layout.addPerspectiveShortcut(DiagramEditorWorkbenchAdvisor.PERSPECTIVE_ID);
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.6f, layout.getEditorArea()); //$NON-NLS-1$
		right.addView(IPageLayout.ID_OUTLINE);
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, 0.6f, "right"); //$NON-NLS-1$	 //$NON-NLS-2$
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
	}
}
