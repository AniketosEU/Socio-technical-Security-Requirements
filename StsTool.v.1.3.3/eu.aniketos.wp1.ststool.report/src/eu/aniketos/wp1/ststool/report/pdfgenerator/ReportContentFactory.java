/*
* ReportContentFactory.java
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

import java.util.*;

import org.eclipse.core.runtime.IProgressMonitor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import eu.aniketos.wp1.ststool.*;
import eu.aniketos.wp1.ststool.analysis.consistency.tasks.ConsistencyAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.consistency.tasks.ConsistencyAnalysisTasksNames;
import eu.aniketos.wp1.ststool.analysis.risk.tasks.RiskAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.risk.tasks.RiskAnalysisTasksNames;
import eu.aniketos.wp1.ststool.analysis.security.tasks.SecurityAnalysisTasks;
import eu.aniketos.wp1.ststool.analysis.security.tasks.SecurityAnalysisTasksNames;
import eu.aniketos.wp1.ststool.analysis.util.analyser.DiagramAnalyser;
import eu.aniketos.wp1.ststool.diagram.custom.editpart.*;
import eu.aniketos.wp1.ststool.diagram.custom.view.ViewsManager;

public class ReportContentFactory extends AbstractContentFactory {

	public ReportContentFactory(ReportValueFactory rvf,
			ReportScreenshotProvider rsp) {

		super(rvf, rsp);
	}

	public int startingPageNumber() {

		return -1;
	}

	public boolean buildIndex() {

		return true;
	}

	public Paragraph getIndexTitle() {

		Paragraph p = new Paragraph("Table of Contents", INDEX_TITLE);
		p.setSpacingAfter(10f);
		return p;
	}

	public void buildContent(PdfWriter writer, Document document,
			IProgressMonitor monitor) {

		monitor.beginTask("Generating Report", 100);
		try {
			buildFirstPage(writer, document);
			document.newPage();

			monitor.worked(1);

			int chapNum = 1;
			if (genrateIntroductionChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildIntorductionChapter(c);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(9);
			if (generateSocialViewChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildSocialViewChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateInformationViewChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildInformationViewChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateAuthorisationViewChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAuthorisationViewChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateSecurityRequirementsChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildSecurityRequirementsChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateAnalysisChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAnalysisChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateAppendixAChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAppendixAChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}

			monitor.worked(10);
			if (generateAppendixBChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAppendixBChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateAppendixCChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAppendixCChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);
			if (generateAppendixDChapter()) {
				Chapter c = new Chapter(chapNum++);
				buildAppendixDChapter(c, document);
				document.add(c);
				c.setComplete(true);
			}
			monitor.worked(10);

		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}

	private void buildFirstPage(PdfWriter writer, Document document)
			throws DocumentException {

		Paragraph firstPageTitleParagraph = new Paragraph(getDocumentTitle(),
				FIRST_PAGE_TITLE);
		firstPageTitleParagraph.setAlignment(Element.ALIGN_CENTER);
		firstPageTitleParagraph.setSpacingAfter(Utilities
				.millimetersToPoints(50));
		document.add(firstPageTitleParagraph);

		Paragraph firstPageProjectNameParagraph = new Paragraph(
				getProjectName(), FIRST_PAGE_PRJ_NAME);
		firstPageProjectNameParagraph.setAlignment(Element.ALIGN_CENTER);
		firstPageProjectNameParagraph.setSpacingAfter(Utilities
				.millimetersToPoints(20));
		document.add(firstPageProjectNameParagraph);

		Paragraph firstPageAuthorNameParagraph = new Paragraph(
				getReportAuthor(), FIRST_PAGE_AUTHOR);
		firstPageAuthorNameParagraph.setAlignment(Element.ALIGN_CENTER);
		firstPageAuthorNameParagraph.setSpacingAfter(Utilities
				.millimetersToPoints(20));
		document.add(firstPageAuthorNameParagraph);

		Paragraph firstPageOrganizationParagraph = new Paragraph(
				getReportIstitution(), FIRST_PAGE_ORGANIZ);
		firstPageOrganizationParagraph.setAlignment(Element.ALIGN_CENTER);
		firstPageOrganizationParagraph.setSpacingAfter(Utilities
				.millimetersToPoints(20));
		document.add(firstPageOrganizationParagraph);

		Paragraph firstPageDateParagraph = new Paragraph(getDate(),
				FIRST_PAGE_DATE);
		firstPageDateParagraph.setAlignment(Element.ALIGN_CENTER);
		firstPageDateParagraph.setSpacingAfter(Utilities
				.millimetersToPoints(40));
		document.add(firstPageDateParagraph);

		String[] phrases = { "This document has been generated by STS-Tool",
				"http://www.sts-tool.eu" };
		Paragraph firstPageLastLineParagraph = new Paragraph(phrases[0],
				FIRST_PAGE_LAST_LINE);
		for (int i = 1; i < phrases.length; i++) {
			firstPageLastLineParagraph.add(Chunk.NEWLINE);
			firstPageLastLineParagraph.add(new Phrase(phrases[i],
					FIRST_PAGE_LAST_LINE));
		}
		firstPageLastLineParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(firstPageLastLineParagraph);

		if (footerImage != null) {
			footerImage.setAbsolutePosition((PageSize.A4.getWidth() / 2)
					- (footerImage.getPlainWidth() / 2), 50);
			document.add(footerImage);
		}
	}

	private void buildIntorductionChapter(Chapter chapter) {

		chapter.setTitle(getChapterTitleParagraph("Introduction"));
		chapter.setTriggerNewPage(true);

		StringBuilder sb = new StringBuilder();
		sb.append("This document describes the security requirements for the "
				+ getProjectName()
				+ " project. It provides a detailed description of the socio-technical security requirements models ");

		if (generateSecurityRequirementsChapter()
				&& (!generateSocialViewChapter()
						&& !generateInformationViewChapter() && !generateAuthorisationViewChapter())) {
			sb.append("presenting the list of security requirements derived in terms of social commitments.");
		} else {
			List<String> views = new ArrayList<String>();
			if (generateSocialViewChapter()) {
				views.add("%iSocial%");
			}
			if (generateInformationViewChapter()) {
				views.add("%iInformation%");
			}
			if (generateAuthorisationViewChapter()) {
				views.add("%iAuthorisation%");
			}

			if (views.size() == 1) {
				sb.append("from a view ");
			} else {
				sb.append("from different views ");
			}
			sb.append("(" + separateListOfString(views, ", ", ", ") + ")");

			if (generateSecurityRequirementsChapter()) {
				sb.append(" and then presents the list of %isecurity requirements% derived from them");
			}
			sb.append(".");

		}

		chapter.add(createParagraph(sb.toString()));

		sb = new StringBuilder();

		if (generateSocialViewChapter()) {
			sb.append("The %iSocial view% represents stakeholders as intentional and social entities, representing their goals and important information in terms of documents, together with their interactions with other actors to achieve these goals and to exchange information. Stakeholders express constraints over their interactions in terms of %i security needs. % ");
		}
		if (generateInformationViewChapter()) {
			if (sb.length() > 0)
				sb.append(" ");
			sb.append("The %iInformation view% represents the informational content of stakeholders’ documents, showing how information and documents are interconnected, as well as how they are composed respectively.");
		}
		if (generateAuthorisationViewChapter()) {
			if (sb.length() > 0)
				sb.append(" ");
			sb.append("The %iAuthorisation view% represents which stakeholders own what information, and captures the flow of permissions from one stakeholder to another. The modelling of authorisations expresses other %i security needs % related to the way information is to be manipulated. ");
		}
		if (sb.length() > 0)
			chapter.add(createParagraph(sb.toString()));

		if (generateSecurityRequirementsChapter()) {
			String s = "The document ends with the list of %isecurity requirements% for the system to be expressed in terms of %i social commitments%, namely promises with contractual validity stakeholders make to one another. The security requirements are derived automatically once the modelling is done and the designer has expressed the security needs. Whenever a security need is expressed over an interaction from one stakeholder to the other, a commitment on the opposite direction is expected from the second stakeholder to satisfy the security need.";
			chapter.add(createParagraph(s));
		}

		chapter.setComplete(true);
	}

	/******************************************************************************************/

	private void buildSocialViewChapter(Chapter chapter, Document document) {

		chapter.setTitle(getChapterTitleParagraph("Social View"));
		String chapterIntro = "The social view shows the involved stakeholders, which are represented as %iroles% and %iagents%. Agents refer to actual participants (stakeholders) known when modelling the "
				+ getProjectName()
				+ " project, whereas roles are a generalisation (abstraction) of agents. To capture the connection between roles and agents, the %i play % relation is used to express the fact that certain agents play certain roles.";
		chapter.add(createParagraph(chapterIntro));
		chapterIntro = "Stakeholders have goals to achieve and they make use of different information to achieve these goals. They interact with one another mainly by %i delegating goals% and %i exchanging information%. Information is represented by means of documents, which actors manipulate to achieve their goals.";
		chapter.add(createParagraph(chapterIntro));

		if (generateSocialViewDiagramSection()) {
			buildSectionSocialDiagram(
					chapter.addSection(getSectionTitleParagraph("Social View Diagram")),
					document);
			chapter.add(Chunk.NEXTPAGE);
		}
		if (generateStakeholdersSection()) {
			buildSectionStakeholders(chapter
					.addSection(getSectionTitleParagraph("Stakeholders")));
		}
		if (generateStakeholdersDocumentSection()) {
			buildSectionStakeholdersDocument(chapter
					.addSection(getSectionTitleParagraph("Stakeholders' documents")));
		}
		if (generateStakeholdersDocumentAndGoalsSection()) {
			buildSectionStakeholdersDocumentAndGoals(chapter
					.addSection(getSectionTitleParagraph("Stakeholders' documents and goals")));
		}
		if (generateGoalAnalysisSection()) {
			buildSectionGoalAnalysis(chapter
					.addSection(getSectionTitleParagraph("Goal Analysis")));
		}
		if (generateGoalContributionSection()) {
			buildSectionGoalContribution(chapter
					.addSection(getSectionTitleParagraph("Contributions")));
		}
		if (generateStakeholdersInteractionsSection()) {
			buildSectionStakeholdersInteractions(chapter
					.addSection(getSectionTitleParagraph("Stakeholders Interactions")));
		}
		if (generateOrganisationalConstraintsSection()) {
			buildSectionOrganisationalConstraints(chapter
					.addSection(getSectionTitleParagraph("Organisational Constraints")));
		}

		if (generateEventSection()) {
			buildEventsSection(chapter.
					addSection(getSectionTitleParagraph("Events")));
		}

		chapter.setTriggerNewPage(true);
		chapter.setComplete(true);
	}

	private void buildSectionSocialDiagram(Section section, Document document) {

		Image i = rsp.generateScrenshot(ViewsManager.SOCIAL_VIEW);
		float reductionScale = fitImageInArea(i, document.getPageSize()
				.getWidth() - document.rightMargin() - document.leftMargin(),
				310, false);
		if (reductionScale <= getMaximumReductionScale()) {
			setAppendixASocialDiagram(true);
		}

		StringBuilder sectionIntro = new StringBuilder();
		sectionIntro.append(ftc.getFigure(FigureConstant.SOCIAL_DIAGRAM)
				+ " presents the graphical representation of the social view");
		if (needSocialDiagramInAppendixA()) {
			sectionIntro.append(" (a larger picture is shown in appendix A)");
		}
		sectionIntro.append(".");
		section.add(createParagraph(sectionIntro.toString()));

		section.add(decorateImage(i,
				ftc.getFigure(FigureConstant.SOCIAL_DIAGRAM)
						+ " - Social View for the " + getProjectName()
						+ " project"));
		section.setComplete(true);
	}

	private void buildSectionStakeholders(Section section) {

		String sectionIntro = "This section describes the stakeholders identified in the "
				+ getProjectName()
				+ " project. Stakeholders are represented by roles and agents.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getStakeholderActors();

		if (selActor.size() != 0) {
			StringBuilder sb = new StringBuilder();

			sb.append("In particular, ");

			List<Role> roles = new ArrayList<Role>();
			for (Actor a : selActor) {
				if (a instanceof Role)
					roles.add((Role) a);
			}
			List<String> rolesNames = new ArrayList<String>();
			for (Role r : roles) {
				rolesNames.add("%i" + r.getName() + "%");
			}
			if (roles.size() == 0) {

			} else if (roles.size() == 1) {
				sb.append("identified role is: " + rolesNames.get(0)
						+ socialDiagRef());
			} else {
				sb.append("identified roles are: "
						+ separateListOfString(rolesNames) + socialDiagRef());
			}

			List<Agent> agents = new ArrayList<Agent>();
			for (Actor a : selActor) {
				if (a instanceof Agent)
					agents.add((Agent) a);
			}
			List<String> agentsNames = new ArrayList<String>();
			for (Agent a : agents) {
				agentsNames.add("%i" + a.getName() + "%");
			}
			if (roles.size() > 0 && agents.size() > 0) {
				sb.append(", while ");
			} else if (agents.size() == 0) {
				sb.append(".");
			}

			if (agents.size() == 0) {

			} else if (agents.size() == 1) {
				if (roles.size() > 0)
					sb.append(" ");
				sb.append("identified agent is: " + agentsNames.get(0)
						+ socialDiagRef() + ".");
			} else {
				if (roles.size() > 0)
					sb.append(" ");
				sb.append("identified agents are: "
						+ separateListOfString(agentsNames) + socialDiagRef()
						+ ". ");
			}
			// section.add(createParagraph(sb.toString()));

			// sb = new StringBuilder();
			if (roles.size() > 0) {
				sb.append(" " + ftc.getTable(FigureConstant.ROLE_TABLE));
				if (agents.size() > 0)
					sb.append(" and "
							+ ftc.getTable(FigureConstant.AGENT_TABLE));
			} else {
				sb.append(" " + ftc.getTable(FigureConstant.AGENT_TABLE));
			}
			sb.append(" summarise the stakeholders.");

			section.add(createParagraph(sb.toString()));

			if (roles.size() > 0) {
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(new String[] { "Role" });
				headers.add(new String[] { "Description" });
				headers.add(new String[] { "Mission" });
				headers.add(new String[] { "Purpose" });

				PdfPTable table = createTable(headers);
				for (Role r : roles) {
					table.addCell(getContentCell(r.getName()));
					table.addCell(getContentCell(r.getDescription()));
					table.addCell(getContentCell(r.getMission()));
					table.addCell(getContentCell(r.getPurpose()));
				}
				addTableCaption(table, ftc.getTable(FigureConstant.ROLE_TABLE)
						+ " - Roles in the " + getProjectName() + " project.");
				section.add(table);
				table.setComplete(true);
				section.add(getDefaultParagraph());
			}
			if (agents.size() > 0) {
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(new String[] { "Agent" });
				headers.add(new String[] { "Description" });
				headers.add(new String[] { "Abilities" });
				headers.add(new String[] { "Important", "Features" });
				headers.add(new String[] { "Certifications", "Accreditations" });
				headers.add(new String[] { "Type Of", "Organisation" });

				PdfPTable table = createTable(headers);
				for (Agent a : agents) {
					table.addCell(getContentCell(a.getName()));
					table.addCell(getContentCell(a.getDescription()));
					table.addCell(getContentCell(a.getAbilities()));
					table.addCell(getContentCell(a.getOtherImportantFeatures()));
					table.addCell(getContentCell(a
							.getPossessedCertificationsAndAccreditations()));
					table.addCell(getContentCell(a.getTypeOfOrganisation()));
				}
				addTableCaption(table, ftc.getTable(FigureConstant.AGENT_TABLE)
						+ " - Agents in the " + getProjectName() + " project");
				section.add(table);
				table.setComplete(true);
				section.add(getDefaultParagraph());
			}

			sb = new StringBuilder();
			List<Play> plays = new ArrayList<Play>();
			for (Actor a : getStakeholderActors()) {
				if (a instanceof Agent) {
					for (Play p : ((Agent) a).getPlayedRoles()) {
						if (p.getTarget() != null)
							plays.add(p);
					}
				}
			}
			if (plays.size() > 0) {
				section.add(createParagraph("Agents and roles are related by means of %i play % relations, as reported on "
						+ ftc.getTable(FigureConstant.PLAY_TABLE)));

				List<String[]> headers = new ArrayList<String[]>();
				headers.add(new String[] { "Agent" });
				headers.add(new String[] { "Role" });

				PdfPTable table = createTable(headers);
				for (Play p : plays) {
					table.addCell(getContentCell(p.getSource().getName()));
					table.addCell(getContentCell(p.getTarget().getName()));
				}
				addTableCaption(table, ftc.getTable(FigureConstant.PLAY_TABLE)
						+ " - Agent/Role relations in the " + getProjectName()
						+ " project");

				section.add(table);
				table.setComplete(true);
			} else {
				section.add(createParagraph("In the "
						+ getProjectName()
						+ " project there are no plays relationships taking place for the given agents/roles."));
			}
		}

		section.setComplete(true);
		section.setTriggerNewPage(true);
	}

	private void buildSectionStakeholdersInteractions(Section section) {

		String sectionIntro = "This section describes stakeholders’ interactions, providing insight on whom they interact with to fulfil their desired objectives, as well as which are the stakeholders that rely on them to fulfil their respective goals. This kind of interaction is carried out by means of%i goal delegations%.";
		section.add(createParagraph(sectionIntro));
		sectionIntro = "To achieve their goals stakeholders might need specific information. If they do not possess this information, they may ask other stakeholders to provide thems documents. %iDocument provision% is used to capture this interaction.";
		section.add(createParagraph(sectionIntro));

		if (generateGoalDelegationsSection()) {
			Section section1 = section
					.addSection(getSectionTitleParagraph("Goal Delegations"));
			buildSectionGoalDelegations(section1);
			section1.setComplete(true);
		}

		if (generateDocumentProvisionsSection()) {
			Section section2 = section
					.addSection(getSectionTitleParagraph("Document Provisions"));
			buildSectionDocumentProvisions(section2);
			section2.setComplete(true);
		}
	}

	private void buildSectionGoalDelegations(Section section) {

		String sectionIntro = "Stakeholders interact with others to achieve some of their goals by means of goal delegations. Goal delegations are graphically represented as a relation that starts from a delegator actor to a delegatee actor (following the direction of the arrow), having a rounded corner rectangle representing the goal being delegated. Security needs are graphically specified as labels that appear below the delegated goal "
				+ socialDiagRef() + ".";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getGoalDelegationsActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {

			for (Delegation d : a.getOutgoingDelegations()) {
				Paragraph par = createParagraph("%b" + a.getName()
						+ "% delegates goal %i" + d.getSourceGoal().getName()
						+ "% to %b" + d.getTarget().getName() + "%.");
				List<String> secNeeds = buildDelegationSecNeedList(d);
				if (secNeeds.size() > 0) {
					par.add(new Phrase(
							" The following security needs apply to this delegation:"));
					par.add(Chunk.NEWLINE);
					par.add(new Phrase(separateListOfString(secNeeds) + "."));
				}
				phrases.add(par);
			}

			/*
			 * StringBuilder sbInc = new StringBuilder(); sbInc.append("%b" +
			 * a.getName() + "% is delegated by ");
			 * 
			 * int incCount = 0; for (Delegation d : a.getIncomingDelegations())
			 * { if (incCount > 0) sbInc.append(", and is delegated by ");
			 * sbInc.append("%i" + d.getSource().getName() + "% goal %i" +
			 * d.getSourceGoal().getName() + "%"); List<String> secNeed1 =
			 * buildSecNeedString(d, false, 'i'); List<String> secNeed2 =
			 * buildSecNeedString(d, true, 'i');
			 * 
			 * if (secNeed2.size() > 0) { switch (secNeed2.size()) { case 1:
			 * sbInc.append(" and is required to comply with the " +
			 * separateListOfString(secNeed2) + " security need"); break;
			 * default: sbInc.append(" and is required to comply with the " +
			 * separateListOfString(secNeed2) + " security needs"); break; } }
			 * if (secNeed1.size() > 0) { switch (secNeed1.size()) { case 1:
			 * sbInc.append(" and requires it to comply with the " +
			 * separateListOfString(secNeed1) + " security need"); break;
			 * default: sbInc.append(" and requires it to comply with the " +
			 * separateListOfString(secNeed1) + " security needs"); break; }
			 * 
			 * } List<String> parmSecNeed = buildParamterizedSecNeedString(d,
			 * 'i'); if (parmSecNeed.size() > 0) {
			 * sbInc.append(" and is required for a level of " +
			 * separateListOfString(parmSecNeed)); } if (secNeed1.size() == 0 &&
			 * secNeed2.size() == 0 && parmSecNeed.size() == 0) {
			 * sbInc.append(": no security need is speccified"); } incCount++; }
			 * 
			 * 
			 * StringBuilder sbOut = new StringBuilder(); int outCount = 0; for
			 * (Delegation d : a.getOutgoingDelegations()) { if (outCount > 0)
			 * sbOut.append(", and delegates to "); sbOut.append("%i" +
			 * d.getTarget().getName() + "% goal %i" +
			 * d.getSourceGoal().getName() + "%"); List<String> secNeed1 =
			 * buildSecNeedString(d, true, 'i'); List<String> secNeed2 =
			 * buildSecNeedString(d, false, 'i');
			 * 
			 * if (secNeed1.size() > 0) { switch (secNeed1.size()) { case 1:
			 * sbOut.append(" and requires it to comply with the " +
			 * separateListOfString(secNeed1) + " security need"); break;
			 * default: sbOut.append(" and requires it to comply with the " +
			 * separateListOfString(secNeed1) + " security needs"); break; } }
			 * if (secNeed2.size() > 0) { switch (secNeed2.size()) { case 1:
			 * sbOut.append(" and is required to comply with the " +
			 * separateListOfString(secNeed2) + " security need"); break;
			 * default: sbOut.append(" and is required to comply with the " +
			 * separateListOfString(secNeed2) + " security needs"); break; } }
			 * List<String> parmSecNeed = buildParamterizedSecNeedString(d,
			 * 'i'); if (parmSecNeed.size() > 0) {
			 * sbOut.append(" and requires a level of " +
			 * separateListOfString(parmSecNeed)); } if (secNeed1.size() == 0 &&
			 * secNeed2.size() == 0 && parmSecNeed.size() == 0) {
			 * sbOut.append(": no security need is speccified"); }
			 * 
			 * outCount++; }
			 * 
			 * StringBuilder sb = new StringBuilder(); if (incCount > 0) { if
			 * (outCount > 0) { sb.append(sbInc.toString() +
			 * " while it delegates to " + sbOut.toString() + "."); } else {
			 * sb.append(sbInc.toString() + "."); } } else { if (outCount > 0) {
			 * sb.append("%b" + a.getName() + "% delegates to " +
			 * sbOut.toString() + "."); } } if (sb.length() > 0) {
			 * phrases.add(createParagraph(sb.toString())); }
			 */
		}

		if (phrases.size() != 0) {
			sectionIntro = "The following description enlists all the delegations from one role/agent to the others. When applicable, security needs expressed over the delegations are enumerated.";
			section.add(createParagraph(sectionIntro));

			String t = "In the " + getProjectName() + " project"
					+ socialDiagRef()
					+ ", we have the following goal delegations:";
			section.add(createParagraph(t));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.DELEGATION_TABLE)
					+ " summarises %igoal delegations%, together with the eventual %isecurity needs%, and the possible %ipreconditions% and %ipostconditions%, which determine when the delegation can take place, and the expected outcome of the delegation, respectively."));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Delegator" });
			headers.add(new String[] { "Goal" });
			headers.add(new String[] { "Delegatee" });
			headers.add(new String[] { "Security", "Needs" });
			headers.add(new String[] { "Delegation", "Description" });
			headers.add(new String[] { "Pre-", "conditions" });
			headers.add(new String[] { "Post-", "conditions" });

			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {

				if (a.getOutgoingDelegations().size() > 0) {

					PdfPCell cell = getContentCell();
					cell.setPhrase(new Phrase(a.getName(), TABLE_CONTENT));
					cell.setRowspan(a.getOutgoingDelegations().size());
					table.addCell(cell);

					for (Delegation d : a.getOutgoingDelegations()) {
						table.addCell(getContentCell(d.getSourceGoal()
								.getName()));
						table.addCell(getContentCell(d.getTarget().getName()));

						List<String> sn = buildDelegationSecNeedList(d);

						Phrase p = null;
						for (int i = 0; i < sn.size(); i++) {
							if (i == 0)
								p = new Phrase(sn.get(i), TABLE_CONTENT_SMALL);
							else {
								p.add(Chunk.NEWLINE);
								p.add(new Phrase(sn.get(i), TABLE_CONTENT_SMALL));
							}
						}

						PdfPCell c = getContentCell();
						c.addElement(p);
						table.addCell(c);

						table.addCell(getContentCell(d.getDescription()));
						table.addCell(getContentCell(d.getPreConditions()));
						table.addCell(getContentCell(d.getPostConditions()));
					}
				}
			}
			addTableCaption(table,
					ftc.getTable(FigureConstant.DELEGATION_TABLE)
							+ " - Goal Delegations and Security Needs");
			section.add(table);
			table.setComplete(true);

		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no goal delegations taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionDocumentProvisions(Section section) {

		String sectionIntro = "Stakeholders exchange information by means of documents with other stakeholders. The following description enlists all the provisions from one role/agent representing the stakeholder, to other roles/agents. %iDocument provision% is represented as an arrow from the provider to the providee, with a rectangle representing the document. The security needs expressed over the provisions are described, if applicable. Security needs are specified with the help of labels that appear below the document.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getDocumentProvisionsActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {

			for (Provision p : a.getOutgoingProvisions()) {
				Paragraph par = createParagraph("%b" + a.getName()
						+ "% provides document %i"
						+ p.getSourceResource().getName() + "% to %b"
						+ p.getTarget().getName() + "%.");
				List<String> secNeeds = buildProvisionSecNeedList(p);
				if (secNeeds.size() > 0) {
					par.add(new Phrase(
							" The following security needs apply to this provision:"));
					par.add(Chunk.NEWLINE);
					par.add(new Phrase(separateListOfString(secNeeds) + "."));
				}
				phrases.add(par);
			}
		}

		/*
		 * for (Actor a : selActor) { StringBuilder sbInc = new StringBuilder();
		 * sbInc.append("%b" + a.getName() + "% is provided by "); int incCount
		 * = 0; for (Provision p : a.getIncomingProvisions()) { if (incCount >
		 * 0) sbInc.append(", and is provided by "); sbInc.append("%i" +
		 * p.getSource().getName() + "% document %i" +
		 * p.getSourceResource().getName() + "%"); if (p.isIntegrity()) {
		 * sbInc.append(" and requires %b" + p.getSource().getName() +
		 * "% to ensure %iintegrity of transmission% over the provision of this document"
		 * ); }
		 * 
		 * incCount++; }
		 * 
		 * StringBuilder sbOut = new StringBuilder(); int outCount = 0; for
		 * (Provision p : a.getOutgoingProvisions()) { if (outCount > 0)
		 * sbOut.append(", and it provides to "); sbOut.append("%i" +
		 * p.getTarget().getName() + "% document %i" +
		 * p.getSourceResource().getName() + "%"); if (p.isIntegrity()) {
		 * sbInc.append(" and requires %iAvailability% level of " +
		 * p.getAvailabilityValue() + "%"); } outCount++; }
		 * 
		 * StringBuilder sb = new StringBuilder(); if (incCount > 0) { if
		 * (outCount > 0) { sb.append(sbInc.toString() +
		 * " while it provides to " + sbOut.toString() + "."); } else {
		 * sb.append(sbInc.toString() + "."); } } else { if (outCount > 0) {
		 * sb.append("%b" + a.getName() + "% provides to " + sbOut.toString() +
		 * "."); } }
		 * 
		 * if (sb.length() > 0) { phrases.add(createParagraph(sb.toString())); }
		 * }
		 */

		if (phrases.size() != 0) {
			String s = "In the " + getProjectName() + " project "
					+ socialDiagRef()
					+ ", we have the following %idocument provisions%:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.PROVISION_TABLE)
					+ " summarises the %idocument provisions% for the "
					+ getProjectName() + " project."));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Providor" });
			headers.add(new String[] { "Document" });
			headers.add(new String[] { "Providee" });
			headers.add(new String[] { "Security", "Needs" });
			headers.add(new String[] { "Provision", "Descr." });

			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {

				if (a.getOutgoingProvisions().size() > 0) {

					PdfPCell cell = getContentCell();
					cell.setPhrase(new Phrase(a.getName(), TABLE_CONTENT));
					cell.setRowspan(a.getOutgoingProvisions().size());
					table.addCell(cell);

					for (Provision p : a.getOutgoingProvisions()) {
						table.addCell(getContentCell(p.getSourceResource()
								.getName()));
						table.addCell(getContentCell(p.getTarget().getName()));

						List<String> sn = buildProvisionSecNeedList(p);

						Phrase pr = null;
						for (int i = 0; i < sn.size(); i++) {
							if (i == 0)
								pr = new Phrase(sn.get(i), TABLE_CONTENT_SMALL);
							else {
								pr.add(Chunk.NEWLINE);
								pr.add(new Phrase(sn.get(i),
										TABLE_CONTENT_SMALL));
							}
						}

						PdfPCell c = getContentCell();
						c.addElement(pr);
						table.addCell(c);

						table.addCell(getContentCell(p.getDescription()));
					}
				}
			}
			addTableCaption(table, ftc.getTable(FigureConstant.PROVISION_TABLE)
					+ " - Document Provisions");
			section.add(table);
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no document provisions taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionGoalAnalysis(Section section) {

		String sectionIntro = "Stakeholders have goals to achieve. Goals are represented within the rationale (round compartment attached to the role/agent"
				+ socialDiagRef(", see ", "")
				+ ") of the role/agent representing the stakeholder. They achieve their goals by further refining them into finer-grained goals (subgoals) by means of AND/OR-decompositions. AND-decompositions structurally refine a goal into multiple subgoals (all AND subgoals need to be achieved for the goal to be achieved), while OR-decompositions represent alternative ways for achieving a goal (at least one of the subgoals in the OR-decomposition needs to be achieved for the goal to be achieved).";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getGoalAnalysisActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {
			if (a.getGoals().size() != 0) {
				StringBuilder sb = new StringBuilder();

				List<String> goalS = new ArrayList<String>();
				List<Goal> goalDecomposed = new ArrayList<Goal>();
				for (Goal g : a.getGoals()) {
					if (g.getIncomingDecompositions() == null) {
						goalS.add("goal %i" + g.getName() + "%");
					}
					if (g.getOutgoingDecompositions().size() > 0) {
						goalDecomposed.add(g);
					}
				}
				if (goalS.size() > 0) {
					sb.append("%b" + a.getName() + "% " + " has to achieve "
							+ separateListOfString(goalS) + ".");
					if (goalDecomposed.size() > 0) {
						for (Goal g : goalDecomposed) {
							sb.append(" To achieve %i" + g.getName() + "%, "
									+ g.getActorOwner().getName()
									+ " should achieve ");
							if (g.getOutgoingDecompositions().get(0) instanceof GoalDecompositionAND) {
								List<String> list = new ArrayList<String>();
								for (GoalDecomposition gd : g
										.getOutgoingDecompositions()) {
									if (gd.getTarget() != null)
										list.add("goal %i"
												+ gd.getTarget().getName()
												+ "%");
								}
								sb.append(separateListOfString(list));
							} else {
								List<String> list = new ArrayList<String>();
								for (GoalDecomposition gd : g
										.getOutgoingDecompositions()) {
									if (gd.getTarget() != null)
										list.add("goal %i"
												+ gd.getTarget().getName()
												+ "%");
								}
								sb.append("either "
										+ separateListOfString(list, ", ",
												" or "));
							}
							sb.append(".");
						}
					}
				}

				if (sb.length() > 0) {
					phrases.add(createParagraph(sb.toString()));
				}
			}
		}
		if (phrases.size() != 0) {
			String s = "In the " + getProjectName() + " project"
					+ socialDiagRef() + " we have:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.GOAL_DEC_TABLE)
					+ " summarises the goals of each agent/role in the "
					+ getProjectName()
					+ " project and how they are decomposed, when applicable."));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Agent/Role" });
			headers.add(new String[] { "Goal" });
			headers.add(new String[] { "Dec. Type" });
			headers.add(new String[] { "Subgoals" });
			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {

				List<Goal> validGoals = new ArrayList<Goal>();

				for (Goal g : a.getGoals()) {
					if (g.getIncomingDecompositions() == null) {
						validGoals.add(g);
					} // remove goal of decompostion
				}
				if (validGoals.size() > 0) {
					PdfPCell actorCell = getContentCell(a.getName());

					int rowspan = 0;
					for (Goal g : validGoals) {
						if (g.getOutgoingDecompositions().size() > 0)
							rowspan += g.getOutgoingDecompositions().size();
						else
							++rowspan;
					}
					actorCell.setRowspan(rowspan);
					table.addCell(actorCell);

					for (Goal g : validGoals) {

						PdfPCell goalCell = getContentCell(g.getName());

						if (g.getOutgoingDecompositions().size() > 0) {
							String decType = "";
							if (g.getOutgoingDecompositions().get(0) instanceof GoalDecompositionAND) {
								decType = "AND";
							} else {
								decType = "OR";
							}
							PdfPCell decTypeCell = getContentCell(decType);

							List<String> decomp = new ArrayList<String>();
							for (GoalDecomposition gd : g
									.getOutgoingDecompositions()) {
								if (gd.getTarget() != null)
									decomp.add(gd.getTarget().getName());
							}

							if (decomp.size() > 1) {
								rowspan = decomp.size();
								goalCell.setRowspan(rowspan);
								decTypeCell.setRowspan(rowspan);
							}
							table.addCell(goalCell);
							table.addCell(decTypeCell);
							for (String stri : decomp) {
								table.addCell(getContentCell(stri));
							}
						} else {
							table.addCell(goalCell);
							table.addCell(getContentCell("-"));
							table.addCell(getContentCell());
						}
					}
				}
			}
			section.add(table);
			addTableCaption(table, ftc.getTable(FigureConstant.GOAL_DEC_TABLE)
					+ " - Goal Decompositions");
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no decomposition relations taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionGoalContribution(Section section) {

		String sectionIntro = "Goals can contribute one to another. A contribution identifies the impact the fulfilment of one goal has on the fulfilment of another goal. This impact can be either positive or negative, and is rappresented with “++” and “--” respectively. Positive contribution means that the achievement of a goal also achieves the other goal. Negative contribution means that the achievement of a goal inhibits the achievement of another goal.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getGoalContributionActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {

			for (Goal g : a.getGoals()) {
				Map<Actor, List<String>> posContib = new HashMap<Actor, List<String>>();
				Map<Actor, List<String>> negContib = new HashMap<Actor, List<String>>();

				for (GoalContribution c : g.getOutgoingContributions()) {
					Actor act = c.getTarget().getActorOwner();
					String gname = "goal %i" + c.getTarget().getName() + "%";
					if (c instanceof PositiveGoalContribution) {
						if (!posContib.containsKey(act)) {
							posContib.put(act, new ArrayList<String>());
						}
						posContib.get(act).add(gname);
					} else {
						if (!negContib.containsKey(act)) {
							negContib.put(act, new ArrayList<String>());
						}
						negContib.get(act).add(gname);
					}
				}

				StringBuilder sb = new StringBuilder();

				if (posContib.size() > 0) {
					sb.append("%ipositive% side effect on ");
					List<String> sl = new ArrayList<String>();
					for (Actor ac : posContib.keySet()) {
						sl.add(separateListOfString(posContib.get(ac)) + " of "
								+ typeActor(ac) + " %i" + ac.getName() + "%");
					}
					sb.append(separateListOfString(sl));
				}
				if (posContib.size() > 0 && negContib.size() > 0) {
					sb.append(" and has ");
				}
				if (negContib.size() > 0) {
					sb.append("%inegative% side effect on ");
					List<String> sl = new ArrayList<String>();
					for (Actor ac : negContib.keySet()) {
						sl.add(separateListOfString(negContib.get(ac)) + " of "
								+ typeActor(ac) + " %i" + ac.getName() + "%");
					}
					sb.append(separateListOfString(sl));
				}

				if (sb.length() > 0) {
					phrases.add(createParagraph("The goal %b" + g.getName()
							+ "% of " + typeActor(a) + " %i" + a.getName()
							+ "% has " + sb.toString() + "."));
				}
			}
		}
		if (phrases.size() != 0) {
			String s = "In the " + getProjectName() + " project"
					+ socialDiagRef() + " we have:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.GOAL_CONTRIB_TABLE)
					+ " summarises the %igoals contributions% in the "
					+ getProjectName() + " project."));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Goal" });
			headers.add(new String[] { "Description" });
			headers.add(new String[] { "Contr. Type" });
			headers.add(new String[] { "Goals" });
			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {
				for (Goal g : a.getGoals()) {
					if (g.getOutgoingContributions().size() > 0) {
						List<GoalContribution> posContib = new ArrayList<GoalContribution>();
						List<GoalContribution> negContib = new ArrayList<GoalContribution>();
						for (GoalContribution c : g.getOutgoingContributions()) {
							if (c.getTarget() != null) {
								if (c instanceof PositiveGoalContribution) {
									posContib.add(c);
								} else {
									negContib.add(c);
								}
							}
						}

						PdfPCell goalCell = getContentCell(new String[] {
								g.getName(), "(" + a.getName() + ")" });

						goalCell.setRowspan(g.getOutgoingContributions().size());
						table.addCell(goalCell);
						PdfPCell descrCell = getContentCell(g.getDescription());
						descrCell.setRowspan(g.getOutgoingContributions()
								.size());
						table.addCell(descrCell);
						if (posContib.size() > 0) {
							PdfPCell typeCell = getContentCell("Positive");
							typeCell.setRowspan(posContib.size());
							table.addCell(typeCell);
							for (GoalContribution gc : posContib) {
								table.addCell(getContentCell(new String[] {
										gc.getTarget().getName(),
										"("
												+ gc.getTarget()
														.getActorOwner()
														.getName() + ")" }));
							}
						}
						if (negContib.size() > 0) {
							PdfPCell typeCell = getContentCell("Negative");
							typeCell.setRowspan(posContib.size());
							table.addCell(typeCell);
							for (GoalContribution gc : negContib) {

								table.addCell(getContentCell(new String[] {
										gc.getTarget().getName(),
										"("
												+ gc.getTarget()
														.getActorOwner()
														.getName() + ")" }));
							}
						}
					}
				}
			}
			section.add(table);
			addTableCaption(table,
					ftc.getTable(FigureConstant.GOAL_CONTRIB_TABLE)
							+ " - Goal Contributions in the "
							+ getProjectName() + " project");
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no contribution relations taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionStakeholdersDocument(Section section) {

		String sectionIntro = "Stakeholders have documents they possess or exchange with others to achieve their goals. Documents are represented within the rationale of the role/agent"
				+ socialDiagRef() + ".";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getStakeholdersDocumentActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {
			if (a.getTResources().size() > 0) {
				StringBuilder sb = new StringBuilder();

				List<String> nonProvidedDocs = new ArrayList<String>();
				Map<Set<Actor>, Set<String>> providedDocs = new HashMap<Set<Actor>, Set<String>>();

				for (TResource r : a.getTResources()) {
					if (r.getProvidedFrom().size() == 0) {
						nonProvidedDocs.add("%i" + r.getName() + "%");
					} else {
						Set<Actor> s = new HashSet<Actor>();
						for (Provision p : r.getProvidedFrom()) {
							s.add(p.getSource());
						}
						if (!providedDocs.containsKey(s)) {
							providedDocs.put(s, new HashSet<String>());
						}
						Set<String> resSet = providedDocs.get(s);
						resSet.add("%i" + r.getName() + "%");
						providedDocs.put(s, resSet);
					}
				}
				sb.append("%b" + a.getName() + "% ");

				if (nonProvidedDocs.size() == 1) {
					sb.append("has document " + nonProvidedDocs.get(0) + ".");
				} else if (nonProvidedDocs.size() > 1) {
					sb.append("has documents ");
					sb.append(separateListOfString(nonProvidedDocs) + ".");
				}
				if (providedDocs.size() > 0) {
					if (nonProvidedDocs.size() > 0) {
						sb.append(" Moreover it has ");
					} else {
						sb.append("has ");
					}
					List<String> subPart = new ArrayList<String>();

					for (Set<Actor> key : providedDocs.keySet()) {
						List<String> actors = new ArrayList<String>();
						List<String> documents = new ArrayList<String>(
								providedDocs.get(key));
						for (Actor act : key) {
							actors.add("%i" + act.getName() + "%");
						}
						if (documents.size() == 1) {
							subPart.add("document "
									+ separateListOfString(documents, ", ",
											", ") + " provided by "
									+ separateListOfString(actors, ", ", ", "));
						} else {
							subPart.add("documents "
									+ separateListOfString(documents, ", ",
											", ") + " provided by "
									+ separateListOfString(actors, ", ", ", "));
						}
					}
					sb.append(separateListOfString(subPart) + ".");
				}

				/*
				 * sb.append("%b" + a.getName() + "% has document");
				 * 
				 * List<String> documents = new ArrayList<String>();
				 * List<String> privdedDocuments = new ArrayList<String>();
				 * 
				 * for (TResource r : a.getTResources()) { String pr = ""; if
				 * (r.getProvidedFrom().size() > 0) { List<String> provider =
				 * new ArrayList<String>(); for (Provision p :
				 * r.getProvidedFrom()) { provider.add(p.getSource().getName());
				 * } if(provider.size()>1){ pr = ", since " +
				 * separateListOfString(provider) +
				 * " provides it with these documents"; }else{ pr = ", since " +
				 * separateListOfString(provider) +
				 * " provides it with this document"; }
				 * 
				 * } String s = "%i" + r.getName() + "%" + pr; if
				 * (r.getProvidedFrom().size() == 0) { documents.add(s); } else
				 * { privdedDocuments.add(s); } }
				 * documents.addAll(privdedDocuments); if (documents.size() > 1)
				 * sb.append("s"); sb.append(" " +
				 * separateListOfString(documents) + ".");
				 */
				phrases.add(createParagraph(sb.toString()));
			}
		}
		if (phrases.size() != 0) {

			String s = "In the " + getProjectName() + " project"
					+ socialDiagRef() + " we have:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.STAKE_DOC_TABLE)
					+ " summarises stakeholders’ %idocuments% for the "
					+ getProjectName() + " project."));
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Agent/Role" });
			headers.add(new String[] { "Document" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {
				if (a.getTResources().size() > 0) {
					PdfPCell actorCell = getContentCell(a.getName());
					actorCell.setRowspan(a.getTResources().size());
					table.addCell(actorCell);
					for (TResource r : a.getTResources()) {
						table.addCell(getContentCell(r.getName()));
						table.addCell(getContentCell(r.getDescription()));
					}
				}
			}
			section.add(table);
			addTableCaption(table, ftc.getTable(FigureConstant.STAKE_DOC_TABLE)
					+ " - Stakeholders’ documents in the " + getProjectName()
					+ " project");
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no document taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionStakeholdersDocumentAndGoals(Section section) {

		String sectionIntro = "Stakeholders’ documents are linked to their goals: they need (use) documents to achieve their goals, they modify documents while achieving their goals, and they may produce documents from achieving their goals.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getStakeholdersDocumentAndGoalsActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor a : selActor) {
			List<Goal> validGoal = new ArrayList<Goal>();
			for (Goal g : a.getGoals()) {
				if (g.getResourceNeeded().size() > 0
						|| g.getResourcesModified().size() > 0
						|| g.getResourcesProduced().size() > 0) {
					validGoal.add(g);
				}
			}
			if (validGoal.size() > 0) {

				Map<Goal, Map<TResource, String>> goalMap = new HashMap<Goal, Map<TResource, String>>();
				for (Goal g : validGoal) {
					Map<TResource, String> docMap = new HashMap<TResource, String>();
					if (g.getResourceNeeded().size() > 0) {
						for (Need n : g.getResourceNeeded()) {
							TResource doc = n.getTarget();
							if (doc != null)
								docMap.put(doc, "%ineeds%");
						}
					}
					if (g.getResourcesModified().size() > 0) {
						for (Modify m : g.getResourcesModified()) {
							TResource doc = m.getTarget();
							if (doc != null) {
								if (docMap.containsKey(doc)) {
									docMap.put(doc, docMap.get(doc)
											+ ", %imodify%");
								} else {
									docMap.put(doc, "%imodifies%");
								}
							}
						}
					}
					if (g.getResourcesProduced().size() > 0) {
						for (Produce p : g.getResourcesProduced()) {
							TResource doc = p.getTarget();
							if (doc != null) {
								if (docMap.containsKey(doc)) {
									docMap.put(doc, docMap.get(doc)
											+ "and %iproduce%");
								} else {
									docMap.put(doc, "%iproduces%");
								}
							}
						}
					}
					goalMap.put(g, docMap);
				}

				List<String> l = new ArrayList<String>();

				for (Goal g : goalMap.keySet()) {
					List<String> list = new ArrayList<String>();
					Map<TResource, String> m = goalMap.get(g);
					for (TResource r : m.keySet()) {
						if (r != null) {
							list.add(m.get(r) + " document %i" + r.getName()
									+ "%");
						} else {
							// System.out.println("NULLLLL");
						}
					}
					l.add(separateListOfString(list) + " to achieve goal %i"
							+ g.getName() + "%");
				}

				phrases.add(createParagraph("%b" + a.getName() + "% "
						+ separateListOfString(l) + "."));
			}
		}
		if (phrases.size() != 0) {
			String s = "In the "
					+ getProjectName()
					+ " project"
					+ socialDiagRef()
					+ " stakeholders’ documents and goals are related as follows:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.STAKE_DOC_GOAL_TABLE)
					+ " summarises goal-document relations for all stakeholders in the "
					+ getProjectName() + " project."));
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Agent/Role" });
			headers.add(new String[] { "Goal" });
			headers.add(new String[] { "Document" });
			headers.add(new String[] { "Relation" });
			PdfPTable table = createTable(headers);

			for (Actor a : selActor) {
				List<Goal> validGoal = new ArrayList<Goal>();
				for (Goal g : a.getGoals()) {
					if (g.getResourceNeeded().size() > 0
							|| g.getResourcesModified().size() > 0
							|| g.getResourcesProduced().size() > 0) {
						validGoal.add(g);
					}
				}
				if (validGoal.size() > 0) {
					PdfPCell actorCell = getContentCell(a.getName());
					int rowspan = 0;

					Map<Goal, Map<TResource, String>> goalMap = new HashMap<Goal, Map<TResource, String>>();
					for (Goal g : validGoal) {
						Map<TResource, String> docMap = new HashMap<TResource, String>();
						if (g.getResourceNeeded().size() > 0) {
							for (Need n : g.getResourceNeeded()) {
								TResource doc = n.getTarget();
								if (doc != null) {
									if (docMap.containsKey(doc)) {
										docMap.put(doc, docMap.get(doc)
												+ ", Need");
									} else {
										docMap.put(doc, "Need");
									}
								}
							}
						}
						if (g.getResourcesModified().size() > 0) {
							for (Modify m : g.getResourcesModified()) {
								TResource doc = m.getTarget();
								if (doc != null) {
									if (docMap.containsKey(doc)) {
										docMap.put(doc, docMap.get(doc)
												+ ", Modify");
									} else {
										docMap.put(doc, "Modify");
									}
								}
							}
						}
						if (g.getResourcesProduced().size() > 0) {
							for (Produce p : g.getResourcesProduced()) {
								TResource doc = p.getTarget();
								if (doc != null) {
									if (docMap.containsKey(doc)) {
										docMap.put(doc, docMap.get(doc)
												+ ", Produce");
									} else {
										docMap.put(doc, "Produce");
									}
								}
							}
						}
						rowspan += docMap.size();
						goalMap.put(g, docMap);
					}
					if (rowspan > 1) {
						actorCell.setRowspan(rowspan);
					}
					table.addCell(actorCell);

					for (Goal g : goalMap.keySet()) {
						Map<TResource, String> m = goalMap.get(g);
						PdfPCell goalCell = getContentCell(g.getName());
						if (m.size() > 1) {
							goalCell.setRowspan(m.size());
						}
						table.addCell(goalCell);

						for (TResource r : m.keySet()) {
							table.addCell(getContentCell(r.getName()));
							table.addCell(getContentCell(m.get(r)));
						}
					}
				}
			}
			section.add(table);
			addTableCaption(
					table,
					ftc.getTable(FigureConstant.STAKE_DOC_GOAL_TABLE)
							+ " - Relation of stakeholders’ documents to their goals");
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no relationships specified between the goals and documents of the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionOrganisationalConstraints(Section section) {

		String sectionIntro = "Apart from the security needs actors specify over their interactions, there are others, which are dictated either by the organisation, business rules and regulations, or law. In this section we enlist these constraints, together with the security requirements derived from them. Currently, the language supports these organisational constraints: %iSeparation of Duties (SoD)% and %iBinding of Duties (BoD)%. Graphically we represent these constraints using a similar notation to that used in workflows, as a circle with the %iunequal% sign within and as a circle with the %iequals% sign within, respectively. The relations are symmetric, and as such they do not have any arrows pointed to the concepts they relate (being these roles or goals).";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getOrganisationalConstraintsActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		Map<Role, List<IncompatibleDuties>> incompRole = new HashMap<Role, List<IncompatibleDuties>>();
		Map<Goal, List<IncompatibleDuties>> incompGoal = new HashMap<Goal, List<IncompatibleDuties>>();
		Map<Role, List<CompatibleDuties>> compRole = new HashMap<Role, List<CompatibleDuties>>();
		Map<Goal, List<CompatibleDuties>> compGoal = new HashMap<Goal, List<CompatibleDuties>>();

		for (Actor a : selActor) {
			if (a instanceof Role) {
				Role r = (Role) a;
				List<IncompatibleDuties> incDutiesList = new ArrayList<IncompatibleDuties>();
				for (IncompatibleDuties id : r.getIncompatibleDutiesOut()) {
					if (id.getTarget() != null
							&& id.getTarget() instanceof Role)
						incDutiesList.add(id);
				}
				for (IncompatibleDuties id : r.getIncompatibleDutiesIn()) {
					if (id.getSource() != null
							&& id.getSource() instanceof Role)
						incDutiesList.add(id);
				}
				if (incDutiesList.size() > 0) {
					incompRole.put(r, incDutiesList);
				}

				List<CompatibleDuties> compDutiesList = new ArrayList<CompatibleDuties>();
				for (CompatibleDuties bd : r.getCompatibleDutiesOut()) {
					if (bd.getTarget() != null
							&& bd.getTarget() instanceof Role)
						compDutiesList.add(bd);
				}
				for (CompatibleDuties bd : r.getCompatibleDutiesIn()) {
					if (bd.getSource() != null
							&& bd.getSource() instanceof Role)
						compDutiesList.add(bd);
				}
				if (compDutiesList.size() > 0) {
					compRole.put(r, compDutiesList);
				}
			}
			for (Goal g : a.getGoals()) {

				List<IncompatibleDuties> incDutiesList = new ArrayList<IncompatibleDuties>();
				for (IncompatibleDuties id : g.getIncompatibleDutiesOut()) {
					if (id.getTarget() != null
							&& id.getTarget() instanceof Goal)
						incDutiesList.add(id);
				}
				for (IncompatibleDuties id : g.getIncompatibleDutiesIn()) {
					if (id.getSource() != null
							&& id.getSource() instanceof Goal)
						incDutiesList.add(id);
				}
				if (incDutiesList.size() > 0) {
					incompGoal.put(g, incDutiesList);
				}

				List<CompatibleDuties> compDutiesList = new ArrayList<CompatibleDuties>();
				for (CompatibleDuties bd : g.getCompatibleDutiesOut()) {
					if (bd.getTarget() != null
							&& bd.getTarget() instanceof Goal)
						compDutiesList.add(bd);
				}
				for (CompatibleDuties bd : g.getCompatibleDutiesIn()) {
					if (bd.getSource() != null
							&& bd.getSource() instanceof Goal)
						compDutiesList.add(bd);
				}
				if (compDutiesList.size() > 0) {
					compGoal.put(g, compDutiesList);
				}
			}
		}

		/*
		 * StringBuilder sb = new StringBuilder(); for (Actor a : selActor) { if
		 * (a instanceof Role) { Role r = (Role) a; List<String> incomp = new
		 * ArrayList<String>(); for (IncompatibleDuties id :
		 * r.getIncompatibleDutiesOut()) { if (id.getTarget() != null &&
		 * id.getTarget() instanceof Role) incomp.add("%b" + ((Role)
		 * id.getTarget()).getName() + "%"); } for (IncompatibleDuties id :
		 * r.getIncompatibleDutiesIn()) { if (id.getSource() != null &&
		 * id.getSource() instanceof Role) incomp.add("%b" + ((Role)
		 * id.getSource()).getName() + "%"); } if (incomp.size() > 0) { String s
		 * = "%b" + r.getName() + "% is incompatible with " +
		 * separateListOfString(incomp) +
		 * ", since %iSoD% constraints are specified between these roles."; if
		 * (sb.length() > 0) sb.append("; "); sb.append("%b" + r.getName() +
		 * "% is incompatible with " + separateListOfString(incomp)); } } }
		 * 
		 * if (sb.length() > 0) {
		 * sb.append(", since a %iSoD% constraint is specified between these roles."
		 * ); phrases.add(createParagraph(sb.toString())); }
		 */

		for (Role incR : incompRole.keySet()) {
			List<String> incomp = new ArrayList<String>();
			for (IncompatibleDuties id : incompRole.get(incR)) {
				SeparationOfDuties r = id.getTarget();
				if (incR == r)
					r = id.getSource();
				if (r != null && r instanceof Role)
					incomp.add("%b" + ((Role) r).getName() + "%");
			}
			if (incomp.size() > 0) {
				String s = "%b"
						+ incR.getName()
						+ "% is incompatible with "
						+ separateListOfString(incomp)
						+ ", since %iSoD% constraints are specified between these roles.";
				phrases.add(createParagraph(s.toString()));
			}
		}

		for (Role compR : compRole.keySet()) {
			List<String> comp = new ArrayList<String>();
			for (CompatibleDuties cd : compRole.get(compR)) {
				BindingOfDuties r = cd.getTarget();
				if (compR == r)
					r = cd.getSource();
				if (r != null && r instanceof Role)
					comp.add("%b" + ((Role) r).getName() + "%");
			}
			if (comp.size() > 0) {
				String s = "%b"
						+ compR.getName()
						+ "% should be combined with "
						+ separateListOfString(comp)
						+ ", since %iBoD% constraints are specified between these roles.";
				phrases.add(createParagraph(s.toString()));
			}
		}

		for (Goal incG : incompGoal.keySet()) {
			List<String> incomp = new ArrayList<String>();
			for (IncompatibleDuties id : incompGoal.get(incG)) {
				SeparationOfDuties g = id.getTarget();
				if (incG == g)
					g = id.getSource();
				if (g != null && g instanceof Goal)
					incomp.add("%b" + ((Goal) g).getName() + "%");
			}
			if (incomp.size() > 0) {
				String s = "%b"
						+ incG.getName()
						+ "% is incompatible with "
						+ separateListOfString(incomp)
						+ ", given that %iSoD% constraint is specified between these goals.";
				phrases.add(createParagraph(s.toString()));
			}
		}

		for (Goal comG : compGoal.keySet()) {
			List<String> comp = new ArrayList<String>();
			for (CompatibleDuties cd : compGoal.get(comG)) {
				BindingOfDuties g = cd.getTarget();
				if (comG == g)
					g = cd.getSource();
				if (g != null && g instanceof Goal)
					comp.add("%b" + ((Goal) g).getName() + "%");
			}
			if (comp.size() > 0) {
				String s = "%b"
						+ comG.getName()
						+ "% should be combined with "
						+ separateListOfString(comp)
						+ ", given that %iBoD% constraint is specified between these goals.";
				phrases.add(createParagraph(s.toString()));
			}
		}

		if (incompRole.size() > 0 || incompGoal.size() > 0
				|| compGoal.size() > 0 || compRole.size() > 0) {
			String s = "In the "
					+ getProjectName()
					+ " project"
					+ socialDiagRef()
					+ " the following organisational constraints have been specified:";
			section.add(createParagraph(s));
			section.add(listParagraphs(phrases));

			section.add(createParagraph(ftc
					.getTable(FigureConstant.ORG_CONSTR_TABLE)
					+ " summarises the organisational constraints for the "
					+ getProjectName() + " project."));
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Organisational", "Constraint" });
			headers.add(new String[] { "Role/Goal" });
			headers.add(new String[] { "Role/Goal" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);

			if (incompRole.size() > 0) {
				int sodRoleRowspan = 0;
				for (List<IncompatibleDuties> idList : incompRole.values()) {
					sodRoleRowspan += idList.size();
				}
				PdfPCell soDRoleCell = getContentCell(new String[] { "SoD",
						"(Role - Role)" });
				soDRoleCell.setRowspan(sodRoleRowspan);
				table.addCell(soDRoleCell);

				for (Role r : incompRole.keySet()) {
					List<IncompatibleDuties> idList = incompRole.get(r);
					PdfPCell sRole = getContentCell(r.getName());
					sRole.setRowspan(idList.size());
					table.addCell(sRole);
					for (IncompatibleDuties id : idList) {
						Role tr = (Role) id.getTarget();
						if (tr == r)
							tr = (Role) id.getSource();
						table.addCell(getContentCell(tr.getName()));
						String desc = id.getDescription();
						if (desc == null)
							desc = "";
						table.addCell(getContentCell(desc));
					}
				}
			}

			if (compRole.size() > 0) {
				int bodRoleRowspan = 0;
				for (List<CompatibleDuties> lr : compRole.values()) {
					bodRoleRowspan += lr.size();
				}
				PdfPCell boDRoleCell = getContentCell(new String[] { "BoD",
						"(Role - Role)" });
				boDRoleCell.setRowspan(bodRoleRowspan);
				table.addCell(boDRoleCell);

				for (Role r : compRole.keySet()) {
					List<CompatibleDuties> rl = compRole.get(r);
					PdfPCell sRole = getContentCell(r.getName());
					sRole.setRowspan(rl.size());
					table.addCell(sRole);
					for (CompatibleDuties id : rl) {
						Role tr = (Role) id.getTarget();
						if (tr == r)
							tr = (Role) id.getSource();
						table.addCell(getContentCell(tr.getName()));
						String desc = id.getDescription();
						if (desc == null)
							desc = "";
						table.addCell(getContentCell(desc));
					}
				}
			}

			if (incompGoal.size() > 0) {
				int sodGoalRowspan = 0;
				for (List<IncompatibleDuties> idList : incompGoal.values()) {
					sodGoalRowspan += idList.size();
				}
				PdfPCell soDGoalCell = getContentCell(new String[] { "SoD",
						"(Goal - Goal)" });
				soDGoalCell.setRowspan(sodGoalRowspan);
				table.addCell(soDGoalCell);

				for (Goal g : incompGoal.keySet()) {
					List<IncompatibleDuties> rl = incompGoal.get(g);
					PdfPCell sGoal = getContentCell(g.getName());
					sGoal.setRowspan(rl.size());
					table.addCell(sGoal);
					for (IncompatibleDuties id : rl) {
						Goal tg = (Goal) id.getTarget();
						if (tg == g)
							tg = (Goal) id.getSource();
						table.addCell(getContentCell(tg.getName()));
						String desc = id.getDescription();
						if (desc == null)
							desc = "";
						table.addCell(getContentCell(desc));
					}
				}
			}

			if (compGoal.size() > 0) {
				int bodGoalRowspan = 0;
				for (List<CompatibleDuties> lr : compGoal.values()) {
					bodGoalRowspan += lr.size();
				}
				PdfPCell boDGoalCell = getContentCell(new String[] { "BoD",
						"(Goal - Goal)" });
				boDGoalCell.setRowspan(bodGoalRowspan);
				table.addCell(boDGoalCell);

				for (Goal g : compGoal.keySet()) {
					List<CompatibleDuties> rl = compGoal.get(g);
					PdfPCell sGoal = getContentCell(g.getName());
					sGoal.setRowspan(rl.size());
					table.addCell(sGoal);
					for (CompatibleDuties id : rl) {
						Goal tg = (Goal) id.getTarget();
						if (tg == g)
							tg = (Goal) id.getSource();
						table.addCell(getContentCell(tg.getName()));
						String desc = id.getDescription();
						if (desc == null)
							desc = "";
						table.addCell(getContentCell(desc));
					}
				}
			}

			/*
			 * PdfPCell soDRoleCell = getContentCell("Sod");
			 * soDRoleCell.setRowspan(sodRowspan); table.addCell(soDRoleCell);
			 * 
			 * for(Role r :sodRole.keySet()){ List<IncompatibleDuties>
			 * rl=sodRole.get(r); PdfPCell sRole = getContentCell(r.getName());
			 * sRole.setRowspan(rl.size()); table.addCell(sRole);
			 * for(IncompatibleDuties id:rl){ Role tr=(Role)id.getTarget();
			 * if(tr==r)tr=(Role)id.getSource();
			 * table.addCell(getContentCell(tr.getName())); String
			 * desc=id.getDescription(); if(desc==null)desc="";
			 * table.addCell(getContentCell(desc)); } }
			 */

			section.add(table);
			addTableCaption(table,
					ftc.getTable(FigureConstant.ORG_CONSTR_TABLE)
							+ " - Organisational Constraints");
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no organisational constraints specified."));
		}
		section.setComplete(true);

	}

	private void buildEventsSection(Section section){
		Set<Event> events=getDiagram().getAllEvents();
		

		if (events.size() > 0) {
		String sectionIntro = ftc.getTable(FigureConstant.EVENT_TABLE)+" represents all the events modeled in the project "+getProjectName()+" together with the set of elements each event threatens. Additionally, for each reported event a textual description is provided, which is extracted from the threat repository.";
		section.add(createParagraph(sectionIntro));
	
		List<String[]> headers = new ArrayList<String[]>();//
		headers.add(new String[] { "Event name" });
		headers.add(new String[] { "Threatened elements" });
		headers.add(new String[] { "Description" });
		PdfPTable table = createTable(headers);

//		List<Event> e1l=new ArrayList<Event>();
//		List<Event> e2l=new ArrayList<Event>();
//		
//		for(Event e:events){
//			if(e.getEventID()!=null && e.getEventID().length()>0)
//				e1l.add(e);
//			else{
//				e2l.add(e);
//			}
//		}
//		
		List<Event> ordredevents=new ArrayList(events);
//		for(Event e:e1l)
		Collections.sort(ordredevents, new Comparator<Event>() {

			@Override
			public int compare(Event e1, Event e2) {
				
				int i1=(e1.getEventID()!=null && e1.getEventID().length()>0)?0:1;
				int i2=(e1.getEventID()!=null && e1.getEventID().length()>0)?0:-1;
				return i1+i2;
			}
			
		});
		
		for (Event e : ordredevents) {
			
			table.addCell(getContentCell(e.getName()));
			
			if(e.getThreatedElements().size()>0){
				List<String> threat=new ArrayList<String>();
				for(Threat t:e.getThreatedElements()){
					if(t.getTarget()!=null && t.getTarget() instanceof StsElement){
						threat.add(t.getTarget().eClass().getName()+" : "+((StsElement)t.getTarget()).getName());	
					}
				}
				String[] phrases=new String[threat.size()];
				for(int i=0;i<phrases.length;i++){
					phrases[i]=threat.get(i);
				}
				table.addCell(getContentCell(phrases));
			}else{
				table.addCell(getContentCell(new String[]{"No elements are depicted","to be threatened by this event"},TABLE_CONTENT_SMALL));
			}
			
			//table.addCell(getContentCell(e.getEventID(),TABLE_CONTENT_SMALL));
			
			String desc="No concrete threats from the threat repository have been associated to this event";
			if(e.getEventID()!=null && e.getEventID().length()>0){
				desc=e.getDescription();
			}
			table.addCell(getContentCell(desc,TABLE_CONTENT_SMALL));
		}

		addTableCaption(table, ftc.getTable(FigureConstant.EVENT_TABLE) + " - Events");
		section.add(table);
		table.setComplete(true);
	} else {
		String sectionIntro = "No events have been modeled in the project "+getProjectName();
		section.add(createParagraph(sectionIntro));
	}
	section.setComplete(true);

	}

	/******************************************************************************************/

	private void buildInformationViewChapter(Chapter chapter, Document document) {

		chapter.setTitle(getChapterTitleParagraph("Information View"));
		chapter.setTriggerNewPage(true);
		String chapterIntro = "The information view gives a structured representation of the information and documents in the "
				+ getProjectName()
				+ " project. It shows what is the informational content of the documents represented in the social view. Information is represented by one or more documents (%itangible by%), and the same document can make tangible multiple information. Moreover, the information view considers composite documents (information) capturing these by means of %ipart of% relations.";
		chapter.add(createParagraph(chapterIntro));

		if (generateInformationViewDiagramSection()) {
			buildSectionInformationViewDiagram(
					chapter.addSection(getSectionTitleParagraph("Information View Diagram")),
					document);
			chapter.add(Chunk.NEXTPAGE);
		}
		if (generateModellingOwnershipSection()) {
			buildSectionModellingOwnership(chapter
					.addSection(getSectionTitleParagraph("Modelling Ownership")));
		}
		if (generateRepresentationInformationSection()) {
			buildSectionRepresentationInformation(chapter
					.addSection(getSectionTitleParagraph("Representation of Information")));
		}
		if (generateCompositionSection()) {
			buildSectionComposition(chapter
					.addSection(getSectionTitleParagraph("Compositions")));
		}
		chapter.setTriggerNewPage(true);
		chapter.setComplete(true);
	}

	private void buildSectionInformationViewDiagram(Section section,
			Document document) {

		Image i = rsp.generateScrenshot(ViewsManager.RESOURCE_VIEW);
		float reductionScale = fitImageInArea(i, document.getPageSize()
				.getWidth() - document.rightMargin() - document.leftMargin(),
				310, false);
		if (reductionScale <= getMaximumReductionScale()) {
			setAppendixAInformationDiagram(true);
		}

		StringBuilder sectionIntro = new StringBuilder();
		sectionIntro
				.append(ftc.getFigure(FigureConstant.INFORMATION_DIAGRAM)
						+ " presents the graphical representation of the information view");
		if (needInformationDiagramInAppendixA()) {
			sectionIntro.append(" (a larger picture is shown in appendix A)");
		}
		sectionIntro.append(".");
		section.add(createParagraph(sectionIntro.toString()));

		section.add(decorateImage(i,
				ftc.getFigure(FigureConstant.INFORMATION_DIAGRAM)
						+ " - Information View for the " + getProjectName()
						+ " project"));
		section.setComplete(true);
	}

	private void buildSectionModellingOwnership(Section section) {

		String sectionIntro = "The information view represents also who are the %iowners% of the information that is being manipulated through the documents that represent them in the social view.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActors = getModellingOwnershipActors();
		List<Actor> validActors = new ArrayList<Actor>();
		for (Actor a : selActors) {
			if (a.getIResources().size() > 0)
				validActors.add(a);
		}
		if (validActors.size() > 0) {
			String s = "The owners for the different information in the "
					+ getProjectName() + " project are summarised in "
					+ ftc.getTable(FigureConstant.INF_OWN_TABLE) + ".";
			section.add(createParagraph(s));

			List<String[]> headers = new ArrayList<String[]>();//
			headers.add(new String[] { "Agent/Role" });
			headers.add(new String[] { "Information" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);

			for (Actor a : validActors) {
				PdfPCell actorCell = getContentCell(a.getName());
				actorCell.setRowspan(a.getIResources().size());
				table.addCell(actorCell);
				for (Own o : a.getIResources()) {
					table.addCell(getContentCell(o.getTarget().getName()));
					table.addCell(getContentCell(o.getTarget().getDescription()));
				}

			}

			addTableCaption(table, ftc.getTable(FigureConstant.INF_OWN_TABLE)
					+ " - Information owners");
			section.add(table);
			table.setComplete(true);
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no relations taking place for the given agents/roles."));
		}
		section.setComplete(true);
		// section.setTriggerNewPage(true);
	}

	private void buildSectionRepresentationInformation(Section section) {

		String sectionIntro = "Information is represented (%imade tangible by%) by documents, which stakeholders have and exchange.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getRepresentationInformationActors();
		Set<IResource> validResource = new HashSet<IResource>();
		for (Actor a : selActor) {
			for (Own o : a.getIResources()) {
				if (o.getTarget().getTangibleElements().size() > 0)
					validResource.add(o.getTarget());
			}
		}
		if (validResource.size() > 0) {
			String s = "The documents stakeholders in the "
					+ getProjectName()
					+ " project"
					+ infDiagRef()
					+ " have and exchange with one another contain the information as summarised in "
					+ ftc.getTable(FigureConstant.RAPPRES_INFORMA_TABLE) + ":";
			section.add(createParagraph(s));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Information" });
			headers.add(new String[] { "Document" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);

			for (IResource r : validResource) {
				List<TangibleBy> tbList = new ArrayList<TangibleBy>();
				for (TangibleBy tb : r.getTangibleElements()) {
					if (tb.getTarget() != null) {
						tbList.add(tb);
					}
				}

				if (tbList.size() != 0) {
					PdfPCell infCell = getContentCell(r.getName());

					infCell.setRowspan(tbList.size());
					table.addCell(infCell);
					for (TangibleBy o : tbList) {
						table.addCell(getContentCell(o.getTarget().getName()));
						table.addCell(getContentCell(o.getDescription()));
					}
				}

			}

			addTableCaption(
					table,
					ftc.getTable(FigureConstant.RAPPRES_INFORMA_TABLE)
							+ " - Representation of Information through Documents");
			section.add(table);
			table.setComplete(true);

		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no \"Tangible By\" relations specified for the documents of the given agents/roles."));
		}
		section.setComplete(true);
	}

	private void buildSectionComposition(Section section) {

		String sectionIntro = "Documents (information) are composed of other documents (information). Composition of documents (information) is captured through %i part of % relations.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getCompositionActors();
		Set<Resource> validResource = new HashSet<Resource>();
		for (Actor a : selActor) {
			for (Own o : a.getIResources()) {
				if (o.getTarget().getSubParts().size() > 0)
					validResource.add(o.getTarget());
			}
			for (TResource r : a.getTResources()) {
				if (r.getSubParts().size() > 0)
					validResource.add(r);
			}
		}

		if (validResource.size() > 0) {

			String s = ftc.getTable(FigureConstant.COMPOSITION_TABLE)
					+ " summarises the documents and information in the "
					+ getProjectName()
					+ " project"
					+ infDiagRef()
					+ ", showing how they are composed and describing the composition.";
			section.add(createParagraph(s));

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(new String[] { "Information", "/ Document" });
			headers.add(new String[] { "Composition" });
			headers.add(new String[] { "Description" });
			PdfPTable table = createTable(headers);

			for (Resource r : validResource) {
				List<PartOf> pofList = new ArrayList<PartOf>();
				for (PartOf p : r.getSubParts()) {
					if (p.getTarget() != null)
						pofList.add(p);
				}
				if (pofList.size() > 0) {
					PdfPCell infCell = getContentCell(r.getName());
					infCell.setRowspan(pofList.size());
					table.addCell(infCell);
					for (PartOf p : pofList) {
						table.addCell(getContentCell(p.getSource().getName()));
						table.addCell(getContentCell(p.getDescription()));
					}
				}
			}

			addTableCaption(table,
					ftc.getTable(FigureConstant.COMPOSITION_TABLE)
							+ " - Information and documents composition");
			section.add(table);
			table.setComplete(true);

		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no composite documents or information."));
		}
		section.setComplete(true);
	}

	/******************************************************************************************/

	private void buildAuthorisationViewChapter(Chapter chapter,
			Document document) {

		chapter.setTitle(getChapterTitleParagraph("Authorisation View"));

		String chapterIntro = "The authorisation view shows the permission flow from a stakeholder to another, that is, the authorisations stakeholders grant to others about information, specifying the operations the others can perform over the information. Apart from granting authority on performing operations, a higher authority can be granted, that of further authorising other actors.";
		chapter.add(createParagraph(chapterIntro));
		chapterIntro = "Authorisations start from the information owner. Therefore, in the authorisation view, ownership is preserved and inherited from the information view.";
		chapter.add(createParagraph(chapterIntro));

		if (generateAuthorisationViewDiagramSection()) {
			buildSectionAuthorisationDiagram(
					chapter.addSection(getSectionTitleParagraph("Authorisation View Diagram")),
					document);
			chapter.add(Chunk.NEXTPAGE);
		}
		if (generateAuthorisationFlowSection()) {
			buildSectionAuthorisationFlow(chapter
					.addSection(getSectionTitleParagraph("Authorisation Flow")));
		}
		chapter.setTriggerNewPage(true);
		chapter.setComplete(true);
	}

	private void buildSectionAuthorisationDiagram(Section section,
			Document document) {

		Image i = rsp.generateScrenshot(ViewsManager.AUTHORIZATION_VIEW);
		float reductionScale = fitImageInArea(i, document.getPageSize()
				.getWidth() - document.rightMargin() - document.leftMargin(),
				310, false);
		if (reductionScale <= getMaximumReductionScale()) {
			setAppendixASocialDiagram(true);
		}

		StringBuilder sectionIntro = new StringBuilder();
		sectionIntro
				.append(ftc.getFigure(FigureConstant.AUTH_DIAGRAM)
						+ " presents the graphical representation of the Authorisation view");
		if (needAuthorisationDiagramInAppendixA()) {
			sectionIntro
					.append(" (a larger picture is rappresented in appendix A)");
		}
		sectionIntro.append(".");
		section.add(createParagraph(sectionIntro.toString()));

		section.add(decorateImage(i, ftc.getFigure(FigureConstant.AUTH_DIAGRAM)
				+ " - Authorisation View for the " + getProjectName()
				+ " project"));
		section.setComplete(true);
	}

	private void buildSectionAuthorisationFlow(Section section) {

		String sectionIntro = "In this section are described for each role/agent, the authorisations it passes to others and what authorisations it receives from other roles/agents.";
		section.add(createParagraph(sectionIntro));

		List<Actor> selActor = getAuthorisationFlowActors();
		List<com.itextpdf.text.List> lists = new ArrayList<com.itextpdf.text.List>();

		for (Actor a : selActor) {
			StringBuilder sbOut = new StringBuilder();
			for (Authorisation au : a.getOutgoingAuthorisations()) {
				if (isValidAuth(au)) {
					if (sbOut.length() > 0)
						sbOut.append(", and ");
					sbOut.append("authorises %i" + au.getTarget().getName()
							+ "% to " + getOperations(au) + " information ");
					List<String> resList = new ArrayList<String>();
					for (IResource r : au.getResources())
						resList.add("%i" + r.getName() + "%");
					sbOut.append(separateListOfString(resList) + ", ");

					List<String> goalList = new ArrayList<String>();
					if (au.getGoals().size() > 0) {
						sbOut.append("in the scope of goal");
						if (au.getGoals().size() > 1)
							sbOut.append("s");
						// sbOut.append(" ");
						for (Goal g : au.getGoals()) {
							goalList.add("%i" + g.getName() + "%");
						}
						sbOut.append(" " + separateListOfString(goalList)
								+ ", ");
					}
					if (au.getTimesTransferable() == 0) {
						sbOut.append("%iwithout% passing");
					} else {
						sbOut.append("%ipassing%");
					}
					sbOut.append(" the right to further authorising other actors");

					String[] res = new String[resList.size()];
					for (int i = 0; i < resList.size(); i++)
						res[i] = resList.get(i);
					String[] goals = new String[goalList.size()];
					for (int i = 0; i < goalList.size(); i++)
						goals[i] = goalList.get(i);
				}
			}

			StringBuilder sbInc = new StringBuilder();
			for (Authorisation au : a.getIncomingAuthorisations()) {
				if (isValidAuth(au)) {
					if (sbInc.length() > 0)
						sbInc.append(", and ");
					sbInc.append("is authorised by %i"
							+ au.getSource().getName() + "% to "
							+ getOperations(au) + " information ");

					List<String> resList = new ArrayList<String>();

					for (IResource r : au.getResources())
						resList.add("%i" + r.getName() + "%");
					sbInc.append(separateListOfString(resList) + ", ");

					List<String> goalList = new ArrayList<String>();
					if (au.getGoals().size() > 0) {
						sbInc.append("in the scope of goal");
						if (au.getGoals().size() > 1)
							sbOut.append("s");
						// sbInc.append(" ");
						for (Goal g : au.getGoals()) {
							goalList.add("%i" + g.getName() + "%");
						}
						sbInc.append(" " + separateListOfString(goalList)
								+ ", ");
					}
					if (au.getTimesTransferable() == 0) {
						sbInc.append("%iwithout% having");
					} else {
						sbInc.append("%ihaving%");
					}
					sbInc.append(" the right to further authorising other actors");

					String[] res = new String[resList.size()];
					for (int i = 0; i < resList.size(); i++)
						res[i] = resList.get(i);
					String[] goals = new String[goalList.size()];
					for (int i = 0; i < goalList.size(); i++)
						goals[i] = goalList.get(i);
				}
			}
			if (sbOut.length() > 0 || sbInc.length() > 0) {

				String type = "%iAgent% ";
				if (a instanceof Role)
					type = "%iRole% ";

				Paragraph actorName = createParagraph(type + "%b" + a.getName()
						+ "%:");
				actorName.setSpacingAfter(5);

				com.itextpdf.text.List list = new com.itextpdf.text.List(
						com.itextpdf.text.List.UNORDERED);
				list.setListSymbol("\u2022" + " ");
				list.setSymbolIndent(10f);
				list.add(new ListItem(actorName));

				if (sbOut.length() > 0) {
					com.itextpdf.text.List sublist = new com.itextpdf.text.List(
							com.itextpdf.text.List.UNORDERED);
					sublist.setListSymbol("- ");
					sublist.setSymbolIndent(30f);
					// Paragraph p=createParagraph("%b" + a.getName() + "% " +
					// sbOut.toString() + ".");
					Paragraph p = createParagraph(sbOut.toString() + ".");

					if (sbInc.length() > 0) {
						p.setSpacingAfter(10);
					}

					sublist.add(new ListItem(p));
					list.add(sublist);
				}
				if (sbInc.length() > 0) {
					com.itextpdf.text.List sublist = new com.itextpdf.text.List(
							com.itextpdf.text.List.UNORDERED);
					sublist.setListSymbol("- ");
					sublist.setSymbolIndent(30f);
					// Paragraph p=createParagraph("%b" + a.getName() + "% " +
					// sbInc.toString() + ".");
					Paragraph p = createParagraph(sbInc.toString() + ".");
					sublist.add(new ListItem(p));
					list.add(sublist);
				}

				lists.add(list);
			}
		}
		if (lists.size() > 0) {
			String s = "In the " + getProjectName() + " project" + autDiagRef()
					+ " the authorisations for each role/agent are:";
			section.add(createParagraph(s));
			for (com.itextpdf.text.List list : lists) {
				section.add(list);
			}
		} else {
			section.add(createParagraph("In the "
					+ getProjectName()
					+ " project there are no authorisation taking place for the given agents/roles."));
		}
		section.setComplete(true);
	}

	/******************************************************************************************/

	private void buildSecurityRequirementsChapter(Chapter chapter,
			Document document) {

		chapter.setTitle(getChapterTitleParagraph("Security Requirements"));

		String chapterIntro = "This section provides the list of security requirements derived for the "
				+ getProjectName() + " project.";
		chapter.add(createParagraph(chapterIntro));

		chapterIntro = "The list of security requirements shows the roles/agents that are %iresponsible% to satisfy them, so that stakeholders know what they have to bring about in order to satisfy the corresponding security needs. Security requirements also include the authorisations granted by stakeholders to other stakeholders.";
		chapter.add(createParagraph(chapterIntro));

		chapterIntro = "%iSecurity needs% are expressed mainly over goal delegations, document provisions and authorisations. Therefore, the list of security requirements is derived from every type of security need. Moreover, the organisational constraints specify further %ineeds% over roles and goal, leading to the generation of other security requirements.";
		chapter.add(createParagraph(chapterIntro));

		chapterIntro = "Finally, the %irequester% actors are represented to capture the actors requiring certain security needs to be brought about.";
		chapter.add(createParagraph(chapterIntro));

		buildSecurityRequirementsChapterContent(chapter);

		chapter.setTriggerNewPage(true);
		chapter.setComplete(true);
	}

	private void buildSecurityRequirementsChapterContent(Chapter chapter) {

		List<Actor> selActors = getSecurityRequirementsActors();
		List<Paragraph> phrases = new ArrayList<Paragraph>();

		for (Actor act : selActors) {

			StringBuilder sbDel = new StringBuilder();
			for (Delegation d : act.getOutgoingDelegations()) {

				if (selActors.contains(d.getTarget())) {

					if ((d.getTimesTransferable() >= 0
							|| (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION || d
									.getRepudiationType() == RepudiationType.DELEGATEEE_REPUDIATION) || d
								.getRedundancyType() != RedundancyType.NO_REDUNDANCY)) {
						if (sbDel.length() > 0)
							sbDel.append("; while it ");
						sbDel.append("requires %i" + d.getTarget().getName()
								+ "% ");

						List<String> req = new ArrayList<String>();

						if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
							switch (d.getRedundancyType()) {
							case TRUE_SINGLE:
								req.add("%isingle-actor-true-redundancy% (true_rs)");
								break;
							case TRUE_MULTI:
								req.add("%imulti-actor-true-redundancy% (true_rm)");
								break;
							case FALLBACK_SINGLE:
								req.add("%isingle-actor-fallback-redundancy% (multi_rs)");
								break;
							case FALLBACK_MULTI:
								req.add("%imulti-actor-fallback-redundancy% (multi_rm)");
								break;
							}
						}
						if (d.getTimesTransferable() >= 0) {
							req.add("%ino-delegation% on goal %i"
									+ d.getSourceGoal().getName() + "%");
						}

						if (d.getRepudiationType() == RepudiationType.DELEGATEEE_REPUDIATION
								|| d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION) {
							req.add("%inon-repudiation-of-acceptance% "
									+ "of the delegation of goal %i"
									+ d.getSourceGoal().getName() + "%");
						}

						if (d.isAvailability()) {
							req.add("an %iAvailability% level of "
									+ d.getAvailabilityValue() + "%");
						}
						if (d.isTrustworthiness()) {
							req.add("a %iTrustworthiness% level of "
									+ d.getTrustworthinessValue() + "");
						}

						if (req.size() > 0) {
							StringBuilder sb = new StringBuilder();
							sb.append(separateListOfString(req));
							sb.append(", when delegating %i"
									+ d.getSourceGoal().getName() + "% to %i"
									+ d.getTarget().getName() + "%");
							sbDel.append(sb.toString());
						}
					}

					if (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION
							|| d.getRepudiationType() == RepudiationType.DELEGATOR_REPUDIATION) {
						if (sbDel.length() > 0)
							sbDel.append("; while it ");
						sbDel.append("is required by %i"
								+ d.getTarget().getName() + "% ");
						sbDel.append("%inon-repudiation-of-delegation% "
								+ "of the delegation of goal %i"
								+ d.getSourceGoal().getName() + "%, ");
						sbDel.append("when delegating %i"
								+ d.getSourceGoal().getName() + "% to %i"
								+ d.getTarget().getName() + "%");
					}
				}
			}

			if (sbDel.length() > 0) {
				phrases.add(createParagraph("%b" + act.getName() + "% "
						+ sbDel.toString() + "."));
			}

			StringBuilder sbProv = new StringBuilder();
			for (Provision p : act.getOutgoingProvisions()) {
				if (selActors.contains(p.getTarget())) {
					if (p.isAvailability()) {
						sbProv.append("requires %i" + p.getTarget().getName()
								+ "% an availability level of "
								+ p.getAvailabilityValue() + "% for document ");
						sbProv.append("%i" + p.getSourceResource().getName()
								+ "%, when providing %i"
								+ p.getTarget().getName() + "% this document");
					}
					if (p.isIntegrity()) {
						if (sbProv.length() > 0)
							sbProv.append("; while it ");
						sbProv.append("is required by %i"
								+ p.getTarget().getName()
								+ "% to ensure integrity of transmission over the provision of document "
								+ p.getSourceResource().getName());
						// sbProv.append("%i" + p.getSourceResource().getName()
						// + "%, when providing document %i" +
						// p.getTarget().getName() + "% ");
					}if (p.isConfidentiality()) {
						if (sbProv.length() > 0)
							sbProv.append("; while it ");
						sbProv.append("is required by %i"
								+ p.getTarget().getName()
								+ "% to ensure confidentiality of transmission over the provision of document "
								+ p.getSourceResource().getName());
					}
				}
			}
			if (sbProv.length() > 0) {
				phrases.add(createParagraph("%b" + act.getName() + "% "
						+ sbProv.toString() + "."));
			}

			StringBuilder sbAut = new StringBuilder();
			for (Authorisation a : act.getOutgoingAuthorisations()) {
				if (selActors.contains(a.getTarget())) {
					if (a.getResources().size() > 0
							&& (a.isDistribution() || a.isModification()
									|| a.isUsage() || a.isProduce())
							&& !(a.isDistribution() && a.isModification()
									&& a.isUsage() && a.isProduce())) {
						List<String> comm = new ArrayList<String>();
						List<String> opera = new ArrayList<String>();
						List<String> goals = new ArrayList<String>();
						List<String> res = new ArrayList<String>();
						for (IResource r : a.getResources()) {
							res.add("%i" + r.getName() + "%");
						}
						for (Goal g : a.getGoals()) {
							goals.add("%i" + g.getName() + "%");
						}

						if (!a.isUsage()) {
							comm.add("%i" + "non-usage" + "%");
						} else {
							opera.add("%i" + "use" + "%");
						}
						if (!a.isModification()) {
							comm.add("%i" + "non-modification" + "%");
						} else {
							opera.add("%i" + "modify" + "%");
						}
						if (!a.isProduce()) {
							comm.add("%i" + "non-production" + "%");
						} else {
							opera.add("%i" + "produce" + "%");
						}
						if (!a.isDistribution()) {
							comm.add("%i" + "non-disclosure" + "%");
						} else {
							opera.add("%i" + "distribute" + "%");
						}

						if (sbAut.length() > 0)
							sbAut.append("; while it ");
						sbAut.append("requires %i" + a.getTarget().getName()
								+ "% the ");
						sbAut.append(separateListOfString(comm)
								+ " of information");
						if (res.size() > 1)
							sbAut.append("s");
						sbAut.append(" " + separateListOfString(res) + ", ");

						if (a.getGoals().size() > 0) {
							sbAut.append("and %ineed-to-know% of these pieces of information");
							if (res.size() > 1)
								sbAut.append("s");
							sbAut.append(" in the scope of goal");
							if (goals.size() > 1)
								sbAut.append("s");
							sbAut.append(" " + separateListOfString(goals)
									+ ", ");
						}
						sbAut.append("when autorising %i"
								+ a.getTarget().getName() + "% to "
								+ separateListOfString(opera) + " ");
						sbAut.append(separateListOfString(res));
						if (a.getGoals().size() > 0) {
							sbAut.append(" in the scope of goal");
							if (goals.size() > 1)
								sbAut.append("s");
							sbAut.append(" " + separateListOfString(goals));
						}
					}
				}
			}
			if (sbAut.length() > 0) {
				phrases.add(createParagraph("%b" + act.getName() + "% "
						+ sbAut.toString() + "."));
			}
		}

		List<Paragraph> sodRole = new ArrayList<Paragraph>();
		List<Paragraph> sodGoal = new ArrayList<Paragraph>();
		List<Paragraph> bodGoal = new ArrayList<Paragraph>();

		for (Actor a : selActors) {
			if (a instanceof Role) {
				Role r = (Role) a;
				for (IncompatibleDuties id : r.getIncompatibleDutiesOut()) {
					if (id.getTarget() != null
							&& id.getTarget() instanceof Role) {
						String r1 = "%i" + r.getName() + "%";
						String r2 = "%i" + ((Role) id.getTarget()).getName()
								+ "%";
						String s = "%iAny agent% playing "
								+ r1
								+ "  is required not to play "
								+ r2
								+ ", and any agent playing "
								+ r2
								+ " is required not to play "
								+ r1
								+ ", given that an SoD constraint is specified between "
								+ r1 + " and " + r2 + ".";
						sodRole.add(createParagraph(s));
					}
				}
			}
			for (Goal g : a.getGoals()) {
				for (IncompatibleDuties id : g.getIncompatibleDutiesOut()) {
					if (id.getTarget() != null
							&& id.getTarget() instanceof Goal) {
						String g1 = "%i" + g.getName() + "%";
						String g2 = "%i" + ((Goal) id.getTarget()).getName()
								+ "%";
						String s = "%iAny agent% achieving "
								+ g1
								+ " is required not to achieve "
								+ g2
								+ ", and any agent achieving "
								+ g2
								+ " is required not to achieve "
								+ g1
								+ ", when specifying a SoD constraint between these goals.";
						sodGoal.add(createParagraph(s));
					}
				}
				for (CompatibleDuties bd : g.getCompatibleDutiesOut()) {
					if (bd.getTarget() != null
							&& bd.getTarget() instanceof Goal) {
						String g1 = "%i" + g.getName() + "%";
						String g2 = "%i" + ((Goal) bd.getTarget()).getName()
								+ "%";
						String s = "%iAny agent% achieving "
								+ g1
								+ " is required to achieve "
								+ g2
								+ ", and any agent achieving "
								+ g2
								+ " is required not to achieve "
								+ g1
								+ ", when specifying a CoD constraint between these goals.";
						bodGoal.add(createParagraph(s));
					}
				}
			}
		}

		phrases.addAll(sodRole);
		phrases.addAll(sodGoal);
		phrases.addAll(bodGoal);

		if (phrases.size() > 0) {
			String s = "The security requirements for the " + getProjectName()
					+ " project (" + ftc.getTable(FigureConstant.COMM_TABLE)
					+ ") are:";
			chapter.add(createParagraph(s));
			chapter.add(listParagraphs(phrases));

			buildSecurityRequirementsChapterContentTable(chapter);
		} else {

		}

	}

	private void buildSecurityRequirementsChapterContentTable(Chapter chapter) {

		List<Actor> selActors = getSecurityRequirementsActors();

		List<String[]> headers = new ArrayList<String[]>();//
		headers.add(new String[] { "Responsible" });
		headers.add(new String[] { "Security Requirement" });
		headers.add(new String[] { "Requester" });
		headers.add(new String[] { "Description" });
		PdfPTable table = createTable(headers);

		for (Actor act : selActors) {

			List<PdfPCell> cells = new ArrayList<PdfPCell>();

			for (Delegation d : act.getIncomingDelegations()) {
				if (selActors.contains(d.getSource())
						&& (d.getTimesTransferable() >= 0
								|| d.getRepudiationType() != RepudiationType.NO_REPUDIATION
								|| d.getRedundancyType() != RedundancyType.NO_REDUNDANCY || d
									.isAvailability())) {
					if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
						String red = "";
						switch (d.getRedundancyType()) {
						case TRUE_SINGLE:
							red = "single-actor-true-redundancy";
							break;
						case TRUE_MULTI:
							red = "multi-actor-true-redundancy";
							break;
						case FALLBACK_SINGLE:
							red = "single-actor-fallback-redundancy";
							break;
						case FALLBACK_MULTI:
							red = "single-actor-fallback-redundancy";
							break;
						}

						cells.add(getContentCell(new String[] { red,
								"(" + d.getSourceGoal().getName() + ")" }));
						cells.add(getContentCell(d.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(d.getTarget().getName() + " requires " + red
								+ " for goal " + d.getSourceGoal().getName()
								+ ",");
						sb.append("when delegating "
								+ d.getSourceGoal().getName() + " to "
								+ d.getTarget().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (d.getTimesTransferable() >= 0) {
						String s = "no-delegation";
						cells.add(getContentCell(new String[] { s,
								"(" + d.getSourceGoal().getName() + ")" }));
						cells.add(getContentCell(d.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(d.getTarget().getName() + " requires " + s
								+ " for goal " + d.getSourceGoal().getName()
								+ ",");
						sb.append("when delegating "
								+ d.getSourceGoal().getName() + " to "
								+ d.getTarget().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION
							|| d.getRepudiationType() == RepudiationType.DELEGATEEE_REPUDIATION) {
						cells.add(getContentCell(new String[] {
								"non-repudiation-of-acceptance",
								"(delegated(" + d.getSource().getName() + ","
										+ d.getTarget().getName() + ","
										+ d.getSourceGoal().getName() + "))" }));
						cells.add(getContentCell(d.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(d.getSource().getName()
								+ " require non-repudiation-of-acceptance for goal "
								+ d.getSourceGoal().getName() + ",");
						sb.append("when delegating "
								+ d.getSourceGoal().getName() + " to "
								+ d.getTarget().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (d.isAvailability()) {
						cells.add(getContentCell(new String[] {
								"availability",
								"(" + d.getSourceGoal().getName() + ","
										+ d.getAvailabilityValue() + "%)" }));

						cells.add(getContentCell(d.getSource().getName()));
						StringBuilder sb = new StringBuilder();
						sb.append(d.getSource().getName() + " require "
								+ d.getTarget().getName()
								+ " to assure an availability level of "
								+ d.getAvailabilityValue() + "% for goal "
								+ d.getSourceGoal().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
				}
			}

			for (Delegation d : act.getOutgoingDelegations()) {
				if (selActors.contains(d.getTarget())
						&& (d.getRepudiationType() != RepudiationType.NO_REPUDIATION || d
								.isTrustworthiness())) {

					if (d.getRepudiationType() == RepudiationType.DUAL_REPUDIATION
							|| d.getRepudiationType() == RepudiationType.DELEGATOR_REPUDIATION) {
						cells.add(getContentCell(new String[] {
								"non-repudiation-of-delegation",
								"(delegated(" + d.getSource().getName() + ","
										+ d.getTarget().getName() + ","
										+ d.getSourceGoal().getName() + "))" }));
						cells.add(getContentCell(d.getTarget().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(d.getTarget().getName()
								+ " require non-repudiation-of-delegation for goal "
								+ d.getSourceGoal().getName() + ",");
						sb.append("when delegated "
								+ d.getSourceGoal().getName() + " by "
								+ d.getSource().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (d.isTrustworthiness()) {
						cells.add(getContentCell(new String[] {
								"trustworthiness",
								"(delegatedTo(" + d.getTarget().getName()
										+ ", trustworthiness level: "
										+ d.getTrustworthinessValue() + "))" }));

						cells.add(getContentCell(d.getSource().getName()));
						StringBuilder sb = new StringBuilder();
						sb.append(d.getSource().getName()
								+ " will delegate to "
								+ d.getTarget().getName()
								+ " that have trustwhorthiness level only grater than "
								+ d.getTrustworthinessValue());
						cells.add(getContentCell(sb.toString()));
					}
				}
			}

			for (Provision p : act.getIncomingProvisions()) {
				if (selActors.contains(p.getTarget()) && (p.isAvailability())) {

					if (p.isAvailability()) {
						cells.add(getContentCell(new String[] {
								"availability",
								"(" + p.getSourceResource().getName() + ","
										+ p.getAvailabilityValue() + "%)" }));

						cells.add(getContentCell(p.getSource().getName()));
						StringBuilder sb = new StringBuilder();
						sb.append(p.getSource().getName() + " require "
								+ p.getTarget().getName()
								+ " to assure an availability level of "
								+ p.getAvailabilityValue() + "% for document "
								+ p.getSourceResource().getName() + ".");
						cells.add(getContentCell(sb.toString()));
					}
				}
			}

			for (Provision p : act.getOutgoingProvisions()) {
				if (selActors.contains(p.getTarget()) && (p.isIntegrity() || p.isConfidentiality())) {

					if (p.isIntegrity()) {
						cells.add(getContentCell(new String[] {
								"integrity",
								"(provided(" + p.getSource().getName() + ","
										+ p.getTarget().getName() + ","
										+ p.getSourceResource().getName(), "))" }));

						cells.add(getContentCell(p.getSource().getName()));
						StringBuilder sb = new StringBuilder();
						String a = p.getTarget().getName();
						String b = p.getSource().getName();
						String d = p.getSourceResource().getName();
						sb.append(a
								+ " requires "
								+ b
								+ " to ensure integrity of transmission over the provision of document "
								+ d + ", when " + b + " provides " + d + " to "
								+ a + ".");
						cells.add(getContentCell(sb.toString()));
					}
					
					if (p.isConfidentiality()) {
						cells.add(getContentCell(new String[] {
								"confidentiality",
								"(provided(" + p.getSource().getName() + ","
										+ p.getTarget().getName() + ","
										+ p.getSourceResource().getName(), "))" }));

						cells.add(getContentCell(p.getSource().getName()));
						StringBuilder sb = new StringBuilder();
						String a = p.getTarget().getName();
						String b = p.getSource().getName();
						String d = p.getSourceResource().getName();
						sb.append(a
								+ " requires "
								+ b
								+ " to ensure confidentiality of transmission over the provision of document "
								+ d + ", when " + b + " provides " + d + " to "
								+ a + ".");
						cells.add(getContentCell(sb.toString()));
					}
				}
			}

			for (Authorisation a : act.getIncomingAuthorisations()) {
				if (selActors.contains(a.getSource())
						&& a.getResources().size() > 0
						&& (a.isDistribution() || a.isModification()
								|| a.isUsage() || a.isProduce())) {

					List<String> res = new ArrayList<String>();
					List<String> goal = new ArrayList<String>();
					for (IResource r : a.getResources()) {
						res.add(r.getName());
					}
					for (Goal g : a.getGoals()) {
						goal.add(g.getName());
					}

					if (!a.isUsage()) {
						String s = "non-usage";
						cells.add(getContentCell(new String[] { s,
								"(" + separateListOfString(res, ",", ",") + ")" }));
						cells.add(getContentCell(a.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(a.getSource().getName() + " requires "
								+ a.getTarget().getName() + " " + s
								+ " of Information ");
						sb.append(separateListOfString(res) + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (!a.isModification()) {
						String s = "non-modification";
						cells.add(getContentCell(new String[] { s,
								"(" + separateListOfString(res, ",", ",") + ")" }));
						cells.add(getContentCell(a.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(a.getSource().getName() + " requires "
								+ a.getTarget().getName() + " " + s
								+ " of Information ");
						sb.append(separateListOfString(res) + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (!a.isProduce()) {
						String s = "non-production";
						cells.add(getContentCell(new String[] { s,
								"(" + separateListOfString(res, ",", ",") + ")" }));
						cells.add(getContentCell(a.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(a.getSource().getName() + " requires "
								+ a.getTarget().getName() + " " + s
								+ " of Information ");
						sb.append(separateListOfString(res) + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (!a.isDistribution()) {
						String s = "non-disclosure";
						cells.add(getContentCell(new String[] { s,
								"(" + separateListOfString(res, ",", ",") + ")" }));
						cells.add(getContentCell(a.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(a.getSource().getName() + " requires "
								+ a.getTarget().getName() + " " + s
								+ " of Information ");
						sb.append(separateListOfString(res) + ".");
						cells.add(getContentCell(sb.toString()));
					}
					if (a.getGoals().size() > 0) {
						String s = "need-to-know";
						cells.add(getContentCell(new String[] {
								s,
								"(" + separateListOfString(res, ",", ",") + ")",
								"(" + separateListOfString(goal, ",", ",")
										+ ")" }));
						cells.add(getContentCell(a.getSource().getName()));

						StringBuilder sb = new StringBuilder();
						sb.append(a.getSource().getName() + " requires "
								+ a.getTarget().getName() + " " + s
								+ " of Information ");
						sb.append(separateListOfString(res)
								+ ", in the scope of goal "
								+ separateListOfString(goal) + ".");
						cells.add(getContentCell(sb.toString()));
					}
				}
			}

			PdfPCell respCell = getContentCell(act.getName());
			if (cells.size() > 0) {
				if (cells.size() >= 3) {
					respCell.setRowspan(cells.size() / 3);
				}
				table.addCell(respCell);
				for (PdfPCell c : cells) {
					table.addCell(c);
				}
			}
		}

		List<PdfPCell> cells = new ArrayList<PdfPCell>();
		for (Actor act : selActors) {

			if (act instanceof Role) {
				Role r = (Role) act;

				for (IncompatibleDuties id : r.getIncompatibleDutiesIn()) {
					cells.add(getContentCell(new String[] {
							"not-play-both",
							"(" + ((Role) id.getSource()).getName() + ","
									+ ((Role) id.getTarget()).getName() + ")" }));
					cells.add(getContentCell("Any agent that play "
							+ r.getName()
							+ " or "
							+ ((Role) id.getSource()).getName()
							+ ", is required not to play (adopt) the other role too."));

				}
			}

			for (Goal g : act.getGoals()) {
				for (IncompatibleDuties id : g.getIncompatibleDutiesIn()) {
					cells.add(getContentCell(new String[] {
							"not-achieve-both",
							"(" + g.getName() + ","
									+ ((Goal) id.getSource()).getName() + ")" }));
					cells.add(getContentCell("Any agent that achieves "
							+ g.getName()
							+ " or "
							+ ((Goal) id.getSource()).getName()
							+ ", is required not to achieve the other goal too."));

				}
				for (CompatibleDuties cd : g.getCompatibleDutiesIn()) {
					cells.add(getContentCell(new String[] {
							"achieve-in-combination",
							"(" + g.getName() + ","
									+ ((Goal) cd.getSource()).getName() + ")" }));
					cells.add(getContentCell("Any agent that achieves one of "
							+ g.getName() + " or "
							+ ((Goal) cd.getSource()).getName()
							+ ", is required to achieve the other goal too."));

				}
			}
			/*
			 * for(Goal g:act.getGoals()){ for (IncompatibleDuties id :
			 * g.getIncompatibleDutiesIn()) { cells.add(getContentCell(new
			 * String[] {"achieves(a,"+g.getName()+")",
			 * "then not achieve(a,"+((Goal)id.getSource()).getName()+")"}));
			 * cells
			 * .add(getContentCell("Org requires any agent a that achieves "
			 * +g.getName
			 * ()+" not to achieve "+((Goal)id.getSource()).getName())); } for
			 * (IncompatibleDuties id : g.getIncompatibleDutiesOut()) {
			 * cells.add(getContentCell(new String[]
			 * {"achieves(a,"+g.getName()+")",
			 * "then not achieve(a,"+((Goal)id.getTarget()).getName()+")"}));
			 * cells
			 * .add(getContentCell("Org requires any agent a that achieves "
			 * +g.getName
			 * ()+" not to achieve "+((Goal)id.getTarget()).getName())); }
			 * 
			 * for (CompatibleDuties cd : g.getCompatibleDutiesIn()) {
			 * cells.add(getContentCell(new String[]
			 * {"achieves(a,"+g.getName()+")",
			 * "then achieve(a,"+((Goal)cd.getSource()).getName()+")"}));
			 * cells.add
			 * (getContentCell("Org requires any agent a that achieves "
			 * +g.getName
			 * ()+" to achieve "+((Goal)cd.getSource()).getName()+" as well"));
			 * } for (CompatibleDuties cd : g.getCompatibleDutiesOut()) {
			 * cells.add(getContentCell(new String[]
			 * {"achieves(a,"+g.getName()+")",
			 * "then achieve(a,"+((Goal)cd.getTarget()).getName()+")"}));
			 * cells.add
			 * (getContentCell("Org requires any agent a that achieves "
			 * +g.getName
			 * ()+" to achieve "+((Goal)cd.getTarget()).getName()+" as well"));
			 * } }
			 */
		}
		PdfPCell respCell = getContentCell("\"Any agents\"");
		if (cells.size() > 0) {
			if (cells.size() >= 2) {
				respCell.setRowspan(cells.size() / 2);
			}
			table.addCell(respCell);
			for (int i = 0; i < cells.size(); i++) {
				table.addCell(cells.get(i));
				table.addCell(getContentCell("-"));
				table.addCell(cells.get(++i));
			}
		}

		addTableCaption(table, ftc.getTable(FigureConstant.COMM_TABLE)
				+ " - Security Requirements for the " + getProjectName()
				+ " Project");
		chapter.add(table);
		table.setComplete(true);

		boolean addAuthTable = false;

		// Chapter section = chapter;
		List<String[]> headers2 = new ArrayList<String[]>();
		headers2.add(new String[] { "Authorisor" });
		headers2.add(new String[] { "Information" });
		headers2.add(new String[] { "Goal" });
		headers2.add(new String[] { "Operation" });
		headers2.add(new String[] { "Authorisee" });
		headers2.add(new String[] { "Description" });
		PdfPTable table2 = createTable(headers2);

		for (Actor a : selActors) {

			List<Authorisation> validAuth = new ArrayList<Authorisation>();
			for (Authorisation au : a.getOutgoingAuthorisations()) {
				if (isValidAuth(au))
					validAuth.add(au);
			}

			if (validAuth.size() > 0) {
				addAuthTable = true;
				PdfPCell actorCell = getContentCell(a.getName());
				actorCell.setRowspan(validAuth.size());
				table2.addCell(actorCell);
				for (Authorisation au : validAuth) {

					Phrase p = null;
					for (int i = 0; i < au.getResources().size(); i++) {
						if (i == 0)
							p = new Phrase(au.getResources().get(i).getName(),
									TABLE_CONTENT_SMALL);
						else {
							p.add(Chunk.NEWLINE);
							p.add(new Phrase(
									au.getResources().get(i).getName(),
									TABLE_CONTENT_SMALL));
						}
					}

					/*
					 * com.itextpdf.text.List resList = new
					 * com.itextpdf.text.List(com.itextpdf.text.List.ORDERED,
					 * com.itextpdf.text.List.NUMERICAL); for (IResource r :
					 * au.getResources()) { ListItem listItem = new ListItem(new
					 * Phrase(r.getName(), TABLE_CONTENT));
					 * resList.add(listItem); }
					 */
					PdfPCell c = getContentCell();
					c.addElement(p);
					table2.addCell(c);
					// -------------------------------------//
					if (au.getGoals().size() > 0) {

						Phrase p1 = null;
						for (int i = 0; i < au.getGoals().size(); i++) {
							if (i == 0)
								p1 = new Phrase(au.getGoals().get(i).getName(),
										TABLE_CONTENT_SMALL);
							else {
								p1.add(Chunk.NEWLINE);
								p1.add(new Phrase(au.getGoals().get(i)
										.getName(), TABLE_CONTENT_SMALL));
							}
						}

						/*
						 * com.itextpdf.text.List goalList = new
						 * com.itextpdf.text
						 * .List(com.itextpdf.text.List.ORDERED,
						 * com.itextpdf.text.List.NUMERICAL); for (Goal g :
						 * au.getGoals()) { ListItem listItem = new ListItem(new
						 * Phrase(g.getName(), TABLE_CONTENT));
						 * goalList.add(listItem); }
						 */
						PdfPCell c1 = getContentCell();
						c1.addElement(p1);
						table2.addCell(c1);
					} else {
						table2.addCell(getContentCell(""));
					}
					// -------------------------------------//
					table2.addCell(getContentCell(getOperationsTable(au)));

					// -------------------------------------//
					table2.addCell(getContentCell(au.getTarget().getName()));
					if (au.getTimesTransferable() == 0) {
						table2.addCell(getContentCell("Non-transferable authority"));
					} else {
						table2.addCell(getContentCell("Transferable authority"));
					}
				}
			}
		}
		if (addAuthTable) {
			chapter.add(createParagraph(""));
			chapter.add(createParagraph(ftc.getTable(FigureConstant.AUTH_TABLE)
					+ " summarises the authorisations actors in the "
					+ getProjectName() + " project grant to one another."));
			chapter.add(table2);
			addTableCaption(table2, ftc.getTable(FigureConstant.AUTH_TABLE)
					+ " - Authorisations in the " + getProjectName()
					+ " project");
			table2.setComplete(true);
		}

	}

	/******************************************************************************************/
	private void buildAnalysisChapter(Chapter chapter, Document document) {

		chapter.setTitle(getChapterTitleParagraph("Analysis"));

		String chapterIntro = "";
		chapter.add(createParagraph(chapterIntro));

		if (generateConsistencyAnalysisSection()) {
			buildSectionConsistencyAnalysis(
					chapter.addSection(getSectionTitleParagraph("Consistency Analysis")),
					document);
		}
		if (generateSecurityAnalysisSection()) {
			buildSectionSecurityAnalysis(chapter
					.addSection(getSectionTitleParagraph("Security Analysis")));
		}
		if (generateRiskAnalysisSection()) {
			buildSectionRiskAnalysis(chapter
					.addSection(getSectionTitleParagraph("Threat Analysis")));
		}
		chapter.setTriggerNewPage(true);
		chapter.setComplete(true);
	}

	private void buildSectionConsistencyAnalysis(final Section section,
			Document document) {

		String sectionIntro = "The purpose of consistency analysis is to verify whether the diagram for the project "
				+ getProjectName()
				+ " is consistent and valid. A diagram is considered to be consistent if its constituent elements (concepts and relationships) are drawn and interconnected following the semantics of the modelling language (STS-ml in our case). Thus, consistency analysis performs post checks to verify compliance with STS-ml semantics for all checks that cannot be performed live over the models.";
		Paragraph pIntro = createParagraph(sectionIntro);
		pIntro.setSpacingAfter(8);
		section.add(pIntro);

		if (generateAppendixBChapter()) {
			String appBref = "More details about the performed checks and their purpose can be found in Appendix B.";
			section.add(createParagraph(appBref));
		}

		ConsistencyAnalysisTasks t = new ConsistencyAnalysisTasks(1);
		DiagramAnalyser analyser = new DiagramAnalyser();

		analyser.addTaskGroup(t);

		AnalysisDescriptor ad = new AnalysisDescriptor("Consistency",
				FigureConstant.CONSISTENCY_RES_TABLE);
		analyser.addTaskListener(ad);
		analyser.evaluateCurrentDiagram();

		section.add(ad.getFinalParagraph());

		section.setComplete(true);
	}

	private void buildSectionSecurityAnalysis(Section section) {
		String sectionIntro = "The purpose of security analysis is to verify whether the diagram for the project "
				+ getProjectName()
				+ " allows the satisfaction of the specified security needs or not. As a result, for all security needs expressed by stakeholders, it checks in the model whether there is any possibility for the security need to be violated. This analysis takes into account the semantics of STS-ml, defining the behaviour of the different elements represented in the models. The elements’ behaviour is defined by propagation rules that consider what concepts and what relationships the specification of a given security need affects. Datalog is used to define the semantics of STS-ml to express facts (things always hold) and rules.";
		Paragraph pIntro = createParagraph(sectionIntro);
		pIntro.setSpacingAfter(8);
		section.add(pIntro);

		if (generateAppendixCChapter()) {
			String appBref = "You can find more details about the performed checks in Appendix C.";
			section.add(createParagraph(appBref));
		}

		ConsistencyAnalysisTasks t = new ConsistencyAnalysisTasks(1);
		DiagramAnalyser analyser = new DiagramAnalyser();
		analyser.addTaskGroup(t);
		boolean analysisRes = analyser.evaluateCurrentDiagram();
		if (analysisRes) {
			analyser.removeAllTaskGroups();
			analyser.addTaskGroup(new SecurityAnalysisTasks("", 1));
			AnalysisDescriptor ad = new AnalysisDescriptor("Security",
					FigureConstant.SECURITY_RES_TABLE);
			analyser.addTaskListener(ad);
			analyser.evaluateCurrentDiagram();
			section.add(ad.getFinalParagraph());
		} else {
			Paragraph p = createParagraph("%i"
					+ "The security analysis for "
					+ getProjectName()
					+ " couldn't be performed because the consistency analysis has identified some errors that need to be fixed first."
					+ "% ");
			p.setIndentationLeft(15);
		}
		section.setComplete(true);
	}

	private void buildSectionRiskAnalysis(Section section) {
		String sectionIntro = "The purpose of the threat analysis is to present the impact of events in the overall model, when they threaten specific elements of the goal model such as goals and documents.";
		Paragraph pIntro = createParagraph(sectionIntro);
		pIntro.setSpacingAfter(8);
		section.add(pIntro);

		if (generateAppendixDChapter()) {
			String appBref = "More details for threat analysis are provided in Appendix D.";
			section.add(createParagraph(appBref));
		}

		RiskAnalysisTasks t = new RiskAnalysisTasks(1);
		DiagramAnalyser analyser = new DiagramAnalyser();

		analyser.addTaskGroup(t);

		AnalysisDescriptor ad = new AnalysisDescriptor("Threat",
				FigureConstant.RISK_RES_TABLE);
		analyser.addTaskListener(ad);
		analyser.evaluateCurrentDiagram();

		section.add(ad.getFinalParagraph());

		section.setComplete(true);
	}

	/******************************************************************************************/

	private void buildAppendixAChapter(Chapter c, Document d) {

		c.setTitle(getChapterTitleParagraph("Appendix A"));
		c.setNumberDepth(0);
		c.setTriggerNewPage(true);

		float width = d.getPageSize().getWidth() - d.leftMargin()
				- d.rightMargin();
		float height = d.getPageSize().getHeight() - d.topMargin()
				- d.bottomMargin() - 70;

		if (needSocialDiagramInAppendixA()) {
			Image socialImage = rsp.generateScrenshot(ViewsManager.SOCIAL_VIEW);
			socialImage.setAlignment(Element.ALIGN_CENTER);
			if (socialImage.getPlainWidth() > socialImage.getPlainHeight()) {
				fitImageInArea(socialImage, height, width, true);
				socialImage.setRotationDegrees(90);
			} else {
				fitImageInArea(socialImage, width, height, true);
			}

			c.add(decorateImage(socialImage,
					ftc.getFigure(FigureConstant.SOCIAL_DIAGRAM)
							+ " - Social View for the " + getProjectName()
							+ " project"));
		}
		if (needInformationDiagramInAppendixA()) {
			Image informationImage = rsp
					.generateScrenshot(ViewsManager.RESOURCE_VIEW);
			informationImage.setAlignment(Element.ALIGN_CENTER);
			if (informationImage.getPlainWidth() > informationImage
					.getPlainHeight()) {
				fitImageInArea(informationImage, height, width, true);
				informationImage.setRotationDegrees(90);
			} else {
				fitImageInArea(informationImage, width, height, true);
			}
			// c.add(informationImage);
			c.add(decorateImage(informationImage,
					ftc.getFigure(FigureConstant.INFORMATION_DIAGRAM)
							+ " - Information View for the " + getProjectName()
							+ " project"));

		}
		if (needInformationDiagramInAppendixA()) {
			Image authorisationImage = rsp
					.generateScrenshot(ViewsManager.AUTHORIZATION_VIEW);
			authorisationImage.setAlignment(Element.ALIGN_CENTER);
			if (authorisationImage.getPlainWidth() > authorisationImage
					.getPlainHeight()) {
				fitImageInArea(authorisationImage, height, width, true);
				authorisationImage.setRotationDegrees(90);
			} else {
				fitImageInArea(authorisationImage, width, height, true);
			}
			// c.add(authorisationImage);
			c.add(decorateImage(authorisationImage,
					ftc.getFigure(FigureConstant.AUTH_DIAGRAM)
							+ " - Authorisation View for the "
							+ getProjectName() + " project"));

		}

		c.setComplete(true);
	}

	private void buildAppendixBChapter(Chapter c, Document d) {

		c.setTitle(getChapterTitleParagraph("Appendix B"));
		c.setNumberDepth(0);
		c.setTriggerNewPage(true);

		String sectionIntro = "Details of consistency analysis:";
		c.add(createParagraph(sectionIntro));

		List<Paragraph> plist = new ArrayList<Paragraph>();

		for (String aName : ConsistencyAnalysisTasksNames.ALL_TASKS_NAMES) {
			plist.add(getConsistencyAnalysisDescription(aName));
		}
		c.add(listParagraphs(plist));

		c.setComplete(true);
	}

	private void buildAppendixCChapter(Chapter c, Document d) {

		c.setTitle(getChapterTitleParagraph("Appendix C"));
		c.setNumberDepth(0);
		c.setTriggerNewPage(true);

		String sectionIntro = "STS-ml allows for the specification of security needs over actors’ interactions. It currently supports a non-exhaustive set of security needs and organisational constraints, namely non-repudiation, redundancy, no-delegation, non-usage, non-modification, non-production, non-disclosure and need-to-know. The purpose of security analysis is to verify whether there are any violations of security needs. As such, it includes defining the rules necessary to detect violations. In the following are provided the details for all the checks performed during security analysis.";
		c.add(createParagraph(sectionIntro));

		List<Paragraph> plist = new ArrayList<Paragraph>();

		int kk = 0;

		for (kk = kk; kk < 4; kk++) {
			plist.add(getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk]));
		}
		c.add(listParagraphs(plist));
		plist.clear();

		Paragraph parList = getDefaultParagraph();
		com.itextpdf.text.List list = new com.itextpdf.text.List(
				com.itextpdf.text.List.UNORDERED);
		list.setListSymbol("\u2022" + " ");
		list.setSymbolIndent(30f);
		parList.add(list);
		parList.setSpacingBefore(0f);
		parList.setSpacingAfter(0f);

		list.add(new ListItem(
				getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk++])));

		com.itextpdf.text.List subList = new com.itextpdf.text.List(
				com.itextpdf.text.List.UNORDERED);
		subList.setListSymbol("\u2022" + " ");
		subList.setSymbolIndent(15f);

		for (kk = kk; kk < 10; kk++) {
			subList.add(new ListItem(
					getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk])));
			// plist.add(getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk]));
		}

		list.add(subList);
		c.add(parList);

		/*
		 * Paragraph actorName = createParagraph(type + "%b" + a.getName() +
		 * "%:"); actorName.setSpacingAfter(5);
		 * 
		 * com.itextpdf.text.List list = new
		 * com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		 * list.setListSymbol("\u2022" + " "); list.setSymbolIndent(10f);
		 * list.add(new ListItem(actorName));
		 * 
		 * if (sbOut.length() > 0) { com.itextpdf.text.List sublist = new
		 * com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		 * sublist.setListSymbol("- "); sublist.setSymbolIndent(30f);
		 * //Paragraph p=createParagraph("%b" + a.getName() + "% " +
		 * sbOut.toString() + "."); Paragraph p =
		 * createParagraph(sbOut.toString() + ".");
		 * 
		 * if (sbInc.length() > 0) { p.setSpacingAfter(10); }
		 * 
		 * sublist.add(new ListItem(p)); list.add(sublist);
		 */

		String text = "Apart from the verification of violations of security needs, security analysis performs checks to verify that actors comply with their authorities. For this, it searches for eventual unauthorised passages of rights. For the time being, the following violations are detected:";
		c.add(createParagraph(text));

		parList = getDefaultParagraph();
		list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		list.setListSymbol("\u2022" + " ");
		list.setSymbolIndent(30f);
		parList.add(list);
		parList.setSpacingBefore(0f);
		parList.setSpacingAfter(0f);

		list.add(new ListItem(
				getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk++])));

		subList = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
		subList.setListSymbol("\u2022" + " ");
		subList.setSymbolIndent(15f);

		for (kk = kk; kk < 16; kk++) {
			subList.add(new ListItem(
					getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk])));
			// plist.add(getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk]));
		}

		list.add(subList);
		c.add(parList);

		text = "As far as organisational constraints are concerned, security analysis verifies that the specification of SoD and BoD constraints can be satisfied in the given model. The satisfaction of role-based SoD and BoD are already covered by the consistency analysis, security analysis deals with goal-based SoD and BoD instead.";
		c.add(createParagraph(text));

		for (kk = kk; kk < SecurityAnalysisTasksNames.ALL_TASKS_NAMES.length; kk++) {
			plist.add(getSecurityAnalysisDescription(SecurityAnalysisTasksNames.ALL_TASKS_NAMES[kk]));
		}
		c.add(listParagraphs(plist));

		c.setComplete(true);
	}

	private void buildAppendixDChapter(Chapter c, Document d) {

		c.setTitle(getChapterTitleParagraph("Appendix D"));
		c.setNumberDepth(0);
		c.setTriggerNewPage(true);

		String sectionIntro = "STS-ml allows for specification of events that threaten goals or documents in a STS model.";
		c.add(createParagraph(sectionIntro));
		sectionIntro = "It currently focus on events threating goals or documents, and as such it propagates the effects over goal trees and goals/documents relationships internal to the actor (need, modify, produce), as well as social relationships involving goals and docs (goal delegation, document provision).";
		c.add(createParagraph(sectionIntro));
		sectionIntro = "In the following are provided the details of the check performed during threat analysis.";
		c.add(createParagraph(sectionIntro));

		List<Paragraph> plist = new ArrayList<Paragraph>();

		for (String aName : RiskAnalysisTasksNames.ALL_TASKS_NAMES) {
			plist.add(getRiskAnalysisDescription(aName));
		}
		c.add(listParagraphs(plist));

		c.setComplete(true);
	}

	private Paragraph getConsistencyAnalysisDescription(final String analysis) {
		String desc = "?";
		if (analysis.equals(ConsistencyAnalysisTasksNames.EMPTYDIAGRAM)) {
			desc = "This check verifies whether the given diagram is empty or not. If that is the case, then no other consistency checks are performed.\nIf the diagram is not empty, the consistency analysis returns: “No errors found” and continues performing the rest of the consistency checks.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.GOALSINGLEDECOMPOSITION)) {
			desc = "This check verifies the consistency of goal decompositions. Following the semantics of STS-ml a given goal is decomposed in two or more subgoals. As a result, the decomposition should specify at least two subgoals. Therefore, goal single decomposition verifies whether there are cases of decompositions to a single subgoal.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.GOALLEAFDELEGATION)) {
			desc = "This check verifies the consistency of goal delegations. Following the semantics of STS-ml only atomic goals or leaf goals in a goal tree can be delegated. Higher-level goals should not be delegated. Goal leaf delegation verifies exactly cases of non-leaf goal delegations.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.GOALLEAFCAPABILITY)) {
			desc = "This check verifies the consistency of specifying information related to capabilities actors have to achieve their goals. Capabilities in STS-ml can be specified over leaf goals only.  If capability is specified over higher-level goals this control returns an error.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.DELEGATIONCHILDCYCLES)) {
			desc = "This check verifies the consistency of goal delegations, so that no cycles or loops are identified as a result of the delegatee decomposing the delegatum (delegated goal) and re-delegating back one of the subgoals. Delegation child cycle verifies exactly this and gives a warning in case of inconsistency.";
			// } else if
			// (analysis.equals(ConsistencyAnalysisTasksNames.DELEGATEDGOALPARTOFDECOMPOSITION))
			// {
			// desc =
			// "This check verifies that all goals (in the delegatee’s scope) that have been delegated are not child (subgoals) in the decomposition.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.CONTRIBUTIONSCYCLE)) {
			desc = "This check verifies whether there are loops of positive or negative contribution relationships, and whether this loop contains contradictory relationships. If such a loop is identified, the consistency analysis returns a warning.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.NEGATIVECONTRIBUTIONSBETWEENAND)) {
			desc = "This check verifies that there are no negative contribution relationships between and-subgoals of a given goal (within an actor’s scope).  It returns a warning if such a case is identified.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.PARTOFOFDOCUMENTSCICLES)) {
			desc = "This check verifies whether there is a loop or cycle of Part Of relationships starting from and ending to a given document. If a case like this is verified, a warning is returned enumerating the documents that form the cycle.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.PARTOFOFINFORMATIONCYCLES)) {
			desc = "This check verifies whether there is a loop or cycle of Part Of relationships starting from and ending to a given document. If a case like this is verified, a warning is returned enumerating the documents that form the cycle.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.INFORMATIONNOOWNER)) {
			desc = "This check verifies that all information have an owner. If there are cases of information without any ownership relationships from any actor in the diagram, the consistency analysis returns a warning.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.AUTHORISATIONSVALIDITY)) {
			desc = "This check verifies that all authorisation relationship between two given actors are valid. An authorisation relationship specifies authorisations or permissions an actor grants to another on some information, to perform some allowed operations. The authorisations could be limited to a goal scope and they can be re-delegated or not.  However, the first two attributes should be specified for an authorisation relationship to be valid. If there are no information specified, the consistency analysis returns an error. The same applies to the cases, in which no allowed operations are specified.";
		} else if (analysis
				.equals(ConsistencyAnalysisTasksNames.DUPLICATEAUTHORISATION)) {
			desc = "This check verifies that there are no duplicate authorisation relationships, that could be merged. There are several cases that are addressed by this check: (i) we encounter two identical authorisation, i.e., between the same roles, in the same direction, for the same set of information, allowed operations and goals, and having the same value of transferability; (ii) identify authorisation relationships between the same roles, in the same direction, in which one grants permissions that are subset of the other authorisation’s relationship.";
		}
		return getAlaysisDescription(analysis, desc);
	}

	private Paragraph getSecurityAnalysisDescription(final String analysis) {
		String desc = "?";
		if (analysis.equals(SecurityAnalysisTasksNames.NODELEGATION_VIOLATION)) {
			desc = "This violation is verified whenever a delegatee actor further delegates a goal, over the delegation of which a no-delegation security need is specified from the delegator actor. No-delegation is specified over a goal delegation by the delegator, who requires the delegatee not to further delegate the delegated goal. Therefore, to check for any violations of no-delegation, the analysis searches for redelegations of the delegatum (delegated goal) or any of its subgoals.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.REDUNDANCY_VIOLATION)) {
			desc = "This check verifies if redundancy is satisfied by controlling that single actor redundancy or multi actor redundancy are not violated. At design time we cannot"
					+ " make the distinction between fallback and true redundancy, so they cannot be verified at this stage.Therefore, both fallback redundancy single and true redundancy single are mapped to single actor redundancy. Similarly for multi actor redundancy. The analysis verifies a redundancy violation if one of the following occurs:\n"
					+ "(1) actor does not decompose the delegated goal in any or-subgoals, for which both types of redundancy are violated\n"
					+ "(2) actor decomposes the goal into or-subgoals and delegates one to another actor when single actor redundancy has been specified, for which this type of redundancy is violated\n"
					+ "(3) actor decomposes the goal into or-subgoals, but does not delegate any of the subgoals to another actor when multi actor redundancy has been specified, for which this type of redundancy is violated.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.AUTH_CONFLICT_PREANALYSIS)) {
			desc = "This task includes a set of checks that are run to verify that no conflicting authorisations are passed towards a given actor.";
		} else if (analysis.equals(SecurityAnalysisTasksNames.AUTH_CONFLICT)) {
			desc = "This task identifies a conflict of authorisation whenever at least two authorisation relationships for the same information are drawn towards the same actor from two illegible actors (being the owner of information or another authorised actor) such that:\n"
					+ "(1) one limits the authorisation to a goal scope (requiring a need-to-know security need) and the other does not (authorising the actor without any limitations)\n"
					+ "(2) for the same goals or intersecting goal scopes, different permissions are granted in terms of operations or authority to transfer authoristaion. That is, one passes the actor the authority to perform operations (use, modify, produce, distribute) on a given information, and the other does not (requiring non-usage, non-modification, non-production, non-disclosure); one passes the actor the authority to further transfer authorisations and the other requires no further authorisations take place. ";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.OPERATION_PREANALYSIS)) {
			desc = "This task includes a set of checks that verify that no unathorised operations are performed by any actor.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.NONDISCLOSURE_VIOLATION)) {
			desc = "This violation is detected whenever an actor discloses information without having the right to distribute it. Non-disclosure expresses the need of not disclosing or further distributing the given information to other actors, apart from the authoriser. Thus, authority to distribute the information is not passed. The way actors exchange information is through document provision. In order to disclose some information, an actor would have to provide to others the document(s) containing that information. Hence, to verify if there are any unauthorized disclosures of information, the analysis checks for provisions of documents representing the given information from any unauthorized actors towards other actors.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.NONUSAGE_VIOLATION)) {
			desc = "This violation is detected whenever an actor discloses information without having the right to distribute it. Non-disclosure expresses the need of not disclosing or further distributing the given information to other actors, apart from the authoriser. Thus, authority to distribute the information is not passed. The way actors exchange information is through document provision. In order to disclose some information, an actor would have to provide to others the document(s) containing that information. Hence, to verify if there are any unauthorized disclosures of information, the analysis checks for provisions of documents representing the given information from any unauthorized actors towards other actors.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.NONMODIFICATION_VIOLATION)) {
			desc = "This violation is detected whenever an actor modifies information without having the right to modify it. Non-modification expresses the need that information should not be changed (modified), i.e. authority to modify the information is not granted. To verify if there could be any violations of non-modification, the analysis looks if the authorisee (or an actor that is not authorised by authorised party) modifies the given information. For this, it searches for modify relationships from any goal of this actor to any document representing the given information.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.NONPRODUCTION_VIOLATION)) {
			desc = "This violation is detected whenever an actor produces information without having the right to produce it. Non-production expresses the need that information should not be produced in any form, i.e. authority to produce the information is not granted. To verify if there could be any violations of non-production, the analysis checks whether if the authorisee (or an actor that is not authorised by authorised party) produces the given information. For this, it searches for produce relationships from any goal of this actor to any document representing the given information.";
		} else if (analysis.equals(SecurityAnalysisTasksNames.NTK_VIOLATION)) {
			desc = "This violation is detected whenever an actor uses, modifies or produces information for other purposes (goal achievement) than the ones for which it is authorized. Need-to-know requires that the information is used, modified, or produced in the scope of the goals specified in the authorisation. This security need concerns confidential information, which should not be utilised for any other purposes other than the intended ones. To verify if there could be any violations of need-to-know, security analysis checks if the authorisee (or an actor that is not authorised by any authorised party) uses, modifies or produces the given information while achieving some goal different from the one it is authorised for. In a nutshell, it searches for need, modify, or produce relationships starting from goals different from the specified ones towards documents representing the given information.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.AUTHORISATION_PREANALYSIS)) {
			desc = "This task includes a set of checks that verify that no actor transfers rights to others in an unauthorised way.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.AUTHORITY_VIOLATION)) {
			desc = "Verifies whether a given actor transfer rights to others even when it does not have the authority to further delegate rights.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.UNATH_DEL_USAGE_VIOLATION)) {
			desc = "Verifies whether a given actors transfer to other actors the right to use a given information, without having itself the right to do so.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.UNATH_DEL_MOD_VIOLATION)) {
			desc = "Verifies whether a given actors transfer to other actors the right to modify a given information, without having itself the right to do so.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.UNATH_DEL_PROD_VIOLATION)) {
			desc = "Verifies whether a given actors transfer to other actors the right to modify a given information, without having itself the right to do so.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.UNATH_DEL_DIST_VIOLATION)) {
			desc = "Verifies whether a given actors transfer to other actors the right to distribute a given information, without having itself the right to do so.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.BUISINESS_PREANALYSIS)) {
			desc = "This task includes a set of checks that verify there are no violations of organisational constraints.";
		} else if (analysis.equals(SecurityAnalysisTasksNames.AGENTNOTPLAYBOD)) {
			desc = "This check verifies the consistency of the Binding of Duty (BoD) constraint between roles. This constraint requires that two roles are played by the same agent, therefore the check verifies whether there is one agent playing both roles. If that is the case the check finds no errors, otherwise an error is identified.";
		} else if (analysis.equals(SecurityAnalysisTasksNames.AGENTPLAYSOD)) {
			desc = "This check verifies the consistency of the Separation of Duty (SoD) constraint between roles. This constraint requires that two roles are not played by the same agent, therefore the check verifies whether there is one agent playing both roles. If that is the case an error is identified, otherwise the check finds no errors.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.ORGANIZATIONALCONSTRAINTCONSISTENCY)) {
			desc = "This check verifies that no conflicting organisational constraints (SoD or BoD) between goals are specified.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.SOD_GOAL_VIOLATION)) {
			desc = "This violation is detected whenever a single actor may perform both goals, between which an SoD constraint is expressed. Goal-based SoD requires that there is no actor performing both goals among which SoD is specified. To perform this verification, the analysis checks that the final performer of the given goals is not the same actor.";
		} else if (analysis
				.equals(SecurityAnalysisTasksNames.COD_GOAL_VIOLATION)) {
			desc = "This violation is detected whenever a single actor may perform both goals, between which an SoD constraint is expressed. Goal-based SoD requires that there is no actor performing both goals among which SoD is specified. To perform this verification, the analysis checks that the final performer of the given goals is not the same actor.";
		}
		return getAlaysisDescription(analysis, desc);
	}

	private Paragraph getRiskAnalysisDescription(final String analysis) {
		Paragraph desc = createParagraph("?");
		if (analysis.equals(RiskAnalysisTasksNames.THREAT_ANALISIS)) {
			desc = createParagraph("This analysis starts with the known events, and propagates their impact over goal trees, documents and social relationships. The new discovered elements are treated as threated elements. The analysis ends when no new elements are found.");
			desc.add(Chunk.NEWLINE);
			desc.add(new Phrase("The propagation rules are the following:"));
			List<Paragraph> pr = new ArrayList<Paragraph>();
			pr.add(createP("If the threatened element is a goal, then the following are also threatened:  goal's decomposition's parents, porduced document and delegated goals."));
			pr.add(createP("If the threatened element is a document, then the following are also threatened: document's partOf's parents, goals that need or modify the document and if the document is provided the provided goal."));

			desc.add(listParagraphs(pr));
		}
		return getAlaysisDescription(analysis, desc);
	}

	private Paragraph createP(String s) {
		Paragraph p = createParagraph(s);
		p.setSpacingAfter(0);
		p.setSpacingBefore(0);
		return p;
	}

	private Paragraph getAlaysisDescription(String analysisName,
			String analysisDesc) {
		return getAlaysisDescription(analysisName,
				createParagraph(analysisDesc));
	}

	private Paragraph getAlaysisDescription(String analysisName,
			Paragraph analysisDesc) {
		Paragraph res = createParagraph("%b" + analysisName + "% ");
		res.add(Chunk.NEWLINE);

		analysisDesc.setSpacingAfter(0);
		analysisDesc.setSpacingBefore(0);
		res.add(analysisDesc);
		return res;
	}

}
