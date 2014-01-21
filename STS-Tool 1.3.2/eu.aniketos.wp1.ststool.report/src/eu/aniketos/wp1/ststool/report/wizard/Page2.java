/*
* Page2.java
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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;

public class Page2 extends WizardPage {

	private NodeCheckboxTreeViewer	checkboxTreeViewer;

	private Node							input	= null;

	/**
	 * Create the wizard.
	 */
	public Page2(WizardData wizardData) {
		super("wizardPage");
		setTitle("Sts-Tool Genertion Report Wizard");
		setDescription("");
		input = wizardData.getSelectionViewTree().copy();
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
		lblTitle.setText("Select the chapter to generate :");

		checkboxTreeViewer = new NodeCheckboxTreeViewer(container);
		Tree tree = checkboxTreeViewer.getTree();

		FormData fd_tree = new FormData();
		fd_tree.top = new FormAttachment(lblTitle, 6);
		fd_tree.left = new FormAttachment(0, 10);
		fd_tree.bottom = new FormAttachment(100, -10);
		fd_tree.right = new FormAttachment(100, -10);
		tree.setLayoutData(fd_tree);
		checkboxTreeViewer.setInputNode(input);
	}



	public WizardData storeData(WizardData data){
		data.setSelectionViewTree(input);
		return data;
	}
}
