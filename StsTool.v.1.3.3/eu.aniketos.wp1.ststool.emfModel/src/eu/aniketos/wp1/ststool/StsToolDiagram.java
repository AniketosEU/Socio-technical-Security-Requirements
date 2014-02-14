/*
* StsToolDiagram.java
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

import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Sts Tool Diagram</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagActors <em>Diag Actors</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagIResources <em>Diag IResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagTResources <em>Diag TResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagGoals <em>Diag Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getDiagEvents <em>Diag Events</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.StsToolDiagram#getGraphicalConstraintMap <em>Graphical Constraint Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram()
 * @model
 * @generated
 */
public interface StsToolDiagram extends EObject {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String	copyright	= "DISI - University of Trento";

	/**
	 * Returns the value of the '<em><b>Diag Actors</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diag Actors</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diag Actors</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_DiagActors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getDiagActors();

	/**
	 * Returns the value of the '<em><b>Diag IResources</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.IResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diag IResources</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diag IResources</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_DiagIResources()
	 * @model containment="true"
	 * @generated
	 */
	EList<IResource> getDiagIResources();

	/**
	 * Returns the value of the '<em><b>Diag TResources</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.TResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diag TResources</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diag TResources</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_DiagTResources()
	 * @model containment="true"
	 * @generated
	 */
	EList<TResource> getDiagTResources();

	/**
	 * Returns the value of the '<em><b>Diag Goals</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Goal}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diag Goals</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diag Goals</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_DiagGoals()
	 * @model containment="true"
	 * @generated
	 */
	EList<Goal> getDiagGoals();

	/**
	 * Returns the value of the '<em><b>Diag Events</b></em>' containment reference list.
	 * The list contents are of type {@link eu.aniketos.wp1.ststool.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diag Events</em>' containment reference list isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diag Events</em>' containment reference list.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_DiagEvents()
	 * @model containment="true"
	 * @generated
	 */
	EList<Event> getDiagEvents();

	/**
	 * Returns the value of the '<em><b>Graphical Constraint Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Graphical Constraint Map</em>' map isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Graphical Constraint Map</em>' map.
	 * @see eu.aniketos.wp1.ststool.StstoolPackage#getStsToolDiagram_GraphicalConstraintMap()
	 * @model mapType="eu.aniketos.wp1.ststool.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getGraphicalConstraintMap();

	String getNextAgentCounter();

	String getNextEventCounter();

	String getNextIResourceCounter();

	String getNextTResourceCounter();

	String getNextGoalCounter();

	String getNextRoleCounter();

	public Set<Agent> getAllAgents();

	public Set<Role> getAllRoles();

	public Set<Goal> getAllGoals();

	public Set<TResource> getAllTRresources();

	public Set<IResource> getAllIRresources();

	public Set<Event> getAllEvents();

	public Set<StsElement> getAllElements();

} // StsToolDiagram
