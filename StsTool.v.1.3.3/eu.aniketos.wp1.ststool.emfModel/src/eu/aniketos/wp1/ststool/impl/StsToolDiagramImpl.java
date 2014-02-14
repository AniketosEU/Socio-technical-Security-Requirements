/*
* StsToolDiagramImpl.java
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

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.TResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Sts Tool Diagram</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getDiagActors <em>Diag Actors</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getDiagIResources <em>Diag IResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getDiagTResources <em>Diag TResources</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getDiagGoals <em>Diag Goals</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getDiagEvents <em>Diag Events</em>}</li>
 *   <li>{@link eu.aniketos.wp1.ststool.impl.StsToolDiagramImpl#getGraphicalConstraintMap <em>Graphical Constraint Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StsToolDiagramImpl extends EObjectImpl implements StsToolDiagram {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String			copyright	= "DISI - University of Trento";

	/**
	 * The cached value of the '{@link #getDiagActors() <em>Diag Actors</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiagActors()
	 * @generated
	 * @ordered
	 */
	protected EList<Actor>				diagActors;

	/**
	 * The cached value of the '{@link #getDiagIResources() <em>Diag IResources</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiagIResources()
	 * @generated
	 * @ordered
	 */
	protected EList<IResource>			diagIResources;

	/**
	 * The cached value of the '{@link #getDiagTResources() <em>Diag TResources</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiagTResources()
	 * @generated
	 * @ordered
	 */
	protected EList<TResource>			diagTResources;

	/**
	 * The cached value of the '{@link #getDiagGoals() <em>Diag Goals</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiagGoals()
	 * @generated
	 * @ordered
	 */
	protected EList<Goal>				diagGoals;

	/**
	 * The cached value of the '{@link #getDiagEvents() <em>Diag Events</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getDiagEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event>				diagEvents;

	/**
	 * The cached value of the '{@link #getGraphicalConstraintMap() <em>Graphical Constraint Map</em>}' map.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getGraphicalConstraintMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String>	graphicalConstraintMap;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected StsToolDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return StstoolPackage.Literals.STS_TOOL_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Actor> getDiagActors(){
		if (diagActors == null) {
			diagActors = new EObjectContainmentEList<Actor>(Actor.class, this, StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS);
		}
		return diagActors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IResource> getDiagIResources(){
		if (diagIResources == null) {
			diagIResources = new EObjectContainmentEList<IResource>(IResource.class, this, StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES);
		}
		return diagIResources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TResource> getDiagTResources(){
		if (diagTResources == null) {
			diagTResources = new EObjectContainmentEList<TResource>(TResource.class, this, StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES);
		}
		return diagTResources;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Goal> getDiagGoals(){
		if (diagGoals == null) {
			diagGoals = new EObjectContainmentEList<Goal>(Goal.class, this, StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS);
		}
		return diagGoals;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getDiagEvents(){
		if (diagEvents == null) {
			diagEvents = new EObjectContainmentEList<Event>(Event.class, this, StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS);
		}
		return diagEvents;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getGraphicalConstraintMap(){
		if (graphicalConstraintMap == null) {
			graphicalConstraintMap = new EcoreEMap<String,String>(StstoolPackage.Literals.STRING_TO_STRING_MAP, StringToStringMapImpl.class, this, StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP);
		}
		return graphicalConstraintMap;
	}

	private final String	KEY_AGENT_COUNTER	= "AgentCounter";
	private final String	KEY_ROLE_COUNTER	= "RoleCounter";
	private final String	KEY_GOAL_COUNTER	= "GoalCounter";
	private final String	KEY_TRES_COUNTER	= "TRresCounter";
	private final String	KEY_IRES_COUNTER	= "IResCounter";
	private final String	KEY_EVENT_COUNTER	= "EventCounter";


	private NumberFormat	nf						= null;

	private String getNextCounter(String key){

		if (nf == null) {
			nf = NumberFormat.getIntegerInstance();
			nf.setMinimumIntegerDigits(2);
		}

		int num = 0;
		try {
			String s = (String) getGraphicalConstraintMap().get(key);
			if (s == null) s = "0";
			num = Integer.parseInt(s);
			num++;
			getGraphicalConstraintMap().put(key, Integer.toString(num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nf.format(num);
	}

	public String getNextAgentCounter(){

		return getNextCounter(KEY_AGENT_COUNTER);
	}


	public String getNextRoleCounter(){

		return getNextCounter(KEY_ROLE_COUNTER);
	}


	public String getNextGoalCounter(){

		return getNextCounter(KEY_GOAL_COUNTER);
	}

	public String getNextTResourceCounter(){

		return getNextCounter(KEY_TRES_COUNTER);
	}

	public String getNextIResourceCounter(){

		return getNextCounter(KEY_IRES_COUNTER);
	}

	public String getNextEventCounter(){

		return getNextCounter(KEY_EVENT_COUNTER);
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS:
				return ((InternalEList<?>)getDiagActors()).basicRemove(otherEnd, msgs);
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES:
				return ((InternalEList<?>)getDiagIResources()).basicRemove(otherEnd, msgs);
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES:
				return ((InternalEList<?>)getDiagTResources()).basicRemove(otherEnd, msgs);
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS:
				return ((InternalEList<?>)getDiagGoals()).basicRemove(otherEnd, msgs);
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS:
				return ((InternalEList<?>)getDiagEvents()).basicRemove(otherEnd, msgs);
			case StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP:
				return ((InternalEList<?>)getGraphicalConstraintMap()).basicRemove(otherEnd, msgs);
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
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS:
				return getDiagActors();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES:
				return getDiagIResources();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES:
				return getDiagTResources();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS:
				return getDiagGoals();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS:
				return getDiagEvents();
			case StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP:
				if (coreType) return getGraphicalConstraintMap();
				else return getGraphicalConstraintMap().map();
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
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS:
				getDiagActors().clear();
				getDiagActors().addAll((Collection<? extends Actor>)newValue);
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES:
				getDiagIResources().clear();
				getDiagIResources().addAll((Collection<? extends IResource>)newValue);
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES:
				getDiagTResources().clear();
				getDiagTResources().addAll((Collection<? extends TResource>)newValue);
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS:
				getDiagGoals().clear();
				getDiagGoals().addAll((Collection<? extends Goal>)newValue);
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS:
				getDiagEvents().clear();
				getDiagEvents().addAll((Collection<? extends Event>)newValue);
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP:
				((EStructuralFeature.Setting)getGraphicalConstraintMap()).set(newValue);
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
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS:
				getDiagActors().clear();
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES:
				getDiagIResources().clear();
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES:
				getDiagTResources().clear();
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS:
				getDiagGoals().clear();
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS:
				getDiagEvents().clear();
				return;
			case StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP:
				getGraphicalConstraintMap().clear();
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
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_ACTORS:
				return diagActors != null && !diagActors.isEmpty();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_IRESOURCES:
				return diagIResources != null && !diagIResources.isEmpty();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_TRESOURCES:
				return diagTResources != null && !diagTResources.isEmpty();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_GOALS:
				return diagGoals != null && !diagGoals.isEmpty();
			case StstoolPackage.STS_TOOL_DIAGRAM__DIAG_EVENTS:
				return diagEvents != null && !diagEvents.isEmpty();
			case StstoolPackage.STS_TOOL_DIAGRAM__GRAPHICAL_CONSTRAINT_MAP:
				return graphicalConstraintMap != null && !graphicalConstraintMap.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	public Set<Agent> getAllAgents(){

		Set<Agent> result = new HashSet<Agent>();
		for (Actor a : getDiagActors()) {
			if (a instanceof Agent) result.add((Agent) a);
		}
		return result;
	}

	public Set<Role> getAllRoles(){

		Set<Role> result = new HashSet<Role>();
		for (Actor a : getDiagActors()) {
			if (a instanceof Role) result.add((Role) a);
		}
		return result;
	}

	/**
	 * delegated goal not included!
	 */
	public Set<Goal> getAllGoals(){

		Set<Goal> result = new HashSet<Goal>();
		for (Actor a : getDiagActors()) {
			for (Goal g : a.getGoals()) {
				if (g.getDelegatedFrom().size() == 0) {
					result.add(g);
				}
			}
		}

		return result;
	}

	/**
	 * provided resources not included!
	 */
	public Set<TResource> getAllTRresources(){

		Set<TResource> result = new HashSet<TResource>();
		for (Actor a : getDiagActors()) {
			for (TResource t : a.getTResources()) {
				if (t.getProvidedFrom().size() == 0) {
					result.add(t);
				}
			}
		}
		return result;
	}

	public Set<IResource> getAllIRresources(){

		Set<IResource> result = new HashSet<IResource>(getDiagIResources());
		return result;
	}

	public Set<Event> getAllEvents(){

		Set<Event> result = new HashSet<Event>(getDiagEvents());
		return result;
	}

	public Set<StsElement> getAllElements(){

		Set<StsElement> result = new HashSet<StsElement>();
		result.addAll(getAllAgents());
		result.addAll(getAllRoles());
		result.addAll(getAllGoals());
		result.addAll(getAllTRresources());
		result.addAll(getAllIRresources());
		result.addAll(getAllEvents());
		return result;
	}

} //StsToolDiagramImpl
