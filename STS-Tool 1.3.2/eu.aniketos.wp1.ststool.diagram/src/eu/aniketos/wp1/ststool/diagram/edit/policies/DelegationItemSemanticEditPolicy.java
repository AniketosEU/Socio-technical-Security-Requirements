/*
* DelegationItemSemanticEditPolicy.java
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
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalContribution;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.ThreatReorientCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommand;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class DelegationItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DelegationItemSemanticEditPolicy() {
		super(StsToolElementTypes.Delegation_4013);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		if (!(req.getElementToDestroy() instanceof Delegation)) return UnexecutableCommand.INSTANCE;


		Delegation delegation = (Delegation) req.getElementToDestroy();

		if (delegation != null && !delegation.isDeletable()) {
			String name = delegation.getSourceGoal().getName();
			String act1 = delegation.getSource().getName();
			String act2 = delegation.getTarget().getName();

			return new WarningDialogCommand("The delegation cannot be deleted ", "The delegation cannot be deleted.\n" + "The delegation of goal " + name + " from " + act1 + " to " + act2 + " cannot be deleted, as " + name + " is part of a delegation chain. Delete the delegations of " + name + " made by " + act2 + " first. ");
		}

		return getGEFWrapper(new DestroyElementCommand(req) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info)
					throws ExecutionException{

				try {
					Delegation delegation = (Delegation) getElementToDestroy();
					delegation.setSourceGoal(null);

					if (delegation.getTargetGoal().getDelegatedFrom().size() == 1) {
						Goal tg = delegation.getTargetGoal();


						for (Iterator<GoalContribution> itr = tg.getIncomingContribution().iterator(); itr.hasNext();) {
							GoalContribution i = itr.next();
							itr.remove();
							EcoreUtil.delete(i, true);
						}
						if (tg.getIncomingDecompositions() != null) {
							EcoreUtil.delete(tg.getIncomingDecompositions(), true);
						}
						for (Iterator<Threat> itr = tg.getThreatedElements().iterator(); itr.hasNext();) {
							Threat i = itr.next();
							itr.remove();
							EcoreUtil.delete(i, true);
						}

						for (Iterator<IncompatibleDuties> itr = tg.getIncompatibleDutiesIn().iterator(); itr.hasNext();) {
							IncompatibleDuties i = itr.next();
							itr.remove();
							EcoreUtil.delete(i, true);
						}
						for (Iterator<CompatibleDuties> itr = tg.getCompatibleDutiesIn().iterator(); itr.hasNext();) {
							CompatibleDuties i = itr.next();
							itr.remove();
							EcoreUtil.delete(i, true);
						}

						EcoreUtil.delete(tg, true);
					}

					delegation.setTargetGoal(null);
					return super.doExecuteWithResult(monitor, info);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return CommandResult.newOKCommandResult();//CommandResult.newErrorCommandResult("Error deleting Delegation");
			}
		});
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommandGen(DestroyElementRequest req){
		return getGEFWrapper(new DestroyElementCommand(req));
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
		if (StsToolElementTypes.Threat_4015 == req.getElementType()) { return null; }
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(CreateRelationshipRequest req){
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
			case ThreatEditPart.VISUAL_ID:
				return getGEFWrapper(new ThreatReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
