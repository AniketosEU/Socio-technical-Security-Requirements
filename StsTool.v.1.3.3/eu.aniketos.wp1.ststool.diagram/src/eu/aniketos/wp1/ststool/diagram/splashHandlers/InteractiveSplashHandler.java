/*
* InteractiveSplashHandler.java
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
package eu.aniketos.wp1.ststool.diagram.splashHandlers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.splash.AbstractSplashHandler;

/**
 * @since 3.3
 * 
 */
public class InteractiveSplashHandler extends AbstractSplashHandler {

	private final static int	F_LABEL_HORIZONTAL_INDENT	= 175;

	private final static int	F_BUTTON_WIDTH_HINT			= 80;

	private final static int	F_TEXT_WIDTH_HINT				= 175;

	private final static int	F_COLUMN_COUNT					= 3;

	private Composite				fCompositeLogin;

	private Text					fTextUsername;

	private Text					fTextPassword;

	private Button					fButtonOK;

	private Button					fButtonCancel;

	private boolean				fAuthenticated;

	/**
	 * 
	 */
	public InteractiveSplashHandler() {

		fCompositeLogin = null;
		fTextUsername = null;
		fTextPassword = null;
		fButtonOK = null;
		fButtonCancel = null;
		fAuthenticated = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.splash.AbstractSplashHandler#init(org.eclipse.swt.widgets
	 * .Shell)
	 */
	@Override
	public void init(final Shell splash){

		// Store the shell
		super.init(splash);
		// Configure the shell layout
		configureUISplash();
		// Create UI
		createUI();
		// Create UI listeners
		createUIListeners();
		// Force the splash screen to layout
		splash.layout(true);
		// Keep the splash screen visible and prevent the RCP application from 
		// loading until the close button is clicked.
		doEventLoop();
	}

	/**
	 * 
	 */
	private void doEventLoop(){

		Shell splash = getSplash();
		while (fAuthenticated == false) {
			if (splash.getDisplay().readAndDispatch() == false) {
				splash.getDisplay().sleep();
			}
		}
	}

	/**
	 * 
	 */
	private void createUIListeners(){

		// Create the OK button listeners
		createUIListenersButtonOK();
		// Create the cancel button listeners
		createUIListenersButtonCancel();
	}

	/**
	 * 
	 */
	private void createUIListenersButtonCancel(){

		fButtonCancel.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				handleButtonCancelWidgetSelected();
			}
		});
	}

	/**
	 * 
	 */
	private void handleButtonCancelWidgetSelected(){

		// Abort the loading of the RCP application
		getSplash().getDisplay().close();
		System.exit(0);
	}

	/**
	 * 
	 */
	private void createUIListenersButtonOK(){

		fButtonOK.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e){

				handleButtonOKWidgetSelected();
			}
		});
	}

	/**
	 * 
	 */
	private void handleButtonOKWidgetSelected(){

		String username = fTextUsername.getText();
		String password = fTextPassword.getText();
		// Aunthentication is successful if a user provides any username and
		// any password
		if ((username.length() > 0) && (password.length() > 0)) {
			fAuthenticated = true;
		} else {
			MessageDialog.openError(getSplash(), "Authentication Failed", //$NON-NLS-1$
			"A username and password must be specified to login."); //$NON-NLS-1$
		}
	}

	/**
	 * 
	 */
	private void createUI(){

		// Create the login panel
		createUICompositeLogin();
		// Create the blank spanner
		createUICompositeBlank();
		// Create the user name label
		createUILabelUserName();
		// Create the user name text widget
		createUITextUserName();
		// Create the password label
		createUILabelPassword();
		// Create the password text widget
		createUITextPassword();
		// Create the blank label
		createUILabelBlank();
		// Create the OK button
		createUIButtonOK();
		// Create the cancel button
		createUIButtonCancel();
	}

	/**
	 * 
	 */
	private void createUIButtonCancel(){

		// Create the button
		fButtonCancel = new Button(fCompositeLogin, SWT.PUSH);
		fButtonCancel.setText("Cancel"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_BUTTON_WIDTH_HINT;
		data.verticalIndent = 10;
		fButtonCancel.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUIButtonOK(){

		// Create the button
		fButtonOK = new Button(fCompositeLogin, SWT.PUSH);
		fButtonOK.setText("OK"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_BUTTON_WIDTH_HINT;
		data.verticalIndent = 10;
		fButtonOK.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUILabelBlank(){

		Label label = new Label(fCompositeLogin, SWT.NONE);
		label.setVisible(false);
	}

	/**
	 * 
	 */
	private void createUITextPassword(){

		// Create the text widget
		int style = SWT.PASSWORD | SWT.BORDER;
		fTextPassword = new Text(fCompositeLogin, style);
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_TEXT_WIDTH_HINT;
		data.horizontalSpan = 2;
		fTextPassword.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUILabelPassword(){

		// Create the label
		Label label = new Label(fCompositeLogin, SWT.NONE);
		label.setText("&Password:"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData();
		data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUITextUserName(){

		// Create the text widget
		fTextUsername = new Text(fCompositeLogin, SWT.BORDER);
		// Configure layout data
		GridData data = new GridData(SWT.NONE, SWT.NONE, false, false);
		data.widthHint = F_TEXT_WIDTH_HINT;
		data.horizontalSpan = 2;
		fTextUsername.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUILabelUserName(){

		// Create the label
		Label label = new Label(fCompositeLogin, SWT.NONE);
		label.setText("&User Name:"); //$NON-NLS-1$
		// Configure layout data
		GridData data = new GridData();
		data.horizontalIndent = F_LABEL_HORIZONTAL_INDENT;
		label.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUICompositeBlank(){

		Composite spanner = new Composite(fCompositeLogin, SWT.NONE);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = F_COLUMN_COUNT;
		spanner.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void createUICompositeLogin(){

		// Create the composite
		fCompositeLogin = new Composite(getSplash(), SWT.BORDER);
		GridLayout layout = new GridLayout(F_COLUMN_COUNT, false);
		fCompositeLogin.setLayout(layout);
	}

	/**
	 * 
	 */
	private void configureUISplash(){

		// Configure layout
		FillLayout layout = new FillLayout();
		getSplash().setLayout(layout);
		// Force shell to inherit the splash background
		getSplash().setBackgroundMode(SWT.INHERIT_DEFAULT);
	}

}
