/*
* ReportValueFactory.java
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
package eu.aniketos.wp1.ststool.report.pdfgenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import eu.aniketos.wp1.ststool.*;

public class ReportValueFactory {

	public final static int		INTRODUCTION_CHAPTER									= 101;
	public final static int		SOCIAL_VIEW_CHAPTER									= 102;
	public final static int		SOCIAL_DIAGRAM_SECTION								= 103;
	public final static int		STAKEHOLDERS_SECTION									= 104;
	public final static int		STAKEHOLDERS_DOCUMENT_SECTION						= 105;
	public final static int		STAKEHOLDERS_DOCUMENT_GOALS_SECTION				= 106;
	public final static int		GOAL_ANALYSIS_SECTION								= 107;
	public final static int		GOAL_CONTRIBUTION_SECTION							= 108;
	public final static int		STAKEHOLDERS_INTERACTIONS_SECTION				= 109;
	public final static int		ORGANISATIONAL_CONSTRAINT_SECTION				= 110;
	public final static int		EVENTS_SECTION									= 111;
	public final static int		INFORMATION_VIEW_CHAPTER							= 200;
	public final static int		INFORMATION_DIAGRAM_SECTION						= 201;
	public final static int		MODELLING_OWNERSHIP_SECTION						= 202;
	public final static int		REPRESENTATION_INFORMATION_SECTION				= 203;
	public final static int		COMPOSITION_SECTION									= 204;
	public final static int		AUTHORISATION_VIEW_CHAPTER							= 301;
	public final static int		AUTHORISATION_DIAGRAM_SECTION						= 302;
	public final static int		AUTHORISATION_FLOW_SECTION							= 303;
	public final static int		SECURITY_REQUIREMENTS_CHAPTER						= 401;
	public final static int		ANALYSIS_CHAPTER										= 501;
	public final static int		CONSICTENCY_ANALYSIS_SECTION						= 502;
	public final static int		SECURITY_ANALYSIS_SECTION							= 503;
	public final static int		RISK_ANALYSIS_SECTION								= 504;
	public final static int		APPENDIX_CHAPTER										= 601;


	public final static int[]	ALL_CHAPETERS_SECTIONS								= new int[] { INTRODUCTION_CHAPTER,
			SOCIAL_VIEW_CHAPTER, SOCIAL_DIAGRAM_SECTION, STAKEHOLDERS_SECTION,STAKEHOLDERS_DOCUMENT_SECTION,
			STAKEHOLDERS_DOCUMENT_GOALS_SECTION, GOAL_ANALYSIS_SECTION, GOAL_CONTRIBUTION_SECTION,
			STAKEHOLDERS_INTERACTIONS_SECTION,ORGANISATIONAL_CONSTRAINT_SECTION, EVENTS_SECTION,INFORMATION_VIEW_CHAPTER,
			INFORMATION_DIAGRAM_SECTION, MODELLING_OWNERSHIP_SECTION, REPRESENTATION_INFORMATION_SECTION,
			COMPOSITION_SECTION, AUTHORISATION_VIEW_CHAPTER, AUTHORISATION_DIAGRAM_SECTION, AUTHORISATION_FLOW_SECTION,
			SECURITY_REQUIREMENTS_CHAPTER, ANALYSIS_CHAPTER, CONSICTENCY_ANALYSIS_SECTION, SECURITY_ANALYSIS_SECTION,RISK_ANALYSIS_SECTION,
			APPENDIX_CHAPTER																	};


	private String					documentTitle											= "Security Requirements Document";
	private String					reportTitle												= "Project";
	private String					reportAuthor											= "Author";
	private String					reportIstitution										= "Organization";
	private String					date														= new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).format(new Date());

	private StsToolDiagram					diagram=null;
	
	private boolean				generateIntroductionChapter						= true;
	private boolean				generateSocialViewChapter							= true;
	private boolean				generateSocialDiagramSection						= true;
	private boolean				generateStakeholdersSection						= true;
	private boolean				generateStakeholdersInteractionsSection		= true;
	private boolean				generateGoalAnalysisSection						= true;
	private boolean				generateGoalContributionSection					= true;
	private boolean				generateStakeholdersDocumentSection				= true;
	private boolean				generateStakeholdersDocumentAndGoalsSection	= true;
	private boolean				generateOrganisationalConstraintsSection		= true;
	private boolean				generateEventsSection							= true;
	private boolean				generateInformationViewChapter					= true;
	private boolean				generateInformationDiagramSection				= true;
	private boolean				generateModellingOwnershipSection				= true;
	private boolean				generateRepresentationInformationSection		= true;
	private boolean				generateCompositionSection							= true;
	private boolean				generateAuthorisationViewChapter					= true;
	private boolean				generateAuthorisationDiagramSection				= true;
	private boolean				generateAuthorisationFlowSection					= true;
	private boolean				generateSecurityRequirementsChapter				= true;
	private boolean				generateAnalysisChapter								= true;
	private boolean				generateConsistencyAnalysisSection				= true;
	private boolean				generateSecurityAnalysisSection					= true;
	private boolean				generateRiskAnalysisSection						= true;
	private boolean				generateAppendixChapter								= true;


	private List<Actor>			actorsIntroductionChapter							= new ArrayList<Actor>();
	private List<Actor>			actorsSocialViewChapter								= new ArrayList<Actor>();
	private List<Actor>			actorsSocialDiagramSection							= new ArrayList<Actor>();
	private List<Actor>			actorsStakeholdersSection							= new ArrayList<Actor>();
	private List<Actor>			actorsStakeholdersInteractionsSection			= new ArrayList<Actor>();
	private List<Actor>			actorsGoalAnalysisSection							= new ArrayList<Actor>();
	private List<Actor>			actorsGoalContributionSection						= new ArrayList<Actor>();
	private List<Actor>			actorsStakeholdersDocumentSection				= new ArrayList<Actor>();
	private List<Actor>			actorsStakeholdersDocumentAndGoalsSection		= new ArrayList<Actor>();
	private List<Actor>			actorsOrganisationalConstraintsSection			= new ArrayList<Actor>();
	private List<Actor>			actorsEventsSection								= new ArrayList<Actor>();
	private List<Actor>			actorsInformationViewChapter						= new ArrayList<Actor>();
	private List<Actor>			actorsInformationDiagramSection					= new ArrayList<Actor>();
	private List<Actor>			actorsModellingOwnershipSection					= new ArrayList<Actor>();
	private List<Actor>			actorsRepresentationInformationSection			= new ArrayList<Actor>();
	private List<Actor>			actorsCompositionSection							= new ArrayList<Actor>();
	private List<Actor>			actorsAuthorisationViewChapter					= new ArrayList<Actor>();
	private List<Actor>			actorsAuthorisationDiagramSection				= new ArrayList<Actor>();
	private List<Actor>			actorsAuthorisationFlowSection					= new ArrayList<Actor>();
	private List<Actor>			actorsSecurityRequirementsChapter				= new ArrayList<Actor>();
	private List<Actor>			actorsAnalysisChapter								= new ArrayList<Actor>();
	private List<Actor>			actorsConsistencyAnalysisSection					= new ArrayList<Actor>();
	private List<Actor>			actorsSecurityAnalysisSection						= new ArrayList<Actor>();
	private List<Actor>			actorsRiskAnalysisSection						= new ArrayList<Actor>();
	private List<Actor>			actorsAppendixChapter								= new ArrayList<Actor>();

	public String getDocumentTitle(){

		return documentTitle;
	}

	public String getReportTitle(){

		return reportTitle;
	}

	public String getReportIstitution(){

		return reportIstitution;
	}

	public String getDate(){

		return date;
	}

	public String getReportAuthor(){

		return reportAuthor;
	}
	
	public StsToolDiagram getDiagram(){

		return diagram;
	}


	public void setReportTitle(String reportTitle){

		this.reportTitle = reportTitle;
	}

	public void setReportAuthor(String reportAuthor){

		this.reportAuthor = reportAuthor;
	}

	public void setReportIstitution(String reportIstitution){

		this.reportIstitution = reportIstitution;
	}
	
	public void setDiagram(StsToolDiagram diagram){
		this.diagram = diagram;
	}
	

	public boolean generateChapterSection(int section){

		switch (section) {
			case INTRODUCTION_CHAPTER:
				return generateIntroductionChapter;
			case SOCIAL_VIEW_CHAPTER:
				return generateSocialViewChapter;
			case SOCIAL_DIAGRAM_SECTION:
				return generateSocialDiagramSection;
			case STAKEHOLDERS_SECTION:
				return generateStakeholdersSection;
			case STAKEHOLDERS_INTERACTIONS_SECTION:
				return generateStakeholdersInteractionsSection;
			case GOAL_ANALYSIS_SECTION:
				return generateGoalAnalysisSection;
			case GOAL_CONTRIBUTION_SECTION:
				return generateGoalContributionSection;
			case STAKEHOLDERS_DOCUMENT_SECTION:
				return generateStakeholdersDocumentSection;
			case STAKEHOLDERS_DOCUMENT_GOALS_SECTION:
				return generateStakeholdersDocumentAndGoalsSection;
			case ORGANISATIONAL_CONSTRAINT_SECTION:
				return generateOrganisationalConstraintsSection;
			case EVENTS_SECTION:
				return generateEventsSection;
			case INFORMATION_VIEW_CHAPTER:
				return generateInformationViewChapter;
			case INFORMATION_DIAGRAM_SECTION:
				return generateInformationDiagramSection;
			case MODELLING_OWNERSHIP_SECTION:
				return generateModellingOwnershipSection;
			case REPRESENTATION_INFORMATION_SECTION:
				return generateRepresentationInformationSection;
			case COMPOSITION_SECTION:
				return generateCompositionSection;
			case AUTHORISATION_VIEW_CHAPTER:
				return generateAuthorisationViewChapter;
			case AUTHORISATION_DIAGRAM_SECTION:
				return generateAuthorisationDiagramSection;
			case AUTHORISATION_FLOW_SECTION:
				return generateAuthorisationFlowSection;
			case SECURITY_REQUIREMENTS_CHAPTER:
				return generateSecurityRequirementsChapter;
			case ANALYSIS_CHAPTER:
				return generateAnalysisChapter;
			case CONSICTENCY_ANALYSIS_SECTION:
				return generateConsistencyAnalysisSection;
			case SECURITY_ANALYSIS_SECTION:
				return generateSecurityAnalysisSection;
			case RISK_ANALYSIS_SECTION:
				return generateRiskAnalysisSection;
			case APPENDIX_CHAPTER:
				return generateAppendixChapter;
			default:
				return false;
		}
	}

	public void setGenerateChapterSection(int section,boolean value){

		switch (section) {
			case INTRODUCTION_CHAPTER:
				generateIntroductionChapter = value;
			break;
			case SOCIAL_VIEW_CHAPTER:
				generateSocialViewChapter = value;
			break;
			case SOCIAL_DIAGRAM_SECTION:
				generateSocialDiagramSection = value;
			break;
			case STAKEHOLDERS_SECTION:
				generateStakeholdersSection = value;
			break;
			case STAKEHOLDERS_INTERACTIONS_SECTION:
				generateStakeholdersInteractionsSection = value;
			break;
			case GOAL_ANALYSIS_SECTION:
				generateGoalAnalysisSection = value;
			break;
			case GOAL_CONTRIBUTION_SECTION:
				generateGoalContributionSection = value;
			break;
			case STAKEHOLDERS_DOCUMENT_SECTION:
				generateStakeholdersDocumentSection = value;
			break;
			case STAKEHOLDERS_DOCUMENT_GOALS_SECTION:
				generateStakeholdersDocumentAndGoalsSection = value;
			break;
			case ORGANISATIONAL_CONSTRAINT_SECTION:
				generateOrganisationalConstraintsSection = value;
			break;
			case EVENTS_SECTION:
				generateEventsSection = value;
			break;
			case INFORMATION_VIEW_CHAPTER:
				generateInformationViewChapter = value;
			break;
			case INFORMATION_DIAGRAM_SECTION:
				generateInformationDiagramSection = value;
			break;
			case MODELLING_OWNERSHIP_SECTION:
				generateModellingOwnershipSection = value;
			break;
			case REPRESENTATION_INFORMATION_SECTION:
				generateRepresentationInformationSection = value;
			break;
			case COMPOSITION_SECTION:
				generateCompositionSection = value;
			break;
			case AUTHORISATION_VIEW_CHAPTER:
				generateAuthorisationViewChapter = value;
			break;
			case AUTHORISATION_DIAGRAM_SECTION:
				generateAuthorisationDiagramSection = value;
			break;
			case AUTHORISATION_FLOW_SECTION:
				generateAuthorisationFlowSection = value;
			break;
			case SECURITY_REQUIREMENTS_CHAPTER:
				generateSecurityRequirementsChapter = value;
			break;
			case ANALYSIS_CHAPTER:
				generateAnalysisChapter = value;
			break;
			case CONSICTENCY_ANALYSIS_SECTION:
				generateConsistencyAnalysisSection = value;
			break;
			case SECURITY_ANALYSIS_SECTION:
				generateSecurityAnalysisSection = value;
			break;
			case RISK_ANALYSIS_SECTION:
				generateRiskAnalysisSection = value;
			break;
			case APPENDIX_CHAPTER:
				generateAppendixChapter = value;
			break;

		}
	}

	public List<Actor> getActors(int section){
		switch (section) {
			case INTRODUCTION_CHAPTER:
				return actorsIntroductionChapter;
			case SOCIAL_VIEW_CHAPTER:
				return actorsSocialViewChapter;
			case SOCIAL_DIAGRAM_SECTION:
				return actorsSocialDiagramSection;
			case STAKEHOLDERS_SECTION:
				return actorsStakeholdersSection;
			case STAKEHOLDERS_INTERACTIONS_SECTION:
				return actorsStakeholdersInteractionsSection;
			case GOAL_ANALYSIS_SECTION:
				return actorsGoalAnalysisSection;
			case GOAL_CONTRIBUTION_SECTION:
				return actorsGoalContributionSection;
			case STAKEHOLDERS_DOCUMENT_SECTION:
				return actorsStakeholdersDocumentSection;
			case STAKEHOLDERS_DOCUMENT_GOALS_SECTION:
				return actorsStakeholdersDocumentAndGoalsSection;
			case ORGANISATIONAL_CONSTRAINT_SECTION:
				return actorsOrganisationalConstraintsSection;
			case EVENTS_SECTION:
				return actorsEventsSection;
			case INFORMATION_VIEW_CHAPTER:
				return actorsInformationViewChapter;
			case INFORMATION_DIAGRAM_SECTION:
				return actorsInformationDiagramSection;
			case MODELLING_OWNERSHIP_SECTION:
				return actorsModellingOwnershipSection;
			case REPRESENTATION_INFORMATION_SECTION:
				return actorsRepresentationInformationSection;
			case COMPOSITION_SECTION:
				return actorsCompositionSection;
			case AUTHORISATION_VIEW_CHAPTER:
				return actorsAuthorisationViewChapter;
			case AUTHORISATION_DIAGRAM_SECTION:
				return actorsAuthorisationDiagramSection;
			case AUTHORISATION_FLOW_SECTION:
				return actorsAuthorisationFlowSection;
			case SECURITY_REQUIREMENTS_CHAPTER:
				return actorsSecurityRequirementsChapter;
			case ANALYSIS_CHAPTER:
				return actorsAnalysisChapter;
			case CONSICTENCY_ANALYSIS_SECTION:
				return actorsConsistencyAnalysisSection;
			case SECURITY_ANALYSIS_SECTION:
				return actorsSecurityAnalysisSection;
			case RISK_ANALYSIS_SECTION:
				return actorsRiskAnalysisSection;
			case APPENDIX_CHAPTER:
				return actorsAppendixChapter;
			default:
				return new ArrayList<Actor>();
		}
	}

	public void setActors(int section,List<Actor> value){

		switch (section) {
			case INTRODUCTION_CHAPTER:
				actorsIntroductionChapter = value;
			break;
			case SOCIAL_VIEW_CHAPTER:
				actorsSocialViewChapter = value;
			break;
			case SOCIAL_DIAGRAM_SECTION:
				actorsSocialDiagramSection = value;
			break;
			case STAKEHOLDERS_SECTION:
				actorsStakeholdersSection = value;
			break;
			case STAKEHOLDERS_INTERACTIONS_SECTION:
				actorsStakeholdersInteractionsSection = value;
			break;
			case GOAL_ANALYSIS_SECTION:
				actorsGoalAnalysisSection = value;
			break;
			case GOAL_CONTRIBUTION_SECTION:
				actorsGoalContributionSection = value;
			break;
			case STAKEHOLDERS_DOCUMENT_SECTION:
				actorsStakeholdersDocumentSection = value;
			break;
			case STAKEHOLDERS_DOCUMENT_GOALS_SECTION:
				actorsStakeholdersDocumentAndGoalsSection = value;
			break;
			case ORGANISATIONAL_CONSTRAINT_SECTION:
				actorsOrganisationalConstraintsSection = value;
			break;
			case EVENTS_SECTION:
				actorsEventsSection = value;
			break;
			case INFORMATION_VIEW_CHAPTER:
				actorsInformationViewChapter = value;
			break;
			case INFORMATION_DIAGRAM_SECTION:
				actorsInformationDiagramSection = value;
			break;
			case MODELLING_OWNERSHIP_SECTION:
				actorsModellingOwnershipSection = value;
			break;
			case REPRESENTATION_INFORMATION_SECTION:
				actorsRepresentationInformationSection = value;
			break;
			case COMPOSITION_SECTION:
				actorsCompositionSection = value;
			break;
			case AUTHORISATION_VIEW_CHAPTER:
				actorsAuthorisationViewChapter = value;
			break;
			case AUTHORISATION_DIAGRAM_SECTION:
				actorsAuthorisationDiagramSection = value;
			break;
			case AUTHORISATION_FLOW_SECTION:
				actorsAuthorisationFlowSection = value;
			break;
			case SECURITY_REQUIREMENTS_CHAPTER:
				actorsSecurityRequirementsChapter = value;
			break;
			case ANALYSIS_CHAPTER:
				actorsAnalysisChapter = value;
			break;
			case CONSICTENCY_ANALYSIS_SECTION:
				actorsConsistencyAnalysisSection = value;
			break;
			case SECURITY_ANALYSIS_SECTION:
				actorsSecurityAnalysisSection = value;
			break;
			case RISK_ANALYSIS_SECTION:
				actorsRiskAnalysisSection = value;
			break;
			case APPENDIX_CHAPTER:
				actorsAppendixChapter = value;
			break;
		}
	}


}
