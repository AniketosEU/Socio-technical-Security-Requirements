/*
* StsToolDiagramEditor.java
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

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;

/**
 *
 */
public class StsToolDiagramEditor extends CustomStsToolDiagramDocumentEditor {



	/**
	 * @generated
	 */
	public static final String	ID				= "eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorID";	//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String	CONTEXT_ID	= "eu.aniketos.wp1.ststool.diagram.ui.diagramContext";				//$NON-NLS-1$

	/**
	 * @generated
	 */
	public StsToolDiagramEditor() {
		super(true);
	}

	private EditorListener	editorListener	= new EditorListener(this);

	@Override
	public void createPartControl(Composite parent){

		super.createPartControl(parent);
		getSite().getWorkbenchWindow().getPartService().addPartListener(editorListener);
	}

	@Override
	public void dispose(){

		getSite().getWorkbenchWindow().getPartService().removePartListener(editorListener);
		super.dispose();
	}

	/**
	 * @generated
	 */
	@Override
	protected String getContextID(){
		return CONTEXT_ID;
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot){

		return super.createPaletteRoot(existingPaletteRoot);
	}

	/**
	 * @generated
	 */
	protected PaletteRoot createPaletteRootGen(PaletteRoot existingPaletteRoot){
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new StsToolPaletteFactory().fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	@Override
	protected PreferencesHint getPreferencesHint(){
		return StsToolDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	@Override
	public String getContributorId(){
		return StsToolDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	@Override
	protected IDocumentProvider getDocumentProvider(IEditorInput input){
		if (input instanceof URIEditorInput) { return StsToolDiagramEditorPlugin.getInstance().getDocumentProvider(); }
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain(){
		IDocument document = getEditorInput() != null ? getDocumentProvider().getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) { return ((IDiagramDocument) document).getEditingDomain(); }
		return super.getEditingDomain();
	}


	/**
	 * @generated
	 */
	@Override
	protected void setDocumentProvider(IEditorInput input){
		if (input instanceof URIEditorInput) {
			setDocumentProvider(StsToolDiagramEditorPlugin.getInstance().getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	protected void configureGraphicalViewer(){

		configureGraphicalViewerGen();
		((DiagramRootEditPart) getDiagramGraphicalViewer().getRootEditPart()).getZoomManager().setZoomAnimationStyle(ZoomManager.ANIMATE_NEVER);
		rebindKey();
	}

	/**
	 * @generated
	 */
	protected void configureGraphicalViewerGen(){
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU, provider, getDiagramGraphicalViewer());
	}

	@Override
	protected TransactionalEditingDomain createEditingDomain(){

		return super.createEditingDomain();
	}



}
