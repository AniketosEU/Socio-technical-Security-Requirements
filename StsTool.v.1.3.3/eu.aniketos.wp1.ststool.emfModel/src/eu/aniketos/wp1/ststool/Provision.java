/*
* Provision.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Provision</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getPreviousProvision <em>Previous Provision</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getNextProvisions <em>Next Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getSourceResource <em>Source Resource</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getTargetResource <em>Target Resource</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#isShowSecurityNeeds <em>Show Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#isIntegrity <em>Integrity</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getSecurityNeeds <em>Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#isAvailability <em>Availability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#getAvailabilityValue <em>Availability Value</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Provision#isConfidentiality <em>Confidentiality</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision()
 * @model
 * @generated
 */
public interface Provision extends StsRelation {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Previous Provision</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Provision</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Provision</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_PreviousProvision()
	 * @model
	 * @generated
	 */
	EList<Provision> getPreviousProvision();

	/**
	 * Returns the value of the '<em><b>Next Provisions</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Provisions</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Provisions</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_NextProvisions()
	 * @model
	 * @generated
	 */
	EList<Provision> getNextProvisions();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getIncomingProvisions <em>Incoming Provisions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_Target()
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingProvisions
	 * @model opposite="incomingProvisions" required="true"
	 * @generated
	 */
	Actor getTarget();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Actor value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingProvisions <em>Outgoing Provisions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_Source()
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingProvisions
	 * @model opposite="outgoingProvisions" required="true" transient="false"
	 * @generated
	 */
	Actor getSource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Actor value);

	/**
	 * Returns the value of the '<em><b>Source Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Resource</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Resource</em>' reference.
	 * @see #setSourceResource(TResource)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_SourceResource()
	 * @model required="true"
	 * @generated
	 */
	TResource getSourceResource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getSourceResource <em>Source Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Resource</em>' reference.
	 * @see #getSourceResource()
	 * @generated
	 */
	void setSourceResource(TResource value);

	/**
	 * Returns the value of the '<em><b>Target Resource</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.TResource#getProvidedFrom <em>Provided From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Resource</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Resource</em>' reference.
	 * @see #setTargetResource(TResource)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_TargetResource()
	 * @see eu.aniketos.wp1.ststool.TResource#getProvidedFrom
	 * @model opposite="providedFrom" required="true"
	 * @generated
	 */
	TResource getTargetResource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getTargetResource <em>Target Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Resource</em>' reference.
	 * @see #getTargetResource()
	 * @generated
	 */
	void setTargetResource(TResource value);

	/**
	 * Returns the value of the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Conditions</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Conditions</em>' attribute.
	 * @see #setPreConditions(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_PreConditions()
	 * @model
	 * @generated
	 */
	String getPreConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getPreConditions <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pre Conditions</em>' attribute.
	 * @see #getPreConditions()
	 * @generated
	 */
	void setPreConditions(String value);

	/**
	 * Returns the value of the '<em><b>Post Conditions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Post Conditions</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Post Conditions</em>' attribute.
	 * @see #setPostConditions(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_PostConditions()
	 * @model
	 * @generated
	 */
	String getPostConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getPostConditions <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Conditions</em>' attribute.
	 * @see #getPostConditions()
	 * @generated
	 */
	void setPostConditions(String value);

	/**
	 * Returns the value of the '<em><b>Show Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Show Security Needs</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Show Security Needs</em>' attribute.
	 * @see #setShowSecurityNeeds(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_ShowSecurityNeeds()
	 * @model
	 * @generated
	 */
	boolean isShowSecurityNeeds();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#isShowSecurityNeeds <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Security Needs</em>' attribute.
	 * @see #isShowSecurityNeeds()
	 * @generated
	 */
	void setShowSecurityNeeds(boolean value);

	/**
	 * Returns the value of the '<em><b>Integrity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integrity</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integrity</em>' attribute.
	 * @see #setIntegrity(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_Integrity()
	 * @model
	 * @generated
	 */
	boolean isIntegrity();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#isIntegrity <em>Integrity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integrity</em>' attribute.
	 * @see #isIntegrity()
	 * @generated
	 */
	void setIntegrity(boolean value);

	/**
	 * Returns the value of the '<em><b>Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Needs</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Needs</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_SecurityNeeds()
	 * @model changeable="false" derived="true"
	 * @generated
	 */
	String getSecurityNeeds();

	/**
	 * Returns the value of the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Availability</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Availability</em>' attribute.
	 * @see #setAvailability(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_Availability()
	 * @model
	 * @generated
	 */
	boolean isAvailability();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#isAvailability <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability</em>' attribute.
	 * @see #isAvailability()
	 * @generated
	 */
	void setAvailability(boolean value);

	/**
	 * Returns the value of the '<em><b>Availability Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Availability Value</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Availability Value</em>' attribute.
	 * @see #setAvailabilityValue(int)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_AvailabilityValue()
	 * @model
	 * @generated
	 */
	int getAvailabilityValue();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#getAvailabilityValue <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability Value</em>' attribute.
	 * @see #getAvailabilityValue()
	 * @generated
	 */
	void setAvailabilityValue(int value);

	/**
	 * Returns the value of the '<em><b>Confidentiality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Confidentiality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Confidentiality</em>' attribute.
	 * @see #setConfidentiality(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getProvision_Confidentiality()
	 * @model
	 * @generated
	 */
	boolean isConfidentiality();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Provision#isConfidentiality <em>Confidentiality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confidentiality</em>' attribute.
	 * @see #isConfidentiality()
	 * @generated
	 */
	void setConfidentiality(boolean value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isDeletable();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean canBeTransferred();

} // Provision
