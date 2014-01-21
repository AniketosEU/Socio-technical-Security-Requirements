/*
* DelegationItemProvider.java
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * This is the item provider adapter for a {@link eu.aniketos.wp1.ststool.Delegation} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class DelegationItemProvider extends StsRelationItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

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
	public DelegationItemProvider(AdapterFactory adapterFactory) {
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
			itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();
		}
		itemPropertyDescriptors.clear();
		super.getPropertyDescriptors(object);

		//addThreatedElementsPropertyDescriptor(object);
		//addSourceGoalPropertyDescriptor(object);
		//addTargetGoalPropertyDescriptor(object);
		//addPreviousDelegationPropertyDescriptor(object);
		//addNextDelegationsPropertyDescriptor(object);
		//addTargetPropertyDescriptor(object);
		//addNoRepudiationPropertyDescriptor(object);
		//addRedundancyPropertyDescriptor(object);
		//addTimesTransferablePropertyDescriptor(object);
		addPreConditionsPropertyDescriptor(object);
		addPostConditionsPropertyDescriptor(object);
		addSecurityNeedsPropertyDescriptor(object);
		if (object instanceof Delegation) {
			if (((Delegation) object).isAvailability()) addAvailabilityValuePropertyDescriptor(object);
		}
		if (object instanceof Delegation) {
			if (((Delegation) object).isTrustworthiness()) addTrustworthinessValuePropertyDescriptor(object);
		}
		//addShowSecurityNeedsPropertyDescriptor(object);
		//addRedundancyTypePropertyDescriptor(object);
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


	protected void addSourceGoalPropertyDescriptor(Object object){

		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Delegation_sourceGoal_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Delegation_sourceGoal_feature", "_UI_Delegation_type"), StstoolPackage.Literals.DELEGATION__SOURCE_GOAL, false, false, true, null, null, null) {

			@Override
			public Collection<?> getChoiceOfValues(Object object){

				//Collection<?> c = super.getChoiceOfValues(object);
				Delegation d = (Delegation) object;
				Actor a = d.getSource();

				Collection<Goal> result = new ArrayList<Goal>();
				for (Goal g : a.getGoals()) {
					if (g.canBeDelegated()) result.add(g);
				}
				result.add(null);
				return result;
			}

			@Override
			public boolean canSetProperty(Object object){

				// TODO Auto-generated method stub
				return super.canSetProperty(object);
			}



		});
	}



	/**
	 * This adds a property descriptor for the Source Goal feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSourceGoalPropertyDescriptorGen(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_sourceGoal_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_sourceGoal_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__SOURCE_GOAL,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Target Goal feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetGoalPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_targetGoal_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_targetGoal_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__TARGET_GOAL,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Previous Delegation feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPreviousDelegationPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_previousDelegation_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_previousDelegation_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__PREVIOUS_DELEGATION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Next Delegations feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNextDelegationsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_nextDelegations_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_nextDelegations_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__NEXT_DELEGATIONS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Target feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTargetPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_target_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_target_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__TARGET,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Times Transferable feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addTimesTransferablePropertyDescriptor(Object object){

		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Delegation_timesTransferable_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Delegation_timesTransferable_feature", "_UI_Delegation_type"), StstoolPackage.Literals.DELEGATION__TIMES_TRANSFERABLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null) {

			final static String	FALSE	= "false";
			final static String	TRUE	= "true";

			@Override
			public Collection<?> getChoiceOfValues(Object object){
				Collection c = new ArrayList<String>();
				//c=super.getChoiceOfValues(object);
				c.add(TRUE);
				c.add(FALSE);

				return c;
			}

			@Override
			public void setPropertyValue(Object object,Object value){
				if (((String) value).equals(TRUE))
					value = new Integer(-1);
				else
					value = new Integer(0);
				super.setPropertyValue(object, value);
			}

			@Override
			public Object getPropertyValue(Object object){
				Delegation d = (Delegation) object;
				int tt = d.getTimesTransferable();
				if (tt == 0)
					return FALSE;
				else
					return TRUE;
			}
		});
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
				 getString("_UI_Delegation_preConditions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_preConditions_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__PRE_CONDITIONS,
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
				 getString("_UI_Delegation_postConditions_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_postConditions_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__POST_CONDITIONS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Show Security Needs feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addShowSecurityNeedsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_showSecurityNeeds_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_showSecurityNeeds_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__SHOW_SECURITY_NEEDS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Redundancy Type feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRedundancyTypePropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_redundancyType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_redundancyType_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__REDUNDANCY_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Repudiation Type feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addRepudiationTypePropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_repudiationType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_repudiationType_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__REPUDIATION_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Security Needs feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSecurityNeedsPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_securityNeeds_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_securityNeeds_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__SECURITY_NEEDS,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Availability feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAvailabilityPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_availability_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_availability_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__AVAILABILITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Availability Value feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addAvailabilityValuePropertyDescriptor(Object object){
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), "Availability Level (in %)",//getString("_UI_Delegation_availabilityValue_feature"),
		getString("_UI_PropertyDescriptor_description", "_UI_Delegation_availabilityValue_feature", "_UI_Delegation_type"), StstoolPackage.Literals.DELEGATION__AVAILABILITY_VALUE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null) {

			@Override
			public void setPropertyValue(Object object,Object value){
				Integer i = (Integer) value;
				if (i < 0) i = 0;
				if (i > 100) i = 100;
				super.setPropertyValue(object, new Integer(i));

			}

		});
	}

	/**
	 * This adds a property descriptor for the Trustworthiness feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTrustworthinessPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Delegation_trustworthiness_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Delegation_trustworthiness_feature", "_UI_Delegation_type"),
				 StstoolPackage.Literals.DELEGATION__TRUSTWORTHINESS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Trustworthiness Value feature. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addTrustworthinessValuePropertyDescriptor(Object object){
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), "Minimun Trustworthiness Level",//getString("_UI_Delegation_trustworthinessValue_feature"),
		getString("_UI_PropertyDescriptor_description", "_UI_Delegation_trustworthinessValue_feature", "_UI_Delegation_type"), StstoolPackage.Literals.DELEGATION__TRUSTWORTHINESS_VALUE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
	}

	/**
	 * This returns Delegation.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object){
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Delegation"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object){
		String label = ((Delegation)object).getStsUniqueID();
		return label == null || label.length() == 0 ?
			getString("_UI_Delegation_type") :
			getString("_UI_Delegation_type") + " " + label;
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

		switch (notification.getFeatureID(Delegation.class)) {
			case StstoolPackage.DELEGATION__TIMES_TRANSFERABLE:
			case StstoolPackage.DELEGATION__PRE_CONDITIONS:
			case StstoolPackage.DELEGATION__POST_CONDITIONS:
			case StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS:
			case StstoolPackage.DELEGATION__REDUNDANCY_TYPE:
			case StstoolPackage.DELEGATION__REPUDIATION_TYPE:
			case StstoolPackage.DELEGATION__SECURITY_NEEDS:
			case StstoolPackage.DELEGATION__AVAILABILITY:
			case StstoolPackage.DELEGATION__AVAILABILITY_VALUE:
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS:
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}
