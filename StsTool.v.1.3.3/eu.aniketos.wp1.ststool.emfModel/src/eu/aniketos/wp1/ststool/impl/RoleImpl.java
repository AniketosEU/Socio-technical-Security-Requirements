/*
* RoleImpl.java
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Dependency;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getIncompatibleDutiesOut <em>Incompatible Duties Out</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getIncompatibleDutiesIn <em>Incompatible Duties In</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getCompatibleDutiesOut <em>Compatible Duties Out</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getCompatibleDutiesIn <em>Compatible Duties In</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getPlayedBy <em>Played By</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getPurpose <em>Purpose</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getMission <em>Mission</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getDependBy <em>Depend By</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.RoleImpl#getDependent <em>Dependent</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends ActorImpl implements Role {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String				copyright			= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getIncompatibleDutiesOut() <em>Incompatible Duties Out</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncompatibleDutiesOut()
	 * @generated
	 * @ordered
	 */
	protected EList<IncompatibleDuties>	incompatibleDutiesOut;

	/**
	 * The cached value of the '{@link #getIncompatibleDutiesIn() <em>Incompatible Duties In</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncompatibleDutiesIn()
	 * @generated
	 * @ordered
	 */
	protected EList<IncompatibleDuties>	incompatibleDutiesIn;

	/**
	 * The cached value of the '{@link #getCompatibleDutiesOut() <em>Compatible Duties Out</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCompatibleDutiesOut()
	 * @generated
	 * @ordered
	 */
	protected EList<CompatibleDuties>	compatibleDutiesOut;

	/**
	 * The cached value of the '{@link #getCompatibleDutiesIn() <em>Compatible Duties In</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCompatibleDutiesIn()
	 * @generated
	 * @ordered
	 */
	protected EList<CompatibleDuties>	compatibleDutiesIn;

	/**
	 * The cached value of the '{@link #getPlayedBy() <em>Played By</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPlayedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Play>					playedBy;

	/**
	 * The default value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected static final String			PURPOSE_EDEFAULT	= null;

	/**
	 * The cached value of the '{@link #getPurpose() <em>Purpose</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPurpose()
	 * @generated
	 * @ordered
	 */
	protected String							purpose				= PURPOSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMission() <em>Mission</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMission()
	 * @generated
	 * @ordered
	 */
	protected static final String			MISSION_EDEFAULT	= null;

	/**
	 * The cached value of the '{@link #getMission() <em>Mission</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getMission()
	 * @generated
	 * @ordered
	 */
	protected String							mission				= MISSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDependBy() <em>Depend By</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> dependBy;

	/**
	 * The cached value of the '{@link #getDependent() <em>Dependent</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependent()
	 * @generated
	 * @ordered
	 */
	protected EList<Dependency> dependent;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IncompatibleDuties> getIncompatibleDutiesOut(){
		if (incompatibleDutiesOut == null) {
			incompatibleDutiesOut = new EObjectContainmentWithInverseEList<IncompatibleDuties>(IncompatibleDuties.class, this, StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT, StstoolPackage.INCOMPATIBLE_DUTIES__SOURCE);
		}
		return incompatibleDutiesOut;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IncompatibleDuties> getIncompatibleDutiesIn(){
		if (incompatibleDutiesIn == null) {
			incompatibleDutiesIn = new EObjectWithInverseResolvingEList<IncompatibleDuties>(IncompatibleDuties.class, this, StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN, StstoolPackage.INCOMPATIBLE_DUTIES__TARGET);
		}
		return incompatibleDutiesIn;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompatibleDuties> getCompatibleDutiesOut(){
		if (compatibleDutiesOut == null) {
			compatibleDutiesOut = new EObjectContainmentWithInverseEList<CompatibleDuties>(CompatibleDuties.class, this, StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT, StstoolPackage.COMPATIBLE_DUTIES__SOURCE);
		}
		return compatibleDutiesOut;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompatibleDuties> getCompatibleDutiesIn(){
		if (compatibleDutiesIn == null) {
			compatibleDutiesIn = new EObjectWithInverseResolvingEList<CompatibleDuties>(CompatibleDuties.class, this, StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN, StstoolPackage.COMPATIBLE_DUTIES__TARGET);
		}
		return compatibleDutiesIn;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Play> getPlayedBy(){
		if (playedBy == null) {
			playedBy = new EObjectWithInverseResolvingEList<Play>(Play.class, this, StstoolPackage.ROLE__PLAYED_BY, StstoolPackage.PLAY__TARGET);
		}
		return playedBy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPurpose(){
		return purpose;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPurpose(String newPurpose){
		String oldPurpose = purpose;
		purpose = newPurpose;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.ROLE__PURPOSE, oldPurpose, purpose));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getMission(){
		return mission;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMission(String newMission){
		String oldMission = mission;
		mission = newMission;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.ROLE__MISSION, oldMission, mission));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getDependBy() {
		if (dependBy == null) {
			dependBy = new EObjectContainmentWithInverseEList<Dependency>(Dependency.class, this, StstoolPackage.ROLE__DEPEND_BY, StstoolPackage.DEPENDENCY__SOURCE);
		}
		return dependBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dependency> getDependent() {
		if (dependent == null) {
			dependent = new EObjectWithInverseResolvingEList<Dependency>(Dependency.class, this, StstoolPackage.ROLE__DEPENDENT, StstoolPackage.DEPENDENCY__TARGET);
		}
		return dependent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncompatibleDutiesOut()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncompatibleDutiesIn()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCompatibleDutiesOut()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCompatibleDutiesIn()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__PLAYED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPlayedBy()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__DEPEND_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDependBy()).basicAdd(otherEnd, msgs);
			case StstoolPackage.ROLE__DEPENDENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDependent()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				return ((InternalEList<?>)getIncompatibleDutiesOut()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				return ((InternalEList<?>)getIncompatibleDutiesIn()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				return ((InternalEList<?>)getCompatibleDutiesOut()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				return ((InternalEList<?>)getCompatibleDutiesIn()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__PLAYED_BY:
				return ((InternalEList<?>)getPlayedBy()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__DEPEND_BY:
				return ((InternalEList<?>)getDependBy()).basicRemove(otherEnd, msgs);
			case StstoolPackage.ROLE__DEPENDENT:
				return ((InternalEList<?>)getDependent()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				return getIncompatibleDutiesOut();
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				return getIncompatibleDutiesIn();
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				return getCompatibleDutiesOut();
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				return getCompatibleDutiesIn();
			case StstoolPackage.ROLE__PLAYED_BY:
				return getPlayedBy();
			case StstoolPackage.ROLE__PURPOSE:
				return getPurpose();
			case StstoolPackage.ROLE__MISSION:
				return getMission();
			case StstoolPackage.ROLE__DEPEND_BY:
				return getDependBy();
			case StstoolPackage.ROLE__DEPENDENT:
				return getDependent();
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
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				getIncompatibleDutiesOut().clear();
				getIncompatibleDutiesOut().addAll((Collection<? extends IncompatibleDuties>)newValue);
				return;
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				getIncompatibleDutiesIn().clear();
				getIncompatibleDutiesIn().addAll((Collection<? extends IncompatibleDuties>)newValue);
				return;
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				getCompatibleDutiesOut().clear();
				getCompatibleDutiesOut().addAll((Collection<? extends CompatibleDuties>)newValue);
				return;
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				getCompatibleDutiesIn().clear();
				getCompatibleDutiesIn().addAll((Collection<? extends CompatibleDuties>)newValue);
				return;
			case StstoolPackage.ROLE__PLAYED_BY:
				getPlayedBy().clear();
				getPlayedBy().addAll((Collection<? extends Play>)newValue);
				return;
			case StstoolPackage.ROLE__PURPOSE:
				setPurpose((String)newValue);
				return;
			case StstoolPackage.ROLE__MISSION:
				setMission((String)newValue);
				return;
			case StstoolPackage.ROLE__DEPEND_BY:
				getDependBy().clear();
				getDependBy().addAll((Collection<? extends Dependency>)newValue);
				return;
			case StstoolPackage.ROLE__DEPENDENT:
				getDependent().clear();
				getDependent().addAll((Collection<? extends Dependency>)newValue);
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
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				getIncompatibleDutiesOut().clear();
				return;
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				getIncompatibleDutiesIn().clear();
				return;
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				getCompatibleDutiesOut().clear();
				return;
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				getCompatibleDutiesIn().clear();
				return;
			case StstoolPackage.ROLE__PLAYED_BY:
				getPlayedBy().clear();
				return;
			case StstoolPackage.ROLE__PURPOSE:
				setPurpose(PURPOSE_EDEFAULT);
				return;
			case StstoolPackage.ROLE__MISSION:
				setMission(MISSION_EDEFAULT);
				return;
			case StstoolPackage.ROLE__DEPEND_BY:
				getDependBy().clear();
				return;
			case StstoolPackage.ROLE__DEPENDENT:
				getDependent().clear();
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
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT:
				return incompatibleDutiesOut != null && !incompatibleDutiesOut.isEmpty();
			case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN:
				return incompatibleDutiesIn != null && !incompatibleDutiesIn.isEmpty();
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT:
				return compatibleDutiesOut != null && !compatibleDutiesOut.isEmpty();
			case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN:
				return compatibleDutiesIn != null && !compatibleDutiesIn.isEmpty();
			case StstoolPackage.ROLE__PLAYED_BY:
				return playedBy != null && !playedBy.isEmpty();
			case StstoolPackage.ROLE__PURPOSE:
				return PURPOSE_EDEFAULT == null ? purpose != null : !PURPOSE_EDEFAULT.equals(purpose);
			case StstoolPackage.ROLE__MISSION:
				return MISSION_EDEFAULT == null ? mission != null : !MISSION_EDEFAULT.equals(mission);
			case StstoolPackage.ROLE__DEPEND_BY:
				return dependBy != null && !dependBy.isEmpty();
			case StstoolPackage.ROLE__DEPENDENT:
				return dependent != null && !dependent.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,Class<?> baseClass){
		if (baseClass == SeparationOfDuties.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT: return StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT;
				case StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN: return StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		if (baseClass == BindingOfDuties.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT: return StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT;
				case StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN: return StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN;
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
		if (baseClass == SeparationOfDuties.class) {
			switch (baseFeatureID) {
				case StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT: return StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_OUT;
				case StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN: return StstoolPackage.ROLE__INCOMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		if (baseClass == BindingOfDuties.class) {
			switch (baseFeatureID) {
				case StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT: return StstoolPackage.ROLE__COMPATIBLE_DUTIES_OUT;
				case StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN: return StstoolPackage.ROLE__COMPATIBLE_DUTIES_IN;
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
		result.append(" (purpose: ");
		result.append(purpose);
		result.append(", mission: ");
		result.append(mission);
		result.append(')');
		return result.toString();
	}

} //RoleImpl
