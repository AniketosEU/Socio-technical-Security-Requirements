/*
* IResourceImpl.java
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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TangibleBy;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>IResource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.IResourceImpl#getAuthorisations <em>Authorisations</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.IResourceImpl#getOwners <em>Owners</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.IResourceImpl#getTangibleElements <em>Tangible Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IResourceImpl extends ResourceImpl implements IResource {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright	= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getAuthorisations() <em>Authorisations</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getAuthorisations()
	 * @generated
	 * @ordered
	 */
	protected EList<Authorisation>	authorisations;

	/**
	 * The cached value of the '{@link #getOwners() <em>Owners</em>}' reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOwners()
	 * @generated
	 * @ordered
	 */
	protected EList<Own>					owners;

	/**
	 * The cached value of the '{@link #getTangibleElements() <em>Tangible Elements</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTangibleElements()
	 * @generated
	 * @ordered
	 */
	protected EList<TangibleBy>		tangibleElements;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.IRESOURCE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Authorisation> getAuthorisations(){
		if (authorisations == null) {
			authorisations = new EObjectWithInverseResolvingEList.ManyInverse<Authorisation>(Authorisation.class, this, StstoolPackage.IRESOURCE__AUTHORISATIONS, StstoolPackage.AUTHORISATION__RESOURCES);
		}
		return authorisations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Own> getOwners(){
		if (owners == null) {
			owners = new EObjectWithInverseResolvingEList<Own>(Own.class, this, StstoolPackage.IRESOURCE__OWNERS, StstoolPackage.OWN__TARGET);
		}
		return owners;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TangibleBy> getTangibleElements(){
		if (tangibleElements == null) {
			tangibleElements = new EObjectContainmentWithInverseEList<TangibleBy>(TangibleBy.class, this, StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS, StstoolPackage.TANGIBLE_BY__SOURCE);
		}
		return tangibleElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAuthorisations()).basicAdd(otherEnd, msgs);
			case StstoolPackage.IRESOURCE__OWNERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwners()).basicAdd(otherEnd, msgs);
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTangibleElements()).basicAdd(otherEnd, msgs);
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
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				return ((InternalEList<?>)getAuthorisations()).basicRemove(otherEnd, msgs);
			case StstoolPackage.IRESOURCE__OWNERS:
				return ((InternalEList<?>)getOwners()).basicRemove(otherEnd, msgs);
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				return ((InternalEList<?>)getTangibleElements()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				return getAuthorisations();
			case StstoolPackage.IRESOURCE__OWNERS:
				return getOwners();
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				return getTangibleElements();
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
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				getAuthorisations().clear();
				getAuthorisations().addAll((Collection<? extends Authorisation>)newValue);
				return;
			case StstoolPackage.IRESOURCE__OWNERS:
				getOwners().clear();
				getOwners().addAll((Collection<? extends Own>)newValue);
				return;
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				getTangibleElements().clear();
				getTangibleElements().addAll((Collection<? extends TangibleBy>)newValue);
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
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				getAuthorisations().clear();
				return;
			case StstoolPackage.IRESOURCE__OWNERS:
				getOwners().clear();
				return;
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				getTangibleElements().clear();
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
			case StstoolPackage.IRESOURCE__AUTHORISATIONS:
				return authorisations != null && !authorisations.isEmpty();
			case StstoolPackage.IRESOURCE__OWNERS:
				return owners != null && !owners.isEmpty();
			case StstoolPackage.IRESOURCE__TANGIBLE_ELEMENTS:
				return tangibleElements != null && !tangibleElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IResourceImpl
