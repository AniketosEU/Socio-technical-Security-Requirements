/*
* Messages.java
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
package eu.aniketos.wp1.ststool.analysis.consistency;

import java.text.MessageFormat;
import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	public static String	ConsistencyCategoryName;
	public static String	AnalysisWindowSubtitle;
	public static String	AnalysisWindowTitle;
	public static String	Result_NoErrorsDesc;
	public static String	Result_NoErrorsText;

	public static String	Result_ContributionLoop_desc;
	public static String	Result_ContributionLoop_text;
	public static String	Result_DecomposedGoalDelegated_desc;
	public static String	Result_DecomposedGoalDelegated_text;
	public static String	Result_DecomposedGoalCapability_text;
	public static String	Result_DecomposedGoalCapability_desc;
	public static String	Result_DlegatedGoalPartOfDecomposition_desc;
	public static String	Result_DlegatedGoalPartOfDecomposition_text;
	public static String	Result_DlegationLoop_desc;
	public static String	Result_DlegationLoop_text;
	public static String	Result_NegativeContributionBetweenAND_desc;
	public static String	Result_NegativeContributionBetweenAND_text;
	public static String	Result_NoActors_desc;
	public static String	Result_NoActors_text;
	public static String	Result_SingleDecomposition_desc;
	public static String	Result_SingleDecomposition_text;

	
	public static String	TaskName_ContributionsCycle;
	public static String	TaskName_DelegatedGoalPartOfDecomposition;
	public static String	TaskName_DelegationChildCycles;
	public static String	TaskName_EmptyDiagram;
	public static String	TaskName_GoalLeafDelegation;
	public static String	TaskName_GoalLeafCapability;
	public static String	TaskName_GoalSingleDecomposition;
	public static String	TaskName_NegativeContributionsBetweenAnd;


	public static String	Result_DocumentPartOfCicle_desc;
	public static String	Result_DocumentPartOfCicle_text;
	public static String	Result_InformationNoOwner_desc;
	public static String	Result_InformationNoOwner_text;
	public static String	Result_InformationPartOfCicle_desc;
	public static String	Result_InformationPartOfCicle_text;
	public static String	TaskName_InformationNoOwner;
	public static String	TaskName_PartofOfDocumentsCicles;
	public static String	TaskName_PartofOfInformationCycles;


	public static String	Result_AuthorisationsInformationNoOwner_desc;
	public static String	Result_AuthorisationsInformationNoOwner_text;
	//public static String				Result_AuthorisationsInformationWrongOwner_desc;
	//public static String				Result_AuthorisationsInformationWrongOwner_text;
	public static String	Result_AuthorisationsValidityNoInformation_desc;
	public static String	Result_AuthorisationsValidityNoInformation_text;
	public static String	Result_AuthorisationsValidityNoOperations_desc;
	public static String	Result_AuthorisationsValidityNoOperations_text;
	public static String	Result_AuthorisationsValidityEmpty_text;
	public static String	Result_AuthorisationsValidityEmpty_desc;
	public static String	Result_DuplicateAuthorisation_desc;
	public static String	Result_DuplicateAuthorisation_text;
	public static String	TaskName_AuthorisationsInformationOwner;
	public static String	TaskName_AuthorisationsValidity;
	public static String	TaskName_DuplicateAuthorisation;

	public static String getMessage(String text,String...param){
		return MessageFormat.format(text, (Object[]) param);
	}

	public static String getMessage(String text){
		return text;
	}

	static {
		NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
	}

	private Messages() {
	}

}
