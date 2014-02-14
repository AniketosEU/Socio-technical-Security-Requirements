/*
* ColoredListViewer.java
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
package eu.aniketos.wp1.ststool.diagram.custom.utility;

import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;


public class ColoredListViewer<T> {

	private final TableViewer			viewer;
	private final TableViewerColumn	viewerColumn;
	private Composite						composite;

	private LabelProvider				labelProvider;
	private IColorProvider				colorProvider;

	public ColoredListViewer(Composite parent) {
		final Composite tableContainer = new Composite(parent, SWT.NONE);
		tableContainer.setLayout(new FillLayout());
		tableContainer.setBackground(ColorConstants.orange);
		// Define the TableViewer
		viewer = new TableViewer(tableContainer, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		viewer.setContentProvider(new ArrayContentProvider());


		if (System.getProperty("os.name").startsWith("Windows")) {
			// First column is for win32 bug		
			final TableViewerColumn viewerColumn0 = new TableViewerColumn(viewer, SWT.NONE);
			final TableColumn column0 = viewerColumn0.getColumn();
			column0.setWidth(0);
			column0.setResizable(false);
			column0.setMoveable(false);
			viewerColumn0.setLabelProvider(new ColumnLabelProvider() {

				@Override
				public String getText(Object element){
					return "";
				}
			});
		}

		viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		//column.setWidth(100);
		column.setResizable(true);
		column.setMoveable(false);
		viewerColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element){
				if (labelProvider != null) return labelProvider.getText(element);
				return super.getText(element);
			}

			@Override
			public Color getBackground(Object element){
				if (colorProvider != null) return colorProvider.getBackground(element);
				return super.getBackground(element);
			}

			@Override
			public Color getForeground(Object element){
				if (colorProvider != null) return colorProvider.getForeground(element);
				return super.getForeground(element);
			}
		});

		tableContainer.addControlListener(new ControlListener() {

			@Override
			public void controlResized(ControlEvent e){
				viewerColumn.getColumn().setWidth(tableContainer.getClientArea().width - (viewer.getTable().getBorderWidth() * 2));
			}

			@Override
			public void controlMoved(ControlEvent e){

			}
		});

		this.composite = tableContainer;
	}

	public void setLabelProvider(LabelProvider labelprovider){
		if (labelprovider != null) {
			this.labelProvider = labelprovider;
			if (labelprovider instanceof IColorProvider) colorProvider = (IColorProvider) labelprovider;
		}
	}

	public void setSorter(ViewerSorter sorter){
		if (viewer != null) viewer.setSorter(sorter);
	}

	public Composite getComposite(){
		return composite;
	}

	public void setInput(List<T> input){
		viewer.setInput(input);
	}

	public List<T> getInput(){
		return (List<T>) viewer.getInput();
	}

	public void addDoubleClickListener(IDoubleClickListener doubleClickListener){
		viewer.addDoubleClickListener(doubleClickListener);
	}

	public void removeDoubleClickListener(IDoubleClickListener doubleClickListener){
		viewer.removeDoubleClickListener(doubleClickListener);
	}

	public ISelection getSelection(){
		return viewer.getSelection();
	}
}
