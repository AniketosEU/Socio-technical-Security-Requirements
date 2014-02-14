/*
* CompatibleMiddleFigure.java
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
package eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;


public class CompatibleMiddleFigure extends Ellipse implements MouseListener, MouseMotionListener, ISTSErrorMarker {

	private final static Cursor	ARROW_CURSOR	= new Cursor(null, SWT.CURSOR_ARROW);

	public CompatibleMiddleFigure(Dimension dim) {
		super();
		dim = new Dimension(15, 15);
		setFill(true);
		setOutline(true);
		setCursor(ARROW_CURSOR);

		/*FlowLayout fl = new FlowLayout(false);
		fl.setHorizontal(false);
		fl.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		fl.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		fl.setStretchMinorAxis(false);
		this.setLayoutManager(fl);*/

		setBackgroundColor(ColorConstants.white);
		setForegroundColor(ColorConstants.black);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMinimumSize(dim);
	}



	@Override
	protected void outlineShape(Graphics g){
		super.outlineShape(g);
		g.pushState();
		g.setLineWidth(1);
		Rectangle b = getBounds().getCopy();
		Dimension d = new Dimension((int) (b.getSize().width / 2.5), b.getSize().height / 4);

		Point p = new Point(b.x + ((b.width - d.width) / 2), b.y + ((b.height - d.height) / 2));
		Rectangle r = new Rectangle(p, d);

		g.drawLine(r.getTopLeft(), r.getTopRight());
		g.drawLine(r.getBottomLeft(), r.getBottomRight());

		g.popState();
	}



	@Override
	public void mouseDoubleClicked(MouseEvent me){
		me.consume();
	}

	@Override
	public void mousePressed(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseReleased(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseDragged(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseEntered(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseExited(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseHover(MouseEvent me){
		me.consume();
	}

	@Override
	public void mouseMoved(MouseEvent me){
		me.consume();
	}

	@Override
	public void setError(STSErrorType error){
		switch (error) {
			case NO_ERROR:
				super.setForegroundColor(ColorConstants.black);
			break;
			case WARNING:
				super.setForegroundColor(ColorConstants.orange);
			break;
			case ERROR:
				super.setForegroundColor(ColorConstants.red);
			break;
		}
		repaint();
	}

}
