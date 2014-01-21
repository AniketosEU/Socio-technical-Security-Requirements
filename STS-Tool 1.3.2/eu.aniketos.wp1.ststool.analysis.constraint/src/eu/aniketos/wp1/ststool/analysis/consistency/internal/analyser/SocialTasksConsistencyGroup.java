/*
* SocialTasksConsistencyGroup.java
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
package eu.aniketos.wp1.ststool.analysis.consistency.internal.analyser;

import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.*;

import java.util.*;

import org.eclipse.emf.ecore.*;

import eu.aniketos.wp1.ststool.*;
import eu.aniketos.wp1.ststool.analysis.results.*;
import eu.aniketos.wp1.ststool.analysis.util.analyser.*;


public class SocialTasksConsistencyGroup extends AbstractConsistencyTasksGroup {

	public SocialTasksConsistencyGroup(String name, int priority) {
		super(name, priority);
	}

	class EmptyDiagramTask extends AbstractConsistencyTasks {

		public EmptyDiagramTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String		name			= getMessage(TaskName_EmptyDiagram);
		int			priority		= 10;
		BlockType	blockType	= BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			if (diagram.getDiagActors().size() == 0) {
				results.add(new ConsistencySocialResult(getMessage(Result_NoActors_text), getMessage(Result_NoActors_desc), getResultForError()));
				return getErrorResult(true);
			}
			return getErrorResult(false);
		}
	}

	class GoalSingleDecompositionTask extends AbstractConsistencyTasks {

		public GoalSingleDecompositionTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_GoalSingleDecomposition);
		int		priority	= 20;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Actor a : diagram.getDiagActors()) {
				for (Goal g : a.getGoals()) {
					if (g.getOutgoingDecompositions().size() == 1) {
						List<EObject> l = new ArrayList<EObject>();
						l.add(g);
						l.add(g.getOutgoingDecompositions().get(0));
						results.add(new ConsistencySocialResult(getMessage(Result_SingleDecomposition_text, g.getName(), a.getName()), getMessage(Result_SingleDecomposition_desc, g.getName(), a.getName()), l, getResultForError())); //$NON-NLS-2$
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

//	class GoalLeafDelegationTask extends AbstractConsistencyTasks {
//
//		public GoalLeafDelegationTask(ITasksGroup group) {
//			super(group, RESULT_ERROR);
//		}
//
//		String	name		= getMessage(TaskName_GoalLeafDelegation);
//		int		priority	= 30;
//
//
//		@Override
//		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
//			for (Actor a : diagram.getDiagActors()) {
//				for (Goal g : a.getGoals()) {
//					if (g.getOutgoingDecompositions().size() > 0 && g.getDelegatedTo().size() > 0) {
//						List<EObject> l = new ArrayList<EObject>();
//						l.add(g);
//						for (Delegation d : g.getDelegatedTo()) {
//							l.add(d);
//						}
//						ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_DecomposedGoalDelegated_text, g.getName()), getMessage(Result_DecomposedGoalDelegated_desc), (List) l, getResultForError()); //$NON-NLS-2$
//						results.add(cr);
//					}
//				}
//			}
//			return getErrorResult(results.size() != 0);
//		}
//	}

//	class GoalLeafCapabilityTask extends AbstractConsistencyTasks {
//
//		public GoalLeafCapabilityTask(ITasksGroup group) {
//			super(group, RESULT_WARNING);
//		}
//
//		String	name		= getMessage(TaskName_GoalLeafCapability);
//		int		priority	= 31;
//
//		@Override
//		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
//			for (Actor a : diagram.getDiagActors()) {
//				for (Goal g : a.getGoals()) {
//					if (g.getOutgoingDecompositions().size() > 0 && g.isCapability()) {
//						ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_DecomposedGoalCapability_text, g.getName()), getMessage(Result_DecomposedGoalCapability_desc), g, getResultForError()); //$NON-NLS-2$
//						results.add(cr);
//					}
//				}
//			}
//			return getErrorResult(results.size() != 0);
//		}
//	}

	class DelegationChildCyclesTask extends AbstractConsistencyTasks {

		public DelegationChildCyclesTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_DelegationChildCycles);
		int		priority	= 40;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			List<Delegation> invalidDelegation = new ArrayList<Delegation>();
			for (Actor a : diagram.getDiagActors()) {
				List<Goal> rootGoals = getRootGoalForActor(a);
				for (Goal g : rootGoals) {
					chekGoalLeafDelegationCyclesRec(g.getActorOwner(), g, invalidDelegation, new ArrayList<Goal>());
				}
			}
			for (Delegation d : invalidDelegation) {
				List<EObject> l = new ArrayList<EObject>();
				l.add(d);
				l.add(d.getSourceGoal());
				l.add(d.getTargetGoal());
				String[] params = new String[] { d.getSourceGoal().getName(),
						d.getSourceGoal().getIncomingDecompositions().getSource().getName(), d.getTarget().getName() };
				ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_DlegationLoop_text), getMessage(Result_DlegationLoop_desc, params), l, getResultForError());
				results.add(cr);
			}
			return getErrorResult(results.size() != 0);
		}

		private List<Goal> getRootGoalForActor(Actor a){
			List<Goal> result = new ArrayList<Goal>();
			for (Goal g : a.getGoals()) {
				if (g.getIncomingDecompositions() == null) result.add(g);
			}
			return result;
		}

		private void chekGoalLeafDelegationCyclesRec(final Actor startActor,Goal goal,List<Delegation> invalidDelegation,List<Goal> vistedGoal){
			if (vistedGoal.contains(goal)) return;
			vistedGoal.add(goal);

			for (Delegation d : goal.getDelegatedTo()) {
				if (d.getTargetGoal().getActorOwner() == startActor) {
					invalidDelegation.add(d);
				} else {
					chekGoalLeafDelegationCyclesRec(startActor, d.getTargetGoal(), invalidDelegation, vistedGoal);
				}
			}
			if (goal.getOutgoingDecompositions().size() > 0) {
				for (GoalDecomposition gd : goal.getOutgoingDecompositions()) {
					if (gd.getTarget() != null) chekGoalLeafDelegationCyclesRec(startActor, gd.getTarget(), invalidDelegation, vistedGoal);
				}
			}
		}
	}

//	class DelegatedGoalPartOfDecompositionTask extends AbstractConsistencyTasks {
//
//		public DelegatedGoalPartOfDecompositionTask(ITasksGroup group) {
//			super(group, RESULT_WARNING);
//		}
//
//		String	name		= getMessage(TaskName_DelegatedGoalPartOfDecomposition);
//		int		priority	= 50;
//
//		@Override
//		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
//			for (Actor a : diagram.getDiagActors()) {
//				for (Goal g : a.getGoals()) {
//					if (g.getDelegatedFrom().size() > 0 && g.getIncomingDecompositions() != null) {
//						List<EObject> l = new ArrayList<EObject>();
//						l.add(g);
//						l.add(g.getIncomingDecompositions());
//						String[] params = new String[] { a.getName(), g.getName(),
//								g.getIncomingDecompositions().getSource().getName() };
//						ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_DlegatedGoalPartOfDecomposition_text, g.getName()), getMessage(Result_DlegatedGoalPartOfDecomposition_desc, params), l, getResultForError()); //$NON-NLS-2$
//						results.add(cr);
//					}
//				}
//			}
//			return getErrorResult(results.size() != 0);
//		}
//	}

	class ContributionsCycleTask extends AbstractConsistencyTasks {

		public ContributionsCycleTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_ContributionsCycle);
		int		priority	= 60;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			Set<Set<GoalContribution>> result = new HashSet<Set<GoalContribution>>();
			for (Goal g : diagram.getAllGoals()) {
				result.addAll(checkGoalContibutionCycleRec(g, g, new HashSet<GoalContribution>()));
			}
			if (result.size() > 0) {
				for (Set<GoalContribution> path : result) {
					List<EObject> l = new ArrayList<EObject>();
					l.addAll(path);
					ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_ContributionLoop_text), getMessage(Result_ContributionLoop_desc), l, getResultForError());
					results.add(cr);
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private Set<Set<GoalContribution>> checkGoalContibutionCycleRec(final Goal startGoal,Goal g,final Set<GoalContribution> contributionPath){
			Set<Set<GoalContribution>> result = new HashSet<Set<GoalContribution>>();

			for (GoalContribution gc : g.getOutgoingContributions()) {
				if (contributionPath.contains(gc)) return result;
				Set<GoalContribution> contributionPathUpdated = new HashSet<GoalContribution>(contributionPath);
				contributionPathUpdated.add(gc);
				if (gc.getTarget() == startGoal) {
					boolean error = false;
					Iterator<GoalContribution> it = contributionPathUpdated.iterator();
					while (it.hasNext() && !error) {
						if (it.next() instanceof NegativeGoalContribution) {
							error = true;
						}
					}
					if (error) {
						result.add(contributionPathUpdated);
					}
				} else {
					result.addAll(checkGoalContibutionCycleRec(startGoal, gc.getTarget(), contributionPathUpdated));
				}
			}
			return result;
		}
	}

	class NegativeContributionsBetweenAndTask extends AbstractConsistencyTasks {

		public NegativeContributionsBetweenAndTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_NegativeContributionsBetweenAnd);
		int		priority	= 70;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Goal g : diagram.getAllGoals()) {
				Set<Goal> sourceParents = getAndParentGoal(g);
				for (GoalContribution gc : g.getOutgoingContributions()) {
					if (gc instanceof NegativeGoalContribution && g.getActorOwner() == gc.getTarget().getActorOwner()) {
						Set<Goal> targetParents = getAndParentGoal(gc.getTarget());
						targetParents.retainAll(sourceParents);
						if (targetParents.size() > 0) {
							String[] params = new String[] { g.getName(), gc.getTarget().getName(),
									targetParents.iterator().next().getName(), g.getActorOwner().getName() };
							ConsistencyResult cr = new ConsistencySocialResult(getMessage(Result_NegativeContributionBetweenAND_text), getMessage(Result_NegativeContributionBetweenAND_desc, params), gc, getResultForError());
							results.add(cr);
						}
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private Set<Goal> getAndParentGoal(Goal g){
			Set<Goal> result = new HashSet<Goal>();
			GoalDecomposition gd = g.getIncomingDecompositions();
			if (gd != null && gd instanceof GoalDecompositionAND) {
				result.add(gd.getSource());
				result.addAll(getAndParentGoal(gd.getSource()));
			}
			return result;
		}
	}

	


	/* Example!
	class Task extends AbstractTask{
		public Task(ITasksGroup group) {super(group);}
		
		//String name="";
		//int priority=1;
		//int minTime=100;
		//int timeOut=1000;
		//BlockType blockType=BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			return NOT_IMPLEMENTED;
		}
	}
	*/


	protected static class ConsistencySocialResult extends ConsistencyResult {

		public ConsistencySocialResult(String text, String description, ResultType restype) {
			super(text, description, restype, SOCIAL_VIEW);
		}

		public ConsistencySocialResult(String text, String description, EObject object, ResultType restype) {
			super(text, description, object, restype, SOCIAL_VIEW);
		}

		public ConsistencySocialResult(String text, String description, List<EObject> objects, ResultType restype) {
			super(text, description, objects, restype, SOCIAL_VIEW);
		}

	}
}
