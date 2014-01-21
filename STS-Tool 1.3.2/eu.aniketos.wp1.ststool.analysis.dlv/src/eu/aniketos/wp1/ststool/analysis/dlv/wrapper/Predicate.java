/*
* Predicate.java
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
package eu.aniketos.wp1.ststool.analysis.dlv.wrapper;

import java.util.ArrayList;
import java.util.List;


public class Predicate implements Parameter {

	//private String predicate;

	private String				name			= null;
	private List<Parameter>	parameters	= new ArrayList<Parameter>();


	public Predicate(String name) {
		this.name = name;
	}

	public int countParameter(){
		return parameters.size();
	}

	public Parameter getParameterAt(int i){
		return parameters.get(i);
	}

	public void addParameter(Parameter p){
		parameters.add(p);
	}

	@Override
	public boolean equals(Object obj){
		if (obj.getClass() != Predicate.class) return false;
		return name.equals(((Predicate) obj).name) && parameters.equals(((Predicate) obj).parameters);
	}

	@Override
	public int hashCode(){
		return name.hashCode() + parameters.hashCode();
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(name + "(");
		int i = 0;
		for (Parameter p : parameters) {
			if (i++ != 0) sb.append(",");
			sb.append(p.toString());
		}
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String getName(){
		return name;
	}
}
