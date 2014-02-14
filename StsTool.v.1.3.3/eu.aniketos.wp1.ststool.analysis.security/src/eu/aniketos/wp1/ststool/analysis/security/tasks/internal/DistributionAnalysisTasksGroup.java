/*
* DistributionAnalysisTasksGroup.java
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

import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NoDelegation_Violation_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NoDelegation_Violation_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NonDisclosure_Violation_NoAuth_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NonDisclosure_Violation_NoAuth_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NonDisclosure_Violation_desc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NonDisclosure_Violation_text;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.TaskName_NoDelegation_Violation;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.TaskName_NonDisclosure_Violation;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.getMessage;
import static eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions.NON_DISCLOSURE_VIOLATION;
import static eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions.NO_DELEGATION_VIOLATION;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.ElementFinder;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.Violation;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;

public class DistributionAnalysisTasksGroup extends AbstractSecurityTasksGroup {

	public DistributionAnalysisTasksGroup(String name, int priority) {
		super(name, priority);
	}

	class NoDelegatonViolationTask extends AbstractSecurityTasks {

		public NoDelegatonViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NoDelegation_Violation);
		int		priority	= 10;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = executeAnalysis(diagram,NO_DELEGATION_VIOLATION);
			Map<String, StsObject> idMap= ElementFinder.buildAllElementMap(diagram);
			
			for (Violation v : violations) {
				//violate_no_delegation(R2,R1,G,Gi)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());
				Goal subg = (Goal) idMap.get(v.p.getParameterAt(3).getName());

				List<EObject> l = new ArrayList<EObject>();
				//l.add(a1);
				l.add(a2);

				Set<String> redelegatedActorsSet = new HashSet<String>();

				for (Delegation d : a1.getOutgoingDelegations()) {
					if (getOriginalDelegatedGoal(d.getSourceGoal()) == g) {
						//l.add(d);
					}
				}
				if (subg != g) {
					l.add(subg);
					for (Delegation d : subg.getDelegatedTo()) {
						l.add(d);
						l.add(d.getTargetGoal());
						redelegatedActorsSet.add(d.getTarget().getName());
						if (!l.contains(d.getTarget())) l.add(d.getTarget());
					}
				} else {
					for (Goal goalA2 : a2.getGoals()) {
						if (getOriginalDelegatedGoal(goalA2) == g) {
							l.add(goalA2);
							for (Delegation d : goalA2.getDelegatedTo()) {
								l.add(d);
								l.add(d.getTargetGoal());
								redelegatedActorsSet.add(d.getTarget().getName());
								if (!l.contains(d.getTarget())) l.add(d.getTarget());
							}
						}
					}
				}
				List<String> redelegatedActorsList = new ArrayList<String>(redelegatedActorsSet);


				results.add(new SecurityResult(v, getMessage(Result_NoDelegation_Violation_text, v.percent(), a2.getName(), subg.getName()), getMessage(Result_NoDelegation_Violation_desc, a1.getName(), g.getName(), a2.getName(), subg.getName(), formatStrings(redelegatedActorsList)), l, getResultForError(), SOCIAL_VIEW));
			}
			return getErrorResult(results.size() != 0);
		}


	}
}
