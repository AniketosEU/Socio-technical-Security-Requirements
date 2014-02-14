/*
* SwitchViewHandler.java
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
package eu.aniketos.wp1.ststool.diagram.custom.view.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;


public class SwitchViewHandler extends AbstractHandler {

	private final static String	COMMAND_ID				= "eu.aniketos.wp1.ststool.diagram.command.switchView";

	public final static String		STATE_SOCIAL			= "social";
	public final static String		STATE_INFORMATION		= "information";
	public final static String		STATE_AUTHORISATION	= "authorisation";


	public Object execute(ExecutionEvent event) throws ExecutionException{

		if (HandlerUtil.matchesRadioState(event)) return null; // we are already in the updated state - do nothing
		String currentState = event.getParameter(RadioState.PARAMETER_ID);

		if (currentState.equals(STATE_SOCIAL))
			switchView(ViewsManager.SOCIAL_VIEW);
		else if (currentState.equals(STATE_INFORMATION))
			switchView(ViewsManager.RESOURCE_VIEW);
		else if (currentState.equals(STATE_AUTHORISATION))
			switchView(ViewsManager.AUTHORIZATION_VIEW);
		else
			return null;

		return null;
	}

	public static void switchView(int view){

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		if (window.getActivePage().getActiveEditor() == null || !(window.getActivePage().getActiveEditor() instanceof CustomStsToolDiagramDocumentEditor)) { return; }


		String state;
		switch (view) {
			case ViewsManager.SOCIAL_VIEW:
				state = STATE_SOCIAL;
			break;
			case ViewsManager.RESOURCE_VIEW:
				state = STATE_INFORMATION;
			break;
			case ViewsManager.AUTHORIZATION_VIEW:
				state = STATE_AUTHORISATION;
			break;
			default:
				return;
		}

		CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) window.getActivePage().getActiveEditor();
		try {
			editor.getViewsManager().setCurrentView(ViewsManager.EMPTY_VIEW);
			editor.getViewsManager().setCurrentView(view);
			//System.out.println(state);
			// and finally update the current state
			HandlerUtil.updateRadioState(getCommand(), state);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	public static void updateMenuButtons(int view){
		String state;
		switch (view) {
			case ViewsManager.SOCIAL_VIEW:
				state = STATE_SOCIAL;
			break;
			case ViewsManager.RESOURCE_VIEW:
				state = STATE_INFORMATION;
			break;
			case ViewsManager.AUTHORIZATION_VIEW:
				state = STATE_AUTHORISATION;
			break;
			default:
				return;
		}
		try {
			HandlerUtil.updateRadioState(getCommand(), state);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private static Command getCommand(){

		try {
			ICommandService cmdService = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
			Category switchViewCategory = cmdService.getCategory("eu.aniketos.wp1.ststool.diagram.commands.changeViewCategory");
			if (!switchViewCategory.isDefined()) {
				switchViewCategory.define("switchViewCategory", "");
			}
			Command switchViewCommand = cmdService.getCommand(COMMAND_ID);
			if (!switchViewCommand.isDefined()) {
				switchViewCommand.define("SwitchView", "Switch The current View", switchViewCategory);
			}
			return switchViewCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
