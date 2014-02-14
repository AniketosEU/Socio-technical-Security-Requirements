/*
* DiagramAnalyser.java
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
package eu.aniketos.wp1.ststool.analysis.util.analyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.Activator;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITaskListener.InterruptionType;
import eu.aniketos.wp1.ststool.analysis.util.analyser.internal.DiagramAnalyserThread;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;

public final class DiagramAnalyser {

	private List<ITaskListener>						listeners		= new ArrayList<ITaskListener>();
	private Map<ITasksGroup, List<AbstractTask>>	tasksGroupMap	= new HashMap<ITasksGroup, List<AbstractTask>>();

	public void addTaskGroupClass(Class<? extends ITasksGroup> clazz)
			throws InstantiationException,IllegalAccessException{
		if (clazz.isInterface()) throw new RuntimeException("Invalid parameter class: Can't be an interface"); //$NON-NLS-1$

		for (ITasksGroup taskGroup : tasksGroupMap.keySet()) {
			if (taskGroup.getClass().equals(clazz)) return;
		}
		ITasksGroup tasksGroup = clazz.newInstance();
		addTaskGroup(tasksGroup);
	}

	public void addTaskGroup(ITasksGroup tasksGroup){
		if (tasksGroupMap.containsKey(tasksGroup)) return;
		tasksGroupMap.put(tasksGroup, tasksGroup.getTasks());
	}

	public void removeAllTaskGroups(){
		tasksGroupMap.clear();
	}


	protected List<AbstractTask> getAllTask(){
		List<AbstractTask> result = new ArrayList<AbstractTask>();
		for (List<AbstractTask> t : tasksGroupMap.values())
			result.addAll(t);
		return result;
	}

	protected TaskResult performTask(AbstractTask task,StsToolDiagram diagram,List<IResult> analysisResults,boolean waitMinTime)
			throws Exception{
		if (diagram == null) throw new AnalysisException("Invalid input parameter : diagram can't be null"); //$NON-NLS-1$
		if (analysisResults == null) throw new AnalysisException("Invalid input parameter : analysisResults can't be null"); //$NON-NLS-1$

		long startTime = System.currentTimeMillis();
		TaskResult taskResult = task.executeTask(diagram, analysisResults);

		for (IResult r : analysisResults) {
			r.setTaskName(task.getName());
		}

		long passedTime = System.currentTimeMillis() - startTime;
		int mintime = task.getMinTime();
		if (waitMinTime && passedTime < mintime) {
			try {
				Thread.sleep(mintime - passedTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fireTaskCompelte(task, taskResult, analysisResults);
		return taskResult;
	}

	protected boolean performTasks(List<AbstractTask> tasks,StsToolDiagram diagram,boolean sort,final Map<AbstractTask, List<IResult>> resultMap){
		return performTasks(tasks, diagram, sort, resultMap, new NullProgressMonitor());
	}


	/**
	 * /** Execute a list of Task;
	 * 
	 * @param tasks
	 * @param diagram
	 * @param sort
	 *           Task is sorted by AnalysisTasksProvider.priority -> AnalysisTasksProvider -> task.priority -> task.name
	 * @param monitor
	 * @return
	 */
	protected boolean performTasks(List<AbstractTask> tasks,StsToolDiagram diagram,boolean sort,final Map<AbstractTask, List<IResult>> resultMap,IProgressMonitor monitor){
		if (diagram == null) throw new AnalysisException("Invalid input parameter : diagram can't be null"); //$NON-NLS-1$
		if (tasks == null) throw new AnalysisException("Invalid input parameter : tasks can't be null"); //$NON-NLS-1$

		final boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0; //$NON-NLS-1$

		if (sort) {
			/**
			 * Sort by AnalysisTasksProvider.priority -> AnalysisTasksProvider -> task.priority -> task.name
			 */
			tasks = new ArrayList<AbstractTask>(tasks);
			Collections.sort(tasks);
		}

		boolean returnValue = true;
		try {
			if (tasks != null && tasks.size() > 0) {
				int tickets = 100;
				int checkTiket = tickets / tasks.size();

				monitor.beginTask("Starting Analysis", tickets); //$NON-NLS-1$

				final Set<ITasksGroup> tasksGroupToSkip = new HashSet<ITasksGroup>();
				boolean skipAll = false;
				for (int i = 0; i < tasks.size(); i++) {
					AbstractTask currentTask = tasks.get(i);
					monitor.setTaskName(currentTask.getGroup().getGroupName());
					monitor.subTask(currentTask.getName());
					if (tasksGroupToSkip.contains(currentTask.getGroup()) || skipAll || monitor.isCanceled()) {
						fireTaskSkipped(currentTask);
						Thread.sleep(40);
					} else {
						List<IResult> analysisResults = new ArrayList<IResult>();
						DiagramAnalyserThread t = new DiagramAnalyserThread(this, currentTask, diagram, analysisResults);
						t.start();
						fireTaskStarted(currentTask);

						int timeout = currentTask.getTimeOut();
						long time = System.currentTimeMillis();
						InterruptionType intType = null;

						while (!t.isTerminated()) {
							if (monitor.isCanceled()) {
								intType = InterruptionType.MONITOR_CANCELLED;
							} else if (timeout > 0 && System.currentTimeMillis() - time > timeout) {
								if (!isDebug) {
									intType = InterruptionType.TIMEOUT;
								}
							}
							if (intType != null) {
								t.stop();
								returnValue = false;
							} else {
								Thread.sleep(1);
							}
						}

						if (intType == null && !t.isTerminatedNormally()) {
							intType = InterruptionType.EXCEPTION;
							String logString = "Exception occured during analysis - Task: \"" + currentTask.getName() + "\" : " + t.getException().getMessage(); //$NON-NLS-1$ //$NON-NLS-2$
							Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, logString, t.getException()));
							returnValue = false;
						}
						if (intType != null) {
							if (intType == InterruptionType.EXCEPTION) {
								fireTaskInterrupted(currentTask, intType, t.getException());
							} else {
								fireTaskInterrupted(currentTask, intType, null);
							}
							if (intType != InterruptionType.MONITOR_CANCELLED) {
								switch (currentTask.getBlockType()) {
									case CLASS:
										tasksGroupToSkip.add(currentTask.getGroup());
									break;
									case ANALYSIS:
										monitor.setCanceled(true);
									break;
								}
							}
						} else {
							if (t.getResult() == TaskResult.COMPLETED_ERROR) {
								returnValue = false;
								switch (currentTask.getBlockType()) {
									case CLASS:
										tasksGroupToSkip.add(currentTask.getGroup());
									break;
									case ANALYSIS:
										skipAll = true;
									break;
								}
							} else {
								resultMap.put(currentTask, analysisResults);
							}
						}
					}
					monitor.worked(checkTiket);
				}
			}
		} catch (Exception e) {
			throw new AnalysisException("Exception occurred while performing task :" + e.getMessage(), e); //$NON-NLS-1$
		} finally {
			if (monitor != null) monitor.done();
		}
		return returnValue;
	}

	public boolean evaluateCurrentDiagram() throws AnalysisException{
		return evaluateCurrentDiagram(null, null);
	}

	public boolean evaluateCurrentDiagram(Map<ITasksGroup, List<IResult>> resultsMap) throws AnalysisException{
		return evaluateCurrentDiagram(resultsMap, null);
	}

	private StsToolDiagram	diagram;

	@SuppressWarnings("static-access")
	public boolean evaluateCurrentDiagram(Map<ITasksGroup, List<IResult>> resultsMap,IProgressMonitor monitor)
			throws AnalysisException{
		if (monitor == null) monitor = new NullProgressMonitor();

		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench.getDisplay().getCurrent() == null) {
			workbench.getDisplay().syncExec(new Runnable() {

				@Override
				public void run(){
					IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if (editor instanceof StsToolDiagramEditor) diagram = ((StsToolDiagramEditor) editor).getStsModel();
				}
			});
		} else {
			IEditorPart editor = workbench.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editor instanceof StsToolDiagramEditor) diagram = ((StsToolDiagramEditor) editor).getStsModel();
		}

		if (diagram == null) { throw new AnalysisException("No active diagram found"); } //$NON-NLS-1$
		return evaluateDiagram(diagram, resultsMap, monitor);
	}

	public boolean evaluateDiagram(StsToolDiagram diagram,Map<ITasksGroup, List<IResult>> resultsMap,IProgressMonitor monitor){
		try {
			Map<AbstractTask, List<IResult>> res = new HashMap<AbstractTask, List<IResult>>();
			boolean result = performTasks(getAllTask(), diagram, true, res, monitor);
			if (resultsMap != null) {
				for (AbstractTask task : res.keySet()) {
					if (resultsMap.containsKey(task.getGroup())) {
						List<IResult> x = resultsMap.get(task.getGroup());
						x.addAll(res.get(task));
						resultsMap.put(task.getGroup(), x);
					} else {
						resultsMap.put(task.getGroup(), res.get(task));
					}
				}
			}
			return result;
		} catch (Exception e) {
			throw new AnalysisException("Error encountred during analysis :" + e.getMessage(), e); //$NON-NLS-1$
		} finally {
			if (monitor != null) monitor.done();
		}
	}

	public void addTaskListener(ITaskListener listener){
		listeners.add(listener);
	}

	public void removeTaskListener(ITaskListener listener){
		listeners.remove(listener);
	}

	protected void fireTaskStarted(AbstractTask task){
		for (ITaskListener l : listeners) {
			l.taskStarted(task);
		}
	}

	protected void fireTaskCompelte(AbstractTask task,TaskResult taskResult,List<IResult> analysisResult){
		for (ITaskListener l : listeners) {
			l.taskCompleted(task, taskResult, analysisResult);
		}
	}

	protected void fireTaskSkipped(AbstractTask task){
		for (ITaskListener l : listeners) {
			l.taskSkipped(task);
		}
	}

	protected void fireTaskInterrupted(AbstractTask task,InterruptionType userInterrupt,Throwable ex){
		for (ITaskListener l : listeners) {
			l.taskInterrupted(task, userInterrupt, ex);
		}
	}

}
