/*
* DiagramEditorActionBarAdvisor.java
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
import java.util.Iterator;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.ActionSetRegistry;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import eu.aniketos.wp1.ststool.diagram.part.Messages;
import eu.aniketos.wp1.ststool.diagram.part.StsToolCreationWizard;

/**
 * @generated
 */
@SuppressWarnings("restriction")
public class DiagramEditorActionBarAdvisor extends ActionBarAdvisor {

	/**
	 * @generated
	 */
	private ActionFactory.IWorkbenchAction	lockToolBarAction;
	/**
	 * @generated
	 */
	private ActionFactory.IWorkbenchAction	toggleCoolbarAction;
	private static final String[]				actionSetId	= new String[] { "org.eclipse.ui.WorkingSetActionSet", //$NON-NLS-1$
			"org.eclipse.ui.edit.text.actionSet.navigation", //$NON-NLS-1$
			"org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo", //$NON-NLS-1$
			"org.eclipse.ui.actionSet.openFiles", //$NON-NLS-1$
			"org.eclipse.ui.edit.text.actionSet.annotationNavigation", //$NON-NLS-1$
			"org.eclipse.ui.NavigateActionSet", //$NON-NLS-1$
			"org.eclipse.search.searchActionSet", "org.eclipse.ui.actionSet.keyBindings" };	//$NON-NLS-1$

	/**
	 * Remove unwanted action provided by plugins solution found on http://andy.ekiwi.de/?p=41
	 */
	private void removeUnWantedActions(){
		ActionSetRegistry asr = WorkbenchPlugin.getDefault().getActionSetRegistry();
		IActionSetDescriptor[] actionSets = asr.getActionSets();
		IExtension ext = null;
		for (IActionSetDescriptor actionSet : actionSets) {
			for (String element : actionSetId) {
				//System.out.println(actionSet.getId());
				if (element.equals(actionSet.getId())) {
					// System.out.println("     DELETED");
					ext = actionSet.getConfigurationElement().getDeclaringExtension();
					asr.removeExtension(ext, new Object[] { actionSet });
				}
			}
		}
	}


	IWorkbenchAction				aboutAction;
	IWorkbenchAction				introAction;
	private IWorkbenchAction	preferencesAction;
	IWorkbenchAction				resetPerspective;
	IWorkbenchAction				helpContentsActon;

	private MenuManager			perspectiveMenu	= new MenuManager("&Open Perspective");
	private MenuManager			viewMenu				= new MenuManager("Show &View");



	/**
	 * @generated
	 */
	public DiagramEditorActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated
	 */
	private IWorkbenchWindow getWindow(){
		return getActionBarConfigurer().getWindowConfigurer().getWindow();
	}

	/**
	 * Register the actions to the workbench
	 * 
	 * @generated NOT
	 */
	@Override
	protected void makeActions(IWorkbenchWindow window){

		register(ActionFactory.CLOSE.create(window));

		register(ActionFactory.CLOSE_ALL.create(window));

		register(ActionFactory.SAVE.create(window));

		register(ActionFactory.SAVE_AS.create(window));

		register(ActionFactory.SAVE_ALL.create(window));

		register(ActionFactory.QUIT.create(window));

		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);

		preferencesAction = ActionFactory.PREFERENCES.create(window);
		register(preferencesAction);

		perspectiveMenu.add(ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(window));
		viewMenu.add(ContributionItemFactory.VIEWS_SHORTLIST.create(window));

		resetPerspective = ActionFactory.RESET_PERSPECTIVE.create(window);
		register(resetPerspective);

		helpContentsActon = ActionFactory.HELP_CONTENTS.create(window);
		register(helpContentsActon);


