/*
* StstoolPackage.java
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolFactory
 * @model kind="package"
 * @generated
 */
public interface StstoolPackage extends EPackage {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String			copyright														= "DISI - University of Trento";

	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String			eNAME																= "ststool";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String			eNS_URI															= "http://ststool/1.3.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String			eNS_PREFIX														= "ststool";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	StstoolPackage	eINSTANCE														= eu.aniketos.wp1.ststool.impl.StstoolPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl <em>Sts Tool Diagram</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsToolDiagram()
	 * @generated
	 */
	int				STS_TOOL_DIAGRAM												= 0;

	/**
	 * The feature id for the '<em><b>Diag Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__DIAG_ACTORS								= 0;

	/**
	 * The feature id for the '<em><b>Diag IResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__DIAG_IRESOURCES						= 1;

	/**
	 * The feature id for the '<em><b>Diag TResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__DIAG_TRESOURCES						= 2;

	/**
	 * The feature id for the '<em><b>Diag Goals</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__DIAG_GOALS								= 3;

	/**
	 * The feature id for the '<em><b>Diag Events</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__DIAG_EVENTS								= 4;

	/**
	 * The feature id for the '<em><b>Graphical Constraint Map</b></em>' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP			= 5;

	/**
	 * The number of structural features of the '<em>Sts Tool Diagram</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_TOOL_DIAGRAM_FEATURE_COUNT							= 6;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.StsObjectImpl <em>Sts Object</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.StsObjectImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsObject()
	 * @generated
	 */
	int				STS_OBJECT														= 31;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_OBJECT__STS_UNIQUE_ID									= 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_OBJECT__DESCRIPTION										= 1;

	/**
	 * The number of structural features of the '<em>Sts Object</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_OBJECT_FEATURE_COUNT									= 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.StsElementImpl <em>Sts Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.StsElementImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsElement()
	 * @generated
	 */
	int				STS_ELEMENT														= 11;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_ELEMENT__STS_UNIQUE_ID									= STS_OBJECT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_ELEMENT__DESCRIPTION									= STS_OBJECT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_ELEMENT__NAME												= STS_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sts Element</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_ELEMENT_FEATURE_COUNT									= STS_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ActorImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getActor()
	 * @generated
	 */
	int				ACTOR																= 1;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__STS_UNIQUE_ID											= STS_ELEMENT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__DESCRIPTION											= STS_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__NAME														= STS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__THREATED_ELEMENTS									= STS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming Delegations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__INCOMING_DELEGATIONS								= STS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Outgoing Delegations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__OUTGOING_DELEGATIONS								= STS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__GOALS													= STS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Outgoing Provisions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__OUTGOING_PROVISIONS									= STS_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Incoming Provisions</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__INCOMING_PROVISIONS									= STS_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Outgoing Authorisations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__OUTGOING_AUTHORISATIONS							= STS_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Incoming Authorisations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__INCOMING_AUTHORISATIONS							= STS_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>TResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__TRESOURCES												= STS_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>IResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR__IRESOURCES												= STS_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ACTOR_FEATURE_COUNT											= STS_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.StsRelationImpl <em>Sts Relation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.StsRelationImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsRelation()
	 * @generated
	 */
	int				STS_RELATION													= 30;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_RELATION__STS_UNIQUE_ID								= STS_OBJECT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_RELATION__DESCRIPTION									= STS_OBJECT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Sts Relation</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STS_RELATION_FEATURE_COUNT									= STS_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.Threatable <em>Threatable</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.Threatable
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getThreatable()
	 * @generated
	 */
	int				THREATABLE														= 20;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.DelegationImpl <em>Delegation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.DelegationImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getDelegation()
	 * @generated
	 */
	int				DELEGATION														= 2;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__STS_UNIQUE_ID									= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__DESCRIPTION										= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__THREATED_ELEMENTS								= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Goal</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__SOURCE_GOAL										= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Goal</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__TARGET_GOAL										= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Previous Delegation</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__PREVIOUS_DELEGATION							= STS_RELATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Next Delegations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__NEXT_DELEGATIONS								= STS_RELATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__TARGET											= STS_RELATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__SOURCE											= STS_RELATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Times Transferable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__TIMES_TRANSFERABLE							= STS_RELATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__PRE_CONDITIONS									= STS_RELATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Post Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__POST_CONDITIONS								= STS_RELATION_FEATURE_COUNT + 9;


	/**
	 * The feature id for the '<em><b>Show Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__SHOW_SECURITY_NEEDS							= STS_RELATION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Redundancy Type</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__REDUNDANCY_TYPE								= STS_RELATION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Repudiation Type</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__REPUDIATION_TYPE								= STS_RELATION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__SECURITY_NEEDS									= STS_RELATION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__AVAILABILITY									= STS_RELATION_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Availability Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__AVAILABILITY_VALUE							= STS_RELATION_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Trustworthiness</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__TRUSTWORTHINESS								= STS_RELATION_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Trustworthiness Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				DELEGATION__TRUSTWORTHINESS_VALUE						= STS_RELATION_FEATURE_COUNT + 17;

	int				DELEGATION__NAME												= STS_RELATION_FEATURE_COUNT + 12;
	/**
	 * The number of structural features of the '<em>Delegation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @ordered
	 */
	int				DELEGATION_FEATURE_COUNT									= STS_RELATION_FEATURE_COUNT + 13;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl <em>Provision</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ProvisionImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getProvision()
	 * @generated
	 */
	int				PROVISION														= 3;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__STS_UNIQUE_ID									= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__DESCRIPTION										= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Previous Provision</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__PREVIOUS_PROVISION								= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Next Provisions</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__NEXT_PROVISIONS									= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__TARGET												= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__SOURCE												= STS_RELATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Source Resource</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__SOURCE_RESOURCE									= STS_RELATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target Resource</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__TARGET_RESOURCE									= STS_RELATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__PRE_CONDITIONS									= STS_RELATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Post Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__POST_CONDITIONS									= STS_RELATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Show Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__SHOW_SECURITY_NEEDS							= STS_RELATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Integrity</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__INTEGRITY											= STS_RELATION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Security Needs</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__SECURITY_NEEDS									= STS_RELATION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__AVAILABILITY										= STS_RELATION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Availability Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION__AVAILABILITY_VALUE								= STS_RELATION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Confidentiality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVISION__CONFIDENTIALITY = STS_RELATION_FEATURE_COUNT + 13;

	int				PROVISION__NAME												= STS_RELATION_FEATURE_COUNT + 11;
	/**
	 * The number of structural features of the '<em>Provision</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PROVISION_FEATURE_COUNT										= STS_RELATION_FEATURE_COUNT + 14;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.AgentImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getAgent()
	 * @generated
	 */
	int				AGENT																= 4;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__STS_UNIQUE_ID											= ACTOR__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__DESCRIPTION											= ACTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__NAME														= ACTOR__NAME;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__THREATED_ELEMENTS									= ACTOR__THREATED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Delegations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__INCOMING_DELEGATIONS								= ACTOR__INCOMING_DELEGATIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Delegations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__OUTGOING_DELEGATIONS								= ACTOR__OUTGOING_DELEGATIONS;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__GOALS													= ACTOR__GOALS;

	/**
	 * The feature id for the '<em><b>Outgoing Provisions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__OUTGOING_PROVISIONS									= ACTOR__OUTGOING_PROVISIONS;

	/**
	 * The feature id for the '<em><b>Incoming Provisions</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__INCOMING_PROVISIONS									= ACTOR__INCOMING_PROVISIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Authorisations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__OUTGOING_AUTHORISATIONS							= ACTOR__OUTGOING_AUTHORISATIONS;

	/**
	 * The feature id for the '<em><b>Incoming Authorisations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__INCOMING_AUTHORISATIONS							= ACTOR__INCOMING_AUTHORISATIONS;

	/**
	 * The feature id for the '<em><b>TResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__TRESOURCES												= ACTOR__TRESOURCES;

	/**
	 * The feature id for the '<em><b>IResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__IRESOURCES												= ACTOR__IRESOURCES;

	/**
	 * The feature id for the '<em><b>Played Roles</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__PLAYED_ROLES											= ACTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Of Organisation</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__TYPE_OF_ORGANISATION								= ACTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Abilities</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__ABILITIES												= ACTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Possessed Certifications And Accreditations</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS	= ACTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Other Important Features</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT__OTHER_IMPORTANT_FEATURES							= ACTOR_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AGENT_FEATURE_COUNT											= ACTOR_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.RoleImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRole()
	 * @generated
	 */
	int				ROLE																= 5;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__STS_UNIQUE_ID											= ACTOR__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__DESCRIPTION												= ACTOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__NAME														= ACTOR__NAME;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__THREATED_ELEMENTS										= ACTOR__THREATED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Delegations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__INCOMING_DELEGATIONS									= ACTOR__INCOMING_DELEGATIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Delegations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__OUTGOING_DELEGATIONS									= ACTOR__OUTGOING_DELEGATIONS;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__GOALS														= ACTOR__GOALS;

