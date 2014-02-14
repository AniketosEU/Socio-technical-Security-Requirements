/*
* AbstractSecurityTasksGroup.java
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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.analysis.dlv.parser.DiagramDLVParser;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.ElementFinder;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DlvInputProgram;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.DLVViolationAnalysisResult;
import eu.aniketos.wp1.ststool.analysis.dlv.wrapper.DLVViolationsAnalysis.Violation;
import eu.aniketos.wp1.ststool.analysis.results.AbstarctResult;
import eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.IViolationDefinition;
import eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions.ViolationsDefinitions;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

public abstract class AbstractSecurityTasksGroup implements ITasksGroup {

	private List<AbstractTask> tasks = new ArrayList<AbstractTask>();

	protected final static int SOCIAL_VIEW = ViewsManager.SOCIAL_VIEW;
	protected final static int INFORMATION_VIEW = ViewsManager.RESOURCE_VIEW;
	protected final static int AUTHORISATION_VIEW = ViewsManager.AUTHORIZATION_VIEW;
	protected final static int CURRENT_VIEW = ViewsManager.EMPTY_VIEW;

	protected final static TaskResult RESULT_WARNING = TaskResult.COMPLETED_WARNING;
	protected final static TaskResult RESULT_ERROR = TaskResult.COMPLETED_ERROR;
	protected final static TaskResult NOT_IMPLEMENTED = TaskResult.NOT_IMPLEMENTED;

	private String name;
	private int priority;
	
	public AbstractSecurityTasksGroup(String name, int priority) {
		this.name = name;
		this.priority = priority;
		initTasks();
	}

	@Override
	public String getGroupName() {
		return name;
	}

	@Override
	public int getGroupPriority() {
		return priority;
	}

	@Override
	public List<AbstractTask> getTasks() {
		return tasks;
	}

	@SuppressWarnings("unchecked")
	private void initTasks() {
		Class[] classes = this.getClass().getDeclaredClasses();
		for (Class clazz : classes) {

			if (AbstractTask.class.isAssignableFrom(clazz)) {
				AbstractTask task = null;
				try {
					if (clazz.getAnnotation(Skip.class) == null) {
						Constructor<AbstractTask> cons = clazz
								.getDeclaredConstructor(this.getClass(),
										ITasksGroup.class);
						if (!cons.isAccessible())
							cons.setAccessible(true);
						task = cons.newInstance(this, this);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (task != null)
					tasks.add(task);
			}
		}
	}

	protected static class SecurityResult extends AbstarctResult {

		public SecurityResult(Violation v, String text, String description,
				ResultType errorResult, int view) {
			super(text, description, getResutlTypeForViolation(v, errorResult),
					view);
		}

		public SecurityResult(Violation v, String text, String description,
				EObject object, ResultType errorResult, int view) {
			super(text, description, getResutlTypeForViolation(v, errorResult),
					view);
			if (object != null) {
				List<EObject> list = new ArrayList<EObject>();
				list.add(object);
				init(list);
			}
		}

		public SecurityResult(Violation v, String text, String description,
				List<EObject> objects, ResultType errorResult, int view) {
			super(text, description, getResutlTypeForViolation(v, errorResult),
					view);
			init(objects);
		}
		
		public SecurityResult(String text, String description, List<EObject> l,
				ResultType resultForError) {
			super(text, description, resultForError,SOCIAL_VIEW);
			init(l);
		}

		private void init(List<EObject> objects) {
			this.setObjects(objects);
			this.setObjectsToMark(objects);
		}

		private static ResultType getResutlTypeForViolation(Violation v,
				ResultType errorResult) {
			if (true)
				return errorResult;
			if (v.percent() >= 100)
				return errorResult;
			return ResultType.WARNING;
		}
	}

	protected Goal getOriginalDelegatedGoal(Goal delegatedGoal) {
		if (delegatedGoal.getDelegatedFrom().size() == 0) {
			return delegatedGoal;
		}
		return getOriginalDelegatedGoal(delegatedGoal.getDelegatedFrom().get(0)
				.getSourceGoal());
	}

	protected TResource getOriginalDelegatedTResource(
			TResource delegatedResource) {
		if (delegatedResource.getProvidedFrom().size() == 0) {
			return delegatedResource;
		}
		return getOriginalDelegatedTResource(delegatedResource
				.getProvidedFrom().get(
						new Random().nextInt(delegatedResource
								.getProvidedFrom().size())).getSourceResource());
	}

	protected List<Violation> executeAnalysis(StsToolDiagram diagram,
			IViolationDefinition violation,
			IViolationDefinition... otherViolations) throws Exception {
		
		DLVViolationsAnalysis dlvInvocation = new DLVViolationsAnalysis();

		DlvInputProgram ip = new DlvInputProgram();
		ip.addMultipleLine(DiagramDLVParser.getNewInstance(diagram)
				.parseDiagram());
		ip.addMultipleLine(violation.getDLVProgram());

		List<String> filters = new ArrayList<String>();
		filters.add(violation.getFilterName());
		for (IViolationDefinition v : otherViolations) {
			filters.add(v.getFilterName());
		}

		DLVViolationAnalysisResult res = dlvInvocation.startAnalysis(ip,
				filters);
		return res.getViolations();
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

	@Retention(RetentionPolicy.RUNTIME)
	protected @interface Skip {
	}
}
