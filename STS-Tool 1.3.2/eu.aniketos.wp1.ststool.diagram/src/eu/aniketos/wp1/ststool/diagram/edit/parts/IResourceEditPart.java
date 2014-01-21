/*
* IResourceEditPart.java
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
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.IResourceNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.IResourceItemSemanticEditPolicy;
import eu.aniketos.wp1.ststool.diagram.edit.policies.StsToolTextSelectionEditPolicy;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * 
 */
public class IResourceEditPart extends StsToolShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 2005;

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
	public IResourceEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new IResourceItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
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

	/**
	 * @generated
	 */
	protected IFigure createNodeShape(){
		IResourceFigure figure = new IResourceFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public IResourceFigure getPrimaryShape(){
		return (IResourceFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart){
		if (childEditPart instanceof IResourceNameEditPart) {
			((IResourceNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureIResourceNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart){
		if (childEditPart instanceof IResourceNameEditPart) { return true; }
		return false;
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

		IResourceNameEditPart label = (IResourceNameEditPart) getChildBySemanticHint(Integer.toString(IResourceNameEditPart.VISUAL_ID));
		label.performDirectEditRequest(request);
		return;
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
		return getChildBySemanticHint(StsToolVisualIDRegistry.getType(IResourceNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.PartOf_4009);
		types.add(StsToolElementTypes.TangibleBy_4010);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof TResourceEditPart) {
			types.add(StsToolElementTypes.PartOf_4009);
		}
		if (targetEditPart instanceof eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart) {
			types.add(StsToolElementTypes.PartOf_4009);
		}
		if (targetEditPart instanceof TResource2EditPart) {
			types.add(StsToolElementTypes.PartOf_4009);
		}
		if (targetEditPart instanceof TResourceEditPart) {
			types.add(StsToolElementTypes.TangibleBy_4010);
		}
		if (targetEditPart instanceof TResource2EditPart) {
			types.add(StsToolElementTypes.TangibleBy_4010);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.IResource_2005);
		}
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		if (relationshipType == StsToolElementTypes.TangibleBy_4010) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.TangibleBy_4010) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget(){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(StsToolElementTypes.Own_4008);
		types.add(StsToolElementTypes.PartOf_4009);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(IElementType relationshipType){
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == StsToolElementTypes.Own_4008) {
			types.add(StsToolElementTypes.Agent_2001);
		}
		if (relationshipType == StsToolElementTypes.Own_4008) {
			types.add(StsToolElementTypes.Role_2002);
		}
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.TResource_2004);
		}
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.IResource_2005);
		}
		if (relationshipType == StsToolElementTypes.PartOf_4009) {
			types.add(StsToolElementTypes.TResource_3002);
		}
		return types;
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
	public class IResourceFigure extends IResourceNodeFigure {

		/**
		 * @generated
		 */
		private WrappingLabel	fFigureIResourceNameFigure;

		public IResourceFigure() {

			super();
			IResourceFigureGen();
		}

		/**
		 * @generated
		 */
		@SuppressWarnings("deprecation")
		private void IResourceFigureGen(){

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

			fFigureIResourceNameFigure = getNameLabel();

		}

		/**
		 * @generated
		 */
		@SuppressWarnings("unused")
		private void createContentsGen(){


			fFigureIResourceNameFigure = new WrappingLabel();
			fFigureIResourceNameFigure.setText("<IResource...>");

			this.add(fFigureIResourceNameFigure);


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
		public WrappingLabel getFigureIResourceNameFigure(){
			return fFigureIResourceNameFigure;
		}

	}

}
