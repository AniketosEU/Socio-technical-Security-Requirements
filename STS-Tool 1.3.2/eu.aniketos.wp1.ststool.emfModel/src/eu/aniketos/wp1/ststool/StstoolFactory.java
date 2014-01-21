/*
* StstoolFactory.java
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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the model. <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolPackage
 * @generated
 */
public interface StstoolFactory extends EFactory {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String			copyright	= "DISI - University of Trento";

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	StstoolFactory	eINSTANCE	= eu.aniketos.wp1.ststool.impl.StstoolFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sts Tool Diagram</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Sts Tool Diagram</em>'.
	 * @generated
	 */
	StsToolDiagram createStsToolDiagram();

	/**
	 * Returns a new object of class '<em>Actor</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Actor</em>'.
	 * @generated
	 */
	Actor createActor();

	/**
	 * Returns a new object of class '<em>Delegation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Delegation</em>'.
	 * @generated
	 */
	Delegation createDelegation();

	/**
	 * Returns a new object of class '<em>Provision</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Provision</em>'.
	 * @generated
	 */
	Provision createProvision();

	/**
	 * Returns a new object of class '<em>Agent</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Agent</em>'.
	 * @generated
	 */
	Agent createAgent();

	/**
	 * Returns a new object of class '<em>Role</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Role</em>'.
	 * @generated
	 */
	Role createRole();

	/**
	 * Returns a new object of class '<em>TResource</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource</em>'.
	 * @generated
	 */
	TResource createTResource();

	/**
	 * Returns a new object of class '<em>Goal</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Goal</em>'.
	 * @generated
	 */
	Goal createGoal();

	/**
	 * Returns a new object of class '<em>Authorisation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Authorisation</em>'.
	 * @generated
	 */
	Authorisation createAuthorisation();

	/**
	 * Returns a new object of class '<em>Resource</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource</em>'.
	 * @generated
	 */
	Resource createResource();

	/**
	 * Returns a new object of class '<em>IResource</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>IResource</em>'.
	 * @generated
	 */
	IResource createIResource();

	/**
	 * Returns a new object of class '<em>Non Transferable Authorisation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Non Transferable Authorisation</em>'.
	 * @generated
	 */
	NonTransferableAuthorisation createNonTransferableAuthorisation();

	/**
	 * Returns a new object of class '<em>Transferable Authorisation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Transferable Authorisation</em>'.
	 * @generated
	 */
	TransferableAuthorisation createTransferableAuthorisation();

	/**
	 * Returns a new object of class '<em>Goal Contribution</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Goal Contribution</em>'.
	 * @generated
	 */
	GoalContribution createGoalContribution();

	/**
	 * Returns a new object of class '<em>Goal Decomposition</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Goal Decomposition</em>'.
	 * @generated
	 */
	GoalDecomposition createGoalDecomposition();

	/**
	 * Returns a new object of class '<em>Positive Goal Contribution</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Positive Goal Contribution</em>'.
	 * @generated
	 */
	PositiveGoalContribution createPositiveGoalContribution();

	/**
	 * Returns a new object of class '<em>Negative Goal Contribution</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Negative Goal Contribution</em>'.
	 * @generated
	 */
	NegativeGoalContribution createNegativeGoalContribution();

	/**
	 * Returns a new object of class '<em>Goal Decomposition AND</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Goal Decomposition AND</em>'.
	 * @generated
	 */
	GoalDecompositionAND createGoalDecompositionAND();

	/**
	 * Returns a new object of class '<em>Goal Decomposition OR</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Goal Decomposition OR</em>'.
	 * @generated
	 */
	GoalDecompositionOR createGoalDecompositionOR();

	/**
	 * Returns a new object of class '<em>Event</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Event</em>'.
	 * @generated
	 */
	Event createEvent();

	/**
	 * Returns a new object of class '<em>Own</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Own</em>'.
	 * @generated
	 */
	Own createOwn();

	/**
	 * Returns a new object of class '<em>Play</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Play</em>'.
	 * @generated
	 */
	Play createPlay();

	/**
	 * Returns a new object of class '<em>Tangible By</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Tangible By</em>'.
	 * @generated
	 */
	TangibleBy createTangibleBy();

	/**
	 * Returns a new object of class '<em>Part Of</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Part Of</em>'.
	 * @generated
	 */
	PartOf createPartOf();

	/**
	 * Returns a new object of class '<em>Need</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Need</em>'.
	 * @generated
	 */
	Need createNeed();

	/**
	 * Returns a new object of class '<em>Produce</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Produce</em>'.
	 * @generated
	 */
	Produce createProduce();

	/**
	 * Returns a new object of class '<em>Modify</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Modify</em>'.
	 * @generated
	 */
	Modify createModify();

	/**
	 * Returns a new object of class '<em>Threat</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Threat</em>'.
	 * @generated
	 */
	Threat createThreat();

	/**
	 * Returns a new object of class '<em>Sts Relation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Sts Relation</em>'.
	 * @generated
	 */
	StsRelation createStsRelation();

	/**
	 * Returns a new object of class '<em>Sts Object</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Sts Object</em>'.
	 * @generated
	 */
	StsObject createStsObject();

	/**
	 * Returns a new object of class '<em>Incompatible Duties</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Incompatible Duties</em>'.
	 * @generated
	 */
	IncompatibleDuties createIncompatibleDuties();

	/**
	 * Returns a new object of class '<em>Compatible Duties</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return a new object of class '<em>Compatible Duties</em>'.
	 * @generated
	 */
	CompatibleDuties createCompatibleDuties();

	/**
	 * Returns a new object of class '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dependency</em>'.
	 * @generated
	 */
	Dependency createDependency();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StstoolPackage getStstoolPackage();

} //StstoolFactory
