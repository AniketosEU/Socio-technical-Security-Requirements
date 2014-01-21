/*
* StsToolDiagramCanonicalEditPolicy.java
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
package eu.aniketos.wp1.ststool.diagram.edit.policies;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalConnectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DependencyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IncompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ModifyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NeedEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NegativeGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.OwnEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PartOfEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PlayEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PositiveGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProduceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProvisionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramUpdater;
import eu.aniketos.wp1.ststool.diagram.part.StsToolLinkDescriptor;
import eu.aniketos.wp1.ststool.diagram.part.StsToolNodeDescriptor;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;

/**
 * @generated
 */
public class StsToolDiagramCanonicalEditPolicy extends CanonicalConnectionEditPolicy {

	/**
	 * @generated
	 */
	Set	myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	@Override
	protected List getSemanticChildrenList(){
		View viewObject = (View) getHost().getModel();
		List result = new LinkedList();
		for (Iterator it = StsToolDiagramUpdater.getStsToolDiagram_1000SemanticChildren(viewObject).iterator(); it.hasNext();) {
			result.add(((StsToolNodeDescriptor) it.next()).getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	protected boolean shouldDeleteView(View view){
		return true;
	}

	/**
	 * @generated
	 */
	@Override
	protected boolean isOrphaned(Collection semanticChildren,final View view){
		int visualID = StsToolVisualIDRegistry.getVisualID(view);
		switch (visualID) {
			case AgentEditPart.VISUAL_ID:
			case RoleEditPart.VISUAL_ID:
			case GoalEditPart.VISUAL_ID:
			case TResourceEditPart.VISUAL_ID:
			case IResourceEditPart.VISUAL_ID:
			case EventEditPart.VISUAL_ID:
				if (!semanticChildren.contains(view.getElement())) { return true; }
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected String getDefaultFactoryHint(){
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected Set getFeaturesToSynchronize(){
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet();
			myFeaturesToSynchronize.add(StstoolPackage.eINSTANCE.getStsToolDiagram_DiagActors());
			myFeaturesToSynchronize.add(StstoolPackage.eINSTANCE.getStsToolDiagram_DiagGoals());
			myFeaturesToSynchronize.add(StstoolPackage.eINSTANCE.getStsToolDiagram_DiagTResources());
			myFeaturesToSynchronize.add(StstoolPackage.eINSTANCE.getStsToolDiagram_DiagIResources());
			myFeaturesToSynchronize.add(StstoolPackage.eINSTANCE.getStsToolDiagram_DiagEvents());
		}
		return myFeaturesToSynchronize;
	}

	/**
	 * @generated
	 */
	@Override
	protected List getSemanticConnectionsList(){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	protected EObject getSourceElement(EObject relationship){
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected EObject getTargetElement(EObject relationship){
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected boolean shouldIncludeConnection(Edge connector,Collection children){
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void refreshSemantic(){
		List createdViews = new LinkedList();
		createdViews.addAll(refreshSemanticChildren());
		List createdConnectionViews = new LinkedList();
		createdConnectionViews.addAll(refreshSemanticConnections());
		createdConnectionViews.addAll(refreshConnections());

		if (createdViews.size() > 1) {
			// perform a layout of the container
			DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host().getEditingDomain(), createdViews, host());
			executeCommand(new ICommandProxy(layoutCmd));
		}

		createdViews.addAll(createdConnectionViews);
		makeViewsImmutable(createdViews);
	}

	/**
	 * @generated
	 */
	private Diagram getDiagram(){
		return ((View) getHost().getModel()).getDiagram();
	}

	/**
	 * @generated
	 */
	private Collection refreshConnections(){
		Map domain2NotationMap = new HashMap();
		Collection linkDescriptors = collectAllLinks(getDiagram(), domain2NotationMap);
		Collection existingLinks = new LinkedList(getDiagram().getEdges());
		for (Iterator linksIterator = existingLinks.iterator(); linksIterator.hasNext();) {
			Edge nextDiagramLink = (Edge) linksIterator.next();
			int diagramLinkVisualID = StsToolVisualIDRegistry.getVisualID(nextDiagramLink);
			if (diagramLinkVisualID == -1) {
				if (nextDiagramLink.getSource() != null && nextDiagramLink.getTarget() != null) {
					linksIterator.remove();
				}
				continue;
			}
			EObject diagramLinkObject = nextDiagramLink.getElement();
			EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
			EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
			for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator.hasNext();) {
				StsToolLinkDescriptor nextLinkDescriptor = (StsToolLinkDescriptor) linkDescriptorsIterator.next();
				if (diagramLinkObject == nextLinkDescriptor.getModelElement() && diagramLinkSrc == nextLinkDescriptor.getSource() && diagramLinkDst == nextLinkDescriptor.getDestination() && diagramLinkVisualID == nextLinkDescriptor.getVisualID()) {
					linksIterator.remove();
					linkDescriptorsIterator.remove();
					break;
				}
			}
		}
		deleteViews(existingLinks.iterator());
		return createConnections(linkDescriptors, domain2NotationMap);
	}

	/**
	 * @generated
	 */
	private Collection collectAllLinks(View view,Map domain2NotationMap){
		if (!StsToolDiagramEditPart.MODEL_ID.equals(StsToolVisualIDRegistry.getModelID(view))) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		switch (StsToolVisualIDRegistry.getVisualID(view)) {
			case StsToolDiagramEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getStsToolDiagram_1000ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case AgentEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getAgent_2001ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case RoleEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getRole_2002ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case GoalEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getGoal_2003ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case TResourceEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getTResource_2004ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case IResourceEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getIResource_2005ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case EventEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getEvent_2006ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case Goal2EditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getGoal_3001ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case TResource2EditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getTResource_3002ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case NeedEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getNeed_4001ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case ProduceEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getProduce_4002ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case ModifyEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getModify_4003ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case PositiveGoalContributionEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getPositiveGoalContribution_4004ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case NegativeGoalContributionEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getNegativeGoalContribution_4005ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case GoalDecompositionOREditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getGoalDecompositionOR_4006ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case GoalDecompositionANDEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getGoalDecompositionAND_4007ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case OwnEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getOwn_4008ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case PartOfEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getPartOf_4009ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case TangibleByEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getTangibleBy_4010ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case PlayEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getPlay_4011ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case ProvisionEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getProvision_4012ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case DelegationEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getDelegation_4013ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case AuthorisationEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getAuthorisation_4014ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case ThreatEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getThreat_4015ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case IncompatibleDutiesEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getIncompatibleDuties_4016ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case CompatibleDutiesEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getCompatibleDuties_4017ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
			case DependencyEditPart.VISUAL_ID: {
				if (!domain2NotationMap.containsKey(view.getElement())) {
					result.addAll(StsToolDiagramUpdater.getDependency_4018ContainedLinks(view));
				}
				if (!domain2NotationMap.containsKey(view.getElement()) || view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
					domain2NotationMap.put(view.getElement(), view);
				}
				break;
			}
		}
		for (Iterator children = view.getChildren().iterator(); children.hasNext();) {
			result.addAll(collectAllLinks((View) children.next(), domain2NotationMap));
		}
		for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
			result.addAll(collectAllLinks((View) edges.next(), domain2NotationMap));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection createConnections(Collection linkDescriptors,Map domain2NotationMap){
		List adapters = new LinkedList();
		for (Iterator linkDescriptorsIterator = linkDescriptors.iterator(); linkDescriptorsIterator.hasNext();) {
			final StsToolLinkDescriptor nextLinkDescriptor = (StsToolLinkDescriptor) linkDescriptorsIterator.next();
			EditPart sourceEditPart = getEditPart(nextLinkDescriptor.getSource(), domain2NotationMap);
			EditPart targetEditPart = getEditPart(nextLinkDescriptor.getDestination(), domain2NotationMap);
			if (sourceEditPart == null || targetEditPart == null) {
				continue;
			}
			CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(nextLinkDescriptor.getSemanticAdapter(), String.valueOf(nextLinkDescriptor.getVisualID()), ViewUtil.APPEND, false, ((IGraphicalEditPart) getHost()).getDiagramPreferencesHint());
			CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(descriptor);
			ccr.setType(RequestConstants.REQ_CONNECTION_START);
			ccr.setSourceEditPart(sourceEditPart);
			sourceEditPart.getCommand(ccr);
			ccr.setTargetEditPart(targetEditPart);
			ccr.setType(RequestConstants.REQ_CONNECTION_END);
			Command cmd = targetEditPart.getCommand(ccr);
			if (cmd != null && cmd.canExecute()) {
				executeCommand(cmd);
				IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
				if (viewAdapter != null) {
					adapters.add(viewAdapter);
				}
			}
		}
		return adapters;
	}

	/**
	 * @generated
	 */
	private EditPart getEditPart(EObject domainModelElement,Map domain2NotationMap){
		View view = (View) domain2NotationMap.get(domainModelElement);
		if (view != null) { return (EditPart) getHost().getViewer().getEditPartRegistry().get(view); }
		return null;
	}
}
