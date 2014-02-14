/*
* EngineExecutionParameters.java
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
package eu.aniketos.wp1.ststool.analysis.dlv.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EngineExecutionParameters {

	private String						inputProgram;
	private Map<String, String>	optionMap	= new HashMap<String, String>();	

	private static final String	MAX_INT		= "max_int";
	private static final String	MAX_N_Model	= "max_n_model";
	private static final String	FILTERS		= "filters";
	private static final String	SILENT		= "silent";

	public EngineExecutionParameters(String inputProgram) {
		if (inputProgram == null) inputProgram = "";
		this.inputProgram = inputProgram;
		optionMap.put(SILENT, "-silent");
	}

	public void setMaxint(Integer maxInt){

		if (maxInt != null) {
			optionMap.put(MAX_INT, "-N=" + maxInt.intValue());
		} else {
			if (optionMap.containsKey(MAX_INT)) optionMap.remove(MAX_INT);
		}
	}

	public void setNumberOfModels(Integer maxModelNum){

		if (maxModelNum != null) {
			optionMap.put(MAX_N_Model, "-n=" + maxModelNum.intValue());
		} else {
			if (optionMap.containsKey(MAX_N_Model)) optionMap.remove(MAX_N_Model);
		}
	}

	public void setFilter(List<String> filters){
		if (filters != null) {
			List<String> filtersOK = new ArrayList<String>();
			for (String s : filters) {
				if (!s.contains(" ")) filtersOK.add(s.trim());
			}
			if (filtersOK.size() > 0) {
				StringBuilder sb = new StringBuilder();
				int i = 0;
				for (String s : filtersOK) {
					if (i++ != 0) sb.append(",");
					sb.append(s);
				}
				optionMap.put(FILTERS, "-filter=" + sb.toString());
				return;
			}
		}
		if (optionMap.containsKey(FILTERS)) optionMap.remove(FILTERS);
	}

	//----------------USED BY ENGINE----------------------
	public String getInputProgram(){
		return inputProgram;
	}

	public final List<String> getParameter(){
		List<String> result = new ArrayList<String>(optionMap.values());
		return result;
	}
}
