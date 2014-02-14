/*
* LookupThreatsPage.java
* Copyright (C) 2013 SINTEF (http://www.sintef.no)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
* The MIT License (MIT)
* http://opensource.org/licenses/mit-license.php
*
*/
/*
* LookupThreatsPage.java
*/
package eu.aniketos.wp1.ststool.threats.wizard;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.aniketos.threatrepository.schema.download.Threat;
import eu.aniketos.threatrepository.schema.download.Threat.Metadata;
import eu.aniketos.threatrepository.schema.download.Threat.Metadata.Externalrefs;

public class LookupThreatsPage extends WizardPage implements IRunnableWithProgress {


	// TODO If used anywhere else in the application (e.g. the Security
	// Requirements Document), this URL should probably be defined elsewhere
	public final static String SVRS_WEB_URL = "https://svrs.shields-project.eu/ANIKETOS/";
	
	private boolean isSearchPerformed;

	private ArrayList<Threat> threatsFromRepository;
	
	private Threat selectedThreat;

	private String searchFilter;
	
	private String domainFilter;
	
	private Combo domainCombo;
	
	private HashMap<String,String> domainItems;
	
	private Composite container; 
	
	private Composite threatsListContainer; 

	private Text searchInput;
	
	private Button searchButton;

	private List threatsList;
	
	private Composite groupContainer;
	
	private Group detailsGroup;
	
	private KeyListener keyListener; 
	
	
	/**
	 * Create the wizard.
	 */
	public LookupThreatsPage(HashMap<String,String> domains) {
		super("wizardPage");
		setTitle("Search for threat definitions");
		setDescription("Browse threats from the Aniketos Threat Repository");
		isSearchPerformed = false;
		threatsFromRepository = new ArrayList<Threat>();
		selectedThreat = null;
		domainItems = domains;
		searchFilter = "";
		domainFilter = "";
	}
	
	
	public LookupThreatsPage(String searchString, String domainString, ArrayList<Threat> searchResults, Threat selectedThreat, HashMap<String,String> domains) {
		super("wizardPage");
		setTitle("Search for threat definitions");
		setDescription("Browse threats from the Aniketos Threat Repository");
		isSearchPerformed = false;
		domainItems = domains;
		restoreFinishedState(searchString, domainString, searchResults, selectedThreat);
	}
	
	
	private void restoreFinishedState(String searchString, String domainString, ArrayList<Threat> searchResults, Threat selectedThreat) {
		searchFilter = searchString;	
		domainFilter = domainString;
		
		threatsFromRepository = searchResults;
		this.selectedThreat = selectedThreat;
//		updateThreatSelectionList();
//		buildDetailsGroup();
	}
	
	

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent){
		container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		GridLayout gridLayout = new GridLayout();
		gridLayout.verticalSpacing = 15;
		gridLayout.numColumns = 1;
		container.setLayout(gridLayout);

		GridData horizontalFillLayout = new GridData();
		horizontalFillLayout.horizontalAlignment = SWT.FILL;
		horizontalFillLayout.grabExcessHorizontalSpace = true;

		Composite searchFormContainer = new Composite(container, SWT.NONE);
		searchFormContainer.setLayout(new FormLayout());
		searchFormContainer.setLayoutData(horizontalFillLayout);
		
		Label searchLabel = new Label(searchFormContainer, SWT.NONE);
		FormData searchLabelLayout = new FormData();
		searchLabelLayout.right = new FormAttachment(60, 0);
		searchLabelLayout.left = new FormAttachment(0, 6);
		searchLabel.setLayoutData(searchLabelLayout);
		searchLabel.setText("Business domain (optional):");
		
//		Label domainLabel = new Label(searchFormContainer, SWT.NONE);
//		FormData domainLabelLayout = new FormData();
//		domainLabelLayout.right = new FormAttachment(100, 0);
//		domainLabelLayout.left = new FormAttachment(searchLabel, 6);
//		domainLabel.setLayoutData(domainLabelLayout);
//		domainLabel.setText("Business domain:");

		domainCombo = new Combo(searchFormContainer, SWT.VERTICAL | SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
//		domainCombo.add("Unspecified");
		
		FormData domainComboLayout = new FormData();
		domainComboLayout.right = new FormAttachment(30, 0);
		domainComboLayout.top = new FormAttachment(searchLabel, 5);
		domainComboLayout.left = new FormAttachment(0, 6);
		domainCombo.setLayoutData(domainComboLayout);
		domainCombo.setToolTipText("Optional field");
		domainCombo.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {				
				domainCombo.removeKeyListener(keyListener);		
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				domainCombo.addKeyListener(keyListener);
			}
		});
		domainCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (!domainCombo.getText().equals("")) {
					searchInput.setEnabled(false);
					searchInput.setText("");
				}
				else
					searchInput.setEnabled(true);				
			}
		});
		
		searchInput = new Text(searchFormContainer, SWT.BORDER);
		FormData searchInputLayout = new FormData();
		searchInputLayout.right = new FormAttachment(80, 0);
		searchInputLayout.top = new FormAttachment(searchLabel, 6);
		searchInputLayout.left = new FormAttachment(domainCombo, 6);
		searchInput.setLayoutData(searchInputLayout);
		searchInput.setMessage("Or type search filter for all threats here");
		if (searchFilter != null && searchFilter != "")
			searchInput.setText(searchFilter);
		
		keyListener = new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					searchButtonFired();
					e.doit = false;
				}
			}
			@Override
			public void keyPressed(KeyEvent e) { }
			
		};

