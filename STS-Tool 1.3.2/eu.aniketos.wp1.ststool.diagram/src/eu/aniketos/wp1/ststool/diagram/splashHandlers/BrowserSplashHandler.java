/*
* BrowserSplashHandler.java
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

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.splash.AbstractSplashHandler;

/**
 * @since 3.3
 * 
 */
public class BrowserSplashHandler extends AbstractSplashHandler {

	private final static String	F_BROWSER_URL	= "http://www.google.com"; //NON-NLS-1

	private Browser					fBrowser;

	private Button						fButton;

	private boolean					fClose;

	/**
	 * 
	 */
	public BrowserSplashHandler() {

		fBrowser = null;
		fButton = null;
		fClose = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.internal.splash.AbstractSplashHandler#init(org.eclipse.
	 * swt.widgets.Shell,
	 * org.eclipse.ui.IWorkbench)
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
		// Force the UI to layout
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
		while (fClose == false) {
			if (splash.getDisplay().readAndDispatch() == false) {
				splash.getDisplay().sleep();
			}
		}
	}

	/**
	 * 
	 */
	private void createUIListeners(){

		// Create the browser listeners
		createUIListenersBrowser();
		// Create the button listeners
		createUIListenersButton();
	}

	/**
	 * 
	 */
	private void createUIListenersButton(){

		fButton.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e){

				// NO-OP
			}

			public void widgetSelected(SelectionEvent e){

				fClose = true;
			}
		});
	}

	/**
	 * 
	 */
	private void createUIListenersBrowser(){

		fBrowser.addProgressListener(new ProgressListener() {

			public void changed(ProgressEvent event){

				// NO-OP
			}

			public void completed(ProgressEvent event){

				// Only show the UI when the URL is fully loaded into the 
				// browser
				fBrowser.setVisible(true);
				fButton.setVisible(true);
			}
		});
	}

	/**
	 * 
	 */
	private void createUI(){

		// Create the web browser
		createUIBrowser();
		// Create the close button
		createUIButton();
	}

	/**
	 * 
	 */
	private void createUIButton(){

		Shell splash = getSplash();
		fButton = new Button(splash, SWT.PUSH);
		fButton.setText("Close"); //NON-NLS-1
		fButton.setVisible(false);
		// Configure the button bounds
		configureUIButtonBounds();
		// Configure layout data
		GridData data = new GridData(SWT.CENTER, SWT.FILL, false, false);
		data.widthHint = 80;
		fButton.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void configureUIButtonBounds(){

		Shell splash = getSplash();

		int button_x_coord = (splash.getSize().x / 2) - (fButton.computeSize(SWT.DEFAULT, SWT.DEFAULT).x / 2);
		int button_y_coord = splash.getSize().y - fButton.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		int button_x_width = splash.getSize().x;
		int button_y_width = fButton.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;

		fButton.setBounds(button_x_coord, button_y_coord, button_x_width, button_y_width);
	}

	/**
	 * 
	 */
	private void createUIBrowser(){

		fBrowser = new Browser(getSplash(), SWT.NONE);
		fBrowser.setUrl(F_BROWSER_URL);
		fBrowser.setVisible(false);
		// Configure layout data
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		fBrowser.setLayoutData(data);
	}

	/**
	 * 
	 */
	private void configureUISplash(){

		GridLayout layout = new GridLayout(1, true);
		getSplash().setLayout(layout);
	}

}
