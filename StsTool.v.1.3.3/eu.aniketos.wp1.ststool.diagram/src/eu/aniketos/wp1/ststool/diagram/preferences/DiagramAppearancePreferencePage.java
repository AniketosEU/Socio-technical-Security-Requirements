/*
* DiagramAppearancePreferencePage.java
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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.gmf.runtime.common.ui.preferences.FontFieldEditor;
import org.eclipse.gmf.runtime.diagram.ui.figures.DiagramColorConstants;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * @generated NOT
 */
public class DiagramAppearancePreferencePage extends AbstractPreferencePage {

	/**
	 * @generated
	 */
	public DiagramAppearancePreferencePage() {

		setPreferenceStore(StsToolDiagramEditorPlugin.getInstance().getPreferenceStore());
	}

	//localized labels
	private String				COLORANDFONT_GROUPBOX_LABEL	= DiagramUIMessages.GeneralPreferencePage_colorAndFontGroupBox_label;
	private String				DEFAULT_FONT_LABEL				= DiagramUIMessages.GeneralPreferencePage_defaultFont_label;
	//preference page editor controls 
	private FontFieldEditor	defaultFontEditor					= null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields
	 * (org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addFields(Composite parent){

		Composite main = createPageLayout(parent);
		createFontAndColorGroup(main);
	}

	/**
	 * Creates a new composite for the entire page and sets up the layout.
	 * 
	 * @param parent
	 *           the parent <code>Composite</code> that the field editors will be added to
	 * @return the new <code>Composite</code>
	 */
	protected Composite createPageLayout(Composite parent){

		Composite main = new Composite(parent, SWT.NULL);
		main.setLayout(new GridLayout());
		main.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		return main;
	}

	/**
	 * Create the font and colour group for the preference page
	 * 
	 * @param parent
	 * @return composite fontAndColourGroup
	 */
	protected Composite createFontAndColorGroup(Composite parent){

		Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group.setLayout(new GridLayout(3, false));
		Composite composite = new Composite(group, SWT.NONE);
		GridLayout gridLayout = new GridLayout(3, false);
		composite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 3;
		composite.setLayoutData(gridData);
		group.setText(COLORANDFONT_GROUPBOX_LABEL);

		addFontAndColorFields(composite);

		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 8;
		composite.setLayout(layout);

		return group;
	}

	/**
	 * Adds the font and color fields to the <code>Composite</code> given.
	 * 
	 * @param composite
	 */
	protected void addFontAndColorFields(Composite composite){

		defaultFontEditor = new FontFieldEditor(IPreferenceConstants.PREF_DEFAULT_FONT, DEFAULT_FONT_LABEL, composite);
		addField(defaultFontEditor);

	}

	/**
	 * Initializes the default preference values for this preference store.
	 * 
	 * @param store
	 */
	public static void initDefaults(IPreferenceStore store){

		setDefaultFontPreference(store);

		Color fontColor = ColorConstants.black;
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_FONT_COLOR, fontColor.getRGB());

		Color fillColor = DiagramColorConstants.white;
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_FILL_COLOR, fillColor.getRGB());

		Color lineColor = DiagramColorConstants.diagramGray;
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_LINE_COLOR, lineColor.getRGB());

		Color noteFillColor = DiagramColorConstants.diagramLightYellow;
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_NOTE_FILL_COLOR, noteFillColor.getRGB());

		Color noteLineColor = DiagramColorConstants.diagramDarkYellow;
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_NOTE_LINE_COLOR, noteLineColor.getRGB());
	}

	/**
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#initHelp()
	 */
	@Override
	protected void initHelp(){

		//do nothing, help not ready yet
		//setPageHelpContextId(IHelpContextIds.VZ_U_APPEAR_PAGE_PREF);
	}

	/**
	 * The field editor preference page implementation of a <code>PreferencePage</code> method loads all the field editors with their default values.
	 * 
	 * Override so that the font field editor default is set. Note: loadDefault() does not work with FontFieldEditor
	 */
	@Override
	protected void performDefaults(){

		setDefaultFontPreference(getPreferenceStore());
		super.performDefaults();
	}

	/**
	 * Set the default font for this preference store.
	 * 
	 * @param store
	 *           IPreferenceStore
	 */
	static protected void setDefaultFontPreference(IPreferenceStore store){

		final IPreferenceStore theStore = store;
		if (Display.getCurrent() != null) {
			initDefaultFontProc(theStore);
		} else {
			Display display = PlatformUI.isWorkbenchRunning() ? PlatformUI.getWorkbench().getDisplay() : Display.getDefault();
			display.syncExec(new Runnable() {

				public void run(){

					initDefaultFontProc(theStore);
				}
			});
		}
	}

	/**
	 * Set the default font for this preference store. Assumes that the method executed on the UI thread
	 * 
	 * @param store
	 *           IPreferenceStore
	 */
	private static void initDefaultFontProc(IPreferenceStore store){

		FontData fontDataArray[] = JFaceResources.getDefaultFont().getFontData();
		FontData fontData = fontDataArray[0];
		fontData.setHeight(9);
		PreferenceConverter.setDefault(store, IPreferenceConstants.PREF_DEFAULT_FONT, fontData);
	}

	/**
	 * Gets the field editor used for the default font.
	 * 
	 * @return the default font field editor
	 */
	protected FontFieldEditor getDefaultFontEditor(){

		return defaultFontEditor;
	}
}
