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
package eu.aniketos.wp1.ststool.analysis.security;

import java.text.MessageFormat;
import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	public static String	MissingSecurityPrerequisite_text;

	public static String	SecurityCategoryName;
	public static String	AnalysisWindowTitle;
	public static String	AnalysisWindowSubtitle;

	public static String	TaskName_Pre_Ahtorisation_Conflict;
	public static String	TaskName_Ahtorisation_Conflict;
	
	public static String	TaskName_NoDelegation_Violation;
	public static String	TaskName_Redundancy_Violation;
	
	public static String	TaskName_Operation_PreAnalysis;
	public static String	TaskName_NonDisclosure_Violation;
	public static String	TaskName_NonUsage_Violation;
	public static String	TaskName_NonModification_Violation;
	public static String	TaskName_NonProduction_Violation;
	public static String	TaskName_NTK_Violation;

	public static String	TaskName_Authorisation_PreAnalysis;
	public static String	TaskName_Unath_Del_Auth_Violation;
	public static String	TaskName_Unath_Del_Usage_Violation;
	public static String	TaskName_Unath_Del_Mod_Violation;
	public static String	TaskName_Unath_Del_Prod_Violation;
	public static String	TaskName_Unath_Del_Dist_Violation;

	public static String 	TaskName_Business_PreAnalysis;
	public static String	TaskName_OrganizationalConstraintConsistency;
	public static String	TaskName_AgentPlaySod;
	public static String	TaskName_AgentNotPlayBod;

	public static String	TaskName_Sod_Goal_Violation;
	public static String	TaskName_Cod_Goal_Violation;

	public static String	Result_NoErrorsText;
	public static String	Result_NoErrorsDesc;

	public static String	Result_Authority_Violation_text;
	public static String	Result_Authority_Violation_desc;
	public static String	Result_NoDelegation_Violation_text;
	public static String	Result_NoDelegation_Violation_desc;
	public static String	Result_NonDisclosure_Violation_text;
	public static String	Result_NonDisclosure_Violation_desc;
	public static String	Result_NonDisclosure_Violation_NoAuth_text;
	public static String	Result_NonDisclosure_Violation_NoAuth_desc;
	public static String	Result_NonUsage_Violation_text;
	public static String	Result_NonUsage_Violation_desc;
	public static String	Result_NonUsage_Violation_NoAuth_text;
	public static String	Result_NonUsage_Violation_NoAuth_desc;
	public static String	Result_NonModification_Violation_text;
	public static String	Result_NonModification_Violation_desc;
	public static String	Result_NonModification_Violation_NoAuth_text;
	public static String	Result_NonModification_Violation_NoAuth_desc;
	public static String	Result_NonProduction_Violation_text;
	public static String	Result_NonProduction_Violation_desc;
	public static String	Result_NonProduction_Violation_NoAuth_text;
	public static String	Result_NonProduction_Violation_NoAuth_desc;
	public static String	Result_NTK_Violation_text;
	public static String	Result_NTK_Violation_desc;
	public static String	Result_NTK_Violation_NoAuth_text;
	public static String	Result_NTK_Violation_NoAuth_desc;

	public static String	Result_Unath_Del_Usage_Violation_text;
	public static String	Result_Unath_Del_Usage_Violation_desc;
	public static String	Result_Unath_Del_Mod_Violation_text;
	public static String	Result_Unath_Del_Mod_Violation_desc;
	public static String	Result_Unath_Del_Prod_Violation_text;
	public static String	Result_Unath_Del_Prod_Violation_desc;
	public static String	Result_Unath_Del_Dist_Violation_text;
	public static String	Result_Unath_Del_Dist_Violation_desc;

	public static String	Result_Sod_Goal_Violation_text;
	public static String	Result_Sod_Goal_Violation_desc;
	public static String	Result_Cod_Goal_Violation_text;
	public static String	Result_Cod_Goal_Violation_desc;
	public static String	Result_Cod_Goal_Violation_NoAgent_text;
	public static String	Result_Cod_Goal_Violation_NoAgent_desc;
	public static String	Result_OrganizationalConsSodTreeGoal_text;
	public static String	Result_OrganizationalConsSodTreeGoal_desc;
	public static String	Result_OrganizationalConsBodTreeGoal_text;
	public static String	Result_OrganizationalConsBodTreeGoal_desc;
	public static String	Result_AgentPlaySod_desc;
	public static String	Result_AgentPlaySod_text;
	public static String	Result_AgentNotPlayBod_text;
	public static String	Result_AgentNotPlayBod_desc;

	
	public static String	Result_Auth_Usage_Conflict;
	public static String	Result_Auth_Usage_Conflict_desc;
	public static String	Result_Auth_Modif_Conflict;
	public static String	Result_Auth_Modif_Conflict_desc;
	public static String	Result_Auth_Produ_Conflict;
	public static String	Result_Auth_Produ_Conflict_desc;
	public static String	Result_Auth_Discl_Conflict;
	public static String	Result_Auth_Discl_Conflict_desc;
	public static String	Result_Auth_Trans_Conflict;
	public static String	Result_Auth_Trans_Conflict_desc;
	
	public static String	Result_Redundancy_text;
	public static String	Result_Redundancy_desc;
	public static String	Result_Redundancy_Single_text;
	public static String	Result_Redundancy_Single_desc;
	public static String	Result_Redundancy_Multi_text;
	public static String	Result_Redundancy_Multi_desc;

	public static String getMessage(String text,Object...param){
		return MessageFormat.format(text, (Object[]) param).trim();
	}

	public static String getMessage(String text){
		return text.trim();
	}

	static {
		NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
	}

	private Messages() {
	}

}
