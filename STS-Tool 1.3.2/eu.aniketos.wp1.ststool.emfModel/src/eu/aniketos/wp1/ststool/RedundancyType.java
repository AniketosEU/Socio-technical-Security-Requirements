/*
* RedundancyType.java
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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Redundancy Type</b></em>', and utility methods for working with them. <!-- end-user-doc -->
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getRedundancyType()
 * @model
 * @generated
 */
public enum RedundancyType implements Enumerator {
	/**
	 * The '<em><b>NO REDUNDANCY</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #NO_REDUNDANCY_VALUE
	 * @generated
	 * @ordered
	 */
	NO_REDUNDANCY(0, "NO_REDUNDANCY", "NO_REDUNDANCY"), /**
	 * The '<em><b>TRUE SINGLE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRUE_SINGLE_VALUE
	 * @generated
	 * @ordered
	 */
	TRUE_SINGLE(1, "TRUE_SINGLE", "TRUE_SINGLE"),

	/**
	 * The '<em><b>TRUE MULTI</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #TRUE_MULTI_VALUE
	 * @generated
	 * @ordered
	 */
	TRUE_MULTI(2, "TRUE_MULTI", "TRUE_MULTI"),

	/**
	 * The '<em><b>FALLBACK SINGLE</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #FALLBACK_SINGLE_VALUE
	 * @generated
	 * @ordered
	 */
	FALLBACK_SINGLE(3, "FALLBACK_SINGLE", "FALLBACK_SINGLE"),

	/**
	 * The '<em><b>FALLBACK MULTI</b></em>' literal object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #FALLBACK_MULTI_VALUE
	 * @generated
	 * @ordered
	 */
	FALLBACK_MULTI(4, "FALLBACK_MULTI", "FALLBACK_MULTI");

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String						copyright					= "DISI - University of Trento";

	/**
	 * The '<em><b>NO REDUNDANCY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NO REDUNDANCY</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_REDUNDANCY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							NO_REDUNDANCY_VALUE		= 0;

	/**
	 * The '<em><b>TRUE SINGLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRUE SINGLE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRUE_SINGLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							TRUE_SINGLE_VALUE			= 1;

	/**
	 * The '<em><b>TRUE MULTI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRUE MULTI</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRUE_MULTI
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							TRUE_MULTI_VALUE			= 2;

	/**
	 * The '<em><b>FALLBACK SINGLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FALLBACK SINGLE</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FALLBACK_SINGLE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							FALLBACK_SINGLE_VALUE	= 3;

	/**
	 * The '<em><b>FALLBACK MULTI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FALLBACK MULTI</b></em>' literal object isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FALLBACK_MULTI
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int							FALLBACK_MULTI_VALUE		= 4;

	/**
	 * An array of all the '<em><b>Redundancy Type</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static final RedundancyType[]		VALUES_ARRAY				= new RedundancyType[] {
			NO_REDUNDANCY,
			TRUE_SINGLE,
			TRUE_MULTI,
			FALLBACK_SINGLE,
			FALLBACK_MULTI,
		};

	/**
	 * A public read-only list of all the '<em><b>Redundancy Type</b></em>' enumerators.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RedundancyType>	VALUES						= Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Redundancy Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RedundancyType get(String literal){
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RedundancyType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Redundancy Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RedundancyType getByName(String name){
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RedundancyType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Redundancy Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static RedundancyType get(int value){
		switch (value) {
			case NO_REDUNDANCY_VALUE: return NO_REDUNDANCY;
			case TRUE_SINGLE_VALUE: return TRUE_SINGLE;
			case TRUE_MULTI_VALUE: return TRUE_MULTI;
			case FALLBACK_SINGLE_VALUE: return FALLBACK_SINGLE;
			case FALLBACK_MULTI_VALUE: return FALLBACK_MULTI;
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
	private RedundancyType(int value, String name, String literal) {
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

} //RedundancyType
