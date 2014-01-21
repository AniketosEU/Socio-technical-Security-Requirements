/*
* NodeGraphicalConstraint.java
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
package eu.aniketos.wp1.ststool.diagram.custom.view.constraint;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Class used to memorize the graphical constraint of a node in the model
 */
public class NodeGraphicalConstraint {

	public Dimension					size;
	public Point						location;

	public boolean						collapsed;
	private final static String	DELIM	= "!";


	/**
	 * Create a instance using a point and a dimension
	 * 
	 * @param location
	 *           of the node
	 * @param size
	 *           of the node
	 */
	public NodeGraphicalConstraint(Point location, Dimension size) {

		this.size = size;
		this.location = location;
		this.collapsed = false;
	}

	/**
	 * Create a instance using a Rectangle, like bounds
	 * 
	 * @param bounds
	 *           the bounds of the node;
	 */
	public NodeGraphicalConstraint(Rectangle bounds) {

		this.size = bounds.getSize().getCopy();
		this.location = bounds.getLocation().getCopy();
		this.collapsed = false;

	}

	/**
	 * Create a instance using a string that have been created with getConstraintInString()
	 * 
	 * @param constraint
	 *           , the string that contain the constraint
	 */
	public NodeGraphicalConstraint(String constraint) {

		int pi = 0;
		String[] s = constraint.split(DELIM);
		collapsed = Boolean.parseBoolean(s[pi]);
		pi++;

		if (!s[pi].equals("null")) {
			size = new Dimension();
			size.width = Integer.parseInt(s[pi]);
			pi++;
			size.height = Integer.parseInt(s[pi]);
		}
		pi++;

		if (!s[pi].equals("null")) {
			location = new Point();
			location.x = Integer.parseInt(s[pi]);
			pi++;
			location.y = Integer.parseInt(s[pi]);
		}
	}

	/**
	 * Generate a string that can be saved as constraint. Whit this string, is possible to reconstruct a new NodeGraphicalConstraint equal() to the one that has generated
	 * 
	 * @return the constraint String
	 */
	public String getConstraintInString(){

		String result = "" + collapsed;

		if (size == null)
			result = result.concat(DELIM + "null");
		else
			result = result.concat(DELIM + size.width + DELIM + size.height);

		if (location == null)
			result = result.concat(DELIM + "null");
		else
			result = result.concat(DELIM + location.x + DELIM + location.y);

		return result;

	}
}
