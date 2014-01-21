/*
* AgentImpl.java
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
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.StstoolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Agent</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AgentImpl#getPlayedRoles <em>Played Roles</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AgentImpl#getTypeOfOrganisation <em>Type Of Organisation</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AgentImpl#getAbilities <em>Abilities</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AgentImpl#getPossessedCertificationsAndAccreditations <em>Possessed Certifications And Accreditations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.AgentImpl#getOtherImportantFeatures <em>Other Important Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AgentImpl extends ActorImpl implements Agent {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String		copyright															= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getPlayedRoles() <em>Played Roles</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPlayedRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Play>			playedRoles;

	/**
	 * The default value of the '{@link #getTypeOfOrganisation() <em>Type Of Organisation</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypeOfOrganisation()
	 * @generated
	 * @ordered
	 */
	protected static final String	TYPE_OF_ORGANISATION_EDEFAULT									= null;

	/**
	 * The cached value of the '{@link #getTypeOfOrganisation() <em>Type Of Organisation</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypeOfOrganisation()
	 * @generated
	 * @ordered
	 */
	protected String					typeOfOrganisation												= TYPE_OF_ORGANISATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAbilities() <em>Abilities</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAbilities()
	 * @generated
	 * @ordered
	 */
	protected static final String	ABILITIES_EDEFAULT												= null;

	/**
	 * The cached value of the '{@link #getAbilities() <em>Abilities</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAbilities()
	 * @generated
	 * @ordered
	 */
	protected String					abilities															= ABILITIES_EDEFAULT;

	/**
	 * The default value of the '{@link #getPossessedCertificationsAndAccreditations() <em>Possessed Certifications And Accreditations</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPossessedCertificationsAndAccreditations()
	 * @generated
	 * @ordered
	 */
	protected static final String	POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS_EDEFAULT	= null;

	/**
	 * The cached value of the '{@link #getPossessedCertificationsAndAccreditations() <em>Possessed Certifications And Accreditations</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPossessedCertificationsAndAccreditations()
	 * @generated
	 * @ordered
	 */
	protected String					possessedCertificationsAndAccreditations					= POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getOtherImportantFeatures() <em>Other Important Features</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOtherImportantFeatures()
	 * @generated
	 * @ordered
	 */
	protected static final String	OTHER_IMPORTANT_FEATURES_EDEFAULT							= null;

	/**
	 * The cached value of the '{@link #getOtherImportantFeatures() <em>Other Important Features</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOtherImportantFeatures()
	 * @generated
	 * @ordered
	 */
	protected String					otherImportantFeatures											= OTHER_IMPORTANT_FEATURES_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.AGENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Play> getPlayedRoles(){
		if (playedRoles == null) {
			playedRoles = new EObjectContainmentWithInverseEList<Play>(Play.class, this, StstoolPackage.AGENT__PLAYED_ROLES, StstoolPackage.PLAY__SOURCE);
		}
		return playedRoles;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeOfOrganisation(){
		return typeOfOrganisation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeOfOrganisation(String newTypeOfOrganisation){
		String oldTypeOfOrganisation = typeOfOrganisation;
		typeOfOrganisation = newTypeOfOrganisation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AGENT__TYPE_OF_ORGANISATION, oldTypeOfOrganisation, typeOfOrganisation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getAbilities(){
		return abilities;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbilities(String newAbilities){
		String oldAbilities = abilities;
		abilities = newAbilities;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AGENT__ABILITIES, oldAbilities, abilities));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPossessedCertificationsAndAccreditations(){
		return possessedCertificationsAndAccreditations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPossessedCertificationsAndAccreditations(String newPossessedCertificationsAndAccreditations){
		String oldPossessedCertificationsAndAccreditations = possessedCertificationsAndAccreditations;
		possessedCertificationsAndAccreditations = newPossessedCertificationsAndAccreditations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS, oldPossessedCertificationsAndAccreditations, possessedCertificationsAndAccreditations));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getOtherImportantFeatures(){
		return otherImportantFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOtherImportantFeatures(String newOtherImportantFeatures){
		String oldOtherImportantFeatures = otherImportantFeatures;
		otherImportantFeatures = newOtherImportantFeatures;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.AGENT__OTHER_IMPORTANT_FEATURES, oldOtherImportantFeatures, otherImportantFeatures));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.AGENT__PLAYED_ROLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPlayedRoles()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.AGENT__PLAYED_ROLES:
				return ((InternalEList<?>)getPlayedRoles()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.AGENT__PLAYED_ROLES:
				return getPlayedRoles();
			case StstoolPackage.AGENT__TYPE_OF_ORGANISATION:
				return getTypeOfOrganisation();
			case StstoolPackage.AGENT__ABILITIES:
				return getAbilities();
			case StstoolPackage.AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS:
				return getPossessedCertificationsAndAccreditations();
			case StstoolPackage.AGENT__OTHER_IMPORTANT_FEATURES:
				return getOtherImportantFeatures();
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
			case StstoolPackage.AGENT__PLAYED_ROLES:
				getPlayedRoles().clear();
				getPlayedRoles().addAll((Collection<? extends Play>)newValue);
				return;
			case StstoolPackage.AGENT__TYPE_OF_ORGANISATION:
				setTypeOfOrganisation((String)newValue);
				return;
			case StstoolPackage.AGENT__ABILITIES:
				setAbilities((String)newValue);
				return;
			case StstoolPackage.AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS:
				setPossessedCertificationsAndAccreditations((String)newValue);
				return;
			case StstoolPackage.AGENT__OTHER_IMPORTANT_FEATURES:
				setOtherImportantFeatures((String)newValue);
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
			case StstoolPackage.AGENT__PLAYED_ROLES:
				getPlayedRoles().clear();
				return;
			case StstoolPackage.AGENT__TYPE_OF_ORGANISATION:
				setTypeOfOrganisation(TYPE_OF_ORGANISATION_EDEFAULT);
				return;
			case StstoolPackage.AGENT__ABILITIES:
				setAbilities(ABILITIES_EDEFAULT);
				return;
			case StstoolPackage.AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS:
				setPossessedCertificationsAndAccreditations(POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS_EDEFAULT);
				return;
			case StstoolPackage.AGENT__OTHER_IMPORTANT_FEATURES:
				setOtherImportantFeatures(OTHER_IMPORTANT_FEATURES_EDEFAULT);
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
			case StstoolPackage.AGENT__PLAYED_ROLES:
				return playedRoles != null && !playedRoles.isEmpty();
			case StstoolPackage.AGENT__TYPE_OF_ORGANISATION:
				return TYPE_OF_ORGANISATION_EDEFAULT == null ? typeOfOrganisation != null : !TYPE_OF_ORGANISATION_EDEFAULT.equals(typeOfOrganisation);
			case StstoolPackage.AGENT__ABILITIES:
				return ABILITIES_EDEFAULT == null ? abilities != null : !ABILITIES_EDEFAULT.equals(abilities);
			case StstoolPackage.AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS:
				return POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS_EDEFAULT == null ? possessedCertificationsAndAccreditations != null : !POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS_EDEFAULT.equals(possessedCertificationsAndAccreditations);
			case StstoolPackage.AGENT__OTHER_IMPORTANT_FEATURES:
				return OTHER_IMPORTANT_FEATURES_EDEFAULT == null ? otherImportantFeatures != null : !OTHER_IMPORTANT_FEATURES_EDEFAULT.equals(otherImportantFeatures);
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
		result.append(" (typeOfOrganisation: ");
		result.append(typeOfOrganisation);
		result.append(", abilities: ");
		result.append(abilities);
		result.append(", possessedCertificationsAndAccreditations: ");
		result.append(possessedCertificationsAndAccreditations);
		result.append(", otherImportantFeatures: ");
		result.append(otherImportantFeatures);
		result.append(')');
		return result.toString();
	}

} //AgentImpl
