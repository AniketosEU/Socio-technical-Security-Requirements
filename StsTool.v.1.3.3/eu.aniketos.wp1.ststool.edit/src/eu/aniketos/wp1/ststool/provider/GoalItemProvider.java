/*
* GoalItemProvider.java
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
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * This is the item provider adapter for a {@link eu.aniketos.wp1.ststool.Goal} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class GoalItemProvider extends StsElementItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

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
	public GoalItemProvider(AdapterFactory adapterFactory) {
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
			//addDelegatedToPropertyDescriptor(object);
			//addDelegatedFromPropertyDescriptor(object);
			//addAuthorisationsPropertyDescriptor(object);
			//addIncomingContributionPropertyDescriptor(object);
			//addIncomingDecompositionsPropertyDescriptor(object);
			//addCapabilityPropertyDescriptor(object);
			addPreConditionsPropertyDescriptor(object);
			addPostConditionsPropertyDescriptor(object);
			//addResourceNeededPropertyDescriptor(object);
			//addResourcesProducedPropertyDescriptor(object);
			//addResourcesModifiedPropertyDescriptor(object);
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
	 * This adds a property descriptor for the Delegated To feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDelegatedToPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_delegatedTo_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_delegatedTo_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__DELEGATED_TO,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Delegated From feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDelegatedFromPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_delegatedFrom_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_delegatedFrom_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__DELEGATED_FROM,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Authorisations feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAuthorisationsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_authorisations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_authorisations_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__AUTHORISATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resource Needed feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourceNeededPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_resourceNeeded_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_resourceNeeded_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__RESOURCE_NEEDED,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resources Produced feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourcesProducedPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_resourcesProduced_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_resourcesProduced_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__RESOURCES_PRODUCED,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resources Modified feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourcesModifiedPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_resourcesModified_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_resourcesModified_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__RESOURCES_MODIFIED,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming Contribution feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingContributionPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_incomingContribution_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_incomingContribution_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__INCOMING_CONTRIBUTION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Incoming Decompositions feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncomingDecompositionsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_incomingDecompositions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_incomingDecompositions_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__INCOMING_DECOMPOSITIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Capability feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCapabilityPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_capability_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_capability_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__CAPABILITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Pre Conditions feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreConditionsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_preConditions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_preConditions_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__PRE_CONDITIONS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Post Conditions feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPostConditionsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Goal_postConditions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Goal_postConditions_feature", "_UI_Goal_type"),
				 StstoolPackage.Literals.GOAL__POST_CONDITIONS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(StstoolPackage.Literals.GOAL__OUTGOING_CONTRIBUTIONS);
			childrenFeatures.add(StstoolPackage.Literals.GOAL__OUTGOING_DECOMPOSITIONS);
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
	 * This returns Goal.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object){
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Goal"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object){
		String label = ((Goal)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Goal_type") :
			getString("_UI_Goal_type") + " " + label;
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

		switch (notification.getFeatureID(Goal.class)) {
			case StstoolPackage.GOAL__CAPABILITY:
			case StstoolPackage.GOAL__PRE_CONDITIONS:
			case StstoolPackage.GOAL__POST_CONDITIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
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
				(StstoolPackage.Literals.GOAL__OUTGOING_CONTRIBUTIONS,
				 StstoolFactory.eINSTANCE.createGoalContribution()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.GOAL__OUTGOING_CONTRIBUTIONS,
				 StstoolFactory.eINSTANCE.createPositiveGoalContribution()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.GOAL__OUTGOING_CONTRIBUTIONS,
				 StstoolFactory.eINSTANCE.createNegativeGoalContribution()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.GOAL__OUTGOING_DECOMPOSITIONS,
				 StstoolFactory.eINSTANCE.createGoalDecomposition()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.GOAL__OUTGOING_DECOMPOSITIONS,
				 StstoolFactory.eINSTANCE.createGoalDecompositionAND()));

		newChildDescriptors.add
			(createChildParameter
				(StstoolPackage.Literals.GOAL__OUTGOING_DECOMPOSITIONS,
				 StstoolFactory.eINSTANCE.createGoalDecompositionOR()));
	}

}
