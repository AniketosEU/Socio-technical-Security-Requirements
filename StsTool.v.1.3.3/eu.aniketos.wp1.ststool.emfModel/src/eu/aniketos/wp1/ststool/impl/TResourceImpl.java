/*
* TResourceImpl.java
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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>TResource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getProvidedTo <em>Provided To</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getProvidedFrom <em>Provided From</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getActorOwner <em>Actor Owner</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getIntangibleElements <em>Intangible Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getGoalsNeeding <em>Goals Needing</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getGoalsProducing <em>Goals Producing</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.TResourceImpl#getGoalsModifing <em>Goals Modifing</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TResourceImpl extends ResourceImpl implements TResource {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String		copyright	= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getThreatedElements() <em>Threated Elements</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThreatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Threat>			threatedElements;

	/**
	 * The cached value of the '{@link #getProvidedTo() <em>Provided To</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProvidedTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>		providedTo;

	/**
	 * The cached value of the '{@link #getProvidedFrom() <em>Provided From</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProvidedFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>		providedFrom;

	/**
	 * The cached value of the '{@link #getIntangibleElements() <em>Intangible Elements</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIntangibleElements()
	 * @generated
	 * @ordered
	 */
	protected EList<TangibleBy>	intangibleElements;

	/**
	 * The cached value of the '{@link #getGoalsNeeding() <em>Goals Needing</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGoalsNeeding()
	 * @generated
	 * @ordered
	 */
	protected EList<Need>			goalsNeeding;

	/**
	 * The cached value of the '{@link #getGoalsProducing() <em>Goals Producing</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGoalsProducing()
	 * @generated
	 * @ordered
	 */
	protected EList<Produce>		goalsProducing;

	/**
	 * The cached value of the '{@link #getGoalsModifing() <em>Goals Modifing</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGoalsModifing()
	 * @generated
	 * @ordered
	 */
	protected EList<Modify>			goalsModifing;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.TRESOURCE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Threat> getThreatedElements(){
		if (threatedElements == null) {
			threatedElements = new EObjectWithInverseResolvingEList<Threat>(Threat.class, this, StstoolPackage.TRESOURCE__THREATED_ELEMENTS, StstoolPackage.THREAT__TARGET);
		}
		return threatedElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public void setName(String newName){

		if (getProvidedFrom().size() > 0) return;
		super.setName(newName);
	}

	public void setNameForced(String newName){

		super.setNameForced(newName, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getProvidedTo(){
		if (providedTo == null) {
			providedTo = new EObjectResolvingEList<Provision>(Provision.class, this, StstoolPackage.TRESOURCE__PROVIDED_TO);
		}
		return providedTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getProvidedFrom(){
		if (providedFrom == null) {
			providedFrom = new EObjectWithInverseResolvingEList<Provision>(Provision.class, this, StstoolPackage.TRESOURCE__PROVIDED_FROM, StstoolPackage.PROVISION__TARGET_RESOURCE);
		}
		return providedFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getActorOwner(){
		if (eContainerFeatureID() != StstoolPackage.TRESOURCE__ACTOR_OWNER) return null;
		return (Actor)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActorOwner(Actor newActorOwner,NotificationChain msgs){
		msgs = eBasicSetContainer((InternalEObject)newActorOwner, StstoolPackage.TRESOURCE__ACTOR_OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setActorOwner(Actor newActorOwner){
		if (newActorOwner != eInternalContainer() || (eContainerFeatureID() != StstoolPackage.TRESOURCE__ACTOR_OWNER && newActorOwner != null)) {
			if (EcoreUtil.isAncestor(this, newActorOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newActorOwner != null)
				msgs = ((InternalEObject)newActorOwner).eInverseAdd(this, StstoolPackage.ACTOR__TRESOURCES, Actor.class, msgs);
			msgs = basicSetActorOwner(newActorOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.TRESOURCE__ACTOR_OWNER, newActorOwner, newActorOwner));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Modify> getGoalsModifing(){
		if (goalsModifing == null) {
			goalsModifing = new EObjectWithInverseResolvingEList<Modify>(Modify.class, this, StstoolPackage.TRESOURCE__GOALS_MODIFING, StstoolPackage.MODIFY__TARGET);
		}
		return goalsModifing;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Produce> getGoalsProducing(){
		if (goalsProducing == null) {
			goalsProducing = new EObjectWithInverseResolvingEList<Produce>(Produce.class, this, StstoolPackage.TRESOURCE__GOALS_PRODUCING, StstoolPackage.PRODUCE__TARGET);
		}
		return goalsProducing;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Need> getGoalsNeeding(){
		if (goalsNeeding == null) {
			goalsNeeding = new EObjectWithInverseResolvingEList<Need>(Need.class, this, StstoolPackage.TRESOURCE__GOALS_NEEDING, StstoolPackage.NEED__TARGET);
		}
		return goalsNeeding;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TangibleBy> getIntangibleElements(){
		if (intangibleElements == null) {
			intangibleElements = new EObjectWithInverseResolvingEList<TangibleBy>(TangibleBy.class, this, StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS, StstoolPackage.TANGIBLE_BY__TARGET);
		}
		return intangibleElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDeletable(){

		if (getProvidedFrom().size() == 0 && getProvidedTo().size() == 0) return true;
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean canBeProvided(){

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getThreatedElements()).basicAdd(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProvidedFrom()).basicAdd(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActorOwner((Actor)otherEnd, msgs);
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIntangibleElements()).basicAdd(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGoalsNeeding()).basicAdd(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGoalsProducing()).basicAdd(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGoalsModifing()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				return ((InternalEList<?>)getThreatedElements()).basicRemove(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				return ((InternalEList<?>)getProvidedFrom()).basicRemove(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				return basicSetActorOwner(null, msgs);
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				return ((InternalEList<?>)getIntangibleElements()).basicRemove(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				return ((InternalEList<?>)getGoalsNeeding()).basicRemove(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				return ((InternalEList<?>)getGoalsProducing()).basicRemove(otherEnd, msgs);
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				return ((InternalEList<?>)getGoalsModifing()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				return eInternalContainer().eInverseRemove(this, StstoolPackage.ACTOR__TRESOURCES, Actor.class, msgs);
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
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				return getThreatedElements();
			case StstoolPackage.TRESOURCE__PROVIDED_TO:
				return getProvidedTo();
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				return getProvidedFrom();
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				return getActorOwner();
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				return getIntangibleElements();
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				return getGoalsNeeding();
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				return getGoalsProducing();
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				return getGoalsModifing();
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
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				getThreatedElements().clear();
				getThreatedElements().addAll((Collection<? extends Threat>)newValue);
				return;
			case StstoolPackage.TRESOURCE__PROVIDED_TO:
				getProvidedTo().clear();
				getProvidedTo().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				getProvidedFrom().clear();
				getProvidedFrom().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				setActorOwner((Actor)newValue);
				return;
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				getIntangibleElements().clear();
				getIntangibleElements().addAll((Collection<? extends TangibleBy>)newValue);
				return;
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				getGoalsNeeding().clear();
				getGoalsNeeding().addAll((Collection<? extends Need>)newValue);
				return;
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				getGoalsProducing().clear();
				getGoalsProducing().addAll((Collection<? extends Produce>)newValue);
				return;
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				getGoalsModifing().clear();
				getGoalsModifing().addAll((Collection<? extends Modify>)newValue);
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
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				getThreatedElements().clear();
				return;
			case StstoolPackage.TRESOURCE__PROVIDED_TO:
				getProvidedTo().clear();
				return;
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				getProvidedFrom().clear();
				return;
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				setActorOwner((Actor)null);
				return;
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				getIntangibleElements().clear();
				return;
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				getGoalsNeeding().clear();
				return;
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				getGoalsProducing().clear();
				return;
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				getGoalsModifing().clear();
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
			case StstoolPackage.TRESOURCE__THREATED_ELEMENTS:
				return threatedElements != null && !threatedElements.isEmpty();
			case StstoolPackage.TRESOURCE__PROVIDED_TO:
				return providedTo != null && !providedTo.isEmpty();
			case StstoolPackage.TRESOURCE__PROVIDED_FROM:
				return providedFrom != null && !providedFrom.isEmpty();
			case StstoolPackage.TRESOURCE__ACTOR_OWNER:
				return getActorOwner() != null;
			case StstoolPackage.TRESOURCE__INTANGIBLE_ELEMENTS:
				return intangibleElements != null && !intangibleElements.isEmpty();
			case StstoolPackage.TRESOURCE__GOALS_NEEDING:
				return goalsNeeding != null && !goalsNeeding.isEmpty();
			case StstoolPackage.TRESOURCE__GOALS_PRODUCING:
				return goalsProducing != null && !goalsProducing.isEmpty();
			case StstoolPackage.TRESOURCE__GOALS_MODIFING:
				return goalsModifing != null && !goalsModifing.isEmpty();
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
				case StstoolPackage.TRESOURCE__THREATED_ELEMENTS: return StstoolPackage.THREATABLE__THREATED_ELEMENTS;
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
				case StstoolPackage.THREATABLE__THREATED_ELEMENTS: return StstoolPackage.TRESOURCE__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TResourceImpl
