/*
* StsToolReorientConnectionViewCommand.java
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
package eu.aniketos.wp1.ststool.diagram.edit.commands;

import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class StsToolReorientConnectionViewCommand extends AbstractTransactionalCommand {

	/**
	 * @generated
	 */
	private IAdaptable	edgeAdaptor;

	/**
	 * @generated
	 */
	public StsToolReorientConnectionViewCommand(TransactionalEditingDomain editingDomain, String label) {
		super(editingDomain, label, null);
	}

	/**
	 * @generated
	 */
	@Override
	public List getAffectedFiles(){
		View view = (View) edgeAdaptor.getAdapter(View.class);
		if (view != null) { return getWorkspaceFiles(view); }
		return super.getAffectedFiles();
	}

	/**
	 * @generated
	 */
	public IAdaptable getEdgeAdaptor(){
		return edgeAdaptor;
	}

	/**
	 * @generated
	 */
	public void setEdgeAdaptor(IAdaptable edgeAdaptor){
		this.edgeAdaptor = edgeAdaptor;
	}

	/**
	 * @generated
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor,IAdaptable info){
		assert null != edgeAdaptor : "Null child in StsToolReorientConnectionViewCommand"; //$NON-NLS-1$
		Edge edge = (Edge) getEdgeAdaptor().getAdapter(Edge.class);
		assert null != edge : "Null edge in StsToolReorientConnectionViewCommand"; //$NON-NLS-1$
		View tempView = edge.getSource();
		edge.setSource(edge.getTarget());
		edge.setTarget(tempView);
		return CommandResult.newOKCommandResult();
	}
}
