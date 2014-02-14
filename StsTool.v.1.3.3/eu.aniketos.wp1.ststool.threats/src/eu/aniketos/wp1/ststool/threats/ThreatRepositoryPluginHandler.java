/*
* ThreatRepositoryPluginHandler.java
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
* ThreatRepositoryHandler.java
*/
package eu.aniketos.wp1.ststool.threats;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.threats.wizard.ThreatRepositoryWizard;


public class ThreatRepositoryPluginHandler implements IHandler {


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardDialog dialog = null;

		try {
			final IWorkbenchWindow window;
			
			if (event != null)
				window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			else
				window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			if (!(window.getActivePage().getActiveEditor() instanceof StsToolDiagramEditor)) return null;
			
			ThreatRepositoryWizard wizard = new ThreatRepositoryWizard((StsToolDiagramEditor) window.getActivePage().getActiveEditor());
			wizard.setDefaultPageImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "resources/aniketos64.png")); //$NON-NLS-1$
			dialog = new WizardDialog(window.getShell(), wizard) {

				@Override
		        protected void configureShell(Shell newShell) {
					super.configureShell(newShell);
					newShell.setMinimumSize(500, 600);
					newShell.setSize(600, 700);
				}
				
				@Override
				public void updateButtons(){
					super.updateButtons();
//					getShell().setDefaultButton(getButton(IDialogConstants.FINISH_ID));
				}

			};
			dialog.create();
			dialog.setBlockOnOpen(true);
			
			dialog.open();

		} catch (Exception e) {
			
			// The dialog should not have opened, but still - let's close it if it already exists.
			if (dialog != null)
				dialog.close();
		}
		return null;
	}

	
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
