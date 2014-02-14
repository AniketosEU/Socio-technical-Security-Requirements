/*
* Delegation.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Delegation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getSourceGoal <em>Source Goal</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getTargetGoal <em>Target Goal</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getPreviousDelegation <em>Previous Delegation</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getNextDelegations <em>Next Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getTimesTransferable <em>Times Transferable</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#isShowSecurityNeeds <em>Show Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getRedundancyType <em>Redundancy Type</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getRepudiationType <em>Repudiation Type</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getSecurityNeeds <em>Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#isAvailability <em>Availability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getAvailabilityValue <em>Availability Value</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#isTrustworthiness <em>Trustworthiness</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Delegation#getTrustworthinessValue <em>Trustworthiness Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation()
 * @model
 * @generated
 */
public interface Delegation extends StsRelation, Threatable {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Source Goal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Goal</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Goal</em>' reference.
	 * @see #setSourceGoal(Goal)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_SourceGoal()
	 * @model required="true"
	 * @generated
	 */
	Goal getSourceGoal();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getSourceGoal <em>Source Goal</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Goal</em>' reference.
	 * @see #getSourceGoal()
	 * @generated
	 */
	void setSourceGoal(Goal value);

	/**
	 * Returns the value of the '<em><b>Target Goal</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Goal#getDelegatedFrom <em>Delegated From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Goal</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Goal</em>' reference.
	 * @see #setTargetGoal(Goal)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_TargetGoal()
	 * @see eu.aniketos.wp1.ststool.Goal#getDelegatedFrom
	 * @model opposite="delegatedFrom" required="true"
	 * @generated
	 */
	Goal getTargetGoal();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getTargetGoal <em>Target Goal</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Goal</em>' reference.
	 * @see #getTargetGoal()
	 * @generated
	 */
	void setTargetGoal(Goal value);

	/**
	 * Returns the value of the '<em><b>Previous Delegation</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous Delegation</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous Delegation</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_PreviousDelegation()
	 * @model
	 * @generated
	 */
	EList<Delegation> getPreviousDelegation();

	/**
	 * Returns the value of the '<em><b>Next Delegations</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next Delegations</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next Delegations</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_NextDelegations()
	 * @model
	 * @generated
	 */
	EList<Delegation> getNextDelegations();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getIncomingDelegations <em>Incoming Delegations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_Target()
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingDelegations
	 * @model opposite="incomingDelegations" required="true"
	 * @generated
	 */
	Actor getTarget();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Actor value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingDelegations <em>Outgoing Delegations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_Source()
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingDelegations
	 * @model opposite="outgoingDelegations" required="true" transient="false"
	 * @generated
	 */
	Actor getSource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Actor value);

	/**
	 * Returns the value of the '<em><b>Times Transferable</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Times Transferable</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Times Transferable</em>' attribute.
	 * @see #setTimesTransferable(int)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_TimesTransferable()
	 * @model default="-1"
	 * @generated
	 */
	int getTimesTransferable();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getTimesTransferable <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Times Transferable</em>' attribute.
	 * @see #getTimesTransferable()
	 * @generated
	 */
	void setTimesTransferable(int value);

	/**
	 * Returns the value of the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Conditions</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Conditions</em>' attribute.
	 * @see #setPreConditions(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_PreConditions()
	 * @model
	 * @generated
	 */
	String getPreConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getPreConditions <em>Pre Conditions</em>}' attribute.
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
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_PostConditions()
	 * @model
	 * @generated
	 */
	String getPostConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getPostConditions <em>Post Conditions</em>}' attribute.
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
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_ShowSecurityNeeds()
	 * @model
	 * @generated
	 */
	boolean isShowSecurityNeeds();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#isShowSecurityNeeds <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Show Security Needs</em>' attribute.
	 * @see #isShowSecurityNeeds()
	 * @generated
	 */
	void setShowSecurityNeeds(boolean value);

	/**
	 * Returns the value of the '<em><b>Redundancy Type</b></em>' attribute.
	 * The default value is <code>"RedundancyType.NO_REDUNDANCY"</code>.
	 * The literals are from the enumeration {@link eu.aniketos.wp1.ststool.RedundancyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Redundancy Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Redundancy Type</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.RedundancyType
	 * @see #setRedundancyType(RedundancyType)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_RedundancyType()
	 * @model default="RedundancyType.NO_REDUNDANCY"
	 * @generated
	 */
	RedundancyType getRedundancyType();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getRedundancyType <em>Redundancy Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Redundancy Type</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.RedundancyType
	 * @see #getRedundancyType()
	 * @generated
	 */
	void setRedundancyType(RedundancyType value);

	/**
	 * Returns the value of the '<em><b>Repudiation Type</b></em>' attribute.
	 * The default value is <code>"RepudiationType.NO_REPUDIATION"</code>.
	 * The literals are from the enumeration {@link eu.aniketos.wp1.ststool.RepudiationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repudiation Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repudiation Type</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.RepudiationType
	 * @see #setRepudiationType(RepudiationType)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_RepudiationType()
	 * @model default="RepudiationType.NO_REPUDIATION"
	 * @generated
	 */
	RepudiationType getRepudiationType();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getRepudiationType <em>Repudiation Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repudiation Type</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.RepudiationType
	 * @see #getRepudiationType()
	 * @generated
	 */
	void setRepudiationType(RepudiationType value);

	/**
	 * Returns the value of the '<em><b>Security Needs</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Needs</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Needs</em>' attribute.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_SecurityNeeds()
	 * @model default="" unique="false" changeable="false" derived="true"
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
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_Availability()
	 * @model
	 * @generated
	 */
	boolean isAvailability();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#isAvailability <em>Availability</em>}' attribute.
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
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_AvailabilityValue()
	 * @model
	 * @generated
	 */
	int getAvailabilityValue();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getAvailabilityValue <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability Value</em>' attribute.
	 * @see #getAvailabilityValue()
	 * @generated
	 */
	void setAvailabilityValue(int value);

	/**
	 * Returns the value of the '<em><b>Trustworthiness</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trustworthiness</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trustworthiness</em>' attribute.
	 * @see #setTrustworthiness(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_Trustworthiness()
	 * @model
	 * @generated
	 */
	boolean isTrustworthiness();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#isTrustworthiness <em>Trustworthiness</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trustworthiness</em>' attribute.
	 * @see #isTrustworthiness()
	 * @generated
	 */
	void setTrustworthiness(boolean value);

	/**
	 * Returns the value of the '<em><b>Trustworthiness Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trustworthiness Value</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trustworthiness Value</em>' attribute.
	 * @see #setTrustworthinessValue(int)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getDelegation_TrustworthinessValue()
	 * @model
	 * @generated
	 */
	int getTrustworthinessValue();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Delegation#getTrustworthinessValue <em>Trustworthiness Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trustworthiness Value</em>' attribute.
	 * @see #getTrustworthinessValue()
	 * @generated
	 */
	void setTrustworthinessValue(int value);

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

} // Delegation
