/*
* AbstractRiskTasksGroup.java
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
package eu.aniketos.wp1.ststool.analysis.risk.tasks.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.dlv.utils.ElementFinder;
import eu.aniketos.wp1.ststool.analysis.results.AbstarctResult;
import eu.aniketos.wp1.ststool.analysis.risk.DiagramObserver;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

public abstract class AbstractRiskTasksGroup implements ITasksGroup {

	private List<AbstractTask>					tasks					= new ArrayList<AbstractTask>();

	protected final static TaskResult		RESULT_WARNING		= TaskResult.COMPLETED_WARNING;
	protected final static TaskResult		RESULT_ERROR		= TaskResult.COMPLETED_ERROR;
	protected final static TaskResult		NOT_IMPLEMENTED	= TaskResult.NOT_IMPLEMENTED;

	private String									name;
	private int										priority;
	private final StsToolDiagram				diagram;
	protected final Map<String, StsObject>	idMap;

	public AbstractRiskTasksGroup(String name, int priority) {
		this.name = name;
		this.priority = priority;
		initTasks();
		diagram = DiagramObserver.getDiagram();
		if (diagram == null) throw new RuntimeException("can't initilalize secuity task! diagram null");
		idMap = ElementFinder.buildAllElementMap(diagram);
	}

	@Override
	public String getGroupName(){
		return name;
	}

	@Override
	public int getGroupPriority(){
		return priority;
	}

	@Override
	public List<AbstractTask> getTasks(){
		return tasks;
	}

	private void initTasks(){
		Class[] classes = this.getClass().getDeclaredClasses();
		for (Class clazz : classes) {

			if (AbstractTask.class.isAssignableFrom(clazz)) {
				AbstractTask task = null;
				try {
					if (clazz.getAnnotation(Skip.class) == null) {
						Constructor<AbstractTask> cons = clazz.getDeclaredConstructor(this.getClass(), ITasksGroup.class);
						if (!cons.isAccessible()) cons.setAccessible(true);
						task = cons.newInstance(this, this);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (task != null) tasks.add(task);
			}
		}
	}


	protected static class RiskResult extends AbstarctResult {

		public RiskResult(String text, String description, ResultType restype) {
			super(text, description, restype, ViewsManager.SOCIAL_VIEW);
		}

		public RiskResult(String text, String description, List<EObject> objects, ResultType restype) {
			super(text, description, restype, ViewsManager.SOCIAL_VIEW);
			init(objects);
		}

		private void init(List<EObject> objects){
			this.setObjects(objects);
			this.setObjectsToMark(objects);
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	protected @interface Skip {
	}
}
