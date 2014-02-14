/*
* CompositionRelationFigure.java
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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;


public abstract class CompositionRelationFigure extends SimpleConnection {

	private Point							lastPoint;
	private CompositionGroupFigure	figure;


	public Point getLastPoint(){

		return lastPoint;
	}

	public void setLastPoint(Point nextPoint){

		this.lastPoint = nextPoint;
		if (nextPoint == null)
			figure.setVisibility(false);
		else
			figure.setVisibility(true);
		figure.repaint();
	}

	public CompositionRelationFigure() {

		super();
		figure = new CompositionGroupFigure();
		figure.setLabel(getLabel());

		add(figure);
	}

	@Override
	protected void outlineShape(Graphics g){

		super.outlineShape(g);

		if (lastPoint != null) {
			//g.drawRectangle(getBounds().getCopy());

			if (getPoints().size() < 2) { return; }
			Point myPoint = PointListUtilities.calculatePointRelativeToLine(getPoints(), 0, 10, false);
			Point startPoint = getPoints().getFirstPoint();
			int h = 30;
			int w = 30;
			Rectangle r = new Rectangle();

			//.setForegroundColor(ColorConstants.blue);
			//g.drawLine(startPoint, getPoints().getLastPoint());
			//g.setForegroundColor(ColorConstants.cyan);
			//g.drawLine(startPoint, lastPoint);
			//g.setForegroundColor(ColorConstants.black);
			r.setLocation(startPoint.x - w / 2, startPoint.y - h / 2);
			r.setSize(w, h);
			Point testPoint = myPoint.getTranslated(startPoint.getNegated());
			Point testPoint2 = lastPoint.getTranslated(startPoint.getNegated());
			//System.out.println("lastpoint:" + lastPoint + "startPoint:" + startPoint) ;
			//System.out.println("testPoint:" + testPoint + "testPoint2:" + testPoint2) ;
			double angle1 = Math.toDegrees(Math.atan2(testPoint.y, testPoint.x));
			double angle2 = Math.toDegrees(Math.atan2(testPoint2.y, testPoint2.x));
			//System.out.println(angle1 + "  " + angle2);

			double primaryAngle = angle1;// > angle2? angle1:angle2;
			double secondaryAngle = angle2;// > angle2? angle2:angle1;
			double angleOffset = 360 - primaryAngle;
			double angleBetween = primaryAngle - secondaryAngle;
			//System.out.println("offset:" + angleOffset + "  " + angleBetween);

			figure.setAngleBetween(angleBetween);
			figure.setAngleOffset(angleOffset);
			figure.setInnerBound(r.getCopy());
			//figure.setLabel(getLabel());
			Rectangle rect = r.getCopy();
			rect.height += 20;
			rect.width += 10;
			figure.setBounds(rect);
			//figure.repaint();
			//g.drawArc(r, (int)angleOffset, (int)angleBetween);
			//g.drawString(compositionType.getLiteral(), r.getBottomLeft());
		} else {
			if (getPoints().size() < 2) { return; }
			Point startPoint = getPoints().getFirstPoint();
			int h = 30;
			int w = 30;
			Rectangle r = new Rectangle();

			r.setLocation(startPoint.x - (w / 2), startPoint.y - h / 2);
			r.setSize(w, h);
			figure.setInnerBound(r.getCopy());
			Rectangle rect = r.getCopy();
			rect.height += 20;
			rect.width += 10;
			figure.setBounds(rect);
		}
	}

	abstract String getLabel();
}
