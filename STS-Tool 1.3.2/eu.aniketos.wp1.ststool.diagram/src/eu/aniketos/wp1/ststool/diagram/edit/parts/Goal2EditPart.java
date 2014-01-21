/*
* Goal2EditPart.java
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
import java.util.List;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.*;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.*;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ComponentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.*;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.GoalNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.Goal2ItemSemanticEditPolicy;
import eu.aniketos.wp1.ststool.diagram.edit.policies.StsToolTextSelectionEditPolicy;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * 
 */
public class Goal2EditPart extends StsToolShapeNodeEditPart {

	@Override
	public Command getCommand(Request request){

		if(request.getType().equals(REQ_CONNECTION_START)){
			CreateUnspecifiedTypeConnectionRequest req=(CreateUnspecifiedTypeConnectionRequest)request;
			Object o= req.getTargetEditPart()!=null ?req.getTargetEditPart().getAdapter(EObject.class):null;
			boolean delegation=((MetamodelType)req.getElementTypes().get(0)).getEClass().getName().equals("Delegation");
			if(delegation && o instanceof Goal){
				if(((Goal)o).getOutgoingDecompositions().size()>0){
					return null;
				}
			}
		}
		
		return super.getCommand(request);
	}

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 3001;

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
	public Goal2EditPart(View view) {
		super(view);
	}


