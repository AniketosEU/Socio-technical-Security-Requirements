/*
* Authorisation.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Authorisation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#getTarget <em>Target</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#getGoals <em>Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#getResources <em>Resources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#getTimesTransferable <em>Times Transferable</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#isUsage <em>Usage</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#isModification <em>Modification</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#isProduce <em>Produce</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Authorisation#isDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation()
 * @model
 * @generated
 */
public interface Authorisation extends StsRelation {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getOutgoingAuthorisations <em>Outgoing Authorisations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Source()
	 * @see eu.aniketos.wp1.ststool.Actor#getOutgoingAuthorisations
	 * @model opposite="outgoingAuthorisations" required="true" transient="false"
	 * @generated
	 */
	Actor getSource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Actor value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Actor#getIncomingAuthorisations <em>Incoming Authorisations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Actor)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Target()
	 * @see eu.aniketos.wp1.ststool.Actor#getIncomingAuthorisations
	 * @model opposite="incomingAuthorisations" required="true"
	 * @generated
	 */
	Actor getTarget();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Actor value);

	/**
	 * Returns the value of the '<em><b>Goals</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Goal}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Goal#getAuthorisations <em>Authorisations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Goals()
	 * @see eu.aniketos.wp1.ststool.Goal#getAuthorisations
	 * @model opposite="authorisations"
	 * @generated
	 */
	EList<Goal> getGoals();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.IResource}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.IResource#getAuthorisations <em>Authorisations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Resources()
	 * @see eu.aniketos.wp1.ststool.IResource#getAuthorisations
	 * @model opposite="authorisations"
	 * @generated
	 */
	EList<IResource> getResources();

	/**
	 * Returns the value of the '<em><b>Times Transferable</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Times Transferable</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Times Transferable</em>' attribute.
	 * @see #setTimesTransferable(int)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_TimesTransferable()
	 * @model default="-1"
	 * @generated
	 */
	int getTimesTransferable();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#getTimesTransferable <em>Times Transferable</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Times Transferable</em>' attribute.
	 * @see #getTimesTransferable()
	 * @generated
	 */
	void setTimesTransferable(int value);

	/**
	 * Returns the value of the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage</em>' attribute.
	 * @see #setUsage(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Usage()
	 * @model
	 * @generated
	 */
	boolean isUsage();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#isUsage <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage</em>' attribute.
	 * @see #isUsage()
	 * @generated
	 */
	void setUsage(boolean value);

	/**
	 * Returns the value of the '<em><b>Modification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modification</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modification</em>' attribute.
	 * @see #setModification(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Modification()
	 * @model
	 * @generated
	 */
	boolean isModification();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#isModification <em>Modification</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modification</em>' attribute.
	 * @see #isModification()
	 * @generated
	 */
	void setModification(boolean value);

	/**
	 * Returns the value of the '<em><b>Produce</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Produce</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Produce</em>' attribute.
	 * @see #setProduce(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Produce()
	 * @model
	 * @generated
	 */
	boolean isProduce();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#isProduce <em>Produce</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Produce</em>' attribute.
	 * @see #isProduce()
	 * @generated
	 */
	void setProduce(boolean value);

	/**
	 * Returns the value of the '<em><b>Distribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Distribution</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Distribution</em>' attribute.
	 * @see #setDistribution(boolean)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getAuthorisation_Distribution()
	 * @model
	 * @generated
	 */
	boolean isDistribution();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Authorisation#isDistribution <em>Distribution</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Distribution</em>' attribute.
	 * @see #isDistribution()
	 * @generated
	 */
	void setDistribution(boolean value);

} // Authorisation
