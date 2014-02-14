/*
* ConsoleForResultsTable.java
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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import eu.aniketos.wp1.ststool.analysis.ImageManager;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;


public class ConsoleForResultsTable {

	private TableViewer				tableViewer;

	private List<ConsoleObject>	mainNodes	= new ArrayList<ConsoleObject>();

	public Composite getComposite(final Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(ColorConstants.red);
		composite.setLayout(new FillLayout());
		tableViewer = new TableViewer(composite, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS) {
		};

		tableViewer.getTable().addListener(SWT.EraseItem, new Listener() {

			public void handleEvent(Event event){
				event.detail &= ~SWT.HOT;
				if ((event.detail & SWT.SELECTED) == 0) return; /// item not selected

				Table table = (Table) event.widget;
				TableItem item = (TableItem) event.item;
				int clientWidth = table.getClientArea().width;

				GC gc = event.gc;
				Color oldForeground = gc.getForeground();
				Color oldBackground = gc.getBackground();

				gc.setBackground(item.getBackground());
				gc.setForeground(item.getForeground());
				gc.fillRectangle(0, event.y, clientWidth, event.height);

				gc.setForeground(oldForeground);
				gc.setBackground(oldBackground);
				event.detail &= ~SWT.SELECTED;
			}
		});

		TableViewerColumn treeViewerColumn = new TableViewerColumn(tableViewer, SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS);
		TableColumn trclmnNewColumn = treeViewerColumn.getColumn();
		trclmnNewColumn.setWidth(300);

		TableViewerColumn treeViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS);
		TableColumn trclmnNewColumn_1 = treeViewerColumn_1.getColumn();
		trclmnNewColumn_1.setWidth(100);

		composite.addControlListener(new TableCompositeAdapter(composite, tableViewer.getTable(), treeViewerColumn.getColumn(), treeViewerColumn_1.getColumn()));

		tableViewer.setLabelProvider(new ConsoleObjectLabelProvider());
		tableViewer.setContentProvider(new ArrayContentProvider());
		return composite;
	}


	public void addLine(String text,String text2){
		addLine((Image) null, text, text2, null, null);
	}

	public void addLine(String text,String text2,Color foreColor1,Color foreColor2){
		addLine((Image) null, text, text2, foreColor1, foreColor2);
	}

	public void addLine(ResultType type,String text,String text2){
		addLine(getImageForResult(type), text, text2, null, null);
	}

	public void addLine(ResultType type,String text,String text2,Color foreColor1,Color foreColor2){
		addLine(getImageForResult(type), text, text2, foreColor1, foreColor2);
	}

	public void addLine(Image image,String text,String text2){
		addLine(image, text, text2, null, null);
	}

	public void addLine(Image image,String text,String text2,Color foreColor1,Color foreColor2){
		mainNodes.add(new ConsoleObject(image, text, text2, foreColor1, foreColor2));
		updateTree();
	}



	public void clear(){
		mainNodes.clear();
		updateTree();
	}

	private void updateTree(){
		if (tableViewer != null) {
			tableViewer.setInput(mainNodes);
		}
	}

	private Image getImageForResult(ResultType type){
		if (type != null) {
			switch (type) {
				case OK:
					return ImageManager.getImage(ImageManager.OK_ICON);
				case WARNING:
					return ImageManager.getImage(ImageManager.WARNING_ICON);
				case ERROR:
					return ImageManager.getImage(ImageManager.ERROR_ICON);
			}
		}
		return null;
	}

	private class ConsoleObjectLabelProvider extends LabelProvider implements ITableLabelProvider, ITableColorProvider {

		@Override
		public Image getColumnImage(Object element,int columnIndex){
			switch (columnIndex) {
				case 0:
					return ((ConsoleObject) element).image;
			}
			return null;
		}

		@Override
		public String getColumnText(Object element,int columnIndex){
			switch (columnIndex) {
				case 0:
					return ((ConsoleObject) element).text;
				case 1:
					return ((ConsoleObject) element).text2;
			}
			return "?"; //$NON-NLS-1$
		}

		@Override
		public Color getBackground(Object element,int columnIndex){
			if (columnIndex == 1) {
				//return ((ConsoleObject)element).backColor;
			}
			return null;
		}

		@Override
		public Color getForeground(Object element,int columnIndex){
			switch (columnIndex) {
				case 0:
					return ((ConsoleObject) element).foreColor1;
				case 1:
					return ((ConsoleObject) element).foreColor2;
			}
			return null;

		}
	}

	/**
	 * Manage the table column resizing
	 */
	private class TableCompositeAdapter extends ControlAdapter {

		private Composite	composite;
		private Table		table;
		TableColumn			col1;
		TableColumn			col2;

		public TableCompositeAdapter(Composite composite, Table table, TableColumn col1, TableColumn col2) {
			super();
			this.composite = composite;
			this.table = table;
			this.col1 = col1;
			this.col2 = col2;
		}

		@Override
		public void controlResized(ControlEvent e){

			Rectangle area = composite.getClientArea();
			Point preferredSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			int width = area.width - 2 * table.getBorderWidth();
			if (preferredSize.y > area.height + table.getHeaderHeight()) {
				// Subtract the scrollbar width from the total column width
				// if a vertical scrollbar will be required
				Point vBarSize = table.getVerticalBar().getSize();
				width -= vBarSize.x;
			}
			Point oldSize = table.getSize();

			//calcluate column size
			int col2size = 200;
			int x = (int) ((width / 100.0) * 30);
			if (col2size > x) col2size = x;
			int col1size = width - col2size;

			//System.out.println(width+" "+firstColumnWidth+" "+centralColumnWidth+" "+lastColumnWidth);

			if (oldSize.x > area.width) {
				// table is getting smaller so make the columns 
				// smaller first and then resize the table to
				// match the client area width

				col1.setWidth(col1size);
				col2.setWidth(col2size);
				table.setSize(area.width, area.height);
			} else {
				// table is getting bigger so make the table 
				// bigger first and then make the columns wider
				// to match the client area width
				table.setSize(area.width, area.height);
				col1.setWidth(col1size);
				col2.setWidth(col2size);
			}
		}
	}


	private class ConsoleObject {

		String	text;
		String	text2;
		Image		image			= null;
		Color		foreColor1	= null;
		Color		foreColor2	= null;

		ConsoleObject(Image image, String text, String text2, Color foreColor1, Color foreColor2) {
			if (text == null) text = ""; //$NON-NLS-1$
			this.text = text;
			this.text2 = text2;
			this.image = image;
			this.foreColor1 = foreColor1;
			this.foreColor2 = foreColor2;
		}
	}
}
