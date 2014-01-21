/*
* OpenSTSDiagramHandler.java
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
package eu.aniketos.wp1.ststool.diagram.custom.handlers;

import java.io.File;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.IDE;
import eu.aniketos.wp1.ststool.diagram.part.Messages;


public class OpenSTSDiagramHandler extends AbstractHandler {

	private static String	lastPath	= null;

	public OpenSTSDiagramHandler() {
		if (lastPath == null) {
			try {
				lastPath = org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{

		FileDialog fileDialog = new FileDialog(HandlerUtil.getActiveShellChecked(event), SWT.OPEN);

		try {
			//String path = org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
			fileDialog.setFilterPath(lastPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String[] estensionFilter = {"*.ststool_diagram","*.bpmn_diagram"};
		String[] estensionFilter = { "*.ststool_diagram" };
		fileDialog.setFilterExtensions(estensionFilter);

		String filename = fileDialog.open();
		if (filename != null && filename.length() > 0) {
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			if (filename.endsWith(".ststool_diagram")) {
				openEditor(window.getWorkbench(), URI.createFileURI(filename));
				lastPath = new File(filename).getParent();
			} else if (filename.endsWith(".bpmn_diagram")) {
				try {
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(".bpmnProject");
					if (!project.exists()) {
						project.create(new NullProgressMonitor());
						project.open(new NullProgressMonitor());
					}

					File diagmramFile = new File(filename);
					String[] s = diagmramFile.getName().split("\\.");
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < s.length - 1; i++) {
						sb.append(s[i]);
					}
					File modelFile = new File(diagmramFile.getParent() + File.separator + sb.toString() + ".bpmn");


					IFile iFileModel = project.getFile(modelFile.getName());
					if (iFileModel.exists()) iFileModel.delete(true, new NullProgressMonitor());
					iFileModel.createLink(modelFile.toURI(), org.eclipse.core.resources.IResource.ALLOW_MISSING_LOCAL, new NullProgressMonitor());

					IFile iFileDiagram = project.getFile(diagmramFile.getName());
					if (iFileDiagram.exists()) iFileDiagram.delete(true, new NullProgressMonitor());
					iFileDiagram.createLink(diagmramFile.toURI(), org.eclipse.core.resources.IResource.ALLOW_MISSING_LOCAL, new NullProgressMonitor());


					IDE.openEditor(window.getWorkbench().getActiveWorkbenchWindow().getActivePage(), iFileDiagram);

				} catch (CoreException e) {
					//e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * @generated
	 */
	private static boolean openEditor(IWorkbench workbench,URI fileURI){

		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorTitle, NLS.bind(Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorMessage, fileURI.toFileString()));
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultEditorOpenErrorTitle, exception.getMessage());
				return false;
			}
		}
		return true;
	}

}
