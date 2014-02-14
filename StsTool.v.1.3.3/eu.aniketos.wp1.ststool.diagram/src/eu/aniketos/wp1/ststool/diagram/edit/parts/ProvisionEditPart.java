/*
* ProvisionEditPart.java
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.EditPartStucturedSelection;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolConnectionNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.SecurityNeedGraphicalDescriptor;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.ProvisionFigure;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.edit.policies.ProvisionItemSemanticEditPolicy;

/**
 * 
 */
public class ProvisionEditPart extends StsToolConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 4012;

	/**
	 * @generated
	 */
	public ProvisionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new ProvisionItemSemanticEditPolicy());
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

		return new ProvisionFigure(new IStsFigureChangeListener() {

			@Override
			public void figureChanged(int elementChanged,Object value){

				switch (elementChanged) {
					case ProvisionFigure.PROP_SHOW_SECNEEDS:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), provision, StstoolPackage.Literals.PROVISION__SHOW_SECURITY_NEEDS, !provision.isShowSecurityNeeds()));
					break;
					case ProvisionFigure.SELECTION:
						try {
							getRoot().getViewer().setSelection(new EditPartStucturedSelection(ProvisionEditPart.this));
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
	public ProvisionFigure getPrimaryShape(){
		return (ProvisionFigure) getFigure();
	}

	AdapterImpl	adapter	= new AdapterImpl() {

									@Override
									public void notifyChanged(Notification notification){

										Object obj = notification.getNotifier();

										if ((obj instanceof Provision) && ((notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__NAME) || (notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__SOURCE_RESOURCE || (notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS) || (notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__INTEGRITY) || (notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__AVAILABILITY))) || (notification.getFeatureID(Provision.class) == StstoolPackage.PROVISION__CONFIDENTIALITY) ) {
											refresh();
										}

									}
								};

	@Override
	public void refresh(){

		super.refresh();
		Provision p = (Provision) getNotationView().getElement();

		List<SecurityNeedGraphicalDescriptor> secNeedDesc = new ArrayList<SecurityNeedGraphicalDescriptor>();
		if (p.isIntegrity()) secNeedDesc.add(SecurityNeedGraphicalDescriptor.INTEGRITY_DESC);
		if (p.isAvailability()) secNeedDesc.add(SecurityNeedGraphicalDescriptor.AVAIAL_DESC);
		if (p.isConfidentiality()) secNeedDesc.add(SecurityNeedGraphicalDescriptor.CONF_DESC);
		getPrimaryShape().updateProperty(ProvisionFigure.PROP_SEC_NEEDS, secNeedDesc);
		getPrimaryShape().updateProperty(ProvisionFigure.PROP_SHOW_SECNEEDS, p.isShowSecurityNeeds());


		if (p.getSourceResource() != null) {
			String name = p.getSourceResource().getName();
			if (name.equals("")) {
				name = "<TResource...>";
			}
			getPrimaryShape().updateProperty(ProvisionFigure.PROP_RESOURCE_NAME, name);
		} else {
			getPrimaryShape().updateProperty(ProvisionFigure.PROP_RESOURCE_NAME, "No TResource Reference");
		}

		try {
			CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			getPrimaryShape().setError(editor.getMarkerValue(this));
		} catch (Exception e) {
		}
	}

	private Provision	provision;

	@Override
	public void activate(){

		super.activate();
		provision = (Provision) getNotationView().getElement();
		provision.eAdapters().add(adapter);
	}

	@Override
	public void deactivate(){

		provision.eAdapters().remove(adapter);
		provision = null;
		super.deactivate();
	}

}
