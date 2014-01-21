/*
* LabaledConnection.java
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

import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

public class LabaledConnection extends SimpleConnection {

	final private static Font	TEXT_FONT	= new Font(null, "Arial", 10, SWT.NORMAL);

	RotableLabel					middleDecoration;

	public LabaledConnection(String label) {
		super();
		middleDecoration = new RotableLabel(" " + label + " ");
		ConnectionLocator locator = new ConnectionLocator(this, ConnectionLocator.MIDDLE);
		add(middleDecoration, locator);
	}



	@Override
	public void paint(Graphics g){
		//if(!System.getProperty("os.name").startsWith("Windows")){
		if (true) {
			super.paint(g);
			return;
		}
		List<Figure> fl = getChildren();
		Label l = null;
		for (Figure f : fl) {
			if (f instanceof Label) {
				l = (Label) f;
			}
			f.paint(g);
		}

		PointList labelPoints = new PointList();
		Rectangle r = l.getBounds();
		if (r != null) {
			labelPoints.addPoint(r.x, r.y);
			labelPoints.addPoint(r.x + r.width, r.y);
			labelPoints.addPoint(r.x + r.width, r.y + r.height);
			labelPoints.addPoint(r.x, r.y + r.height);
			labelPoints.addPoint(r.x, r.y);
		}

		PointList connectionPoints = getPoints();
		int index;
		boolean pointInLabel;
		if (connectionPoints.size() % 2 == 0) {
			index = (connectionPoints.size() / 2) - 1;
			pointInLabel = false;
		} else {
			index = (connectionPoints.size() / 2) - 1;
			pointInLabel = true;
		}


		try {
			for (int i = 0; i < connectionPoints.size() - 1; i++) {
				if (i == index) {
					PointList intersections = new PointList();
					PointList distances = new PointList();
					PointList segment = new PointList();
					segment.addPoint(connectionPoints.getPoint(i));
					segment.addPoint(connectionPoints.getPoint(i + 1));
					PointListUtilities.findIntersections(segment, labelPoints, intersections, distances);
					Point p;
					if (!pointInLabel) {
						p = PointListUtilities.pickClosestPoint(intersections, connectionPoints.getPoint(i));
						if (p != null) g.drawLine(connectionPoints.getPoint(i), p);
						p = PointListUtilities.pickClosestPoint(intersections, connectionPoints.getPoint(i + 1));
						if (p != null) g.drawLine(connectionPoints.getPoint(i + 1), p);
					} else {
						Point fp = null;
						try {
							fp = intersections.getFirstPoint();
						} catch (Exception e) {
						}
						if (fp != null) {
							g.drawLine(connectionPoints.getPoint(i), fp);
							++i;
							segment = new PointList();
							intersections = new PointList();
							segment.addPoint(connectionPoints.getPoint(i));
							segment.addPoint(connectionPoints.getPoint(i + 1));

							PointListUtilities.findIntersections(segment, labelPoints, intersections, distances);

							fp = null;
							try {
								fp = intersections.getFirstPoint();
							} catch (Exception e) {
							}
							if (fp != null) g.drawLine(connectionPoints.getPoint(i + 1), fp);
						}
					}
				} else {
					g.drawLine(connectionPoints.getPoint(i), connectionPoints.getPoint(i + 1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class RotableLabel extends Label implements RotatableDecoration {

		public RotableLabel(String label) {
			super(label);
			setFont(TEXT_FONT);
			//if(!System.getProperty("os.name").startsWith("Windows")){
			if (true) {
				setBackgroundColor(ColorConstants.white);
				setForegroundColor(ColorConstants.black);
				setOpaque(true);
			}
		}

		@Override
		public void setReferencePoint(Point p){
		}

		@Override
		public void paint(Graphics g){
			//g.rotate(45);
			super.paint(g);
			//g.rotate(0);
		}

	}

}
