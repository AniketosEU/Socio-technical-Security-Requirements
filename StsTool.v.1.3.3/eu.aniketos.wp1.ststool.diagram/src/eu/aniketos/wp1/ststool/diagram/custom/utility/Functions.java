/*
* Functions.java
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
package eu.aniketos.wp1.ststool.diagram.custom.utility;



public class Functions {
	/*static double distance(Point p, Point q)
	{
	  return(Math.sqrt((p.x - q.x)*(p.x - q.x) + (p.y - q.y)*(p.y - q.y)));
	}
	
	static double distance(double x1, double y1, double x2, double y2)
	{
	  return(Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1)));
	}

	static double length(double x, double y)
	{
	  return(Math.sqrt(x*x + y*y));
	}

	static double length(segment s)
	{
	  return(Math.sqrt((s.q.x - s.p.x)*(s.q.x - s.p.x) + 
			   (s.q.y - s.p.y)*(s.q.y - s.p.y)));
	}

	static double angle(Point a, Point b, Point c)
	{
	  double v1x = a.x - b.x;
	  double v1y = a.y - b.y;
	  double v1Length = Math.sqrt(v1x*v1x + v1y*v1y);

	  double v2x = c.x - b.x;
	  double v2y = c.y - b.y;
	  double v2Length = Math.sqrt(v2x*v2x + v2y*v2y);

	  if ((v2Length < 1e-7) || (v1Length < 1e-7))
	  {
	       return(0.0);
	  }
	  
	  return(Math.acos((v1x*v2x + v1y*v2y) / (v1Length*v2Length)));
	}

	static double sqr(double x)
	{
	  return(x * x);
	}

	static double sgn(double x)
	{
	  return((x < 0) ? -1.0 : 1.0);
	}

	static Point intersect(line L1, line L2)
	{
	  double px = L1.p.x, py = L1.p.y;
	  double qx = L1.q.x, qy = L1.q.y;
	  double rx = L2.p.x, ry = L2.p.y;
	  double sx = L2.q.x, sy = L2.q.y;

	  double vx = qx - px;
	  double vy = qy - py;
	  double wx = sx - rx;
	  double wy = sy - ry;
	  double nx = -wy;
	  double ny = wx;
	  
	  if ( ((Math.abs(wx) < 1e-7) && (Math.abs(wy) < 1e-7)) ||
	       ((Math.abs(vx) < 1e-7) && (Math.abs(vy) < 1e-7)) )
	       return(null);

	  double nDotv = vx*nx + vy*ny;
	  
	  if (nDotv < 1e-7)
	       return(null);
	  
	  double lambda = (nx*(rx - px) + ny*(ry - py))/nDotv;

	  if (L1 instanceof segment)
	       if ((lambda < 0) || (lambda > 1))
		    return(null);
	  else if (L1 instanceof ray)
	       if (lambda < 0)
		    return(null);
	  
	  double x = lambda*vx + px;
	  double y = lambda*vy + py;

	  double kappa;
	  if (Math.abs(wx) > 1e-7)
	       kappa = (x - rx) / wx;
	  else
	       kappa = (y - ry) / wy;

	  if (L2 instanceof segment)
	       if ((kappa < 0) || (kappa > 1))
		    return(null);
	  else if (L2 instanceof ray)
	       if (kappa < 0)
		    return(null);

	  return(new Point(x, y));
	}

	static Point intersect(circle c, line l, int whichPoint)
	{
	  double px = l.p.x - c.center.x;
	  double py = l.p.y - c.center.y;
	  double qx = l.q.x - c.center.x;
	  double qy = l.q.y - c.center.y;

	  if (whichPoint >= 0)
	       whichPoint = 1;
	  else
	       whichPoint = -1;

	  double pxSqr = px*px;
	  double pySqr = py*py;
	  double pxqx = px*qx;
	  double pyqy = py*qy;

	  double gamma = pxSqr + pySqr + qx*qx + qy*qy - 2*pxqx - 2*pyqy;
	  double delta = 2*pxqx + 2*pyqy - 2*pxSqr - 2*pySqr;

	  double lambda;
	  double r = c.radius();

	  if (gamma == 0)
	  {
	       if (delta == 0)
		    return(null);

	       lambda = (r*r - pxSqr - pySqr) / delta;
	  }
	  else
	  {
	       double argument = (r*r - pxSqr - pySqr) / gamma 
		    + delta*delta/(4*gamma*gamma);
		    
	       if (argument < 0)
		    return(null);
	       
	       lambda = whichPoint * Math.sqrt(argument) - delta/(2 * gamma);
	  }

	  return(new Point((1-lambda)*px + lambda*qx + c.center.x,
			   (1-lambda)*py + lambda*qy + c.center.y));
	}

	static Point intersect(circle c1, circle c2, int whichPoint)
	{
	  double a = c1.center.x - c2.center.x;
	  double b = c1.center.y - c2.center.y;
	  double x, y;
	  double r = c1.radius();
	  double s = c2.radius();

	  if (whichPoint >= 0)
	       whichPoint = 1;
	  else
	       whichPoint = -1;

	  if (Math.abs(a) >= 1e-10)
	  {
	       double delta = -b / a;
	       double gamma = (a*a + b*b - r*r + s*s)/(2*a);
	       double denom = delta*delta + 1;
	       double gammaSquared = gamma*gamma;

	       double argument = (s*s - gammaSquared)/denom + 
		    (delta*delta*gammaSquared)/(denom*denom);
	       
	       if (argument < 0)
		    return(null);

	       y = sgn(a) * whichPoint*Math.sqrt(argument) - delta*gamma / denom;
	       x = delta*y + gamma;
	  }
	  else if (Math.abs(b) >= 1e-10)
	  {
	       // delta = -a / b = 0 in this case
	       double gamma = (b*b - r*r + s*s)/(2*b);  // a*a = 0 in this case
	       // denom = 1 in this case
	       
	       double argument = s*s - gamma*gamma;
	       
	       if (argument < 0)
		    return(null);

	       x = -sgn(b)*whichPoint*Math.sqrt(argument);  // the - is for consistency, in the previous
	       y = gamma;                                   // case delta is negative.
	  }
	  else
	       return(null);

	  return(new Point(x + c2.center.x, y + c2.center.y));
	}*/
}
