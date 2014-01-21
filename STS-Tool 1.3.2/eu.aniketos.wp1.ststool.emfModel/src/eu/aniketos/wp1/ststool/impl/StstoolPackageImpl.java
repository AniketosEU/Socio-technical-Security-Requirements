/*
* StstoolPackageImpl.java
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

import static eu.aniketos.wp1.ststool.StstoolPackage.RESOURCE;
import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Dependency;
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
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsRelation;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;
import eu.aniketos.wp1.ststool.TransferableAuthorisation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class StstoolPackageImpl extends EPackageImpl implements StstoolPackage {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String	copyright									= "DISI - University of Trento";

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					stsToolDiagramEClass						= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					actorEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					delegationEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					provisionEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					agentEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					roleEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					tResourceEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					goalEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					authorisationEClass						= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					resourceEClass								= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					iResourceEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					stsElementEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					nonTransferableAuthorisationEClass	= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					transferableAuthorisationEClass		= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					goalContributionEClass					= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					goalDecompositionEClass					= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					positiveGoalContributionEClass		= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					negativeGoalContributionEClass		= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					goalDecompositionANDEClass				= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					goalDecompositionOREClass				= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					threatableEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					eventEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					ownEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					playEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					tangibleByEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					partOfEClass								= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					needEClass									= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					produceEClass								= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					modifyEClass								= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					threatEClass								= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					stsRelationEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					stsObjectEClass							= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					stringToStringMapEClass					= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					incompatibleDutiesEClass				= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					separationOfDutiesEClass				= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					bindingOfDutiesEClass					= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass					compatibleDutiesEClass					= null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependencyEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum					redundancyTypeEEnum						= null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum					repudiationTypeEEnum						= null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StstoolPackageImpl() {
		super(eNS_URI, StstoolFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean	isInited	= false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link StstoolPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StstoolPackage init(){
		if (isInited) return (StstoolPackage)EPackage.Registry.INSTANCE.getEPackage(StstoolPackage.eNS_URI);

		// Obtain or create and register package
		StstoolPackageImpl theStstoolPackage = (StstoolPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StstoolPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StstoolPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theStstoolPackage.createPackageContents();

		// Initialize created meta-data
		theStstoolPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStstoolPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StstoolPackage.eNS_URI, theStstoolPackage);
		return theStstoolPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStsToolDiagram(){
		return stsToolDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_DiagActors(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_DiagIResources(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_DiagTResources(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_DiagGoals(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_DiagEvents(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStsToolDiagram_GraphicalConstraintMap(){
		return (EReference)stsToolDiagramEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActor(){
		return actorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IncomingDelegations(){
		return (EReference)actorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_OutgoingDelegations(){
		return (EReference)actorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_Goals(){
		return (EReference)actorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_OutgoingProvisions(){
		return (EReference)actorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IncomingProvisions(){
		return (EReference)actorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_OutgoingAuthorisations(){
		return (EReference)actorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IncomingAuthorisations(){
		return (EReference)actorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IResources(){
		return (EReference)actorEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_TResources(){
		return (EReference)actorEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDelegation(){
		return delegationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_SourceGoal(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_TargetGoal(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_PreviousDelegation(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_NextDelegations(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_Target(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDelegation_Source(){
		return (EReference)delegationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_TimesTransferable(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_PreConditions(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_PostConditions(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_ShowSecurityNeeds(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_RedundancyType(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_RepudiationType(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_SecurityNeeds(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_Availability(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_AvailabilityValue(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_Trustworthiness(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDelegation_TrustworthinessValue(){
		return (EAttribute)delegationEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProvision(){
		return provisionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_PreviousProvision(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_NextProvisions(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_Target(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_Source(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_SourceResource(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProvision_TargetResource(){
		return (EReference)provisionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_PreConditions(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_PostConditions(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_ShowSecurityNeeds(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_Integrity(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_SecurityNeeds(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_Availability(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_AvailabilityValue(){
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProvision_Confidentiality() {
		return (EAttribute)provisionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAgent(){
		return agentEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAgent_PlayedRoles(){
		return (EReference)agentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgent_TypeOfOrganisation(){
		return (EAttribute)agentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgent_Abilities(){
		return (EAttribute)agentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgent_PossessedCertificationsAndAccreditations(){
		return (EAttribute)agentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAgent_OtherImportantFeatures(){
		return (EAttribute)agentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole(){
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_PlayedBy(){
		return (EReference)roleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRole_Purpose(){
		return (EAttribute)roleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRole_Mission(){
		return (EAttribute)roleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_DependBy() {
		return (EReference)roleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRole_Dependent() {
		return (EReference)roleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResource(){
		return tResourceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_ProvidedTo(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_ProvidedFrom(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_ActorOwner(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_GoalsModifing(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_GoalsProducing(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_GoalsNeeding(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_IntangibleElements(){
		return (EReference)tResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoal(){
		return goalEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_DelegatedTo(){
		return (EReference)goalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_DelegatedFrom(){
		return (EReference)goalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_Authorisations(){
		return (EReference)goalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_ActorOwner(){
		return (EReference)goalEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_ResourceNeeded(){
		return (EReference)goalEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_ResourcesProduced(){
		return (EReference)goalEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_ResourcesModified(){
		return (EReference)goalEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_OutgoingContributions(){
		return (EReference)goalEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_IncomingContribution(){
		return (EReference)goalEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_OutgoingDecompositions(){
		return (EReference)goalEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoal_IncomingDecompositions(){
		return (EReference)goalEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGoal_Capability(){
		return (EAttribute)goalEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGoal_PreConditions(){
		return (EAttribute)goalEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGoal_PostConditions(){
		return (EAttribute)goalEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAuthorisation(){
		return authorisationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthorisation_Source(){
		return (EReference)authorisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthorisation_Target(){
		return (EReference)authorisationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthorisation_Goals(){
		return (EReference)authorisationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAuthorisation_Resources(){
		return (EReference)authorisationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorisation_TimesTransferable(){
		return (EAttribute)authorisationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorisation_Usage(){
		return (EAttribute)authorisationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorisation_Modification(){
		return (EAttribute)authorisationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorisation_Produce(){
		return (EAttribute)authorisationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAuthorisation_Distribution(){
		return (EAttribute)authorisationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource(){
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_PartsOf(){
		return (EReference)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_SubParts(){
		return (EReference)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIResource(){
		return iResourceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIResource_Authorisations(){
		return (EReference)iResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIResource_Owners(){
		return (EReference)iResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIResource_TangibleElements(){
		return (EReference)iResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStsElement(){
		return stsElementEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStsElement_Name(){
		return (EAttribute)stsElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonTransferableAuthorisation(){
		return nonTransferableAuthorisationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransferableAuthorisation(){
		return transferableAuthorisationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoalContribution(){
		return goalContributionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoalContribution_Source(){
		return (EReference)goalContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoalContribution_Target(){
		return (EReference)goalContributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoalDecomposition(){
		return goalDecompositionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoalDecomposition_Source(){
		return (EReference)goalDecompositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGoalDecomposition_Target(){
		return (EReference)goalDecompositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPositiveGoalContribution(){
		return positiveGoalContributionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNegativeGoalContribution(){
		return negativeGoalContributionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoalDecompositionAND(){
		return goalDecompositionANDEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoalDecompositionOR(){
		return goalDecompositionOREClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThreatable(){
		return threatableEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThreatable_ThreatedElements(){
		return (EReference)threatableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvent(){
		return eventEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvent_ThreatedElements(){
		return (EReference)eventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvent_EventID() {
		return (EAttribute)eventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvent_Countermeasures(){
		return (EAttribute)eventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOwn(){
		return ownEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOwn_Source(){
		return (EReference)ownEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOwn_Target(){
		return (EReference)ownEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlay(){
		return playEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlay_Source(){
		return (EReference)playEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPlay_Target(){
		return (EReference)playEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTangibleBy(){
		return tangibleByEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTangibleBy_Source(){
		return (EReference)tangibleByEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTangibleBy_Target(){
		return (EReference)tangibleByEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPartOf(){
		return partOfEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPartOf_Target(){
		return (EReference)partOfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPartOf_Source(){
		return (EReference)partOfEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNeed(){
		return needEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNeed_Source(){
		return (EReference)needEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNeed_Target(){
		return (EReference)needEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProduce(){
		return produceEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProduce_Source(){
		return (EReference)produceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProduce_Target(){
		return (EReference)produceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModify(){
		return modifyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModify_Source(){
		return (EReference)modifyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModify_Target(){
		return (EReference)modifyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThreat(){
		return threatEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThreat_Target(){
		return (EReference)threatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThreat_Source(){
		return (EReference)threatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStsRelation(){
		return stsRelationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStsObject(){
		return stsObjectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStsObject_StsUniqueID(){
		return (EAttribute)stsObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStsObject_Description(){
		return (EAttribute)stsObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToStringMap(){
		return stringToStringMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToStringMap_Key(){
		return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToStringMap_Value(){
		return (EAttribute)stringToStringMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIncompatibleDuties(){
		return incompatibleDutiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIncompatibleDuties_Source(){
		return (EReference)incompatibleDutiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIncompatibleDuties_Target(){
		return (EReference)incompatibleDutiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSeparationOfDuties(){
		return separationOfDutiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSeparationOfDuties_IncompatibleDutiesOut(){
		return (EReference)separationOfDutiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSeparationOfDuties_IncompatibleDutiesIn(){
		return (EReference)separationOfDutiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBindingOfDuties(){
		return bindingOfDutiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingOfDuties_CompatibleDutiesOut(){
		return (EReference)bindingOfDutiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBindingOfDuties_CompatibleDutiesIn(){
		return (EReference)bindingOfDutiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompatibleDuties(){
		return compatibleDutiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompatibleDuties_Target(){
		return (EReference)compatibleDutiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompatibleDuties_Source(){
		return (EReference)compatibleDutiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependency() {
		return dependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_Source() {
		return (EReference)dependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_Target() {
		return (EReference)dependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRedundancyType(){
		return redundancyTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getRepudiationType(){
		return repudiationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public StstoolFactory getStstoolFactory(){
		return (StstoolFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean	isCreated	= false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents(){
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		stsToolDiagramEClass = createEClass(STS_TOOL_DIAGRAM);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__DIAG_ACTORS);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__DIAG_IRESOURCES);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__DIAG_TRESOURCES);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__DIAG_GOALS);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__DIAG_EVENTS);
		createEReference(stsToolDiagramEClass, STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP);

		actorEClass = createEClass(ACTOR);
		createEReference(actorEClass, ACTOR__INCOMING_DELEGATIONS);
		createEReference(actorEClass, ACTOR__OUTGOING_DELEGATIONS);
		createEReference(actorEClass, ACTOR__GOALS);
		createEReference(actorEClass, ACTOR__OUTGOING_PROVISIONS);
		createEReference(actorEClass, ACTOR__INCOMING_PROVISIONS);
		createEReference(actorEClass, ACTOR__OUTGOING_AUTHORISATIONS);
		createEReference(actorEClass, ACTOR__INCOMING_AUTHORISATIONS);
		createEReference(actorEClass, ACTOR__TRESOURCES);
		createEReference(actorEClass, ACTOR__IRESOURCES);

		delegationEClass = createEClass(DELEGATION);
		createEReference(delegationEClass, DELEGATION__SOURCE_GOAL);
		createEReference(delegationEClass, DELEGATION__TARGET_GOAL);
		createEReference(delegationEClass, DELEGATION__PREVIOUS_DELEGATION);
		createEReference(delegationEClass, DELEGATION__NEXT_DELEGATIONS);
		createEReference(delegationEClass, DELEGATION__TARGET);
		createEReference(delegationEClass, DELEGATION__SOURCE);
		createEAttribute(delegationEClass, DELEGATION__TIMES_TRANSFERABLE);
		createEAttribute(delegationEClass, DELEGATION__PRE_CONDITIONS);
		createEAttribute(delegationEClass, DELEGATION__POST_CONDITIONS);
		createEAttribute(delegationEClass, DELEGATION__SHOW_SECURITY_NEEDS);
		createEAttribute(delegationEClass, DELEGATION__REDUNDANCY_TYPE);
		createEAttribute(delegationEClass, DELEGATION__REPUDIATION_TYPE);
		createEAttribute(delegationEClass, DELEGATION__SECURITY_NEEDS);
		createEAttribute(delegationEClass, DELEGATION__AVAILABILITY);
		createEAttribute(delegationEClass, DELEGATION__AVAILABILITY_VALUE);
		createEAttribute(delegationEClass, DELEGATION__TRUSTWORTHINESS);
		createEAttribute(delegationEClass, DELEGATION__TRUSTWORTHINESS_VALUE);

		provisionEClass = createEClass(PROVISION);
		createEReference(provisionEClass, PROVISION__PREVIOUS_PROVISION);
		createEReference(provisionEClass, PROVISION__NEXT_PROVISIONS);
		createEReference(provisionEClass, PROVISION__TARGET);
		createEReference(provisionEClass, PROVISION__SOURCE);
		createEReference(provisionEClass, PROVISION__SOURCE_RESOURCE);
		createEReference(provisionEClass, PROVISION__TARGET_RESOURCE);
		createEAttribute(provisionEClass, PROVISION__PRE_CONDITIONS);
		createEAttribute(provisionEClass, PROVISION__POST_CONDITIONS);
		createEAttribute(provisionEClass, PROVISION__SHOW_SECURITY_NEEDS);
		createEAttribute(provisionEClass, PROVISION__INTEGRITY);
		createEAttribute(provisionEClass, PROVISION__SECURITY_NEEDS);
		createEAttribute(provisionEClass, PROVISION__AVAILABILITY);
		createEAttribute(provisionEClass, PROVISION__AVAILABILITY_VALUE);
		createEAttribute(provisionEClass, PROVISION__CONFIDENTIALITY);

		agentEClass = createEClass(AGENT);
		createEReference(agentEClass, AGENT__PLAYED_ROLES);
		createEAttribute(agentEClass, AGENT__TYPE_OF_ORGANISATION);
		createEAttribute(agentEClass, AGENT__ABILITIES);
		createEAttribute(agentEClass, AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS);
		createEAttribute(agentEClass, AGENT__OTHER_IMPORTANT_FEATURES);

		roleEClass = createEClass(ROLE);
		createEReference(roleEClass, ROLE__PLAYED_BY);
		createEAttribute(roleEClass, ROLE__PURPOSE);
		createEAttribute(roleEClass, ROLE__MISSION);
		createEReference(roleEClass, ROLE__DEPEND_BY);
		createEReference(roleEClass, ROLE__DEPENDENT);

		tResourceEClass = createEClass(TRESOURCE);
		createEReference(tResourceEClass, TRESOURCE__PROVIDED_TO);
		createEReference(tResourceEClass, TRESOURCE__PROVIDED_FROM);
		createEReference(tResourceEClass, TRESOURCE__ACTOR_OWNER);
		createEReference(tResourceEClass, TRESOURCE__INTANGIBLE_ELEMENTS);
		createEReference(tResourceEClass, TRESOURCE__GOALS_NEEDING);
		createEReference(tResourceEClass, TRESOURCE__GOALS_PRODUCING);
		createEReference(tResourceEClass, TRESOURCE__GOALS_MODIFING);

		goalEClass = createEClass(GOAL);
		createEReference(goalEClass, GOAL__DELEGATED_TO);
		createEReference(goalEClass, GOAL__DELEGATED_FROM);
		createEReference(goalEClass, GOAL__AUTHORISATIONS);
		createEReference(goalEClass, GOAL__ACTOR_OWNER);
		createEReference(goalEClass, GOAL__OUTGOING_CONTRIBUTIONS);
		createEReference(goalEClass, GOAL__INCOMING_CONTRIBUTION);
		createEReference(goalEClass, GOAL__OUTGOING_DECOMPOSITIONS);
		createEReference(goalEClass, GOAL__INCOMING_DECOMPOSITIONS);
		createEAttribute(goalEClass, GOAL__CAPABILITY);
		createEAttribute(goalEClass, GOAL__PRE_CONDITIONS);
		createEAttribute(goalEClass, GOAL__POST_CONDITIONS);
		createEReference(goalEClass, GOAL__RESOURCE_NEEDED);
		createEReference(goalEClass, GOAL__RESOURCES_PRODUCED);
		createEReference(goalEClass, GOAL__RESOURCES_MODIFIED);

		authorisationEClass = createEClass(AUTHORISATION);
		createEReference(authorisationEClass, AUTHORISATION__SOURCE);
		createEReference(authorisationEClass, AUTHORISATION__TARGET);
		createEReference(authorisationEClass, AUTHORISATION__GOALS);
		createEReference(authorisationEClass, AUTHORISATION__RESOURCES);
		createEAttribute(authorisationEClass, AUTHORISATION__TIMES_TRANSFERABLE);
		createEAttribute(authorisationEClass, AUTHORISATION__USAGE);
		createEAttribute(authorisationEClass, AUTHORISATION__MODIFICATION);
		createEAttribute(authorisationEClass, AUTHORISATION__PRODUCE);
		createEAttribute(authorisationEClass, AUTHORISATION__DISTRIBUTION);

		resourceEClass = createEClass(RESOURCE);
		createEReference(resourceEClass, RESOURCE__PARTS_OF);
		createEReference(resourceEClass, RESOURCE__SUB_PARTS);

		iResourceEClass = createEClass(IRESOURCE);
		createEReference(iResourceEClass, IRESOURCE__AUTHORISATIONS);
		createEReference(iResourceEClass, IRESOURCE__OWNERS);
		createEReference(iResourceEClass, IRESOURCE__TANGIBLE_ELEMENTS);

		stsElementEClass = createEClass(STS_ELEMENT);
		createEAttribute(stsElementEClass, STS_ELEMENT__NAME);

		nonTransferableAuthorisationEClass = createEClass(NON_TRANSFERABLE_AUTHORISATION);

		transferableAuthorisationEClass = createEClass(TRANSFERABLE_AUTHORISATION);

		goalContributionEClass = createEClass(GOAL_CONTRIBUTION);
		createEReference(goalContributionEClass, GOAL_CONTRIBUTION__SOURCE);
		createEReference(goalContributionEClass, GOAL_CONTRIBUTION__TARGET);

		goalDecompositionEClass = createEClass(GOAL_DECOMPOSITION);
		createEReference(goalDecompositionEClass, GOAL_DECOMPOSITION__SOURCE);
		createEReference(goalDecompositionEClass, GOAL_DECOMPOSITION__TARGET);

		positiveGoalContributionEClass = createEClass(POSITIVE_GOAL_CONTRIBUTION);

		negativeGoalContributionEClass = createEClass(NEGATIVE_GOAL_CONTRIBUTION);

		goalDecompositionANDEClass = createEClass(GOAL_DECOMPOSITION_AND);

		goalDecompositionOREClass = createEClass(GOAL_DECOMPOSITION_OR);

		threatableEClass = createEClass(THREATABLE);
		createEReference(threatableEClass, THREATABLE__THREATED_ELEMENTS);

		eventEClass = createEClass(EVENT);
		createEAttribute(eventEClass, EVENT__COUNTERMEASURES);
		createEReference(eventEClass, EVENT__THREATED_ELEMENTS);
		createEAttribute(eventEClass, EVENT__EVENT_ID);

		ownEClass = createEClass(OWN);
		createEReference(ownEClass, OWN__SOURCE);
		createEReference(ownEClass, OWN__TARGET);

		playEClass = createEClass(PLAY);
		createEReference(playEClass, PLAY__SOURCE);
		createEReference(playEClass, PLAY__TARGET);

		tangibleByEClass = createEClass(TANGIBLE_BY);
		createEReference(tangibleByEClass, TANGIBLE_BY__SOURCE);
		createEReference(tangibleByEClass, TANGIBLE_BY__TARGET);

		partOfEClass = createEClass(PART_OF);
		createEReference(partOfEClass, PART_OF__TARGET);
		createEReference(partOfEClass, PART_OF__SOURCE);

		needEClass = createEClass(NEED);
		createEReference(needEClass, NEED__SOURCE);
		createEReference(needEClass, NEED__TARGET);

		produceEClass = createEClass(PRODUCE);
		createEReference(produceEClass, PRODUCE__SOURCE);
		createEReference(produceEClass, PRODUCE__TARGET);

		modifyEClass = createEClass(MODIFY);
		createEReference(modifyEClass, MODIFY__SOURCE);
		createEReference(modifyEClass, MODIFY__TARGET);

		threatEClass = createEClass(THREAT);
		createEReference(threatEClass, THREAT__TARGET);
		createEReference(threatEClass, THREAT__SOURCE);

		stsRelationEClass = createEClass(STS_RELATION);

		stsObjectEClass = createEClass(STS_OBJECT);
		createEAttribute(stsObjectEClass, STS_OBJECT__STS_UNIQUE_ID);
		createEAttribute(stsObjectEClass, STS_OBJECT__DESCRIPTION);

		stringToStringMapEClass = createEClass(STRING_TO_STRING_MAP);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__KEY);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__VALUE);

		incompatibleDutiesEClass = createEClass(INCOMPATIBLE_DUTIES);
		createEReference(incompatibleDutiesEClass, INCOMPATIBLE_DUTIES__SOURCE);
		createEReference(incompatibleDutiesEClass, INCOMPATIBLE_DUTIES__TARGET);

		separationOfDutiesEClass = createEClass(SEPARATION_OF_DUTIES);
		createEReference(separationOfDutiesEClass, SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT);
		createEReference(separationOfDutiesEClass, SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN);

		bindingOfDutiesEClass = createEClass(BINDING_OF_DUTIES);
		createEReference(bindingOfDutiesEClass, BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT);
		createEReference(bindingOfDutiesEClass, BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN);

		compatibleDutiesEClass = createEClass(COMPATIBLE_DUTIES);
		createEReference(compatibleDutiesEClass, COMPATIBLE_DUTIES__TARGET);
		createEReference(compatibleDutiesEClass, COMPATIBLE_DUTIES__SOURCE);

		dependencyEClass = createEClass(DEPENDENCY);
		createEReference(dependencyEClass, DEPENDENCY__SOURCE);
		createEReference(dependencyEClass, DEPENDENCY__TARGET);

		// Create enums
		redundancyTypeEEnum = createEEnum(REDUNDANCY_TYPE);
		repudiationTypeEEnum = createEEnum(REPUDIATION_TYPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean	isInitialized	= false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents(){
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		actorEClass.getESuperTypes().add(this.getStsElement());
		actorEClass.getESuperTypes().add(this.getThreatable());
		delegationEClass.getESuperTypes().add(this.getStsRelation());
		delegationEClass.getESuperTypes().add(this.getThreatable());
		provisionEClass.getESuperTypes().add(this.getStsRelation());
		agentEClass.getESuperTypes().add(this.getActor());
		roleEClass.getESuperTypes().add(this.getActor());
		roleEClass.getESuperTypes().add(this.getSeparationOfDuties());
		roleEClass.getESuperTypes().add(this.getBindingOfDuties());
		tResourceEClass.getESuperTypes().add(this.getResource());
		tResourceEClass.getESuperTypes().add(this.getThreatable());
		goalEClass.getESuperTypes().add(this.getStsElement());
		goalEClass.getESuperTypes().add(this.getThreatable());
		goalEClass.getESuperTypes().add(this.getSeparationOfDuties());
		goalEClass.getESuperTypes().add(this.getBindingOfDuties());
		authorisationEClass.getESuperTypes().add(this.getStsRelation());
		resourceEClass.getESuperTypes().add(this.getStsElement());
		iResourceEClass.getESuperTypes().add(this.getResource());
		stsElementEClass.getESuperTypes().add(this.getStsObject());
		nonTransferableAuthorisationEClass.getESuperTypes().add(this.getAuthorisation());
		transferableAuthorisationEClass.getESuperTypes().add(this.getAuthorisation());
		goalContributionEClass.getESuperTypes().add(this.getStsRelation());
		goalDecompositionEClass.getESuperTypes().add(this.getStsRelation());
		positiveGoalContributionEClass.getESuperTypes().add(this.getGoalContribution());
		negativeGoalContributionEClass.getESuperTypes().add(this.getGoalContribution());
		goalDecompositionANDEClass.getESuperTypes().add(this.getGoalDecomposition());
		goalDecompositionOREClass.getESuperTypes().add(this.getGoalDecomposition());
		eventEClass.getESuperTypes().add(this.getStsElement());
		ownEClass.getESuperTypes().add(this.getStsRelation());
		playEClass.getESuperTypes().add(this.getStsRelation());
		tangibleByEClass.getESuperTypes().add(this.getStsRelation());
		partOfEClass.getESuperTypes().add(this.getStsRelation());
		needEClass.getESuperTypes().add(this.getStsRelation());
		produceEClass.getESuperTypes().add(this.getStsRelation());
		modifyEClass.getESuperTypes().add(this.getStsRelation());
		threatEClass.getESuperTypes().add(this.getStsRelation());
		stsRelationEClass.getESuperTypes().add(this.getStsObject());
		incompatibleDutiesEClass.getESuperTypes().add(this.getStsRelation());
		compatibleDutiesEClass.getESuperTypes().add(this.getStsRelation());

		// Initialize classes and features; add operations and parameters
		initEClass(stsToolDiagramEClass, StsToolDiagram.class, "StsToolDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStsToolDiagram_DiagActors(), this.getActor(), null, "diagActors", null, 0, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStsToolDiagram_DiagIResources(), this.getIResource(), null, "diagIResources", null, 0, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStsToolDiagram_DiagTResources(), this.getTResource(), null, "diagTResources", null, 0, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStsToolDiagram_DiagGoals(), this.getGoal(), null, "diagGoals", null, 0, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStsToolDiagram_DiagEvents(), this.getEvent(), null, "diagEvents", null, 0, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStsToolDiagram_GraphicalConstraintMap(), this.getStringToStringMap(), null, "graphicalConstraintMap", null, 1, -1, StsToolDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActor_IncomingDelegations(), this.getDelegation(), this.getDelegation_Target(), "incomingDelegations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_OutgoingDelegations(), this.getDelegation(), this.getDelegation_Source(), "outgoingDelegations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_Goals(), this.getGoal(), this.getGoal_ActorOwner(), "goals", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_OutgoingProvisions(), this.getProvision(), this.getProvision_Source(), "outgoingProvisions", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_IncomingProvisions(), this.getProvision(), this.getProvision_Target(), "incomingProvisions", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_OutgoingAuthorisations(), this.getAuthorisation(), this.getAuthorisation_Source(), "outgoingAuthorisations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_IncomingAuthorisations(), this.getAuthorisation(), this.getAuthorisation_Target(), "incomingAuthorisations", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_TResources(), this.getTResource(), this.getTResource_ActorOwner(), "tResources", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_IResources(), this.getOwn(), this.getOwn_Source(), "iResources", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(actorEClass, ecorePackage.getEBoolean(), "isDeletable", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(delegationEClass, Delegation.class, "Delegation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDelegation_SourceGoal(), this.getGoal(), null, "sourceGoal", null, 1, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDelegation_TargetGoal(), this.getGoal(), this.getGoal_DelegatedFrom(), "targetGoal", null, 1, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDelegation_PreviousDelegation(), this.getDelegation(), null, "previousDelegation", null, 0, -1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDelegation_NextDelegations(), this.getDelegation(), null, "nextDelegations", null, 0, -1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDelegation_Target(), this.getActor(), this.getActor_IncomingDelegations(), "target", null, 1, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDelegation_Source(), this.getActor(), this.getActor_OutgoingDelegations(), "source", null, 1, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_TimesTransferable(), ecorePackage.getEInt(), "timesTransferable", "-1", 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_PreConditions(), ecorePackage.getEString(), "preConditions", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_PostConditions(), ecorePackage.getEString(), "postConditions", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_ShowSecurityNeeds(), ecorePackage.getEBoolean(), "showSecurityNeeds", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_RedundancyType(), this.getRedundancyType(), "redundancyType", "RedundancyType.NO_REDUNDANCY", 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_RepudiationType(), this.getRepudiationType(), "repudiationType", "RepudiationType.NO_REPUDIATION", 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_SecurityNeeds(), ecorePackage.getEString(), "securityNeeds", "", 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_Availability(), ecorePackage.getEBoolean(), "availability", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_AvailabilityValue(), ecorePackage.getEInt(), "availabilityValue", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_Trustworthiness(), ecorePackage.getEBoolean(), "trustworthiness", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDelegation_TrustworthinessValue(), ecorePackage.getEInt(), "trustworthinessValue", null, 0, 1, Delegation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(delegationEClass, ecorePackage.getEBoolean(), "isDeletable", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(delegationEClass, ecorePackage.getEBoolean(), "canBeTransferred", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(provisionEClass, Provision.class, "Provision", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProvision_PreviousProvision(), this.getProvision(), null, "previousProvision", null, 0, -1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvision_NextProvisions(), this.getProvision(), null, "nextProvisions", null, 0, -1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvision_Target(), this.getActor(), this.getActor_IncomingProvisions(), "target", null, 1, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvision_Source(), this.getActor(), this.getActor_OutgoingProvisions(), "source", null, 1, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvision_SourceResource(), this.getTResource(), null, "sourceResource", null, 1, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvision_TargetResource(), this.getTResource(), this.getTResource_ProvidedFrom(), "targetResource", null, 1, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_PreConditions(), ecorePackage.getEString(), "preConditions", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_PostConditions(), ecorePackage.getEString(), "postConditions", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_ShowSecurityNeeds(), ecorePackage.getEBoolean(), "showSecurityNeeds", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_Integrity(), ecorePackage.getEBoolean(), "integrity", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_SecurityNeeds(), ecorePackage.getEString(), "securityNeeds", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_Availability(), ecorePackage.getEBoolean(), "availability", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_AvailabilityValue(), ecorePackage.getEInt(), "availabilityValue", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvision_Confidentiality(), ecorePackage.getEBoolean(), "confidentiality", null, 0, 1, Provision.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(provisionEClass, ecorePackage.getEBoolean(), "isDeletable", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(provisionEClass, ecorePackage.getEBoolean(), "canBeTransferred", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(agentEClass, Agent.class, "Agent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAgent_PlayedRoles(), this.getPlay(), this.getPlay_Source(), "playedRoles", null, 0, -1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgent_TypeOfOrganisation(), ecorePackage.getEString(), "typeOfOrganisation", null, 0, 1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgent_Abilities(), ecorePackage.getEString(), "abilities", null, 0, 1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgent_PossessedCertificationsAndAccreditations(), ecorePackage.getEString(), "possessedCertificationsAndAccreditations", null, 0, 1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAgent_OtherImportantFeatures(), ecorePackage.getEString(), "otherImportantFeatures", null, 0, 1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRole_PlayedBy(), this.getPlay(), this.getPlay_Target(), "playedBy", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRole_Purpose(), ecorePackage.getEString(), "purpose", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRole_Mission(), ecorePackage.getEString(), "mission", null, 0, 1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRole_DependBy(), this.getDependency(), this.getDependency_Source(), "dependBy", null, 1, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRole_Dependent(), this.getDependency(), this.getDependency_Target(), "dependent", null, 0, -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tResourceEClass, TResource.class, "TResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTResource_ProvidedTo(), this.getProvision(), null, "providedTo", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_ProvidedFrom(), this.getProvision(), this.getProvision_TargetResource(), "providedFrom", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_ActorOwner(), this.getActor(), this.getActor_TResources(), "actorOwner", null, 0, 1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_IntangibleElements(), this.getTangibleBy(), this.getTangibleBy_Target(), "intangibleElements", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_GoalsNeeding(), this.getNeed(), this.getNeed_Target(), "goalsNeeding", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_GoalsProducing(), this.getProduce(), this.getProduce_Target(), "goalsProducing", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTResource_GoalsModifing(), this.getModify(), this.getModify_Target(), "goalsModifing", null, 0, -1, TResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(tResourceEClass, ecorePackage.getEBoolean(), "isDeletable", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(tResourceEClass, ecorePackage.getEBoolean(), "canBeProvided", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(goalEClass, Goal.class, "Goal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGoal_DelegatedTo(), this.getDelegation(), null, "delegatedTo", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_DelegatedFrom(), this.getDelegation(), this.getDelegation_TargetGoal(), "delegatedFrom", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_Authorisations(), this.getAuthorisation(), this.getAuthorisation_Goals(), "authorisations", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_ActorOwner(), this.getActor(), this.getActor_Goals(), "actorOwner", null, 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_OutgoingContributions(), this.getGoalContribution(), this.getGoalContribution_Source(), "outgoingContributions", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_IncomingContribution(), this.getGoalContribution(), this.getGoalContribution_Target(), "incomingContribution", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_OutgoingDecompositions(), this.getGoalDecomposition(), this.getGoalDecomposition_Source(), "outgoingDecompositions", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_IncomingDecompositions(), this.getGoalDecomposition(), this.getGoalDecomposition_Target(), "incomingDecompositions", null, 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGoal_Capability(), ecorePackage.getEBoolean(), "capability", null, 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGoal_PreConditions(), ecorePackage.getEString(), "preConditions", null, 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGoal_PostConditions(), ecorePackage.getEString(), "postConditions", null, 0, 1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_ResourceNeeded(), this.getNeed(), this.getNeed_Source(), "resourceNeeded", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_ResourcesProduced(), this.getProduce(), this.getProduce_Source(), "resourcesProduced", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoal_ResourcesModified(), this.getModify(), this.getModify_Source(), "resourcesModified", null, 0, -1, Goal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(goalEClass, ecorePackage.getEBoolean(), "isDeletable", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(goalEClass, ecorePackage.getEBoolean(), "canBeDelegated", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(authorisationEClass, Authorisation.class, "Authorisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAuthorisation_Source(), this.getActor(), this.getActor_OutgoingAuthorisations(), "source", null, 1, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAuthorisation_Target(), this.getActor(), this.getActor_IncomingAuthorisations(), "target", null, 1, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAuthorisation_Goals(), this.getGoal(), this.getGoal_Authorisations(), "goals", null, 0, -1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAuthorisation_Resources(), this.getIResource(), this.getIResource_Authorisations(), "resources", null, 0, -1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthorisation_TimesTransferable(), ecorePackage.getEInt(), "timesTransferable", "-1", 0, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthorisation_Usage(), ecorePackage.getEBoolean(), "usage", null, 0, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthorisation_Modification(), ecorePackage.getEBoolean(), "modification", null, 0, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthorisation_Produce(), ecorePackage.getEBoolean(), "produce", null, 0, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAuthorisation_Distribution(), ecorePackage.getEBoolean(), "distribution", null, 0, 1, Authorisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResource_PartsOf(), this.getPartOf(), this.getPartOf_Source(), "partsOf", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_SubParts(), this.getPartOf(), this.getPartOf_Target(), "subParts", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iResourceEClass, IResource.class, "IResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIResource_Authorisations(), this.getAuthorisation(), this.getAuthorisation_Resources(), "authorisations", null, 0, -1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIResource_Owners(), this.getOwn(), this.getOwn_Target(), "owners", null, 0, -1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIResource_TangibleElements(), this.getTangibleBy(), this.getTangibleBy_Source(), "tangibleElements", null, 0, -1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stsElementEClass, StsElement.class, "StsElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStsElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, StsElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nonTransferableAuthorisationEClass, NonTransferableAuthorisation.class, "NonTransferableAuthorisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(transferableAuthorisationEClass, TransferableAuthorisation.class, "TransferableAuthorisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(goalContributionEClass, GoalContribution.class, "GoalContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGoalContribution_Source(), this.getGoal(), this.getGoal_OutgoingContributions(), "source", null, 1, 1, GoalContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoalContribution_Target(), this.getGoal(), this.getGoal_IncomingContribution(), "target", null, 1, 1, GoalContribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(goalDecompositionEClass, GoalDecomposition.class, "GoalDecomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGoalDecomposition_Source(), this.getGoal(), this.getGoal_OutgoingDecompositions(), "source", null, 0, 1, GoalDecomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGoalDecomposition_Target(), this.getGoal(), this.getGoal_IncomingDecompositions(), "target", null, 0, 1, GoalDecomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(positiveGoalContributionEClass, PositiveGoalContribution.class, "PositiveGoalContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(negativeGoalContributionEClass, NegativeGoalContribution.class, "NegativeGoalContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(goalDecompositionANDEClass, GoalDecompositionAND.class, "GoalDecompositionAND", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(goalDecompositionOREClass, GoalDecompositionOR.class, "GoalDecompositionOR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(threatableEClass, Threatable.class, "Threatable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getThreatable_ThreatedElements(), this.getThreat(), this.getThreat_Target(), "threatedElements", null, 0, -1, Threatable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvent_Countermeasures(), ecorePackage.getEString(), "countermeasures", null, 0, 1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvent_ThreatedElements(), this.getThreat(), this.getThreat_Source(), "threatedElements", null, 0, -1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvent_EventID(), ecorePackage.getEString(), "eventID", null, 0, 1, Event.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ownEClass, Own.class, "Own", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOwn_Source(), this.getActor(), this.getActor_IResources(), "source", null, 1, 1, Own.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOwn_Target(), this.getIResource(), this.getIResource_Owners(), "target", null, 1, 1, Own.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(playEClass, Play.class, "Play", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPlay_Source(), this.getAgent(), this.getAgent_PlayedRoles(), "source", null, 1, 1, Play.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPlay_Target(), this.getRole(), this.getRole_PlayedBy(), "target", null, 1, 1, Play.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tangibleByEClass, TangibleBy.class, "TangibleBy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTangibleBy_Source(), this.getIResource(), this.getIResource_TangibleElements(), "source", null, 1, 1, TangibleBy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTangibleBy_Target(), this.getTResource(), this.getTResource_IntangibleElements(), "target", null, 1, 1, TangibleBy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(partOfEClass, PartOf.class, "PartOf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPartOf_Target(), this.getResource(), this.getResource_SubParts(), "target", null, 1, 1, PartOf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPartOf_Source(), this.getResource(), this.getResource_PartsOf(), "source", null, 1, 1, PartOf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(needEClass, Need.class, "Need", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNeed_Source(), this.getGoal(), this.getGoal_ResourceNeeded(), "source", null, 1, 1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNeed_Target(), this.getTResource(), this.getTResource_GoalsNeeding(), "target", null, 1, 1, Need.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(produceEClass, Produce.class, "Produce", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProduce_Source(), this.getGoal(), this.getGoal_ResourcesProduced(), "source", null, 1, 1, Produce.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduce_Target(), this.getTResource(), this.getTResource_GoalsProducing(), "target", null, 1, 1, Produce.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modifyEClass, Modify.class, "Modify", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModify_Source(), this.getGoal(), this.getGoal_ResourcesModified(), "source", null, 1, 1, Modify.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModify_Target(), this.getTResource(), this.getTResource_GoalsModifing(), "target", null, 1, 1, Modify.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(threatEClass, Threat.class, "Threat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getThreat_Target(), this.getThreatable(), this.getThreatable_ThreatedElements(), "target", null, 1, 1, Threat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getThreat_Source(), this.getEvent(), this.getEvent_ThreatedElements(), "source", null, 1, 1, Threat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stsRelationEClass, StsRelation.class, "StsRelation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stsObjectEClass, StsObject.class, "StsObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStsObject_StsUniqueID(), ecorePackage.getEString(), "stsUniqueID", null, 0, 1, StsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStsObject_Description(), ecorePackage.getEString(), "description", null, 0, 1, StsObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(incompatibleDutiesEClass, IncompatibleDuties.class, "IncompatibleDuties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncompatibleDuties_Source(), this.getSeparationOfDuties(), this.getSeparationOfDuties_IncompatibleDutiesOut(), "source", null, 1, 1, IncompatibleDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIncompatibleDuties_Target(), this.getSeparationOfDuties(), this.getSeparationOfDuties_IncompatibleDutiesIn(), "target", null, 1, 1, IncompatibleDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(separationOfDutiesEClass, SeparationOfDuties.class, "SeparationOfDuties", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSeparationOfDuties_IncompatibleDutiesOut(), this.getIncompatibleDuties(), this.getIncompatibleDuties_Source(), "incompatibleDutiesOut", null, 0, -1, SeparationOfDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSeparationOfDuties_IncompatibleDutiesIn(), this.getIncompatibleDuties(), this.getIncompatibleDuties_Target(), "incompatibleDutiesIn", null, 0, -1, SeparationOfDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bindingOfDutiesEClass, BindingOfDuties.class, "BindingOfDuties", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBindingOfDuties_CompatibleDutiesOut(), this.getCompatibleDuties(), this.getCompatibleDuties_Source(), "compatibleDutiesOut", null, 1, -1, BindingOfDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBindingOfDuties_CompatibleDutiesIn(), this.getCompatibleDuties(), this.getCompatibleDuties_Target(), "compatibleDutiesIn", null, 1, -1, BindingOfDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compatibleDutiesEClass, CompatibleDuties.class, "CompatibleDuties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompatibleDuties_Target(), this.getBindingOfDuties(), this.getBindingOfDuties_CompatibleDutiesIn(), "target", null, 1, 1, CompatibleDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompatibleDuties_Source(), this.getBindingOfDuties(), this.getBindingOfDuties_CompatibleDutiesOut(), "source", null, 1, 1, CompatibleDuties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependency_Source(), this.getRole(), this.getRole_DependBy(), "source", null, 1, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDependency_Target(), this.getRole(), this.getRole_Dependent(), "target", null, 1, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(redundancyTypeEEnum, RedundancyType.class, "RedundancyType");
		addEEnumLiteral(redundancyTypeEEnum, RedundancyType.NO_REDUNDANCY);
		addEEnumLiteral(redundancyTypeEEnum, RedundancyType.TRUE_SINGLE);
		addEEnumLiteral(redundancyTypeEEnum, RedundancyType.TRUE_MULTI);
		addEEnumLiteral(redundancyTypeEEnum, RedundancyType.FALLBACK_SINGLE);
		addEEnumLiteral(redundancyTypeEEnum, RedundancyType.FALLBACK_MULTI);

		initEEnum(repudiationTypeEEnum, RepudiationType.class, "RepudiationType");
		addEEnumLiteral(repudiationTypeEEnum, RepudiationType.NO_REPUDIATION);
		addEEnumLiteral(repudiationTypeEEnum, RepudiationType.DELEGATOR_REPUDIATION);
		addEEnumLiteral(repudiationTypeEEnum, RepudiationType.DUAL_REPUDIATION);
		addEEnumLiteral(repudiationTypeEEnum, RepudiationType.DELEGATEEE_REPUDIATION);

		// Create resource
		createResource(eNS_URI);
	}

} //StstoolPackageImpl
