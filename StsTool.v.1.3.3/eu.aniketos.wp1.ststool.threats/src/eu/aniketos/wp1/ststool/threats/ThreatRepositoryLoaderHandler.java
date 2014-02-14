/*
* ThreatRepositoryLoaderHandler.java
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
package eu.aniketos.wp1.ststool.threats;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import eu.aniketos.threatrepository.TagData;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditor;
import eu.aniketos.wp1.ststool.threats.wizard.ThreatRepositoryServiceWrapper;
import eu.aniketos.wp1.ststool.threats.wizard.ThreatRepositoryWizard;

public class ThreatRepositoryLoaderHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
	IWorkbenchWindow window = null;
	try {
		if (event != null)
			window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		else
			window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	} catch (Exception e) {
	}

	if (window == null && PlatformUI.getWorkbench().getWorkbenchWindows().length > 0) {
		window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
	} else {
		return new Exception("An internal error has occurred. Please try to update the software.");
	}

			IEditorPart editorPart = window.getActivePage().getActiveEditor();

			if (editorPart instanceof StsToolDiagramEditor) {
				StsToolDiagramEditor editor = (StsToolDiagramEditor) editorPart;
				return loadDomainTags(editor);
			} else
				return new Exception("An internal error has occurred. Please try to update the software.");
		} catch (Exception e) {
			e.printStackTrace();
			return new Exception("An internal error has occurred. Please try to update the software.", e);
		}
	}

	public static Exception loadDomainTags(StsToolDiagramEditor editor) {

		HashMap<String, String> domainsList = (HashMap<String, String>) editor.getPreferenceMap().get(ThreatRepositoryWizard.DOMAINS_LIST);
		if (domainsList == null)
			domainsList = new HashMap<String, String>();

		// new HashMap<String,String>(); //
		if (domainsList.isEmpty()) {

			try {
				ThreatRepositoryServiceWrapper processor = new ThreatRepositoryServiceWrapper();
				List<TagData> tagsFromRepository = processor.processGetTagList();
				if (tagsFromRepository != null && !tagsFromRepository.isEmpty()) {

					for (TagData tag : tagsFromRepository) {

						if (tag.tag.startsWith("domain:")) {
							String tagName = tag.tag.substring(7, tag.tag.length()) + " (" + tag.occurrences + ")";
							tagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
							domainsList.put(tag.tag, tagName);
						}
					}

					editor.getPreferenceMap().put(ThreatRepositoryWizard.DOMAINS_LIST, domainsList);
				} else
					throw new Exception(
							"Please check your internet connection and proxy settings.\n\nYou might also need to set/verify your credentials (in Preferences) for accessing the Threat Repository.\n\nSign up for (free) access: https://svrs.shields-project.eu/ANIKETOS");

			} catch (Exception ex) {
				return ex;
			}
		}
		return null;
	}

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public boolean isHandled() {

		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}
}
