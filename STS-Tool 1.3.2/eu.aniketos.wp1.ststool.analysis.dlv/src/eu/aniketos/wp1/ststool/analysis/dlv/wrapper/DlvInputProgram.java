/*
* DlvInputProgram.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class DlvInputProgram {

	private List<String>	programInput	= new ArrayList<String>();

	public void addFile(File f){
		try {
			addInputStream(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addInputStream(InputStream is){
		loadInputStream(is);
	}

	public void addLine(String line){
		if (line != null) {
			line = line.trim();
			if (line.length() > 0 && !line.startsWith("%") && !line.startsWith("\n")) {
				int x = line.indexOf("%");
				if (x > 0) {
					programInput.add(line.substring(0, x - 1));
				} else {
					programInput.add(line);
				}
			}
		}
		//programInput.add(line);
	}

	public void addMultipleLine(List<String> lines){
		for (String line : lines) {
			addLine(line);
		}
	}

	private void loadInputStream(InputStream is){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = in.readLine()) != null) {
				addLine(line);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clear(){
		programInput.clear();
	}


	public boolean isEmpty(){
		return programInput.size() == 0;
	}

	public String getInputProgram(){
		if (!isEmpty()) {
			List<String> ls = new LinkedList<String>(programInput);
			Collections.shuffle(ls);
			StringBuilder sb = new StringBuilder();
			for (String s : ls) {
				sb.append(s + "\n");
			}
			return sb.toString();
		}
		return "";
	}

	public List<String> getInputProgramList(){
		return programInput;
	}

}
