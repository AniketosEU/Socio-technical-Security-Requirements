/*
* EventNodeFigure.java
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

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Color;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.StsWrappingLabel;

public class EventNodeFigure extends RectangleFigure implements ISTSErrorMarker {

	public final static Dimension	THIS_DIMENSION		= new Dimension(90, 60);
	private final static int		TRIANGLE_HEIGHT	= 24;
	private final static int		TRIANGLE_WIDTH		= 30;

	private StsWrappingLabel		nameLabel;
	private Triangle					t;

	public EventNodeFigure() {

		super();


		setFill(false);
		setOutline(false);
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setSpacing(0);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
		super.setLayoutManager(layout);

		t = new Triangle();

		t.setSize(TRIANGLE_WIDTH, TRIANGLE_HEIGHT);
		t.setPreferredSize(TRIANGLE_WIDTH, TRIANGLE_HEIGHT);
		add(t);

		super.setMinimumSize(THIS_DIMENSION);
		super.setPreferredSize(THIS_DIMENSION);
		super.setMaximumSize(THIS_DIMENSION);
		super.setSize(THIS_DIMENSION);
	}

	private void initLabel(){

		nameLabel = new StsWrappingLabel("<Event...>");
		nameLabel.setAlignment(PositionConstants.CENTER);
		nameLabel.setTextWrap(true);
		add(nameLabel);
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


	@Override
	public void setPreferredSize(Dimension size){

		// TODO Auto-generated method stub
		//super.setPreferredSize(size);
	}

	@Override
	public void setLayoutManager(LayoutManager manager){

	}

	@Override
	public Dimension getMaximumSize(){

		// TODO Auto-generated method stub
		return THIS_DIMENSION;
	}

	@Override
	public Dimension getMinimumSize(int wHint,int hHint){

		return THIS_DIMENSION;
	}

	@Override
	public Dimension getPreferredSize(int wHint,int hHint){

		return THIS_DIMENSION;
	}

	@Override
	public void setError(STSErrorType errorType){
		switch (errorType) {
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
	}

	public ConnectionAnchor getConnectionAnchor(){

		return new ChopboxAnchor(t) {

			@Override
			public Point getLocation(Point ref){

				Point p = intersection(ref);
				if (p == null) {
					Rectangle b = nameLabel.getBounds().getCopy();
					t.translateToAbsolute(b);
					p = b.getBottom().getCopy();
				}
				return p;
			}

			private Point intersection(Point ref){

				Point center = t.getBounds().getCenter().getCopy();
				t.translateToAbsolute(center);

				if (center.x == ref.x && center.y < ref.y) return null;

				PointList line = new PointList(3);
				Point bottomLeft = t.getBounds().getBottomLeft().getCopy();
				t.translateToAbsolute(bottomLeft);
				line.addPoint(bottomLeft);
				Point topCenter = t.getBounds().getTop().getCopy();
				t.translateToAbsolute(topCenter);
				line.addPoint(topCenter);
				Point bottomRight = t.getBounds().getBottomRight().getCopy();
				t.translateToAbsolute(bottomRight);
				line.addPoint(bottomRight);



				Point result = null;
				for (int i = 0; i < line.size() - 1 && result == null; i++) {
					result = intersection(center, ref, line.getPoint(i), line.getPoint(i + 1));
				}

				return result;
			}


			public Point intersection(Point p1,Point p2,Point p3,Point p4){

				return intersection(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y);
			}

			/**
			 * Computes the intersection between two lines.
			 * 
			 * @param x1
			 *           Point 1 of Line 1
			 * @param y1
			 *           Point 1 of Line 1
			 * @param x2
			 *           Point 2 of Line 1
			 * @param y2
			 *           Point 2 of Line 1
			 * @param x3
			 *           Point 1 of Line 2
			 * @param y3
			 *           Point 1 of Line 2
			 * @param x4
			 *           Point 2 of Line 2
			 * @param y4
			 *           Point 2 of Line 2
			 * @return Point where the segments intersect, or null if they don't
			 */

			public Point intersection(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4){

				double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
				if (d == 0) return null;

				double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
				double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

				Point p = new Point((int) xi, (int) yi);
				if (xi < Math.min(x1, x2) || xi > Math.max(x1, x2)) return null;
				if (xi < Math.min(x3, x4) || xi > Math.max(x3, x4)) return null;
				return p;
			}
		};



	}

	@SuppressWarnings("unused")
	private class Triangle extends Shape {

		private Triangle() {

			setForegroundColor(ColorConstants.white);
			setBackgroundColor(ColorConstants.red);
			setLineWidth(2);
		}

		PointList	pointlist	= new PointList(3);
		Rectangle	lastBounds;


		@Override
		protected void fillShape(Graphics g){

			if (lastBounds == null || !lastBounds.equals(getBounds())) {
				lastBounds = getBounds().getCopy();
				pointlist.removeAllPoints();
				pointlist.addPoint(lastBounds.getBottomLeft().getCopy());
				pointlist.addPoint(lastBounds.getTopLeft().x + (lastBounds.getSize().width / 2), lastBounds.getTopLeft().y);
				pointlist.addPoint(lastBounds.getBottomRight().getCopy());

			}
			g.fillPolygon(pointlist);
		}

		@Override
		protected void outlineShape(Graphics g){

			Rectangle bounds = getBounds().getCopy();
			final int thunderWidth = 3;
			final int thunderHeight = 6;
			int midX = bounds.getTopLeft().x + (bounds.getSize().width / 2);
			int midY = bounds.getTopLeft().y + (bounds.getSize().height / 2) + 2;
			g.drawLine(new Point(midX + (thunderWidth), midY - (thunderHeight)), new Point(midX - (thunderWidth), midY));
			g.drawLine(new Point(midX - (thunderWidth), midY), new Point(midX + (thunderWidth), midY));
			g.drawLine(new Point(midX + (thunderWidth), midY), new Point(midX - (thunderWidth), midY + thunderHeight));
		}


		public Point getTop(){

			Point p = pointlist.getPoint(2);
			translateToAbsolute(p);
			return p;
		}

		public Point getBottomLeft(){

			Point p = pointlist.getPoint(2);
			translateToAbsolute(p);
			return p;
		}

		public Point getBottomRight(){

			Point p = pointlist.getPoint(2);
			translateToAbsolute(p);
			return p;
		}
	}
}
