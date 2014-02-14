/*
* WizardData.java
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
package eu.aniketos.wp1.ststool.report.wizard;

import eu.aniketos.wp1.ststool.report.pdfgenerator.ReportValueFactory;


public class WizardData {

	private String		reportTitle			= "example";
	private String		reportAuthor		= "";
	private String		reportIstitution	= "";

	private boolean	pdfOutput			= true;
	private String		pdfPath				= "";
	private boolean	rtfOutput			= false;
	private String		rtfPath				= "";

	private Node		selectionViewTree	= getTreeInput();



	public String getReportTitle(){

		return reportTitle;
	}



	public String getReportAuthor(){

		return reportAuthor;
	}



	public String getReportIstitution(){

		return reportIstitution;
	}



	public boolean isPdfOutput(){

		return pdfOutput;
	}



	public String getPdfPath(){

		return pdfPath;
	}



	public boolean isRtfOutput(){

		return rtfOutput;
	}



	public String getRtfPath(){

		return rtfPath;
	}



	public Node getSelectionViewTree(){

		return selectionViewTree;
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



	public void setPdfOutput(boolean pdfOutput){

		this.pdfOutput = pdfOutput;
	}



	public void setPdfPath(String pdfPath){

		this.pdfPath = pdfPath;
	}



	public void setRtfOutput(boolean rtfOutput){

		this.rtfOutput = rtfOutput;
	}



	public void setRtfPath(String rtfPath){

		this.rtfPath = rtfPath;
	}



	public void setSelectionViewTree(Node selectionViewTree){

		this.selectionViewTree = selectionViewTree;
	}



	private Node getTreeInput(){

		Node root = new Node(-1);

		Node elem1 = new Node(ReportValueFactory.INTRODUCTION_CHAPTER, "Introduction");
		elem1.setModificable(false);
		Node elem2 = new Node(ReportValueFactory.SOCIAL_VIEW_CHAPTER, "Social View");
		Node elem2a = new Node(ReportValueFactory.SOCIAL_DIAGRAM_SECTION, "Social View Diagram");
		Node elem2b = new Node(ReportValueFactory.STAKEHOLDERS_SECTION, "Stakeholders");
		Node elem2c = new Node(ReportValueFactory.STAKEHOLDERS_DOCUMENT_SECTION, "Stakeholders' document");
		Node elem2d = new Node(ReportValueFactory.STAKEHOLDERS_DOCUMENT_GOALS_SECTION, "Stakeholders' document and goals");
		Node elem2e = new Node(ReportValueFactory.GOAL_ANALYSIS_SECTION, "Goal Analysis");
		Node elem2f = new Node(ReportValueFactory.GOAL_CONTRIBUTION_SECTION, "Contributions");
		Node elem2g = new Node(ReportValueFactory.STAKEHOLDERS_INTERACTIONS_SECTION, "Stakeholders Interactions");
		Node elem2h = new Node(ReportValueFactory.ORGANISATIONAL_CONSTRAINT_SECTION, "Organisational Constraints");
		Node elem2i = new Node(ReportValueFactory.EVENTS_SECTION, "Events");
		Node elem3 = new Node(ReportValueFactory.INFORMATION_VIEW_CHAPTER, "Information View");
		Node elem3a = new Node(ReportValueFactory.INFORMATION_DIAGRAM_SECTION, "Information View Diagram");
		Node elem3b = new Node(ReportValueFactory.MODELLING_OWNERSHIP_SECTION, "Modelling Ownership");
		Node elem3c = new Node(ReportValueFactory.REPRESENTATION_INFORMATION_SECTION, "Representation of Information");
		Node elem3d = new Node(ReportValueFactory.COMPOSITION_SECTION, "Compositions");
		Node elem4 = new Node(ReportValueFactory.AUTHORISATION_VIEW_CHAPTER, "Authorisation View");
		Node elem4a = new Node(ReportValueFactory.AUTHORISATION_DIAGRAM_SECTION, "Authorisation View Diagram");
		Node elem4b = new Node(ReportValueFactory.AUTHORISATION_FLOW_SECTION, "Authorisation Flow");
		Node elem5 = new Node(ReportValueFactory.SECURITY_REQUIREMENTS_CHAPTER, "Security Requirements");
		elem5.setModificable(false);
		Node elem6 = new Node(ReportValueFactory.ANALYSIS_CHAPTER, "Analysis");
		Node elem6a = new Node(ReportValueFactory.CONSICTENCY_ANALYSIS_SECTION, "Consistency Analysis");
		Node elem6b = new Node(ReportValueFactory.SECURITY_ANALYSIS_SECTION, "Security Analysis");
		Node elem6c = new Node(ReportValueFactory.RISK_ANALYSIS_SECTION, "Risk Analysis");
		Node elem7 = new Node(ReportValueFactory.APPENDIX_CHAPTER, "Appendix");
		elem7.setModificable(false);

		root.addChildren(elem1);
		root.addChildren(elem2);
		elem2.addChildren(elem2a);
		elem2.addChildren(elem2b);
		elem2.addChildren(elem2c);
		elem2.addChildren(elem2d);
		elem2.addChildren(elem2e);
		elem2.addChildren(elem2f);
		elem2.addChildren(elem2g);
		elem2.addChildren(elem2h);
		elem2.addChildren(elem2i);
		root.addChildren(elem3);
		elem3.addChildren(elem3a);
		elem3.addChildren(elem3b);
		elem3.addChildren(elem3c);
		elem3.addChildren(elem3d);
		root.addChildren(elem4);
		elem4.addChildren(elem4a);
		elem4.addChildren(elem4b);
		root.addChildren(elem5);
		root.addChildren(elem6);
		elem6.addChildren(elem6a);
		elem6.addChildren(elem6b);
		elem6.addChildren(elem6c);
		root.addChildren(elem7);

		return root;
	}
}
