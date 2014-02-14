/*
* IncompatibleDuties.java
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


/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Incompatible Duties</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getSource <em>Source</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getIncompatibleDuties()
 * @model
 * @generated
 */
public interface IncompatibleDuties extends StsRelation {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesOut <em>Incompatible Duties Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(SeparationOfDuties)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getIncompatibleDuties_Source()
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesOut
	 * @model opposite="incompatibleDutiesOut" required="true" transient="false"
	 * @generated
	 */
	SeparationOfDuties getSource();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(SeparationOfDuties value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesIn <em>Incompatible Duties In</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(SeparationOfDuties)
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getIncompatibleDuties_Target()
	 * @see eu.aniketos.wp1.ststool.SeparationOfDuties#getIncompatibleDutiesIn
	 * @model opposite="incompatibleDutiesIn" required="true"
	 * @generated
	 */
	SeparationOfDuties getTarget();

	/**
	 * Sets the value of the '{@link eu.aniketos.wp1.ststool.IncompatibleDuties#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(SeparationOfDuties value);

} // IncompatibleDuties
