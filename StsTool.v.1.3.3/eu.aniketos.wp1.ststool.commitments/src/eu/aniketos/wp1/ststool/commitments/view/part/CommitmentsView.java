/*
* CommitmentsView.java
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
package eu.aniketos.wp1.ststool.commitments.view.part;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.commitments.actions.CommitmentsFollowSelectionAction;
import eu.aniketos.wp1.ststool.commitments.actions.CommitmentsRemoveAllFilterAction;
import eu.aniketos.wp1.ststool.commitments.actions.CommitmentsRequesterFilterAction;
import eu.aniketos.wp1.ststool.commitments.actions.CommitmentsRequirementFilterAction;
import eu.aniketos.wp1.ststool.commitments.actions.CommitmentsResponsibleFilterAction;
import eu.aniketos.wp1.ststool.commitments.actions.ExportSRSAction;
import eu.aniketos.wp1.ststool.commitments.actions.FilterDropDownAction;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;
import eu.aniketos.wp1.ststool.commitments.manager.CommitmentManager;
import eu.aniketos.wp1.ststool.commitments.manager.ICommitmentListener;


public class CommitmentsView extends ViewPart implements ISelectionListener, ICommitmentListener {


	private CommitmentTable								table;
	private Text											descriptionArea;


	private CommitmentsResponsibleFilterAction	commitmentsDebtorFilterAction;
	private CommitmentsRequesterFilterAction		commitmentsCreditorFilterAction;
	private CommitmentsRequirementFilterAction	commitmentsReqTypeFilterAction;
	private FilterDropDownAction						filterDropDownAction;
	private CommitmentsRemoveAllFilterAction		commitmentsRemoveAllFilterAction;
	private ExportSRSAction								exportSRSAction;
	/**
	 * a flag indicating if the view listen for selection changes
	 */
	private boolean										followSelection;



	/**
	 * This is a callback that will allow us to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(Composite parent){


		SashForm sashForm = new SashForm(parent, SWT.BORDER | SWT.VERTICAL);
		sashForm.setSashWidth(1);

		Composite tableComposite = new Composite(sashForm, SWT.NONE);
		tableComposite.setLayout(new FillLayout());
		table = new CommitmentTable(tableComposite, SWT.SINGLE | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL);
		table.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event){
				if (event.getSelection() instanceof StructuredSelection) {
					StructuredSelection s = (StructuredSelection) event.getSelection();
					if (s.getFirstElement() instanceof ICommitment) {
						ICommitment c = (ICommitment) s.getFirstElement();
						descriptionArea.setText(c.getDescritption());
					}
				}
			}
		});
		{

			Composite composite_1 = new Composite(sashForm, SWT.NONE);
			composite_1.setLayout(new FormLayout());

			Label lblDescription = new Label(composite_1, SWT.NONE);
			FormData fd_lblDescription = new FormData();
			fd_lblDescription.right = new FormAttachment(100, -10);
			fd_lblDescription.top = new FormAttachment(0, 10);
			fd_lblDescription.left = new FormAttachment(0, 10);
			lblDescription.setLayoutData(fd_lblDescription);
			lblDescription.setText("Description:");

			Composite composite_2 = new Composite(composite_1, SWT.NONE);
			FormData fd_composite_2 = new FormData();
			fd_composite_2.bottom = new FormAttachment(100, -10);
			fd_composite_2.right = new FormAttachment(100, -10);
			fd_composite_2.top = new FormAttachment(lblDescription, 6);
			fd_composite_2.left = new FormAttachment(0, 10);
			composite_2.setLayoutData(fd_composite_2);

			composite_2.setLayout(new FormLayout());
			composite_2.addPaintListener(new BorderPainter());

			descriptionArea = new Text(composite_2, SWT.NONE);
			descriptionArea.setBackground(descriptionArea.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
			FormData fd_text = new FormData();
			int x = 3;
			fd_text.bottom = new FormAttachment(100, -x);
			fd_text.right = new FormAttachment(100, -x);
			fd_text.top = new FormAttachment(0, x);
			fd_text.left = new FormAttachment(0, x);
			descriptionArea.setLayoutData(fd_text);
			descriptionArea.setEditable(false);
		}
		sashForm.setWeights(new int[] { 1, 1 });



		createViewPulldownMenu();
		CommitmentManager cm = CommitmentManager.getManager();
		cm.addCommitmentListener(this);
	}

	@Override
	public void init(IViewSite site) throws PartInitException{

		super.init(site);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
	}

	@Override
	public void dispose(){

		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		CommitmentManager cm = CommitmentManager.getManager();
		cm.removeCommitmentListener(this);
		super.dispose();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus(){
		table.getControl().setFocus();
	}

	private void createViewPulldownMenu(){

		commitmentsDebtorFilterAction = new CommitmentsResponsibleFilterAction(this, "Responsible");
		commitmentsReqTypeFilterAction = new CommitmentsRequirementFilterAction(this, "Requirement");
		commitmentsCreditorFilterAction = new CommitmentsRequesterFilterAction(this, "Requester");

		exportSRSAction = new ExportSRSAction(this);

		IToolBarManager menu = getViewSite().getActionBars().getToolBarManager();
		menu.add(exportSRSAction);
		menu.add(new CommitmentsFollowSelectionAction(this));
		filterDropDownAction = new FilterDropDownAction();
		filterDropDownAction.add(commitmentsDebtorFilterAction);
		filterDropDownAction.add(commitmentsReqTypeFilterAction);
		filterDropDownAction.add(commitmentsCreditorFilterAction);
		menu.add(filterDropDownAction);
		commitmentsRemoveAllFilterAction = new CommitmentsRemoveAllFilterAction(this, "Remove all Filters");
		menu.add(commitmentsRemoveAllFilterAction);

	}

	@Override
	public void selectionChanged(IWorkbenchPart part,ISelection selection){

		if (!followSelection) return;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) selection;
			List<String> selectionPattern = new ArrayList<String>();
			for (Object o : s.toArray()) {
				Actor a = resolveElement(o);
				if (a != null) selectionPattern.add(a.getName());
			}
			if (selectionPattern.size() > 0)
				setActorsFilter(selectionPattern);
			else if (getActorsFilter() != null) setActorsFilter(null);

		}
	}

	private Actor resolveElement(Object e){

		if (e == null) return null;
		if (e instanceof IGraphicalEditPart && ((IGraphicalEditPart) e).getPrimaryView() != null) {
			EObject eo = ((IGraphicalEditPart) e).getPrimaryView().getElement();
			if (eo instanceof Actor) { return (Actor) eo; }
		}
		return null;
	}

	public void followSelection(boolean follow){

		this.followSelection = follow;
		if (follow) {
			filterDropDownAction.setEnabled(false);
			commitmentsRemoveAllFilterAction.setEnabled(false);
		} else {
			filterDropDownAction.setEnabled(true);
			commitmentsRemoveAllFilterAction.setEnabled(true);
			//CommitmentManager cm=CommitmentManager.getManager();
			//table.setInput(cm.getAllCommitments().toArray());
		}
		removeAllFilter();
	}

	@Override
	public void refreshCommitments(){
		table.refresh();
	}

	@Override
	public void commitmentsChanged(final List<ICommitment> commitments){
		table.setInput(commitments);
		descriptionArea.setText("");
		if (exportSRSAction.isEnabled() && CommitmentManager.getManager().getDiagram() == null)
			exportSRSAction.setEnabled(false);
		else if (!exportSRSAction.isEnabled() && CommitmentManager.getManager().getDiagram() != null) exportSRSAction.setEnabled(true);
	}

	//--------------------------FILTER OPERATIONS-------------------------

	public void removeAllFilter(){

		setDebtorFilter(null);
		setCreditorFilter(null);
		setReqTypeFilter(null);
		setActorsFilter(null);
	}

	public void setDebtorFilter(String pattern){

		if (pattern == null || pattern.equals("")) {
			commitmentsDebtorFilterAction.setChecked(false);
		} else
			commitmentsDebtorFilterAction.setChecked(true);
		table.setFilter(CommitmentTable.FILTER_DEBTOR, pattern);
	}

	public void setCreditorFilter(String pattern){

		if (pattern == null || pattern.equals(""))
			commitmentsCreditorFilterAction.setChecked(false);
		else
			commitmentsCreditorFilterAction.setChecked(true);
		table.setFilter(CommitmentTable.FILTER_CREDITOR, pattern);
	}

	public void setReqTypeFilter(String pattern){

		if (pattern == null || pattern.equals(""))
			commitmentsReqTypeFilterAction.setChecked(false);
		else
			commitmentsReqTypeFilterAction.setChecked(true);
		table.setFilter(CommitmentTable.FILTER_REQTYPE, pattern);
	}

	public void setActorsFilter(List<String> pattern){

		table.setFilter(CommitmentTable.FILTER_ACTORS, pattern);
	}

	public String getDebtorFilterPattern(){

		return table.getFilterPattern(CommitmentTable.FILTER_DEBTOR).toString();
	}

	public String getCreditorFilterPattern(){

		return table.getFilterPattern(CommitmentTable.FILTER_CREDITOR).toString();
	}

	public String getReqTypeFilterPattern(){

		return table.getFilterPattern(CommitmentTable.FILTER_REQTYPE).toString();
	}

	public List<String> getActorsFilter(){

		return (List<String>) table.getFilterPattern(CommitmentTable.FILTER_ACTORS);
	}

	private class BorderPainter implements PaintListener {

		public void paintControl(PaintEvent e){
			GC gc = e.gc;
			Rectangle rect = new Rectangle(e.x, e.y, e.width - 1, e.height - 1);
			int lineS = gc.getLineStyle();
			int lined[] = gc.getLineDash();
			Color c = gc.getForeground();

			gc.setLineStyle(SWT.LINE_DASH);
			gc.setLineDash(new int[] { 3, 2 });
			gc.setForeground(ColorConstants.darkGray);

			gc.drawRectangle(rect);

			gc.setLineStyle(lineS);
			gc.setLineDash(lined);
			gc.setForeground(c);
		}
	}
}
