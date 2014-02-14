/*
* AuthorisationAnalysisTask.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.ElementFinder;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.Violation;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.IViolationDefinition;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;

public class AuthorisationAnalysisTask extends AbstractSecurityTasksGroup {

	public AuthorisationAnalysisTask(String name, int priority) {
		super(name, priority);
	}	
	
	private List<Violation> violations;
	private Map<String, StsObject> idMap;
	
	/*private Map<Actor,List<IResource>> invalidUsageMap=new HashMap<Actor, List<IResource>>();
	private Map<Actor,List<IResource>> invalidModifMap=new HashMap<Actor, List<IResource>>();
	private Map<Actor,List<IResource>> invalidProduMap=new HashMap<Actor, List<IResource>>();
	private Map<Actor,List<IResource>> invalidDisclMap=new HashMap<Actor, List<IResource>>();*/
	private Map<Actor,List<IResource>> invalidTransMap=new HashMap<Actor, List<IResource>>();
	
	class AuthorisationsConflictPreanalysisTask extends AbstractSecurityTasks {

		public AuthorisationsConflictPreanalysisTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Pre_Ahtorisation_Conflict);
		int		priority	= 20;
		BlockType blockType = BlockType.CLASS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{
			idMap = ElementFinder.buildAllElementMap(diagram);
			try {
				violations = executeAnalysis(diagram,AUTH_USAGE_CONFLICT,AUTH_MODIF_CONFLICT,AUTH_PRODU_CONFLICT,AUTH_DISCL_CONFLICT,AUTH_TRASF_CONFLICT);
			} catch (Exception e) {
				e.printStackTrace();
				return TaskResult.COMPLETED_ERROR;
			}
			return TaskResult.COMPLETED_OK;
		}
	}
	
	class AuthorisationsConflictCheckTask extends AbstractSecurityTasks {

		public AuthorisationsConflictCheckTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Ahtorisation_Conflict);
		int		priority	= 21;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{
			for(Violation v:violations){
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				Actor a3 = (Actor) idMap.get(v.p.getParameterAt(2).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(3).getName());
				
				Object[] msgParam={a1.getName(),a2.getName(),a3.getName(),i.getName()};
				List<EObject> l=new ArrayList<EObject>();
				l.add(a3);
				for(Authorisation a:a1.getOutgoingAuthorisations()){
					if(a.getTarget()==a3 && a.getResources().contains(i)){
						l.add(a);
					}
				}
				for(Authorisation a:a2.getOutgoingAuthorisations()){
					if(a.getTarget()==a3 && a.getResources().contains(i)){
						l.add(a);
					}
				}
				
				if(v.p.getName().equals(AUTH_USAGE_CONFLICT.getFilterName())){
					results.add(new SecurityResult(v, getMessage(Result_Auth_Usage_Conflict,msgParam), getMessage(Result_Auth_Usage_Conflict_desc,msgParam), l, getResultForError(), AUTHORISATION_VIEW));
				}else if(v.p.getName().equals(AUTH_MODIF_CONFLICT.getFilterName())){
					results.add(new SecurityResult(v, getMessage(Result_Auth_Modif_Conflict,msgParam), getMessage(Result_Auth_Modif_Conflict_desc,msgParam), l, getResultForError(), AUTHORISATION_VIEW));
				}else if(v.p.getName().equals(AUTH_PRODU_CONFLICT.getFilterName())){
					results.add(new SecurityResult(v, getMessage(Result_Auth_Produ_Conflict,msgParam), getMessage(Result_Auth_Produ_Conflict_desc,msgParam), l, getResultForError(), AUTHORISATION_VIEW));
				}else if(v.p.getName().equals(AUTH_DISCL_CONFLICT.getFilterName())){
					results.add(new SecurityResult(v, getMessage(Result_Auth_Discl_Conflict,msgParam), getMessage(Result_Auth_Discl_Conflict_desc,msgParam), l, getResultForError(), AUTHORISATION_VIEW));
				}else if(v.p.getName().equals(AUTH_TRASF_CONFLICT.getFilterName())){
					addRoleToMap(invalidTransMap, a3, i);
					results.add(new SecurityResult(v, getMessage(Result_Auth_Trans_Conflict,msgParam), getMessage(Result_Auth_Trans_Conflict_desc,msgParam), l, getResultForError(), AUTHORISATION_VIEW));
				}
			}
			return getErrorResult(results.size() !=0);
		}
		
		private void addRoleToMap(Map<Actor,List<IResource>> map,Actor a,IResource i){
			if(!map.containsKey(a)){
				map.put(a,new ArrayList<IResource>());
			}
			 List<IResource> l=map.get(a);
			 l.add(i);
			 map.put(a,l);
		}
	}

	
	
	class OperationsViolationPreanalysisTask extends AbstractSecurityTasks {

		public OperationsViolationPreanalysisTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Operation_PreAnalysis);
		int		priority	= 30;
		BlockType blockType = BlockType.CLASS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			try {
				violations = executeAnalysis(diagram,NON_USAGE_VIOLATION,NON_MODIFICATION_VIOLATION,NON_PRODUCTION_VIOLATION,NTK_VIOLATION,NON_DISCLOSURE_VIOLATION);
				idMap = ElementFinder.buildAllElementMap(diagram);
			} catch (Exception e) {
				e.printStackTrace();
				return TaskResult.COMPLETED_ERROR;
			}
			return TaskResult.COMPLETED_OK;
		}
	}
	
	class NonDisclosureViolationTask extends AbstractSecurityTasks {

		public NonDisclosureViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NonDisclosure_Violation);
		int		priority	= 34;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(NON_DISCLOSURE_VIOLATION);
			for (Violation v : violations) {
				//violate_non_disclosure(R1,I,D)
				Actor a = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(1).getName());
				TResource d = (TResource) idMap.get(v.p.getParameterAt(2).getName());

				List<EObject> l = new ArrayList<EObject>();
				l.add(a);
				l.add(i);

				Set<String> providedToSet = new HashSet<String>();


				TResource res = null;
				for (TResource aRes : a.getTResources()) {
					if (getOriginalDelegatedTResource(aRes) == d) {
						res = aRes;
					}
				}
				l.add(res);
				for (TangibleBy tb : res.getIntangibleElements()) {
					if (tb.getSource() == i) l.add(tb);
				}

				for (Provision p : res.getProvidedTo()) {
					l.add(p);
					l.add(p.getTargetResource());
					providedToSet.add(p.getTarget().getName());
					if (!l.contains(p.getTarget())) l.add(p.getTarget());
				}

				String requester = null;
				for (Authorisation auth : a.getIncomingAuthorisations()) {
					if (auth.getResources().contains(i) && (!auth.isDistribution())) {
						requester = auth.getSource().getName();
						l.add(auth);
					}
				}
				if (requester != null) {
					results.add(new SecurityResult(v, getMessage(Result_NonDisclosure_Violation_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonDisclosure_Violation_desc, requester, a.getName(), i.getName(), formatStrings(new ArrayList<String>(providedToSet)), d.getName()), l, getResultForError(), SOCIAL_VIEW));
				} else {
					results.add(new SecurityResult(v, getMessage(Result_NonDisclosure_Violation_NoAuth_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonDisclosure_Violation_NoAuth_desc, a.getName(), i.getName(), formatStrings(new ArrayList<String>(providedToSet)), d.getName()), l, getResultForError(), SOCIAL_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	
	class NonUsageViolationTask extends AbstractSecurityTasks {

		public NonUsageViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NonUsage_Violation);
		int		priority	= 35;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(NON_USAGE_VIOLATION);
			for (Violation v : violations) {
				//violate_non_usage(R,I,G)
				Actor a = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(1).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());

				List<EObject> l = new ArrayList<EObject>();
				l.add(a);
				l.add(i);

				Goal goal = null;
				for (Goal aGoal : a.getGoals()) {
					if (getOriginalDelegatedGoal(aGoal) == g) {
						goal = aGoal;
					}
				}
				l.add(goal);

				Set<String> documents = new HashSet<String>();
				for (Need n : goal.getResourceNeeded()) {
					TResource r = n.getTarget();
					if (r != null) {
						boolean validResource = false;
						for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
							if (tb.getSource() == i) validResource = true;
						}
						if (validResource) {
							l.add(n);
							l.add(n.getTarget());
							documents.add(n.getTarget().getName());
						}
					}
				}

				String requester = null;
				for (Authorisation auth : a.getIncomingAuthorisations()) {
					if (auth.getResources().contains(i) && (!auth.isUsage())) {
						requester = auth.getSource().getName();
						l.add(auth);
					}
				}

				if (requester != null) {
					results.add(new SecurityResult(v, getMessage(Result_NonUsage_Violation_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonUsage_Violation_desc, requester, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				} else {
					results.add(new SecurityResult(v, getMessage(Result_NonUsage_Violation_NoAuth_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonUsage_Violation_NoAuth_desc, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class NonModificationViolationTask extends AbstractSecurityTasks {

		public NonModificationViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NonModification_Violation);
		int		priority	= 40;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(NON_MODIFICATION_VIOLATION);
			for (Violation v : violations) {
				//violate_non_modification(R,I,G)
				Actor a = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(1).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());

				List<EObject> l = new ArrayList<EObject>();
				l.add(a);
				l.add(i);

				Goal goal = null;
				for (Goal aGoal : a.getGoals()) {
					if (getOriginalDelegatedGoal(aGoal) == g) {
						goal = aGoal;
					}
				}
				l.add(goal);

				Set<String> documents = new HashSet<String>();
				for (Modify m : goal.getResourcesModified()) {
					TResource r = m.getTarget();
					if (r != null) {
						boolean validResource = false;
						for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
							if (tb.getSource() == i) validResource = true;
						}
						if (validResource) {
							l.add(m);
							l.add(m.getTarget());
							documents.add(m.getTarget().getName());
						}
					}
				}

				String requester = null;
				for (Authorisation auth : a.getIncomingAuthorisations()) {
					if (auth.getResources().contains(i) && (!auth.isModification())) {
						requester = auth.getSource().getName();
						l.add(auth);
					}
				}

				if (requester != null) {
					results.add(new SecurityResult(v, getMessage(Result_NonModification_Violation_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonModification_Violation_desc, requester, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				} else {
					results.add(new SecurityResult(v, getMessage(Result_NonModification_Violation_NoAuth_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonModification_Violation_NoAuth_desc, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class NonProductionViolationTask extends AbstractSecurityTasks {

		public NonProductionViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NonProduction_Violation);
		int		priority	= 50;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(NON_PRODUCTION_VIOLATION);
			for (Violation v : violations) {
				//violate_non_production(R,I,G)
				Actor a = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(1).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());

				List<EObject> l = new ArrayList<EObject>();
				l.add(a);
				l.add(i);

				Goal goal = null;
				for (Goal aGoal : a.getGoals()) {
					if (getOriginalDelegatedGoal(aGoal) == g) {
						goal = aGoal;
					}
				}
				l.add(goal);

				Set<String> documents = new HashSet<String>();
				for (Produce p : goal.getResourcesProduced()) {
					TResource r = p.getTarget();
					if (r != null) {
						boolean validResource = false;
						for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
							if (tb.getSource() == i) validResource = true;
						}
						if (validResource) {
							l.add(p);
							l.add(p.getTarget());
							documents.add(p.getTarget().getName());
						}
					}
				}

				String requester = null;
				for (Authorisation auth : a.getIncomingAuthorisations()) {
					if (auth.getResources().contains(i) && (!auth.isProduce())) {
						requester = auth.getSource().getName();
						l.add(auth);
					}
				}

				if (requester != null) {
					results.add(new SecurityResult(v, getMessage(Result_NonProduction_Violation_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonProduction_Violation_desc, requester, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				} else {
					results.add(new SecurityResult(v, getMessage(Result_NonProduction_Violation_NoAuth_text, v.percent(), a.getName(), i.getName()), getMessage(Result_NonProduction_Violation_NoAuth_desc, a.getName(), i.getName(), g.getName(), formatStrings(new ArrayList<String>(documents))), l, getResultForError(), SOCIAL_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class NTKViolationTask extends AbstractSecurityTasks {

		public NTKViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_NTK_Violation);
		int		priority	= 51;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(NTK_VIOLATION);
			for (Violation v : violations) {
				//violate_ntk(R,I,G)
				Actor a = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(1).getName());
				Goal g = (Goal) idMap.get(v.p.getParameterAt(2).getName());

				List<EObject> l = new ArrayList<EObject>();
				l.add(a);
				l.add(i);

				Goal goal = null;
				for (Goal aGoal : a.getGoals()) {
					if (getOriginalDelegatedGoal(aGoal) == g) {
						goal = aGoal;
					}
				}
				l.add(goal);

				for (Need n : goal.getResourceNeeded()) {
					TResource r = n.getTarget();
					boolean validResource = false;
					for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
						if (tb.getSource() == i) validResource = true;
					}
					if (validResource) {
						l.add(n);
						l.add(n.getTarget());
					}
				}
				for (Modify m : goal.getResourcesModified()) {
					TResource r = m.getTarget();
					boolean validResource = false;
					for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
						if (tb.getSource() == i) validResource = true;
					}
					if (validResource) {
						l.add(m);
						l.add(m.getTarget());
					}
				}
				for (Produce p : goal.getResourcesProduced()) {
					TResource r = p.getTarget();
					boolean validResource = false;
					for (TangibleBy tb : getOriginalDelegatedTResource(r).getIntangibleElements()) {
						if (tb.getSource() == i) validResource = true;
					}
					if (validResource) {
						l.add(p);
						l.add(p.getTarget());
					}
				}

				List<String> goals = new ArrayList<String>();
				String requester = null;
				for (Authorisation auth : a.getIncomingAuthorisations()) {
					if (auth.getResources().contains(i) && auth.getGoals().size() > 0) {
						requester = auth.getSource().getName();
						l.add(auth);
						goals.clear();
						for (Goal ggg : auth.getGoals()) {
							goals.add(ggg.getName());
						}

					}
				}

				if (requester != null) {
					results.add(new SecurityResult(v, getMessage(Result_NTK_Violation_text, v.percent(), a.getName()), getMessage(Result_NTK_Violation_desc, requester, a.getName(), i.getName(), formatStrings(goals), g.getName()), l, getResultForError(), SOCIAL_VIEW));
				} 
//					else {
//					results.add(new SecurityResult(v, "----------"+getMessage(Result_NTK_Violation_NoAuth_text, v.percent(), a.getName()), getMessage(Result_NTK_Violation_NoAuth_desc, a.getName(), i.getName(), formatStrings(goals), g.getName()), l, getResultForError(), SOCIAL_VIEW));
//				}
			}
			return getErrorResult(results.size() != 0);
		}
	}
	
	class AuthorityViolationPreanalysisTask extends AbstractSecurityTasks {

		public AuthorityViolationPreanalysisTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String		name			= getMessage(TaskName_Authorisation_PreAnalysis);
		int			priority		= 59;
		BlockType	blockType	= BlockType.CLASS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			try {
				violations = executeAnalysis(diagram,UNAUTH_DEL_OF_AUTH_VIOLATION,UNAUTH_DEL_OF_DIST_VIOLATION,UNAUTH_DEL_OF_MOD_VIOLATION,UNAUTH_DEL_OF_PROD_VIOLATION,UNAUTH_DEL_OF_USAGE_VIOLATION);
			} catch (Exception e) {
				e.printStackTrace();
				return TaskResult.COMPLETED_ERROR;
			}
			return TaskResult.COMPLETED_OK;
		}
	}

	class UnAuthDelOfAuthorityViolationTask extends AbstractSecurityTasks {

		public UnAuthDelOfAuthorityViolationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Unath_Del_Auth_Violation);
		int		priority	= 60;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(UNAUTH_DEL_OF_AUTH_VIOLATION);
			for (Violation v : violations) {
				//violate_del_of_authority(R1,R2,I)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(2).getName());
				
				if(!invalidTransMap.containsKey(a1) || !invalidTransMap.get(a1).contains(i)){
				
					List<EObject> l = new ArrayList<EObject>();
					l.add(a1);
					l.add(a2);
					for (Authorisation auth : a1.getOutgoingAuthorisations()) {
						if ((auth.getTarget() == a2) && (auth.getResources().contains(i))) {
							l.add(auth);
						}
					}
					results.add(new SecurityResult(v, getMessage(Result_Authority_Violation_text, v.percent(), a1.getName()), getMessage(Result_Authority_Violation_desc, a1.getName(), a2.getName()), l, getResultForError(), AUTHORISATION_VIEW));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class UnAuthDelOfUsageTask extends AbstractSecurityTasks {

		public UnAuthDelOfUsageTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Unath_Del_Usage_Violation);
		int		priority	= 70;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(UNAUTH_DEL_OF_USAGE_VIOLATION);
			for (Violation v : violations) {
				//FIXME: remove Goal
				//unauthorised_del_of_usage(R1,R2,I)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(2).getName());
				List<EObject> l = new ArrayList<EObject>();
				l.add(a1);
				l.add(a2);
				for (Authorisation auth : a1.getOutgoingAuthorisations()) {
					if ((auth.getTarget() == a2) && (auth.getResources().contains(i)) && (auth.isUsage())) {
						l.add(auth);
					}
				}
				results.add(new SecurityResult(v, getMessage(Result_Unath_Del_Usage_Violation_text, v.percent(), a1.getName()), getMessage(Result_Unath_Del_Usage_Violation_desc, a1.getName(), i.getName(), a2.getName()), l, getResultForError(), AUTHORISATION_VIEW));
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class UnAuthDelOfModificationTask extends AbstractSecurityTasks {

		public UnAuthDelOfModificationTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Unath_Del_Mod_Violation);
		int		priority	= 80;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(UNAUTH_DEL_OF_MOD_VIOLATION);
			for (Violation v : violations) {
				//unauthorised_del_of_modification(R1,R2,I)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(2).getName());
				List<EObject> l = new ArrayList<EObject>();
				l.add(a1);
				l.add(a2);
				for (Authorisation auth : a1.getOutgoingAuthorisations()) {
					if ((auth.getTarget() == a2) && (auth.getResources().contains(i)) && (auth.isModification())) {
						l.add(auth);
					}
				}
				results.add(new SecurityResult(v, getMessage(Result_Unath_Del_Mod_Violation_text, v.percent(), a1.getName()), getMessage(Result_Unath_Del_Mod_Violation_desc, a1.getName(), i.getName(), a2.getName()), l, getResultForError(), AUTHORISATION_VIEW));
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class UnAuthDelOfProductionTask extends AbstractSecurityTasks {

		public UnAuthDelOfProductionTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Unath_Del_Prod_Violation);
		int		priority	= 90;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(UNAUTH_DEL_OF_PROD_VIOLATION);
			for (Violation v : violations) {
				//unauthorised_del_of_production(R1,R2,I)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(2).getName());
				List<EObject> l = new ArrayList<EObject>();
				l.add(a1);
				l.add(a2);
				for (Authorisation auth : a1.getOutgoingAuthorisations()) {
					if ((auth.getTarget() == a2) && (auth.getResources().contains(i)) && (auth.isProduce())) {
						l.add(auth);
					}
				}
				results.add(new SecurityResult(v, getMessage(Result_Unath_Del_Prod_Violation_text, v.percent(), a1.getName()), getMessage(Result_Unath_Del_Prod_Violation_desc, a1.getName(), i.getName(), a2.getName()), l, getResultForError(), AUTHORISATION_VIEW));
			}
			return getErrorResult(results.size() != 0);
		}
	}

	class UnAuthDelOfDistributionTask extends AbstractSecurityTasks {

		public UnAuthDelOfDistributionTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_Unath_Del_Dist_Violation);
		int		priority	= 100;


		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception{

			List<Violation> violations = getFilteredViolation(UNAUTH_DEL_OF_DIST_VIOLATION);
			for (Violation v : violations) {
				//unauthorised_del_of_distribution(R1,R2,I)
				Actor a1 = (Actor) idMap.get(v.p.getParameterAt(0).getName());
				Actor a2 = (Actor) idMap.get(v.p.getParameterAt(1).getName());
				IResource i = (IResource) idMap.get(v.p.getParameterAt(2).getName());
				List<EObject> l = new ArrayList<EObject>();
				l.add(a1);
				l.add(a2);
				for (Authorisation auth : a1.getOutgoingAuthorisations()) {
					if ((auth.getTarget() == a2) && (auth.getResources().contains(i)) && (auth.isDistribution())) {
						l.add(auth);
					}
				}
				results.add(new SecurityResult(v, getMessage(Result_Unath_Del_Dist_Violation_text, v.percent(), a1.getName()), getMessage(Result_Unath_Del_Dist_Violation_desc, a1.getName(), i.getName(), a2.getName()), l, getResultForError(), AUTHORISATION_VIEW));
			}
			return getErrorResult(results.size() != 0);
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
