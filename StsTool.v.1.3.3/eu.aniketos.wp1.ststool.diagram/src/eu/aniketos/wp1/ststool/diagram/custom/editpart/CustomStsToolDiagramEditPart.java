/*
* CustomStsToolDiagramEditPart.java
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.ui.services.icon.IconService;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SemanticCreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramDragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DiagramPopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.RefreshConnectionsRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.custom.view.constraint.NodeGraphicalConstraint;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.ExecuteCommandRequest;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;
import eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl;

/**
 * Extension class of the DiagramEditPart that override some method to achieve some stsTool functionality
 */

public abstract class CustomStsToolDiagramEditPart extends DiagramEditPart {


	@Override
	public void performRequest(Request request){
		if (request instanceof ExecuteCommandRequest && request.getType().equals("ExecuteCommandRequest")) {
			getEditDomain().getCommandStack().execute(((ExecuteCommandRequest) request).getCommand());
		}
		super.performRequest(request);
	}



	/**
	 * The viewsManager instance for the current Diagram
	 */
	private ViewsManager								viewsManager	= null;

	private static final List<IElementType>	popupElements	= new ArrayList<IElementType>();
	static {
		popupElements.add(StsToolElementTypes.Agent_2001);
		popupElements.add(StsToolElementTypes.Role_2002);
		popupElements.add(StsToolElementTypes.IResource_2005);
		popupElements.add(StsToolElementTypes.Event_2006);
	}

