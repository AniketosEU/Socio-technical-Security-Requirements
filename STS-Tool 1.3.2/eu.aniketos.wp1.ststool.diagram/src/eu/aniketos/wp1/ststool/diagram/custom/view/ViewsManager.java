/*
* ViewsManager.java
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
package eu.aniketos.wp1.ststool.diagram.custom.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;

public class ViewsManager {

	//public static final boolean NEW_VIEWMANAGER=true;


	public final static int							FULL_VIEW				= 1;
	public final static int							EMPTY_VIEW				= 2;
	public final static int							SOCIAL_VIEW				= 3;
	public final static int							RESOURCE_VIEW			= 4;
	public final static int							AUTHORIZATION_VIEW	= 5;

	private View										VIEW_ALL					= new FullView();
	private View										VIEW_EMPTY				= new EmptyView();
	private View										VIEW_SOCIAL				= new SocialView();
	private View										VIEW_RESOURCE			= new ResourceView();
	private View										VIEW_AUTHORIZATION	= new AuthorizationView();


	//private static ViewsManager viewsManager=null;

	protected ArrayList<View>						viewList;
	protected View										currentView;
	protected View										previousView;
	protected ArrayList<ViewChangeListener>	viewChangeListener;
	protected EMap<String, String>				objectConstraintMap;

	/**
	 * Default constructor
	 */
	public ViewsManager() {

		this(null);
	}

	/**
	 * Constructor with a map to save the position constraint
	 * 
	 * @param map
	 *           , the map where the constraint are saved
	 */
	public ViewsManager(EMap<String, String> map) {

		if (map == null)
			objectConstraintMap = new BasicEMap<String, String>(20);
		else
			objectConstraintMap = map;

		viewList = new ArrayList<View>();
		viewList.add(VIEW_ALL);
		viewList.add(VIEW_SOCIAL);
		viewList.add(VIEW_RESOURCE);
		viewList.add(VIEW_AUTHORIZATION);
		currentView = getView(SOCIAL_VIEW);
		viewChangeListener = new ArrayList<ViewChangeListener>();
	}


	/**
	 * @return the previous View
	 */
	public View getPreviousView(){

		return previousView;
	}

	/**
	 * @return the current View
	 */
	public View getCurrentView(){

		return currentView;
	}

	/**
	 * @return the current View as a int
	 */
	public int getCurrentIntView(){

		return getIntView(getCurrentView());
	}


	private Map<Integer, ViewValues>	viewValueMap	= new HashMap<Integer, ViewValues>();

	private class ViewValues {

		double	zoom;
		Point		point;
	}

	/**
	 * @param view
	 *           - the current View as int
	 */
	public void setCurrentView(int view){

		IEditorPart editor = null;
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		CustomStsToolDiagramDocumentEditor ed = null;
		if (editor != null && editor instanceof CustomStsToolDiagramDocumentEditor) {
			ed = (CustomStsToolDiagramDocumentEditor) editor;
		}

		if (ed != null && getCurrentIntView() != EMPTY_VIEW) {
			ViewValues v = new ViewValues();
			v.zoom = ((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().getZoom();
			v.point = ((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().getViewport().getViewLocation();
			viewValueMap.put(getCurrentIntView(), v);
		}

		previousView = currentView;
		currentView = getView(view);
		notifyViewChangeListener();

		if (ed != null && view != EMPTY_VIEW) {

			if (viewValueMap.containsKey(getCurrentIntView())) {
				ViewValues vv = viewValueMap.get(getCurrentIntView());
				((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().setZoom(vv.zoom);
				((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().setViewLocation(vv.point);
				//((FigureCanvas)((DiagramRootEditPart) editor.getDiagramEditPart().getRoot()).getViewer().getControl()).getVerticalBar().setSelection(v.vertical);
				//(FigureCanvas)((DiagramRootEditPart) editor.getDiagramEditPart().getRoot()).getViewer().getControl()).getHorizontalBar().getSelection(v.horizonatal);
			} else {
				((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().setZoom(1);
				((DiagramRootEditPart) ed.getDiagramEditPart().getRoot()).getZoomManager().setViewLocation(new Point());
			}
		}



	}

	/**
	 * @param vcl
	 *           - add the ViewChangeListener to the notification system, that will be notified when the current view is changed
	 */
	public void addViewChangeListener(ViewChangeListener vcl){

		viewChangeListener.add(vcl);
	}

	/**
	 * @param vcl
	 *           - remove the ViewChangeListener to the notification system
	 */
	public void removeViewChangeListener(ViewChangeListener vcl){

		viewChangeListener.remove(vcl);
	}

	/**
	 * Notify the registered ViewChangeListener
	 */
	protected void notifyViewChangeListener(){

		for (ViewChangeListener vlc : viewChangeListener) {
			vlc.changedView(getIntView(currentView));
		}
	}

	/**
	 * Translate a view in int
	 * 
	 * @param view
	 *           the view that need to be translated to int
	 * @return the view as a int
	 */
	protected int getIntView(View view){

		if (view == VIEW_ALL)
			return FULL_VIEW;
		else if (view == VIEW_EMPTY)
			return EMPTY_VIEW;
		else if (view == VIEW_SOCIAL)
			return SOCIAL_VIEW;
		else if (view == VIEW_RESOURCE)
			return RESOURCE_VIEW;
		else if (view == VIEW_AUTHORIZATION) return AUTHORIZATION_VIEW;
		return -1;
	}

	/**
	 * Translate a int to View
	 * 
	 * @param view
	 *           the int view that need to be translated
	 * @return the view
	 */
	public View getView(int view){

		switch (view) {
			case FULL_VIEW:
				return VIEW_ALL;
			case EMPTY_VIEW:
				return VIEW_EMPTY;
			case SOCIAL_VIEW:
				return VIEW_SOCIAL;
			case RESOURCE_VIEW:
				return VIEW_RESOURCE;
			case AUTHORIZATION_VIEW:
				return VIEW_AUTHORIZATION;
		}
		return null;
	}

	/**
	 * Method to check if a element is visible in the view
	 * 
	 * @param element
	 *           , the element that has to be check
	 * @return true if the element is visible in the current view, false otherwise.
	 */
	public boolean isElementVisible(IElementType e){
		return currentView.isElementVisible(e);
	}

	public boolean isElementVisibleInPalette(IElementType e){
		return currentView.isElementVisibleInPalette(e);
	}

	/**
	 * Create a unique key for a determinate object in a view
	 * 
	 * @param view
	 *           the view of the object
	 * @param objectID
	 *           the object ID
	 * 
	 * @return the key
	 */
	private String getKey(int view,String objectID){

		return new String(Integer.toString(view) + "_" + objectID);
	}


	/**
	 * Get all available constraint for a specific object id
	 * 
	 * @param objectID
	 *           the id of the object
	 * @return a map containing all the found constraint
	 */
	public Map<Integer, String> getConstraintObjectsForAllViews(String objectID){

		Map<Integer, String> result = new HashMap<Integer, String>();
		for (View v : viewList) {
			try {
				int view = getIntView(v);
				result.put(view, getObjectConstraint(view, objectID));
			} catch (Exception e) {
			}
		}
		return result;
	}

	/**
	 * Get a constraint for a specific object id in a view
	 * 
	 * @param view
	 *           the view
	 * @param objectID
	 *           the Object ID
	 * @return the constraint
	 */
	public String getObjectConstraint(int view,String objectID){

		return objectConstraintMap.get(getKey(view, objectID));
	}


	/**
	 * Save a constraint for a specific object id in a view
	 * 
	 * @param view
	 *           the view
	 * @param objectID
	 *           the Object ID
	 * @param constraint
	 *           the constraint that need to be saved
	 * @return the constraint
	 */
	public void setObjectConstraint(int view,String objectID,String constraint){

		try {
			objectConstraintMap.put(getKey(view, objectID), constraint);
		} catch (Exception e) {
		}
	}


	/**
	 * Save a constraint for a specific object id for all available view
	 * 
	 * @param objectID
	 *           the Object ID
	 * @param constraint
	 *           the constraint that need to be saved
	 * @return the constraint
	 */
	public void setObjectConstraintForAllViews(String objectID,String constraint){

		for (View v : viewList) {
			setObjectConstraint(getIntView(v), objectID, constraint);
		}
	}

	/**
	 * Remove a constraint for a specific object id in a view
	 * 
	 * @param view
	 *           the view
	 * @param objectID
	 *           the Object ID
	 */
	public void removeObjectConstraint(int view,String objectID){

		try {
			objectConstraintMap.remove(getKey(view, objectID));
		} catch (Exception e) {
		}
	}

	/**
	 * Remove a constraint for a specific object id for all available view
	 * 
	 * @param objectID
	 *           the Object ID
	 */
	public void removeObjectConstraintForAllViews(String objectID){

		for (View v : viewList) {
			removeObjectConstraint(getIntView(v), objectID);
		}
	}

}
