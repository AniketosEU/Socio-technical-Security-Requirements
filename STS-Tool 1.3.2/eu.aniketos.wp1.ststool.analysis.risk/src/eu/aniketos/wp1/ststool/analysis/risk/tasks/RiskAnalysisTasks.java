/*
* RiskAnalysisTasks.java
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
package eu.aniketos.wp1.ststool.analysis.risk.tasks;

import static eu.aniketos.wp1.ststool.analysis.risk.Messages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.GoalDecompositionAND;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.Threat;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.analysis.risk.Messages;
import eu.aniketos.wp1.ststool.analysis.risk.tasks.internal.AbstractRiskTasks;
import eu.aniketos.wp1.ststool.analysis.risk.tasks.internal.AbstractRiskTasksGroup;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;

public class RiskAnalysisTasks extends AbstractRiskTasksGroup {

	public RiskAnalysisTasks(int priority) {
		super(Messages.getMessage(Messages.RiskCategoryName), priority);
	}

	private List<Event> events;;

	public RiskAnalysisTasks(String name, int priority) {
		this(name, priority, null);
	}

	public RiskAnalysisTasks(String name, int priority, Event event) {
		super(name, priority);
		if (event != null) {
			this.events = new ArrayList<Event>();
			events.add(event);
		}
	}

	class ThreathAnlysisTask extends AbstractRiskTasks {

		public ThreathAnlysisTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String name = getMessage(TaskName_ThreatAnalysis);
		int priority = 20;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram, List<IResult> results) {
			if (events == null) {
				events = diagram.getDiagEvents();
			}
			for (Event e : events) {
				List<EObject> influencedObjects = analyseEvent(e);
				if (influencedObjects.size() == 0) {
					results.add(new RiskResult(getMessage(Result_Event_NoConsequence_Text, e.getName()), getMessage(Result_Event_NoConsequence_Desc,
							e.getName()), ResultType.OK));
				} else {

					List<StsElement> obj1 = new ArrayList<StsElement>();
					for (Threat t : e.getThreatedElements()) {
						if(t.getTarget() instanceof StsElement)
						obj1.add(((StsElement) t.getTarget()));
					}

					List<String> obj2 = new ArrayList<String>();
					for (StsElement obj : obj1) {
						obj2.add(obj.getName());

					}

					List<EObject> obj3 = new ArrayList<EObject>(influencedObjects);
					obj3.removeAll(obj1);
					List<String> obj4 = new ArrayList<String>();
					for (EObject obj : obj3) {
						if (obj instanceof StsElement)
							obj4.add(((StsElement) obj).getName());
					}
					if (obj3.size() > 0) {
						results.add(new RiskResult(getMessage(Result_Event_Consequence_Text, e.getName()), getMessage(Result_Event_Consequence_Desc,
								e.getName(), formatStrings(obj2), formatStrings(obj4)), influencedObjects, getResultForError()));
					} else {
						results.add(new RiskResult(getMessage(Result_Event_Consequence_Text, e.getName()), getMessage(
								Result_Event_Consequence_No_Prop_Desc, e.getName(), formatStrings(obj2)), influencedObjects, getResultForError()));
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private List<EObject> analyseEvent(Event e) {
			Set<EObject> threatenObject = new HashSet<EObject>(initThreatenObject(e));
			Set<EObject> analysedObject = new HashSet<EObject>();

			Set<EObject> nonAnalysedObject;
			while ((nonAnalysedObject = getNonAnalyzedObject(threatenObject, analysedObject)).size() > 0) {
				for (EObject obj : nonAnalysedObject) {
					analysedObject.add(obj);
					if (obj instanceof Goal) {
						Goal g = (Goal) obj;
						if (g.getIncomingDecompositions() instanceof GoalDecompositionAND && g.getIncomingDecompositions().getSource() != null) {
							threatenObject.add(g.getIncomingDecompositions());
							threatenObject.add(g.getIncomingDecompositions().getSource());
						}
						for (Delegation d : g.getDelegatedFrom()) {
							if (d.getSource() != null) {
								threatenObject.add(d.getSourceGoal());
								threatenObject.add(d);
							}
						}
						for (Produce p : g.getResourcesProduced()) {
							if (p.getTarget() != null) {
								threatenObject.add(p);
								threatenObject.add(p.getTarget());
							}
						}
					} else if (obj instanceof TResource) {
						TResource r = (TResource) obj;
						List<TResource> resources = new ArrayList<TResource>();
						getDocuemntsParents((TResource) obj, resources);

						for (TResource g : resources) {
							threatenObject.add(g);
						}
						for (Modify m : r.getGoalsModifing()) {
							if (m.getSource() != null) {
								threatenObject.add(m);
								threatenObject.add(m.getSource());
							}
						}
						for (Need n : r.getGoalsNeeding()) {
							if (n.getSource() != null) {
								threatenObject.add(n);
								threatenObject.add(n.getSource());
							}
						}
						for (Provision p : r.getProvidedTo()) {
							if (p.getTarget() != null) {
								threatenObject.add(p.getTargetResource());
								threatenObject.add(p);
							}
						}
					}
				}
			}

			/*
			 * for(Threat t:e.getThreatedElements()){ EObject obj=t.getTarget();
			 * if(obj instanceof Goal){ List<Goal> goals=new ArrayList<Goal>();
			 * getLeafGoals((Goal)obj,goals); for(Goal g:goals){
			 * threatenObject.add(g); } }else if (obj instanceof Resource){
			 * threatenObject.add((Resource)obj); List<Resource> resources=new
			 * ArrayList<Resource>();
			 * getDocuemntsParents((Resource)obj,resources);
			 * getDocuemntsChildren((Resource)obj,resources); for(Resource
			 * g:resources){ threatenObject.add(g); }
			 * 
			 * } }
			 */

			return new ArrayList<EObject>(threatenObject);
		}

		private Set<EObject> getNonAnalyzedObject(Set<EObject> objects, Set<EObject> anlaysed) {
			Set<EObject> result = new HashSet<EObject>(objects);
			result.removeAll(anlaysed);
			return result;
		}

		private Set<EObject> initThreatenObject(Event e) {
			Set<EObject> threatenObject = new HashSet<EObject>();
			for (Threat t : e.getThreatedElements()) {
				EObject obj = t.getTarget();
				if (obj instanceof Goal) {
					threatenObject.add(t);
					List<Goal> goals = new ArrayList<Goal>();
					getLeafGoals((Goal) obj, goals);
					for (Goal g : goals) {
						threatenObject.add(g);
					}
				} else if (obj instanceof TResource) {
					threatenObject.add(t);
					threatenObject.add((TResource) obj);
					List<TResource> resources = new ArrayList<TResource>();
					getDocuemntsChildren((TResource) obj, resources);
					for (TResource r : resources) {
						threatenObject.add(r);
					}
				}
			}
			return threatenObject;
		}

		private void getLeafGoals(Goal g, final List<Goal> goalList) {
			if (g == null)
				return;
			if (g.getOutgoingDecompositions().size() > 0) {
				for (GoalDecomposition gd : g.getOutgoingDecompositions()) {
					getLeafGoals(gd.getTarget(), goalList);
				}
			} else {
				goalList.add(g);
			}
		}

		private void getDocuemntsParents(TResource r, final List<TResource> goalList) {
			if (r == null)
				return;
			if (r.getPartsOf().size() > 0) {
				for (PartOf pf : r.getPartsOf()) {
					if (pf.getTarget() instanceof TResource) {
						goalList.add((TResource) pf.getTarget());
						getDocuemntsParents((TResource) pf.getTarget(), goalList);
					}
				}
			}
		}

		private void getDocuemntsChildren(TResource r, final List<TResource> goalList) {
			if (r == null)
				return;
			if (r.getSubParts().size() > 0) {
				for (PartOf pf : r.getSubParts()) {
					if (pf.getSource() instanceof TResource) {
						goalList.add((TResource) pf.getSource());
						getDocuemntsChildren((TResource) pf.getSource(), goalList);
					}
				}
			}
		}
	}

	protected String formatStrings(List<String> strings) {
		if (strings.size() == 1) {
			return strings.get(0);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			if (i == strings.size() - 1)
				sb.append(" and ");
			else if (i > 0)
				sb.append(", ");
			sb.append(strings.get(i));
		}
		return sb.toString();
	}
}
