/*
* EventImpl.java
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
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.Threat;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Event</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.EventImpl#getCountermeasures <em>Countermeasures</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.EventImpl#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.EventImpl#getEventID <em>Event ID</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventImpl extends StsElementImpl implements Event {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String		copyright						= "DISI - University of Trento";

	/**
	 * The default value of the '{@link #getCountermeasures() <em>Countermeasures</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCountermeasures()
	 * @generated
	 * @ordered
	 */
	protected static final String	COUNTERMEASURES_EDEFAULT	= null;

	/**
	 * The cached value of the '{@link #getCountermeasures() <em>Countermeasures</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCountermeasures()
	 * @generated
	 * @ordered
	 */
	protected String					countermeasures				= COUNTERMEASURES_EDEFAULT;

	/**
	 * The cached value of the '{@link #getThreatedElements() <em>Threated Elements</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThreatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Threat>			threatedElements;

	/**
	 * The default value of the '{@link #getEventID() <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventID()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEventID() <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventID()
	 * @generated
	 * @ordered
	 */
	protected String eventID = EVENT_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getCountermeasures(){
		return countermeasures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountermeasures(String newCountermeasures){
		String oldCountermeasures = countermeasures;
		countermeasures = newCountermeasures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.EVENT__COUNTERMEASURES, oldCountermeasures, countermeasures));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Threat> getThreatedElements(){
		if (threatedElements == null) {
			threatedElements = new EObjectContainmentWithInverseEList<Threat>(Threat.class, this, StstoolPackage.EVENT__THREATED_ELEMENTS, StstoolPackage.THREAT__SOURCE);
		}
		return threatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEventID() {
		return eventID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventID(String newEventID) {
		String oldEventID = eventID;
		eventID = newEventID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.EVENT__EVENT_ID, oldEventID, eventID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getThreatedElements()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				return ((InternalEList<?>)getThreatedElements()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.EVENT__COUNTERMEASURES:
				return getCountermeasures();
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				return getThreatedElements();
			case StstoolPackage.EVENT__EVENT_ID:
				return getEventID();
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
			case StstoolPackage.EVENT__COUNTERMEASURES:
				setCountermeasures((String)newValue);
				return;
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				getThreatedElements().clear();
				getThreatedElements().addAll((Collection<? extends Threat>)newValue);
				return;
			case StstoolPackage.EVENT__EVENT_ID:
				setEventID((String)newValue);
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
			case StstoolPackage.EVENT__COUNTERMEASURES:
				setCountermeasures(COUNTERMEASURES_EDEFAULT);
				return;
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				getThreatedElements().clear();
				return;
			case StstoolPackage.EVENT__EVENT_ID:
				setEventID(EVENT_ID_EDEFAULT);
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
			case StstoolPackage.EVENT__COUNTERMEASURES:
				return COUNTERMEASURES_EDEFAULT == null ? countermeasures != null : !COUNTERMEASURES_EDEFAULT.equals(countermeasures);
			case StstoolPackage.EVENT__THREATED_ELEMENTS:
				return threatedElements != null && !threatedElements.isEmpty();
			case StstoolPackage.EVENT__EVENT_ID:
				return EVENT_ID_EDEFAULT == null ? eventID != null : !EVENT_ID_EDEFAULT.equals(eventID);
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
		result.append(" (countermeasures: ");
		result.append(countermeasures);
		result.append(", eventID: ");
		result.append(eventID);
		result.append(')');
		return result.toString();
	}

} //EventImpl
