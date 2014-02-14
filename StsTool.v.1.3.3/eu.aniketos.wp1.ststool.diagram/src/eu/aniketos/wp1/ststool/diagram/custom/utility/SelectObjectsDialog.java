/*
* SelectObjectsDialog.java
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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SelectObjectsDialog<T> extends Dialog {

	private String					elementsListLabel				= "Objects";
	private String					selectedElementsListLabel	= "Objects";

	private ColoredListViewer	objectsList;
	private ColoredListViewer	selectedObjectsList;

	private List<T>				objects;
	private List<T>				selectedObjects;
	private ViewerSorter			sorter;

	private Dimension				shellInitalSize				= null;

	private LabelProvider		labelProvider					= new LabelProvider() {

																				@Override
																				public String getText(Object element){
																					return element.toString();
																				}
																			};


	public void setLabelProvider(LabelProvider labelProvider){
		this.labelProvider = labelProvider;
	}

	public void setSorter(ViewerSorter sorter){
		this.sorter = sorter;
		if (objectsList != null) objectsList.setSorter(sorter);
		if (selectedObjectsList != null) selectedObjectsList.setSorter(sorter);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectObjectsDialog(Shell parentShell, List<T> objects, List<T> selectedObjects, String elementsListLabel, String selectedElementsListLabel) {
		this(parentShell, objects, selectedObjects, elementsListLabel, selectedElementsListLabel, null);
	}

	public SelectObjectsDialog(Shell parentShell, List<T> objects, List<T> selectedObjects, String elementsListLabel, String selectedElementsListLabel, Dimension shellInitialSize) {
		super(parentShell);
		this.shellInitalSize = shellInitialSize;
		//setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setShellStyle(SWT.RESIZE | SWT.APPLICATION_MODAL);
		this.objects = new ArrayList<T>();
		for (T o : objects) {
			this.objects.add(o);
		}
		if (selectedObjects == null) {
			this.selectedObjects = new ArrayList<T>();
		} else {
			this.selectedObjects = selectedObjects;
		}

		if (this.selectedObjects.size() > 0) this.objects.removeAll(selectedObjects);

		if (elementsListLabel != null) this.elementsListLabel = elementsListLabel;
		if (selectedElementsListLabel != null) this.selectedElementsListLabel = selectedElementsListLabel;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent){

		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());

		Label lblObjectsName = new Label(container, SWT.CENTER);
		FormData fd_lblObjectsName = new FormData();
		fd_lblObjectsName.top = new FormAttachment(0, 10);
		fd_lblObjectsName.right = new FormAttachment(50, -50);
		fd_lblObjectsName.left = new FormAttachment(0, 10);
		lblObjectsName.setLayoutData(fd_lblObjectsName);
		lblObjectsName.setText(elementsListLabel + " List");

		Label lblSelObjectsName = new Label(container, SWT.CENTER);
		FormData fd_lblSelObjectsName = new FormData();
		fd_lblSelObjectsName.top = new FormAttachment(0, 10);
		fd_lblSelObjectsName.left = new FormAttachment(50, 50);
		fd_lblSelObjectsName.right = new FormAttachment(100, -10);
		lblSelObjectsName.setLayoutData(fd_lblSelObjectsName);
		lblSelObjectsName.setText(selectedElementsListLabel);

		objectsList = new ColoredListViewer<T>(container);
		FormData fd_actorsList = new FormData();
		fd_actorsList.top = new FormAttachment(lblObjectsName, 10);
		fd_actorsList.bottom = new FormAttachment(100, -10);
		fd_actorsList.right = new FormAttachment(50, -50);
		fd_actorsList.left = new FormAttachment(0, 10);
		objectsList.getComposite().setLayoutData(fd_actorsList);

		objectsList.setLabelProvider(labelProvider);
		if (sorter != null) objectsList.setSorter(sorter);
		objectsList.setInput(objects);
		objectsList.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event){
				if (event.getSelection() instanceof StructuredSelection) {
					StructuredSelection ss = (StructuredSelection) event.getSelection();
					add((T) ss.getFirstElement());
				}
			}
		});


		selectedObjectsList = new ColoredListViewer<T>(container);
		FormData fd_selectedActorList = new FormData();
		fd_selectedActorList.top = new FormAttachment(lblSelObjectsName, 10);
		fd_selectedActorList.left = new FormAttachment(50, 50);
		fd_selectedActorList.right = new FormAttachment(100, -10);
		fd_selectedActorList.bottom = new FormAttachment(100, -10);
		selectedObjectsList.getComposite().setLayoutData(fd_selectedActorList);

		selectedObjectsList.setLabelProvider(labelProvider);
		if (sorter != null) selectedObjectsList.setSorter(sorter);
		selectedObjectsList.setInput(selectedObjects);
		selectedObjectsList.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event){

				if (event.getSelection() instanceof StructuredSelection) {
					StructuredSelection ss = (StructuredSelection) event.getSelection();
					remove((T) ss.getFirstElement());
				}
			}
		});



		/*selectedObjectsList = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		org.eclipse.swt.widgets.List selectedActorsListlist = selectedObjectsList.getList();
		FormData fd_selectedActorList = new FormData();
		fd_selectedActorList.top = new FormAttachment(lblSelObjectsName, 10);
		fd_selectedActorList.left = new FormAttachment(50, 50);
		fd_selectedActorList.right = new FormAttachment(100, -10);
		fd_selectedActorList.bottom = new FormAttachment(100, -10);
		selectedActorsListlist.setLayoutData(fd_selectedActorList);

		selectedObjectsList.setContentProvider(new ArrayContentProvider());
		selectedObjectsList.setLabelProvider(labelProvider);
		selectedObjectsList.setInput(selectedObjects);
		selectedObjectsList.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {

				if (event.getSelection() instanceof StructuredSelection) {
					StructuredSelection ss = (StructuredSelection) event.getSelection();
					remove((T) ss.getFirstElement());
				}
			}
		});*/

		Button butAddAll = new Button(container, SWT.NONE);
		FormData fd_butAddAll = new FormData();
		fd_butAddAll.left = new FormAttachment(50, -40);
		fd_butAddAll.width = 80;
		fd_butAddAll.top = new FormAttachment(50, 10);
		butAddAll.setLayoutData(fd_butAddAll);
		butAddAll.setText("Add All");
		butAddAll.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				add((List<T>) objectsList.getInput());
			}
		});


		Button butRemAll = new Button(container, SWT.NONE);
		FormData fd_butRemAll = new FormData();
		fd_butRemAll.left = new FormAttachment(50, -40);
		fd_butRemAll.width = 80;
		fd_butRemAll.top = new FormAttachment(butAddAll, 10);
		butRemAll.setLayoutData(fd_butRemAll);
		butRemAll.setText("Remove all");
		butRemAll.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				remove((List<T>) selectedObjectsList.getInput());
			}
		});


		Button butRem = new Button(container, SWT.NONE);
		FormData fd_butRem = new FormData();
		fd_butRem.width = 80;
		fd_butRem.left = new FormAttachment(50, -40);
		fd_butRem.bottom = new FormAttachment(butAddAll, -10);
		fd_butRem.width = 80;
		butRem.setLayoutData(fd_butRem);
		butRem.setText("<");
		butRem.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				if (selectedObjectsList.getSelection() instanceof StructuredSelection) {
					StructuredSelection ss = (StructuredSelection) selectedObjectsList.getSelection();
					remove((List<T>) ss.toList());
				}
			}
		});

		Button butAdd = new Button(container, SWT.NONE);
		butAdd.setText(">");
		FormData fd_butAdd = new FormData();
		fd_butAdd.bottom = new FormAttachment(butRem, -10);
		fd_butAdd.width = 80;
		fd_butAdd.left = new FormAttachment(50, -40);
		butAdd.setLayoutData(fd_butAdd);

		butAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				if (objectsList.getSelection() instanceof StructuredSelection) {
					StructuredSelection ss = (StructuredSelection) objectsList.getSelection();
					add((List<T>) ss.toList());
				}
			}
		});

		return container;
	}

	private void add(List<T> a){

		selectedObjects.addAll(a);
		objects.removeAll(a);
		updateList();
	}

	private void remove(List<T> a){

		objects.addAll(a);
		selectedObjects.removeAll(a);
		updateList();
	}

	private void add(T a){

		selectedObjects.add(a);
		objects.remove(a);
		updateList();
	}

	private void remove(T a){

		objects.add(a);
		selectedObjects.remove(a);
		updateList();
	}

	private void updateList(){

		selectedObjectsList.setInput(selectedObjects);
		objectsList.setInput(objects);

	}


	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent){

		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize(){

		if (shellInitalSize != null) { return new Point(shellInitalSize.width, shellInitalSize.height); }
		return new Point(450, 300);
	}

	@Override
	public int open(){

		return super.open();
	}

	public List<T> getSelectedObjects(){

		return selectedObjects;
	}

}
