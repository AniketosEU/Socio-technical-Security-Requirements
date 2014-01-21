/*
* StsToolIconProvider.java
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
package eu.aniketos.wp1.ststool.diagram.providers;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.GetIconOperation;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import eu.aniketos.wp1.ststool.diagram.custom.constraint.STSElementIconDescriptor;

/**
 * @generated
 */
public class StsToolIconProvider extends AbstractProvider implements IIconProvider {

	private Map<String, Image>	imageCache	= new HashMap<String, Image>();

	/**
	 * @generated NOT
	 */
	public Image getIcon(IAdaptable hint,int flags){

		IElementType element = (IElementType) hint.getAdapter(IElementType.class);
		if (element != null) {
			if (flags != 0)
				return getLargeImage(element);
			else
				return getSmallImage(element);
		}
		return StsToolElementTypes.getImage(hint);
	}

	final static private String	TYPE_SMALL	= "small";
	final static private String	TYPE_LARGE	= "large";


	private Image getLargeImage(IElementType element){

		return getImage(element, TYPE_LARGE);
	}

	private Image getSmallImage(IElementType element){

		return getImage(element, TYPE_SMALL);
	}

	private Image getImage(IElementType element,String type){

		final String elementID = element.getId() + type;
		Image result = imageCache.get(elementID);
		if (result == null) {
			ImageDescriptor id = null;
			if (type == TYPE_LARGE)
				id = STSElementIconDescriptor.getLargeImageDescriptord(element);
			else
				id = STSElementIconDescriptor.getSmallImageDescriptord(element);
			if (id != null) {
				imageCache.put(elementID, id.createImage());
				result = imageCache.get(elementID);
			}
		}
		return result;
	}



	/**
	 * @generated
	 */
	public boolean provides(IOperation operation){
		if (operation instanceof GetIconOperation) { return ((GetIconOperation) operation).execute(this) != null; }
		return false;
	}

}
