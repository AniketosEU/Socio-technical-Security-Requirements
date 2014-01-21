/*
* SecurityAnalysisHandler.java
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
package eu.aniketos.wp1.ststool.analysis.security.handlers;

import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NoErrorsDesc;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.Result_NoErrorsText;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.SecurityCategoryName;
import static eu.aniketos.wp1.ststool.analysis.security.Messages.getMessage;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import eu.aniketos.wp1.ststool.analysis.consistency.tasks.ConsistencyAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.dialog.AnalysisDialog;
import eu.aniketos.wp1.ststool.analysis.results.AbstarctResult;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.analysis.security.Messages;
import eu.aniketos.wp1.ststool.analysis.security.tasks.SecurityAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.util.analyser.ITasksGroup;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;


public class SecurityAnalysisHandler extends AbstractHandler {

	private final static String	SECURITY_CATEGORY	= getMessage(SecurityCategoryName);

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException{

		final ResultsManager rm = ResultsManager.getInstance();

		List<ITasksGroup> atpList = new ArrayList<ITasksGroup>();
		atpList.add(new SecurityAnalysisTasks(SECURITY_CATEGORY, 2));
		AnalysisDialog secDialog = new AnalysisDialog(HandlerUtil.getActiveShell(event), atpList, Messages.AnalysisWindowTitle, Messages.AnalysisWindowSubtitle, false,false) {

			@Override
			protected void startAnalysis(){
				//ResultsManager.getInstance().cleanCategoriesAndResults();
				List<ITasksGroup> atpList = new ArrayList<ITasksGroup>();
				atpList.add(new ConsistencyAnalysisTasks(1));
				final Shell secDialogShell = getShell();
				AnalysisDialog dialog = new AnalysisDialog(getShell(), atpList, Messages.AnalysisWindowTitle, Messages.AnalysisWindowSubtitle, true,false) {

					@Override
					protected Point getInitialLocation(Point initialSize){
						Point p = secDialogShell.getLocation();
						p.x += 10;
						p.y += 10;
						return p;
					}

				};
				dialog.setAutoStartAndClose(true);
				if (dialog.open() == IDialogConstants.OK_ID) {
					super.startAnalysis(false);
				} else {
					MessageDialog.openWarning(secDialogShell, "Warning", Messages.MissingSecurityPrerequisite_text);
					super.performClose(false);
				}
			}
		};
		if (secDialog.open() == IDialogConstants.OK_ID) {
			if (rm.containCategory(SECURITY_CATEGORY) && rm.getAllResultsInCategory(SECURITY_CATEGORY).size() == 0) {
				rm.addResult(new TestResults(getMessage(Result_NoErrorsText), getMessage(Result_NoErrorsDesc), ResultType.OK), SECURITY_CATEGORY,true);
			}
		}
		return null;
	}

	class TestResults extends AbstarctResult {

		public TestResults(String text, String description, ResultType type) {
			super(text, description, type, ViewsManager.EMPTY_VIEW);
		}
	}
}
