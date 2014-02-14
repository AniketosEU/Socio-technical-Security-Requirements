/*
* ActorImpl.java
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
import java.util.List;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Actor</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getIncomingDelegations <em>Incoming Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getOutgoingDelegations <em>Outgoing Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getGoals <em>Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getOutgoingProvisions <em>Outgoing Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getIncomingProvisions <em>Incoming Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getOutgoingAuthorisations <em>Outgoing Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getIncomingAuthorisations <em>Incoming Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getTResources <em>TResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ActorImpl#getIResources <em>IResources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends StsElementImpl implements Actor {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright	= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getThreatedElements() <em>Threated Elements</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThreatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Threat>				threatedElements;

	/**
	 * The cached value of the '{@link #getIncomingDelegations() <em>Incoming Delegations</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingDelegations()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>		incomingDelegations;

	/**
	 * The cached value of the '{@link #getOutgoingDelegations() <em>Outgoing Delegations</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingDelegations()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>		outgoingDelegations;

	/**
	 * The cached value of the '{@link #getGoals() <em>Goals</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGoals()
	 * @generated
	 * @ordered
	 */
	protected EList<Goal>				goals;

	/**
	 * The cached value of the '{@link #getOutgoingProvisions() <em>Outgoing Provisions</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingProvisions()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>			outgoingProvisions;

	/**
	 * The cached value of the '{@link #getIncomingProvisions() <em>Incoming Provisions</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingProvisions()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>			incomingProvisions;

	/**
	 * The cached value of the '{@link #getOutgoingAuthorisations() <em>Outgoing Authorisations</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingAuthorisations()
	 * @generated
	 * @ordered
	 */
	protected EList<Authorisation>	outgoingAuthorisations;

	/**
	 * The cached value of the '{@link #getIncomingAuthorisations() <em>Incoming Authorisations</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingAuthorisations()
	 * @generated
	 * @ordered
	 */
	protected EList<Authorisation>	incomingAuthorisations;

	/**
	 * The cached value of the '{@link #getTResources() <em>TResources</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTResources()
	 * @generated
	 * @ordered
	 */
	protected EList<TResource>			tResources;

	/**
	 * The cached value of the '{@link #getIResources() <em>IResources</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Own>					iResources;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ActorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.ACTOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Threat> getThreatedElements(){
		if (threatedElements == null) {
			threatedElements = new EObjectWithInverseResolvingEList<Threat>(Threat.class, this, StstoolPackage.ACTOR__THREATED_ELEMENTS, StstoolPackage.THREAT__TARGET);
		}
		return threatedElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getIncomingDelegations(){
		if (incomingDelegations == null) {
			incomingDelegations = new EObjectWithInverseResolvingEList<Delegation>(Delegation.class, this, StstoolPackage.ACTOR__INCOMING_DELEGATIONS, StstoolPackage.DELEGATION__TARGET);
		}
		return incomingDelegations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getOutgoingDelegations(){
		if (outgoingDelegations == null) {
			outgoingDelegations = new EObjectContainmentWithInverseEList<Delegation>(Delegation.class, this, StstoolPackage.ACTOR__OUTGOING_DELEGATIONS, StstoolPackage.DELEGATION__SOURCE);
		}
		return outgoingDelegations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goal> getGoals(){
		if (goals == null) {
			goals = new EObjectContainmentWithInverseEList<Goal>(Goal.class, this, StstoolPackage.ACTOR__GOALS, StstoolPackage.GOAL__ACTOR_OWNER);
		}
		return goals;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getOutgoingProvisions(){
		if (outgoingProvisions == null) {
			outgoingProvisions = new EObjectContainmentWithInverseEList<Provision>(Provision.class, this, StstoolPackage.ACTOR__OUTGOING_PROVISIONS, StstoolPackage.PROVISION__SOURCE);
		}
		return outgoingProvisions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getIncomingProvisions(){
		if (incomingProvisions == null) {
			incomingProvisions = new EObjectWithInverseResolvingEList<Provision>(Provision.class, this, StstoolPackage.ACTOR__INCOMING_PROVISIONS, StstoolPackage.PROVISION__TARGET);
		}
		return incomingProvisions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Authorisation> getOutgoingAuthorisations(){
		if (outgoingAuthorisations == null) {
			outgoingAuthorisations = new EObjectContainmentWithInverseEList<Authorisation>(Authorisation.class, this, StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS, StstoolPackage.AUTHORISATION__SOURCE);
		}
		return outgoingAuthorisations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Authorisation> getIncomingAuthorisations(){
		if (incomingAuthorisations == null) {
			incomingAuthorisations = new EObjectWithInverseResolvingEList<Authorisation>(Authorisation.class, this, StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS, StstoolPackage.AUTHORISATION__TARGET);
		}
		return incomingAuthorisations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Own> getIResources(){
		if (iResources == null) {
			iResources = new EObjectContainmentWithInverseEList<Own>(Own.class, this, StstoolPackage.ACTOR__IRESOURCES, StstoolPackage.OWN__SOURCE);
		}
		return iResources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TResource> getTResources(){
		if (tResources == null) {
			tResources = new EObjectContainmentWithInverseEList<TResource>(TResource.class, this, StstoolPackage.ACTOR__TRESOURCES, StstoolPackage.TRESOURCE__ACTOR_OWNER);
		}
		return tResources;
	}

	/**
	 * <!-- begin-user-doc --> Method to check if the actor is seafly deletable from the model <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDeletable(){

		boolean deletable = true;
		List incDel = getIncomingDelegations();
		List outDel = getOutgoingDelegations();
		List incProv = getIncomingProvisions();
		List outProv = getOutgoingProvisions();

		if (outDel.size() > 0 || outProv.size() > 0) return false;
		if (incDel.size() > 0) {
			for (int i = 0; deletable && i < incDel.size(); i++) {
				if (((Delegation) incDel.get(i)).getNextDelegations().size() > 0) deletable = false;
			}
		}
		if (incProv.size() > 0) {
			for (int i = 0; deletable && i < incProv.size(); i++) {
				if (((Provision) incProv.get(i)).getNextProvisions().size() > 0) deletable = false;
			}
		}

		return deletable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getThreatedElements()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingDelegations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingDelegations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__GOALS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGoals()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingProvisions()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingProvisions()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingAuthorisations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingAuthorisations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__TRESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTResources()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ACTOR__IRESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIResources()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				return ((InternalEList<?>)getThreatedElements()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				return ((InternalEList<?>)getIncomingDelegations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				return ((InternalEList<?>)getOutgoingDelegations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__GOALS:
				return ((InternalEList<?>)getGoals()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				return ((InternalEList<?>)getOutgoingProvisions()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				return ((InternalEList<?>)getIncomingProvisions()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				return ((InternalEList<?>)getOutgoingAuthorisations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				return ((InternalEList<?>)getIncomingAuthorisations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__TRESOURCES:
				return ((InternalEList<?>)getTResources()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ACTOR__IRESOURCES:
				return ((InternalEList<?>)getIResources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				return getThreatedElements();
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				return getIncomingDelegations();
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				return getOutgoingDelegations();
			case StstoolPackage.ACTOR__GOALS:
				return getGoals();
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				return getOutgoingProvisions();
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				return getIncomingProvisions();
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				return getOutgoingAuthorisations();
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				return getIncomingAuthorisations();
			case StstoolPackage.ACTOR__TRESOURCES:
				return getTResources();
			case StstoolPackage.ACTOR__IRESOURCES:
				return getIResources();
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
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				getThreatedElements().clear();
				getThreatedElements().addAll((Collection<? extends Threat>)newValue);
				return;
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				getIncomingDelegations().clear();
				getIncomingDelegations().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				getOutgoingDelegations().clear();
				getOutgoingDelegations().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.ACTOR__GOALS:
				getGoals().clear();
				getGoals().addAll((Collection<? extends Goal>)newValue);
				return;
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				getOutgoingProvisions().clear();
				getOutgoingProvisions().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				getIncomingProvisions().clear();
				getIncomingProvisions().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				getOutgoingAuthorisations().clear();
				getOutgoingAuthorisations().addAll((Collection<? extends Authorisation>)newValue);
				return;
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				getIncomingAuthorisations().clear();
				getIncomingAuthorisations().addAll((Collection<? extends Authorisation>)newValue);
				return;
			case StstoolPackage.ACTOR__TRESOURCES:
				getTResources().clear();
				getTResources().addAll((Collection<? extends TResource>)newValue);
				return;
			case StstoolPackage.ACTOR__IRESOURCES:
				getIResources().clear();
				getIResources().addAll((Collection<? extends Own>)newValue);
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
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				getThreatedElements().clear();
				return;
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				getIncomingDelegations().clear();
				return;
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				getOutgoingDelegations().clear();
				return;
			case StstoolPackage.ACTOR__GOALS:
				getGoals().clear();
				return;
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				getOutgoingProvisions().clear();
				return;
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				getIncomingProvisions().clear();
				return;
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				getOutgoingAuthorisations().clear();
				return;
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				getIncomingAuthorisations().clear();
				return;
			case StstoolPackage.ACTOR__TRESOURCES:
				getTResources().clear();
				return;
			case StstoolPackage.ACTOR__IRESOURCES:
				getIResources().clear();
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
			case StstoolPackage.ACTOR__THREATED_ELEMENTS:
				return threatedElements != null && !threatedElements.isEmpty();
			case StstoolPackage.ACTOR__INCOMING_DELEGATIONS:
				return incomingDelegations != null && !incomingDelegations.isEmpty();
			case StstoolPackage.ACTOR__OUTGOING_DELEGATIONS:
				return outgoingDelegations != null && !outgoingDelegations.isEmpty();
			case StstoolPackage.ACTOR__GOALS:
				return goals != null && !goals.isEmpty();
			case StstoolPackage.ACTOR__OUTGOING_PROVISIONS:
				return outgoingProvisions != null && !outgoingProvisions.isEmpty();
			case StstoolPackage.ACTOR__INCOMING_PROVISIONS:
				return incomingProvisions != null && !incomingProvisions.isEmpty();
			case StstoolPackage.ACTOR__OUTGOING_AUTHORISATIONS:
				return outgoingAuthorisations != null && !outgoingAuthorisations.isEmpty();
			case StstoolPackage.ACTOR__INCOMING_AUTHORISATIONS:
				return incomingAuthorisations != null && !incomingAuthorisations.isEmpty();
			case StstoolPackage.ACTOR__TRESOURCES:
				return tResources != null && !tResources.isEmpty();
			case StstoolPackage.ACTOR__IRESOURCES:
				return iResources != null && !iResources.isEmpty();
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
				case StstoolPackage.ACTOR__THREATED_ELEMENTS: return StstoolPackage.THREATABLE__THREATED_ELEMENTS;
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
				case StstoolPackage.THREATABLE__THREATED_ELEMENTS: return StstoolPackage.ACTOR__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ActorImpl
