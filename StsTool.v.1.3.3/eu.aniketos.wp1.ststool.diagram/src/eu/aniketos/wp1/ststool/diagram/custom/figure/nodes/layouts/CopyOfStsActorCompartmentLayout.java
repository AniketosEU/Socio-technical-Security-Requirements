/*
* CopyOfStsActorCompartmentLayout.java
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
package eu.aniketos.wp1.ststool.diagram.custom.figure.nodes.layouts;

import java.util.Iterator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * Layout used to manage the compartment figure
 * 
 */
public class CopyOfStsActorCompartmentLayout extends XYLayout {

	private IFigure	nodeFigure			= null;
	private IFigure	compartmentFigure	= null;
	private boolean	collapsed;


	/**
	 * @param figure
	 *           The node figure
	 */
	public void setNodeFigure(IFigure figure){

		nodeFigure = figure;
	}

	/**
	 * @param the
	 *           compartment Figure
	 */
	public void setCompartmentFigure(IFigure figure){

		compartmentFigure = figure;
	}

	/**
	 * @param collapsed
	 *           true - if the layout should collapse the compartment
	 */
	public void setCollapsed(boolean collapsed){

		this.collapsed = collapsed;
	}

	/**
	 * Manage the layout of the node and compartment other figure will be set to a size of 0,0
	 * 
	 * @see org.eclipse.draw2d.XYLayout#layout(IFigure)
	 */
	@Override
	public void layout(IFigure parent){

		Iterator children = parent.getChildren().iterator();
		IFigure f;
		while (children.hasNext()) {
			f = (IFigure) children.next();
			Rectangle bounds = null;
			if (nodeFigure != null && nodeFigure == f) {//nodeFigure
				Point p = getOrigin(parent).getCopy();
				if (!collapsed) {
					p = findUpperLeftPointForNode(compartmentFigure.getBounds().getCopy(), nodeFigure.getBounds().getCopy());
					Point origin = compartmentFigure.getBounds().getTopLeft().getCopy();
					if (p.x < origin.x) p.x = origin.x;
					if (p.y < origin.y) p.y = origin.y;
				}
				bounds = new Rectangle(p, f.getPreferredSize());
				//bounds = new Rectangle(getOrigin(parent).getCopy(), new Dimension(10,10));
				f.setBounds(bounds);
			} else if (compartmentFigure != null && compartmentFigure == f) {
				//Rectangle parentbounds=parent.getClientArea();
				bounds = new Rectangle(getOrigin(parent), parent.getClientArea().getSize());
				f.setBounds(bounds);
			} else {
				bounds = new Rectangle(0, 0, 0, 0);
			}
			f.setBounds(bounds);
		}
	}

	/**
	 * Calculate the minimum size of the container checking if the container is collapsed
	 * 
	 * @see org.eclipse.draw2d.XYLayout#getMinimumSize(IFigure, int, int)
	 */
	@Override
	public Dimension getMinimumSize(IFigure container,int wHint,int hHint){

		if (nodeFigure != null && compartmentFigure != null) {
			if (collapsed) {
				return nodeFigure.getPreferredSize();
			} else {
				Dimension d = Dimension.max(nodeFigure.getPreferredSize(), compartmentFigure.getPreferredSize());
				d.width += 30;
				d.height += 30;
				return d;
			}
		}
		return new Dimension(0, 0);
	}

	/**
	 * Calculate the preferred size of the container checking if the container is collapsed
	 * 
	 * @see org.eclipse.draw2d.XYLayout#getPreferredSize(IFigure, int, int)
	 */
	@Override
	public Dimension getPreferredSize(IFigure container,int wHint,int hHint){

		return getMinimumSize(container, wHint, hHint);
	}

	private static Point findUpperLeftPointForNode(Rectangle ellipse,Rectangle node){

		Point nodeCenter = findCenterPointForNode(ellipse);
		return new Point(nodeCenter.x - node.width / 2, nodeCenter.y - node.height / 2);
	}


	private static Point findCenterPointForNode(Rectangle ellipse){

		Dimension elipseRadius = new Dimension(ellipse.getSize().width / 2, ellipse.getSize().height / 2);
		PointList pl = ellipseIntersectLine(elipseRadius, ellipse.getCenter(), ellipse.getTopLeft(), ellipse.getCenter());
		return pl.getLastPoint();
	}

