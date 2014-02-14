/*
* STSOffscreenEditPartFactory.java
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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IDiagramPreferenceSupport;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

/**
 * @author sshaw
 * 
 *         Utility class to generate editpart containment offscreen without creating an editor.
 */
public class STSOffscreenEditPartFactory {

	private static STSOffscreenEditPartFactory	INSTANCE	= new STSOffscreenEditPartFactory();

	/**
	 * gives access to the singleton instance of <code>OffscreenEditPartFactory</code>
	 * 
	 * @return the singleton instance
	 */
	public static STSOffscreenEditPartFactory getInstance(){

		return INSTANCE;
	}

	/**
	 * Creates a <code>DiagramEditPart</code> given the <code>Diagram</code> without opening an editor.
	 * 
	 * @param diagram
	 *           the <code>Diagram</code>
	 * @return the new populated <code>DiagramEditPart</code>
	 * @deprecated Please use {@link #createDiagramEditPart(Diagram, Shell)} instead as this method does not dispose the new Shell that it creates.
	 */
	public DiagramEditPart createDiagramEditPart(Diagram diagram){

		return createDiagramEditPart(diagram, new Shell(), null, ViewsManager.SOCIAL_VIEW);
	}

	/**
	 * Creates a <code>DiagramEditPart</code> given the <code>Diagram</code> without opening an editor.
	 * 
	 * @param diagram
	 *           the <code>Diagram</code>
	 * @param shell
	 *           the shell
	 * @return the new populated <code>DiagramEditPart</code>
	 */
	public DiagramEditPart createDiagramEditPart(Diagram diagram,Shell shell){

		return createDiagramEditPart(diagram, shell, null, ViewsManager.SOCIAL_VIEW);
	}

	/**
	 * Creates a <code>DiagramEditPart</code> given the <code>Diagram</code> without opening an editor.
	 * 
	 * @param diagram
	 *           the <code>Diagram</code>
	 * @param shell
	 *           the shell
	 * @param preferencesHint
	 *           the preferences hint to be used when creating the diagram; if null, the preferences hint from the root editpart will be used.
	 * @return the new populated <code>DiagramEditPart</code>
	 */
	public DiagramEditPart createDiagramEditPart(Diagram diagram,Shell shell,PreferencesHint preferencesHint,int view){

		DiagramGraphicalViewer customViewer = new DiagramGraphicalViewer();
		customViewer.createControl(shell);

		DiagramEditDomain editDomain = new DiagramEditDomain(null);
		editDomain.setCommandStack(new DiagramCommandStack(editDomain));

		customViewer.setEditDomain(editDomain);

		STSEditPartService editPartService = new STSEditPartService(view);

		// hook in preferences
		RootEditPart rootEP = editPartService.createRootEditPart(diagram);
		if (rootEP instanceof IDiagramPreferenceSupport) {
			if (preferencesHint == null) {
				preferencesHint = ((IDiagramPreferenceSupport) rootEP).getPreferencesHint();
			} else {
				((IDiagramPreferenceSupport) rootEP).setPreferencesHint(preferencesHint);
			}
			customViewer.hookWorkspacePreferenceStore((IPreferenceStore) preferencesHint.getPreferenceStore());
		}

		customViewer.setRootEditPart(rootEP);

		customViewer.setEditPartFactory(editPartService);

		DiagramEventBroker.startListening(TransactionUtil.getEditingDomain(diagram));

		customViewer.setContents(diagram);
		customViewer.flush();

		Assert.isTrue(customViewer.getContents() instanceof DiagramEditPart);

		/*
		 * We need to flush all the deferred updates. 
		 */
		while (shell.getDisplay().readAndDispatch()) {
			// nothing
		}

		return (DiagramEditPart) customViewer.getContents();

	}

}
