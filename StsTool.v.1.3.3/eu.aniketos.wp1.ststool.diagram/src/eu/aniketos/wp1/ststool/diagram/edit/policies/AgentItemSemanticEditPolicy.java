/*
* AgentItemSemanticEditPolicy.java
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
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.diagram.edit.commands.AuthorisationCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.AuthorisationReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.DelegationCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.DelegationReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.OwnCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.OwnReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PlayCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PlayReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProvisionCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProvisionReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommand;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentAgentCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
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
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class AgentItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public AgentItemSemanticEditPolicy() {
		super(StsToolElementTypes.Agent_2001);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		EObject e = req.getElementToDestroy();
		if (e != null && e instanceof Agent && !(((Agent) e).isDeletable())) { return new WarningDialogCommand("Object actually not deletable", "Object actually not deletable. \n Maybe this object still have incoming/outgoing delegation/provision, try delete that first"); }
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
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == ProvisionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == DelegationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(incomingLink) == AuthorisationEditPart.VISUAL_ID) {
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
		}
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == OwnEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == PlayEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == ProvisionEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == DelegationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == AuthorisationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
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
	private void addDestroyChildNodesCommand(ICompositeCommand cmd){
		View view = (View) getHost().getModel();
		for (Iterator nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (StsToolVisualIDRegistry.getVisualID(node)) {
				case AgentAgentCompartmentEditPart.VISUAL_ID:
					for (Iterator cit = node.getChildren().iterator(); cit.hasNext();) {
						Node cnode = (Node) cit.next();
						switch (StsToolVisualIDRegistry.getVisualID(cnode)) {
							case Goal2EditPart.VISUAL_ID:
								for (Iterator it = cnode.getTargetEdges().iterator(); it.hasNext();) {
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
								for (Iterator it = cnode.getSourceEdges().iterator(); it.hasNext();) {
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
								cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
								// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
								// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
							break;
							case TResource2EditPart.VISUAL_ID:
								for (Iterator it = cnode.getTargetEdges().iterator(); it.hasNext();) {
									Edge incomingLink = (Edge) it.next();
									if (StsToolVisualIDRegistry.getVisualID(incomingLink) == NeedEditPart.VISUAL_ID) {
										DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
										cmd.add(new DestroyElementCommand(r));
										cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
										continue;
									}
									if (StsToolVisualIDRegistry.getVisualID(incomingLink) == ProduceEditPart.VISUAL_ID) {
										DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
										cmd.add(new DestroyElementCommand(r));
										cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
										continue;
									}
									if (StsToolVisualIDRegistry.getVisualID(incomingLink) == ModifyEditPart.VISUAL_ID) {
										DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
										cmd.add(new DestroyElementCommand(r));
										cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
										continue;
									}
									if (StsToolVisualIDRegistry.getVisualID(incomingLink) == PartOfEditPart.VISUAL_ID) {
										DestroyElementRequest r = new DestroyElementRequest(incomingLink.getElement(), false);
										cmd.add(new DestroyElementCommand(r));
										cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
										continue;
									}
									if (StsToolVisualIDRegistry.getVisualID(incomingLink) == TangibleByEditPart.VISUAL_ID) {
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
								}
								for (Iterator it = cnode.getSourceEdges().iterator(); it.hasNext();) {
									Edge outgoingLink = (Edge) it.next();
									if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == PartOfEditPart.VISUAL_ID) {
										DestroyElementRequest r = new DestroyElementRequest(outgoingLink.getElement(), false);
										cmd.add(new DestroyElementCommand(r));
										cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
										continue;
									}
								}
								cmd.add(new DestroyElementCommand(new DestroyElementRequest(getEditingDomain(), cnode.getElement(), false))); // directlyOwned: true
								// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
								// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
							break;
						}
					}
				break;
			}
		}
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
		if (StsToolElementTypes.Own_4008 == req.getElementType()) { return getGEFWrapper(new OwnCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Play_4011 == req.getElementType()) { return getGEFWrapper(new PlayCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Provision_4012 == req.getElementType()) { return getGEFWrapper(new ProvisionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Delegation_4013 == req.getElementType()) { return getGEFWrapper(new DelegationCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Authorisation_4014 == req.getElementType()) { return getGEFWrapper(new AuthorisationCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return null; }
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req){
		if (StsToolElementTypes.Own_4008 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Play_4011 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Provision_4012 == req.getElementType()) { return getGEFWrapper(new ProvisionCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Delegation_4013 == req.getElementType()) { return getGEFWrapper(new DelegationCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Authorisation_4014 == req.getElementType()) { return getGEFWrapper(new AuthorisationCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return getGEFWrapper(new ThreatCreateCommand(req, req.getSource(), req.getTarget())); }
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
			case OwnEditPart.VISUAL_ID:
				return getGEFWrapper(new OwnReorientCommand(req));
			case PlayEditPart.VISUAL_ID:
				return getGEFWrapper(new PlayReorientCommand(req));
			case ProvisionEditPart.VISUAL_ID:
				return getGEFWrapper(new ProvisionReorientCommand(req));
			case DelegationEditPart.VISUAL_ID:
				return getGEFWrapper(new DelegationReorientCommand(req));
			case AuthorisationEditPart.VISUAL_ID:
				return getGEFWrapper(new AuthorisationReorientCommand(req));
			case ThreatEditPart.VISUAL_ID:
				return getGEFWrapper(new ThreatReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
