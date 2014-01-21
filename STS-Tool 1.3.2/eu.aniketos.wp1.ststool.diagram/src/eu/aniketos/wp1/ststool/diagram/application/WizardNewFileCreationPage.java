/*
* WizardNewFileCreationPage.java
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
package eu.aniketos.wp1.ststool.diagram.application;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import eu.aniketos.wp1.ststool.diagram.part.Messages;

/**
 * @generated
 */
public class WizardNewFileCreationPage extends WizardPage {

	/**
	 * @generated
	 */
	private final IStructuredSelection	currentSelection;

	/**
	 * @generated
	 */
	private String								initialFileName;

	/**
	 * @generated
	 */
	private IPath								initialContainerFullPath;

	/**
	 * @generated
	 */
	private Text								fileNameEditor;

	/**
	 * @generated NOT
	 */
	public WizardNewFileCreationPage(String name, IStructuredSelection currentSelection) {

		super(name);
		this.currentSelection = currentSelection;
		String home = org.eclipse.core.resources.ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		if (home != null) {
			initialContainerFullPath = new Path(home);
		}
	}

	/**
	 * @generated
	 */
	protected IStructuredSelection getSelection(){
		return currentSelection;
	}

	/**
	 * @generated
	 */
	public String getFileName(){
		if (fileNameEditor == null) { return initialFileName; }
		IPath path = getFilePath();
		if (path == null || path.isEmpty() || path.hasTrailingSeparator()) { return null; }
		return path.lastSegment();
	}

	/**
	 * @generated
	 */
	public void setFileName(String fileName){
		if (fileNameEditor == null) {
			initialFileName = fileName;
			return;
		}
		setFilePath(getContainerFullPath(), fileName);
	}

	/**
	 * @generated
	 */
	public IPath getContainerFullPath(){
		if (fileNameEditor == null) { return initialContainerFullPath; }
		IPath path = getFilePath();
		if (path == null || path.isEmpty()) { return null; }
		if (path.hasTrailingSeparator()) { return path; }
		path = path.removeLastSegments(1);
		if (path.isEmpty()) { return null; }
		return path.addTrailingSeparator();
	}

	/**
	 * @generated
	 */
	public void setContainerFullPath(IPath containerPath){
		if (fileNameEditor == null) {
			initialContainerFullPath = containerPath;
			return;
		}
		setFilePath(containerPath, getFileName());
	}

	/**
	 * @generated
	 */
	protected IPath getFilePath(){
		String fileName = fileNameEditor.getText().trim();
		if (fileName.length() == 0) { return null; }
		return new Path(fileNameEditor.getText());
	}

	/**
	 * @generated
	 */
	protected void setFilePath(IPath containerPath,String fileName){
		if (fileName == null) {
			fileName = ""; //$NON-NLS-1$
		} else {
			fileName = fileName.trim();
		}
		if (containerPath == null) {
			fileNameEditor.setText(fileName);
		} else {
			if (!containerPath.hasTrailingSeparator()) {
				containerPath = containerPath.addTrailingSeparator();
			}
			IPath path = fileName.length() == 0 ? containerPath : containerPath.append(fileName);
			fileNameEditor.setText(path.toOSString());
		}
		setPageComplete(validatePage());
	}

	/**
	 * @generated
	 */
	public void createControl(Composite parent){
		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayout(new GridLayout(2, false));
		Label label = new Label(plate, SWT.NONE);
		label.setText(Messages.WizardNewFileCreationPage_FileLabel);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 2, 1));
		fileNameEditor = new Text(plate, SWT.SINGLE | SWT.BORDER);
		fileNameEditor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button button = new Button(plate, SWT.PUSH);
		button.setText(Messages.WizardNewFileCreationPage_BrowseButton);
		button.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

		// logic
		fileNameEditor.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e){
				setPageComplete(validatePage());
			}
		});
		button.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e){
				FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
				dialog.setText(Messages.WizardNewFileCreationPage_SelectNewFileDialog);
				dialog.setFileName(getFileName());
				String fileName = dialog.open();
				if (fileName != null) {
					fileNameEditor.setText(fileName);
					setPageComplete(validatePage());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e){
			}
		});

		// init
		setFilePath(initialContainerFullPath, initialFileName);
		setControl(plate);
	}

	/**
	 * @generated
	 */
	protected boolean validatePage(){
		String fileName = fileNameEditor.getText().trim();
		if (fileName.length() == 0) {
			setErrorMessage(Messages.WizardNewFileCreationPage_EmptyFileNameError);
			return false;
		}
		if (!new Path("").isValidPath(fileName)) { //$NON-NLS-1$
			setErrorMessage(Messages.WizardNewFileCreationPage_InvalidFileNameError);
			return false;
		}
		setErrorMessage(null);
		return true;
	}
}
