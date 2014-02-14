/*
* StsToolTextSelectionEditPolicy.java
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
package eu.aniketos.wp1.ststool.diagram.edit.policies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * @generated
 */
public class StsToolTextSelectionEditPolicy extends SelectionEditPolicy {

	/**
	 * @generated
	 */
	private IFigure			selectionFeedbackFigure;

	/**
	 * @generated
	 */
	private IFigure			focusFeedbackFigure;

	/**
	 * @generated
	 */
	private FigureListener	hostPositionListener;

	/**
	 * @generated
	 */
	@Override
	protected void showPrimarySelection(){
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(true);
			((WrappingLabel) getHostFigure()).setFocus(true);
		} else {
			showSelection();
			showFocus();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void showSelection(){
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(true);
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			hideSelection();
			addFeedback(selectionFeedbackFigure = createSelectionFeedbackFigure());
			getHostFigure().addFigureListener(getHostPositionListener());
			refreshSelectionFeedback();
			hideFocus();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void hideSelection(){
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setSelected(false);
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			if (selectionFeedbackFigure != null) {
				removeFeedback(selectionFeedbackFigure);
				getHostFigure().removeFigureListener(getHostPositionListener());
				selectionFeedbackFigure = null;
			}
			hideFocus();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void showFocus(){
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setFocus(true);
		} else {
			hideFocus();
			addFeedback(focusFeedbackFigure = createFocusFeedbackFigure());
			refreshFocusFeedback();
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void hideFocus(){
		if (getHostFigure() instanceof WrappingLabel) {
			((WrappingLabel) getHostFigure()).setFocus(false);
		} else {
			if (focusFeedbackFigure != null) {
				removeFeedback(focusFeedbackFigure);
				focusFeedbackFigure = null;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Rectangle getFeedbackBounds(){
		Rectangle bounds;
		if (getHostFigure() instanceof Label) {
			bounds = ((Label) getHostFigure()).getTextBounds();
			bounds.intersect(getHostFigure().getBounds());
		} else {
			bounds = getHostFigure().getBounds().getCopy();
		}
		getHostFigure().getParent().translateToAbsolute(bounds);
		getFeedbackLayer().translateToRelative(bounds);
		return bounds;
	}

	/**
	 * @generated
	 */
	protected IFigure createSelectionFeedbackFigure(){
		if (getHostFigure() instanceof Label) {
			Label feedbackFigure = new Label();
			feedbackFigure.setOpaque(true);
			feedbackFigure.setBackgroundColor(ColorConstants.menuBackgroundSelected);
			feedbackFigure.setForegroundColor(ColorConstants.menuForegroundSelected);
			return feedbackFigure;
		} else {
			RectangleFigure feedbackFigure = new RectangleFigure();
			feedbackFigure.setFill(false);
			return feedbackFigure;
		}
	}

	/**
	 * @generated
	 */
	protected IFigure createFocusFeedbackFigure(){
		return new Figure() {

			protected void paintFigure(Graphics graphics){
				graphics.drawFocus(getBounds().getResized(-1, -1));
			}
		};
	}

	/**
	 * @generated
	 */
	protected void updateLabel(Label target){
		Label source = (Label) getHostFigure();
		target.setText(source.getText());
		target.setTextAlignment(source.getTextAlignment());
		target.setFont(source.getFont());
	}

	/**
	 * @generated
	 */
	protected void refreshSelectionFeedback(){
		if (selectionFeedbackFigure != null) {
			if (selectionFeedbackFigure instanceof Label) {
				updateLabel((Label) selectionFeedbackFigure);
				selectionFeedbackFigure.setBounds(getFeedbackBounds());
			} else {
				selectionFeedbackFigure.setBounds(getFeedbackBounds().expand(5, 5));
			}
		}
	}

	/**
	 * @generated
	 */
	protected void refreshFocusFeedback(){
		if (focusFeedbackFigure != null) {
			focusFeedbackFigure.setBounds(getFeedbackBounds());
		}
	}

	/**
	 * @generated
	 */
	public void refreshFeedback(){
		refreshSelectionFeedback();
		refreshFocusFeedback();
	}

	/**
	 * @generated
	 */
	private FigureListener getHostPositionListener(){
		if (hostPositionListener == null) {
			hostPositionListener = new FigureListener() {

				public void figureMoved(IFigure source){
					refreshFeedback();
				}
			};
		}
		return hostPositionListener;
	}
}
