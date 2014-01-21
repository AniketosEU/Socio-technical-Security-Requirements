/*
* AbstractConsistencyTasksGroup.java
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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.analysis.results.AbstarctResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

public abstract class AbstractConsistencyTasksGroup implements ITasksGroup {

	private List<AbstractTask>				tasks						= new ArrayList<AbstractTask>();

	protected final static int				SOCIAL_VIEW				= ViewsManager.SOCIAL_VIEW;
	protected final static int				INFORMATION_VIEW		= ViewsManager.RESOURCE_VIEW;
	protected final static int				AUTHORISATION_VIEW	= ViewsManager.AUTHORIZATION_VIEW;
	protected final static int				CURRENT_VIEW			= ViewsManager.EMPTY_VIEW;

	protected final static TaskResult	RESULT_WARNING			= TaskResult.COMPLETED_WARNING;
	protected final static TaskResult	RESULT_ERROR			= TaskResult.COMPLETED_ERROR;
	protected final static TaskResult	NOT_IMPLEMENTED		= TaskResult.NOT_IMPLEMENTED;

	private String								name;
	private int									priority;

	public AbstractConsistencyTasksGroup(String name, int priority) {
		this.name = name;
		this.priority = priority;
		initTasks();
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
					task = (AbstractTask) clazz.getDeclaredConstructor(this.getClass(), ITasksGroup.class).newInstance(this, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (task != null) tasks.add(task);
			}
		}
	}


	protected static class ConsistencyResult extends AbstarctResult {

		public ConsistencyResult(String text, String description, ResultType restype, int view) {
			super(text, description, restype, view);
		}

		public ConsistencyResult(String text, String description, EObject object, ResultType restype, int view) {
			super(text, description, restype, view);
			if (object != null) {
				List<EObject> list = new ArrayList<EObject>();
				list.add(object);
				init(list);
			}
		}

		public ConsistencyResult(String text, String description, List<EObject> objects, ResultType restype, int view) {
			super(text, description, restype, view);
			init(objects);
		}

		private void init(List<EObject> objects){
			this.setObjects(objects);
			this.setObjectsToMark(objects);
		}
	}

}
