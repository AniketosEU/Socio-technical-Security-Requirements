/*
* Goal.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Goal</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getDelegatedTo <em>Delegated To</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getDelegatedFrom <em>Delegated From</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getAuthorisations <em>Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getActorOwner <em>Actor Owner</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getOutgoingContributions <em>Outgoing Contributions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getIncomingContribution <em>Incoming Contribution</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getOutgoingDecompositions <em>Outgoing Decompositions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getIncomingDecompositions <em>Incoming Decompositions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#isCapability <em>Capability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getResourceNeeded <em>Resource Needed</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getResourcesProduced <em>Resources Produced</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Goal#getResourcesModified <em>Resources Modified</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal()
 * @model
 * @generated
 */
public interface Goal extends StsElement, Threatable, SeparationOfDuties, BindingOfDuties {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";


	public void setNameForced(String newName);


	/**
	 * Returns the value of the '<em><b>Delegated To</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegated To</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegated To</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_DelegatedTo()
	 * @model
	 * @generated
	 */
	EList<Delegation> getDelegatedTo();

	/**
	 * Returns the value of the '<em><b>Delegated From</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Delegation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Delegation#getTargetGoal <em>Target Goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegated From</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegated From</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_DelegatedFrom()
	 * @see eu.aniketos.wp1.ststool.Delegation#getTargetGoal
	 * @model opposite="targetGoal"
	 * @generated
	 */
	EList<Delegation> getDelegatedFrom();

	/**
	 * Returns the value of the '<em><b>Authorisations</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Authorisation}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Authorisation#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authorisations</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authorisations</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_Authorisations()
	 * @see eu.aniketos.wp1.ststool.Authorisation#getGoals
	 * @model opposite="goals"
	 * @generated
	 */
	EList<Authorisation> getAuthorisations();

	/**
	 * Returns the value of the '<em><b>Actor Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Owner</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor Owner</em>' container reference.
	 * @see #setActorOwner(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_ActorOwner()
	 * @see eu.aniketos.wp1.ststool.Actor#getGoals
	 * @model opposite="goals" transient="false"
	 * @generated
	 */
	Actor getActorOwner();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Goal#getActorOwner <em>Actor Owner</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor Owner</em>' container reference.
	 * @see #getActorOwner()
	 * @generated
	 */
	void setActorOwner(Actor value);

	/**
	 * Returns the value of the '<em><b>Resource Needed</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Need}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Need#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Needed</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Needed</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_ResourceNeeded()
	 * @see eu.aniketos.wp1.ststool.Need#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Need> getResourceNeeded();

	/**
	 * Returns the value of the '<em><b>Resources Produced</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Produce}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Produce#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources Produced</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources Produced</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_ResourcesProduced()
	 * @see eu.aniketos.wp1.ststool.Produce#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Produce> getResourcesProduced();

	/**
	 * Returns the value of the '<em><b>Resources Modified</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Modify}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Modify#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources Modified</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources Modified</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_ResourcesModified()
	 * @see eu.aniketos.wp1.ststool.Modify#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Modify> getResourcesModified();

	/**
	 * Returns the value of the '<em><b>Outgoing Contributions</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.GoalContribution}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.GoalContribution#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Contributions</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Contributions</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_OutgoingContributions()
	 * @see eu.aniketos.wp1.ststool.GoalContribution#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<GoalContribution> getOutgoingContributions();

	/**
	 * Returns the value of the '<em><b>Incoming Contribution</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.GoalContribution}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.GoalContribution#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Contribution</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Contribution</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_IncomingContribution()
	 * @see eu.aniketos.wp1.ststool.GoalContribution#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<GoalContribution> getIncomingContribution();

	/**
	 * Returns the value of the '<em><b>Outgoing Decompositions</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.GoalDecomposition}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.GoalDecomposition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Decompositions</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Decompositions</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_OutgoingDecompositions()
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<GoalDecomposition> getOutgoingDecompositions();


	/**
	 * Returns the value of the '<em><b>Incoming Decompositions</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.GoalDecomposition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Decompositions</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Decompositions</em>' reference.
	 * @see #setIncomingDecompositions(GoalDecomposition)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_IncomingDecompositions()
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	GoalDecomposition getIncomingDecompositions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Goal#getIncomingDecompositions <em>Incoming Decompositions</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Incoming Decompositions</em>' reference.
	 * @see #getIncomingDecompositions()
	 * @generated
	 */
	void setIncomingDecompositions(GoalDecomposition value);

	/**
	 * Returns the value of the '<em><b>Capability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Capability</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Capability</em>' attribute.
	 * @see #setCapability(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_Capability()
	 * @model
	 * @generated
	 */
	boolean isCapability();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Goal#isCapability <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Capability</em>' attribute.
	 * @see #isCapability()
	 * @generated
	 */
	void setCapability(boolean value);

	/**
	 * Returns the value of the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pre Conditions</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pre Conditions</em>' attribute.
	 * @see #setPreConditions(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_PreConditions()
	 * @model
	 * @generated
	 */
	String getPreConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Goal#getPreConditions <em>Pre Conditions</em>}' attribute.
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
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getGoal_PostConditions()
	 * @model
	 * @generated
	 */
	String getPostConditions();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Goal#getPostConditions <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Conditions</em>' attribute.
	 * @see #getPostConditions()
	 * @generated
	 */
	void setPostConditions(String value);

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
	boolean canBeDelegated();

} // Goal
