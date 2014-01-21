/*
* GoalNodeFigure.java
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
package eu.aniketos.wp1.ststool.diagram.custom.figure.nodes;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.StsWrappingLabel;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.GoalSubfigure;

public class GoalNodeFigure extends RoundedRectangle implements ISTSErrorMarker {

	private static final Color		THIS_BACK			= new Color(null, 200, 255, 200);	//new Color(null, 110, 139, 61);
	private static final Color		THIS_BACK_DELEG	= new Color(null, 162, 224, 162);
	private static final Color		THIS_FORE			= ColorConstants.black;


	private StsWrappingLabel		nameLabel;

	private GoalSubfigure			goalShape			= new GoalSubfigure();

	public static final Dimension	THIS_DIMENSION		= new Dimension(100, 50);


	public GoalNodeFigure() {

		super();
		setXOR(true);
		setFill(false);
		setOutline(false);
		super.setLayoutManager(new StackLayout());

		super.setPreferredSize(THIS_DIMENSION);
		goalShape.setBackgroundColor(THIS_BACK);
		goalShape.setForegroundColor(THIS_FORE);
		goalShape.setCornerDimensions(new Dimension(20, 20));
		goalShape.setBorder(new MarginBorder(3));

		initLabel();

		add(goalShape);
	}

	@Override
	public void setLayoutManager(LayoutManager manager){

	}

	private void initLabel(){

		goalShape.setLayoutManager(new StackLayout());
		nameLabel = new StsWrappingLabel("<Goal...>");
		nameLabel.setAlignment(PositionConstants.CENTER);
		nameLabel.setTextWrap(true);
		goalShape.add(nameLabel);
	}

	public WrappingLabel getNameLabel(){

		if (nameLabel == null) initLabel();
		return nameLabel;
	}

	@Override
	public IFigure getToolTip(){

		if (nameLabel != null) {
			Label result = new Label(nameLabel.getText());
			return result;
		}
		return null;
	}

	@Override
	public void setBackgroundColor(Color bg){

		//super.setBackgroundColor(THIS_BACK);
	}

	@Override
	public void setForegroundColor(Color fg){

		//super.setForegroundColor(THIS_FORE);
	}

	public Point getSourceAnchorPoint(){

		Point p = getBounds().getBottomLeft();
		p.setX(p.x() + (getBounds().width / 2));
		p.setY(p.y());
		translateToAbsolute(p);
		return p;

	}

	public Point getTargetAnchorPoint(){

		Point p = getBounds().getTopLeft();
		p.setX(p.x() + (getBounds().width / 2));
		translateToAbsolute(p);
		return p;
	}


	public void setCapability(boolean capability){

		goalShape.setCapability(capability);
	}


	public void setDelegated(boolean delegated){
		if (!delegated) {
			goalShape.setBackgroundColor(THIS_BACK);
		} else {
			goalShape.setBackgroundColor(THIS_BACK_DELEG);
		}
	}

	@Override
	public void setError(STSErrorType errorType){
		switch (errorType) {
			case NO_ERROR:
				goalShape.setForegroundColor(THIS_FORE);
				goalShape.setLineWidth(1);
			break;
			case WARNING:
				goalShape.setForegroundColor(ColorConstants.orange);
				goalShape.setLineWidth(2);
			break;
			case ERROR:
				goalShape.setForegroundColor(ColorConstants.red);
				goalShape.setLineWidth(2);
			break;
		}
	}
}
