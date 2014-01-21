/*
* STSCustomTextDirectEditManager.java
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
package eu.aniketos.wp1.ststool.diagram.edit.parts;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import eu.aniketos.wp1.ststool.StsElement;


public class STSCustomTextDirectEditManager extends STSTextDirectEditManager {

	final static int	MAX_NAME_LENGHT	= 20;

	public STSCustomTextDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {

		super(source, editorType, locator);
	}

	public STSCustomTextDirectEditManager(ITextAwareEditPart source) {

		super(source);
	}

	@Override
	protected CellEditor createCellEditorOn(Composite composite){

		TextCellEditor result = new TextCellEditor(composite, SWT.WRAP) {

			@Override
			protected void keyReleaseOccured(KeyEvent keyEvent){
				if (keyEvent.character == '\r') {
					
					Text t = (Text) keyEvent.getSource();
					String str = t.getText();
					if (str.equals("")){
						bringDown();// Return key
					}
					fireApplyEditorValue();
					deactivate();
				}
				
				super.keyReleaseOccured(keyEvent);
			}
		};
		((Text) result.getControl()).addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e){
				if (e.keyCode == SWT.DEL | e.keyCode == SWT.BS | e.keyCode == SWT.ESC | e.keyCode == SWT.ARROW_DOWN | e.keyCode == SWT.ARROW_UP | e.keyCode == SWT.ARROW_LEFT | e.keyCode == SWT.ARROW_RIGHT) return;
				Text text = ((Text) e.getSource());
				if (text.getSelectionCount() == 0 && text.getText().length() >= MAX_NAME_LENGHT) e.doit = false;
			}
		});
		return result;
	}


	@Override
	protected boolean sholudRename(){
		Object o = ((CompartmentEditPart) getSource()).getPrimaryView().getElement();
		String s = ((Text) getCellEditor().getControl()).getText();
		if (o instanceof StsElement && !((StsElement) o).canAssignName(s)) {
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openWarning(shell, "Warning", "Can't rename the current element, because there is already an element with the same name");
			return false;
		} else {
			return true;
		}
	}

	/*@Override
	public void show(char initialChar) {
		super.show(initialChar);
		System.out.println("sdasdasd");
		if(getCellEditor()!=null && getCellEditor().getControl()!=null)
		((Text)getCellEditor().getControl()).setText(""+initialChar);
	}*/
}
