/*
* StsToolVisualIDRegistry.java
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

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentAgentCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DependencyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalName2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceNameEditPart;
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
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleRoleCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceName2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;

/**
 * This registry is used to determine which type of visual object should be created for the corresponding Diagram, Node, ChildNode or Link represented by a domain model object.
 * 
 * @generated
 */
public class StsToolVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String	DEBUG_KEY	= "eu.aniketos.wp1.ststool.diagram/debug/visualID";	//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view){
		if (view instanceof Diagram) {
			if (StsToolDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return StsToolDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view){
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) { return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type){
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				StsToolDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID){
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement){
		if (domainElement == null) { return -1; }
		if (StstoolPackage.eINSTANCE.getStsToolDiagram().isSuperTypeOf(domainElement.eClass()) && isDiagram((StsToolDiagram) domainElement)) { return StsToolDiagramEditPart.VISUAL_ID; }
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView,EObject domainElement){
		if (domainElement == null) { return -1; }
		String containerModelID = eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry.getModelID(containerView);
		if (!StsToolDiagramEditPart.MODEL_ID.equals(containerModelID)) { return -1; }
		int containerVisualID;
		if (StsToolDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StsToolDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
			case AgentAgentCompartmentEditPart.VISUAL_ID:
				if (StstoolPackage.eINSTANCE.getGoal().isSuperTypeOf(domainElement.eClass())) { return Goal2EditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getTResource().isSuperTypeOf(domainElement.eClass())) { return TResource2EditPart.VISUAL_ID; }
			break;
			case RoleRoleCompartmentEditPart.VISUAL_ID:
				if (StstoolPackage.eINSTANCE.getGoal().isSuperTypeOf(domainElement.eClass())) { return Goal2EditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getTResource().isSuperTypeOf(domainElement.eClass())) { return TResource2EditPart.VISUAL_ID; }
			break;
			case StsToolDiagramEditPart.VISUAL_ID:
				if (StstoolPackage.eINSTANCE.getAgent().isSuperTypeOf(domainElement.eClass())) { return AgentEditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getRole().isSuperTypeOf(domainElement.eClass())) { return RoleEditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getGoal().isSuperTypeOf(domainElement.eClass())) { return GoalEditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getTResource().isSuperTypeOf(domainElement.eClass())) { return TResourceEditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getIResource().isSuperTypeOf(domainElement.eClass())) { return IResourceEditPart.VISUAL_ID; }
				if (StstoolPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) { return EventEditPart.VISUAL_ID; }
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView,int nodeVisualID){
		String containerModelID = eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry.getModelID(containerView);
		if (!StsToolDiagramEditPart.MODEL_ID.equals(containerModelID)) { return false; }
		int containerVisualID;
		if (StsToolDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = StsToolDiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
			case AgentEditPart.VISUAL_ID:
				if (AgentNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (AgentAgentCompartmentEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case RoleEditPart.VISUAL_ID:
				if (RoleNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (RoleRoleCompartmentEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case GoalEditPart.VISUAL_ID:
				if (GoalNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case TResourceEditPart.VISUAL_ID:
				if (TResourceNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case IResourceEditPart.VISUAL_ID:
				if (IResourceNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case EventEditPart.VISUAL_ID:
				if (EventNameEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case Goal2EditPart.VISUAL_ID:
				if (GoalName2EditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case TResource2EditPart.VISUAL_ID:
				if (TResourceName2EditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case AgentAgentCompartmentEditPart.VISUAL_ID:
				if (Goal2EditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (TResource2EditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case RoleRoleCompartmentEditPart.VISUAL_ID:
				if (Goal2EditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (TResource2EditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
			case StsToolDiagramEditPart.VISUAL_ID:
				if (AgentEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (RoleEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (GoalEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (TResourceEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (IResourceEditPart.VISUAL_ID == nodeVisualID) { return true; }
				if (EventEditPart.VISUAL_ID == nodeVisualID) { return true; }
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement){
		if (domainElement == null) { return -1; }
		if (StstoolPackage.eINSTANCE.getNeed().isSuperTypeOf(domainElement.eClass())) { return NeedEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getProduce().isSuperTypeOf(domainElement.eClass())) { return ProduceEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getModify().isSuperTypeOf(domainElement.eClass())) { return ModifyEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getPositiveGoalContribution().isSuperTypeOf(domainElement.eClass())) { return PositiveGoalContributionEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getNegativeGoalContribution().isSuperTypeOf(domainElement.eClass())) { return NegativeGoalContributionEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getGoalDecompositionOR().isSuperTypeOf(domainElement.eClass())) { return GoalDecompositionOREditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getGoalDecompositionAND().isSuperTypeOf(domainElement.eClass())) { return GoalDecompositionANDEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getOwn().isSuperTypeOf(domainElement.eClass())) { return OwnEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getPartOf().isSuperTypeOf(domainElement.eClass())) { return PartOfEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getTangibleBy().isSuperTypeOf(domainElement.eClass())) { return TangibleByEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getPlay().isSuperTypeOf(domainElement.eClass())) { return PlayEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getProvision().isSuperTypeOf(domainElement.eClass())) { return ProvisionEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getDelegation().isSuperTypeOf(domainElement.eClass())) { return DelegationEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getAuthorisation().isSuperTypeOf(domainElement.eClass())) { return AuthorisationEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getThreat().isSuperTypeOf(domainElement.eClass())) { return ThreatEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getIncompatibleDuties().isSuperTypeOf(domainElement.eClass())) { return IncompatibleDutiesEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getCompatibleDuties().isSuperTypeOf(domainElement.eClass())) { return CompatibleDutiesEditPart.VISUAL_ID; }
		if (StstoolPackage.eINSTANCE.getDependency().isSuperTypeOf(domainElement.eClass())) { return DependencyEditPart.VISUAL_ID; }
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(StsToolDiagram element){
		return true;
	}

}
