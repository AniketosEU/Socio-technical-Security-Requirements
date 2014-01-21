/*
* AuthorisationFigure.java
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



import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.ArrowDecoration2;
import eu.aniketos.wp1.ststool.diagram.custom.figure.subfigures.AuthorisationMiddleFigure;
import eu.aniketos.wp1.ststool.diagram.custom.locators.RotatableObjectLocator;

public class AuthorisationFigure extends StsConnection {

	public final static int		PROP_GOALS_LIST			= 0;
	public final static int		PROP_RESOURCES_LIST		= 1;
	public final static int		PROP_MODIFICATION			= 2;
	public final static int		PROP_PRODUCE				= 3;
	public final static int		PROP_USAGE					= 4;
	public final static int		PROP_DISTRIBUTION			= 5;
	public final static int		PROP_TIMES_TRANSFERABLE	= 6;
	public static final int		SELECTION					= 7;
	public static final int		OPEN_RESOURCE				= 8;
	public static final int		OPEN_GOAL					= 9;

	AuthorisationMiddleFigure	middleDecoration;

	public AuthorisationFigure(IStsFigureChangeListener cl) {

		super();

		setForegroundColor(ColorConstants.black);
		setBackgroundColor(ColorConstants.black);

		ArrowDecoration2 sourceDecoration = new ArrowDecoration2(ArrowDecoration2.DIRECTION_RIGHT);//  ArrowDecoration(ArrowDecoration.RIGHT_ARROW, ARROW_LABEL);
		RotatableObjectLocator locator2 = new RotatableObjectLocator(this, RotatableObjectLocator.SOURCE);
		add(sourceDecoration, locator2);

		ArrowDecoration2 targetDecoration = new ArrowDecoration2(ArrowDecoration2.DIRECTION_LEFT);
		RotatableObjectLocator locator3 = new RotatableObjectLocator(this, RotatableObjectLocator.TARGET);
		add(targetDecoration, locator3);

		middleDecoration = new AuthorisationMiddleFigure(cl);
		RotatableObjectLocator locator = new RotatableObjectLocator(this, RotatableObjectLocator.MIDDLE);
		add(middleDecoration, locator);

	}

	public void updateProperty(int property,Object value){

		if (property == PROP_TIMES_TRANSFERABLE) {
			if ((Integer) value == 0) {
				setLineStyle(SWT.LINE_DASH);
			} else {
				setLineStyle(SWT.LINE_SOLID);
			}
		} else {
			middleDecoration.updateProperty(property, value);
		}
	}

	public AuthorisationMiddleFigure getMiddleFigure(){
		return middleDecoration;
	}

	@Override
	public void setError(STSErrorType errorType){
		super.setError(errorType);
		middleDecoration.setError(errorType);
	}


}
