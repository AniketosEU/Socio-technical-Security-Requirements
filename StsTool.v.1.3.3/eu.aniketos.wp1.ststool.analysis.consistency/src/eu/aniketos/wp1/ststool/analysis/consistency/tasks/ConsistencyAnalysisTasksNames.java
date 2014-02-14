/*
* ConsistencyAnalysisTasksNames.java
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
package eu.aniketos.wp1.ststool.analysis.consistency.tasks;

import static eu.aniketos.wp1.ststool.analysis.consistency.Messages.*;

public final class ConsistencyAnalysisTasksNames {

	public final static String		EMPTYDIAGRAM								= getMessage(TaskName_EmptyDiagram);
//	public final static String		AGENTNOTPLAYBOD							= getMessage(TaskName_AgentNotPlayBod);
//	public final static String		AGENTPLAYSOD								= getMessage(TaskName_AgentPlaySod);
	public final static String		GOALSINGLEDECOMPOSITION					= getMessage(TaskName_GoalSingleDecomposition);
	public final static String		GOALLEAFDELEGATION						= getMessage(TaskName_GoalLeafDelegation);
	public final static String		GOALLEAFCAPABILITY						= getMessage(TaskName_GoalLeafCapability);
	public final static String		DELEGATIONCHILDCYCLES					= getMessage(TaskName_DelegationChildCycles);
//	public final static String		DELEGATEDGOALPARTOFDECOMPOSITION		= getMessage(TaskName_DelegatedGoalPartOfDecomposition);
	public final static String		CONTRIBUTIONSCYCLE						= getMessage(TaskName_ContributionsCycle);
	public final static String		NEGATIVECONTRIBUTIONSBETWEENAND		= getMessage(TaskName_NegativeContributionsBetweenAnd);
	//public final static String		ORGANIZATIONALCONSTRAINTCONSISTENCY	= getMessage(TaskName_OrganizationalConstraintConsistency);
	public final static String		PARTOFOFDOCUMENTSCICLES					= getMessage(TaskName_PartofOfDocumentsCicles);
	public final static String		PARTOFOFINFORMATIONCYCLES				= getMessage(TaskName_PartofOfInformationCycles);
	public final static String		INFORMATIONNOOWNER						= getMessage(TaskName_InformationNoOwner);
	public final static String		AUTHORISATIONSVALIDITY					= getMessage(TaskName_AuthorisationsValidity);
	public final static String		DUPLICATEAUTHORISATION					= getMessage(TaskName_DuplicateAuthorisation);

	public final static String[]	ALL_TASKS_NAMES							= new String[] { EMPTYDIAGRAM, /*AGENTNOTPLAYBOD,
			AGENTPLAYSOD,*/ GOALSINGLEDECOMPOSITION, /*GOALLEAFDELEGATION, GOALLEAFCAPABILITY,*/ DELEGATIONCHILDCYCLES,
			/*DELEGATEDGOALPARTOFDECOMPOSITION,*/ CONTRIBUTIONSCYCLE, NEGATIVECONTRIBUTIONSBETWEENAND,
			/*ORGANIZATIONALCONSTRAINTCONSISTENCY,*/ PARTOFOFDOCUMENTSCICLES, PARTOFOFINFORMATIONCYCLES, INFORMATIONNOOWNER,
			AUTHORISATIONSVALIDITY, DUPLICATEAUTHORISATION,				};

}
