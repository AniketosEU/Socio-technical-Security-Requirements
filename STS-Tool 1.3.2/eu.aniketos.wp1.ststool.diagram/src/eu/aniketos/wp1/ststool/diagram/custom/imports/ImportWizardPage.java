/*
* ImportWizardPage.java
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
package eu.aniketos.wp1.ststool.diagram.custom.imports;

import java.io.File;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ImportWizardPage extends WizardPage {

	private Text			textInput;
	private Text			textOptupt;
	private Button			btnBrowse;
	private Button			btnOverwriteTheInput;
	private Button			btnBrowse_1;

	private String			input;


	private String			output;
	private boolean		overwrite;
	private final String	finalversion;

	/**
	 * Create the wizard.
	 */
	public ImportWizardPage(String finalversion) {

		super("wizardPage");
		setTitle("Import Wizard");
		setDescription("Import an old diagram");
		this.finalversion = finalversion;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent){

		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FormLayout());

		Label lblSelectTheFile = new Label(container, SWT.NONE);
		FormData fd_lblSelectTheFile = new FormData();
		fd_lblSelectTheFile.right = new FormAttachment(100, -10);
		fd_lblSelectTheFile.top = new FormAttachment(0, 10);
		fd_lblSelectTheFile.left = new FormAttachment(0, 10);
		lblSelectTheFile.setLayoutData(fd_lblSelectTheFile);
		lblSelectTheFile.setText("Select the file to import:");

		btnBrowse = new Button(container, SWT.NONE);
		FormData fd_btnBrowse = new FormData();
		fd_btnBrowse.top = new FormAttachment(lblSelectTheFile, 6);
		fd_btnBrowse.right = new FormAttachment(lblSelectTheFile, 0, SWT.RIGHT);
		btnBrowse.setLayoutData(fd_btnBrowse);
		btnBrowse.setText("Browse");

		textInput = new Text(container, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(btnBrowse, 3, SWT.CENTER);
		fd_text.left = new FormAttachment(0, 10);
		fd_text.right = new FormAttachment(btnBrowse, -6);
		textInput.setLayoutData(fd_text);


		btnOverwriteTheInput = new Button(container, SWT.CHECK);
		FormData fd_btnOverwriteTheInput = new FormData();
		fd_btnOverwriteTheInput.top = new FormAttachment(textInput, 25);
		fd_btnOverwriteTheInput.left = new FormAttachment(lblSelectTheFile, 0, SWT.LEFT);
		btnOverwriteTheInput.setLayoutData(fd_btnOverwriteTheInput);
		btnOverwriteTheInput.setText("Overwrite the input File");


		btnBrowse_1 = new Button(container, SWT.NONE);
		FormData fd_btnBrowse_1 = new FormData();
		fd_btnBrowse_1.top = new FormAttachment(btnOverwriteTheInput, 6);
		fd_btnBrowse_1.right = new FormAttachment(100, -10);
		btnBrowse_1.setLayoutData(fd_btnBrowse_1);
		btnBrowse_1.setText("Browse");

		textOptupt = new Text(container, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(btnBrowse_1, 3, SWT.TOP);
		fd_text_1.left = new FormAttachment(0, 10);
		fd_text_1.right = new FormAttachment(textInput, 0, SWT.RIGHT);
		textOptupt.setLayoutData(fd_text_1);

		Label lblNoteThisWizard = new Label(container, SWT.WRAP);
		FormData fd_lblNoteThisWizard = new FormData();
		fd_lblNoteThisWizard.right = new FormAttachment(100, -10);
		fd_lblNoteThisWizard.top = new FormAttachment(textOptupt, 26);
		fd_lblNoteThisWizard.left = new FormAttachment(0, 10);
		lblNoteThisWizard.setLayoutData(fd_lblNoteThisWizard);
		lblNoteThisWizard.setText("Note: This wizard has been created to import only diagrams created with STS-Tool\nversion 1.1.x or greater");

		Label lblNewLabel = new Label(container, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.bottom = new FormAttachment(btnOverwriteTheInput, -1, SWT.BOTTOM);
		fd_lblNewLabel.left = new FormAttachment(btnOverwriteTheInput, 6);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("(If selected the original file will be modified)");

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(lblNoteThisWizard, 6);
		fd_lblNewLabel_1.left = new FormAttachment(lblSelectTheFile, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Trying to import diagrams of other version may have unexpected results.");


		attachListeners();
		checkErrors();
	}

	private void attachListeners(){

		btnOverwriteTheInput.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				overwrite = btnOverwriteTheInput.getSelection();
				if (overwrite) {
					textOptupt.setEnabled(false);
					btnBrowse_1.setEnabled(false);
				} else {
					textOptupt.setEnabled(true);
					btnBrowse_1.setEnabled(true);
				}
				checkErrors();
			}
		});

		textInput.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e){

				input = textInput.getText().trim();
				if (!btnOverwriteTheInput.getSelection()) {
					setOutputFileName();
				}
				checkErrors();
			}
		});

		textOptupt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e){

				output = textOptupt.getText().trim();
				checkErrors();
			}
		});

		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				dialog.setText("Open");
				String[] filterExt = { "*.ststool_diagram" };
				dialog.setFilterExtensions(filterExt);
				String result = dialog.open();
				if (result != null) {
					textInput.setText(result);
				}
				;

			}
		});

		btnBrowse_1.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setOverwrite(true);
				dialog.setText("Save");
				String[] filterExt = { "*.ststool_diagram" };
				dialog.setFilterExtensions(filterExt);
				String result = dialog.open();
				if (result != null) {
					textOptupt.setText(result);
				}
				;
			}
		});
	}

	private void setOutputFileName(){

		String inputFileName = textInput.getText().trim();
		if (inputFileName == null) inputFileName = "";
		int dotPosition = inputFileName.lastIndexOf('.');
		if (dotPosition == -1) {
			textOptupt.setText("");
		} else {
			textOptupt.setText(inputFileName.substring(0, dotPosition) + "_imported_" + finalversion + inputFileName.substring(dotPosition, inputFileName.length()));
		}
		output = textOptupt.getText().trim();
	}


	@Override
	public void setErrorMessage(String newMessage){

		super.setErrorMessage(newMessage);
		if (newMessage == null && !isPageComplete())
			setPageComplete(true);
		else if (newMessage != null && isPageComplete()) setPageComplete(false);
	}

	protected void checkErrors(){

		if (textInput.getText().trim().length() < 1) {
			setErrorMessage("Input File Path can't be empty");
			return;
		} else {
			File f = new File(textInput.getText().trim());
			if (f.isDirectory()) {
				setErrorMessage("Invalid Input File file location. The specifyed path is a directory");
				return;
			}
			if (f.getParentFile() == null) {
				setErrorMessage("Invalid Input File file location.");
				return;
			}
			if (!f.exists()) {
				setErrorMessage("Invalid Input File: the file dosen't exist.");
				return;
			}
			if (!f.getParentFile().canWrite()) {
				setErrorMessage("Invalid Input File file location. The specifyed path can't be written");
				return;
			}
		}

		if (!btnOverwriteTheInput.getSelection()) {
			if (textOptupt.getText().trim().length() < 1) {
				setErrorMessage("Output File Path cannot be empty");
				return;
			} else {
				File f = new File(textOptupt.getText().trim());
				if (f.isDirectory()) {
					setErrorMessage("Invalid Output File location. The specifyed path is a directory");
					return;
				}
				if (f.getParentFile() == null) {
					setErrorMessage("Invalid Output File location.");
					return;
				}
				if (!f.getParentFile().canWrite()) {
					setErrorMessage("Invalid Output File location. The specifyed path can't be written");
					return;
				}
			}
		}
		setErrorMessage(null);
	}

	public String getInputFileName(){

		return input;
	}


	public String getOutputFileName(){

		return output;
	}


	public boolean isOverwriteInputFile(){

		return overwrite;
	}

}
