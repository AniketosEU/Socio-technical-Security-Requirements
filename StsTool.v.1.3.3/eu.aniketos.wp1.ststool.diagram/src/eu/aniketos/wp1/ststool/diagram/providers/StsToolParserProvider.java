/*
* StsToolParserProvider.java
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
package eu.aniketos.wp1.ststool.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalName2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleNameEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceName2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceNameEditPart;
import eu.aniketos.wp1.ststool.diagram.parsers.MessageFormatParser;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;

/**
 * @generated
 */
public class StsToolParserProvider extends AbstractProvider implements IParserProvider {

	/**
	 * @generated
	 */
	private IParser	agentName_5003Parser;

	/**
	 * @generated
	 */
	private IParser getAgentName_5003Parser(){
		if (agentName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			agentName_5003Parser = parser;
		}
		return agentName_5003Parser;
	}

	/**
	 * @generated
	 */
	private IParser	roleName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getRoleName_5004Parser(){
		if (roleName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			roleName_5004Parser = parser;
		}
		return roleName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser	goalName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5005Parser(){
		if (goalName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			goalName_5005Parser = parser;
		}
		return goalName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser	tResourceName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getTResourceName_5006Parser(){
		if (tResourceName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			tResourceName_5006Parser = parser;
		}
		return tResourceName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser	iResourceName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getIResourceName_5007Parser(){
		if (iResourceName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			iResourceName_5007Parser = parser;
		}
		return iResourceName_5007Parser;
	}

	/**
	 * @generated
	 */
	private IParser	eventName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getEventName_5008Parser(){
		if (eventName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			eventName_5008Parser = parser;
		}
		return eventName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser	goalName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getGoalName_5001Parser(){
		if (goalName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			goalName_5001Parser = parser;
		}
		return goalName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser	tResourceName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getTResourceName_5002Parser(){
		if (tResourceName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { StstoolPackage.eINSTANCE.getStsElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			tResourceName_5002Parser = parser;
		}
		return tResourceName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID){
		switch (visualID) {
			case AgentNameEditPart.VISUAL_ID:
				return getAgentName_5003Parser();
			case RoleNameEditPart.VISUAL_ID:
				return getRoleName_5004Parser();
			case GoalNameEditPart.VISUAL_ID:
				return getGoalName_5005Parser();
			case TResourceNameEditPart.VISUAL_ID:
				return getTResourceName_5006Parser();
			case IResourceNameEditPart.VISUAL_ID:
				return getIResourceName_5007Parser();
			case EventNameEditPart.VISUAL_ID:
				return getEventName_5008Parser();
			case GoalName2EditPart.VISUAL_ID:
				return getGoalName_5001Parser();
			case TResourceName2EditPart.VISUAL_ID:
				return getTResourceName_5002Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * 
	 * @generated
	 */
	public static IParser getParser(IElementType type,EObject object,String parserHint){
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint){
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) { return getParser(StsToolVisualIDRegistry.getVisualID(vid)); }
		View view = (View) hint.getAdapter(View.class);
		if (view != null) { return getParser(StsToolVisualIDRegistry.getVisualID(view)); }
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation){
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (StsToolElementTypes.getElement(hint) == null) { return false; }
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType	elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		@Override
		public Object getAdapter(Class adapter){
			if (IElementType.class.equals(adapter)) { return elementType; }
			return super.getAdapter(adapter);
		}
	}
}