// 	      // THIS DOESN'T WORK WHEN THE TEXT CONTROL IS DISABLED...
//        final ToolTip tip = new ToolTip(searchFormContainer.getShell(), SWT.BALLOON);
//        tip.setMessage("Domain-based search does not allow further filtering. Unset the domain selector to search for a particular string.");
//
//        searchInput.addMouseTrackListener(new MouseTrackListener() {
//
//			@Override
//			public void mouseEnter(MouseEvent e) {
//                Text actionWidget = (Text) e.widget;
//                Point loc = actionWidget.toDisplay(actionWidget.getLocation());
//                tip.setLocation(loc.x - actionWidget.getBorderWidth(), loc.y); // + actionWidget.getSize().x
//                tip.setVisible(true);
//            }
//
//			@Override
//			public void mouseExit(MouseEvent e) {
//				tip.setVisible(false);
//			}
//        });
		
		searchInput.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (threatsList.getSelectionCount() > 0)
					setPageComplete(true);
				searchInput.removeKeyListener(keyListener);
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				setPageComplete(false);
				searchInput.addKeyListener(keyListener);
			}
		});

		if (domainItems != null) {
			// We need an empty selection
			domainItems.put("","");
			
			if (domainItems.isEmpty()) {
				domainItems.put("atm", "Air Traffic Management");
				domainItems.put("healthcare", "Healthcare");
				domainItems.put("public", "Public services");
				domainItems.put("telecom", "Telecom");
			}
			
			Set<Entry<String, String>> domains = domainItems.entrySet();
			
			for (Entry<String, String> domain : domains) {
				domainCombo.add(domain.getValue());
	
				if (domain.getKey().equals(domainFilter))
					domainFilter = domain.getValue();
			}

			if (domainFilter != null && domainFilter != "") {
				domainCombo.setText(domainFilter);
				searchInput.setEnabled(false);
				searchInput.setToolTipText("Domain-based search does not allow further filtering. Unset the domain selector to search for a particular string.");
			}
		}
		
		searchButton = new Button(searchFormContainer, SWT.PUSH);
		FormData searchButtonLayout = new FormData();
		searchButtonLayout.right = new FormAttachment(100, -6);
		searchButtonLayout.top = new FormAttachment(searchLabel, 4);
		searchButtonLayout.left = new FormAttachment(searchInput, 6);
		searchButton.setLayoutData(searchButtonLayout);
		searchButton.setText("Search");
		searchButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				boolean result = searchButtonFired();
				// Only trigger change if no error is present
				if (result)
					threatsListSelectionChanged();
			}
		});
		
		
		threatsListContainer = new Composite(container, SWT.NULL);
		GridData threatsListContainerLayout = new GridData();
		threatsListContainerLayout.horizontalAlignment = SWT.FILL;
		threatsListContainerLayout.grabExcessHorizontalSpace = true;
		threatsListContainerLayout.verticalAlignment = SWT.FILL;
		threatsListContainerLayout.grabExcessVerticalSpace = true;
		threatsListContainerLayout.minimumHeight = 200;
		threatsListContainerLayout.heightHint = 200;
		threatsListContainer.setLayoutData(threatsListContainerLayout);
		
		GridLayout threatListContainerGridLayout = new GridLayout();
		threatListContainerGridLayout.verticalSpacing = 6;
		threatListContainerGridLayout.numColumns = 1;
		threatsListContainer.setLayout(threatListContainerGridLayout);

		Label listLabel = new Label(threatsListContainer, SWT.NONE);
		listLabel.setText("Select a threat from the list below:");
		
		threatsList = new List(threatsListContainer, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		GridData threatsListLayout = new GridData();
		threatsListLayout.grabExcessHorizontalSpace = true;
		threatsListLayout.horizontalAlignment = SWT.FILL;
		threatsListLayout.grabExcessVerticalSpace = true;
		threatsListLayout.verticalAlignment = SWT.FILL;
		threatsListLayout.minimumHeight = 100;
		threatsListLayout.heightHint = 200;
		threatsList.setLayoutData(threatsListLayout);
		
		threatsList.addListener(SWT.Selection, new Listener() {
			public void handleEvent (Event e) {
				threatsListSelectionChanged();
			}
		});
		updateThreatSelectionList();
//		threatsList.addListener (SWT.DefaultSelection, new Listener() {
//			public void handleEvent (Event e) { }
//		});
		
		groupContainer = new Composite(container, SWT.NONE);
		GridData groupContainerLayout = new GridData();
		groupContainerLayout.minimumHeight = 90;
		groupContainerLayout.horizontalAlignment = SWT.FILL;
		groupContainerLayout.grabExcessHorizontalSpace = true;
		groupContainerLayout.verticalAlignment = SWT.FILL;
		groupContainerLayout.grabExcessVerticalSpace = true;
		groupContainerLayout.heightHint = 250;
		groupContainer.setLayoutData(groupContainerLayout);
		GridLayout groupContainerGridLayout = new GridLayout(1, false);
		groupContainerGridLayout.verticalSpacing = 6;
		groupContainer.setLayout(groupContainerGridLayout);
		
		buildDetailsGroup();
		
		Control[] tabOrder = new Control[] { domainCombo, searchInput, searchButton }; // searchLabel, 
		searchFormContainer.setTabList(tabOrder);
		container.forceFocus();
		container.getShell().setDefaultButton(searchButton);
		parent.getShell().setDefaultButton(searchButton);
		
		parent.setSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	
	
	protected boolean searchButtonFired() {
		searchFilter = getSearchText();
		domainFilter = getDomainFilter();
		selectedThreat = null;
		
//		if ((searchFilter == null) && (domainFilter == null))
//			setErrorMessage("Both business domain and search filter cannot be blank at the same time.");
//		else {
		setErrorMessage(null);
		triggerThreatsDownload();
		isSearchPerformed = true;
		updateThreatSelectionList();
		buildDetailsGroup();
//		}
		
		// Return success or failure
		return (getErrorMessage() == null);
	}
	
	
	protected void threatsListSelectionChanged() {
		setErrorMessage(null);
		
		if (threatsList.getSelectionCount() > 0) {
			int selection = threatsList.getSelectionIndex();
			selectedThreat = (Threat) threatsList.getData(selection + "");
			boolean isPageComplete = (selectedThreat != null);
			setPageComplete(isPageComplete);
		}
		else {
			selectedThreat = null;
			setPageComplete(false);
		}
		
		buildDetailsGroup();
	}


	private void buildDetailsGroup() {

		if (detailsGroup != null)
			detailsGroup.dispose();
		
		detailsGroup = new Group(groupContainer, SWT.NONE);
		GridData detailsGroupLayout = new GridData();
		detailsGroupLayout.horizontalAlignment = SWT.FILL;
		detailsGroupLayout.grabExcessVerticalSpace = true;
		detailsGroupLayout.verticalAlignment = SWT.FILL;
		detailsGroupLayout.minimumHeight = 90;
		detailsGroupLayout.heightHint = 250;
		detailsGroup.setLayoutData(detailsGroupLayout);
		detailsGroup.setText("Detailed information");
		detailsGroup.setLayout(new FillLayout());
		
	    ScrolledComposite groupScrollContainer = new ScrolledComposite(detailsGroup, SWT.V_SCROLL);
	    groupScrollContainer.setExpandHorizontal(true);
	    groupScrollContainer.setExpandVertical(true);
	    
	    Composite detailsContainer = new Composite(groupScrollContainer, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = 10;
		gridLayout.verticalSpacing = 10;
		detailsContainer.setLayout(gridLayout);
				
		if (selectedThreat != null) {
			setPageComplete(true);		    
			Metadata threatData = selectedThreat.getMetadata();
			Label nameLabel = new Label(detailsContainer, SWT.NONE);

			FontData[] fD = nameLabel.getFont().getFontData();
			fD[0].setStyle(SWT.BOLD);
			Font font = new Font(container.getDisplay(), fD[0]);
			
			nameLabel.setText("Threat");
			nameLabel.setFont(font);
//			Label nameText = new Label(detailsContainer, SWT.NONE);
			

		    Link nameLink = new Link(detailsContainer, SWT.NONE);
		    nameLink.setText("<A href=\"https://svrs.shields-project.eu/ANIKETOS/" +  "resourceView.jsp?version=1&id=" + threatData.getThreatId() + "\">" + threatData.getName() + "</A>");
		    nameLink.setToolTipText("Click to show more info in a web browser...");
		    nameLink.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					try {
						// Open default external browser
						PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
					} catch (PartInitException ex) {
						// TODO Auto-generated catch block
//						ex.printStackTrace();
					} catch (MalformedURLException ex) {
						// TODO Auto-generated catch block
//						ex.printStackTrace();
					}
				}
			});
			
			nameLink.setSize(445, nameLabel.getSize().y);
			
//			Label typeLabel = new Label(detailsContainer, SWT.NONE);
//			typeLabel.setText("Type/class");
//			typeLabel.setFont(font);
//			Label typeText = new Label(detailsContainer, SWT.NONE);
//			typeText.setText(threatData.getType() + " (" + threatData.getThreatclass() + ")");
			
			Label descLabel = new Label(detailsContainer, SWT.NONE);
			descLabel.setText("Description");
			descLabel.setFont(font);

			GridData descLabelLayout = new GridData();
			descLabelLayout.verticalAlignment = SWT.BEGINNING;
			descLabel.setLayoutData(descLabelLayout);
			
			final StyledText descTextbox = new StyledText(detailsContainer, SWT.WRAP | SWT.MULTI | SWT.READ_ONLY);
			descTextbox.setText(threatData.getDescription());
			descTextbox.setCursor(Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW));
			descTextbox.setCaret(null);
			descTextbox.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

			GridData descTextboxLayout = new GridData();
			descTextboxLayout.grabExcessVerticalSpace = false;
			descTextboxLayout.verticalAlignment = SWT.FILL;
			descTextboxLayout.horizontalAlignment = SWT.BEGINNING;
			descTextboxLayout.widthHint = 445;
			descTextbox.setLayoutData(descTextboxLayout);

			descTextbox.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					descTextbox.setSelection(0);
				}
			});
			
