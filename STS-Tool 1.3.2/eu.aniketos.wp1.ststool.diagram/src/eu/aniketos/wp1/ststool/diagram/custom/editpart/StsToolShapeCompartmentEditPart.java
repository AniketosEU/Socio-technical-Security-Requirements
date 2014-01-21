/*
* StsToolShapeCompartmentEditPart.java
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.ui.services.icon.IconService;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SemanticCreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ShapeCompartmentDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.handles.CompartmentCollapseHandle;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.RefreshConnectionsRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.AgentNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.custom.view.constraint.NodeGraphicalConstraint;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

public class StsToolShapeCompartmentEditPart extends ShapeCompartmentEditPart {

	private MyCompartmentCollapseHandle			myCompartmentCollapseHandle	= new MyCompartmentCollapseHandle(this);

	private static final List<IElementType>	popupElements						= new ArrayList<IElementType>();
	static {
		popupElements.add(StsToolElementTypes.Goal_3001);
		popupElements.add(StsToolElementTypes.TResource_3002);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#ShapeCompartmentEditPart(Request)
	 */
	public StsToolShapeCompartmentEditPart(View view) {

		super(view);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#getCommand(Request)
	 */
	@Override
	public Command getCommand(Request request){

		if (request instanceof ChangePropertyValueRequest) {

			ChangePropertyValueRequest req = (ChangePropertyValueRequest) request;
			if (req.getValue() != null) {
				final boolean value = Boolean.parseBoolean(req.getValue().toString());
				//Intercept the collapse request, and add to the result command to save the compartment status in the model
				if (req.getPropertyName().equals("Collapse Compartment")) {
					if (getViewsManager().getCurrentIntView() == ViewsManager.AUTHORIZATION_VIEW) return UnexecutableCommand.INSTANCE;
					CompoundCommand cc = new CompoundCommand();
					cc.add(new ChangeCompartmentConstriantCommand(value, getPrimaryView().getElement(), getViewsManager()));
					cc.add(getParent().getParent().getCommand(getParentChangeBoundRequest(value)));
					cc.add(new ChangeCompartmentResizePolicyCommand(value));
					cc.add(super.getCommand(request));
					return cc;
				}
			}
		}
		return super.getCommand(request);
	}

	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#performRequest(Request)
	 */
	@Override
	public void performRequest(Request request){

		if (request instanceof ChangePropertyValueRequest) {
			ChangePropertyValueRequest req = (ChangePropertyValueRequest) request;
			if (req.getPropertyName().equals("Collapse Compartment")) { return; }
		}
		super.performRequest(request);
	}

	/**
	 * @param collapse
	 *           true if the parent must have the size of a collapsed compartment
	 * @return a request to change the size of the parent of this container
	 */
	private ChangeBoundsRequest getParentChangeBoundRequest(boolean collapse){

		ChangeBoundsRequest changeReq = new ChangeBoundsRequest("resize children");
		changeReq.setEditParts(getParent());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noSave", "true");
		changeReq.setExtendedData(map);

		Dimension finalDimension = new Dimension();
		if (collapse) {
			finalDimension = AgentNodeFigure.THIS_DIMENSION;
		} else {
			StsElement element = (StsElement) getPrimaryView().getElement();
			String c = getViewsManager().getObjectConstraint(getViewsManager().getCurrentIntView(), element.getStsUniqueID());
			NodeGraphicalConstraint ngc = new NodeGraphicalConstraint(c);
			finalDimension = ngc.size.getCopy();
		}

		map.put("size", finalDimension);
		return changeReq;
	}


	/**
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#createDefaultEditPolicies()
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {

			@Override
			protected Command getCreateCommand(CreateRequest request){

				CompoundCommand cc = new CompoundCommand();
				Command c = super.getCreateCommand(request);
				if (c == null) return null;
				cc.add(c);

				cc.add(new InitConstraint(getViewsManager(), null, (CreateViewRequest) request, (Rectangle) getConstraintFor(request)));

				return cc;

			}

			@Override
			protected Command createChangeConstraintCommand(ChangeBoundsRequest request,EditPart child,Object constraint){

				CompoundCommand cc = null;
				try {
					Rectangle r = (Rectangle) constraint;
					r.setSize(-1, -1);
					Point location = r.getLocation();
					if (location.x < 1) location.x = 1;
					if (location.y < 1) location.y = 1;
					r.setLocation(location);

					IFigure f = null;
					if (getParent() instanceof AgentEditPart) {
						f = ((AgentEditPart) getParent()).getPrimaryShape().getFigureAgentNodeFigure();
					}
					if (getParent() instanceof RoleEditPart) {
						f = ((RoleEditPart) getParent()).getPrimaryShape().getFigureRoleNodeFigure();
					}

					Point p = r.getTopLeft().getCopy();
					((IGraphicalEditPart) child).getFigure().translateToAbsolute(p);
					p = getNearestAvaiablePoint(f, p);
					((IGraphicalEditPart) child).getFigure().translateToRelative(p);

					r.setLocation(p);

					StsElement element = (StsElement) (((ShapeImpl) ((GraphicalEditPart) child).getModel()).basicGetElement());
					cc = new CompoundCommand();
					Rectangle oldConstraint = new Rectangle();

					int view = getViewsManager().getCurrentIntView();
					String oldC = getViewsManager().getObjectConstraint(view, element.getStsUniqueID());
					NodeGraphicalConstraint oldCons = null;
					if (oldC != null) {
						oldCons = new NodeGraphicalConstraint(oldC);
						oldConstraint.setLocation(oldCons.location);
						oldConstraint.setSize(oldCons.size);
					}
					cc.add(super.createChangeConstraintCommand(request, child, constraint));

					if (oldCons != null) {
						cc.add(new ChangePositionConstraintCommand(view, element, (Rectangle) constraint, oldConstraint));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return cc;
			}

			private Point getNearestAvaiablePoint(IFigure f,Point p){

				Point result = new Point(p);
				if (f.containsPoint(p)) {

					Rectangle b = f.getBounds().getCopy();
					Point center = b.getCenter();
					if (p.x > center.x && p.y < center.y) {
						result.x = b.getTopRight().x;
					} else if (p.x < center.x && p.y > center.y) {
						result.y = b.getBottomLeft().y;
					} else {
						do {
							result.x = result.x + 1;
							result.y = result.y + 1;
						} while (f.containsPoint(result));
					}
				}
				return result;
			}
		});

		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy() {

			@Override
			protected Command getCreateElementAndViewCommand(CreateViewAndElementRequest request){

				request.setSize(new Dimension(-1, -1));
				// get the element descriptor
				CreateElementRequestAdapter requestAdapter = request.getViewAndElementDescriptor().getCreateElementRequestAdapter();

				// get the semantic request
				CreateElementRequest createElementRequest = (CreateElementRequest) requestAdapter.getAdapter(CreateElementRequest.class);

				if (createElementRequest.getContainer() == null) {
					// complete the semantic request by filling in the
					// host's semantic
					// element as the context
					View view = (View) getHost().getModel();
					EObject hostElement = ViewUtil.resolveSemanticElement(view);

					if (hostElement == null && view.getElement() == null) {
						hostElement = view;
					}

					// Returns null if host is unresolvable so that
					// trying to create a
					// new element in an unresolved shape will not be
					// allowed.
					if (hostElement == null) { return null; }
					createElementRequest.setContainer(hostElement);
				}

				// get the create element command based on the
				// elementdescriptor's
				// request
				Command createElementCommand = getHost().getCommand(new EditCommandRequestWrapper((CreateElementRequest) requestAdapter.getAdapter(CreateElementRequest.class), request.getExtendedData()));

				if (createElementCommand == null) { return UnexecutableCommand.INSTANCE; }
				if (!createElementCommand.canExecute()) { return createElementCommand; }
				// create the semantic create wrapper command
				SemanticCreateCommand semanticCommand = new SemanticCreateCommand(requestAdapter, createElementCommand);
				Command viewCommand = getCreateCommand(request);



				Command refreshConnectionCommand = getHost().getCommand(new RefreshConnectionsRequest(((List) request.getNewObject())));

				InitConstraint c = new InitConstraint(null, request, null, null);

				CompositeCommand cc = new CompositeCommand(semanticCommand.getLabel());
				cc.compose(semanticCommand);
				Command result = new ICommandProxy(cc).chain(c); // <--added here command to initialize position constrait
				result.chain(viewCommand);

				if (refreshConnectionCommand != null) {
					result.chain(refreshConnectionCommand);
				}
				return result;
			}

		});

		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new ShapeCompartmentDropEditPolicy() {

			@Override
			protected Command getDropCommand(ChangeBoundsRequest request){

				//TODO: manage multi object Drag & Drop
				if (request.getEditParts().size() != 1) return UnexecutableCommand.INSTANCE;

				EditPart ep = (EditPart) request.getEditParts().get(0);
				EObject o = ((ShapeImpl) ep.getModel()).basicGetElement();

				if (o != null && (o instanceof Goal || o instanceof TResource)) {
					request.setMoveDelta(new Point(0, 0));
					return UnexecutableCommand.INSTANCE;
				}
				return super.getDropCommand(request);
			}

			@Override
			public void showTargetFeedback(Request req){
			}


		});

		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new ResizableCompartmentEditPolicy() {

			@Override
			protected List createCollapseHandles(){

				List collapseHandles = new ArrayList();
				collapseHandles.add(myCompartmentCollapseHandle);
				return collapseHandles;
			}

		});

		installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new PopupBarEditPolicy() {

			@Override
			protected void populatePopupBars(){

				for (IElementType e : popupElements) {
					if (getViewsManager().isElementVisible(e)) addPopupBarDescriptor(e, IconService.getInstance().getIcon(e, 0));
				}
			}
		});
	}


	/**
	 * Filtered list of children, based on the current view
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#getModelChildren()
	 */
	@Override
	protected List getModelChildren(){

		Object model = getModel();
		if (model != null && model instanceof View) {
			List list = ((View) model).getVisibleChildren();
			ArrayList result = new ArrayList();

			Iterator i = list.iterator();
			while (i.hasNext()) {
				Object o = i.next();
				if (o instanceof ShapeImpl) {
					String SvisualID = ((ShapeImpl) o).getType();
					if (SvisualID.equals("Note") || SvisualID.equals("Text")) {
						//result.add(o);
					} else {

						try {
							int visualID = Integer.parseInt(((ShapeImpl) o).getType());
							if (getViewsManager().getCurrentIntView() != ViewsManager.RESOURCE_VIEW) {


								if (getViewsManager().isElementVisible(StsToolElementTypes.getElementType(visualID))) {
									if (((ShapeImpl) o).getElement() instanceof TResource) {
										TResource res = (TResource) ((ShapeImpl) o).getElement();
										TResource orig = res.getSourceResource(res);
										if (res == orig || res.getActorOwner() != orig.getActorOwner()) {
											result.add(o);
										}
									} else {
										result.add(o);
									}
								}
							} else {
								if (getViewsManager().isElementVisible(StsToolElementTypes.getElementType(visualID))) {
									if (((ShapeImpl) o).getElement() instanceof TResource) {
										TResource tr = (TResource) ((ShapeImpl) o).getElement();
										if (tr.getProvidedFrom().size() == 0) result.add(o);
									} else
										result.add(o);
								}
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return result;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * This method has been disabled. (Void method)
	 */
	@Override
	protected void setCollapsed(boolean collapsed,boolean animate){

	}

	/**
	 * field to indicate if the current compartment is collapsed;
	 */
	boolean	collapsed	= false;

	/**
	 * @return true if the current compartment is collapsed;
	 */
	public boolean isCollapsed(){

		return collapsed;
	}

	/**
	 * Used to collapse or expand this compartment
	 * 
	 * @param collapsed
	 *           true if the current compartment must be collapsed
	 * @param persist
	 *           true if the value must be saved in the metamodel;
	 */
	protected void collapse(boolean collapsed,boolean persist){

		if (getViewsManager().getCurrentIntView() == ViewsManager.AUTHORIZATION_VIEW) {
			collapsed = true;
		}
		this.collapsed = collapsed;
		CompoundCommand cc = new CompoundCommand();
		try {
			cc.add(getParent().getParent().getCommand(getParentChangeBoundRequest(collapsed)));
			cc.add(new ChangeCompartmentResizePolicyCommand(collapsed));
			try {
				cc.execute();
			} catch (Exception e1) {
			}

			ShapeCompartmentFigure ac = (ShapeCompartmentFigure) getCompartmentFigure();
			if (collapsed) {
				ac.setCollapsed();
			} else {
				ac.setExpanded();
			}

			EditPart ep = getParent();
			if (ep instanceof AgentEditPart)
				((AgentEditPart) ep).getPrimaryShape().setCollapsed(collapsed);
			else if (ep instanceof RoleEditPart) ((RoleEditPart) ep).getPrimaryShape().setCollapsed(collapsed);

			if (persist) {
				Command c = new ChangeCompartmentConstriantCommand(collapsed, getPrimaryView().getElement(), getViewsManager());
				getEditDomain().getCommandStack().execute(c);
			}

		} catch (Exception e) {
		}
		myCompartmentCollapseHandle.updateArrow();

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
	 * Command used to initialize the position constraint of a object when created in the current compartment
	 */
	class InitConstraint extends Command {

		CreateViewRequest					createViewRequest;
		CreateViewAndElementRequest	request;
		Rectangle							bounds;
		ViewsManager						vm;

		public InitConstraint(ViewsManager vm, CreateViewAndElementRequest request, CreateViewRequest createViewRequest, Rectangle constraint) {

			this.createViewRequest = createViewRequest;
			this.bounds = constraint;
			this.request = request;
			this.vm = vm;
		}

		@Override
		public void execute(){

			if (request != null) {
				Object o = request.getViewAndElementDescriptor().getElementAdapter().getAdapter(CreateElementRequest.class);
				if (o != null && o instanceof CreateElementRequest) {
					CreateElementRequest cer = (CreateElementRequest) o;
					HashMap<String, EObject> map = new HashMap<String, EObject>(1);
					map.put("Element", cer.getNewElement());
					request.setExtendedData(map);
				}
			} else if (createViewRequest != null) {

				StsElement element = (StsElement) createViewRequest.getExtendedData().get("Element");
				if (element != null) {
					try {
						NodeGraphicalConstraint constraint = new NodeGraphicalConstraint(bounds.getLocation(), bounds.getSize());
						getViewsManager().setObjectConstraintForAllViews(element.getStsUniqueID(), constraint.getConstraintInString());
					} catch (Exception e) {
						System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
					}
				}
			}
		}

		@Override
		public void undo(){

			/*
			 * StsElement element = (StsElement)
			 * createElementRequest.getNewElement();
			 * if (element != null) {
			 * try {
			 * getViewsManager().removeObjectConstraintForAllViews(element.
			 * getStsUniqueID(), null);
			 * } catch (Exception e) {
			 * e.printStackTrace();
			 * StsToolDiagramEditorPlugin.getInstance().logError(
			 * "Error while UNDO initializing Position constraint", e);
			 * }
			 * }
			 */
		}

		@Override
		public boolean canExecute(){

			return true;
		}

		@Override
		public boolean canUndo(){

			return true;
		}
	}

	/**
	 * Command used to change the position constraint of a object when moved in the current compartment
	 */
	class ChangePositionConstraintCommand extends Command {

		StsElement	element;
		Rectangle	oldConstraint;
		Rectangle	newConstraint;
		int			view;

		public ChangePositionConstraintCommand(int view, StsElement element, Rectangle newConstraint, Rectangle oldConstraint) {

			super("Change Position");
			this.element = element;
			this.oldConstraint = oldConstraint;
			this.newConstraint = newConstraint;

			this.view = view;
		}

		@Override
		public boolean canExecute(){

			return true;
		}

		@Override
		public boolean canUndo(){

			return true;
		}

		@Override
		public void execute(){

			if (element != null) {
				try {
					NodeGraphicalConstraint c = new NodeGraphicalConstraint(newConstraint);
					getViewsManager().setObjectConstraint(view, element.getStsUniqueID(), c.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}

		@Override
		public void undo(){

			if (element != null) {
				try {
					NodeGraphicalConstraint c = new NodeGraphicalConstraint(oldConstraint);
					getViewsManager().setObjectConstraint(view, element.getStsUniqueID(), c.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}
	}

	/**
	 * Command used to change the compartment constraint, used when collapsing or expanding with persistence
	 * 
	 * @see collapse(collapsed,persist)
	 */
	class ChangeCompartmentConstriantCommand extends Command {

		private boolean						collapse;
		private EObject						element;
		private NodeGraphicalConstraint	oldC;
		private ViewsManager					vm;

		public ChangeCompartmentConstriantCommand(boolean collapse, EObject element, ViewsManager vm) {

			this.collapse = collapse;
			this.element = element;
			this.vm = vm;
		}

		@Override
		public boolean canExecute(){

			return true;
		}

		@Override
		public boolean canUndo(){

			return true;
		}

		@Override
		public void execute(){

			if (element != null) {
				try {
					String oldCons = vm.getObjectConstraint(vm.getCurrentIntView(), ((StsElement) element).getStsUniqueID());
					this.oldC = new NodeGraphicalConstraint(oldCons);
					NodeGraphicalConstraint oldC = new NodeGraphicalConstraint(oldCons);
					oldC.collapsed = collapse;
					vm.setObjectConstraint(vm.getCurrentIntView(), ((StsElement) element).getStsUniqueID(), oldC.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}

		@Override
		public void undo(){

			if (element != null) {
				try {
					vm.setObjectConstraint(vm.getCurrentIntView(), ((StsElement) element).getStsUniqueID(), oldC.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}
	}

	/**
	 * Command used to change the Resizing policy of the compartment
	 * 
	 * @see collapse(collapsed,persist)
	 */
	class ChangeCompartmentResizePolicyCommand extends Command {

		private boolean	collapsed;

		/**
		 * 
		 * @param collapsed
		 *           if true the compartment become unresizable
		 */
		public ChangeCompartmentResizePolicyCommand(boolean collapsed) {

			this.collapsed = collapsed;
		}

		@Override
		public void execute(){

			EditPolicy editPolicy = getParent().getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
			if (editPolicy instanceof ResizableEditPolicy) {
				if (collapsed) {
					((ResizableEditPolicy) editPolicy).setResizeDirections(PositionConstants.NONE);
				} else {
					((ResizableEditPolicy) editPolicy).setResizeDirections(PositionConstants.NSEW);
				}
				getParent().installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, editPolicy);
			}
		}

		@Override
		public void undo(){

			EditPolicy editPolicy = getParent().getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
			if (editPolicy instanceof ResizableEditPolicy) {
				if (!collapsed) {
					((ResizableEditPolicy) editPolicy).setResizeDirections(PositionConstants.NONE);
				} else {
					((ResizableEditPolicy) editPolicy).setResizeDirections(PositionConstants.NSEW);
				}
				getParent().installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, editPolicy);
			}
		}

	}

	/**
	 * Personalized collapse handle (the blue triangle in the left corner) to intercept the collapse expand actions
	 * 
	 * @see collapse(collapsed,persist)
	 */
	class MyCompartmentCollapseHandle extends CompartmentCollapseHandle {

		public MyCompartmentCollapseHandle(IGraphicalEditPart owner) {

			super(owner);
			collapseFigure.addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClicked(MouseEvent me){

				}

				@Override
				public void mousePressed(MouseEvent me){

					collapsed = !collapsed;
					collapse(collapsed, true);
					me.consume();
				}

				@Override
				public void mouseReleased(MouseEvent me){

					me.consume();
				}


			});
		}

		@SuppressWarnings("restriction")
		public void updateArrow(){

			collapseFigure.setCollapsed(collapsed);
		}
	}
}
