/*
* RotatableObjectLocator.java
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
package eu.aniketos.wp1.ststool.diagram.custom.locators;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.DelegationMiddleFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.ProvisionMiddleFigure;

/**
 * Locator used to position the decoration over a connection it support 3 point of insertion - SOURCE,MIDDLE,TARGET
 * 
 */

public class RotatableObjectLocator extends ConnectionLocator {

	/**
	 * Default constructor
	 */
	public RotatableObjectLocator(Connection c, int i) {

		super(c, i);
	}

	/**
	 * Relocate the current figure
	 */
	@Override
	public void relocate(IFigure target){

		PointList points = getConnection().getPoints();
		Dimension d = target.getSize();
		Point p = getLocation(points);
		if (p == null) return;
		Point targetCenter = new Point(p.x - (d.width / 2), p.y - (d.height / 2));
		if (target instanceof DelegationMiddleFigure || target instanceof ProvisionMiddleFigure) {
			targetCenter = new Point(p.x - (d.width / 2), p.y - 25);
		}
		target.setLocation(targetCenter);

		if (target instanceof RotatableDecoration) {
			RotatableDecoration arrow = (RotatableDecoration) target;
			arrow.setLocation(getLocation(points));
			switch (getAlignment()) {
				case SOURCE:
					arrow.setReferencePoint(points.getPoint(1));
				break;
				case TARGET:
					arrow.setReferencePoint(points.getPoint(points.size() - 2));
				break;
				case MIDDLE:
					int index = (points.size() % 2 == 0) ? ((points.size() / 2) - 1) : ((points.size() - 1) / 2);
					arrow.setReferencePoint(points.getPoint(index));
				break;
			}
		}
	}

	/**
	 * Calculate the point relative to the connection
	 */
	@Override
	protected Point getLocation(PointList points){

		PointList pointList = new PointList();
		switch (getAlignment()) {
			case SOURCE:
				pointList.addPoint(points.getPoint(0));
				pointList.addPoint(points.getPoint(1));
				return PointListUtilities.calculatePointRelativeToLine(pointList, 0, 25, true);
				//return points.getPoint(0);		
			case TARGET:
				pointList.addPoint(points.getPoint(points.size() - 1));
				pointList.addPoint(points.getPoint(points.size() - 2));
				return PointListUtilities.calculatePointRelativeToLine(pointList, 0, 20, true);

				//return points.getPoint(points.size() - 1);
			case MIDDLE:

				if (points.size() % 2 == 0) {
					int i = points.size() / 2;
					int j = i - 1;
					Point p1 = points.getPoint(j);
					Point p2 = points.getPoint(i);
					Dimension d = p2.getDifference(p1);
					return Point.SINGLETON.setLocation(p1.x + d.width / 2, p1.y + d.height / 2);
				}
				int i = (points.size() - 1) / 2 + 1;
				int j = i - 1;
				Point p1 = points.getPoint(j);
				Point p2 = points.getPoint(i);

				pointList.addPoint(p1);
				pointList.addPoint(p2);

				return PointListUtilities.calculatePointRelativeToLine(pointList, 0, 20, true);
			default:
				return new Point();
		}
	}
}
