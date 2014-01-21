/*
* StsToolDocumentProvider.java
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

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.common.notify.*;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.*;
import org.eclipse.emf.transaction.*;
import org.eclipse.emf.transaction.impl.*;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl.FactoryImpl.*;
import org.eclipse.emf.transaction.util.*;
import org.eclipse.emf.workspace.*;
import org.eclipse.emf.workspace.impl.*;
import org.eclipse.emf.workspace.internal.*;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory.*;
import org.eclipse.gmf.runtime.diagram.core.internal.listener.*;
import org.eclipse.gmf.runtime.diagram.core.listener.*;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorStatusCodes;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.*;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorInput;

/**
 * @generated
 */
@SuppressWarnings("restriction")
public class StsToolDocumentProvider extends AbstractDocumentProvider implements IDiagramDocumentProvider {

	/**
	 * @generated
	 */
	@Override
	protected ElementInfo createElementInfo(Object element) throws CoreException{
		if (false == element instanceof URIEditorInput) { throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, NLS.bind(Messages.StsToolDocumentProvider_IncorrectInputError, new Object[] {
				element, "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
		null)); }
		IEditorInput editorInput = (IEditorInput) element;
		IDiagramDocument document = (IDiagramDocument) createDocument(editorInput);

		ResourceSetInfo info = new ResourceSetInfo(document, editorInput);
		info.setModificationStamp(computeModificationStamp(info));
		info.fStatus = null;
		return info;
	}

	/**
	 * @generated
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException{
		if (false == element instanceof URIEditorInput) { throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, NLS.bind(Messages.StsToolDocumentProvider_IncorrectInputError, new Object[] {
				element, "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
		null)); }
		IDocument document = createEmptyDocument();
		setDocumentContent(document, (IEditorInput) element);
		setupDocument(element, document);
		return document;
	}

	/**
	 * Sets up the given document as it would be provided for the given element. The content of the document is not changed. This default implementation is empty. Subclasses may reimplement.
	 * 
	 * @param element
	 *           the blue-print element
	 * @param document
	 *           the document to set up
	 * @generated
	 */
	protected void setupDocument(Object element,IDocument document){
		// for subclasses
	}