	/**
	 * The feature id for the '<em><b>Outgoing Provisions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__OUTGOING_PROVISIONS									= ACTOR__OUTGOING_PROVISIONS;

	/**
	 * The feature id for the '<em><b>Incoming Provisions</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__INCOMING_PROVISIONS									= ACTOR__INCOMING_PROVISIONS;

	/**
	 * The feature id for the '<em><b>Outgoing Authorisations</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__OUTGOING_AUTHORISATIONS								= ACTOR__OUTGOING_AUTHORISATIONS;

	/**
	 * The feature id for the '<em><b>Incoming Authorisations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__INCOMING_AUTHORISATIONS								= ACTOR__INCOMING_AUTHORISATIONS;

	/**
	 * The feature id for the '<em><b>TResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__TRESOURCES												= ACTOR__TRESOURCES;

	/**
	 * The feature id for the '<em><b>IResources</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__IRESOURCES												= ACTOR__IRESOURCES;

	/**
	 * The feature id for the '<em><b>Incompatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__INCOMPATIBLE_DUTIES_OUT								= ACTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incompatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__INCOMPATIBLE_DUTIES_IN								= ACTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Compatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__COMPATIBLE_DUTIES_OUT								= ACTOR_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Compatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__COMPATIBLE_DUTIES_IN									= ACTOR_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Played By</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__PLAYED_BY												= ACTOR_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Purpose</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__PURPOSE													= ACTOR_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mission</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE__MISSION													= ACTOR_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Depend By</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DEPEND_BY = ACTOR_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Dependent</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DEPENDENT = ACTOR_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				ROLE_FEATURE_COUNT											= ACTOR_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ResourceImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getResource()
	 * @generated
	 */
	int				RESOURCE															= 9;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE__STS_UNIQUE_ID										= STS_ELEMENT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE__DESCRIPTION										= STS_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE__NAME													= STS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Parts Of</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE__PARTS_OF											= STS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Parts</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE__SUB_PARTS											= STS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				RESOURCE_FEATURE_COUNT										= STS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.TResourceImpl <em>TResource</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.TResourceImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTResource()
	 * @generated
	 */
	int				TRESOURCE														= 6;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__STS_UNIQUE_ID									= RESOURCE__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__DESCRIPTION										= RESOURCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__NAME												= RESOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Parts Of</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__PARTS_OF											= RESOURCE__PARTS_OF;

	/**
	 * The feature id for the '<em><b>Sub Parts</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__SUB_PARTS											= RESOURCE__SUB_PARTS;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__THREATED_ELEMENTS								= RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Provided To</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__PROVIDED_TO										= RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Provided From</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__PROVIDED_FROM									= RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Actor Owner</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__ACTOR_OWNER										= RESOURCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Intangible Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__INTANGIBLE_ELEMENTS							= RESOURCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Goals Needing</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__GOALS_NEEDING									= RESOURCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Goals Producing</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__GOALS_PRODUCING									= RESOURCE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Goals Modifing</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE__GOALS_MODIFING									= RESOURCE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>TResource</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRESOURCE_FEATURE_COUNT										= RESOURCE_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.GoalImpl <em>Goal</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.GoalImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoal()
	 * @generated
	 */
	int				GOAL																= 7;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__STS_UNIQUE_ID											= STS_ELEMENT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__DESCRIPTION												= STS_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__NAME														= STS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__THREATED_ELEMENTS										= STS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incompatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__INCOMPATIBLE_DUTIES_OUT								= STS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Incompatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__INCOMPATIBLE_DUTIES_IN								= STS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Compatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__COMPATIBLE_DUTIES_OUT								= STS_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Compatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__COMPATIBLE_DUTIES_IN									= STS_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Delegated To</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__DELEGATED_TO											= STS_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Delegated From</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__DELEGATED_FROM											= STS_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Authorisations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__AUTHORISATIONS											= STS_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Actor Owner</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__ACTOR_OWNER												= STS_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Outgoing Contributions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__OUTGOING_CONTRIBUTIONS								= STS_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Incoming Contribution</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__INCOMING_CONTRIBUTION								= STS_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Outgoing Decompositions</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__OUTGOING_DECOMPOSITIONS								= STS_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Incoming Decompositions</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__INCOMING_DECOMPOSITIONS								= STS_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Capability</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__CAPABILITY												= STS_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Pre Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__PRE_CONDITIONS											= STS_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Post Conditions</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__POST_CONDITIONS										= STS_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Resource Needed</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__RESOURCE_NEEDED										= STS_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Resources Produced</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__RESOURCES_PRODUCED									= STS_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Resources Modified</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL__RESOURCES_MODIFIED									= STS_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The number of structural features of the '<em>Goal</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_FEATURE_COUNT											= STS_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl <em>Authorisation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.AuthorisationImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getAuthorisation()
	 * @generated
	 */
	int				AUTHORISATION													= 8;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__STS_UNIQUE_ID								= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__DESCRIPTION									= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__SOURCE										= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__TARGET										= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__GOALS											= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__RESOURCES									= STS_RELATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Times Transferable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__TIMES_TRANSFERABLE						= STS_RELATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__USAGE											= STS_RELATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modification</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__MODIFICATION								= STS_RELATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__PRODUCE										= STS_RELATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Distribution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION__DISTRIBUTION								= STS_RELATION_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Authorisation</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				AUTHORISATION_FEATURE_COUNT								= STS_RELATION_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.IResourceImpl <em>IResource</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.IResourceImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getIResource()
	 * @generated
	 */
	int				IRESOURCE														= 10;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__STS_UNIQUE_ID									= RESOURCE__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__DESCRIPTION										= RESOURCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__NAME												= RESOURCE__NAME;

	/**
	 * The feature id for the '<em><b>Parts Of</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__PARTS_OF											= RESOURCE__PARTS_OF;

	/**
	 * The feature id for the '<em><b>Sub Parts</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__SUB_PARTS											= RESOURCE__SUB_PARTS;

	/**
	 * The feature id for the '<em><b>Authorisations</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__AUTHORISATIONS									= RESOURCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owners</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__OWNERS												= RESOURCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tangible Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE__TANGIBLE_ELEMENTS								= RESOURCE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IResource</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				IRESOURCE_FEATURE_COUNT										= RESOURCE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.NonTransferableAuthorisationImpl <em>Non Transferable Authorisation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.NonTransferableAuthorisationImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNonTransferableAuthorisation()
	 * @generated
	 */
	int				NON_TRANSFERABLE_AUTHORISATION							= 12;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__STS_UNIQUE_ID		= AUTHORISATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__DESCRIPTION			= AUTHORISATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__SOURCE					= AUTHORISATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__TARGET					= AUTHORISATION__TARGET;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__GOALS					= AUTHORISATION__GOALS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__RESOURCES				= AUTHORISATION__RESOURCES;

	/**
	 * The feature id for the '<em><b>Times Transferable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__TIMES_TRANSFERABLE	= AUTHORISATION__TIMES_TRANSFERABLE;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__USAGE					= AUTHORISATION__USAGE;

	/**
	 * The feature id for the '<em><b>Modification</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__MODIFICATION			= AUTHORISATION__MODIFICATION;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__PRODUCE				= AUTHORISATION__PRODUCE;

	/**
	 * The feature id for the '<em><b>Distribution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION__DISTRIBUTION			= AUTHORISATION__DISTRIBUTION;

	/**
	 * The number of structural features of the '<em>Non Transferable Authorisation</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NON_TRANSFERABLE_AUTHORISATION_FEATURE_COUNT			= AUTHORISATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.TransferableAuthorisationImpl <em>Transferable Authorisation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.TransferableAuthorisationImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTransferableAuthorisation()
	 * @generated
	 */
	int				TRANSFERABLE_AUTHORISATION									= 13;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__STS_UNIQUE_ID				= AUTHORISATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__DESCRIPTION				= AUTHORISATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__SOURCE						= AUTHORISATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__TARGET						= AUTHORISATION__TARGET;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__GOALS						= AUTHORISATION__GOALS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__RESOURCES					= AUTHORISATION__RESOURCES;

	/**
	 * The feature id for the '<em><b>Times Transferable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__TIMES_TRANSFERABLE		= AUTHORISATION__TIMES_TRANSFERABLE;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__USAGE						= AUTHORISATION__USAGE;

	/**
	 * The feature id for the '<em><b>Modification</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__MODIFICATION				= AUTHORISATION__MODIFICATION;

	/**
	 * The feature id for the '<em><b>Produce</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__PRODUCE						= AUTHORISATION__PRODUCE;

	/**
	 * The feature id for the '<em><b>Distribution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION__DISTRIBUTION				= AUTHORISATION__DISTRIBUTION;

	/**
	 * The number of structural features of the '<em>Transferable Authorisation</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TRANSFERABLE_AUTHORISATION_FEATURE_COUNT				= AUTHORISATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.GoalContributionImpl <em>Goal Contribution</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.GoalContributionImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalContribution()
	 * @generated
	 */
	int				GOAL_CONTRIBUTION												= 14;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_CONTRIBUTION__STS_UNIQUE_ID							= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_CONTRIBUTION__DESCRIPTION							= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_CONTRIBUTION__SOURCE									= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_CONTRIBUTION__TARGET									= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Goal Contribution</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_CONTRIBUTION_FEATURE_COUNT							= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionImpl <em>Goal Decomposition</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecomposition()
	 * @generated
	 */
	int				GOAL_DECOMPOSITION											= 15;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION__STS_UNIQUE_ID						= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION__DESCRIPTION							= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION__SOURCE									= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION__TARGET									= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Goal Decomposition</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_FEATURE_COUNT							= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.PositiveGoalContributionImpl <em>Positive Goal Contribution</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.PositiveGoalContributionImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPositiveGoalContribution()
	 * @generated
	 */
	int				POSITIVE_GOAL_CONTRIBUTION									= 16;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				POSITIVE_GOAL_CONTRIBUTION__STS_UNIQUE_ID				= GOAL_CONTRIBUTION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				POSITIVE_GOAL_CONTRIBUTION__DESCRIPTION				= GOAL_CONTRIBUTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				POSITIVE_GOAL_CONTRIBUTION__SOURCE						= GOAL_CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				POSITIVE_GOAL_CONTRIBUTION__TARGET						= GOAL_CONTRIBUTION__TARGET;

