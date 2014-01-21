/*
* CommitmentManager.java
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
package eu.aniketos.wp1.ststool.commitments.manager;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.BindingOfDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.SeparationOfDuties;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.commitments.evaluator.CommitmentEvaluator;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;

/**
 * Manage and evaluate the commitment for the active diagram if exist
 * 
 * @author Mauro Poggianella
 */
public class CommitmentManager {

	/** Unique instance for this class */
	private static CommitmentManager		INSTANCE		= null;

	/** Listener that need to be notified if the commitments change, or need refresh */
	private List<ICommitmentListener>	listeners	= new ArrayList<ICommitmentListener>();

	/** Local copy of the last commitment committed to the listener */
	private List<ICommitment>				commitments;

	/** the diagram on which the commitment are calculated */
	//private StsToolDiagram					diagram;

	/** A map used to store the commitments */
	//private CommitmentMap					commitmentMap	= new CommitmentMap();

	/**
	 * Constructor Add to the workbench the ActiveEditorPartListener;
	 */
	protected CommitmentManager() {
		commitments = new ArrayList<ICommitment>();
	}

	/**
	 * Return an instance for this class;
	 * 
	 * @return a unique instance for this class
	 */
	public static CommitmentManager getManager(){

		if (INSTANCE == null) {
			INSTANCE = new CommitmentManager();
		}
		return INSTANCE;
	}

	/**
	 * Add a listener that will be notified when commitments change
	 * 
	 * @param listener
	 *           the listener to add
	 */
	public void addCommitmentListener(ICommitmentListener listener){

		listeners.add(listener);
		if (commitments.size() > 0) {
			fireCommitmentChanged(listener, commitments);
		}
	}

	/**
	 * Remove a listener
	 * 
	 * @param listener
	 *           the listener to remove
	 */
	public void removeCommitmentListener(ICommitmentListener listener){

		listeners.remove(listener);
	}

	/**
	 * Notify all listeners that the commitments are changed
	 * 
	 * @param commitments
	 *           the new commitments
	 */
	protected void fireCommitmentChanged(List<ICommitment> commitments){

		for (ICommitmentListener l : listeners) {
			fireCommitmentChanged(l, commitments);
		}
	}

	/**
	 * Notify a listener that the commitments are changed
	 * 
	 * @param commitments
	 *           the new commitments
	 */
	protected void fireCommitmentChanged(ICommitmentListener listener,List<ICommitment> commitments){

		listener.commitmentsChanged(commitments);
	}

	/**
	 * Notify all listeners that the commitments need to be refreshed
	 * 
	 */
	protected void fireRefreshCommitment(){

		if (commitments.size() > 0) {
			for (ICommitmentListener l : listeners) {
				l.refreshCommitments();
			}
		}
	}

	/**
	 * Clear all the previous commitment and evaluate them again
	 */
	protected void updateAllCommitments(){
		commitments.clear();
		StsToolDiagram diagram = getDiagram();
		if (diagram == null) return;
		TreeIterator<EObject> i = diagram.eAllContents();
		while (i.hasNext()) {
			EObject e = i.next();
			commitments.addAll(CommitmentEvaluator.evaluateCommitments(e));
		}
		/*if (diagram.getDiagActors().size() > 1) {
			for (Actor a : diagram.getDiagActors()) {
				for (Delegation d : a.getOutgoingDelegations()) {
					addObjectCommitments(d);
				}
				for (Provision p : a.getOutgoingProvisions()) {
					addObjectCommitments(p);
				}
				for (Authorisation auth : a.getOutgoingAuthorisations()) {
					addObjectCommitments(auth);
				}
				if (a instanceof SeparationOfDuties) {
					for (IncompatibleDuties incd : ((SeparationOfDuties) a).getIncompatibleDutiesOut()) {
						addObjectCommitments(incd);
					}
				}
				for (Goal g : a.getGoals()) {
					for (IncompatibleDuties incd : g.getIncompatibleDutiesOut()) {
						addObjectCommitments(incd);
					}
					for (CompatibleDuties cd : g.getCompatibleDutiesOut()) {
						addObjectCommitments(cd);
					}
				}
			}
		}*/
		fireCommitmentChanged(getAllCommitments());
	}

	/**
	 * 
	 */
	protected void addObjectCommitments(final Object element){
		updateAllCommitments();
		/*CommitmentJobManager.getInstrance().addAndExecuteJob(new CommitmentsJob() {

			@Override
			public void runJob(){
			updateAllCommitments();
				//List<ICommitment> calculatedCommitment = CommitmentEvaluator.evaluateCommitments(element);
				//List<ICommitment> removedCommitment = commitmentMap.addCommitmentforElement(element, calculatedCommitment);
				//fireCommitmentChanged(commitmentMap.getAllCommitments(), calculatedCommitment, removedCommitment);
			}
		});*/
	}

