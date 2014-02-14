/*
* DiagramEditorWorkbenchWindowAdvisor.java
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

import java.io.File;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import eu.aniketos.wp1.ststool.diagram.custom.shells.LicenseDialog;
import eu.aniketos.wp1.ststool.diagram.custom.utility.FileUtility;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.ISTSDiagramObserver;
import eu.aniketos.wp1.ststool.diagram.part.Messages;

/**
 * @generated
 */
public class DiagramEditorWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	/**
	 * @generated
	 */
	public DiagramEditorWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated
	 */
	@Override
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		return new DiagramEditorActionBarAdvisor(configurer);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void preWindowOpen() {

		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 700));
		configurer.setShowStatusLine(false);
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// configurer.setInitialSize(new Point(screenSize.height,
		// screenSize.width));
		configurer.setTitle("STS-Tool " + Messages.StsToolBuildVersion);
		// PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_MEMORY_MONITOR,
		// true);
	}

	private final String LICENSE = "License";

	@Override
	public void postWindowOpen() {

		super.postWindowOpen();
		final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		if (!PlatformUI.getPreferenceStore().contains(LICENSE)) {
			LicenseDialog licenseD = new LicenseDialog(shell);
			if (licenseD.open(FileUtility.readTextFile("resources/license.txt"))) {
				if (Platform.OS_MACOSX.equals(Platform.getOS())) {
					MessageDialog
							.openInformation(
									shell,
									"Maverik and newer versions are not supported",
									"It seems that you are running the tool from an OSX operating system.\n\nWe are sorry to inform you that OS X Mavericks, or any later version of OS X, is not supported.\n\n"
											+ "This is due to a bug in the graphics library used by the tool, because of which the dialogs allowing the selection of files from the file system open only once.\nRestart the tool to continue.");
					PlatformUI.getPreferenceStore().setValue(LICENSE, "true");
				}
			} else {
				PlatformUI.getWorkbench().close();
			}
		}

		String[] args = Platform.getCommandLineArgs();
		IWorkbench workbench = getWindowConfigurer().getWorkbenchConfigurer().getWorkbench();
		IWorkbenchWindow workbenchWindow = getWindowConfigurer().getWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		for (String s : args) {
			try {
				File f = new File(s);
				IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(f.getAbsolutePath());
				if (editorDescriptor != null) {
					page.openEditor(new URIEditorInput(URI.createFileURI(f.getAbsolutePath())), editorDescriptor.getId());
				}
			} catch (PartInitException e) {
			}
		}
	}

	@Override
	public void postWindowCreate() {

		super.postWindowCreate();
		if (getWindowConfigurer().getWindow().getShell() != null)
			getWindowConfigurer().getWindow().getShell().setMaximized(true);
		IContributionItem[] mItems, mSubItems;
		IMenuManager mm = getWindowConfigurer().getActionBarConfigurer().getMenuManager();
		mItems = mm.getItems();
		for (int i = 0; i < mItems.length; i++) {
			if (mItems[i] instanceof MenuManager) {
				mSubItems = ((MenuManager) mItems[i]).getItems();
				for (int j = 0; j < mSubItems.length; j++) {
					if (mItems[i].getId().equals("file"))
						((MenuManager) mItems[i]).remove("org.eclipse.ui.openLocalFile");
				}
			}
		}
		registerSTSPlugins();
	}

	// This is the ID from your extension point
	private static final String STS_EXTENSIONPOINT_ID = "eu.aniketos.wp1.ststool.diagram.extensionpoint_plugin";

	private void registerSTSPlugins() {

		STSPluginManager stsPluginManager = STSPluginManager.getInstance();

		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(STS_EXTENSIONPOINT_ID);

		for (IConfigurationElement e : config) {
			try {
				if (e.getName().equals("DiagramObserver")) {
					Object o = e.createExecutableExtension("class");
					if (o instanceof ISTSDiagramObserver) {
						stsPluginManager.registerPlugin((ISTSDiagramObserver) o);
					}
				}
			} catch (CoreException e1) {
				e1.printStackTrace();
			}
		}
	}

}
