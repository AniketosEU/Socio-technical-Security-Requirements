/*
* CustomStsPaletteFactory.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.gef.Tool;
import org.eclipse.gef.commands.*;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.*;

import eu.aniketos.wp1.ststool.diagram.custom.constraint.STSElementIconDescriptor;
import eu.aniketos.wp1.ststool.diagram.custom.part.SelectionToolExEx;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;
import eu.aniketos.wp1.ststool.diagram.part.Messages;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;


public class CustomStsPaletteFactory {

	/**
	 * List of all tools inserted in the palette
	 */
	static private Map<ToolEntry, IElementType>	paletteItems	= new HashMap<ToolEntry, IElementType>(20);


	/**
	 * Fill the palette with all the tools
	 * 
	 * @param paletteRoot
	 *           the paletteRoot that need to be filled with the tool
	 */
	public static void fillPalette(PaletteRoot paletteRoot){
		//standardGroup/selectionTool
		PaletteToolbar pt = (PaletteToolbar) paletteRoot.getChildren().get(0);
		PanningSelectionToolEntry pe = (PanningSelectionToolEntry) pt.getChildren().get(1);
		pe.setToolClass(SelectionToolExEx.class);

		paletteRoot.add(createNodePalette());
		paletteRoot.add(createRelationsPalette());
	}


	/**
	 * Create a Palette group containing all the tools used to create the Node Element
	 * 
	 * @return a PaletteContainer filled with the tools
	 */
	private static PaletteContainer createNodePalette(){

		PaletteDrawer paletteContainer = new PaletteDrawer("Elements");
		paletteContainer.setUserModificationPermission(PaletteGroup.PERMISSION_NO_MODIFICATION);
		//adding palette entry
		paletteContainer.add(createToolEntry(StsToolElementTypes.Agent_2001,Messages.Agent1CreationTool_title, Messages.Agent1CreationTool_desc, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Role_2002,Messages.Role2CreationTool_title, Messages.Role2CreationTool_desc, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Goal_3001,Messages.Goal3CreationTool_title, Messages.Goal3CreationTool_desc, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.TResource_3002,Messages.Document4CreationTool_title, Messages.Document4CreationTool_desc, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.IResource_2005,Messages.Information5CreationTool_title, Messages.Information5CreationTool_desc, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Event_2006,Messages.Event6CreationTool_title, Messages.Event6CreationTool_desc, true));
		return paletteContainer;
	}

	/*private static PaletteContainer createNodePalette() {

		PaletteDrawer paletteContainer = new PaletteDrawer("Elements");
		paletteContainer.setUserModificationPermission(PaletteGroup.PERMISSION_NO_MODIFICATION);
		//adding palette entry
		paletteContainer.add(createToolEntry(StsToolElementTypes.Agent_2001, "agent", Messages.Agent1CreationTool_title, Messages.Agent1CreationTool_title, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Role_2002, "role", Messages.Role2CreationTool_title, Messages.Role2CreationTool_title, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Goal_3001, "goal", Messages.Goal3CreationTool_title, Messages.Goal3CreationTool_title, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.TResource_3002, "tresource", Messages.TResource4CreationTool_title, Messages.TResource4CreationTool_title, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.IResource_2005, "iresource", Messages.IResource5CreationTool_title, Messages.IResource5CreationTool_title, true));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Event_2006, "event", Messages.Event6CreationTool_title, Messages.Event6CreationTool_desc, true));
		return paletteContainer;
	}*/

	/**
	 * Create a Palette group containing all the tools used to create the Relations Element
	 * 
	 * @return a PaletteContainer filled with the tools
	 */
	private static PaletteContainer createRelationsPalette(){

		PaletteDrawer paletteContainer = new PaletteDrawer("Relations");
		paletteContainer.setUserModificationPermission(PaletteGroup.PERMISSION_NO_MODIFICATION);

		//adding palette entry
		//paletteContainer.add(createToolEntry(StsToolElementTypes.Dependency_4018, Messages.Dependby18CreationTool_title, Messages.Dependby18CreationTool_desc, false));
		//paletteContainer.add(createSeparator());
		paletteContainer.add(createToolEntry(StsToolElementTypes.Delegation_4013, Messages.Delegate13CreationTool_title, Messages.Delegate13CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Provision_4012, Messages.Provide12CreationTool_title, Messages.Provide12CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Play_4011, Messages.Play11CreationTool_title, Messages.Play11CreationTool_desc, false));
		paletteContainer.add(createSeparator());
		paletteContainer.add(createToolEntry(StsToolElementTypes.GoalDecompositionAND_4007, Messages.AND7CreationTool_title, Messages.AND7CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.GoalDecompositionOR_4006, Messages.OR6CreationTool_title, Messages.OR6CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Need_4001, Messages.Need1CreationTool_title, Messages.Need1CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Produce_4002, Messages.Produce2CreationTool_title, Messages.Produce2CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Modify_4003, Messages.Modify3CreationTool_title, Messages.Modify3CreationTool_desc, false));
		paletteContainer.add(createSeparator());
		paletteContainer.add(createToolEntry(StsToolElementTypes.PositiveGoalContribution_4004, Messages.PositiveContribution4CreationTool_title, Messages.PositiveContribution4CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.NegativeGoalContribution_4005, Messages.NegativeContribution5CreationTool_title, Messages.NegativeContribution5CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Own_4008, Messages.Own8CreationTool_title, Messages.Own8CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.PartOf_4009, Messages.PartOf9CreationTool_title, Messages.PartOf9CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.TangibleBy_4010, Messages.TangibleBy10CreationTool_title, Messages.TangibleBy10CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Authorisation_4014, Messages.Authorisation14CreationTool_title, Messages.Authorisation14CreationTool_desc, false));
		paletteContainer.add(createSeparator());
		paletteContainer.add(createToolEntry(StsToolElementTypes.CompatibleDuties_4017, Messages.Compatible17CreationTool_title, Messages.Compatible17CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.IncompatibleDuties_4016, Messages.Incompatible16CreationTool_title, Messages.Incompatible16CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Threat_4015, Messages.Threat15CreationTool_title, Messages.Threat15CreationTool_desc, false));

		return paletteContainer;
	}

	/*private static PaletteContainer createRelationsPalette() {

		PaletteDrawer paletteContainer = new PaletteDrawer("Relations");
		paletteContainer.setUserModificationPermission(PaletteGroup.PERMISSION_NO_MODIFICATION);

		//adding palette entry
		
		paletteContainer.add(createToolEntry(StsToolElementTypes.Delegation_4013, "delegation_trasferable", Messages.DelegateGoalTransferable15CreationTool_title, Messages.DelegateGoalTransferable15CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Provision_4012, "provision_trasferable", Messages.ProvideResourceTransferable13CreationTool_title, Messages.ProvideResourceTransferable13CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.GoalDecompositionAND_4007, "decomposition_and", Messages.ANDDecomposition7CreationTool_title, Messages.ANDDecomposition7CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.GoalDecompositionOR_4006, "decomposition_or", Messages.ORDecomposition6CreationTool_title, Messages.ORDecomposition6CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Need_4001, "need", Messages.Need1CreationTool_title, Messages.Need1CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Produce_4002, "produce", Messages.Produce2CreationTool_title, Messages.Produce2CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Modify_4003, "modify", Messages.Modify3CreationTool_title, Messages.Modify3CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Threat_4015, "threat", Messages.Threat19CreationTool_title, Messages.Threat19CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.PositiveGoalContribution_4004, "contribution_positive", Messages.PositiveContribution4CreationTool_title, Messages.PositiveContribution4CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.NegativeGoalContribution_4005, "contribution_negative", Messages.NegativeContribution5CreationTool_title, Messages.NegativeContribution5CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Play_4011, "arrow", Messages.Play12CreationTool_title, Messages.Play12CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Own_4008, "own", Messages.Own9CreationTool_title, Messages.Own9CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.PartOf_4009, "part_of", Messages.PartOfResource10CreationTool_title, Messages.PartOfResource10CreationTool_desc, false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.TangibleBy_4010, "tangible_by", Messages.MadeTangibleBy11CreationTool_title, Messages.MadeTangibleBy11CreationTool_desc, false));
				//paletteContainer.add(createToolEntry(StsToolElementTypes.LINK_DELEGATE,				"delegation_non_trasferable",	Messages.DelegateGoalNonTransferable16CreationTool_title, 	    Messages.DelegateGoalNonTransferable16CreationTool_desc,	false));
		paletteContainer.add(createToolEntry(StsToolElementTypes.Authorisation_4014, "arrow", Messages.AuthorisationTransferable17CreationTool_title, Messages.AuthorisationTransferable17CreationTool_desc, false));
		//paletteContainer.add(createToolEntry(StsToolElementTypes.LINK_AUTHORISATION,			"dotted_arrow",					Messages.AuthorisationNonTransferable18CreationTool_title, 		Messages.AuthorisationNonTransferable18CreationTool_desc,	false));    
		
		return paletteContainer;
	}*/


	private static ToolEntry createSeparator(){
		ToolEntry entry = new PaletteSeparatorTool();
		paletteItems.put(entry, null);
		return entry;
	}



	/**
	 * Method to create a tool entry
	 * 
	 * @param types
	 *           A list of IElementType describing which element the tool can create
	 * @param iconName
	 *           the icon name - must be in gif format
	 * @param title
	 *           Title of the tool
	 * @param description
	 *           Description of the tool - Showed as tooltip
	 * @param node
	 *           boolean if the tool is a NodeCreation tool /n flase if the tool is a LinkCreation Tool
	 * 
	 * @return the tool entry
	 */
	private static ToolEntry createToolEntry(IElementType type,String title,String description,boolean node){

		ToolEntry entry;
		List<IElementType> types = new ArrayList<IElementType>();
		types.add(type);
		if (node)
			entry = new NodeToolEntry(title, description, types);
		else
			entry = new LinkToolEntry(title, description, types);
		entry.setId(title + "Tool");

		ImageDescriptor smallImgd = STSElementIconDescriptor.getSmallImageDescriptord(type);
		ImageDescriptor largeImgd = STSElementIconDescriptor.getLargeImageDescriptord(type);

		if (smallImgd == null) {
			entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(types.get(0)));
			entry.setLargeIcon(entry.getSmallIcon());
		} else {
			entry.setSmallIcon(smallImgd);
			if (largeImgd == null)
				entry.setLargeIcon(entry.getSmallIcon());
			else
				entry.setLargeIcon(largeImgd);
		}
		entry.setUserModificationPermission(NodeToolEntry.PERMISSION_NO_MODIFICATION);
		paletteItems.put(entry, types.get(0));
		return entry;
	}


	/**
	 * Hide the tool entry that are not visible in the current view
	 * 
	 * @param view
	 *           the view Manager of the current diagram
	 */
	public static void hideElements(ViewsManager view){

		for (ToolEntry te : paletteItems.keySet()) {
			if (te instanceof PaletteSeparatorTool) {
				if (view.getCurrentIntView() != ViewsManager.SOCIAL_VIEW) {
					te.setVisible(false);
				} else {
					te.setVisible(true);
				}
			} else {
				if (view.isElementVisibleInPalette(paletteItems.get(te)))
					te.setVisible(true);
				else
					te.setVisible(false);
			}
		}
	}

	/**
	 * Hide all the Creation tool from the palette
	 * 
	 */
	public static void hideAll(){

		for (ToolEntry te : paletteItems.keySet()) {
			te.setVisible(false);
		}
	}


	/**
	 * @generated NOT
	 */
	static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List	elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description, List elementTypes) {

			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool(){

			Tool tool = new UnspecifiedTypeCreationTool(elementTypes) {

				@Override
				protected void handleFinished(){

					if (!getCurrentInput().isControlKeyDown()) {
						super.handleFinished();
					} else {
						reactivate();
					}
				}

			};
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated NOT
	 */
	static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List	relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description, List relationshipTypes) {

			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool(){

			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes) {

				@Override
				protected void handleFinished(){

					if (!getCurrentInput().isControlKeyDown()) {
						super.handleFinished();
					} else {
						reactivate();
					}
				}

//				protected Command getCommand() {
//					
//					Command c= null;
//					if (getTargetEditPart() != null)
//						c= getTargetEditPart().getCommand(getTargetRequest());
//					if(c!=null)
//					System.out.println("getCommand() "+c.getLabel());
//					else{
//						System.out.println("getTargetEditPart() "+getTargetEditPart());
//					}
//					return c;
//				}

			};
			tool.setProperties(getToolProperties());
			return tool;
		}

		

	}


}
