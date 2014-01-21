/*
* RepudiationType.java
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Repudiation Type</b></em>', and utility methods for working with them. <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getRepudiationType()
 * @model
 * @generated
 */
public enum RepudiationType implements Enumerator {
	/**
	 * The '<em><b>NO REPUDIATION</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NO_REPUDIATION_VALUE
	 * @generated
	 * @ordered
	 */
	NO_REPUDIATION(0, "NO_REPUDIATION", "NO_REPUDIATION"),

	/**
	 * The '<em><b>DELEGATOR REPUDIATION</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DELEGATOR_REPUDIATION_VALUE
	 * @generated
	 * @ordered
	 */
	DELEGATOR_REPUDIATION(1, "DELEGATOR_REPUDIATION", "DELEGATOR_REPUDIATION"), /**
	 * The '<em><b>DUAL REPUDIATION</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DUAL_REPUDIATION_VALUE
	 * @generated
	 * @ordered
	 */
	DUAL_REPUDIATION(2, "DUAL_REPUDIATION", "DUAL_REPUDIATION"), /**
	 * The '<em><b>DELEGATEEE REPUDIATION</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #DELEGATEEE_REPUDIATION_VALUE
	 * @generated
	 * @ordered
	 */
	DELEGATEEE_REPUDIATION(3, "DELEGATEEE_REPUDIATION", "DELEGATEEE_REPUDIATION");

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String						copyright							= "DISI - University of Trento";

	/**
	 * The '<em><b>NO REPUDIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO REPUDIATION</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_REPUDIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							NO_REPUDIATION_VALUE				= 0;

	/**
	 * The '<em><b>DELEGATOR REPUDIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELEGATOR REPUDIATION</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELEGATOR_REPUDIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							DELEGATOR_REPUDIATION_VALUE	= 1;

	/**
	 * The '<em><b>DUAL REPUDIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DUAL REPUDIATION</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DUAL_REPUDIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							DUAL_REPUDIATION_VALUE			= 2;

	/**
	 * The '<em><b>DELEGATEEE REPUDIATION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DELEGATEEE REPUDIATION</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DELEGATEEE_REPUDIATION
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							DELEGATEEE_REPUDIATION_VALUE	= 3;

	/**
	 * An array of all the '<em><b>Repudiation Type</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final RepudiationType[]		VALUES_ARRAY						= new RepudiationType[] {
			NO_REPUDIATION,
			DELEGATOR_REPUDIATION,
			DUAL_REPUDIATION,
			DELEGATEEE_REPUDIATION,
		};

	/**
	 * A public read-only list of all the '<em><b>Repudiation Type</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RepudiationType>	VALUES								= Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Repudiation Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RepudiationType get(String literal){
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RepudiationType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Repudiation Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RepudiationType getByName(String name){
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RepudiationType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Repudiation Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RepudiationType get(int value){
		switch (value) {
			case NO_REPUDIATION_VALUE: return NO_REPUDIATION;
			case DELEGATOR_REPUDIATION_VALUE: return DELEGATOR_REPUDIATION;
			case DUAL_REPUDIATION_VALUE: return DUAL_REPUDIATION;
			case DELEGATEEE_REPUDIATION_VALUE: return DELEGATEEE_REPUDIATION;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final int		value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String	name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private final String	literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private RepudiationType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue(){
	  return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName(){
	  return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral(){
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		return literal;
	}

} //RepudiationType
