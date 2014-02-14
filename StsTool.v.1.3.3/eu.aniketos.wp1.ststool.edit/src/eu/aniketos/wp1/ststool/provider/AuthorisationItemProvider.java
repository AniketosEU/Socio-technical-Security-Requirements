/*
* AuthorisationItemProvider.java
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
import org.eclipse.emf.ecore.EObject;
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
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.impl.GoalImpl;

/**
 * This is the item provider adapter for a {@link eu.aniketos.wp1.ststool.Authorisation} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * @generated
 */
public class AuthorisationItemProvider extends StsRelationItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

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
	public AuthorisationItemProvider(AdapterFactory adapterFactory) {
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

			//addTargetPropertyDescriptor(object);
			//addGoalsPropertyDescriptor(object);
			//addResourcesPropertyDescriptor(object);
			//addTimesTransferablePropertyDescriptor(object);
			//addUsagePropertyDescriptor(object);
			//addModificationPropertyDescriptor(object);
			//addProducePropertyDescriptor(object);
			//addDistributionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
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
				 getString("_UI_Authorisation_target_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_target_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__TARGET,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}



	protected void addGoalsPropertyDescriptor(Object object){

		ItemPropertyDescriptor ipd = new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Authorisation_goals_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_goals_feature", "_UI_Authorisation_type"), StstoolPackage.Literals.AUTHORISATION__GOALS, true, false, true, null, null, null) {

			/**
			 *Filter the goal list, returning only the goal that
			 * 
			 */
			@Override
			public Collection<?> getChoiceOfValues(Object object){

				Collection<GoalImpl> c = (Collection<GoalImpl>) super.getChoiceOfValues(object);
				Collection<GoalImpl> result = new ArrayList<GoalImpl>(c);
				for (GoalImpl g : c) {
					if (g.getDelegatedFrom().size() > 0) result.remove(g);
				}
				return result;
			}

			/**
			 *Return a goal name in the form ActorOwner:GoalName
			 * 
			 */
			@Override
			public IItemLabelProvider getLabelProvider(Object object){

				final IItemLabelProvider defaultLabelProvider = super.getLabelProvider(object);
				return new IItemLabelProvider() {

					@Override
					public String getText(Object object){

						if (object instanceof Goal) {
							Goal goal = (Goal) object;
							Actor parent = (Actor) goal.eContainer();
							String goalName = "Goal Without Name";
							if (goal.getName() != null) goalName = goal.getName();
							String actorName = "Actor Without Name";
							if (parent.getName() != null) actorName = parent.getName();

							return actorName + " : " + goalName;
						} else {
							return defaultLabelProvider.getText(object);
						}
					}

					@Override
					public Object getImage(Object object){

						return defaultLabelProvider.getImage(object);
					}
				};
			}
		};
		itemPropertyDescriptors.add(ipd);


	}

	/**
	 * This adds a property descriptor for the Goals feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGoalsPropertyDescriptorGen(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_goals_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_goals_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__GOALS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	protected void addResourcesPropertyDescriptor(Object object){

		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Authorisation_resources_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_resources_feature", "_UI_Authorisation_type"), StstoolPackage.Literals.AUTHORISATION__RESOURCES, true, false, true, null, null, null) {

			/*
			 * @Override
			 * public Collection<?> getChoiceOfValues(Object object) {
			 * //Collection<?> c = super.getChoiceOfValues(object);
			 * Authorisation auth=(Authorisation)object;
			 * Actor a=auth.getSource();
			 * Collection<TResource> result = new ArrayList<TResource>();
			 * for (TResource tr:a.getTResources()){
			 * result.add(tr);
			 * }
			 * result.add(null);
			 * return result;
			 * }
			 */

			@Override
			public IItemLabelProvider getLabelProvider(Object object){

				final IItemLabelProvider defaultLabelProvider = super.getLabelProvider(object);
				return new IItemLabelProvider() {

					/**
					 *Return a Iresource name in the form ActorOwner:IResourceName
					 * 
					 */
					@Override
					public String getText(Object object){

						if (object instanceof IResource) {
							IResource resource = (IResource) object;
							String resourceName = "IResource Without Name";
							if (resource.getName() != null) resourceName = resource.getName();

							return resourceName;
						} else {
							return defaultLabelProvider.getText(object);
						}
					}

					@Override
					public Object getImage(Object object){

						return defaultLabelProvider.getImage(object);
					}
				};
			}

			@Override
			protected Object createPropertyValueWrapper(Object object,Object propertyValue){

				// TODO Auto-generated method stub
				return super.createPropertyValueWrapper(object, propertyValue);
			}

			@Override
			protected Object getValue(EObject object,EStructuralFeature feature){

				// TODO Auto-generated method stub
				return super.getValue(object, feature);
			}
		});
	}

	/**
	 * This adds a property descriptor for the Resources feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addResourcesPropertyDescriptorGen(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_resources_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_resources_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__RESOURCES,
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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(), getString("_UI_Authorisation_timesTransferable_feature"), getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_timesTransferable_feature", "_UI_Authorisation_type"), StstoolPackage.Literals.AUTHORISATION__TIMES_TRANSFERABLE, true, false, false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null) {

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
				Authorisation a = (Authorisation) object;
				int tt = a.getTimesTransferable();
				if (tt == 0)
					return FALSE;
				else
					return TRUE;
			}
		});
	}

	/**
	 * This adds a property descriptor for the Usage feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addUsagePropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_usage_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_usage_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__USAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Modification feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addModificationPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_modification_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_modification_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__MODIFICATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Produce feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addProducePropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_produce_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_produce_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__PRODUCE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Distribution feature.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDistributionPropertyDescriptor(Object object){
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Authorisation_distribution_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Authorisation_distribution_feature", "_UI_Authorisation_type"),
				 StstoolPackage.Literals.AUTHORISATION__DISTRIBUTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns Authorisation.gif.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object){
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Authorisation"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object){
		String label = ((Authorisation)object).getStsUniqueID();
		return label == null || label.length() == 0 ?
			getString("_UI_Authorisation_type") :
			getString("_UI_Authorisation_type") + " " + label;
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

		switch (notification.getFeatureID(Authorisation.class)) {
			case StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE:
			case StstoolPackage.AUTHORISATION__USAGE:
			case StstoolPackage.AUTHORISATION__MODIFICATION:
			case StstoolPackage.AUTHORISATION__PRODUCE:
			case StstoolPackage.AUTHORISATION__DISTRIBUTION:
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
