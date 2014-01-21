/*
* MyAdvancedPropertySection.java
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
package eu.aniketos.wp1.ststool.diagram.sheet;

import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.gmf.runtime.emf.ui.properties.sections.UndoableModelPropertySheetEntry;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * The advanced property section displayed the "original" tabular property sheet page.
 * 
 * @author Anthony Hunter <a href="mailto:anthonyh@ca.ibm.com">anthonyh@ca.ibm.com </a>
 */
public class MyAdvancedPropertySection extends AbstractModelerPropertySection {

	/**
	 * the property sheet page for this section
	 */
	protected PropertySheetPage	page;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse
	 * .swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(final Composite parent,TabbedPropertySheetPage aTabbedPropertySheetPage){

		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		FormData data = null;

		String tableLabelStr = getTableLabel();
		CLabel tableLabel = null;
		if (tableLabelStr != null && tableLabelStr.length() > 0) {
			tableLabel = getWidgetFactory().createCLabel(composite, tableLabelStr);
			data = new FormData();
			data.left = new FormAttachment(0, 0);
			data.top = new FormAttachment(0, 0);
			tableLabel.setLayoutData(data);
		}

		page = new MySortedPropertySheetPage();
		UndoableModelPropertySheetEntry root = new UndoableModelPropertySheetEntry(OperationHistoryFactory.getOperationHistory());

		root.setPropertySourceProvider(getPropertySourceProvider());
		page.setRootEntry(root);

		page.createControl(composite);
		data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		if (tableLabel == null) {
			data.top = new FormAttachment(0, 0);
		} else {
			data.top = new FormAttachment(tableLabel, 0, SWT.BOTTOM);
		}
		data.bottom = new FormAttachment(100, 0);
		data.height = 100;
		data.width = 100;
		page.getControl().setLayoutData(data);

		setActionBars(aTabbedPropertySheetPage.getSite().getActionBars());

	}

	/**
	 * Sets and prepares the actionBars for this section
	 * 
	 * @param actionBars
	 *           the action bars for this page
	 * @see org.eclipse.gmf.runtime.common.ui.properties.TabbedPropertySheetPage#setActionBars(org.eclipse.ui.IActionBars)
	 */
	public void setActionBars(IActionBars actionBars){

		if (actionBars != null) {
			actionBars.getMenuManager().removeAll();
			actionBars.getToolBarManager().removeAll();
			actionBars.getStatusLineManager().removeAll();

			page.makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(), actionBars.getStatusLineManager());

			actionBars.getToolBarManager().update(true);
		}

	}

	/**
	 * Returns the PropertySource provider. The default implementation returns static adapter factory for the properties services. If the extending class needs to use a different provider then this method has to be overwriten.
	 * 
	 * @return The PropertySource provider
	 */
	protected IPropertySourceProvider getPropertySourceProvider(){

		return propertiesProvider;
	}

	/**
	 * Returns the label for the table. The default implementation returns null, that is, there is no label.
	 * 
	 * @return The label for the table
	 */
	protected String getTableLabel(){

		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui
	 * .IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){

		IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);
		if (provider != null) {
			EditingDomain theEditingDomain = provider.getEditingDomain();
			if (theEditingDomain instanceof TransactionalEditingDomain) {
				setEditingDomain((TransactionalEditingDomain) theEditingDomain);
			}
		}

		// Set the eObject for the section, too. The workbench part may not
		// adapt to IEditingDomainProvider, in which case the selected EObject
		// will be used to derive the editing domain.
		if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
			Object firstElement = ((IStructuredSelection) selection).getFirstElement();

			if (firstElement != null) {
				EObject adapted = unwrap(firstElement);

				if (adapted != null) {
					setEObject(adapted);
				}
			}
		}

		page.selectionChanged(part, selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	@Override
	public void dispose(){

		super.dispose();

		if (page != null) {
			page.dispose();
			page = null;
		}

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh(){

		page.refresh();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	@Override
	public boolean shouldUseExtraSpace(){

		return true;
	}

	/**
	 * Update if nessesary, upon receiving the model event.
	 * 
	 * @see #aboutToBeShown()
	 * @see #aboutToBeHidden()
	 * @param notification
	 *           - even notification
	 * @param element
	 *           - element that has changed
	 */
	@Override
	public void update(final Notification notification,EObject element){

		if (!isDisposed()) {
			postUpdateRequest(new Runnable() {

				public void run(){

					if (!isDisposed() && !isNotifierDeleted(notification)) refresh();
				}
			});
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.emf.core.edit.IDemuxedMListener#getFilter()
	 */
	@Override
	public NotificationFilter getFilter(){

		return NotificationFilter.createEventTypeFilter(Notification.SET).or(NotificationFilter.createEventTypeFilter(Notification.UNSET)).or(NotificationFilter.createEventTypeFilter(Notification.ADD)).or(NotificationFilter.createEventTypeFilter(Notification.ADD_MANY)).or(NotificationFilter.createEventTypeFilter(Notification.REMOVE)).or(NotificationFilter.createEventTypeFilter(Notification.REMOVE_MANY)).and(NotificationFilter.createNotifierTypeFilter(EObject.class));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gmf.runtime.diagram.ui.properties.sections.
	 * AbstractModelerPropertySection#addToEObjectList(java.lang.Object)
	 */
	@Override
	protected boolean addToEObjectList(Object object){

		/* not implemented */
		return true;
	}

	class MySortedPropertySheetPage extends PropertySheetPage {

		public MySortedPropertySheetPage() {

			super();
			setSorter(new StsToolPropertySheetSorter());
		}
	}

}
