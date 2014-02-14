/*
* AbstractContentFactory.java
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import eu.aniketos.wp1.ststool.*;
import eu.aniketos.wp1.ststool.analysis.results.IResult;
import eu.aniketos.wp1.ststool.analysis.results.IResult.ResultType;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask;
import eu.aniketos.wp1.ststool.analysis.util.analyser.TaskAdapter;
import eu.aniketos.wp1.ststool.analysis.util.analyser.AbstractTask.TaskResult;


public abstract class AbstractContentFactory {

	private final ReportValueFactory		vf;
	protected final FigureTableCounter	ftc									= new FigureTableCounter();

	protected Image							footerImage							= null;
	protected ReportScreenshotProvider	rsp									= null;
	protected URL								footerImageURL						= null;
	protected Rectangle						footerImageBound					= null;

	protected boolean							appendixASocialDiagram			= false;



	protected boolean							appendixAAuthorisationDiagram	= false;
	protected boolean							appendixAInformationDiagram	= false;

	public AbstractContentFactory(ReportValueFactory rvf, ReportScreenshotProvider rsp) {

		if (rvf == null) throw new RuntimeException("Invalid Report Value Factory");
		if (rvf == null) throw new RuntimeException("Invalid Report Screenshot Provider");
		this.vf = rvf;
		this.rsp = rsp;
		try {
			footerImage = GenUtils.getImage("/resources/reportimage/report_footer_logo.gif");
			fitImageInArea(footerImage, -1, 45, false);
			//footerImage.scaleAbsolute(139, 45);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Paragraph createParagraph(String s){

		Paragraph result = getDefaultParagraph();
		StringBuilder sb = new StringBuilder();
		Font f = CONTENT_NORMAL;
		boolean customStyle = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!customStyle) {
				if (c == '%') {
					if (i + 1 < s.length()) {
						i++;
						c = s.charAt(i);
						if (c == 'b') {//bold
							f = CONTENT_BOLD;
							customStyle = true;
						} else if (c == 'i') {//italic
							f = CONTENT_ITALIC;
							customStyle = true;
						} else if (c == 'u') {//underlined
							f = CONTENT_UNDERLINE;
							customStyle = true;
						} else if (c == 'e') {//emphasized (bold/italic)
							f = CONTENT_BOLDITALIC;
							customStyle = true;
						} else {
							sb.append("%" + c);
						}
						if (customStyle) {
							result.add(new Chunk(sb.toString()/*.replace(" ","\u00a0")*/, CONTENT_NORMAL));
							sb = new StringBuilder();
						}
					} else {
						sb.append(c);
					}
				} else {
					sb.append(c);
				}
			} else {
				if (c == '%') {
					result.add(new Chunk(sb.toString()/*.replace(" ","\u00a0")*/, f));
					sb = new StringBuilder();
					customStyle = false;
				} else {
					sb.append(c);
				}
			}
		}
		if (sb.length() > 0 && customStyle) {
			result.add(new Chunk(sb.toString()/*.replace(" ","\u00a0")*/, f));
		} else if (sb.length() > 0 && !customStyle) {
			result.add(new Chunk(sb.toString()/*.replace(" ","\u00a0")*/, CONTENT_NORMAL));
		}
		return result;
	}

	protected Paragraph getDefaultParagraph(){

		Paragraph result = new Paragraph();
		result.setFont(CONTENT_NORMAL);
		result.setAlignment(Element.ALIGN_JUSTIFIED);
		result.setSpacingAfter(result.getFont().getSize() * 1.5f);
		return result;
	}

	protected Phrase getDefaultPhrase(){

		Paragraph result = new Paragraph();
		result.setFont(CONTENT_NORMAL);
		result.setAlignment(Element.ALIGN_JUSTIFIED);
		result.setSpacingAfter(result.getFont().getSize() * 1.5f);
		return result;
	}

	protected Paragraph getChapterTitleParagraph(String title){

		Paragraph result = new Paragraph(title, CHAPTER);
		result.setSpacingAfter(15);
		return result;
	}

	protected Paragraph getSectionTitleParagraph(String title){

		Paragraph result = new Paragraph(title, SECTION);
		result.setSpacingAfter(15);
		result.setSpacingBefore(10);
		return result;
	}

	public Phrase getPageNumberPhrase(int pageNumber){

		Phrase p = new Phrase(Integer.toString(pageNumber), PAGE_NUMBER);
		return p;
	}

	public Phrase getIndexPageNumberPhrase(int pageNumber){

		Phrase p = new Phrase(Integer.toString(pageNumber), PAGE_NUMBER);
		return p;
	}

	protected Paragraph decorateImage(Image i,String caption){

		Paragraph p = new Paragraph("", IMAGE_CAPTION);
		p.setAlignment(Element.ALIGN_CENTER);
		i.setAlignment(Element.ALIGN_CENTER);
		i.setBorder(Image.BOX);
		i.setBorderWidth(3f);
		i.setBorderColor(new BaseColor(52, 90, 138));
		p.add(i);

		Paragraph captionP = new Paragraph(caption, IMAGE_CAPTION);
		captionP.setAlignment(Element.ALIGN_CENTER);
		//p.add(Chunk.NEWLINE);
		p.add(captionP);


		//p.setKeepTogether(true);
		return p;
	}

	/**
	 * 
	 * @param img
	 *           The Image to resize
	 * @param maxWidht
	 *           maximum imageWitdh
	 * @param maxHeight
	 *           maximum imageHeight
	 * @param enlarge
	 *           true if the image should be enlarged if is smaller than maxWidth && maxHeigt
	 * @return the percentual reduction scale factor (100 means no reduction)
	 */
	protected float fitImageInArea(Image img,float maxWidht,float maxHeight,boolean enlarge){

		if (maxHeight <= 0 && maxWidht <= 0) throw new RuntimeException("Invalid value");

		float imgWidth = img.getPlainWidth();
		float imgHeight = img.getPlainHeight();

		float reductionScale = 100;

		if (maxHeight <= 0) {
			reductionScale = (maxWidht / imgWidth) * 100;
		} else if (maxWidht <= 0) {
			reductionScale = (maxHeight / imgHeight) * 100;
		} else {
			float widthScale = maxWidht / imgWidth;
			float heightScale = maxHeight / imgHeight;

			reductionScale = (Math.min(widthScale, heightScale)) * 100;
		}
		if (reductionScale > 100 && !enlarge) reductionScale = 100;

		if (reductionScale != 100) {
			img.scalePercent(reductionScale);
		}
		return reductionScale;
	}

	protected float getMaximumReductionScale(){

		return 50;
	}

	protected Font getIndexChapterFont(){

		return INDEX_CHAPTER;
	}

	protected Font getIndexSectionFont(){

		return INDEX_SECTION;
	}

	protected Font getIndexNumberFont(){

		return INDEX_NUMBER;
	}

	protected Image getFooterImage(){

		return footerImage;
	}

	/*protected List<String> buildSecNeedString(Delegation d,boolean requires,Character textStyle){

	List<String> sn = new ArrayList<String>();
	String pre = "";
	String post = "";
	if (textStyle != null) {
		pre = "%" + textStyle;
		post = "%";
	}
	//if(outgoing){
	if (requires) {
		if (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION || d.getRepudiationType() == RepudiationType.DELEGATEEE_REPUDIATION) {
			sn.add(pre + "Non-Repudiation-of-Acceptance" + post);
		}

		if (d.getTimesTransferable() == 0) sn.add(pre + "No-Delegation" + post);
		if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
			switch (d.getRedundancyType()) {
				case FALLBACK_SINGLE:
					sn.add(pre + "Fallback-Single-Redundancy" + post);
				break;
				case FALLBACK_MULTI:
					sn.add(pre + "Fallback-Multi-Redundancy" + post);
				break;
				case TRUE_SINGLE:
					sn.add(pre + "True-Single-Redundancy" + post);
				break;
				case TRUE_MULTI:
					sn.add(pre + "True-Multi-Redundancy" + post);
				break;
			}
		}
	} else {
		if (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION || d.getRepudiationType() == RepudiationType.DELEGATOR_REPUDIATION) {
			sn.add(pre + "Non-Repudiation-of-Delegation" + post);
		}
	}
	return sn;
	}

	protected List<String> buildParamterizedSecNeedString(Delegation d,Character textStyle){
	List<String> sn = new ArrayList<String>();
	String pre = "";
	String post = "";
	if (textStyle != null) {
		pre = "%" + textStyle;
		post = "%";
	}
	if (d.isAvailability()) {
		sn.add(pre + "Availability" + post + " of " + pre + d.getAvailabilityValue() + "%" + post);
	}
	if (d.isTrustworthiness()) {
		sn.add(pre + "Trustworthiness" + post + " of " + pre + d.getTrustworthinessValue() + "" + post);
	}
	return sn;
	}*/

	
	protected List<String> buildProvisionSecNeedList(Provision p){
		List<String> sn = new ArrayList<String>();
		String pre = "";
		String post = "";
		if (p.isIntegrity()) {
			sn.add(pre + "Integrity" + post);
		}
		if (p.isAvailability()) {
			sn.add(pre + "Availability (" + p.getAvailabilityValue() + "%)" + post);
		}
		if (p.isConfidentiality()) {
			sn.add(pre + "Confidentiality" + post);
		}
		return sn;
	}
	
	
	protected List<String> buildDelegationSecNeedList(Delegation d){

		List<String> sn = new ArrayList<String>();
		String pre = "";
		String post = "";
		if (d.getRepudiationType() != RepudiationType.NO_REPUDIATION) {
			switch (d.getRepudiationType()) {
				case DELEGATEEE_REPUDIATION:
					sn.add(pre + "Non-Repudiation-of-Acceptance" + post);
				break;
				case DELEGATOR_REPUDIATION:
					sn.add(pre + "Non-Repudiation-of-Delegation" + post);
				break;
				case DUAL_REPUDIATION:
					sn.add(pre + "Non-Repudiation-of-Acceptance/Delegation" + post);
				break;
			}
		}
		if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
			switch (d.getRedundancyType()) {
				case FALLBACK_SINGLE:
					sn.add(pre + "Fallback-Single-Redundancy" + post);
				break;
				case FALLBACK_MULTI:
					sn.add(pre + "Fallback-Multi-Redundancy" + post);
				break;
				case TRUE_SINGLE:
					sn.add(pre + "True-Single-Redundancy" + post);
				break;
				case TRUE_MULTI:
					sn.add(pre + "True-Multi-Redundancy" + post);
				break;
			}
		}
		if (d.getTimesTransferable() == 0) sn.add(pre + "No-Delegation" + post);
		if (d.isAvailability()) {
			sn.add(pre + "Availability (" + d.getAvailabilityValue() + "%)" + post);
		}
		if (d.isTrustworthiness()) {
			sn.add(pre + "Trustworthiness (" + d.getTrustworthinessValue() + ")" + post);
		}
		return sn;
	}

	


	protected String separateListOfString(List<String> list){

		return separateListOfString(list, ", ", " and ");
	}

	protected String separateListOfString(List<String> list,String sep,String lastSep){

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1 && i > 0)
				sb.append(lastSep);
			else if (i > 0) sb.append(sep);
			sb.append(list.get(i));
		}
		return sb.toString();
	}

	protected PdfPTable createTable(List<String[]> headers){

		if (headers == null || headers.size() < 1) throw new RuntimeException("Table with 0 column");

		PdfPTable table = new PdfPTable(headers.size());
		for (String[] header : headers) {
			table.addCell(getHeaderCell(header));
		}
		if (headers.size() > 2) {
			table.setWidthPercentage(100);
		}
		//table.setHeaderRows(1);
		table.setKeepTogether(!splitTable());

		return table;
	}

	protected void addTableCaption(PdfPTable table,String caption){

		PdfPCell cell = getContentCell();
		cell.setColspan(table.getNumberOfColumns());
		table.addCell(cell);
		PdfPCell captionCell = new PdfPCell(new Phrase(caption, TABLE_CAPTION));
		captionCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		captionCell.setPaddingBottom(5);
		captionCell.setBorder(Rectangle.TOP);
		captionCell.setBorderWidthTop(1.5f);
		captionCell.setPaddingBottom(7);
		captionCell.setPaddingTop(2);
		captionCell.setColspan(table.getNumberOfColumns());
		table.addCell(captionCell);
	}

	protected PdfPCell getHeaderCell(String[] header){

		Phrase p = new Phrase();
		p.setFont(TABLE_HEADER);
		for (String s : header) {
			p.add(new Chunk(s));
			p.add(Chunk.NEWLINE);
		}
		PdfPCell cell = new PdfPCell(p);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
		cell.setBorderWidthTop(1.5f);
		cell.setBorderWidthBottom(1.5f);
		cell.setPaddingBottom(7);
		cell.setPaddingTop(2);
		return cell;
	}

	protected PdfPCell getContentCell(){

		PdfPCell cell = new PdfPCell();
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setPaddingBottom(5);
		cell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
		return cell;
	}

	protected PdfPCell getContentCell(String phrase){
		return getContentCell(phrase, TABLE_CONTENT);
	}

	protected PdfPCell getContentCell(String phrase,Font font){

		PdfPCell cell = getContentCell();
		cell.setPhrase(new Phrase(phrase, font));
		return cell;
	}

	protected PdfPCell getContentCell(String[] phrases){
		return getContentCell(phrases,TABLE_CONTENT);
	}
	
	protected PdfPCell getContentCell(String[] phrases,Font font){

		PdfPCell cell = getContentCell();
		Phrase p = new Phrase(phrases[0], font);
		for (int i = 1; i < phrases.length; i++) {
			p.add(Chunk.NEWLINE);
			p.add(new Phrase(phrases[i], font));
		}
		cell.setPhrase(p);
		return cell;
	}

	protected Paragraph listParagraphs(List<Paragraph> paragraphs){

		Paragraph pList = getDefaultParagraph();
		com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		list.setListSymbol("\u2022" + " ");
		list.setSymbolIndent(30f);
		pList.add(list);
		pList.setSpacingBefore(0f);
		pList.setSpacingAfter(0f);

		for (Paragraph p : paragraphs) {
			list.add(new ListItem(p));
		}
		return pList;
	}
	



	protected String getOperations(Authorisation a){

		List<String> s = new ArrayList<String>();
		if (a.isUsage()) s.add("%iuse%");
		if (a.isModification()) s.add("%imodify%");
		if (a.isProduce()) s.add("%iproduce%");
		if (a.isDistribution()) s.add("%idistribute%");

		return separateListOfString(s);
	}

	protected String getOperationsTable(Authorisation a){

		List<String> s = new ArrayList<String>();
		if (a.isUsage()) s.add("U");
		if (a.isModification()) s.add("M");
		if (a.isProduce()) s.add("P");
		if (a.isDistribution()) s.add("D");

		return separateListOfString(s, ", ", ", ");
	}

	protected boolean isValidAuth(Authorisation a){

		return a.getResources().size() > 0 && (a.isUsage() || a.isModification() || a.isProduce() || a.isDistribution());
	}

	protected String typeActor(Actor a){

		if (a instanceof Agent)
			return "agent";
		else
			return "role";
	}


	/*------------------------------------VARIABLES----------------------------------*/;

	protected void setAppendixASocialDiagram(boolean appendixSocialDiagram){

		this.appendixASocialDiagram = appendixSocialDiagram;
	}

	protected void setAppendixAInformationDiagram(boolean appendixInformationDiagram){

		this.appendixAInformationDiagram = appendixInformationDiagram;
	}

	protected void setAppendixAuthorisationDiagram(boolean appendixAuthorisationDiagram){

		this.appendixAAuthorisationDiagram = appendixAuthorisationDiagram;
	}



	protected boolean splitTable(){

		return false;
	}



	protected String getProjectName(){

		return vf.getReportTitle();
	}

	protected String getDocumentTitle(){

		return vf.getDocumentTitle();
	}

	protected String getReportIstitution(){

		return vf.getReportIstitution();
	}

	protected String getReportAuthor(){

		return vf.getReportAuthor();
	}

	protected String getDate(){

		return vf.getDate();
	}

	/*----------------------------------------------------*/
	protected boolean genrateIntroductionChapter(){

		return vf.generateChapterSection(ReportValueFactory.INTRODUCTION_CHAPTER);
	}

	/*----------------------------------------------------*/
	protected boolean generateSocialViewChapter(){

		return vf.generateChapterSection(ReportValueFactory.SOCIAL_VIEW_CHAPTER);
	}

	protected boolean generateSocialViewDiagramSection(){

		return vf.generateChapterSection(ReportValueFactory.SOCIAL_DIAGRAM_SECTION);
	}

	protected boolean generateStakeholdersSection(){

		return vf.generateChapterSection(ReportValueFactory.STAKEHOLDERS_SECTION);
	}

	protected boolean generateStakeholdersInteractionsSection(){

		return vf.generateChapterSection(ReportValueFactory.STAKEHOLDERS_INTERACTIONS_SECTION);
	}

	protected boolean generateGoalDelegationsSection(){

		return generateStakeholdersInteractionsSection();
	}

	protected boolean generateDocumentProvisionsSection(){

		return generateStakeholdersInteractionsSection();
	}

	protected boolean generateGoalAnalysisSection(){

		return vf.generateChapterSection(ReportValueFactory.GOAL_ANALYSIS_SECTION);
	}

	protected boolean generateGoalContributionSection(){

		return vf.generateChapterSection(ReportValueFactory.GOAL_CONTRIBUTION_SECTION);
	}

	protected boolean generateStakeholdersDocumentSection(){

		return vf.generateChapterSection(ReportValueFactory.STAKEHOLDERS_DOCUMENT_SECTION);
	}

	protected boolean generateStakeholdersDocumentAndGoalsSection(){

		return vf.generateChapterSection(ReportValueFactory.STAKEHOLDERS_DOCUMENT_GOALS_SECTION);
	}

	protected boolean generateOrganisationalConstraintsSection(){

		return vf.generateChapterSection(ReportValueFactory.ORGANISATIONAL_CONSTRAINT_SECTION);
	}
	
	protected boolean generateEventSection(){

		return vf.generateChapterSection(ReportValueFactory.EVENTS_SECTION);
	}

	/*----------------------------------------------------*/
	protected boolean generateInformationViewChapter(){

		return vf.generateChapterSection(ReportValueFactory.INFORMATION_VIEW_CHAPTER);
	}

	protected boolean generateInformationViewDiagramSection(){

		return vf.generateChapterSection(ReportValueFactory.INFORMATION_DIAGRAM_SECTION);
	}

	protected boolean generateModellingOwnershipSection(){

		return vf.generateChapterSection(ReportValueFactory.MODELLING_OWNERSHIP_SECTION);
	}

	protected boolean generateRepresentationInformationSection(){

		return vf.generateChapterSection(ReportValueFactory.REPRESENTATION_INFORMATION_SECTION);
	}

	protected boolean generateCompositionSection(){

		return vf.generateChapterSection(ReportValueFactory.COMPOSITION_SECTION);
	}

	/*----------------------------------------------------*/
	protected boolean generateAuthorisationViewChapter(){

		return vf.generateChapterSection(ReportValueFactory.AUTHORISATION_VIEW_CHAPTER);
	}

	protected boolean generateAuthorisationViewDiagramSection(){

		return vf.generateChapterSection(ReportValueFactory.AUTHORISATION_DIAGRAM_SECTION);
	}

	protected boolean generateAuthorisationFlowSection(){

		return vf.generateChapterSection(ReportValueFactory.AUTHORISATION_FLOW_SECTION);
	}

	/*----------------------------------------------------*/
	protected boolean generateSecurityRequirementsChapter(){

		return vf.generateChapterSection(ReportValueFactory.SECURITY_REQUIREMENTS_CHAPTER);
	}


	protected boolean generateAnalysisChapter(){

		return vf.generateChapterSection(ReportValueFactory.ANALYSIS_CHAPTER) && (generateConsistencyAnalysisSection() || generateSecurityAnalysisSection() || generateRiskAnalysisSection());
	}

	protected boolean generateConsistencyAnalysisSection(){
		return vf.generateChapterSection(ReportValueFactory.CONSICTENCY_ANALYSIS_SECTION);
	}

	protected boolean generateSecurityAnalysisSection(){

		return vf.generateChapterSection(ReportValueFactory.SECURITY_ANALYSIS_SECTION);
	}

	protected boolean generateRiskAnalysisSection(){

		return vf.generateChapterSection(ReportValueFactory.RISK_ANALYSIS_SECTION);
	}

	//------------------------------------------------------------------------------


	protected boolean generateAppendixChapter(){

		return generateAppendixAChapter() || generateAppendixBChapter() || generateAppendixCChapter();
	}

	protected boolean generateAppendixAChapter(){

		return vf.generateChapterSection(ReportValueFactory.APPENDIX_CHAPTER) && (appendixASocialDiagram || appendixAInformationDiagram || appendixAAuthorisationDiagram);
	}

	protected boolean needSocialDiagramInAppendixA(){

		return generateAppendixAChapter() && appendixASocialDiagram;
	}

	protected boolean needInformationDiagramInAppendixA(){

		return generateAppendixAChapter() && appendixAInformationDiagram;
	}

	protected boolean needAuthorisationDiagramInAppendixA(){

		return generateAppendixAChapter() && appendixAAuthorisationDiagram;
	}

	protected boolean generateAppendixBChapter(){

		return vf.generateChapterSection(ReportValueFactory.APPENDIX_CHAPTER) && generateConsistencyAnalysisSection();
	}

	protected boolean generateAppendixCChapter(){

		return vf.generateChapterSection(ReportValueFactory.APPENDIX_CHAPTER) && generateSecurityAnalysisSection();
	}

	protected boolean generateAppendixDChapter(){

		return vf.generateChapterSection(ReportValueFactory.APPENDIX_CHAPTER) && generateRiskAnalysisSection();
	}

	/*----------------------------------------------------*/

	protected StsToolDiagram getDiagram(){

		return vf.getDiagram();
	}

	
	/*----------------------------------------------------*/


	/*----------------------------------------------------*/


	/*--------------------------------------------------------*/
	protected List<Actor> getStakeholderActors(){

		return vf.getActors(ReportValueFactory.STAKEHOLDERS_SECTION);
	}

	protected List<Actor> getGoalDelegationsActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.STAKEHOLDERS_INTERACTIONS_SECTION);
	}

	protected List<Actor> getDocumentProvisionsActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.STAKEHOLDERS_INTERACTIONS_SECTION);
	}

	protected List<Actor> getGoalContributionActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.GOAL_CONTRIBUTION_SECTION);
	}

	protected List<Actor> getGoalAnalysisActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.GOAL_ANALYSIS_SECTION);
	}

	protected List<Actor> getStakeholdersDocumentActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.STAKEHOLDERS_DOCUMENT_SECTION);
	}

	protected List<Actor> getStakeholdersDocumentAndGoalsActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.STAKEHOLDERS_DOCUMENT_GOALS_SECTION);
	}

	protected List<Actor> getOrganisationalConstraintsActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.ORGANISATIONAL_CONSTRAINT_SECTION);
	}

	/*-------------------------------------------------------------*/
	protected List<Actor> getModellingOwnershipActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.MODELLING_OWNERSHIP_SECTION);
	}

	protected List<Actor> getRepresentationInformationActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.REPRESENTATION_INFORMATION_SECTION);
	}

	protected List<Actor> getCompositionActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.COMPOSITION_SECTION);
	}

	/*-------------------------------------------------------------*/
	protected List<Actor> getAuthorisationFlowActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.AUTHORISATION_FLOW_SECTION);
	}

	/*-------------------------------------------------------------*/
	protected List<Actor> getSecurityRequirementsActors(){

		//return new ArrayList<Actor>();
		return vf.getActors(ReportValueFactory.SECURITY_REQUIREMENTS_CHAPTER);
	}

	/*-------------------------------------------------------------*/

	protected String socialDiagRef(){

		return socialDiagRef(" (", ")");
	}

	protected String socialDiagRef(String pre,String post){

		if (generateSocialViewDiagramSection())
			return pre + ftc.getFigure(FigureConstant.SOCIAL_DIAGRAM) + post;
		else
			return "";
	}

	protected String infDiagRef(){

		if (generateInformationViewDiagramSection())
			return " (" + ftc.getFigure(FigureConstant.INFORMATION_DIAGRAM) + ")";
		else
			return "";
	}

	protected String autDiagRef(){

		if (generateAuthorisationViewDiagramSection())
			return " (" + ftc.getFigure(FigureConstant.AUTH_DIAGRAM) + ")";
		else
			return "";
	}

	/*abstract protected class AnalysisDescriptorOLD implements ITaskListener {
	
		private List<Paragraph>	paragraphs	= new ArrayList<Paragraph>();
	
		@Override
		public final void taskCompleted(AbstractTask task,TaskResult taskResult,List<IResult> analysisResults){
			addResults(task, analysisResults);
		}
	
		@Override
		public final void taskInterrupted(AbstractTask task,InterruptionType interruptionType,Throwable ex){
			String interruptionText = "";
			switch (interruptionType) {
				case EXCEPTION:
					interruptionText = "The analysis has encountred an error while performing the evaluation of this task, and couldn't complete it.";
				break;
				case TIMEOUT:
					interruptionText = "This task was taking too much time to get evaluated, and have been interrupted";
				break;
			}
			addResultTextError(task, interruptionText);
		}
	
		@Override
		public final void taskSkipped(AbstractTask task){
			addResultTextError(task, "This task has been skipped.");
		}
	
		@Override
		public final void taskStarted(AbstractTask task){
		}
	
		protected abstract String getDescriptionForTask(AbstractTask task);
	
		public List<Paragraph> getAnalysisParagraphs(){
			return paragraphs;
		}
	
		private Paragraph writeIntro(AbstractTask task){
			Paragraph p = createParagraph("%b" + task.getName() + "% ");
			p.setKeepTogether(true);
			String desc = getDescriptionForTask(task);
			if (desc != null) {
				p.add(Chunk.NEWLINE);
	
				for (String s : desc.split("\n")) {
					Paragraph dPar = createParagraph(s);
					dPar.setSpacingAfter(0);
					p.add(dPar);
				}
				Paragraph par = createParagraph("%iResults of the consistency analysis:% ");
				par.setSpacingAfter(0);
				par.setSpacingBefore(5);
				p.add(par);
			}
			return p;
		}
	
		private void addResultTextError(AbstractTask task,String text){
			addResultText(task, text, CONTENT_ANALYSYS_ERROR);
		}
	
		private void addResultTextOk(AbstractTask task,String text){
			addResultText(task, text, CONTENT_ANALYSYS_RESULT);
		}
	
		private void addResultText(AbstractTask task,String text,Font f){
			Paragraph p = writeIntro(task);
			Paragraph result = new Paragraph(new Chunk(text, f));
			result.setAlignment(Element.ALIGN_JUSTIFIED);
			result.setSpacingBefore(p.getFont().getSize() / 2);
			result.setIndentationLeft(10);
			p.add(result);
			paragraphs.add(p);
		}
	
	
		private void addResults(AbstractTask task,List<IResult> results){
			Paragraph p = writeIntro(task);
			Collections.sort(results);
	
			if (results.size() > 0) {
				PdfPTable table = createResultsTable();
				for (IResult r : results) {
					addResutlToTable(table, r);
				}
				p.add(table);
				paragraphs.add(p);
			} else {
				addResultTextOk(task, "No errors have been found.");
			}
		}
	
		private PdfPTable createResultsTable(){
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Type" });
			headers.add(new String[] { "Text" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);
			table.setSpacingBefore(10);
			try {
				table.setWidths(new float[] { 1, 4.5f, 5.5f });
			} catch (DocumentException e) {
				e.printStackTrace();
			}
	
			return table;
		}
	
		private void addResutlToTable(PdfPTable table,IResult result){
			if (result.getType() == ResultType.ERROR || result.getType() == ResultType.WARNING) {
	
				PdfPCell cell1 = null;
				switch (result.getType()) {
					case ERROR:
						cell1 = getContentCell("ERROR", TABLE_CONTENT_RED);
					break;
					case WARNING:
						cell1 = getContentCell("WARN.", TABLE_CONTENT_ORANGE);
					break;
				}
				PdfPCell cell2 = getContentCell(result.getText());
				PdfPCell cell3 = getContentCell(result.getDescription(), TABLE_CONTENT_SMALL);
	
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
	
			}
		}
	}*/

	protected class AnalysisDescriptor extends TaskAdapter {

		private PdfPTable		table;
		private boolean		tableDataInserted	= false;

		private final String	analysisName;
		private final String	tableConstant;


		public AnalysisDescriptor(String analysisName, String tableConstant) {
			this.analysisName = analysisName;
			this.tableConstant = tableConstant;
			table = createResultsTable();
		}

		@Override
		public final void taskCompleted(AbstractTask task,TaskResult taskResult,List<IResult> analysisResults){
			addResults(task, analysisResults);
		}

		@Override
		public final void taskInterrupted(AbstractTask task,InterruptionType interruptionType,Throwable ex){
			switch (interruptionType) {
				case EXCEPTION:
					addResutlToTable("EXCEPT.", TABLE_CONTENT_RED, task.getName(), "Excpetion occoured during evaluation", "The analysis has encountred an error while performing the evaluation of this task, and couldn't complete it.");
				break;
				case TIMEOUT:
					addResutlToTable("TIMEO.", TABLE_CONTENT_RED, task.getName(), "Time Out occoured during evaluation", "This task was taking too much time to get evaluated, and have been interrupted");
				break;
			}
		}

		@Override
		public final void taskSkipped(AbstractTask task){
			addResutlToTable("SKIPPED", TABLE_CONTENT_GRAY, task.getName(), "Task Skipped", "This task has been skipped.");
		}

		private void addResults(AbstractTask task,List<IResult> results){
			if (results.size() > 0) {
				for (IResult r : results) {
					addResutlToTable(task, r);
				}
			}
		}

		private PdfPTable createResultsTable(){
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Type" });
			headers.add(new String[] { "Category" });
			headers.add(new String[] { "Text" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);
			table.setKeepTogether(false);
			table.setSpacingBefore(10);
			try {
				table.setWidths(new float[] { 1, 2f, 3f, 4.0f });
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			return table;
		}

		private void addResutlToTable(AbstractTask task,IResult result){

			if (result.getType() == ResultType.ERROR || result.getType() == ResultType.WARNING) {
				switch (result.getType()) {
					case ERROR:
						addResutlToTable("ERROR", TABLE_CONTENT_RED, task.getName(), result.getText(), result.getDescription());
					break;
					case WARNING:
						addResutlToTable("WARN.", TABLE_CONTENT_ORANGE, task.getName(), result.getText(), result.getDescription());
					break;
				}
			}
		}

		private void addResutlToTable(String type,Font typeFont,String category,String text,String desc){
			if (tableDataInserted == false) tableDataInserted = true;
			PdfPCell cell1 = getContentCell(type, typeFont);
			PdfPCell cell2 = getContentCell(category);
			PdfPCell cell3 = getContentCell(text);
			PdfPCell cell4 = getContentCell(desc, TABLE_CONTENT_SMALL);

			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
		}

		public Paragraph getFinalParagraph(){
			if (tableDataInserted) {
				Paragraph p = createParagraph("The " + analysisName + " analysis for the " + getProjectName() + " has identified the problems summarised in " + ftc.getTable(tableConstant) + ".");
				addTableCaption(table, ftc.getTable(tableConstant) + " - " + analysisName + " Analysis Results");
				p.add(table);
				return p;
			} else {
				Paragraph p = createParagraph("%i" + "The " + analysisName + " analysis for " + getProjectName() + " project didn't find any errors." + "% ");
				p.setIndentationLeft(15);
				return p;
			}
		}
	}

	protected final static FontFamily	BASE_FONT					= FontFamily.TIMES_ROMAN;

	protected final static Font			FIRST_PAGE_TITLE			= new Font(BASE_FONT, 26, Font.BOLD);
	protected final static Font			FIRST_PAGE_PRJ_NAME		= new Font(BASE_FONT, 26, Font.BOLD);
	protected final static Font			FIRST_PAGE_AUTHOR			= new Font(BASE_FONT, 16, Font.BOLD);
	protected final static Font			FIRST_PAGE_ORGANIZ		= new Font(BASE_FONT, 14, Font.BOLD);
	protected final static Font			FIRST_PAGE_DATE			= new Font(BASE_FONT, 14, Font.NORMAL);
	protected final static Font			FIRST_PAGE_LAST_LINE		= new Font(BASE_FONT, 14, Font.NORMAL);

	protected final static Font			INDEX_TITLE					= new Font(BASE_FONT, 14, Font.BOLD);
	protected final static Font			INDEX_CHAPTER				= new Font(BASE_FONT, 12, Font.NORMAL);
	protected final static Font			INDEX_SECTION				= new Font(BASE_FONT, 12, Font.NORMAL);
	protected final static Font			INDEX_NUMBER				= new Font(BASE_FONT, 12, Font.NORMAL);
	protected final static Font			PAGE_NUMBER_INDEX			= new Font(BASE_FONT, 12, Font.NORMAL);



	protected final static Font			CHAPTER						= new Font(BASE_FONT, 14, Font.BOLD, new BaseColor(52, 90, 138));
	protected final static Font			SECTION						= new Font(BASE_FONT, 12, Font.ITALIC, new BaseColor(52, 90, 138));
	protected final static Font			PAGE_NUMBER					= new Font(BASE_FONT, 10, Font.NORMAL);



	protected final static FontFamily	CONTENT_FONT				= BASE_FONT;
	protected final static int				CONTENT_SIZE				= 12;
	protected final static Font			CONTENT_NORMAL				= new Font(CONTENT_FONT, CONTENT_SIZE, Font.NORMAL);
	protected final static Font			CONTENT_BOLD				= new Font(CONTENT_FONT, CONTENT_SIZE, Font.BOLD);
	protected final static Font			CONTENT_ITALIC				= new Font(CONTENT_FONT, CONTENT_SIZE, Font.ITALIC);
	protected final static Font			CONTENT_UNDERLINE			= new Font(CONTENT_FONT, CONTENT_SIZE, Font.UNDERLINE);
	protected final static Font			CONTENT_BOLDITALIC		= new Font(CONTENT_FONT, CONTENT_SIZE, Font.BOLDITALIC);
	protected final static Font			IMAGE_CAPTION				= new Font(FontFamily.HELVETICA, 8, Font.BOLD, new BaseColor(52, 90, 138));

	protected final static Font			CONTENT_ANALYSYS_ERROR	= new Font(CONTENT_FONT, CONTENT_SIZE, Font.NORMAL, new BaseColor(255, 0, 0));
	protected final static Font			CONTENT_ANALYSYS_RESULT	= new Font(CONTENT_FONT, CONTENT_SIZE - 2, Font.NORMAL);

	protected final static Font			TABLE_HEADER				= new Font(CONTENT_FONT, 10, Font.BOLD, new BaseColor(0, 0, 138));
	protected final static Font			TABLE_CONTENT				= new Font(CONTENT_FONT, 9, Font.NORMAL);
	protected final static Font			TABLE_CONTENT_SMALL		= new Font(CONTENT_FONT, 7, Font.NORMAL);
	protected final static Font			TABLE_CAPTION				= new Font(CONTENT_FONT, 8, Font.NORMAL);
	protected final static Font			TABLE_CONTENT_RED			= new Font(CONTENT_FONT, 9, Font.NORMAL, new BaseColor(255, 0, 0));
	protected final static Font			TABLE_CONTENT_ORANGE		= new Font(CONTENT_FONT, 9, Font.NORMAL, new BaseColor(255, 102, 0));
	protected final static Font			TABLE_CONTENT_GRAY		= new Font(CONTENT_FONT, 9, Font.NORMAL, new BaseColor(125, 125, 125));



}
