/*
* StsToolBaseItemSemanticEditPolicy.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.Threatable;
import eu.aniketos.wp1.ststool.diagram.custom.constraint.LinkConstraint;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.edit.commands.CommandWithPreCheck;
import eu.aniketos.wp1.ststool.diagram.edit.helpers.StsToolBaseEditHelper;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProvisionEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class StsToolBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * 
	 * @generated
	 */
	public static final String	VISUAL_ID_KEY	= "visual_id"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	private final IElementType	myElementType;

	/**
	 * @generated
	 */
	protected StsToolBaseItemSemanticEditPolicy(IElementType elementType) {
		myElementType = elementType;
	}

	/**
	 * Extended request data key to hold editpart visual id. Add visual id of edited editpart to extended data of the request so command switch can decide what kind of diagram element is being edited. It is done in those cases when it's not possible to deduce diagram element kind from domain element.
	 * 
	 * @generated
	 */
	@Override
	public Command getCommand(Request request){
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart().getModel();
			if (view instanceof View) {
				Integer id = new Integer(StsToolVisualIDRegistry.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * 
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request){
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getSemanticCommand(IEditCommandRequest request){
		IEditCommandRequest completedRequest = completeRequest(request);
		Command semanticCommand = getSemanticCommandSwitch(completedRequest);
		semanticCommand = getEditHelperCommand(completedRequest, semanticCommand);
		if (completedRequest instanceof DestroyRequest) {
			DestroyRequest destroyRequest = (DestroyRequest) completedRequest;
			return shouldProceed(destroyRequest) ? addDeleteViewCommand(semanticCommand, destroyRequest) : null;
		}
		return semanticCommand;
	}

	/**
	 * @generated
	 */
	protected Command addDeleteViewCommand(Command mainCommand,DestroyRequest completedRequest){
		Command deleteViewCommand = getGEFWrapper(new DeleteCommand(getEditingDomain(), (View) getHost().getModel()));
		return mainCommand == null ? deleteViewCommand : mainCommand.chain(deleteViewCommand);
	}

	/**
	 * @generated
	 */
	private Command getEditHelperCommand(IEditCommandRequest request,Command editPolicyCommand){
		if (editPolicyCommand != null) {
			ICommand command = editPolicyCommand instanceof ICommandProxy ? ((ICommandProxy) editPolicyCommand).getICommand() : new CommandProxy(editPolicyCommand);
			request.setParameter(StsToolBaseEditHelper.EDIT_POLICY_COMMAND, command);
		}
		IElementType requestContextElementType = getContextElementType(request);
		request.setParameter(StsToolBaseEditHelper.CONTEXT_ELEMENT_TYPE, requestContextElementType);
		ICommand command = requestContextElementType.getEditCommand(request);
		request.setParameter(StsToolBaseEditHelper.EDIT_POLICY_COMMAND, null);
		request.setParameter(StsToolBaseEditHelper.CONTEXT_ELEMENT_TYPE, null);
		if (command != null) {
			if (!(command instanceof CompositeTransactionalCommand)) {
				command = new CompositeTransactionalCommand(getEditingDomain(), command.getLabel()).compose(command);
			}
			return new ICommandProxy(command);
		}
		return editPolicyCommand;
	}

	/**
	 * @generated
	 */
	private IElementType getContextElementType(IEditCommandRequest request){
		IElementType requestContextElementType = StsToolElementTypes.getElementType(getVisualID(request));
		return requestContextElementType != null ? requestContextElementType : myElementType;
	}


	protected Command getSemanticCommandSwitch(IEditCommandRequest req){

		if (req instanceof DestroyElementRequest) {
			Command c = myDestroyElementCommand((DestroyElementRequest) req);
			return c;
		}
		return getSemanticCommandSwitchGen(req);
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitchGen(IEditCommandRequest req){
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) { return getSetCommand((SetRequest) req); }
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req){
		return null;
	}


	private Command myDestroyElementCommand(DestroyElementRequest req){

		Command deleteC = getDestroyElementCommand(req);
		if (deleteC instanceof CommandWithPreCheck) { return deleteC; }

		CompoundCommand cc = new CompoundCommand("Delete Elementz");
		if (getHost() instanceof StsToolShapeNodeEditPart) {
			StsToolShapeNodeEditPart node = (StsToolShapeNodeEditPart) getHost();
			cc.add(new DeletePositionConstraintCommand(node.getViewsManager(), (IGraphicalEditPart) getHost()));
		}
		cc.add(deleteC);
		return (cc);
	}


	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req){
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req){
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req){
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected final Command getGEFWrapper(ICommand cmd){
		return new ICommandProxy(cmd);
	}

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain(){
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Clean all shortcuts to the host element from the same diagram
	 * 
	 * @generated
	 */
	protected void addDestroyShortcutsCommand(ICompositeCommand cmd,View view){
		assert view.getEAnnotation("Shortcut") == null; //$NON-NLS-1$
		for (Iterator it = view.getDiagram().getChildren().iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (nextView.getEAnnotation("Shortcut") == null || !nextView.isSetElement() || nextView.getElement() != view.getElement()) { //$NON-NLS-1$
				continue;
			}
			cmd.add(new DeleteCommand(getEditingDomain(), nextView));
		}
	}

	/**
	 * @generated
	 */
	public static class LinkConstraints {

		/**
		 * @generated
		 */
		public static boolean canCreateNeed_4001(Goal container,Goal source,TResource target){
			return canExistNeed_4001(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateProduce_4002(Goal container,Goal source,TResource target){
			return canExistProduce_4002(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateModify_4003(Goal container,Goal source,TResource target){
			return canExistModify_4003(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreatePositiveGoalContribution_4004(Goal container,Goal source,Goal target){
			return canExistPositiveGoalContribution_4004(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateNegativeGoalContribution_4005(Goal container,Goal source,Goal target){
			return canExistNegativeGoalContribution_4005(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateGoalDecompositionOR_4006(Goal container,Goal source,Goal target){
			return canExistGoalDecompositionOR_4006(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateGoalDecompositionAND_4007(Goal container,Goal source,Goal target){
			return canExistGoalDecompositionAND_4007(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateOwn_4008(Actor container,Actor source,IResource target){
			return canExistOwn_4008(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreatePartOf_4009(Resource container,Resource source,Resource target){
			return canExistPartOf_4009(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateTangibleBy_4010(IResource container,IResource source,TResource target){
			return canExistTangibleBy_4010(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreatePlay_4011(Agent container,Agent source,Role target){
			return canExistPlay_4011(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateProvision_4012(Actor container,Actor source,Actor target){
			return canExistProvision_4012(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateDelegation_4013(Actor container,Actor source,Actor target){
			return canExistDelegation_4013(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateAuthorisation_4014(Actor container,Actor source,Actor target){
			return canExistAuthorisation_4014(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateThreat_4015(Event container,Event source,Threatable target){
			return canExistThreat_4015(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateIncompatibleDuties_4016(SeparationOfDuties container,SeparationOfDuties source,SeparationOfDuties target){
			return canExistIncompatibleDuties_4016(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateCompatibleDuties_4017(BindingOfDuties container,BindingOfDuties source,BindingOfDuties target){
			return canExistCompatibleDuties_4017(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canCreateDependency_4018(Role container,Role source,Role target){
			return canExistDependency_4018(container, source, target);
		}

		//-------------------------------------------------------------------------------------------

		/**
		 * @generated NOT
		 */
		public static boolean canExistNeed_4001(Goal container,Goal source,TResource target){

			return LinkConstraint.canExistGoalNeed(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistProduce_4002(Goal container,Goal source,TResource target){

			return LinkConstraint.canExistGoalProduce(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistModify_4003(Goal container,Goal source,TResource target){

			return LinkConstraint.canExistGoalModify(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistPositiveGoalContribution_4004(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistContributionPositive(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistNegativeGoalContribution_4005(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistContributionNegative(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistGoalDecompositionOR_4006(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistDecomposition_OR(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistGoalDecompositionAND_4007Gen(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistDecomposition_AND(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistGoalDecompositionOR_4006Gen(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistDecomposition_OR(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistGoalDecompositionAND_4007(Goal container,Goal source,Goal target){

			return LinkConstraint.canExistDecomposition_AND(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistOwn_4008(Actor container,Actor source,IResource target){

			return LinkConstraint.canExistActorOwn(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistPartOf_4009(Resource container,Resource source,Resource target){

			return LinkConstraint.canExistPartOf(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistTangibleBy_4010(IResource container,IResource source,TResource target){

			return LinkConstraint.canExistTangibleBy(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistPlay_4011(Agent container,Agent source,Role target){

			return LinkConstraint.canExistAgentPlays(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistProvision_4012(Actor container,Actor source,Actor target){

			return LinkConstraint.canExistProvision(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		@SuppressWarnings("deprecation")
		public static boolean canExistDelegation_4013(Actor container,Actor source,Actor target){
			return LinkConstraint.canExistDelegation(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistAuthorisation_4014(Actor container,Actor source,Actor target){

			return LinkConstraint.canExistAuthorisation(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistThreat_4015(Event container,Event source,Threatable target){

			return LinkConstraint.canExistThreat(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistIncompatibleDuties_4016(SeparationOfDuties container,SeparationOfDuties source,SeparationOfDuties target){

			return LinkConstraint.canExistIncompatibleDuties(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistCompatibleDuties_4017(BindingOfDuties container,BindingOfDuties source,BindingOfDuties target){

			return LinkConstraint.canExistCompatibleDuties(container, source, target);
		}

		/**
		 * @generated NOT
		 */
		public static boolean canExistDependency_4018(Role container,Role source,Role target){
			return LinkConstraint.canExistDependency(container, source, target);
		}
	}

	class DeletePositionConstraintCommand extends Command {

		//private Map<Integer,String> oldConstraint=new HashMap<Integer,String>();
		private Map<String, Map<Integer, String>>	elementConstraint	= new HashMap<String, Map<Integer, String>>();
		private ViewsManager								vm;
		private IGraphicalEditPart						host;

		public DeletePositionConstraintCommand(ViewsManager vm, IGraphicalEditPart host) {

			this.vm = vm;
			this.host = host;
		}

		@Override
		public void execute(){

			if (host instanceof StsToolShapeNodeEditPart) {
				try {
					StsToolShapeNodeEditPart node = (StsToolShapeNodeEditPart) host;
					if (vm == null) vm = node.getViewsManager();
					StsElement element = (StsElement) (node.getPrimaryView().getElement());
					try {
						elementConstraint.put(element.getStsUniqueID(), vm.getConstraintObjectsForAllViews(element.getStsUniqueID()));
					} catch (Exception e1) {
					}

					List l = new ArrayList();
					if (element instanceof Actor) {
						Actor a = (Actor) element;
						l.addAll(a.getGoals());
						l.addAll(a.getTResources());
						Iterator i = l.iterator();
						while (i.hasNext()) {
							StsElement e = (StsElement) i.next();
							elementConstraint.put(e.getStsUniqueID(), vm.getConstraintObjectsForAllViews(e.getStsUniqueID()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (host instanceof DelegationEditPart) {
				DelegationEditPart link = (DelegationEditPart) host;
				ViewsManager vm = link.getViewsManager();
				Delegation element = (Delegation) (link.getPrimaryView().getElement());
				//vm.printMap();
				elementConstraint.put(element.getTargetGoal().getStsUniqueID(), vm.getConstraintObjectsForAllViews(element.getTargetGoal().getStsUniqueID()));
				//vm.printMap();
			} else if (host instanceof ProvisionEditPart) {
				ProvisionEditPart link = (ProvisionEditPart) host;
				ViewsManager vm = link.getViewsManager();
				Provision element = (Provision) (link.getPrimaryView().getElement());
				elementConstraint.put(element.getTargetResource().getStsUniqueID(), vm.getConstraintObjectsForAllViews(element.getTargetResource().getStsUniqueID()));
			}

			redo();
		}

		@Override
		public void redo(){

			if (elementConstraint != null && elementConstraint.size() > 0) {
				for (Map.Entry<String, Map<Integer, String>> entry : elementConstraint.entrySet()) {
					try {
						vm.removeObjectConstraintForAllViews(entry.getKey());
					} catch (Exception e) {
						e.printStackTrace();
						//System.err.println("Error "+e.getStackTrace()[0].getClassName()+" at line "+e.getStackTrace()[0].getLineNumber());
					}
				}
			}
		}



		@Override
		public void undo(){

			/*for (Map.Entry<String, Map<Integer, String>> entry : elementConstraint.entrySet()) {
				String id = entry.getKey();
				Map<Integer, String> con = elementConstraint.get(id);

				for (Map.Entry<Integer, String> en : con.entrySet()) {
					try {
					
					} catch (Exception e) {
					}
				}
			}*/
		}


	}

}
