/*
* STSPredicates.java
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
package eu.aniketos.wp1.ststool.analysis.dlv.parser.STSpredicate;

import eu.aniketos.wp1.ststool.Actor;
import eu.aniketos.wp1.ststool.Agent;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.Role;
import eu.aniketos.wp1.ststool.StsElement;
import eu.aniketos.wp1.ststool.StsObject;
import eu.aniketos.wp1.ststool.TResource;


public class STSPredicates {

	public final static STSPredicate	AGENT						= new STSPredicate("agent", Agent.class);
	public final static STSPredicate	ROLE						= new STSPredicate("role", Role.class);
	public final static STSPredicate	GOAL						= new STSPredicate("goal", Goal.class);
	public final static STSPredicate	DOCUMENT					= new STSPredicate("document", TResource.class);
	public final static STSPredicate	INFORMATION				= new STSPredicate("information", IResource.class);
	//public final static Predicate 	EVENT					=new Predicate("event", Event.class);


	public final static STSPredicate	CAN_DELEGATE			= new STSPredicate("can_delegate", Actor.class, Actor.class, Goal.class);
	public final static STSPredicate	CAN_PROVIDE				= new STSPredicate("can_provide", Actor.class, Actor.class, TResource.class);
	public final static STSPredicate	PLAY						= new STSPredicate("play", Agent.class, Role.class);

	public final static STSPredicate	AND_DEC					= new STSPredicate("and_dec", Actor.class, Goal.class);
	public final static STSPredicate	OR_DEC					= new STSPredicate("or_dec", Actor.class, Goal.class);
	public final static STSPredicate	IS_REFINED				= new STSPredicate("isRefined", Actor.class, Goal.class, Goal.class);
	public final static STSPredicate	NEED						= new STSPredicate("need", Actor.class, TResource.class, Goal.class);
	public final static STSPredicate	PRODUCE					= new STSPredicate("produce", Actor.class, TResource.class, Goal.class);
	public final static STSPredicate	MODIFY					= new STSPredicate("modify", Actor.class, TResource.class, Goal.class);


	public final static STSPredicate	POS_CONTRIB				= new STSPredicate("pos_contribution", Goal.class, Goal.class);
	public final static STSPredicate	NEG_CONTRIB				= new STSPredicate("neg_contribution", Goal.class, Goal.class);

	public final static STSPredicate	SOD_ROLE					= new STSPredicate("sod_role", Role.class, Role.class);
	public final static STSPredicate	SOD_GOAL					= new STSPredicate("sod_goal", Goal.class, Goal.class);
	public final static STSPredicate	COD_GOAL					= new STSPredicate("cod_goal", Goal.class, Goal.class);
	//public final static Predicate THREATEN=new Predicate("cod_goal",Goal.class, Goal.class);


	public final static STSPredicate	OWN						= new STSPredicate("own", Actor.class, IResource.class);
	public final static STSPredicate	PART_OF_D				= new STSPredicate("partOfD", TResource.class, TResource.class);
	public final static STSPredicate	PART_OF_I				= new STSPredicate("partOfI", IResource.class, IResource.class);
	public final static STSPredicate	MADE_TANGIBLE_BY		= new STSPredicate("madeTangibleBy", IResource.class, TResource.class);

	public final static STSPredicate	AUTHORISE				= new STSPredicate("authorise", Actor.class, Actor.class, IResource.class, Goal.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class);
	public final static STSPredicate	AUTHORISE_ALL_GOALS		= new STSPredicate("authorise", Actor.class, Actor.class, IResource.class, String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class);


	public final static STSPredicate	HAS						= new STSPredicate("has", Actor.class, Goal.class);
	public final static STSPredicate	CAPABLE_OF				= new STSPredicate("capable_of", Actor.class, Goal.class);

	public final static STSPredicate	HAS_IN_SCOPE			= new STSPredicate("has_in_scope", Actor.class, TResource.class);

	public final static STSPredicate	NO_DELEGATION			= new STSPredicate("no_delegation", Actor.class, Actor.class, Goal.class, Goal.class);

	
	public final static STSPredicate 	REDUNDANCY_SINGLE		=new STSPredicate("single_red", Actor.class,Actor.class,Goal.class);
	public final static STSPredicate 	REDUNDANCY_MULTI		=new STSPredicate("multi_red", Actor.class,Actor.class,Goal.class);
	//public final static Predicate NO_REPUDIATION=new Predicate("capable_of", Actor.class,Goal.class);
	//public final static Predicate REDUNDANCY=new Predicate("capable_of", Actor.class,Goal.class);

	//public final static Predicate INTEGRITY=new Predicate("capable_of", Actor.class,Goal.class);

	// EVENT=new Predicate("information", IResource.class);
	// PLAY=new Predicate("can_provide", Actor.class,Role.class);	
	// POSITIVE_CONTRIB=new Predicate("modify", Goal.class,Goal.class);
	// NEGATIVE_CONTRIB=new Predicate("modify", Goal.class,Goal.class);
	// THREATEN=new Predicate("cod_goal",Goal.class, Goal.class);
	// NO_REPUDIATION=new Predicate("capable_of", Actor.class,Goal.class);
	// REDUNDANCY=new Predicate("capable_of", Actor.class,Goal.class);
	// INTEGRITY=new Predicate("capable_of", Actor.class,Goal.class);


	public static String getPredicate(STSPredicate p,Object...params){
		if (params.length < 1 || params.length != p.paramType.length) throw new RuntimeException("invalid parameters");

		for (int i = 0; i < params.length; i++) {
			if (params[i] == null || !(p.paramType[i].isAssignableFrom(params[i].getClass()))) { return "\n"; 
			}
		}

		StringBuilder parameters = new StringBuilder();
		for (int i = 0; i < params.length; i++) {
			if (i > 0) parameters.append(",");//$NON-NLS-N$

			if (params[i] instanceof StsObject) {
				parameters.append("&&"+((StsElement) params[i]).getStsUniqueID());
			} else if (params[i] instanceof Boolean) {
				if ((Boolean) params[i]) {
					parameters.append("1");
				} else {
					parameters.append("0");
				}
			} else if (params[i] instanceof String) {
				parameters.append((String) params[i]);
			}
		}
		return p.name + "(" + parameters + ").";//$NON-NLS-N1$ $NON-NLS-N2$
	}
}