	/**
	 * Extension of the default constructor with the viewsManager initialization
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#DiagramEditPart(View)
	 */
	public CustomStsToolDiagramEditPart(View diagramView) {

		super(diagramView);

		StsToolDiagramImpl diag = (StsToolDiagramImpl) diagramView.getElement();
		try {
			this.viewsManager = new ViewsManager(diag.getGraphicalConstraintMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Filtered list of children, based on the current view. Note and Text will be leaved.
	 * 
	 * @return List of children that can be showed in the current view.
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#getModelChildren()
	 */
	@Override
	protected List getModelChildren(){

		Object model = getModel();
		if (model != null && model instanceof View) {
			ArrayList list = new ArrayList(((View) model).getVisibleChildren());
			ArrayList result = new ArrayList();

			Iterator it = list.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o instanceof ShapeImpl) {
					String SvisualID = ((ShapeImpl) o).getType();
					if (SvisualID.equals("Note") || SvisualID.equals("Text")) {
						//result.add(o);
					} else {
						int visualID = Integer.parseInt(((ShapeImpl) o).getType());
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



	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		//removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new DiagramPopupBarEditPolicy() {

			@Override
			protected void populatePopupBars(){

				for (IElementType e : popupElements) {
					if (getViewsManager().isElementVisible(e)) addPopupBarDescriptor(e, IconService.getInstance().getIcon(e, 0));
				}
			}
		});
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new XYLayoutEditPolicy() {

			@Override
			protected Command createChangeConstraintCommand(ChangeBoundsRequest request,EditPart child,Object constraint){


				Map<String, Object> map = request.getExtendedData();
				if (map != null && map.get("noSave") != null && map.get("noSave").equals("true")) {
					Rectangle r = (Rectangle) constraint;
					r.setSize((Dimension) map.get("size"));
					return super.createChangeConstraintCommand(request, child, r);
				}


				StsElement element = (StsElement) (((ShapeImpl) ((GraphicalEditPart) child).getModel()).basicGetElement());
				CompoundCommand cc = new CompoundCommand();
				Rectangle oldConstraint = new Rectangle();

				int view = getViewsManager().getCurrentIntView();
				NodeGraphicalConstraint oldCons = new NodeGraphicalConstraint(getViewsManager().getObjectConstraint(view, element.getStsUniqueID()));
				oldConstraint.setLocation(oldCons.location);
				oldConstraint.setSize(oldCons.size);
				cc.add(super.createChangeConstraintCommand(request, child, constraint));
				Rectangle r = ((Rectangle) constraint).getCopy();
				if (child instanceof AgentEditPart || child instanceof RoleEditPart) {
					StsToolShapeCompartmentEditPart scep = (StsToolShapeCompartmentEditPart) ((IGraphicalEditPart) child).getChildBySemanticHint("7001");
					if (scep == null) scep = (StsToolShapeCompartmentEditPart) ((IGraphicalEditPart) child).getChildBySemanticHint("7002");
					if (scep.isCollapsed()) {
						r.setSize(oldCons.size);
					}
				}
				cc.add(new ChangePositionConstraintCommand(view, element, r, oldConstraint));

				return cc;
			}

		});

		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy() {

			@Override
			protected Command getCreateElementAndViewCommand(CreateViewAndElementRequest request){

				request.setSize(new Dimension(-1, -1));
				/*
				 * if(request.getViewAndElementDescriptor().getSemanticHint().equals(
				 * "2001")){
				 * request.setSize(AgentNodeFigure.THIS_DIMENSION);
				 * }
				 * else
				 * if(request.getViewAndElementDescriptor().getSemanticHint().equals
				 * ("2002")){
				 * request.setSize(RoleNodeFigure.THIS_DIMENSION);
				 * }
				 */
				if (request.getViewAndElementDescriptor().getSemanticHint().equals("2003"))
					return UnexecutableCommand.INSTANCE;
				else if (request.getViewAndElementDescriptor().getSemanticHint().equals("2004")) return UnexecutableCommand.INSTANCE;
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



				Rectangle r = new Rectangle(request.getLocation(), new Dimension(-1, -1));
				InitConstraint c = new InitConstraint(createElementRequest, r);

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

		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DiagramDragDropEditPolicy() {

			@Override
			protected Command getDropCommand(ChangeBoundsRequest request){

				Iterator i = request.getEditParts().iterator();
				while (i.hasNext()) {
					Object o = i.next();
					if (o instanceof Goal2EditPart || o instanceof TResource2EditPart) {
						request.setMoveDelta(new Point(0, 0));
						return UnexecutableCommand.INSTANCE;
					}
				}
				return super.getDropCommand(request);
			}
		});
	}

	/**
	 * Get the current viewManager, used to get the current view, or to change view.
	 * 
	 * @return the currentViewManger
	 */
	public ViewsManager getViewsManager(){

		return viewsManager;
	}



	IPropertyChangeListener	ipcl	= new IPropertyChangeListener() {

												@Override
												public void propertyChange(PropertyChangeEvent event){

													if (event.getProperty().equals(IPreferenceConstants.PREF_DEFAULT_FONT)) {
														try {
															int view = getViewsManager().getCurrentIntView();
															getViewsManager().setCurrentView(ViewsManager.EMPTY_VIEW);
															getViewsManager().setCurrentView(view);
														} catch (Exception ex) {
															ex.printStackTrace();
														}
													}

												}
											};

	@Override
	public void activate(){

		super.activate();
		StsToolDiagramEditorPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(ipcl);
	}

	@Override
	public void deactivate(){

		StsToolDiagramEditorPlugin.getInstance().getPreferenceStore().removePropertyChangeListener(ipcl);
		super.deactivate();
	}

	@Override
	public Command getCommand(Request request){
		if (request.getType().equals("duplicate")) return null;
		return super.getCommand(request);
	}



	/**
	 * Command used to change the position constraint of a object when moved in the current diagram
	 */
	class ChangePositionConstraintCommand extends Command {

		StsElement	element;
		Rectangle	oldConstraint;
		Rectangle	newConstraint;
		int			view;
		boolean		collapsed;

		/**
		 * 
		 * @param view
		 *           the current view, for which the object position should be changed
		 * @param element
		 *           the model element associated to the object
		 * @param newConstraint
		 *           the new position Constraint
		 * @param oldConstraint
		 *           the current position constraint (used to perform UNDO)
		 */
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
					collapsed = new NodeGraphicalConstraint(getViewsManager().getObjectConstraint(view, element.getStsUniqueID())).collapsed;
					NodeGraphicalConstraint c = new NodeGraphicalConstraint(newConstraint);
					c.collapsed = collapsed;

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
					c.collapsed = collapsed;
					getViewsManager().setObjectConstraint(view, element.getStsUniqueID(), c.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}
	}

	/**
	 * Command used to initialize the position constraint of a object when created in the current diagram
	 */
	class InitConstraint extends Command {

		CreateElementRequest	createElementRequest;

		Rectangle				bounds;

		Command					setConstraintCommand	= null;

		/**
		 * Create a Command used to initialize the position constraint for the current view
		 * 
		 * @param createElementRequest
		 *           the request used to create the element
		 * @param constraint
		 *           the object Constriant
		 */
		public InitConstraint(CreateElementRequest createElementRequest, Rectangle constraint) {

			this.createElementRequest = createElementRequest;
			this.bounds = constraint;
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

			StsElement element = (StsElement) createElementRequest.getNewElement();
			if (element != null) {
				try {
					NodeGraphicalConstraint constraint = new NodeGraphicalConstraint(bounds.getLocation(), bounds.getSize());
					getViewsManager().setObjectConstraintForAllViews(element.getStsUniqueID(), constraint.getConstraintInString());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}

		@Override
		public void undo(){

			StsElement element = (StsElement) createElementRequest.getNewElement();
			if (element != null) {
				try {
					getViewsManager().removeObjectConstraintForAllViews(element.getStsUniqueID());
				} catch (Exception e) {
					System.err.println("Error " + e.getStackTrace()[0].getClassName() + " at line " + e.getStackTrace()[0].getLineNumber());
				}
			}
		}
	}

	public void flushHistory(){

		getEditDomain().getCommandStack().dispose();
	}


	/* @Override
	   protected void handleNotificationEvent(Notification event) {
	   super.handleNotificationEvent(event);
	   
	   if (event.getEventType()==Notification.ADD){
	   Object o = event.getNewValue();
	   if(o instanceof StsElement){
	   final StsElement e = (StsElement)o;
	   
	   System.out.println("ADDED :"+e.getClass().getSimpleName()+" "+e.getStsUniqueID
	   ());
	   new Thread(new Runnable() {
	   
	   @Override
	   public void run() {
	   long startTime= System.currentTimeMillis();
	   long maxTime=1000;
	   EditPart ep=null;
	   while(System.currentTimeMillis()-startTime<maxTime && ep==null){
	   ep=findEditPart(CustomStsToolDiagramEditPart.this, e);
	   try {Thread.currentThread().sleep(10);} catch (InterruptedException e)
	   {e.printStackTrace();}
	   }
	   System.out.println("EP found "+ep);
	   }
	   }).start();
	   }
	   }
	   if (event.getEventType()==Notification.REMOVE){
	   Object o = event.getOldValue();
	   if(o instanceof StsElement){
	   StsElement e = (StsElement)o;
	   System.out.println("REMOVED :"+e.getClass().getSimpleName()+" "+e.
	   getStsUniqueID());
	   }
	   }
	   }*/

}