	protected void removeObjectCommitments(final Object element){
		updateAllCommitments();
		/*CommitmentJobManager.getInstrance().addAndExecuteJob(new CommitmentsJob(Display.getDefault()) {

			@Override
			public void runJob(){
				updateAllCommitments();
				//List<ICommitment> removedCommitment = commitmentMap.removeCommitmentforElement(element);
				//fireCommitmentChanged(commitmentMap.getAllCommitments(), new ArrayList<ICommitment>(), removedCommitment);
			}
		});*/
	}

	public List<ICommitment> getAllCommitments(){

		return new ArrayList(commitments);
	}

	/**
	 * Called by the CommitmentPlugin to notify that the active diagram has changed
	 * 
	 * @param diagram
	 *           the new active diagram or null if there isn't a new diagram
	 */
	protected void diagramChanged(StsToolDiagram diagram){
		updateAllCommitments();
	}

	/**
	 * Called by the CommitmentPlugin to notify that the active diagram has been modified
	 * 
	 * @param n
	 *           the EMF {@link Notification} describing the modification
	 */
	protected void diagramModifyed(Notification n){

		int eventType = n.getEventType();
		Object notif = n.getNotifier();

		try {
			if (eventType == Notification.REMOVE) {
				if (n.getFeature() instanceof EReference) {
					EReference ref = (EReference) n.getFeature();
					String refName = ref.getName();
					if (notif instanceof Actor) {
						if (refName.equals("outgoingDelegations"))
							removeObjectCommitments(n.getOldValue());
						else if (refName.equals("outgoingAuthorisations")) removeObjectCommitments(n.getOldValue());
					}
					if (notif instanceof Authorisation) {
						if (refName.equals("resources"))
							addObjectCommitments(notif);
						else if (refName.equals("goals")) addObjectCommitments(notif);
					}
					if (notif instanceof SeparationOfDuties) {
						if (refName.equals("incompatibleDutiesOut")) removeObjectCommitments(n.getOldValue());
					}
					if (notif instanceof BindingOfDuties) {
						if (refName.equals("compatibleDutiesOut")) removeObjectCommitments(n.getOldValue());
					}
				}
			} else if (eventType == Notification.ADD) {
				if (n.getFeature() instanceof EReference) {
					EReference ref = (EReference) n.getFeature();
					String refName = ref.getName();
					if (notif instanceof Actor) {
						if (refName.equals("outgoingDelegations"))
							addObjectCommitments(n.getNewValue());
						else if (refName.equals("outgoingAuthorisations")) addObjectCommitments(n.getNewValue());
					}
					if (notif instanceof Authorisation) {
						if (refName.equals("resources"))
							addObjectCommitments(notif);
						else if (refName.equals("goals")) addObjectCommitments(notif);
					}
					if (notif instanceof SeparationOfDuties) {
						if (refName.equals("incompatibleDutiesOut")) addObjectCommitments(n.getNewValue());
					}
					if (notif instanceof BindingOfDuties) {
						if (refName.equals("compatibleDutiesOut")) addObjectCommitments(n.getNewValue());
					}
				}
			} else if (eventType == Notification.SET) {
				if (n.getFeature() instanceof EAttribute) {
					EAttribute feat = (EAttribute) n.getFeature();
					String featName = feat.getName();
					if (featName.equals("name") && (((notif instanceof Goal) && (((Goal) notif).getDelegatedFrom().size() == 0)) || (notif instanceof IResource) || (notif instanceof Actor))) {
						fireRefreshCommitment();
					} else if (notif instanceof Authorisation || (notif instanceof Delegation && (featName.equals("redundancyType") || featName.equals("repudiationType") || featName.equals("timesTransferable") || featName.equals("availability") || featName.equals("availabilityValue") || featName.equals("trustworthiness") || featName.equals("trustworthinessValue"))) || ((notif instanceof Provision) && (featName.equals("integrity") || featName.equals("availability") || featName.equals("confidentiality") || featName.equals("availabilityValue")))) {
						updateAllCommitments();//addObjectCommitments(notif);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StsToolDiagram getDiagram(){
		try {
			IEditorPart ep = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (ep instanceof CustomStsToolDiagramDocumentEditor) return ((CustomStsToolDiagramDocumentEditor) ep).getStsModel();
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
