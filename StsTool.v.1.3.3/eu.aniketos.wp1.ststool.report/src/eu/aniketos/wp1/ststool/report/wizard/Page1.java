/*
* Page1.java
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
package eu.aniketos.wp1.ststool.report.wizard;

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

public class Page1 extends WizardPage {

	private Text			txtTitle;
	private Text			txtAuthor;
	private Text			txtIstsitution;
	private Text			txtPdfLocation;
	private Text			txtRtfLocation;
	private Button			btnPdf;
	private Button			btnRtf;

	private Adapter		adapter	= new Adapter();

	private WizardData	wizardData;

	/**
	 * Create the wizard.
	 */
	public Page1(WizardData wizardData) {
		super("wizardPage");
		setTitle("Sts-Tool Genertion Report Wizard");
		setDescription("");
		this.wizardData = wizardData;
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

		Label lblTitle = new Label(container, SWT.NONE);
		FormData fd_lblTitle = new FormData();
		fd_lblTitle.right = new FormAttachment(100, -10);
		fd_lblTitle.top = new FormAttachment(0, 10);
		fd_lblTitle.left = new FormAttachment(0, 10);
		lblTitle.setLayoutData(fd_lblTitle);
		lblTitle.setText("Report Title :");

		txtTitle = new Text(container, SWT.BORDER);
		FormData fd_txtTitle = new FormData();
		fd_txtTitle.right = new FormAttachment(100, -10);
		fd_txtTitle.top = new FormAttachment(lblTitle, 6);
		fd_txtTitle.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		txtTitle.setLayoutData(fd_txtTitle);
		txtTitle.setText(wizardData.getReportTitle());

		Label lblAuthor = new Label(container, SWT.NONE);
		FormData fd_lblAuthor = new FormData();
		fd_lblAuthor.right = new FormAttachment(100, -10);
		fd_lblAuthor.top = new FormAttachment(txtTitle, 10);
		fd_lblAuthor.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		lblAuthor.setLayoutData(fd_lblAuthor);
		lblAuthor.setText("Author :");

		txtAuthor = new Text(container, SWT.BORDER);
		FormData fd_txtAuthor = new FormData();
		fd_txtAuthor.right = new FormAttachment(100, -10);
		fd_txtAuthor.top = new FormAttachment(lblAuthor, 6);
		fd_txtAuthor.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		txtAuthor.setLayoutData(fd_txtAuthor);
		txtAuthor.setText(wizardData.getReportAuthor());
		Label lblIstittution = new Label(container, SWT.NONE);
		FormData fd_lblIstittution = new FormData();
		fd_lblIstittution.right = new FormAttachment(100, -10);
		fd_lblIstittution.top = new FormAttachment(txtAuthor, 10);
		fd_lblIstittution.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		lblIstittution.setLayoutData(fd_lblIstittution);
		lblIstittution.setText("Institution :");

		txtIstsitution = new Text(container, SWT.BORDER);
		FormData fd_txtIstsitution = new FormData();
		fd_txtIstsitution.right = new FormAttachment(100, -10);
		fd_txtIstsitution.top = new FormAttachment(lblIstittution, 6);
		fd_txtIstsitution.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		txtIstsitution.setLayoutData(fd_txtIstsitution);
		txtIstsitution.setText(wizardData.getReportIstitution());

		final Button btnBrowsePdf = new Button(container, SWT.NONE);
		FormData fd_btnBrowsePdf = new FormData();
		fd_btnBrowsePdf.right = new FormAttachment(txtTitle, 0, SWT.RIGHT);
		btnBrowsePdf.setLayoutData(fd_btnBrowsePdf);
		btnBrowsePdf.setText("Browse");

		final Button btnBrowseRtf = new Button(container, SWT.NONE);
		FormData fd_btnBrowseRtf = new FormData();
		fd_btnBrowseRtf.right = new FormAttachment(txtTitle, 0, SWT.RIGHT);
		btnBrowseRtf.setLayoutData(fd_btnBrowseRtf);
		btnBrowseRtf.setText("Browse");

		btnPdf = new Button(container, SWT.CHECK);
		btnPdf.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){
				boolean selected = ((Button) e.getSource()).getSelection();
				txtPdfLocation.setEnabled(selected);
				btnBrowsePdf.setEnabled(selected);
			}
		});


		FormData fd_btnPdf = new FormData();
		fd_btnPdf.right = new FormAttachment(100, -10);
		fd_btnPdf.top = new FormAttachment(txtIstsitution, 15);
		fd_btnPdf.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		btnPdf.setLayoutData(fd_btnPdf);
		btnPdf.setText("Generate PDF");



		txtPdfLocation = new Text(container, SWT.BORDER);
		fd_btnBrowsePdf.top = new FormAttachment(txtPdfLocation, -2, SWT.TOP);
		FormData fd_txtPdfLocation = new FormData();
		fd_txtPdfLocation.right = new FormAttachment(btnBrowsePdf, -10);
		fd_txtPdfLocation.top = new FormAttachment(btnPdf, 6);
		fd_txtPdfLocation.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		txtPdfLocation.setLayoutData(fd_txtPdfLocation);


		btnRtf = new Button(container, SWT.CHECK);

		FormData fd_btnRtf = new FormData();
		fd_btnRtf.right = new FormAttachment(100, -10);
		fd_btnRtf.top = new FormAttachment(txtPdfLocation, 10);
		fd_btnRtf.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		btnRtf.setLayoutData(fd_btnRtf);
		btnRtf.setText("Generate RTF (Not yet supported)");
		btnRtf.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){
				boolean selected = ((Button) e.getSource()).getSelection();
				txtRtfLocation.setEnabled(selected);
				btnBrowseRtf.setEnabled(selected);
			}
		});

		txtRtfLocation = new Text(container, SWT.BORDER);
		fd_btnBrowseRtf.top = new FormAttachment(txtRtfLocation, -2, SWT.TOP);
		FormData fd_txtRtfLocation = new FormData();
		fd_txtRtfLocation.top = new FormAttachment(btnRtf, 6);
		fd_txtRtfLocation.right = new FormAttachment(btnBrowseRtf, -10);
		fd_txtRtfLocation.left = new FormAttachment(lblTitle, 0, SWT.LEFT);
		txtRtfLocation.setLayoutData(fd_txtRtfLocation);


		btnBrowsePdf.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setText("Save");
				dialog.setOverwrite(true);
				String[] filterExt = { "*.pdf" };
				dialog.setFilterExtensions(filterExt);
				if (txtPdfLocation.getText() != null) {
					File f = new File(txtPdfLocation.getText().trim());
					if (f != null && f.getParent() != null) {
						dialog.setFilterPath(new File(txtPdfLocation.getText().trim()).getParent()); // Windows specific
					}
				}
				String result = dialog.open();
				if (result != null) {
					txtPdfLocation.setText(result);
				}
				;
			}
		});


		btnBrowseRtf.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setText("Save");
				dialog.setOverwrite(true);
				String[] filterExt = { "*.rtf" };
				dialog.setFilterExtensions(filterExt);
				if (txtRtfLocation.getText() != null) {
					File f = new File(txtPdfLocation.getText().trim());
					if (f != null && f.getParent() != null) {
						dialog.setFilterPath(new File(txtRtfLocation.getText().trim()).getParent());
					}
				}
				String result = dialog.open();
				if (result != null) {
					txtRtfLocation.setText(result);
				}
			}
		});

		if (wizardData.isPdfOutput()) {
			btnPdf.setSelection(true);
			txtPdfLocation.setEditable(true);
			txtPdfLocation.setText(wizardData.getPdfPath());
			btnBrowsePdf.setEnabled(true);
		} else {
			btnPdf.setSelection(false);
			txtPdfLocation.setEditable(false);
			txtPdfLocation.setText(wizardData.getPdfPath());
			btnBrowsePdf.setEnabled(false);
		}

		if (wizardData.isRtfOutput()) {
			btnRtf.setSelection(true);
			txtRtfLocation.setEditable(true);
			txtRtfLocation.setText(wizardData.getRtfPath());
			btnBrowseRtf.setEnabled(true);
		} else {
			btnRtf.setSelection(false);
			txtRtfLocation.setEditable(false);
			txtRtfLocation.setText(wizardData.getRtfPath());
			btnBrowseRtf.setEnabled(false);
		}
		btnRtf.setEnabled(false);

		checkErrors();
		txtTitle.addModifyListener(adapter);
		txtPdfLocation.addModifyListener(adapter);
		txtRtfLocation.addModifyListener(adapter);
		btnPdf.addSelectionListener(adapter);
		btnRtf.addSelectionListener(adapter);
	}

	protected void checkErrors(){

		if (txtTitle.getText().trim().length() < 1) {
			setErrorMessage("Invalid report Title");
			return;
		}
		if (!btnPdf.getSelection() && !btnRtf.getSelection()) {
			setErrorMessage("Select at least one output format");
			return;
		}
		if (btnPdf.getSelection()) {
			if (txtPdfLocation.getText().trim().length() < 1) {
				setErrorMessage("PDF Output Path cannot be empty");
				return;
			} else {
				File f = new File(txtPdfLocation.getText().trim());
				if (f.isDirectory()) {
					setErrorMessage("Invalid PDF file location. The specifyed path is a directory");
					return;
				}
				if (f.getParentFile() == null) {
					setErrorMessage("Invalid PDF file location.");
					return;
				}
				if (!f.getParentFile().canWrite()) {
					setErrorMessage("Invalid PDF file location. The specifyed path can't be written");
					return;
				}
			}
		}
		if (btnRtf.getSelection()) {
			if (txtRtfLocation.getText().trim().length() < 1) {
				setErrorMessage("RTF Output Path cannot be empty");
				return;
			} else {
				File f = new File(txtRtfLocation.getText().trim());
				if (f.isDirectory()) {
					setErrorMessage("Invalid RTF file location. The specifyed path is a directory");
					return;
				}
				if (f.getParentFile() == null) {
					setErrorMessage("Invalid RTF file location.");
					return;
				}
				if (!f.getParentFile().canWrite()) {
					setErrorMessage("Invalid RTF file location. The specifyed path can't be written");
					return;
				}
			}
		}


		setErrorMessage(null);
	}


	@Override
	public void setErrorMessage(String newMessage){

		super.setErrorMessage(newMessage);
		if (newMessage == null && !isPageComplete())
			setPageComplete(true);
		else if (newMessage != null && isPageComplete()) setPageComplete(false);
	}



	private class Adapter extends SelectionAdapter implements ModifyListener {

		@Override
		public void widgetSelected(SelectionEvent e){
			checkErrors();
		}

		@Override
		public void modifyText(ModifyEvent e){
			checkErrors();
		}
	}

	public WizardData storeData(WizardData data){
		data.setReportTitle(txtTitle.getText().trim());
		data.setReportAuthor(txtAuthor.getText().trim());
		data.setReportIstitution(txtIstsitution.getText().trim());
		data.setPdfOutput(btnPdf.getSelection());
		data.setPdfPath(txtPdfLocation.getText().trim());
		data.setRtfOutput(btnRtf.getSelection());
		data.setRtfPath(txtRtfLocation.getText().trim());
		return data;
	}

}
