/*
* AbstractAuthorisationCommitment.java
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
package eu.aniketos.wp1.ststool.commitments.model.AuthorisationCommitment;

import java.util.ArrayList;
import java.util.List;
import eu.aniketos.wp1.ststool.Authorisation;
import eu.aniketos.wp1.ststool.Goal;
import eu.aniketos.wp1.ststool.IResource;
import eu.aniketos.wp1.ststool.commitments.interfaces.ICommitment;


public abstract class AbstractAuthorisationCommitment implements ICommitment {

	protected Authorisation	authorisation;
	protected int				index;

	public AbstractAuthorisationCommitment(Authorisation authorisation, int index) {

		super();
		this.authorisation = authorisation;
		this.index = index;
	}

	@Override
	public String getRequester(){

		if (authorisation.getSource() == null || authorisation.getSource().getName() == null)
			return "Unknwon";
		else
			return authorisation.getSource().getName();
	}

	@Override
	public String getResponsible(){

		if (authorisation.getTarget() == null || authorisation.getTarget().getName() == null)
			return "Unknwon";
		else
			return authorisation.getTarget().getName();
	}

	@Override
	public String getDescritption(){
		StringBuilder sb = new StringBuilder();
		sb.append(getRequester() + " requires " + getResponsible() + " " + securityNeedName() + " of information ");

		List<String> res = new ArrayList<String>();
		for (IResource r : getAuthorisation().getResources()) {
			res.add(r.getName());
		}
		sb.append(printListOfElement(res, ", ", " and ") + ".");

		return sb.toString();
	}


	@Override
	public int getIndex(){

		return index;
	}

	protected String getGoals(){

		List<Goal> goals = authorisation.getGoals();
		StringBuffer result = new StringBuffer();
		result.append("{");
		for (int i = 0; i < goals.size(); i++) {
			if (i != 0) result.append(",");
			if (goals.get(i).getName() != null)
				result.append(goals.get(i).getName());
			else
				result.append("Unknwon");
		}
		result.append("}");
		return result.toString();
	}

	protected String getResources(){

		List<IResource> resources = authorisation.getResources();
		StringBuffer result = new StringBuffer();
		result.append("{");
		for (int i = 0; i < resources.size(); i++) {
			if (i != 0) result.append(",");
			if (resources.get(i).getName() != null)
				result.append(resources.get(i).getName());
			else
				result.append("Unknwon");
		}
		result.append("}");
		return result.toString();
	}

	public Authorisation getAuthorisation(){
		return authorisation;
	}

	protected String printListOfElement(List<String> list,String sep,String lastSep){

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1 && i > 0)
				sb.append(lastSep);
			else if (i > 0) sb.append(sep);
			sb.append(list.get(i));
		}
		return sb.toString();
	}

}