	/**
	 * @generated
	 */
	private long computeModificationStamp(ResourceSetInfo info){
		int result = 0;
		for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = info.getLoadedResourcesIterator(); it.hasNext();) {
			Resource nextResource = (Resource) it.next();
			File file = getFile(nextResource);
			if (file != null && file.exists()) {
				result += file.lastModified();
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	@Override
	protected IDocument createEmptyDocument(){
		DiagramDocument document = new DiagramDocument();
		document.setEditingDomain(createEditingDomain());
		return document;
	}
	
	/**
	 * @generated
	 */
	private TransactionalEditingDomain createEditingDomain(){
		TransactionalEditingDomain editingDomain = DiagramEditingDomainFactory.getInstance().createEditingDomain();
		editingDomain.setID("eu.aniketos.wp1.ststool.diagram.EditingDomain"); //$NON-NLS-1$
		final NotificationFilter diagramResourceModifiedFilter = NotificationFilter.createNotifierFilter(editingDomain.getResourceSet()).and(NotificationFilter.createEventTypeFilter(Notification.ADD)).and(NotificationFilter.createFeatureFilter(ResourceSet.class, ResourceSet.RESOURCE_SET__RESOURCES));
		editingDomain.getResourceSet().eAdapters().add(new Adapter() {

			private Notifier	myTarger;

			public Notifier getTarget(){
				return myTarger;
			}

			public boolean isAdapterForType(Object type){
				return false;
			}

			public void notifyChanged(Notification notification){
				if (diagramResourceModifiedFilter.matches(notification)) {
					Object value = notification.getNewValue();
					if (value instanceof Resource) {
						((Resource) value).setTrackingModification(true);
					}
				}
			}

			public void setTarget(Notifier newTarget){
				myTarger = newTarget;
			}

		});

		return editingDomain;
	}

	/**
	 * @generated
	 */
	protected void setDocumentContent(IDocument document,IEditorInput element) throws CoreException{
		IDiagramDocument diagramDocument = (IDiagramDocument) document;
		TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
		if (element instanceof URIEditorInput) {
			URI uri = ((URIEditorInput) element).getURI();
			Resource resource = null;
			try {
				resource = domain.getResourceSet().getResource(uri.trimFragment(), false);
				if (resource == null) {
					resource = domain.getResourceSet().createResource(uri.trimFragment());
				}
				if (!resource.isLoaded()) {
					try {
						Map options = new HashMap(GMFResourceFactory.getDefaultLoadOptions());
						// @see 171060 
						 options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
						resource.load(options);
					} catch (IOException e) {
						resource.unload();
						throw e;
					}
				}
				if (uri.fragment() != null) {
					EObject rootElement = resource.getEObject(uri.fragment());
					if (rootElement instanceof Diagram) {
						document.setContent((Diagram) rootElement);
						return;
					}
				} else {
					for (Iterator it = resource.getContents().iterator(); it.hasNext();) {
						Object rootElement = it.next();
						if (rootElement instanceof Diagram) {
							document.setContent((Diagram) rootElement);
							return;
						}
					}
				}
				throw new RuntimeException(Messages.StsToolDocumentProvider_NoDiagramInResourceError);
			} catch (Exception e) {
				CoreException thrownExcp = null;
				if (e instanceof CoreException) {
					thrownExcp = (CoreException) e;
				} else {
					String msg = e.getLocalizedMessage();
					thrownExcp = new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, msg != null ? msg : Messages.StsToolDocumentProvider_DiagramLoadingError, e));
				}
				throw thrownExcp;
			}
		} else {
			throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, NLS.bind(Messages.StsToolDocumentProvider_IncorrectInputError, new Object[] {
					element, "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
			null));
		}
	}

	/**
	 * @generated
	 */
	@Override
	public long getModificationStamp(Object element){
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) { return computeModificationStamp(info); }
		return super.getModificationStamp(element);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean isDeleted(Object element){
		IDiagramDocument document = getDiagramDocument(element);
		if (document != null) {
			Resource diagramResource = document.getDiagram().eResource();
			if (diagramResource != null) {
				File file = getFile(diagramResource);
				return file != null && !file.exists();
			}
		}
		return super.isDeleted(element);
	}

	/**
	 * @generated
	 */
	public ResourceSetInfo getResourceSetInfo(Object editorInput){
		return (ResourceSetInfo) super.getElementInfo(editorInput);
	}

	/**
	 * @generated
	 */
	@Override
	protected void disposeElementInfo(Object element,ElementInfo info){
		if (info instanceof ResourceSetInfo) {
			ResourceSetInfo resourceSetInfo = (ResourceSetInfo) info;
			resourceSetInfo.dispose();
		}
		super.disposeElementInfo(element, info);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean isReadOnly(Object element){
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (info.isUpdateCache()) {
				try {
					updateCache(element);
				} catch (CoreException ex) {
					StsToolDiagramEditorPlugin.getInstance().logError(Messages.StsToolDocumentProvider_isModifiable, ex);
					// Error message to log was initially taken from org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages.StorageDocumentProvider_isModifiable
				}
			}
			return info.isReadOnly();
		}
		return super.isReadOnly(element);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean isModifiable(Object element){
		if (!isStateValidated(element)) {
			if (element instanceof URIEditorInput) { return true; }
		}
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (info.isUpdateCache()) {
				try {
					updateCache(element);
				} catch (CoreException ex) {
					StsToolDiagramEditorPlugin.getInstance().logError(Messages.StsToolDocumentProvider_isModifiable, ex);
					// Error message to log was initially taken from org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages.StorageDocumentProvider_isModifiable
				}
			}
			return info.isModifiable();
		}
		return super.isModifiable(element);
	}

