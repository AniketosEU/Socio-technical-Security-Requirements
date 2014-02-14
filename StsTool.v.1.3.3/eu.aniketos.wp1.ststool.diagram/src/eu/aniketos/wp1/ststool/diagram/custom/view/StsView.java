/*
* StsView.java
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import eu.aniketos.wp1.ststool.diagram.providers.StsToolElementTypes;


/**
 * Generic Sts View
 */
public abstract class StsView implements View {

	protected Set<IElementType>		visibleElementInDiagram;
	protected Set<IElementType>		visibleElementsinPalette;

	public final static IElementType	NODE_AGENT					= StsToolElementTypes.Agent_2001;
	public final static IElementType	NODE_ROLE					= StsToolElementTypes.Role_2002;
	public final static IElementType	NODE_GOAL					= StsToolElementTypes.Goal_3001;
	public final static IElementType	NODE_TRESOURCE				= StsToolElementTypes.TResource_3002;
	public final static IElementType	NODE_IRESOURCE				= StsToolElementTypes.IResource_2005;
	public final static IElementType	NODE_EVENT					= StsToolElementTypes.Event_2006;

	public final static IElementType	LINK_NEED					= StsToolElementTypes.Need_4001;
	public final static IElementType	LINK_PRODUCE				= StsToolElementTypes.Produce_4002;
	public final static IElementType	LINK_MODIFY					= StsToolElementTypes.Modify_4003;
	public final static IElementType	LINK_CONTRIBUTION_POS	= StsToolElementTypes.PositiveGoalContribution_4004;
	public final static IElementType	LINK_CONTRIBUTION_NEG	= StsToolElementTypes.NegativeGoalContribution_4005;
	public final static IElementType	LINK_DECOMPOSITION_OR	= StsToolElementTypes.GoalDecompositionOR_4006;
	public final static IElementType	LINK_DECOMPOSITION_AND	= StsToolElementTypes.GoalDecompositionAND_4007;
	public final static IElementType	LINK_OWN						= StsToolElementTypes.Own_4008;
	public final static IElementType	LINK_PART_OF				= StsToolElementTypes.PartOf_4009;
	public final static IElementType	LINK_TANGIBLE_BY			= StsToolElementTypes.TangibleBy_4010;
	public final static IElementType	LINK_PLAY					= StsToolElementTypes.Play_4011;
	public final static IElementType	LINK_PROVIDE				= StsToolElementTypes.Provision_4012;
	public final static IElementType	LINK_DELEGATE				= StsToolElementTypes.Delegation_4013;
	public final static IElementType	LINK_AUTHORISATION		= StsToolElementTypes.Authorisation_4014;
	public final static IElementType	LINK_THREAT					= StsToolElementTypes.Threat_4015;
	public final static IElementType	LINK_COMPATIBLE			= StsToolElementTypes.CompatibleDuties_4017;
	public final static IElementType	LINK_INCOMPATIBLE			= StsToolElementTypes.IncompatibleDuties_4016;
	public final static IElementType	LINK_DEPENDENCY			= StsToolElementTypes.Dependency_4018;

	public StsView() {

		visibleElementInDiagram = new HashSet<IElementType>();
		visibleElementsinPalette = new HashSet<IElementType>();
	}

	/**
	 * @return the human readable name for this view
	 */
	@Override
	abstract public String getName();


	@Override
	public boolean isElementVisible(IElementType e){
		if (e == null) return false;
		return visibleElementInDiagram.contains(e);
	}

	@Override
	public boolean isElementVisibleInPalette(IElementType e){
		if (e == null) return false;
		return visibleElementsinPalette.contains(e);
	}

	/**
	 * @param element
	 *           , the element to add to the view
	 */
	protected void addElementToView(IElementType e){
		visibleElementInDiagram.add(e);
	}

	/**
	 * @param element
	 *           , the element to remove from the view
	 */
	protected void removeElementFromView(IElementType e){
		visibleElementInDiagram.remove(e);
	}

	/**
	 * @param element
	 *           , the element to add to the view
	 */
	protected void addElementToPalette(IElementType e){
		visibleElementsinPalette.add(e);
	}

	/**
	 * @param element
	 *           , the element to remove from the view
	 */
	protected void removeElementFromPalette(IElementType e){
		visibleElementsinPalette.remove(e);
	}

	/**
	 * @param element
	 *           , the element to add to the view
	 */
	protected void addElementToViewAndPalette(IElementType e){
		visibleElementInDiagram.add(e);
		visibleElementsinPalette.add(e);
	}

	/**
	 * @return a list of IElementType, containing all the visible element
	 */
	@Override
	public List<IElementType> getVisibleElementInDiagram(){
		List<IElementType> result = new ArrayList<IElementType>(visibleElementInDiagram);
		return result;
	}

	@Override
	public List<IElementType> getVisiblePaletteTool(){
		List<IElementType> result = new ArrayList<IElementType>(visibleElementsinPalette);
		return result;
	}


}