	/**
	 * The number of structural features of the '<em>Positive Goal Contribution</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				POSITIVE_GOAL_CONTRIBUTION_FEATURE_COUNT				= GOAL_CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.NegativeGoalContributionImpl <em>Negative Goal Contribution</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.NegativeGoalContributionImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNegativeGoalContribution()
	 * @generated
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION									= 17;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION__STS_UNIQUE_ID				= GOAL_CONTRIBUTION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION__DESCRIPTION				= GOAL_CONTRIBUTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION__SOURCE						= GOAL_CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION__TARGET						= GOAL_CONTRIBUTION__TARGET;

	/**
	 * The number of structural features of the '<em>Negative Goal Contribution</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEGATIVE_GOAL_CONTRIBUTION_FEATURE_COUNT				= GOAL_CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionANDImpl <em>Goal Decomposition AND</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionANDImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecompositionAND()
	 * @generated
	 */
	int				GOAL_DECOMPOSITION_AND										= 18;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_AND__STS_UNIQUE_ID					= GOAL_DECOMPOSITION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_AND__DESCRIPTION						= GOAL_DECOMPOSITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_AND__SOURCE							= GOAL_DECOMPOSITION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_AND__TARGET							= GOAL_DECOMPOSITION__TARGET;

	/**
	 * The number of structural features of the '<em>Goal Decomposition AND</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_AND_FEATURE_COUNT					= GOAL_DECOMPOSITION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionORImpl <em>Goal Decomposition OR</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionORImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecompositionOR()
	 * @generated
	 */
	int				GOAL_DECOMPOSITION_OR										= 19;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_OR__STS_UNIQUE_ID					= GOAL_DECOMPOSITION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_OR__DESCRIPTION						= GOAL_DECOMPOSITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_OR__SOURCE								= GOAL_DECOMPOSITION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_OR__TARGET								= GOAL_DECOMPOSITION__TARGET;

	/**
	 * The number of structural features of the '<em>Goal Decomposition OR</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				GOAL_DECOMPOSITION_OR_FEATURE_COUNT						= GOAL_DECOMPOSITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREATABLE__THREATED_ELEMENTS								= 0;

	/**
	 * The number of structural features of the '<em>Threatable</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREATABLE_FEATURE_COUNT									= 1;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.EventImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getEvent()
	 * @generated
	 */
	int				EVENT																= 21;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT__STS_UNIQUE_ID											= STS_ELEMENT__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT__DESCRIPTION											= STS_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT__NAME														= STS_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Countermeasures</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT__COUNTERMEASURES										= STS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Threated Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT__THREATED_ELEMENTS									= STS_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Event ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__EVENT_ID = STS_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				EVENT_FEATURE_COUNT											= STS_ELEMENT_FEATURE_COUNT + 3;


	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.OwnImpl <em>Own</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.OwnImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getOwn()
	 * @generated
	 */
	int				OWN																= 22;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				OWN__STS_UNIQUE_ID											= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				OWN__DESCRIPTION												= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				OWN__SOURCE														= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				OWN__TARGET														= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Own</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				OWN_FEATURE_COUNT												= STS_RELATION_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.PlayImpl <em>Play</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.PlayImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPlay()
	 * @generated
	 */
	int				PLAY																= 23;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PLAY__STS_UNIQUE_ID											= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PLAY__DESCRIPTION												= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PLAY__SOURCE													= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PLAY__TARGET													= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Play</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PLAY_FEATURE_COUNT											= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.TangibleByImpl <em>Tangible By</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.TangibleByImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTangibleBy()
	 * @generated
	 */
	int				TANGIBLE_BY														= 24;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TANGIBLE_BY__STS_UNIQUE_ID									= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TANGIBLE_BY__DESCRIPTION									= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TANGIBLE_BY__SOURCE											= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TANGIBLE_BY__TARGET											= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tangible By</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				TANGIBLE_BY_FEATURE_COUNT									= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.PartOfImpl <em>Part Of</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.PartOfImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPartOf()
	 * @generated
	 */
	int				PART_OF															= 25;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PART_OF__STS_UNIQUE_ID										= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PART_OF__DESCRIPTION											= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PART_OF__TARGET												= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PART_OF__SOURCE												= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Part Of</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PART_OF_FEATURE_COUNT										= STS_RELATION_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.NeedImpl <em>Need</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.NeedImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNeed()
	 * @generated
	 */
	int				NEED																= 26;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEED__STS_UNIQUE_ID											= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEED__DESCRIPTION												= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEED__SOURCE													= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEED__TARGET													= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Need</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				NEED_FEATURE_COUNT											= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ProduceImpl <em>Produce</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ProduceImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getProduce()
	 * @generated
	 */
	int				PRODUCE															= 27;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PRODUCE__STS_UNIQUE_ID										= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PRODUCE__DESCRIPTION											= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PRODUCE__SOURCE												= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PRODUCE__TARGET												= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Produce</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				PRODUCE_FEATURE_COUNT										= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ModifyImpl <em>Modify</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ModifyImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getModify()
	 * @generated
	 */
	int				MODIFY															= 28;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				MODIFY__STS_UNIQUE_ID										= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				MODIFY__DESCRIPTION											= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				MODIFY__SOURCE													= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				MODIFY__TARGET													= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Modify</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				MODIFY_FEATURE_COUNT											= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.ThreatImpl <em>Threat</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.ThreatImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getThreat()
	 * @generated
	 */
	int				THREAT															= 29;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREAT__STS_UNIQUE_ID										= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREAT__DESCRIPTION											= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREAT__TARGET													= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREAT__SOURCE													= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Threat</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				THREAT_FEATURE_COUNT											= STS_RELATION_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.StringToStringMapImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStringToStringMap()
	 * @generated
	 */
	int				STRING_TO_STRING_MAP											= 32;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STRING_TO_STRING_MAP__KEY									= 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STRING_TO_STRING_MAP__VALUE								= 1;

	/**
	 * The number of structural features of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				STRING_TO_STRING_MAP_FEATURE_COUNT						= 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.IncompatibleDutiesImpl <em>Incompatible Duties</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.IncompatibleDutiesImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getIncompatibleDuties()
	 * @generated
	 */
	int				INCOMPATIBLE_DUTIES											= 33;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				INCOMPATIBLE_DUTIES__STS_UNIQUE_ID						= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				INCOMPATIBLE_DUTIES__DESCRIPTION							= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				INCOMPATIBLE_DUTIES__SOURCE								= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				INCOMPATIBLE_DUTIES__TARGET								= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Incompatible Duties</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				INCOMPATIBLE_DUTIES_FEATURE_COUNT						= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.SeparationOfDuties <em>Separation Of Duties</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getSeparationOfDuties()
	 * @generated
	 */
	int				SEPARATION_OF_DUTIES											= 34;

	/**
	 * The feature id for the '<em><b>Incompatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT		= 0;

	/**
	 * The feature id for the '<em><b>Incompatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN			= 1;

	/**
	 * The number of structural features of the '<em>Separation Of Duties</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				SEPARATION_OF_DUTIES_FEATURE_COUNT						= 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.BindingOfDuties <em>Binding Of Duties</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.BindingOfDuties
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getBindingOfDuties()
	 * @generated
	 */
	int				BINDING_OF_DUTIES												= 35;

	/**
	 * The feature id for the '<em><b>Compatible Duties Out</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT				= 0;

	/**
	 * The feature id for the '<em><b>Compatible Duties In</b></em>' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN				= 1;

	/**
	 * The number of structural features of the '<em>Binding Of Duties</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				BINDING_OF_DUTIES_FEATURE_COUNT							= 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.CompatibleDutiesImpl <em>Compatible Duties</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.CompatibleDutiesImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getCompatibleDuties()
	 * @generated
	 */
	int				COMPATIBLE_DUTIES												= 36;

	/**
	 * The feature id for the '<em><b>Sts Unique ID</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				COMPATIBLE_DUTIES__STS_UNIQUE_ID							= STS_RELATION__STS_UNIQUE_ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				COMPATIBLE_DUTIES__DESCRIPTION							= STS_RELATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				COMPATIBLE_DUTIES__TARGET									= STS_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				COMPATIBLE_DUTIES__SOURCE									= STS_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Compatible Duties</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int				COMPATIBLE_DUTIES_FEATURE_COUNT							= STS_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.impl.DependencyImpl
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getDependency()
	 * @generated
	 */
	int DEPENDENCY = 37;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.RedundancyType <em>Redundancy Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.RedundancyType
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRedundancyType()
	 * @generated
	 */
	int				REDUNDANCY_TYPE												= 38;

	/**
	 * The meta object id for the '{@link eu.aniketos.wp1.ststool.RepudiationType <em>Repudiation Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see eu.aniketos.wp1.ststool.RepudiationType
	 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRepudiationType()
	 * @generated
	 */
	int				REPUDIATION_TYPE												= 39;

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.StsToolDiagram <em>Sts Tool Diagram</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sts Tool Diagram</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram
	 * @generated
	 */
	EClass getStsToolDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagActors <em>Diag Actors</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diag Actors</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getDiagActors()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_DiagActors();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagIResources <em>Diag IResources</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diag IResources</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getDiagIResources()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_DiagIResources();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagTResources <em>Diag TResources</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diag TResources</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getDiagTResources()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_DiagTResources();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagGoals <em>Diag Goals</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diag Goals</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getDiagGoals()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_DiagGoals();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagEvents <em>Diag Events</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diag Events</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getDiagEvents()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_DiagEvents();

