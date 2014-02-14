/*
* Actor.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Actor</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getIncomingDelegations <em>Incoming Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getOutgoingDelegations <em>Outgoing Delegations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getGoals <em>Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getOutgoingProvisions <em>Outgoing Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getIncomingProvisions <em>Incoming Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getOutgoingAuthorisations <em>Outgoing Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getIncomingAuthorisations <em>Incoming Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getTResources <em>TResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Actor#getIResources <em>IResources</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor()
 * @model
 * @generated
 */
public interface Actor extends StsElement, Threatable {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Incoming Delegations</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Delegation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Delegations</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Delegations</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_IncomingDelegations()
	 * @see eu.aniketos.wp1.ststool.Delegation#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Delegation> getIncomingDelegations();

	/**
	 * Returns the value of the '<em><b>Outgoing Delegations</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Delegation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Delegations</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Delegations</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_OutgoingDelegations()
	 * @see eu.aniketos.wp1.ststool.Delegation#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Delegation> getOutgoingDelegations();

	/**
	 * Returns the value of the '<em><b>Goals</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Goal}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Goal#getActorOwner <em>Actor Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_Goals()
	 * @see eu.aniketos.wp1.ststool.Goal#getActorOwner
	 * @model opposite="actorOwner" containment="true"
	 * @generated
	 */
	EList<Goal> getGoals();

	/**
	 * Returns the value of the '<em><b>Outgoing Provisions</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Provision#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Provisions</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Provisions</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_OutgoingProvisions()
	 * @see eu.aniketos.wp1.ststool.Provision#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Provision> getOutgoingProvisions();

	/**
	 * Returns the value of the '<em><b>Incoming Provisions</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Provision#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Provisions</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Provisions</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_IncomingProvisions()
	 * @see eu.aniketos.wp1.ststool.Provision#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Provision> getIncomingProvisions();

	/**
	 * Returns the value of the '<em><b>Outgoing Authorisations</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Authorisation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Authorisation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Authorisations</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Authorisations</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_OutgoingAuthorisations()
	 * @see eu.aniketos.wp1.ststool.Authorisation#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Authorisation> getOutgoingAuthorisations();

	/**
	 * Returns the value of the '<em><b>Incoming Authorisations</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Authorisation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Authorisation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Authorisations</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Authorisations</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_IncomingAuthorisations()
	 * @see eu.aniketos.wp1.ststool.Authorisation#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Authorisation> getIncomingAuthorisations();

	/**
	 * Returns the value of the '<em><b>IResources</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Own}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Own#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>IResources</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>IResources</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_IResources()
	 * @see eu.aniketos.wp1.ststool.Own#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Own> getIResources();

	/**
	 * Returns the value of the '<em><b>TResources</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.TResource}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.TResource#getActorOwner <em>Actor Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TResources</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TResources</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getActor_TResources()
	 * @see eu.aniketos.wp1.ststool.TResource#getActorOwner
	 * @model opposite="actorOwner" containment="true"
	 * @generated
	 */
	EList<TResource> getTResources();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isDeletable();

} // Actor