	/**
	 * @generated
	 */
	protected void updateCache(Object element) throws CoreException{
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = (Resource) it.next();
				File file = getFile(nextResource);
				if (file != null && file.exists() && !file.canWrite()) {
					info.setReadOnly(true);
					info.setModifiable(false);
					return;
				}
			}
			info.setReadOnly(false);
			info.setModifiable(true);
			return;
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void doUpdateStateCache(Object element) throws CoreException{
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			info.setUpdateCache(true);
		}
		super.doUpdateStateCache(element);
	}

	/**
	 * @generated
	 */
	@Override
	public boolean isSynchronized(Object element){
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) { return info.isSynchronized(); }
		return super.isSynchronized(element);
	}

	/**
	 * @generated
	 */
	@Override
	protected void doSynchronize(Object element,IProgressMonitor monitor) throws CoreException{
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = (Resource) it.next();
				handleElementChanged(info, nextResource, monitor);
			}
			return;
		}
		super.doSynchronize(element, monitor);
	}


	/*private void preSave(){

		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		//IWorkbenchPage page = win.getActivePage();

		if (window.getActivePage().getActiveEditor() == null || !(window.getActivePage().getActiveEditor() instanceof CustomStsToolDiagramDocumentEditor)) { return; }

		DiagramEditPart dep = ((CustomStsToolDiagramDocumentEditor) window.getActivePage().getActiveEditor()).getDiagramEditPart();

		if (dep instanceof CustomStsToolDiagramEditPart) {
			try {
				CustomStsToolDiagramEditPart cdep = (CustomStsToolDiagramEditPart) dep;
				if (!wb.isClosing()) {
					ViewsManager vm = cdep.getViewsManager();
					int currentView = vm.getCurrentIntView();
					vm.setCurrentView(ViewsManager.EMPTY_VIEW);
					cdep.refresh();
					vm.setCurrentView(currentView);
					cdep.refresh();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}*/

	/**
	 * @generated NOT
	 */
	@Override
	protected void doSaveDocument(IProgressMonitor monitor,Object element,IDocument document,boolean overwrite)
			throws CoreException{

		try {
			monitor.beginTask("Saving", 100);
			doSaveDocumentGen(new SubProgressMonitor(monitor, 100), element, document, overwrite);
			//doSaveDocumentGen(new SubProgressMonitor(monitor, 50), element, document, overwrite);
		} finally {
			if (monitor != null) monitor.done();
		}
		;
	}


	/**
	 * @generated
	 */
	protected void doSaveDocumentGen(IProgressMonitor monitor,Object element,IDocument document,boolean overwrite)
			throws CoreException{
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (!overwrite && !info.isSynchronized()) { throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, IStatus.ERROR, Messages.StsToolDocumentProvider_UnsynchronizedFileSaveError, null)); }
			fireElementStateChanging(element);
			try {
				monitor.beginTask(Messages.StsToolDocumentProvider_SaveDiagramTask, info.getResourceSet().getResources().size() + 1); //"Saving diagram"
				for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = info.getLoadedResourcesIterator(); it.hasNext();) {
					Resource nextResource = (Resource) it.next();
					monitor.setTaskName(NLS.bind(Messages.StsToolDocumentProvider_SaveNextResourceTask, nextResource.getURI()));
					if (nextResource.isLoaded() && !info.getEditingDomain().isReadOnly(nextResource)) {
						try {
							nextResource.save(StsToolDiagramEditorUtil.getSaveOptions());
						} catch (IOException e) {
							fireElementStateChangeFailed(element);
							throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, EditorStatusCodes.RESOURCE_FAILURE, e.getLocalizedMessage(), null));
						}
					}
					monitor.worked(1);
				}
				monitor.done();
				info.setModificationStamp(computeModificationStamp(info));
			} catch (RuntimeException x) {
				fireElementStateChangeFailed(element);
				throw x;
			}
		} else {
			URI newResoruceURI;
			List affectedFiles = null;
			if (element instanceof URIEditorInput) {
				newResoruceURI = ((URIEditorInput) element).getURI();
			} else {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, NLS.bind(Messages.StsToolDocumentProvider_IncorrectInputError, new Object[] {
						element, "org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
				null));
			}
			if (false == document instanceof IDiagramDocument) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, "Incorrect document used: " + document + " instead of org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument", null)); //$NON-NLS-1$ //$NON-NLS-2$
			}
			IDiagramDocument diagramDocument = (IDiagramDocument) document;
			final Resource newResource = diagramDocument.getEditingDomain().getResourceSet().createResource(newResoruceURI);
			final Diagram diagramCopy = (Diagram) EcoreUtil.copy(diagramDocument.getDiagram());
			try {
				new AbstractTransactionalCommand(diagramDocument.getEditingDomain(), NLS.bind(Messages.StsToolDocumentProvider_SaveAsOperation, diagramCopy.getName()), affectedFiles) {

					protected CommandResult doExecuteWithResult(IProgressMonitor monitor,IAdaptable info)
							throws ExecutionException{
						newResource.getContents().add(diagramCopy);
						return CommandResult.newOKCommandResult();
					}
				}.execute(monitor, null);
				newResource.save(StsToolDiagramEditorUtil.getSaveOptions());
			} catch (ExecutionException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			} catch (IOException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, StsToolDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			}
			newResource.unload();
		}
	}

	/**
	 * @generated
	 */
	protected void handleElementChanged(ResourceSetInfo info,Resource changedResource,IProgressMonitor monitor){
		changedResource.unload();

		fireElementContentAboutToBeReplaced(info.getEditorInput());
		removeUnchangedElementListeners(info.getEditorInput(), info);
		info.fStatus = null;
		try {
			setDocumentContent(info.fDocument, info.getEditorInput());
		} catch (CoreException e) {
			info.fStatus = e.getStatus();
		}
		if (!info.fCanBeSaved) {
			info.setModificationStamp(computeModificationStamp(info));
		}
		addUnchangedElementListeners(info.getEditorInput(), info);
		fireElementContentReplaced(info.getEditorInput());
	}

	/**
	 * @generated
	 */
	protected void handleElementMoved(IEditorInput input,URI uri){

		// TODO: append suffix to the URI! (use diagram as a parameter)
		fireElementMoved(input, new URIEditorInput(uri));
	}

	/**
	 * @generated
	 */
	public IEditorInput createInputWithEditingDomain(IEditorInput editorInput,TransactionalEditingDomain domain){
		return editorInput;
	}

	/**
	 * @generated
	 */
	public IDiagramDocument getDiagramDocument(Object element){
		IDocument doc = getDocument(element);
		if (doc instanceof IDiagramDocument) { return (IDiagramDocument) doc; }
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor){
		return null;
	}

	/**
	 * @generated
	 */
	private static File getFile(Resource resource){
		URI resourceUri = resource.getURI();
		if (resourceUri != null && resourceUri.isFile()) {
			File file = new File(resourceUri.toFileString());
			if (!file.isDirectory()) { return file; }
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected class ResourceSetInfo extends ElementInfo {

		/**
		 * @generated
		 */
		private long										myModificationStamp	= 0;

		/**
		 * @generated
		 */
		private IDiagramDocument						myDocument;

		/**
		 * @generated
		 */
		private IEditorInput								myEditorInput;

		/**
		 * @generated
		 */
		private boolean									myUpdateCache			= true;

		/**
		 * @generated
		 */
		private boolean									myModifiable			= false;

		/**
		 * @generated
		 */
		private boolean									myReadOnly				= true;

		/**
		 * @generated
		 */
		private ResourceSetModificationListener	myResourceSetListener;

		/**
		 * @generated
		 */
		public ResourceSetInfo(IDiagramDocument document, IEditorInput editorInput) {
			super(document);
			myDocument = document;
			myEditorInput = editorInput;
			myResourceSetListener = new ResourceSetModificationListener(this);
			getResourceSet().eAdapters().add(myResourceSetListener);
		}

		/**
		 * @generated
		 */
		public long getModificationStamp(){
			return myModificationStamp;
		}

		/**
		 * @generated
		 */
		public void setModificationStamp(long modificationStamp){
			myModificationStamp = modificationStamp;
		}

		/**
		 * @generated
		 */
		public TransactionalEditingDomain getEditingDomain(){
			return myDocument.getEditingDomain();
		}

		/**
		 * @generated
		 */
		public ResourceSet getResourceSet(){
			return getEditingDomain().getResourceSet();
		}

		/**
		 * @generated
		 */
		public Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/getLoadedResourcesIterator(){
			return new ArrayList/*<org.eclipse.emf.ecore.resource.Resource>*/(getResourceSet().getResources()).iterator();
		}

		/**
		 * @generated
		 */
		public IEditorInput getEditorInput(){
			return myEditorInput;
		}

		/**
		 * @generated
		 */
		public void dispose(){
			getResourceSet().eAdapters().remove(myResourceSetListener);
			for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = getLoadedResourcesIterator(); it.hasNext();) {
				Resource resource = (Resource) it.next();
				resource.unload();
			}
			getEditingDomain().dispose();
		}

		/**
		 * @generated
		 */
		public boolean isSynchronized(){
			return getModificationStamp() == computeModificationStamp(this);
		}

		/**
		 * @generated
		 */
		public boolean isUpdateCache(){
			return myUpdateCache;
		}

		/**
		 * @generated
		 */
		public void setUpdateCache(boolean update){
			myUpdateCache = update;
		}

		/**
		 * @generated
		 */
		public boolean isModifiable(){
			return myModifiable;
		}

		/**
		 * @generated
		 */
		public void setModifiable(boolean modifiable){
			myModifiable = modifiable;
		}

		/**
		 * @generated
		 */
		public boolean isReadOnly(){
			return myReadOnly;
		}

		/**
		 * @generated
		 */
		public void setReadOnly(boolean readOnly){
			myReadOnly = readOnly;
		}

	}

	/**
	 * @generated
	 */
	private class ResourceSetModificationListener extends EContentAdapter {

		/**
		 * @generated
		 */
		private NotificationFilter	myModifiedFilter;

		/**
		 * @generated
		 */
		private ResourceSetInfo		myInfo;

		/**
		 * @generated
		 */
		public ResourceSetModificationListener(ResourceSetInfo info) {
			myInfo = info;
			myModifiedFilter = NotificationFilter.createEventTypeFilter(Notification.SET).or(NotificationFilter.createEventTypeFilter(Notification.UNSET)).and(NotificationFilter.createFeatureFilter(Resource.class, Resource.RESOURCE__IS_MODIFIED));
		}

		/**
		 * @generated
		 */
		@Override
		public void notifyChanged(Notification notification){
			if (notification.getNotifier() instanceof ResourceSet) {
				super.notifyChanged(notification);
			}
			if (!notification.isTouch() && myModifiedFilter.matches(notification)) {
				if (notification.getNotifier() instanceof Resource) {
					Resource resource = (Resource) notification.getNotifier();
					if (resource.isLoaded()) {
						boolean modified = false;
						for (Iterator/*<org.eclipse.emf.ecore.resource.Resource>*/it = myInfo.getLoadedResourcesIterator(); it.hasNext() && !modified;) {
							Resource nextResource = (Resource) it.next();
							if (nextResource.isLoaded()) {
								modified = nextResource.isModified();
							}
						}
						boolean dirtyStateChanged = false;
						synchronized (myInfo) {
							if (modified != myInfo.fCanBeSaved) {
								myInfo.fCanBeSaved = modified;
								dirtyStateChanged = true;
							}
						}
						if (dirtyStateChanged) {
							fireElementDirtyStateChanged(myInfo.getEditorInput(), modified);

							if (!modified) {
								myInfo.setModificationStamp(computeModificationStamp(myInfo));
							}
						}
					}
				}
			}
		}

	}
	
	}
