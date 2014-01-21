/*
* ArrowDecoration.java
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
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ArrowDecoration extends PolygonDecoration {

	public final static PointList	RIGHT_ARROW	= new PointList();
	static {
		RIGHT_ARROW.addPoint(-3, 0);
		RIGHT_ARROW.addPoint(-1, 3);
		RIGHT_ARROW.addPoint(3, 3);
		RIGHT_ARROW.addPoint(3, -3);
		RIGHT_ARROW.addPoint(-1, -3);
	}

	public final static PointList	LEFT_ARROW	= new PointList();
	static {
		LEFT_ARROW.addPoint(3, 0);
		LEFT_ARROW.addPoint(1, 3);
		LEFT_ARROW.addPoint(-3, 3);
		LEFT_ARROW.addPoint(-3, -3);
		LEFT_ARROW.addPoint(1, -3);
	}


	/**
	 * Template for a default tip that points to the right when the rotation angle is 0
	 */

	private String						label;
	private Color						labelColor;


	public ArrowDecoration(PointList template, String label) {

		super();

		this.label = label;
		this.setBackgroundColor(ColorConstants.white);
		this.setForegroundColor(ColorConstants.black);
		this.setLabelColor(ColorConstants.black);
		this.setTemplate(template);
	}

	@Override
	protected void outlineShape(Graphics g){

		super.outlineShape(g);
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getBounds());
		r.width--;
		r.height--;
		r.shrink((getLineWidth() - 1) / 2, (getLineWidth() - 1) / 2);
		g.setForegroundColor(labelColor);
		g.drawString(label, r.x + (r.width - g.getFontMetrics().getAverageCharWidth() * label.length()) / 2, r.y + (r.height - g.getFontMetrics().getHeight()) / 2);
	}

	public String getLabel(){

		return label;
	}

	public void setLabel(String label){

		this.label = label;
	}

	public Color getLabelColor(){

		return labelColor;
	}

	public void setLabelColor(Color labelColor){

		this.labelColor = labelColor;
	}

}
