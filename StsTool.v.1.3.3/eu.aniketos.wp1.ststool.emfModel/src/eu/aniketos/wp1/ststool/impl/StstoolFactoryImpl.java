/*
* StstoolFactoryImpl.java
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

import eu.aniketos.wp1.ststool.*;
import java.util.Map;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
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
import eu.aniketos.wp1.ststool.RedundancyType;
import eu.aniketos.wp1.ststool.RepudiationType;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsRelation;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.TransferableAuthorisation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class StstoolFactoryImpl extends EFactoryImpl implements StstoolFactory {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String	copyright	= "DISI - University of Trento";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static StstoolFactory init(){
		try {
			StstoolFactory theStstoolFactory = (StstoolFactory)EPackage.Registry.INSTANCE.getEFactory("http://ststool/1.3.0"); 
			if (theStstoolFactory != null) {
				return theStstoolFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StstoolFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StstoolFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass){
		switch (eClass.getClassifierID()) {
			case StstoolPackage.STS_TOOL_DIAGRAM: return createStsToolDiagram();
			case StstoolPackage.ACTOR: return createActor();
			case StstoolPackage.DELEGATION: return createDelegation();
			case StstoolPackage.PROVISION: return createProvision();
			case StstoolPackage.AGENT: return createAgent();
			case StstoolPackage.ROLE: return createRole();
			case StstoolPackage.TRESOURCE: return createTResource();
			case StstoolPackage.GOAL: return createGoal();
			case StstoolPackage.AUTHORISATION: return createAuthorisation();
			case StstoolPackage.RESOURCE: return createResource();
			case StstoolPackage.IRESOURCE: return createIResource();
			case StstoolPackage.NON_TRANSFERABLE_AUTHORISATION: return createNonTransferableAuthorisation();
			case StstoolPackage.TRANSFERABLE_AUTHORISATION: return createTransferableAuthorisation();
			case StstoolPackage.GOAL_CONTRIBUTION: return createGoalContribution();
			case StstoolPackage.GOAL_DECOMPOSITION: return createGoalDecomposition();
			case StstoolPackage.POSITIVE_GOAL_CONTRIBUTION: return createPositiveGoalContribution();
			case StstoolPackage.NEGATIVE_GOAL_CONTRIBUTION: return createNegativeGoalContribution();
			case StstoolPackage.GOAL_DECOMPOSITION_AND: return createGoalDecompositionAND();
			case StstoolPackage.GOAL_DECOMPOSITION_OR: return createGoalDecompositionOR();
			case StstoolPackage.EVENT: return createEvent();
			case StstoolPackage.OWN: return createOwn();
			case StstoolPackage.PLAY: return createPlay();
			case StstoolPackage.TANGIBLE_BY: return createTangibleBy();
			case StstoolPackage.PART_OF: return createPartOf();
			case StstoolPackage.NEED: return createNeed();
			case StstoolPackage.PRODUCE: return createProduce();
			case StstoolPackage.MODIFY: return createModify();
			case StstoolPackage.THREAT: return createThreat();
			case StstoolPackage.STS_RELATION: return createStsRelation();
			case StstoolPackage.STS_OBJECT: return createStsObject();
			case StstoolPackage.STRING_TO_STRING_MAP: return (EObject)createStringToStringMap();
			case StstoolPackage.INCOMPATIBLE_DUTIES: return createIncompatibleDuties();
			case StstoolPackage.COMPATIBLE_DUTIES: return createCompatibleDuties();
			case StstoolPackage.DEPENDENCY: return createDependency();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType,String initialValue){
		switch (eDataType.getClassifierID()) {
			case StstoolPackage.REDUNDANCY_TYPE:
				return createRedundancyTypeFromString(eDataType, initialValue);
			case StstoolPackage.REPUDIATION_TYPE:
				return createRepudiationTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType,Object instanceValue){
		switch (eDataType.getClassifierID()) {
			case StstoolPackage.REDUNDANCY_TYPE:
				return convertRedundancyTypeToString(eDataType, instanceValue);
			case StstoolPackage.REPUDIATION_TYPE:
				return convertRepudiationTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StsToolDiagram createStsToolDiagram(){
		StsToolDiagramImpl stsToolDiagram = new StsToolDiagramImpl();
		return stsToolDiagram;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor createActor(){
		ActorImpl actor = new ActorImpl();
		return actor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Delegation createDelegation(){
		DelegationImpl delegation = new DelegationImpl();
		return delegation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Provision createProvision(){
		ProvisionImpl provision = new ProvisionImpl();
		return provision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Agent createAgent(){
		AgentImpl agent = new AgentImpl();
		return agent;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole(){
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TResource createTResource(){
		TResourceImpl tResource = new TResourceImpl();
		return tResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Goal createGoal(){
		GoalImpl goal = new GoalImpl();
		return goal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Authorisation createAuthorisation(){
		AuthorisationImpl authorisation = new AuthorisationImpl();
		return authorisation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource(){
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IResource createIResource(){
		IResourceImpl iResource = new IResourceImpl();
		return iResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NonTransferableAuthorisation createNonTransferableAuthorisation(){
		NonTransferableAuthorisationImpl nonTransferableAuthorisation = new NonTransferableAuthorisationImpl();
		return nonTransferableAuthorisation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TransferableAuthorisation createTransferableAuthorisation(){
		TransferableAuthorisationImpl transferableAuthorisation = new TransferableAuthorisationImpl();
		return transferableAuthorisation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalContribution createGoalContribution(){
		GoalContributionImpl goalContribution = new GoalContributionImpl();
		return goalContribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalDecomposition createGoalDecomposition(){
		GoalDecompositionImpl goalDecomposition = new GoalDecompositionImpl();
		return goalDecomposition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PositiveGoalContribution createPositiveGoalContribution(){
		PositiveGoalContributionImpl positiveGoalContribution = new PositiveGoalContributionImpl();
		return positiveGoalContribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NegativeGoalContribution createNegativeGoalContribution(){
		NegativeGoalContributionImpl negativeGoalContribution = new NegativeGoalContributionImpl();
		return negativeGoalContribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalDecompositionAND createGoalDecompositionAND(){
		GoalDecompositionANDImpl goalDecompositionAND = new GoalDecompositionANDImpl();
		return goalDecompositionAND;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalDecompositionOR createGoalDecompositionOR(){
		GoalDecompositionORImpl goalDecompositionOR = new GoalDecompositionORImpl();
		return goalDecompositionOR;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent(){
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Own createOwn(){
		OwnImpl own = new OwnImpl();
		return own;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Play createPlay(){
		PlayImpl play = new PlayImpl();
		return play;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TangibleBy createTangibleBy(){
		TangibleByImpl tangibleBy = new TangibleByImpl();
		return tangibleBy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PartOf createPartOf(){
		PartOfImpl partOf = new PartOfImpl();
		return partOf;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Need createNeed(){
		NeedImpl need = new NeedImpl();
		return need;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Produce createProduce(){
		ProduceImpl produce = new ProduceImpl();
		return produce;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Modify createModify(){
		ModifyImpl modify = new ModifyImpl();
		return modify;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Threat createThreat(){
		ThreatImpl threat = new ThreatImpl();
		return threat;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StsRelation createStsRelation(){
		StsRelationImpl stsRelation = new StsRelationImpl();
		return stsRelation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StsObject createStsObject(){
		StsObjectImpl stsObject = new StsObjectImpl();
		return stsObject;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createStringToStringMap(){
		StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
		return stringToStringMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IncompatibleDuties createIncompatibleDuties(){
		IncompatibleDutiesImpl incompatibleDuties = new IncompatibleDutiesImpl();
		return incompatibleDuties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CompatibleDuties createCompatibleDuties(){
		CompatibleDutiesImpl compatibleDuties = new CompatibleDutiesImpl();
		return compatibleDuties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dependency createDependency() {
		DependencyImpl dependency = new DependencyImpl();
		return dependency;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RedundancyType createRedundancyTypeFromString(EDataType eDataType,String initialValue){
		RedundancyType result = RedundancyType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRedundancyTypeToString(EDataType eDataType,Object instanceValue){
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public RepudiationType createRepudiationTypeFromString(EDataType eDataType,String initialValue){
		RepudiationType result = RepudiationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRepudiationTypeToString(EDataType eDataType,Object instanceValue){
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StstoolPackage getStstoolPackage(){
		return (StstoolPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StstoolPackage getPackage(){
		return StstoolPackage.eINSTANCE;
	}

} //StstoolFactoryImpl
