/*
* ProgressMonitor.java
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
package eu.aniketos.wp1.ststool.analysis.dialog.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;


public class ProgressMonitor implements IProgressMonitor {

	private ProgressBar			progressIndicator;
	private Label					progressLabel;
	private Display				display;

	private volatile boolean	fIsCanceled;

	int								workTotal;
	int								worked;
	String							task;
	String							subtask;



	public ProgressMonitor(Display display, ProgressBar progressIndicator, Label progressLabel) {
		super();
		this.progressIndicator = progressIndicator;
		this.progressLabel = progressLabel;
		this.display = display;
	}

	public void beginTask(String name,int totalWork){

		this.workTotal = totalWork;
		setMaximum(workTotal);
		setTaskName(name);
	}

	public void done(){
		worked = 0;
		setSelection(worked);
		task = null;
		subtask = null;
		computeLabelText();
	}

	public void setTaskName(String name){
		task = name;
		computeLabelText();
	}

	public boolean isCanceled(){
		return fIsCanceled;
	}

	public void setCanceled(boolean b){
		fIsCanceled = b;
	}

	public void subTask(String name){
		subtask = name;
		computeLabelText();
	}

	public void worked(int work){
		worked += work;
		setSelection(worked);
	}

	@Override
	public void internalWorked(double work){
	}


	private void setSelection(final int selection){
		if (progressIndicator != null && !progressIndicator.isDisposed()) {
			executeAsyncRunnable(new Runnable() {

				@Override
				public void run(){
					progressIndicator.setSelection(selection);
				}
			});
		}
	}

	private void setMaximum(final int maximum){
		if (progressIndicator != null && !progressIndicator.isDisposed()) {
			executeAsyncRunnable(new Runnable() {

				@Override
				public void run(){
					progressIndicator.setMaximum(maximum);
				}
			});
		}
	}

	private void setText(final String text){
		if (progressLabel != null && !progressLabel.isDisposed()) {
			executeAsyncRunnable(new Runnable() {

				@Override
				public void run(){
					progressLabel.setText(text);
				}
			});
		}
	}

	private void computeLabelText(){
		String text = ""; //$NON-NLS-1$
		if (task != null) {
			if (subtask != null) {
				text = task + ": " + subtask; //$NON-NLS-1$
			} else {
				text = task;
			}
		}
		setText(text);
	}

	private void executeAsyncRunnable(Runnable r){
		display.asyncExec(r);
	}
}
