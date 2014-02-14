/*
* ThreatRepositoryServiceWrapper.java
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
* ThreatUIEventProcessor.java
* 
* Contains code from eu.aniketos.threatrepository.client authored by SEARCH
*/
package eu.aniketos.wp1.ststool.threats.wizard;

import java.io.StringWriter;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import eu.aniketos.threatrepository.TagData;
import eu.aniketos.threatrepository.ThreatRepositoryService;
import eu.aniketos.threatrepository.ThreatType;
import eu.aniketos.threatrepository.schema.download.Threat;
import eu.aniketos.wp1.ststool.threats.Activator;
import eu.aniketos.wp1.ststool.threats.preferences.PreferenceConstants;
import eu.aniketos.wp1.ststool.threats.preferences.PreferenceInitializer;
import eu.aniketos.wp1.ststool.threats.preferences.SecureIPreferenceStore;

public class ThreatRepositoryServiceWrapper {

	/** The Threat Repository Module service instance. */
	private ThreatRepositoryService repository; 
	
	
	
	/**
	 * Creates the connector with a reference to the Threat Repository Module (TRM) Service
	 * @param context OSGI Bundle context from the Activator
	 * @throws Exception 
	 */
	public ThreatRepositoryServiceWrapper() throws Exception {
		BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference serviceReference = context.getServiceReference(ThreatRepositoryService.class.getName());
		
		if (serviceReference != null)
			repository = (ThreatRepositoryService) context.getService(serviceReference);
		else
			repository = null;
	
		configureProxy();
		setCredentials();
		
		new SecureIPreferenceStore(ConfigurationScope.INSTANCE, PreferenceInitializer.QUALIFIER).addPropertyChangeListener(new IPropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent event) {
		    	configureProxy();
				setCredentials();
		    }
		});
		
	}
	
	/**
	 * Use credentials, if set, for authentication with TRM
	 */
	private void setCredentials() {
		IPreferenceStore store = new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID);
		String email = "aniketos";
		String password = "aniketos";
		
		if(store.getString(PreferenceConstants.SVRS_EMAIL) != null) {
			email = store.getString(PreferenceConstants.SVRS_EMAIL);
			password = store.getString(PreferenceConstants.SVRS_PASSWORD);
		}
		// TODO Actual credentials might need to be set here!
		repository.setCredentials(email, password); // username, password
	}
	
	/**
	 * Use proxy settings, if set, for connection to TRM
	 */
	private void configureProxy() {
		IPreferenceStore store = new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID);
		if (store.getBoolean(PreferenceConstants.PROXY_ENABLED)) {
			String proxy = store.getString(PreferenceConstants.PROXY_ADDRESS);
			int port = store.getInt(PreferenceConstants.PROXY_PORT);
			String username = null;
			String password = null;
			if(store.getBoolean(PreferenceConstants.PROXY_AUTHENTICATION)) {
				username = store.getString(PreferenceConstants.PROXY_USERNAME);
				password = store.getString(PreferenceConstants.PROXY_PASSWORD);
			}
			repository.setProxy(proxy, port, username, password);
		}
		else
			repository.setProxy(null, null, null, null);
	}
	


	/** 
	 * This function processes UI events for searching for threats by name.
	 * 
	 * @param searchtext The search string the threat's name needs to contain
	 * @return The result of the transaction: either an error message, or the XML contents of the threats that contain the search text.
	 */
	public ArrayList<Threat> processGetThreatsEvent(String searchtext) {
		ArrayList<Threat> result = new ArrayList<Threat>();
		List<Threat> threats = repository.getThreats(searchtext, null, ThreatType.threat);

		for (Threat threat : threats) {
			try {
				StringWriter sw = new StringWriter();
				JAXBContext jc = JAXBContext
						.newInstance(eu.aniketos.threatrepository.schema.download.Threat.class);
				Marshaller m = jc.createMarshaller();
				m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
						"http://www.aniketos.eu DownloadThreat.xsd");
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
				m.marshal(threat, sw);
				result.add(threat);
				
			} catch (javax.xml.bind.JAXBException ex) {
				// Add error threat to list? Or just discard?
			}
			
		}
		return result;
	}


	/** This function processes UI events for searching for threats by name and domain
	 * 
	 * @param searchString The search string the threat's name needs to contain
	 * @param domainFilter A string that matches the domain tag that needs to be used by the threat
	 * @return The result of the transaction: The XML contents of the threats that contain the search text.
	 * @throws Exception If the Threat Repository service has not been initialized.
	 */
	public ArrayList<Threat> processSearchThreatEvent(String searchString, String domainFilter) throws Exception {
		if (repository == null)
			throw new Exception("Threat Repository service has not been initialized.");
		
//		if (domainFilter != null && !domainFilter.equals(""))
//			domainFilter = "domain:" + domainFilter;	
		if (domainFilter != null && domainFilter.trim().equals(""))
			domainFilter = null;
			
		List<Threat> res = repository.getThreats(searchString, null, ThreatType.threat, domainFilter);
		if (null == res)
			throw new ConnectException("No response received from Threat Repository. Please check your login details or proxy settings in Preferences.");
		
		ArrayList<Threat> marshalled = new ArrayList<Threat>();

		for (Threat threat : res) {
			try { 
				marshalXmlThreat(threat);
				marshalled.add(threat);
				
			} catch (JAXBException ex) {
				// Do not add the threat to the final results
				// TODO Do some logging/error messaging..?
			}
		}
		return marshalled;
	}

	
	/** This function processes UI events for searching for getting all threats available for STS abstraction level.
	 * 
	 * @return The result of the transaction: The XML contents of the threats.
	 * @throws Exception If the Threat Repository service has not been initialized.
	 */
	public ArrayList<Threat> processGetAllThreatsEvent() throws Exception {
		return processSearchThreatEvent(null, null);
	}
	

	/** 
	 * This function processes UI events for downloading a threat by UUID.
	 * 
	 * @param uuid The UUID of the threat to download
	 * @return The result of the transaction: either an error message, or the XML contents of the threat.
	 * @throws Exception If the Threat Repository service has not been initialized.
	 */
	public String processDownloadThreatEvent(String uuid) throws Exception {
		if (repository == null)
			throw new Exception("Threat Repository service has not been initialized.");
		
		Threat t = null;
		List<Threat> res = repository.getThreats(null, uuid, null);
		if (null != res)
			if (res.size()>0)
				t = res.get(0); // Only one hit since we are downloading via UUID
			else 
				return ("Resource doesn't exist.\n");
		else
			return ("Resource doesn't exist.\n");
		try {
			StringWriter sw = new StringWriter();
			JAXBContext jc = JAXBContext
					.newInstance(eu.aniketos.threatrepository.schema.download.Threat.class);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
					"http://www.aniketos.eu DownloadThreat.xsd");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			m.marshal(t, sw);
			return 	"Download result:\n-------------------------------------------\n" + sw.toString();
		} catch (javax.xml.bind.JAXBException ex) {
			return ("JAXB Marshalling error\n");
		}
	}
	
	
	public List<TagData> processGetTagList() throws Exception {
		if (repository == null)
			throw new Exception("Threat Repository service has not been initialized.");
		
		return repository.getTagList();		
	}
	
	
	private Threat marshalXmlThreat(Threat threat) throws JAXBException {
		StringWriter sw = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(eu.aniketos.threatrepository.schema.download.Threat.class);
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.aniketos.eu DownloadThreat.xsd");
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.marshal(threat, sw);
		return threat;
	}

}
