/*
* ProvisionImpl.java
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Provision</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getPreviousProvision <em>Previous Provision</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getNextProvisions <em>Next Provisions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getSourceResource <em>Source Resource</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getTargetResource <em>Target Resource</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getPreConditions <em>Pre Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getPostConditions <em>Post Conditions</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#isShowSecurityNeeds <em>Show Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#isIntegrity <em>Integrity</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getSecurityNeeds <em>Security Needs</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#isAvailability <em>Availability</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#getAvailabilityValue <em>Availability Value</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ProvisionImpl#isConfidentiality <em>Confidentiality</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProvisionImpl extends StsRelationImpl implements Provision {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright							= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getPreviousProvision() <em>Previous Provision</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreviousProvision()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>			previousProvision;

	/**
	 * The cached value of the '{@link #getNextProvisions() <em>Next Provisions</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getNextProvisions()
	 * @generated
	 * @ordered
	 */
	protected EList<Provision>			nextProvisions;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Actor						target;

	/**
	 * The cached value of the '{@link #getSourceResource() <em>Source Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSourceResource()
	 * @generated
	 * @ordered
	 */
	protected TResource					sourceResource;

	/**
	 * The cached value of the '{@link #getTargetResource() <em>Target Resource</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTargetResource()
	 * @generated
	 * @ordered
	 */
	protected TResource					targetResource;

	/**
	 * The default value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String		PRE_CONDITIONS_EDEFAULT			= null;

	/**
	 * The cached value of the '{@link #getPreConditions() <em>Pre Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPreConditions()
	 * @generated
	 * @ordered
	 */
	protected String						preConditions						= PRE_CONDITIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected static final String		POST_CONDITIONS_EDEFAULT		= null;

	/**
	 * The cached value of the '{@link #getPostConditions() <em>Post Conditions</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPostConditions()
	 * @generated
	 * @ordered
	 */
	protected String						postConditions						= POST_CONDITIONS_EDEFAULT;


	/**
	 * The default value of the '{@link #isShowSecurityNeeds() <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isShowSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	SHOW_SECURITY_NEEDS_EDEFAULT	= false;

	/**
	 * The cached value of the '{@link #isShowSecurityNeeds() <em>Show Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isShowSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected boolean						showSecurityNeeds					= SHOW_SECURITY_NEEDS_EDEFAULT;

	/**
	 * The default value of the '{@link #isIntegrity() <em>Integrity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIntegrity()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	INTEGRITY_EDEFAULT				= false;

	/**
	 * The cached value of the '{@link #isIntegrity() <em>Integrity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isIntegrity()
	 * @generated
	 * @ordered
	 */
	protected boolean						integrity							= INTEGRITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityNeeds() <em>Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected static final String		SECURITY_NEEDS_EDEFAULT			= null;

	/**
	 * The cached value of the '{@link #getSecurityNeeds() <em>Security Needs</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSecurityNeeds()
	 * @generated
	 * @ordered
	 */
	protected String						securityNeeds						= SECURITY_NEEDS_EDEFAULT;

	/**
	 * The default value of the '{@link #isAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAvailability()
	 * @generated
	 * @ordered
	 */
	protected static final boolean	AVAILABILITY_EDEFAULT			= false;

	/**
	 * The cached value of the '{@link #isAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAvailability()
	 * @generated
	 * @ordered
	 */
	protected boolean						availability						= AVAILABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvailabilityValue() <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAvailabilityValue()
	 * @generated
	 * @ordered
	 */
	protected static final int			AVAILABILITY_VALUE_EDEFAULT	= 0;

	/**
	 * The cached value of the '{@link #getAvailabilityValue() <em>Availability Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAvailabilityValue()
	 * @generated
	 * @ordered
	 */
	protected int							availabilityValue					= AVAILABILITY_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isConfidentiality() <em>Confidentiality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfidentiality()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONFIDENTIALITY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConfidentiality() <em>Confidentiality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConfidentiality()
	 * @generated
	 * @ordered
	 */
	protected boolean confidentiality = CONFIDENTIALITY_EDEFAULT;

	TResourceAdapter						resourceAdapter					= new TResourceAdapter(this);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ProvisionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.PROVISION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getPreviousProvision(){
		if (previousProvision == null) {
			previousProvision = new EObjectResolvingEList<Provision>(Provision.class, this, StstoolPackage.PROVISION__PREVIOUS_PROVISION);
		}
		return previousProvision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Provision> getNextProvisions(){
		if (nextProvisions == null) {
			nextProvisions = new EObjectResolvingEList<Provision>(Provision.class, this, StstoolPackage.PROVISION__NEXT_PROVISIONS);
		}
		return nextProvisions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getTarget(){
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Actor)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.PROVISION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetTarget(){
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Actor newTarget,NotificationChain msgs){
		Actor oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Actor newTarget){
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_PROVISIONS, Actor.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, StstoolPackage.ACTOR__INCOMING_PROVISIONS, Actor.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getSource(){
		if (eContainerFeatureID() != StstoolPackage.PROVISION__SOURCE) return null;
		return (Actor)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Actor newSource,NotificationChain msgs){
		msgs = eBasicSetContainer((InternalEObject)newSource, StstoolPackage.PROVISION__SOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Actor newSource){
		if (newSource != eInternalContainer() || (eContainerFeatureID() != StstoolPackage.PROVISION__SOURCE && newSource != null)) {
			if (EcoreUtil.isAncestor(this, newSource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, StstoolPackage.ACTOR__OUTGOING_PROVISIONS, Actor.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TResource getSourceResource(){
		if (sourceResource != null && sourceResource.eIsProxy()) {
			InternalEObject oldSourceResource = (InternalEObject)sourceResource;
			sourceResource = (TResource)eResolveProxy(oldSourceResource);
			if (sourceResource != oldSourceResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.PROVISION__SOURCE_RESOURCE, oldSourceResource, sourceResource));
			}
		}
		return sourceResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TResource basicGetSourceResource(){
		return sourceResource;
	}


	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setSourceResource(TResource newSourceResource){

		TResource oldResource = sourceResource;

		if (newSourceResource != oldResource && newSourceResource != getTargetResource()) {

			sourceResource = newSourceResource;
			TResource sourceResource = getSourceResource();

			//removing reference in previous goal;
			if (oldResource != null) {
				oldResource.getProvidedTo().remove(this);
				for (Provision p : oldResource.getProvidedFrom()) {
					p.getNextProvisions().remove(this);
				}
				oldResource.eAdapters().remove(resourceAdapter);
			}

			if (sourceResource != null) {//Setting a new source goal
				sourceResource.getProvidedTo().add(this);

				//getTargetResource().setNameForced("@"+sourceResource.getName());
				if (getTargetResource() != null && getTargetResource().getProvidedFrom().size() == 1) {
					getTargetResource().setNameForced("" + sourceResource.getName());
				}
				sourceResource.eAdapters().add(resourceAdapter);

				for (Provision p : sourceResource.getProvidedFrom()) {
					p.getNextProvisions().add(this);
					getPreviousProvision().add(p);
				}
			} else {//Clearing source goal
				getPreviousProvision().clear();
				//getTargetResource().setNameForced("No TResource Reference");
			}
			if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__SOURCE_RESOURCE, oldResource, sourceResource));
		}
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceResourceGen(TResource newSourceResource){
		TResource oldSourceResource = sourceResource;
		sourceResource = newSourceResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__SOURCE_RESOURCE, oldSourceResource, sourceResource));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TResource getTargetResource(){
		if (targetResource != null && targetResource.eIsProxy()) {
			InternalEObject oldTargetResource = (InternalEObject)targetResource;
			targetResource = (TResource)eResolveProxy(oldTargetResource);
			if (targetResource != oldTargetResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StstoolPackage.PROVISION__TARGET_RESOURCE, oldTargetResource, targetResource));
			}
		}
		return targetResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TResource basicGetTargetResource(){
		return targetResource;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetResource(TResource newTargetResource,NotificationChain msgs){
		TResource oldTargetResource = targetResource;
		targetResource = newTargetResource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__TARGET_RESOURCE, oldTargetResource, newTargetResource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setTargetResource(TResource newTargetResource){

		if (newTargetResource != targetResource) {
			if (newTargetResource != null && getSourceResource() != null) {
				newTargetResource.setNameForced(getSourceResource().getName());
			}
			if (newTargetResource != null) {
				for (Provision p : newTargetResource.getProvidedTo()) {
					p.getPreviousProvision().add(this);
					getNextProvisions().add(p);
				}
			}
			NotificationChain msgs = null;
			if (targetResource != null) msgs = ((InternalEObject) targetResource).eInverseRemove(this, StstoolPackage.TRESOURCE__PROVIDED_FROM, TResource.class, msgs);
			if (newTargetResource != null) msgs = ((InternalEObject) newTargetResource).eInverseAdd(this, StstoolPackage.TRESOURCE__PROVIDED_FROM, TResource.class, msgs);
			msgs = basicSetTargetResource(newTargetResource, msgs);
			if (msgs != null) msgs.dispatch();
		} else if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__TARGET_RESOURCE, newTargetResource, newTargetResource));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__PRE_CONDITIONS, oldPreConditions, preConditions));
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
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__POST_CONDITIONS, oldPostConditions, postConditions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShowSecurityNeeds(){
		return showSecurityNeeds;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setShowSecurityNeeds(boolean newShowSecurityNeeds){
		boolean oldShowSecurityNeeds = showSecurityNeeds;
		showSecurityNeeds = newShowSecurityNeeds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS, oldShowSecurityNeeds, showSecurityNeeds));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIntegrity(){
		return integrity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegrity(boolean newIntegrity){
		boolean oldIntegrity = integrity;
		integrity = newIntegrity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__INTEGRITY, oldIntegrity, integrity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getSecurityNeeds(){
		List<String> secNeed = new ArrayList<String>();

		if (isIntegrity()) {
			secNeed.add("Integrity");
		}
		if (isAvailability()) {
			secNeed.add("Availability " + getAvailabilityValue());
		}
		if (isConfidentiality()) {
			secNeed.add("Confidentiality");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < secNeed.size(); i++) {
			if (i > 0) sb.append(", ");
			sb.append("\"" + secNeed.get(i) + "\"");
		}

		return sb.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAvailability(){
		return availability;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailability(boolean newAvailability){
		boolean oldAvailability = availability;
		availability = newAvailability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__AVAILABILITY, oldAvailability, availability));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getAvailabilityValue(){
		return availabilityValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailabilityValue(int newAvailabilityValue){
		int oldAvailabilityValue = availabilityValue;
		availabilityValue = newAvailabilityValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__AVAILABILITY_VALUE, oldAvailabilityValue, availabilityValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConfidentiality() {
		return confidentiality;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfidentiality(boolean newConfidentiality) {
		boolean oldConfidentiality = confidentiality;
		confidentiality = newConfidentiality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StstoolPackage.PROVISION__CONFIDENTIALITY, oldConfidentiality, confidentiality));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see Actor#isDeletable()
	 * @generated NOT
	 */
	public boolean isDeletable(){
		try {
			if (getNextProvisions().size() == 0 || canDeleteProvision(this)) { return true; }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean canDeleteProvision(Provision provision){
		List<TResource> visitedNode = new ArrayList<TResource>();
		TResource source = provision.getTargetResource().getSourceResource(provision.getTargetResource());
		return canDeleteProvisionRecursive(source, provision.getTargetResource(), visitedNode, provision);
	}

	private boolean canDeleteProvisionRecursive(TResource startResource,final TResource destination,List<TResource> visitedResource,Provision provisionToDelete){

		for (Provision p : startResource.getProvidedTo()) {
			if (p != provisionToDelete) {
				TResource targetR = p.getTargetResource();
				if (targetR == destination) {
					return true;
				} else {//Resource to analyze
					if (!visitedResource.contains(targetR)) {
						List<TResource> updatedVisitedResource = new ArrayList<TResource>(visitedResource);
						updatedVisitedResource.add(startResource);
						if (canDeleteProvisionRecursive(targetR, destination, updatedVisitedResource, provisionToDelete)) return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc --> This method return true if the provided resource can be reprovided. <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean canBeTransferred(){

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.PROVISION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, StstoolPackage.ACTOR__INCOMING_PROVISIONS, Actor.class, msgs);
				return basicSetTarget((Actor)otherEnd, msgs);
			case StstoolPackage.PROVISION__SOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSource((Actor)otherEnd, msgs);
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				if (targetResource != null)
					msgs = ((InternalEObject)targetResource).eInverseRemove(this, StstoolPackage.TRESOURCE__PROVIDED_FROM, TResource.class, msgs);
				return basicSetTargetResource((TResource)otherEnd, msgs);
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
			case StstoolPackage.PROVISION__TARGET:
				return basicSetTarget(null, msgs);
			case StstoolPackage.PROVISION__SOURCE:
				return basicSetSource(null, msgs);
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				return basicSetTargetResource(null, msgs);
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
			case StstoolPackage.PROVISION__SOURCE:
				return eInternalContainer().eInverseRemove(this, StstoolPackage.ACTOR__OUTGOING_PROVISIONS, Actor.class, msgs);
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
			case StstoolPackage.PROVISION__PREVIOUS_PROVISION:
				return getPreviousProvision();
			case StstoolPackage.PROVISION__NEXT_PROVISIONS:
				return getNextProvisions();
			case StstoolPackage.PROVISION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case StstoolPackage.PROVISION__SOURCE:
				return getSource();
			case StstoolPackage.PROVISION__SOURCE_RESOURCE:
				if (resolve) return getSourceResource();
				return basicGetSourceResource();
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				if (resolve) return getTargetResource();
				return basicGetTargetResource();
			case StstoolPackage.PROVISION__PRE_CONDITIONS:
				return getPreConditions();
			case StstoolPackage.PROVISION__POST_CONDITIONS:
				return getPostConditions();
			case StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS:
				return isShowSecurityNeeds();
			case StstoolPackage.PROVISION__INTEGRITY:
				return isIntegrity();
			case StstoolPackage.PROVISION__SECURITY_NEEDS:
				return getSecurityNeeds();
			case StstoolPackage.PROVISION__AVAILABILITY:
				return isAvailability();
			case StstoolPackage.PROVISION__AVAILABILITY_VALUE:
				return getAvailabilityValue();
			case StstoolPackage.PROVISION__CONFIDENTIALITY:
				return isConfidentiality();
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
			case StstoolPackage.PROVISION__PREVIOUS_PROVISION:
				getPreviousProvision().clear();
				getPreviousProvision().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.PROVISION__NEXT_PROVISIONS:
				getNextProvisions().clear();
				getNextProvisions().addAll((Collection<? extends Provision>)newValue);
				return;
			case StstoolPackage.PROVISION__TARGET:
				setTarget((Actor)newValue);
				return;
			case StstoolPackage.PROVISION__SOURCE:
				setSource((Actor)newValue);
				return;
			case StstoolPackage.PROVISION__SOURCE_RESOURCE:
				setSourceResource((TResource)newValue);
				return;
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				setTargetResource((TResource)newValue);
				return;
			case StstoolPackage.PROVISION__PRE_CONDITIONS:
				setPreConditions((String)newValue);
				return;
			case StstoolPackage.PROVISION__POST_CONDITIONS:
				setPostConditions((String)newValue);
				return;
			case StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS:
				setShowSecurityNeeds((Boolean)newValue);
				return;
			case StstoolPackage.PROVISION__INTEGRITY:
				setIntegrity((Boolean)newValue);
				return;
			case StstoolPackage.PROVISION__AVAILABILITY:
				setAvailability((Boolean)newValue);
				return;
			case StstoolPackage.PROVISION__AVAILABILITY_VALUE:
				setAvailabilityValue((Integer)newValue);
				return;
			case StstoolPackage.PROVISION__CONFIDENTIALITY:
				setConfidentiality((Boolean)newValue);
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
			case StstoolPackage.PROVISION__PREVIOUS_PROVISION:
				getPreviousProvision().clear();
				return;
			case StstoolPackage.PROVISION__NEXT_PROVISIONS:
				getNextProvisions().clear();
				return;
			case StstoolPackage.PROVISION__TARGET:
				setTarget((Actor)null);
				return;
			case StstoolPackage.PROVISION__SOURCE:
				setSource((Actor)null);
				return;
			case StstoolPackage.PROVISION__SOURCE_RESOURCE:
				setSourceResource((TResource)null);
				return;
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				setTargetResource((TResource)null);
				return;
			case StstoolPackage.PROVISION__PRE_CONDITIONS:
				setPreConditions(PRE_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__POST_CONDITIONS:
				setPostConditions(POST_CONDITIONS_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS:
				setShowSecurityNeeds(SHOW_SECURITY_NEEDS_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__INTEGRITY:
				setIntegrity(INTEGRITY_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__AVAILABILITY:
				setAvailability(AVAILABILITY_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__AVAILABILITY_VALUE:
				setAvailabilityValue(AVAILABILITY_VALUE_EDEFAULT);
				return;
			case StstoolPackage.PROVISION__CONFIDENTIALITY:
				setConfidentiality(CONFIDENTIALITY_EDEFAULT);
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
			case StstoolPackage.PROVISION__PREVIOUS_PROVISION:
				return previousProvision != null && !previousProvision.isEmpty();
			case StstoolPackage.PROVISION__NEXT_PROVISIONS:
				return nextProvisions != null && !nextProvisions.isEmpty();
			case StstoolPackage.PROVISION__TARGET:
				return target != null;
			case StstoolPackage.PROVISION__SOURCE:
				return getSource() != null;
			case StstoolPackage.PROVISION__SOURCE_RESOURCE:
				return sourceResource != null;
			case StstoolPackage.PROVISION__TARGET_RESOURCE:
				return targetResource != null;
			case StstoolPackage.PROVISION__PRE_CONDITIONS:
				return PRE_CONDITIONS_EDEFAULT == null ? preConditions != null : !PRE_CONDITIONS_EDEFAULT.equals(preConditions);
			case StstoolPackage.PROVISION__POST_CONDITIONS:
				return POST_CONDITIONS_EDEFAULT == null ? postConditions != null : !POST_CONDITIONS_EDEFAULT.equals(postConditions);
			case StstoolPackage.PROVISION__SHOW_SECURITY_NEEDS:
				return showSecurityNeeds != SHOW_SECURITY_NEEDS_EDEFAULT;
			case StstoolPackage.PROVISION__INTEGRITY:
				return integrity != INTEGRITY_EDEFAULT;
			case StstoolPackage.PROVISION__SECURITY_NEEDS:
				return SECURITY_NEEDS_EDEFAULT == null ? securityNeeds != null : !SECURITY_NEEDS_EDEFAULT.equals(securityNeeds);
			case StstoolPackage.PROVISION__AVAILABILITY:
				return availability != AVAILABILITY_EDEFAULT;
			case StstoolPackage.PROVISION__AVAILABILITY_VALUE:
				return availabilityValue != AVAILABILITY_VALUE_EDEFAULT;
			case StstoolPackage.PROVISION__CONFIDENTIALITY:
				return confidentiality != CONFIDENTIALITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (preConditions: ");
		result.append(preConditions);
		result.append(", postConditions: ");
		result.append(postConditions);
		result.append(", showSecurityNeeds: ");
		result.append(showSecurityNeeds);
		result.append(", integrity: ");
		result.append(integrity);
		result.append(", securityNeeds: ");
		result.append(securityNeeds);
		result.append(", availability: ");
		result.append(availability);
		result.append(", availabilityValue: ");
		result.append(availabilityValue);
		result.append(", confidentiality: ");
		result.append(confidentiality);
		result.append(')');
		return result.toString();
	}



	class TResourceAdapter extends AdapterImpl {

		Provision	p;

		public TResourceAdapter(Provision p) {

			this.p = p;
		}

		@Override
		public void notifyChanged(Notification msg){

			TResource obj = (TResource) msg.getNotifier();
			if (msg.getEventType() == Notification.SET && msg.getFeatureID(TResource.class) == StstoolPackage.TRESOURCE__NAME) {
				String name = obj.getName() + "";
				if (p.getTargetResource() != null && !(p.getTargetResource().getName().equals(name))) p.getTargetResource().setNameForced(name);
				try {
					if (eNotificationRequired()) eNotify(new ENotificationImpl((InternalEObject) p, Notification.SET, StstoolPackage.PROVISION__NAME, msg.getOldStringValue(), msg.getNewStringValue()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

} //ProvisionImpl
