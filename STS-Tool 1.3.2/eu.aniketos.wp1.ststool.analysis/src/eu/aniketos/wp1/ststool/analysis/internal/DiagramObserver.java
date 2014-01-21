/*
* DiagramObserver.java
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
package eu.aniketos.wp1.ststool.analysis.internal;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DefaultEditDomain;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.ISTSDiagramObserverWithEditSupport;

@SuppressWarnings("static-access")
public class DiagramObserver implements ISTSDiagramObserverWithEditSupport {

	private static StsToolDiagram								diagram		= null;
	private static CustomStsToolDiagramDocumentEditor	editor		= null;

	private static Set<DiagramObserverListener>			listeners	= new HashSet<DiagramObserverListener>();


	@Override
	public void diagramChanged(StsToolDiagram diagram){
		this.diagram = diagram;
	}

	@Override
	public void diagramModifyed(Notification n){
		for (DiagramObserverListener l : listeners) {
			try {
				l.diagramModified();
			} catch (Exception e) {
			}
		}
	}


	public static StsToolDiagram getDiagram(){
		return diagram;
	}

	@Override
	public void setEditingDomain(DefaultEditDomain ed){
	}

	@Override
	public void setEditor(CustomStsToolDiagramDocumentEditor editor){
		if (this.editor != null) this.editor.clearAllMarker();
		ResultsManager.getInstance().cleanCategoriesAndResults();
		this.editor = editor;
	}

	public static CustomStsToolDiagramDocumentEditor getEditor(){
		return editor;
	}

	public static void addDiagramObserverListener(DiagramObserverListener listener){
		listeners.add(listener);
	}

	public static void removeDiagramObserverListener(DiagramObserverListener listener){
		listeners.remove(listener);
	}
}
