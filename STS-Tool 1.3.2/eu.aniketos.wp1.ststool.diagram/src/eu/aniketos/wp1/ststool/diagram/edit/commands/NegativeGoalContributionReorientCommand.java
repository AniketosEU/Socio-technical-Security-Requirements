/*
* NegativeGoalContributionReorientCommand.java
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
package eu.aniketos.wp1.ststool.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.NegativeGoalContribution;
import eu.aniketos.wp1.ststool.diagram.edit.policies.StsToolBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class NegativeGoalContributionReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int			reorientDirection;

	/**
	 * @generated
	 */
	private final EObject	oldEnd;

	/**
	 * @generated
	 */
	private final EObject	newEnd;

	/**
	 * @generated
	 */
	public NegativeGoalContributionReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	@Override
	public boolean canExecute(){
		if (false == getElementToEdit() instanceof NegativeGoalContribution) { return false; }
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) { return canReorientSource(); }
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) { return canReorientTarget(); }
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource(){
		if (!(oldEnd instanceof Goal && newEnd instanceof Goal)) { return false; }
		Goal target = getLink().getTarget();
		if (!(getLink().eContainer() instanceof Goal)) { return false; }
		Goal container = (Goal) getLink().eContainer();
		return StsToolBaseItemSemanticEditPolicy.LinkConstraints.canExistNegativeGoalContribution_4005(container, getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget(){
		if (!(oldEnd instanceof Goal && newEnd instanceof Goal)) { return false; }
		Goal source = getLink().getSource();
		if (!(getLink().eContainer() instanceof Goal)) { return false; }
		Goal container = (Goal) getLink().eContainer();
		return StsToolBaseItemSemanticEditPolicy.LinkConstraints.canExistNegativeGoalContribution_4005(container, source, getNewTarget());
	}

	/**
	 * @generated
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info) throws ExecutionException{
		if (!canExecute()) { throw new ExecutionException("Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) { return reorientSource(); }
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) { return reorientTarget(); }
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException{
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException{
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected NegativeGoalContribution getLink(){
		return (NegativeGoalContribution) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected Goal getOldSource(){
		return (Goal) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Goal getNewSource(){
		return (Goal) newEnd;
	}

	/**
	 * @generated
	 */
	protected Goal getOldTarget(){
		return (Goal) oldEnd;
	}

	/**
	 * @generated
	 */
	protected Goal getNewTarget(){
		return (Goal) newEnd;
	}
}
