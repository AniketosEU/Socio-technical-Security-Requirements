/*
* CommitmentEvaluator.java
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
package eu.aniketos.wp1.ststool.commitments.evaluator;

import java.util.ArrayList;
import java.util.List;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.RedundancyType;
import eu.aniketos.wp1.ststool.RepudiationType;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NeedToKnow;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonDisclosure;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonModification;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonProduction;
import eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment.NonUsage;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.AchieveInCombination;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.NotAchieveBoth;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.NotPlayBoth;
import eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment.PlayBoth;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.AvailabilityDelegationCommitment;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NoDelegation;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NonRepudiationOfAcceptance;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.NonRepudiationOfDelegation;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.Redundancy;
import eu.aniketos.wp1.ststool.commitments.model.DelegationCommitment.TrustworthinessCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.AvailabilityProvisionCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.ConfidentialityCommitment;
import eu.aniketos.wp1.ststool.commitments.model.ProvisionCommitment.IntegrityCommitment;


public class CommitmentEvaluator {


	public static List<ICommitment> evaluateCommitments(Object element){

		if (element instanceof Delegation) {
			return CommitmentEvaluator.evaluateDelegationCommitment((Delegation) element);
		} else if (element instanceof Provision) {
			return CommitmentEvaluator.evaluateProvisionCommitment((Provision) element);
		} else if (element instanceof Authorisation) {
			return CommitmentEvaluator.evaluateAuthorisationCommitment((Authorisation) element);
		} else if (element instanceof IncompatibleDuties) {
			return CommitmentEvaluator.evaluateSeparationOfDutiesCommitment((IncompatibleDuties) element);
		} else if (element instanceof CompatibleDuties) { return CommitmentEvaluator.evaluateBindingOfDutiesCommitment((CompatibleDuties) element); }
		return new ArrayList<ICommitment>();
	}


	protected static List<ICommitment> evaluateDelegationCommitment(Delegation d){

		List<ICommitment> result = new ArrayList<ICommitment>();
		if (d.getRepudiationType() != RepudiationType.NO_REPUDIATION) {
			switch (d.getRepudiationType()) {
				case DELEGATOR_REPUDIATION:
					result.add(new NonRepudiationOfDelegation(d, 0));
				break;
				case DUAL_REPUDIATION:
					result.add(new NonRepudiationOfDelegation(d, 0));
					result.add(new NonRepudiationOfAcceptance(d, 0));
				break;
				case DELEGATEEE_REPUDIATION:
					result.add(new NonRepudiationOfAcceptance(d, 0));
				break;
			}
		}
		if (d.getTimesTransferable() == 0) {
			result.add(new NoDelegation(d, 0));
		}
		if (d.getRedundancyType() != RedundancyType.NO_REDUNDANCY) {
			result.add(new Redundancy(d, 0));
		}
		if (d.isTrustworthiness()) {
			result.add(new TrustworthinessCommitment(d, 0));
		}
		if (d.isAvailability()) {
			result.add(new AvailabilityDelegationCommitment(d, 0));
		}
		return result;
	}

	protected static List<ICommitment> evaluateProvisionCommitment(Provision p){
		List<ICommitment> result = new ArrayList<ICommitment>();
		if (p.isIntegrity()) {
			result.add(new IntegrityCommitment(p, 0));
		}
		if (p.isAvailability()) {
			result.add(new AvailabilityProvisionCommitment(p, 0));
		}
		if (p.isConfidentiality()) {
			result.add(new ConfidentialityCommitment(p, 0));
		}
		return result;
	}

	protected static List<ICommitment> evaluateAuthorisationCommitment(Authorisation auth){

		List<ICommitment> result = new ArrayList<ICommitment>();
		if (auth.getResources().size() > 0) {
			if (auth.getGoals().size() > 0) {
				result.add(new NeedToKnow(auth, 0));
			}
			if (!auth.isUsage()) {
				result.add(new NonUsage(auth, 0));
			}
			if (!auth.isModification()) {
				result.add(new NonModification(auth, 0));
			}
			if (!auth.isProduce()) {
				result.add(new NonProduction(auth, 0));
			}
			if (!auth.isDistribution()) {
				result.add(new NonDisclosure(auth, 0));
			}
		}
		return result;
	}


	protected static List<ICommitment> evaluateBindingOfDutiesCommitment(CompatibleDuties comaptibelDuties){
		List<ICommitment> result = new ArrayList<ICommitment>();
		if (comaptibelDuties.getSource() instanceof Goal) {
			result.add(new AchieveInCombination(comaptibelDuties, 0));
		} else if (comaptibelDuties.getSource() instanceof Role) {
			result.add(new PlayBoth(comaptibelDuties, 0));
		}
		return result;
	}

	protected static List<ICommitment> evaluateSeparationOfDutiesCommitment(IncompatibleDuties incomaptibelDuties){

		List<ICommitment> result = new ArrayList<ICommitment>();
		if (incomaptibelDuties.getSource() instanceof Goal) {
			result.add(new NotAchieveBoth(incomaptibelDuties, 0));
		} else if (incomaptibelDuties.getSource() instanceof Role) {
			result.add(new NotPlayBoth(incomaptibelDuties, 0));
		}
		return result;
	}


}
