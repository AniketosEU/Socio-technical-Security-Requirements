/*
* AgentNameEditPart.java
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
package eu.aniketos.wp1.ststool.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gef.AccessibleEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserEditStatus;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ISemanticParser;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import eu.aniketos.wp1.ststool.diagram.edit.policies.StsToolTextSelectionEditPolicy;
import eu.aniketos.wp1.ststool.diagram.part.StsToolVisualIDRegistry;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolParserProvider;

/**
 * @generated
 */
public class AgentNameEditPart extends CompartmentEditPart implements ITextAwareEditPart {

	/**
	 * @generated
	 */
	public static final int		VISUAL_ID	= 5003;

	/**
	 * @generated
	 */
	private DirectEditManager	manager;

	/**
	 * @generated
	 */
	private IParser				parser;

	/**
	 * @generated
	 */
	private List					parserElements;

	/**
	 * @generated
	 */
	private String					defaultText;

	/**
	 * @generated
	 */
	public AgentNameEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new StsToolTextSelectionEditPolicy());
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new LabelDirectEditPolicy());
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, new NonResizableEditPolicy() {

			protected List createSelectionHandles(){
				List handles = new ArrayList();
				NonResizableHandleKit.addMoveHandle((GraphicalEditPart) getHost(), handles);
				((MoveHandle) handles.get(0)).setBorder(null);
				return handles;
			}

			public Command getCommand(Request request){
				return null;
			}

			public boolean understandsRequest(Request request){
				return false;
			}
		});
	}


	@Override
	public boolean isSelectable(){

		return false;
	}

	/**
	 * @generated
	 */
	protected String getLabelTextHelper(IFigure figure){
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getText();
		} else {
			return ((Label) figure).getText();
		}
	}

	/**
	 * @generated
	 */
	protected void setLabelTextHelper(IFigure figure,String text){
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setText(text);
		} else {
			((Label) figure).setText(text);
		}
	}

	/**
	 * @generated
	 */
	protected Image getLabelIconHelper(IFigure figure){
		if (figure instanceof WrappingLabel) {
			return ((WrappingLabel) figure).getIcon();
		} else {
			return ((Label) figure).getIcon();
		}
	}

	/**
	 * @generated
	 */
	protected void setLabelIconHelper(IFigure figure,Image icon){
		if (figure instanceof WrappingLabel) {
			((WrappingLabel) figure).setIcon(icon);
		} else {
			((Label) figure).setIcon(icon);
		}
	}

	/**
	 * @generated
	 */
	public void setLabel(WrappingLabel figure){
		unregisterVisuals();
		setFigure(figure);
		defaultText = getLabelTextHelper(figure);
		registerVisuals();
		refreshVisuals();
	}

	/**
	 * @generated
	 */
	@Override
	protected List getModelChildren(){
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public IGraphicalEditPart getChildBySemanticHint(String semanticHint){
		return null;
	}

	/**
	 * @generated
	 */
	protected EObject getParserElement(){
		return resolveSemanticElement();
	}

	/**
	 * @generated
	 */
	protected Image getLabelIcon(){
		return null;
	}

	/**
	 * @generated
	 */
	protected String getLabelText(){
		String text = null;
		EObject parserElement = getParserElement();
		if (parserElement != null && getParser() != null) {
			text = getParser().getPrintString(new EObjectAdapter(parserElement), getParserOptions().intValue());
		}
		if (text == null || text.length() == 0) {
			text = defaultText;
		}
		return text;
	}

	/**
	 * @generated
	 */
	public void setLabelText(String text){
		setLabelTextHelper(getFigure(), text);
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof StsToolTextSelectionEditPolicy) {
			((StsToolTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof StsToolTextSelectionEditPolicy) {
			((StsToolTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}

	/**
	 * @generated
	 */
	public String getEditText(){
		if (getParserElement() == null || getParser() == null) { return ""; //$NON-NLS-1$
		}
		return getParser().getEditString(new EObjectAdapter(getParserElement()), getParserOptions().intValue());
	}

	/**
	 * @generated
	 */
	protected boolean isEditable(){
		return getParser() != null;
	}

	/**
	 * @generated
	 */
	public ICellEditorValidator getEditTextValidator(){
		return new ICellEditorValidator() {

			public String isValid(final Object value){
				if (value instanceof String) {
					final EObject element = getParserElement();
					final IParser parser = getParser();
					try {
						IParserEditStatus valid = (IParserEditStatus) getEditingDomain().runExclusive(new RunnableWithResult.Impl() {

							public void run(){
								setResult(parser.isValidEditString(new EObjectAdapter(element), (String) value));
							}
						});
						return valid.getCode() == ParserEditStatus.EDITABLE ? null : valid.getMessage();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}

				// shouldn't get here
				return null;
			}
		};
	}

	/**
	 * @generated
	 */
	public IContentAssistProcessor getCompletionProcessor(){
		if (getParserElement() == null || getParser() == null) { return null; }
		return getParser().getCompletionProcessor(new EObjectAdapter(getParserElement()));
	}

	/**
	 * @generated
	 */
	public ParserOptions getParserOptions(){
		return ParserOptions.NONE;
	}

	/**
	 * @generated
	 */
	public IParser getParser(){
		if (parser == null) {
			parser = StsToolParserProvider.getParser(StsToolElementTypes.Agent_2001, getParserElement(), StsToolVisualIDRegistry.getType(eu.aniketos.wp1.ststool.diagram.edit.parts.AgentNameEditPart.VISUAL_ID));
		}
		return parser;
	}

	/**
	 * @generated NOT
	 */
	protected DirectEditManager getManager(){

		if (manager == null) manager = StsToolEditPartFactory.getDirectEditManager(this, this);
		return manager;
	}

	/**
	 * @generated
	 */
	protected void setManager(DirectEditManager manager){
		this.manager = manager;
	}

	/**
	 * @generated
	 */
	protected void performDirectEdit(){
		getManager().show();
	}

	/**
	 * @generated
	 */
	protected void performDirectEdit(Point eventLocation){
		if (getManager().getClass() == TextDirectEditManager.class) {
			((TextDirectEditManager) getManager()).show(eventLocation.getSWTPoint());
		}
	}

	/**
	 * @generated NOT
	 */
	private void performDirectEdit(char initialCharacter){
		if (getManager() instanceof STSCustomTextDirectEditManager) {
			((STSCustomTextDirectEditManager) getManager()).show(initialCharacter);
		} else {
			performDirectEdit();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void performDirectEditRequest(Request request){
		final Request theRequest = request;
		try {
			getEditingDomain().runExclusive(new Runnable() {

				public void run(){
					if (isActive() && isEditable()) {
						if (theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR) instanceof Character) {
							Character initialChar = (Character) theRequest.getExtendedData().get(RequestConstants.REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR);
							performDirectEdit(initialChar.charValue());
						} else if ((theRequest instanceof DirectEditRequest) && (getEditText().equals(getLabelText()))) {
							DirectEditRequest editRequest = (DirectEditRequest) theRequest;
							performDirectEdit(editRequest.getLocation());
						} else {
							performDirectEdit();
						}
					}
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void refreshVisuals(){
		super.refreshVisuals();
		refreshLabel();
		refreshFont();
		refreshFontColor();
		refreshUnderline();
		refreshStrikeThrough();
	}

	/**
	 * @generated
	 */
	protected void refreshLabel(){
		setLabelTextHelper(getFigure(), getLabelText());
		setLabelIconHelper(getFigure(), getLabelIcon());
		Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof StsToolTextSelectionEditPolicy) {
			((StsToolTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof StsToolTextSelectionEditPolicy) {
			((StsToolTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}

	/**
	 * @generated
	 */
	protected void refreshUnderline(){
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextUnderline(style.isUnderline());
		}
	}

	/**
	 * @generated
	 */
	protected void refreshStrikeThrough(){
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null && getFigure() instanceof WrappingLabel) {
			((WrappingLabel) getFigure()).setTextStrikeThrough(style.isStrikeThrough());
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void refreshFont(){
		FontStyle style = (FontStyle) getFontStyleOwnerView().getStyle(NotationPackage.eINSTANCE.getFontStyle());
		if (style != null) {
			FontData fontData = new FontData(style.getFontName(), style.getFontHeight(), (style.isBold() ? SWT.BOLD : SWT.NORMAL) | (style.isItalic() ? SWT.ITALIC : SWT.NORMAL));
			setFont(fontData);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void setFontColor(Color color){
		getFigure().setForegroundColor(color);
	}

	/**
	 * @generated
	 */
	@Override
	protected void addSemanticListeners(){
		if (getParser() instanceof ISemanticParser) {
			EObject element = resolveSemanticElement();
			parserElements = ((ISemanticParser) getParser()).getSemanticElementsBeingParsed(element);
			for (int i = 0; i < parserElements.size(); i++) {
				addListenerFilter("SemanticModel" + i, this, (EObject) parserElements.get(i)); //$NON-NLS-1$
			}
		} else {
			super.addSemanticListeners();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void removeSemanticListeners(){
		if (parserElements != null) {
			for (int i = 0; i < parserElements.size(); i++) {
				removeListenerFilter("SemanticModel" + i); //$NON-NLS-1$
			}
		} else {
			super.removeSemanticListeners();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected AccessibleEditPart getAccessibleEditPart(){
		if (accessibleEP == null) {
			accessibleEP = new AccessibleGraphicalEditPart() {

				public void getName(AccessibleEvent e){
					e.result = getLabelTextHelper(getFigure());
				}
			};
		}
		return accessibleEP;
	}

	/**
	 * @generated
	 */
	private View getFontStyleOwnerView(){
		return getPrimaryView();
	}

	/**
	 * @generated
	 */
	@Override
	protected void addNotationalListeners(){
		super.addNotationalListeners();
		addListenerFilter("PrimaryView", this, getPrimaryView()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	@Override
	protected void removeNotationalListeners(){
		super.removeNotationalListeners();
		removeListenerFilter("PrimaryView"); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	@Override
	protected void handleNotificationEvent(Notification event){
		Object feature = event.getFeature();
		if (NotationPackage.eINSTANCE.getFontStyle_FontColor().equals(feature)) {
			Integer c = (Integer) event.getNewValue();
			setFontColor(DiagramColorRegistry.getInstance().getColor(c));
		} else if (NotationPackage.eINSTANCE.getFontStyle_Underline().equals(feature)) {
			refreshUnderline();
		} else if (NotationPackage.eINSTANCE.getFontStyle_StrikeThrough().equals(feature)) {
			refreshStrikeThrough();
		} else if (NotationPackage.eINSTANCE.getFontStyle_FontHeight().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_FontName().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Bold().equals(feature) || NotationPackage.eINSTANCE.getFontStyle_Italic().equals(feature)) {
			refreshFont();
		} else {
			if (getParser() != null && getParser().isAffectingEvent(event, getParserOptions().intValue())) {
				refreshLabel();
			}
			if (getParser() instanceof ISemanticParser) {
				ISemanticParser modelParser = (ISemanticParser) getParser();
				if (modelParser.areSemanticElementsAffected(null, event)) {
					removeSemanticListeners();
					if (resolveSemanticElement() != null) {
						addSemanticListeners();
					}
					refreshLabel();
				}
			}
		}
		super.handleNotificationEvent(event);
	}

	/**
	 * @generated
	 */
	@Override
	protected IFigure createFigure(){
		// Parent should assign one using setLabel() method
		return null;
	}

}
