/*
* ViolationsDefinitions.java
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
package eu.aniketos.wp1.ststool.analysis.security.internal.violationsdefinitions;



public class ViolationsDefinitions {

	public final static IViolationDefinition	NO_DELEGATION_VIOLATION			= new STSViolation("violate_no_delegation", 			"goal-rules-XOR", "no-delegation-violation");
	
	//public final static IViolationDefinition	REDUNDANCY_VIOLATION			= new STSViolation("violation_red",						"goal-rules-AND", "redundancy-violation");
	public final static IViolationDefinition	REDUNDANCY_SINGLE_VIOLATION		= new STSViolation("violation_rs",						"goal-rules-AND", "redundancy-violation");
	public final static IViolationDefinition	REDUNDANCY_MULTI_VIOLATION		= new STSViolation("violation_rm",						"goal-rules-AND", "redundancy-violation");
	
	
	public final static IViolationDefinition	AUTH_USAGE_CONFLICT				= new STSViolation("auth_usage_conflict",				"goal-rules-AND", "info-doc-rules", "authorisation-rules", "authorisation-conflicts");
	public final static IViolationDefinition	AUTH_MODIF_CONFLICT				= new STSViolation("auth_modif_conflict",				"goal-rules-AND", "info-doc-rules", "authorisation-rules", "authorisation-conflicts");
	public final static IViolationDefinition	AUTH_PRODU_CONFLICT				= new STSViolation("auth_produ_conflict",				"goal-rules-AND", "info-doc-rules", "authorisation-rules", "authorisation-conflicts");
	public final static IViolationDefinition	AUTH_DISCL_CONFLICT				= new STSViolation("auth_discl_conflict",				"goal-rules-AND", "info-doc-rules", "authorisation-rules", "authorisation-conflicts");
	public final static IViolationDefinition	AUTH_TRASF_CONFLICT				= new STSViolation("auth_trasf_conflict",				"goal-rules-AND", "info-doc-rules", "authorisation-rules", "authorisation-conflicts");

	public final static IViolationDefinition	NON_DISCLOSURE_VIOLATION		= new STSViolation("violate_non_disclosure",								"info-doc-rules", "authorisation-rules", "authorisations-violation");
	public final static IViolationDefinition	NON_USAGE_VIOLATION				= new STSViolation("violate_non_usage", 				"goal-rules-XOR", 	"info-doc-rules", "authorisation-rules", "authorisations-violation");
	public final static IViolationDefinition	NON_MODIFICATION_VIOLATION		= new STSViolation("violate_non_modification", 			"goal-rules-XOR", 	"info-doc-rules", "authorisation-rules", "authorisations-violation");
	public final static IViolationDefinition	NON_PRODUCTION_VIOLATION		= new STSViolation("violate_non_production", 			"goal-rules-XOR", 	"info-doc-rules", "authorisation-rules", "authorisations-violation");
	public final static IViolationDefinition	NTK_VIOLATION					= new STSViolation("violate_ntk", 						"goal-rules-XOR", 	"info-doc-rules", "authorisation-rules", "authorisations-violation");

	public final static IViolationDefinition	UNAUTH_DEL_OF_AUTH_VIOLATION	= new STSViolation("violate_del_of_authority", 			"goal-rules-XOR", "info-doc-rules", "authorisation-rules", "authority-violation");
	public final static IViolationDefinition	UNAUTH_DEL_OF_USAGE_VIOLATION	= new STSViolation("unauthorised_del_of_usage", 		"goal-rules-XOR", "info-doc-rules", "authorisation-rules", "authority-violation");
	public final static IViolationDefinition	UNAUTH_DEL_OF_MOD_VIOLATION		= new STSViolation("unauthorised_del_of_modification", 	"goal-rules-XOR", "info-doc-rules", "authorisation-rules", "authority-violation");
	public final static IViolationDefinition	UNAUTH_DEL_OF_PROD_VIOLATION	= new STSViolation("unauthorised_del_of_production", 	"goal-rules-XOR", "info-doc-rules", "authorisation-rules", "authority-violation");
	public final static IViolationDefinition	UNAUTH_DEL_OF_DIST_VIOLATION	= new STSViolation("unauthorised_del_of_distribution", 	"goal-rules-XOR", "info-doc-rules", "authorisation-rules", "authority-violation");

	public final static IViolationDefinition	SOD_ROLE_VIOLATION				= new STSViolation("violate_sod_role", 					"goal-rules-AND", "org-constraints");
	public final static IViolationDefinition	SOD_GOAL_VIOLATION				= new STSViolation("violate_sod_goal", 					"goal-rules-AND", "org-constraints");
	public final static IViolationDefinition	COD_GOAL_VIOLATION				= new STSViolation("violate_cod_goal", 					"goal-rules-AND", "org-constraints");
	
	//public final static IViolationDefinition	NO_VIOLATION						= new GeneralViolationDefinition("NO VIOLATION");
	//public final static IViolationDefinition	FILE_VIOLATION						= new FileViolation();
}
