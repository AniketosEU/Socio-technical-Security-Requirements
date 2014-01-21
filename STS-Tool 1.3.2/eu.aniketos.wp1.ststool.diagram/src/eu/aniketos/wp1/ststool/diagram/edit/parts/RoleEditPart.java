/*
* RoleEditPart.java
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
package eu.aniketos.wp1.ststool.diagram.edit.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableOvalAnchor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.diagram.custom.constraint.LinkConstraint;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.RoleCompartmentFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.RoleNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.layouts.StsActorCompartmentLayout;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.RoleItemSemanticEditPolicy;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * 
 */
public class RoleEditPart extends StsToolShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 2002;

	/**
	 * @generated
	 */
	protected IFigure			contentPane;

	/**
	 * @generated
	 */
	protected IFigure			primaryShape;

	/**
	 * @generated
	 */
	public RoleEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RoleItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy(){
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child){
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request){
				return null;
			}

			protected Command getCreateCommand(CreateRequest request){
				return null;
			}
		};
		return lep;
	}

	@Override
	public Command getCommand(Request request){

		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			CreateUnspecifiedTypeConnectionRequest req = (CreateUnspecifiedTypeConnectionRequest) request;
			if (req.getElementTypes().contains(StsToolElementTypes.Provision_4012) && !(req.getSourceEditPart() instanceof TResource2EditPart))
				return null;
			else if (req.getElementTypes().contains(StsToolElementTypes.Delegation_4013) && !(req.getSourceEditPart() instanceof Goal2EditPart)) return null;
		}

		if (request instanceof CreateConnectionViewAndElementRequest) {

			CreateConnectionViewAndElementRequest req = (CreateConnectionViewAndElementRequest) request;

			String semanticHint = req.getConnectionViewDescriptor().getSemanticHint();
			if (semanticHint.equals("4012") && req.getSourceEditPart() instanceof TResource2EditPart) {//Provision

				IGraphicalEditPart sourceEP = (IGraphicalEditPart) req.getSourceEditPart().getParent().getParent();
				if (sourceEP instanceof AgentEditPart || sourceEP instanceof RoleEditPart) {

					TResource resource = (TResource) ((TResource2EditPart) req.getSourceEditPart()).getPrimaryView().getElement();
					if (resource.getName() == null) return UnexecutableCommand.INSTANCE;
					if (!LinkConstraint.canExistProvision((Actor) sourceEP.getPrimaryView().getElement(), (Actor) this.getPrimaryView().getElement(), resource)) return null;
					CreateConnectionViewAndElementRequest ccver = new CreateConnectionViewAndElementRequest(StsToolElementTypes.Provision_4012, "4012", getDiagramPreferencesHint());
					Command c = CreateConnectionViewAndElementRequest.getCreateCommand(ccver, sourceEP, this);

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("sourceResource", resource);
					ccver.setExtendedData(map);

					request = ccver;
					return c;
				}

			} else if (semanticHint.equals("4013") && req.getSourceEditPart() instanceof Goal2EditPart) {//Delegation
				IGraphicalEditPart sourceEP = (IGraphicalEditPart) req.getSourceEditPart().getParent().getParent();
				if (sourceEP instanceof AgentEditPart || sourceEP instanceof RoleEditPart) {

					Goal goal = (Goal) ((Goal2EditPart) req.getSourceEditPart()).getPrimaryView().getElement();
					if (goal.getName() == null) return UnexecutableCommand.INSTANCE;
					if (!LinkConstraint.canExistDelegation((Actor) sourceEP.getPrimaryView().getElement(), (Actor) this.getPrimaryView().getElement(), goal)) return null;
					CreateConnectionViewAndElementRequest ccver = new CreateConnectionViewAndElementRequest(StsToolElementTypes.Delegation_4013, "4013", getDiagramPreferencesHint());
					Command c = CreateConnectionViewAndElementRequest.getCreateCommand(ccver, sourceEP, this);

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("sourceGoal", goal);
					ccver.setExtendedData(map);

					request = ccver;
					return c;
				}
			}
		}
		return super.getCommand(request);
	}


	@Override
	public void performRequest(Request request){

		if (request.getType().equals(RequestConstants.REQ_OPEN)) {
			editLabel(request);
		} else
			super.performRequest(request);
	}

	@Override
	protected void performDirectEditRequest(Request request){

		if (request.getType().equals(REQ_DIRECT_EDIT)) editLabel(request);
	}

	private void editLabel(Request request){

		RoleNameEditPart label = (RoleNameEditPart) getChildBySemanticHint(Integer.toString(RoleNameEditPart.VISUAL_ID));
		label.performDirectEditRequest(request);
		return;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape(){
		RoleFigure figure = new RoleFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public RoleFigure getPrimaryShape(){
		return (RoleFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart){
		if (childEditPart instanceof RoleNameEditPart) {
			((RoleNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureRoleNameFigure());
			return true;
		}
		if (childEditPart instanceof RoleRoleCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureRoleCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((RoleRoleCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart){
		if (childEditPart instanceof RoleNameEditPart) { return true; }
		if (childEditPart instanceof RoleRoleCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigureRoleCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((RoleRoleCompartmentEditPart) childEditPart).getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	@Override
	protected void addChildVisual(EditPart childEditPart,int index){
		if (addFixedChild(childEditPart)) { return; }
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	@Override
	protected void removeChildVisual(EditPart childEditPart){
		if (removeFixedChild(childEditPart)) { return; }
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	@Override
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart){
		if (editPart instanceof RoleRoleCompartmentEditPart) { return getPrimaryShape().getFigureRoleCompartmentFigure(); }
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate(){
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	@Override
	protected NodeFigure createNodeFigure(){
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane. Respects layout one may have set for generated figure.
	 * 
	 * @param nodeShape
	 *           instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape){
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	@Override
	public IFigure getContentPane(){
		if (contentPane != null) { return contentPane; }
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	@Override
	protected void setForegroundColor(Color color){
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setBackgroundColor(Color color){
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineWidth(int width){
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setLineType(int style){
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	@Override
	public EditPart getPrimaryChildEditPart(){
		return getChildBySemanticHint(StsToolVisualIDRegistry.getType(RoleNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.Own_4008);
		types.add(StsToolElementTypes.Provision_4012);
		types.add(StsToolElementTypes.Delegation_4013);
		types.add(StsToolElementTypes.Authorisation_4014);
		types.add(StsToolElementTypes.IncompatibleDuties_4016);
		types.add(StsToolElementTypes.CompatibleDuties_4017);
		types.add(StsToolElementTypes.Dependency_4018);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof IResourceEditPart) {
			types.add(StsToolElementTypes.Own_4008);
		}
		if (targetEditPart instanceof AgentEditPart) {
			types.add(StsToolElementTypes.Provision_4012);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.Provision_4012);
		}
		if (targetEditPart instanceof AgentEditPart) {
			types.add(StsToolElementTypes.Delegation_4013);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.Delegation_4013);
		}
		if (targetEditPart instanceof AgentEditPart) {
			types.add(StsToolElementTypes.Authorisation_4014);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.Authorisation_4014);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof Goal2EditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		if (targetEditPart instanceof Goal2EditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart) {
			types.add(StsToolElementTypes.Dependency_4018);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.Own_4008) {
			types.add(StsToolElementTypes.IResource_2005);
		}
		if (relationshipType == StsToolElementTypes.Provision_4012) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Provision_4012) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.Delegation_4013) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Delegation_4013) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.Authorisation_4014) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Authorisation_4014) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.Dependency_4018) {
			types.add(StsToolElementTypes.Role_2002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.Play_4011);
		types.add(StsToolElementTypes.Provision_4012);
		types.add(StsToolElementTypes.Delegation_4013);
		types.add(StsToolElementTypes.Authorisation_4014);
		types.add(StsToolElementTypes.Threat_4015);
		types.add(StsToolElementTypes.IncompatibleDuties_4016);
		types.add(StsToolElementTypes.CompatibleDuties_4017);
		types.add(StsToolElementTypes.Dependency_4018);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.Play_4011) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Provision_4012) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Provision_4012) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.Delegation_4013) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Delegation_4013) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.Authorisation_4014) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Authorisation_4014) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.Threat_4015) {
			types.add(StsToolElementTypes.Event_2006);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.IncompatibleDuties_4016) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.CompatibleDuties_4017) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.Dependency_4018) {
			types.add(StsToolElementTypes.Role_2002);
		}
		return types;
	}


	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connEditPart){

		return new SlidableOvalAnchor(getPrimaryShape().getFigureRoleNodeFigure());

	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request){

		return new SlidableOvalAnchor(getPrimaryShape().getFigureRoleNodeFigure());

	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connEditPart){

		return new SlidableOvalAnchor(getPrimaryShape().getFigureRoleNodeFigure());
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request){

		return new SlidableOvalAnchor(getPrimaryShape().getFigureRoleNodeFigure());
	}

	@Override
	public void refresh(){
		super.refresh();
		try {
			CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			getPrimaryShape().setError(editor.getMarkerValue(this));
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 */
	public class RoleFigure extends RectangleFigure implements ISTSErrorMarker {

		/**
		 * @generated
		 */
		private RoleCompartmentFigure			fFigureRoleCompartmentFigure;
		/**
		 * @generated
		 */
		private WrappingLabel					fFigureRoleNameFigure;

		private StsActorCompartmentLayout	layout;

		private RoleNodeFigure					roleNodeFigure0;

		/**
		 * @generated
		 */
		public RoleFigure() {
			this.setOutline(false);
			this.setLineWidth(1);
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents(){

			setFill(false);
			layout = new StsActorCompartmentLayout();
			setLayoutManager(layout);

			fFigureRoleCompartmentFigure = new RoleCompartmentFigure();
			layout.setCompartmentFigure(fFigureRoleCompartmentFigure);
			this.add(fFigureRoleCompartmentFigure);

			roleNodeFigure0 = new RoleNodeFigure();
			layout.setNodeFigure(roleNodeFigure0);

			ToolbarLayout layoutRoleNodeFigure0 = new ToolbarLayout();
			layoutRoleNodeFigure0.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
			layoutRoleNodeFigure0.setSpacing(5);

			roleNodeFigure0.setLayoutManager(new StackLayout());

			this.add(roleNodeFigure0, new Rectangle(0, 0, RoleNodeFigure.THIS_DIMENSION.width, RoleNodeFigure.THIS_DIMENSION.height));
			fFigureRoleNameFigure = roleNodeFigure0.getNameLabel();
		}

		/**
		 * @generated
		 */
		@SuppressWarnings( { "unused", "deprecation" })
		private void createContentsGen(){


			RoleNodeFigure roleNodeFigure0 = new RoleNodeFigure();


			roleNodeFigure0.setPreferredSize(new Dimension(getMapMode().DPtoLP(100), getMapMode().DPtoLP(100)));

			this.add(roleNodeFigure0);

			ToolbarLayout layoutRoleNodeFigure0 = new ToolbarLayout();
			layoutRoleNodeFigure0.setStretchMinorAxis(false);
			layoutRoleNodeFigure0.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);

			layoutRoleNodeFigure0.setSpacing(5);
			layoutRoleNodeFigure0.setVertical(false);

			roleNodeFigure0.setLayoutManager(layoutRoleNodeFigure0);



			fFigureRoleNameFigure = new WrappingLabel();
			fFigureRoleNameFigure.setText("<Role...>");

			roleNodeFigure0.add(fFigureRoleNameFigure);



			fFigureRoleCompartmentFigure = new RoleCompartmentFigure();



			this.add(fFigureRoleCompartmentFigure);


		}

		@Override
		public Dimension getMinimumSize(int wHint,int hHint){

			return super.getMinimumSize(1, 1);
		}

		boolean	collapsed;

		public void setCollapsed(boolean collapsed){

			layout.setCollapsed(collapsed);
			this.collapsed = collapsed;
		}

		@Override
		protected void outlineShape(Graphics graphics){

			// TODO Auto-generated method stub
			//super.outlineShape(graphics);
		}

		/**
		 * @generated
		 */
		private boolean	myUseLocalCoordinates	= false;

		/**
		 * @generated
		 */
		@Override
		protected boolean useLocalCoordinates(){
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates){
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public RoleCompartmentFigure getFigureRoleCompartmentFigure(){
			return fFigureRoleCompartmentFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureRoleNameFigure(){
			return fFigureRoleNameFigure;
		}

		/**
		 * @return the ellipse figure used for Upper left Node
		 */
		public RoleNodeFigure getFigureRoleNodeFigure(){

			return roleNodeFigure0;
		}

		@Override
		public void setError(STSErrorType errorType){
			roleNodeFigure0.setError(errorType);
		}
	}

}