//			Composite externalrefsContainer = new Composite(detailsContainer, SWT.NONE);
//			externalrefsContainer.setLayout(new GridLayout(2, false));
			
//			String tagString = "";
//			Tags tags = threatData.getTags();
//
//			if (tags != null && tags.getTag().size() > 0) {
//				
//				for (String tag : tags.getTag()) {
//					
//					if (tagString != "")
//						tagString = tagString + ", ";
//					tagString = tagString + tag;
//				}
//			}
//			
//			if (tagString.length() > 0) {
//				Label tagsLabel = new Label(detailsContainer, SWT.NONE);
//				tagsLabel.setText("Tags");
//				tagsLabel.setFont(font);
//				Label tagsText = new Label(detailsContainer, SWT.NONE);
//				tagsText.setText(tagString);
//			}
			
			Externalrefs externalrefs = threatData.getExternalrefs();
			if (externalrefs != null && externalrefs.getExternalref().size() > 0) {
				Label externalrefsLabel = new Label(detailsContainer, SWT.NONE);
				externalrefsLabel.setText("Resources");
				externalrefsLabel.setFont(font);
				externalrefsLabel.setAlignment(SWT.LEAD);
				
				Composite externalrefsContainer = new Composite(detailsContainer, SWT.NONE);
				externalrefsContainer.setLayout(new GridLayout(1, false));
				
				for (String ref : externalrefs.getExternalref()) {
				    Link link = new Link(externalrefsContainer, SWT.NONE);
				    String urlText = ref.length() > 70 ? ref.substring(0, Math.min(ref.length(), 70)) + "..."  : ref;
				    link.setText("<A href=\"" + ref + "\">" + urlText + "</A>");

					link.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							try {
								// Open default external browser
								PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
							} catch (PartInitException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							} catch (MalformedURLException ex) {
								// TODO Auto-generated catch block
								ex.printStackTrace();
							}
						}
					});
				}
			}

