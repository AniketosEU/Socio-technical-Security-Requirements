/*
* StstoolAdapterFactory.java
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
package eu.aniketos.wp1.ststool.util;

import eu.aniketos.wp1.ststool.*;
import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalContribution;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.GoalDecompositionAND;
import eu.aniketos.wp1.ststool.GoalDecompositionOR;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.NegativeGoalContribution;
import eu.aniketos.wp1.ststool.NonTransferableAuthorisation;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.PositiveGoalContribution;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsRelation;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;
import eu.aniketos.wp1.ststool.TransferableAuthorisation;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolPackage
 * @generated
 */
public class StstoolAdapterFactory extends AdapterFactoryImpl {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright	= "DISI - University of Trento";

	/**
	 * The cached model package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static StstoolPackage	modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StstoolAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = StstoolPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model. <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object){
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected StstoolSwitch<Adapter>	modelSwitch	= new StstoolSwitch<Adapter>() {
			@Override
			public Adapter caseStsToolDiagram(StsToolDiagram object) {
				return createStsToolDiagramAdapter();
			}
			@Override
			public Adapter caseActor(Actor object) {
				return createActorAdapter();
			}
			@Override
			public Adapter caseDelegation(Delegation object) {
				return createDelegationAdapter();
			}
			@Override
			public Adapter caseProvision(Provision object) {
				return createProvisionAdapter();
			}
			@Override
			public Adapter caseAgent(Agent object) {
				return createAgentAdapter();
			}
			@Override
			public Adapter caseRole(Role object) {
				return createRoleAdapter();
			}
			@Override
			public Adapter caseTResource(TResource object) {
				return createTResourceAdapter();
			}
			@Override
			public Adapter caseGoal(Goal object) {
				return createGoalAdapter();
			}
			@Override
			public Adapter caseAuthorisation(Authorisation object) {
				return createAuthorisationAdapter();
			}
			@Override
			public Adapter caseResource(Resource object) {
				return createResourceAdapter();
			}
			@Override
			public Adapter caseIResource(IResource object) {
				return createIResourceAdapter();
			}
			@Override
			public Adapter caseStsElement(StsElement object) {
				return createStsElementAdapter();
			}
			@Override
			public Adapter caseNonTransferableAuthorisation(NonTransferableAuthorisation object) {
				return createNonTransferableAuthorisationAdapter();
			}
			@Override
			public Adapter caseTransferableAuthorisation(TransferableAuthorisation object) {
				return createTransferableAuthorisationAdapter();
			}
			@Override
			public Adapter caseGoalContribution(GoalContribution object) {
				return createGoalContributionAdapter();
			}
			@Override
			public Adapter caseGoalDecomposition(GoalDecomposition object) {
				return createGoalDecompositionAdapter();
			}
			@Override
			public Adapter casePositiveGoalContribution(PositiveGoalContribution object) {
				return createPositiveGoalContributionAdapter();
			}
			@Override
			public Adapter caseNegativeGoalContribution(NegativeGoalContribution object) {
				return createNegativeGoalContributionAdapter();
			}
			@Override
			public Adapter caseGoalDecompositionAND(GoalDecompositionAND object) {
				return createGoalDecompositionANDAdapter();
			}
			@Override
			public Adapter caseGoalDecompositionOR(GoalDecompositionOR object) {
				return createGoalDecompositionORAdapter();
			}
			@Override
			public Adapter caseThreatable(Threatable object) {
				return createThreatableAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseOwn(Own object) {
				return createOwnAdapter();
			}
			@Override
			public Adapter casePlay(Play object) {
				return createPlayAdapter();
			}
			@Override
			public Adapter caseTangibleBy(TangibleBy object) {
				return createTangibleByAdapter();
			}
			@Override
			public Adapter casePartOf(PartOf object) {
				return createPartOfAdapter();
			}
			@Override
			public Adapter caseNeed(Need object) {
				return createNeedAdapter();
			}
			@Override
			public Adapter caseProduce(Produce object) {
				return createProduceAdapter();
			}
			@Override
			public Adapter caseModify(Modify object) {
				return createModifyAdapter();
			}
			@Override
			public Adapter caseThreat(Threat object) {
				return createThreatAdapter();
			}
			@Override
			public Adapter caseStsRelation(StsRelation object) {
				return createStsRelationAdapter();
			}
			@Override
			public Adapter caseStsObject(StsObject object) {
				return createStsObjectAdapter();
			}
			@Override
			public Adapter caseStringToStringMap(Map.Entry<String, String> object) {
				return createStringToStringMapAdapter();
			}
			@Override
			public Adapter caseIncompatibleDuties(IncompatibleDuties object) {
				return createIncompatibleDutiesAdapter();
			}
			@Override
			public Adapter caseSeparationOfDuties(SeparationOfDuties object) {
				return createSeparationOfDutiesAdapter();
			}
			@Override
			public Adapter caseBindingOfDuties(BindingOfDuties object) {
				return createBindingOfDutiesAdapter();
			}
			@Override
			public Adapter caseCompatibleDuties(CompatibleDuties object) {
				return createCompatibleDutiesAdapter();
			}
			@Override
			public Adapter caseDependency(Dependency object) {
				return createDependencyAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target){
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.StsToolDiagram <em>Sts Tool Diagram</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram
	 * @generated
	 */
	public Adapter createStsToolDiagramAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Actor
	 * @generated
	 */
	public Adapter createActorAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Delegation <em>Delegation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Delegation
	 * @generated
	 */
	public Adapter createDelegationAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Provision <em>Provision</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Provision
	 * @generated
	 */
	public Adapter createProvisionAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Agent
	 * @generated
	 */
	public Adapter createAgentAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Role <em>Role</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Role
	 * @generated
	 */
	public Adapter createRoleAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.TResource <em>TResource</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.TResource
	 * @generated
	 */
	public Adapter createTResourceAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Goal <em>Goal</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Goal
	 * @generated
	 */
	public Adapter createGoalAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Authorisation <em>Authorisation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Authorisation
	 * @generated
	 */
	public Adapter createAuthorisationAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.IResource <em>IResource</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.IResource
	 * @generated
	 */
	public Adapter createIResourceAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.StsElement <em>Sts Element</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.StsElement
	 * @generated
	 */
	public Adapter createStsElementAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.NonTransferableAuthorisation <em>Non Transferable Authorisation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.NonTransferableAuthorisation
	 * @generated
	 */
	public Adapter createNonTransferableAuthorisationAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.TransferableAuthorisation <em>Transferable Authorisation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.TransferableAuthorisation
	 * @generated
	 */
	public Adapter createTransferableAuthorisationAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.GoalContribution <em>Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.GoalContribution
	 * @generated
	 */
	public Adapter createGoalContributionAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.GoalDecomposition <em>Goal Decomposition</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition
	 * @generated
	 */
	public Adapter createGoalDecompositionAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.PositiveGoalContribution <em>Positive Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.PositiveGoalContribution
	 * @generated
	 */
	public Adapter createPositiveGoalContributionAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.NegativeGoalContribution <em>Negative Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.NegativeGoalContribution
	 * @generated
	 */
	public Adapter createNegativeGoalContributionAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.GoalDecompositionAND <em>Goal Decomposition AND</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.GoalDecompositionAND
	 * @generated
	 */
	public Adapter createGoalDecompositionANDAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.GoalDecompositionOR <em>Goal Decomposition OR</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.GoalDecompositionOR
	 * @generated
	 */
	public Adapter createGoalDecompositionORAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Threatable <em>Threatable</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Threatable
	 * @generated
	 */
	public Adapter createThreatableAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Event <em>Event</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Event
	 * @generated
	 */
	public Adapter createEventAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Own <em>Own</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Own
	 * @generated
	 */
	public Adapter createOwnAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Play <em>Play</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Play
	 * @generated
	 */
	public Adapter createPlayAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.TangibleBy <em>Tangible By</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.TangibleBy
	 * @generated
	 */
	public Adapter createTangibleByAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.PartOf <em>Part Of</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.PartOf
	 * @generated
	 */
	public Adapter createPartOfAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Need <em>Need</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Need
	 * @generated
	 */
	public Adapter createNeedAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Produce <em>Produce</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Produce
	 * @generated
	 */
	public Adapter createProduceAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Modify <em>Modify</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Modify
	 * @generated
	 */
	public Adapter createModifyAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Threat <em>Threat</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Threat
	 * @generated
	 */
	public Adapter createThreatAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.StsRelation <em>Sts Relation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.StsRelation
	 * @generated
	 */
	public Adapter createStsRelationAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.StsObject <em>Sts Object</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.StsObject
	 * @generated
	 */
	public Adapter createStsObjectAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToStringMapAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.IncompatibleDuties <em>Incompatible Duties</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.IncompatibleDuties
	 * @generated
	 */
	public Adapter createIncompatibleDutiesAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.SeparationOfDuties <em>Separation Of Duties</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties
	 * @generated
	 */
	public Adapter createSeparationOfDutiesAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.BindingOfDuties <em>Binding Of Duties</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.BindingOfDuties
	 * @generated
	 */
	public Adapter createBindingOfDutiesAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.CompatibleDuties <em>Compatible Duties</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.CompatibleDuties
	 * @generated
	 */
	public Adapter createCompatibleDutiesAdapter(){
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eu.aniketos.wp1.ststool.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see eu.aniketos.wp1.ststool.Dependency
	 * @generated
	 */
	public Adapter createDependencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This default implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter(){
		return null;
	}

} //StstoolAdapterFactory
