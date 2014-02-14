/*
* ChangeLog.java
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
package eu.aniketos.wp1.ststool.diagram.custom.intro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * Class that read the change log file and transform it in Java Object
 * 
 * @author Mauro Poggianella
 */
public class ChangeLog {

	/**
	 * Static constant to enable the output of errors
	 */
	private final static boolean REPORT_ERROR = true;

	/**
	 * Path to the change log file;
	 */
	private static final String CHANGELOG_PATH = "resources/changelog.xml";

	/**
	 * Unique instance of this class
	 */
	private static final ChangeLog INSTANCE = new ChangeLog();

	/**
	 * List of Releases
	 */
	private List<ChangeLogRelease> changeLogReleases = new ArrayList<ChangeLogRelease>();

	

	/**
	 * Constructor
	 */
	private ChangeLog() {
		loadChangeLogFile();
	}

	/**
	 * Load and parse the changeLogFile
	 */
	private void loadChangeLogFile() {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(StsToolDiagramEditorPlugin.getInstance().getBundle().getResource(CHANGELOG_PATH).openStream());
//			Document doc = dBuilder.parse(new File("C:/Users/Mauro/Desktop/changelog.xml"));
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("log");
			if (REPORT_ERROR && nodes.getLength() == 0)
				System.out.println("No log node found: stop parsing");
			else if (REPORT_ERROR && nodes.getLength() > 1)
				System.out.println("Found multiple log node: parsing only the first node");

			if (nodes.getLength() > 0) {
				NodeList releaseNodes = nodes.item(0).getChildNodes();
				for (int i = 0; i < releaseNodes.getLength(); i++) {
					Node release = releaseNodes.item(i);
					if (release.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) release;
						if (element.getNodeName().equalsIgnoreCase("ReleaseVersion")) {
							ChangeLogRelease clr=parseXmlVersion(element);
							if(clr!=null)
								changeLogReleases.add(clr);
						}
					}
				}
			}
		} catch (Exception e) {
			if (REPORT_ERROR)
			e.printStackTrace();
			StsToolDiagramEditorPlugin.getInstance().logError("Couldn't parse log file", e);
		}
	}

	/**
	 * Parse an XML entry of type "ReleaseVersion"
	 * @param e the entry element
	 * @return the ChangeLogRelease if the parsing will finish without error
	 */
	private ChangeLogRelease parseXmlVersion(Element e) {
		ChangeLogRelease clr = ChangeLogRelease.getNewObject(e.getAttribute("date"), e.getAttribute("version"));
		if (clr == null)
			return null;
		NodeList child = e.getElementsByTagName("entry");
		for (int i = 0; i < child.getLength(); i++) {
			Node entry = child.item(i);
			if (entry.getNodeType() == Node.ELEMENT_NODE) {
				ChangeLogEntry cle =parseXmlEntry((Element) entry);
				if(cle!=null)
					clr.addEntry(cle);
			}
		}
		return clr.getEntries().size() > 0 ? clr : null;
	}

	/**
	 * Parse an XML entry of type "entry"
	 * @param e the entry element
	 * @return the ChangeLogEntry if the parsing will finish without error
	 */
	private ChangeLogEntry parseXmlEntry(Element e) {

		try {
			int bugID = -1;
			if (e.hasAttribute("bugId")) {
				bugID = Integer.parseInt(e.getAttribute("bugId"));
			}
			String text = getTextContent(e);
			if (text != null) {
				return new ChangeLogEntry(bugID, text);
			} else if (REPORT_ERROR) {
				System.out.println("Entry witout text");
			}
		} catch (NumberFormatException e1) {
			if (REPORT_ERROR)
				System.out.println("invalid bug id: " + e.getAttribute("bugId"));
		}
		return null;
	}

	/**
	 * Retrieve a text content form an {@link Element}
	 * @param e the element
	 * @return the text content if found <code>null</code> otherwise
	 */
	private String getTextContent(Element e) {
		NodeList child = e.getChildNodes();
		for (int i = 0; i < child.getLength(); i++) {
			Node entry = child.item(i);
			if (entry.getNodeType() == Node.TEXT_NODE) {
				Text t = (Text) entry;
				String text=t.getTextContent().trim();
				return text.length()>0?text:null;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return the list of ChangeLogRelease
	 */
	public List<ChangeLogRelease> getChangeLogReleases() {
		return changeLogReleases;
	}

	@SuppressWarnings("javadoc")
	public static void main(String[] args) {
		getInstance();
	}

	/**
	 * Return an instance of this class
	 * 
	 * @return the Unique instance of this class
	 */
	static ChangeLog getInstance() {
		return INSTANCE;
	}

	
	
	/**
	 * ChangeLog Release bean
	 */
	static class ChangeLogRelease {
		/**
		 * Release date of the release
		 */
		private final Date releaseDate;
		/**
		 * Version number
		 */
		private final String version;

		/**
		 * Version number
		 */
		private final List<ChangeLogEntry> entries;

		/**
		 * Construct a new ReleaseVersion
		 * 
		 * @param releaseDate
		 * @param version
		 */
		public ChangeLogRelease(Date releaseDate, String version) {
			super();
			this.releaseDate = releaseDate;
			this.version = version;
			entries = new ArrayList<ChangeLogEntry>();
		}

		/**
		 * Construct a new Release Version if parameters are valid
		 * 
		 * @param releaseDate
		 *            the release date of the version
		 * @param version
		 *            the version number
		 * @return a new ReleaseVersion
		 */
		private static ChangeLogRelease getNewObject(String releaseDate, String version) {
			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(releaseDate);

			} catch (ParseException e) {
				if (REPORT_ERROR)
					System.out.println("invalid date on version " + version + " : " + releaseDate);
				return null;
			}
			return new ChangeLogRelease(date, version);
		}

		/**
		 * @return The release Date for this release
		 */
		public Date getReleaseDate() {
			return releaseDate;
		}

		/**
		 * @return The version number
		 */
		public String getVersion() {
			return version;
		}

		/**
		 * @return the entries of this release
		 */
		public List<ChangeLogEntry> getEntries() {
			return new ArrayList<ChangeLogEntry>(entries);
		}

		/**
		 * Add an entry to this release
		 * 
		 * @param entry
		 *            the entry to add;
		 */
		private void addEntry(ChangeLogEntry entry) {
			entries.add(entry);
		}
	}

	/**
	 * ChangeLog Release Entry bean
	 */
	static class ChangeLogEntry {
		/**
		 * id of the bug
		 */
		final int bugID;

		/**
		 * the text of the bug
		 */
		final String bugText;

		/**
		 * Construct a new ChangeLogEntry
		 * 
		 * @param bugID
		 *            the id of the bug;
		 * @param bugText
		 *            the text of the bug
		 */
		public ChangeLogEntry(int bugID, String bugText) {
			super();
			this.bugID = bugID;
			this.bugText = bugText;
		}

		/**
		 * @return The bug id
		 */
		public int getBugID() {
			return bugID;
		}

		/**
		 * @return The bug text
		 */
		public String getBugText() {
			return bugText;
		}

	}

}
