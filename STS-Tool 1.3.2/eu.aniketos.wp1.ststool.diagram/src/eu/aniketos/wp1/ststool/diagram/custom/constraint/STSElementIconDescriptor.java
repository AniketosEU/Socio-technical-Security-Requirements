/*
* STSElementIconDescriptor.java
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
package eu.aniketos.wp1.ststool.diagram.custom.constraint;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;

/**
 * This class contain static field to group different IElementType and method to get their icons
 * 
 * This class is Used by the Palette to initialize the Creation tools
 */

public class STSElementIconDescriptor {

	/**
	 * Return the image descriptor of a small icon for the indicated type
	 * 
	 * @param type
	 *           must be one of the static type of this class
	 * 
	 * @return the ImageDescriptor of the small icon related to the type
	 */
	public static ImageDescriptor getSmallImageDescriptord(IElementType type){

		return getImageDescriptor(type, "icons/palette/small/");
	}

	/**
	 * Return the image descriptor of a Large icon for the indicated type
	 * 
	 * @param type
	 *           must be one of the static type of this class
	 * 
	 * @return the ImageDescriptor of the Large icon related to the type
	 */
	public static ImageDescriptor getLargeImageDescriptord(IElementType type){

		ImageDescriptor id = getImageDescriptor(type, "icons/palette/large/");
		if (id == null) getSmallImageDescriptord(type);
		return id;
	}


	/**
	 * Return the image descriptor of icon for the indicated type
	 * 
	 * @param type
	 *           must be one of the static type of this class
	 * @param finalPath
	 *           the relative path, where the icons should be found
	 * 
	 * @return the ImageDescriptor of the icon related to the type
	 */
	private static ImageDescriptor getImageDescriptor(IElementType type,String path){

		String finalPath = "";
		/*if (type.equals(StsToolElementTypes.Agent_2001))
			finalPath = path.concat("agent");
		else if (type.equals(StsToolElementTypes.Role_2002))
			finalPath = path.concat("role");
		else if (type.equals(StsToolElementTypes.Goal_2003))
			finalPath = path.concat("goal");
		else if (type.equals(StsToolElementTypes.Goal_3001))
			finalPath = path.concat("goal");
		else if (type.equals(StsToolElementTypes.TResource_2004))
			finalPath = path.concat("tresource");
		else if (type.equals(StsToolElementTypes.TResource_3002))
			finalPath = path.concat("tresource");
		else if (type.equals(StsToolElementTypes.IResource_2005))
			finalPath = path.concat("iresource");
		else if (type.equals(StsToolElementTypes.Event_2006))
			finalPath = path.concat("event");
		else if (type.equals(StsToolElementTypes.Need_4001))
			finalPath = path.concat("need");
		else if (type.equals(StsToolElementTypes.Produce_4002))
			finalPath = path.concat("produce");
		else if (type.equals(StsToolElementTypes.Modify_4003))
			finalPath = path.concat("modify");
		else if (type.equals(StsToolElementTypes.PositiveGoalContribution_4004))
			finalPath = path.concat("contribution_positive");
		else if (type.equals(StsToolElementTypes.NegativeGoalContribution_4005))
			finalPath = path.concat("contribution_negative");
		else if (type.equals(StsToolElementTypes.GoalDecompositionOR_4006))
			finalPath = path.concat("decomposition_or");
		else if (type.equals(StsToolElementTypes.GoalDecompositionAND_4007))
			finalPath = path.concat("decomposition_and");
		else if (type.equals(StsToolElementTypes.Own_4008))
			finalPath = path.concat("own");
		else if (type.equals(StsToolElementTypes.PartOf_4009))
			finalPath = path.concat("part_of");
		else if (type.equals(StsToolElementTypes.TangibleBy_4010))
			finalPath = path.concat("tangible_by");
		else if (type.equals(StsToolElementTypes.Play_4011))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Provision_4012))
			finalPath = path.concat("provision_trasferable");
		else if (type.equals(StsToolElementTypes.Delegation_4013))
			finalPath = path.concat("delegation_trasferable" + "");
		else if (type.equals(StsToolElementTypes.Authorisation_4014))
			//finalPath=path.concat("authorisation");
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Threat_4015)) finalPath = path.concat("threat");*/

		if (type.equals(StsToolElementTypes.Agent_2001))
			finalPath = path.concat("agent");
		else if (type.equals(StsToolElementTypes.Role_2002))
			finalPath = path.concat("role");
		else if (type.equals(StsToolElementTypes.Goal_2003))
			finalPath = path.concat("goal");
		else if (type.equals(StsToolElementTypes.Goal_3001))
			finalPath = path.concat("goal");
		else if (type.equals(StsToolElementTypes.TResource_2004))
			finalPath = path.concat("tresource");
		else if (type.equals(StsToolElementTypes.TResource_3002))
			finalPath = path.concat("tresource");
		else if (type.equals(StsToolElementTypes.IResource_2005))
			finalPath = path.concat("iresource");
		else if (type.equals(StsToolElementTypes.Event_2006))
			finalPath = path.concat("event");
		else if (type.equals(StsToolElementTypes.Need_4001))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Produce_4002))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Modify_4003))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.PositiveGoalContribution_4004))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.NegativeGoalContribution_4005))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.GoalDecompositionOR_4006))
			finalPath = path.concat("decomposition_or");
		else if (type.equals(StsToolElementTypes.GoalDecompositionAND_4007))
			finalPath = path.concat("decomposition_and");
		else if (type.equals(StsToolElementTypes.Own_4008))
			finalPath = path.concat("own");
		else if (type.equals(StsToolElementTypes.PartOf_4009))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.TangibleBy_4010))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Play_4011))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Provision_4012))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Delegation_4013))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Authorisation_4014))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Threat_4015))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.IncompatibleDuties_4016))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.CompatibleDuties_4017))
			finalPath = path.concat("arrow");
		else if (type.equals(StsToolElementTypes.Dependency_4018))
			finalPath = path.concat("arrow");

		ImageDescriptor id = StsToolDiagramEditorPlugin.findImageDescriptor(finalPath + ".gif");
		return id;
	}
}
