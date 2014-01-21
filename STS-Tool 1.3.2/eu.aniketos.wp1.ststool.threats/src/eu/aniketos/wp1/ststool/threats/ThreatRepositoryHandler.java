/*
* ThreatRepositoryHandler.java
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
* ThreatRepositoryHandler.java
*/
package eu.aniketos.wp1.ststool.threats;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.threats.wizard.ThreatRepositoryWizard;


public class ThreatRepositoryHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener){

	}

	@Override
	public void dispose(){
		//IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow(); 																	// UNCOMMENT THIS LINE??
		
//		DiagramEditor diagramEditor = (DiagramEditor) window.getActivePage().getActiveEditor();
//		Diagram diagram = diagramEditor.getDiagram();
//		EObject element = diagram.getElement();
//		
		//StructuredSelection selection = (StructuredSelection) window.getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection(); 	// UNCOMMENT THIS LINE??
		//window.getActivePage().getActiveEditor().getSite().getSelectionProvider().setSelection(null); 													// UNCOMMENT THIS LINE??
		
//		EList<EObject> eContents = element.eContents();
//		
//		Iterator<EObject> diagramContents = eContents.iterator();
//		while (diagramContents.hasNext()) {
//			EObject next = diagramContents.next();
//			List<EStructuralFeature> features = next.eClass().getEAllStructuralFeatures();
//
//			for (EStructuralFeature feature : features) {
//				if (feature.getName().equalsIgnoreCase("eventID") && newThreatName.equals(next.eGet(feature))) {
//					String typeMatch = next.eClass().getName().equals(eventEType.getDisplayName()) ? "event" : "element";
//					// TODO Highlight the matched element??
//					throw new Exception("An " + typeMatch + " with the same name already exists in the diagram!");
//				}
//			}
//		}
//		
//		DiagramEditPart selection = diagramEditor.getDiagramEditPart();
//		
//		diagramEditor.getDiagramGraphicalViewer().select(null);
//		diagramEditor.getDiagramGraphicalViewer().select(selection);
//		diagram.setElement(element);
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
			
			ThreatRepositoryWizard wizard = new ThreatRepositoryWizard((StsToolDiagramEditor) window.getActivePage().getActiveEditor());
			wizard.setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "resources/aniketos64.png")); //$NON-NLS-1$
			WizardDialog dialog = new WizardDialog(window.getShell(), wizard) {

				@Override
		        protected void configureShell(Shell newShell) {
					super.configureShell(newShell);
					newShell.setMinimumSize(600, 600);
					newShell.setSize(600, 700);
				}
				
				@Override
				public void updateButtons(){
					super.updateButtons();
//					getShell().setDefaultButton(getButton(IDialogConstants.FINISH_ID));
				}

			};
			dialog.create();
			dialog.setBlockOnOpen(false);
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
