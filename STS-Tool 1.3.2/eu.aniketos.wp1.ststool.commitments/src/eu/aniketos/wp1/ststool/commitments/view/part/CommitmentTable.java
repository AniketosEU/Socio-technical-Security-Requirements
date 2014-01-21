/*
* CommitmentTable.java
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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Widget;
import eu.aniketos.wp1.ststool.commitments.filters.CommitmentActorsFilter;
import eu.aniketos.wp1.ststool.commitments.filters.CommitmentCreditorFilter;
import eu.aniketos.wp1.ststool.commitments.filters.CommitmentDebtorFilter;
import eu.aniketos.wp1.ststool.commitments.filters.CommitmentReqTypeFilter;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;
import eu.aniketos.wp1.ststool.commitments.sorter.CommitmentSorter;


public class CommitmentTable extends TableViewer {

	final static int						FILTER_CREDITOR	= 0;
	final static int						FILTER_DEBTOR		= 1;
	final static int						FILTER_REQTYPE		= 2;
	final static int						FILTER_ACTORS		= 3;

	private TableColumn					fakeFisrstColumn;
	private TableColumn					debtorColumn;
	private TableColumn					creditorColumn;
	private TableColumn					antecedentColumn;
	private TableColumn					reqTypeColumn;
	private TableColumn					descriptionColumn;

	private CommitmentSorter			sorter;

	private CommitmentDebtorFilter	debtorFilter;
	private CommitmentCreditorFilter	creditorFilter;
	private CommitmentReqTypeFilter	reqTypeFilter;
	private CommitmentActorsFilter	actorsFilter;



	public CommitmentTable(Composite parent, int style) {

		super(parent, style);
		init(parent);
	}

	public CommitmentTable(Composite parent) {

		super(parent);
		init(parent);
	}

	protected void init(Composite parent){

		final Table table = getTable();
		/** disable the row selection */
		table.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				//table.setSelection(-1);
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		initColum();

		setContentProvider(new ArrayContentProvider());
		setLabelProvider(new CommitmentLabelProvider());
		createTableSorter();
		buildFilter();

	}

	private void initColum(){

		final Table table = getTable();

		fakeFisrstColumn = new TableColumn(table, SWT.CENTER);
		fakeFisrstColumn.setText("");
		fakeFisrstColumn.setWidth(0);
		fakeFisrstColumn.setMoveable(false);
		fakeFisrstColumn.setResizable(false);

		debtorColumn = new TableColumn(table, SWT.CENTER);
		debtorColumn.setText("Responsible");
		debtorColumn.setWidth(300);
		debtorColumn.setMoveable(true);

		reqTypeColumn = new TableColumn(table, SWT.LEFT);
		reqTypeColumn.setText("Requirement");
		reqTypeColumn.setWidth(600);
		reqTypeColumn.setMoveable(true);

		creditorColumn = new TableColumn(table, SWT.CENTER);
		creditorColumn.setText("Requester");
		creditorColumn.setWidth(300);
		creditorColumn.setMoveable(true);
	}



	private void createTableSorter(){

		Comparator<ICommitment> debtorComparator = new Comparator<ICommitment>() {

			@Override
			public int compare(ICommitment c1,ICommitment c2){

				return c1.getResponsible().compareTo(c2.getResponsible());
			}
		};

		Comparator<ICommitment> creditorComparator = new Comparator<ICommitment>() {

			public int compare(ICommitment c1,ICommitment c2){

				return c1.getRequester().compareTo(c2.getRequester());
			}
		};
		Comparator<ICommitment> reqTypeComparator = new Comparator<ICommitment>() {

			public int compare(ICommitment c1,ICommitment c2){

				return c1.getReqisite().compareTo(c2.getReqisite());
			}
		};
		sorter = new CommitmentSorter(this, new TableColumn[] { debtorColumn, creditorColumn, reqTypeColumn }, new Comparator[] {
				debtorComparator, creditorComparator, reqTypeComparator }, debtorColumn);
		this.setSorter(sorter);
	}

	private void buildFilter(){

		debtorFilter = new CommitmentDebtorFilter(this);
		creditorFilter = new CommitmentCreditorFilter(this);
		reqTypeFilter = new CommitmentReqTypeFilter(this);
		actorsFilter = new CommitmentActorsFilter(this);
	}

	public void setFilter(int filter,Object pattern){

		switch (filter) {
			case FILTER_CREDITOR:
				creditorFilter.setPattern((String) pattern);
				return;
			case FILTER_DEBTOR:
				debtorFilter.setPattern((String) pattern);
				return;
			case FILTER_REQTYPE:
				reqTypeFilter.setPattern((String) pattern);
				return;
			case FILTER_ACTORS:
				actorsFilter.setPattern((List<String>) pattern);
				return;
		}
	}

	public Object getFilterPattern(int filter){

		switch (filter) {
			case FILTER_CREDITOR:
				return creditorFilter.getPattern();
			case FILTER_DEBTOR:
				return debtorFilter.getPattern();
			case FILTER_REQTYPE:
				return reqTypeFilter.getPattern();
			case FILTER_ACTORS:
				return actorsFilter.getPattern();
		}
		return null;
	}



	@Override
	protected void doUpdateItem(Widget widget,Object element,boolean fullMap){

		super.doUpdateItem(widget, element, fullMap);
	}



	class CommitmentLabelProvider extends LabelProvider implements ITableLabelProvider, IColorProvider {

		@Override
		public Image getColumnImage(Object element,int columnIndex){

			return null;
		}

		@Override
		public String getColumnText(Object element,int columnIndex){

			if (!(element instanceof ICommitment)) return "Invalid Object " + element.getClass().getSimpleName();
			ICommitment commit = (ICommitment) element;
			TableColumn t = getTable().getColumn(columnIndex);

			if (t == fakeFisrstColumn) {
				return "";
			} else if (t == debtorColumn) {
				return commit.getResponsible();
			} else if (t == creditorColumn) {
				return commit.getRequester();
			} else if (t == antecedentColumn) {
				return ""/*commit.getAntecedent()*/;
			} else if (t == reqTypeColumn) {
				return commit.getReqisite();
			} else if (t == descriptionColumn) {
				return commit.getDescritption();
			} else
				return "unknown column" + columnIndex;
		}

		boolean		color	= true;

		private int	call	= 0;

		@Override
		public Color getBackground(Object element){

			if (call++ % getTable().getColumnCount() == 0) {

				List list = Arrays.asList(getSortedChildren(getInput()));
				int index = list.indexOf(element);
				if (index == 0) {
					color = true;
				} else {
					ICommitment pervRow = (ICommitment) list.get(index - 1);
					TableColumn col = sorter.getSortingColumn();
					if (col == debtorColumn) {
						if (!pervRow.getResponsible().equals(((ICommitment) element).getResponsible())) color = !color;
					} else if (col == creditorColumn) {
						if (!pervRow.getRequester().equals(((ICommitment) element).getRequester())) color = !color;
					} else if (col == reqTypeColumn) {
						if (!pervRow.getClass().equals(element.getClass())) color = !color;
					} else
						return null;
				}
			}
			if (color) {
				return color1;
			} else {
				return null;
			}
		}

		@Override
		public Color getForeground(Object element){

			return null;
		}

		Color	color1	= new Color(Display.getDefault(), new RGB(215, 227, 253));

		//Color color2=new Color(Display.getDefault(),new RGB(red, green, blue));

		@Override
		public void dispose(){

			super.dispose();
			color1.dispose();

		}
	}
}