	/**
	 * Returns the meta object for the map '{@link eu.aniketos.wp1.ststool.StsToolDiagram#getGraphicalConstraintMap <em>Graphical Constraint Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Graphical Constraint Map</em>'.
	 * @see eu.aniketos.wp1.ststool.StsToolDiagram#getGraphicalConstraintMap()
	 * @see #getStsToolDiagram()
	 * @generated
	 */
	EReference getStsToolDiagram_GraphicalConstraintMap();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Actor#getIncomingDelegations <em>Incoming Delegations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Delegations</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingDelegations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IncomingDelegations();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingDelegations <em>Outgoing Delegations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Delegations</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingDelegations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_OutgoingDelegations();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Goals</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getGoals()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Goals();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingProvisions <em>Outgoing Provisions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Provisions</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingProvisions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_OutgoingProvisions();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Actor#getIncomingProvisions <em>Incoming Provisions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Provisions</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingProvisions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IncomingProvisions();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingAuthorisations <em>Outgoing Authorisations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Authorisations</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingAuthorisations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_OutgoingAuthorisations();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Actor#getIncomingAuthorisations <em>Incoming Authorisations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Authorisations</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingAuthorisations()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IncomingAuthorisations();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getIResources <em>IResources</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>IResources</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getIResources()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IResources();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Actor#getTResources <em>TResources</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>TResources</em>'.
	 * @see eu.aniketos.wp1.ststool.Actor#getTResources()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_TResources();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Delegation <em>Delegation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delegation</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation
	 * @generated
	 */
	EClass getDelegation();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Delegation#getSourceGoal <em>Source Goal</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Goal</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getSourceGoal()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_SourceGoal();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Delegation#getTargetGoal <em>Target Goal</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Goal</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getTargetGoal()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_TargetGoal();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Delegation#getPreviousDelegation <em>Previous Delegation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous Delegation</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getPreviousDelegation()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_PreviousDelegation();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Delegation#getNextDelegations <em>Next Delegations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next Delegations</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getNextDelegations()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_NextDelegations();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Delegation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getTarget()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_Target();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Delegation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getSource()
	 * @see #getDelegation()
	 * @generated
	 */
	EReference getDelegation_Source();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getTimesTransferable <em>Times Transferable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Times Transferable</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getTimesTransferable()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_TimesTransferable();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getPreConditions <em>Pre Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getPreConditions()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_PreConditions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getPostConditions <em>Post Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Post Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getPostConditions()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_PostConditions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#isShowSecurityNeeds <em>Show Security Needs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Security Needs</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#isShowSecurityNeeds()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_ShowSecurityNeeds();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getRedundancyType <em>Redundancy Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Redundancy Type</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getRedundancyType()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_RedundancyType();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getRepudiationType <em>Repudiation Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Repudiation Type</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getRepudiationType()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_RepudiationType();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getSecurityNeeds <em>Security Needs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Needs</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getSecurityNeeds()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_SecurityNeeds();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#isAvailability <em>Availability</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Availability</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#isAvailability()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_Availability();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getAvailabilityValue <em>Availability Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Availability Value</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getAvailabilityValue()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_AvailabilityValue();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#isTrustworthiness <em>Trustworthiness</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trustworthiness</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#isTrustworthiness()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_Trustworthiness();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Delegation#getTrustworthinessValue <em>Trustworthiness Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trustworthiness Value</em>'.
	 * @see eu.aniketos.wp1.ststool.Delegation#getTrustworthinessValue()
	 * @see #getDelegation()
	 * @generated
	 */
	EAttribute getDelegation_TrustworthinessValue();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Provision <em>Provision</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provision</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision
	 * @generated
	 */
	EClass getProvision();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Provision#getPreviousProvision <em>Previous Provision</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Previous Provision</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getPreviousProvision()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_PreviousProvision();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Provision#getNextProvisions <em>Next Provisions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Next Provisions</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getNextProvisions()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_NextProvisions();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Provision#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getTarget()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_Target();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Provision#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getSource()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Provision#getSourceResource <em>Source Resource</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Resource</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getSourceResource()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_SourceResource();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Provision#getTargetResource <em>Target Resource</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target Resource</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getTargetResource()
	 * @see #getProvision()
	 * @generated
	 */
	EReference getProvision_TargetResource();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#getPreConditions <em>Pre Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getPreConditions()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_PreConditions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#getPostConditions <em>Post Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Post Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getPostConditions()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_PostConditions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#isShowSecurityNeeds <em>Show Security Needs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Security Needs</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#isShowSecurityNeeds()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_ShowSecurityNeeds();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#isIntegrity <em>Integrity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integrity</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#isIntegrity()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_Integrity();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#getSecurityNeeds <em>Security Needs</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Security Needs</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getSecurityNeeds()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_SecurityNeeds();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#isAvailability <em>Availability</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Availability</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#isAvailability()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_Availability();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#getAvailabilityValue <em>Availability Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Availability Value</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#getAvailabilityValue()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_AvailabilityValue();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Provision#isConfidentiality <em>Confidentiality</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confidentiality</em>'.
	 * @see eu.aniketos.wp1.ststool.Provision#isConfidentiality()
	 * @see #getProvision()
	 * @generated
	 */
	EAttribute getProvision_Confidentiality();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Agent#getPlayedRoles <em>Played Roles</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Played Roles</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent#getPlayedRoles()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_PlayedRoles();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Agent#getTypeOfOrganisation <em>Type Of Organisation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Of Organisation</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent#getTypeOfOrganisation()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_TypeOfOrganisation();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Agent#getAbilities <em>Abilities</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abilities</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent#getAbilities()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_Abilities();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Agent#getPossessedCertificationsAndAccreditations <em>Possessed Certifications And Accreditations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Possessed Certifications And Accreditations</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent#getPossessedCertificationsAndAccreditations()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_PossessedCertificationsAndAccreditations();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Agent#getOtherImportantFeatures <em>Other Important Features</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Other Important Features</em>'.
	 * @see eu.aniketos.wp1.ststool.Agent#getOtherImportantFeatures()
	 * @see #getAgent()
	 * @generated
	 */
	EAttribute getAgent_OtherImportantFeatures();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Role <em>Role</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see eu.aniketos.wp1.ststool.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Role#getPlayedBy <em>Played By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Played By</em>'.
	 * @see eu.aniketos.wp1.ststool.Role#getPlayedBy()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_PlayedBy();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Role#getPurpose <em>Purpose</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Purpose</em>'.
	 * @see eu.aniketos.wp1.ststool.Role#getPurpose()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Purpose();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Role#getMission <em>Mission</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mission</em>'.
	 * @see eu.aniketos.wp1.ststool.Role#getMission()
	 * @see #getRole()
	 * @generated
	 */
	EAttribute getRole_Mission();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Role#getDependBy <em>Depend By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Depend By</em>'.
	 * @see eu.aniketos.wp1.ststool.Role#getDependBy()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_DependBy();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Role#getDependent <em>Dependent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependent</em>'.
	 * @see eu.aniketos.wp1.ststool.Role#getDependent()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Dependent();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.TResource <em>TResource</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>TResource</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource
	 * @generated
	 */
	EClass getTResource();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getProvidedTo <em>Provided To</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Provided To</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getProvidedTo()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_ProvidedTo();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getProvidedFrom <em>Provided From</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Provided From</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getProvidedFrom()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_ProvidedFrom();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.TResource#getActorOwner <em>Actor Owner</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Actor Owner</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getActorOwner()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_ActorOwner();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getGoalsModifing <em>Goals Modifing</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Goals Modifing</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getGoalsModifing()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_GoalsModifing();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getGoalsProducing <em>Goals Producing</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Goals Producing</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getGoalsProducing()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_GoalsProducing();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getGoalsNeeding <em>Goals Needing</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Goals Needing</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getGoalsNeeding()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_GoalsNeeding();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.TResource#getIntangibleElements <em>Intangible Elements</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Intangible Elements</em>'.
	 * @see eu.aniketos.wp1.ststool.TResource#getIntangibleElements()
	 * @see #getTResource()
	 * @generated
	 */
	EReference getTResource_IntangibleElements();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Goal <em>Goal</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal
	 * @generated
	 */
	EClass getGoal();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Goal#getDelegatedTo <em>Delegated To</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delegated To</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getDelegatedTo()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_DelegatedTo();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Goal#getDelegatedFrom <em>Delegated From</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delegated From</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getDelegatedFrom()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_DelegatedFrom();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Goal#getAuthorisations <em>Authorisations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Authorisations</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getAuthorisations()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_Authorisations();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Goal#getActorOwner <em>Actor Owner</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Actor Owner</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getActorOwner()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_ActorOwner();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Goal#getResourceNeeded <em>Resource Needed</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Needed</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getResourceNeeded()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_ResourceNeeded();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Goal#getResourcesProduced <em>Resources Produced</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources Produced</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getResourcesProduced()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_ResourcesProduced();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Goal#getResourcesModified <em>Resources Modified</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources Modified</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getResourcesModified()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_ResourcesModified();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Goal#getOutgoingContributions <em>Outgoing Contributions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Contributions</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getOutgoingContributions()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_OutgoingContributions();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Goal#getIncomingContribution <em>Incoming Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Contribution</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getIncomingContribution()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_IncomingContribution();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Goal#getOutgoingDecompositions <em>Outgoing Decompositions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Decompositions</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getOutgoingDecompositions()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_OutgoingDecompositions();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Goal#getIncomingDecompositions <em>Incoming Decompositions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Incoming Decompositions</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getIncomingDecompositions()
	 * @see #getGoal()
	 * @generated
	 */
	EReference getGoal_IncomingDecompositions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Goal#isCapability <em>Capability</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Capability</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#isCapability()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_Capability();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Goal#getPreConditions <em>Pre Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pre Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getPreConditions()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_PreConditions();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Goal#getPostConditions <em>Post Conditions</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Post Conditions</em>'.
	 * @see eu.aniketos.wp1.ststool.Goal#getPostConditions()
	 * @see #getGoal()
	 * @generated
	 */
	EAttribute getGoal_PostConditions();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Authorisation <em>Authorisation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Authorisation</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation
	 * @generated
	 */
	EClass getAuthorisation();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Authorisation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#getSource()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EReference getAuthorisation_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Authorisation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#getTarget()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EReference getAuthorisation_Target();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Authorisation#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Goals</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#getGoals()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EReference getAuthorisation_Goals();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Authorisation#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#getResources()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EReference getAuthorisation_Resources();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Authorisation#getTimesTransferable <em>Times Transferable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Times Transferable</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#getTimesTransferable()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EAttribute getAuthorisation_TimesTransferable();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Authorisation#isUsage <em>Usage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#isUsage()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EAttribute getAuthorisation_Usage();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Authorisation#isModification <em>Modification</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modification</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#isModification()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EAttribute getAuthorisation_Modification();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Authorisation#isProduce <em>Produce</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Produce</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#isProduce()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EAttribute getAuthorisation_Produce();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Authorisation#isDistribution <em>Distribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Distribution</em>'.
	 * @see eu.aniketos.wp1.ststool.Authorisation#isDistribution()
	 * @see #getAuthorisation()
	 * @generated
	 */
	EAttribute getAuthorisation_Distribution();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see eu.aniketos.wp1.ststool.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Resource#getPartsOf <em>Parts Of</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parts Of</em>'.
	 * @see eu.aniketos.wp1.ststool.Resource#getPartsOf()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_PartsOf();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Resource#getSubParts <em>Sub Parts</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Parts</em>'.
	 * @see eu.aniketos.wp1.ststool.Resource#getSubParts()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_SubParts();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.IResource <em>IResource</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>IResource</em>'.
	 * @see eu.aniketos.wp1.ststool.IResource
	 * @generated
	 */
	EClass getIResource();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.IResource#getAuthorisations <em>Authorisations</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Authorisations</em>'.
	 * @see eu.aniketos.wp1.ststool.IResource#getAuthorisations()
	 * @see #getIResource()
	 * @generated
	 */
	EReference getIResource_Authorisations();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.IResource#getOwners <em>Owners</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Owners</em>'.
	 * @see eu.aniketos.wp1.ststool.IResource#getOwners()
	 * @see #getIResource()
	 * @generated
	 */
	EReference getIResource_Owners();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.IResource#getTangibleElements <em>Tangible Elements</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tangible Elements</em>'.
	 * @see eu.aniketos.wp1.ststool.IResource#getTangibleElements()
	 * @see #getIResource()
	 * @generated
	 */
	EReference getIResource_TangibleElements();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.StsElement <em>Sts Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sts Element</em>'.
	 * @see eu.aniketos.wp1.ststool.StsElement
	 * @generated
	 */
	EClass getStsElement();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.StsElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.aniketos.wp1.ststool.StsElement#getName()
	 * @see #getStsElement()
	 * @generated
	 */
	EAttribute getStsElement_Name();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.NonTransferableAuthorisation <em>Non Transferable Authorisation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Transferable Authorisation</em>'.
	 * @see eu.aniketos.wp1.ststool.NonTransferableAuthorisation
	 * @generated
	 */
	EClass getNonTransferableAuthorisation();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.TransferableAuthorisation <em>Transferable Authorisation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transferable Authorisation</em>'.
	 * @see eu.aniketos.wp1.ststool.TransferableAuthorisation
	 * @generated
	 */
	EClass getTransferableAuthorisation();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.GoalContribution <em>Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Contribution</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalContribution
	 * @generated
	 */
	EClass getGoalContribution();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.GoalContribution#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalContribution#getSource()
	 * @see #getGoalContribution()
	 * @generated
	 */
	EReference getGoalContribution_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.GoalContribution#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalContribution#getTarget()
	 * @see #getGoalContribution()
	 * @generated
	 */
	EReference getGoalContribution_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.GoalDecomposition <em>Goal Decomposition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Decomposition</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition
	 * @generated
	 */
	EClass getGoalDecomposition();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.GoalDecomposition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition#getSource()
	 * @see #getGoalDecomposition()
	 * @generated
	 */
	EReference getGoalDecomposition_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.GoalDecomposition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalDecomposition#getTarget()
	 * @see #getGoalDecomposition()
	 * @generated
	 */
	EReference getGoalDecomposition_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.PositiveGoalContribution <em>Positive Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Positive Goal Contribution</em>'.
	 * @see eu.aniketos.wp1.ststool.PositiveGoalContribution
	 * @generated
	 */
	EClass getPositiveGoalContribution();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.NegativeGoalContribution <em>Negative Goal Contribution</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Negative Goal Contribution</em>'.
	 * @see eu.aniketos.wp1.ststool.NegativeGoalContribution
	 * @generated
	 */
	EClass getNegativeGoalContribution();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.GoalDecompositionAND <em>Goal Decomposition AND</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Decomposition AND</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalDecompositionAND
	 * @generated
	 */
	EClass getGoalDecompositionAND();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.GoalDecompositionOR <em>Goal Decomposition OR</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal Decomposition OR</em>'.
	 * @see eu.aniketos.wp1.ststool.GoalDecompositionOR
	 * @generated
	 */
	EClass getGoalDecompositionOR();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Threatable <em>Threatable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Threatable</em>'.
	 * @see eu.aniketos.wp1.ststool.Threatable
	 * @generated
	 */
	EClass getThreatable();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.Threatable#getThreatedElements <em>Threated Elements</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Threated Elements</em>'.
	 * @see eu.aniketos.wp1.ststool.Threatable#getThreatedElements()
	 * @see #getThreatable()
	 * @generated
	 */
	EReference getThreatable_ThreatedElements();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Event <em>Event</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see eu.aniketos.wp1.ststool.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.Event#getThreatedElements <em>Threated Elements</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Threated Elements</em>'.
	 * @see eu.aniketos.wp1.ststool.Event#getThreatedElements()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_ThreatedElements();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Event#getEventID <em>Event ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event ID</em>'.
	 * @see eu.aniketos.wp1.ststool.Event#getEventID()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_EventID();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.Event#getCountermeasures <em>Countermeasures</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Countermeasures</em>'.
	 * @see eu.aniketos.wp1.ststool.Event#getCountermeasures()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Countermeasures();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Own <em>Own</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Own</em>'.
	 * @see eu.aniketos.wp1.ststool.Own
	 * @generated
	 */
	EClass getOwn();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Own#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Own#getSource()
	 * @see #getOwn()
	 * @generated
	 */
	EReference getOwn_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Own#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Own#getTarget()
	 * @see #getOwn()
	 * @generated
	 */
	EReference getOwn_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Play <em>Play</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Play</em>'.
	 * @see eu.aniketos.wp1.ststool.Play
	 * @generated
	 */
	EClass getPlay();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Play#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Play#getSource()
	 * @see #getPlay()
	 * @generated
	 */
	EReference getPlay_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Play#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Play#getTarget()
	 * @see #getPlay()
	 * @generated
	 */
	EReference getPlay_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.TangibleBy <em>Tangible By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tangible By</em>'.
	 * @see eu.aniketos.wp1.ststool.TangibleBy
	 * @generated
	 */
	EClass getTangibleBy();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.TangibleBy#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.TangibleBy#getSource()
	 * @see #getTangibleBy()
	 * @generated
	 */
	EReference getTangibleBy_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.TangibleBy#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.TangibleBy#getTarget()
	 * @see #getTangibleBy()
	 * @generated
	 */
	EReference getTangibleBy_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.PartOf <em>Part Of</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Part Of</em>'.
	 * @see eu.aniketos.wp1.ststool.PartOf
	 * @generated
	 */
	EClass getPartOf();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.PartOf#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.PartOf#getTarget()
	 * @see #getPartOf()
	 * @generated
	 */
	EReference getPartOf_Target();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.PartOf#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.PartOf#getSource()
	 * @see #getPartOf()
	 * @generated
	 */
	EReference getPartOf_Source();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Need <em>Need</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Need</em>'.
	 * @see eu.aniketos.wp1.ststool.Need
	 * @generated
	 */
	EClass getNeed();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Need#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Need#getSource()
	 * @see #getNeed()
	 * @generated
	 */
	EReference getNeed_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Need#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Need#getTarget()
	 * @see #getNeed()
	 * @generated
	 */
	EReference getNeed_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Produce <em>Produce</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Produce</em>'.
	 * @see eu.aniketos.wp1.ststool.Produce
	 * @generated
	 */
	EClass getProduce();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Produce#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Produce#getSource()
	 * @see #getProduce()
	 * @generated
	 */
	EReference getProduce_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Produce#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Produce#getTarget()
	 * @see #getProduce()
	 * @generated
	 */
	EReference getProduce_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Modify <em>Modify</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modify</em>'.
	 * @see eu.aniketos.wp1.ststool.Modify
	 * @generated
	 */
	EClass getModify();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Modify#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Modify#getSource()
	 * @see #getModify()
	 * @generated
	 */
	EReference getModify_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Modify#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Modify#getTarget()
	 * @see #getModify()
	 * @generated
	 */
	EReference getModify_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Threat <em>Threat</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Threat</em>'.
	 * @see eu.aniketos.wp1.ststool.Threat
	 * @generated
	 */
	EClass getThreat();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Threat#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Threat#getTarget()
	 * @see #getThreat()
	 * @generated
	 */
	EReference getThreat_Target();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Threat#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Threat#getSource()
	 * @see #getThreat()
	 * @generated
	 */
	EReference getThreat_Source();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.StsRelation <em>Sts Relation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sts Relation</em>'.
	 * @see eu.aniketos.wp1.ststool.StsRelation
	 * @generated
	 */
	EClass getStsRelation();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.StsObject <em>Sts Object</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sts Object</em>'.
	 * @see eu.aniketos.wp1.ststool.StsObject
	 * @generated
	 */
	EClass getStsObject();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.StsObject#getStsUniqueID <em>Sts Unique ID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sts Unique ID</em>'.
	 * @see eu.aniketos.wp1.ststool.StsObject#getStsUniqueID()
	 * @see #getStsObject()
	 * @generated
	 */
	EAttribute getStsObject_StsUniqueID();