	/**
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new GraphicalNodeEditPolicy());

		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Goal2ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy());
	}



	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy(){

		ConstrainedToolbarLayoutEditPolicy lep = new ConstrainedToolbarLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child){
				if (child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null) {
					if (child instanceof ITextAwareEditPart) { return new StsToolTextSelectionEditPolicy(); }
				}
				return super.createChildEditPolicy(child);
			}
		};
		return lep;
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

		GoalName2EditPart label = (GoalName2EditPart) getChildBySemanticHint(Integer.toString(GoalName2EditPart.VISUAL_ID));
		label.performDirectEditRequest(request);
		return;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape(){
		GoalFigure figure = new GoalFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public GoalFigure getPrimaryShape(){
		return (GoalFigure) primaryShape;
	}

	@Override
	protected void handleNotificationEvent(Notification notification){

		super.handleNotificationEvent(notification);
		Object obj = notification.getNotifier();
		if (obj instanceof Goal) {

			if ((notification.getFeatureID(Goal.class) == StstoolPackage.GOAL__CAPABILITY)) {
				refreshVisuals();
			}
			if ((notification.getFeatureID(Goal.class) == StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS)) {
				refreshVisuals();
			}
			if ((notification.getFeatureID(Goal.class) == StstoolPackage.GOAL__DELEGATED_FROM)) {
				refreshVisuals();
			}
		}
	}

	@Override
	protected void refreshVisuals(){

		try {
			super.refreshVisuals();
			if (!(getNotationView().getElement() instanceof Goal)) return;
			Goal goal = (Goal) getNotationView().getElement();
			if (goal == null) return;
			getPrimaryShape().setCapability(goal.isCapability());
			getPrimaryShape().setDelegated(goal.getDelegatedFrom().size() > 0);
			try {
				CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
				getPrimaryShape().setError(editor.getMarkerValue(this));
			} catch (Exception e) {
			}
			/*int type = -1;
			if (goal.getOutgoingDecompositions().size() > 0) {
				if (goal.getOutgoingDecompositions().get(0) instanceof GoalDecompositionOR)
					type = GoalNodeFigure.TYPE_OR;
				else if (goal.getOutgoingDecompositions().get(0) instanceof GoalDecompositionAND) type = GoalNodeFigure.TYPE_AND;
			}
			getPrimaryShape().setDecompostition(type);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart){
		if (childEditPart instanceof GoalName2EditPart) {
			((GoalName2EditPart) childEditPart).setLabel(getPrimaryShape().getFigureGoalNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart){
		if (childEditPart instanceof GoalName2EditPart) { return true; }
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
	 * @generated
	 */
	@Override
	public EditPolicy getPrimaryDragEditPolicy(){
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
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
		return getChildBySemanticHint(StsToolVisualIDRegistry.getType(GoalName2EditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.Need_4001);
		types.add(StsToolElementTypes.Produce_4002);
		types.add(StsToolElementTypes.Modify_4003);
		types.add(StsToolElementTypes.PositiveGoalContribution_4004);
		types.add(StsToolElementTypes.NegativeGoalContribution_4005);
		types.add(StsToolElementTypes.GoalDecompositionOR_4006);
		types.add(StsToolElementTypes.GoalDecompositionAND_4007);
		types.add(StsToolElementTypes.IncompatibleDuties_4016);
		types.add(StsToolElementTypes.CompatibleDuties_4017);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof TResourceEditPart) {
			types.add(StsToolElementTypes.Need_4001);
		}
		if (targetEditPart instanceof TResource2EditPart) {
			types.add(StsToolElementTypes.Need_4001);
		}
		if (targetEditPart instanceof TResourceEditPart) {
			types.add(StsToolElementTypes.Produce_4002);
		}
		if (targetEditPart instanceof TResource2EditPart) {
			types.add(StsToolElementTypes.Produce_4002);
		}
		if (targetEditPart instanceof TResourceEditPart) {
			types.add(StsToolElementTypes.Modify_4003);
		}
		if (targetEditPart instanceof TResource2EditPart) {
			types.add(StsToolElementTypes.Modify_4003);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.PositiveGoalContribution_4004);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.PositiveGoalContribution_4004);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.NegativeGoalContribution_4005);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.NegativeGoalContribution_4005);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.GoalDecompositionOR_4006);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.GoalDecompositionOR_4006);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.GoalDecompositionAND_4007);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.GoalDecompositionAND_4007);
		}
		if (targetEditPart instanceof RoleEditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.IncompatibleDuties_4016);
		}
		if (targetEditPart instanceof RoleEditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		if (targetEditPart instanceof GoalEditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart) {
			types.add(StsToolElementTypes.CompatibleDuties_4017);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.Need_4001) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.Need_4001) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		if (relationshipType == StsToolElementTypes.Produce_4002) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.Produce_4002) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		if (relationshipType == StsToolElementTypes.Modify_4003) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.Modify_4003) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		if (relationshipType == StsToolElementTypes.PositiveGoalContribution_4004) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.PositiveGoalContribution_4004) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.NegativeGoalContribution_4005) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.NegativeGoalContribution_4005) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionOR_4006) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionOR_4006) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionAND_4007) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionAND_4007) {
			types.add(StsToolElementTypes.Goal_3001);
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
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.PositiveGoalContribution_4004);
		types.add(StsToolElementTypes.NegativeGoalContribution_4005);
		types.add(StsToolElementTypes.GoalDecompositionOR_4006);
		types.add(StsToolElementTypes.GoalDecompositionAND_4007);
		types.add(StsToolElementTypes.Threat_4015);
		types.add(StsToolElementTypes.IncompatibleDuties_4016);
		types.add(StsToolElementTypes.CompatibleDuties_4017);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.PositiveGoalContribution_4004) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.PositiveGoalContribution_4004) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.NegativeGoalContribution_4005) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.NegativeGoalContribution_4005) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionOR_4006) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionOR_4006) {
			types.add(StsToolElementTypes.Goal_3001);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionAND_4007) {
			types.add(StsToolElementTypes.Goal_2003);
		}
		if (relationshipType == StsToolElementTypes.GoalDecompositionAND_4007) {
			types.add(StsToolElementTypes.Goal_3001);
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
		return types;
	}



	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connEditPart){

		if (connEditPart instanceof GoalDecompositionOREditPart || connEditPart instanceof GoalDecompositionANDEditPart) return getDecompositionSourceAnchor();
		return super.getSourceConnectionAnchor(connEditPart);

	}


	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connEditPart){

		if (connEditPart instanceof GoalDecompositionOREditPart || connEditPart instanceof GoalDecompositionANDEditPart) return getDecompositionTargetAnchor();
		return super.getTargetConnectionAnchor(connEditPart);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request){

		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			CreateUnspecifiedTypeConnectionRequest req = (CreateUnspecifiedTypeConnectionRequest) request;
			if ((req.getElementTypes().get(0).equals(StsToolElementTypes.GoalDecompositionAND_4007) || req.getElementTypes().get(0).equals(StsToolElementTypes.GoalDecompositionOR_4006))) return getDecompositionSourceAnchor();

			return new SlidableAnchor(this.getPrimaryShape(), new PrecisionPoint(req.getLocation()));
		}
		return super.getSourceConnectionAnchor(request);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request){

		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			CreateUnspecifiedTypeConnectionRequest req = (CreateUnspecifiedTypeConnectionRequest) request;
			if ((req.getElementTypes().get(0).equals(StsToolElementTypes.GoalDecompositionAND_4007) || req.getElementTypes().get(0).equals(StsToolElementTypes.GoalDecompositionOR_4006))) { return getDecompositionTargetAnchor(); }
			return new SlidableAnchor(this.getPrimaryShape(), new PrecisionPoint(req.getLocation()));
		}
		return super.getSourceConnectionAnchor(request);
	}

	private ConnectionAnchor getDecompositionSourceAnchor(){

		return new SlidableAnchor(getPrimaryShape()) {

			@Override
			public Point getLocation(Point reference){

				return ((GoalNodeFigure) getOwner()).getSourceAnchorPoint();
			}
		};
	}

	private ConnectionAnchor getDecompositionTargetAnchor(){

		return new SlidableAnchor(getPrimaryShape()) {

			@Override
			public Point getLocation(Point reference){

				return ((GoalNodeFigure) getOwner()).getTargetAnchorPoint();
			}
		};
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
	 * @generated
	 */
	public class GoalFigure extends GoalNodeFigure {

		/**
		 * @generated NOT
		 */
		private WrappingLabel	fFigureGoalNameFigure;

		public GoalFigure() {

			super();
			GoalFigureGen();
		}

		/**
		 * @generated
		 */
		@SuppressWarnings("deprecation")
		private void GoalFigureGen(){

			ToolbarLayout layoutThis = new ToolbarLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);

			layoutThis.setSpacing(5);
			layoutThis.setVertical(false);

			this.setLayoutManager(layoutThis);



			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents(){

			fFigureGoalNameFigure = getNameLabel();

		}

		/**
		 * @generated
		 */
		@SuppressWarnings("unused")
		private void createContentsGen(){


			fFigureGoalNameFigure = new WrappingLabel();
			fFigureGoalNameFigure.setText("<Goal...>");

			this.add(fFigureGoalNameFigure);


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
		public WrappingLabel getFigureGoalNameFigure(){
			return fFigureGoalNameFigure;
		}

	}

}
