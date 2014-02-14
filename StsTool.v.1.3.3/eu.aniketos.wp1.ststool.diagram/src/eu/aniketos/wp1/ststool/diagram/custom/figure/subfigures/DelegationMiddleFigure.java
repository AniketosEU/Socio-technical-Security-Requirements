/*
* DelegationMiddleFigure.java
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


import java.util.Iterator;
import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import eu.aniketos.wp1.ststool.diagram.custom.figure.ISTSErrorMarker;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.STSErrorType;
import eu.aniketos.wp1.ststool.diagram.custom.figure.SecurityNeedGraphicalDescriptor;
import eu.aniketos.wp1.ststool.diagram.custom.figure.StsWrappingLabel;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.DelegationFigure;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.LockFigure;

public class DelegationMiddleFigure extends RectangleFigure implements ISTSErrorMarker {

	private final static Dimension	THIS_DIMENSION			= GoalSubfigure.THIS_DIMENSION;				//new Dimension(90,45);
	private final static Cursor		ARROW_CURSOR			= new Cursor(null, SWT.CURSOR_ARROW);
	private final static Font			SEC_NEED_FONT			= new Font(null, "Arial", 8, SWT.NORMAL);


	private GoalFigureWithLabel		gfwl;

	private IStsFigureChangeListener	cl;

	private RectangleFigure				secNeedsContainer;

	private boolean						showLock					= false;
	private boolean						lockOpen					= false;
	private LockFigure					lockFigure				= null;


	private final static Dimension	SECNEED_DIMENSION		= new Dimension(40, 18);
	private final static int			MAX_SECNEED_PER_LINE	= 4;

	public DelegationMiddleFigure(IStsFigureChangeListener cl) {

		super();

		this.cl = cl;
		setFill(false);
		setOutline(false);
		setCursor(ARROW_CURSOR);

		FlowLayout fl = new FlowLayout(false);
		fl.setHorizontal(false);
		fl.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		fl.setMinorAlignment(FlowLayout.ALIGN_CENTER);
		fl.setStretchMinorAxis(false);
		this.setLayoutManager(fl);

		lockFigure = new LockFigure(lockOpen);
		lockFigure.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClicked(MouseEvent me){
				DelegationMiddleFigure.this.cl.figureChanged(DelegationFigure.PROP_SHOW_SECNEEDS, null);
				me.consume();
			}

			@Override
			public void mousePressed(MouseEvent me){
				me.consume();
			}

			@Override
			public void mouseReleased(MouseEvent me){
				me.consume();
			}
		});

		gfwl = new GoalFigureWithLabel();
		gfwl.setBackgroundColor(new Color(null, 200, 255, 200));
		gfwl.setForegroundColor(ColorConstants.black);
		gfwl.setCornerDimensions(new Dimension(20, 20));
		Dimension d = THIS_DIMENSION;
		gfwl.setSize(d);
		gfwl.setMinimumSize(d);
		gfwl.setMaximumSize(d);
		gfwl.setPreferredSize(d);
		gfwl.setBorder(new MarginBorder(7));
		add(gfwl);


		secNeedsContainer = new RectangleFigure();
		secNeedsContainer.setFill(false);
		secNeedsContainer.setOutline(false);
		FlowLayout flc = new FlowLayout();
		flc.setStretchMinorAxis(false);
		flc.setMajorAlignment(FlowLayout.ALIGN_CENTER);
		secNeedsContainer.setLayoutManager(flc);

		int maxWidth = MAX_SECNEED_PER_LINE * (SECNEED_DIMENSION.width + flc.getMajorSpacing());
		int maxHeight = 3 * (SECNEED_DIMENSION.height + flc.getMinorSpacing());
		secNeedsContainer.setMaximumSize(new Dimension(maxWidth, maxHeight));
		secNeedsContainer.setPreferredSize(new Dimension(maxWidth, maxHeight));


		add(secNeedsContainer);
	}

	private RectangleFigure buildSecurityNeed(String name,String tooltipName,Color borderColor){

		RectangleFigure result = new RectangleFigure();
		result.setSize(29, 15);
		result.setPreferredSize(SECNEED_DIMENSION);
		result.setCursor(ARROW_CURSOR);
		result.setBorder(new LineBorder(2));
		result.setBackgroundColor(ColorConstants.white);
		result.setForegroundColor(borderColor);
		StackLayout layout = new StackLayout();
		result.setLayoutManager(layout);


		Label l = new Label();
		l.setText(name);
		l.setForegroundColor(ColorConstants.black);
		l.setFont(SEC_NEED_FONT);
		result.setToolTip(new Label(tooltipName));
		result.add(l);
		return result;
	}

	private void updateSecurityNeed(List<SecurityNeedGraphicalDescriptor> desc){

		secNeedsContainer.removeAll();
		if (desc.size() == 0) {
			showLock = false;
		} else {
			showLock = true;
			for (int i = 0; i < 10; i++) {
				//secNeedsContainer.add(buildSecurityNeed("Se"+i,"Se"+i,new Color(null,random(),random(),random())));
			}
			for (SecurityNeedGraphicalDescriptor d : desc) {
				secNeedsContainer.add(buildSecurityNeed(d.getName(), d.getTooltipName(), d.getBorderColor()));
			}
		}
		updateLock();
	}

	private void updateLock(){

		if (showLock) {
			gfwl.add(lockFigure);
			lockFigure.setStatus(lockOpen);
			if (lockOpen) {
				this.add(secNeedsContainer);
				return;
			}
		} else {
			if (gfwl.getChildren().contains(lockFigure)) gfwl.remove(lockFigure);
		}
		if (this.getChildren().contains(secNeedsContainer)) this.remove(secNeedsContainer);
	}

	public void updateProperty(int property,Object value){

		boolean needRepaint = false;

		switch (property) {
			case DelegationFigure.PROP_GOAL_NAME:
				gfwl.setText((String) value);
				needRepaint = true;
			break;
			case DelegationFigure.PROP_SEC_NEEDS:
				updateSecurityNeed((List<SecurityNeedGraphicalDescriptor>) value);
				needRepaint = true;
			break;
			case DelegationFigure.PROP_SHOW_SECNEEDS:
				lockOpen = (Boolean) value;
				updateLock();
				needRepaint = true;
			break;
		}
		if (needRepaint) repaint();
	}

	@Override
	public IFigure getToolTip(){
		return null;
	}

	class GoalFigureWithLabel extends RoundedRectangle implements MouseListener, MouseMotionListener, ISTSErrorMarker {

		WrappingLabel	label;

		public GoalFigureWithLabel() {

			super();
			label = new StsWrappingLabel();
			label.setTextWrap(true);
			label.setAlignment(PositionConstants.CENTER);
			setBorder(new MarginBorder(3));
			this.setLayoutManager(new XYLayout() {

				@Override
				public void layout(IFigure parent){

					Iterator children = parent.getChildren().iterator();
					Point offset = getOrigin(parent);
					IFigure f;
					while (children.hasNext()) {
						f = (IFigure) children.next();
						if (f == label) {
							if (showLock) {
								int lockSize = lockFigure.getPreferredSize().width;
								f.setBounds(new Rectangle(new Point(offset.x + lockSize, offset.y), new Dimension(parent.getClientArea().getSize().shrink(lockSize, 0))));
							} else {
								f.setBounds(new Rectangle(offset, new Dimension(parent.getClientArea().getSize())));
							}
						} else if (f == lockFigure) {
							try {
								int lockHeight = lockFigure.getPreferredSize().height;
								int offsetY = ((parent.getClientArea().getSize().height) - lockHeight) / 2;
								f.setBounds(new Rectangle(new Point(offset.x, offset.y + offsetY), new Dimension(f.getPreferredSize())));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}

			});
			this.add(label);
			addMouseListener(this);
			addMouseMotionListener(this);
			//setBorder(new MarginBorder(1));
		}

		public void setText(String text){

			label.setText(text);
		}

		@Override
		public IFigure getToolTip(){

			return new Label(" " + label.getText().trim() + " ");
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me){

		}


		@Override
		public void mousePressed(MouseEvent me){
			if (cl != null) {
				cl.figureChanged(DelegationFigure.SELECTION, null);
			}
			//setCursor(HAND_CURSOR);
			me.consume();
		}

		@Override
		public void mouseReleased(MouseEvent me){

			setCursor(ARROW_CURSOR);
			me.consume();
		}

		@Override
		public void mouseMoved(MouseEvent me){


		}

		@Override
		public void mouseDragged(MouseEvent me){
			me.consume();
		}

		@Override
		public void mouseEntered(MouseEvent me){

			setCursor(ARROW_CURSOR);
		}

		@Override
		public void mouseExited(MouseEvent me){

		}

		@Override
		public void mouseHover(MouseEvent me){

		}

		@Override
		public void setError(STSErrorType errorType){
			switch (errorType) {
				case NO_ERROR:
					setForegroundColor(ColorConstants.black);
					setLineWidth(1);
					label.setForegroundColor(ColorConstants.black);
				break;
				case WARNING:
					setForegroundColor(ColorConstants.orange);
					setLineWidth(2);
					label.setForegroundColor(ColorConstants.black);
				break;
				case ERROR:
					setForegroundColor(ColorConstants.red);
					setLineWidth(2);
					label.setForegroundColor(ColorConstants.black);
				break;
			}
		}
	}

	@Override
	public void setError(STSErrorType errorType){
		gfwl.setError(errorType);
	}



}
