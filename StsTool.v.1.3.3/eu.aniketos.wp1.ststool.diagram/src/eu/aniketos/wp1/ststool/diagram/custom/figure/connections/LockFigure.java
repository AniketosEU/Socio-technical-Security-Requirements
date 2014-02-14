/*
* LockFigure.java
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
package eu.aniketos.wp1.ststool.diagram.custom.figure.connections;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public class LockFigure extends RectangleFigure {

	boolean	open;

	public LockFigure(boolean open) {

		this.setPreferredSize(new Dimension(15, 22));
		this.setMinimumSize(new Dimension(15, 22));
		this.setMaximumSize(new Dimension(15, 22));
		this.open = !open;
		setStatus(open);
		setOpaque(false);
	}

	public void setStatus(boolean open){

		if (this.open == open) return;
		this.open = open;
		if (open) {
			this.setForegroundColor(ColorConstants.lightGray);
		} else {
			this.setForegroundColor(ColorConstants.black);
		}
	}


	private int	border	= 2;
	private int	offset	= 20;

	@Override
	protected void outlineShape(Graphics g){

		Rectangle b = getBounds().getCopy();
		b.shrink(border, border);

		g.pushState();
		g.setBackgroundColor(getForegroundColor());
		g.fillRectangle(new Rectangle(new Point(b.x, b.y + b.height / 2), new Dimension(b.width, b.height / 2)));
		g.setLineWidth(2);
		b.shrink(2, 0);
		if (open) {
			g.drawArc(b, offset, 180 - offset);
		} else {
			g.drawArc(b, 0, 180);
		}
		g.popState();
	}


}
