/*
* StsXMISaveImpl.java
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
package eu.aniketos.wp1.ststool.diagram.xmi.serializer;

import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMISaveImpl;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorUtil;


public class StsXMISaveImpl extends XMISaveImpl {

	public StsXMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding, String xmlVersion) {

		super(options, helper, encoding, xmlVersion);
	}

	public StsXMISaveImpl(Map<?, ?> options, XMLHelper helper, String encoding) {

		super(options, helper, encoding);
	}

	public StsXMISaveImpl(XMLHelper helper) {

		super(helper);
	}

	private Map	options	= null;

	@Override
	protected void init(XMLResource resource,Map<?, ?> options){

		super.init(resource, options);
		this.options = options;
	}

	@Override
	public void traverse(List<? extends EObject> contents){

		if (!toDOM && declareXML) {

			doc.add("<?xml version=\"" + xmlVersion + "\" encoding=\"" + encoding + "\"?>");
			doc.addLine();

			if (options.containsKey(StsToolDiagramEditorUtil.STYLESHEET)) {
				//Adding the stylesheet declaration
				doc.add("<?xml-stylesheet type=\"text/xsl\" href=\"" + options.get(StsToolDiagramEditorUtil.STYLESHEET) + "\"?>");
				doc.addLine();
			}
		}

		int size = contents.size();

		// Reserve a place to insert xmlns declarations after we know what they all are.
		//
		Object mark;

		if (size == 1) {
			mark = writeTopObject(contents.get(0));
		} else {
			mark = writeTopObjects(contents);
		}
		if (!toDOM) {
			// Go back and add all the XMLNS stuff.
			//
			doc.resetToMark(mark);
		} else {
			currentNode = document.getDocumentElement();
		}
		addNamespaceDeclarations();
		addDoctypeInformation();
	}

}
