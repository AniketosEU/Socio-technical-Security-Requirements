/*
* StsToolDiagramUpdater.java
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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Dependency;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalDecompositionAND;
import eu.aniketos.wp1.ststool.GoalDecompositionOR;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.NegativeGoalContribution;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.PositiveGoalContribution;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentAgentCompartmentEditPart;
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
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleRoleCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class StsToolDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view){
		switch (StsToolVisualIDRegistry.getVisualID(view)) {
			case AgentAgentCompartmentEditPart.VISUAL_ID:
				return getAgentAgentCompartment_7001SemanticChildren(view);
			case RoleRoleCompartmentEditPart.VISUAL_ID:
				return getRoleRoleCompartment_7002SemanticChildren(view);
			case StsToolDiagramEditPart.VISUAL_ID:
				return getStsToolDiagram_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAgentAgentCompartment_7001SemanticChildren(View view){
		if (false == view.eContainer() instanceof View) { return Collections.EMPTY_LIST; }
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) { return Collections.EMPTY_LIST; }
		Agent modelElement = (Agent) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getGoals().iterator(); it.hasNext();) {
			Goal childElement = (Goal) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == Goal2EditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getTResources().iterator(); it.hasNext();) {
			TResource childElement = (TResource) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TResource2EditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRoleRoleCompartment_7002SemanticChildren(View view){
		if (false == view.eContainer() instanceof View) { return Collections.EMPTY_LIST; }
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) { return Collections.EMPTY_LIST; }
		Role modelElement = (Role) containerView.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getGoals().iterator(); it.hasNext();) {
			Goal childElement = (Goal) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == Goal2EditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getTResources().iterator(); it.hasNext();) {
			TResource childElement = (TResource) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TResource2EditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getStsToolDiagram_1000SemanticChildren(View view){
		if (!view.isSetElement()) { return Collections.EMPTY_LIST; }
		StsToolDiagram modelElement = (StsToolDiagram) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getDiagActors().iterator(); it.hasNext();) {
			Actor childElement = (Actor) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == AgentEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == RoleEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getDiagGoals().iterator(); it.hasNext();) {
			Goal childElement = (Goal) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == GoalEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getDiagTResources().iterator(); it.hasNext();) {
			TResource childElement = (TResource) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == TResourceEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getDiagIResources().iterator(); it.hasNext();) {
			IResource childElement = (IResource) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IResourceEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator it = modelElement.getDiagEvents().iterator(); it.hasNext();) {
			Event childElement = (Event) it.next();
			int visualID = StsToolVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == EventEditPart.VISUAL_ID) {
				result.add(new StsToolNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view){
		switch (StsToolVisualIDRegistry.getVisualID(view)) {
			case StsToolDiagramEditPart.VISUAL_ID:
				return getStsToolDiagram_1000ContainedLinks(view);
			case AgentEditPart.VISUAL_ID:
				return getAgent_2001ContainedLinks(view);
			case RoleEditPart.VISUAL_ID:
				return getRole_2002ContainedLinks(view);
			case GoalEditPart.VISUAL_ID:
				return getGoal_2003ContainedLinks(view);
			case TResourceEditPart.VISUAL_ID:
				return getTResource_2004ContainedLinks(view);
			case IResourceEditPart.VISUAL_ID:
				return getIResource_2005ContainedLinks(view);
			case EventEditPart.VISUAL_ID:
				return getEvent_2006ContainedLinks(view);
			case Goal2EditPart.VISUAL_ID:
				return getGoal_3001ContainedLinks(view);
			case TResource2EditPart.VISUAL_ID:
				return getTResource_3002ContainedLinks(view);
			case NeedEditPart.VISUAL_ID:
				return getNeed_4001ContainedLinks(view);
			case ProduceEditPart.VISUAL_ID:
				return getProduce_4002ContainedLinks(view);
			case ModifyEditPart.VISUAL_ID:
				return getModify_4003ContainedLinks(view);
			case PositiveGoalContributionEditPart.VISUAL_ID:
				return getPositiveGoalContribution_4004ContainedLinks(view);
			case NegativeGoalContributionEditPart.VISUAL_ID:
				return getNegativeGoalContribution_4005ContainedLinks(view);
			case GoalDecompositionOREditPart.VISUAL_ID:
				return getGoalDecompositionOR_4006ContainedLinks(view);
			case GoalDecompositionANDEditPart.VISUAL_ID:
				return getGoalDecompositionAND_4007ContainedLinks(view);
			case OwnEditPart.VISUAL_ID:
				return getOwn_4008ContainedLinks(view);
			case PartOfEditPart.VISUAL_ID:
				return getPartOf_4009ContainedLinks(view);
			case TangibleByEditPart.VISUAL_ID:
				return getTangibleBy_4010ContainedLinks(view);
			case PlayEditPart.VISUAL_ID:
				return getPlay_4011ContainedLinks(view);
			case ProvisionEditPart.VISUAL_ID:
				return getProvision_4012ContainedLinks(view);
			case DelegationEditPart.VISUAL_ID:
				return getDelegation_4013ContainedLinks(view);
			case AuthorisationEditPart.VISUAL_ID:
				return getAuthorisation_4014ContainedLinks(view);
			case ThreatEditPart.VISUAL_ID:
				return getThreat_4015ContainedLinks(view);
			case IncompatibleDutiesEditPart.VISUAL_ID:
				return getIncompatibleDuties_4016ContainedLinks(view);
			case CompatibleDutiesEditPart.VISUAL_ID:
				return getCompatibleDuties_4017ContainedLinks(view);
			case DependencyEditPart.VISUAL_ID:
				return getDependency_4018ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view){
		switch (StsToolVisualIDRegistry.getVisualID(view)) {
			case AgentEditPart.VISUAL_ID:
				return getAgent_2001IncomingLinks(view);
			case RoleEditPart.VISUAL_ID:
				return getRole_2002IncomingLinks(view);
			case GoalEditPart.VISUAL_ID:
				return getGoal_2003IncomingLinks(view);
			case TResourceEditPart.VISUAL_ID:
				return getTResource_2004IncomingLinks(view);
			case IResourceEditPart.VISUAL_ID:
				return getIResource_2005IncomingLinks(view);
			case EventEditPart.VISUAL_ID:
				return getEvent_2006IncomingLinks(view);
			case Goal2EditPart.VISUAL_ID:
				return getGoal_3001IncomingLinks(view);
			case TResource2EditPart.VISUAL_ID:
				return getTResource_3002IncomingLinks(view);
			case NeedEditPart.VISUAL_ID:
				return getNeed_4001IncomingLinks(view);
			case ProduceEditPart.VISUAL_ID:
				return getProduce_4002IncomingLinks(view);
			case ModifyEditPart.VISUAL_ID:
				return getModify_4003IncomingLinks(view);
			case PositiveGoalContributionEditPart.VISUAL_ID:
				return getPositiveGoalContribution_4004IncomingLinks(view);
			case NegativeGoalContributionEditPart.VISUAL_ID:
				return getNegativeGoalContribution_4005IncomingLinks(view);
			case GoalDecompositionOREditPart.VISUAL_ID:
				return getGoalDecompositionOR_4006IncomingLinks(view);
			case GoalDecompositionANDEditPart.VISUAL_ID:
				return getGoalDecompositionAND_4007IncomingLinks(view);
			case OwnEditPart.VISUAL_ID:
				return getOwn_4008IncomingLinks(view);
			case PartOfEditPart.VISUAL_ID:
				return getPartOf_4009IncomingLinks(view);
			case TangibleByEditPart.VISUAL_ID:
				return getTangibleBy_4010IncomingLinks(view);
			case PlayEditPart.VISUAL_ID:
				return getPlay_4011IncomingLinks(view);
			case ProvisionEditPart.VISUAL_ID:
				return getProvision_4012IncomingLinks(view);
			case DelegationEditPart.VISUAL_ID:
				return getDelegation_4013IncomingLinks(view);
			case AuthorisationEditPart.VISUAL_ID:
				return getAuthorisation_4014IncomingLinks(view);
			case ThreatEditPart.VISUAL_ID:
				return getThreat_4015IncomingLinks(view);
			case IncompatibleDutiesEditPart.VISUAL_ID:
				return getIncompatibleDuties_4016IncomingLinks(view);
			case CompatibleDutiesEditPart.VISUAL_ID:
				return getCompatibleDuties_4017IncomingLinks(view);
			case DependencyEditPart.VISUAL_ID:
				return getDependency_4018IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view){
		switch (StsToolVisualIDRegistry.getVisualID(view)) {
			case AgentEditPart.VISUAL_ID:
				return getAgent_2001OutgoingLinks(view);
			case RoleEditPart.VISUAL_ID:
				return getRole_2002OutgoingLinks(view);
			case GoalEditPart.VISUAL_ID:
				return getGoal_2003OutgoingLinks(view);
			case TResourceEditPart.VISUAL_ID:
				return getTResource_2004OutgoingLinks(view);
			case IResourceEditPart.VISUAL_ID:
				return getIResource_2005OutgoingLinks(view);
			case EventEditPart.VISUAL_ID:
				return getEvent_2006OutgoingLinks(view);
			case Goal2EditPart.VISUAL_ID:
				return getGoal_3001OutgoingLinks(view);
			case TResource2EditPart.VISUAL_ID:
				return getTResource_3002OutgoingLinks(view);
			case NeedEditPart.VISUAL_ID:
				return getNeed_4001OutgoingLinks(view);
			case ProduceEditPart.VISUAL_ID:
				return getProduce_4002OutgoingLinks(view);
			case ModifyEditPart.VISUAL_ID:
				return getModify_4003OutgoingLinks(view);
			case PositiveGoalContributionEditPart.VISUAL_ID:
				return getPositiveGoalContribution_4004OutgoingLinks(view);
			case NegativeGoalContributionEditPart.VISUAL_ID:
				return getNegativeGoalContribution_4005OutgoingLinks(view);
			case GoalDecompositionOREditPart.VISUAL_ID:
				return getGoalDecompositionOR_4006OutgoingLinks(view);
			case GoalDecompositionANDEditPart.VISUAL_ID:
				return getGoalDecompositionAND_4007OutgoingLinks(view);
			case OwnEditPart.VISUAL_ID:
				return getOwn_4008OutgoingLinks(view);
			case PartOfEditPart.VISUAL_ID:
				return getPartOf_4009OutgoingLinks(view);
			case TangibleByEditPart.VISUAL_ID:
				return getTangibleBy_4010OutgoingLinks(view);
			case PlayEditPart.VISUAL_ID:
				return getPlay_4011OutgoingLinks(view);
			case ProvisionEditPart.VISUAL_ID:
				return getProvision_4012OutgoingLinks(view);
			case DelegationEditPart.VISUAL_ID:
				return getDelegation_4013OutgoingLinks(view);
			case AuthorisationEditPart.VISUAL_ID:
				return getAuthorisation_4014OutgoingLinks(view);
			case ThreatEditPart.VISUAL_ID:
				return getThreat_4015OutgoingLinks(view);
			case IncompatibleDutiesEditPart.VISUAL_ID:
				return getIncompatibleDuties_4016OutgoingLinks(view);
			case CompatibleDutiesEditPart.VISUAL_ID:
				return getCompatibleDuties_4017OutgoingLinks(view);
			case DependencyEditPart.VISUAL_ID:
				return getDependency_4018OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getStsToolDiagram_1000ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAgent_2001ContainedLinks(View view){
		Agent modelElement = (Agent) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Own_4008(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Play_4011(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Provision_4012(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Delegation_4013(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Authorisation_4014(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2002ContainedLinks(View view){
		Role modelElement = (Role) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Own_4008(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Provision_4012(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Delegation_4013(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Authorisation_4014(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Dependency_4018(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGoal_2003ContainedLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Need_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Produce_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Modify_4003(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_2004ContainedLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_PartOf_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getIResource_2005ContainedLinks(View view){
		IResource modelElement = (IResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_PartOf_4009(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_TangibleBy_4010(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006ContainedLinks(View view){
		Event modelElement = (Event) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Threat_4015(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGoal_3001ContainedLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Need_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Produce_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Modify_4003(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_3002ContainedLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_PartOf_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNeed_4001ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProduce_4002ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModify_4003ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPositiveGoalContribution_4004ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNegativeGoalContribution_4005ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionOR_4006ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionAND_4007ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOwn_4008ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPartOf_4009ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTangibleBy_4010ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPlay_4011ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProvision_4012ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDelegation_4013ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAuthorisation_4014ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getThreat_4015ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncompatibleDuties_4016ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCompatibleDuties_4017ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDependency_4018ContainedLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAgent_2001IncomingLinks(View view){
		Agent modelElement = (Agent) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Provision_4012(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Delegation_4013(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Authorisation_4014(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2002IncomingLinks(View view){
		Role modelElement = (Role) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Play_4011(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Provision_4012(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Delegation_4013(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Authorisation_4014(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CompatibleDuties_4017(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Dependency_4018(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGoal_2003IncomingLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CompatibleDuties_4017(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_2004IncomingLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Need_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Produce_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Modify_4003(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_PartOf_4009(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TangibleBy_4010(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getIResource_2005IncomingLinks(View view){
		IResource modelElement = (IResource) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Own_4008(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_PartOf_4009(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoal_3001IncomingLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_CompatibleDuties_4017(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_3002IncomingLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Need_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Produce_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Modify_4003(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_PartOf_4009(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TangibleBy_4010(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNeed_4001IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProduce_4002IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModify_4003IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPositiveGoalContribution_4004IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNegativeGoalContribution_4005IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionOR_4006IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionAND_4007IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOwn_4008IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPartOf_4009IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTangibleBy_4010IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPlay_4011IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProvision_4012IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDelegation_4013IncomingLinks(View view){
		Delegation modelElement = (Delegation) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Threat_4015(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAuthorisation_4014IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getThreat_4015IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncompatibleDuties_4016IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCompatibleDuties_4017IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDependency_4018IncomingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAgent_2001OutgoingLinks(View view){
		Agent modelElement = (Agent) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Own_4008(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Play_4011(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Provision_4012(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Delegation_4013(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Authorisation_4014(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getRole_2002OutgoingLinks(View view){
		Role modelElement = (Role) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Own_4008(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Provision_4012(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Delegation_4013(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Authorisation_4014(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Dependency_4018(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGoal_2003OutgoingLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Need_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Produce_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Modify_4003(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_2004OutgoingLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_PartOf_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getIResource_2005OutgoingLinks(View view){
		IResource modelElement = (IResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_PartOf_4009(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_TangibleBy_4010(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006OutgoingLinks(View view){
		Event modelElement = (Event) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Threat_4015(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGoal_3001OutgoingLinks(View view){
		Goal modelElement = (Goal) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Need_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Produce_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Modify_4003(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_PositiveGoalContribution_4004(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_NegativeGoalContribution_4005(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_GoalDecompositionOR_4006(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_GoalDecompositionAND_4007(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_IncompatibleDuties_4016(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_CompatibleDuties_4017(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTResource_3002OutgoingLinks(View view){
		TResource modelElement = (TResource) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_PartOf_4009(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getNeed_4001OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProduce_4002OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getModify_4003OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPositiveGoalContribution_4004OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getNegativeGoalContribution_4005OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionOR_4006OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGoalDecompositionAND_4007OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOwn_4008OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPartOf_4009OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTangibleBy_4010OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPlay_4011OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProvision_4012OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDelegation_4013OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAuthorisation_4014OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getThreat_4015OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncompatibleDuties_4016OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCompatibleDuties_4017OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDependency_4018OutgoingLinks(View view){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Need_4001(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getResourceNeeded().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Need) {
				continue;
			}
			Need link = (Need) linkObject;
			if (NeedEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Need_4001, NeedEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Produce_4002(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getResourcesProduced().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Produce) {
				continue;
			}
			Produce link = (Produce) linkObject;
			if (ProduceEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Produce_4002, ProduceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Modify_4003(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getResourcesModified().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Modify) {
				continue;
			}
			Modify link = (Modify) linkObject;
			if (ModifyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Modify_4003, ModifyEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_PositiveGoalContribution_4004(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingContributions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PositiveGoalContribution) {
				continue;
			}
			PositiveGoalContribution link = (PositiveGoalContribution) linkObject;
			if (PositiveGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.PositiveGoalContribution_4004, PositiveGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_NegativeGoalContribution_4005(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingContributions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof NegativeGoalContribution) {
				continue;
			}
			NegativeGoalContribution link = (NegativeGoalContribution) linkObject;
			if (NegativeGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.NegativeGoalContribution_4005, NegativeGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_GoalDecompositionOR_4006(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDecompositions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof GoalDecompositionOR) {
				continue;
			}
			GoalDecompositionOR link = (GoalDecompositionOR) linkObject;
			if (GoalDecompositionOREditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.GoalDecompositionOR_4006, GoalDecompositionOREditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_GoalDecompositionAND_4007(Goal container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDecompositions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof GoalDecompositionAND) {
				continue;
			}
			GoalDecompositionAND link = (GoalDecompositionAND) linkObject;
			if (GoalDecompositionANDEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.GoalDecompositionAND_4007, GoalDecompositionANDEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Own_4008(Actor container){
		Collection result = new LinkedList();
		for (Iterator links = container.getIResources().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Own) {
				continue;
			}
			Own link = (Own) linkObject;
			if (OwnEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			IResource dst = link.getTarget();
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Own_4008, OwnEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_PartOf_4009(Resource container){
		Collection result = new LinkedList();
		for (Iterator links = container.getPartsOf().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PartOf) {
				continue;
			}
			PartOf link = (PartOf) linkObject;
			if (PartOfEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Resource dst = link.getTarget();
			Resource src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.PartOf_4009, PartOfEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_TangibleBy_4010(IResource container){
		Collection result = new LinkedList();
		for (Iterator links = container.getTangibleElements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof TangibleBy) {
				continue;
			}
			TangibleBy link = (TangibleBy) linkObject;
			if (TangibleByEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			IResource src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.TangibleBy_4010, TangibleByEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Play_4011(Agent container){
		Collection result = new LinkedList();
		for (Iterator links = container.getPlayedRoles().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Play) {
				continue;
			}
			Play link = (Play) linkObject;
			if (PlayEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Role dst = link.getTarget();
			Agent src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Play_4011, PlayEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Provision_4012(Actor container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingProvisions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Provision) {
				continue;
			}
			Provision link = (Provision) linkObject;
			if (ProvisionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Provision_4012, ProvisionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Delegation_4013(Actor container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDelegations().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Delegation) {
				continue;
			}
			Delegation link = (Delegation) linkObject;
			if (DelegationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Delegation_4013, DelegationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Authorisation_4014(Actor container){
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingAuthorisations().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Authorisation) {
				continue;
			}
			Authorisation link = (Authorisation) linkObject;
			if (AuthorisationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Authorisation_4014, AuthorisationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Threat_4015(Event container){
		Collection result = new LinkedList();
		for (Iterator links = container.getThreatedElements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Threat) {
				continue;
			}
			Threat link = (Threat) linkObject;
			if (ThreatEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Threatable dst = link.getTarget();
			Event src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Threat_4015, ThreatEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_IncompatibleDuties_4016(SeparationOfDuties container){
		Collection result = new LinkedList();
		for (Iterator links = container.getIncompatibleDutiesOut().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof IncompatibleDuties) {
				continue;
			}
			IncompatibleDuties link = (IncompatibleDuties) linkObject;
			if (IncompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			SeparationOfDuties dst = link.getTarget();
			SeparationOfDuties src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.IncompatibleDuties_4016, IncompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_CompatibleDuties_4017(BindingOfDuties container){
		Collection result = new LinkedList();
		for (Iterator links = container.getCompatibleDutiesOut().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CompatibleDuties) {
				continue;
			}
			CompatibleDuties link = (CompatibleDuties) linkObject;
			if (CompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			BindingOfDuties dst = link.getTarget();
			BindingOfDuties src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.CompatibleDuties_4017, CompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Dependency_4018(Role container){
		Collection result = new LinkedList();
		for (Iterator links = container.getDependBy().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Dependency) {
				continue;
			}
			Dependency link = (Dependency) linkObject;
			if (DependencyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Role dst = link.getTarget();
			Role src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Dependency_4018, DependencyEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Need_4001(TResource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getNeed_Target() || false == setting.getEObject() instanceof Need) {
				continue;
			}
			Need link = (Need) setting.getEObject();
			if (NeedEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Need_4001, NeedEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Produce_4002(TResource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getProduce_Target() || false == setting.getEObject() instanceof Produce) {
				continue;
			}
			Produce link = (Produce) setting.getEObject();
			if (ProduceEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Produce_4002, ProduceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Modify_4003(TResource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getModify_Target() || false == setting.getEObject() instanceof Modify) {
				continue;
			}
			Modify link = (Modify) setting.getEObject();
			if (ModifyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Modify_4003, ModifyEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_PositiveGoalContribution_4004(Goal target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getGoalContribution_Target() || false == setting.getEObject() instanceof PositiveGoalContribution) {
				continue;
			}
			PositiveGoalContribution link = (PositiveGoalContribution) setting.getEObject();
			if (PositiveGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.PositiveGoalContribution_4004, PositiveGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_NegativeGoalContribution_4005(Goal target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getGoalContribution_Target() || false == setting.getEObject() instanceof NegativeGoalContribution) {
				continue;
			}
			NegativeGoalContribution link = (NegativeGoalContribution) setting.getEObject();
			if (NegativeGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.NegativeGoalContribution_4005, NegativeGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_GoalDecompositionOR_4006(Goal target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getGoalDecomposition_Target() || false == setting.getEObject() instanceof GoalDecompositionOR) {
				continue;
			}
			GoalDecompositionOR link = (GoalDecompositionOR) setting.getEObject();
			if (GoalDecompositionOREditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.GoalDecompositionOR_4006, GoalDecompositionOREditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_GoalDecompositionAND_4007(Goal target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getGoalDecomposition_Target() || false == setting.getEObject() instanceof GoalDecompositionAND) {
				continue;
			}
			GoalDecompositionAND link = (GoalDecompositionAND) setting.getEObject();
			if (GoalDecompositionANDEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.GoalDecompositionAND_4007, GoalDecompositionANDEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Own_4008(IResource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getOwn_Target() || false == setting.getEObject() instanceof Own) {
				continue;
			}
			Own link = (Own) setting.getEObject();
			if (OwnEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Own_4008, OwnEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_PartOf_4009(Resource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getPartOf_Target() || false == setting.getEObject() instanceof PartOf) {
				continue;
			}
			PartOf link = (PartOf) setting.getEObject();
			if (PartOfEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Resource src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.PartOf_4009, PartOfEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_TangibleBy_4010(TResource target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getTangibleBy_Target() || false == setting.getEObject() instanceof TangibleBy) {
				continue;
			}
			TangibleBy link = (TangibleBy) setting.getEObject();
			if (TangibleByEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			IResource src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.TangibleBy_4010, TangibleByEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Play_4011(Role target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getPlay_Target() || false == setting.getEObject() instanceof Play) {
				continue;
			}
			Play link = (Play) setting.getEObject();
			if (PlayEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Agent src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Play_4011, PlayEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Provision_4012(Actor target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getProvision_Target() || false == setting.getEObject() instanceof Provision) {
				continue;
			}
			Provision link = (Provision) setting.getEObject();
			if (ProvisionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Provision_4012, ProvisionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Delegation_4013(Actor target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getDelegation_Target() || false == setting.getEObject() instanceof Delegation) {
				continue;
			}
			Delegation link = (Delegation) setting.getEObject();
			if (DelegationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Delegation_4013, DelegationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Authorisation_4014(Actor target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getAuthorisation_Target() || false == setting.getEObject() instanceof Authorisation) {
				continue;
			}
			Authorisation link = (Authorisation) setting.getEObject();
			if (AuthorisationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Authorisation_4014, AuthorisationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Threat_4015(Threatable target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getThreat_Target() || false == setting.getEObject() instanceof Threat) {
				continue;
			}
			Threat link = (Threat) setting.getEObject();
			if (ThreatEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Event src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Threat_4015, ThreatEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_IncompatibleDuties_4016(SeparationOfDuties target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getIncompatibleDuties_Target() || false == setting.getEObject() instanceof IncompatibleDuties) {
				continue;
			}
			IncompatibleDuties link = (IncompatibleDuties) setting.getEObject();
			if (IncompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			SeparationOfDuties src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.IncompatibleDuties_4016, IncompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_CompatibleDuties_4017(BindingOfDuties target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getCompatibleDuties_Target() || false == setting.getEObject() instanceof CompatibleDuties) {
				continue;
			}
			CompatibleDuties link = (CompatibleDuties) setting.getEObject();
			if (CompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			BindingOfDuties src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.CompatibleDuties_4017, CompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Dependency_4018(Role target,Map crossReferences){
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it.next();
			if (setting.getEStructuralFeature() != StstoolPackage.eINSTANCE.getDependency_Target() || false == setting.getEObject() instanceof Dependency) {
				continue;
			}
			Dependency link = (Dependency) setting.getEObject();
			if (DependencyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Role src = link.getSource();
			result.add(new StsToolLinkDescriptor(src, target, link, StsToolElementTypes.Dependency_4018, DependencyEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Need_4001(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getResourceNeeded().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Need) {
				continue;
			}
			Need link = (Need) linkObject;
			if (NeedEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Need_4001, NeedEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Produce_4002(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getResourcesProduced().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Produce) {
				continue;
			}
			Produce link = (Produce) linkObject;
			if (ProduceEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Produce_4002, ProduceEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Modify_4003(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getResourcesModified().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Modify) {
				continue;
			}
			Modify link = (Modify) linkObject;
			if (ModifyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Modify_4003, ModifyEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_PositiveGoalContribution_4004(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingContributions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PositiveGoalContribution) {
				continue;
			}
			PositiveGoalContribution link = (PositiveGoalContribution) linkObject;
			if (PositiveGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.PositiveGoalContribution_4004, PositiveGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_NegativeGoalContribution_4005(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingContributions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof NegativeGoalContribution) {
				continue;
			}
			NegativeGoalContribution link = (NegativeGoalContribution) linkObject;
			if (NegativeGoalContributionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.NegativeGoalContribution_4005, NegativeGoalContributionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_GoalDecompositionOR_4006(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDecompositions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof GoalDecompositionOR) {
				continue;
			}
			GoalDecompositionOR link = (GoalDecompositionOR) linkObject;
			if (GoalDecompositionOREditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.GoalDecompositionOR_4006, GoalDecompositionOREditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_GoalDecompositionAND_4007(Goal source){
		Goal container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Goal) {
				container = (Goal) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDecompositions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof GoalDecompositionAND) {
				continue;
			}
			GoalDecompositionAND link = (GoalDecompositionAND) linkObject;
			if (GoalDecompositionANDEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Goal dst = link.getTarget();
			Goal src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.GoalDecompositionAND_4007, GoalDecompositionANDEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Own_4008(Actor source){
		Actor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Actor) {
				container = (Actor) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getIResources().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Own) {
				continue;
			}
			Own link = (Own) linkObject;
			if (OwnEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			IResource dst = link.getTarget();
			Actor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Own_4008, OwnEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_PartOf_4009(Resource source){
		Resource container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Resource) {
				container = (Resource) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getPartsOf().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PartOf) {
				continue;
			}
			PartOf link = (PartOf) linkObject;
			if (PartOfEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Resource dst = link.getTarget();
			Resource src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.PartOf_4009, PartOfEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_TangibleBy_4010(IResource source){
		IResource container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof IResource) {
				container = (IResource) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getTangibleElements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof TangibleBy) {
				continue;
			}
			TangibleBy link = (TangibleBy) linkObject;
			if (TangibleByEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TResource dst = link.getTarget();
			IResource src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.TangibleBy_4010, TangibleByEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Play_4011(Agent source){
		Agent container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Agent) {
				container = (Agent) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getPlayedRoles().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Play) {
				continue;
			}
			Play link = (Play) linkObject;
			if (PlayEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Role dst = link.getTarget();
			Agent src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Play_4011, PlayEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Provision_4012(Actor source){
		Actor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Actor) {
				container = (Actor) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingProvisions().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Provision) {
				continue;
			}
			Provision link = (Provision) linkObject;
			if (ProvisionEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Provision_4012, ProvisionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Delegation_4013(Actor source){
		Actor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Actor) {
				container = (Actor) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingDelegations().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Delegation) {
				continue;
			}
			Delegation link = (Delegation) linkObject;
			if (DelegationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Delegation_4013, DelegationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Authorisation_4014(Actor source){
		Actor container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Actor) {
				container = (Actor) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getOutgoingAuthorisations().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Authorisation) {
				continue;
			}
			Authorisation link = (Authorisation) linkObject;
			if (AuthorisationEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor dst = link.getTarget();
			Actor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Authorisation_4014, AuthorisationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Threat_4015(Event source){
		Event container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Event) {
				container = (Event) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getThreatedElements().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Threat) {
				continue;
			}
			Threat link = (Threat) linkObject;
			if (ThreatEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Threatable dst = link.getTarget();
			Event src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Threat_4015, ThreatEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_IncompatibleDuties_4016(SeparationOfDuties source){
		SeparationOfDuties container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof SeparationOfDuties) {
				container = (SeparationOfDuties) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getIncompatibleDutiesOut().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof IncompatibleDuties) {
				continue;
			}
			IncompatibleDuties link = (IncompatibleDuties) linkObject;
			if (IncompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			SeparationOfDuties dst = link.getTarget();
			SeparationOfDuties src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.IncompatibleDuties_4016, IncompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_CompatibleDuties_4017(BindingOfDuties source){
		BindingOfDuties container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof BindingOfDuties) {
				container = (BindingOfDuties) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getCompatibleDutiesOut().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof CompatibleDuties) {
				continue;
			}
			CompatibleDuties link = (CompatibleDuties) linkObject;
			if (CompatibleDutiesEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			BindingOfDuties dst = link.getTarget();
			BindingOfDuties src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.CompatibleDuties_4017, CompatibleDutiesEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Dependency_4018(Role source){
		Role container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Role) {
				container = (Role) element;
			}
		}
		if (container == null) { return Collections.EMPTY_LIST; }
		Collection result = new LinkedList();
		for (Iterator links = container.getDependBy().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Dependency) {
				continue;
			}
			Dependency link = (Dependency) linkObject;
			if (DependencyEditPart.VISUAL_ID != StsToolVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Role dst = link.getTarget();
			Role src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new StsToolLinkDescriptor(src, dst, link, StsToolElementTypes.Dependency_4018, DependencyEditPart.VISUAL_ID));
		}
		return result;
	}

}
