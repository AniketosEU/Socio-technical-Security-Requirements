/*
* StsToolModelingAssistantProvider.java
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
package eu.aniketos.wp1.ststool.diagram.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentAgentCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleRoleCompartmentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.part.Messages;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * @generated
 */
public class StsToolModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List getTypesForPopupBar(IAdaptable host){
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof AgentAgentCompartmentEditPart) {
			ArrayList types = new ArrayList(2);
			types.add(StsToolElementTypes.Goal_3001);
			types.add(StsToolElementTypes.TResource_3002);
			return types;
		}
		if (editPart instanceof RoleRoleCompartmentEditPart) {
			ArrayList types = new ArrayList(2);
			types.add(StsToolElementTypes.Goal_3001);
			types.add(StsToolElementTypes.TResource_3002);
			return types;
		}
		if (editPart instanceof StsToolDiagramEditPart) {
			ArrayList types = new ArrayList(6);
			types.add(StsToolElementTypes.Agent_2001);
			types.add(StsToolElementTypes.Role_2002);
			types.add(StsToolElementTypes.Goal_2003);
			types.add(StsToolElementTypes.TResource_2004);
			types.add(StsToolElementTypes.IResource_2005);
			types.add(StsToolElementTypes.Event_2006);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnSource(IAdaptable source){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AgentEditPart) { return ((AgentEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof RoleEditPart) { return ((RoleEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof GoalEditPart) { return ((GoalEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof TResourceEditPart) { return ((TResourceEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof IResourceEditPart) { return ((IResourceEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof EventEditPart) { return ((EventEditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof Goal2EditPart) { return ((Goal2EditPart) sourceEditPart).getMARelTypesOnSource(); }
		if (sourceEditPart instanceof TResource2EditPart) { return ((TResource2EditPart) sourceEditPart).getMARelTypesOnSource(); }
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnTarget(IAdaptable target){
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof AgentEditPart) { return ((AgentEditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof RoleEditPart) { return ((RoleEditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof GoalEditPart) { return ((GoalEditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof TResourceEditPart) { return ((TResourceEditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof IResourceEditPart) { return ((IResourceEditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof Goal2EditPart) { return ((Goal2EditPart) targetEditPart).getMARelTypesOnTarget(); }
		if (targetEditPart instanceof TResource2EditPart) { return ((TResource2EditPart) targetEditPart).getMARelTypesOnTarget(); }
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getRelTypesOnSourceAndTarget(IAdaptable source,IAdaptable target){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AgentEditPart) { return ((AgentEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof RoleEditPart) { return ((RoleEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof GoalEditPart) { return ((GoalEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof TResourceEditPart) { return ((TResourceEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof IResourceEditPart) { return ((IResourceEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof EventEditPart) { return ((EventEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof Goal2EditPart) { return ((Goal2EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		if (sourceEditPart instanceof TResource2EditPart) { return ((TResource2EditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart); }
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getTypesForSource(IAdaptable target,IElementType relationshipType){
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof AgentEditPart) { return ((AgentEditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof RoleEditPart) { return ((RoleEditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof GoalEditPart) { return ((GoalEditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof TResourceEditPart) { return ((TResourceEditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof IResourceEditPart) { return ((IResourceEditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof Goal2EditPart) { return ((Goal2EditPart) targetEditPart).getMATypesForSource(relationshipType); }
		if (targetEditPart instanceof TResource2EditPart) { return ((TResource2EditPart) targetEditPart).getMATypesForSource(relationshipType); }
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public List getTypesForTarget(IAdaptable source,IElementType relationshipType){
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof AgentEditPart) { return ((AgentEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof RoleEditPart) { return ((RoleEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof GoalEditPart) { return ((GoalEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof TResourceEditPart) { return ((TResourceEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof IResourceEditPart) { return ((IResourceEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof EventEditPart) { return ((EventEditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof Goal2EditPart) { return ((Goal2EditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		if (sourceEditPart instanceof TResource2EditPart) { return ((TResource2EditPart) sourceEditPart).getMATypesForTarget(relationshipType); }
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	@Override
	public EObject selectExistingElementForSource(IAdaptable target,IElementType relationshipType){
		return selectExistingElement(target, getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	@Override
	public EObject selectExistingElementForTarget(IAdaptable source,IElementType relationshipType){
		return selectExistingElement(source, getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host,Collection types){
		if (types.isEmpty()) { return null; }
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) { return null; }
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) { return null; }
		return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element,Collection types){
		IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements){
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(StsToolDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
		dialog.setMessage(Messages.StsToolModelingAssistantProviderMessage);
		dialog.setTitle(Messages.StsToolModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
