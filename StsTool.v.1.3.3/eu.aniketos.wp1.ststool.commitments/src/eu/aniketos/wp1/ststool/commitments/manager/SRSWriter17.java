/*
* SRSWriter17.java
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
package eu.aniketos.wp1.ststool.commitments.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.Resource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.StstoolFactory;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.AbstractAuthorisationCommitment;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NeedToKnow;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonDisclosure;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonModification;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonProduction;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonUsage;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.AbstractBusinessCommitment;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.AchieveInCombination;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.NotAchieveBoth;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.NotPlayBoth;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.PlayBoth;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.AbstractDelegationCommitment;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.AvailabilityDelegationCommitment;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NoDelegation;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NonRepudiationOfAcceptance;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NonRepudiationOfDelegation;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.Redundancy;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.TrustworthinessCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.AbstractProvisionCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.AvailabilityProvisionCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.ConfidentialityCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.IntegrityCommitment;

public class SRSWriter17 {

	private Document dom;

	public SRSWriter17(List<ICommitment> commitments, StsToolDiagram diagram) {

		createDocument();
		createDOMTree(commitments, diagram);
	}

	/**
	 * Using JAXP in implementation independent manner create a document object
	 * using which we create a xml tree in memory
	 */
	private void createDocument() {

		// get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// get an instance of builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// create an instance of DOM
			dom = db.newDocument();

		} catch (ParserConfigurationException pce) {
		}

	}

	/**
	 * The real workhorse which creates the XML structure
	 */
	private void createDOMTree(List<ICommitment> commitments, StsToolDiagram diagram) {

		Element rootEle = dom.createElement("secReqSpe");
		rootEle.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		rootEle.setAttribute("xsi:noNamespaceSchemaLocation", "http://www.sts-tool.eu/xsd/SRS_1.7.xsd");
		dom.appendChild(rootEle);

		int commitmentIndex = 1;
		try {

			for (ICommitment c : commitments) {
				if (acceptCommitment(c)) {
					createCommitment(rootEle, c, commitmentIndex++);
				}
			}
			createKnowledgeBase(rootEle, diagram);
		} finally {
		}
	}

	private boolean acceptCommitment(ICommitment c) {
		// if ((c instanceof NeedToKnow) || (c instanceof NonDisclosure) || (c
		// instanceof NonModification) || (c instanceof NonUsage) || (c
		// instanceof NonProduction) || (c instanceof NoDelegation) || (c
		// instanceof NonRepudiationOfDelegation) || (c instanceof
		// NonRepudiationOfAcceptance) || (c instanceof Redundancy)) { return
		// true; }
		return true;
	}

	private Element createCommitment(Element e, ICommitment commitment, int index) {
		Element root = createChild(e, "commitment");
		root.setAttribute("id", "C" + index);

		Actor debtor = null;
		Actor creditor = null;
		if (commitment instanceof AbstractAuthorisationCommitment) {
			Authorisation a = ((AbstractAuthorisationCommitment) commitment).getAuthorisation();
			debtor = a.getTarget();
			creditor = a.getSource();
		} else if (commitment instanceof TrustworthinessCommitment) {
			Delegation d = ((TrustworthinessCommitment) commitment).getDelegation();
			debtor = d.getSource();
			creditor = d.getSource();
		} else if (commitment instanceof NonRepudiationOfDelegation) {
			Delegation d = ((NonRepudiationOfDelegation) commitment).getDelegation();
			creditor = d.getTarget();
			debtor = d.getSource();
		} else if (commitment instanceof AbstractDelegationCommitment) {
			Delegation d = ((AbstractDelegationCommitment) commitment).getDelegation();
			debtor = d.getTarget();
			creditor = d.getSource();
		}  else if (commitment instanceof IntegrityCommitment) {
			Provision p = ((IntegrityCommitment) commitment).getProvision();
			creditor = p.getTarget();
			debtor = p.getSource();
		} else if (commitment instanceof AbstractProvisionCommitment) {
			Provision p = ((AbstractProvisionCommitment) commitment).getProvision();
			debtor = p.getTarget();
			creditor = p.getSource();
		} else if (commitment instanceof AbstractBusinessCommitment) {
			Agent a = StstoolFactory.eINSTANCE.createAgent();
			a.setName("");
			a.setStsUniqueID("*");
			debtor = a;
			creditor = a;
		}

		createChildActorWithNameAndID(createChild(root, "debtor"), debtor);
		createChildActorWithNameAndID(createChild(root, "creditor"), creditor);

		if (commitment instanceof AbstractAuthorisationCommitment) {
			Element precondition = createChild(root, "precondition");
			Authorisation a = ((AbstractAuthorisationCommitment) commitment).getAuthorisation();

			Element auth = createChild(precondition, "authorization");

			createChildActorWithNameAndID(createChild(auth, "source"), a.getSource());
			createChildActorWithNameAndID(createChild(auth, "destination"), a.getTarget());

			createInfoSet(auth, "infoSet", a.getResources());

			if (a.getGoals().size() > 0) {
				createGoalSet(auth, "scopeSet", a.getGoals());
			}

			Element operationSet = createChild(auth, "operationSet");
			if (a.isUsage())
				createChildWithName(operationSet, "operation", "use");
			if (a.isModification())
				createChildWithName(operationSet, "operation", "modify");
			if (a.isProduce())
				createChildWithName(operationSet, "operation", "produce");
			if (a.isDistribution())
				createChildWithName(operationSet, "operation", "distribute");

			String transferable = "true";
			if (a.getTimesTransferable() == 0)
				transferable = "false";
			createChildWithName(auth, "transferable", transferable);

		} else if (commitment instanceof TrustworthinessCommitment) {
			Element precondition = createChild(root, "precondition");
			Delegation d = ((TrustworthinessCommitment) commitment).getDelegation();

			Element trust = createChild(precondition, "trustworthiness");
			trust.setAttribute("minLevel", new Integer(d.getTrustworthinessValue()).toString());
			createChildActorWithNameAndID(createChild(trust, "destination"), d.getTarget());
			createChildGoalWithNameAndID(trust, d.getSourceGoal());

		} else if (commitment instanceof AbstractDelegationCommitment) {
			Element precondition = createChild(root, "precondition");
			Delegation d = ((AbstractDelegationCommitment) commitment).getDelegation();
			Element del = createChild(precondition, "delegation");

			createChildActorWithNameAndID(createChild(del, "source"), d.getSource());
			createChildActorWithNameAndID(createChild(del, "destination"), d.getTarget());

			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(d.getSourceGoal());
			createGoalSet(del, "goalSet", goal);

			String transferable = "true";
			if (d.getTimesTransferable() == 0)
				transferable = "false";
			createChildWithName(del, "transferable", transferable);

		} else if (commitment instanceof AbstractProvisionCommitment) {
			Element precondition = createChild(root, "precondition");
			Provision p = ((AbstractProvisionCommitment) commitment).getProvision();
			Element prov = createChild(precondition, "provision");

			createChildActorWithNameAndID(createChild(prov, "source"), p.getSource());
			createChildActorWithNameAndID(createChild(prov, "destination"), p.getTarget());

			createChildResourceWithNameAndID(prov, p.getSourceResource());
		} else if (commitment instanceof AbstractBusinessCommitment) {

		} else {
			throw new RuntimeException("Error while parsing commitments: Invalid Commitment type");
		}

		Element postcondition = createChild(root, "postcondition");
		if (commitment instanceof NeedToKnow) {
			NeedToKnow c = (NeedToKnow) commitment;
			Element needToKnow = createChild(postcondition, "needToKnow");
			createGoalSet(needToKnow, "goalSet", c.getAuthorisation().getGoals());
		} else if (commitment instanceof NonDisclosure) {
			NonDisclosure c = (NonDisclosure) commitment;
			Element nonDisclosure = createChild(postcondition, "non-disclosure");
			createInfoSet(nonDisclosure, "infoSet", c.getAuthorisation().getResources());
		} else if (commitment instanceof NonModification) {
			NonModification c = (NonModification) commitment;
			Element noModification = createChild(postcondition, "non-modification");
			createInfoSet(noModification, "infoSet", c.getAuthorisation().getResources());
		} else if (commitment instanceof NonUsage) {
			NonUsage c = (NonUsage) commitment;
			Element noUsage = createChild(postcondition, "non-usage");
			createInfoSet(noUsage, "infoSet", c.getAuthorisation().getResources());
		} else if (commitment instanceof NonProduction) {
			NonProduction c = (NonProduction) commitment;
			Element noProduction = createChild(postcondition, "non-production");
			createInfoSet(noProduction, "infoSet", c.getAuthorisation().getResources());
		} else if (commitment instanceof NoDelegation) {
			NoDelegation c = (NoDelegation) commitment;
			Element noRepudiation = createChild(postcondition, "non-delegation");
			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(c.getDelegation().getTargetGoal());
			createGoalSet(noRepudiation, "goalSet", goal);
		} else if (commitment instanceof NonRepudiationOfDelegation) {
			NonRepudiationOfDelegation c = (NonRepudiationOfDelegation) commitment;
			Element noRepudiation = createChild(postcondition, "non-repudiation");
			noRepudiation.setAttribute("type", "delegation");
			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(c.getDelegation().getTargetGoal());
			createGoalSet(noRepudiation, "goalSet", goal);
		} else if (commitment instanceof NonRepudiationOfAcceptance) {
			NonRepudiationOfAcceptance c = (NonRepudiationOfAcceptance) commitment;
			Element noRepudiation = createChild(postcondition, "non-repudiation");
			noRepudiation.setAttribute("type", "acceptance");
			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(c.getDelegation().getTargetGoal());
			createGoalSet(noRepudiation, "goalSet", goal);
		} else if (commitment instanceof Redundancy) {
			Redundancy c = (Redundancy) commitment;
			Element redundancy = createChild(postcondition, "redundancy");
			String type = "";
			String multiplcity = "";
			switch (c.getDelegation().getRedundancyType()) {
			case TRUE_MULTI:
				type = "true";
				multiplcity = "multiActor";
				break;
			case TRUE_SINGLE:
				type = "true";
				multiplcity = "singleActor";
				break;
			case FALLBACK_MULTI:
				type = "fallback";
				multiplcity = "multiActor";
				break;
			case FALLBACK_SINGLE:
				type = "fallback";
				multiplcity = "singleActor";
				break;
			}
			redundancy.setAttribute("type", type);
			redundancy.setAttribute("multiplicity", multiplcity);

			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(c.getDelegation().getTargetGoal());
			createGoalSet(redundancy, "goalSet", goal);
		} else if (commitment instanceof AvailabilityDelegationCommitment) {
			AvailabilityDelegationCommitment c = (AvailabilityDelegationCommitment) commitment;
			Element availability = createChild(postcondition, "availability");
			availability.setAttribute("minLevel", new Integer(c.getDelegation().getAvailabilityValue()).toString());
			createChildGoalWithNameAndID(availability, c.getDelegation().getSourceGoal());
		} else if (commitment instanceof TrustworthinessCommitment) {

			Element del = createChild(postcondition, "delegation");

			Delegation d = ((TrustworthinessCommitment) commitment).getDelegation();

			createChildActorWithNameAndID(createChild(del, "source"), d.getSource());
			createChildActorWithNameAndID(createChild(del, "destination"), d.getTarget());

			List<Goal> goal = new ArrayList<Goal>(1);
			goal.add(d.getSourceGoal());
			createGoalSet(del, "goalSet", goal);

			String transferable = "true";
			if (d.getTimesTransferable() == 0)
				transferable = "false";
			createChildWithName(del, "transferable", transferable);

		} else if (commitment instanceof AvailabilityProvisionCommitment) {
			AvailabilityProvisionCommitment c = (AvailabilityProvisionCommitment) commitment;
			Element availability = createChild(postcondition, "availability");
			availability.setAttribute("minLevel", new Integer(c.getProvision().getAvailabilityValue()).toString());
			createChildResourceWithNameAndID(availability, c.getProvision().getSourceResource());
		} else if (commitment instanceof IntegrityCommitment) {
			IntegrityCommitment c = (IntegrityCommitment) commitment;
			Element integrity = createChild(postcondition, "integrity");
			createChildResourceWithNameAndID(integrity, c.getProvision().getSourceResource());
		} else if (commitment instanceof IntegrityCommitment) {
			IntegrityCommitment c = (IntegrityCommitment) commitment;
			Element integrity = createChild(postcondition, "integrity");
			createChildResourceWithNameAndID(integrity, c.getProvision().getSourceResource());
		} else if (commitment instanceof ConfidentialityCommitment) {
			ConfidentialityCommitment c = (ConfidentialityCommitment) commitment;
			Element confidentilaity = createChild(postcondition, "confidentiality");
			createChildResourceWithNameAndID(confidentilaity, c.getProvision().getSourceResource());
		} else if (commitment instanceof PlayBoth) {
			PlayBoth c = (PlayBoth) commitment;
			Element playB = createChild(postcondition, "play-both");
			List<Actor> actors = new ArrayList<Actor>(2);
			actors.add((Role) c.getCompatibleDuties().getSource());
			actors.add((Role) c.getCompatibleDuties().getTarget());
			createActorSet(playB, "actorSet", actors);
		} else if (commitment instanceof NotPlayBoth) {
			NotPlayBoth c = (NotPlayBoth) commitment;
			Element notPlayB = createChild(postcondition, "not-play-both");
			List<Actor> actors = new ArrayList<Actor>(2);
			actors.add((Role) c.getIncompatibleDuties().getSource());
			actors.add((Role) c.getIncompatibleDuties().getTarget());
			createActorSet(notPlayB, "actorSet", actors);
		} else if (commitment instanceof AchieveInCombination) {
			AchieveInCombination c = (AchieveInCombination) commitment;
			Element achiveB = createChild(postcondition, "achieve-in-combination");
			List<Goal> goals = new ArrayList<Goal>(2);
			goals.add((Goal) c.getCompatibleDuties().getSource());
			goals.add((Goal) c.getCompatibleDuties().getTarget());
			createGoalSet(achiveB, "goalSet", goals);
		} else if (commitment instanceof NotAchieveBoth) {
			NotAchieveBoth c = (NotAchieveBoth) commitment;
			Element notAchiveB = createChild(postcondition, "not-achieve-in-combination");
			List<Goal> goals = new ArrayList<Goal>(2);
			goals.add((Goal) c.getIncompatibleDuties().getSource());
			goals.add((Goal) c.getIncompatibleDuties().getTarget());
			createGoalSet(notAchiveB, "goalSet", goals);
		} else {
			throw new RuntimeException("Error while parsing commitments: Invalid Commitment type: " + commitment.getClass());
		}

		return root;
	}

	private Element createKnowledgeBase(Element e, StsToolDiagram diagram) {

		Element root = createChild(e, "knowledgeBase");

		for (PartOf p : getAllPartOf(diagram)) {
			Element partOf = createChild(root, "partOf");
			createChildResourceWithNameAndID(createChild(partOf, "container"), p.getSource());
			createChildResourceWithNameAndID(createChild(partOf, "content"), p.getTarget());
		}

		for (TangibleBy t : getAllTangibleBy(diagram)) {
			Element tangibleBy = createChild(root, "tangibleBy");
			createChildResourceWithNameAndID(tangibleBy, t.getSource());
			createChildResourceWithNameAndID(tangibleBy, t.getTarget());
		}

		for (Own o : getAllOwn(diagram)) {
			Element own = createChild(root, "owns");
			createChildActorWithNameAndID(createChild(own, "actor"), o.getSource());
			createChildResourceWithNameAndID(own, o.getTarget());
		}

		for (Authorisation a : getAllFullAuthorisation(diagram)) {
			for (IResource r : a.getResources()) {
				Element own = createChild(root, "fullyAuthorized");
				createChildActorWithNameAndID(createChild(own, "actor"), a.getTarget());
				createChildResourceWithNameAndID(own, r);
			}
		}

		return root;
	}

	private List<PartOf> getAllPartOf(StsToolDiagram diagram) {

		List<PartOf> result = new ArrayList<PartOf>();
		List<Resource> resources = new ArrayList<Resource>(diagram.getDiagIResources());
		for (Actor a : diagram.getDiagActors()) {
			resources.addAll(a.getTResources());
		}
		for (Resource r : resources) {
			result.addAll(r.getPartsOf());
		}
		return result;
	}

	private List<TangibleBy> getAllTangibleBy(StsToolDiagram diagram) {

		List<TangibleBy> result = new ArrayList<TangibleBy>();
		for (IResource r : diagram.getDiagIResources()) {
			result.addAll(r.getTangibleElements());
		}
		return result;
	}

	private List<Own> getAllOwn(StsToolDiagram diagram) {

		List<Own> result = new ArrayList<Own>();
		for (Actor a : diagram.getDiagActors()) {
			result.addAll(a.getIResources());
		}
		return result;
	}

	private List<Authorisation> getAllFullAuthorisation(StsToolDiagram diagram) {

		List<Authorisation> result = new ArrayList<Authorisation>();
		for (Actor actor : diagram.getDiagActors()) {
			for (Authorisation a : actor.getOutgoingAuthorisations()) {
				if (a.getGoals().size() == 0 && a.getResources().size() > 0 && a.isUsage() && a.isModification() && a.isProduce() && a.isUsage()) {
					result.add(a);
				}
			}
		}
		return result;
	}

	/**
	 * This method uses Xerces specific classes prints the XML document to file.
	 */
	public void writeToOutputStream(OutputStream os) {

		try {
			// print
			OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

			XMLSerializer serializer = new XMLSerializer(os, format);
			serializer.serialize(dom);

		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	Document getDom() {
		return dom;
	}

	private Element createChildGoalWithNameAndID(Element root, Goal g) {
		Element e = createChildWithNameAndID(root, "goal", g.getName(), g.getStsUniqueID());
		return e;
	}

	private Element createChildResourceWithNameAndID(Element root, Resource r) {
		String type = "document";
		if (r instanceof IResource)
			type = "information";
		Element e = createChildWithNameAndID(root, type, r.getName(), r.getStsUniqueID());
		return e;
	}

	private Element createChildActorWithNameAndID(Element root, Actor a) {
		String type = "agent";
		if (a instanceof Role)
			type = "role";
		Element e = createChildWithNameAndID(root, type, a.getName(), a.getStsUniqueID());
		return e;
	}

	private Element createChildWithNameAndID(Element root, String elementName, String elementText, String id) {
		Element e = createChildWithName(root, elementName, elementText);
		/*
		 * StringBuilder sb = new StringBuilder(); for (Character c :
		 * id.toCharArray()) { if (Character.isDigit(c)) sb.append(c); }
		 */

		e.setAttribute("id", id);
		return e;
	}

	private Element createChildWithName(Element root, String elementName, String elementText) {

		Element e = createChild(root, elementName);
		Text textEl = dom.createTextNode(elementText);
		e.appendChild(textEl);
		return e;
	}

	private Element createChild(Element root, String elementName) {

		Element e = dom.createElement(elementName);
		root.appendChild(e);
		return e;
	}

	private Element createInfoSet(Element root, String infoSetName, List<IResource> resources) {

		Element infoSet = dom.createElement(infoSetName);
		root.appendChild(infoSet);
		for (IResource r : resources) {
			createChildResourceWithNameAndID(infoSet, r);
			// createChildWithNameAndID(infoSet, "information",
			// r.getName(),r.getStsUniqueID());
		}
		return infoSet;
	}

	private Element createGoalSet(Element root, String resSetName, List<Goal> goals) {

		Element resSet = dom.createElement(resSetName);
		root.appendChild(resSet);
		for (Goal g : goals) {
			createChildGoalWithNameAndID(resSet, g);
			// createChildWithNameAndID(resSet, "goal",
			// g.getName(),g.getStsUniqueID());
		}
		return resSet;
	}

	private Element createActorSet(Element root, String resSetName, List<Actor> actors) {

		Element resSet = dom.createElement(resSetName);
		root.appendChild(resSet);
		for (Actor a : actors) {
			createChildActorWithNameAndID(resSet, a);
			// createChildWithNameAndID(resSet, "goal",
			// g.getName(),g.getStsUniqueID());
		}
		return resSet;
	}
}
