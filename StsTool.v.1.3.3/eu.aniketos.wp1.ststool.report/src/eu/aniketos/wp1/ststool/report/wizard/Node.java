/*
* Node.java
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
package eu.aniketos.wp1.ststool.report.wizard;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String			data;
	private int				id;
	private boolean		cheked;
	private boolean		modificable	= true;
	private Node			parent;
	private List<Node>	children;

	public Node(int id) {
		this(id, "", true);
	}

	public Node(int id, String data) {
		this(id, data, true);
	}

	public Node(int id, String data, boolean cheked) {
		this.children = new ArrayList<Node>();
		this.data = data;
		this.cheked = cheked;
		this.id = id;
	}



	public boolean isRoot(){
		return parent == null;
	}

	public boolean hasCildren(){
		return children.size() > 0;
	}

	public List<Node> getChildren(){
		return children;
	}

	public Node getParent(){
		return parent;
	}

	public void addChildren(Node child){
		children.add(child);
		if (child != null)
		;
		child.parent = this;
	}

	public String getData(){
		return data;
	}

	public void setData(String data){
		this.data = data;
	}

	public boolean isChecked(){
		return cheked;
	}

	public void setCheked(boolean cheked){
		this.cheked = cheked;
	}

	public boolean isModificable(){
		return modificable;
	}

	public void setModificable(boolean modificable){
		this.modificable = modificable;
	}

	public Node getChildren(String data){
		Node result = null;
		for (int i = 0; i < getChildren().size() && result == null; i++) {
			if (getChildren().get(i).getData().equals(data)) {
				result = getChildren().get(i);
			}
		}
		return result;
	}

	public int getId(){
		return id;
	}


	public void printContent(){
		printContentRecursive("", this);
	}

	private void printContentRecursive(String pre,Node n){
		//System.out.println(pre + n.getData() + " " + n.cheked);
		for (Node no : n.getChildren()) {
			printContentRecursive(pre + "  ", no);
		}
	}

	public Node getNodeByID(int id){
		return getNodeByIDRecursive(this, id);
	}

	private Node getNodeByIDRecursive(Node n,int id){
		if (n.id == id) return n;
		for (Node no : n.getChildren()) {
			return getNodeByIDRecursive(no, id);
		}
		return null;
	}

	public Node copy(){
		Node result = new Node(id, new String(data), cheked);
		result.modificable = modificable;
		for (Node child : children) {
			result.addChildren(child.copy());
		}
		return result;
	}
}
