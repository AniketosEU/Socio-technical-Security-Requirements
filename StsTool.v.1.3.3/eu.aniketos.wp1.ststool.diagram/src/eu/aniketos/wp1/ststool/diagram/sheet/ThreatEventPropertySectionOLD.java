/*
* ThreatEventPropertySectionOLD.java
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
package eu.aniketos.wp1.ststool.diagram.sheet;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;

/**
 * @generated NOT
 */
public class ThreatEventPropertySectionOLD extends AbstractPropertySection {

	// TODO If used anywhere else in the application (e.g. the Security
	// Requirements Document), this URL should probably be defined elsewhere
	public final static String SVRS_WEB_URL = "https://svrs.shields-project.eu/ANIKETOS/";

	private Composite container;

	private Composite controlsContainer;

	private EObject event;

	private String eventId;

	private Adapter changeAdapter;

	public ThreatEventPropertySectionOLD() {

		event = null;
		eventId = null;
		controlsContainer = null;
		changeAdapter = new AdapterImpl() {

			public void notifyChanged(Notification notification) {
				if (notification.getEventType() == Notification.REMOVE) {
					event = null;
					eventId = null;
				} else {
					Object obj = notification.getFeature();
					if (obj != null && obj.getClass() == EAttributeImpl.class) {
						EAttributeImpl feature = (EAttributeImpl) obj;
						if (feature != null && feature.getName() != null) {
							String name = feature.getName();
							if (name.equals("eventID")) {
								eventId = notification.getNewValue().toString();
								updateControls();
							}
						}
					}
				}
			}
		};
	}

	/**
	 * TODO If the focus is inside the Property window --> Threat tab, pressing
	 * Delete/Backspace will not trigger the deletion of the selected Event. Try
	 * to fix this here..
	 */
	private void deleteElement() {

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		DiagramEditor diagramEditor = (DiagramEditor) window.getActivePage().getActiveEditor();
		Diagram diagram = diagramEditor.getDiagram();

		EObject element = diagram.getElement();
		EList<EObject> eContents = element.eContents();

		ISelection selection = window.getActivePage().getActiveEditor().getSite().getSelectionProvider().getSelection();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		IGraphicalEditPart selectElement = ((IGraphicalEditPart) structuredSelection.getFirstElement());

		if (selection == null || ((EditPart) structuredSelection.getFirstElement()) == null
				|| ((EditPart) structuredSelection.getFirstElement()).getModel() == null)
			return;

		View view = (View) ((EditPart) structuredSelection.getFirstElement()).getModel();
		if (view == null)
			return;

		EObject event = (EObject) view.getElement();
		IDiagramEditDomain domain = selectElement.getDiagramEditDomain();
		// domain.getDiagramCommandStack().execute(new new
		// RemoveCommand(event));

		// DestroyElementRequest

		// Create an Event element if not selected
		// if (!event.eClass().getName().equals(eventEType.getDisplayName())) {
		// // If an Event is not selected, creat a new Event element in the
		// diagram
		// CreateElementRequest request = new CreateElementRequest(type);
		// ViewAndElementDescriptor viewDescriptor = new
		// ViewAndElementDescriptor(
		// new CreateElementRequestAdapter(request), Node.class,
		// ((IHintedType) type).getSemanticHint(),
		// selectElement.getDiagramPreferencesHint());
		// CompoundCommand cmd = new
		// CompoundCommand("Creating a new event element");
		//
		// CreateViewAndElementRequest req = new
		// CreateViewAndElementRequest(viewDescriptor);
		// cmd.add(selectElement.getCommand(req));
		// selectElement.getDiagramEditDomain().getDiagramCommandStack().execute(cmd);
		// Collection results = DiagramCommandStack.getReturnValues(cmd);
		// IAdaptable[] adaptables = (IAdaptable[]) results.toArray();
		// if (adaptables.length > 0) {
		// View altView = (View) adaptables[1].getAdapter(View.class);
		// event = (EObject) altView.getElement();
		// System.out.println("Creating a new event element!");
		// }
		// else
		// throw new Exception("Unable to create a new Event element");
		// }
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		container = new Composite(parent, SWT.NULL);
		container.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		container.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS) {
					// deleteElement(); // Uncomment this line when the
					// deleteElement() method is fixed
					// e.doit = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		});

		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 15;
		gridLayout.numColumns = 1;
		container.setLayout(gridLayout);

		updateControls();
	}

