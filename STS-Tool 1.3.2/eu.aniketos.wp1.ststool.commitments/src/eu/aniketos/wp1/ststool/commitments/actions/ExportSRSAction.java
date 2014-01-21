/*
* ExportSRSAction.java
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
package eu.aniketos.wp1.ststool.commitments.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.commitments.Activator;
import eu.aniketos.wp1.ststool.commitments.manager.CommitmentManager;
import eu.aniketos.wp1.ststool.commitments.manager.SRSWriter;
import eu.aniketos.wp1.ststool.commitments.view.part.CommitmentsView;


public class ExportSRSAction extends Action {

	private final Shell	shell;

	public ExportSRSAction(CommitmentsView view) {

		super("Exoprt srs");
		shell = view.getSite().getShell();
		setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "/icons/exportSRS.gif"));
		setToolTipText("Export SRS Specification");
	}

	@Override
	public void run(){

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setOverwrite(true);
		dialog.setFilterNames(new String[] { "Extensible Markup Language" });
		dialog.setFilterExtensions(new String[] { "*.xml" });
		File f = new File(ResourcesPlugin.getWorkspace().getRoot().getLocationURI().getPath());
		dialog.setFilterPath(f.getAbsolutePath()); // Windows path
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		String diagramName = "";
		try {
			diagramName = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorInput().getName();
			diagramName = diagramName.substring(0, diagramName.length() - 16);


		} catch (Exception e) {
		}


		String filename = "SRS_" + diagramName + "_" + date + ".xml";

		dialog.setFileName(filename);
		String path = dialog.open();
		if (path != null) {
			if (!path.endsWith(".xml")) path = path + ".xml";
			FileOutputStream fos = null;
			try {
				CommitmentManager cm = CommitmentManager.getManager();
				SRSWriter srsWriter = new SRSWriter(cm.getAllCommitments(), cm.getDiagram());
				fos = new FileOutputStream(new File(path));
				srsWriter.writeToOutputStream(fos);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fos != null) try {
					fos.close();
				} catch (IOException e) {
				}
			}
		}
	}

	@Override
	public boolean isEnabled(){

		return true;
	}

	@Override
	public boolean isHandled(){

		return true;
	}


}
