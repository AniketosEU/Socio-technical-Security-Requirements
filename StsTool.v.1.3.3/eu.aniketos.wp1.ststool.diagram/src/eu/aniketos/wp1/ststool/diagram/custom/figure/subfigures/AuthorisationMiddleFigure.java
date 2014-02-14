/*
* AuthorisationMiddleFigure.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.AuthorisationFigure;


public class AuthorisationMiddleFigure extends RectangleFigure implements MouseListener, MouseMotionListener, ISTSErrorMarker {

	//private final static Dimension	THIS_DIMENSION	= new Dimension(200, 100);



	private final static int			PROPERTY_SPACING					= 10;
	private final static int			VERTICAL_SPACING					= 2;
	private final static int			PROPERTY_SIZE						= 18;

	private final static int			MAXIMUM_WIDTH						= 300;
	private final static int			MINIMUM_BOX_HEIGHT				= 20;
	private final static int			MAXIMUM_BOX_HEIGHT				= 80;

	private final static Dimension	PROPERTY_AREA_SIZE				= new Dimension((PROPERTY_SIZE + PROPERTY_SPACING) * 3 + PROPERTY_SIZE, PROPERTY_SIZE);
	private final static Dimension	MIN_BOX_AREA_SIZE					= new Dimension(PROPERTY_AREA_SIZE.width, MINIMUM_BOX_HEIGHT);
	private final static Dimension	MAX_BOX_AREA_SIZE					= new Dimension(MAXIMUM_WIDTH, MAXIMUM_BOX_HEIGHT);


	final private static Color			RESOURCE_GRADIENT_START_COLOR	= new Color(Display.getDefault(), 241, 241, 241);														// ColorConstants.darkGray;																					//new Color(null,206,215,255);
	final private static Color			RESOURCE_GRADIENT_END_COLOR	= ColorConstants.lightGray;																					//new Color(null,206,255,215);

	final private static Color			GOAL_GRADIENT_START_COLOR		= new Color(Display.getDefault(), 204, 255, 204);														//ColorConstants.darkGreen;																					//new Color(null,206,215,255);
	final private static Color			GOAL_GRADIENT_END_COLOR			= ColorConstants.lightGreen;																					//new Color(null,206,255,215);



	private final static Color			SET_COLOR							= ColorConstants.orange;
	private final static Color			UNSET_COLOR							= ColorConstants.white;
	private final static Cursor		HAND_CURSOR							= new Cursor(null, SWT.CURSOR_ARROW);																		//new Cursor(null,SWT.CURSOR_HAND);
	private final static Cursor		ARROW_CURSOR						= new Cursor(null, SWT.CURSOR_ARROW);

	private final static Font			TEXT_FONT_FIXED					= new Font(null, "Arial", 10, SWT.NORMAL);

	private LineBorder					b										= new LineBorder(1);

	ScrollPane								spResArea							= new AuthorizationScrollPane();
	ScrollPane								spGoalArea							= new AuthorizationScrollPane();

	RectangleFigure						goalArea;
	RectangleFigure						iResourceArea;

	RectangleFigure						mArea;
	RectangleFigure						pArea;
	RectangleFigure						uArea;
	RectangleFigure						dArea;

	IStsFigureChangeListener			cl;

	public AuthorisationMiddleFigure(IStsFigureChangeListener cl) {

		this.cl = cl;

		addMouseListener(this);
		addMouseMotionListener(this);
		super.setBackgroundColor(ColorConstants.white);
		super.setForegroundColor(ColorConstants.black);
		//setMaximumSize(new Dimension(MAXIMUM_WIDTH,90));
		setOutline(false);
		setFill(false);

		FlowLayout layout = new FlowLayout(false);
		layout.setStretchMinorAxis(true);
		layout.setMajorAlignment(FlowLayout.ALIGN_TOPLEFT);
		layout.setMinorSpacing(VERTICAL_SPACING);
		setLayoutManager(layout);
		add(createPropertyArea());

		spResArea.addMouseListener(this);
		spGoalArea.addMouseListener(this);
		add(spResArea);
		add(spGoalArea);

	}


	private RectangleFigure createPropertyArea(){

		RectangleFigure result = new RectangleFigure();
		result.setMinimumSize(PROPERTY_AREA_SIZE);
		//result.setMaximumSize(PROPERTY_AREA_SIZE);
		//result.setPreferredSize(PROPERTY_AREA_SIZE);

		FlowLayout layout = new FlowLayout(true);
		layout.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		layout.setMinorSpacing(PROPERTY_SPACING);
		result.setLayoutManager(layout);
		result.setOutline(false);
		result.setFill(false);

		uArea = createPropertyIcon("U","Use");
		mArea = createPropertyIcon("M","Modify");
		pArea = createPropertyIcon("P","Produce");
		dArea = createPropertyIcon("D","Distribute");

		result.add(uArea);
		result.add(mArea);
		result.add(pArea);
		result.add(dArea);

		return result;
	}

	private RectangleFigure createPropertyIcon(String text,String tooltip){

		RectangleFigure result = new RectangleFigure();
		result.setToolTip(new Label(" "+tooltip+" "));
		result.setBorder(b);
		result.setPreferredSize(PROPERTY_SIZE, PROPERTY_SIZE);
		result.setLayoutManager(new StackLayout());
		Label l = new Label(text.substring(0, 1).toUpperCase());
		l.setFont(TEXT_FONT_FIXED);
		result.add(l);
		result.addMouseListener(this);
		result.addMouseMotionListener(this);
		return result;
	}



	private RectangleFigure createElementsBox(List<StsElement> elements,Map<IFigure, StsElement> map,String tooltip,boolean showLabelTooltip,final Color startColor,final Color endColor,int alpha,IFigure container){

		map.clear();
		RectangleFigure result = new RectangleFigure() {

			@Override
			public Dimension getPreferredSize(int wHint,int hHint){

				Dimension d = super.getPreferredSize(wHint, hHint);
				if (d.width > MAX_BOX_AREA_SIZE.width) {
					d = super.getPreferredSize(MAX_BOX_AREA_SIZE.width, hHint);
				}
				return d;
			}

		};
		result.setMaximumSize(MAX_BOX_AREA_SIZE);
		result.setMinimumSize(MIN_BOX_AREA_SIZE);

		result.setOutline(false);
		result.setBorder(new MarginBorder(3));
		result.setLayoutManager(new FlowLayout(true));
		result.setToolTip(new Label(" " + tooltip + " "));

		if (elements.size() == 0) {
			if (showLabelTooltip) {
				result.setLayoutManager(new StackLayout());
				Label l = new Label(tooltip);
				l.setFont(TEXT_FONT_FIXED);
				l.setForegroundColor(ColorConstants.lightGray);
				result.add(l);
			}
		} else {

			for (StsElement e : elements) {
				IFigure label = createLabel(e, map, startColor, endColor, alpha);
				ElementSelectionListener esl = resourceElementSelectionListener;
				if (container == spGoalArea) esl = goalElementSelectionListener;
				label.addMouseListener(esl);
				label.addMouseMotionListener(esl);
				result.add(label);
			}
		}
		return result;
	}

	private IFigure createLabel(StsElement element,Map<IFigure, StsElement> map,final Color startColor,final Color endColor,int alpha){

		Label l = new Label(element.getName());

		RoundedRectangle e = new RoundedRectangle() {

			@Override
			protected void fillShape(Graphics graphics){
				graphics.setBackgroundColor(startColor);
				super.fillShape(graphics);
			}

		};
		e.setCornerDimensions(new Dimension(4, 4));
		e.setLayoutManager(new StackLayout() {

			@Override
			protected Dimension calculatePreferredSize(IFigure figure,int wHint,int hHint){

				Dimension d = super.calculatePreferredSize(figure, wHint, hHint);
				d.height = d.height + 4;
				d.width = d.width + 6;
				return d;
			}
		});

		e.setOutline(false);
		e.add(l);
		map.put(e, element);
		return e;
	}

	Map<IFigure, StsElement>	resourceMap	= new HashMap<IFigure, StsElement>();
	Map<IFigure, StsElement>	goalMap		= new HashMap<IFigure, StsElement>();

	public void updateProperty(int property,Object value){

		boolean needRepaint = false;

		switch (property) {
			case AuthorisationFigure.PROP_USAGE:
				if ((Boolean) value) {
					uArea.setBackgroundColor(SET_COLOR);
				} else {
					uArea.setBackgroundColor(UNSET_COLOR);
				}
				;
			break;
			case AuthorisationFigure.PROP_PRODUCE:
				if ((Boolean) value) {
					pArea.setBackgroundColor(SET_COLOR);
				} else {
					pArea.setBackgroundColor(UNSET_COLOR);
				}
				;
			break;
			case AuthorisationFigure.PROP_DISTRIBUTION:
				if ((Boolean) value) {
					dArea.setBackgroundColor(SET_COLOR);
				} else {
					dArea.setBackgroundColor(UNSET_COLOR);
				}
				;
			break;
			case AuthorisationFigure.PROP_MODIFICATION:
				if ((Boolean) value) {
					mArea.setBackgroundColor(SET_COLOR);
				} else {
					mArea.setBackgroundColor(UNSET_COLOR);
				}
				;
			break;
			case AuthorisationFigure.PROP_GOALS_LIST:
				//spGoalArea.setContents(createElementsBox((List<StsElement>) value, goalMap,"Double click to add Goals", GOAL_GRADIENT_START_COLOR, GOAL_GRADIENT_END_COLOR, 100));
				spGoalArea.setContents(createElementsBox((List<StsElement>) value, goalMap, "Double click to add Goals", false, GOAL_GRADIENT_START_COLOR, GOAL_GRADIENT_END_COLOR, 100, spGoalArea));
			break;
			case AuthorisationFigure.PROP_RESOURCES_LIST:
				spResArea.setContents(createElementsBox((List<StsElement>) value, resourceMap, "Double click to add Informations", true, RESOURCE_GRADIENT_START_COLOR, RESOURCE_GRADIENT_END_COLOR, 100, spResArea));
			break;
		}
		if (needRepaint) repaint();
	}

	@Override
	public boolean containsPoint(int x,int y){

		return spResArea.containsPoint(x, y) || spGoalArea.containsPoint(x, y) || mArea.containsPoint(x, y) || pArea.containsPoint(x, y) || uArea.containsPoint(x, y) || dArea.containsPoint(x, y);
	}

	@Override
	public void mouseDoubleClicked(MouseEvent me){

		me.consume();
		if (cl == null) return;
		Object source = me.getSource();
		if (source == mArea) {
			cl.figureChanged(AuthorisationFigure.PROP_MODIFICATION, null);
		} else if (source == pArea) {
			cl.figureChanged(AuthorisationFigure.PROP_PRODUCE, null);
		} else if (source == uArea) {
			cl.figureChanged(AuthorisationFigure.PROP_USAGE, null);
		} else if (source == dArea) {
			cl.figureChanged(AuthorisationFigure.PROP_DISTRIBUTION, null);
		} else if (source == spResArea) {
			cl.figureChanged(AuthorisationFigure.OPEN_RESOURCE, null);
		} else if (source == spGoalArea) {
			cl.figureChanged(AuthorisationFigure.OPEN_GOAL, null);
		}
	}

	private IFigure	selectedElement	= null;

	public StsElement getSelectedElement(){

		if (selectedElement == null)
			return null;
		else {
			StsElement element = resourceMap.get(selectedElement);
			if (element == null) element = goalMap.get(selectedElement);
			return element;
		}
	}

	@Override
	public void mousePressed(MouseEvent me){
		me.consume();
		setCursor(HAND_CURSOR);
		if (cl == null) return;
		/*Object source = me.getSource();
		if (source == mArea) {
			cl.figureChanged(AuthorisationFigure.PROP_MODIFICATION, null);
		} else if (source == pArea) {
			cl.figureChanged(AuthorisationFigure.PROP_PRODUCE, null);
		} else if (source == uArea) {
			cl.figureChanged(AuthorisationFigure.PROP_USAGE, null);
		} else if (source == dArea) {
			cl.figureChanged(AuthorisationFigure.PROP_DISTRIBUTION, null);
		} else {
			
		}*/
		cl.figureChanged(AuthorisationFigure.SELECTION, null);
	}

	@Override
	public void mouseReleased(MouseEvent me){

		setCursor(ARROW_CURSOR);
		me.consume();
	}

	@Override
	public void mouseDragged(MouseEvent me){

		me.consume();
	}

	@Override
	public void mouseEntered(MouseEvent me){

		setCursor(ARROW_CURSOR);
		me.consume();
	}

	@Override
	public void mouseExited(MouseEvent me){

		me.consume();
	}

	@Override
	public void mouseHover(MouseEvent me){

		me.consume();
	}

	@Override
	public void mouseMoved(MouseEvent me){

		me.consume();
	}

	@Override
	public void setBackgroundColor(Color bg){

	}

	@Override
	public void setForegroundColor(Color fg){

	}

	private ElementSelectionListener	resourceElementSelectionListener	= new ElementSelectionListener(spResArea);
	private ElementSelectionListener	goalElementSelectionListener		= new ElementSelectionListener(spGoalArea);


	class ElementSelectionListener implements MouseListener, MouseMotionListener {

		IFigure	area;

		public ElementSelectionListener(IFigure area) {
			this.area = area;
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me){
			me.consume();
			if (cl == null) return;
			if (area == spResArea) {
				cl.figureChanged(AuthorisationFigure.OPEN_RESOURCE, null);
			} else if (area == spGoalArea) {
				cl.figureChanged(AuthorisationFigure.OPEN_GOAL, null);
			}
		}

		@Override
		public void mousePressed(MouseEvent me){

			cl.figureChanged(AuthorisationFigure.SELECTION, null);
			//if(me.button!=1)return;
			((IFigure) me.getSource()).setBorder(new LineBorder(ColorConstants.black, 1));
			selectedElement = (IFigure) me.getSource();
			me.consume();
		}

		@Override
		public void mouseReleased(MouseEvent me){

			me.consume();
		}

		@Override
		public void mouseDragged(MouseEvent me){

			me.consume();
		}

		@Override
		public void mouseEntered(MouseEvent me){

			me.consume();
		}

		@Override
		public void mouseExited(MouseEvent me){

			if (selectedElement == me.getSource()) {
				selectedElement.setBorder(null);
				selectedElement = null;
			}

			me.consume();
		}

		@Override
		public void mouseHover(MouseEvent me){

			me.consume();
		}

		@Override
		public void mouseMoved(MouseEvent me){

			me.consume();
		}

	}

	class AuthorizationScrollPane extends ScrollPane {

		public AuthorizationScrollPane() {
			super();
			getVerticalScrollBar().setPreferredSize(10, 0);
			getHorizontalScrollBar().setPreferredSize(0, 10);
			setHorizontalScrollBarVisibility(ScrollPane.NEVER);
			setVerticalScrollBarVisibility(ScrollPane.AUTOMATIC);
			setMaximumSize(new Dimension(MAXIMUM_WIDTH, MINIMUM_BOX_HEIGHT));
			setMinimumSize(MIN_BOX_AREA_SIZE);
			setBorder(b);
		}

		@Override
		public Dimension getPreferredSize(int wHint,int hHint){

			Dimension d = super.getPreferredSize(wHint, hHint);
			if (d.height > MAX_BOX_AREA_SIZE.height) {
				d.height = MAXIMUM_BOX_HEIGHT;
			}
			d.height = d.height + 3;
			if (d.width < PROPERTY_AREA_SIZE.width) {
				d.width = PROPERTY_AREA_SIZE.width;
			}
			if (d.height < MINIMUM_BOX_HEIGHT) d.height = MINIMUM_BOX_HEIGHT;
			return d;

		}

		public void setBorderColor(Color borderColor){
		}
	}

	@Override
	public void setError(STSErrorType error){
		switch (error) {
			case NO_ERROR:
				super.setLineWidth(1);
				b.setColor(ColorConstants.black);
			break;
			case WARNING:
				b.setColor(ColorConstants.orange);
			break;
			case ERROR:
				b.setColor(ColorConstants.red);
			break;
		}
		repaint();
	}
}
