/*
* STSintro.java
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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

import eu.aniketos.wp1.ststool.diagram.application.SWTResourceManager;
import eu.aniketos.wp1.ststool.diagram.custom.intro.ChangeLog.ChangeLogEntry;
import eu.aniketos.wp1.ststool.diagram.custom.intro.ChangeLog.ChangeLogRelease;
import eu.aniketos.wp1.ststool.diagram.part.StsToolDiagramEditorPlugin;

/**
 * Custom welcome page, that display the log.
 * 
 * @author Mauro Poggianella
 * 
 */
public class STSintro implements IIntroPart {

	/**
	 * Construction and initialization of the welcome page
	 * 
	 * @see org.eclipse.ui.intro.IIntroPart#createPartControl(Composite)
	 */
	@Override
	public void createPartControl(Composite container) {
		SashForm sashForm = new SashForm(container, SWT.VERTICAL);
		sashForm.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		sashForm.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		sashForm.setSashWidth(0);

		final ScrolledComposite sc = new ScrolledComposite(sashForm,
				SWT.V_SCROLL);
		sc.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		final Composite composite = new Composite(sc, SWT.NONE);
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				composite.setSize(composite.computeSize(
						sc.getClientArea().width, SWT.DEFAULT, true));
			}
		});
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager
				.getFont("Segoe UI", 16, SWT.BOLD));
		lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true,
				false, 1, 1));
		lblNewLabel.setText("Change Log");

		final ExpandBar expandBar = new ExpandBar(composite,
				SWT.INHERIT_DEFAULT);
		expandBar.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		expandBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		expandBar.addExpandListener(new ExpandListener() {
			@Override
			public void itemExpanded(ExpandEvent e) {
				composite.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						composite.pack();
					}
				});
			}

			@Override
			public void itemCollapsed(ExpandEvent e) {
				composite.getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						composite.pack();
					}
				});
			}
		});

		createLogContent(expandBar);

		composite.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				composite.pack(true);
			}
		});

		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new FillLayout());
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		sashForm.setWeights(new int[] { 217, 78 });

		StyledText styledText = new StyledText(composite_1, SWT.BORDER);
		sashForm.setWeights(new int[] { 217, 78 });

		try {

			final String NL = "\n";
			String text = "Reporting Bugs"
					+ NL
					+ NL
					+ "The Developing team needs your feedback in order to improve the tool!!!"
					+ NL
					+ "There are two easy things you can do to help us debug your (and maybe not only yours) crash!"
					+ NL
					+ "   1. Before reporting on Mantis, we ask you to zip your \".metadata\" folder. This folder is located in \"../StsTool/Workspaces/\", and contains the log files that are 	really useful to us. Zip also the \".ststool_diagram\" file you were working on, while the crash occurred."
					+ NL
					+ "   2. Attach both these files directly into Mantis to report the crash."+ NL 
					+ NL + "Thanks from the developing team.";

			styledText.setText(text);
			styledText.setEditable(false);
			FontData fd = styledText.getFont().getFontData()[0];
			styledText.setFont(SWTResourceManager.getFont(fd.getName(),(int) (fd.getHeight() * 1.2), fd.getStyle()));

			fd = styledText.getFont().getFontData()[0];
			StyleRange range1 = new StyleRange();
			range1.start = 0;
			range1.length = 14;
			range1.font=SWTResourceManager.getFont(fd.getName(),(int)(fd.getHeight()*1.2),SWT.BOLD);
			
			

			StyleRange[] styles = new StyleRange[] { range1};
			styledText.setStyleRanges(styles);

			// final String NL="\n";
			// String text="How to: Bug reporting:"+NL+NL+
			// "The Developer team need you to improve the tool!!!"+NL+
			// "There are 2 easy things to do, to help us debugging your (and maybe not only yours) crash!"
			// +NL+
			// "Before reporting in mantis, we ask you to zip your \".metadata\" folder."+NL+
			// "This folder is located in \"../StsTool/Workspaces/\", it contains the logs file that are really useful to us."+NL+
			// "If you want you can also zip the \".ststool_diagram\" file you were working on, while the crash occurred."+NL+
			// "Then you can attach this files directly in mantis when you report the crash"+NL+NL+
			// "Thanks from the developers team.";
			//
			// styledText.setText(text);
			// styledText.setEditable(false);
			// FontData fd=styledText.getFont().getFontData()[0];
			// styledText.setFont(SWTResourceManager.getFont(fd.getName(),(int)(fd.getHeight()*1.2),fd.getStyle()));
			//
			// fd=styledText.getFont().getFontData()[0];
			// StyleRange range1 = new StyleRange();
			// range1.start=0;
			// range1.length=7;
			// range1.font=SWTResourceManager.getFont(fd.getName(),(int)(fd.getHeight()*1.2),SWT.BOLD);
			//
			//
			// StyleRange range2 = new StyleRange();
			// range2.start=8;
			// range2.length=14;
			// range2.font=SWTResourceManager.getFont(fd.getName(),(int)(fd.getHeight()*1.2),SWT.BOLD);
			// range2.underline=true;
			//
			//
			// StyleRange range3 = new StyleRange();
			// range3.start=217;
			// range3.length=10;
			// range3.fontStyle=SWT.BOLD;
			//
			// StyleRange range4 = new StyleRange();
			// range4.start=264;
			// range4.length=22;
			// range4.fontStyle=SWT.BOLD;
			//
			// StyleRange range5 = new StyleRange();
			// range5.start=380;
			// range5.length=16;
			// range5.fontStyle=SWT.BOLD;
			//
			//
			// StyleRange range6 = new StyleRange();
			// range6.start=526;
			// range6.length=32;
			// range6.fontStyle=SWT.ITALIC;
			//
			// StyleRange[]styles=new
			// StyleRange[]{range1,range2,range3,range4,range5,range6};
			// styledText.setStyleRanges(styles);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the content of the ExpandBar
	 * 
	 * @param expandBar
	 */
	private void createLogContent(ExpandBar expandBar) {

		List<ChangeLogRelease> clrl = new ArrayList<ChangeLogRelease>(ChangeLog
				.getInstance().getChangeLogReleases());

		Collections.sort(clrl, new Comparator<ChangeLogRelease>() {
			@Override
			public int compare(ChangeLogRelease o1, ChangeLogRelease o2) {
				return -o1.getReleaseDate().compareTo(o2.getReleaseDate());
			}
		});

		for (int i = 0; i < clrl.size(); i++) {
			ExpandItem ei = createVersionItem(expandBar, clrl.get(i));
			if (i == 0) {
				ei.setExpanded(true);
			}

		}
	}

	/**
	 * Create an ExpandItem in the specified expandBar, that will represent a
	 * release log
	 * 
	 * @param parent
	 *            the parent
	 * @param clr
	 *            the ChangeLogRelease descriptor
	 * @return the added {@link ExpandItem}
	 */
	private ExpandItem createVersionItem(ExpandBar parent, ChangeLogRelease clr) {
		ExpandItem xpandI = new ExpandItem(parent, SWT.NONE);
		xpandI.setText("Version "
				+ clr.getVersion()
				+ "  ("
				+ new SimpleDateFormat("dd MMMMM yyyy", Locale.ENGLISH)
						.format(clr.getReleaseDate()) + ")");

		Composite composite = new Composite(parent, SWT.BORDER);
		xpandI.setControl(composite);
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.spacing = 0;
		composite.setLayout(layout);

		for (ChangeLogEntry cle : clr.getEntries()) {
			createEntry(composite, cle);
		}

		xpandI.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
		return xpandI;
	}

	/**
	 * Create a Composite that describe an ChangeLogEntry
	 * 
	 * @param parent
	 *            the parent COmposite
	 * @param entry
	 *            the ChangeLogEntry descriptor
	 * @return the created Composite
	 */
	private Composite createEntry(Composite parent, final ChangeLogEntry entry) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new RowLayout());

		CLabel label = new CLabel(composite, SWT.NONE);
		label.setMargins(3, 0, 3, 0);
		int size = 5;

		Image image = new Image(composite.getDisplay(), size, size);
		GC gc = new GC(image);
		gc.setBackground(composite.getForeground());
		gc.fillOval(0, 0, size, size);

		label.setImage(image);
		label.setText(entry.getBugText());

		if (entry.bugID > 0) {
			Link link = new Link(composite, SWT.NONE);
			link.setText("(<a>bug " + String.format("%07d", entry.bugID)
					+ "</a>)");
			link.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						if (Desktop.isDesktopSupported()) {
							Desktop desktop = Desktop.getDesktop();
							if (desktop.isSupported(Desktop.Action.BROWSE)) {
								try {
									URL url = new URL(
											"http://www.sts-tool.eu/mantis/view.php?id="
													+ entry.bugID);
									desktop.browse(url.toURI());
								} catch (IOException ex) {
								}
							}
						}
					} catch (Exception ex) {
					}
				}
			});
		}

		return composite;
	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#dispose()
	 */
	@Override
	public void dispose() {
	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#getTitle()
	 */
	@Override
	public String getTitle() {

		return "Welcome!";
	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#getTitleImage()
	 */
	@Override
	public Image getTitleImage() {
		ImageDescriptor myImage = StsToolDiagramEditorPlugin
				.getBundledImageDescriptor("icons/logo/logo 16.gif");
		return myImage.createImage();
	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#init(IIntroSite, IMemento)
	 */
	@Override
	public void init(IIntroSite site, IMemento memento)
			throws PartInitException {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#removePropertyListener(IPropertyListener)
	 */
	@Override
	public void removePropertyListener(IPropertyListener listener) {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#saveState(IMemento)
	 */
	@Override
	public void saveState(IMemento memento) {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#setFocus()
	 */
	@Override
	public void setFocus() {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#standbyStateChanged(boolean)
	 */
	@Override
	public void standbyStateChanged(boolean standby) {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#getAdapter(Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#addPropertyListener(IPropertyListener)
	 */
	@Override
	public void addPropertyListener(IPropertyListener listener) {

	}

	/**
	 * @see org.eclipse.ui.intro.IIntroPart#getIntroSite()
	 */
	@Override
	public IIntroSite getIntroSite() {
		return null;
	}
}
