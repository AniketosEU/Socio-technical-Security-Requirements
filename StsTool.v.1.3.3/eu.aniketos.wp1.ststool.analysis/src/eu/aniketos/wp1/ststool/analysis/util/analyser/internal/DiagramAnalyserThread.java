/*
* DiagramAnalyserThread.java
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
package eu.aniketos.wp1.ststool.analysis.util.analyser.internal;

import java.lang.reflect.Method;
import java.util.List;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.DiagramAnalyser;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;


public class DiagramAnalyserThread {

	private DiagramAnalyser	da;
	private AbstractTask		task;
	private StsToolDiagram	diagram;
	private List<IResult>	analysisResults;
	private TaskResult		result					= null;

	private Thread				thread					= null;

	private Throwable			ex							= null;
	private boolean			terminatedNormally	= false;

	public DiagramAnalyserThread(DiagramAnalyser da, AbstractTask task, StsToolDiagram diagram, List<IResult> analysisResults) {
		super();
		this.da = da;
		this.task = task;
		this.diagram = diagram;
		this.analysisResults = analysisResults;
	}

	public boolean isTerminated(){
		if (thread == null) return true;
		return thread.getState().equals(Thread.State.TERMINATED);
	}

	public boolean isTerminatedNormally(){
		return terminatedNormally;
	}

	public Throwable getException(){
		return ex;
	}

	public TaskResult getResult(){
		return result;
	}

	public synchronized void start(){
		thread = new Thread(new Runnable() {

			@Override
			public void run(){
				try {
					Method m = DiagramAnalyser.class.getDeclaredMethod("performTask", AbstractTask.class, StsToolDiagram.class, List.class, boolean.class); //$NON-NLS-1$
					m.setAccessible(true);
					result = (TaskResult) m.invoke(da, task, diagram, analysisResults, true);
					m.setAccessible(false);

				} catch (Throwable e) {
					ex = e;
					/*ex=e.getCause();
					if(ex.getCause()!=null)ex=ex.getCause();
					
					List<StackTraceElement> stackTraceList=new ArrayList<StackTraceElement>();

					boolean stop=false;
					if(ex.getClass().equals(java.lang.StackOverflowError.class)){
						StackTraceElement previous=null;
						for(int i=0; i<ex.getStackTrace().length && !stop; i++){
							if(previous!=null && previous.equals(ex.getStackTrace()[i])){
								stop=true;
							}
							stackTraceList.add(ex.getStackTrace()[i]);
							previous=ex.getStackTrace()[i];
						}
					}else{
						for(int i=0; i<ex.getStackTrace().length && !stop; i++){
							if(ex.getStackTrace()[i].getClassName().equalsIgnoreCase("sun.reflect.NativeMethodAccessorImpl")){ //$NON-NLS-1$
								stop=true;
							}else{
								stackTraceList.add(ex.getStackTrace()[i]);
							}
						}
					}
					
					
					StackTraceElement[] stackTraceArray=new StackTraceElement[stackTraceList.size()];
					for(int i=0;i<stackTraceArray.length;i++)stackTraceArray[i]=stackTraceList.get(i);
					
					ex.setStackTrace(stackTraceArray);*/
					terminatedNormally = false;
					return;
				}
				terminatedNormally = true;
			}
		});
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public void stop(){
		if (thread != null) {
			thread.stop();
			terminatedNormally = false;
		}
	}


}
