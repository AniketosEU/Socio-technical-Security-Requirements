/*
* GoalDecompositionANDEditPart.java
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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolConnectionNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.DecompositionANDFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.router.CompositionConnectionRouter;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.GoalDecompositionANDItemSemanticEditPolicy;

/**
 * 
 */
@SuppressWarnings("restriction")
public class GoalDecompositionANDEditPart extends StsToolConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 4007;

	/**
	 * @generated
	 */
	public GoalDecompositionANDEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new GoalDecompositionANDItemSemanticEditPolicy());
		removeEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE);

	}


	@Override
	public void installEditPolicy(Object key,EditPolicy editPolicy){

		if (editPolicy instanceof ConnectionBendpointEditPolicy) return;

		super.installEditPolicy(key, editPolicy);

	}


	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	@Override
	protected Connection createConnectionFigure(){
		return new DecompositionANDFigure();
	}

	/**
	 * @generated
	 */
	public DecompositionANDFigure getPrimaryShape(){
		return (DecompositionANDFigure) getFigure();
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void installRouter(){

		ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		RoutingStyle style = (RoutingStyle) ((View) getModel()).getStyle(NotationPackage.Literals.ROUTING_STYLE);

		if (style != null && cLayer instanceof ConnectionLayerEx) {

			ConnectionLayerEx cLayerEx = (ConnectionLayerEx) cLayer;
			Routing routing = style.getRouting();
			if (getConnectionFigure() instanceof DecompositionANDFigure)
				getConnectionFigure().setConnectionRouter(new CompositionConnectionRouter());
			else if (Routing.MANUAL_LITERAL == routing) {
				getConnectionFigure().setConnectionRouter(cLayerEx.getObliqueRouter());
			} else if (Routing.RECTILINEAR_LITERAL == routing) {
				getConnectionFigure().setConnectionRouter(cLayerEx.getRectilinearRouter());
			} else if (Routing.TREE_LITERAL == routing) {
				getConnectionFigure().setConnectionRouter(cLayerEx.getTreeRouter());
			}

		}

		refreshRouterChange();
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
}
