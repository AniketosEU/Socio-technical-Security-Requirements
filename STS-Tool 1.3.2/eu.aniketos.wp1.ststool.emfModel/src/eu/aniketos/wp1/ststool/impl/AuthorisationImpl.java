/*
* AuthorisationImpl.java
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
package eu.aniketos.wp1.ststool.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Authorisation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#getGoals <em>Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#getTimesTransferable <em>Times Transferable</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#isUsage <em>Usage</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#isModification <em>Modification</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#isProduce <em>Produce</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl#isDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AuthorisationImpl extends StsRelationImpl implements Authorisation {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright							= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Actor						target;

	/**
	 * The cached value of the '{@link #getGoals() <em>Goals</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGoals()
	 * @generated
	 * @ordered
	 */
	protected EList<Goal>				goals;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<IResource>			resources;

	/**
	 * The default value of the '{@link #getTimesTransferable() <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimesTransferable()
	 * @generated
	 * @ordered
	 */
	protected static final int			TIMES_TRANSFERABLE_EDEFAULT	= -1;

	/**
	 * The cached value of the '{@link #getTimesTransferable() <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimesTransferable()
	 * @generated
	 * @ordered
	 */
	protected int							timesTransferable					= TIMES_TRANSFERABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUsage()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	USAGE_EDEFAULT						= false;

	/**
	 * The cached value of the '{@link #isUsage() <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUsage()
	 * @generated
	 * @ordered
	 */
	protected boolean						usage									= USAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #isModification() <em>Modification</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isModification()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	MODIFICATION_EDEFAULT			= false;

	/**
	 * The cached value of the '{@link #isModification() <em>Modification</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isModification()
	 * @generated
	 * @ordered
	 */
	protected boolean						modification						= MODIFICATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isProduce() <em>Produce</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isProduce()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	PRODUCE_EDEFAULT					= false;

	/**
	 * The cached value of the '{@link #isProduce() <em>Produce</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isProduce()
	 * @generated
	 * @ordered
	 */
	protected boolean						produce								= PRODUCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDistribution() <em>Distribution</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isDistribution()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	DISTRIBUTION_EDEFAULT			= false;

	/**
	 * The cached value of the '{@link #isDistribution() <em>Distribution</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isDistribution()
	 * @generated
	 * @ordered
	 */
	protected boolean						distribution						= DISTRIBUTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AuthorisationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.AUTHORISATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getSource(){
		if (eContainerFeatureID() != StstoolPackage.AUTHORISATION__SOURCE) return null;
		return (Actor)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Actor newSource,NotificationChain msgs){
		msgs = eBasicSetContainer((InternalEObject)newSource, StstoolPackage.AUTHORISATION__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Actor newSource){
		if (newSource != eInternalContainer() || (eContainerFeatureID() != StstoolPackage.AUTHORISATION__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS, Actor.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getTarget(){
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Actor)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.AUTHORISATION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetTarget(){
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Actor newTarget,NotificationChain msgs){
		Actor oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Actor newTarget){
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS, Actor.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS, Actor.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goal> getGoals(){
		if (goals == null) {
			goals = new EObjectWithInverseResolvingEList.ManyInverse<Goal>(Goal.class, this, StstoolPackage.AUTHORISATION__GOALS, StstoolPackage.GOAL__AUTHORISATIONS);
		}
		return goals;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IResource> getResources(){
		if (resources == null) {
			resources = new EObjectWithInverseResolvingEList.ManyInverse<IResource>(IResource.class, this, StstoolPackage.AUTHORISATION__RESOURCES, StstoolPackage.IRESOURCE__AUTHORISATIONS);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getTimesTransferable(){
		return timesTransferable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimesTransferable(int newTimesTransferable){
		int oldTimesTransferable = timesTransferable;
		timesTransferable = newTimesTransferable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE, oldTimesTransferable, timesTransferable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUsage(){
		return usage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsage(boolean newUsage){
		boolean oldUsage = usage;
		usage = newUsage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__USAGE, oldUsage, usage));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isModification(){
		return modification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setModification(boolean newModification){
		boolean oldModification = modification;
		modification = newModification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__MODIFICATION, oldModification, modification));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProduce(){
		return produce;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProduce(boolean newProduce){
		boolean oldProduce = produce;
		produce = newProduce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__PRODUCE, oldProduce, produce));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDistribution(){
		return distribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setDistribution(boolean newDistribution){
		boolean oldDistribution = distribution;
		distribution = newDistribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AUTHORISATION__DISTRIBUTION, oldDistribution, distribution));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((Actor)otherEnd, msgs);
			case StstoolPackage.AUTHORISATION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS, Actor.class, msgs);
				return basicSetTarget((Actor)otherEnd, msgs);
			case StstoolPackage.AUTHORISATION__GOALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGoals()).basicAdd(otherEnd, msgs);
			case StstoolPackage.AUTHORISATION__RESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				return basicSetSource(null, msgs);
			case StstoolPackage.AUTHORISATION__TARGET:
				return basicSetTarget(null, msgs);
			case StstoolPackage.AUTHORISATION__GOALS:
				return ((InternalEList<?>)getGoals()).basicRemove(otherEnd, msgs);
			case StstoolPackage.AUTHORISATION__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs){
		switch (eContainerFeatureID()) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				return eInternalContainer().eInverseRemove(this, StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS, Actor.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				return getSource();
			case StstoolPackage.AUTHORISATION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case StstoolPackage.AUTHORISATION__GOALS:
				return getGoals();
			case StstoolPackage.AUTHORISATION__RESOURCES:
				return getResources();
			case StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE:
				return getTimesTransferable();
			case StstoolPackage.AUTHORISATION__USAGE:
				return isUsage();
			case StstoolPackage.AUTHORISATION__MODIFICATION:
				return isModification();
			case StstoolPackage.AUTHORISATION__PRODUCE:
				return isProduce();
			case StstoolPackage.AUTHORISATION__DISTRIBUTION:
				return isDistribution();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID,Object newValue){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				setSource((Actor)newValue);
				return;
			case StstoolPackage.AUTHORISATION__TARGET:
				setTarget((Actor)newValue);
				return;
			case StstoolPackage.AUTHORISATION__GOALS:
				getGoals().clear();
				getGoals().addAll((Collection<? extends Goal>)newValue);
				return;
			case StstoolPackage.AUTHORISATION__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends IResource>)newValue);
				return;
			case StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE:
				setTimesTransferable((Integer)newValue);
				return;
			case StstoolPackage.AUTHORISATION__USAGE:
				setUsage((Boolean)newValue);
				return;
			case StstoolPackage.AUTHORISATION__MODIFICATION:
				setModification((Boolean)newValue);
				return;
			case StstoolPackage.AUTHORISATION__PRODUCE:
				setProduce((Boolean)newValue);
				return;
			case StstoolPackage.AUTHORISATION__DISTRIBUTION:
				setDistribution((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				setSource((Actor)null);
				return;
			case StstoolPackage.AUTHORISATION__TARGET:
				setTarget((Actor)null);
				return;
			case StstoolPackage.AUTHORISATION__GOALS:
				getGoals().clear();
				return;
			case StstoolPackage.AUTHORISATION__RESOURCES:
				getResources().clear();
				return;
			case StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE:
				setTimesTransferable(TIMES_TRANSFERABLE_EDEFAULT);
				return;
			case StstoolPackage.AUTHORISATION__USAGE:
				setUsage(USAGE_EDEFAULT);
				return;
			case StstoolPackage.AUTHORISATION__MODIFICATION:
				setModification(MODIFICATION_EDEFAULT);
				return;
			case StstoolPackage.AUTHORISATION__PRODUCE:
				setProduce(PRODUCE_EDEFAULT);
				return;
			case StstoolPackage.AUTHORISATION__DISTRIBUTION:
				setDistribution(DISTRIBUTION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID){
		switch (featureID) {
			case StstoolPackage.AUTHORISATION__SOURCE:
				return getSource() != null;
			case StstoolPackage.AUTHORISATION__TARGET:
				return target != null;
			case StstoolPackage.AUTHORISATION__GOALS:
				return goals != null && !goals.isEmpty();
			case StstoolPackage.AUTHORISATION__RESOURCES:
				return resources != null && !resources.isEmpty();
			case StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE:
				return timesTransferable != TIMES_TRANSFERABLE_EDEFAULT;
			case StstoolPackage.AUTHORISATION__USAGE:
				return usage != USAGE_EDEFAULT;
			case StstoolPackage.AUTHORISATION__MODIFICATION:
				return modification != MODIFICATION_EDEFAULT;
			case StstoolPackage.AUTHORISATION__PRODUCE:
				return produce != PRODUCE_EDEFAULT;
			case StstoolPackage.AUTHORISATION__DISTRIBUTION:
				return distribution != DISTRIBUTION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (timesTransferable: ");
		result.append(timesTransferable);
		result.append(", usage: ");
		result.append(usage);
		result.append(", modification: ");
		result.append(modification);
		result.append(", produce: ");
		result.append(produce);
		result.append(", distribution: ");
		result.append(distribution);
		result.append(')');
		return result.toString();
	}

} //AuthorisationImpl
