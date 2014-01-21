/*
* RedundancyAnalysisTasksGroup.java
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

import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_Multi_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_Multi_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_Single_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_Single_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_Redundancy_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.TaskName_Redundancy_Violation;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.getMessage;
import static eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions.REDUNDANCY_MULTI_VIOLATION;
import static eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions.REDUNDANCY_SINGLE_VIOLATION;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.ElementFinder;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.Violation;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;

public class RedundancyAnalysisTasksGroup extends AbstractSecurityTasksGroup {

	public RedundancyAnalysisTasksGroup(String name, int priority) {
		super(name, priority);
	}

	class RedundancyViolationTask extends AbstractSecurityTasks {

		public RedundancyViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String name = getMessage(TaskName_Redundancy_Violation);
		int priority = 12;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram, List<IResult> results) throws Exception {

			List<Violation> violations = executeAnalysis(diagram,/*
																 * REDUNDANCY_VIOLATION
																 * ,
																 */REDUNDANCY_SINGLE_VIOLATION, REDUNDANCY_MULTI_VIOLATION);
			Map<String, StsObject> idMap = ElementFinder.buildAllElementMap(diagram);

			for (Violation v : violations) {
				// violate_*(R1,R2,G)
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());

				Goal tgoal = null;
				// for(Goal gA2:a2.getGoals()){
				// if(getOriginalDelegatedGoal(gA2)==g){
				// tgoal=gA2;
				// }
				// }

				List<EObject> l = new ArrayList<EObject>();
				l.add(a1);
				l.add(a2);

				for (Delegation d : a1.getOutgoingDelegations()) {
					if (getOriginalDelegatedGoal(d.getSourceGoal()) == g && d.getTarget() == a2) {
						l.add(d);
						l.add(d.getTargetGoal());
						tgoal=d.getTargetGoal();
						if (v.p.getName().equals(REDUNDANCY_SINGLE_VIOLATION.getFilterName())) {
							List<Goal> goals = new ArrayList<Goal>();
							getSubGoals(d.getTargetGoal(), goals);
							for (Goal subgoal : goals) {
								for (Delegation del : subgoal.getDelegatedTo()) {
									l.add(del);
								}
							}
						}
					}
				}

				if (tgoal != null) {

					if (tgoal.getOutgoingDecompositions().size() == 0) {
						results.add(new SecurityResult(v, getMessage(Result_Redundancy_text, a2.getName(), a1.getName(), g.getName()),
								getMessage(Result_Redundancy_desc, a2.getName(), a1.getName(), g.getName()), l, getResultForError(),
								SOCIAL_VIEW));
					} else {

						if (v.p.getName().equals(REDUNDANCY_SINGLE_VIOLATION.getFilterName())) {
							results.add(new SecurityResult(v, getMessage(Result_Redundancy_Single_text, a2.getName(), a1.getName(),
									g.getName()), getMessage(Result_Redundancy_Single_desc, a2.getName(), a1.getName(), g.getName()), l,
									getResultForError(), SOCIAL_VIEW));

						} else if (v.p.getName().equals(REDUNDANCY_MULTI_VIOLATION.getFilterName())) {
							results.add(new SecurityResult(v, getMessage(Result_Redundancy_Multi_text, a2.getName(), a1.getName(),
									g.getName()), getMessage(Result_Redundancy_Multi_desc, a2.getName(), a1.getName(), g.getName()), l,
									getResultForError(), SOCIAL_VIEW));
						}

					}
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private void getSubGoals(Goal g, List<Goal> goals) {
			goals.add(g);
			if (g.getOutgoingDecompositions().size() > 0) {
				for (GoalDecomposition gd : g.getOutgoingDecompositions()) {
					if (gd != null && gd.getTarget() != null) {
						getSubGoals(gd.getTarget(), goals);
					}
				}
			}
		}
	}
}
