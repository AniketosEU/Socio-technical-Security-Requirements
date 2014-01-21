/*
* TResource.java
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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>TResource</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getProvidedTo <em>Provided To</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getProvidedFrom <em>Provided From</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getActorOwner <em>Actor Owner</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getIntangibleElements <em>Intangible Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getGoalsNeeding <em>Goals Needing</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getGoalsProducing <em>Goals Producing</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.TResource#getGoalsModifing <em>Goals Modifing</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource()
 * @model
 * @generated
 */
public interface TResource extends Resource, Threatable {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";


	public void setNameForced(String newName);

	/**
	 * Returns the value of the '<em><b>Provided To</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided To</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided To</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_ProvidedTo()
	 * @model
	 * @generated
	 */
	EList<Provision> getProvidedTo();

	/**
	 * Returns the value of the '<em><b>Provided From</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Provision}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Provision#getTargetResource <em>Target Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided From</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided From</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_ProvidedFrom()
	 * @see eu.aniketos.wp1.ststool.Provision#getTargetResource
	 * @model opposite="targetResource"
	 * @generated
	 */
	EList<Provision> getProvidedFrom();

	/**
	 * Returns the value of the '<em><b>Actor Owner</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getTResources <em>TResources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor Owner</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor Owner</em>' container reference.
	 * @see #setActorOwner(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_ActorOwner()
	 * @see eu.aniketos.wp1.ststool.Actor#getTResources
	 * @model opposite="tResources" transient="false"
	 * @generated
	 */
	Actor getActorOwner();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.TResource#getActorOwner <em>Actor Owner</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor Owner</em>' container reference.
	 * @see #getActorOwner()
	 * @generated
	 */
	void setActorOwner(Actor value);

	/**
	 * Returns the value of the '<em><b>Goals Modifing</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Modify}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Modify#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals Modifing</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals Modifing</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_GoalsModifing()
	 * @see eu.aniketos.wp1.ststool.Modify#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Modify> getGoalsModifing();

	/**
	 * Returns the value of the '<em><b>Goals Producing</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Produce}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Produce#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals Producing</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals Producing</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_GoalsProducing()
	 * @see eu.aniketos.wp1.ststool.Produce#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Produce> getGoalsProducing();

	/**
	 * Returns the value of the '<em><b>Goals Needing</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Need}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Need#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals Needing</em>' containment reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals Needing</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_GoalsNeeding()
	 * @see eu.aniketos.wp1.ststool.Need#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Need> getGoalsNeeding();

	/**
	 * Returns the value of the '<em><b>Intangible Elements</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.TangibleBy}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.TangibleBy#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intangible Elements</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intangible Elements</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getTResource_IntangibleElements()
	 * @see eu.aniketos.wp1.ststool.TangibleBy#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<TangibleBy> getIntangibleElements();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	boolean isDeletable();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean canBeProvided();

} // TResource
