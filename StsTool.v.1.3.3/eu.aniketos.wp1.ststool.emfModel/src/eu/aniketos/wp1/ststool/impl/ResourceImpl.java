/*
* ResourceImpl.java
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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Resource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ResourceImpl#getPartsOf <em>Parts Of</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.ResourceImpl#getSubParts <em>Sub Parts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceImpl extends StsElementImpl implements Resource {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String	copyright	= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getPartsOf() <em>Parts Of</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPartsOf()
	 * @generated
	 * @ordered
	 */
	protected EList<PartOf>		partsOf;

	/**
	 * The cached value of the '{@link #getSubParts() <em>Sub Parts</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSubParts()
	 * @generated
	 * @ordered
	 */
	protected EList<PartOf>		subParts;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PartOf> getPartsOf(){
		if (partsOf == null) {
			partsOf = new EObjectContainmentWithInverseEList<PartOf>(PartOf.class, this, StstoolPackage.RESOURCE__PARTS_OF, StstoolPackage.PART_OF__SOURCE);
		}
		return partsOf;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PartOf> getSubParts(){
		if (subParts == null) {
			subParts = new EObjectWithInverseResolvingEList<PartOf>(PartOf.class, this, StstoolPackage.RESOURCE__SUB_PARTS, StstoolPackage.PART_OF__TARGET);
		}
		return subParts;
	}


	public TResource getSourceResource(TResource resource){
		return getSourceResourceRecursive(resource, new ArrayList<Provision>());
	}

	private TResource getSourceResourceRecursive(TResource resource,List<Provision> visitedProvision){
		if (resource.getProvidedFrom().size() == 0) return resource;
		for (Provision p : resource.getProvidedFrom()) {
			if (!visitedProvision.contains(p)) {
				visitedProvision.add(p);
				TResource result = getSourceResourceRecursive(p.getSourceResource(), visitedProvision);
				if (result != null) return result;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.RESOURCE__PARTS_OF:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPartsOf()).basicAdd(otherEnd, msgs);
			case StstoolPackage.RESOURCE__SUB_PARTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubParts()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.RESOURCE__PARTS_OF:
				return ((InternalEList<?>)getPartsOf()).basicRemove(otherEnd, msgs);
			case StstoolPackage.RESOURCE__SUB_PARTS:
				return ((InternalEList<?>)getSubParts()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case StstoolPackage.RESOURCE__PARTS_OF:
				return getPartsOf();
			case StstoolPackage.RESOURCE__SUB_PARTS:
				return getSubParts();
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
			case StstoolPackage.RESOURCE__PARTS_OF:
				getPartsOf().clear();
				getPartsOf().addAll((Collection<? extends PartOf>)newValue);
				return;
			case StstoolPackage.RESOURCE__SUB_PARTS:
				getSubParts().clear();
				getSubParts().addAll((Collection<? extends PartOf>)newValue);
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
			case StstoolPackage.RESOURCE__PARTS_OF:
				getPartsOf().clear();
				return;
			case StstoolPackage.RESOURCE__SUB_PARTS:
				getSubParts().clear();
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
			case StstoolPackage.RESOURCE__PARTS_OF:
				return partsOf != null && !partsOf.isEmpty();
			case StstoolPackage.RESOURCE__SUB_PARTS:
				return subParts != null && !subParts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResourceImpl
