/*
* ThreatRepositoryUIEventProcessor.java
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
/*
* ThreatUIEventProcessor.java
* 
* Contains code from eu.aniketos.threatrepository.client authored by SEARCH
*/
package eu.aniketos.wp1.ststool.threats;

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

import eu.aniketos.wp1.ststool.threats.preferences.PreferenceConstants;
import eu.aniketos.wp1.ststool.threats.preferences.PreferenceInitializer;
import eu.aniketos.wp1.ststool.threats.preferences.SecureIPreferenceStore;
import eu.aniketos.threatrepository.ThreatRepositoryService;
import eu.aniketos.threatrepository.ThreatType;
import eu.aniketos.threatrepository.schema.download.Threat;

public class ThreatRepositoryUIEventProcessor {

	/** The Threat Repository service instance. */
	private ThreatRepositoryService repository; 
	
	
	
	/**
	 * Creates the connector with a reference to the Threat Repository Service
	 * @param context OSGI Bundle context from the Activator
	 */
	public ThreatRepositoryUIEventProcessor() {
		BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		ServiceReference serviceReference = context.getServiceReference(ThreatRepositoryService.class.getName());
		
		if (serviceReference != null)
			repository = (ThreatRepositoryService) context.getService(serviceReference);
		else
			repository = null;
		
		configureProxy();
		
		new SecureIPreferenceStore(ConfigurationScope.INSTANCE, PreferenceInitializer.QUALIFIER).addPropertyChangeListener(new IPropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent event) {
		    	configureProxy();
		    }
		});
		
	}
	
	
	private void configureProxy() {
		IPreferenceStore store = new SecureIPreferenceStore(ConfigurationScope.INSTANCE, Activator.PLUGIN_ID);
		if (store.getBoolean(PreferenceConstants.PROXY_ENABLED)) {
			String proxy = store.getString(PreferenceConstants.PROXY_ADDRESS);
			int port = store.getInt(PreferenceConstants.PROXY_PORT);
			String username = null;
			String password = null;
			if(store.getBoolean(PreferenceConstants.PROXY_AUTHENTICATION)) {
				store.getString(PreferenceConstants.PROXY_USERNAME);
				store.getString(PreferenceConstants.PROXY_PASSWORD);
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


	/** This function processes UI events for searching for threats by name.
	 * 
	 * @param searchString The search string the threat's name needs to contain
	 * @param domainFilter A string that matches the domain tag that needs to be used by the threat
	 * @return The result of the transaction: The XML contents of the threats that contain the search text.
	 * @throws Exception If the Threat Repository service has not been initialized.
	 */
	public ArrayList<Threat> processSearchThreatEvent(String searchString, String domainFilter) throws Exception {
		if (repository == null)
			throw new Exception("Threat Repository service has not been initialized.");
		
		if (domainFilter != null && domainFilter.equals(""))
			domainFilter = "domain:" + domainFilter;			
			
		List<Threat> res = repository.getThreats(searchString, null, ThreatType.threat, domainFilter);
		if (null == res)
			throw new ConnectException("No response received from Threat Repository");
		
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
