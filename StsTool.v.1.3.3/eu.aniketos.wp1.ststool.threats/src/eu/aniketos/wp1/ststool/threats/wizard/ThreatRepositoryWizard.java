/*
* ThreatRepositoryWizard.java
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
* ThreatRepositoryWizard.java
*/
package eu.aniketos.wp1.ststool.threats.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.aniketos.threatrepository.schema.download.Threat;
import eu.aniketos.threatrepository.schema.download.Threat.Metadata;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.threats.ThreatRepositoryLoaderHandler;


public class ThreatRepositoryWizard extends Wizard {

	private final static String	IMPORT_DATA = "ThreatRepositoryWizardImport";
	private final static String	SEARCH_STRING_DATA = "ThreatRepositoryWizardSearchString";
	private final static String	DOMAIN_FILTER_DATA = "ThreatRepositoryWizardDomainFilter";
	private final static String	SEARCH_RESULTS_DATA = "ThreatRepositoryWizardSearchResults";
	public final static String	DOMAINS_LIST = "ThreatRepositoryWizardDomains";

	private StsToolDiagramEditor editor;
	
	private LookupThreatsPage page1;
	

	public ThreatRepositoryWizard(StsToolDiagramEditor editor) throws Exception {

		if (editor == null) 
			throw new RuntimeException("Editor can't be null");
		this.editor = editor;
		
		setNeedsProgressMonitor(true);
		setWindowTitle("Import threat definition");
		ThreatRepositoryServiceWrapper processor;
		
		try {
			processor = new ThreatRepositoryServiceWrapper();
		} catch (Exception ex) {
			MessageDialog.openError(getShell(), "Threat Repository connection failed", "Please check your internet connection and proxy settings.\n\nYou might also need to set/verify your credentials (in Preferences) for accessing the Threat Repository.\n\nSign up for (free) access: https://svrs.shields-project.eu/ANIKETOS");
			throw new Exception("Threat Repository plugin failed to load.");
		}
		
		HashMap<String,String> domainsList = (HashMap<String,String>) editor.getPreferenceMap().get(DOMAINS_LIST);
		if (domainsList == null)
			domainsList = new HashMap<String,String>();
		
		// This would mean that the first connection attempt has failed, and we should try again
		if (domainsList.isEmpty()) {
			try {
				Exception ex = ThreatRepositoryLoaderHandler.loadDomainTags(editor);
				if (ex != null)
					throw ex;
				
//				List<TagData> tagsFromRepository = processor.processGetTagList();
//				if (tagsFromRepository != null && !tagsFromRepository.isEmpty()) {
//					
//					for (TagData tag : tagsFromRepository) {
//						
//						if (tag.tag.startsWith("domain:")) {
//							String tagName = tag.tag.substring(7, tag.tag.length()) + " (" + tag.occurrences + ")";
//							tagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
//							domainsList.put(tag.tag, tagName);
//						}
//					}
//				}
			} catch (Exception ex) {
//				ex.printStackTrace();
				MessageDialog.openError(getShell(), "Threat Repository connection failed", ex.getMessage());
				
				throw ex;
			}
		}
		

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
			editor.getPreferenceMap().put(DOMAINS_LIST, page1.getDomainsList());
			
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
