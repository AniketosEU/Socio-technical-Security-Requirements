/*
* NodeCheckboxTreeViewer.java
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
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;

public class NodeCheckboxTreeViewer {

	final CheckboxTreeViewer	checkboxTreeViewer;

	public NodeCheckboxTreeViewer(Composite parent) {

		checkboxTreeViewer = new CheckboxTreeViewer(parent, SWT.BORDER);


		checkboxTreeViewer.setContentProvider(new TreeProvider());
		checkboxTreeViewer.setLabelProvider(new TreeProvider());
		checkboxTreeViewer.setCheckStateProvider(new TreeProvider());

		checkboxTreeViewer.expandAll();

		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event){
				Node n = (Node) event.getElement();
				boolean checked = event.getChecked();
				if (!n.isModificable()) {
					event.getCheckable().setChecked(n, !checked);
					return;
				} else {
					n.setCheked(checked);
					for (Node ch : n.getChildren()) {
						ch.setCheked(checked);
					}
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), checked);

					if (n.getParent() != null) {
						checkboxTreeViewer.setChecked(n.getParent(), true);
						n.getParent().setCheked(true);
					}
				}

			}
		});
		checkboxTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event){
				IStructuredSelection s = (IStructuredSelection) event.getSelection();
				if (s.getFirstElement() != null && !((Node) s.getFirstElement()).isModificable()) checkboxTreeViewer.setSelection(StructuredSelection.EMPTY);
			}
		});

	}

	public Tree getTree(){
		return checkboxTreeViewer.getTree();
	}

	public void setInputNode(Node n){
		checkboxTreeViewer.setInput(n);
		checkboxTreeViewer.expandAll();
	}

	public List<Node> getSelected(){
		List<Node> result = new ArrayList<Node>();
		for (Object o : checkboxTreeViewer.getCheckedElements())
			result.add((Node) o);
		return result;
	}


	private class TreeProvider implements ITreeContentProvider, ILabelProvider, IColorProvider, ICheckStateProvider {

		public Object[] getChildren(Object element){
			if (element instanceof Node) {
				return ((Node) element).getChildren().toArray();
			} else
				return new Object[0];

		}

		public Object getParent(Object element){
			if (element instanceof Node && !((Node) element).isRoot()) {
				return ((Node) element).getParent();
			} else
				return null;
		}

		public boolean hasChildren(Object element){
			if (element instanceof Node) {
				return ((Node) element).hasCildren();
			} else
				return false;
		}

		public Object[] getElements(Object element){
			if (element instanceof Node && ((Node) element).isRoot()) {
				return ((Node) element).getChildren().toArray();
			} else
				return new Object[0];
		}

		@Override
		public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
		}

		public Image getImage(Object element){
			return null;
		}

		public String getText(Object element){
			if (element instanceof Node) {
				return ((Node) element).getData();
			} else
				return "";
		}

		public void addListener(ILabelProviderListener element){
		}

		public void dispose(){
		}

		public boolean isLabelProperty(Object element,String arg1){
			return false;
		}

		public void removeListener(ILabelProviderListener arg0){
		}

		Color	c	= new Color(Display.getDefault(), 130, 130, 130);

		@Override
		public Color getForeground(Object element){
			if (element instanceof Node && !((Node) element).isModificable()) { return c; }
			return null;
		}

		@Override
		public Color getBackground(Object element){
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isChecked(Object element){
			if (element instanceof Node) {
				return ((Node) element).isChecked();
			} else
				return false;
		}

		@Override
		public boolean isGrayed(Object element){
			return false;
		}
	}



}
