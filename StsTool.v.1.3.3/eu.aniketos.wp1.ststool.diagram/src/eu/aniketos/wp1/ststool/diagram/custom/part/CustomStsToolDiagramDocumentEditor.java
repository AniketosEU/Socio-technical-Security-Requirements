/*
* CustomStsToolDiagramDocumentEditor.java
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
package eu.aniketos.wp1.ststool.diagram.custom.part;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ExposeHelper;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.action.*;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.parts.*;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.l10n.EditorMessages;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.CustomStsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.palette.CustomStsPaletteFactory;
import eu.aniketos.wp1.ststool.diagram.custom.palette.PaletteViewerExEx;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.edit.commands.WarningDialogCommandException;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IncompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ModifyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NeedEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NegativeGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.OwnEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PartOfEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PlayEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PositiveGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProduceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProvisionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.modelProvider.ISTSModelProvider;

@SuppressWarnings("restriction")
public abstract class CustomStsToolDiagramDocumentEditor extends DiagramDocumentEditor implements ViewChangeListener, ISTSModelProvider {

	PaletteRoot							paletteRoot;
	private String						baseName;
	private Map<String, Object>	tempPreferenceMap;

	/**
	 * Default Constructor
	 * 
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#DiagramDocumentEditor(boolean)
	 */
	public CustomStsToolDiagramDocumentEditor(boolean hasFlyoutPalette) {

		super(hasFlyoutPalette);
		tempPreferenceMap = new HashMap<String, Object>();
	}

	public Map getPreferenceMap(){

		return tempPreferenceMap;
	}


	@Override
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot){

		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		CustomStsPaletteFactory.fillPalette(root);
		return root;
	}


	/**
	 * Return a string used as base name for the editor
	 * 
	 * @return The basic name of the editor
	 */
	public String getBaseName(){

		if (baseName == null) {
			String[] result = ((URIEditorInput) getEditorInput()).getURI().path().split("/");
			baseName = result[result.length - 1];
		}
		return baseName;
	}

	/**
	 * Method that override the default key binding for some functions
	 */
	protected void rebindKey(){

		try {
			KeyHandler keyHandler = getDiagramGraphicalViewer().getKeyHandler();

			keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL));
			keyHandler.put(KeyStroke.getPressed(SWT.BS, 8, 0), getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL));

			keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, SWT.CONTROL), getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
			keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, SWT.CONTROL), getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	@Override
	public void setFocus(){

		super.setFocus();
		CustomStsPaletteFactory.hideElements(getViewsManager());
		getDiagramEditPart().refresh();
	}

	/**
	 * @return the default ViewManager for the current editor
	 */
	public ViewsManager getViewsManager(){

		return ((CustomStsToolDiagramEditPart) getDiagramEditPart()).getViewsManager();
	}

	/**
	 * 
	 */
	@Override
	protected void initializeGraphicalViewer(){
		super.initializeGraphicalViewer();
		getViewsManager().addViewChangeListener(this);
		setPartName(getBaseName() + " (" + getViewsManager().getCurrentView().getName() + ")");
	}


	/**
	 * Manage the change of view, updating the diagram and the palette.
	 * 
	 * @param view
	 *           The new view
	 */
	@Override
	public void changedView(int view){

		if (view == ViewsManager.FULL_VIEW) {
			CustomStsPaletteFactory.hideAll();
		} else {
			CustomStsPaletteFactory.hideElements(getViewsManager());
		}
		getDiagramEditPart().refresh();
		if (view != ViewsManager.EMPTY_VIEW) {
			setPartName(getBaseName() + " (" + getViewsManager().getCurrentView().getName() + ")");
		}
	}


	/**
	 * Implementation of the "Save As" for the current editor. The user is prompted to input a new filename, then the new file is opened
	 * 
	 * @param progressMonitor
	 *           The progress monitor for the operation
	 */
	@Override
	protected void performSaveAs(IProgressMonitor progressMonitor){

		URIEditorInput input = (URIEditorInput) getEditorInput();
		URI inputURI = input.getURI();


		Shell shell = getSite().getShell();
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setOverwrite(true);
		dialog.setFilterNames(new String[] { "ststool_diagram" });
		dialog.setFilterExtensions(new String[] { "*.ststool_diagram" });
		dialog.setFilterPath(inputURI.toFileString()); // Windows path
		dialog.setFileName("copy_" + input.getName());

		String destinationPath = dialog.open();
		if (destinationPath == null) return;

		File outFile = new File(destinationPath);

		try {

			File inputFile = new File(inputURI.toFileString());
			File tempFile = File.createTempFile("ststemp", "sts");
			copyfile(inputFile, tempFile);
			performSave(true, progressMonitor);
			copyfile(inputFile, outFile);
			copyfile(tempFile, inputFile);

			URI uri = URI.createURI(outFile.toURI().toASCIIString());
			final URIEditorInput newInput = new URIEditorInput(uri);
			//setInput(newInput);

			IWorkbenchPage page = getSite().getWorkbenchWindow().getWorkbench().getActiveWorkbenchWindow().getActivePage();

			try {
				page.openEditor(newInput, "eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorID", true);
			} catch (PartInitException e) {
				e.printStackTrace();
			}

			close(false);
			tempFile.delete();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to copy 2 file
	 * 
	 * @param original
	 *           the original file.
	 * @param copy
	 *           the copy file
	 */
	private void copyfile(File original,File copy){

		try {
			InputStream in = new FileInputStream(original);
			OutputStream out = new FileOutputStream(copy);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage() + " in the specified directory.");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean isSaveAsAllowed(){

		return (getEditorInput() instanceof URIEditorInput) && ((URIEditorInput) getEditorInput()).getURI().isFile();
	}

	/**
	 * 
	 */
	@Override
	public void setInput(IEditorInput input){

		super.setInput(input);
	}

	@Override
	public StsToolDiagram getStsModel(){

		return (StsToolDiagram) getDiagram().getElement();
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor){

		IDocumentProvider p = getDocumentProvider();
		if (p == null) return;
		getDiagramGraphicalViewer().getContents().refresh();
		if (p.isDeleted(getEditorInput())) {

			if (isSaveAsAllowed()) {

				/*
				 * 1GEUSSR: ITPUI:ALL - User should never loose changes made in the editors.
				 * Changed Behavior to make sure that if called inside a regular save (because
				 * of deletion of input element) there is a way to report back to the caller.
				 */
				performSaveAs(progressMonitor);

			} else {

				Shell shell = getSite().getShell();
				String title = EditorMessages.Editor_error_save_deleted_title;
				String msg = EditorMessages.Editor_error_save_deleted_message;
				MessageDialog.openError(shell, title, msg);
			}

		} else {
			updateState(getEditorInput());
			validateState(getEditorInput());
			performSave(false, progressMonitor);
		}
	}

	 /**
     * Creates a diagram edit domain
     */
    protected void createDiagramEditDomain() {
        DiagramEditDomain editDomain = new DiagramEditDomain(this);
        editDomain.setActionManager(createActionManager());
        setEditDomain(editDomain);
    }
	
	
	/**
	 * Configures my diagram edit domain with its command stack.
	 */
	@Override
	protected void configureDiagramEditDomain(){

		DefaultEditDomain editDomain = getEditDomain();

		if (editDomain != null) {
			CommandStack stack = editDomain.getCommandStack();

			if (stack != null) {
				// dispose the old stack
				stack.dispose();
			}

			// create and assign the new stack
			DiagramCommandStack diagramStack = new DiagramCommandStack(getDiagramEditDomain()) {

				@Override
				protected void execute(ICommand command,IProgressMonitor progressMonitor){
					
					if (progressMonitor != null) {
			            try {
			                command.addContext(getUndoContext());
			                getOperationHistory().execute(command, progressMonitor, null);

			            } catch (WarningDialogCommandException e) {
			            	
			            } catch (ExecutionException e) {
			                Trace.catching(DiagramUIPlugin.getInstance(),
			                    DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
			                    getClass(), "execute", e); //$NON-NLS-1$
			                Log.error(DiagramUIPlugin.getInstance(),
			                    DiagramUIStatusCodes.COMMAND_FAILURE, "execute", e); //$NON-NLS-1$
			            } 
			        } else {
			            try {
			                command.addContext(getUndoContext());
			                getOperationHistory().execute(command,
			                    new NullProgressMonitor(), null);

			            }  catch (WarningDialogCommandException e) {
			            	
			            } catch (ExecutionException e) {
			                Trace.catching(DiagramUIPlugin.getInstance(),
			                    DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
			                    getClass(), "execute", e); //$NON-NLS-1$
			                Log.error(DiagramUIPlugin.getInstance(),
			                    DiagramUIStatusCodes.COMMAND_FAILURE, "execute", e); //$NON-NLS-1$
			            }
			        }
					
					
					
					
					
					/*int view = getViewsManager().getCurrentIntView();
					getViewsManager().setCurrentView(ViewsManager.EMPTY_VIEW);
					getViewsManager().setCurrentView(view);*/
				}
				
			};
			diagramStack.setOperationHistory(getOperationHistory());

			// changes made on the stack can be undone from this editor
			diagramStack.setUndoContext(getUndoContext());

			editDomain.setCommandStack(diagramStack);
		}
		
//		editDomain.getCommandStack().addCommandStackListener(new CommandStackListener() {
//			
//			@Override
//			public void commandStackChanged(EventObject event) {
//				System.out.println("ssss");
//				
//			}
//		});
	}

	public DefaultEditDomain getDefEditDomain(){

		return getEditDomain();
	}

	private final static List<Class>	classes	= new ArrayList<Class>();
	static {
		classes.add(AgentEditPart.class);
		classes.add(AuthorisationEditPart.class);
		classes.add(CompatibleDutiesEditPart.class);
		classes.add(DelegationEditPart.class);
		classes.add(EventEditPart.class);
		classes.add(Goal2EditPart.class);
		classes.add(GoalDecompositionANDEditPart.class);
		classes.add(GoalDecompositionOREditPart.class);
		classes.add(IncompatibleDutiesEditPart.class);
		classes.add(IResourceEditPart.class);
		classes.add(NeedEditPart.class);
		classes.add(ModifyEditPart.class);
		classes.add(NegativeGoalContributionEditPart.class);
		classes.add(OwnEditPart.class);
		classes.add(PartOfEditPart.class);
		classes.add(PlayEditPart.class);
		classes.add(PositiveGoalContributionEditPart.class);
		classes.add(ProduceEditPart.class);
		classes.add(ProvisionEditPart.class);
		classes.add(RoleEditPart.class);
		classes.add(TangibleByEditPart.class);
		classes.add(ThreatEditPart.class);
		classes.add(TResource2EditPart.class);
	}


	public void focusObject(EObject o){

		IGraphicalEditPart ep = getEditPartForEObject(o);
		if (ep != null) {
			if (ep.isSelectable()) {
				getDiagramGraphicalViewer().select(ep);
				//org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer
				getDiagramGraphicalViewer().reveal(ep);
			}
		}
	}

	/**
	 * Creates a ScrollingGraphicalViewer without the drop adapter which excludes drag and drop functionality from other defined views (XML) Subclasses must override this method to include the DnD functionality
	 * 
	 * @return ScrollingGraphicalViewer
	 */
	@Override
	protected ScrollingGraphicalViewer createScrollingGraphicalViewer(){
		return new DiagramGraphicalViewer() {

			@Override
			public void reveal(EditPart part){
				if (part == null) return;
				EditPart current = part.getParent();
				while (current != null) {
					ExposeHelper helper = (ExposeHelper) current.getAdapter(ExposeHelper.class);
					if (helper != null) helper.exposeDescendant(part);
					current = current.getParent();
				}
				AccessibleEditPart acc = (AccessibleEditPart) part.getAdapter(AccessibleEditPart.class);
				if (acc != null) getControl().getAccessible().setFocus(acc.getAccessibleID());

				Viewport port = getFigureCanvas().getViewport();
				IFigure target = ((GraphicalEditPart) part).getFigure();
				Rectangle exposeRegion = target.getBounds().getCopy();
				target = target.getParent();
				while (target != null && target != port) {
					target.translateToParent(exposeRegion);
					target = target.getParent();
				}
				Dimension viewportSize = port.getClientArea().getSize();
				Point topLeft = exposeRegion.getTopLeft();
				Point center=topLeft.translate(exposeRegion.width/1,exposeRegion.height/2);
				getFigureCanvas().scrollSmoothTo(center.x-(viewportSize.width/2),center.y-(viewportSize.height/2));
			}
		};
	}

	private Map<EObject, Integer>	errorMarkerMap		= new HashMap<EObject, Integer>();
	private Map<EObject, Integer>	warningMarkerMap	= new HashMap<EObject, Integer>();


	public void setMarkerValue(EObject o,boolean error){
		if (o == null) return;
		if (error) {
			if (!errorMarkerMap.containsKey(o)) {
				errorMarkerMap.put(o, new Integer(0));
			}
			errorMarkerMap.put(o, errorMarkerMap.get(o) + 1);
		} else {
			if (!warningMarkerMap.containsKey(o)) {
				warningMarkerMap.put(o, new Integer(0));
			}
			warningMarkerMap.put(o, warningMarkerMap.get(o) + 1);
		}
	}

	public void removeMarkerValue(EObject o,boolean error){
		if (o == null) return;
		if (error) {
			if (errorMarkerMap.containsKey(o)) {
				Integer i = errorMarkerMap.get(o);
				if (i == 1) {
					errorMarkerMap.remove(o);
				} else {
					errorMarkerMap.put(o, new Integer(i - 1));
				}
			}
		} else {
			if (warningMarkerMap.containsKey(o)) {
				Integer i = warningMarkerMap.get(o);
				if (i == 1) {
					warningMarkerMap.remove(o);
				} else {
					warningMarkerMap.put(o, new Integer(i - 1));
				}
			}
		}
	}

	public void refresh(){

		int x = getViewsManager().getCurrentIntView();
		getViewsManager().setCurrentView(ViewsManager.EMPTY_VIEW);
		getViewsManager().setCurrentView(x);
	}

	public void clearAllMarker(){
		errorMarkerMap.clear();
		warningMarkerMap.clear();
		refresh();
	}

	public STSErrorType getMarkerValue(IGraphicalEditPart ep){
		EObject obj = (EObject) ((IAdaptable) ep).getAdapter(EObject.class);
		if (obj != null) {
			if (errorMarkerMap.containsKey(obj)) {
				return STSErrorType.ERROR;
			} else if (warningMarkerMap.containsKey(obj)) { return STSErrorType.WARNING; }
		}
		return STSErrorType.NO_ERROR;
	}

	private IGraphicalEditPart getEditPartForEObject(EObject o){

		if (o == null) return null;
		for (Object editPart : getDiagramGraphicalViewer().getEditPartRegistry().values()) {
			if (classes.contains(editPart.getClass()) && ((IAdaptable) editPart).getAdapter(EObject.class) == o) { return (IGraphicalEditPart) editPart; }
		}
		return null;
	}


	@Override
	protected PaletteViewer constructPaletteViewer(){

		PaletteViewerExEx paletteViewer = new PaletteViewerExEx();
		return paletteViewer;
	}

}
