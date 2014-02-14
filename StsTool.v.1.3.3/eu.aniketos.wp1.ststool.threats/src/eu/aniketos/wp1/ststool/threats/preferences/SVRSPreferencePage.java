/*
* SVRSPreferencePage.java
* Copyright (C) 2013 SINTEF (http://www.sintef.no)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
* The MIT License (MIT)
* http://opensource.org/licenses/mit-license.php
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

public class SVRSPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

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

	public SVRSPreferencePage() {
		super(GRID);
		//PreferenceInitializer.QUALIFIER
		setPreferenceStore(new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID)); // Activator.getDefault().getPreferenceStore()
		setDescription("A user account may be required for connecting to the Threat Repository.");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		addField(new StringFieldEditor(PreferenceConstants.SVRS_EMAIL, "E-mail:", getFieldEditorParent()));

		StringFieldEditor passf = new StringFieldEditor(PreferenceConstants.SVRS_PASSWORD, "Password:", getFieldEditorParent());
		passf.getTextControl(getFieldEditorParent()).setEchoChar('*');
		addField(passf);
		
		
		addField(new BooleanFieldEditor(PreferenceConstants.PROXY_ENABLED, "Enable proxy for HTTPS", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.PROXY_ADDRESS, "Host:", getFieldEditorParent()));

		IntegerFieldEditor port = new IntegerFieldEditor(PreferenceConstants.PROXY_PORT, "Port:", getFieldEditorParent());
		port.setValidRange(0, 49151); // http://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers
		addField(port);
		
		addField(new BooleanFieldEditor(PreferenceConstants.PROXY_AUTHENTICATION, "Proxy requires authentication:", getFieldEditorParent()));

		addField(new StringFieldEditor(PreferenceConstants.PROXY_USERNAME, "User:", getFieldEditorParent()));

		StringFieldEditor passf2 = new StringFieldEditor(PreferenceConstants.PROXY_PASSWORD, "Password:", getFieldEditorParent());
		passf2.getTextControl(getFieldEditorParent()).setEchoChar('*');
		addField(passf2);
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
