/*
* PdfGenerator.java
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class PdfGenerator {

	private final static float		HEADER_HEIGHT	= Utilities.millimetersToPoints(20f);
	private final static float		FOOTER_HEIGHT	= Utilities.millimetersToPoints(30f);
	private final static float		MARGIN_RIGHT	= Utilities.millimetersToPoints(23f);
	private final static float		MARGIN_LEFT		= Utilities.millimetersToPoints(23f);
	private final static float		MARGIN_UP		= HEADER_HEIGHT + Utilities.millimetersToPoints(4f);
	private final static float		MARGIN_DOWN		= FOOTER_HEIGHT + Utilities.millimetersToPoints(2f);

	private ReportContentFactory	rcf;

	private List<Paragraph>			indexElements	= new ArrayList<Paragraph>();



	Image									footerImage;

	public PdfGenerator(ReportContentFactory rcf) {

		this.rcf = rcf;
		footerImage = rcf.getFooterImage();
	}

	/**
	 * Creates a PDF document.
	 * 
	 * @param pdfFilename
	 *           the path to the new PDF document
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void createPdf(OutputStream os,IProgressMonitor monitor) throws DocumentException,IOException{


		//createPdf(filename, createPdf(filename,0));
		createPdf(os, -1, monitor);

	}

	private int createPdf(OutputStream os,int indexSize,IProgressMonitor monitor) throws DocumentException,IOException{

		Document document = new Document(PageSize.A4, MARGIN_LEFT, MARGIN_RIGHT, MARGIN_UP, MARGIN_DOWN);

		PdfWriter writer = PdfWriter.getInstance(document, os);

		writer.setStrictImageSequence(true);

		writer.setLinearPageMode();// used to reorder page;
		// writer.setPdfVersion(PdfWriter.PDF_VERSION_1_6);
		ChapterSectionTOC event = new ChapterSectionTOC();
		writer.setPageEvent(event);
		event.pagenumber = rcf.startingPageNumber();
		writer.setBoxSize("art", new Rectangle(MARGIN_LEFT, 0, PageSize.A4.getWidth() - MARGIN_RIGHT, FOOTER_HEIGHT));

		monitor.beginTask("Generating Report", 100);

		document.open();
		rcf.buildContent(writer, document, new SubProgressMonitor(monitor, 85));

		if (rcf.buildIndex()) {
			document.newPage();
			event.setIndex(true);
			int toc = writer.getPageNumber();
			buildIndex(writer, document);
			document.newPage();

			monitor.worked(10);

			int total = writer.reorderPages(null);
			int tocLenght = total - toc + 1;
			int[] order = new int[total];
			order[0] = 1;
			for (int i = 0; i < tocLenght; i++) {
				order[i + 1] = toc + i;
			}
			for (int i = 2; i < toc; i++) {
				order[i + tocLenght - 1] = i;
			}
			writer.reorderPages(order);
			document.close();
			monitor.worked(5);
			return tocLenght;
		}
		return -1;
	}

	private void buildIndex(PdfWriter writer,Document document) throws DocumentException{
		document.add(rcf.getIndexTitle());
		for (Paragraph p : indexElements) {
			document.add(p);
		}
	}

	/**
	 * Inner class to keep track of the TOC and to draw lines under ever chapter and section.
	 */
	class ChapterSectionTOC extends PdfPageEventHelper {

		final static int	INDEX_INDENTATION	= 10;

		/** List with the titles. */

		Phrase				header				= new Phrase(" ");
		int					pagenumber			= -1;

		boolean				index					= false;

		public boolean isIndex(){

			return index;
		}

		public void setIndex(boolean index){

			this.index = index;
		}

		@Override
		public void onChapter(PdfWriter writer,Document document,float position,Paragraph title){

			header = new Phrase("header : " + title.getContent());

			Paragraph p = new Paragraph();
			p.add(new Chunk(title.getContent(), rcf.getIndexChapterFont()));
			Chunk c = new Chunk(Integer.toString(pagenumber), rcf.getIndexNumberFont());
			p.add(new Chunk(new DottedLineSeparator(), PageSize.A4.getWidth() - MARGIN_LEFT - MARGIN_RIGHT - c.getWidthPoint() - INDEX_INDENTATION - 1, false));
			p.setIndentationLeft(INDEX_INDENTATION);
			p.add(c);
			indexElements.add(p);
		}

		@Override
		public void onChapterEnd(PdfWriter writer,Document document,float position){

		}

		@Override
		public void onSection(PdfWriter writer,Document document,float position,int depth,Paragraph title){

			Paragraph p = new Paragraph();
			p.add(new Chunk(title.getContent(), rcf.getIndexSectionFont()));
			Chunk c = new Chunk(Integer.toString(pagenumber), rcf.getIndexNumberFont());
			p.add(new Chunk(new DottedLineSeparator(), PageSize.A4.getWidth() - MARGIN_LEFT - MARGIN_RIGHT - c.getWidthPoint() - (INDEX_INDENTATION * depth) - 1, false));
			p.add(c);
			p.setIndentationLeft(INDEX_INDENTATION * depth);
			indexElements.add(p);
		}

		@Override
		public void onSectionEnd(PdfWriter writer,Document document,float position){

		}


		@Override
		public void onOpenDocument(PdfWriter writer,Document document){

		}

		/**
		 * Increase the page number.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
		 */
		@Override
		public void onStartPage(PdfWriter writer,Document document){

			pagenumber++;
		}

		/**
		 * Adds the header and the footer.
		 * 
		 * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
		 */
		@Override
		public void onEndPage(PdfWriter writer,Document document){

			if (writer.getPageNumber() == 1) return;

			if (isIndex()) {

			} else {
				PdfContentByte cb = writer.getDirectContent();
				cb.saveState();
				cb.setLineWidth(1);
				/** ----------------------HEADER-------------------- */
				cb.moveTo(MARGIN_LEFT, PageSize.A4.getHeight() - HEADER_HEIGHT - 2);
				cb.lineTo(PageSize.A4.getWidth() - MARGIN_RIGHT, PageSize.A4.getHeight() - HEADER_HEIGHT - 2);
				cb.stroke();

				//ColumnText.showTextAligned(writer.getDirectContent(),Element.ALIGN_CENTER, header,PageSize.A4.getWidth()/2,PageSize.A4.getHeight()-HEADER_HEIGHT, 0);
				/** ----------------------FOOTER-------------------- */

				cb.moveTo(MARGIN_LEFT, FOOTER_HEIGHT);
				cb.lineTo(PageSize.A4.getWidth() - MARGIN_RIGHT, FOOTER_HEIGHT);
				cb.stroke();

				footerImage.setAbsolutePosition((PageSize.A4.getWidth() - footerImage.getPlainWidth()) / 2, FOOTER_HEIGHT - footerImage.getPlainHeight() - 5);
				try {
					cb.addImage(footerImage);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				Phrase p = rcf.getPageNumberPhrase(pagenumber);
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, p, PageSize.A4.getWidth() - MARGIN_RIGHT, FOOTER_HEIGHT - p.getFont().getSize() - 2, 0);

				cb.restoreState();
			}

			if (SHOW_AREA) {
				PdfContentByte under = writer.getDirectContentUnder();

				under.saveState();
				under.setRGBColorFill(0xFF, 0xD7, 0x00);
				under.rectangle(MARGIN_LEFT, MARGIN_DOWN, PageSize.A4.getWidth() - MARGIN_RIGHT - MARGIN_LEFT, PageSize.A4.getHeight() - MARGIN_UP - MARGIN_DOWN);
				under.fill();
				under.restoreState();

				under.saveState();
				under.setRGBColorFill(0x99, 0xcc, 0xff);
				under.rectangle(MARGIN_LEFT, PageSize.A4.getHeight() - HEADER_HEIGHT, PageSize.A4.getWidth() - MARGIN_RIGHT - MARGIN_LEFT, HEADER_HEIGHT);
				under.fill();
				under.restoreState();

				under.saveState();
				under.setRGBColorFill(0x99, 0xcc, 0xff);
				under.rectangle(MARGIN_LEFT, 0, PageSize.A4.getWidth() - MARGIN_RIGHT - MARGIN_LEFT, FOOTER_HEIGHT);
				under.fill();
				under.restoreState();
			}
		}
	}



	private final static boolean	SHOW_AREA	= false;
}
