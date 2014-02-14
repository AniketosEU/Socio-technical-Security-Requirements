/*
* STSPaletteEditPartFactory.java
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
package eu.aniketos.wp1.ststool.diagram.custom.palette;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.internal.ui.palette.editparts.DrawerEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.GroupEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.PaletteStackEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.PinnablePaletteStackEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SeparatorEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.SliderPaletteEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.TemplateEditPart;
import org.eclipse.gef.internal.ui.palette.editparts.ToolbarEditPart;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PaletteStack;
import org.eclipse.gef.palette.PaletteTemplateEntry;
import org.eclipse.gef.palette.PaletteToolbar;
import org.eclipse.gef.ui.palette.editparts.PaletteEditPart;

@SuppressWarnings("restriction")
public class STSPaletteEditPartFactory implements EditPartFactory {

	/**
	 * Create DrawerEditPart - edit part for PaletteDrawer
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteDrawer
	 * @return the newly created EditPart
	 */

	protected EditPart createDrawerEditPart(EditPart parentEditPart,Object model){
		return new DrawerEditPart((PaletteDrawer) model);
	}

	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(EditPart, Object)
	 */
	public EditPart createEditPart(EditPart parentEditPart,Object model){
		if (model instanceof PaletteRoot) return createMainPaletteEditPart(parentEditPart, model);
		if (model instanceof PaletteStack) return createStackEditPart(parentEditPart, model);
		if (model instanceof PaletteContainer) {
			Object type = ((PaletteContainer) model).getType();
			if (PaletteDrawer.PALETTE_TYPE_DRAWER.equals(type)) return createDrawerEditPart(parentEditPart, model);
			if (PaletteGroup.PALETTE_TYPE_GROUP.equals(type) || PaletteContainer.PALETTE_TYPE_UNKNOWN.equals(type)) return createGroupEditPart(parentEditPart, model);
			if (PaletteToolbar.PALETTE_TYPE_TOOLBAR_GROUP.equals(type)) return createToolbarEditPart(parentEditPart, model);
		}
		if (model instanceof PaletteTemplateEntry) return createTemplateEditPart(parentEditPart, model);
		if (model instanceof PaletteSeparator) return createSeparatorEditPart(parentEditPart, model);
		if (model instanceof PaletteEntry) return createEntryEditPart(parentEditPart, model);
		return null;
	}

	/**
	 * Create SeparatorEditPart - edit part for PaletteSeparator
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteSeparator
	 * @return the newly created EditPart
	 */
	protected EditPart createSeparatorEditPart(EditPart parentEditPart,Object model){
		return new SeparatorEditPart((PaletteSeparator) model);
	}

	/**
	 * Create PaletteStackEditPart - edit part for PaletteStack
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteStack
	 * @return the newly created EditPart
	 */
	protected EditPart createStackEditPart(EditPart parentEditPart,Object model){
		if (parentEditPart instanceof PaletteEditPart && ((PaletteEditPart) parentEditPart).isToolbarItem()) { return new PaletteStackEditPart((PaletteStack) model); }
		return new PinnablePaletteStackEditPart((PaletteStack) model);
	}

	/**
	 * Create ToolEntryEditPart - edit part for ToolEntry
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the ToolEntry
	 * @return the newly created EditPart
	 */
	protected EditPart createEntryEditPart(EditPart parentEditPart,Object model){
		if (model instanceof PaletteSeparatorTool) {
			final PaletteSeparatorTool tool = (PaletteSeparatorTool) model;
			return new PaletteEditPart(tool) {

				@Override
				protected IFigure createFigure(){
					return tool.createFigure();
				}
			};
		}
		return new ToolEntryEditPartEx((PaletteEntry) model);
	}

	/**
	 * Create GroupEditPart - edit part for PaletteGroup
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteGroup
	 * @return the newly created EditPart
	 */
	protected EditPart createGroupEditPart(EditPart parentEditPart,Object model){
		return new GroupEditPart((PaletteContainer) model);
	}

	/**
	 * Create ToolbarEditPart - edit part for PaletteToolbar
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteToolbar
	 * @return the newly created EditPart
	 */
	protected EditPart createToolbarEditPart(EditPart parentEditPart,Object model){
		return new ToolbarEditPart((PaletteToolbar) model);
	}

	/**
	 * Create SliderPaletteEditPart - edit part for PaletteRoot
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteRoot
	 * @return the newly created EditPart
	 */
	protected EditPart createMainPaletteEditPart(EditPart parentEditPart,Object model){
		return new SliderPaletteEditPart((PaletteRoot) model);
	}

	/**
	 * Create TemplateEditPart - edit part for PaletteTemplateEntry
	 * 
	 * @param parentEditPart
	 *           the parent of the new editpart to be created
	 * @param model
	 *           the PaletteTemplateEntry
	 * @return the newly created EditPart
	 */
	protected EditPart createTemplateEditPart(EditPart parentEditPart,Object model){
		return new TemplateEditPart((PaletteTemplateEntry) model);
	}

}
