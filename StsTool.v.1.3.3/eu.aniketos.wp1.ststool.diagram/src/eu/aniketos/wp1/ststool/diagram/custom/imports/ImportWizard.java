/*
* ImportWizard.java
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
package eu.aniketos.wp1.ststool.diagram.custom.imports;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import eu.aniketos.wp1.ststool.diagram.part.Messages;

public class ImportWizard extends Wizard implements IRunnableWithProgress {

	private ImportWizardPage	page1;

	private IWorkbench			workbench	= null;

	public ImportWizard(IWorkbench workbench) {
		this.workbench = workbench;
		page1 = new ImportWizardPage("1.3.0");
	}

	@Override
	public void addPages(){

		super.addPages();
		addPage(page1);
	}

	@Override
	public boolean canFinish(){

		return page1.isPageComplete();
	}

	@Override
	public boolean performFinish(){

		try {
			getContainer().run(false, false, this);
		} catch (Exception e) {
			MessageDialog.openError(getShell(), "Error", e.getMessage());

		}
		return true;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,InterruptedException{

		List<String> input = null;
		try {
			input = readInput(page1.getInputFileName());
		} catch (IOException e) {
			throw new InterruptedException("Error reading the input File");
		}

		List<String> transformedDiagram = null;
		String version = getVersion(input);
		if ("1.0".equals(version)) {
			transformedDiagram = transform1_2_0_To_1_3_0(transform1_0_To_1_2_0(input));
		} else if ("1.2.0".equals(version)) {
			transformedDiagram = transform1_2_0_To_1_3_0(input);
		} else {
			throw new InterruptedException("Error importing Diagram : Invalid or missing version"); //missing header
		}



		String outputFileName = page1.getOutputFileName();
		if (page1.isOverwriteInputFile()) {
			outputFileName = page1.getInputFileName();
		}
		try {
			saveOutput(outputFileName, transformedDiagram);
		} catch (IOException e) {
			throw new InterruptedException("Error saving the diagram: " + e.getMessage());
		}

		if (workbench != null) openEditor(workbench, outputFileName);
	}

	private String getVersion(List<String> input) throws InterruptedException{
		if (input.size() < 3) throw new InterruptedException("Error importing Diagram : Invalid File"); //invalid file
		if (!input.get(0).trim().equals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) throw new InterruptedException("Error importing Diagram : Missing XML Header"); //missing header
		if (!input.get(1).trim().contains("http://ststool/")) throw new InterruptedException("Error importing Diagram : Missing STS Header"); //missing header
		try {
			String ver = input.get(1);
			ver = ver.substring(ver.indexOf("http://ststool/") + 15);
			int ind = ver.indexOf('"');
			if (ind > 0) {
				ver = ver.substring(0, ind);
			}
			return ver.trim();
		} catch (Exception e) {
			throw new InterruptedException("Error importing Diagram : can't rertive version");
		}
	}

	private List<String> transform1_0_To_1_2_0(List<String> input) throws InterruptedException{

		List<String> result = new ArrayList<String>(input.size());
		result.add(input.get(0));
		result.add(input.get(1).replace("http://ststool/1.0", "http://ststool/1.2.0"));

		for (int i = 2; i < input.size(); i++) {
			String s = input.get(i);

			s = s.replace("noRepudiation=\"true\"", "repudiationType=\"DUAL_REPUDIATION\"");
			s = s.replace("redundancy=\"true\"", "redundancyType=\"FALLBACK_MULTI\"");

			result.add(s);
		}
		return result;
	}

	private List<String> transform1_2_0_To_1_3_0(List<String> input) throws InterruptedException{
		List<String> result = new ArrayList<String>(input);
		String s = result.remove(1);
		s = s.replace("http://ststool/1.2.0", "http://ststool/1.3.0");
		result.add(1, s);
		return result;
	}

	private List<String> readInput(String fileName) throws IOException{

		List<String> outText = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				outText.add(strLine);
			}
		} finally {
			if (br != null) br.close();
		}
		return outText;
	}


	private void saveOutput(String filename,List<String> output) throws IOException{
		BufferedWriter out = new BufferedWriter(new FileWriter(filename, false));
		for (String s : output) {
			out.write(s);
			out.newLine();
		}
		out.close();
	}

	private static boolean openEditor(IWorkbench workbench,String filename){

		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		URI fileURI = URI.createFileURI(filename);
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorTitle, NLS.bind(Messages.DiagramEditorActionBarAdvisor_DefaultFileEditorMessage, fileURI.toFileString()));
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), Messages.DiagramEditorActionBarAdvisor_DefaultEditorOpenErrorTitle, exception.getMessage());
				return false;
			}
		}
		return true;
	}
}
