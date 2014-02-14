/*
* SecureIPreferenceStore.java
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

import eu.aniketos.wp1.ststool.threats.Activator;

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
