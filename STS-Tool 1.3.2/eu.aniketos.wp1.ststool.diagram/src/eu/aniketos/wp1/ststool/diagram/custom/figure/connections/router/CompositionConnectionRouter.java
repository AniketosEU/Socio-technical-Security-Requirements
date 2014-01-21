/*
* CompositionConnectionRouter.java
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
package eu.aniketos.wp1.ststool.diagram.custom.figure.connections.router;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ObliqueRouter;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.CompositionRelationFigure;

@SuppressWarnings("restriction")
public class CompositionConnectionRouter extends ObliqueRouter {

	@Override
	public void routeLine(Connection conn,int nestedRoutingDepth,PointList newLine){
		super.routeLine(conn, nestedRoutingDepth, newLine);
		arrangeComposition(conn, false);
	}

	@Override
	public void routeBendpoints(Connection conn){

		PointList points = conn.getPoints();

		Point p1 = getStartPoint(conn);
		Point p2 = getEndPoint(conn);
		if (p1 == null || p2 == null) return;

		points.removeAllPoints();
		conn.translateToRelative(p1);
		points.addPoint(p1);

		conn.translateToRelative(p2);
		points.addPoint(p2);
		conn.setPoints(points);
		arrangeComposition(conn, false);
	}

	@Override
	protected Point getStartPoint(Connection conn){

		Point startPoint = null;
		if (conn instanceof CompositionRelationFigure) {
			if (conn.getTargetAnchor() == null) return new Point();

			//Point ref = conn.getTargetAnchor().getReferencePoint();
			startPoint = conn.getSourceAnchor().getLocation(conn.getTargetAnchor().getReferencePoint());
		} else
			startPoint = super.getStartPoint(conn);
		return startPoint;

	}

	@Override
	protected Point getEndPoint(Connection connection){

		Point endPoint = null;
		if (connection instanceof CompositionRelationFigure) {
			if (connection.getSourceAnchor() == null) return new Point();

			//Point ref = connection.getSourceAnchor().getReferencePoint();
			endPoint = connection.getTargetAnchor().getLocation(connection.getSourceAnchor().getReferencePoint());
		} else
			endPoint = super.getEndPoint(connection);
		return endPoint;
	}

	protected void arrangeComposition(Connection connection,boolean isRemove){

		Point endPoint = getEndPoint(connection);
		if (connection.getSourceAnchor() == null) return;
		IFigure refFigure = connection.getSourceAnchor().getOwner();

		ConnectionLayer layer = (ConnectionLayer) connection.getParent();
		List list = layer.getChildren();
		Point referencePoint = getStartPoint(connection);
		Point referenceLeftPoint = referencePoint.getCopy();
		referenceLeftPoint.x -= 5;
		PointList line = new PointList();
		line.addPoint(referencePoint);
		line.addPoint(endPoint);

		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Connection element = (Connection) list.get(i);
			if (isRemove && element == connection) continue;
			if (!(element instanceof CompositionRelationFigure)) continue;
			if (element.getSourceAnchor() != null && element.getSourceAnchor().getOwner() == refFigure) {
				PointList lineRef = element.getPoints();
				if (lineRef.size() >= 2) {
					Point myPoint = PointListUtilities.calculatePointRelativeToLine(lineRef, 0, 5, false);

					Point leftPoint = lineRef.getFirstPoint().getCopy();
					leftPoint.x -= 10;
					double angle = getAngle(leftPoint, lineRef.getFirstPoint(), myPoint);
					map.put(angle, i);
				}
				((CompositionRelationFigure) element).setLastPoint(null);
			}
		}
		if (map.size() >= 2) {
			TreeMap treeMap = new TreeMap(map);
			int minIndex = (Integer) treeMap.get(treeMap.firstKey());
			int maxIndex = (Integer) treeMap.get(treeMap.lastKey());

			Connection maxIndexConnection = (Connection) list.get(maxIndex);
			Connection minIndexConnection = (Connection) list.get(minIndex);
			PointList lineRef = maxIndexConnection.getPoints();

			Point lastPoint = lineRef.getLastPoint();//PointListUtilities.calculatePointRelativeToLine(lineRef, 0, 20, false);
			((CompositionRelationFigure) minIndexConnection).setLastPoint(lastPoint);
		}
	}

	@Override
	public void remove(Connection connection){

		super.remove(connection);
		arrangeComposition(connection, true);
	}

	private double getAngle(Point a,Point b,Point c){

		Point d = a.getTranslated(b.getCopy().getNegated());
		Point e = c.getTranslated(b.getCopy().getNegated());

		double angle1 = Math.toDegrees(Math.atan2(d.y, d.x));
		double angle2 = Math.toDegrees(Math.atan2(e.y, e.x));
		return (angle1 - angle2);
	}
}