	private static PointList ellipseIntersectLine(Dimension ellipseRadius,Point ellipseCenter,Point lineP1,Point lineP2){

		double orizontalRadius = ellipseRadius.preciseWidth();
		double verticalRadius = ellipseRadius.preciseHeight();
		double centerX = ellipseCenter.preciseX();
		double centerY = ellipseCenter.preciseY();
		double x1 = lineP1.x();
		double y1 = lineP1.y();
		double x2 = lineP2.x();
		double y2 = lineP2.y();

		double aa, bb, cc, m = 0;


		//
		if (x1 != x2) {
			m = (y2 - y1) / (x2 - x1);
			double c = y1 - m * x1;
			//
			aa = verticalRadius * verticalRadius + orizontalRadius * orizontalRadius * m * m;
			bb = 2 * orizontalRadius * orizontalRadius * c * m - 2 * orizontalRadius * orizontalRadius * centerY * m - 2 * centerX * verticalRadius * verticalRadius;
			cc = verticalRadius * verticalRadius * centerX * centerX + orizontalRadius * orizontalRadius * c * c - 2 * orizontalRadius * orizontalRadius * centerY * c + orizontalRadius * orizontalRadius * centerY * centerY - orizontalRadius * orizontalRadius * verticalRadius * verticalRadius;
		} else {
			//
			// vertical line case
			//
			aa = orizontalRadius * orizontalRadius;
			bb = -2.0F * centerY * orizontalRadius * orizontalRadius;
			cc = -orizontalRadius * orizontalRadius * verticalRadius * verticalRadius + verticalRadius * verticalRadius * (x1 - centerX) * (x1 - centerX);
		}

		double d = bb * bb - 4 * aa * cc;

		//
		// intersection points : (xi1,yi1) and (xi2,yi2)
		//
		double xi1, xi2, yi1, yi2;
		if (d >= 0.0) {
			if (x1 != x2) {
				xi1 = (-bb + Math.sqrt(d)) / (2 * aa);
				xi2 = (-bb - Math.sqrt(d)) / (2 * aa);
				yi1 = y1 + m * (xi1 - x1);
				yi2 = y1 + m * (xi2 - x1);
			} else {
				yi1 = (-bb + Math.sqrt(d)) / (2 * aa);
				yi2 = (-bb - Math.sqrt(d)) / (2 * aa);
				xi1 = x1;
				xi2 = x1;
			}
		} else {
			return new PointList(); // no intersections
		}
		return new PointList(new int[] { (int) xi1, (int) yi1, (int) xi2, (int) yi2 });
	}

	/*
	 * private Point ellipseIntersectLine(double orizontalRadius, double
	 * verticalRadius, double centerX, double centerY,double x1 , double y1 ,
	 * double x2 , double y2){
	 * 
	 * double aa, bb, cc, m = 0;
	 * double xi1, xi2, yi1, yi2;
	 * 
	 * //
	 * if (x1 != x2) {
	 * m = (y2 - y1) / (x2 - x1);
	 * double c = y1 - m * x1;
	 * //
	 * aa = verticalRadius * verticalRadius + orizontalRadius * orizontalRadius *
	 * m * m;
	 * bb = 2 * orizontalRadius * orizontalRadius * c * m - 2 * orizontalRadius *
	 * orizontalRadius * centerY * m - 2 * centerX * verticalRadius *
	 * verticalRadius;
	 * cc = verticalRadius * verticalRadius * centerX * centerX + orizontalRadius
	 * * orizontalRadius * c * c - 2 * orizontalRadius * orizontalRadius *
	 * centerY * c + orizontalRadius * orizontalRadius * centerY * centerY -
	 * orizontalRadius * orizontalRadius * verticalRadius * verticalRadius;
	 * } else {
	 * //
	 * // vertical line case
	 * //
	 * aa = orizontalRadius * orizontalRadius;
	 * bb = -2.0F * centerY * orizontalRadius * orizontalRadius;
	 * cc = -orizontalRadius * orizontalRadius * verticalRadius * verticalRadius
	 * + verticalRadius * verticalRadius * (x1 - centerX) * (x1 - centerX);
	 * }
	 * 
	 * double d = bb * bb - 4 * aa * cc;
	 * //
	 * // intersection points : (xi1,yi1) and (xi2,yi2)
	 * //
	 * if (d >= 0.0) {
	 * if (x1 != x2) {
	 * xi1 = (-bb + Math.sqrt(d)) / (2 * aa);
	 * xi2 = (-bb - Math.sqrt(d)) / (2 * aa);
	 * yi1 = y1 + m * (xi1 - x1);
	 * yi2 = y1 + m * (xi2 - x1);
	 * } else {
	 * yi1 = (-bb + Math.sqrt(d)) / (2 * aa);
	 * yi2 = (-bb - Math.sqrt(d)) / (2 * aa);
	 * xi1 = x1;
	 * xi2 = x1;
	 * }
	 * } else {
	 * return null; // no intersections
	 * }
	 * return new Point((int)xi2,(int)yi2);
	 * }
	 */
}
