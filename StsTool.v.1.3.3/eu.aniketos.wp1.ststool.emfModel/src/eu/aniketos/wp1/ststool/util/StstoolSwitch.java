/*
* StstoolSwitch.java
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
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model, starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is returned, which is the result of the switch. <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolPackage
 * @generated
 */
public class StstoolSwitch<T> extends Switch<T> {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright	= "DISI - University of Trento";

	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static StstoolPackage	modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StstoolSwitch() {
		if (modelPackage == null) {
			modelPackage = StstoolPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID,EObject theEObject){
		switch (classifierID) {
			case StstoolPackage.STS_TOOL_DIAGRAM: {
				StsToolDiagram stsToolDiagram = (StsToolDiagram)theEObject;
				T result = caseStsToolDiagram(stsToolDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				T result = caseActor(actor);
				if (result == null) result = caseStsElement(actor);
				if (result == null) result = caseThreatable(actor);
				if (result == null) result = caseStsObject(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.DELEGATION: {
				Delegation delegation = (Delegation)theEObject;
				T result = caseDelegation(delegation);
				if (result == null) result = caseStsRelation(delegation);
				if (result == null) result = caseThreatable(delegation);
				if (result == null) result = caseStsObject(delegation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.PROVISION: {
				Provision provision = (Provision)theEObject;
				T result = caseProvision(provision);
				if (result == null) result = caseStsRelation(provision);
				if (result == null) result = caseStsObject(provision);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.AGENT: {
				Agent agent = (Agent)theEObject;
				T result = caseAgent(agent);
				if (result == null) result = caseActor(agent);
				if (result == null) result = caseStsElement(agent);
				if (result == null) result = caseThreatable(agent);
				if (result == null) result = caseStsObject(agent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.ROLE: {
				Role role = (Role)theEObject;
				T result = caseRole(role);
				if (result == null) result = caseActor(role);
				if (result == null) result = caseSeparationOfDuties(role);
				if (result == null) result = caseBindingOfDuties(role);
				if (result == null) result = caseStsElement(role);
				if (result == null) result = caseThreatable(role);
				if (result == null) result = caseStsObject(role);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.TRESOURCE: {
				TResource tResource = (TResource)theEObject;
				T result = caseTResource(tResource);
				if (result == null) result = caseResource(tResource);
				if (result == null) result = caseThreatable(tResource);
				if (result == null) result = caseStsElement(tResource);
				if (result == null) result = caseStsObject(tResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.GOAL: {
				Goal goal = (Goal)theEObject;
				T result = caseGoal(goal);
				if (result == null) result = caseStsElement(goal);
				if (result == null) result = caseThreatable(goal);
				if (result == null) result = caseSeparationOfDuties(goal);
				if (result == null) result = caseBindingOfDuties(goal);
				if (result == null) result = caseStsObject(goal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.AUTHORISATION: {
				Authorisation authorisation = (Authorisation)theEObject;
				T result = caseAuthorisation(authorisation);
				if (result == null) result = caseStsRelation(authorisation);
				if (result == null) result = caseStsObject(authorisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = caseStsElement(resource);
				if (result == null) result = caseStsObject(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.IRESOURCE: {
				IResource iResource = (IResource)theEObject;
				T result = caseIResource(iResource);
				if (result == null) result = caseResource(iResource);
				if (result == null) result = caseStsElement(iResource);
				if (result == null) result = caseStsObject(iResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.STS_ELEMENT: {
				StsElement stsElement = (StsElement)theEObject;
				T result = caseStsElement(stsElement);
				if (result == null) result = caseStsObject(stsElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.NON_TRANSFERABLE_AUTHORISATION: {
				NonTransferableAuthorisation nonTransferableAuthorisation = (NonTransferableAuthorisation)theEObject;
				T result = caseNonTransferableAuthorisation(nonTransferableAuthorisation);
				if (result == null) result = caseAuthorisation(nonTransferableAuthorisation);
				if (result == null) result = caseStsRelation(nonTransferableAuthorisation);
				if (result == null) result = caseStsObject(nonTransferableAuthorisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.TRANSFERABLE_AUTHORISATION: {
				TransferableAuthorisation transferableAuthorisation = (TransferableAuthorisation)theEObject;
				T result = caseTransferableAuthorisation(transferableAuthorisation);
				if (result == null) result = caseAuthorisation(transferableAuthorisation);
				if (result == null) result = caseStsRelation(transferableAuthorisation);
				if (result == null) result = caseStsObject(transferableAuthorisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.GOAL_CONTRIBUTION: {
				GoalContribution goalContribution = (GoalContribution)theEObject;
				T result = caseGoalContribution(goalContribution);
				if (result == null) result = caseStsRelation(goalContribution);
				if (result == null) result = caseStsObject(goalContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.GOAL_DECOMPOSITION: {
				GoalDecomposition goalDecomposition = (GoalDecomposition)theEObject;
				T result = caseGoalDecomposition(goalDecomposition);
				if (result == null) result = caseStsRelation(goalDecomposition);
				if (result == null) result = caseStsObject(goalDecomposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.POSITIVE_GOAL_CONTRIBUTION: {
				PositiveGoalContribution positiveGoalContribution = (PositiveGoalContribution)theEObject;
				T result = casePositiveGoalContribution(positiveGoalContribution);
				if (result == null) result = caseGoalContribution(positiveGoalContribution);
				if (result == null) result = caseStsRelation(positiveGoalContribution);
				if (result == null) result = caseStsObject(positiveGoalContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.NEGATIVE_GOAL_CONTRIBUTION: {
				NegativeGoalContribution negativeGoalContribution = (NegativeGoalContribution)theEObject;
				T result = caseNegativeGoalContribution(negativeGoalContribution);
				if (result == null) result = caseGoalContribution(negativeGoalContribution);
				if (result == null) result = caseStsRelation(negativeGoalContribution);
				if (result == null) result = caseStsObject(negativeGoalContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.GOAL_DECOMPOSITION_AND: {
				GoalDecompositionAND goalDecompositionAND = (GoalDecompositionAND)theEObject;
				T result = caseGoalDecompositionAND(goalDecompositionAND);
				if (result == null) result = caseGoalDecomposition(goalDecompositionAND);
				if (result == null) result = caseStsRelation(goalDecompositionAND);
				if (result == null) result = caseStsObject(goalDecompositionAND);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.GOAL_DECOMPOSITION_OR: {
				GoalDecompositionOR goalDecompositionOR = (GoalDecompositionOR)theEObject;
				T result = caseGoalDecompositionOR(goalDecompositionOR);
				if (result == null) result = caseGoalDecomposition(goalDecompositionOR);
				if (result == null) result = caseStsRelation(goalDecompositionOR);
				if (result == null) result = caseStsObject(goalDecompositionOR);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.THREATABLE: {
				Threatable threatable = (Threatable)theEObject;
				T result = caseThreatable(threatable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.EVENT: {
				Event event = (Event)theEObject;
				T result = caseEvent(event);
				if (result == null) result = caseStsElement(event);
				if (result == null) result = caseStsObject(event);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.OWN: {
				Own own = (Own)theEObject;
				T result = caseOwn(own);
				if (result == null) result = caseStsRelation(own);
				if (result == null) result = caseStsObject(own);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.PLAY: {
				Play play = (Play)theEObject;
				T result = casePlay(play);
				if (result == null) result = caseStsRelation(play);
				if (result == null) result = caseStsObject(play);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.TANGIBLE_BY: {
				TangibleBy tangibleBy = (TangibleBy)theEObject;
				T result = caseTangibleBy(tangibleBy);
				if (result == null) result = caseStsRelation(tangibleBy);
				if (result == null) result = caseStsObject(tangibleBy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.PART_OF: {
				PartOf partOf = (PartOf)theEObject;
				T result = casePartOf(partOf);
				if (result == null) result = caseStsRelation(partOf);
				if (result == null) result = caseStsObject(partOf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.NEED: {
				Need need = (Need)theEObject;
				T result = caseNeed(need);
				if (result == null) result = caseStsRelation(need);
				if (result == null) result = caseStsObject(need);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.PRODUCE: {
				Produce produce = (Produce)theEObject;
				T result = caseProduce(produce);
				if (result == null) result = caseStsRelation(produce);
				if (result == null) result = caseStsObject(produce);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.MODIFY: {
				Modify modify = (Modify)theEObject;
				T result = caseModify(modify);
				if (result == null) result = caseStsRelation(modify);
				if (result == null) result = caseStsObject(modify);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.THREAT: {
				Threat threat = (Threat)theEObject;
				T result = caseThreat(threat);
				if (result == null) result = caseStsRelation(threat);
				if (result == null) result = caseStsObject(threat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.STS_RELATION: {
				StsRelation stsRelation = (StsRelation)theEObject;
				T result = caseStsRelation(stsRelation);
				if (result == null) result = caseStsObject(stsRelation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.STS_OBJECT: {
				StsObject stsObject = (StsObject)theEObject;
				T result = caseStsObject(stsObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.STRING_TO_STRING_MAP: {
				@SuppressWarnings("unchecked") Map.Entry<String, String> stringToStringMap = (Map.Entry<String, String>)theEObject;
				T result = caseStringToStringMap(stringToStringMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.INCOMPATIBLE_DUTIES: {
				IncompatibleDuties incompatibleDuties = (IncompatibleDuties)theEObject;
				T result = caseIncompatibleDuties(incompatibleDuties);
				if (result == null) result = caseStsRelation(incompatibleDuties);
				if (result == null) result = caseStsObject(incompatibleDuties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.SEPARATION_OF_DUTIES: {
				SeparationOfDuties separationOfDuties = (SeparationOfDuties)theEObject;
				T result = caseSeparationOfDuties(separationOfDuties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.BINDING_OF_DUTIES: {
				BindingOfDuties bindingOfDuties = (BindingOfDuties)theEObject;
				T result = caseBindingOfDuties(bindingOfDuties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.COMPATIBLE_DUTIES: {
				CompatibleDuties compatibleDuties = (CompatibleDuties)theEObject;
				T result = caseCompatibleDuties(compatibleDuties);
				if (result == null) result = caseStsRelation(compatibleDuties);
				if (result == null) result = caseStsObject(compatibleDuties);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case StstoolPackage.DEPENDENCY: {
				Dependency dependency = (Dependency)theEObject;
				T result = caseDependency(dependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sts Tool Diagram</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sts Tool Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStsToolDiagram(StsToolDiagram object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActor(Actor object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Delegation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Delegation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDelegation(Delegation object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provision</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provision</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProvision(Provision object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgent(Agent object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResource(TResource object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoal(Goal object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Authorisation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Authorisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAuthorisation(Authorisation object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResource(Resource object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IResource</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IResource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIResource(IResource object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sts Element</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sts Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStsElement(StsElement object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Transferable Authorisation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Transferable Authorisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNonTransferableAuthorisation(NonTransferableAuthorisation object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transferable Authorisation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transferable Authorisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransferableAuthorisation(TransferableAuthorisation object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal Contribution</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalContribution(GoalContribution object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal Decomposition</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal Decomposition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalDecomposition(GoalDecomposition object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Positive Goal Contribution</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Positive Goal Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePositiveGoalContribution(PositiveGoalContribution object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Negative Goal Contribution</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Negative Goal Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNegativeGoalContribution(NegativeGoalContribution object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal Decomposition AND</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal Decomposition AND</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalDecompositionAND(GoalDecompositionAND object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal Decomposition OR</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal Decomposition OR</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoalDecompositionOR(GoalDecompositionOR object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Threatable</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Threatable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThreatable(Threatable object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvent(Event object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Own</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Own</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOwn(Own object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Play</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Play</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlay(Play object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tangible By</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tangible By</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTangibleBy(TangibleBy object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Part Of</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Part Of</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePartOf(PartOf object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Need</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Need</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNeed(Need object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Produce</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Produce</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduce(Produce object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modify</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modify</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModify(Modify object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Threat</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Threat</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThreat(Threat object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sts Relation</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sts Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStsRelation(StsRelation object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sts Object</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sts Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStsObject(StsObject object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringToStringMap(Map.Entry<String, String> object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Incompatible Duties</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Incompatible Duties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIncompatibleDuties(IncompatibleDuties object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Separation Of Duties</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Separation Of Duties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSeparationOfDuties(SeparationOfDuties object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Binding Of Duties</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Binding Of Duties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBindingOfDuties(BindingOfDuties object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compatible Duties</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compatible Duties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompatibleDuties(CompatibleDuties object){
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependency(Dependency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object){
		return null;
	}

} //StstoolSwitch
