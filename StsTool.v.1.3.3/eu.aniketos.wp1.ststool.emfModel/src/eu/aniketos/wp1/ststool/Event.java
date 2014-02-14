/*
* Event.java
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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Event</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.Event#getCountermeasures <em>Countermeasures</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Event#getThreatedElements <em>Threated Elements</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.Event#getEventID <em>Event ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends StsElement {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Countermeasures</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Countermeasures</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Countermeasures</em>' attribute.
	 * @see #setCountermeasures(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getEvent_Countermeasures()
	 * @model
	 * @generated
	 */
	String getCountermeasures();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Event#getCountermeasures <em>Countermeasures</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Countermeasures</em>' attribute.
	 * @see #getCountermeasures()
	 * @generated
	 */
	void setCountermeasures(String value);

	/**
	 * Returns the value of the '<em><b>Threated Elements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Threat}.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.Threat#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Threated Elements</em>' reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Threated Elements</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getEvent_ThreatedElements()
	 * @see eu.aniketos.wp1.ststool.Threat#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Threat> getThreatedElements();

	/**
	 * Returns the value of the '<em><b>Event ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event ID</em>' attribute.
	 * @see #setEventID(String)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getEvent_EventID()
	 * @model
	 * @generated
	 */
	String getEventID();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.Event#getEventID <em>Event ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event ID</em>' attribute.
	 * @see #getEventID()
	 * @generated
	 */
	void setEventID(String value);

} // Event
