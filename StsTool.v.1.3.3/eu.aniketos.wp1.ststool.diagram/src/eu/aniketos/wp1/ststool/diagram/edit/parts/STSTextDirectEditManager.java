/*
* STSTextDirectEditManager.java
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
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.AncestorListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.contentassist.ContentAssistantHelper;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.TextCellEditorEx;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.CellEditorActionHandler;


/**
 * @author melaasar
 */
@SuppressWarnings( { "restriction", "deprecation" })
public abstract class STSTextDirectEditManager extends STSDirectEditManager {

	/**
	 * content assist background color
	 */
	private Color							proposalPopupBackgroundColor	= null;

	/**
	 * content assist foreground color
	 */
	private Color							proposalPopupForegroundColor	= null;

	private boolean						committed							= false;

	/**
	 * flag used to avoid unhooking listeners twice if the UI thread is blocked
	 */
	private boolean						listenersAttached					= true;

	/** String buffer to hold initial characters * */
	private StringBuffer					initialString						= new StringBuffer();

	/**
	 * Cache the font descriptor when a font is created so that it can be disposed later.
	 */
	private List							cachedFontDescriptors			= new ArrayList();

	private IActionBars					actionBars;

	private CellEditorActionHandler	actionHandler;

	private IAction						copy, cut, paste, undo, redo, find, selectAll, delete;

	private Font							zoomLevelFont						= null;

	/**
	 * The superclass only relocates the cell editor when the location of the editpart's figure moves, but we need to also relocate the cell editor when the text figure's location changes.
	 */
	private AncestorListener			textFigureListener;

	/**
	 * Cache locally so we can check if the user specified an editorType.
	 */
	private Class							editorType;

	/**
	 * constructor
	 * 
	 * @param source
	 *           <code>GraphicalEditPart</code> to support direct edit of. The figure of the <code>source</code> edit part must be of type <code>WrapLabel</code>.
	 */
	public STSTextDirectEditManager(ITextAwareEditPart source) {
		this(source, null, getTextCellEditorLocator(source));
	}

