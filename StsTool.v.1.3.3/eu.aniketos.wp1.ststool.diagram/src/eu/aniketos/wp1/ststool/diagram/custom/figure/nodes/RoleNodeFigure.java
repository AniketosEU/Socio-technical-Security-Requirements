/*
* RoleNodeFigure.java
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
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IOvalAnchorableFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.StsWrappingLabel;

public class RoleNodeFigure extends Ellipse implements IOvalAnchorableFigure, ISTSErrorMarker {

	public static final Color		THIS_BACK		= new Color(null, 255, 200, 200);	//new Color(null, 205, 92, 92);
	public static final Color		THIS_FORE		= ColorConstants.black;
	public static final Dimension	THIS_DIMENSION	= new Dimension(100, 100);
	private StsWrappingLabel		nameLabel;

	public RoleNodeFigure() {

		super();
		super.setBackgroundColor(THIS_BACK);
		super.setForegroundColor(THIS_FORE);
		super.setPreferredSize(THIS_DIMENSION);
		super.setBorder(new MarginBorder(3));
	}

	private void initLabel(){

		this.setLayoutManager(new StackLayout());
		nameLabel = new StsWrappingLabel("<Role...>");
		nameLabel.setAlignment(PositionConstants.CENTER);
		nameLabel.setTextWrap(true);
		this.add(nameLabel);
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
	public void setLineWidth(int width){

		//super.setLineWidth(setLineWidth);
	}

	@Override
	public void setBackgroundColor(Color bg){

		//super.setBackgroundColor(THIS_BACK);
	}

	@Override
	public void setForegroundColor(Color fg){

		//super.setForegroundColor(THIS_FORE);
	}


	@Override
	protected void fillShape(Graphics graphics){

		// TODO Auto-generated method stub
		super.fillShape(graphics);
	}

	@Override
	protected void outlineShape(Graphics graphics){

		Rectangle r = this.getBounds().getCopy();
		r.shrink((getLineWidth() - 1) / 2, (getLineWidth() - 1) / 2);
		graphics.drawArc(r.x, r.y + (r.height / 6), r.width, 2 * r.height / 3, 180, 180);
		super.outlineShape(graphics);
	}

	@Override
	public Rectangle getOvalBounds(){

		return getBounds();
	}

	@Override
	public void setError(STSErrorType errorType){
		switch (errorType) {
			case NO_ERROR:
				super.setLineWidth(1);
				super.setForegroundColor(THIS_FORE);
			break;
			case WARNING:
				super.setLineWidth(2);
				super.setForegroundColor(ColorConstants.orange);
			break;
			case ERROR:
				super.setLineWidth(2);
				super.setForegroundColor(ColorConstants.red);
			break;
		}
	}
}
