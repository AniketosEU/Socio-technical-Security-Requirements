/*
* StsToolPaletteFactory.java
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
package eu.aniketos.wp1.ststool.diagram.part;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * @generated
 */
public class StsToolPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot){
		paletteRoot.add(createStsElements1Group());
		paletteRoot.add(createStsRelations2Group());
	}

	/**
	 * Creates "Sts Elements" palette tool group
	 * 
	 * @generated
	 */
	private PaletteContainer createStsElements1Group(){
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.StsElements1Group_title);
		paletteContainer.setId("createStsElements1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.StsElements1Group_desc);
		paletteContainer.add(createAgent1CreationTool());
		paletteContainer.add(createRole2CreationTool());
		paletteContainer.add(createGoal3CreationTool());
		paletteContainer.add(createDocument4CreationTool());
		paletteContainer.add(createInformation5CreationTool());
		paletteContainer.add(createEvent6CreationTool());
		return paletteContainer;
	}

	/**
	 * Creates "Sts Relations" palette tool group
	 * 
	 * @generated
	 */
	private PaletteContainer createStsRelations2Group(){
		PaletteDrawer paletteContainer = new PaletteDrawer(Messages.StsRelations2Group_title);
		paletteContainer.setId("createStsRelations2Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.StsRelations2Group_desc);
		paletteContainer.add(createNeed1CreationTool());
		paletteContainer.add(createProduce2CreationTool());
		paletteContainer.add(createModify3CreationTool());
		paletteContainer.add(createPositiveContribution4CreationTool());
		paletteContainer.add(createNegativeContribution5CreationTool());
		paletteContainer.add(createOR6CreationTool());
		paletteContainer.add(createAND7CreationTool());
		paletteContainer.add(createOwn8CreationTool());
		paletteContainer.add(createPartOf9CreationTool());
		paletteContainer.add(createTangibleBy10CreationTool());
		paletteContainer.add(createPlay11CreationTool());
		paletteContainer.add(createProvide12CreationTool());
		paletteContainer.add(createDelegate13CreationTool());
		paletteContainer.add(createAuthorisation14CreationTool());
		paletteContainer.add(createThreat15CreationTool());
		paletteContainer.add(createIncompatible16CreationTool());
		paletteContainer.add(createCompatible17CreationTool());
		paletteContainer.add(createDependby18CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAgent1CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Agent_2001);
		NodeToolEntry entry = new NodeToolEntry(Messages.Agent1CreationTool_title, Messages.Agent1CreationTool_desc, types);
		entry.setId("createAgent1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Agent_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRole2CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Role_2002);
		NodeToolEntry entry = new NodeToolEntry(Messages.Role2CreationTool_title, Messages.Role2CreationTool_desc, types);
		entry.setId("createRole2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Role_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGoal3CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(StsToolElementTypes.Goal_3001);
		types.add(StsToolElementTypes.Goal_2003);
		NodeToolEntry entry = new NodeToolEntry(Messages.Goal3CreationTool_title, Messages.Goal3CreationTool_desc, types);
		entry.setId("createGoal3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Goal_3001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDocument4CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(2);
		types.add(StsToolElementTypes.TResource_3002);
		types.add(StsToolElementTypes.TResource_2004);
		NodeToolEntry entry = new NodeToolEntry(Messages.Document4CreationTool_title, Messages.Document4CreationTool_desc, types);
		entry.setId("createDocument4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.TResource_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInformation5CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.IResource_2005);
		NodeToolEntry entry = new NodeToolEntry(Messages.Information5CreationTool_title, Messages.Information5CreationTool_desc, types);
		entry.setId("createInformation5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.IResource_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createEvent6CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Event_2006);
		NodeToolEntry entry = new NodeToolEntry(Messages.Event6CreationTool_title, Messages.Event6CreationTool_desc, types);
		entry.setId("createEvent6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Event_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNeed1CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Need_4001);
		LinkToolEntry entry = new LinkToolEntry(Messages.Need1CreationTool_title, Messages.Need1CreationTool_desc, types);
		entry.setId("createNeed1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Need_4001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProduce2CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Produce_4002);
		LinkToolEntry entry = new LinkToolEntry(Messages.Produce2CreationTool_title, Messages.Produce2CreationTool_desc, types);
		entry.setId("createProduce2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Produce_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createModify3CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Modify_4003);
		LinkToolEntry entry = new LinkToolEntry(Messages.Modify3CreationTool_title, Messages.Modify3CreationTool_desc, types);
		entry.setId("createModify3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Modify_4003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPositiveContribution4CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.PositiveGoalContribution_4004);
		LinkToolEntry entry = new LinkToolEntry(Messages.PositiveContribution4CreationTool_title, Messages.PositiveContribution4CreationTool_desc, types);
		entry.setId("createPositiveContribution4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.PositiveGoalContribution_4004));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createNegativeContribution5CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.NegativeGoalContribution_4005);
		LinkToolEntry entry = new LinkToolEntry(Messages.NegativeContribution5CreationTool_title, Messages.NegativeContribution5CreationTool_desc, types);
		entry.setId("createNegativeContribution5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.NegativeGoalContribution_4005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOR6CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.GoalDecompositionOR_4006);
		LinkToolEntry entry = new LinkToolEntry(Messages.OR6CreationTool_title, Messages.OR6CreationTool_desc, types);
		entry.setId("createOR6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.GoalDecompositionOR_4006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAND7CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.GoalDecompositionAND_4007);
		LinkToolEntry entry = new LinkToolEntry(Messages.AND7CreationTool_title, Messages.AND7CreationTool_desc, types);
		entry.setId("createAND7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.GoalDecompositionAND_4007));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOwn8CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Own_4008);
		LinkToolEntry entry = new LinkToolEntry(Messages.Own8CreationTool_title, Messages.Own8CreationTool_desc, types);
		entry.setId("createOwn8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Own_4008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPartOf9CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.PartOf_4009);
		LinkToolEntry entry = new LinkToolEntry(Messages.PartOf9CreationTool_title, Messages.PartOf9CreationTool_desc, types);
		entry.setId("createPartOf9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.PartOf_4009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTangibleBy10CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.TangibleBy_4010);
		LinkToolEntry entry = new LinkToolEntry(Messages.TangibleBy10CreationTool_title, Messages.TangibleBy10CreationTool_desc, types);
		entry.setId("createTangibleBy10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.TangibleBy_4010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPlay11CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Play_4011);
		LinkToolEntry entry = new LinkToolEntry(Messages.Play11CreationTool_title, Messages.Play11CreationTool_desc, types);
		entry.setId("createPlay11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Play_4011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProvide12CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Provision_4012);
		LinkToolEntry entry = new LinkToolEntry(Messages.Provide12CreationTool_title, Messages.Provide12CreationTool_desc, types);
		entry.setId("createProvide12CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Provision_4012));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDelegate13CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Delegation_4013);
		LinkToolEntry entry = new LinkToolEntry(Messages.Delegate13CreationTool_title, Messages.Delegate13CreationTool_desc, types);
		entry.setId("createDelegate13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Delegation_4013));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAuthorisation14CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Authorisation_4014);
		LinkToolEntry entry = new LinkToolEntry(Messages.Authorisation14CreationTool_title, Messages.Authorisation14CreationTool_desc, types);
		entry.setId("createAuthorisation14CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Authorisation_4014));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createThreat15CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Threat_4015);
		LinkToolEntry entry = new LinkToolEntry(Messages.Threat15CreationTool_title, Messages.Threat15CreationTool_desc, types);
		entry.setId("createThreat15CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Threat_4015));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createIncompatible16CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.IncompatibleDuties_4016);
		LinkToolEntry entry = new LinkToolEntry(Messages.Incompatible16CreationTool_title, Messages.Incompatible16CreationTool_desc, types);
		entry.setId("createIncompatible16CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.IncompatibleDuties_4016));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createCompatible17CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.CompatibleDuties_4017);
		LinkToolEntry entry = new LinkToolEntry(Messages.Compatible17CreationTool_title, Messages.Compatible17CreationTool_desc, types);
		entry.setId("createCompatible17CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.CompatibleDuties_4017));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createDependby18CreationTool(){
		List/*<IElementType>*/types = new ArrayList/*<IElementType>*/(1);
		types.add(StsToolElementTypes.Dependency_4018);
		LinkToolEntry entry = new LinkToolEntry(Messages.Dependby18CreationTool_title, Messages.Dependby18CreationTool_desc, types);
		entry.setId("createDependby18CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(StsToolElementTypes.getImageDescriptor(StsToolElementTypes.Dependency_4018));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

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
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

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
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
