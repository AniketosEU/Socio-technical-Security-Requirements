/*
* DiagramEditorContextMenuProvider.java
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
package eu.aniketos.wp1.ststool.diagram.part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

import eu.aniketos.wp1.ststool.*;
import eu.aniketos.wp1.ststool.diagram.custom.actions.AuthorisationTransferabilityAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.CapabilityAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedAvailabilityAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedConfidentialyAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedIntegrityAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedRedundancyAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedRepudiationAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedTransferableAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.SecNeedTrustworthiness;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolConnectionNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator.ExportImageWizard;
import eu.aniketos.wp1.ststool.diagram.custom.utility.DescriptionDialog;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProvisionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.IContextMenuAction;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends DiagramContextMenuProvider {

	/**
	 * @generated
	 */
	private IWorkbenchPart part;

	/**
	 * @generated
	 */
	private DeleteElementAction deleteAction;

	/**
	 * @generated NOT
	 */
	public DiagramEditorContextMenuProvider(IWorkbenchPart part, EditPartViewer viewer) {
		super(part, viewer);
		this.part = part;
		deleteAction = new DeleteElementAction(part);
		deleteAction.init();
		initPluginActions();
	}

	Map<String, List<IContextMenuAction>> contextMenuActions = new HashMap<String, List<IContextMenuAction>>();

	private void initPluginActions() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"eu.aniketos.wp1.ststool.diagram.contextcontribution");
		for (IConfigurationElement e : config) {

			if (e.getName().equals("contextaction")) {

				String ec = e.getAttribute("element");
				if (ec != null) {
					try {
						Object obj = e.createExecutableExtension("action");
						if (obj instanceof IContextMenuAction) {
							if (!contextMenuActions.containsKey(ec)) {
								contextMenuActions.put(ec, new ArrayList<IContextMenuAction>());
							}
							List<IContextMenuAction> l = contextMenuActions.get(ec);
							l.add((IContextMenuAction) obj);
							contextMenuActions.put(ec, l);
						}
					} catch (Exception ex) {
					}
				}
			}
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void dispose() {
		if (deleteAction != null) {
			deleteAction.dispose();
			deleteAction = null;
		}
		super.dispose();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void buildContextMenu(final IMenuManager menu) {
		getViewer().flush();
		try {
			TransactionUtil.getEditingDomain((EObject) getViewer().getContents().getModel()).runExclusive(new Runnable() {

				public void run() {

					ContributionItemService.getInstance().contributeToPopupMenu(DiagramEditorContextMenuProvider.this, part);
					menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
					menu.remove("addGroup");
					menu.remove("umlAddGroup");
					menu.remove("addNoteLinkAction");
					menu.remove("deleteFromDiagramAction");
					menu.remove("diagramAddMenu");

					for (IContributionItem i : menu.getItems()) {
						// System.out.println(i);
						if (!i.getId().equals("editGroup"))
							menu.remove(i);
					}

					if (getViewer().getSelection() instanceof IStructuredSelection) {
						IStructuredSelection sel = (IStructuredSelection) getViewer().getSelection();
						if (sel.size() == 1) {

							if (sel.getFirstElement() instanceof StsToolShapeNodeEditPart
									|| sel.getFirstElement() instanceof StsToolConnectionNodeEditPart) {
								menu.appendToGroup("editGroup", deleteAction);
								deleteAction.setText("Delete");
							}

							if (sel.getFirstElement() instanceof StsToolShapeNodeEditPart) {
								final StsToolShapeNodeEditPart ep = (StsToolShapeNodeEditPart) sel.getFirstElement();
								Action rename = new Action() {

									@Override
									public void run() {

										SelectionRequest sr = new SelectionRequest();
										sr.setType(RequestConstants.REQ_OPEN);
										ep.performRequest(sr);
									}
								};
								rename.setText("Rename");
								rename.setDescription("Rename the current element");
								rename.setImageDescriptor(StsToolDiagramEditorPlugin
										.getBundledImageDescriptor("/icons/menu_buttons/rename.gif"));
								menu.add(rename);
							}

							if (sel.getFirstElement() instanceof DelegationEditPart) {
								menu.add(new Separator());
								MenuManager m = new MenuManager("Security Needs");
								menu.add(m);

								MenuManager repudiaition = new MenuManager("Non-Repudiation of");
								m.add(repudiaition);
								repudiaition.add(new SecNeedRepudiationAction("Delegation and Acceptance",
										RepudiationType.DUAL_REPUDIATION, (DelegationEditPart) sel.getFirstElement()));
								repudiaition.add(new SecNeedRepudiationAction("Delegation", RepudiationType.DELEGATOR_REPUDIATION,
										(DelegationEditPart) sel.getFirstElement()));
								repudiaition.add(new SecNeedRepudiationAction("Acceptance", RepudiationType.DELEGATEEE_REPUDIATION,
										(DelegationEditPart) sel.getFirstElement()));

								MenuManager redundancy = new MenuManager("Redundancy");
								m.add(redundancy);
								redundancy.add(new SecNeedRedundancyAction("True Single Actor", RedundancyType.TRUE_SINGLE,
										(DelegationEditPart) sel.getFirstElement()));
								redundancy.add(new SecNeedRedundancyAction("True Multi Actor", RedundancyType.TRUE_MULTI,
										(DelegationEditPart) sel.getFirstElement()));
								redundancy.add(new SecNeedRedundancyAction("Fallback Single Actor", RedundancyType.FALLBACK_SINGLE,
										(DelegationEditPart) sel.getFirstElement()));
								redundancy.add(new SecNeedRedundancyAction("Fallback Multi Actor", RedundancyType.FALLBACK_MULTI,
										(DelegationEditPart) sel.getFirstElement()));

								m.add(new SecNeedTransferableAction("No Delegation", (DelegationEditPart) sel.getFirstElement()));
								m.add(new SecNeedTrustworthiness("Trustworthiness", (DelegationEditPart) sel.getFirstElement()));
								m.add(new SecNeedAvailabilityAction("Availability", (DelegationEditPart) sel.getFirstElement()));

							} else if (sel.getFirstElement() instanceof ProvisionEditPart) {
								menu.add(new Separator());
								MenuManager m = new MenuManager("Security Needs");
								menu.add(m);
								m.add(new SecNeedIntegrityAction("Integrity", (ProvisionEditPart) sel.getFirstElement()));
								m.add(new SecNeedAvailabilityAction("Availability", (ProvisionEditPart) sel.getFirstElement()));
								m.add(new SecNeedConfidentialyAction("Confidentialy", (ProvisionEditPart) sel.getFirstElement()));
							} else if (sel.getFirstElement() instanceof AuthorisationEditPart) {
								((AuthorisationEditPart) sel.getFirstElement()).populateContextMenu(menu);
								// MenuManager m = new
								// MenuManager("Security Needs");
								// menu.add(m);
								// m.add(new
								// AuthorisationTransferabilityAction("Transferability",
								// (AuthorisationEditPart)
								// sel.getFirstElement()));
								menu.add(new Separator());
								menu.add(new AuthorisationTransferabilityAction("Transferability", (AuthorisationEditPart) sel
										.getFirstElement()));

							} else if (sel.getFirstElement() instanceof Goal2EditPart) {
								Goal g = (Goal) ((EditPart) sel.getFirstElement()).getAdapter(EObject.class);
								if (g.getOutgoingDecompositions().size() == 0) {
									menu.add(new Separator());
									menu.add(new CapabilityAction("Capability", (Goal2EditPart) sel.getFirstElement()));
								}
							}

							if (sel.getFirstElement() instanceof StsToolShapeNodeEditPart
									|| sel.getFirstElement() instanceof StsToolConnectionNodeEditPart) {
								StsObject obj = null;
								EditingDomain editDomain = null;
								if (sel.getFirstElement() instanceof StsToolShapeNodeEditPart) {
									obj = (StsObject) ((StsToolShapeNodeEditPart) sel.getFirstElement()).getPrimaryView().getElement();
									editDomain = ((StsToolShapeNodeEditPart) sel.getFirstElement()).getEditingDomain();
								}
								if (sel.getFirstElement() instanceof StsToolConnectionNodeEditPart) {
									obj = (StsObject) ((StsToolConnectionNodeEditPart) sel.getFirstElement()).getPrimaryView().getElement();
									editDomain = ((StsToolConnectionNodeEditPart) sel.getFirstElement()).getEditingDomain();
								}
								if (obj != null && editDomain != null) {
									final StsObject element = obj;
									final EditingDomain ed = editDomain;
									Action description = new Action() {

										@Override
										public void run() {

											try {
												DescriptionDialog dd = new DescriptionDialog(part.getSite().getShell(), element
														.getDescription());
												int ret = dd.open();
												if (ret == IDialogConstants.OK_ID) {
													ed.getCommandStack().execute(
															SetCommand.create(ed, element, StstoolPackage.Literals.STS_OBJECT__DESCRIPTION,
																	dd.getResult()));
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									};
									description.setText("Description");
									// description.setImageDescriptor(StsToolDiagramEditorPlugin.getBundledImageDescriptor("icons/menu_buttons/ExportImage.gif"));
									menu.add(description);
								}
							}

							if (sel.getFirstElement() instanceof StsToolDiagramEditPart) {
								menu.add(new Separator());
								Action export = new Action() {

									@Override
									public void run() {

										try {
											final IWorkbenchWindow window = part.getSite().getWorkbenchWindow();
											if (!(window.getActivePage().getActiveEditor() instanceof StsToolDiagramEditor))
												return;
											CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) window
													.getActivePage().getActiveEditor();
											WizardDialog dialog = new WizardDialog(window.getShell(), new ExportImageWizard(editor));
											dialog.create();
											dialog.setBlockOnOpen(true);
											dialog.open();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								};
								export.setText("Export");
								export.setImageDescriptor(StsToolDiagramEditorPlugin
										.getBundledImageDescriptor("icons/menu_buttons/ExportImage.gif"));
								menu.add(export);
							}
							if (sel.getFirstElement() instanceof StsToolShapeNodeEditPart
									|| sel.getFirstElement() instanceof StsToolConnectionNodeEditPart) {
								EObject e = ((View) ((EditPart) sel.getFirstElement()).getModel()).getElement();
								String eClass = e.eClass().getName();
								if (contextMenuActions.containsKey(eClass)) {
									for (IContextMenuAction cma : contextMenuActions.get(eClass)) {
										cma.setSelectedElement(e);
										menu.add(cma);
									}

								}
							}
						}
					}

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			StsToolDiagramEditorPlugin.getInstance().logError("Error building context menu", e);
		}
	}
}
