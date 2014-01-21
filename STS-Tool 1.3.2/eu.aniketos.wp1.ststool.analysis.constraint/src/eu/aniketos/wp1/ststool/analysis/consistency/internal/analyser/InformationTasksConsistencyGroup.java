/*
* InformationTasksConsistencyGroup.java
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

import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_DocumentPartOfCicle_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_DocumentPartOfCicle_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_InformationNoOwner_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_InformationNoOwner_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_InformationPartOfCicle_desc;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.Result_InformationPartOfCicle_text;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.TaskName_InformationNoOwner;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.TaskName_PartofOfDocumentsCicles;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.TaskName_PartofOfInformationCycles;
import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.getMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;


public class InformationTasksConsistencyGroup extends AbstractConsistencyTasksGroup {

	public InformationTasksConsistencyGroup(String name, int priority) {
		super(name, priority);
	}

	class PartOfOfDocumentsCiclesTask extends AbstractConsistencyTasks {

		public PartOfOfDocumentsCiclesTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_PartofOfDocumentsCicles);
		int		priority	= 10;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){

			Set<Set<PartOf>> result = new HashSet<Set<PartOf>>();
			for (TResource r : diagram.getAllTRresources()) {
				result.addAll(checkTResourcePartOfCycleRec(r, r, new HashSet<PartOf>()));
			}
			if (result.size() > 0) {
				for (Set<PartOf> path : result) {
					List<EObject> l = new ArrayList<EObject>();
					List<Resource> resources = new ArrayList<Resource>();
					List<Resource> resources2 = new ArrayList<Resource>();
					for (PartOf p : path) {
						resources.add(p.getSource());
						resources2.add(p.getSource());
						resources2.add(p.getTarget());

						if (!l.contains(p.getSource())) l.add(p.getSource());
						l.add(p);
						if (!l.contains(p.getTarget())) l.add(p.getTarget());
					}
					l.addAll(path);

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < resources.size(); i++) {
						if (i == resources.size() - 1)
							sb.append(" and ");
						else if (i > 0) sb.append(", ");
						sb.append(resources.get(i).getName());
					}

					StringBuilder sb1 = new StringBuilder();
					for (int i = 0; i < resources2.size() / 2; i++) {
						if (i == (resources2.size() / 2) - 1)
							sb1.append(" and ");
						else if (i > 0) sb1.append(", ");
						sb1.append("from " + resources2.get(i * 2).getName() + " to " + resources2.get((i * 2) + 1).getName());
					}

					String[] params = new String[] { sb.toString(), sb1.toString() };
					results.add(new ConsistencyInformationResult(getMessage(Result_DocumentPartOfCicle_text, params), getMessage(Result_DocumentPartOfCicle_desc, params), l, getResultForError())); //$NON-NLS-2$

				}
			}
			return getErrorResult(results.size() != 0);
		}

		private Set<Set<PartOf>> checkTResourcePartOfCycleRec(final Resource startResource,Resource r,final Set<PartOf> partOfPath){
			Set<Set<PartOf>> result = new HashSet<Set<PartOf>>();

			for (PartOf pf : r.getPartsOf()) {
				if (partOfPath.contains(pf)) return result;
				Set<PartOf> partOfPathUpdated = new HashSet<PartOf>(partOfPath);
				partOfPathUpdated.add(pf);
				if (pf.getTarget() == startResource) {
					result.add(partOfPathUpdated);
				} else {
					result.addAll(checkTResourcePartOfCycleRec(startResource, pf.getTarget(), partOfPathUpdated));
				}
			}
			return result;
		}
	}

	class PartofOfInformationCyclesTask extends AbstractConsistencyTasks {

		public PartofOfInformationCyclesTask(ITasksGroup group) {
			super(group, RESULT_ERROR);
		}

		String	name		= getMessage(TaskName_PartofOfInformationCycles);
		int		priority	= 20;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){

			Set<Set<PartOf>> result = new HashSet<Set<PartOf>>();
			for (IResource r : diagram.getAllIRresources()) {
				result.addAll(checkTResourcePartOfCycleRec(r, r, new HashSet<PartOf>()));
			}
			if (result.size() > 0) {
				for (Set<PartOf> path : result) {
					List<EObject> l = new ArrayList<EObject>();
					List<Resource> resources = new ArrayList<Resource>();
					List<Resource> resources2 = new ArrayList<Resource>();
					for (PartOf p : path) {
						resources.add(p.getSource());
						resources2.add(p.getSource());
						resources2.add(p.getTarget());

						if (!l.contains(p.getSource())) l.add(p.getSource());
						l.add(p);
						if (!l.contains(p.getTarget())) l.add(p.getTarget());
					}
					l.addAll(path);

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < resources.size(); i++) {
						if (i == resources.size() - 1)
							sb.append(" and ");
						else if (i > 0) sb.append(", ");
						sb.append(resources.get(i).getName());
					}

					StringBuilder sb1 = new StringBuilder();
					for (int i = 0; i < resources2.size() / 2; i++) {
						if (i == (resources2.size() / 2) - 1)
							sb1.append(" and ");
						else if (i > 0) sb1.append(", ");
						sb1.append("from " + resources2.get(i * 2).getName() + " to " + resources2.get((i * 2) + 1).getName());
					}

					String[] params = new String[] { sb.toString(), sb1.toString() };
					results.add(new ConsistencyInformationResult(getMessage(Result_InformationPartOfCicle_text, params), getMessage(Result_InformationPartOfCicle_desc, params), l, getResultForError())); //$NON-NLS-2$

				}
			}
			return getErrorResult(results.size() != 0);
		}

		private Set<Set<PartOf>> checkTResourcePartOfCycleRec(final Resource startResource,Resource r,final Set<PartOf> partOfPath){
			Set<Set<PartOf>> result = new HashSet<Set<PartOf>>();

			for (PartOf pf : r.getPartsOf()) {
				if (partOfPath.contains(pf)) return result;
				Set<PartOf> partOfPathUpdated = new HashSet<PartOf>(partOfPath);
				partOfPathUpdated.add(pf);
				if (pf.getTarget() == startResource) {
					result.add(partOfPathUpdated);
				} else {
					result.addAll(checkTResourcePartOfCycleRec(startResource, pf.getTarget(), partOfPathUpdated));
				}
			}
			return result;
		}
	}

	/*@Override
	public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
		for(IResource tr:diagram.getAllIRresources()){
			Resource invalidRes=hasPartOfCicleRecursive(tr,new ArrayList<Resource>());
			if(invalidRes!=null){
				results.add(new ConsistencyInformationResult(getMessage(Result_InformationPartOfCicle_text,invalidRes.getName()),getMessage(Result_InformationPartOfCicle_desc),invalidRes,getResultForError())); //$NON-NLS-2$
			}
		}
		return getErrorResult(results.size() != 0);
	}
	
	private Resource hasPartOfCicleRecursive(Resource r,List<Resource> visitedResource){
		if(r.getPartsOf().size()==0)return null;
		visitedResource.add(r);
		for(PartOf p:r.getPartsOf()){
			if(p.getTarget()!=null){
				if(visitedResource.contains(p.getTarget())){
					return p.getTarget();
				}else{
					Resource res=hasPartOfCicleRecursive(p.getTarget(),new ArrayList<Resource>(visitedResource));
					if(res!=null)return res;
				}
			}
		}
		return null;
	}
	}*/

	class InformationNoOwnerTask extends AbstractConsistencyTasks {

		public InformationNoOwnerTask(ITasksGroup group) {
			super(group, RESULT_WARNING);
		}

		String	name		= getMessage(TaskName_InformationNoOwner);
		int		priority	= 30;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			for (IResource r : diagram.getDiagIResources()) {
				if (r.getOwners().size() == 0) {
					results.add(new ConsistencyInformationResult(getMessage(Result_InformationNoOwner_text, r.getName()), getMessage(Result_InformationNoOwner_desc, r.getName()), r, getResultForError()));
				}
			}
			return getErrorResult(results.size() != 0);
		}
	}


	/* Example!
	class Task1 extends AbstractTask{
		public Task (ITasksGroup group) {super(group);}
		
		//String name="";
		//int priority=1;
		//int minTime=100;
		//int timeOut=1000;
		//BlockType blockType=BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
		
		}
	}
	*/

	protected static class ConsistencyInformationResult extends ConsistencyResult {

		public ConsistencyInformationResult(String text, String description, ResultType restype) {
			super(text, description, restype, INFORMATION_VIEW);
		}

		public ConsistencyInformationResult(String text, String description, EObject object, ResultType restype) {
			super(text, description, object, restype, INFORMATION_VIEW);
		}

		public ConsistencyInformationResult(String text, String description, List<EObject> objects, ResultType restype) {
			super(text, description, objects, restype, INFORMATION_VIEW);
		}

	}
}
