/*
* DiagramDLVParser.java
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
package eu.aniketos.wp1.ststool.analysis.dlv.parser;

import static eu.aniketos.wp1.ststool.analysis.dlv.parser.STSpredicate.STSPredicates.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Delegation;
import eu.aniketos.wp1.ststool.Event;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.GoalDecomposition;
import eu.aniketos.wp1.ststool.GoalDecompositionAND;
import eu.aniketos.wp1.ststool.GoalDecompositionOR;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.IncompatibleDuties;
import eu.aniketos.wp1.ststool.Modify;
import eu.aniketos.wp1.ststool.Need;
import eu.aniketos.wp1.ststool.NegativeGoalContribution;
import eu.aniketos.wp1.ststool.Own;
import eu.aniketos.wp1.ststool.PartOf;
import eu.aniketos.wp1.ststool.Play;
import eu.aniketos.wp1.ststool.PositiveGoalContribution;
import eu.aniketos.wp1.ststool.Produce;
import eu.aniketos.wp1.ststool.Provision;
import eu.aniketos.wp1.ststool.RedundancyType;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.TResource;
import eu.aniketos.wp1.ststool.TangibleBy;
import eu.aniketos.wp1.ststool.analysis.dlv.parser.STSpredicate.STSPredicate;


public class DiagramDLVParser {

	private StsToolDiagram		diagram;
	private List<String>			result	= new ArrayList<String>();
	private List<STSPredicate>	validPredicates;
	private boolean				parsed	= false;


	public static DiagramDLVParser getNewInstance(StsToolDiagram diagram){
		return new DiagramDLVParser(diagram, null);
	}

	public static DiagramDLVParser getNewInstance(StsToolDiagram diagram,List<STSPredicate> validPredicates){
		return new DiagramDLVParser(diagram, validPredicates);
	}

	public DiagramDLVParser(StsToolDiagram diagram, List<STSPredicate> validPredicates) {
		this.diagram = diagram;
		this.validPredicates = validPredicates;
		if (diagram == null) parsed = true;
	}

	public List<String> parseDiagram(){
		if (!parsed) {
			internalParseDiagram();
			parsed = true;
		}
		return result;
	}

	protected List<String> internalParseDiagram(){
		Set<String> result = new HashSet<String>();
		Iterator<EObject> i = diagram.eAllContents();
		while (i.hasNext()) {
			EObject obj = i.next();
			if (obj instanceof Agent) {
				addPredicate(AGENT, obj);
			}

			else if (obj instanceof Role) {
				addPredicate(ROLE, obj);
			}

			else if (obj instanceof Goal) {
				Goal g = (Goal) obj;
				Goal og = getOG((Goal) obj);
				if (g.getDelegatedFrom().size() == 0) {
					addPredicate(GOAL, og);
					if (g.getIncomingDecompositions() == null) {
						addPredicate(HAS, g.getActorOwner(), og);
					}
				}
				if (g.isCapability()) {
					addPredicate(CAPABLE_OF, g.getActorOwner(), og);
				}
				if (g.getOutgoingDecompositions().size() > 0) {
					if (g.getOutgoingDecompositions().get(0) instanceof GoalDecompositionAND) {
						addPredicate(AND_DEC, g.getActorOwner(), og);
					} else if (g.getOutgoingDecompositions().get(0) instanceof GoalDecompositionOR) {
						addPredicate(OR_DEC, g.getActorOwner(), og);
					}
				}
				if (g.getIncomingDecompositions() != null) {
					GoalDecomposition gd = g.getIncomingDecompositions();
					addPredicate(IS_REFINED, g.getActorOwner(), getOG(gd.getSource()), og);
				}

			}

			else if (obj instanceof TResource) {
				TResource r = (TResource) obj;
				TResource od = getOD((TResource) obj);
				if (r.getProvidedFrom().size() == 0) {
					addPredicate(DOCUMENT, od);
					addPredicate(HAS_IN_SCOPE, r.getActorOwner(), od);
				}

			}

			else if (obj instanceof IResource) {
				addPredicate(INFORMATION, obj);

			}

			else if (obj instanceof Event) {


			}

			else if (obj instanceof Play) {
				Play p = ((Play) obj);
				addPredicate(PLAY, p.getSource(), p.getTarget());

			}

			else if (obj instanceof Delegation) {
				Delegation d = (Delegation) obj;
				Goal g = getOG(d.getSourceGoal());
				addPredicate(CAN_DELEGATE, d.getSource(), d.getTarget(), g);
				if (d.getTimesTransferable() == 0) {
					addPredicate(NO_DELEGATION, d.getSource(), d.getTarget(), g, g);
				}if (d.getRedundancyType()!=RedundancyType.NO_REDUNDANCY) {
					switch (d.getRedundancyType()) {
					case TRUE_SINGLE:
					case FALLBACK_SINGLE:
						addPredicate(REDUNDANCY_SINGLE, d.getSource(), d.getTarget(), g);
						break;
					case TRUE_MULTI:
					case FALLBACK_MULTI:
						addPredicate(REDUNDANCY_MULTI, d.getSource(), d.getTarget(), g);
						break;
					}
				}
			}

			else if (obj instanceof Provision) {
				Provision p = (Provision) obj;
				TResource r = getOD(p.getTargetResource());
				addPredicate(CAN_PROVIDE, p.getSource(), p.getTarget(), r);
			}

			else if (obj instanceof Provision) {
				Provision p = (Provision) obj;
				TResource r = getOD(p.getTargetResource());
				addPredicate(CAN_PROVIDE, p.getSource(), p.getTarget(), r);
			}

			else if (obj instanceof Need && ((Need) obj).getSource() != null && ((Need) obj).getTarget() != null) {
				Need n = (Need) obj;
				addPredicate(NEED, n.getSource().getActorOwner(), getOD(n.getTarget()), getOG(n.getSource()));
			}

			else if (obj instanceof Produce && ((Produce) obj).getSource() != null && ((Produce) obj).getTarget() != null) {
				Produce p = (Produce) obj;
				addPredicate(PRODUCE, p.getSource().getActorOwner(), getOD(p.getTarget()), getOG(p.getSource()));
			}

			else if (obj instanceof Modify && ((Modify) obj).getSource() != null && ((Modify) obj).getTarget() != null) {
				Modify m = (Modify) obj;
				addPredicate(MODIFY, m.getSource().getActorOwner(), getOD(m.getTarget()), getOG(m.getSource()));
			}

			else if (obj instanceof PositiveGoalContribution) {
				PositiveGoalContribution p = (PositiveGoalContribution) obj;
				addPredicate(POS_CONTRIB, getOG(p.getSource()), getOG(p.getTarget()));
			}

			else if (obj instanceof NegativeGoalContribution) {
				NegativeGoalContribution n = (NegativeGoalContribution) obj;
				addPredicate(NEG_CONTRIB, getOG(n.getSource()), getOG(n.getTarget()));
			}

			else if (obj instanceof Own) {
				Own o = (Own) obj;
				addPredicate(OWN, o.getSource(), o.getTarget());
			}

			else if (obj instanceof PartOf) {
				PartOf p = (PartOf) obj;
				if (p.getSource() instanceof TResource) {
					addPredicate(PART_OF_D, getOD((TResource) p.getSource()), getOD((TResource) p.getTarget()));
				} else if (p.getSource() instanceof IResource) {
					addPredicate(PART_OF_I, p.getSource(), p.getTarget());
				}
			}

			else if (obj instanceof TangibleBy) {
				TangibleBy t = (TangibleBy) obj;
				addPredicate(MADE_TANGIBLE_BY, t.getSource(), getOD(t.getTarget()));
			}

			else if (obj instanceof Authorisation) {
				Authorisation a = (Authorisation) obj;
				for (IResource ar : a.getResources()) {
					if (a.getGoals().size() > 0) {
						for (Goal ag : a.getGoals()) {
							addPredicate(AUTHORISE, a.getSource(), a.getTarget(), ar, getOG(ag), a.isUsage(), a.isModification(), a.isProduce(), a.isDistribution(), a.getTimesTransferable() != 0);
						}
					} else {
						if (a.getTarget().getGoals().size() > 0) {
							for (Goal ag : a.getTarget().getGoals()) {
								addPredicate(AUTHORISE, a.getSource(), a.getTarget(), ar, getOG(ag), a.isUsage(), a.isModification(), a.isProduce(), a.isDistribution(), a.getTimesTransferable() != 0);
							}
						} else {
							addPredicate(AUTHORISE_ALL_GOALS, a.getSource(), a.getTarget(), ar, "all_goals", a.isUsage(), a.isModification(), a.isProduce(), a.isDistribution(), a.getTimesTransferable() != 0);
						}
					}
				}
			}

			else if (obj instanceof IncompatibleDuties) {
				IncompatibleDuties id = (IncompatibleDuties) obj;
				if (id.getSource() instanceof Role) {
					addPredicate(SOD_ROLE, id.getSource(), id.getTarget());
				} else if (id.getSource() instanceof Goal) {
					addPredicate(SOD_GOAL, getOG((Goal) id.getSource()), getOG((Goal) id.getTarget()));
				}
			}

			else if (obj instanceof CompatibleDuties) {
				CompatibleDuties cd = (CompatibleDuties) obj;
				if (cd.getSource() instanceof Goal) {
					addPredicate(COD_GOAL, getOG((Goal) cd.getSource()), getOG((Goal) cd.getTarget()));
				}
			}

		}
		return new ArrayList<String>(result);
	}

	private void addPredicate(STSPredicate p,Object...params){
		if (validPredicates == null || validPredicates.contains(p)) {
			result.add(getPredicate(p, params));
		}
	}

	private Goal getOG(Goal delegatedGoal){
		if (delegatedGoal.getDelegatedFrom().size() == 0) { return delegatedGoal; }
		return getOG(delegatedGoal.getDelegatedFrom().get(0).getSourceGoal());
	}

	private TResource getOD(TResource providedResource){
		if (providedResource.getProvidedFrom().size() == 0) { return providedResource; }
		return getOD(providedResource.getProvidedFrom().get(new Random().nextInt(providedResource.getProvidedFrom().size())).getSourceResource());
	}

}
