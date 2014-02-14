/*
* AchieveInCombination.java
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
package eu.aniketos.wp1.ststool.commitments.model.BusinessCommitment;

import eu.aniketos.wp1.ststool.CompatibleDuties;
import eu.aniketos.wp1.ststool.Goal;


public class AchieveInCombination extends AbstractBusinessCommitment {

	protected CompatibleDuties	comaptibelDuties;
	protected int					index;

	public AchieveInCombination(CompatibleDuties comaptibelDuties, int index) {
		this.comaptibelDuties = comaptibelDuties;
		this.index = index;
	}

	@Override
	public String getDescritption(){
		return "Any agent that achieves one of " + getGoal1Name() + " or " + getGoal2Name() + ", is required to achieve the other goal too.";
	}

	@Override
	public int getIndex(){

		return index;
	}

	@Override
	public String getReqisite(){

		return "achieve-in-combination(" + getGoal1Name() + "," + getGoal2Name() + ")";
	}

	protected String getGoal1Name(){
		if (comaptibelDuties.getSource() instanceof Goal) { return ((Goal) comaptibelDuties.getSource()).getName(); }
		return "Unknown";
	}

	protected String getGoal2Name(){
		if (comaptibelDuties.getTarget() instanceof Goal) { return ((Goal) comaptibelDuties.getTarget()).getName(); }
		return "Unknown";
	}


	@Override
	public String securityNeedName(){
		return "achieve-in-combination";
	}
	
	public CompatibleDuties getCompatibleDuties(){
		return comaptibelDuties;
	}
}
