/*
* LicenseDialog.java
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
package eu.aniketos.wp1.ststool.diagram.custom.shells;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class LicenseDialog extends Dialog {

	private boolean			licenseAccepted	= false;

	private Text				text;
	private Button				check;
	private Button				butAccept;
	private Button				butDecline;


	private Point				shellSize			= new Point(1024, 768);
	final private int			BORDER				= 10;
	final private int			bottomHeight		= 90;
	final private Dimension	buttonSize			= new Dimension(100, 25);
	private Dimension			cA						= new Dimension();

	/**
	 * @param parent
	 *           the parent for this Dialog
	 */
	public LicenseDialog(Shell parent) {

		super(parent);
	}

	/**
	 * @param parent
	 *           the parent for this Dialog
	 * @param style
	 *           the style for the dialog
	 */
	public LicenseDialog(Shell parent, int style) {

		super(parent, style);
	}


	/**
	 * Makes the dialog visible.
	 * 
	 * @param licenseFile
	 *           the text that have to be displayed as a license text and must be accepted
	 * 
	 * @return true if the license agreement is accepted, otherwise false
	 */
	public boolean open(String licenseFile){

		Shell parent = getParent();
		final Shell shell = new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL); //| SWT.RESIZE);
		shell.setText("License Agreement");

		shell.setLayout(null);

		int XXX = 5;

		int screenWidth = Display.getCurrent().getBounds().width;
		int screenHeight = Display.getCurrent().getBounds().height;
		if (shellSize.x > screenWidth - (screenWidth / XXX)) shellSize.x = screenWidth - (screenWidth / XXX);
		if (shellSize.y > screenHeight - (screenWidth / XXX)) shellSize.y = screenHeight - (screenHeight / XXX);

		shell.setSize(shellSize);
		shell.setLocation((screenWidth - shellSize.x) / 2, (screenHeight - shellSize.y) / 2);

		cA = new Dimension(shell.getClientArea().width, shell.getClientArea().height);

		text = new Text(shell, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.BORDER | SWT.V_SCROLL); //SWT.CENTER|
		text.setText(licenseFile);
		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		FontData[] fontData = text.getFont().getFontData();
		for (int i = 0; i < fontData.length; ++i)
			fontData[i].setHeight(12);

		final Font newFont = new Font(Display.getCurrent(), fontData);
		text.setFont(newFont);

		// Since you created the font, you must dispose it
		text.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e){

				newFont.dispose();
			}
		});


		check = new Button(shell, SWT.CHECK | SWT.CENTER);
		check.setText("I have read the license agreement and I accept all the terms in it.");
		int checkWidth = (check.computeSize(SWT.DEFAULT, SWT.DEFAULT).x) + 5;

		butAccept = new Button(shell, SWT.PUSH);
		butAccept.setText("I Agree");
		butAccept.setEnabled(false);

		butDecline = new Button(shell, SWT.PUSH);
		butDecline.setText("Decline");

		text.setBounds(new Rectangle(BORDER, BORDER, cA.width - BORDER - text.getVerticalBar().getSize().x, cA.height - bottomHeight));
		check.setBounds(new Rectangle(cA.width - checkWidth - BORDER, cA.height - bottomHeight + 20, checkWidth, 20));
		butAccept.setBounds(new Rectangle(cA.width - (BORDER * 2) - (buttonSize.width * 2), cA.height - bottomHeight + 40 + BORDER, buttonSize.width, buttonSize.height));
		butDecline.setBounds(new Rectangle(cA.width - BORDER - buttonSize.width, cA.height - bottomHeight + 40 + BORDER, buttonSize.width, buttonSize.height));

		butAccept.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				licenseAccepted = true;
				shell.dispose();
			}
		});

		butDecline.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				shell.dispose();
			}
		});

		check.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				butAccept.setEnabled(check.getSelection());
			}
		});

		shell.open();

		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}

		return licenseAccepted;
	}
}
