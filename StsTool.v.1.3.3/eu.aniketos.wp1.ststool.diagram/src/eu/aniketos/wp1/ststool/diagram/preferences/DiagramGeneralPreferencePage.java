/*
* DiagramGeneralPreferencePage.java
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
package eu.aniketos.wp1.ststool.diagram.preferences;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * @generated
 */
public class DiagramGeneralPreferencePage extends AbstractPreferencePage {

	//	localized labels
	private String					GLOBAL_SETTINGS_GROUP_LABEL	= DiagramUIMessages.DiagramsPreferencePage_globalGroup_label;

	private String					SHOW_CONNECTION_HANDLES_LABEL	= DiagramUIMessages.DiagramsPreferencePage_showConnectionHandles_label;

	private String					SHOW_POPUP_BARS_LABEL			= DiagramUIMessages.DiagramsPreferencePage_showPopupBars_label;

	private String					ENABLE_ANIMATED_LAYOUT			= DiagramUIMessages.DiagramsPreferencePage_enableAnimatedLayout_label;

	private String					ENABLE_ANIMATED_ZOOM				= DiagramUIMessages.DiagramsPreferencePage_enableAnimatedZoom_label;

	private String					SHOW_STATUS_LINE					= DiagramUIMessages.DiagramsPreferencePage_showStatusLine_label;

	// preference page editor controls
	private BooleanFieldEditor	showConnectionHandles			= null;

	private BooleanFieldEditor	showPopupBars						= null;

	private BooleanFieldEditor	enableAnimatedLayout				= null;

	private BooleanFieldEditor	enableAnimatedZoom				= null;

	private BooleanFieldEditor	showStatusLine						= null;

	private Composite				globalSettingsComposite;

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addFields(Composite parent){

		Group generalGlobalGroup = new Group(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		generalGlobalGroup.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		generalGlobalGroup.setLayoutData(gridData);
		generalGlobalGroup.setText(GLOBAL_SETTINGS_GROUP_LABEL);

		globalSettingsComposite = new Composite(generalGlobalGroup, SWT.NONE);

		showConnectionHandles = new BooleanFieldEditor(IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES, SHOW_CONNECTION_HANDLES_LABEL, globalSettingsComposite);
		addField(showConnectionHandles);

		showPopupBars = new BooleanFieldEditor(IPreferenceConstants.PREF_SHOW_POPUP_BARS, SHOW_POPUP_BARS_LABEL, globalSettingsComposite);
		addField(showPopupBars);

		enableAnimatedLayout = new BooleanFieldEditor(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, ENABLE_ANIMATED_LAYOUT, globalSettingsComposite);
		addField(enableAnimatedLayout);

		enableAnimatedZoom = new BooleanFieldEditor(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, ENABLE_ANIMATED_ZOOM, globalSettingsComposite);
		addField(enableAnimatedZoom);

		/*enableAntiAlias = new BooleanFieldEditor(
			IPreferenceConstants.PREF_ENABLE_ANTIALIAS, ENABLE_ANTIALIAS,
			globalSettingsComposite);
		addField(enableAntiAlias);
		  
		// enable anti-aliasing only if advanced graphics is supported.
		  enableAntiAlias.setEnabled(GCUtilities.supportsAdvancedGraphics(), globalSettingsComposite);*/

		showStatusLine = new BooleanFieldEditor(IPreferenceConstants.PREF_SHOW_STATUS_LINE, SHOW_STATUS_LINE, globalSettingsComposite);
		addField(showStatusLine);

	}


	/**
	 * Initializes the default preference values for this preference store.
	 * 
	 * @param IPreferenceStore
	 *           preferenceStore
	 */
	public static void initDefaults(IPreferenceStore preferenceStore){

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_CONNECTION_HANDLES, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_POPUP_BARS, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANIMATED_LAYOUT, true);

		preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM, true);

		//preferenceStore.setDefault(IPreferenceConstants.PREF_ENABLE_ANTIALIAS, true);	

		preferenceStore.setDefault(IPreferenceConstants.PREF_SHOW_STATUS_LINE, true);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#initHelp()
	 */
	@Override
	protected void initHelp(){
		//setPageHelpContextId(IHelpContextIds.VZ_U_UMLV_PAGE_PREF);
		//do nothing, no context help for modeler yet
	}

	/**
	 * Returns the Composite containing global settings controls
	 * 
	 * @return global settings controls container
	 * @since 1.3
	 */
	final protected Composite getGlobalSettingsComposite(){
		return globalSettingsComposite;
	}
}
