/*
* ResultViewDataProvider.java
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
package eu.aniketos.wp1.ststool.analysis.ui;

import static eu.aniketos.wp1.ststool.analysis.Messages.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import eu.aniketos.wp1.ststool.analysis.Activator;
import eu.aniketos.wp1.ststool.analysis.ImageManager;
import eu.aniketos.wp1.ststool.analysis.internal.DiagramObserver;
import eu.aniketos.wp1.ststool.analysis.internal.DiagramObserverListener;
import eu.aniketos.wp1.ststool.analysis.results.AnalysisResultListener;
import eu.aniketos.wp1.ststool.analysis.results.Category;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;

public class ResultViewDataProvider implements AnalysisResultListener,
		DisposeListener, ISelectionChangedListener, DiagramObserverListener,
		IOpenListener {

	private TreeViewer treeViewer;
	private ResultsManager rm = ResultsManager.getInstance();
	private Label statusLabel;

	public ResultViewDataProvider(final TreeViewer treeViewer,
			TreeViewerColumn treeViewerColumn,
			TreeViewerColumn treeViewerColumn2, Label statusLabel) {
		this.treeViewer = treeViewer;
		this.statusLabel = statusLabel;
		treeViewer.getTree().addDisposeListener(this);
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		treeViewer.setContentProvider(new ListTreeNodeContentProvider());
		treeViewerColumn.setLabelProvider(new ResultLabelProviderWithToolTip());
		treeViewerColumn2
				.setLabelProvider(new ResultLabelProviderWithToolTip2());
		//treeViewer.addSelectionChangedListener(this);
		treeViewer.addOpenListener(this);

		rm.addAnalysisResultListener(this);
		DiagramObserver.addDiagramObserverListener(this);
		resultsChanged(rm);

		final Tree tree = treeViewer.getTree();
		tree.addListener(SWT.EraseItem, new Listener() {

			public void handleEvent(Event event) {
				event.detail &= ~SWT.FOREGROUND;
			}
		});

		tree.addListener(SWT.PaintItem, new Listener() {

			Map<Image, Image> grayedimage = new HashMap<Image, Image>();

			public void handleEvent(Event event) {

				TreeItem item = (TreeItem) event.item;
				String text = item.getText(event.index);
				Image img = item.getImage();

				if (((TreeNode) item.getData()).isEnabled()) {
					event.gc.setForeground(item.getForeground(event.index));
				} else {
					if (!grayedimage.containsKey(img)) {
						grayedimage.put(img, new Image(null, img,
								SWT.IMAGE_GRAY));
					}
					img = grayedimage.get(img);
					event.gc.setForeground(ColorConstants.gray);
				}

				int xOffset = 3;
				if (img != null && event.index == 0) {
					int yOffset = Math.max(0, (event.height - img
							.getImageData().height) / 2);
					event.gc.drawImage(img, event.x + xOffset, event.y
							+ yOffset);
					xOffset += img.getImageData().width;
				}

				int yOffset = 0;
				Point size = event.gc.textExtent(text);
				yOffset = Math.max(0, (event.height - size.y) / 2);
				event.gc.drawText(text, event.x + xOffset + 3, event.y
						+ yOffset, true);
			}
		});
	}

	private class ListTreeNodeContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return ((List<TreeNode>) inputElement).toArray();
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			return ((TreeNode) parentElement).getChildren().toArray();
		}

		@Override
		public Object getParent(Object element) {
			return ((TreeNode) element).getParent();
		}

		@Override
		public boolean hasChildren(Object element) {
			return ((TreeNode) element).hasChildren();
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	private class ResultLabelProviderWithToolTip extends CellLabelProvider {

		@Override
		protected void initialize(ColumnViewer viewer, ViewerColumn column) {
		}

		public Image getImage(Object element) {
			Object e = ((TreeNode) element).getValue();
			ResultType type = null;

			if (e instanceof Category) {
				List<IResult> catRes = rm.getAllResultsInCategory((Category) e);
				type = ResultType.OK;
				for (IResult r : catRes) {
					type = takeWorst(r.getType(), type);
				}
			} else if (e instanceof IResult) {
				type = ((IResult) e).getType();
			}
			if (type != null)
				switch (type) {
				case OK:
					return ImageManager.getImage(ImageManager.OK_ICON);
				case WARNING:
					return ImageManager.getImage(ImageManager.WARNING_ICON);
				case ERROR:
					return ImageManager.getImage(ImageManager.ERROR_ICON);
				}
			return ImageManager.getImage(ImageManager.UNKNOWN_ICON);

		}

		private ResultType takeWorst(ResultType type1, ResultType type2) {
			if (type1 == null)
				return type2;
			if (type2 == null)
				return type1;

			if (type1 == ResultType.ERROR || type2 == ResultType.ERROR)
				return ResultType.ERROR;
			if (type1 == ResultType.WARNING || type2 == ResultType.WARNING)
				return ResultType.WARNING;
			return ResultType.OK;
		}

		public String getText(Object element) {
			Object e = ((TreeNode) element).getValue();
			if (e instanceof Category) {
				return ((Category) e).getName();
			} else if (e instanceof IResult) {
				return ((IResult) e).getText();
			} else {
				return "?"; //$NON-NLS-1$
			}
		}

		@Override
		public String getToolTipText(Object element) {
			Object e = ((TreeNode) element).getValue();
			if (e instanceof Category) {
				return ((Category) e).getDescription();
			} else if (e instanceof IResult) {
				return ((IResult) e).getDescription();
			} else {
				return "?"; //$NON-NLS-1$
			}
		}

		@Override
		public Point getToolTipShift(Object object) {
			return new Point(5, 5);
		}

		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 100;
		}

		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return 5000;
		}

		@Override
		public void update(ViewerCell cell) {
			cell.setText(getText(cell.getElement()));
			cell.setImage(getImage(cell.getElement()));
			((TreeItem) cell.getItem()).setGrayed(true/*
													 * ((TreeNode)cell.getElement
													 * ()).isEnabled()
													 */);
		}

		@Override
		public boolean useNativeToolTip(Object object) {
			return true;
		}
	}

	private class ResultLabelProviderWithToolTip2 extends CellLabelProvider {

		@Override
		public boolean useNativeToolTip(Object object) {
			return true;
		}

		public String getText(Object element) {
			Object e = ((TreeNode) element).getValue();
			if (e instanceof Category) {
				return null;
			} else if (e instanceof IResult) {
				return ((IResult) e).getTaskName();
			} else {
				return "?"; //$NON-NLS-1$
			}
		}

		@Override
		public String getToolTipText(Object element) {
			Object e = ((TreeNode) element).getValue();
			if (e instanceof Category) {
				return ""; //$NON-NLS-1$
			} else if (e instanceof IResult) {
				return ((IResult) e).getTaskName();
			} else {
				return "?"; //$NON-NLS-1$
			}
		}

		@Override
		public Point getToolTipShift(Object object) {
			return new Point(5, 5);
		}

		@Override
		public int getToolTipDisplayDelayTime(Object object) {
			return 100;
		}

		@Override
		public int getToolTipTimeDisplayed(Object object) {
			return 5000;
		}

		@Override
		public void update(ViewerCell cell) {
			cell.setText(getText(cell.getElement()));
		}
	}

	private List<TreeNode> getTree() {
		List<Category> catList = rm.getAllCategories();
		Collections.sort(catList);
		List<TreeNode> result = new ArrayList<TreeNode>();
		for (Category c : catList) {
			TreeNode catNode = new TreeNode(c, true);
			result.add(catNode);
			List<IResult> resList = rm.getAllResultsInCategory(c);
			Collections.sort(resList);
			for (IResult r : resList) {
				catNode.addChildren(new TreeNode(r, rm.isMarked(r)));
			}
		}
		return result;
	}

	@Override
	public void resultsChanged(ResultsManager rm) {
		statusUpdated = false;
		statusLabel.setText(""); //$NON-NLS-1$
		treeViewer.setInput(getTree());
		treeViewer.expandAll();
	}

	boolean statusUpdated = false;

	@Override
	public void diagramModified() {
		if (!statusUpdated && !rm.getAllResults().isEmpty()) {
			statusUpdated = true;
			statusLabel.setText(getMessage(ResultView_DiagramModyfied_Text));
		}
	}

	@Override
	public void widgetDisposed(DisposeEvent e) {
		rm.removeAnalysisResultListener(this);
	}

	public void open(final OpenEvent event) {
		Object o = ((IStructuredSelection) event.getSelection())
				.getFirstElement();
		if (o instanceof TreeNode) {
			TreeNode node = ((TreeNode) o);
			if (node.getValue() instanceof IResult) {
				node.setEnabled(!node.isEnabled());
				if (node.isEnabled()) {
					ResultsManager.getInstance().markObject(
							(IResult) node.getValue(), true);
				} else {
					ResultsManager.getInstance().removeMarkObject(
							(IResult) node.getValue(), true);
				}
				treeViewer.getTree().redraw();

			} else if (node.getValue() instanceof Category
					&& node.hasChildren()) {
				int enabled=0;
				
				for(TreeNode tn:node.getChildren()){
					if(tn.isEnabled())enabled++;
				}
				
				if(enabled==node.getChildren().size()){
					for (TreeNode tn : node.getChildren()) {
						if (tn.isEnabled()) {
							tn.setEnabled(false);
							ResultsManager.getInstance().removeMarkObject(
									(IResult) tn.getValue(), true);
						}
					}
				}else{
					for (TreeNode tn : node.getChildren()) {
						if (!tn.isEnabled()) {
							tn.setEnabled(true);
							ResultsManager.getInstance().markObject(
									(IResult) tn.getValue(), true);
						}
					}
				}
				treeViewer.getTree().redraw();
			}
		}

	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection
				&& !((IStructuredSelection) event.getSelection()).isEmpty()) {
			TreeNode e = (TreeNode) ((IStructuredSelection) event
					.getSelection()).getFirstElement();
			MenuManager menuMgr = new MenuManager();
			Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
			treeViewer.getControl().setMenu(menu);
			if (e.getValue() instanceof IResult) {
				menuMgr.add(new HideUnhideResultAction(e));
			} else if (e.getValue() instanceof Category && e.hasChildren()) {
				menuMgr.add(new HideAllResultsAction(e));
				menuMgr.add(new UnHideAllResultsAction(e));
			}
		}
	}

	private class HideUnhideResultAction extends Action {

		TreeNode node;

		public HideUnhideResultAction(TreeNode node) {
			if (node.isEnabled()) {
				setText(getMessage(ResultView_HideResult_Action_Text));
			} else {
				setText(getMessage(ResultView_UnhideResult_Action_Text));
			}
			setImageDescriptor(Activator.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "resources/icons/view/error_icon.gif")); //$NON-NLS-1$
			this.node = node;
		}

		@Override
		public void run() {
			try {
				node.setEnabled(!node.isEnabled());

				if (node.isEnabled()) {
					ResultsManager.getInstance().markObject(
							(IResult) node.getValue(), true);
					setText(getMessage(ResultView_HideResult_Action_Text));
				} else {
					ResultsManager.getInstance().removeMarkObject(
							(IResult) node.getValue(), true);
					setText(getMessage(ResultView_UnhideResult_Action_Text));
				}
				treeViewer.getTree().redraw();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class HideAllResultsAction extends Action {

		TreeNode node;

		public HideAllResultsAction(TreeNode node) {
			this.node = node;
			setText(getMessage(ResultView_HideAllResult_Action_Text));
			setImageDescriptor(Activator.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "resources/icons/view/error_icon.gif")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			try {
				for (TreeNode tn : node.getChildren()) {
					if (tn.isEnabled()) {
						tn.setEnabled(false);
						ResultsManager.getInstance().removeMarkObject(
								(IResult) tn.getValue(), true);
					}
				}
				treeViewer.getTree().redraw();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class UnHideAllResultsAction extends Action {

		TreeNode node;

		public UnHideAllResultsAction(TreeNode node) {
			this.node = node;
			setText(getMessage(ResultView_UnhideAllResult_Action_Text));
			setImageDescriptor(Activator.imageDescriptorFromPlugin(
					Activator.PLUGIN_ID, "resources/icons/view/error_icon.gif")); //$NON-NLS-1$
		}

		@Override
		public void run() {
			try {
				for (TreeNode tn : node.getChildren()) {
					if (!tn.isEnabled()) {
						tn.setEnabled(true);
						ResultsManager.getInstance().markObject(
								(IResult) tn.getValue(), true);
					}
				}
				treeViewer.getTree().redraw();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class TreeNode {

		private TreeNode parent;
		private List<TreeNode> children = new ArrayList<TreeNode>();
		private Object value;
		private boolean enabled;

		public TreeNode(Object value, boolean enabled) {
			this.value = value;
			this.enabled = enabled;
		}

		public void addChildren(TreeNode child) {
			child.parent = this;
			children.add(child);
		}

		public void removeChildren(TreeNode child) {
			if (children.contains(child)) {
				TreeNode node = children.remove(children.indexOf(child));
				node.parent = null;
			}
		}

		public List<TreeNode> getChildren() {
			return children;
		}

		public TreeNode getParent() {
			return parent;
		}

		public Object getValue() {
			return value;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

}