	/**
	 * Returns the meta object for the attribute '{@link eu.aniketos.wp1.ststool.StsObject#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.aniketos.wp1.ststool.StsObject#getDescription()
	 * @see #getStsObject()
	 * @generated
	 */
	EAttribute getStsObject_Description();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To String Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringToStringMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Value();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.IncompatibleDuties <em>Incompatible Duties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Incompatible Duties</em>'.
	 * @see eu.aniketos.wp1.ststool.IncompatibleDuties
	 * @generated
	 */
	EClass getIncompatibleDuties();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.IncompatibleDuties#getSource()
	 * @see #getIncompatibleDuties()
	 * @generated
	 */
	EReference getIncompatibleDuties_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.IncompatibleDuties#getTarget()
	 * @see #getIncompatibleDuties()
	 * @generated
	 */
	EReference getIncompatibleDuties_Target();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.SeparationOfDuties <em>Separation Of Duties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Separation Of Duties</em>'.
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties
	 * @generated
	 */
	EClass getSeparationOfDuties();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesOut <em>Incompatible Duties Out</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Incompatible Duties Out</em>'.
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesOut()
	 * @see #getSeparationOfDuties()
	 * @generated
	 */
	EReference getSeparationOfDuties_IncompatibleDutiesOut();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesIn <em>Incompatible Duties In</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incompatible Duties In</em>'.
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesIn()
	 * @see #getSeparationOfDuties()
	 * @generated
	 */
	EReference getSeparationOfDuties_IncompatibleDutiesIn();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.BindingOfDuties <em>Binding Of Duties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binding Of Duties</em>'.
	 * @see eu.aniketos.wp1.ststool.BindingOfDuties
	 * @generated
	 */
	EClass getBindingOfDuties();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.aniketos.wp1.ststool.BindingOfDuties#getCompatibleDutiesOut <em>Compatible Duties Out</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Compatible Duties Out</em>'.
	 * @see eu.aniketos.wp1.ststool.BindingOfDuties#getCompatibleDutiesOut()
	 * @see #getBindingOfDuties()
	 * @generated
	 */
	EReference getBindingOfDuties_CompatibleDutiesOut();

