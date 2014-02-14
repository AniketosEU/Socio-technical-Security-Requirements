/*
* GoalImpl.java
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

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalContribution;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Goal</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getIncompatibleDutiesOut <em>Incompatible Duties Out</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getIncompatibleDutiesIn <em>Incompatible Duties In</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getCompatibleDutiesOut <em>Compatible Duties Out</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getCompatibleDutiesIn <em>Compatible Duties In</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getDelegatedTo <em>Delegated To</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getDelegatedFrom <em>Delegated From</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getAuthorisations <em>Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getActorOwner <em>Actor Owner</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getOutgoingContributions <em>Outgoing Contributions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getIncomingContribution <em>Incoming Contribution</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getOutgoingDecompositions <em>Outgoing Decompositions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getIncomingDecompositions <em>Incoming Decompositions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#isCapability <em>Capability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getResourceNeeded <em>Resource Needed</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getResourcesProduced <em>Resources Produced</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.GoalImpl#getResourcesModified <em>Resources Modified</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GoalImpl extends StsElementImpl implements Goal {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String				copyright						= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getThreatedElements() <em>Threated Elements</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThreatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Threat>					threatedElements;

	/**
	 * The cached value of the '{@link #getIncompatibleDutiesOut() <em>Incompatible Duties Out</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncompatibleDutiesOut()
	 * @generated
	 * @ordered
	 */
	protected EList<IncompatibleDuties>	incompatibleDutiesOut;

	/**
	 * The cached value of the '{@link #getIncompatibleDutiesIn() <em>Incompatible Duties In</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncompatibleDutiesIn()
	 * @generated
	 * @ordered
	 */
	protected EList<IncompatibleDuties>	incompatibleDutiesIn;

	/**
	 * The cached value of the '{@link #getCompatibleDutiesOut() <em>Compatible Duties Out</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCompatibleDutiesOut()
	 * @generated
	 * @ordered
	 */
	protected EList<CompatibleDuties>	compatibleDutiesOut;

	/**
	 * The cached value of the '{@link #getCompatibleDutiesIn() <em>Compatible Duties In</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCompatibleDutiesIn()
	 * @generated
	 * @ordered
	 */
	protected EList<CompatibleDuties>	compatibleDutiesIn;

	/**
	 * The cached value of the '{@link #getDelegatedTo() <em>Delegated To</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDelegatedTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>			delegatedTo;

	/**
	 * The cached value of the '{@link #getDelegatedFrom() <em>Delegated From</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDelegatedFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Delegation>			delegatedFrom;

	/**
	 * The cached value of the '{@link #getAuthorisations() <em>Authorisations</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAuthorisations()
	 * @generated
	 * @ordered
	 */
	protected EList<Authorisation>		authorisations;

	/**
	 * The cached value of the '{@link #getOutgoingContributions() <em>Outgoing Contributions</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingContributions()
	 * @generated
	 * @ordered
	 */
	protected EList<GoalContribution>	outgoingContributions;

	/**
	 * The cached value of the '{@link #getIncomingContribution() <em>Incoming Contribution</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingContribution()
	 * @generated
	 * @ordered
	 */
	protected EList<GoalContribution>	incomingContribution;

	/**
	 * The cached value of the '{@link #getOutgoingDecompositions() <em>Outgoing Decompositions</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOutgoingDecompositions()
	 * @generated
	 * @ordered
	 */
	protected EList<GoalDecomposition>	outgoingDecompositions;

	/**
	 * The cached value of the '{@link #getIncomingDecompositions() <em>Incoming Decompositions</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIncomingDecompositions()
	 * @generated
	 * @ordered
	 */
	protected GoalDecomposition			incomingDecompositions;

	/**
	 * The default value of the '{@link #isCapability() <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCapability()
	 * @generated
	 * @ordered
	 */
	protected static final boolean		CAPABILITY_EDEFAULT			= false;

	/**
	 * The cached value of the '{@link #isCapability() <em>Capability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCapability()
	 * @generated
	 * @ordered
	 */
	protected boolean							capability						= CAPABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String			PRE_CONDITIONS_EDEFAULT		= null;

	/**
	 * The cached value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected String							preConditions					= PRE_CONDITIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String			POST_CONDITIONS_EDEFAULT	= null;

	/**
	 * The cached value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected String							postConditions					= POST_CONDITIONS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResourceNeeded() <em>Resource Needed</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourceNeeded()
	 * @generated
	 * @ordered
	 */
	protected EList<Need>					resourceNeeded;

	/**
	 * The cached value of the '{@link #getResourcesProduced() <em>Resources Produced</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourcesProduced()
	 * @generated
	 * @ordered
	 */
	protected EList<Produce>				resourcesProduced;

	/**
	 * The cached value of the '{@link #getResourcesModified() <em>Resources Modified</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourcesModified()
	 * @generated
	 * @ordered
	 */
	protected EList<Modify>					resourcesModified;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected GoalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.GOAL;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Threat> getThreatedElements(){
		if (threatedElements == null) {
			threatedElements = new EObjectWithInverseResolvingEList<Threat>(Threat.class, this, StstoolPackage.GOAL__THREATED_ELEMENTS, StstoolPackage.THREAT__TARGET);
		}
		return threatedElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IncompatibleDuties> getIncompatibleDutiesOut(){
		if (incompatibleDutiesOut == null) {
			incompatibleDutiesOut = new EObjectContainmentWithInverseEList<IncompatibleDuties>(IncompatibleDuties.class, this, StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT, StstoolPackage.INCOMPATIBLE_DUTIES__SOURCE);
		}
		return incompatibleDutiesOut;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IncompatibleDuties> getIncompatibleDutiesIn(){
		if (incompatibleDutiesIn == null) {
			incompatibleDutiesIn = new EObjectWithInverseResolvingEList<IncompatibleDuties>(IncompatibleDuties.class, this, StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN, StstoolPackage.INCOMPATIBLE_DUTIES__TARGET);
		}
		return incompatibleDutiesIn;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompatibleDuties> getCompatibleDutiesOut(){
		if (compatibleDutiesOut == null) {
			compatibleDutiesOut = new EObjectContainmentWithInverseEList<CompatibleDuties>(CompatibleDuties.class, this, StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT, StstoolPackage.COMPATIBLE_DUTIES__SOURCE);
		}
		return compatibleDutiesOut;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompatibleDuties> getCompatibleDutiesIn(){
		if (compatibleDutiesIn == null) {
			compatibleDutiesIn = new EObjectWithInverseResolvingEList<CompatibleDuties>(CompatibleDuties.class, this, StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN, StstoolPackage.COMPATIBLE_DUTIES__TARGET);
		}
		return compatibleDutiesIn;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getDelegatedTo(){
		if (delegatedTo == null) {
			delegatedTo = new EObjectResolvingEList<Delegation>(Delegation.class, this, StstoolPackage.GOAL__DELEGATED_TO);
		}
		return delegatedTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Delegation> getDelegatedFrom(){
		if (delegatedFrom == null) {
			delegatedFrom = new EObjectWithInverseResolvingEList<Delegation>(Delegation.class, this, StstoolPackage.GOAL__DELEGATED_FROM, StstoolPackage.DELEGATION__TARGET_GOAL);
		}
		return delegatedFrom;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public void setName(String newName){

		if (getDelegatedFrom().size() > 0) return;
		super.setName(newName);
	}

	public void setNameForced(String newName){

		super.setNameForced(newName, true);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Authorisation> getAuthorisations(){
		if (authorisations == null) {
			authorisations = new EObjectWithInverseResolvingEList.ManyInverse<Authorisation>(Authorisation.class, this, StstoolPackage.GOAL__AUTHORISATIONS, StstoolPackage.AUTHORISATION__GOALS);
		}
		return authorisations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getActorOwner(){
		if (eContainerFeatureID() != StstoolPackage.GOAL__ACTOR_OWNER) return null;
		return (Actor)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActorOwner(Actor newActorOwner,NotificationChain msgs){
		msgs = eBasicSetContainer((InternalEObject)newActorOwner, StstoolPackage.GOAL__ACTOR_OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setActorOwner(Actor newActorOwner){
		if (newActorOwner != eInternalContainer() || (eContainerFeatureID() != StstoolPackage.GOAL__ACTOR_OWNER && newActorOwner != null)) {
			if (EcoreUtil.isAncestor(this, newActorOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newActorOwner != null)
				msgs = ((InternalEObject)newActorOwner).eInverseAdd(this, StstoolPackage.ACTOR__GOALS, Actor.class, msgs);
			msgs = basicSetActorOwner(newActorOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__ACTOR_OWNER, newActorOwner, newActorOwner));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Need> getResourceNeeded(){
		if (resourceNeeded == null) {
			resourceNeeded = new EObjectContainmentWithInverseEList<Need>(Need.class, this, StstoolPackage.GOAL__RESOURCE_NEEDED, StstoolPackage.NEED__SOURCE);
		}
		return resourceNeeded;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Produce> getResourcesProduced(){
		if (resourcesProduced == null) {
			resourcesProduced = new EObjectContainmentWithInverseEList<Produce>(Produce.class, this, StstoolPackage.GOAL__RESOURCES_PRODUCED, StstoolPackage.PRODUCE__SOURCE);
		}
		return resourcesProduced;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Modify> getResourcesModified(){
		if (resourcesModified == null) {
			resourcesModified = new EObjectContainmentWithInverseEList<Modify>(Modify.class, this, StstoolPackage.GOAL__RESOURCES_MODIFIED, StstoolPackage.MODIFY__SOURCE);
		}
		return resourcesModified;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GoalContribution> getOutgoingContributions(){
		if (outgoingContributions == null) {
			outgoingContributions = new EObjectContainmentWithInverseEList<GoalContribution>(GoalContribution.class, this, StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS, StstoolPackage.GOAL_CONTRIBUTION__SOURCE);
		}
		return outgoingContributions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GoalContribution> getIncomingContribution(){
		if (incomingContribution == null) {
			incomingContribution = new EObjectWithInverseResolvingEList<GoalContribution>(GoalContribution.class, this, StstoolPackage.GOAL__INCOMING_CONTRIBUTION, StstoolPackage.GOAL_CONTRIBUTION__TARGET);
		}
		return incomingContribution;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GoalDecomposition> getOutgoingDecompositions(){
		if (outgoingDecompositions == null) {
			outgoingDecompositions = new EObjectContainmentWithInverseEList<GoalDecomposition>(GoalDecomposition.class, this, StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS, StstoolPackage.GOAL_DECOMPOSITION__SOURCE);
		}
		return outgoingDecompositions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalDecomposition getIncomingDecompositions(){
		if (incomingDecompositions != null && incomingDecompositions.eIsProxy()) {
			InternalEObject oldIncomingDecompositions = (InternalEObject)incomingDecompositions;
			incomingDecompositions = (GoalDecomposition)eResolveProxy(oldIncomingDecompositions);
			if (incomingDecompositions != oldIncomingDecompositions) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS, oldIncomingDecompositions, incomingDecompositions));
			}
		}
		return incomingDecompositions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public GoalDecomposition basicGetIncomingDecompositions(){
		return incomingDecompositions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncomingDecompositions(GoalDecomposition newIncomingDecompositions,NotificationChain msgs){
		GoalDecomposition oldIncomingDecompositions = incomingDecompositions;
		incomingDecompositions = newIncomingDecompositions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS, oldIncomingDecompositions, newIncomingDecompositions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncomingDecompositions(GoalDecomposition newIncomingDecompositions){
		if (newIncomingDecompositions != incomingDecompositions) {
			NotificationChain msgs = null;
			if (incomingDecompositions != null)
				msgs = ((InternalEObject)incomingDecompositions).eInverseRemove(this, StstoolPackage.GOAL_DECOMPOSITION__TARGET, GoalDecomposition.class, msgs);
			if (newIncomingDecompositions != null)
				msgs = ((InternalEObject)newIncomingDecompositions).eInverseAdd(this, StstoolPackage.GOAL_DECOMPOSITION__TARGET, GoalDecomposition.class, msgs);
			msgs = basicSetIncomingDecompositions(newIncomingDecompositions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS, newIncomingDecompositions, newIncomingDecompositions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCapability(){
		return capability;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setCapability(boolean newCapability){
		boolean oldCapability = capability;
		capability = newCapability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__CAPABILITY, oldCapability, capability));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPreConditions(){
		return preConditions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreConditions(String newPreConditions){
		String oldPreConditions = preConditions;
		preConditions = newPreConditions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__PRE_CONDITIONS, oldPreConditions, preConditions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getPostConditions(){
		return postConditions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPostConditions(String newPostConditions){
		String oldPostConditions = postConditions;
		postConditions = newPostConditions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.GOAL__POST_CONDITIONS, oldPostConditions, postConditions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isDeletable(){

		if (getDelegatedFrom().size() == 0 && getDelegatedTo().size() == 0) return true;
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean canBeDelegated(){

		return true;
		/*
		 * if(getDelegatedFrom()==null)return true;
		 * else{
		 * return getDelegatedFrom().canBedelegated();
		 * }
		 */
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getThreatedElements()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncompatibleDutiesOut()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncompatibleDutiesIn()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCompatibleDutiesOut()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCompatibleDutiesIn()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__DELEGATED_FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDelegatedFrom()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__AUTHORISATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAuthorisations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__ACTOR_OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetActorOwner((Actor)otherEnd, msgs);
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingContributions()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncomingContribution()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoingDecompositions()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				if (incomingDecompositions != null)
					msgs = ((InternalEObject)incomingDecompositions).eInverseRemove(this, StstoolPackage.GOAL_DECOMPOSITION__TARGET, GoalDecomposition.class, msgs);
				return basicSetIncomingDecompositions((GoalDecomposition)otherEnd, msgs);
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourceNeeded()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourcesProduced()).basicAdd(otherEnd, msgs);
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourcesModified()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				return ((InternalEList<?>)getThreatedElements()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				return ((InternalEList<?>)getIncompatibleDutiesOut()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				return ((InternalEList<?>)getIncompatibleDutiesIn()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				return ((InternalEList<?>)getCompatibleDutiesOut()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				return ((InternalEList<?>)getCompatibleDutiesIn()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__DELEGATED_FROM:
				return ((InternalEList<?>)getDelegatedFrom()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__AUTHORISATIONS:
				return ((InternalEList<?>)getAuthorisations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__ACTOR_OWNER:
				return basicSetActorOwner(null, msgs);
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				return ((InternalEList<?>)getOutgoingContributions()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				return ((InternalEList<?>)getIncomingContribution()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				return ((InternalEList<?>)getOutgoingDecompositions()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				return basicSetIncomingDecompositions(null, msgs);
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				return ((InternalEList<?>)getResourceNeeded()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				return ((InternalEList<?>)getResourcesProduced()).basicRemove(otherEnd, msgs);
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				return ((InternalEList<?>)getResourcesModified()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs){
		switch (eContainerFeatureID()) {
			case StstoolPackage.GOAL__ACTOR_OWNER:
				return eInternalContainer().eInverseRemove(this, StstoolPackage.ACTOR__GOALS, Actor.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				return getThreatedElements();
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				return getIncompatibleDutiesOut();
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				return getIncompatibleDutiesIn();
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				return getCompatibleDutiesOut();
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				return getCompatibleDutiesIn();
			case StstoolPackage.GOAL__DELEGATED_TO:
				return getDelegatedTo();
			case StstoolPackage.GOAL__DELEGATED_FROM:
				return getDelegatedFrom();
			case StstoolPackage.GOAL__AUTHORISATIONS:
				return getAuthorisations();
			case StstoolPackage.GOAL__ACTOR_OWNER:
				return getActorOwner();
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				return getOutgoingContributions();
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				return getIncomingContribution();
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				return getOutgoingDecompositions();
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				if (resolve) return getIncomingDecompositions();
				return basicGetIncomingDecompositions();
			case StstoolPackage.GOAL__CAPABILITY:
				return isCapability();
			case StstoolPackage.GOAL__PRE_CONDITIONS:
				return getPreConditions();
			case StstoolPackage.GOAL__POST_CONDITIONS:
				return getPostConditions();
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				return getResourceNeeded();
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				return getResourcesProduced();
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				return getResourcesModified();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID,Object newValue){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				getThreatedElements().clear();
				getThreatedElements().addAll((Collection<? extends Threat>)newValue);
				return;
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				getIncompatibleDutiesOut().clear();
				getIncompatibleDutiesOut().addAll((Collection<? extends IncompatibleDuties>)newValue);
				return;
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				getIncompatibleDutiesIn().clear();
				getIncompatibleDutiesIn().addAll((Collection<? extends IncompatibleDuties>)newValue);
				return;
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				getCompatibleDutiesOut().clear();
				getCompatibleDutiesOut().addAll((Collection<? extends CompatibleDuties>)newValue);
				return;
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				getCompatibleDutiesIn().clear();
				getCompatibleDutiesIn().addAll((Collection<? extends CompatibleDuties>)newValue);
				return;
			case StstoolPackage.GOAL__DELEGATED_TO:
				getDelegatedTo().clear();
				getDelegatedTo().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.GOAL__DELEGATED_FROM:
				getDelegatedFrom().clear();
				getDelegatedFrom().addAll((Collection<? extends Delegation>)newValue);
				return;
			case StstoolPackage.GOAL__AUTHORISATIONS:
				getAuthorisations().clear();
				getAuthorisations().addAll((Collection<? extends Authorisation>)newValue);
				return;
			case StstoolPackage.GOAL__ACTOR_OWNER:
				setActorOwner((Actor)newValue);
				return;
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				getOutgoingContributions().clear();
				getOutgoingContributions().addAll((Collection<? extends GoalContribution>)newValue);
				return;
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				getIncomingContribution().clear();
				getIncomingContribution().addAll((Collection<? extends GoalContribution>)newValue);
				return;
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				getOutgoingDecompositions().clear();
				getOutgoingDecompositions().addAll((Collection<? extends GoalDecomposition>)newValue);
				return;
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				setIncomingDecompositions((GoalDecomposition)newValue);
				return;
			case StstoolPackage.GOAL__CAPABILITY:
				setCapability((Boolean)newValue);
				return;
			case StstoolPackage.GOAL__PRE_CONDITIONS:
				setPreConditions((String)newValue);
				return;
			case StstoolPackage.GOAL__POST_CONDITIONS:
				setPostConditions((String)newValue);
				return;
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				getResourceNeeded().clear();
				getResourceNeeded().addAll((Collection<? extends Need>)newValue);
				return;
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				getResourcesProduced().clear();
				getResourcesProduced().addAll((Collection<? extends Produce>)newValue);
				return;
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				getResourcesModified().clear();
				getResourcesModified().addAll((Collection<? extends Modify>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				getThreatedElements().clear();
				return;
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				getIncompatibleDutiesOut().clear();
				return;
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				getIncompatibleDutiesIn().clear();
				return;
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				getCompatibleDutiesOut().clear();
				return;
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				getCompatibleDutiesIn().clear();
				return;
			case StstoolPackage.GOAL__DELEGATED_TO:
				getDelegatedTo().clear();
				return;
			case StstoolPackage.GOAL__DELEGATED_FROM:
				getDelegatedFrom().clear();
				return;
			case StstoolPackage.GOAL__AUTHORISATIONS:
				getAuthorisations().clear();
				return;
			case StstoolPackage.GOAL__ACTOR_OWNER:
				setActorOwner((Actor)null);
				return;
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				getOutgoingContributions().clear();
				return;
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				getIncomingContribution().clear();
				return;
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				getOutgoingDecompositions().clear();
				return;
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				setIncomingDecompositions((GoalDecomposition)null);
				return;
			case StstoolPackage.GOAL__CAPABILITY:
				setCapability(CAPABILITY_EDEFAULT);
				return;
			case StstoolPackage.GOAL__PRE_CONDITIONS:
				setPreConditions(PRE_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.GOAL__POST_CONDITIONS:
				setPostConditions(POST_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				getResourceNeeded().clear();
				return;
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				getResourcesProduced().clear();
				return;
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				getResourcesModified().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID){
		switch (featureID) {
			case StstoolPackage.GOAL__THREATED_ELEMENTS:
				return threatedElements != null && !threatedElements.isEmpty();
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT:
				return incompatibleDutiesOut != null && !incompatibleDutiesOut.isEmpty();
			case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN:
				return incompatibleDutiesIn != null && !incompatibleDutiesIn.isEmpty();
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT:
				return compatibleDutiesOut != null && !compatibleDutiesOut.isEmpty();
			case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN:
				return compatibleDutiesIn != null && !compatibleDutiesIn.isEmpty();
			case StstoolPackage.GOAL__DELEGATED_TO:
				return delegatedTo != null && !delegatedTo.isEmpty();
			case StstoolPackage.GOAL__DELEGATED_FROM:
				return delegatedFrom != null && !delegatedFrom.isEmpty();
			case StstoolPackage.GOAL__AUTHORISATIONS:
				return authorisations != null && !authorisations.isEmpty();
			case StstoolPackage.GOAL__ACTOR_OWNER:
				return getActorOwner() != null;
			case StstoolPackage.GOAL__OUTGOING_CONTRIBUTIONS:
				return outgoingContributions != null && !outgoingContributions.isEmpty();
			case StstoolPackage.GOAL__INCOMING_CONTRIBUTION:
				return incomingContribution != null && !incomingContribution.isEmpty();
			case StstoolPackage.GOAL__OUTGOING_DECOMPOSITIONS:
				return outgoingDecompositions != null && !outgoingDecompositions.isEmpty();
			case StstoolPackage.GOAL__INCOMING_DECOMPOSITIONS:
				return incomingDecompositions != null;
			case StstoolPackage.GOAL__CAPABILITY:
				return capability != CAPABILITY_EDEFAULT;
			case StstoolPackage.GOAL__PRE_CONDITIONS:
				return PRE_CONDITIONS_EDEFAULT == null ? preConditions != null : !PRE_CONDITIONS_EDEFAULT.equals(preConditions);
			case StstoolPackage.GOAL__POST_CONDITIONS:
				return POST_CONDITIONS_EDEFAULT == null ? postConditions != null : !POST_CONDITIONS_EDEFAULT.equals(postConditions);
			case StstoolPackage.GOAL__RESOURCE_NEEDED:
				return resourceNeeded != null && !resourceNeeded.isEmpty();
			case StstoolPackage.GOAL__RESOURCES_PRODUCED:
				return resourcesProduced != null && !resourcesProduced.isEmpty();
			case StstoolPackage.GOAL__RESOURCES_MODIFIED:
				return resourcesModified != null && !resourcesModified.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,Class<?> baseClass){
		if (baseClass == Threatable.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.GOAL__THREATED_ELEMENTS: return StstoolPackage.THREATABLE__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		if (baseClass == SeparationOfDuties.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT: return StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT;
				case StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN: return StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		if (baseClass == BindingOfDuties.class) {
			switch (derivedFeatureID) {
				case StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT: return StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT;
				case StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN: return StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID,Class<?> baseClass){
		if (baseClass == Threatable.class) {
			switch (baseFeatureID) {
				case StstoolPackage.THREATABLE__THREATED_ELEMENTS: return StstoolPackage.GOAL__THREATED_ELEMENTS;
				default: return -1;
			}
		}
		if (baseClass == SeparationOfDuties.class) {
			switch (baseFeatureID) {
				case StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_OUT: return StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_OUT;
				case StstoolPackage.SEPARATION_OF_DUTIES__INCOMPATIBLE_DUTIES_IN: return StstoolPackage.GOAL__INCOMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		if (baseClass == BindingOfDuties.class) {
			switch (baseFeatureID) {
				case StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_OUT: return StstoolPackage.GOAL__COMPATIBLE_DUTIES_OUT;
				case StstoolPackage.BINDING_OF_DUTIES__COMPATIBLE_DUTIES_IN: return StstoolPackage.GOAL__COMPATIBLE_DUTIES_IN;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (capability: ");
		result.append(capability);
		result.append(", preConditions: ");
		result.append(preConditions);
		result.append(", postConditions: ");
		result.append(postConditions);
		result.append(')');
		return result.toString();
	}

} //GoalImpl
