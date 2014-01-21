/*
* AuthorisationTasksConsistencyGroup.java
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

import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityEmpty_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityEmpty_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityNoInformation_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityNoInformation_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityNoOperations_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_AuthorisationsValidityNoOperations_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_DuplicateAuthorisation_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_DuplicateAuthorisation_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.TaskName_AuthorisationsValidity;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.TaskName_DuplicateAuthorisation;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.getMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;

public class AuthorisationTasksConsistencyGroup extends AbstractConsistencyTasksGroup {

	public AuthorisationTasksConsistencyGroup(String name, int priority) {
		super(name, priority);
	}

	class AuthorisationsValidityTask extends AbstractConsistencyTasks {

		public AuthorisationsValidityTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_AuthorisationsValidity);
		int		priority	= 10;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (Actor a : diagram.getDiagActors()) {
				for (Authorisation auth : a.getOutgoingAuthorisations()) {
					if ((auth.getResources().size() == 0) && (!auth.isUsage() && !auth.isModification() && !auth.isProduce() && !auth.isDistribution())) {
						results.add(new ConsistencyInformationResult(getMessage(Result_AuthorisationsValidityEmpty_text, auth.getSource().getName(), auth.getTarget().getName()), getMessage(Result_AuthorisationsValidityEmpty_desc), auth, getResultForError()));
					} else if (auth.getResources().size() == 0) {
						results.add(new ConsistencyInformationResult(getMessage(Result_AuthorisationsValidityNoInformation_text, auth.getSource().getName(), auth.getTarget().getName()), getMessage(Result_AuthorisationsValidityNoInformation_desc), auth, getResultForError()));
					} else if (!auth.isUsage() && !auth.isModification() && !auth.isProduce() && !auth.isDistribution()) {
						results.add(new ConsistencyInformationResult(getMessage(Result_AuthorisationsValidityNoOperations_text, auth.getSource().getName(), auth.getTarget().getName()), getMessage(Result_AuthorisationsValidityNoOperations_desc), auth, getResultForError()));
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}

	/*class AuthorisationsInformationOwnerTask extends AbstractTask{
		public AuthorisationsInformationOwnerTask(ITasksGroup group) {super(group);}
		
		String name=getMessage(TaskName_AuthorisationsInformationOwner);
		int priority=12;
		//int minTime=100;
		//int timeOut=1000;
		//BlockType blockType=BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for(Actor a:diagram.getDiagActors()){
				for(Authorisation auth:a.getOutgoingAuthorisations()){
					for(IResource r:auth.getResources()){
						if(r.getOwners().size()==0){
							results.add(new ConsistencyInformationResult(getMessage(Result_AuthorisationsInformationNoOwner_text,r.getName(),a.getName()),getMessage(Result_AuthorisationsInformationNoOwner_desc),auth,WARNING));
						}else if(r.getOwners().get(0).getSource()!=a){
							results.add(new ConsistencyInformationResult(getMessage(Result_AuthorisationsInformationWrongOwner_text,r.getName(),((Own)r.getOwners().get(0)).getSource().getName(),a.getName()),getMessage(Result_AuthorisationsInformationWrongOwner_desc),auth,WARNING));
						}
					}
				}
			}
			if(results.size()==0)return COMPLETED_OK;
			else return COMPLETED_WARNING;
		}
	}*/

	class DuplicateAuthorisationTask extends AbstractConsistencyTasks {

		public DuplicateAuthorisationTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_DuplicateAuthorisation);
		int		priority	= 30;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){

			for (Actor a : diagram.getDiagActors()) {
				List<Authorisation> authList = new ArrayList<Authorisation>();
				for (Authorisation auth : a.getOutgoingAuthorisations()) {
					//if(auth.getResources().size()>0) here shouldn't be invalid auth
					authList.add(auth);
				}
				if (authList.size() > 1) {
					for (int i = 0; i < authList.size() - 1; i++) {
						Authorisation a1 = authList.get(i);
						for (int k = i + 1; k < authList.size(); k++) {
							Authorisation a2 = authList.get(k);
							compareAuthorisation(a1, a2, results);
						}
					}
				}
			}
			return getErrorResult(results.size() != 0);
		}

		private boolean compareAuthorisation(Authorisation a1,Authorisation a2,List<IResult> results){
			if (a1.getTarget() != a2.getTarget() || a1.getSource() != a2.getSource()) return true;
			String[] errParam = new String[] { a1.getSource().getName(), a1.getTarget().getName() };
			Map<IResource, List<Goal>> map1 = new HashMap<IResource, List<Goal>>();
			for (IResource r : a1.getResources()) {
				map1.put(r, a1.getGoals());
			}
			Map<IResource, List<Goal>> map2 = new HashMap<IResource, List<Goal>>();
			for (IResource r : a2.getResources()) {
				map2.put(r, a2.getGoals());
			}
			for (IResource r : map1.keySet()) {
				if (map2.containsKey(r)) {

					List<Goal> l1 = map1.get(r);
					List<Goal> l2 = map2.get(r);
					if (l1.isEmpty() || l2.isEmpty()) {
						List<EObject> list = new ArrayList<EObject>();
						list.add(a1);
						list.add(a2);
						results.add(new ConsistencyInformationResult(getMessage(Result_DuplicateAuthorisation_text, errParam), getMessage(Result_DuplicateAuthorisation_desc, errParam), list, getResultForError()));
					} else {
						for (Goal g : l1) {
							if (l2.contains(g)) {
								List<EObject> list = new ArrayList<EObject>();
								list.add(a1);
								list.add(a2);
								results.add(new ConsistencyInformationResult(getMessage(Result_DuplicateAuthorisation_text, errParam), getMessage(Result_DuplicateAuthorisation_desc, errParam), list, getResultForError()));
								//results.add(new ConsistencyInformationResult("Duplicate authorisation for resource "+r.getName()+" in the goal "+g.getName()+"scope","",list,ResultType.WARNING));
							}
						}
					}
				}
			}

			return false;
		}

	}


	/* Example!
	class Task extends AbstractTask{
		public Task(ITasksGroup group) {super(group);}
		
		String name="";
		int priority=1;
		//int minTime=100;
		//int timeOut=1000;
		//BlockType blockType=BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			return NOT_IMPLEMENTED;
		}
	}
	*/

	protected static class ConsistencyInformationResult extends ConsistencyResult {

		public ConsistencyInformationResult(String text, String description, ResultType restype) {
			super(text, description, restype, AUTHORISATION_VIEW);
		}

		public ConsistencyInformationResult(String text, String description, EObject object, ResultType restype) {
			super(text, description, object, restype, AUTHORISATION_VIEW);
		}

		public ConsistencyInformationResult(String text, String description, List<EObject> objects, ResultType restype) {
			super(text, description, objects, restype, AUTHORISATION_VIEW);
		}

	}
}
