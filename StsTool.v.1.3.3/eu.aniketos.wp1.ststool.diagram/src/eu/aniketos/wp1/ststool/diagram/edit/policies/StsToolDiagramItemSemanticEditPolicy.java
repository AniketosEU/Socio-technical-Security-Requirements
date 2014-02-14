/*
* StsToolDiagramItemSemanticEditPolicy.java
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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import eu.aniketos.wp1.ststool.diagram.edit.commands.AgentCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.EventCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.GoalCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.IResourceCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.RoleCreateCommand;
import eu.aniketos.wp1.ststool.diagram.edit.commands.TResourceCreateCommand;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class StsToolDiagramItemSemanticEditPolicy extends StsToolBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public StsToolDiagramItemSemanticEditPolicy() {
		super(StsToolElementTypes.StsToolDiagram_1000);
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req){
		if (StsToolElementTypes.Agent_2001 == req.getElementType()) { return getGEFWrapper(new AgentCreateCommand(req)); }
		if (StsToolElementTypes.Role_2002 == req.getElementType()) { return getGEFWrapper(new RoleCreateCommand(req)); }
		if (StsToolElementTypes.Goal_2003 == req.getElementType()) { return getGEFWrapper(new GoalCreateCommand(req)); }
		if (StsToolElementTypes.TResource_2004 == req.getElementType()) { return getGEFWrapper(new TResourceCreateCommand(req)); }
		if (StsToolElementTypes.IResource_2005 == req.getElementType()) { return getGEFWrapper(new IResourceCreateCommand(req)); }
		if (StsToolElementTypes.Event_2006 == req.getElementType()) { return getGEFWrapper(new EventCreateCommand(req)); }
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	@Override
	protected Command getDuplicateCommand(DuplicateElementsRequest req){
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(TransactionalEditingDomain editingDomain, DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req.getElementsToBeDuplicated(), req.getAllDuplicatedElementsMap());
		}

	}

	@Override
	protected Command getDestroyElementCommand(DestroyElementRequest req){

		return null;
	}

}