	/**
	 * @param source
	 * @param editorType
	 * @param locator
	 */
	public STSTextDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
		super(source, editorType, locator);
		this.editorType = editorType;
	}

	/**
	 * @param source
	 *           the <code>ITextAwareEditPart</code> to determine the cell editor for
	 * @return the <code>CellEditorLocator</code> that is appropriate for the source <code>EditPart</code>
	 */
	public static CellEditorLocator getTextCellEditorLocator(final ITextAwareEditPart source){

		final ILabelDelegate label = (ILabelDelegate) source.getAdapter(ILabelDelegate.class);
		if (label != null) { return new CellEditorLocator() {

			public void relocate(CellEditor celleditor){
				Text text = (Text) celleditor.getControl();

				Rectangle rect = label.getTextBounds().getCopy();
				if (label.getText().length() <= 0) {
					// if there is no text, let's assume a default size
					// because it looks silly when the cell editor it tiny.
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)));

					if (label.isTextWrapOn()) {
						// adjust the location of the cell editor based on text
						// justification (i.e. where the cursor will be
						if (label.getTextJustification() == PositionConstants.RIGHT) {
							rect.translate(-rect.width, 0);
						} else if (label.getTextJustification() == PositionConstants.CENTER) {
							rect.translate(-rect.width / 2, 0);
						}
					}
				}

				if (!text.getFont().isDisposed()) {
					// Font may be disposed if the locator is called while
					// this manager is being brought down in which case the
					// calls below that use the font will result in an
					// exception.
					if (label.isTextWrapOn()) {
						// When zoomed in, the height of this rectangle is not
						// sufficient because the text is shifted downwards a
						// little bit. Add some to the height to compensate for
						// this. I'm not sure why this is happening, but I can
						// see the text shifting down even in a label on a GEF
						// logic diagram when zoomed into 400%.
						int charHeight = FigureUtilities.getFontMetrics(text.getFont()).getHeight();
						rect.resize(0, charHeight / 2);
					} else {

						rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)));

						// If SWT.WRAP is not passed in as a style of the
						// TextCellEditor, then for some reason the first
						// character disappears upon entering the second 
						// character. This should be investigated and an 
						// SWT bug logged.
						int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
						rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
					}
				}

				org.eclipse.swt.graphics.Rectangle newRect = text.computeTrim(rect.x, rect.y, rect.width, rect.height);
				if (!newRect.equals(text.getBounds())) {
					text.setBounds(newRect.x, newRect.y, newRect.width, newRect.height);
				}
			}
		}; }

		// return a default figure locator
		return new CellEditorLocator() {

			public void relocate(CellEditor celleditor){
				Text text = (Text) celleditor.getControl();
				Rectangle rect = source.getFigure().getBounds().getCopy();
				source.getFigure().translateToAbsolute(rect);
				if (!rect.equals(new Rectangle(text.getBounds()))) {
					text.setBounds(rect.x, rect.y, rect.width, rect.height);
				}
			}
		};
	}

	/**
	 * @param source
	 *           the <code>GraphicalEditPart</code> that is used to determine which <code>CellEditor</code> class to use.
	 * @return the <code>Class</code> of the <code>CellEditor</code> to use for the text editing.
	 * @deprecated to override the cell editor class, use {@link #createCellEditorOn(Composite)}, this provides the flexibility necessary to initialize the cell editor with a style.
	 */
	public static Class getTextCellEditorClass(GraphicalEditPart source){
		IFigure figure = source.getFigure();

		if (figure instanceof WrapLabel && ((WrapLabel) figure).isTextWrapped()) return WrapTextCellEditor.class;

		return TextCellEditorEx.class;
	}

	/**
	 * This method is overridden so that the editor class can have a style as the style needs to be passed into the editor class when it is created. It will default to the super behavior if an <code>editorType</code> was passed into the constructor.
	 * 
	 * @since 2.1
	 */
	@Override
	protected CellEditor createCellEditorOn(Composite composite){

		// if the client has overridden this class and provided their own editor
		// type, then we should use that
		if (editorType != null) { return super.createCellEditorOn(composite); }

		ILabelDelegate label = (ILabelDelegate) getEditPart().getAdapter(ILabelDelegate.class);
		if (label != null && label.isTextWrapOn()) {
			int style = SWT.WRAP | SWT.MULTI;

			switch (label.getTextJustification()) {
				case PositionConstants.LEFT:
					style = style | SWT.LEAD;
				break;
				case PositionConstants.RIGHT:
					style = style | SWT.TRAIL;
				break;
				case PositionConstants.CENTER:
					style = style | SWT.CENTER;
				break;
				default:
				break;
			}
			return new WrapTextCellEditor(composite, style);
		} else {
			return new TextCellEditorEx(composite);
		}
	}

	/**
	 * Given a label figure object, this will calculate the correct Font needed to display into screen coordinates, taking into account the current mapmode. This will typically be used by direct edit cell editors that need to display independent of the zoom or any coordinate mapping that is taking place on the drawing surface.
	 * 
	 * @param label
	 *           the label to use for the font calculation
	 * @return the <code>Font</code> that is scaled to the screen coordinates. Note: the returned <code>Font</code> should not be disposed since it is cached by a common resource manager.
	 */
	protected Font getScaledFont(IFigure label){
		Font scaledFont = label.getFont();
		FontData data = scaledFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, MapModeUtil.getMapMode(label).DPtoLP(data.getHeight()));
		label.translateToAbsolute(fontSize);

		if (Math.abs(data.getHeight() - fontSize.height) < 2) fontSize.height = data.getHeight();

		try {
			FontDescriptor fontDescriptor = FontDescriptor.createFrom(data);
			cachedFontDescriptors.add(fontDescriptor);
			return getResourceManager().createFont(fontDescriptor);
		} catch (DeviceResourceException e) {
			Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "getScaledFont", e); //$NON-NLS-1$
			Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING, "getScaledFont", e); //$NON-NLS-1$
		}
		return JFaceResources.getDefaultFont();
	}


	@Override
	protected void initCellEditor(){
		committed = false;

		// Get the Text Compartments Edit Part
		ITextAwareEditPart textEP = (ITextAwareEditPart) getEditPart();

		setEditText(textEP.getEditText());

		IFigure label = textEP.getFigure();
		Assert.isNotNull(label);
		Text text = (Text) getCellEditor().getControl();
		// scale the font accordingly to the zoom level
		text.setFont(getScaledFont(label));


		// register a validator on the cell editor
		getCellEditor().setValidator(textEP.getEditTextValidator());

		if (textEP.getParser() != null) {
			IContentAssistProcessor processor = textEP.getCompletionProcessor();
			if (processor != null) {
				// register content assist
				proposalPopupBackgroundColor = new Color(getCellEditor().getControl().getShell().getDisplay(), new RGB(254, 241, 233));
				proposalPopupForegroundColor = new Color(getCellEditor().getControl().getShell().getDisplay(), new RGB(0, 0, 0));

				ContentAssistantHelper.createTextContentAssistant(text, proposalPopupForegroundColor, proposalPopupBackgroundColor, processor);
			}
		}

		//Hook the cell editor's copy/paste actions to the actionBars so that they can
		// be invoked via keyboard shortcuts.
		actionBars = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorSite().getActionBars();
		saveCurrentActions(actionBars);
		actionHandler = new CellEditorActionHandler(actionBars);
		actionHandler.addCellEditor(getCellEditor());
		actionBars.updateActionBars();
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#commit()
	 */
	@Override
	protected void commit(){
		Shell activeShell = Display.getCurrent().getActiveShell();
		if (activeShell != null && getCellEditor().getControl().getShell().equals(activeShell.getParent())) {
			Control[] children = activeShell.getChildren();
			if (children.length == 1 && children[0] instanceof Table) {
				/*
				 * CONTENT ASSIST: focus is lost to the content assist pop up -
				 * stay in focus
				 */
				getCellEditor().getControl().setVisible(true);
				((TextCellEditorEx) getCellEditor()).setDeactivationLock(true);
				return;
			}
		}

		// content assist hacks
		if (committed) {
			bringDown();
			return;
		}
		committed = true;
		super.commit();
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
	 */
	@Override
	protected void bringDown(){
		if (proposalPopupForegroundColor != null) {
			proposalPopupForegroundColor.dispose();
			proposalPopupForegroundColor = null;
		}
		if (proposalPopupBackgroundColor != null) {
			proposalPopupBackgroundColor.dispose();
			proposalPopupBackgroundColor = null;
		}

		// myee - RATLC00523014: crashes when queued in asyncExec()
		eraseFeedback();

		initialString = new StringBuffer();

		Display.getCurrent().asyncExec(new Runnable() {

			public void run(){
				// Content Assist hack - allow proper cleanup on childen
				// controls
				STSTextDirectEditManager.super.bringDown();
			}
		});

		for (Iterator iter = cachedFontDescriptors.iterator(); iter.hasNext();) {
			getResourceManager().destroyFont((FontDescriptor) iter.next());
		}
		cachedFontDescriptors.clear();

		if (actionHandler != null) {
			actionHandler.dispose();
			actionHandler = null;
		}
		if (actionBars != null) {
			restoreSavedActions(actionBars);
			actionBars.updateActionBars();
			actionBars = null;
		}
	}

	/**
	 * This method is used to set the cell editors text
	 * 
	 * @param toEdit
	 *           String to be set in the cell editor
	 */
	public void setEditText(String toEdit){

		// Get the cell editor
		CellEditor cellEditor = getCellEditor();

		// IF the cell editor doesn't exist yet...
		if (cellEditor == null) {
			// Do nothing
			return;
		}

		// Get the Text Compartment Edit Part
		ITextAwareEditPart textEP = (ITextAwareEditPart) getEditPart();

		// Get the Text control
		Text textControl = (Text) cellEditor.getControl();

		// Set the Figures text
		textEP.setLabelText(toEdit);


		// See RATLC00522324
		if (cellEditor instanceof TextCellEditorEx) {
			((TextCellEditorEx) cellEditor).setValueAndProcessEditOccured(toEdit);
		} else {
			cellEditor.setValue(toEdit);
		}

		// Set the controls text and position the caret at the end of the text
		textControl.setSelection(toEdit.length());
	}

	/**
	 * Performs show and sets the edit string to be the initial character or string
	 * 
	 * @param initialChar
	 */
	public void show(char initialChar){
		initialString = initialString.append(initialChar);
		show();
		if (SWT.getPlatform() != "carbon") { //$NON-NLS-1$ 
			// Set the cell editor text to the initial character
			setEditText(initialString.toString());
		}
	}

	/**
	 * This method obtains the fonts that are being used by the figure at its zoom level.
	 * 
	 * @param gep
	 *           the associated <code>GraphicalEditPart</code> of the figure
	 * @param actualFont
	 *           font being used by the figure
	 * @param display
	 * @return <code>actualFont</code> if zoom level is 1.0 (or when there's an error), new Font otherwise.
	 */
	private Font getZoomLevelFont(Font actualFont,Display display){
		Object zoom = getEditPart().getViewer().getProperty(ZoomManager.class.toString());

		if (zoom != null) {
			double zoomLevel = ((ZoomManager) zoom).getZoom();

			if (zoomLevel == 1.0f) return actualFont;

			FontData[] fd = new FontData[actualFont.getFontData().length];
			FontData tempFD = null;

			for (int i = 0; i < fd.length; i++) {
				tempFD = actualFont.getFontData()[i];

				fd[i] = new FontData(tempFD.getName(), (int) (zoomLevel * tempFD.getHeight()), tempFD.getStyle());
			}

			try {
				FontDescriptor fontDescriptor = FontDescriptor.createFrom(fd);
				cachedFontDescriptors.add(fontDescriptor);
				return getResourceManager().createFont(fontDescriptor);
			} catch (DeviceResourceException e) {
				Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(), "getZoomLevelFonts", e); //$NON-NLS-1$
				Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING, "getZoomLevelFonts", e); //$NON-NLS-1$

				return actualFont;
			}
		} else
			return actualFont;
	}

	@Override
	public void show(){
		super.show();

		IFigure fig = getEditPart().getFigure();

		Control control = getCellEditor().getControl();
		this.zoomLevelFont = getZoomLevelFont(fig.getFont(), control.getDisplay());

		control.setFont(this.zoomLevelFont);

		//since the font's have been resized, we need to resize the  Text control...
		getLocator().relocate(getCellEditor());

	}

	/**
	 * 
	 * Performs show and sends an extra mouse click to the point location so that cursor appears at the mouse click point
	 * 
	 * The Text control does not allow for the cursor to appear at point location but at a character location
	 * 
	 * @param location
	 */
	public void show(Point location){
		show();
		sendClickToCellEditor(location);
	}

	private void sendClickToCellEditor(final Point location){
		//make sure the diagram doesn't receive the click event..
		getCellEditor().getControl().setCapture(true);

		if (getCellEditor() != null && getCellEditor().getControl().getBounds().contains(location)) sendMouseClick(location);
	}


	/**
	 * 
	 * Sends a SWT MouseUp and MouseDown event to the point location to the current Display
	 * 
	 * @param location
	 */
	private void sendMouseClick(final Point location){

		final Display currDisplay = Display.getCurrent();
		currDisplay.asyncExec(new Runnable() {

			public void run(){
				Event event;
				event = new Event();
				event.type = SWT.MouseDown;
				event.button = 1;
				event.x = location.x;
				event.y = location.y;
				currDisplay.post(event);
				event.type = SWT.MouseUp;
				currDisplay.post(event);
			}
		});
	}

	@Override
	protected void hookListeners(){
		super.hookListeners();

		// TODO: This gets around the problem of the cell editor not growing big
		// enough when in autosize mode because it doesn't listen to textflow
		// size changes. The superclass should be modified to not assume we want
		// to listen to the editpart's figure.
		ILabelDelegate label = (ILabelDelegate) getEditPart().getAdapter(ILabelDelegate.class);
		if (label != null && getEditPart().getFigure() instanceof WrappingLabel) {

			textFigureListener = new AncestorListener.Stub() {

				@Override
				public void ancestorMoved(IFigure ancestor){
					getLocator().relocate(getCellEditor());
				}
			};
			((IFigure) ((WrappingLabel) getEditPart().getFigure()).getTextFigure().getChildren().get(0)).addAncestorListener(textFigureListener);
		}
	}

	/*
	 * Overrides super unhookListeners to set listeners attached flag This
	 * method prevents unhooking listeners twice if the UI thread is blocked.
	 * For example, a validation dialog may block the thread
	 */
	@Override
	protected void unhookListeners(){
		if (listenersAttached) {
			listenersAttached = false;
			super.unhookListeners();

			ILabelDelegate label = (ILabelDelegate) getEditPart().getAdapter(ILabelDelegate.class);
			if (label != null && textFigureListener != null) {
				((IFigure) ((WrappingLabel) getEditPart().getFigure()).getTextFigure().getChildren().get(0)).removeAncestorListener(textFigureListener);
				textFigureListener = null;
			}
		}
	}

	/* 
	 * Sets the listeners attached flag if the cell editor exists
	 */
	@Override
	protected void setCellEditor(CellEditor editor){
		super.setCellEditor(editor);
		if (editor != null) {
			listenersAttached = true;
		}
	}

	@Override
	public void showFeedback(){
		try {
			getEditPart().getRoot();
			super.showFeedback();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Gets the resource manager to remember the resources allocated for this graphical viewer. All resources will be disposed when the graphical viewer is closed if they have not already been disposed.
	 * 
	 * @return
	 */
	protected ResourceManager getResourceManager(){
		return ((DiagramGraphicalViewer) getEditPart().getViewer()).getResourceManager();
	}

	private void saveCurrentActions(IActionBars _actionBars){
		copy = _actionBars.getGlobalActionHandler(ActionFactory.COPY.getId());
		paste = _actionBars.getGlobalActionHandler(ActionFactory.PASTE.getId());
		delete = _actionBars.getGlobalActionHandler(ActionFactory.DELETE.getId());
		selectAll = _actionBars.getGlobalActionHandler(ActionFactory.SELECT_ALL.getId());
		cut = _actionBars.getGlobalActionHandler(ActionFactory.CUT.getId());
		find = _actionBars.getGlobalActionHandler(ActionFactory.FIND.getId());
		undo = _actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId());
		redo = _actionBars.getGlobalActionHandler(ActionFactory.REDO.getId());
	}

	private void restoreSavedActions(IActionBars _actionBars){
		_actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
		_actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), paste);
		_actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), delete);
		_actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAll);
		_actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cut);
		_actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), find);
		_actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undo);
		_actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redo);
	}
}
