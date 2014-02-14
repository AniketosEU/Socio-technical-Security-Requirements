/*
* ResultView.java
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

import static eu.aniketos.wp1.ststool.analysis.Messages.ResultView_ClearError_Action_Text;
import static eu.aniketos.wp1.ststool.analysis.Messages.ResultView_Column_1_Titile;
import static eu.aniketos.wp1.ststool.analysis.Messages.ResultView_Column_2_Titile;
import static eu.aniketos.wp1.ststool.analysis.Messages.getMessage;
import java.util.List;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import eu.aniketos.wp1.ststool.analysis.Activator;
import eu.aniketos.wp1.ststool.analysis.internal.DiagramObserver;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.results.ResultsManager;
import eu.aniketos.wp1.ststool.analysis.ui.ResultViewDataProvider.TreeNode;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

public class ResultView extends ViewPart implements DisposeListener, ISelectionChangedListener {

	public final static String	ID			= "eu.aniketos.wp1.ststool.analysis.analysisresultviewID";	//$NON-NLS-1$

	private final FormToolkit	toolkit	= new FormToolkit(Display.getCurrent());

	private TreeViewer			treeViewer;
	private Label					statusLabel;
	private Text					descriptionText;

	public ResultView() {

	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent){

		parent.addDisposeListener(this);
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);

		container.setLayout(new FormLayout());

		Composite compositeResults = new Composite(container, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.left = new FormAttachment(0);
		fd_composite.top = new FormAttachment(0);
		fd_composite.bottom = new FormAttachment(100, -30);
		fd_composite.right = new FormAttachment(100);
		compositeResults.setLayoutData(fd_composite);
		compositeResults.setLayout(new FillLayout());


		Composite compositeStatusBar = new Composite(container, SWT.BORDER);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.right = new FormAttachment(100);
		fd_composite_1.left = new FormAttachment(0);
		fd_composite_1.bottom = new FormAttachment(100);
		fd_composite_1.top = new FormAttachment(compositeResults);
		compositeStatusBar.setLayoutData(fd_composite_1);
		compositeStatusBar.setLayout(new FormLayout());

		SashForm sashForm = new SashForm(compositeResults, SWT.SMOOTH | SWT.VERTICAL);
		sashForm.setSashWidth(2);
		toolkit.paintBordersFor(sashForm);

		TreeViewer treeViewer = new TreeViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		treeViewer.addPostSelectionChangedListener(this);
		Tree tree = treeViewer.getTree();
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setSortDirection(SWT.UP);
		toolkit.paintBordersFor(tree);

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

		descriptionText = new Text(composite_2, SWT.NONE | SWT.WRAP | SWT.V_SCROLL);
		descriptionText.setBackground(descriptionText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		FormData fd_text = new FormData();
		int x = 3;
		fd_text.bottom = new FormAttachment(100, -x);
		fd_text.right = new FormAttachment(100, -x);
		fd_text.top = new FormAttachment(0, x);
		fd_text.left = new FormAttachment(0, x);
		descriptionText.setLayoutData(fd_text);
		descriptionText.setEditable(false);

		sashForm.setWeights(new int[] { 55, 45 });

		TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.CENTER);
		treeViewerColumn.getColumn().setText(getMessage(ResultView_Column_1_Titile));
		treeViewerColumn.getColumn().setWidth(700);

		TreeViewerColumn treeViewerColumn2 = new TreeViewerColumn(treeViewer, SWT.LEFT);
		treeViewerColumn2.getColumn().setText(getMessage(ResultView_Column_2_Titile));
		treeViewerColumn2.getColumn().setWidth(500);

		statusLabel = new Label(compositeStatusBar, SWT.HORIZONTAL | SWT.SHADOW_NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -3);
		fd_lblNewLabel.top = new FormAttachment(0, 6);
		fd_lblNewLabel.left = new FormAttachment(3);
		statusLabel.setLayoutData(fd_lblNewLabel);
		statusLabel.setForeground(ColorConstants.red);

		container.addControlListener(new TableCompositeAdapter(container, treeViewer.getTree(), treeViewerColumn.getColumn(), treeViewerColumn2.getColumn()));
		new ResultViewDataProvider(treeViewer, treeViewerColumn, treeViewerColumn2, statusLabel);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	@Override
	public void dispose(){
		toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions(){

		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar(){

		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
		tbm.add(new ClearAction());
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu(){
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
		manager.add(new ClearAction());
	}

	@Override
	public void setFocus(){

		if (treeViewer != null) treeViewer.getTree().setFocus();
	}

	@Override
	public void widgetDisposed(DisposeEvent e){
		toolkit.dispose();
	}



	private Object	lastSelected	= null;
	private int		count				= 0;

	@Override
	public void selectionChanged(SelectionChangedEvent event){
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();
		if (!sel.isEmpty()) {
			Object o = ((TreeNode) sel.getFirstElement()).getValue();
			if (o == lastSelected) {
				count++;
			} else {
				lastSelected = o;
				count = 0;
			}
			if (o instanceof IResult) {
				descriptionText.setText(((IResult) o).getDescription());
				if (((IResult) o).getObjects() != null && DiagramObserver.getEditor() != null) {
					CustomStsToolDiagramDocumentEditor editor = DiagramObserver.getEditor();
					switchView(((IResult) o).getView(), editor);
					List<EObject> oList = ((IResult) o).getObjects();
					int index = count - ((count / oList.size()) * oList.size());
					EObject obj = ((IResult) o).getObjects().get(index);
					editor.focusObject(obj);
				}
			} else {
				descriptionText.setText("");
			}
		} else {
			descriptionText.setText("");
		}
	}

	private void switchView(int view,CustomStsToolDiagramDocumentEditor editor){

		if ((view == ViewsManager.SOCIAL_VIEW || view == ViewsManager.RESOURCE_VIEW || view == ViewsManager.AUTHORIZATION_VIEW) && (view != editor.getViewsManager().getCurrentIntView())) {
			try {
				ICommandService cmdService = (ICommandService) getSite().getService(ICommandService.class);
				Command switchView = cmdService.getCommand("eu.aniketos.wp1.ststool.diagram.command.switchView"); //$NON-NLS-1$
				String v = null; //$NON-NLS-1$
				switch (view) {
					case ViewsManager.SOCIAL_VIEW:
						v = "social"; //$NON-NLS-1$
					break;
					case ViewsManager.RESOURCE_VIEW:
						v = "information"; //$NON-NLS-1$
					break;
					case ViewsManager.AUTHORIZATION_VIEW:
						v = "authorisation"; //$NON-NLS-1$
					break;
				}

				IParameter param = switchView.getParameter("org.eclipse.ui.commands.radioStateParameter"); //$NON-NLS-1$

				Parameterization parm = new Parameterization(param, v);
				ParameterizedCommand parmCommand = new ParameterizedCommand(switchView, new Parameterization[] { parm });

				IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
				handlerService.executeCommand(parmCommand, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class ClearAction extends Action {

		public ClearAction() {
			setText(getMessage(ResultView_ClearError_Action_Text));
			setImageDescriptor(Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "resources/icons/view/clear.gif")); //$NON-NLS-1$
		}

		@Override
		public void run(){
			ResultsManager.getInstance().cleanCategoriesAndResults();
		}
	}

	/**
	 * Manage the table column resizing
	 */
	private class TableCompositeAdapter extends ControlAdapter {

		private Composite	composite;
		private Tree		table;
		TreeColumn			col1;
		TreeColumn			col2;

		public TableCompositeAdapter(Composite composite, Tree tree, TreeColumn treeColumn, TreeColumn treeColumn2) {
			super();
			this.composite = composite;
			this.table = tree;
			this.col1 = treeColumn;
			this.col2 = treeColumn2;
		}

		@Override
		public void controlResized(ControlEvent e){
			Rectangle area = composite.getClientArea();
			Point preferredSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			int width = area.width - 2 * table.getBorderWidth();
			if (preferredSize.y > area.height + table.getHeaderHeight()) {
				Point vBarSize = table.getVerticalBar().getSize();
				width -= vBarSize.x;
			}
			Point oldSize = table.getSize();

			int col1size = col1.getWidth();
			int col2size = width - col1size;

			if (oldSize.x > area.width) {
				col1.setWidth(col1size);
				col2.setWidth(col2size);
				table.setSize(area.width, area.height);
			} else {
				table.setSize(area.width, area.height);
				col1.setWidth(col1size);
				col2.setWidth(col2size);
			}
		}
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
