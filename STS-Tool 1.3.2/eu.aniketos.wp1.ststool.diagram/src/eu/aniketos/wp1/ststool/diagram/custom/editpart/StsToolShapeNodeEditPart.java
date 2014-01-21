/*
* StsToolShapeNodeEditPart.java
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
package eu.aniketos.wp1.ststool.diagram.custom.editpart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ConnectorImpl;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.custom.view.constraint.NodeGraphicalConstraint;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * Extension class of the ShapeNodeEditPart that override some method to achieve some stsTool functionality
 */
public abstract class StsToolShapeNodeEditPart extends ShapeNodeEditPart {

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#ShapeNodeEditPart(Request)
	 */
	public StsToolShapeNodeEditPart(View view) {

		super(view);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#createDefaultEditPolicies()
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		//Hand connector don't work correctly so they are disabled
		removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#getModelSourceConnections()
	 */
	@Override
	protected List getModelSourceConnections(){

		return filterList(super.getModelSourceConnections());
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#getModelTargetConnections()
	 */
	@Override
	protected List getModelTargetConnections(){

		return filterList(super.getModelTargetConnections());
	}

	/**
	 * Filter a list of connection element based on the current view.
	 * 
	 * @param list
	 *           of element that need to be filtered
	 * @return the filtered list
	 */
	private List filterList(List list){

		if (list != null) {
			ArrayList result = new ArrayList();
			for (Object o : list) {
				if (o instanceof ConnectorImpl) {
					String SvisualID = ((ConnectorImpl) o).getType();
					if (SvisualID.equals("NoteAttachment")) {
						result.add(o);
					} else {
						int visualID = Integer.parseInt(((ConnectorImpl) o).getType());
						if (getViewsManager().isElementVisible(StsToolElementTypes.getElementType(visualID))) {
							result.add(o);
						}
					}
				}
			}
			return result;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Get the current viewManager, used to get the current view, or to change view.
	 * 
	 * @return the currentViewManger
	 */
	public ViewsManager getViewsManager(){

		CustomStsToolDiagramEditPart ep = (CustomStsToolDiagramEditPart) ((RenderedDiagramRootEditPart) getRoot()).getContents();
		return ep.getViewsManager();
	}


	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#activate()
	 */
	@Override
	public void activate(){

		super.activate();
		try {

			/*
			 * Get from the metamodel the constraint for the object of this
			 * editpart,
			 * and set the size / position of the related figure
			 * If is a compartment it will also set the collapse /expand value
			 * If no constraint is set, new constraint are created with the default
			 * value.
			 */
			StsElement element = (StsElement) getNotationView().getElement();
			int view = getViewsManager().getCurrentIntView();
			if (element != null) {
				String constraint = getViewsManager().getObjectConstraint(view, element.getStsUniqueID());
				if (constraint != null) {
					NodeGraphicalConstraint c = new NodeGraphicalConstraint(constraint);
					Rectangle r = new Rectangle(c.location, c.size);
					if (getViewsManager().getCurrentIntView() == ViewsManager.AUTHORIZATION_VIEW) c.collapsed = true;



					if ((this instanceof AgentEditPart || this instanceof RoleEditPart) && (c.collapsed)) {
						r.setSize(new Dimension(100, 100));
					}
					((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), r);
					if ((this instanceof AgentEditPart || this instanceof RoleEditPart)) {
						IGraphicalEditPart targetEp = getChildBySemanticHint("7001");
						if (targetEp == null) targetEp = getChildBySemanticHint("7002");

						if (targetEp != null && targetEp instanceof StsToolShapeCompartmentEditPart) {
							try {
								StsToolShapeCompartmentEditPart ep = (StsToolShapeCompartmentEditPart) targetEp;
								ep.collapse(c.collapsed, false);

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				} else {
					try {
						Rectangle r = getFigure().getBounds().getCopy();
						NodeGraphicalConstraint c = new NodeGraphicalConstraint(r);
						getViewsManager().setObjectConstraintForAllViews(element.getStsUniqueID(), c.getConstraintInString());
					} catch (Exception e) {
						System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#getPrimaryDragEditPolicy()
	 * 
	 *      used to change the ghost figure of Agent and Role
	 */
	@Override
	public EditPolicy getPrimaryDragEditPolicy(){

		return new ResizableShapeEditPolicy() {

			@Override
			protected IFigure createDragSourceFeedbackFigure(){
				Shape shape = getDragShape();
				shape.setXOR(false);
				shape.setLineStyle(Graphics.LINE_DOT);
				shape.setForegroundColor(ColorConstants.black);
				shape.setBackgroundColor(ColorConstants.lightGray);
				shape.setAlpha(190);
				shape.setBounds(getInitialFeedbackBounds());
				addFeedback(shape);
				return shape;

			}
		};
	}

	protected Shape getDragShape(){
		return new RectangleFigure();
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart#getCommand(Request)
	 */
	@Override
	public Command getCommand(Request request){

		if (request instanceof CreateConnectionViewAndElementRequest) {
			CreateConnectionViewAndElementRequest req = (CreateConnectionViewAndElementRequest) request;
			/*
			 * if the request is a request for creating a delegation / provision
			 * relation return a compound command that
			 * also create the delegated Goal / provided Resource
			 */
			String semanticHint = req.getConnectionViewDescriptor().getSemanticHint();
			if (semanticHint.equals("4012")) {
				CompoundCommand resultCommand = new CompoundCommand();
				resultCommand.add(new CreateTargetRelationElement(req, StsToolElementTypes.TResource_3002, getViewsManager()));
				resultCommand.add(super.getCommand(request));
				return resultCommand;
			} else if (semanticHint.equals("4013")) {
				CompoundCommand resultCommand = new CompoundCommand();
				resultCommand.add(new CreateTargetRelationElement(req, StsToolElementTypes.Goal_3001, getViewsManager()));
				resultCommand.add(super.getCommand(request));
				return resultCommand;
			}
		}
		return super.getCommand(request);
	}



	@Override
	protected NodeFigure createNodeFigure(){

		return null;
	}

	@Override
	protected void handleNotificationEvent(Notification notification){
		super.handleNotificationEvent(notification);
		if (getParent() != null) getParent().refresh();
	}



	/**
	 * Command used to create a element when creating a delegation or a provision
	 */
	class CreateTargetRelationElement extends Command {

		private CreateConnectionViewAndElementRequest	request;
		private Command											command	= null;
		private StsElement										element	= null;
		private IElementType										elementType;
		private ViewsManager										vm			= null;
		private IGraphicalEditPart								targetEp;

		public CreateTargetRelationElement(CreateConnectionViewAndElementRequest request, IElementType elementType, ViewsManager vm) {

			this.request = request;
			this.elementType = elementType;
			this.vm = vm;
		}

		@Override
		public void execute(){

			try {
				targetEp = ((IGraphicalEditPart) request.getTargetEditPart()).getChildBySemanticHint("7001");
				if (targetEp == null) targetEp = ((IGraphicalEditPart) request.getTargetEditPart()).getChildBySemanticHint("7002");


				if (elementType == StsToolElementTypes.Goal_3001) {
					Goal g = (Goal) request.getExtendedData().get("sourceGoal");

					Goal reference = containGoalReference((Actor) (((IGraphicalEditPart) request.getTargetEditPart()).getPrimaryView().getElement()), g);

					if (reference != null) {
						CreateRelationshipRequest crr = (CreateRelationshipRequest) request.getConnectionViewAndElementDescriptor().getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class);
						crr.setParameter("delegatedObject", reference);
						return;
					}
				} else if (elementType == StsToolElementTypes.TResource_3002) {
					TResource r = (TResource) request.getExtendedData().get("sourceResource");

					TResource reference = containResourceReference((Actor) (((IGraphicalEditPart) request.getTargetEditPart()).getPrimaryView().getElement()), r);

					if (reference != null) {
						CreateRelationshipRequest crr = (CreateRelationshipRequest) request.getConnectionViewAndElementDescriptor().getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class);
						crr.setParameter("delegatedObject", reference);
						return;
					}
				}



				CreateViewAndElementRequest req = (CreateViewAndElementRequest) CreateViewRequestFactory.getCreateShapeRequest(elementType, targetEp.getDiagramPreferencesHint());

				if (req != null) {
					command = targetEp.getCommand(req);
					try {
						command.execute();
					} catch (Exception e) {
						e.printStackTrace();
					}

					CreateElementRequest cer = (CreateElementRequest) req.getViewAndElementDescriptor().getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class);
					CreateRelationshipRequest crr = (CreateRelationshipRequest) request.getConnectionViewAndElementDescriptor().getCreateElementRequestAdapter().getAdapter(CreateElementRequest.class);
					element = (StsElement) cer.getNewElement();
					crr.setParameter("delegatedObject", element);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public boolean canExecute(){

			return (request.getTargetEditPart() instanceof AgentEditPart || request.getTargetEditPart() instanceof RoleEditPart);
		}

		@Override
		public void undo(){

			String id = element.getStsUniqueID();
			try {
				command.undo();

			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if ((targetEp) != null) targetEp.refresh();
				vm.removeObjectConstraintForAllViews(id);
			} catch (Exception e) {
				System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
			}

		}

		@Override
		public boolean canUndo(){

			return false;
		}

		@Override
		public void redo(){

		}

		private Goal containGoalReference(Actor a,Goal g){

			Goal goalReferenceToFind = getSourceGoalOfReference(g);
			for (Goal actorGoal : a.getGoals()) {
				if (goalReferenceToFind == getSourceGoalOfReference(actorGoal)) { return actorGoal; }
			}
			return null;
		}

		private Goal getSourceGoalOfReference(Goal reference){

			Goal g = reference;
			while (g.getDelegatedFrom().size() > 0) {
				g = g.getDelegatedFrom().get(0).getSourceGoal();
			}
			return g;
		}

		private TResource containResourceReference(Actor a,TResource r){

			TResource resourceReferenceToFind = getSourceResourceOfReference(r);
			for (TResource actorResource : a.getTResources()) {
				if (resourceReferenceToFind == getSourceResourceOfReference(actorResource)) {
					if (resourceReferenceToFind != actorResource) return actorResource;
				}
			}
			return null;
		}

		private TResource getSourceResourceOfReference(TResource reference){

			TResource g = reference;
			while (g.getProvidedFrom().size() > 0) {
				g = g.getProvidedFrom().get(0).getSourceResource();
			}
			return g;
		}

	}
}
