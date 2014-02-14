/*
* GenerateReportWizard.java
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
package eu.aniketos.wp1.ststool.report.wizard;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.diagram.custom.screenshotgenerator.STSImageGeneratorUtils;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.report.pdfgenerator.GenUtils;
import eu.aniketos.wp1.ststool.report.pdfgenerator.PdfGenerator;
import eu.aniketos.wp1.ststool.report.pdfgenerator.ReportContentFactory;
import eu.aniketos.wp1.ststool.report.pdfgenerator.ReportScreenshotProvider;
import eu.aniketos.wp1.ststool.report.pdfgenerator.ReportValueFactory;


public class GenerateReportWizard extends Wizard implements IRunnableWithProgress {

	private final static String	INITDATA	= "ReportWizardInitData";

	private WizardData				wizardData;
	private StsToolDiagramEditor	editor;


	private Page1						page1;
	private Page2						page2;


	public GenerateReportWizard(StsToolDiagramEditor editor) {

		if (editor == null) throw new RuntimeException("Editor can't be null");
		this.editor = editor;
		setNeedsProgressMonitor(true);

		wizardData = (WizardData) editor.getPreferenceMap().get(INITDATA);
		if (wizardData == null) {
			wizardData = new WizardData();

			String fileName = editor.getEditorInput().getName();
			String reportTitle = fileName.substring(0, fileName.length() - 16);
			if (reportTitle.length() > 1)
			;
			reportTitle = reportTitle.substring(0, 1).toUpperCase() + reportTitle.substring(1);
			wizardData.setReportTitle(reportTitle);
			String author = System.getProperty("user.name");
			if (author != null)
			;
			wizardData.setReportAuthor(author);

			try {
				wizardData.setPdfPath(getUninqueFileName(ResourcesPlugin.getWorkspace().getRoot().getRawLocation().makeAbsolute().toString() + File.separatorChar + reportTitle + ".pdf"));
			} catch (Exception e) {
			}
		} else {
			try {
				wizardData.setPdfPath(getUninqueFileName(wizardData.getPdfPath()));
			} catch (Exception e) {
			}
		}

		page1 = new Page1(wizardData);
		page2 = new Page2(wizardData);
	}

	private String getUninqueFileName(String filename){
		File f = new File(filename);
		if (!f.exists())
			return filename;
		else {
			int index = filename.lastIndexOf('.');
			String pre = filename.substring(0, index);
			String post = filename.substring(index, filename.length());
			return pre + (new SimpleDateFormat("_ddMMyy_HHmmss").format(new Date())) + post;
		}
	}

	@Override
	public void addPages(){

		super.addPages();
		addPage(page1);
		addPage(page2);
	}

	@Override
	public boolean canFinish(){

		return page1.isPageComplete() && page2.isPageComplete();
	}

	@Override
	public boolean performFinish(){

		page1.storeData(wizardData);
		page2.storeData(wizardData);
		editor.getPreferenceMap().put(INITDATA, wizardData);
		try {
			getContainer().run(false, false, this);
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "Error", "The report generation encountred a problem.");

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
		return true;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException{

		ByteArrayOutputStream baos = null;

		try {
			monitor.beginTask("Generating Report", 100);

			if (wizardData.isPdfOutput()) {
				deleteFile(wizardData.getPdfPath());
			}
			if (wizardData.isRtfOutput()) {
				deleteFile(wizardData.getRtfPath());
			}

			int monitorTiket = 100;

			PdfGenerator generator = new PdfGenerator(new ReportContentFactory(getReportValueFactory(wizardData), new ScreenShotProvider()));
			baos = new ByteArrayOutputStream(4096000);

			generator.createPdf(baos, new SubProgressMonitor(monitor, monitorTiket));

			if (wizardData.isPdfOutput()) {
				try {
					FileOutputStream fos = null;
					try {
						String RESULT = System.getProperty("user.home") + File.separatorChar + "Desktop" + File.separator + "STS-Test.pdf";
						RESULT = wizardData.getPdfPath();
						fos = new FileOutputStream(RESULT);
						fos.write(baos.toByteArray());
					} finally {
						if (fos != null) fos.close();
					}
				} catch (Exception e) {
				}
			}

			boolean x = false;
			if (x/*wizardData.isRtfOutput()*/) {
				try {
					FileOutputStream fos = null;
					ByteArrayInputStream bais = null;
					try {
						String RESULT = System.getProperty("user.home") + File.separatorChar + "Desktop" + File.separator + "STS-Test.rtf";
						fos = new FileOutputStream(RESULT);
						bais = new ByteArrayInputStream(baos.toByteArray());

						//ConvertPDFToRTF.convert(bais, fos);
						//ConvertPDFToRTF.concatPDFs(bais, fos, false);
					} finally {
						if (fos != null) fos.close();
						if (bais != null) bais.close();
					}
				} catch (Exception e) {
				}
			}
			monitor.done();

			try {
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.BROWSE)) {
						if (wizardData.isPdfOutput()) {
							desktop.browse(new File(wizardData.getPdfPath()).toURI());
						}
					}
				}
			} catch (Exception e) {
			}

		} catch (Exception e) {
			throw new InvocationTargetException(e, "Error while generating report");
		} finally {
			if (monitor != null) monitor.done();
			if (baos != null) try {
				baos.close();
			} catch (IOException e) {
			}
		}
	}

	private ReportValueFactory getReportValueFactory(WizardData data){

		ReportValueFactory rvf = new ReportValueFactory();
		rvf.setReportTitle(wizardData.getReportTitle());
		rvf.setReportAuthor(wizardData.getReportAuthor());
		rvf.setReportIstitution(wizardData.getReportIstitution());
		rvf.setDiagram((StsToolDiagram) editor.getDiagram().getElement());

		initSectionsGeneration(rvf, wizardData.getSelectionViewTree());
		initSectionsActors(rvf, ((StsToolDiagram) editor.getDiagram().getElement()).getDiagActors());

		return rvf;
	}

	private void initSectionsGeneration(ReportValueFactory rvf,Node n){
		rvf.setGenerateChapterSection(n.getId(), n.isChecked());
		for (Node child : n.getChildren()) {
			initSectionsGeneration(rvf, child);
		}
	}

	private void initSectionsActors(ReportValueFactory rvf,List<Actor> value){
		for (int s : ReportValueFactory.ALL_CHAPETERS_SECTIONS) {
			rvf.setActors(s, value);
		}
	}

	private void deleteFile(String file){
		File f = new File(file);
		if (f.exists()) {
			f.delete();
		}
	}

	private class ScreenShotProvider implements ReportScreenshotProvider {

		@Override
		public Image generateScrenshot(int view){

			Image result = null;
			File temp;
			try {
				temp = File.createTempFile("sts", ".tmp");
				STSImageGeneratorUtils.generateDiagramScreenshot(getShell(), temp.getAbsolutePath(), editor.getDiagram(), view, ImageFileFormat.JPEG);
				result = GenUtils.getImage(temp.toURI().toURL());
				temp.delete();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (BadElementException e) {
				e.printStackTrace();
			}
			return result;
		}
	}

}
