/*
* AnalysisDialog.java
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
package eu.aniketos.wp1.ststool.analysis.dialog;

import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_CancelledByUser;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_Error;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_Exception;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_No_Error;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_NotImplemented;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_Skipped;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_TimeOut;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_Warning;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_btn_Cancel;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_btn_Done;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_btn_Exit;
import static eu.aniketos.wp1.ststool.analysis.Messages.AnalysisDialog_btn_StartAnalysis;
import static eu.aniketos.wp1.ststool.analysis.Messages.getMessage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.analysis.ImageManager;
import eu.aniketos.wp1.ststool.analysis.SWTResourceManager;
import eu.aniketos.wp1.ststool.analysis.dialog.internal.ConsoleForResultsTree;
import eu.aniketos.wp1.ststool.analysis.dialog.internal.ProgressMonitor;
import eu.aniketos.wp1.ststool.analysis.internal.DiagramObserver;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.analysis.ui.ResultView;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.DiagramAnalyser;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITaskListener;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.analysis.util.analyser.TaskAdapter;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;


public class AnalysisDialog extends Dialog {

	private Map<ITasksGroup, List<IResult>>	result;
	String												shellTitle;
	String												consoleTitle;

	private Composite									consoleComposite;
	private ProgressBar								progressIndicator;
	private Label										progressLabel;


	//private ConsoleForResults									console								= new ConsoleForResults();
	private ConsoleForResultsTree					console								= new ConsoleForResultsTree();
	private ProgressMonitor							monitor;
	private List<ITasksGroup>						tasksGroups;


	private boolean									analysisStarted					= false;
	private boolean									analysisResult						= false;

	private boolean									groupByAnalysisTasksProvider	= false;
	private boolean									autoupdateAnalysisView			= true;
	private boolean									autoStartAndClose					= false;

	private Color										LIGHT_RED;
	private Color										LIGHT_GRREN;
	private boolean									clearPrevoiousResult;
	private boolean markResults	=false;
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public AnalysisDialog(Shell parent, List<ITasksGroup> tasksGroups, String shellTitle, String consoleTitle, boolean clearPrevoiousResult,boolean markResults) {
		super(parent);
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		this.tasksGroups = tasksGroups;
		this.shellTitle = shellTitle;
		this.consoleTitle = consoleTitle;
		this.clearPrevoiousResult = clearPrevoiousResult;
		this.markResults = markResults;
	}

	@Override
	protected void configureShell(Shell shell){
		super.configureShell(shell);
		if (shellTitle != null) shell.setText(shellTitle);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent){
		final Composite composite = (Composite) super.createDialogArea(parent);
		if (LIGHT_RED == null) LIGHT_RED = new Color(parent.getDisplay(), 255, 204, 204);
		if (LIGHT_GRREN == null) LIGHT_GRREN = new Color(parent.getDisplay(), 204, 255, 204);

		composite.addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e){
				if (LIGHT_RED != null) {
					LIGHT_RED.dispose();
					LIGHT_RED = null;
				}
				if (LIGHT_GRREN != null) {
					LIGHT_GRREN.dispose();
					LIGHT_GRREN = null;
				}
				composite.removeDisposeListener(this);
			}
		});

		composite.setLayout(new FormLayout());

		Label lblNewLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 10);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		if (consoleTitle != null) lblNewLabel.setText(consoleTitle);

		progressIndicator = new ProgressBar(composite, SWT.NONE);
		FormData fd_progressBar = new FormData();
		fd_progressBar.right = new FormAttachment(100, -10);
		fd_progressBar.bottom = new FormAttachment(100, -10);
		fd_progressBar.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		progressIndicator.setLayoutData(fd_progressBar);


		progressLabel = new Label(composite, SWT.NONE);
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.bottom = new FormAttachment(progressIndicator, -4);
		fd_lblNewLabel_2.left = new FormAttachment(lblNewLabel, 0, SWT.LEFT);
		fd_lblNewLabel_2.right = new FormAttachment(100, -10);
		progressLabel.setLayoutData(fd_lblNewLabel_2);


		consoleComposite = console.getComposite(composite);
		FormData fd_tree = new FormData();
		fd_tree.top = new FormAttachment(lblNewLabel, 10);
		fd_tree.bottom = new FormAttachment(progressLabel, -10);
		fd_tree.right = new FormAttachment(100, -10);
		fd_tree.left = new FormAttachment(0, 10);
		consoleComposite.setLayoutData(fd_tree);

		monitor = new ProgressMonitor(getShell().getDisplay(), progressIndicator, progressLabel);
		return composite;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent){
		createButton(parent, IDialogConstants.OK_ID, getMessage(AnalysisDialog_btn_StartAnalysis), true);
		createButton(parent, IDialogConstants.CANCEL_ID, getMessage(AnalysisDialog_btn_Exit), false);
	}



	@Override
	public int open(){
		analysisStarted = false;
		Class c = this.getClass();

		while (!c.equals(Window.class)) {
			c = c.getSuperclass();
		}


		if (getShell() == null || getShell().isDisposed()) {
			//Hacking cause shell field in Window class is private and there is no setShell()
			try {
				Field fshell = c.getDeclaredField("shell"); //$NON-NLS-1$
				if (!fshell.isAccessible()) {
					fshell.setAccessible(true);
					fshell.set(this, null);
					fshell.setAccessible(false);
				} else {
					fshell.set(this, null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			create();
		}

		// limit the shell size to the display size
		constrainShellSize();

		// open the window
		getShell().open();
		if (autoStartAndClose) {
			//getButton(CANCEL).setEnabled(false);
			startAnalysis();
		}


		try {
			//Hacking cause runEventLoop method in Window class is private
			Method m = c.getDeclaredMethod("runEventLoop", Shell.class); //$NON-NLS-1$
			if (!m.isAccessible()) {
				m.setAccessible(true);
				m.invoke(this, getShell());
				m.setAccessible(false);
			} else {
				m.invoke(this, getShell());
			}
		} catch (Exception e) {

		}

		return getReturnCode();
	}



	@Override
	protected void buttonPressed(int buttonId){
		switch (buttonId) {
			case IDialogConstants.OK_ID:
				startAnalysis();
			break;
			case IDialogConstants.CANCEL_ID:
				if (analysisStarted) {
					monitor.setCanceled(true);
				} else {
					performClose(analysisResult);
				}
			break;
		}
	}

	protected void performClose(boolean analysisResult){
		if (analysisResult) {
			setReturnCode(OK);
			if (autoupdateAnalysisView) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ResultView.ID);
				} catch (PartInitException ex) {
					ex.printStackTrace();
				}
			}
		} else {
			setReturnCode(CANCEL);
		}
		close();
		Display.getCurrent().syncExec(new Runnable() {

			@Override
			public void run(){
				if (DiagramObserver.getEditor() != null) DiagramObserver.getEditor().refresh();
			}
		});
	}

	protected void startAnalysis(){
		startAnalysis(true);
	}

	protected void startAnalysis(boolean clearMarker){

		getButton(OK).setEnabled(false);
		getButton(CANCEL).setText(getMessage(AnalysisDialog_btn_Cancel));
		analysisStarted = true;
		console.clear();

		if (autoupdateAnalysisView) {
			ResultsManager rm = ResultsManager.getInstance();
			if (clearPrevoiousResult) {
				rm.cleanCategoriesAndResults();
			}
			for (ITasksGroup tasksGroup : tasksGroups) {
				if (!rm.containCategory(tasksGroup.getGroupName())) {
					rm.addCategory(tasksGroup.getGroupName(), tasksGroup.getGroupName(), tasksGroup.getGroupPriority());
				} else {
					rm.cleanCategory(tasksGroup.getGroupName());
				}
			}
		}

		new Thread(new Runnable() {

			@Override
			public void run(){
				try {
					ITaskListener listener = new TaskAdapter() {

						ITasksGroup	lastGroup	= null;
						String		lastName		= null;

						@Override
						public void taskStarted(final AbstractTask task){
							executeAsyncRunnable(new Runnable() {

								@Override
								public void run(){
									if (groupByAnalysisTasksProvider) {
										if (lastGroup != task.getGroup()) {
											lastGroup = task.getGroup();
											console.addLine(task.getGroup().getGroupName());
										}
									} else {
										if (lastName != task.getGroup().getGroupName()) {
											lastName = task.getGroup().getGroupName();
											console.addLine(task.getGroup().getGroupName());
										}
									}
								}
							});
						}


						@Override
						public void taskCompleted(final AbstractTask task,final TaskResult taskResult,final List<IResult> analysisResults){
							executeAsyncRunnable(new Runnable() {

								@Override
								public void run(){
									switch (taskResult) {
										case COMPLETED_OK:
											console.addSubLine(ResultType.OK, task.getName(), getMessage(AnalysisDialog_No_Error), null, null);
										break;
										case COMPLETED_WARNING:
											console.addSubLine(ResultType.WARNING, task.getName(), getMessage(AnalysisDialog_Warning), null, new Color(null, 255, 153, 0));
										break;
										case COMPLETED_ERROR:
											console.addSubLine(ResultType.ERROR, task.getName(), getMessage(AnalysisDialog_Error), null, ColorConstants.red);
										break;
										case NOT_IMPLEMENTED:
											console.addSubLine(task.getName(), getMessage(AnalysisDialog_NotImplemented), ColorConstants.gray, ColorConstants.gray);
										break;
									}
									if (autoupdateAnalysisView) {
										ResultsManager.getInstance().addAllResults(analysisResults, task.getGroup().getGroupName(),markResults);
									}
								}
							});
						}

						@Override
						public void taskSkipped(final AbstractTask task){
							executeAsyncRunnable(new Runnable() {

								@Override
								public void run(){
									if (groupByAnalysisTasksProvider) {
										if (lastGroup != task.getGroup()) {
											lastGroup = task.getGroup();
											console.addLine(task.getGroup().getGroupName()); //$NON-NLS-1$
										}
									} else {
										if (lastName != task.getGroup().getGroupName()) {
											lastName = task.getGroup().getGroupName();
											console.addLine(task.getGroup().getGroupName()); //$NON-NLS-1$
										}
									}
									console.addSubLine(ImageManager.getImage(ImageManager.UNKNOWN_B_N_ICON), task.getName(), getMessage(AnalysisDialog_Skipped), ColorConstants.gray, ColorConstants.gray);
								}
							}

							);
						}


						@Override
						public void taskInterrupted(final AbstractTask task,final InterruptionType interruptionType,final Throwable ex){
							executeAsyncRunnable(new Runnable() {

								@Override
								public void run(){
									switch (interruptionType) {
										case EXCEPTION:
											console.addSubLine(ImageManager.getImage(ImageManager.ERROR_ICON), task.getName(), getMessage(AnalysisDialog_Exception), null, SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
											if (autoupdateAnalysisView) {
												//ResultsManager.getInstance().addAllResults(new As, atp.getName());
											}
										break;
										case TIMEOUT:
											console.addSubLine(ImageManager.getImage(ImageManager.ERROR_ICON), task.getName(), getMessage(AnalysisDialog_TimeOut), null, SWTResourceManager.getColor(SWT.COLOR_DARK_MAGENTA));
										break;
										case MONITOR_CANCELLED:
											console.addSubLine(ImageManager.getImage(ImageManager.ERROR_ICON), task.getName(), getMessage(AnalysisDialog_CancelledByUser), null, ColorConstants.red);
											if (autoupdateAnalysisView) {
												ResultsManager.getInstance().cleanCategoriesAndResults();
											}
										break;
									}
								}
							});
						}

					};

					DiagramAnalyser diagramAnalyser = new DiagramAnalyser();



					diagramAnalyser.addTaskListener(listener);
					try {
						Map<ITasksGroup, List<IResult>> res = new HashMap<ITasksGroup, List<IResult>>();

						Collections.sort(tasksGroups, new Comparator<ITasksGroup>() {

							@Override
							public int compare(ITasksGroup o1,ITasksGroup o2){
								int x = o1.getGroupPriority() - o2.getGroupPriority();
								if (x == 0) x = o1.getGroupName().compareTo(o2.getGroupName());
								return x;
							}
						});

						executeSyncRunnable(new Runnable() {

							@Override
							public void run(){
								getShell().setCursor(new Cursor(null, SWT.CURSOR_APPSTARTING));
							}
						});

						boolean done = false;
						for (int i = 0; i < tasksGroups.size() && !done; i++) {
							ITasksGroup taskGroup = tasksGroups.get(i);
							diagramAnalyser.removeAllTaskGroups();
							diagramAnalyser.addTaskGroup(taskGroup);
							analysisResult = diagramAnalyser.evaluateCurrentDiagram(res, monitor);
							if (i + 1 < tasksGroups.size()) {
								if (!shouldProceedOnNextAnalysis(tasksGroups.get(i + 1), taskGroup, analysisResult)) done = true;
							}
						}
						executeSyncRunnable(new Runnable() {

							@Override
							public void run(){
								consoleComposite.setCursor(new Cursor(null, SWT.CURSOR_ARROW));
							}
						});
						result = res;
					} catch (Exception e) {
					} finally {
						diagramAnalyser.removeTaskListener(listener);
					}

				} finally {
					executeSyncRunnable(new Runnable() {

						@Override
						public void run(){
							if (result == null) {
								getButton(CANCEL).setText(getMessage(AnalysisDialog_btn_Exit));
							} else {
								getButton(CANCEL).setText(getMessage(AnalysisDialog_btn_Done));
							}
							analysisStarted = false;
							if (autoStartAndClose) {
								performClose(analysisResult);
							}
						}

					});
					if (monitor != null) monitor.done();
				}
			}
		}).start();
	}

	protected boolean shouldProceedOnNextAnalysis(ITasksGroup nextTaskGroup,ITasksGroup currentTaskGroup,boolean currentAnalysisResult){
		return currentAnalysisResult;
	}

	public void setAutoUpdateAnalysisView(boolean autoupdate){
		if (analysisStarted) return;
		this.autoupdateAnalysisView = autoupdate;
	}

	public void setGroupByAnalysisTasksProvider(boolean groupByAnalysisTasksProvider){
		if (analysisStarted) return;
		this.groupByAnalysisTasksProvider = groupByAnalysisTasksProvider;
	}

	public void setAutoStartAndClose(boolean autoStartAndClose){
		if (analysisStarted) return;
		this.autoStartAndClose = autoStartAndClose;
	}

	private void executeSyncRunnable(Runnable r){
		getShell().getDisplay().syncExec(r);
	}

	private void executeAsyncRunnable(Runnable r){
		getShell().getDisplay().asyncExec(r);
	}

	public Map<ITasksGroup, List<IResult>> getResult(){
		if (getReturnCode() == OK) {
			return result;
		} else
			return null;
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize(){
		return new Point(550, 500);
	}

}
