/*
* RoleRoleCompartmentEditPart.java
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolShapeCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.RoleCompartment;
import eu.aniketos.wp1.ststool.diagram.edit.policies.RoleRoleCompartmentCanonicalEditPolicy;
import eu.aniketos.wp1.ststool.diagram.edit.policies.RoleRoleCompartmentItemSemanticEditPolicy;
import eu.aniketos.wp1.ststool.diagram.part.Messages;

/**
 * 
 */
public class RoleRoleCompartmentEditPart extends StsToolShapeCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 7002;

	/**
	 * @generated
	 */
	public RoleRoleCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	public String getCompartmentName(){
		return Messages.RoleRoleCompartmentEditPart_title;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public IFigure createFigure(){

		RoleCompartment result = new RoleCompartment(getCompartmentName(), getMapMode());
		result.getContentPane().setLayoutManager(getLayoutManager());
		result.setBorder(null);
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	public IFigure createFigureGen(){
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RoleRoleCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new RoleRoleCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	@Override
	protected void setRatio(Double ratio){
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}
}