	/**
	 * Returns the meta object for the reference list '{@link eu.aniketos.wp1.ststool.BindingOfDuties#getCompatibleDutiesIn <em>Compatible Duties In</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Compatible Duties In</em>'.
	 * @see eu.aniketos.wp1.ststool.BindingOfDuties#getCompatibleDutiesIn()
	 * @see #getBindingOfDuties()
	 * @generated
	 */
	EReference getBindingOfDuties_CompatibleDutiesIn();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.CompatibleDuties <em>Compatible Duties</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compatible Duties</em>'.
	 * @see eu.aniketos.wp1.ststool.CompatibleDuties
	 * @generated
	 */
	EClass getCompatibleDuties();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.CompatibleDuties#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.CompatibleDuties#getTarget()
	 * @see #getCompatibleDuties()
	 * @generated
	 */
	EReference getCompatibleDuties_Target();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.CompatibleDuties#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.CompatibleDuties#getSource()
	 * @see #getCompatibleDuties()
	 * @generated
	 */
	EReference getCompatibleDuties_Source();

	/**
	 * Returns the meta object for class '{@link eu.aniketos.wp1.ststool.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see eu.aniketos.wp1.ststool.Dependency
	 * @generated
	 */
	EClass getDependency();

	/**
	 * Returns the meta object for the container reference '{@link eu.aniketos.wp1.ststool.Dependency#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eu.aniketos.wp1.ststool.Dependency#getSource()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_Source();

	/**
	 * Returns the meta object for the reference '{@link eu.aniketos.wp1.ststool.Dependency#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.aniketos.wp1.ststool.Dependency#getTarget()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_Target();

	/**
	 * Returns the meta object for enum '{@link eu.aniketos.wp1.ststool.RedundancyType <em>Redundancy Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Redundancy Type</em>'.
	 * @see eu.aniketos.wp1.ststool.RedundancyType
	 * @generated
	 */
	EEnum getRedundancyType();

	/**
	 * Returns the meta object for enum '{@link eu.aniketos.wp1.ststool.RepudiationType <em>Repudiation Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Repudiation Type</em>'.
	 * @see eu.aniketos.wp1.ststool.RepudiationType
	 * @generated
	 */
	EEnum getRepudiationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StstoolFactory getStstoolFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl <em>Sts Tool Diagram</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsToolDiagram()
		 * @generated
		 */
		EClass		STS_TOOL_DIAGRAM												= eINSTANCE.getStsToolDiagram();

		/**
		 * The meta object literal for the '<em><b>Diag Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__DIAG_ACTORS								= eINSTANCE.getStsToolDiagram_DiagActors();

		/**
		 * The meta object literal for the '<em><b>Diag IResources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__DIAG_IRESOURCES						= eINSTANCE.getStsToolDiagram_DiagIResources();

		/**
		 * The meta object literal for the '<em><b>Diag TResources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__DIAG_TRESOURCES						= eINSTANCE.getStsToolDiagram_DiagTResources();

		/**
		 * The meta object literal for the '<em><b>Diag Goals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__DIAG_GOALS								= eINSTANCE.getStsToolDiagram_DiagGoals();

		/**
		 * The meta object literal for the '<em><b>Diag Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__DIAG_EVENTS								= eINSTANCE.getStsToolDiagram_DiagEvents();

		/**
		 * The meta object literal for the '<em><b>Graphical Constraint Map</b></em>' map feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP			= eINSTANCE.getStsToolDiagram_GraphicalConstraintMap();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ActorImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getActor()
		 * @generated
		 */
		EClass		ACTOR																= eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Incoming Delegations</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__INCOMING_DELEGATIONS								= eINSTANCE.getActor_IncomingDelegations();

		/**
		 * The meta object literal for the '<em><b>Outgoing Delegations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__OUTGOING_DELEGATIONS								= eINSTANCE.getActor_OutgoingDelegations();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__GOALS													= eINSTANCE.getActor_Goals();

		/**
		 * The meta object literal for the '<em><b>Outgoing Provisions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__OUTGOING_PROVISIONS									= eINSTANCE.getActor_OutgoingProvisions();

		/**
		 * The meta object literal for the '<em><b>Incoming Provisions</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__INCOMING_PROVISIONS									= eINSTANCE.getActor_IncomingProvisions();

		/**
		 * The meta object literal for the '<em><b>Outgoing Authorisations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__OUTGOING_AUTHORISATIONS							= eINSTANCE.getActor_OutgoingAuthorisations();

		/**
		 * The meta object literal for the '<em><b>Incoming Authorisations</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__INCOMING_AUTHORISATIONS							= eINSTANCE.getActor_IncomingAuthorisations();

		/**
		 * The meta object literal for the '<em><b>IResources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__IRESOURCES												= eINSTANCE.getActor_IResources();

		/**
		 * The meta object literal for the '<em><b>TResources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ACTOR__TRESOURCES												= eINSTANCE.getActor_TResources();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.DelegationImpl <em>Delegation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.DelegationImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getDelegation()
		 * @generated
		 */
		EClass		DELEGATION														= eINSTANCE.getDelegation();

		/**
		 * The meta object literal for the '<em><b>Source Goal</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__SOURCE_GOAL										= eINSTANCE.getDelegation_SourceGoal();

		/**
		 * The meta object literal for the '<em><b>Target Goal</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__TARGET_GOAL										= eINSTANCE.getDelegation_TargetGoal();

		/**
		 * The meta object literal for the '<em><b>Previous Delegation</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__PREVIOUS_DELEGATION							= eINSTANCE.getDelegation_PreviousDelegation();

		/**
		 * The meta object literal for the '<em><b>Next Delegations</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__NEXT_DELEGATIONS								= eINSTANCE.getDelegation_NextDelegations();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__TARGET											= eINSTANCE.getDelegation_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	DELEGATION__SOURCE											= eINSTANCE.getDelegation_Source();

		/**
		 * The meta object literal for the '<em><b>Times Transferable</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__TIMES_TRANSFERABLE							= eINSTANCE.getDelegation_TimesTransferable();

		/**
		 * The meta object literal for the '<em><b>Pre Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__PRE_CONDITIONS									= eINSTANCE.getDelegation_PreConditions();

		/**
		 * The meta object literal for the '<em><b>Post Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__POST_CONDITIONS								= eINSTANCE.getDelegation_PostConditions();

		/**
		 * The meta object literal for the '<em><b>Show Security Needs</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__SHOW_SECURITY_NEEDS							= eINSTANCE.getDelegation_ShowSecurityNeeds();

		/**
		 * The meta object literal for the '<em><b>Redundancy Type</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__REDUNDANCY_TYPE								= eINSTANCE.getDelegation_RedundancyType();

		/**
		 * The meta object literal for the '<em><b>Repudiation Type</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__REPUDIATION_TYPE								= eINSTANCE.getDelegation_RepudiationType();

		/**
		 * The meta object literal for the '<em><b>Security Needs</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__SECURITY_NEEDS									= eINSTANCE.getDelegation_SecurityNeeds();

		/**
		 * The meta object literal for the '<em><b>Availability</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__AVAILABILITY									= eINSTANCE.getDelegation_Availability();

		/**
		 * The meta object literal for the '<em><b>Availability Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__AVAILABILITY_VALUE							= eINSTANCE.getDelegation_AvailabilityValue();

		/**
		 * The meta object literal for the '<em><b>Trustworthiness</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__TRUSTWORTHINESS								= eINSTANCE.getDelegation_Trustworthiness();

		/**
		 * The meta object literal for the '<em><b>Trustworthiness Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	DELEGATION__TRUSTWORTHINESS_VALUE						= eINSTANCE.getDelegation_TrustworthinessValue();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl <em>Provision</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ProvisionImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getProvision()
		 * @generated
		 */
		EClass		PROVISION														= eINSTANCE.getProvision();

