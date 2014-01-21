/*
* EventContextMenuAction.java
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
package eu.aniketos.wp1.ststool.analysis.risk.actions;

import static eu.aniketos.wp1.ststool.analysis.risk.Messages.*;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.analysis.dialog.AnalysisDialog;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.analysis.risk.Messages;
import eu.aniketos.wp1.ststool.analysis.risk.tasks.RiskAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.diagram.extensionpoint.IContextMenuAction;

public class EventContextMenuAction extends Action implements IContextMenuAction{

	private final static String RISK_CATEGORY = getMessage(RiskCategoryName);
	private Event event=null;
	
	
	public EventContextMenuAction(){
		super(Messages.getMessage(Messages.ContextMenuActionName));
		setEnabled(false);
	}
	
	@Override
	public void setSelectedElement(EObject obj) {
		if(obj instanceof Event){
			setEnabled(true);
			event=(Event)obj;
		}else{
			event=null;
			setEnabled(false);
		}
	}

	@Override
	public void run() {
		if(event!=null){
		List<ITasksGroup> atpList = new ArrayList<ITasksGroup>();
		atpList.add(new RiskAnalysisTasks(RISK_CATEGORY, 0,event));

		ResultsManager rm = ResultsManager.getInstance();
		rm.cleanCategory(RISK_CATEGORY);
		Shell s=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		AnalysisDialog secDialog = new AnalysisDialog(s, atpList, AnalysisWindowTitle,
				AnalysisWindowSubtitle, false,true);
		secDialog.setAutoStartAndClose(true);
		secDialog.open();
		}
	}
	
	

}
