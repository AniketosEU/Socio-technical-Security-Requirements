/*
* SecureIPreferenceStore.java
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

import org.eclipse.core.runtime.preferences.*;
import org.eclipse.jface.preference.*;
import org.eclipse.jface.util.*;

import eu.aniketos.wp1.ststool.threats.*;
import eu.aniketos.wp1.ststool.threats.preferences.internal.*;

/**
 * 
 * @author Mauro Poggianella
 *       
 */
public class SecureIPreferenceStore implements IPreferenceStore {

	private static final String SEED=SecureStorage.getDefault().encryptString("+?=}U]](MXQcB8zudJjlg8.zQVgTC10n%:#+");
	private final IPreferenceStore PS;
	private final SecureStorage secureStorage;

	
	public SecureIPreferenceStore() {
		PS = Activator.getDefault().getPreferenceStore();				
		
		if(!PS.contains(SEED)){
			String seed=Long.toString(System.nanoTime());
			PS.putValue(SEED, SecureStorage.getDefault().encryptString(seed));
		}
		long seed=PS.getLong(SEED);
		secureStorage=new SecureStorage(seed);
	}
	
	public SecureIPreferenceStore(IScopeContext instance,String pluginId) {
		this();
	}
	
	
	
	@Override
	public void addPropertyChangeListener(IPropertyChangeListener listener) {
		PS.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(IPropertyChangeListener listener) {
		PS.removePropertyChangeListener(listener);
	}
	
	@Override
	public void firePropertyChangeEvent(String name, Object oldValue,Object newValue) {
		PS.firePropertyChangeEvent(name, oldValue, newValue);
	}
	
	
	@Override
	public boolean needsSaving() {
		return PS.needsSaving();
	}	
	
	

	@Override
	public double getDouble(String name) {
		try {
			return Double.parseDouble(this.getString(name));
		} catch (Exception e) {
			return DOUBLE_DEFAULT_DEFAULT;
		}
	}

	@Override
	public float getFloat(String name) {
		try {
			return Float.parseFloat(this.getString(name));
		} catch (Exception e) {
			return FLOAT_DEFAULT_DEFAULT;
		}
	}

	@Override
	public int getInt(String name) {
		try {
			return Integer.parseInt(this.getString(name));
		} catch (Exception e) {
			return INT_DEFAULT_DEFAULT;
		}
	}

	@Override
	public long getLong(String name) {
		try {
			return Long.parseLong(this.getString(name));
		} catch (Exception e) {
			return LONG_DEFAULT_DEFAULT;
		}
	}

	@Override
	public boolean getBoolean(String name) {
		try {
			return Boolean.parseBoolean(this.getString(name));
		} catch (Exception e) {
			return BOOLEAN_DEFAULT_DEFAULT;
		}
	}

	@Override
	public void setValue(String name, double value) {
		this.setValue(name, Double.toString(value));
	}

	@Override
	public void setValue(String name, float value) {
		this.setValue(name, Float.toString(value));
	}

	@Override
	public void setValue(String name, int value) {
		this.setValue(name, Integer.toString(value));
	}

	@Override
	public void setValue(String name, long value) {
		this.setValue(name, Long.toString(value));
	}

	@Override
	public void setValue(String name, boolean value) {
		this.setValue(name, Boolean.toString(value));
	}
	
	
	
	
	
	
	
	@Override
	public boolean contains(String name) {
		return PS.contains(secureStorage.encryptString(name, true));
	}
	
	@Override
	public void setValue(String name, String value) {
		PS.setValue(secureStorage.encryptString(name, true), secureStorage.encryptString(value));
	}
	
	@Override
	public void putValue(String name, String value) {
		PS.putValue(secureStorage.encryptString(name, true), secureStorage.encryptString(value));
	}

	@Override
	public String getString(String name) {
		String s= secureStorage.decryptString(PS.getString(secureStorage.encryptString(name, true)));
		return s!=null ? s : "";
	}
	
	
	@Override
	public boolean isDefault(String name) {
		return PS.isDefault(secureStorage.encryptString(name, true));
	}
	
	@Override
	public void setDefault(String name, String defaultObject) {
		PS.setDefault(secureStorage.encryptString(name, true), secureStorage.encryptString(defaultObject));
	}
	
	@Override
	public String getDefaultString(String name) {
		String s= secureStorage.decryptString(name);
		return s!=null ? s : "";
	}
	
	
	@Override
	public boolean getDefaultBoolean(String name) {
		try {
			return Boolean.parseBoolean(getDefaultString(name));
		} catch (Exception e) {
			return BOOLEAN_DEFAULT_DEFAULT;
		}
	}

	@Override
	public double getDefaultDouble(String name) {
		try {
			return Double.parseDouble(getDefaultString(name));
		} catch (Exception e) {
			return DOUBLE_DEFAULT_DEFAULT;
		}
	}

	@Override
	public float getDefaultFloat(String name) {
		try {
			return Float.parseFloat(getDefaultString(name));
		} catch (Exception e) {
			return FLOAT_DEFAULT_DEFAULT;
		}
	}

	@Override
	public int getDefaultInt(String name) {
		try {
			return Integer.parseInt(getDefaultString(name));
		} catch (Exception e) {
			return INT_DEFAULT_DEFAULT;
		}
	}

	@Override
	public long getDefaultLong(String name) {
		try {
			return Long.parseLong(getDefaultString(name));
		} catch (Exception e) {
			return LONG_DEFAULT_DEFAULT;
		}
	}

	
	
	@Override
	public void setDefault(String name, double value) {
		this.setDefault(name,Double.toString(value));
	}

	@Override
	public void setDefault(String name, float value) {
		this.setDefault(name, Float.toString(value));
	}

	@Override
	public void setDefault(String name, int value) {
		this.setDefault(name, Integer.toString(value));
	}

	@Override
	public void setDefault(String name, long value) {
		this.setDefault(name, Long.toString(value));
	}

	@Override
	public void setDefault(String name, boolean value) {
		this.setDefault(name, Boolean.toString(value));
	}

	
	
	@Override
	public void setToDefault(String name) {
		PS.setToDefault(name);
	}

}