		/**
		 * The meta object literal for the '<em><b>Previous Provision</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__PREVIOUS_PROVISION								= eINSTANCE.getProvision_PreviousProvision();

		/**
		 * The meta object literal for the '<em><b>Next Provisions</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__NEXT_PROVISIONS									= eINSTANCE.getProvision_NextProvisions();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__TARGET												= eINSTANCE.getProvision_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__SOURCE												= eINSTANCE.getProvision_Source();

		/**
		 * The meta object literal for the '<em><b>Source Resource</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__SOURCE_RESOURCE									= eINSTANCE.getProvision_SourceResource();

		/**
		 * The meta object literal for the '<em><b>Target Resource</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PROVISION__TARGET_RESOURCE									= eINSTANCE.getProvision_TargetResource();

		/**
		 * The meta object literal for the '<em><b>Pre Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__PRE_CONDITIONS									= eINSTANCE.getProvision_PreConditions();

		/**
		 * The meta object literal for the '<em><b>Post Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__POST_CONDITIONS									= eINSTANCE.getProvision_PostConditions();

		/**
		 * The meta object literal for the '<em><b>Show Security Needs</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__SHOW_SECURITY_NEEDS							= eINSTANCE.getProvision_ShowSecurityNeeds();

		/**
		 * The meta object literal for the '<em><b>Integrity</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__INTEGRITY											= eINSTANCE.getProvision_Integrity();

		/**
		 * The meta object literal for the '<em><b>Security Needs</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__SECURITY_NEEDS									= eINSTANCE.getProvision_SecurityNeeds();

		/**
		 * The meta object literal for the '<em><b>Availability</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__AVAILABILITY										= eINSTANCE.getProvision_Availability();

		/**
		 * The meta object literal for the '<em><b>Availability Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	PROVISION__AVAILABILITY_VALUE								= eINSTANCE.getProvision_AvailabilityValue();

		/**
		 * The meta object literal for the '<em><b>Confidentiality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVISION__CONFIDENTIALITY = eINSTANCE.getProvision_Confidentiality();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.AgentImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getAgent()
		 * @generated
		 */
		EClass		AGENT																= eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Played Roles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	AGENT__PLAYED_ROLES											= eINSTANCE.getAgent_PlayedRoles();

		/**
		 * The meta object literal for the '<em><b>Type Of Organisation</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AGENT__TYPE_OF_ORGANISATION								= eINSTANCE.getAgent_TypeOfOrganisation();

		/**
		 * The meta object literal for the '<em><b>Abilities</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AGENT__ABILITIES												= eINSTANCE.getAgent_Abilities();

		/**
		 * The meta object literal for the '<em><b>Possessed Certifications And Accreditations</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AGENT__POSSESSED_CERTIFICATIONS_AND_ACCREDITATIONS	= eINSTANCE.getAgent_PossessedCertificationsAndAccreditations();

		/**
		 * The meta object literal for the '<em><b>Other Important Features</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AGENT__OTHER_IMPORTANT_FEATURES							= eINSTANCE.getAgent_OtherImportantFeatures();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.RoleImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRole()
		 * @generated
		 */
		EClass		ROLE																= eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Played By</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	ROLE__PLAYED_BY												= eINSTANCE.getRole_PlayedBy();

		/**
		 * The meta object literal for the '<em><b>Purpose</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	ROLE__PURPOSE													= eINSTANCE.getRole_Purpose();

		/**
		 * The meta object literal for the '<em><b>Mission</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	ROLE__MISSION													= eINSTANCE.getRole_Mission();

		/**
		 * The meta object literal for the '<em><b>Depend By</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__DEPEND_BY = eINSTANCE.getRole_DependBy();

		/**
		 * The meta object literal for the '<em><b>Dependent</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__DEPENDENT = eINSTANCE.getRole_Dependent();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.TResourceImpl <em>TResource</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.TResourceImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTResource()
		 * @generated
		 */
		EClass		TRESOURCE														= eINSTANCE.getTResource();

		/**
		 * The meta object literal for the '<em><b>Provided To</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__PROVIDED_TO										= eINSTANCE.getTResource_ProvidedTo();

		/**
		 * The meta object literal for the '<em><b>Provided From</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__PROVIDED_FROM									= eINSTANCE.getTResource_ProvidedFrom();

		/**
		 * The meta object literal for the '<em><b>Actor Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__ACTOR_OWNER										= eINSTANCE.getTResource_ActorOwner();

		/**
		 * The meta object literal for the '<em><b>Goals Modifing</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__GOALS_MODIFING									= eINSTANCE.getTResource_GoalsModifing();

		/**
		 * The meta object literal for the '<em><b>Goals Producing</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__GOALS_PRODUCING									= eINSTANCE.getTResource_GoalsProducing();

		/**
		 * The meta object literal for the '<em><b>Goals Needing</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__GOALS_NEEDING									= eINSTANCE.getTResource_GoalsNeeding();

		/**
		 * The meta object literal for the '<em><b>Intangible Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TRESOURCE__INTANGIBLE_ELEMENTS							= eINSTANCE.getTResource_IntangibleElements();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.GoalImpl <em>Goal</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.GoalImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoal()
		 * @generated
		 */
		EClass		GOAL																= eINSTANCE.getGoal();

		/**
		 * The meta object literal for the '<em><b>Delegated To</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__DELEGATED_TO											= eINSTANCE.getGoal_DelegatedTo();

		/**
		 * The meta object literal for the '<em><b>Delegated From</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__DELEGATED_FROM											= eINSTANCE.getGoal_DelegatedFrom();

		/**
		 * The meta object literal for the '<em><b>Authorisations</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__AUTHORISATIONS											= eINSTANCE.getGoal_Authorisations();

		/**
		 * The meta object literal for the '<em><b>Actor Owner</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__ACTOR_OWNER												= eINSTANCE.getGoal_ActorOwner();

		/**
		 * The meta object literal for the '<em><b>Resource Needed</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__RESOURCE_NEEDED										= eINSTANCE.getGoal_ResourceNeeded();

		/**
		 * The meta object literal for the '<em><b>Resources Produced</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__RESOURCES_PRODUCED									= eINSTANCE.getGoal_ResourcesProduced();

		/**
		 * The meta object literal for the '<em><b>Resources Modified</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__RESOURCES_MODIFIED									= eINSTANCE.getGoal_ResourcesModified();

		/**
		 * The meta object literal for the '<em><b>Outgoing Contributions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__OUTGOING_CONTRIBUTIONS								= eINSTANCE.getGoal_OutgoingContributions();

		/**
		 * The meta object literal for the '<em><b>Incoming Contribution</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__INCOMING_CONTRIBUTION								= eINSTANCE.getGoal_IncomingContribution();

		/**
		 * The meta object literal for the '<em><b>Outgoing Decompositions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__OUTGOING_DECOMPOSITIONS								= eINSTANCE.getGoal_OutgoingDecompositions();

		/**
		 * The meta object literal for the '<em><b>Incoming Decompositions</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL__INCOMING_DECOMPOSITIONS								= eINSTANCE.getGoal_IncomingDecompositions();

		/**
		 * The meta object literal for the '<em><b>Capability</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	GOAL__CAPABILITY												= eINSTANCE.getGoal_Capability();

		/**
		 * The meta object literal for the '<em><b>Pre Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	GOAL__PRE_CONDITIONS											= eINSTANCE.getGoal_PreConditions();

		/**
		 * The meta object literal for the '<em><b>Post Conditions</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	GOAL__POST_CONDITIONS										= eINSTANCE.getGoal_PostConditions();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.AuthorisationImpl <em>Authorisation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.AuthorisationImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getAuthorisation()
		 * @generated
		 */
		EClass		AUTHORISATION													= eINSTANCE.getAuthorisation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	AUTHORISATION__SOURCE										= eINSTANCE.getAuthorisation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	AUTHORISATION__TARGET										= eINSTANCE.getAuthorisation_Target();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	AUTHORISATION__GOALS											= eINSTANCE.getAuthorisation_Goals();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	AUTHORISATION__RESOURCES									= eINSTANCE.getAuthorisation_Resources();

		/**
		 * The meta object literal for the '<em><b>Times Transferable</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AUTHORISATION__TIMES_TRANSFERABLE						= eINSTANCE.getAuthorisation_TimesTransferable();

		/**
		 * The meta object literal for the '<em><b>Usage</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AUTHORISATION__USAGE											= eINSTANCE.getAuthorisation_Usage();

		/**
		 * The meta object literal for the '<em><b>Modification</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AUTHORISATION__MODIFICATION								= eINSTANCE.getAuthorisation_Modification();

		/**
		 * The meta object literal for the '<em><b>Produce</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AUTHORISATION__PRODUCE										= eINSTANCE.getAuthorisation_Produce();

		/**
		 * The meta object literal for the '<em><b>Distribution</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	AUTHORISATION__DISTRIBUTION								= eINSTANCE.getAuthorisation_Distribution();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ResourceImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getResource()
		 * @generated
		 */
		EClass		RESOURCE															= eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Parts Of</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	RESOURCE__PARTS_OF											= eINSTANCE.getResource_PartsOf();

		/**
		 * The meta object literal for the '<em><b>Sub Parts</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	RESOURCE__SUB_PARTS											= eINSTANCE.getResource_SubParts();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.IResourceImpl <em>IResource</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.IResourceImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getIResource()
		 * @generated
		 */
		EClass		IRESOURCE														= eINSTANCE.getIResource();

