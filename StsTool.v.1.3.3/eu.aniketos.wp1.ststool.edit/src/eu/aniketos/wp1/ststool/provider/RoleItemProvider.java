/*
* RoleItemProvider.java
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
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * This is the item provider adapter for a {@link eu.aniketos.wp1.ststool.Role} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class RoleItemProvider extends ActorItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

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
	public RoleItemProvider(AdapterFactory adapterFactory) {
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
			addMissionPropertyDescriptor(object);
			addPurposePropertyDescriptor(object);
			//addPlayedByPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Incompatible Duties In feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncompatibleDutiesInPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_SeparationOfDuties_incompatibleDutiesIn_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_SeparationOfDuties_incompatibleDutiesIn_feature", "_UI_SeparationOfDuties_type"),
				 StstoolPackage.Literals.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Compatible Duties In feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompatibleDutiesInPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_BindingOfDuties_compatibleDutiesIn_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_BindingOfDuties_compatibleDutiesIn_feature", "_UI_BindingOfDuties_type"),
				 StstoolPackage.Literals.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Played By feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPlayedByPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Role_playedBy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Role_playedBy_feature", "_UI_Role_type"),
				 StstoolPackage.Literals.ROLE__PLAYED_BY,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Purpose feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPurposePropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Role_purpose_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Role_purpose_feature", "_UI_Role_type"),
				 StstoolPackage.Literals.ROLE__PURPOSE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mission feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMissionPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Role_mission_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Role_mission_feature", "_UI_Role_type"),
				 StstoolPackage.Literals.ROLE__MISSION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dependent feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDependentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Role_dependent_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Role_dependent_feature", "_UI_Role_type"),
				 StstoolPackage.Literals.ROLE__DEPENDENT,
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
			childrenFeatures.add(StstoolPackage.Literals.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT);
			childrenFeatures.add(StstoolPackage.Literals.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT);
			childrenFeatures.add(StstoolPackage.Literals.ROLE__DEPEND_BY);
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
	 * This returns Role.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object){
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Role"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object){
		String label = ((Role)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Role_type") :
			getString("_UI_Role_type") + " " + label;
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

		switch (notification.getFeatureID(Role.class)) {
			case StstoolPackage.ROLE__PURPOSE:
			case StstoolPackage.ROLE__MISSION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
			case StstoolPackage.ROLE__DEPEND_BY:
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
				(StstoolPackage.Literals.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT,
				 StstoolFactory.eINSTANCE.createIncompatibleDuties()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT,
				 StstoolFactory.eINSTANCE.createCompatibleDuties()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.ROLE__DEPEND_BY,
				 StstoolFactory.eINSTANCE.createDependency()));
	}

}
