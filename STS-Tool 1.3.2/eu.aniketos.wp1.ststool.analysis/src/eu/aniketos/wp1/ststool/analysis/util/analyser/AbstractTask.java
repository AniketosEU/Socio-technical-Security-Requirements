/*
* AbstractTask.java
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
package eu.aniketos.wp1.ststool.analysis.util.analyser;

import java.lang.reflect.Field;
import java.util.List;
import eu.aniketos.wp1.ststool.StsToolDiagram;
import eu.aniketos.wp1.ststool.analysis.results.IResult;


public abstract class AbstractTask implements Comparable<AbstractTask> {

	private final ITasksGroup	group;


	public AbstractTask(ITasksGroup group) {
		if (group == null) throw new RuntimeException("Invalid parameter: group can't be null"); //$NON-NLS-1$
		this.group = group;

	}

	public int getPrioirity(){
		return (Integer) getField("priority", int.class, new Integer(100)); //$NON-NLS-1$
	}

	public String getName(){
		return (String) getField("name", String.class, splitCamelCase(this.getClass().getSimpleName())); //$NON-NLS-1$
	}

	public int getMinTime(){
		return (Integer) getField("minTime", int.class, new Integer(100)); //$NON-NLS-1$
	}

	public int getTimeOut(){
		return (Integer) getField("timeOut", int.class, new Integer(0)); //$NON-NLS-1$
	}

	public BlockType getBlockType(){
		return (BlockType) getField("blockType", BlockType.class, BlockType.NO); //$NON-NLS-1$
	}

	public final ITasksGroup getGroup(){
		return group;
	}

	public abstract TaskResult executeTask(StsToolDiagram diagram,List<IResult> results) throws Exception;

	public int compareTo(AbstractTask o){
		int x = getGroup().getGroupPriority() - o.getGroup().getGroupPriority();
		if (x != 0) return x;
		x = getGroup().hashCode() - o.getGroup().hashCode();
		if (x != 0) return x;
		x = getPrioirity() - o.getPrioirity();
		if (x != 0) return x;
		return getName().compareTo(o.getName());
	}

	private Object getField(String fieldName,Class type,Object defaultValue){
		Object result = defaultValue;
		try {
			Field filed = this.getClass().getDeclaredField(fieldName);
			if (filed != null && filed.getType().equals(type)) {
				if (!filed.isAccessible()) filed.setAccessible(true);
				return filed.get(this);
			}
		} catch (Exception e) {
		}
		return result;
	}

	static private String splitCamelCase(String s){
		return s.replaceAll(String.format("%s|%s|%s", //$NON-NLS-1$
		"(?<=[A-Z])(?=[A-Z][a-z])", //$NON-NLS-1$
		"(?<=[^A-Z])(?=[A-Z])", //$NON-NLS-1$
		"(?<=[A-Za-z])(?=[^A-Za-z])" //$NON-NLS-1$
		), " " //$NON-NLS-1$
		);
	}

	public static enum BlockType {
		NO, CLASS, ANALYSIS
	}

	public static enum TaskResult {
		COMPLETED_OK, COMPLETED_WARNING, COMPLETED_ERROR, NOT_IMPLEMENTED
	}

	/* Example!
	class Task1 extends AbstractTask{
		public Task1(ITasksGroup group) {super(group);}
		
		//String name="Yea!!";
		//int priority=1;
		//int minTime=100;
		//int timeOut=1000;
		//BlockType blockType=BlockType.ANALYSIS;

		@Override
		public TaskResult executeTask(StsToolDiagram diagram,List<IResult> results){
			return NOT_IMPLEMENTED;
		}
	}
	*/

}