//			Label uuidLabel = new Label(detailsContainer, SWT.NONE);
//			uuidLabel.setText("ID");
//			uuidLabel.setFont(font);
//			Label uuidText = new Label(detailsContainer, SWT.NONE);
//			uuidText.setText(threatData.getThreatId());	

			SimpleDateFormat df = new SimpleDateFormat();
	        df.applyPattern("dd/MM/yyyy");
	        Date createdDate = threatData.getCreationdate().toGregorianCalendar().getTime();
	        Date updatedDate = threatData.getLastupdated().toGregorianCalendar().getTime();
	        
			Label createdLabel = new Label(detailsContainer, SWT.NONE);
			createdLabel.setText("Created");
			createdLabel.setFont(font);
			Label createdText = new Label(detailsContainer, SWT.NONE);
			String dateText = df.format(createdDate);
			if (!createdDate.equals(updatedDate))
				dateText += " - updated " + df.format(updatedDate);
			createdText.setText(dateText);	
	        
		}
		else
			setPageComplete(false);
		 	    
		detailsContainer.setSize(detailsContainer.computeSize(530, SWT.DEFAULT));
		groupScrollContainer.setContent(detailsContainer);		
		groupScrollContainer.setMinSize(detailsContainer.getSize());
		
		groupContainer.layout();
	}


	public String getSearchText() {
		String filter = searchInput.getText().trim();
		if (filter.length()==0)
			filter = null;
		return filter;
	}
	
	public String getDomainFilter() {
		String domainContents = domainCombo.getText().trim();
		boolean isDomainSet = false;
		String filter = null;
		
		if (domainContents != null && !domainContents.equals("")) {
			Set<Entry<String, String>> domains = domainItems.entrySet();
			
			for (Entry<String, String> domain : domains) {
				
				if (domain.getValue().equals(domainContents)) {
					filter = domain.getKey();
					isDomainSet = true;
				}
			}
			
			if (!isDomainSet)
				filter = domainContents;
			
		}

		return filter;
	}
	
	public HashMap<String,String> getDomainsList() {
		return domainItems;
	}

	public ArrayList<Threat> getThreatsList() {
		return threatsFromRepository;
	}

	public void setThreatsList(ArrayList<Threat> threats) {
		this.threatsFromRepository = threats;
	}



	public void updateThreatSelectionList() {
		int index = 0;
		threatsList.removeAll();
		
		if (!threatsFromRepository.isEmpty()) {
			setErrorMessage(null);			
			
			for (Threat threat : threatsFromRepository) {
				Metadata data = threat.getMetadata();
				threatsList.add(data.getName(), index); // + ": " + data.getDescription()
				threatsList.setData(index + "", threat);
				if (selectedThreat != null && selectedThreat.getMetadata().getThreatId().equals(threat.getMetadata().getThreatId())) {
					threatsList.setSelection(index);
					selectedThreat = threat;
				}
				index++;
			}
		}
		else if (isSearchPerformed && getErrorMessage() == null)
			setErrorMessage("Could not find any matching threat definitions!");
	}


	public Threat getSelectedThreat() {
		return selectedThreat;
	}
	
	
	@Override
	public void setErrorMessage(String newMessage){

		super.setErrorMessage(newMessage);
		if (newMessage == null && !isPageComplete())
			setPageComplete(true);
		else if (newMessage != null && isPageComplete()) 
			setPageComplete(false);
	}


	public void triggerThreatsDownload() {
		final IRunnableWithProgress runnable = this;
		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {
				try {
					getContainer().run(true, false, runnable);
				} catch (InvocationTargetException ex) {
					MessageDialog.openError(container.getShell(), "Threat Repository search failed", ex.getCause().getMessage());
//					e.printStackTrace();
				} catch (Exception ex) {
					MessageDialog.openError(container.getShell(), "Unknown error", ex.getCause().getMessage());
//					e.printStackTrace();
				}
			}
			
		});
	}
	
	
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException {
  		monitor.beginTask("Performing search. Please wait...", IProgressMonitor.UNKNOWN);

  		try {
			ThreatRepositoryServiceWrapper processor = new ThreatRepositoryServiceWrapper();
			ArrayList<Threat> threatsFromRepository = processor.processSearchThreatEvent(searchFilter, domainFilter);
			setThreatsList(threatsFromRepository);
  		} catch (IOException e) {
  			throw new InvocationTargetException(e, "Connection error: " + e.getMessage());
  		} catch (Exception e) {
  			throw new InvocationTargetException(e, "Internal error: " + e.getMessage());
		}
  		
  		monitor.done();
	}
}
