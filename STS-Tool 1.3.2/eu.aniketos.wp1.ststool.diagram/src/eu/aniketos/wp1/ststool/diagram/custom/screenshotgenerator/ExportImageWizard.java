/*
* ExportImageWizard.java
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
package eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;


public class ExportImageWizard extends Wizard implements IRunnableWithProgress {

	private final static String						INITDATA	= "ExportImageWizardInitData";

	private WizardData									wizardData;
	private ExoprtImageWizardPage						page		= new ExoprtImageWizardPage();
	private CustomStsToolDiagramDocumentEditor	editor;

	private int												count		= 0;

	public ExportImageWizard(CustomStsToolDiagramDocumentEditor editor) {
		this.editor = editor;
		wizardData = (WizardData) editor.getPreferenceMap().get(INITDATA);
		page = new ExoprtImageWizardPage();

		String[] imageFormat = new String[ImageFileFormat.VALUES.length];
		for (int i = 0; i < imageFormat.length; i++) {
			imageFormat[i] = ImageFileFormat.VALUES[i].getName();
		}
		page.setOutputFormats(imageFormat);

		if (wizardData != null) {
			page.setPath(wizardData.path);
			page.setSocialViewSelected(wizardData.socialSelected);
			page.setInformationViewSelected(wizardData.informationSelected);
			page.setAuthorisationViewSelected(wizardData.authorisationSelected);
			count = wizardData.count;
			int result = 0;
			boolean found = false;
			for (int i = 0; i < imageFormat.length && !found; i++) {
				if (imageFormat[i].equals(wizardData.selectedOutputFormat)) {
					found = true;
					result = i;
				}
			}
			page.setInitialIndex(result);

		} else {
			switch (editor.getViewsManager().getCurrentIntView()) {
				case ViewsManager.SOCIAL_VIEW:
					page.setSocialViewSelected(true);
				break;
				case ViewsManager.RESOURCE_VIEW:
					page.setInformationViewSelected(true);
				break;
				case ViewsManager.AUTHORIZATION_VIEW:
					page.setAuthorisationViewSelected(true);
				break;
			}
			page.setInitialIndex(0);
		}
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish(){
		try {
			getContainer().run(false, false, this);
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			Display.getCurrent().asyncExec(new Runnable() {

				@Override
				public void run(){
					int x = editor.getViewsManager().getCurrentIntView();
					editor.getViewsManager().setCurrentView(ViewsManager.EMPTY_VIEW);
					editor.getViewsManager().setCurrentView(x);
				}
			});
		}

		WizardData wd = new WizardData();
		wd.path = page.getPath();
		wd.selectedOutputFormat = page.getSelectedOutputFormats();
		wd.socialSelected = page.isSocialViewSelected();
		wd.informationSelected = page.isInformationViewSelected();
		wd.authorisationSelected = page.isAuthorisationViewSelected();
		wd.count = ++count;
		editor.getPreferenceMap().put(INITDATA, wd);
		return true;
	}

	@Override
	public void addPages(){
		super.addPages();
		addPage(page);
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException{
		try {
			monitor.beginTask("Generating Screenshot", 100);


			IEditorInput in = editor.getEditorInput();
			if (!(in instanceof URIEditorInput)) throw new InterruptedException("Invalid editor input");

			URI diagramURI = ((URIEditorInput) in).getURI();

			String diagramStringPath = diagramURI.toFileString();
			String diagramName;
			String[] subdividedPath = subdividePath(diagramStringPath);
			diagramName = subdividedPath[1];

			List<Integer> screenshot = new ArrayList<Integer>(3);
			if (page.isSocialViewSelected()) screenshot.add(new Integer(ViewsManager.SOCIAL_VIEW));
			if (page.isInformationViewSelected()) screenshot.add(new Integer(ViewsManager.RESOURCE_VIEW));
			if (page.isAuthorisationViewSelected()) screenshot.add(new Integer(ViewsManager.AUTHORIZATION_VIEW));

			for (Integer i : screenshot) {
				String filename = diagramName + "_screenshot_" + count + "(" + editor.getViewsManager().getView(i).getName() + ")." + ImageFileFormat.resolveImageFormat(page.getSelectedOutputFormats()).getName().toLowerCase();
				STSImageGeneratorUtils.generateDiagramScreenshot(getShell(), page.getPath() + File.separatorChar + filename, editor.getDiagramEditPart().getDiagramView(), i, ImageFileFormat.resolveImageFormat(page.getSelectedOutputFormats()));
			}
		} catch (Exception e) {
		}
	}

	private String[] subdividePath(String path){

		String[] result = { "", "", "" };


		boolean foundDot = false;
		boolean foundSep = false;
		for (int i = path.length() - 1; i >= 0 && !foundSep; i--) {
			char c = path.charAt(i);

			if (c == '.') {
				if (!foundDot && !foundSep && !(i == path.length() - 1)) {
					foundDot = true;
					result[2] = path.substring(i + 1);
				}
			} else if (c == File.separatorChar) {
				result[0] = path.substring(0, i);
				if (foundDot)
					result[1] = path.substring(i + 1, path.length() - result[2].length() - 1);
				else
					result[1] = path.substring(i + 1);
				foundSep = true;
			}
		}
		return result;
	}

	private class WizardData {

		String	path;
		String	selectedOutputFormat;
		boolean	socialSelected;
		boolean	informationSelected;
		boolean	authorisationSelected;
		int		count;
	}

}
