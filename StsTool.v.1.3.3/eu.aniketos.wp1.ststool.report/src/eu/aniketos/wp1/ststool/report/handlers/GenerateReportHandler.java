/*
* GenerateReportHandler.java
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
package eu.aniketos.wp1.ststool.report.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.report.wizard.GenerateReportWizard;


public class GenerateReportHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener){

	}

	@Override
	public void dispose(){

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{

		try {
			final IWorkbenchWindow window;
			if (event != null)
				window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			else
				window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			if (!(window.getActivePage().getActiveEditor() instanceof StsToolDiagramEditor)) return null;
			GenerateReportWizard wizard = new GenerateReportWizard((StsToolDiagramEditor) window.getActivePage().getActiveEditor());
			WizardDialog dialog = new WizardDialog(window.getShell(), wizard) {

				@Override
				public void updateButtons(){
					super.updateButtons();
					boolean canFlipToNextPage = false;
					if (getButton(IDialogConstants.NEXT_ID) != null) {
						canFlipToNextPage = getCurrentPage().canFlipToNextPage();
					}
					if (canFlipToNextPage) {
						getShell().setDefaultButton(getButton(IDialogConstants.NEXT_ID));
					} else {
						getShell().setDefaultButton(getButton(IDialogConstants.FINISH_ID));
					}
				}

			};
			dialog.create();
			dialog.setBlockOnOpen(true);
			dialog.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isEnabled(){

		return true;
	}

	@Override
	public boolean isHandled(){

		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener){

	}

}