		try {
			introAction = ActionFactory.INTRO.create(window);
			introAction.setText("Change Log");
			register(introAction);
		} catch (Exception e) {
		}


	}

	/**
	 * Add the eclipse menu entry
	 * 
	 * @generated NOT
	 */
	@Override
	protected void fillMenuBar(IMenuManager menu){
		//menu:org.eclipse.ui.main.menu
		//toolbar:org.eclipse.ui.main.toolbar
		{
			//file
			IMenuManager menuX = new MenuManager(Messages.ApplicationMenuName_File, IWorkbenchActionConstants.M_FILE);

			//new
			menuX.add(new GroupMarker("new"));
			//open
			menuX.add(new Separator("open"));
			//import
			menuX.add(new Separator("import"));

			menuX.add(new Separator());

			//save
			menuX.add(getAction(ActionFactory.SAVE.getId()));

			//saveAs
			menuX.add(getAction(ActionFactory.SAVE_AS.getId()));

			//saveAll
			menuX.add(getAction(ActionFactory.SAVE_ALL.getId()));

			menuX.add(new Separator());

			//close
			menuX.add(getAction(ActionFactory.CLOSE.getId()));

			//closeAll
			menuX.add(getAction(ActionFactory.CLOSE_ALL.getId()));

			menuX.add(new Separator());

			//additions
			menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

			//ExportAdditions
			menuX.add(new GroupMarker("ExportAdditions"));

			menuX.add(new Separator());

			//quit
			menuX.add(getAction(ActionFactory.QUIT.getId()));

			//fileEnd
			menuX.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
			menu.add(menuX);
		}

		//additions
		menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		{
			//window
			IMenuManager menuX = new MenuManager(Messages.ApplicationMenuName_Window, IWorkbenchActionConstants.M_WINDOW);

			//org.eclipse.ui.window.resetPerspective
			menuX.add(resetPerspective);
			menuX.add(new Separator());
			//org.eclipse.ui.window.preferences
			menuX.add(preferencesAction);
			//additions
			menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			menu.add(menuX);
		}

		{
			//help
			IMenuManager menuX = new MenuManager(Messages.ApplicationMenuName_Help, IWorkbenchActionConstants.M_HELP);

			//org.eclipse.ui.help.quickStartAction
			if (introAction != null) menuX.add(introAction);

			//helpStart
			menuX.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
			menuX.add(helpContentsActon);
			//help
			menuX.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));

			//menuX.add(helpAction);
			menuX.add(new Separator());
			menuX.add(aboutAction);

			//additions
			menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
			menu.add(menuX);
		}
	}

	/**
	 * Add the eclipse coolbar entry
	 * 
	 * @generated NOT
	 */
	@Override
	protected void fillCoolBar(ICoolBarManager toolBar){

		myFillCoolBar(toolBar);

	}


	/**
	 * @generated
	 */
	private static boolean openEditor(IWorkbench workbench,URI fileURI){
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorTitle, NLS.bind(Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorMessage, fileURI.toFileString()));
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultEditorOpenErrorTitle, exception.getMessage());
				return false;
			}
		}
		return true;
	}


	/**
	 * @generated
	 */
	public static class NewDiagramAction extends WorkbenchWindowActionDelegate {

		/**
		 * @generated
		 */
		public void run(IAction action){
			StsToolCreationWizard wizard = new StsToolCreationWizard();
			wizard.init(getWindow().getWorkbench(), StructuredSelection.EMPTY);
			WizardDialog wizardDialog = new WizardDialog(getWindow().getShell(), wizard);
			wizardDialog.open();
		}
	}


	/**
	 * @generated
	 */
	public static class OpenURIAction extends WorkbenchWindowActionDelegate {

		/**
		 * @generated
		 */
		public void run(IAction action){
			LoadResourceAction.LoadResourceDialog loadResourceDialog = new LoadResourceAction.LoadResourceDialog(getWindow().getShell());
			if (Dialog.OK == loadResourceDialog.open()) {
				for (Iterator i = loadResourceDialog.getURIs().iterator(); i.hasNext();) {
					openEditor(getWindow().getWorkbench(), (URI) i.next());
				}
			}
		}
	}


	/**
	 * @generated
	 */
	public static class OpenAction extends WorkbenchWindowActionDelegate {

		/**
		 * @generated
		 */
		public void run(IAction action){
			FileDialog fileDialog = new FileDialog(getWindow().getShell(), SWT.OPEN);
			fileDialog.open();
			if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0) {
				openEditor(getWindow().getWorkbench(), URI.createFileURI(fileDialog.getFilterPath() + File.separator + fileDialog.getFileName()));
			}
		}
	}


	/**
	 * @generated
	 */
	public static class AboutAction extends WorkbenchWindowActionDelegate {

		/**
		 * @generated
		 */
		public void run(IAction action){
			MessageDialog.openInformation(getWindow().getShell(), Messages.DiagramEditorActionBarAdvisor_AboutDialogTitle, Messages.DiagramEditorActionBarAdvisor_AboutDialogMessage);
		}
	}


	protected void myFillCoolBar(ICoolBarManager toolBar){
		{
			IToolBarManager toolBarX = new ToolBarManager();

			toolBarX.add(new GroupMarker("new"));
			toolBarX.add(new GroupMarker("open"));
			toolBarX.add(getAction(ActionFactory.SAVE.getId()));
			toolBarX.add(new Separator("exoprt"));
			toolBarX.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			toolBar.add(new ToolBarContributionItem(toolBarX, "file"));
		}


		{
		IToolBarManager toolBarX = new ToolBarManager();
			toolBarX.add(new GroupMarker("views"));
			toolBar.add(new ToolBarContributionItem(toolBarX, "view"));
	}

		toolBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
	}

}
