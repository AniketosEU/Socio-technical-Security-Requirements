/*
* RoleCompartmentFigure.java
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

import java.util.Iterator;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;

public class RoleCompartmentFigure extends Ellipse {

	/**
	 * Modified method to include children area and node area.
	 * 
	 * @see org.eclipse.draw2d.Ellipse#containsPoint(org.eclipse.draw2d.geometry.Point)
	 * 
	 */
	@Override
	public boolean containsPoint(int x,int y){

		if (super.containsPoint(x, y))
			return true;
		else
			return childernContainPoint(x, y);
	}

	/**
	 * 
	 * Check if there is a Goal/TResource children and check if the point is inside them
	 * 
	 * @param x
	 *           the x coordinate of the point
	 * @param y
	 *           the y coordinate of the point
	 * @return true - if the current point(x,y) is inside the area of one of the children Goal/TResource
	 * 
	 */
	private boolean childernContainPoint(int x,int y){

		return childernContainPointRecursive(this, x, y);
	}


	/**
	 * 
	 * Recursive Method to check if there is a Goal/TResource children and check if the point is inside them
	 * 
	 * @param parent
	 *           the figure recursively to check
	 * @param x
	 *           the x coordinate of the point
	 * @param y
	 *           the y coordinate of the point
	 * @return true - if the current point(x,y) is inside the area of one of the children Goal/TResource
	 * 
	 */
	private boolean childernContainPointRecursive(IFigure parent,int x,int y){

		Iterator i = parent.getChildren().iterator();
		boolean contain = false;

		while (!contain && i.hasNext()) {
			IFigure f = (IFigure) i.next();
			if (f instanceof GoalNodeFigure || f instanceof TResourceNodeFigure) {
				Point p = new Point(x, y);
				f.translateToRelative(p);
				if (f.containsPoint(p)) return true;
			} else {
				contain = childernContainPointRecursive(f, x, y);
			}
		}
		return contain;
	}


	/**
	 * @see org.eclipse.draw2d.Ellipse#getToolTip()
	 */
	@Override
	public IFigure getToolTip(){

		return new Label("");
	}


}
