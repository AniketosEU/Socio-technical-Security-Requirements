/*
* STSEditPartService.java
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
/******************************************************************************
 * Copyright (c) 2002, 2008 IBM Corporation and others. All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ****************************************************************************/

package eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.core.service.ExecutionStrategy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.Service;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DefaultCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DefaultConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DefaultNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.editpart.EditPartProviderConfiguration;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.editpart.IEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateRootEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Ratio;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;

/**
 * A service that supports the creation of editpart elements. Default editparts will be created if no sub-implementation creates one.
 * 
 * @see #createGraphicEditPart(View)
 */
/*
 * @canBeSeenBy %partners
 */
@SuppressWarnings("restriction")
final public class STSEditPartService extends Service implements IEditPartProvider, EditPartFactory {

	/**
	 * A descriptor for <code>ISemanticProvider</code> defined by a configuration element.
	 */
	protected static class ProviderDescriptor extends Service.ProviderDescriptor {

		/** the provider configuration parsed from XML */
		private EditPartProviderConfiguration	providerConfiguration;

		/**
		 * Constructs a <code>ISemanticProvider</code> descriptor for the specified configuration element.
		 * 
		 * @param element
		 *           The configuration element describing the provider.
		 */
		public ProviderDescriptor(IConfigurationElement element) {

			super(element);

			this.providerConfiguration = EditPartProviderConfiguration.parse(element);
			Assert.isNotNull(providerConfiguration);
		}

		/**
		 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
		 */
		@Override
		public boolean provides(IOperation operation){

			if (!policyInitialized) {
				policy = getPolicy();
				policyInitialized = true;
			}
			if (policy != null) return policy.provides(operation);
			if (provider == null) {
				if (isSupportedInExtention(operation)) {
					providerConfiguration = null;
					return getProvider().provides(operation);
				}
				return false;
			}
			return getProvider().provides(operation);
		}

		/**
		 * Cheks if the operation is supported by the XML extension
		 * 
		 * @param operation
		 * @return <code> true</code> or <code>false</code>
		 */
		private boolean isSupportedInExtention(IOperation operation){

			if (operation instanceof CreateGraphicEditPartOperation) {
				CreateGraphicEditPartOperation o = (CreateGraphicEditPartOperation) operation;
				return providerConfiguration.supports(o.getView());
			} else if (operation instanceof CreateRootEditPartOperation) { return providerConfiguration.supportsRootEditPart(); }
			return false;
		}

		/**
		 * the default implementation is overriden here to make it easier to debug XML providers, now when you select the ProviderDescriptor in the debug window the provider class name will be displayed
		 * 
		 * @return the provider class name
		 */
		@Override
		public String toString(){

			return getElement().getAttribute("class"); //$NON-NLS-1$
		}
	}



	private int	view;

	/**
	 * constructor
	 */
	public STSEditPartService(int view) {

		super(true, false);
		configureProviders(DiagramUIPlugin.getPluginId(), "editpartProviders"); //$NON-NLS-1$
		this.view = view;
	}


	/**
	 * @see org.eclipse.gmf.runtime.common.core.service.Service#newProviderDescriptor(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	protected Service.ProviderDescriptor newProviderDescriptor(IConfigurationElement element){

		return new ProviderDescriptor(element);
	}

	/**
	 * @see org.eclipse.gmf.runtime.common.core.service.Service#createPriorityCache()
	 */
	@Override
	protected Map createPriorityCache(){

		return new HashMap();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.service.Service#getCachingKey(org.eclipse.gmf.runtime.common.core.service.IOperation)
	 */
	@Override
	protected Object getCachingKey(IOperation operation){

		return ((IEditPartOperation) operation).getCachingKey();
	}

	/**
	 * Creates an <code>IGraphicalEditPart</code> instance by forwarding a <code>CreateGraphicEditPartOperation</code> to the registered providers. The supplied parameter is the editpart's constructor parameter.
	 * <P>
	 * The following <i>default</i> editparts are created if none is created by a provider.
	 * <UL>
	 * <LI> <code>IDiagramView</code> ... <code>DiagramEditPart</code>
	 * <LI> <code>ILabelView</code< ... <code>LabelEditPart</code>
	 * <LI> <code>ITextCompartmentView</code> ... <code>TextCompartmentEditPart</code>
	 * </UL>
	 * 
	 * @param view
	 *           the view element <i>controlled</i> by the created editpart
	 * @return an instance.
	 */
	public IGraphicalEditPart createGraphicEditPart(View view){

		if (view == null) return null;

		IGraphicalEditPart result = null;
		CreateGraphicEditPartOperation createGraphicEditPartOperation = new STSCreateGraphicEditPartOperation(view);
		result = (IGraphicalEditPart) execute(createGraphicEditPartOperation);

		if (result == null) {
			if (view instanceof Node) {
				if (((Node) view).getLayoutConstraint() instanceof Ratio) {
					result = new DefaultCompartmentEditPart(view);
				} else {
					result = new DefaultNodeEditPart(view);
				}
			} else if (view instanceof Edge) {
				result = new DefaultConnectionEditPart(view);
			} else if (view instanceof Diagram) {
				result = new DiagramEditPart(view);
			}
		}
		return result;
	}

	/**
	 * Executes the specified operation using the FIRST execution strategy; the first provider capable of honoring the supplied operation.
	 * 
	 * @param operation
	 * @return the provider's return value (or <tt>null</tt> if there was no provider able to honor the supplied operation.
	 */
	private Object execute(IOperation operation){

		List results = execute(ExecutionStrategy.FIRST, operation);
		return results.isEmpty() ? null : results.get(0);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context,final Object model){


		try {
			return (EditPart) TransactionUtil.getEditingDomain(model).runExclusive(new RunnableWithResult.Impl() {

				public void run(){

					setResult(createGraphicEditPart((View) model));
					if (getResult() instanceof StsToolDiagramEditPart) {
						((StsToolDiagramEditPart) getResult()).getViewsManager().setCurrentView(view);
					}
				}
			});
		} catch (InterruptedException e) {
			Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "createEditPart", e); //$NON-NLS-1$
			Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING, "createEditPart", e); //$NON-NLS-1$
			return null;
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.internal.services.editpart.IEditPartProvider#createRootEditPart(org.eclipse.gmf.runtime.notation.Diagram)
	 */
	public RootEditPart createRootEditPart(Diagram diagram){

		RootEditPart result;
		CreateRootEditPartOperation createRootEditPartOperation = new CreateRootEditPartOperation(diagram);
		result = (RootEditPart) execute(createRootEditPartOperation);

		// provide default implementation
		return (result == null) ? new DiagramRootEditPart(diagram.getMeasurementUnit()) : result;
	}

	private class STSCreateGraphicEditPartOperation extends CreateGraphicEditPartOperation {

		protected STSCreateGraphicEditPartOperation(View view) {

			super(view);
		}

	}

}
