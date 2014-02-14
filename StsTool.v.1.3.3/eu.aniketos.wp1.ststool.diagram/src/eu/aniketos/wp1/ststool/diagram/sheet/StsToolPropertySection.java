/*
* StsToolPropertySection.java
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

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * @generated NOT
 */
public class StsToolPropertySection extends MyAdvancedPropertySection implements IPropertySourceProvider {

	/**
	 * @generated
	 */
	public IPropertySource getPropertySource(Object object){

		if (object instanceof IPropertySource) { return (IPropertySource) object; }
		AdapterFactory af = getAdapterFactory(object);
		if (af != null) {
			IItemPropertySource ips = (IItemPropertySource) af.adapt(object, IItemPropertySource.class);
			if (ips != null) { return new PropertySource(object, ips); }
		}
		if (object instanceof IAdaptable) { return (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class); }
		return null;
	}

	/**
	 * @generated
	 */
	@Override
	protected IPropertySourceProvider getPropertySourceProvider(){

		return this;
	}

	/**
	 * Modify/unwrap selection.
	 * 
	 * @generated
	 */
	protected Object transformSelection(Object selected){

		if (selected instanceof EditPart) {
			Object model = ((EditPart) selected).getModel();
			return model instanceof View ? ((View) model).getElement() : null;
		}
		if (selected instanceof View) { return ((View) selected).getElement(); }
		if (selected instanceof IAdaptable) {
			View view = (View) ((IAdaptable) selected).getAdapter(View.class);
			if (view != null) { return view.getElement(); }
		}
		return selected;
	}

	/**
	 * @generated
	 */
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){

		if (selection.isEmpty() || false == selection instanceof StructuredSelection) {
			super.setInput(part, selection);
			return;
		}
		final StructuredSelection structuredSelection = ((StructuredSelection) selection);
		ArrayList transformedSelection = new ArrayList(structuredSelection.size());
		for (Iterator it = structuredSelection.iterator(); it.hasNext();) {
			Object r = transformSelection(it.next());
			if (r != null) {
				transformedSelection.add(r);
			}
		}
		super.setInput(part, new StructuredSelection(transformedSelection));
	}

	/**
	 * @generated
	 */
	protected AdapterFactory getAdapterFactory(Object object){

		if (getEditingDomain() instanceof AdapterFactoryEditingDomain) { return ((AdapterFactoryEditingDomain) getEditingDomain()).getAdapterFactory(); }
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
		if (editingDomain != null) { return ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory(); }
		return null;
	}

	class CustomOrdredPropertySheetPage extends PropertySheetPage {

		public CustomOrdredPropertySheetPage() {

			super();
			setSorter(new StsToolPropertySheetSorter());
		}
	}



}
