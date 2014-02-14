/*
* ResourceView.java
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

/**
 * Class that define the Resource View
 */
public class ResourceView extends StsView {

	/**
	 * Default constructor
	 */
	public ResourceView() {

		super();

		//--Uncomment to enable a element in this view		

		addElementToViewAndPalette(NODE_AGENT);
		addElementToViewAndPalette(NODE_ROLE);
		// addElementToView(NODE_GOAL);
		addElementToViewAndPalette(NODE_TRESOURCE);
		addElementToViewAndPalette(NODE_IRESOURCE);
		// addElementToView(NODE_EVENT);

		// addElementToView(LINK_NEED);  
		// addElementToView(LINK_PRODUCE);
		// addElementToView(LINK_MODIFY);
		// addElementToView(LINK_CONTRIBUTION_POS);
		// addElementToView(LINK_CONTRIBUTION_NEG);
		// addElementToView(LINK_DOCOMPOSITION_OR);
		// addElementToView(LINK_DOCOMPOSITION_AND);                                    
		// addElementToView(LINK_POSSESS);
		addElementToViewAndPalette(LINK_OWN);
		addElementToViewAndPalette(LINK_PART_OF);
		addElementToViewAndPalette(LINK_TANGIBLE_BY);
		addElementToView(LINK_PLAY); 				
		// addElementToView(LINK_PROVIDE); 					
		// addElementToView(LINK_DELEGATE);
		// addElementToView(LINK_AUTHORISATION);
		// addElementToView(LINK_THREAT);

	}

	/**
	 * @return the human readable name for this view
	 */
	@Override
	public String getName(){

		return "Information View";
	}
}
