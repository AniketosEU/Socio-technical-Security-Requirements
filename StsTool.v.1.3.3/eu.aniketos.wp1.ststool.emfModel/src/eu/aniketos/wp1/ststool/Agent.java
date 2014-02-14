/*
* Agent.java
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
package eu.aniketos.wp1.ststool;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Agent</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Agent#getPlayedRoles <em>Played Roles</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Agent#getTypeOfOrganisation <em>Type Of Organisation</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Agent#getAbilities <em>Abilities</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Agent#getPossessedCertificationsAndAccreditations <em>Possessed Certifications And Accreditations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Agent#getOtherImportantFeatures <em>Other Important Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent()
 * @model
 * @generated
 */
public interface Agent extends Actor {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Played Roles</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Play}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Play#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Played Roles</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Played Roles</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent_PlayedRoles()
	 * @see eu.aniketos.wp1.ststool.Play#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Play> getPlayedRoles();

	/**
	 * Returns the value of the '<em><b>Type Of Organisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Of Organisation</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Of Organisation</em>' attribute.
	 * @see #setTypeOfOrganisation(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent_TypeOfOrganisation()
	 * @model
	 * @generated
	 */
	String getTypeOfOrganisation();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Agent#getTypeOfOrganisation <em>Type Of Organisation</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Of Organisation</em>' attribute.
	 * @see #getTypeOfOrganisation()
	 * @generated
	 */
	void setTypeOfOrganisation(String value);

	/**
	 * Returns the value of the '<em><b>Abilities</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abilities</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abilities</em>' attribute.
	 * @see #setAbilities(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent_Abilities()
	 * @model
	 * @generated
	 */
	String getAbilities();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Agent#getAbilities <em>Abilities</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abilities</em>' attribute.
	 * @see #getAbilities()
	 * @generated
	 */
	void setAbilities(String value);

	/**
	 * Returns the value of the '<em><b>Possessed Certifications And Accreditations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Possessed Certifications And Accreditations</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Possessed Certifications And Accreditations</em>' attribute.
	 * @see #setPossessedCertificationsAndAccreditations(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent_PossessedCertificationsAndAccreditations()
	 * @model
	 * @generated
	 */
	String getPossessedCertificationsAndAccreditations();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Agent#getPossessedCertificationsAndAccreditations <em>Possessed Certifications And Accreditations</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Possessed Certifications And Accreditations</em>' attribute.
	 * @see #getPossessedCertificationsAndAccreditations()
	 * @generated
	 */
	void setPossessedCertificationsAndAccreditations(String value);

	/**
	 * Returns the value of the '<em><b>Other Important Features</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Important Features</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other Important Features</em>' attribute.
	 * @see #setOtherImportantFeatures(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAgent_OtherImportantFeatures()
	 * @model
	 * @generated
	 */
	String getOtherImportantFeatures();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Agent#getOtherImportantFeatures <em>Other Important Features</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other Important Features</em>' attribute.
	 * @see #getOtherImportantFeatures()
	 * @generated
	 */
	void setOtherImportantFeatures(String value);

} // Agent
