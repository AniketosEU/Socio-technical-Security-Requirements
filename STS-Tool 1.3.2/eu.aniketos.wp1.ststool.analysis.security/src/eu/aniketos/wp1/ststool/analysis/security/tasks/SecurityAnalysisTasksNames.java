/*
* SecurityAnalysisTasksNames.java
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
package eu.aniketos.wp1.ststool.analysis.security.tasks;

import static eu.aniketos.wp1.ststool.analysis.security.Messages.*;

public interface SecurityAnalysisTasksNames {

	public static String		NODELEGATION_VIOLATION		= getMessage(TaskName_NoDelegation_Violation);

	public static String		AUTH_CONFLICT_PREANALYSIS	= getMessage(TaskName_Pre_Ahtorisation_Conflict);
	public static String		AUTH_CONFLICT				= getMessage(TaskName_Ahtorisation_Conflict);
	
	public static String		REDUNDANCY_VIOLATION		= getMessage(TaskName_Redundancy_Violation);
	
	public static String		OPERATION_PREANALYSIS		= getMessage(TaskName_Operation_PreAnalysis);
	public static String		NONDISCLOSURE_VIOLATION		= getMessage(TaskName_NonDisclosure_Violation);
	public static String		NONUSAGE_VIOLATION			= getMessage(TaskName_NonUsage_Violation);
	public static String		NONMODIFICATION_VIOLATION	= getMessage(TaskName_NonModification_Violation);
	public static String		NONPRODUCTION_VIOLATION		= getMessage(TaskName_NonProduction_Violation);
	public static String		NTK_VIOLATION				= getMessage(TaskName_NTK_Violation);
	
	public static String		AUTHORISATION_PREANALYSIS	= getMessage(TaskName_Authorisation_PreAnalysis);
	public static String		AUTHORITY_VIOLATION			= getMessage(TaskName_Unath_Del_Auth_Violation);
	public static String		UNATH_DEL_USAGE_VIOLATION	= getMessage(TaskName_Unath_Del_Usage_Violation);
	public static String		UNATH_DEL_MOD_VIOLATION		= getMessage(TaskName_Unath_Del_Mod_Violation);
	public static String		UNATH_DEL_PROD_VIOLATION	= getMessage(TaskName_Unath_Del_Prod_Violation);
	public static String		UNATH_DEL_DIST_VIOLATION	= getMessage(TaskName_Unath_Del_Dist_Violation);
	
	public static String		BUISINESS_PREANALYSIS		= getMessage(TaskName_Business_PreAnalysis);
	public static String		AGENTNOTPLAYBOD				= getMessage(TaskName_AgentPlaySod);
	public static String		AGENTPLAYSOD				= getMessage(TaskName_AgentNotPlayBod);
	public static String		ORGANIZATIONALCONSTRAINTCONSISTENCY= getMessage(TaskName_OrganizationalConstraintConsistency);
	public static String		SOD_GOAL_VIOLATION			= getMessage(TaskName_Sod_Goal_Violation);
	public static String		COD_GOAL_VIOLATION			= getMessage(TaskName_Cod_Goal_Violation);

	public static String[]	ALL_TASKS_NAMES				= new String[] { NODELEGATION_VIOLATION, REDUNDANCY_VIOLATION, AUTH_CONFLICT_PREANALYSIS,AUTH_CONFLICT,OPERATION_PREANALYSIS,NONDISCLOSURE_VIOLATION,
			NONUSAGE_VIOLATION, NONMODIFICATION_VIOLATION, NONPRODUCTION_VIOLATION, NTK_VIOLATION,AUTHORISATION_PREANALYSIS, AUTHORITY_VIOLATION,
			UNATH_DEL_USAGE_VIOLATION, UNATH_DEL_MOD_VIOLATION, UNATH_DEL_PROD_VIOLATION, UNATH_DEL_DIST_VIOLATION,BUISINESS_PREANALYSIS,AGENTNOTPLAYBOD,AGENTPLAYSOD,ORGANIZATIONALCONSTRAINTCONSISTENCY,
			SOD_GOAL_VIOLATION, COD_GOAL_VIOLATION		};
}
