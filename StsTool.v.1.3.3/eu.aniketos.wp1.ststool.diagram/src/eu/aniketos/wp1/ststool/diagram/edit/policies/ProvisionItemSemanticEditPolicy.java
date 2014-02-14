/*
* ProvisionItemSemanticEditPolicy.java
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
package eu.aniketos.wp1.ststool.diagram.edit.policies;

import java.util.Iterator;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommand;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class ProvisionItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ProvisionItemSemanticEditPolicy() {
		super(StsToolElementTypes.Provision_4012);
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		if (!(req.getElementToDestroy() instanceof Provision)) return UnexecutableCommand.INSTANCE;

		Provision provision = (Provision) req.getElementToDestroy();

		if (provision != null && !provision.isDeletable()) {
			String name = provision.getSourceResource().getName();
			String act1 = provision.getSource().getName();
			String act2 = provision.getTarget().getName();
			return new WarningDialogCommand("The provision cannot be deleted", "The provision cannot be deleted. \n" + "The provision of document " + name + " from " + act1 + " to " + act2 + " cannot be deleted, as " + name + " is part of a provision chain. Delete the provisions of " + name + " made by " + act2 + " first.");
		}

		return getGEFWrapper(new DestroyElementCommand(req) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info)
					throws ExecutionException{

				try {
					Provision provision = (Provision) getElementToDestroy();
					provision.setSourceResource(null);

					TResource tr = provision.getTargetResource();

					for (Iterator<Need> itr = tr.getGoalsNeeding().iterator(); itr.hasNext();) {
						Need i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}
					for (Iterator<Produce> itr = tr.getGoalsProducing().iterator(); itr.hasNext();) {
						Produce i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}
					for (Iterator<Modify> itr = tr.getGoalsModifing().iterator(); itr.hasNext();) {
						Modify i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}

					for (Iterator<TangibleBy> itr = tr.getIntangibleElements().iterator(); itr.hasNext();) {
						TangibleBy i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}

					for (Iterator<PartOf> itr = tr.getSubParts().iterator(); itr.hasNext();) {
						PartOf i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}

					for (Iterator<Threat> itr = tr.getThreatedElements().iterator(); itr.hasNext();) {
						Threat i = itr.next();
						itr.remove();
						EcoreUtil.delete(i, true);
					}

					if (provision.getTargetResource().getProvidedFrom().size() == 1) {
						EcoreUtil.delete(tr, true);
					}

					provision.setTargetResource(null);
					return super.doExecuteWithResult(monitor, info);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return CommandResult.newOKCommandResult();//CommandResult.newErrorCommandResult("Error deleting Delegation");
			}
		});
	}


	/**
	 * @generated
	 */
	protected Command getDestroyElementCommandGen(DestroyElementRequest req){
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
