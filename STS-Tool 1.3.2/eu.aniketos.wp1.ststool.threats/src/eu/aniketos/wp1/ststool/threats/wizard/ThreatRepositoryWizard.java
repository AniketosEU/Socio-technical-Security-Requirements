/*
* ThreatRepositoryWizard.java
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
* ThreatRepositoryWizard.java
*/
package eu.aniketos.wp1.ststool.threats.wizard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.threatrepository.schema.download.Threat;
import eu.aniketos.threatrepository.schema.download.Threat.Metadata;


public class ThreatRepositoryWizard extends Wizard {

	private final static String	IMPORT_DATA = "ThreatRepositoryWizardImport";
	private final static String	SEARCH_STRING_DATA = "ThreatRepositoryWizardSearchString";
	private final static String	DOMAIN_FILTER_DATA = "ThreatRepositoryWizardDomainFilter";
	private final static String	SEARCH_RESULTS_DATA = "ThreatRepositoryWizardSearchResults";
	private final static String	DOMAINS_LIST = "ThreatRepositoryWizardDomains";

	private StsToolDiagramEditor editor;
	
	private LookupThreatsPage page1;
	

	public ThreatRepositoryWizard(StsToolDiagramEditor editor) {

		if (editor == null) 
			throw new RuntimeException("Editor can't be null");
		this.editor = editor;
		
		setNeedsProgressMonitor(true);
		setWindowTitle("Import threat definition");
		
		HashMap<String,String> domainsList = new HashMap<String,String>(); //(HashMap<String,String>) editor.getPreferenceMap().get(DOMAINS_LIST);

//		if (domainsList == null) {
//			domainsList = new HashMap<String,String>();
//			try {
//		        URL url = new URL("http://aniketos.sintef9013.com/sts-domains.txt");
//		        // Create connection
//		        URLConnection urlc = url.openConnection();
//		        urlc.setDoOutput(true);
//
//			    // Get result
//		        BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
//		        String l = null;
//		        while ((l=br.readLine())!=null) {
//		            domainsList.put(l.substring(0, l.indexOf(":")), l.substring(l.indexOf(":")+1, l.length()));
//		        }
//		        br.close();
//				editor.getPreferenceMap().put(DOMAINS_LIST, domainsList);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
		
		ArrayList<Threat> threats = (ArrayList<Threat>) editor.getPreferenceMap().get(SEARCH_RESULTS_DATA);
		
		if (threats != null && !threats.isEmpty()) {
			String searchString = (String) editor.getPreferenceMap().get(SEARCH_STRING_DATA);
			String domainString = (String) editor.getPreferenceMap().get(DOMAIN_FILTER_DATA);
			Threat selectedThreat = (Threat) editor.getPreferenceMap().get(IMPORT_DATA);
			
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;

			if (selection != null && ((EditPart) structuredSelection.getFirstElement()) != null && ((EditPart) structuredSelection.getFirstElement()).getModel() != null) {
				IElementType type = eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes.Event_2006;
				View view = (View) ((EditPart) structuredSelection.getFirstElement()).getModel();
				EObject event = (EObject) view.getElement();
				
				if (event != null && event.eClass().getName().equals(type.getDisplayName())) {
					EStructuralFeature featureEventID = event.eClass().getEStructuralFeature("eventID");
					Object rawThreatId = event.eGet(featureEventID);
					
					if (rawThreatId != null) {
						String threatId = rawThreatId.toString();
						
						Threat newSelection = null;
						
						for (Threat threat : threats) {
							if (threat.getMetadata().getThreatId().equals(threatId)) {
								newSelection = threat;
								break;
							}
						}
						
//					if (selectedThreat != null && newSelection != null && newSelection.getMetadata().getThreatId().equals(selectedThreat.getMetadata().getThreatId()))
//						selectedThreat = newSelection;
//					else
						selectedThreat = null;
					}
					else
						selectedThreat = null;
				}
			}
			page1 = new LookupThreatsPage(searchString, domainString, threats, selectedThreat, domainsList);
		}
		else {
			page1 = new LookupThreatsPage(domainsList);			
		}
	}


