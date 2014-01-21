/*
* StsToolEditPartFactory.java
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

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.CustomStsToolEditPartFactory;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.AgentNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.EventNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.IResourceNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.RoleNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.TResourceNodeFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.GoalSubfigure;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;


/**
 *
 */
public class StsToolEditPartFactory extends CustomStsToolEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context,Object model){

		EditPart ep = super.createEditPart(context, model);
		if (ep != null) return ep;
		return createEditPartGen(context, model);
	}

	/**
	 * @generated
	 */
	public EditPart createEditPartGen(EditPart context,Object model){
		if (model instanceof View) {
			View view = (View) model;
			switch (StsToolVisualIDRegistry.getVisualID(view)) {

				case StsToolDiagramEditPart.VISUAL_ID:
					return new StsToolDiagramEditPart(view);

				case AgentEditPart.VISUAL_ID:
					return new AgentEditPart(view);

				case AgentNameEditPart.VISUAL_ID:
					return new AgentNameEditPart(view);

				case RoleEditPart.VISUAL_ID:
					return new RoleEditPart(view);

				case RoleNameEditPart.VISUAL_ID:
					return new RoleNameEditPart(view);

				case GoalEditPart.VISUAL_ID:
					return new GoalEditPart(view);

				case GoalNameEditPart.VISUAL_ID:
					return new GoalNameEditPart(view);

				case TResourceEditPart.VISUAL_ID:
					return new TResourceEditPart(view);

				case TResourceNameEditPart.VISUAL_ID:
					return new TResourceNameEditPart(view);

				case IResourceEditPart.VISUAL_ID:
					return new IResourceEditPart(view);

				case IResourceNameEditPart.VISUAL_ID:
					return new IResourceNameEditPart(view);

				case EventEditPart.VISUAL_ID:
					return new EventEditPart(view);

				case EventNameEditPart.VISUAL_ID:
					return new EventNameEditPart(view);

				case Goal2EditPart.VISUAL_ID:
					return new Goal2EditPart(view);

				case GoalName2EditPart.VISUAL_ID:
					return new GoalName2EditPart(view);

				case TResource2EditPart.VISUAL_ID:
					return new TResource2EditPart(view);

				case TResourceName2EditPart.VISUAL_ID:
					return new TResourceName2EditPart(view);

				case AgentAgentCompartmentEditPart.VISUAL_ID:
					return new AgentAgentCompartmentEditPart(view);

				case RoleRoleCompartmentEditPart.VISUAL_ID:
					return new RoleRoleCompartmentEditPart(view);

				case NeedEditPart.VISUAL_ID:
					return new NeedEditPart(view);


				case ProduceEditPart.VISUAL_ID:
					return new ProduceEditPart(view);


				case ModifyEditPart.VISUAL_ID:
					return new ModifyEditPart(view);


				case PositiveGoalContributionEditPart.VISUAL_ID:
					return new PositiveGoalContributionEditPart(view);


				case NegativeGoalContributionEditPart.VISUAL_ID:
					return new NegativeGoalContributionEditPart(view);


				case GoalDecompositionOREditPart.VISUAL_ID:
					return new GoalDecompositionOREditPart(view);


				case GoalDecompositionANDEditPart.VISUAL_ID:
					return new GoalDecompositionANDEditPart(view);


				case OwnEditPart.VISUAL_ID:
					return new OwnEditPart(view);


				case PartOfEditPart.VISUAL_ID:
					return new PartOfEditPart(view);


				case TangibleByEditPart.VISUAL_ID:
					return new TangibleByEditPart(view);


				case PlayEditPart.VISUAL_ID:
					return new PlayEditPart(view);


				case ProvisionEditPart.VISUAL_ID:
					return new ProvisionEditPart(view);


				case DelegationEditPart.VISUAL_ID:
					return new DelegationEditPart(view);


				case AuthorisationEditPart.VISUAL_ID:
					return new AuthorisationEditPart(view);


				case ThreatEditPart.VISUAL_ID:
					return new ThreatEditPart(view);


				case IncompatibleDutiesEditPart.VISUAL_ID:
					return new IncompatibleDutiesEditPart(view);


				case CompatibleDutiesEditPart.VISUAL_ID:
					return new CompatibleDutiesEditPart(view);


				case DependencyEditPart.VISUAL_ID:
					return new DependencyEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context,Object model){
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated NOT
	 */
	public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source){

		if (source.getFigure() instanceof WrappingLabel) {
			return new TextCellEditorLocator((WrappingLabel) source.getFigure(), source);
		} else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel	wrapLabel;

		/**
		 * @generated
		 */
		@SuppressWarnings("unused")
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		private ITextAwareEditPart	source;

		/**
		 * @generated NOT
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel, ITextAwareEditPart source) {

			this.wrapLabel = wrapLabel;
			this.source = source;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel(){
			return wrapLabel;
		}

		private double getZoomLevel(){

			return ((DiagramRootEditPart) source.getRoot()).getZoomManager().getZoom();
		}

		/**
		 * @generated NOT
		 */
		public void relocate(CellEditor celleditor){


			Rectangle r = new Rectangle();

			IFigure parent = wrapLabel.getParent();
			if (parent instanceof AgentNodeFigure)
				r.setSize(new Dimension(AgentNodeFigure.THIS_DIMENSION));
			else if (parent instanceof RoleNodeFigure)
				r.setSize(new Dimension(RoleNodeFigure.THIS_DIMENSION));
			else if (parent instanceof GoalSubfigure)
				r.setSize(new Dimension(GoalSubfigure.THIS_DIMENSION));
			else if (parent instanceof TResourceNodeFigure)
				r.setSize(new Dimension(TResourceNodeFigure.THIS_DIMENSION));
			else if (parent instanceof IResourceNodeFigure)
				r.setSize(new Dimension(IResourceNodeFigure.THIS_DIMENSION));
			else if (parent instanceof EventNodeFigure) r.setSize(new Dimension(EventNodeFigure.THIS_DIMENSION));

			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);

			Dimension d = new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			if (d.width > r.width - 6) d = new Dimension(text.computeSize((int) ((r.width - 6) * getZoomLevel()), SWT.DEFAULT));
			rect.setSize(d);

			Rectangle parentBound = parent.getBounds().getCopy();
			int width = (int) (parent.getBounds().width * getZoomLevel());
			Point location = new Point(parentBound.getTopLeft());
			parent.translateToAbsolute(location);
			int x = location.x;

			rect.setLocation((x + width / 2) - (rect.width / 2), rect.y);
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label	label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel(){
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor){
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
			rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	public static DirectEditManager getDirectEditManager(IGraphicalEditPart ep,ITextAwareEditPart itap){

		return new STSCustomTextDirectEditManager(ep, TextCellEditor.class, getTextCellEditorLocator(itap));
	}
}
