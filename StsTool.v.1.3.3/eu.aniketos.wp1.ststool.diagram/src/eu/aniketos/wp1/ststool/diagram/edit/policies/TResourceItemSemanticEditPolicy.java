/*
* TResourceItemSemanticEditPolicy.java
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
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ModifyCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ModifyReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NeedCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.NeedReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PartOfCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.PartOfReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProduceCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ProduceReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.TangibleByCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.TangibleByReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommand;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ModifyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NeedEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PartOfEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProduceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class TResourceItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TResourceItemSemanticEditPolicy() {
		super(StsToolElementTypes.TResource_2004);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		EObject e = req.getElementToDestroy();
		if (e != null && e instanceof TResource && !(((TResource) e).isDeletable())) {
			TResource r = (TResource) e;
			if (r.getProvidedFrom().size() > 0) {
				return new WarningDialogCommand("The document cannot be deleted", "The document cannot be deleted\n" + "The document " + r.getName() + " is created due to a provision to " + r.getActorOwner().getName() + ". To delete the document, delete the provision.");
			} else {
				return new WarningDialogCommand("The document cannot be deleted", "The document cannot be deleted\n" + "The document " + r.getName() + " is further provided. To delete the document, delete that provision.");
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
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (StsToolVisualIDRegistry.getVisualID(outgoingLink) == PartOfEditPart.VISUAL_ID) {
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
		if (StsToolElementTypes.Need_4001 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Produce_4002 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Modify_4003 == req.getElementType()) { return null; }
		if (StsToolElementTypes.PartOf_4009 == req.getElementType()) { return getGEFWrapper(new PartOfCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.TangibleBy_4010 == req.getElementType()) { return null; }
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return null; }
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req){
		if (StsToolElementTypes.Need_4001 == req.getElementType()) { return getGEFWrapper(new NeedCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Produce_4002 == req.getElementType()) { return getGEFWrapper(new ProduceCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.Modify_4003 == req.getElementType()) { return getGEFWrapper(new ModifyCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.PartOf_4009 == req.getElementType()) { return getGEFWrapper(new PartOfCreateCommand(req, req.getSource(), req.getTarget())); }
		if (StsToolElementTypes.TangibleBy_4010 == req.getElementType()) { return getGEFWrapper(new TangibleByCreateCommand(req, req.getSource(), req.getTarget())); }
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
			case NeedEditPart.VISUAL_ID:
				return getGEFWrapper(new NeedReorientCommand(req));
			case ProduceEditPart.VISUAL_ID:
				return getGEFWrapper(new ProduceReorientCommand(req));
			case ModifyEditPart.VISUAL_ID:
				return getGEFWrapper(new ModifyReorientCommand(req));
			case PartOfEditPart.VISUAL_ID:
				return getGEFWrapper(new PartOfReorientCommand(req));
			case TangibleByEditPart.VISUAL_ID:
				return getGEFWrapper(new TangibleByReorientCommand(req));
			case ThreatEditPart.VISUAL_ID:
				return getGEFWrapper(new ThreatReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
