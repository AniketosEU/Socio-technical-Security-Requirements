/*
* StsToolCreationWizard.java
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
package eu.aniketos.wp1.ststool.diagram.part;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;

/**
 * @generated
 */
public class StsToolCreationWizard extends Wizard implements INewWizard {

	/**
	 * @generated
	 */
	private IWorkbench						workbench;

	/**
	 * @generated
	 */
	protected IStructuredSelection		selection;

	/**
	 * @generated
	 */
	protected StsToolCreationWizardPage	diagramModelFilePage;

	/**
	 * @generated
	 */
	protected Resource						diagram;

	/**
	 * @generated
	 */
	private boolean							openNewlyCreatedDiagramEditor	= true;

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench(){
		return workbench;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection(){
		return selection;
	}

	/**
	 * @generated
	 */
	public final Resource getDiagram(){
		return diagram;
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor(){
		return openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor){
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void init(IWorkbench workbench,IStructuredSelection selection){
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(Messages.StsToolCreationWizardTitle);
		setDefaultPageImageDescriptor(StsToolDiagramEditorPlugin.getBundledImageDescriptor("icons/wizban/NewStstoolWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/**
	 * @generated
	 */
	@Override
	public void addPages(){
		diagramModelFilePage = new StsToolCreationWizardPage("DiagramModelFile", getSelection(), "ststool_diagram"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage.setTitle(Messages.StsToolCreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage.setDescription(Messages.StsToolCreationWizard_DiagramModelFilePageDescription);
		addPage(diagramModelFilePage);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean performFinish(){
		IRunnableWithProgress op = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException{
				diagram = StsToolDiagramEditorUtil.createDiagram(diagramModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						StsToolDiagramEditorUtil.openDiagram(diagram);
					} catch (PartInitException e) {
						ErrorDialog.openError(getContainer().getShell(), Messages.StsToolCreationWizardOpenEditorError, null, e.getStatus());
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(), Messages.StsToolCreationWizardCreationError, null, ((CoreException) e.getTargetException()).getStatus());
			} else {
				StsToolDiagramEditorPlugin.getInstance().logError("Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		return diagram != null;
	}
}
