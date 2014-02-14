/*
* STSPluginManager.java
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
package eu.aniketos.wp1.ststool.diagram.application;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gmf.runtime.common.ui.util.PartListenerAdapter;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.ISTSDiagramObserver;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.ISTSDiagramObserverWithEditSupport;
import eu.aniketos.wp1.ststool.impl.StringToStringMapImpl;
import eu.aniketos.wp1.ststool.modelProvider.ISTSModelProvider;

/**
 * Manage and evaluate the commitment for the active editor if it is supported
 * 
 * @author Mauro Poggianella
 */
class STSPluginManager {


	/** the diagram on which the commitment are calculated */
	private StsToolDiagram	diagram;

	/**
	 * Constructor Add to the workbench the ActiveEditorPartListener;
	 */
	private STSPluginManager() {

		start();
	}

	private ActiveEditorPartListener	editorListener	= new ActiveEditorPartListener();

	public final void start(){

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().addPartListener(editorListener);
	}

	public final void stop(){

		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getPartService().removePartListener(editorListener);
	}

	private static STSPluginManager	INSTANCE	= null;

	static STSPluginManager getInstance(){

		if (INSTANCE == null) {
			INSTANCE = new STSPluginManager();
		}
		return INSTANCE;
	}


	/**
	 * Called every time the active editor change
	 * 
	 * @param editor
	 */
	private final void activeEditorChanged(CustomStsToolDiagramDocumentEditor editor){

		if (diagram != null) {
			diagram.eAdapters().remove(adapter);
		}
		if (editor != null) {
			diagram = editor.getStsModel();
			diagram.eAdapters().add(adapter);
			/*editor.getDiagramEditPart().performRequest(new ExecuteCommandRequest() {
				@Override
				public Command getCommand() {
					return new Command() {
						@Override
						public void execute() {
							ArrayList<Goal> goals=new ArrayList<Goal>(diagram.getAllGoals());
							if(goals.size()>0){
								Goal g=goals.get(0);
								System.out.println("foung goal "+g.getName()+" -> capability "+g.isCapability());
								g.setName("asdasdsad"+Math.random());
								g.setCapability(!(g.isCapability()));
							}
						}
					};
				}
			});*/
			diagramChanged(diagram, editor.getDefEditDomain(), editor);
		} else
			diagramChanged(null, null, null);
	}


	private List<ISTSDiagramObserver>	registredPlugin	= new ArrayList<ISTSDiagramObserver>();

	boolean registerPlugin(ISTSDiagramObserver stsPlugin){

		return registredPlugin.add(stsPlugin);
	}

	boolean unregisterPlugin(ISTSDiagramObserver stsPlugin){

		return registredPlugin.remove(stsPlugin);
	}


	protected void diagramChanged(StsToolDiagram diagrammTrans,DefaultEditDomain ed,CustomStsToolDiagramDocumentEditor editor){

		for (ISTSDiagramObserver s : registredPlugin) {
			s.diagramChanged((StsToolDiagram) EcoreUtil.copy(diagram));
			//s.diagramChanged(diagram);
			if (s instanceof ISTSDiagramObserverWithEditSupport) {
				((ISTSDiagramObserverWithEditSupport) s).setEditor(editor);
				((ISTSDiagramObserverWithEditSupport) s).setEditingDomain(ed);
			}
		}
	}

	/**
	 * Called by the diagram adapter every time a change occur in the model. A pre-filter is applied in the adapter. Only notification of type ADD REMVOE SET will be passed.
	 * 
	 * @param n
	 *           the notification describing the notification
	 */
	protected void diagramModifyed(Notification n){

		for (ISTSDiagramObserver s : registredPlugin) {
			s.diagramModifyed(n);
		}
	}



	/**
	 * Listen for model modification and call the method diagramChanged(Notification)
	 */
	private EContentAdapter	adapter	= new EContentAdapter() {

													@Override
													public void notifyChanged(Notification msg){

														super.notifyChanged(msg);
														if (msg.getEventType() == Notification.REMOVING_ADAPTER) return;
														if (msg.getEventType() == Notification.ADD || msg.getEventType() == Notification.REMOVE) {
															if (msg.getFeature() instanceof EReference) {
																EReference f = (EReference) msg.getFeature();
																if (f.getName().equals("graphicalConstraintMap")) return;
															}
														} else if (msg.getNotifier() instanceof StringToStringMapImpl && msg.getEventType() == Notification.SET) { return; }
														diagramModifyed(msg);
													}
												};


	/**
	 * Listen for the active editor switching. It first check if there is an active editor, and after if it implements the interface {@link ISTSModelProvider} After call the method activeEditorChanged() if the current editor has changed;
	 * 
	 * @author Mauro Poggianella
	 */
	private class ActiveEditorPartListener extends PartListenerAdapter {

		CustomStsToolDiagramDocumentEditor	lastActiveEditor;

		@Override
		public void partActivated(IWorkbenchPart part){

			IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (activeEditor == null || !(activeEditor instanceof CustomStsToolDiagramDocumentEditor)) {
				lastActiveEditor = null;
				activeEditorChanged(null);
			} else if (lastActiveEditor != activeEditor) {
				lastActiveEditor = (CustomStsToolDiagramDocumentEditor) activeEditor;
				activeEditorChanged(lastActiveEditor);
			}
		}
	}

}
