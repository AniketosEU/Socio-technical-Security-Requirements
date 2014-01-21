/*
* SecureIPreferenceStoreOriginal.java
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
