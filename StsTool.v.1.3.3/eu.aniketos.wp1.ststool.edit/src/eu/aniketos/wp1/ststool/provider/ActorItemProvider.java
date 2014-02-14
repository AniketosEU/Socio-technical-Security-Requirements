/*
* ActorItemProvider.java
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
/**
 * DISI - University of Trento
 * 
 * $Id$
 */
package eu.aniketos.wp1.ststool.provider;


import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * This is the item provider adapter for a {@link eu.aniketos.wp1.ststool.Actor} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class ActorItemProvider extends StsElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String	copyright	= "DISI - University of Trento";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ActorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object){

		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			//addThreatedElementsPropertyDescriptor(object);
			//addIncomingDelegationsPropertyDescriptor(object);
			//addIncomingProvisionsPropertyDescriptor(object);
			//addIncomingAuthorisationsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Threated Elements feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addThreatedElementsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Threatable_threatedElements_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Threatable_threatedElements_feature", "_UI_Threatable_type"),
				 StstoolPackage.Literals.THREATABLE__THREATED_ELEMENTS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming Delegations feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingDelegationsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Actor_incomingDelegations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Actor_incomingDelegations_feature", "_UI_Actor_type"),
				 StstoolPackage.Literals.ACTOR__INCOMING_DELEGATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming Provisions feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingProvisionsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Actor_incomingProvisions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Actor_incomingProvisions_feature", "_UI_Actor_type"),
				 StstoolPackage.Literals.ACTOR__INCOMING_PROVISIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming Authorisations feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingAuthorisationsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Actor_incomingAuthorisations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Actor_incomingAuthorisations_feature", "_UI_Actor_type"),
				 StstoolPackage.Literals.ACTOR__INCOMING_AUTHORISATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object){
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__OUTGOING_DELEGATIONS);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__GOALS);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__OUTGOING_PROVISIONS);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__OUTGOING_AUTHORISATIONS);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__TRESOURCES);
			childrenFeatures.add(StstoolPackage.Literals.ACTOR__IRESOURCES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object,Object child){
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Actor.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object){
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Actor"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object){
		String label = ((Actor)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Actor_type") :
			getString("_UI_Actor_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification){
		updateChildren(notification);

		switch (notification.getFeatureID(Actor.class)) {
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
			case StstoolPackage.ACTOR__GOALS:
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
			case StstoolPackage.ACTOR__TRESOURCES:
			case StstoolPackage.ACTOR__IRESOURCES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors,Object object){
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__OUTGOING_DELEGATIONS,
				 StstoolFactory.eINSTANCE.createDelegation()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__GOALS,
				 StstoolFactory.eINSTANCE.createGoal()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__OUTGOING_PROVISIONS,
				 StstoolFactory.eINSTANCE.createProvision()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__OUTGOING_AUTHORISATIONS,
				 StstoolFactory.eINSTANCE.createAuthorisation()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__OUTGOING_AUTHORISATIONS,
				 StstoolFactory.eINSTANCE.createNonTransferableAuthorisation()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__OUTGOING_AUTHORISATIONS,
				 StstoolFactory.eINSTANCE.createTransferableAuthorisation()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__TRESOURCES,
				 StstoolFactory.eINSTANCE.createTResource()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ACTOR__IRESOURCES,
				 StstoolFactory.eINSTANCE.createOwn()));
	}

}
