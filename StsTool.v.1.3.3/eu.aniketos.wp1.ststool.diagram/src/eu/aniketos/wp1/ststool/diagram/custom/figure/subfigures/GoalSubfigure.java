/*
* GoalSubfigure.java
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
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;


public class GoalSubfigure extends RoundedRectangle {

	public static final Dimension	THIS_DIMENSION	= new Dimension(100, 50);
	private boolean					capability		= false;

	@Override
	protected void fillShape(Graphics g){

		super.fillShape(g);
		if (capability) {
			Dimension d = new Dimension(30, 30);
			drawV(g, new Point(bounds.x + bounds.width - d.width - 1, bounds.y + bounds.height - d.height), d);

		}
	}

	public void setCapability(boolean capability){

		this.capability = capability;
		repaint();
	}

	private void drawV(Graphics g,Point p,Dimension d){

		Color temp = g.getForegroundColor();
		float linewithd = g.getLineWidthFloat();


		g.setForegroundColor(ColorConstants.darkGreen);
		g.setLineWidthFloat(2.5F);

		int x = (int) (d.width / 6.0);
		int y = (int) (d.height / 6.0);

		g.drawLine(new Point(p.x + x, p.y + (d.height / 2)), new Point(p.x + (d.width() / 2), p.y + d.height - y));
		g.drawLine(new Point(p.x + (d.width() / 2), p.y + d.height - y), new Point(p.x + d.width - x, p.y + y));

		g.setForegroundColor(temp);
		g.setLineWidthFloat(linewithd);
	}

	/*private void drawX(Graphics g,Point p,Dimension d){

		Color temp = g.getForegroundColor();
		float linewithd = g.getLineWidthFloat();

		int x = (int) (d.width / 6.0);
		int y = (int) (d.height / 6.0);
		g.setForegroundColor(ColorConstants.red);
		g.setLineWidthFloat(2.5F);

		g.drawLine(p.x + x, p.y + y, p.x + d.width - x, p.y + d.height - y);
		g.drawLine(p.x + d.width - x, p.y + y, p.x + x, p.y + d.height() - y);

		g.setForegroundColor(temp);
		g.setLineWidthFloat(linewithd);
	}*/
}
