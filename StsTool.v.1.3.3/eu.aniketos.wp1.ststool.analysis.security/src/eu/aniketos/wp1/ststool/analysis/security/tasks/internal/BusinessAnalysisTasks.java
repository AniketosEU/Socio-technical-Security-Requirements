/*
* BusinessAnalysisTasks.java
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
package eu.aniketos.wp1.ststool.analysis.security.tasks.internal;

import static eu.aniketos.wp1.ststool.analysis.security.Messages.*;
import static eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions.*;

import java.util.*;

import org.eclipse.emf.ecore.*;

import eu.aniketos.wp1.ststool.*;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.*;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.Violation;
import eu.aniketos.wp1.ststool.analysis.results.*;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.*;
import eu.aniketos.wp1.ststool.analysis.util.analyser.*;

public class BusinessAnalysisTasks extends AbstractSecurityTasksGroup {

	public BusinessAnalysisTasks(String name, int priority) {
		super(name, priority);
	}
	
	private List<Violation> violations;
	private Map<String, StsObject> idMap;
	
	
	
	class BuisinessCheckTask extends AbstractSecurityTasks{

		public BuisinessCheckTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Business_PreAnalysis);
		int		priority	= 119;
		BlockType blockType = BlockType.CLASS;
		
		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			try {
				violations = executeAnalysis(diagram,SOD_GOAL_VIOLATION,COD_GOAL_VIOLATION);
				idMap = ElementFinder.buildAllElementMap(diagram);
			} catch (Exception e) {
				e.printStackTrace();
				return TaskResult.COMPLETED_ERROR;
			}
			return TaskResult.COMPLETED_OK;
		}
	}

	class AgentPlaySodTask extends AbstractSecurityTasks {

		public AgentPlaySodTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_AgentPlaySod);
		int		priority	= 120;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Actor a : diagram.getDiagActors()) {
				if (a instanceof Role && ((Role) a).getIncompatibleDutiesOut().size() > 0 && ((Role) a).getPlayedBy().size() > 0) {
					Role r = (Role) a;
					List<Agent> playedBy = new ArrayList<Agent>();
					for (Play p : r.getPlayedBy()) {
						playedBy.add(p.getSource());
					}
					for (IncompatibleDuties id : ((Role) a).getIncompatibleDutiesOut()) {
						List<Agent> targetPlayedBy = new ArrayList<Agent>();
						for (Play p : ((Role) id.getTarget()).getPlayedBy()) {
							targetPlayedBy.add(p.getSource());
						}
						targetPlayedBy.retainAll(playedBy);
						for (Agent ag : targetPlayedBy) {
							List<EObject> l = new ArrayList<EObject>();
							l.add(ag);
							l.add(r);
							l.add(id.getTarget());
							Object[] params = new String[] { ag.getName(), r.getName(), ((Role) id.getTarget()).getName() };
							results.add(new SecurityResult(getMessage(Result_AgentPlaySod_text, params), getMessage(Result_AgentPlaySod_desc, params), l, getResultForError())); //$NON-NLS-2$
						}
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class AgentNotPlayBodTask extends AbstractSecurityTasks {

		public AgentNotPlayBodTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_AgentNotPlayBod);
		int		priority	= 121;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Actor a : diagram.getDiagActors()) {
				if (a instanceof Agent && ((Agent) a).getPlayedRoles().size() > 0) {
					List<Role> playedRoles = new ArrayList<Role>();
					for (Play p : ((Agent) a).getPlayedRoles()) {
						playedRoles.add(p.getTarget());
					}
					for (Role r : playedRoles) {
						Map<Role, CompatibleDuties> notPlayedRoles = new HashMap<Role, CompatibleDuties>();
						for (CompatibleDuties cd : r.getCompatibleDutiesOut()) {
							if (!playedRoles.contains(cd.getTarget())) {
								notPlayedRoles.put((Role) cd.getTarget(), cd);
							}
						}
						for (CompatibleDuties cd : r.getCompatibleDutiesIn()) {
							if (!playedRoles.contains(cd.getSource())) {
								notPlayedRoles.put((Role) cd.getSource(), cd);
							}
						}
						for (Role npr : notPlayedRoles.keySet()) {
							List<EObject> l = new ArrayList<EObject>();
							l.add(a);
							l.add(r);
							l.add(npr);
							for (Play p : r.getPlayedBy()) {
								if (p.getSource() == a) l.add(p);
							}
							l.add(notPlayedRoles.get(npr));
							Object[] params = new String[] { a.getName(), r.getName(), npr.getName() };
							results.add(new SecurityResult(getMessage(Result_AgentNotPlayBod_text, params), getMessage(Result_AgentNotPlayBod_desc, params), l, getResultForError())); //$NON-NLS-2$
						}
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}
	
	class OrganizationalConstraintConsistencyTask extends AbstractSecurityTasks {

		public OrganizationalConstraintConsistencyTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_OrganizationalConstraintConsistency);
		int		priority	= 122;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Actor a : diagram.getDiagActors()) {
				for (Goal g : a.getGoals()) {
					for (IncompatibleDuties id : g.getIncompatibleDutiesOut()) {
						if (isG1childofG2(g, (Goal) id.getTarget()) || isG1childofG2((Goal) id.getTarget(), g)) {
							List<EObject> l = new ArrayList<EObject>();
							l.add(g);
							l.add(id.getTarget());
							l.add(id);
							SecurityResult cr = new SecurityResult(getMessage(Result_OrganizationalConsSodTreeGoal_text, g.getName(), ((Goal) id.getTarget()).getName()), getMessage(Result_OrganizationalConsSodTreeGoal_desc), l, getResultForError());
							results.add(cr);
						}
					}
					for (CompatibleDuties cd : g.getCompatibleDutiesOut()) {
						if (isG1childofG2(g, (Goal) cd.getTarget()) || isG1childofG2((Goal) cd.getTarget(), g)) {
							List<EObject> l = new ArrayList<EObject>();
							l.add(g);
							l.add(cd.getTarget());
							l.add(cd);
							SecurityResult cr = new SecurityResult(getMessage(Result_OrganizationalConsBodTreeGoal_text, g.getName(), ((Goal) cd.getTarget()).getName()), getMessage(Result_OrganizationalConsBodTreeGoal_desc), l, getResultForError());
							results.add(cr);
						}
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private boolean isG1childofG2(Goal g1,Goal g2){
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
	}
	
	class SodGoalTask extends AbstractSecurityTasks {

		public SodGoalTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Sod_Goal_Violation);
		int		priority	= 123;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(SOD_GOAL_VIOLATION);
			for (Violation v : violations) {
				//violate_sod_goal(R,R,G1,R,G2)
				//violate_sod_goal(A,R1,G1,R2,G2)
				//violate_sod_goal(A,A,G1,R,G2)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor fp1 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				Goal g1 = (Goal) idMap.get(v.p.getParameterAt(2).getName());
				Actor fp2 = (Actor) idMap.get(v.p.getParameterAt(3).getName());
				Goal g2 = (Goal) idMap.get(v.p.getParameterAt(4).getName());
				List<EObject> l = new ArrayList<EObject>();

				if ((a1 == fp1) && (a1 == fp2)) {
					l.add(a1);
					IncompatibleDuties id = searchSodGoals(a1, g1, a1, g2);
					if (id != null) {
						l.add(id.getSource());
						l.add(id);
						l.add(id.getTarget());
					}
				} else {
					if ((a1 != fp1) && (a1 != fp2)) {
						l.add(a1);
					}
					if (a1 instanceof Agent) {
						for (Play p : ((Agent) a1).getPlayedRoles()) {
							if (p.getTarget() == fp1 || p.getTarget() == fp2) l.add(p);
						}
					}
					l.add(fp1);
					l.add(fp2);
					IncompatibleDuties id = searchSodGoals(fp1, g1, fp2, g2);
					if (id != null) {
						l.add(id.getSource());
						l.add(id);
						l.add(id.getTarget());
					} else {
						for (Goal g : fp1.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g1) l.add(g);
						}
						for (Goal g : fp2.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g2) l.add(g);
						}
					}
				}



				results.add(new SecurityResult(v, getMessage(Result_Sod_Goal_Violation_text, v.percent(), g1.getName(), g2.getName()), getMessage(Result_Sod_Goal_Violation_desc, g1.getName(), g2.getName(), a1.getName()), l, getResultForError(), SOCIAL_VIEW));
			}
			return getErrorResult(results.size() != 0);
		}
		
		/**
		 * Search an {@link IncompatibleDuties} between 2 goals that are delegated (or are the original goals) specified as parameter, and are contained in 2 specific actors
		 * 
		 * @param a1
		 *           the actor that must contain a goal that represent {@link g1}
		 * @param g1
		 * @param a2
		 *           the actor that must contain a goal that represent {@link g2}
		 * @param g2
		 * @return the {@link IncompatibleDuties} between the 2 goals if exists, or <code>null</code>
		 */
		private IncompatibleDuties searchSodGoals(Actor a1,Goal g1,Actor a2,Goal g2){
			IncompatibleDuties id = null;
			List<Goal> gl = a1.getGoals();
			for (int i = 0; i < gl.size() && id == null; i++) {
				if (getOriginalDelegatedGoal(gl.get(i)) == g1) {
					List<IncompatibleDuties> idList = gl.get(i).getIncompatibleDutiesOut();
					for (int k = 0; k < idList.size() && id == null; k++) {
						Goal tar = (Goal) idList.get(k).getTarget();
						if (getOriginalDelegatedGoal(tar) == g2 && (tar.getActorOwner() == a2)) {
							id = idList.get(k);
						}
					}
					idList = gl.get(i).getIncompatibleDutiesIn();
					for (int k = 0; k < idList.size() && id == null; k++) {
						Goal src = (Goal) idList.get(k).getTarget();
						if (getOriginalDelegatedGoal(src) == g2 && (src.getActorOwner() == a2)) {
							id = idList.get(k);
						}
					}
				}
			}
			return id;
		}

	}

	class CodGoalTask extends AbstractSecurityTasks {

		public CodGoalTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Cod_Goal_Violation);
		int		priority	= 130;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(COD_GOAL_VIOLATION);
			for (Violation v : violations) {
				//violate_cod_goal(A,R1,G1,R2,G2)
				//violate_cod_goal(A,A,G1,R,G2)
				//violate_cod_goal(R1,R1,G1,R2,G2)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor fp1 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				Goal g1 = (Goal) idMap.get(v.p.getParameterAt(2).getName());
				Actor fp2 = (Actor) idMap.get(v.p.getParameterAt(3).getName());
				Goal g2 = (Goal) idMap.get(v.p.getParameterAt(4).getName());
				List<EObject> l = new ArrayList<EObject>();

				if (a1 instanceof Role) {//no agent plays the 2 roles
					l.add(fp1);
					l.add(fp2);
					CompatibleDuties cd = searchCodGoals(fp1, g1, fp2, g2);
					if (cd != null) {
						l.add(cd);
						l.add(cd.getSource());
						l.add(cd.getTarget());
					} else {
						for (Goal g : fp1.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g1) l.add(g);
						}
						for (Goal g : fp2.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g2) l.add(g);
						}
					}
					results.add(new SecurityResult(v, getMessage(Result_Cod_Goal_Violation_NoAgent_text, v.percent()), getMessage(Result_Cod_Goal_Violation_NoAgent_desc, g1.getName(), g2.getName()), l, ResultType.WARNING, SOCIAL_VIEW));
				} else {
					l.add(fp1);
					l.add(fp2);
					String goalAchieved = "?";
					String goalNotAchieved = "?";
					if ((a1 != fp1) && (a1 != fp2)) {
						l.add(a1);
						for (Play p : ((Agent) a1).getPlayedRoles()) {
							if (p.getTarget() == fp1 || p.getTarget() == fp2) {
								l.add(p);
								if (p.getTarget() == fp1) {
									goalAchieved = g1.getName();
									goalNotAchieved = g2.getName();
								} else {
									goalAchieved = g2.getName();
									goalNotAchieved = g1.getName();
								}
							}
						}
					} else {
						if (a1 == fp1) {
							goalAchieved = g1.getName();
							goalNotAchieved = g2.getName();
						} else {
							goalAchieved = g2.getName();
							goalNotAchieved = g1.getName();
						}
					}
					CompatibleDuties cd = searchCodGoals(fp1, g1, fp2, g2);
					if (cd != null) {
						l.add(cd);
						l.add(cd.getSource());
						l.add(cd.getTarget());
					} else {
						for (Goal g : fp1.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g1) l.add(g);
						}
						for (Goal g : fp2.getGoals()) {
							if (getOriginalDelegatedGoal(g) == g2) l.add(g);
						}
					}
					results.add(new SecurityResult(v, getMessage(Result_Cod_Goal_Violation_text, v.percent(), g1.getName(), g2.getName()), getMessage(Result_Cod_Goal_Violation_desc, g1.getName(), g2.getName(), a1.getName(), goalAchieved, goalNotAchieved), l, getResultForError(), SOCIAL_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}

		/**
		 * Search an {@link IncompatibleDuties} between 2 goals that are delegated (or are the original goals) specified as parameter, and are contained in 2 specific actors
		 * 
		 * @param a1
		 *           the actor that must contain a goal that represent {@link g1}
		 * @param g1
		 * @param a2
		 *           the actor that must contain a goal that represent {@link g2}
		 * @param g2
		 * @return the {@link IncompatibleDuties} between the 2 goals if exists, or <code>null</code>
		 */
		private CompatibleDuties searchCodGoals(Actor a1,Goal g1,Actor a2,Goal g2){
			CompatibleDuties cd = null;
			List<Goal> gl = a1.getGoals();
			for (int i = 0; i < gl.size() && cd == null; i++) {
				if (getOriginalDelegatedGoal(gl.get(i)) == g1) {
					List<CompatibleDuties> cdList = gl.get(i).getCompatibleDutiesOut();
					for (int k = 0; k < cdList.size() && cd == null; k++) {
						Goal tar = (Goal) cdList.get(k).getTarget();
						if (getOriginalDelegatedGoal(tar) == g2 && (tar.getActorOwner() == a2)) {
							cd = cdList.get(k);
						}
					}
					cdList = gl.get(i).getCompatibleDutiesIn();
					for (int k = 0; k < cdList.size() && cd == null; k++) {
						Goal src = (Goal) cdList.get(k).getTarget();
						if (getOriginalDelegatedGoal(src) == g2 && (src.getActorOwner() == a2)) {
							cd = cdList.get(k);
						}
					}
				}
			}
			return cd;
		}
	}

	
	private List<Violation> getFilteredViolation(IViolationDefinition violation){
		List<Violation> filtredViolations=new ArrayList<Violation>();
		for(Violation v:violations){
			if(v.p.getName().equals(violation.getFilterName())){
				filtredViolations.add(v);
			}
		}
		return filtredViolations;
	}
}
