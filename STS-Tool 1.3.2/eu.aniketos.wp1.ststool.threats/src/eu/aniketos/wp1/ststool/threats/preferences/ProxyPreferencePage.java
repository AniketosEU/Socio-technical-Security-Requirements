/*
* ProxyPreferencePage.java
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
package eu.aniketos.wp1.ststool.threats.preferences;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.aniketos.wp1.ststool.threats.Activator;

public class ProxyPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * This class represents a preference page that is contributed to the
	 * Preferences dialog. By subclassing
	 * <samp>FieldEditorPreferencePage</samp>, we can use the field support
	 * built into JFace that allows us to create a page that is small and knows
	 * how to save, restore and apply itself.
	 * <p>
	 * This page is used to modify preferences only. They are stored in the
	 * preference store that belongs to the main plug-in class. That way,
	 * preferences can be accessed directly via the preference store.
	 */

	public ProxyPreferencePage() {
		super(GRID);
		//PreferenceInitializer.QUALIFIER
		setPreferenceStore(new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID)); // Activator.getDefault().getPreferenceStore()
		setDescription("A proxy may be required for connecting to the Threat Repository.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		
		addField(new BooleanFieldEditor(PreferenceConstants.PROXY_ENABLED, "Enable proxy for HTTPS", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.PROXY_ADDRESS, "Host:", getFieldEditorParent()));

		IntegerFieldEditor port = new IntegerFieldEditor(PreferenceConstants.PROXY_PORT, "Port:", getFieldEditorParent());
		port.setValidRange(0, 49151); // http://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers
		addField(port);
		
		addField(new BooleanFieldEditor(PreferenceConstants.PROXY_AUTHENTICATION, "Requires authentication:", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.PROXY_USERNAME, "User:", getFieldEditorParent()));

		StringFieldEditor passf = new StringFieldEditor(PreferenceConstants.PROXY_PASSWORD, "Password:", getFieldEditorParent());
		passf.getTextControl(getFieldEditorParent()).setEchoChar('*');
		addField(passf);
		// TODO: Store securely:
		// http://wiki.eclipse.org/Storage/Retrieval_of_IDs,_IContainers_using_Equinox_Secure_Preferences


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}
