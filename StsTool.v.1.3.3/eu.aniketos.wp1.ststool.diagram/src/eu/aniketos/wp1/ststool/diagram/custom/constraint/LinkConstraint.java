/*
* LinkConstraint.java
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
package eu.aniketos.wp1.ststool.diagram.custom.constraint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Dependency;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalContribution;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.GoalDecompositionAND;
import eu.aniketos.wp1.ststool.GoalDecompositionOR;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.Threatable;

public abstract class LinkConstraint {

	/**
	 * Method called to check if a Need relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistGoalNeed(Goal container,Goal source,TResource target){

		if (source != null && target != null) {
			if (!source.getActorOwner().equals(target.getActorOwner())) return false;
			for (Need n : source.getResourceNeeded()) {
				if (n.getTarget() == target) return false;
			}
		}
		return true;
	}

	/**
	 * Method called to check if a Produce relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistGoalProduce(Goal container,Goal source,TResource target){

		if (source != null && target != null) {
			if (!source.getActorOwner().equals(target.getActorOwner())) return false;
			if (target.getGoalsProducing().size() > 0) return false;
			if (target.getProvidedFrom().size() > 0) return false;
			for (Produce n : source.getResourcesProduced()) {
				if (n.getTarget() == target) return false;
			}
			/*for (Modify n : source.getResourcesModified()) {
				if (n.getTarget() == target) return false;
			}*/
		}
		return true;
	}

	/**
	 * Method called to check if a Modify relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistGoalModify(Goal container,Goal source,TResource target){

		if (source != null && target != null) {
			if (!source.getActorOwner().equals(target.getActorOwner())) return false;
			for (Modify n : source.getResourcesModified()) {
				if (n.getTarget() == target) return false;
			}
			/*for (Produce p : source.getResourcesProduced()) {
				if (p.getTarget() == target) return false;
			}*/
		}
		return true;
	}

	/**
	 * Method called to check if a Positive Contribution relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistContributionPositive(Goal container,Goal source,Goal target){

		if (target == null) return true;
		if (source == target) return false;
		Goal g = source;
		for (GoalContribution c : g.getOutgoingContributions()) {
			if (c.getTarget() == target) { return false; }
		}
		while (g != null) {
			if (g.getIncomingDecompositions() != null) {
				if (g.getIncomingDecompositions().getSource() == target) {//parent
					return false;
				} else {
					g = g.getIncomingDecompositions().getSource();
				}
			} else {
				g = null;
			}//end while
		}

		if (isGoalChildOfDecompositionRecursive(source, target)) return false;
		if (getOriginalDelegatedGoal(source) == getOriginalDelegatedGoal(target)) return false;
		return true;
	}

	/**
	 * Method called to check if a Negative Contribution relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistContributionNegative(Goal container,Goal source,Goal target){

		if (target == null) return true;
		if (source == target) return false;
		Goal g = source;
		for (GoalContribution c : g.getOutgoingContributions()) {
			if (c.getTarget() == target) return false;
		}

		while (g != null) { //check if is a parent
			if (g.getIncomingDecompositions() != null) {
				if (g.getIncomingDecompositions().getSource() == target) {//parent
					return false;
				} else {
					g = g.getIncomingDecompositions().getSource();
				}
			} else {
				g = null;
			}//end while
		}

		if (isGoalChildOfDecompositionRecursive(source, target)) return false;
		if (getOriginalDelegatedGoal(source) == getOriginalDelegatedGoal(target)) return false;

		return true;
	}

	/**
	 * Method called to check if a Decomposition And relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistDecomposition_AND(Goal container,Goal source,Goal target){
		if(source !=null && source.getDelegatedTo().size()>0)return false;
		if(source !=null && source.isCapability())return false;
		if (!(source.getOutgoingDecompositions().isEmpty()) && !(source.getOutgoingDecompositions().get(0) instanceof GoalDecompositionAND)) return false;
		if (target == null) return true;
		if (target == source) return false;
		if (source != null && target != null) {
			if (!source.getActorOwner().equals(target.getActorOwner())) return false;
		}
		
		
		if (target.getIncomingDecompositions() != null) return false;
		for (GoalDecomposition r : source.getOutgoingDecompositions()) {
			if (r.getTarget() == target) return false;
		}
		for (GoalDecomposition r : target.getOutgoingDecompositions()) {
			if (r.getTarget() == source) return false;
		}

		for (IncompatibleDuties id : source.getIncompatibleDutiesOut()) {
			if (id.getTarget() == target || isG1childofG2((Goal) id.getTarget(), target)) { return false; }
		}
		for (IncompatibleDuties id : source.getIncompatibleDutiesIn()) {
			if (isG1childofG2((Goal) id.getSource(), target)) { return false; }
		}
		for (IncompatibleDuties id : target.getIncompatibleDutiesOut()) {
			if (id.getTarget() == source || isG1childofG2(source, (Goal) id.getTarget())) { return false; }
		}
		for (IncompatibleDuties id : target.getIncompatibleDutiesIn()) {
			if (isG1childofG2(source, (Goal) id.getSource())) { return false; }
		}

		for (CompatibleDuties id : source.getCompatibleDutiesOut()) {
			if (id.getTarget() == target || isG1childofG2((Goal) id.getTarget(), target)) { return false; }
		}
		for (CompatibleDuties id : source.getCompatibleDutiesIn()) {
			if (isG1childofG2((Goal) id.getSource(), target)) { return false; }
		}
		for (CompatibleDuties id : target.getCompatibleDutiesOut()) {
			if (id.getTarget() == source || isG1childofG2(source, (Goal) id.getTarget())) { return false; }
		}
		for (CompatibleDuties id : target.getCompatibleDutiesIn()) {
			if (isG1childofG2(source, (Goal) id.getSource())) { return false; }
		}


		return evaluateGoalCicle(source, target);
	}

	/**
	 * Method called to check if a Decomposition OR relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistDecomposition_OR(Goal container,Goal source,Goal target){
		
		if(source !=null && source.getDelegatedTo().size()>0)return false;
		if(source !=null && source.isCapability())return false;
		if (!(source.getOutgoingDecompositions().isEmpty()) && !(source.getOutgoingDecompositions().get(0) instanceof GoalDecompositionOR)) return false;
		if (target == null) return true;
		if (target == source) return false;
		if (source != null && target != null) {
			if (!source.getActorOwner().equals(target.getActorOwner())) return false;
		}
		
		
		if (target.getIncomingDecompositions() != null) return false;
		for (GoalDecomposition r : source.getOutgoingDecompositions()) {
			if (r.getTarget() == target) return false;
		}
		for (GoalDecomposition r : target.getOutgoingDecompositions()) {
			if (r.getTarget() == source) return false;
		}

		for (IncompatibleDuties id : source.getIncompatibleDutiesOut()) {
			if (id.getTarget() == target || isG1childofG2((Goal) id.getTarget(), target)) { return false; }
		}
		for (IncompatibleDuties id : source.getIncompatibleDutiesIn()) {
			if (isG1childofG2((Goal) id.getSource(), target)) { return false; }
		}
		for (IncompatibleDuties id : target.getIncompatibleDutiesOut()) {
			if (id.getTarget() == source || isG1childofG2(source, (Goal) id.getTarget())) { return false; }
		}
		for (IncompatibleDuties id : target.getIncompatibleDutiesIn()) {
			if (isG1childofG2(source, (Goal) id.getSource())) { return false; }
		}

		for (CompatibleDuties id : source.getCompatibleDutiesOut()) {
			if (id.getTarget() == target || isG1childofG2((Goal) id.getTarget(), target)) { return false; }
		}
		for (CompatibleDuties id : source.getCompatibleDutiesIn()) {
			if (isG1childofG2((Goal) id.getSource(), target)) { return false; }
		}
		for (CompatibleDuties id : target.getCompatibleDutiesOut()) {
			if (id.getTarget() == source || isG1childofG2(source, (Goal) id.getTarget())) { return false; }
		}
		for (CompatibleDuties id : target.getCompatibleDutiesIn()) {
			if (isG1childofG2(source, (Goal) id.getSource())) { return false; }
		}


		return evaluateGoalCicle(source, target);
	}

	private static boolean isG1childofG2(Goal g1,Goal g2){
		Goal gTest = g1;
		while (gTest != null) {
			if (gTest.getIncomingDecompositions() != null) {
				if (gTest.getIncomingDecompositions().getSource() == g2) {//parent
					return true;
				} else {
					gTest = gTest.getIncomingDecompositions().getSource();
				}
			} else {
				gTest = null;
			}
		}
		return false;
	}

	/**
	 * Method called to check if a Own relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistActorOwn(Actor container,Actor source,IResource target){

		if (target != null && target.getOwners().size() > 0) return false;

		if (source != null && target != null) {
			for (Own r : source.getIResources()) {
				if (r.getTarget() == target) return false;
			}
		}
		return true;
	}

	/**
	 * Method called to check if a PartOf relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistPartOf(Resource container,Resource source,Resource target){

		if (source != null && target != null) {

			if (source == target) return false;
			if (source.getClass() != target.getClass()) return false;

			for (PartOf r : source.getPartsOf()) {
				if (r.getTarget() == target) return false;
			}
			for (PartOf r : target.getPartsOf()) {
				if (r.getTarget() == source) return false;
			}
		}
		return true;
	}


	/**
	 * Method called to check if a TangibleBy relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistTangibleBy(IResource container,IResource source,TResource target){

		if (source != null && target != null) {
			for (TangibleBy r : source.getTangibleElements()) {
				if (r.getTarget() == target) return false;
			}
		}
		return true;
	}

	/**
	 * Method called to check if a Paly relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistAgentPlays(Agent container,Agent source,Role target){

		if (source != null && target != null) {
			for (Play r : source.getPlayedRoles()) {
				if (r.getTarget() == target) return false;
			}
		}
		return true;
	}

	/**
	 * Method called to check if a Authorisation relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistAuthorisation(Actor container,Actor source,Actor target){

		if (source == target) return false;
		return true;
	}

	/**
	 * Method called to check if a Provision relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistProvision(Actor container,Actor source,Actor target){
		if (source == target) return false;
		return true;
	}

	public static boolean canExistProvision(Actor source,Actor target,TResource provided){
		if (source == target) return false;
		for (Provision p : source.getOutgoingProvisions()) {
			if (p.getTarget() == target && p.getSourceResource() == provided) return false;
		}

		/*Provision p = provided.getProvidedFrom();
		while (p != null) {
			if (p.getSource() == target) return false;
			p = p.getPreviousProvision();
		}*/
		return true;
	}

	/**
	 * Method called to check if a Delegation relation can be created or reoriented
	 * 
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 * @deprecated disabled from version 1.0.6
	 */
	public static boolean canExistDelegation(Actor container,Actor source,Actor target){
		if (source == target) return false;
		return true;
	}
	
	public static boolean canExistDelegation(Actor source,Goal delegated){
		return true;
	}

	public static boolean canExistDelegation(Actor source,Actor target,Goal delegated){
		if (delegated.getOutgoingDecompositions().size()>0)return false;
		if (source == target) return false;
		for (Delegation d : source.getOutgoingDelegations()) {
			if (d.getTarget() == target && d.getSourceGoal() == delegated) return false;
		}
		if (evaluateDelegationCicles(target, delegated.getDelegatedFrom())) return false;
		return true;
	}


	private static boolean evaluateDelegationCicles(Actor target,List<Delegation> delegation){

		if (delegation.size() == 0) return false;
		for (Delegation d : delegation) {
			if (d.getSource() == target || evaluateDelegationCicles(target, d.getPreviousDelegation())) return true;
		}
		return false;
	}


	/**
	 * Method called to check if a Threat relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistThreat(Event container,Event source,Threatable target){

		for (Threat t : source.getThreatedElements()) {
			if (t.getTarget() == target) return false;
		}
		return true;
	}

	/**
	 * Method called to check if a Incompatible relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistIncompatibleDuties(SeparationOfDuties container,SeparationOfDuties source,SeparationOfDuties target){

		if (source == null || target == null) return true;
		if (source == target) return false;

		if (source instanceof BindingOfDuties) {
			for (CompatibleDuties i : ((BindingOfDuties) source).getCompatibleDutiesOut()) {
				if (i.getTarget() == target) return false;
			}
		}
		if (target instanceof BindingOfDuties) {
			for (CompatibleDuties i : ((BindingOfDuties) target).getCompatibleDutiesOut()) {
				if (i.getTarget() == source) return false;
			}
		}

		if (source.getClass() != target.getClass()) return false;


		if (source instanceof Goal) {
			Goal s = (Goal) source;
			Goal t = (Goal) target;

			if (isGoalChildOfDecompositionRecursive(s, t) || isGoalChildOfDecompositionRecursive(t, s)) return false;
		}

		for (IncompatibleDuties id : source.getIncompatibleDutiesOut()) {
			if (id.getTarget() == target) return false;
		}
		for (IncompatibleDuties id : source.getIncompatibleDutiesIn()) {
			if (id.getSource() == target) return false;
		}

		return true;
	}

	/**
	 * Method called to check if a Threat relation can be created or reoriented
	 * 
	 * @param container
	 *           the container of the Need relation
	 * @param source
	 *           the source of the relation (can be null)
	 * @param target
	 *           the target of the relation (can be null)
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean canExistCompatibleDuties(BindingOfDuties container,BindingOfDuties source,BindingOfDuties target){
		if (source == null || target == null) return true;
		if (source == target) return false;

		if (source instanceof SeparationOfDuties) {
			for (IncompatibleDuties i : ((SeparationOfDuties) source).getIncompatibleDutiesOut()) {
				if (i.getTarget() == target) return false;
			}
		}
		if (target instanceof SeparationOfDuties) {
			for (IncompatibleDuties i : ((SeparationOfDuties) target).getIncompatibleDutiesOut()) {
				if (i.getTarget() == source) return false;
			}
		}

		if (source.getClass() != target.getClass()) return false;

		if (source instanceof Goal) {
			Goal s = (Goal) source;
			Goal t = (Goal) target;

			if (isGoalChildOfDecompositionRecursive(s, t) || isGoalChildOfDecompositionRecursive(t, s)) return false;
		}

		for (CompatibleDuties id : source.getCompatibleDutiesOut()) {
			if (id.getTarget() == target) return false;
		}
		for (CompatibleDuties id : source.getCompatibleDutiesIn()) {
			if (id.getSource() == target) return false;
		}

		return true;
	}
	
	public static boolean canExistDependency(Role container,Role source,Role target){
		if (source != null && target != null) {
			if(source==target)return false;
			for (Dependency d : source.getDependBy()) {
				if (d.getTarget() == target) return false;
			}
			for (Dependency d : target.getDependBy()) {
				if (d.getTarget() == source) return false;
			}
		}
		return true;
	}


	/**
	 * Evaluate if a decomposition relation can exist without creating loop in the tree
	 * 
	 * @param source
	 *           the source of the relation
	 * @param target
	 *           the target of the relation
	 * 
	 * @return true if the relation can be created
	 */
	public static boolean evaluateGoalCicle(Goal source,Goal target){

		Set initialSet = new HashSet<Goal>();
		initialSet.add(target);
		return !haveDecompositionCicles(source, initialSet); //ew HashSet();
	}

	/**
	 * Recursive method to evaluate loop in a tree of decomposition
	 * 
	 * @param source
	 *           the source of the relation
	 * @param initialSet
	 *           an empty set of goal to initialize the recursive function
	 * 
	 * @return true if the relation can be created
	 */
	private static boolean haveDecompositionCicles(Goal source,Set<Goal> initialSet){

		if (source.getIncomingDecompositions() == null)
			return false;
		else {
			Goal g = source.getIncomingDecompositions().getSource();
			if (initialSet.add(g)) {
				return haveDecompositionCicles(g, initialSet);
			} else {
				return true;
			}
		}
	}


	/**
	 * Evaluate if a Goal is a child of a decomposition
	 * 
	 * @param parent
	 *           the parent goal
	 * @param child
	 *           goal to test if is a child
	 * 
	 * @return true if the testChild goal is a child
	 */
	private static boolean isGoalChildOfDecompositionRecursive(Goal parent,Goal testChild){

		if (parent == testChild)
			return true;
		else {
			for (GoalDecomposition d : parent.getOutgoingDecompositions()) {
				if (isGoalChildOfDecompositionRecursive(d.getTarget(), testChild)) return true;
			}
		}
		return false;
	}

	private static Goal getOriginalDelegatedGoal(Goal delegatedGoal){
		if (delegatedGoal.getDelegatedFrom().size() == 0) { return delegatedGoal; }
		return getOriginalDelegatedGoal(delegatedGoal.getDelegatedFrom().get(0).getSourceGoal());
	}

}
