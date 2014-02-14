/*
* GoalItemSemanticEditPolicy.java
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

import java.util.Iterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.diagram.edit.commands.CompatibleDutiesCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.CompatibleDutiesReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.GoalDecompositionANDCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.GoalDecompositionANDReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.GoalDecompositionORCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.GoalDecompositionORReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.IncompatibleDutiesCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.IncompatibleDutiesReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ModifyCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ModifyReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NeedCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NeedReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NegativeGoalContributionCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NegativeGoalContributionReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PositiveGoalContributionCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PositiveGoalContributionReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProduceCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProduceReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommand;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IncompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ModifyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NeedEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NegativeGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PositiveGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProduceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class GoalItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public GoalItemSemanticEditPolicy() {
		super(StsToolElementTypes.Goal_2003);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		EObject e = req.getElementToDestroy();
		if (e != null && e instanceof Goal && !(((Goal) e).isDeletable())) {
			Goal g = (Goal) e;
			if (g.getDelegatedFrom().size() > 0) {
				return new WarningDialogCommand("The goal cannot be deleted", "The goal cannot be deleted\n" + "The goal " + g.getName() + " is created due to a delegation to " + g.getActorOwner().getName() + ". To delete the goal, delete the delegation.");
			} else {
				return new WarningDialogCommand("The goal cannot be deleted", "The goal cannot be deleted\n" + "The goal " + g.getName() + " is further delegated. Delete such delegation first.");
			}
		}
		return getDestroyElementCommandGen(req);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommandGen(DestroyElementRequest req){
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == PositiveGoalContributionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == NegativeGoalContributionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == GoalDecompositionOREditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == GoalDecompositionANDEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == ThreatEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == IncompatibleDutiesEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == CompatibleDutiesEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == NeedEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == ProduceEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == ModifyEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == PositiveGoalContributionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == NegativeGoalContributionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == GoalDecompositionOREditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == GoalDecompositionANDEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == IncompatibleDutiesEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == CompatibleDutiesEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req){
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req) : getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(CreateRelationshipRequest req){
		if (StsToolElementTypes.Need_4001 == req.getElementType()) { return getGEFWrapper(new NeedCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Produce_4002 == req.getElementType()) { return getGEFWrapper(new ProduceCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Modify_4003 == req.getElementType()) { return getGEFWrapper(new ModifyCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.PositiveGoalContribution_4004 == req.getElementType()) { return getGEFWrapper(new PositiveGoalContributionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.NegativeGoalContribution_4005 == req.getElementType()) { return getGEFWrapper(new NegativeGoalContributionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.GoalDecompositionOR_4006 == req.getElementType()) { return getGEFWrapper(new GoalDecompositionORCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.GoalDecompositionAND_4007 == req.getElementType()) { return getGEFWrapper(new GoalDecompositionANDCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return null; }
		if (StsToolElementTypes.IncompatibleDuties_4016 == req.getElementType()) { return getGEFWrapper(new IncompatibleDutiesCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.CompatibleDuties_4017 == req.getElementType()) { return getGEFWrapper(new CompatibleDutiesCreateCommand(req, req.getSource(), req.getTarget())); }
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req){
		if (StsToolElementTypes.Need_4001 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Produce_4002 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Modify_4003 == req.getElementType()) { return null; }
		if (StsToolElementTypes.PositiveGoalContribution_4004 == req.getElementType()) { return getGEFWrapper(new PositiveGoalContributionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.NegativeGoalContribution_4005 == req.getElementType()) { return getGEFWrapper(new NegativeGoalContributionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.GoalDecompositionOR_4006 == req.getElementType()) { return getGEFWrapper(new GoalDecompositionORCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.GoalDecompositionAND_4007 == req.getElementType()) { return getGEFWrapper(new GoalDecompositionANDCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return getGEFWrapper(new ThreatCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.IncompatibleDuties_4016 == req.getElementType()) { return getGEFWrapper(new IncompatibleDutiesCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.CompatibleDuties_4017 == req.getElementType()) { return getGEFWrapper(new CompatibleDutiesCreateCommand(req, req.getSource(), req.getTarget())); }
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	@Override
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req){
		switch (getVisualID(req)) {
			case NeedEditPart.VISUAL_ID:
				return getGEFWrapper(new NeedReorientCommand(req));
			case ProduceEditPart.VISUAL_ID:
				return getGEFWrapper(new ProduceReorientCommand(req));
			case ModifyEditPart.VISUAL_ID:
				return getGEFWrapper(new ModifyReorientCommand(req));
			case PositiveGoalContributionEditPart.VISUAL_ID:
				return getGEFWrapper(new PositiveGoalContributionReorientCommand(req));
			case NegativeGoalContributionEditPart.VISUAL_ID:
				return getGEFWrapper(new NegativeGoalContributionReorientCommand(req));
			case GoalDecompositionOREditPart.VISUAL_ID:
				return getGEFWrapper(new GoalDecompositionORReorientCommand(req));
			case GoalDecompositionANDEditPart.VISUAL_ID:
				return getGEFWrapper(new GoalDecompositionANDReorientCommand(req));
			case ThreatEditPart.VISUAL_ID:
				return getGEFWrapper(new ThreatReorientCommand(req));
			case IncompatibleDutiesEditPart.VISUAL_ID:
				return getGEFWrapper(new IncompatibleDutiesReorientCommand(req));
			case CompatibleDutiesEditPart.VISUAL_ID:
				return getGEFWrapper(new CompatibleDutiesReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
