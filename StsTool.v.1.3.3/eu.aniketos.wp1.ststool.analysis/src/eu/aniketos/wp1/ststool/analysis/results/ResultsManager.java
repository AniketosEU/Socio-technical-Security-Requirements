/*
* ResultsManager.java
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
package eu.aniketos.wp1.ststool.analysis.results;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.eclipse.emf.ecore.EObject;
import eu.aniketos.wp1.ststool.analysis.internal.DiagramObserver;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;


public class ResultsManager {

	private static ResultsManager	INSTANCE;

	public static ResultsManager getInstance(){

		if (INSTANCE == null) INSTANCE = new ResultsManager();
		return INSTANCE;
	}

	private Map<Category, List<IResult>>	resultsMap	= new HashMap<Category, List<IResult>>();


	private ResultsManager() {

		super();
		initResultMap();
	}

	private void initResultMap(){
	}


	public Category getCategoryByName(String name){

		for (Category cat : resultsMap.keySet()) {
			if (cat.getName().equalsIgnoreCase(name)) return cat;
		}
		return null;
	}

	public List<Category> getAllCategories(){

		List<Category> result = new ArrayList<Category>();
		for (Category c : resultsMap.keySet()) {
			result.add(c);
		}
		return result;
	}

	public List<String> getAllCategoriesName(){

		List<String> result = new ArrayList<String>();
		for (Category c : resultsMap.keySet()) {
			result.add(c.getName());
		}
		return result;
	}

	public Category addCategory(String name,String description,int priority){
		Category category = new Category(name, description, priority);
		if (addCategory(category)) return category;
		return null;
	}

	public boolean addCategory(Category category){

		if (category != null && !containCategory(category)) {
			synchronized (resultsMap) {
				resultsMap.put(category, new ArrayList<IResult>());
				fireResultChangedEvent();
			}
			return true;
		}
		return false;
	}

	public boolean removeCategory(String name){

		return removeCategory(getCategoryByName(name));
	}

	public boolean removeCategory(Category category){

		if (category != null && containCategory(category)) {
			synchronized (resultsMap) {
				resultsMap.remove(category);
				fireResultChangedEvent();
			}
			return true;
		}
		return false;
	}

	public boolean containCategory(String name){

		return containCategory(getCategoryByName(name));
	}

	public boolean containCategory(Category category){

		if (category == null) return false;
		return resultsMap.containsKey(category);
	}

	public List<Category> getCategorForResult(IResult result){
		if (result == null) return null;
		List<Category> catList = new ArrayList<Category>();
		for (Entry<Category, List<IResult>> e : resultsMap.entrySet()) {
			List<IResult> resList = e.getValue();
			if (resList.contains(result)) {
				catList.add(e.getKey());
			}
		}
		return catList;
	}

	public boolean addResult(IResult result,String category,boolean markResult){

		List<IResult> results=new ArrayList<IResult>();
		results.add(result);
		return addAllResults(results,category,markResult);
	}
	
	private Map<IResult,Boolean> markedObject=new HashMap<IResult, Boolean>();

	public boolean isMarked(IResult result){
		if(markedObject.containsKey(result)){
			return markedObject.get(result);
		}return false;
	}
	
	public boolean addResult(IResult result,Category category,boolean markResult){
		List<IResult> results=new ArrayList<IResult>();
		results.add(result);
		return addAllResults(results,category,markResult);
	}


	public boolean addAllResults(Collection<IResult> results,String category,boolean markResult){

		return addAllResults(results, getCategoryByName(category),markResult);
	}

	public boolean addAllResults(Collection<IResult> results,Category category,boolean markResult){

		if (results != null && category != null && containCategory(category)) {
			synchronized (resultsMap) {
				List<IResult> catResults = resultsMap.get(category);
				if (catResults.addAll(results)) {
					resultsMap.put(category, catResults);
					for(IResult r:results){
						markedObject.put(r, false);
					}
					if(markResult){
						for (IResult result : results) {
							markObject(result, false);
						}
					}
					fireResultChangedEvent();
				}
				return true;
			}
		}
		return false;
	}

	public void markObject(IResult result,boolean refresh){
		markedObject.put(result, true);
		
		List<EObject> objects = result.getMarkedObjects();
		if (DiagramObserver.getEditor() != null) {
			if (result.getType().equals(ResultType.ERROR)) {
				for (EObject o : objects) {
					DiagramObserver.getEditor().setMarkerValue(o, true);
				}
				if (refresh) {
					DiagramObserver.getEditor().refresh();
				}
			} else if (result.getType().equals(ResultType.WARNING)) {
				for (EObject o : objects) {
					DiagramObserver.getEditor().setMarkerValue(o, false);
				}
				if (refresh) {
					DiagramObserver.getEditor().refresh();
				}
			}
		}
	}

	public void removeMarkObject(IResult result,boolean refresh){
		markedObject.put(result, false);
		List<EObject> objects = result.getMarkedObjects();
		if (DiagramObserver.getEditor() != null) {
			if (result.getType().equals(ResultType.ERROR)) {
				for (EObject o : objects) {
					DiagramObserver.getEditor().removeMarkerValue(o, true);
				}
				if (refresh) {
					DiagramObserver.getEditor().refresh();
				}
			} else if (result.getType().equals(ResultType.WARNING)) {
				for (EObject o : objects) {
					DiagramObserver.getEditor().removeMarkerValue(o, false);
				}
				if (refresh) {
					DiagramObserver.getEditor().refresh();
				}
			}
		}
	}

	/*public void removeAllObjectMarker(){
		DiagramObserver.getEditor().clearAllMarker();
	}*/

	
	public boolean removeResult(IResult result,String category){
		return removeResult(result, getCategoryByName(category));
	}

	public boolean removeResult(IResult result,Category category){
		List<IResult> results=new ArrayList<IResult>();
		results.add(result);
		return removeAllResults(results,category);
	}


	public boolean removeAllResults(Collection<IResult> results,String category){
		return removeAllResults(results, getCategoryByName(category));
	}

	public boolean removeAllResults(Collection<IResult> results,Category category){

		if (results != null && category != null && containCategory(category)) {
			synchronized (resultsMap) {
				List<IResult> catResults = resultsMap.get(category);
				if (catResults.removeAll(results)) {
					resultsMap.put(category, catResults);
					for(IResult r:results){
						markedObject.remove(r);
					}
					fireResultChangedEvent();
				}
				return true;
			}
		}
		return false;
	}

	public void cleanCategoriesAndResults(){
		
		synchronized (resultsMap) {
			if (DiagramObserver.getEditor() != null) 
				DiagramObserver.getEditor().clearAllMarker();
			resultsMap.clear();
			markedObject.clear();
		}
		fireResultChangedEvent();
	}

	public void cleanCategory(String category){
		cleanCategory(getCategoryByName(category));
	}

	public void cleanCategory(Category category){
		if (category != null && containCategory(category)) {
			synchronized (resultsMap) {
				List<IResult> res=getAllResultsInCategory(category);
				removeAllResults(res,category);
				resultsMap.put(category, new ArrayList<IResult>());
			}
		}
		fireResultChangedEvent();
	}

	public List<IResult> getAllResultsInCategory(String category){

		return getAllResultsInCategory(getCategoryByName(category));
	}

	public List<IResult> getAllResultsInCategory(Category category){

		if (category != null && containCategory(category)) {
			List<IResult> result = new ArrayList<IResult>(resultsMap.get(category));
			return result;
		}
		return null;
	}

	public Map<Category, List<IResult>> getAllResults(){

		Map<Category, List<IResult>> result = new HashMap<Category, List<IResult>>();
		for (Category category : resultsMap.keySet()) {
			result.put(category, getAllResultsInCategory(category));
		}
		return result;
	}


	private Set<AnalysisResultListener>	resultListeners	= new HashSet<AnalysisResultListener>();

	public void addAnalysisResultListener(AnalysisResultListener listener){

		resultListeners.add(listener);
	}

	public void removeAnalysisResultListener(AnalysisResultListener listener){

		resultListeners.remove(listener);
	}

	protected void fireResultChangedEvent(){

		for (AnalysisResultListener listener : resultListeners) {
			listener.resultsChanged(this);
		}
	}

	class TestResults extends AbstarctResult {

		public TestResults(String text, String description, ResultType type) {
			super(text, description, type, ViewsManager.EMPTY_VIEW);
		}
	}
}
