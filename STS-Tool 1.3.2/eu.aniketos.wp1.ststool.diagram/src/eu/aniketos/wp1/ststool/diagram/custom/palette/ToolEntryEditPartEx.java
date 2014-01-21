/*
* ToolEntryEditPartEx.java
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
package eu.aniketos.wp1.ststool.diagram.custom.palette;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;


@SuppressWarnings("restriction")
public class ToolEntryEditPartEx extends ToolEntryEditPart {

	public ToolEntryEditPartEx(PaletteEntry paletteEntry) {

		super(paletteEntry);
	}


	@Override
	public IFigure createFigure(){

		return super.createFigure();
	}


	@Override
	protected IFigure createToolTip(){

		IFigure result = null;
		//result = new EipPaletteTooltipFigure();
		result = super.createToolTip();
		return result;
	}

	@SuppressWarnings("unused")
	private class EipPaletteTooltipFigure extends Figure {

		public EipPaletteTooltipFigure() {

			setLayoutManager(new ToolbarLayout());
			setBackgroundColor(ColorConstants.white);
			setOpaque(true);
			setBorder(new CompoundBorder(new LineBorder(ColorConstants.black, 2), new MarginBorder(12)));

			FontData fontData = new FontData("Arial", 12, SWT.BOLD);
			Font font = new Font(Display.getDefault(), fontData);

			Label l = new Label("pippo");
			l.setFont(font);
			l.setLabelAlignment(PositionConstants.LEFT);
			l.setBorder(new MarginBorder(0, 4, 12, 4));
			add(l);

			l = new Label("Explanation");
			l.setBorder(new MarginBorder(4, 4, 6, 4));
			add(l);
		}
	}
}
