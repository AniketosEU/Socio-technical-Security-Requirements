/*
* AuthorisationEditPart.java
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
package eu.aniketos.wp1.ststool.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.custom.actions.RemoveAuthorizatioResourceAction;
import eu.aniketos.wp1.ststool.diagram.custom.actions.RemoveAuthorizationGoalAction;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.EditPartStucturedSelection;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.StsToolConnectionNodeEditPart;
import eu.aniketos.wp1.ststool.diagram.custom.figure.IStsFigureChangeListener;
import eu.aniketos.wp1.ststool.diagram.custom.figure.connections.AuthorisationFigure;
import eu.aniketos.wp1.ststool.diagram.custom.part.CustomStsToolDiagramDocumentEditor;
import eu.aniketos.wp1.ststool.diagram.custom.utility.SelectObjectsDialog;
import eu.aniketos.wp1.ststool.diagram.edit.policies.AuthorisationItemSemanticEditPolicy;

/**
 * 
 */
public class AuthorisationEditPart extends StsToolConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int	VISUAL_ID	= 4014;

	/**
	 * @generated
	 */
	public AuthorisationEditPart(View view) {
		super(view);
	}

	/**
	 * Adapter used to track Information and goal's name
	 * changes.
	 */
	private NameAdapter nameAdapter=new NameAdapter();
	
	/**
	 * @generated
	 */
	@Override
	protected void createDefaultEditPolicies(){
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new AuthorisationItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated NOT
	 */
	@Override
	protected Connection createConnectionFigure(){

		return new AuthorisationFigure(new IStsFigureChangeListener() {

			@Override
			public void figureChanged(int elementChanged,Object value){

				switch (elementChanged) {

					case AuthorisationFigure.PROP_MODIFICATION:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__MODIFICATION, !authorisation.isModification()));
					break;
					case AuthorisationFigure.PROP_PRODUCE:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__PRODUCE, !authorisation.isProduce()));
					break;
					case AuthorisationFigure.PROP_USAGE:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__USAGE, !authorisation.isUsage()));
					break;
					case AuthorisationFigure.PROP_DISTRIBUTION:
						getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__DISTRIBUTION, !authorisation.isDistribution()));
					break;
					case AuthorisationFigure.PROP_GOALS_LIST:
					break;
					case AuthorisationFigure.PROP_RESOURCES_LIST:
					break;
					case AuthorisationFigure.SELECTION:
						try {
							getRoot().getViewer().setSelection(new EditPartStucturedSelection(AuthorisationEditPart.this));
						} catch (Exception e) {
						}
					break;
					case AuthorisationFigure.OPEN_RESOURCE:
						openResourceSelectionDialog();
					break;
					case AuthorisationFigure.OPEN_GOAL:
						openGoalSelectionDialog();
					break;
				}
			}
		});
	}

	/**
	 * @generated
	 */
	public AuthorisationFigure getPrimaryShape(){
		return (AuthorisationFigure) getFigure();
	}

	public void populateContextMenu(IMenuManager menu){

		StsElement e = getPrimaryShape().getMiddleFigure().getSelectedElement();
		if (e != null) {
			//menu.removeAll();
			if (e instanceof Goal)
				menu.add(new RemoveAuthorizationGoalAction(this, (Goal) e));
			else if (e instanceof IResource) menu.add(new RemoveAuthorizatioResourceAction(this, (IResource) e));
		}
	}

	List<StsElement>	resourcesList	= getResourcesNameList();
	List<StsElement>	goalsList		= getGoalsNameList();

	AdapterImpl			adapter			= new AdapterImpl() {

													@Override
													public void notifyChanged(Notification msg){

														try {
															Object obj = msg.getNotifier();

															if (obj instanceof Authorisation) {
																if ((msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__TIMES_TRANSFERABLE) || (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__MODIFICATION) || (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__PRODUCE) || (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__USAGE) || (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__DISTRIBUTION)) {
																	refresh();
																} else if (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__GOALS) {
																	goalsList = getGoalsNameList();
																	refresh();
																} else if (msg.getFeatureID(Authorisation.class) == StstoolPackage.AUTHORISATION__RESOURCES) {
																	resourcesList = getResourcesNameList();
																	refresh();
																} 
																if(msg.getFeatureID(Authorisation.class)==StstoolPackage.AUTHORISATION__RESOURCES 
																		|| msg.getFeatureID(Authorisation.class)==StstoolPackage.AUTHORISATION__GOALS){
																	if(msg.getEventType()==Notification.REMOVE){
																		((StsElement)msg.getOldValue()).eAdapters().remove(nameAdapter);
																	}else if(msg.getEventType()==Notification.REMOVE_MANY){
																		for(StsElement n:(List<StsElement>)msg.getOldValue()){
																			n.eAdapters().remove(nameAdapter);
																		}
																	}else if(msg.getEventType()==Notification.ADD){
																		((StsElement)msg.getNewValue()).eAdapters().add(nameAdapter);
																	}else if(msg.getEventType()==Notification.ADD_MANY){
																		for(StsElement n:(List<StsElement>)msg.getNewValue()){
																			n.eAdapters().add(nameAdapter);
																		}
																	}
																}
															}
														} catch (Exception e) {
															e.printStackTrace();
														}
													}
												};

	private List<StsElement> getGoalsNameList(){

		if (getNotationView().getElement() instanceof Authorisation) {
			Authorisation a = (Authorisation) getNotationView().getElement();
			Iterator i = a.getGoals().iterator();
			List<StsElement> result = new ArrayList<StsElement>();
			while (i.hasNext()) {
				result.add((Goal) i.next());
			}
			return result;
		} else
			return new ArrayList<StsElement>();
	}

	private List<StsElement> getResourcesNameList(){

		if (getNotationView().getElement() instanceof Authorisation) {
			Authorisation a = (Authorisation) getNotationView().getElement();
			Iterator i = a.getResources().iterator();
			List<StsElement> result = new ArrayList<StsElement>();
			while (i.hasNext()) {
				result.add((IResource) i.next());
			}
			return result;
		} else
			return new ArrayList<StsElement>();
	}

	@Override
	public void refresh(){

		super.refresh();
		if (!(getNotationView().getElement() instanceof Authorisation)) return;
		Authorisation a = (Authorisation) getNotationView().getElement();

		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_TIMES_TRANSFERABLE, new Integer(a.getTimesTransferable()));

		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_MODIFICATION, a.isModification());
		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_PRODUCE, a.isProduce());
		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_USAGE, a.isUsage());
		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_DISTRIBUTION, a.isDistribution());

		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_GOALS_LIST, goalsList);
		getPrimaryShape().updateProperty(AuthorisationFigure.PROP_RESOURCES_LIST, resourcesList);

		try {
			CustomStsToolDiagramDocumentEditor editor = (CustomStsToolDiagramDocumentEditor) ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			getPrimaryShape().setError(editor.getMarkerValue(this));
		} catch (Exception e) {
		}
	}



	Authorisation	authorisation;

	@Override
	public void activate(){

		super.activate();
		authorisation = (Authorisation) getNotationView().getElement();
		authorisation.eAdapters().add(adapter);
		for(Goal g:authorisation.getGoals()){
			g.eAdapters().add(nameAdapter);
		}
		for(IResource i:authorisation.getResources()){
			i.eAdapters().add(nameAdapter);
		}
	}

	@Override
	public void deactivate(){

		authorisation.eAdapters().remove(adapter);
		for(Goal g:authorisation.getGoals()){
			g.eAdapters().remove(nameAdapter);
		}
		for(IResource i:authorisation.getResources()){
			i.eAdapters().remove(nameAdapter);
		}
		authorisation = null;
		super.deactivate();
	}

	private final static Dimension	SELECTION_DIALOG_DIMESION	= new Dimension(650, 400);

	private void openResourceSelectionDialog(){

		Shell parent = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		try {
			Authorisation auth = (Authorisation) getPrimaryView().getElement();
			if (((StsToolDiagram) getPrimaryView().getDiagram().getElement()).getDiagIResources().size() == 0) {
				MessageDialog.openWarning(parent, "Warning", "There aren't any Informations in the diagram");
				return;
			}

			List<IResource> choices = new ArrayList<IResource>(((StsToolDiagram) getPrimaryView().getDiagram().getElement()).getDiagIResources());
			choices.removeAll(auth.getResources());

			SelectObjectsDialog<IResource> sod = new SelectObjectsDialog<IResource>(parent, choices, new ArrayList<IResource>(auth.getResources()), "Information ", "Authorisation for", SELECTION_DIALOG_DIMESION);
			sod.setLabelProvider(new ResourceLabelProvider(auth.getSource()));
			sod.setSorter(new ResourceViewSorter(auth.getSource()));
			int ret = sod.open();

			if (ret == IDialogConstants.OK_ID) {
				List<IResource> slectedResources = sod.getSelectedObjects();
				List<IResource> acutalResources = authorisation.getResources();

				CompoundCommand cc = new CompoundCommand();

				for (IResource r : slectedResources) {
					if (!acutalResources.contains(r)) {
						cc.append(AddCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__RESOURCES, r));
					}
				}
				for (IResource r : acutalResources) {
					if (!slectedResources.contains(r)) {
						cc.append(RemoveCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__RESOURCES, r));
					}
				}
				getEditingDomain().getCommandStack().execute(cc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openGoalSelectionDialog(){

		Shell parent = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		try {
			Authorisation auth = (Authorisation) getPrimaryView().getElement();
			List<Goal> choices = new ArrayList<Goal>(auth.getTarget().getGoals());
			if (choices.size() == 0) {
				String type = "agent";
				if (auth.getTarget() instanceof Role) type = "role";
				MessageDialog.openWarning(parent, "Warning", "The " + type + " \"" + auth.getTarget().getName() + "\" don't have any goals");
				return;
			}
			choices.removeAll(auth.getGoals());
			SelectObjectsDialog<Goal> sod = new SelectObjectsDialog<Goal>(parent, choices, new ArrayList<Goal>(auth.getGoals()), "Goals", "Selected Goals", SELECTION_DIALOG_DIMESION);
			sod.setLabelProvider(new LabelProvider() {

				@Override
				public String getText(Object element){

					if (element instanceof Goal) return ((Goal) element).getName();
					return super.getText(element);
				}
			});
			int ret = sod.open();
			if (ret == IDialogConstants.OK_ID) {
				List<Goal> slectedGoals = (List<Goal>) sod.getSelectedObjects();
				List<Goal> acutalGoals = authorisation.getGoals();

				CompoundCommand cc = new CompoundCommand();

				for (Goal g : slectedGoals) {
					if (!acutalGoals.contains(g)) {
						cc.append(AddCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__GOALS, g));
					}
				}
				for (Goal g : acutalGoals) {
					if (!slectedGoals.contains(g)) {
						cc.append(RemoveCommand.create(getEditingDomain(), authorisation, StstoolPackage.Literals.AUTHORISATION__GOALS, g));
					}
				}
				getEditingDomain().getCommandStack().execute(cc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ResourceLabelProvider extends LabelProvider implements IColorProvider {

		private Actor	a;

		public ResourceLabelProvider(Actor a) {

			this.a = a;
		}

		@Override
		public String getText(Object element){

			if (element instanceof IResource) {
				IResource r = (IResource) element;
				if (!isActorOwner(a, r)) {
					if (r.getOwners().size() == 0) return r.getName() + " (No owner)";
					return r.getName() + " (" + r.getOwners().get(0).getSource().getName() + ")";
				} else {
					return ((IResource) element).getName();
				}
			}
			return super.getText(element);
		}

		@Override
		public Color getBackground(Object element){

			return null;
		}

		@Override
		public Color getForeground(Object element){

			if (element instanceof IResource) {
				if (!isActorOwner(a, (IResource) element)) return ColorConstants.red;
			}
			return null;
		}

	}

	private class ResourceViewSorter extends ViewerSorter {

		private Actor	a;

		public ResourceViewSorter(Actor a) {

			this.a = a;
		}

		@Override
		public int compare(Viewer viewer,Object e1,Object e2){

			IResource r1 = (IResource) e1;
			IResource r2 = (IResource) e2;
			boolean ownerR1 = isActorOwner(a, r1);
			boolean ownerR2 = isActorOwner(a, r2);
			if ((ownerR1 && ownerR2) || (!ownerR1 && !ownerR2))
				return r1.getName().compareToIgnoreCase(r2.getName());
			else if (ownerR1)
				return -1;
			else
				return 1;
		}

	}

	private boolean isActorOwner(Actor a,IResource r){

		if (r.getOwners().size() == 0) return false;
		return r.getOwners().get(0).getSource().equals(a);
	}
	
	/**
	 * Class used to track resource and goals name change;
	 * 
	 * @author Mauro Poggianella
	 *
	 */
	private class NameAdapter extends AdapterImpl{

		/**
		 * Track chnages of resources and goal's name and update
		 * the figure.
		 */
		@Override
		public void notifyChanged(Notification msg){
			super.notifyChanged(msg);
			if(msg.getFeatureID(Goal.class)==StstoolPackage.GOAL__NAME){
				goalsList = getGoalsNameList();
				refresh();
			}else if(msg.getFeatureID(IResource.class)==StstoolPackage.IRESOURCE__NAME){
				resourcesList = getResourcesNameList();
				refresh();
			}
		}
		
		
	}
}