	public void updateControls() {
		if (controlsContainer != null)
			controlsContainer.dispose();

		GridData horizontalFillLayout = new GridData();
		horizontalFillLayout.horizontalAlignment = SWT.FILL;
		horizontalFillLayout.grabExcessHorizontalSpace = true;
		horizontalFillLayout.verticalAlignment = SWT.FILL;
		horizontalFillLayout.grabExcessVerticalSpace = true;

		controlsContainer = new Composite(container, SWT.NONE);
		controlsContainer.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		controlsContainer.setLayout(new FormLayout());
		controlsContainer.setLayoutData(horizontalFillLayout);

		Label headingLabel = new Label(controlsContainer, SWT.NONE);
		headingLabel.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		FormData headingLabelLayout = new FormData();
		headingLabelLayout.right = new FormAttachment(60, 0);
		headingLabelLayout.left = new FormAttachment(0, 10);
		headingLabel.setLayoutData(headingLabelLayout);
		headingLabel.setText("Threat Repository");

		FontData[] fD = headingLabel.getFont().getFontData();
		fD[0].setStyle(SWT.BOLD);
		fD[0].setHeight(18);
		Font font = new Font(container.getDisplay(), fD[0]);
		headingLabel.setFont(font);

		// Simple validation of the Event ID string, though it could be
		// validated better!
		if (eventId != null && eventId.length() == 36) {
			Label instructionLabel = new Label(controlsContainer, SWT.NONE);
			instructionLabel.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			FormData instructionLabelLayout = new FormData();
			instructionLabelLayout.top = new FormAttachment(headingLabel, 18);
			instructionLabelLayout.left = new FormAttachment(0, 10);
			instructionLabelLayout.right = new FormAttachment(60, 0);
			instructionLabel.setLayoutData(instructionLabelLayout);
			instructionLabel.setText("Click to view additional information in web browser (free registration/login required):"); // (for
																																	// testing
																																	// username:
																																	// aniketos
																																	// /
																																	// password:
																																	// aniketos)

			final String url = SVRS_WEB_URL + "resourceView.jsp?version=1&id=" + eventId;
			Link browserLink = new Link(controlsContainer, SWT.NONE);
			// String urlText = url.length() > 250 ? url.substring(0,
			// Math.min(url.length(), 250)) + "..." : url;
			browserLink.setText("<A href=\"" + url + "\">" + url + "</A>");

			// Button openBrowserButton = new Button(controlsContainer,
			// SWT.PUSH);
			// openBrowserButton.setText("Open threat in browser");
			FormData browserLinkLayout = new FormData();
			browserLinkLayout.top = new FormAttachment(instructionLabel, 6);
			browserLinkLayout.left = new FormAttachment(0, 10);
			browserLinkLayout.right = new FormAttachment(60, 0);
			browserLink.setLayoutData(browserLinkLayout);
			browserLink.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			browserLink.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					try {
						// Open default external browser
						PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(url));
					} catch (PartInitException ex) {
						ex.printStackTrace();
					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					}
				}
			});

			Button wizardButton = new Button(controlsContainer, SWT.PUSH);
			FormData wizardLayout = new FormData();
			wizardLayout.top = new FormAttachment(browserLink, 20);
			wizardLayout.left = new FormAttachment(0, 10);
			wizardLayout.right = new FormAttachment(15, 0);
			wizardButton.setLayoutData(wizardLayout);
			wizardButton.setText("Change threat definition");
			wizardButton.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					invokeThreatRepositoryWizard();
				}
			});
		}

		else {
			Label instructionLabel = new Label(controlsContainer, SWT.NONE);
			instructionLabel.setBackground(container.getDisplay().getSystemColor(SWT.COLOR_WHITE));
			FormData instructionLabelLayout = new FormData();
			instructionLabelLayout.top = new FormAttachment(headingLabel, 20);
			instructionLabelLayout.left = new FormAttachment(0, 10);
			instructionLabelLayout.right = new FormAttachment(60, 0);
			instructionLabel.setLayoutData(instructionLabelLayout);
			instructionLabel.setText("Click to browse a list of common threats:");

			Button wizardButton = new Button(controlsContainer, SWT.PUSH);
			FormData wizardButtonLayout = new FormData();
			wizardButtonLayout.top = new FormAttachment(instructionLabel, 10);
			wizardButtonLayout.left = new FormAttachment(0, 10);
			wizardButtonLayout.right = new FormAttachment(15, 0);
			wizardButton.setLayoutData(wizardButtonLayout);
			wizardButton.setText("Import threat definition");
			wizardButton.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					invokeThreatRepositoryWizard();
				}
			});

		}

		container.layout();
	}

	private void invokeThreatRepositoryWizard() {
		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);

		try {
			handlerService.executeCommand("addthreats", null);
		} catch (Exception ex) {
			// Give message about missing plug-in

			MessageDialog.openError(container.getShell(), "Required component missing", "The Threat Repository plug-in could not be initialized: "
					+ ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * @generated NOT
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (event != null)
			event.eAdapters().remove(changeAdapter);

		EventEditPart editPart;

		if (selection instanceof IStructuredSelection)
			editPart = (EventEditPart) ((IStructuredSelection) selection).getFirstElement();
		else {
			MessageDialog.openError(container.getShell(), "Diagram error", "Invalid selection of event element.");
			throw new InputMismatchException("Invalid selection of diagram element.");
		}

		View view = (View) editPart.getModel();

		EObject newEvent = (EObject) view.getElement();
		List<EStructuralFeature> features = newEvent.eClass().getEAllStructuralFeatures();

		for (EStructuralFeature feature : features) {

			if ((feature.getName().replaceAll(" ", "")).equalsIgnoreCase("eventID")) {
				eventId = (String) newEvent.eGet(feature);
			}

			// else if ((feature.getName().replaceAll(" ",
			// "")).equalsIgnoreCase("name")) {
			// name = (String) event.eGet(feature);
			// }
			// else if ((feature.getName().replaceAll(" ",
			// "")).equalsIgnoreCase("description")) {
			// description = (String) event.eGet(feature);
			// }
		}

		newEvent.eAdapters().add(changeAdapter);
		event = newEvent;

		updateControls();
	}
}
