/*
* ExoprtImageWizardPage.java
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
package eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator;


import java.io.File;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExoprtImageWizardPage extends WizardPage {

	private String		path								= "";
	private String[]	outputFormats					= { "" };
	private int			selectedOutputFormats		= 0;
	private boolean		socialViewSelected			= false;
	private boolean		informationViewSelected		= false;
	private boolean		authorisationViewSelected	= false;

	private Text		txtFileOutput;
	private Combo		cmbOutputFormat;
	private Button		cbSocial;
	private Button		cbInformation;
	private Button		cbAutorisation;

	private Adapter	adapter							= new Adapter();

	/**
	 * Create the wizard.
	 */
	public ExoprtImageWizardPage() {
		super("");
		setTitle("Export Image");
		setDescription("Export Current diagram to Image");
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

		Label lblOutputPath = new Label(container, SWT.NONE);
		FormData fd_lblOutputPath = new FormData();
		fd_lblOutputPath.right = new FormAttachment(100, -10);
		fd_lblOutputPath.top = new FormAttachment(0, 10);
		fd_lblOutputPath.left = new FormAttachment(0, 10);
		lblOutputPath.setLayoutData(fd_lblOutputPath);
		lblOutputPath.setText("Select Output Path:");

		Button btnBrowse = new Button(container, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(lblOutputPath, 8);
		fd_btnNewButton.right = new FormAttachment(lblOutputPath, 0, SWT.RIGHT);
		btnBrowse.setLayoutData(fd_btnNewButton);
		btnBrowse.setText("Browse");


		txtFileOutput = new Text(container, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(lblOutputPath, 10);
		fd_text.right = new FormAttachment(btnBrowse, -10);
		fd_text.left = new FormAttachment(lblOutputPath, 0, SWT.LEFT);
		txtFileOutput.setLayoutData(fd_text);
		txtFileOutput.setText(path);

		cmbOutputFormat = new Combo(container, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(100, -384);
		fd_combo.left = new FormAttachment(0, 10);
		cmbOutputFormat.setLayoutData(fd_combo);

		cmbOutputFormat.setItems(outputFormats);
		cmbOutputFormat.select(selectedOutputFormats);

		Label lblNewLabel = new Label(container, SWT.NONE);
		fd_combo.top = new FormAttachment(lblNewLabel, 10);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.right = new FormAttachment(100, -10);
		fd_lblNewLabel.top = new FormAttachment(txtFileOutput, 15);
		fd_lblNewLabel.left = new FormAttachment(lblOutputPath, 0, SWT.LEFT);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Output Format:");

		Group grpViews = new Group(container, SWT.NONE);
		grpViews.setText("Views");
		grpViews.setLayout(new FillLayout(SWT.VERTICAL));
		FormData fd_grpViews = new FormData();
		fd_grpViews.height = 75;
		fd_grpViews.top = new FormAttachment(cmbOutputFormat, 15);
		fd_grpViews.left = new FormAttachment(0, 10);
		fd_grpViews.right = new FormAttachment(0, 190);
		grpViews.setLayoutData(fd_grpViews);

		cbSocial = new Button(grpViews, SWT.CHECK);
		cbSocial.setText("Social View");
		cbSocial.setSelection(socialViewSelected);

		cbInformation = new Button(grpViews, SWT.CHECK);
		cbInformation.setText("Information View");
		cbInformation.setSelection(informationViewSelected);

		cbAutorisation = new Button(grpViews, SWT.CHECK);
		cbAutorisation.setText("Authorisation View");
		cbAutorisation.setSelection(authorisationViewSelected);

		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				DirectoryDialog dialog = new DirectoryDialog(getShell());

				if (txtFileOutput.getText() != null) dialog.setFilterPath(txtFileOutput.getText().trim()); // Windows specific
				String result = dialog.open();
				if (result != null) {
					txtFileOutput.setText(result);
				}
				;
			}
		});

		txtFileOutput.addModifyListener(adapter);
		cbSocial.addSelectionListener(adapter);
		cbInformation.addSelectionListener(adapter);
		cbAutorisation.addSelectionListener(adapter);
		chekErrors();
	}

	protected void chekErrors(){

		if (txtFileOutput.getText().trim().length() < 1) {
			setErrorMessage("Location cannot be empty");
			return;
		} else {
			File f = new File(txtFileOutput.getText().trim());
			if (!f.exists()) {
				setErrorMessage("Invalid Location. The specifyed path don't exist");
				return;
			} else if (!f.isDirectory()) {
				setErrorMessage("Invalid Location. The specifyed path must be a directory");
				return;
			} else if (!f.canWrite()) {
				setErrorMessage("Invalid Location. The specifyed path can't be written ");
				return;
			}
		}
		if (!cbSocial.getSelection() && !cbInformation.getSelection() && !cbAutorisation.getSelection()) {
			setErrorMessage("Select at least one view");
			return;
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
			chekErrors();
		}

		@Override
		public void modifyText(ModifyEvent e){
			chekErrors();
		}
	}


	public String getPath(){
		return txtFileOutput.getText().trim();
	}

	public String getSelectedOutputFormats(){
		return cmbOutputFormat.getText();
	}


	public boolean isSocialViewSelected(){
		return cbSocial.getSelection();
	}


	public boolean isInformationViewSelected(){
		return cbInformation.getSelection();
	}


	public boolean isAuthorisationViewSelected(){
		return cbAutorisation.getSelection();
	}


	public void setPath(String path){
		this.path = path;
	}


	public void setOutputFormats(String[] outputFormats){
		this.outputFormats = outputFormats;
	}


	public void setInitialIndex(int index){
		this.selectedOutputFormats = index;
	}


	public void setSocialViewSelected(boolean socialViewSelected){
		this.socialViewSelected = socialViewSelected;
	}


	public void setInformationViewSelected(boolean informationViewSelected){
		this.informationViewSelected = informationViewSelected;
	}


	public void setAuthorisationViewSelected(boolean authorisationViewSelected){
		this.authorisationViewSelected = authorisationViewSelected;
	}



}
