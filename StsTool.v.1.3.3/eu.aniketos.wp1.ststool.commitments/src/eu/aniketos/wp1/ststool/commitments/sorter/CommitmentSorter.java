/*
* CommitmentSorter.java
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
package eu.aniketos.wp1.ststool.commitments.sorter;

import java.io.IOException;
import java.util.Comparator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;
import eu.aniketos.wp1.ststool.commitments.Activator;


public class CommitmentSorter extends ViewerSorter {

	private static Image	upArrow;
	private static Image	downArrow;
	static {
		try {
			upArrow = new Image(null, Activator.getDefault().getBundle().getEntry("icons/arrowup.gif").openStream());
		} catch (IOException e) {
			upArrow = null;
		}
		try {
			downArrow = new Image(null, Activator.getDefault().getBundle().getEntry("icons/arrowdown.gif").openStream());
		} catch (IOException e) {
			downArrow = null;
		}

	}

	// Simple data structure for grouping
	// sort information by column.
	private class SortInfo {

		Comparator<Object>	comparator;
		boolean					descending;
	}

	private TableViewer	viewer;
	private SortInfo[]	infos;
	private int				activeSorter	= 0;
	private TableColumn	sortingColumn;

	public CommitmentSorter(TableViewer viewer, TableColumn[] columns, Comparator<Object>[] comparators, TableColumn initialSort) {

		this.viewer = viewer;
		infos = new SortInfo[columns.length];
		for (int i = 0; i < columns.length; i++) {
			infos[i] = new SortInfo();
			infos[i].comparator = comparators[i];
			infos[i].descending = false;
			createSelectionListener(columns[i], i);
		}
		sortingColumn = initialSort;
		initialSort.setImage(upArrow);
	}

	@Override
	public int compare(Viewer viewer,Object favorite1,Object favorite2){

		int result = infos[activeSorter].comparator.compare(favorite1, favorite2);
		if (infos[activeSorter].descending) return -result;
		return result;
	}

	public TableColumn getSortingColumn(){

		return sortingColumn;
	}



	private void createSelectionListener(final TableColumn column,final int index){

		column.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				if (sortingColumn != null) {
					sortingColumn.setImage(null);
				}
				if (activeSorter == index) {
					infos[index].descending = !infos[index].descending;
				} else {
					activeSorter = index;
				}
				if (infos[index].descending) {
					((TableColumn) e.getSource()).setImage(downArrow);
				} else {
					((TableColumn) e.getSource()).setImage(upArrow);
				}
				sortingColumn = (TableColumn) e.getSource();
				viewer.refresh(true, true);
			}
		});
	}
}
