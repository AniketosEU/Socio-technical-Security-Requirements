/*
* DelegationCreateCommand.java
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
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.diagram.edit.policies.StsToolBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class DelegationCreateCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final EObject	source;

	/**
	 * @generated
	 */
	private final EObject	target;

	/**
	 * @generated
	 */
	private final Actor		container;

	/**
	 * @generated
	 */
	public DelegationCreateCommand(CreateRelationshipRequest request, EObject source, EObject target) {
		super(request.getLabel(), null, request);
		this.source = source;
		this.target = target;
		container = deduceContainer(source, target);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean canExecute(){
		getRequest();
		if (source == null && target == null) { return false; }
		if (source != null && false == source instanceof Actor) { return false; }
		if (target != null && false == target instanceof Actor) { return false; }
		if (getSource() == null) { return true; // link creation is in progress; source is not defined yet
		}
		// target may be null here but it's possible to check constraint
		if (getContainer() == null) { return false;}
		return StsToolBaseItemSemanticEditPolicy.LinkConstraints.canCreateDelegation_4013(getContainer(), getSource(), getTarget());
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info) throws ExecutionException{

		if (!canExecute()) { throw new ExecutionException("Invalid arguments in create link command"); //$NON-NLS-1$
		}

		Delegation newElement = StstoolFactory.eINSTANCE.createDelegation();
		getContainer().getOutgoingDelegations().add(newElement);
		newElement.setSource(getSource());
		newElement.setTarget(getTarget());

		try {
			CreateRelationshipRequest crr = (CreateRelationshipRequest) getRequest();

			Goal g = (Goal) crr.getParameter("delegatedObject");
			//g.setNameForced("No Goal Reference");
			newElement.setTargetGoal(g);

			Goal sourceGoal = (Goal) crr.getParameter("sourceGoal");
			if (sourceGoal != null && sourceGoal.getName() != null) newElement.setSourceGoal(sourceGoal);

			doConfigure(newElement, monitor, info);
			((CreateElementRequest) getRequest()).setNewElement(newElement);
		} catch (Exception e) {
			e.printStackTrace();
		}

		doConfigure(newElement, monitor, info);
		return CommandResult.newOKCommandResult(newElement);

	}


	/**
	 * @generated
	 */
	protected void doConfigure(Delegation newElement,IProgressMonitor monitor,IAdaptable info) throws ExecutionException{
		IElementType elementType = ((CreateElementRequest) getRequest()).getElementType();
		ConfigureRequest configureRequest = new ConfigureRequest(getEditingDomain(), newElement, elementType);
		configureRequest.setClientContext(((CreateElementRequest) getRequest()).getClientContext());
		configureRequest.addParameters(getRequest().getParameters());
		configureRequest.setParameter(CreateRelationshipRequest.SOURCE, getSource());
		configureRequest.setParameter(CreateRelationshipRequest.TARGET, getTarget());
		ICommand configureCommand = elementType.getEditCommand(configureRequest);
		if (configureCommand != null && configureCommand.canExecute()) {
			configureCommand.execute(monitor, info);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setElementToEdit(EObject element){
		throw new UnsupportedOperationException();
	}

	/**
	 * @generated
	 */
	protected Actor getSource(){
		return (Actor) source;
	}

	/**
	 * @generated
	 */
	protected Actor getTarget(){
		return (Actor) target;
	}

	/**
	 * @generated
	 */
	public Actor getContainer(){
		return container;
	}

	/**
	 * Default approach is to traverse ancestors of the source to find instance of container. Modify with appropriate logic.
	 * 
	 * @generated
	 */
	private static Actor deduceContainer(EObject source,EObject target){
		// Find container element for the new link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null; element = element.eContainer()) {
			if (element instanceof Actor) { return (Actor) element; }
		}
		return null;
	}

}