	@Override
	public void addPages(){		
		super.addPages();
		addPage(page1);
	}
	
	
	@Override
	public boolean canFinish(){
		return page1.isPageComplete();
	}

	
	@Override
	public boolean performFinish() {	

		try {
			updateSelectedElement(page1.getSelectedThreat());
			editor.getPreferenceMap().put(IMPORT_DATA, page1.getSelectedThreat());
			editor.getPreferenceMap().put(SEARCH_STRING_DATA, page1.getSearchText());
			editor.getPreferenceMap().put(DOMAIN_FILTER_DATA, page1.getDomainFilter());
			editor.getPreferenceMap().put(SEARCH_RESULTS_DATA, page1.getThreatsList());
			
		} catch (Exception ex) {
			page1.setErrorMessage(ex.getMessage());
//			ex.printStackTrace();
			return false;
		}
		
		return true;
	}

	
	
	
	public <E extends IElementType> void updateSelectedElement(Threat threat) throws Exception {
		Metadata threatData = threat.getMetadata(); 
		String newThreatName = threatData.getName();
		IElementType eventEType = eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes.Event_2006;
		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		DiagramEditor diagramEditor = (DiagramEditor) window.getActivePage().getActiveEditor();
		Diagram diagram = diagramEditor.getDiagram();

		EObject element = diagram.getElement();
		EList<EObject> eContents = element.eContents();
		
		Iterator<EObject> diagramContents = eContents.iterator();
		while (diagramContents.hasNext()) {
			EObject next = diagramContents.next();
			List<EStructuralFeature> features = next.eClass().getEAllStructuralFeatures();

			for (EStructuralFeature feature : features) {
				if (feature.getName().equalsIgnoreCase("name") && newThreatName.equals(next.eGet(feature))) {
					String typeMatch = next.eClass().getName().equals(eventEType.getDisplayName()) ? "event" : "element";
					// TODO Highlight the matched element??
					throw new Exception("An " + typeMatch + " with the same name already exists in the diagram!");
				}
			}
		}
		
		ISelection selection = window.getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		IGraphicalEditPart selectElement = ((IGraphicalEditPart) structuredSelection.getFirstElement());
		
		if (selection == null || ((EditPart) structuredSelection.getFirstElement()) == null || ((EditPart) structuredSelection.getFirstElement()).getModel() == null)
			throw new Exception("Invalid selection of event element. Close the wizard and try again!");
		  
		View view = (View) ((EditPart) structuredSelection.getFirstElement()).getModel();
		if (view == null)
			throw new Exception("Invalid condition occured in wizard. Close the wizard and try again!");

		EObject event = (EObject) view.getElement();
		
		
		// Create an Event element if not selected
//		if (!event.eClass().getName().equals(eventEType.getDisplayName())) {
//			// If an Event is not selected, creat a new Event element in the diagram
//			CreateElementRequest request = new CreateElementRequest(type);
//			ViewAndElementDescriptor viewDescriptor = new ViewAndElementDescriptor(
//					new CreateElementRequestAdapter(request), Node.class,
//					((IHintedType) type).getSemanticHint(), selectElement.getDiagramPreferencesHint());
//			CompoundCommand cmd = new CompoundCommand("Creating a new event element");
//
//			CreateViewAndElementRequest req = new CreateViewAndElementRequest(viewDescriptor);
//			cmd.add(selectElement.getCommand(req));
//			selectElement.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
//			Collection results = DiagramCommandStack.getReturnValues(cmd);
//			IAdaptable[] adaptables = (IAdaptable[]) results.toArray();
//			if (adaptables.length > 0) {
//				View altView = (View) adaptables[1].getAdapter(View.class);
//				event = (EObject) altView.getElement();
//				System.out.println("Creating a new event element!");
//			}
//			else
//				throw new Exception("Unable to create a new Event element");
//		}
			
		List<EStructuralFeature> features = event.eClass().getEAllStructuralFeatures();

		for (EStructuralFeature feature : features) {
			
			if ((feature.getName().replaceAll(" ", "")).equalsIgnoreCase("name")) {
				setFeatureString(feature, threatData.getName(), selectElement, event);
			} 
			else if ((feature.getName().replaceAll(" ", "")) .equalsIgnoreCase("description")) {
				setFeatureString(feature, threatData.getDescription(), selectElement, event);
			} 
			else if ((feature.getName().replaceAll(" ", "")).equalsIgnoreCase("eventID")) {
				setFeatureString(feature, threatData.getThreatId(), selectElement, event);
			}
		}		
	}
	

	private void setFeatureString(EStructuralFeature feature, String value, IGraphicalEditPart selectElement, EObject event) {
		SetRequest reqSet = null;
		SetValueCommand operation = null;
		
		reqSet = new SetRequest(selectElement.getEditingDomain(), event, feature, value);
//		System.out.println(feature);
//		System.out.println(value);
//		System.out.println(selectElement);
//		System.out.println(event);
		operation = new SetValueCommand(reqSet);
		
		selectElement.getDiagramEditDomain().getDiagramCommandStack().execute(new ICommandProxy(operation));
	}
}
