/*
* Engine.java
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.core.runtime.Status;

import eu.aniketos.wp1.ststool.analysis.dlv.Activator;
import eu.aniketos.wp1.ststool.analysis.dlv.TempFileManager;

/**
 * This class wraps the external Datalog engine. It checks if the current Operating system is supported, and temporarily install the executable on the filesystem. Also Provide methods to interact with the engine and get results
 * 
 * @author Mauro Poggianella
 * 
 */
public class Engine {

	/**
	 * Fields used for declaring the supported os;
	 */


	final private static boolean	canRunOnCurrentOs;
	final private static String	executableFullName;

	static {

		String curOs = System.getProperty("os.name").toLowerCase();

		if (curOs.startsWith("windows")) {
			executableFullName = "dlv_win_x86.exe";
		} else if (curOs.startsWith("linux") || curOs.startsWith("freebsd")) {
			executableFullName = "dlv_linux_x86.bin";
		} else if (curOs.startsWith("mac")) {
			executableFullName = "dlv_osx_i386.bin";
		} else {
			executableFullName = null;
		}
		canRunOnCurrentOs = executableFullName != null;
	}


	/**
	 * Unique instance of this class;
	 */
	private static Engine			INSTANCE;

	/**
	 * File pointing to the executable installed in the fileSystem
	 */
	private File						executable;


	/**
	 * Method that return the unique instance for this class previously checking if the operating system is supported.
	 * 
	 * @param throwException
	 *           true if the method should throw an Exception instead of returning a null value;
	 * @return an Unique instance for this class or null(if throwException is false)if the current OS is not supported
	 * @throws UnsupportedOSException
	 *            exception thrown when the current operating system is not Supported
	 */
	synchronized public static Engine getInstance() throws EngineInitaliziationException,UnsupportedOSException{

		if (canExecuteOnCurrentOS()) {
			if (INSTANCE == null) INSTANCE = new Engine();
			return INSTANCE;
		} else {
			throw new UnsupportedOSException();
		}
	}

	/**
	 * Convenience method, same as getInstance(false);
	 * 
	 * @return an Unique instance for this class or null if the current OS is not supported
	 */
	public static Engine getInstanceNoException(){
		try {
			return getInstance();
		} catch (Exception e) {
			return null;
		}
	}

	private Engine() throws EngineInitaliziationException {
		try {
			executable = prepareExecutableOnFileSystem();
		} catch (IOException e) {
			throw new EngineInitaliziationException("Can't initialize the Engine", e);
		}
	}

	private File prepareExecutableOnFileSystem() throws IOException{

		String executableName = executableFullName.substring(0, executableFullName.length() - 4);
		String executableExt = executableFullName.substring(executableFullName.length() - 4, executableFullName.length());

		File executableFile = TempFileManager.getTempFile(executableName, executableExt);

		InputStream is = Activator.getDefault().getBundle().getResource("resources/dlv/" + executableFullName).openStream();

		copyEngineToFileSystem(is, executableFile);
		executableFile.setExecutable(true);

		return executableFile;
	}

	private void copyEngineToFileSystem(InputStream is,File outputFile) throws IOException{

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
			byte[] buf = new byte[3145728]; //3MB buffer
			int len;
			while ((len = is.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}

	public static boolean canExecuteOnCurrentOS(){
		return canRunOnCurrentOs;
	}

	private Process p;
	
	public synchronized int execute(EngineExecutionParameters executionParam,EngineOutputInterpreter outputInterpreter,EngineOutputInterpreter errorInterpreter) throws EngineExecutionException{

		if (executable == null || !executable.exists()) { throw new EngineExecutionException("Executable engine not Found"); }

		File f = null;
		StreamGobbler sgOutput = null;
		StreamGobbler sgError = null;
		try {
			f = TempFileManager.getTempFile("DlvModel", ".txt");

			PrintWriter out = new PrintWriter(new FileWriter(f));
			out.print(executionParam.getInputProgram());
			out.close();

			List<String> params = executionParam.getParameter();

			String[] finalParams = new String[params.size() + 2];
			finalParams[0] = executable.getAbsolutePath();
			for (int i = 1; i < finalParams.length - 1; i++) {
				finalParams[i] = params.get(i - 1);
			}
			finalParams[finalParams.length - 1] = f.getAbsolutePath();

			p = Runtime.getRuntime().exec(finalParams);
			sgOutput = new StreamGobbler(p.getInputStream(),outputInterpreter, "Process Output Stream Gobbler");
			sgError = new StreamGobbler(p.getErrorStream(),errorInterpreter, "Process Error Stream Gobbler");
			sgOutput.start();
			sgError.start();
			int exitStatus = p.waitFor();
			long time = System.currentTimeMillis();
			boolean timeout = false;
			while ((!sgOutput.getState().equals(Thread.State.TERMINATED) || !sgError.getState().equals(Thread.State.TERMINATED)) && !timeout) {
				if (System.currentTimeMillis() - time > 5000) {
					timeout = true;
				} else {
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
				}
			}
			if (timeout) {
				sgOutput.interrupt();
				sgError.interrupt();
			}
			if(p!=null){
				p.destroy();
				p=null;
			}
			return exitStatus;
		} catch (IOException e1) {
			throw new EngineExecutionException("Exeption occured while executing DLV engine, can't create local file for inputProgram", e1);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "error executing dlv", e));
			throw new EngineExecutionException("Exeption occured while executing DLV engine", e);
		} finally {
			if (f != null && f.exists()) TempFileManager.deleteTempFiles(f);
		}
	}
	
	public void kill(){
		if(p!=null){
			p.destroy();
			p=null;
		}
	}
	
	private class StreamGobbler extends Thread {
		
		private InputStream	is;
		private EngineOutputInterpreter interpreter;
		private boolean		interrupt	= false;

		StreamGobbler(InputStream is,EngineOutputInterpreter interpreter, String name) {
			super(name);
			this.is = is;
			this.interpreter = interpreter;
		}

		@Override
		public void run(){
			BufferedReader outB = null;
			try {
				outB = new BufferedReader(new InputStreamReader(is));
				String line=null;
				while ((line=outB.readLine())!= null && !interrupt) {
					if(interpreter!=null){
						interpreter.parseOutput(line);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(outB!=null)
					try {
						outB.close();
					} catch (IOException e) {}
			}	
		}

		public void interrupt(){
			interrupt = true;
			if(interpreter!=null)interpreter.setInterrupted();
		}
	}
	
	public interface EngineOutputInterpreter{
		public void setInterrupted();
		public void parseOutput(String line);
	}
}
