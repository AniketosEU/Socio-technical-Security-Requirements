/*
* DelegationEditPart.java
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
import org.eclipse.draw2d.Connection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.RedundancyType;
import eu.aniketos.wp1.ststool.RepudiationType;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.EditPartStucturedSelection;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolConnectionNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.SecurityNeedGraphicalDescriptor;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.DelegationFigure;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.DelegationItemSemanticEditPolicy;

/**
 * 
 */
public class DelegationEditPart extends StsToolConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 4013;

	/**
	 * @generated
	 */
	public DelegationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void createDefaultEditPolicies(){

		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DelegationItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, new ConnectionBendpointEditPolicy());
	}

	AdapterImpl	adapter	= new AdapterImpl() {

									@Override
									public void notifyChanged(Notification notification){

										Object obj = notification.getNotifier();

										if ((obj instanceof Delegation) && ((notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__TIMES_TRANSFERABLE) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__REPUDIATION_TYPE) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__REDUNDANCY_TYPE) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__NAME) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__SOURCE_GOAL) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__AVAILABILITY)) || (notification.getFeatureID(Delegation.class) == StstoolPackage.DELEGATION__TRUSTWORTHINESS)) {
											refresh();
										}
									}
								};

	@Override
	public void refresh(){

		super.refresh();
		Delegation d = (Delegation) getNotationView().getElement();

		getPrimaryShape().updateProperty(DelegationFigure.PROP_TIMES_TRANSFERABLE, new Integer(d.getTimesTransferable()));

		List<SecurityNeedGraphicalDescriptor> secNeedDesc = new ArrayList<SecurityNeedGraphicalDescriptor>();
		if (d.getRepudiationType() != RepudiationType.NO_REPUDIATION) secNeedDesc.add(SecurityNeedGraphicalDescriptor.NOREP_DESC);
		if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) secNeedDesc.add(SecurityNeedGraphicalDescriptor.RED_DESC);
		if (d.getTimesTransferable() == 0) secNeedDesc.add(SecurityNeedGraphicalDescriptor.NO_DEL_DESC);
		if (d.isAvailability()) secNeedDesc.add(SecurityNeedGraphicalDescriptor.AVAIAL_DESC);
		if (d.isTrustworthiness()) secNeedDesc.add(SecurityNeedGraphicalDescriptor.TRUST_DESC);
		getPrimaryShape().updateProperty(DelegationFigure.PROP_SEC_NEEDS, secNeedDesc);

		getPrimaryShape().updateProperty(DelegationFigure.PROP_SHOW_SECNEEDS, d.isShowSecurityNeeds());

		if (d.getSourceGoal() != null) {
			String name = d.getSourceGoal().getName();
			if (name != null && name.equals("")) {
				name = "<Goal...>";
			}
			getPrimaryShape().updateProperty(DelegationFigure.PROP_GOAL_NAME, name);
		} else {
			getPrimaryShape().updateProperty(DelegationFigure.PROP_GOAL_NAME, "No Goal Reference");
		}

		try {
			CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			getPrimaryShape().setError(editor.getMarkerValue(this));
		} catch (Exception e) {
		}

	}

	private Delegation	delegation;

	@Override
	public void activate(){

		super.activate();
		delegation = (Delegation) getNotationView().getElement();
		delegation.eAdapters().add(adapter);
	}

	@Override
	public void deactivate(){

		delegation.eAdapters().remove(adapter);
		delegation = null;
		super.deactivate();
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated NOT
	 */
	@Override
	protected Connection createConnectionFigure(){

		return new DelegationFigure(new IStsFigureChangeListener() {

			@Override
			public void figureChanged(int elementChanged,Object value){

				switch (elementChanged) {
					case DelegationFigure.PROP_SHOW_SECNEEDS:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), delegation, StstoolPackage.Literals.DELEGATION__SHOW_SECURITY_NEEDS, !delegation.isShowSecurityNeeds()));
					break;
					case DelegationFigure.SELECTION:
						try {
							getRoot().getViewer().setSelection(new EditPartStucturedSelection(DelegationEditPart.this));
						} catch (Exception e) {
						}
					break;
				}
			}
		});
	}

	/**
	 * @generated
	 */
	public DelegationFigure getPrimaryShape(){
		return (DelegationFigure) getFigure();
	}


}
