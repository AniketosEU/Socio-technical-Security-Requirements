/*
* FilterDialog.java
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
package eu.aniketos.wp1.ststool.commitments.actions;

import java.util.List;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
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
import org.eclipse.swt.widgets.Text;
import eu.aniketos.wp1.ststool.diagram.custom.utility.SelectObjectsDialog;

public class FilterDialog extends Dialog {

	private String			windowTitle;
	private Text			txtSelect;
	private String			initialValue;
	private List<String>	selectionList;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public FilterDialog(Shell parentShell, String windowTitle, String initialValue, List<String> selectionList) {

		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.TITLE | SWT.APPLICATION_MODAL);
		setBlockOnOpen(true);
		this.windowTitle = windowTitle;
		this.initialValue = initialValue;
		this.selectionList = selectionList;
	}

	@Override
	protected void configureShell(Shell newShell){

		super.configureShell(newShell);
		newShell.setText(windowTitle);
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

		Label lblNewLabel = new Label(container, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -10);
		fd_lblNewLabel.top = new FormAttachment(0, 15);
		fd_lblNewLabel.left = new FormAttachment(0, 10);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Enter a name filter pattern" + " (* = any string, ? = any character, | = OR separator)" + System.getProperty("line.separator") + "or an empty string for no filtering:");

		Button btnNewButton = new Button(container, SWT.NONE);
		txtSelect = new Text(container, SWT.BORDER);
		FormData fd_txtSelect = new FormData();
		fd_txtSelect.right = new FormAttachment(btnNewButton, -10);
		fd_txtSelect.top = new FormAttachment(lblNewLabel, 10);
		fd_txtSelect.left = new FormAttachment(0, 10);
		txtSelect.setLayoutData(fd_txtSelect);
		txtSelect.setText(initialValue);

		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(lblNewLabel, 8);
		fd_btnNewButton.right = new FormAttachment(100, -10);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Select");
		btnNewButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				try {
					SelectObjectsDialog<String> dialog = new SelectObjectsDialog<String>(getShell(), selectionList, null, "Elements List", "Selected Elements");
					if (dialog.open() == InputDialog.OK) {
						List<String> selected = dialog.getSelectedObjects();
						if (selected.size() > 0) {
							StringBuilder sb = new StringBuilder();
							for (int i = 0; i < selected.size(); i++) {
								if (i > 0) sb.append(" | ");
								sb.append(selected.get(i));
							}
							txtSelect.setText(sb.toString());
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		return container;
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

	private String	result	= null;

	@Override
	protected void okPressed(){

		result = txtSelect.getText().trim();
		super.okPressed();
	}

	public String getText(){

		return result;
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize(){

		return new Point(500, 210);
	}
}
