/*
* StsToolElementTypes.java
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

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import eu.aniketos.wp1.ststool.StstoolPackage;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AgentEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.AuthorisationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.CompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DelegationEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.DependencyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.EventEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.Goal2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionANDEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalDecompositionOREditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.GoalEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.IncompatibleDutiesEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ModifyEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NeedEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.NegativeGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.OwnEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PartOfEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PlayEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.PositiveGoalContributionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProduceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ProvisionEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.RoleEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.StsToolDiagramEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResource2EditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TResourceEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.TangibleByEditPart;
import eu.aniketos.wp1.ststool.diagram.edit.parts.ThreatEditPart;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * @generated
 */
public class StsToolElementTypes extends ElementInitializers {

	/**
	 * @generated
	 */
	private StsToolElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map					elements;

	/**
	 * @generated
	 */
	private static ImageRegistry		imageRegistry;

	/**
	 * @generated
	 */
	private static Set					KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType	StsToolDiagram_1000				= getElementType("eu.aniketos.wp1.ststool.diagram.StsToolDiagram_1000");				//$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType	Agent_2001							= getElementType("eu.aniketos.wp1.ststool.diagram.Agent_2001");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Role_2002							= getElementType("eu.aniketos.wp1.ststool.diagram.Role_2002");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Goal_2003							= getElementType("eu.aniketos.wp1.ststool.diagram.Goal_2003");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	TResource_2004						= getElementType("eu.aniketos.wp1.ststool.diagram.TResource_2004");						//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	IResource_2005						= getElementType("eu.aniketos.wp1.ststool.diagram.IResource_2005");						//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Event_2006							= getElementType("eu.aniketos.wp1.ststool.diagram.Event_2006");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Goal_3001							= getElementType("eu.aniketos.wp1.ststool.diagram.Goal_3001");							//$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType	TResource_3002						= getElementType("eu.aniketos.wp1.ststool.diagram.TResource_3002");						//$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType	Need_4001							= getElementType("eu.aniketos.wp1.ststool.diagram.Need_4001");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Produce_4002						= getElementType("eu.aniketos.wp1.ststool.diagram.Produce_4002");						//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Modify_4003							= getElementType("eu.aniketos.wp1.ststool.diagram.Modify_4003");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	PositiveGoalContribution_4004	= getElementType("eu.aniketos.wp1.ststool.diagram.PositiveGoalContribution_4004");	//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	NegativeGoalContribution_4005	= getElementType("eu.aniketos.wp1.ststool.diagram.NegativeGoalContribution_4005");	//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	GoalDecompositionOR_4006		= getElementType("eu.aniketos.wp1.ststool.diagram.GoalDecompositionOR_4006");		//$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType	GoalDecompositionAND_4007		= getElementType("eu.aniketos.wp1.ststool.diagram.GoalDecompositionAND_4007");		//$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType	Own_4008								= getElementType("eu.aniketos.wp1.ststool.diagram.Own_4008");								//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	PartOf_4009							= getElementType("eu.aniketos.wp1.ststool.diagram.PartOf_4009");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	TangibleBy_4010					= getElementType("eu.aniketos.wp1.ststool.diagram.TangibleBy_4010");					//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Play_4011							= getElementType("eu.aniketos.wp1.ststool.diagram.Play_4011");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Provision_4012						= getElementType("eu.aniketos.wp1.ststool.diagram.Provision_4012");						//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Delegation_4013					= getElementType("eu.aniketos.wp1.ststool.diagram.Delegation_4013");					//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Authorisation_4014				= getElementType("eu.aniketos.wp1.ststool.diagram.Authorisation_4014");				//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Threat_4015							= getElementType("eu.aniketos.wp1.ststool.diagram.Threat_4015");							//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	IncompatibleDuties_4016			= getElementType("eu.aniketos.wp1.ststool.diagram.IncompatibleDuties_4016");			//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	CompatibleDuties_4017			= getElementType("eu.aniketos.wp1.ststool.diagram.CompatibleDuties_4017");			//$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType	Dependency_4018					= getElementType("eu.aniketos.wp1.ststool.diagram.Dependency_4018");					//$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry(){
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element){
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element){
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) { return StsToolDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass)); }
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element){
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element){
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint){
		ENamedElement element = getElement(hint);
		if (element == null) { return null; }
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint){
		ENamedElement element = getElement(hint);
		if (element == null) { return null; }
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint){
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap();

			elements.put(StsToolDiagram_1000, StstoolPackage.eINSTANCE.getStsToolDiagram());


			elements.put(Agent_2001, StstoolPackage.eINSTANCE.getAgent());


			elements.put(Role_2002, StstoolPackage.eINSTANCE.getRole());


			elements.put(Goal_2003, StstoolPackage.eINSTANCE.getGoal());


			elements.put(TResource_2004, StstoolPackage.eINSTANCE.getTResource());


			elements.put(IResource_2005, StstoolPackage.eINSTANCE.getIResource());


			elements.put(Event_2006, StstoolPackage.eINSTANCE.getEvent());


			elements.put(Goal_3001, StstoolPackage.eINSTANCE.getGoal());


			elements.put(TResource_3002, StstoolPackage.eINSTANCE.getTResource());


			elements.put(Need_4001, StstoolPackage.eINSTANCE.getNeed());


			elements.put(Produce_4002, StstoolPackage.eINSTANCE.getProduce());


			elements.put(Modify_4003, StstoolPackage.eINSTANCE.getModify());


			elements.put(PositiveGoalContribution_4004, StstoolPackage.eINSTANCE.getPositiveGoalContribution());


			elements.put(NegativeGoalContribution_4005, StstoolPackage.eINSTANCE.getNegativeGoalContribution());


			elements.put(GoalDecompositionOR_4006, StstoolPackage.eINSTANCE.getGoalDecompositionOR());


			elements.put(GoalDecompositionAND_4007, StstoolPackage.eINSTANCE.getGoalDecompositionAND());


			elements.put(Own_4008, StstoolPackage.eINSTANCE.getOwn());


			elements.put(PartOf_4009, StstoolPackage.eINSTANCE.getPartOf());


			elements.put(TangibleBy_4010, StstoolPackage.eINSTANCE.getTangibleBy());


			elements.put(Play_4011, StstoolPackage.eINSTANCE.getPlay());


			elements.put(Provision_4012, StstoolPackage.eINSTANCE.getProvision());


			elements.put(Delegation_4013, StstoolPackage.eINSTANCE.getDelegation());


			elements.put(Authorisation_4014, StstoolPackage.eINSTANCE.getAuthorisation());


			elements.put(Threat_4015, StstoolPackage.eINSTANCE.getThreat());


			elements.put(IncompatibleDuties_4016, StstoolPackage.eINSTANCE.getIncompatibleDuties());


			elements.put(CompatibleDuties_4017, StstoolPackage.eINSTANCE.getCompatibleDuties());


			elements.put(Dependency_4018, StstoolPackage.eINSTANCE.getDependency());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id){
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType){
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet();
			KNOWN_ELEMENT_TYPES.add(StsToolDiagram_1000);
			KNOWN_ELEMENT_TYPES.add(Agent_2001);
			KNOWN_ELEMENT_TYPES.add(Role_2002);
			KNOWN_ELEMENT_TYPES.add(Goal_2003);
			KNOWN_ELEMENT_TYPES.add(TResource_2004);
			KNOWN_ELEMENT_TYPES.add(IResource_2005);
			KNOWN_ELEMENT_TYPES.add(Event_2006);
			KNOWN_ELEMENT_TYPES.add(Goal_3001);
			KNOWN_ELEMENT_TYPES.add(TResource_3002);
			KNOWN_ELEMENT_TYPES.add(Need_4001);
			KNOWN_ELEMENT_TYPES.add(Produce_4002);
			KNOWN_ELEMENT_TYPES.add(Modify_4003);
			KNOWN_ELEMENT_TYPES.add(PositiveGoalContribution_4004);
			KNOWN_ELEMENT_TYPES.add(NegativeGoalContribution_4005);
			KNOWN_ELEMENT_TYPES.add(GoalDecompositionOR_4006);
			KNOWN_ELEMENT_TYPES.add(GoalDecompositionAND_4007);
			KNOWN_ELEMENT_TYPES.add(Own_4008);
			KNOWN_ELEMENT_TYPES.add(PartOf_4009);
			KNOWN_ELEMENT_TYPES.add(TangibleBy_4010);
			KNOWN_ELEMENT_TYPES.add(Play_4011);
			KNOWN_ELEMENT_TYPES.add(Provision_4012);
			KNOWN_ELEMENT_TYPES.add(Delegation_4013);
			KNOWN_ELEMENT_TYPES.add(Authorisation_4014);
			KNOWN_ELEMENT_TYPES.add(Threat_4015);
			KNOWN_ELEMENT_TYPES.add(IncompatibleDuties_4016);
			KNOWN_ELEMENT_TYPES.add(CompatibleDuties_4017);
			KNOWN_ELEMENT_TYPES.add(Dependency_4018);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID){
		switch (visualID) {
			case StsToolDiagramEditPart.VISUAL_ID:
				return StsToolDiagram_1000;
			case AgentEditPart.VISUAL_ID:
				return Agent_2001;
			case RoleEditPart.VISUAL_ID:
				return Role_2002;
			case GoalEditPart.VISUAL_ID:
				return Goal_2003;
			case TResourceEditPart.VISUAL_ID:
				return TResource_2004;
			case IResourceEditPart.VISUAL_ID:
				return IResource_2005;
			case EventEditPart.VISUAL_ID:
				return Event_2006;
			case Goal2EditPart.VISUAL_ID:
				return Goal_3001;
			case TResource2EditPart.VISUAL_ID:
				return TResource_3002;
			case NeedEditPart.VISUAL_ID:
				return Need_4001;
			case ProduceEditPart.VISUAL_ID:
				return Produce_4002;
			case ModifyEditPart.VISUAL_ID:
				return Modify_4003;
			case PositiveGoalContributionEditPart.VISUAL_ID:
				return PositiveGoalContribution_4004;
			case NegativeGoalContributionEditPart.VISUAL_ID:
				return NegativeGoalContribution_4005;
			case GoalDecompositionOREditPart.VISUAL_ID:
				return GoalDecompositionOR_4006;
			case GoalDecompositionANDEditPart.VISUAL_ID:
				return GoalDecompositionAND_4007;
			case OwnEditPart.VISUAL_ID:
				return Own_4008;
			case PartOfEditPart.VISUAL_ID:
				return PartOf_4009;
			case TangibleByEditPart.VISUAL_ID:
				return TangibleBy_4010;
			case PlayEditPart.VISUAL_ID:
				return Play_4011;
			case ProvisionEditPart.VISUAL_ID:
				return Provision_4012;
			case DelegationEditPart.VISUAL_ID:
				return Delegation_4013;
			case AuthorisationEditPart.VISUAL_ID:
				return Authorisation_4014;
			case ThreatEditPart.VISUAL_ID:
				return Threat_4015;
			case IncompatibleDutiesEditPart.VISUAL_ID:
				return IncompatibleDuties_4016;
			case CompatibleDutiesEditPart.VISUAL_ID:
				return CompatibleDuties_4017;
			case DependencyEditPart.VISUAL_ID:
				return Dependency_4018;
		}
		return null;
	}

}
