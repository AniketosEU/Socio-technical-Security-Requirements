/*
* SecureIPreferenceStoreOriginal.java
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

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * 
 * @author 
 *         http://stackoverflow.com/questions/11105321/eclipse-rcp-application-create
 *         -preference-page-entry-which-is-saved-encrypted
 * 
 */
public class SecureIPreferenceStoreOriginal extends ScopedPreferenceStore {

	/*
	 * Current class will override all storage and loading methods, using this
	 * secure preferenceStore, to store all preferences in an encrypted way.
	 */
	private static final ISecurePreferences SECURE_PREFERENCES = SecurePreferencesFactory.getDefault();

	/**
	 * * Use this class to retrieve an instance of
	 * {@code SecureIPreferenceStore}, which will store all preferences in the
	 * default secure storage. * * 
	 * @param context - the scope to store to, e.g. {@link org.eclipse.core.runtime.preferences.ConfigurationScope
	 * ConfigurationScope} * or
	 * {@link org.eclipse.core.runtime.preferences.InstanceScope InstanceScope} *
	 * * @param qualifier - the qualifier to look up the preference node. An
	 * example of a qualifier would be the plug-in identifier that the preference
	 * is associated with, * (e.g. the "org.eclipse.core.resources" uses the
	 * preference node in the instance scope where the preferences for
	 * "org.eclipse.core.resources" are stored)
	 */
	public SecureIPreferenceStoreOriginal(IScopeContext context, String qualifier) {
		super(context, qualifier);
	}

	@Override
	public void setValue(String name, boolean value) {
		try {
			SECURE_PREFERENCES.putBoolean(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(String name, double value) {
		try {
			SECURE_PREFERENCES.putDouble(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(String name, float value) {
		try {
			SECURE_PREFERENCES.putFloat(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(String name, int value) {
		try {
			SECURE_PREFERENCES.putInt(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(String name, long value) {
		try {
			SECURE_PREFERENCES.putLong(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setValue(String name, String value) {
		try {
			SECURE_PREFERENCES.put(name, value, true);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean getBoolean(String name) {
		Boolean result = false;
		try {
			result = SECURE_PREFERENCES.getBoolean(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getString(String name) {
		String result = "";
		try {
			result = SECURE_PREFERENCES.get(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String get(String name) {
		return getString(name);		
	}

	@Override
	public double getDouble(String name) {
		Double result = 0.0;
		try {
			result = SECURE_PREFERENCES.getDouble(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public float getFloat(String name) {
		Float result = 0.0f;
		try {
			result = SECURE_PREFERENCES.getFloat(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int getInt(String name) {
		int result = 0;
		try {
			result = SECURE_PREFERENCES.getInt(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public long getLong(String name) {
		Long result = 0L;
		try {
			result = SECURE_PREFERENCES.getLong(name, result);
		} catch (StorageException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
