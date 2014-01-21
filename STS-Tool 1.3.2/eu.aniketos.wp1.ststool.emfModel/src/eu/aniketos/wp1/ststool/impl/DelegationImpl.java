/*
* DelegationImpl.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.RedundancyType;
import eu.aniketos.wp1.ststool.RepudiationType;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Delegation</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getSourceGoal <em>Source Goal</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getTargetGoal <em>Target Goal</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getPreviousDelegation <em>Previous Delegation</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getNextDelegations <em>Next Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getTimesTransferable <em>Times Transferable</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#isShowSecurityNeeds <em>Show Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getRedundancyType <em>Redundancy Type</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getRepudiationType <em>Repudiation Type</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getSecurityNeeds <em>Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#isAvailability <em>Availability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getAvailabilityValue <em>Availability Value</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#isTrustworthiness <em>Trustworthiness</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.DelegationImpl#getTrustworthinessValue <em>Trustworthiness Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DelegationImpl extends StsRelationImpl implements Delegation {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String					copyright								= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getThreatedElements() <em>Threated Elements</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThreatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Threat>						threatedElements;

	/**
	 * The cached value of the '{@link #getSourceGoal() <em>Source Goal</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSourceGoal()
	 * @generated
	 * @ordered
	 */
	protected Goal									sourceGoal;

	/**
	 * The cached value of the '{@link #getTargetGoal() <em>Target Goal</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTargetGoal()
	 * @generated
	 * @ordered
	 */
	protected Goal									targetGoal;

	/**
	 * The cached value of the '{@link #getPreviousDelegation() <em>Previous Delegation</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreviousDelegation()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>				previousDelegation;

	/**
	 * The cached value of the '{@link #getNextDelegations() <em>Next Delegations</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNextDelegations()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>				nextDelegations;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Actor								target;

	/**
	 * The default value of the '{@link #getTimesTransferable() <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimesTransferable()
	 * @generated
	 * @ordered
	 */
	protected static final int					TIMES_TRANSFERABLE_EDEFAULT		= -1;

	/**
	 * The cached value of the '{@link #getTimesTransferable() <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTimesTransferable()
	 * @generated
	 * @ordered
	 */
	protected int									timesTransferable						= TIMES_TRANSFERABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String				PRE_CONDITIONS_EDEFAULT				= null;

	/**
	 * The cached value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected String								preConditions							= PRE_CONDITIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String				POST_CONDITIONS_EDEFAULT			= null;

	/**
	 * The cached value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected String								postConditions							= POST_CONDITIONS_EDEFAULT;


	/**
	 * The default value of the '{@link #isShowSecurityNeeds() <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isShowSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected static final boolean			SHOW_SECURITY_NEEDS_EDEFAULT		= false;

	/**
	 * The cached value of the '{@link #isShowSecurityNeeds() <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isShowSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected boolean								showSecurityNeeds						= SHOW_SECURITY_NEEDS_EDEFAULT;

	/**
	 * The default value of the '{@link #getRedundancyType() <em>Redundancy Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRedundancyType()
	 * @generated
	 * @ordered
	 */
	protected static final RedundancyType	REDUNDANCY_TYPE_EDEFAULT			= RedundancyType.NO_REDUNDANCY;

	/**
	 * The cached value of the '{@link #getRedundancyType() <em>Redundancy Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRedundancyType()
	 * @generated
	 * @ordered
	 */
	protected RedundancyType					redundancyType							= REDUNDANCY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepudiationType() <em>Repudiation Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRepudiationType()
	 * @generated
	 * @ordered
	 */
	protected static final RepudiationType	REPUDIATION_TYPE_EDEFAULT			= RepudiationType.NO_REPUDIATION;

	/**
	 * The cached value of the '{@link #getRepudiationType() <em>Repudiation Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRepudiationType()
	 * @generated
	 * @ordered
	 */
	protected RepudiationType					repudiationType						= REPUDIATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityNeeds() <em>Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected static final String				SECURITY_NEEDS_EDEFAULT				= "";

	/**
	 * The cached value of the '{@link #getSecurityNeeds() <em>Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected String								securityNeeds							= SECURITY_NEEDS_EDEFAULT;

	/**
	 * The default value of the '{@link #isAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAvailability()
	 * @generated
	 * @ordered
	 */
	protected static final boolean			AVAILABILITY_EDEFAULT				= false;

	/**
	 * The cached value of the '{@link #isAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAvailability()
	 * @generated
	 * @ordered
	 */
	protected boolean								availability							= AVAILABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailabilityValue() <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAvailabilityValue()
	 * @generated
	 * @ordered
	 */
	protected static final int					AVAILABILITY_VALUE_EDEFAULT		= 0;

	/**
	 * The cached value of the '{@link #getAvailabilityValue() <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAvailabilityValue()
	 * @generated
	 * @ordered
	 */
	protected int									availabilityValue						= AVAILABILITY_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isTrustworthiness() <em>Trustworthiness</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isTrustworthiness()
	 * @generated
	 * @ordered
	 */
	protected static final boolean			TRUSTWORTHINESS_EDEFAULT			= false;

	/**
	 * The cached value of the '{@link #isTrustworthiness() <em>Trustworthiness</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isTrustworthiness()
	 * @generated
	 * @ordered
	 */
	protected boolean								trustworthiness						= TRUSTWORTHINESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTrustworthinessValue() <em>Trustworthiness Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrustworthinessValue()
	 * @generated
	 * @ordered
	 */
	protected static final int					TRUSTWORTHINESS_VALUE_EDEFAULT	= 0;

	/**
	 * The cached value of the '{@link #getTrustworthinessValue() <em>Trustworthiness Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTrustworthinessValue()
	 * @generated
	 * @ordered
	 */
	protected int									trustworthinessValue					= TRUSTWORTHINESS_VALUE_EDEFAULT;

	private GoalAdapter							goalAapter								= new GoalAdapter(this);



	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected DelegationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.DELEGATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Threat> getThreatedElements(){
		if (threatedElements == null) {
			threatedElements = new EObjectWithInverseResolvingEList<Threat>(Threat.class, this, StstoolPackage.DELEGATION__THREATED_ELEMENTS, StstoolPackage.THREAT__TARGET);
		}
		return threatedElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Goal getSourceGoal(){
		if (sourceGoal != null && sourceGoal.eIsProxy()) {
			InternalEObject oldSourceGoal = (InternalEObject)sourceGoal;
			sourceGoal = (Goal)eResolveProxy(oldSourceGoal);
			if (sourceGoal != oldSourceGoal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.DELEGATION__SOURCE_GOAL, oldSourceGoal, sourceGoal));
			}
		}
		return sourceGoal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Goal basicGetSourceGoal(){
		return sourceGoal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSourceGoal(Goal newSourceGoal){

		Goal oldGoal = sourceGoal;

		if (newSourceGoal != oldGoal && newSourceGoal != getTargetGoal()) {

			sourceGoal = newSourceGoal;
			Goal sourceGoal = getSourceGoal();

			//removing reference in previous goal;
			if (oldGoal != null) {
				oldGoal.getDelegatedTo().remove(this);
				for (Delegation d : oldGoal.getDelegatedFrom()) {
					d.getNextDelegations().remove(this);
				}
				oldGoal.eAdapters().remove(goalAapter);
			}

			if (sourceGoal != null) {//Setting a new source goal
				sourceGoal.getDelegatedTo().add(this);
				sourceGoal.eAdapters().add(goalAapter);
				//getTargetGoal().setNameForced("@"+sourceGoal.getName());
				if (getTargetGoal() != null) getTargetGoal().setNameForced("" + sourceGoal.getName());


				for (Delegation d : sourceGoal.getDelegatedFrom()) {
					d.getNextDelegations().add(this);
					getPreviousDelegation().add(d);
				}
			} else {//Clearing source goal
				getPreviousDelegation().clear();

				//getTargetGoal().setNameForced("No Goal Reference");
			}

			if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__SOURCE_GOAL, oldGoal, sourceGoal));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceGoalGen(Goal newSourceGoal){
		Goal oldSourceGoal = sourceGoal;
		sourceGoal = newSourceGoal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__SOURCE_GOAL, oldSourceGoal, sourceGoal));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Goal getTargetGoal(){
		if (targetGoal != null && targetGoal.eIsProxy()) {
			InternalEObject oldTargetGoal = (InternalEObject)targetGoal;
			targetGoal = (Goal)eResolveProxy(oldTargetGoal);
			if (targetGoal != oldTargetGoal) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.DELEGATION__TARGET_GOAL, oldTargetGoal, targetGoal));
			}
		}
		return targetGoal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Goal basicGetTargetGoal(){
		return targetGoal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetGoal(Goal newTargetGoal,NotificationChain msgs){
		Goal oldTargetGoal = targetGoal;
		targetGoal = newTargetGoal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TARGET_GOAL, oldTargetGoal, newTargetGoal);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTargetGoal(Goal newTargetGoal){

		if (newTargetGoal != targetGoal) {
			if (newTargetGoal != null && getSourceGoal() != null) {
				newTargetGoal.setNameForced(getSourceGoal().getName());
			}
			NotificationChain msgs = null;
			if (targetGoal != null) msgs = ((InternalEObject) targetGoal).eInverseRemove(this, StstoolPackage.GOAL__DELEGATED_FROM, Goal.class, msgs);
			if (newTargetGoal != null) msgs = ((InternalEObject) newTargetGoal).eInverseAdd(this, StstoolPackage.GOAL__DELEGATED_FROM, Goal.class, msgs);
			msgs = basicSetTargetGoal(newTargetGoal, msgs);
			if (msgs != null) msgs.dispatch();
		} else if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TARGET_GOAL, newTargetGoal, newTargetGoal));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getPreviousDelegation(){
		if (previousDelegation == null) {
			previousDelegation = new EObjectResolvingEList<Delegation>(Delegation.class, this, StstoolPackage.DELEGATION__PREVIOUS_DELEGATION);
		}
		return previousDelegation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getNextDelegations(){
		if (nextDelegations == null) {
			nextDelegations = new EObjectResolvingEList<Delegation>(Delegation.class, this, StstoolPackage.DELEGATION__NEXT_DELEGATIONS);
		}
		return nextDelegations;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.DELEGATION__TARGET, oldTarget, target));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TARGET, oldTarget, newTarget);
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
				msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_DELEGATIONS, Actor.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, StstoolPackage.ACTOR__INCOMING_DELEGATIONS, Actor.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getSource(){
		if (eContainerFeatureID() != StstoolPackage.DELEGATION__SOURCE) return null;
		return (Actor)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Actor newSource,NotificationChain msgs){
		msgs = eBasicSetContainer((InternalEObject)newSource, StstoolPackage.DELEGATION__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Actor newSource){
		if (newSource != eInternalContainer() || (eContainerFeatureID() != StstoolPackage.DELEGATION__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, StstoolPackage.ACTOR__OUTGOING_DELEGATIONS, Actor.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> return the number of time that the delegation can be redelegated. <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int getTimesTransferable(){

		return timesTransferable;

		/*if (getPreviousDelegation() == null) {
			return timesTransferable;
		} else {
			return getPreviousDelegation().getTimesTransferable();
		}*/

		/*
		 * if (getPreviousDelegation()==null){
		 * return timesTransferable;
		 * }else{
		 * int timesdelegable=getPreviousDelegation().getTimesDelegable();
		 * if (timesdelegable==-1)return-1;
		 * return getPreviousDelegation().getTimesDelegable()-1;
		 * }
		 */
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTimesTransferable(int newTimesTransferable){

		if (newTimesTransferable != 0) newTimesTransferable = -1;
		/*
		 * if(newTimesDelegable<=-1){
		 * newTimesDelegable=-1;
		 * }else{
		 * int existentDelegation=0;
		 * Delegation d=this;
		 * while (d.getNextDelegation()!=null){
		 * ++existentDelegation;
		 * d=d.getNextDelegation();
		 * }
		 * if(newTimesDelegable<existentDelegation){//limit to existent delegation
		 * newTimesDelegable = existentDelegation;
		 * }
		 * }
		 */

		int oldTimesTransferable = timesTransferable;
		timesTransferable = newTimesTransferable;
		if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TIMES_TRANSFERABLE, oldTimesTransferable, timesTransferable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPreConditions(){
		return preConditions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreConditions(String newPreConditions){
		String oldPreConditions = preConditions;
		preConditions = newPreConditions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__PRE_CONDITIONS, oldPreConditions, preConditions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostConditions(){
		return postConditions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostConditions(String newPostConditions){
		String oldPostConditions = postConditions;
		postConditions = newPostConditions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__POST_CONDITIONS, oldPostConditions, postConditions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShowSecurityNeeds(){
		return showSecurityNeeds;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setShowSecurityNeeds(boolean newShowSecurityNeeds){
		boolean oldShowSecurityNeeds = showSecurityNeeds;
		showSecurityNeeds = newShowSecurityNeeds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS, oldShowSecurityNeeds, showSecurityNeeds));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RedundancyType getRedundancyType(){
		return redundancyType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedundancyType(RedundancyType newRedundancyType){
		RedundancyType oldRedundancyType = redundancyType;
		redundancyType = newRedundancyType == null ? REDUNDANCY_TYPE_EDEFAULT : newRedundancyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__REDUNDANCY_TYPE, oldRedundancyType, redundancyType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RepudiationType getRepudiationType(){
		return repudiationType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepudiationType(RepudiationType newRepudiationType){
		RepudiationType oldRepudiationType = repudiationType;
		repudiationType = newRepudiationType == null ? REPUDIATION_TYPE_EDEFAULT : newRepudiationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__REPUDIATION_TYPE, oldRepudiationType, repudiationType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getSecurityNeeds(){


		List<String> secNeed = new ArrayList<String>();

		if (getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
			switch (getRedundancyType()) {
				case FALLBACK_SINGLE:
					secNeed.add("Redundancy Fallback Single");
				break;
				case FALLBACK_MULTI:
					secNeed.add("Redundancy Fallback Multi");
				break;
				case TRUE_SINGLE:
					secNeed.add("Redundancy True Single");
				break;
				case TRUE_MULTI:
					secNeed.add("Redundancy True Multi");
				break;
			}
		}
		if (getRepudiationType() != RepudiationType.NO_REPUDIATION) {
			switch (getRepudiationType()) {
				case DELEGATEEE_REPUDIATION:
					secNeed.add("Repudiation Delegatee");
				break;
				case DELEGATOR_REPUDIATION:
					secNeed.add("Repudiation Delegator");
				break;
				case DUAL_REPUDIATION:
					secNeed.add("Repudiation Dual");
				break;
			}
		}
		if (getTimesTransferable() == 0) {
			secNeed.add("No Delegation");
		}
		if (isAvailability()) {
			secNeed.add("Availability " + getAvailabilityValue() + "%");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < secNeed.size(); i++) {
			if (i > 0) sb.append(", ");
			sb.append("\"" + secNeed.get(i) + "\"");
		}

		return sb.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAvailability(){
		return availability;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailability(boolean newAvailability){
		boolean oldAvailability = availability;
		availability = newAvailability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__AVAILABILITY, oldAvailability, availability));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getAvailabilityValue(){
		return availabilityValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailabilityValue(int newAvailabilityValue){
		int oldAvailabilityValue = availabilityValue;
		availabilityValue = newAvailabilityValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__AVAILABILITY_VALUE, oldAvailabilityValue, availabilityValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTrustworthiness(){
		return trustworthiness;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrustworthiness(boolean newTrustworthiness){
		boolean oldTrustworthiness = trustworthiness;
		trustworthiness = newTrustworthiness;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TRUSTWORTHINESS, oldTrustworthiness, trustworthiness));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getTrustworthinessValue(){
		return trustworthinessValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrustworthinessValue(int newTrustworthinessValue){
		int oldTrustworthinessValue = trustworthinessValue;
		trustworthinessValue = newTrustworthinessValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE, oldTrustworthinessValue, trustworthinessValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @see Actor#isDeletable() <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDeletable(){

		if (getNextDelegations().size() == 0) return true;
		return false;
	}

	/**
	 * <!-- begin-user-doc --> This method return true if the delegated goal can be redelegated. <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean canBeTransferred(){

		return true;

		/*
		 * int timedelegable=getTimesDelegable();
		 * if (timedelegable!=0)return true;
		 * return false
		 */
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getThreatedElements()).basicAdd(otherEnd, msgs);
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				if (targetGoal != null)
					msgs = ((InternalEObject)targetGoal).eInverseRemove(this, StstoolPackage.GOAL__DELEGATED_FROM, Goal.class, msgs);
				return basicSetTargetGoal((Goal)otherEnd, msgs);
			case StstoolPackage.DELEGATION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_DELEGATIONS, Actor.class, msgs);
				return basicSetTarget((Actor)otherEnd, msgs);
			case StstoolPackage.DELEGATION__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((Actor)otherEnd, msgs);
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
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				return ((InternalEList<?>)getThreatedElements()).basicRemove(otherEnd, msgs);
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				return basicSetTargetGoal(null, msgs);
			case StstoolPackage.DELEGATION__TARGET:
				return basicSetTarget(null, msgs);
			case StstoolPackage.DELEGATION__SOURCE:
				return basicSetSource(null, msgs);
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
			case StstoolPackage.DELEGATION__SOURCE:
				return eInternalContainer().eInverseRemove(this, StstoolPackage.ACTOR__OUTGOING_DELEGATIONS, Actor.class, msgs);
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
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				return getThreatedElements();
			case StstoolPackage.DELEGATION__SOURCE_GOAL:
				if (resolve) return getSourceGoal();
				return basicGetSourceGoal();
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				if (resolve) return getTargetGoal();
				return basicGetTargetGoal();
			case StstoolPackage.DELEGATION__PREVIOUS_DELEGATION:
				return getPreviousDelegation();
			case StstoolPackage.DELEGATION__NEXT_DELEGATIONS:
				return getNextDelegations();
			case StstoolPackage.DELEGATION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case StstoolPackage.DELEGATION__SOURCE:
				return getSource();
			case StstoolPackage.DELEGATION__TIMES_TRANSFERABLE:
				return getTimesTransferable();
			case StstoolPackage.DELEGATION__PRE_CONDITIONS:
				return getPreConditions();
			case StstoolPackage.DELEGATION__POST_CONDITIONS:
				return getPostConditions();
			case StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS:
				return isShowSecurityNeeds();
			case StstoolPackage.DELEGATION__REDUNDANCY_TYPE:
				return getRedundancyType();
			case StstoolPackage.DELEGATION__REPUDIATION_TYPE:
				return getRepudiationType();
			case StstoolPackage.DELEGATION__SECURITY_NEEDS:
				return getSecurityNeeds();
			case StstoolPackage.DELEGATION__AVAILABILITY:
				return isAvailability();
			case StstoolPackage.DELEGATION__AVAILABILITY_VALUE:
				return getAvailabilityValue();
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS:
				return isTrustworthiness();
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE:
				return getTrustworthinessValue();
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
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				getThreatedElements().clear();
				getThreatedElements().addAll((Collection<? extends Threat>)newValue);
				return;
			case StstoolPackage.DELEGATION__SOURCE_GOAL:
				setSourceGoal((Goal)newValue);
				return;
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				setTargetGoal((Goal)newValue);
				return;
			case StstoolPackage.DELEGATION__PREVIOUS_DELEGATION:
				getPreviousDelegation().clear();
				getPreviousDelegation().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.DELEGATION__NEXT_DELEGATIONS:
				getNextDelegations().clear();
				getNextDelegations().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.DELEGATION__TARGET:
				setTarget((Actor)newValue);
				return;
			case StstoolPackage.DELEGATION__SOURCE:
				setSource((Actor)newValue);
				return;
			case StstoolPackage.DELEGATION__TIMES_TRANSFERABLE:
				setTimesTransferable((Integer)newValue);
				return;
			case StstoolPackage.DELEGATION__PRE_CONDITIONS:
				setPreConditions((String)newValue);
				return;
			case StstoolPackage.DELEGATION__POST_CONDITIONS:
				setPostConditions((String)newValue);
				return;
			case StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS:
				setShowSecurityNeeds((Boolean)newValue);
				return;
			case StstoolPackage.DELEGATION__REDUNDANCY_TYPE:
				setRedundancyType((RedundancyType)newValue);
				return;
			case StstoolPackage.DELEGATION__REPUDIATION_TYPE:
				setRepudiationType((RepudiationType)newValue);
				return;
			case StstoolPackage.DELEGATION__AVAILABILITY:
				setAvailability((Boolean)newValue);
				return;
			case StstoolPackage.DELEGATION__AVAILABILITY_VALUE:
				setAvailabilityValue((Integer)newValue);
				return;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS:
				setTrustworthiness((Boolean)newValue);
				return;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE:
				setTrustworthinessValue((Integer)newValue);
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
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				getThreatedElements().clear();
				return;
			case StstoolPackage.DELEGATION__SOURCE_GOAL:
				setSourceGoal((Goal)null);
				return;
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				setTargetGoal((Goal)null);
				return;
			case StstoolPackage.DELEGATION__PREVIOUS_DELEGATION:
				getPreviousDelegation().clear();
				return;
			case StstoolPackage.DELEGATION__NEXT_DELEGATIONS:
				getNextDelegations().clear();
				return;
			case StstoolPackage.DELEGATION__TARGET:
				setTarget((Actor)null);
				return;
			case StstoolPackage.DELEGATION__SOURCE:
				setSource((Actor)null);
				return;
			case StstoolPackage.DELEGATION__TIMES_TRANSFERABLE:
				setTimesTransferable(TIMES_TRANSFERABLE_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__PRE_CONDITIONS:
				setPreConditions(PRE_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__POST_CONDITIONS:
				setPostConditions(POST_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS:
				setShowSecurityNeeds(SHOW_SECURITY_NEEDS_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__REDUNDANCY_TYPE:
				setRedundancyType(REDUNDANCY_TYPE_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__REPUDIATION_TYPE:
				setRepudiationType(REPUDIATION_TYPE_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__AVAILABILITY:
				setAvailability(AVAILABILITY_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__AVAILABILITY_VALUE:
				setAvailabilityValue(AVAILABILITY_VALUE_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS:
				setTrustworthiness(TRUSTWORTHINESS_EDEFAULT);
				return;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE:
				setTrustworthinessValue(TRUSTWORTHINESS_VALUE_EDEFAULT);
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
			case StstoolPackage.DELEGATION__THREATED_ELEMENTS:
				return threatedElements != null && !threatedElements.isEmpty();
			case StstoolPackage.DELEGATION__SOURCE_GOAL:
				return sourceGoal != null;
			case StstoolPackage.DELEGATION__TARGET_GOAL:
				return targetGoal != null;
			case StstoolPackage.DELEGATION__PREVIOUS_DELEGATION:
				return previousDelegation != null && !previousDelegation.isEmpty();
			case StstoolPackage.DELEGATION__NEXT_DELEGATIONS:
				return nextDelegations != null && !nextDelegations.isEmpty();
			case StstoolPackage.DELEGATION__TARGET:
				return target != null;
			case StstoolPackage.DELEGATION__SOURCE:
				return getSource() != null;
			case StstoolPackage.DELEGATION__TIMES_TRANSFERABLE:
				return timesTransferable != TIMES_TRANSFERABLE_EDEFAULT;
			case StstoolPackage.DELEGATION__PRE_CONDITIONS:
				return PRE_CONDITIONS_EDEFAULT == null ? preConditions != null : !PRE_CONDITIONS_EDEFAULT.equals(preConditions);
			case StstoolPackage.DELEGATION__POST_CONDITIONS:
				return POST_CONDITIONS_EDEFAULT == null ? postConditions != null : !POST_CONDITIONS_EDEFAULT.equals(postConditions);
			case StstoolPackage.DELEGATION__SHOW_SECURITY_NEEDS:
				return showSecurityNeeds != SHOW_SECURITY_NEEDS_EDEFAULT;
			case StstoolPackage.DELEGATION__REDUNDANCY_TYPE:
				return redundancyType != REDUNDANCY_TYPE_EDEFAULT;
			case StstoolPackage.DELEGATION__REPUDIATION_TYPE:
				return repudiationType != REPUDIATION_TYPE_EDEFAULT;
			case StstoolPackage.DELEGATION__SECURITY_NEEDS:
				return SECURITY_NEEDS_EDEFAULT == null ? securityNeeds != null : !SECURITY_NEEDS_EDEFAULT.equals(securityNeeds);
			case StstoolPackage.DELEGATION__AVAILABILITY:
				return availability != AVAILABILITY_EDEFAULT;
			case StstoolPackage.DELEGATION__AVAILABILITY_VALUE:
				return availabilityValue != AVAILABILITY_VALUE_EDEFAULT;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS:
				return trustworthiness != TRUSTWORTHINESS_EDEFAULT;
			case StstoolPackage.DELEGATION__TRUSTWORTHINESS_VALUE:
				return trustworthinessValue != TRUSTWORTHINESS_VALUE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,Class<?> baseClass){
		if (baseClass == Threatable.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.DELEGATION__THREATED_ELEMENTS: return StstoolPackage.THREATABLE__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID,Class<?> baseClass){
		if (baseClass == Threatable.class) {
			switch (baseFeatureID) {
				case StstoolPackage.THREATABLE__THREATED_ELEMENTS: return StstoolPackage.DELEGATION__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", preConditions: ");
		result.append(preConditions);
		result.append(", postConditions: ");
		result.append(postConditions);
		result.append(", showSecurityNeeds: ");
		result.append(showSecurityNeeds);
		result.append(", redundancyType: ");
		result.append(redundancyType);
		result.append(", repudiationType: ");
		result.append(repudiationType);
		result.append(", securityNeeds: ");
		result.append(securityNeeds);
		result.append(", availability: ");
		result.append(availability);
		result.append(", availabilityValue: ");
		result.append(availabilityValue);
		result.append(", trustworthiness: ");
		result.append(trustworthiness);
		result.append(", trustworthinessValue: ");
		result.append(trustworthinessValue);
		result.append(')');
		return result.toString();
	}


	class GoalAdapter extends AdapterImpl {

		Delegation	d;

		public GoalAdapter(Delegation d) {

			this.d = d;
		}

		@Override
		public void notifyChanged(Notification msg){

			Goal obj = (Goal) msg.getNotifier();
			if (msg.getEventType() == Notification.SET && msg.getFeatureID(Goal.class) == StstoolPackage.GOAL__NAME) {
				d.getTargetGoal().setNameForced("" + obj.getName());
				try {
					if (eNotificationRequired()) eNotify(new ENotificationImpl((InternalEObject) d, Notification.SET, StstoolPackage.DELEGATION__NAME, msg.getOldStringValue(), msg.getNewStringValue()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

} //DelegationImpl