		/**
		 * The meta object literal for the '<em><b>Authorisations</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	IRESOURCE__AUTHORISATIONS									= eINSTANCE.getIResource_Authorisations();

		/**
		 * The meta object literal for the '<em><b>Owners</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	IRESOURCE__OWNERS												= eINSTANCE.getIResource_Owners();

		/**
		 * The meta object literal for the '<em><b>Tangible Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	IRESOURCE__TANGIBLE_ELEMENTS								= eINSTANCE.getIResource_TangibleElements();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.StsElementImpl <em>Sts Element</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.StsElementImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsElement()
		 * @generated
		 */
		EClass		STS_ELEMENT														= eINSTANCE.getStsElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	STS_ELEMENT__NAME												= eINSTANCE.getStsElement_Name();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.NonTransferableAuthorisationImpl <em>Non Transferable Authorisation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.NonTransferableAuthorisationImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNonTransferableAuthorisation()
		 * @generated
		 */
		EClass		NON_TRANSFERABLE_AUTHORISATION							= eINSTANCE.getNonTransferableAuthorisation();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.TransferableAuthorisationImpl <em>Transferable Authorisation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.TransferableAuthorisationImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTransferableAuthorisation()
		 * @generated
		 */
		EClass		TRANSFERABLE_AUTHORISATION									= eINSTANCE.getTransferableAuthorisation();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.GoalContributionImpl <em>Goal Contribution</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.GoalContributionImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalContribution()
		 * @generated
		 */
		EClass		GOAL_CONTRIBUTION												= eINSTANCE.getGoalContribution();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL_CONTRIBUTION__SOURCE									= eINSTANCE.getGoalContribution_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL_CONTRIBUTION__TARGET									= eINSTANCE.getGoalContribution_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionImpl <em>Goal Decomposition</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecomposition()
		 * @generated
		 */
		EClass		GOAL_DECOMPOSITION											= eINSTANCE.getGoalDecomposition();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL_DECOMPOSITION__SOURCE									= eINSTANCE.getGoalDecomposition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	GOAL_DECOMPOSITION__TARGET									= eINSTANCE.getGoalDecomposition_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.PositiveGoalContributionImpl <em>Positive Goal Contribution</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.PositiveGoalContributionImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPositiveGoalContribution()
		 * @generated
		 */
		EClass		POSITIVE_GOAL_CONTRIBUTION									= eINSTANCE.getPositiveGoalContribution();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.NegativeGoalContributionImpl <em>Negative Goal Contribution</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.NegativeGoalContributionImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNegativeGoalContribution()
		 * @generated
		 */
		EClass		NEGATIVE_GOAL_CONTRIBUTION									= eINSTANCE.getNegativeGoalContribution();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionANDImpl <em>Goal Decomposition AND</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionANDImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecompositionAND()
		 * @generated
		 */
		EClass		GOAL_DECOMPOSITION_AND										= eINSTANCE.getGoalDecompositionAND();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.GoalDecompositionORImpl <em>Goal Decomposition OR</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.GoalDecompositionORImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getGoalDecompositionOR()
		 * @generated
		 */
		EClass		GOAL_DECOMPOSITION_OR										= eINSTANCE.getGoalDecompositionOR();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.Threatable <em>Threatable</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.Threatable
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getThreatable()
		 * @generated
		 */
		EClass		THREATABLE														= eINSTANCE.getThreatable();

		/**
		 * The meta object literal for the '<em><b>Threated Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	THREATABLE__THREATED_ELEMENTS								= eINSTANCE.getThreatable_ThreatedElements();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.EventImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getEvent()
		 * @generated
		 */
		EClass		EVENT																= eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Threated Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	EVENT__THREATED_ELEMENTS									= eINSTANCE.getEvent_ThreatedElements();

		/**
		 * The meta object literal for the '<em><b>Event ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__EVENT_ID = eINSTANCE.getEvent_EventID();

		/**
		 * The meta object literal for the '<em><b>Countermeasures</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	EVENT__COUNTERMEASURES										= eINSTANCE.getEvent_Countermeasures();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.OwnImpl <em>Own</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.OwnImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getOwn()
		 * @generated
		 */
		EClass		OWN																= eINSTANCE.getOwn();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	OWN__SOURCE														= eINSTANCE.getOwn_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	OWN__TARGET														= eINSTANCE.getOwn_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.PlayImpl <em>Play</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.PlayImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPlay()
		 * @generated
		 */
		EClass		PLAY																= eINSTANCE.getPlay();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PLAY__SOURCE													= eINSTANCE.getPlay_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PLAY__TARGET													= eINSTANCE.getPlay_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.TangibleByImpl <em>Tangible By</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.TangibleByImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getTangibleBy()
		 * @generated
		 */
		EClass		TANGIBLE_BY														= eINSTANCE.getTangibleBy();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TANGIBLE_BY__SOURCE											= eINSTANCE.getTangibleBy_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	TANGIBLE_BY__TARGET											= eINSTANCE.getTangibleBy_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.PartOfImpl <em>Part Of</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.PartOfImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getPartOf()
		 * @generated
		 */
		EClass		PART_OF															= eINSTANCE.getPartOf();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PART_OF__TARGET												= eINSTANCE.getPartOf_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PART_OF__SOURCE												= eINSTANCE.getPartOf_Source();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.NeedImpl <em>Need</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.NeedImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getNeed()
		 * @generated
		 */
		EClass		NEED																= eINSTANCE.getNeed();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	NEED__SOURCE													= eINSTANCE.getNeed_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	NEED__TARGET													= eINSTANCE.getNeed_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ProduceImpl <em>Produce</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ProduceImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getProduce()
		 * @generated
		 */
		EClass		PRODUCE															= eINSTANCE.getProduce();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PRODUCE__SOURCE												= eINSTANCE.getProduce_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	PRODUCE__TARGET												= eINSTANCE.getProduce_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ModifyImpl <em>Modify</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ModifyImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getModify()
		 * @generated
		 */
		EClass		MODIFY															= eINSTANCE.getModify();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	MODIFY__SOURCE													= eINSTANCE.getModify_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	MODIFY__TARGET													= eINSTANCE.getModify_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.ThreatImpl <em>Threat</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.ThreatImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getThreat()
		 * @generated
		 */
		EClass		THREAT															= eINSTANCE.getThreat();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	THREAT__TARGET													= eINSTANCE.getThreat_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	THREAT__SOURCE													= eINSTANCE.getThreat_Source();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.StsRelationImpl <em>Sts Relation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.StsRelationImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsRelation()
		 * @generated
		 */
		EClass		STS_RELATION													= eINSTANCE.getStsRelation();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.StsObjectImpl <em>Sts Object</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.StsObjectImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStsObject()
		 * @generated
		 */
		EClass		STS_OBJECT														= eINSTANCE.getStsObject();

		/**
		 * The meta object literal for the '<em><b>Sts Unique ID</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	STS_OBJECT__STS_UNIQUE_ID									= eINSTANCE.getStsObject_StsUniqueID();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	STS_OBJECT__DESCRIPTION										= eINSTANCE.getStsObject_Description();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.StringToStringMapImpl <em>String To String Map</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.StringToStringMapImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getStringToStringMap()
		 * @generated
		 */
		EClass		STRING_TO_STRING_MAP											= eINSTANCE.getStringToStringMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	STRING_TO_STRING_MAP__KEY									= eINSTANCE.getStringToStringMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute	STRING_TO_STRING_MAP__VALUE								= eINSTANCE.getStringToStringMap_Value();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.IncompatibleDutiesImpl <em>Incompatible Duties</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.IncompatibleDutiesImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getIncompatibleDuties()
		 * @generated
		 */
		EClass		INCOMPATIBLE_DUTIES											= eINSTANCE.getIncompatibleDuties();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	INCOMPATIBLE_DUTIES__SOURCE								= eINSTANCE.getIncompatibleDuties_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	INCOMPATIBLE_DUTIES__TARGET								= eINSTANCE.getIncompatibleDuties_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.SeparationOfDuties <em>Separation Of Duties</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.SeparationOfDuties
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getSeparationOfDuties()
		 * @generated
		 */
		EClass		SEPARATION_OF_DUTIES											= eINSTANCE.getSeparationOfDuties();

		/**
		 * The meta object literal for the '<em><b>Incompatible Duties Out</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT		= eINSTANCE.getSeparationOfDuties_IncompatibleDutiesOut();

		/**
		 * The meta object literal for the '<em><b>Incompatible Duties In</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN			= eINSTANCE.getSeparationOfDuties_IncompatibleDutiesIn();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.BindingOfDuties <em>Binding Of Duties</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.BindingOfDuties
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getBindingOfDuties()
		 * @generated
		 */
		EClass		BINDING_OF_DUTIES												= eINSTANCE.getBindingOfDuties();

		/**
		 * The meta object literal for the '<em><b>Compatible Duties Out</b></em>' containment reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT				= eINSTANCE.getBindingOfDuties_CompatibleDutiesOut();

		/**
		 * The meta object literal for the '<em><b>Compatible Duties In</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN				= eINSTANCE.getBindingOfDuties_CompatibleDutiesIn();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.CompatibleDutiesImpl <em>Compatible Duties</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.CompatibleDutiesImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getCompatibleDuties()
		 * @generated
		 */
		EClass		COMPATIBLE_DUTIES												= eINSTANCE.getCompatibleDuties();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	COMPATIBLE_DUTIES__TARGET									= eINSTANCE.getCompatibleDuties_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference	COMPATIBLE_DUTIES__SOURCE									= eINSTANCE.getCompatibleDuties_Source();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.impl.DependencyImpl <em>Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.impl.DependencyImpl
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getDependency()
		 * @generated
		 */
		EClass DEPENDENCY = eINSTANCE.getDependency();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCY__SOURCE = eINSTANCE.getDependency_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCY__TARGET = eINSTANCE.getDependency_Target();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.RedundancyType <em>Redundancy Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.RedundancyType
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRedundancyType()
		 * @generated
		 */
		EEnum			REDUNDANCY_TYPE												= eINSTANCE.getRedundancyType();

		/**
		 * The meta object literal for the '{@link eu.aniketos.wp1.ststool.RepudiationType <em>Repudiation Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see eu.aniketos.wp1.ststool.RepudiationType
		 * @see eu.aniketos.wp1.ststool.impl.StstoolPackageImpl#getRepudiationType()
		 * @generated
		 */
		EEnum			REPUDIATION_TYPE												= eINSTANCE.getRepudiationType();

	}

} //StstoolPackage
