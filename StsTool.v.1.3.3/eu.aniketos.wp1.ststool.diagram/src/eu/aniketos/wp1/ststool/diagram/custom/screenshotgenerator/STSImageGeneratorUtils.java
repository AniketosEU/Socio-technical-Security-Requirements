/*
* STSImageGeneratorUtils.java
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
package eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

public class STSImageGeneratorUtils {

	public static void generateDiagramScreenshotJPEG(Shell shell,String destinationPath,Diagram diagram,int view){
		generateDiagramScreenshot(shell, destinationPath, diagram, view, ImageFileFormat.JPEG);
	}

	public static void generateDiagramScreenshot(Shell shell,String destinationPath,Diagram diagram,int view,ImageFileFormat fileFormat){

		DiagramEditPart dep = STSOffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell, null, view);
		IPath destination = new Path(destinationPath);
		CopyToImageUtil img = new CopyToImageUtil();
		try {
			IProgressMonitor monitor = new NullProgressMonitor();
			img.copyToImage(dep, destination, fileFormat, monitor);
		} catch (Exception e) {
			StsToolDiagramEditorPlugin.getInstance().logError("Could not make schreenshot '" + destination + "': " + e.getMessage(), e);
		}

	}

	public static void openExportWindows(IWorkbenchWindow window){

	}
}
