/*
* PreferenceInitializer.java
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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.IPreferenceStore;

import eu.aniketos.wp1.ststool.threats.Activator;


/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	
	public final static String QUALIFIER = "eu.aniketos.wp1.ststool.diagram";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
//		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		IPreferenceStore store = new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID);

		store.setDefault(PreferenceConstants.SVRS_EMAIL, "");
		store.setDefault(PreferenceConstants.SVRS_PASSWORD, "");
		store.setDefault(PreferenceConstants.PROXY_ENABLED, false);
		store.setDefault(PreferenceConstants.PROXY_ADDRESS, "");
		store.setDefault(PreferenceConstants.PROXY_PORT, 0);
		store.setDefault(PreferenceConstants.PROXY_AUTHENTICATION, false);
		store.setDefault(PreferenceConstants.PROXY_USERNAME, "");
		store.setDefault(PreferenceConstants.PROXY_PASSWORD, "");
	}
}

