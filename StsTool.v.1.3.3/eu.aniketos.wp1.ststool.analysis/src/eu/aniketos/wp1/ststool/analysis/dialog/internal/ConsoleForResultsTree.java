/*
* ConsoleForResultsTree.java
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
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import eu.aniketos.wp1.ststool.analysis.ImageManager;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;


public class ConsoleForResultsTree {

	private TreeViewer		treeViewer;

	private List<TreeNode>	mainNodes	= new ArrayList<TreeNode>();

	public Composite getComposite(final Composite parent){
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(ColorConstants.red);
		composite.setLayout(new FillLayout());
		treeViewer = new TreeViewer(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS) {
		};

		treeViewer.getTree().addListener(SWT.EraseItem, new Listener() {

			public void handleEvent(Event event){
				event.detail &= ~SWT.HOT;
				event.detail &= ~SWT.SELECTED;
				event.detail &= ~SWT.FOREGROUND;
			}
		});

		treeViewer.getTree().addListener(SWT.PaintItem, new Listener() {

			public void handleEvent(Event event){

				TreeItem it = (TreeItem) event.item;

				ConsoleObject obj = (ConsoleObject) (((TreeNode) it.getData()).getValue());

				String text = null;
				Image img = null;
				Color c = null;
				if (event.index == 0) {
					text = obj.text;
					img = obj.image;
					c = obj.foreColor1;
				} else {
					text = obj.text2;
					c = obj.foreColor2;
				}
				if (c == null) c = ColorConstants.black;
				event.gc.setForeground(c);

				int xOffset = 3;
				if (img != null && event.index == 0) {
					int yOffset = Math.max(0, (event.height - img.getImageData().height) / 2);
					event.gc.drawImage(img, event.x + xOffset, event.y + yOffset);
					xOffset += img.getImageData().width;
				} else if (it.getParentItem() != null) {
					xOffset += 16;
				}

				int yOffset = 0;
				Point size = event.gc.textExtent(text);
				yOffset = Math.max(0, (event.height - size.y) / 2);
				event.gc.drawText(text, event.x + xOffset + 3, event.y + yOffset, true);
				/*if(event.index==0 && obj.isSubline){
					xOffset+= event.x+size.x;
					event.gc.setLineStyle(SWT.LINE_DOT);
					event.gc.drawLine(xOffset+5, event.y + yOffset+size.y-4, xOffset+1000, event.y + yOffset+size.y-4);
				}*/
			}
		});
		treeViewer.getTree().setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_ARROW));

		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS);
		TreeColumn trclmnNewColumn = treeViewerColumn.getColumn();
		trclmnNewColumn.setWidth(270);

		TreeViewerColumn treeViewerColumn_1 = new TreeViewerColumn(treeViewer, SWT.FULL_SELECTION | SWT.HIDE_SELECTION | SWT.NO_FOCUS);
		TreeColumn trclmnNewColumn_1 = treeViewerColumn_1.getColumn();
		trclmnNewColumn_1.setWidth(100);

		composite.addControlListener(new TableCompositeAdapter(composite, treeViewer.getTree(), treeViewerColumn.getColumn(), treeViewerColumn_1.getColumn()));

		CellLabelProvider lp = new CellLabelProvider() {

			@Override
			public void update(ViewerCell cell){
			}
		};

		treeViewerColumn.setLabelProvider(lp);
		treeViewerColumn_1.setLabelProvider(lp);
		treeViewer.setContentProvider(new ContentProvider());
		return composite;
	}


	public void addLine(String text){
		addLine((Image) null, text, "", null, null);
	}

	public void addLine(Image image,String text,String text2,Color foreColor1,Color foreColor2){
		mainNodes.add(new TreeNode(new ConsoleObject(image, text, text2, foreColor1, foreColor2, false)));
		updateTree();
	}

	public void addSubLine(String name,String message,Color fg1,Color fg2){
		addSubLine((Image) null, name, message, fg1, fg2);
	}


	public void addSubLine(ResultType error,String name,String message,Color fg1,Color fg2){
		addSubLine(getImageForResult(error), name, message, fg1, fg2);
	}

	public void addSubLine(Image image,String text,String text2,Color foreColor1,Color foreColor2){
		TreeNode tn = mainNodes.get(mainNodes.size() - 1);
		tn.addChildren(new TreeNode(new ConsoleObject(image, text, text2, foreColor1, foreColor2, true)));
		updateTree();
	}

	public void clear(){
		mainNodes.clear();
		updateTree();
	}

	private void updateTree(){
		if (treeViewer != null) {
			treeViewer.setInput(mainNodes);
			treeViewer.expandAll();
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

	private class ContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement){
			return ((TreeNode) parentElement).getChildren().toArray();
		}

		@Override
		public Object getParent(Object element){
			return ((TreeNode) element).getParent();
		}

		@Override
		public boolean hasChildren(Object element){
			return ((TreeNode) element).hasChildren();
		}

		@Override
		public Object[] getElements(Object inputElement){
			return mainNodes.toArray();
		}

		@Override
		public void dispose(){
		}

		@Override
		public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		}

	}

	/**
	 * Manage the table column resizing
	 */
	private class TableCompositeAdapter extends ControlAdapter {

		private Composite	composite;
		private Tree		tree;
		TreeColumn			col1;
		TreeColumn			col2;

		public TableCompositeAdapter(Composite composite, Tree tree, TreeColumn col1, TreeColumn col2) {
			super();
			this.composite = composite;
			this.tree = tree;
			this.col1 = col1;
			this.col2 = col2;
		}

		@Override
		public void controlResized(ControlEvent e){

			Rectangle area = composite.getClientArea();
			Point preferredSize = tree.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			int width = area.width - 2 * tree.getBorderWidth();
			if (preferredSize.y > area.height + tree.getHeaderHeight()) {
				Point vBarSize = tree.getVerticalBar().getSize();
				width -= vBarSize.x;
			}
			Point oldSize = tree.getSize();

			/*int col2size = 240;
			int x=(int)((width/100.0)*30);
			if(col2size>x)col2size=x;
			int col1size = width-col2size;*/


			int col1size = 330;
			int col2size = width - col1size;
			if (oldSize.x > area.width) {
				col1.setWidth(col1size);
				col2.setWidth(col2size);
				tree.setSize(area.width, area.height);
			} else {
				tree.setSize(area.width, area.height);
				col1.setWidth(col1size);
				col2.setWidth(col2size);
			}
		}
	}



	class TreeNode {

		private TreeNode			parent;
		private List<TreeNode>	children	= new ArrayList<TreeNode>();
		private Object				value;
		private boolean			enabled;


		public TreeNode(Object value) {
			this.value = value;
			enabled = true;
		}

		public void addChildren(TreeNode child){
			child.parent = this;
			children.add(child);
		}

		public void removeChildren(TreeNode child){
			if (children.contains(child)) {
				TreeNode node = children.remove(children.indexOf(child));
				node.parent = null;
			}
		}

		public List<TreeNode> getChildren(){
			return children;
		}

		public TreeNode getParent(){
			return parent;
		}

		public Object getValue(){
			return value;
		}

		public boolean isEnabled(){
			return enabled;
		}

		public void setEnabled(boolean enabled){
			this.enabled = enabled;
		}

		public boolean hasChildren(){
			return children.size() > 0;
		}
	}

	private class ConsoleObject {

		String	text;
		String	text2;
		Image		image			= null;
		Color		foreColor1	= null;
		Color		foreColor2	= null;
		@SuppressWarnings("unused")
		boolean	isSubline	= false;

		ConsoleObject(Image image, String text, String text2, Color foreColor1, Color foreColor2, boolean isSubline) {
			if (text == null) text = ""; //$NON-NLS-1$
			this.text = text;
			this.text2 = text2;
			if (image != null) {
				this.image = new Image(Display.getCurrent(), image.getImageData().scaledTo(16, 16));
				;
			}
			this.foreColor1 = foreColor1;
			this.foreColor2 = foreColor2;
			this.isSubline = isSubline;
		}
	}



}
