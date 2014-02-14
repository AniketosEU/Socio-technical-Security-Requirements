/*
* DLVViolationsAnalysis.java
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.aniketos.wp1.ststool.analysis.dlv.engine.Engine;
import eu.aniketos.wp1.ststool.analysis.dlv.engine.EngineExecutionParameters;
import eu.aniketos.wp1.ststool.analysis.dlv.engine.Engine.EngineOutputInterpreter;

public class DLVViolationsAnalysis {


	public final static boolean canExecuteAnalysis() {
		return Engine.canExecuteOnCurrentOS();
	}

	public DLVViolationAnalysisResult startAnalysis(
			DlvInputProgram inputProgram, List<String> filters)
			throws Exception {
		Engine engine = Engine.getInstance();

		final Obfuscator ob = new Obfuscator();
		EngineExecutionParameters exParam = new EngineExecutionParameters(
				getObufuscatedInputProgram(inputProgram, ob));
		if (filters != null && filters.size() > 0) {
			exParam.setFilter(filters);
		}

		OutputInterpreter outputInterpreter = new OutputInterpreter();
		ErrorInterpreter errrorInterpreter = new ErrorInterpreter();
		engine.execute(exParam, outputInterpreter, errrorInterpreter);

		if(errrorInterpreter.containErrors()){
			for(String s:errrorInterpreter.getErrors()){
				System.err.println(s);
			}
			throw new Exception("DLV Analysis encountred an error");
		}else{
		
		List<Violation> violations = new ArrayList<Violation>();

		for (String s : outputInterpreter.getPredicatesFound().keySet()) {
			violations.add(new Violation(parsePredicateString(s, ob),
					outputInterpreter.getPredicatesFound().get(s),
					outputInterpreter.getTotalModels()));
		}
		return new DLVViolationAnalysisResult(violations, outputInterpreter
				.getTotalModels());
		}
	}

	public class Violation {

		public Predicate p;
		public int violationCount;
		public int totalViolation;

		public Violation(Predicate p, int violationCount, int totalViolation) {
			super();
			this.p = p;
			this.violationCount = violationCount;
			this.totalViolation = totalViolation;
		}

		public int percent() {
			return (int) (((double) violationCount / totalViolation) * 100);
		}

	}

	public class DLVViolationAnalysisResult {

		private List<Violation> violations;
		private int modelGenerated;

		private DLVViolationAnalysisResult(List<Violation> violations,
				int modelGenerated) {
			super();
			this.violations = violations;
			this.modelGenerated = modelGenerated;
		}

		public List<Violation> getViolations() {
			return violations;
		}

//		public int getNumberOfModelGenerated() {
//			return modelGenerated;
//		}
	}

	private static final Pattern p = Pattern
			.compile("[a-z][_[a-zA-Z]]*\\(.+?\\)");

	private Predicate parsePredicateString(String s, Obfuscator deobfuscator)
			throws IllegalArgumentException {

		String predicateName = null;

		// Test first and last character
		if (!Character.isLetterOrDigit(s.charAt(0)))
			throw new IllegalArgumentException(
					"Malformed input while parsing Parameter: found \'"
							+ s.charAt(0)
							+ "\' at position 0 -> expected letter or nummber");
		if (s.charAt(s.length() - 1) != ')')
			throw new IllegalArgumentException(
					"Malformed input while parsing Parameter: found \'"
							+ s.charAt(s.length() - 1) + "\' at position "
							+ (s.length() - 1) + " -> expected \')\'");

		int paramStart;
		boolean found = false;
		for (paramStart = 0; paramStart < s.length() && !found; paramStart++) {
			if (s.charAt(paramStart) == '(') {
				predicateName = s.substring(0, paramStart);
				found = true;
			}
		}
		if (predicateName == null)
			throw new IllegalArgumentException(
					"Malformed input while parsing Parameter: missing char \'(\'");

		Predicate predicate = new Predicate(predicateName);

		String param = s.substring(paramStart, s.length() - 1);
		List<String> textParam = new ArrayList<String>();

		int parConut = 0;
		paramStart = 0;

		for (int i = 0; i < param.length(); i++) {
			if (param.charAt(i) == '(') {
				parConut++;
			}
			if (param.charAt(i) == ')') {
				parConut--;
			}
			if (param.charAt(i) == ',' && parConut == 0) {
				if (paramStart != 0)
					paramStart += 1;
				textParam.add(param.substring(paramStart, i));
				paramStart = i;
			}
			if (i == param.length() - 1) {
				if (paramStart != 0)
					paramStart += 1;
				textParam.add(param.substring(paramStart, i + 1));
			}
		}
		for (String p : textParam) {
			if (p.contains("(")) {
				predicate.addParameter(parsePredicateString(p, deobfuscator));
			} else {
				String temp = p;
				if (deobfuscator != null)
					p = deobfuscator.decodeLiteral(p);
				if (p == null)
					p = temp;
				predicate.addParameter(new Literal(p));
			}
		}
		return predicate;
	}

	private static String getObufuscatedInputProgram(
			DlvInputProgram inputProgram, Obfuscator obfuscator) {
		List<String> result = new ArrayList<String>();
		for (String line : inputProgram.getInputProgramList()) {
			StringBuilder sb = new StringBuilder();
			try {
				sb.append(line);
				Matcher m = p.matcher(line);
				final int lenght = sb.length();
				while (m.find()) {
					int offset = lenght - sb.length();
					sb.replace(m.start() - offset, m.end() - offset,
							obfuscateParameter(m.group(), obfuscator));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.add(sb.toString());
		}
		// Collections.shuffle(result);
		StringBuilder sb = new StringBuilder();
		for (String s : result) {
			sb.append(s + "\n");
		}
		return sb.toString();
	}

	private static String obfuscateParameter(String param, Obfuscator obfuscator) {
		StringBuilder sb = new StringBuilder();
		sb.append(param.substring(0, param.indexOf('(')) + "(");

		String[] literals = param.substring(param.indexOf('(') + 1,
				param.length() - 1).split(",");

		for (int i = 0; i < literals.length; i++) {
			if (i > 0)
				sb.append(",");
			sb.append(obfuscator.encodeLiteral(literals[i]));
		}
		sb.append(")");
		return sb.toString();
	}

	private class OutputInterpreter implements EngineOutputInterpreter {

		private boolean interrupted = false;
		private int totalModels = 0;

		private Map<String, Integer> predicatesMap = new HashMap<String, Integer>();

		@Override
		public void setInterrupted() {
			interrupted = true;
		}

		public boolean isInterrupted() {
			return interrupted;
		}

		@Override
		public void parseOutput(String line) {

			if (!line.equals("{}")) {
				for (String predicate : getPredicatesStringsFromModel(line)) {
					addPredicate(predicate);
				}
			}

			totalModels++;
		}

		private void addPredicate(String predicate) {
			if (!predicatesMap.containsKey(predicate)) {
				predicatesMap.put(predicate, 0);
			}
			predicatesMap.put(predicate, predicatesMap.get(predicate) + 1);
		}

		private List<String> getPredicatesStringsFromModel(String model) {
			List<String> predicates = new ArrayList<String>();

			// Test first and last character
			if (model.charAt(0) != '{')
				throw new IllegalArgumentException(
						"Invalid model text: missing char \'{\' at position 0");
			if (model.charAt(model.length() - 1) != '}')
				throw new IllegalArgumentException(
						"Invalid model text: missing char \'}\' at position "
								+ (model.length() - 1));
			// System.err.println(s.substring(1,s.length()-1));

			int startC = 0;
			int paren = 0;
			boolean searchingEnd = false;
			for (int i = 0; i < model.length(); i++) {
				if (!searchingEnd && Character.isLetterOrDigit(model.charAt(i))) {
					startC = i;
					searchingEnd = true;
				}
				if (searchingEnd && model.charAt(i) == '(') {
					paren++;
				}
				if (searchingEnd && model.charAt(i) == ')') {
					if (--paren == 0) {
						searchingEnd = false;
						predicates.add(model.substring(startC, i + 1));
					}
				}
			}
			return predicates;
		}

		public int getTotalModels() {
			return totalModels;
		}

		public Map<String, Integer> getPredicatesFound() {
			return predicatesMap;
		}
	}
	
	private class ErrorInterpreter implements EngineOutputInterpreter {

		private boolean interrupted = false;
		private List<String> errors = new ArrayList<String>();

		private Map<String, Integer> predicatesMap = new HashMap<String, Integer>();

		@Override
		public void setInterrupted() {
			interrupted = true;
		}

		public boolean isInterrupted() {
			return interrupted;
		}

		@Override
		public void parseOutput(String line) {
			errors.add(line);
		}

		public List<String> getErrors() {
			if(containErrors())
				return errors;
			else return null;
		}
		
		public boolean containErrors() {
			return errors.size()>0;
		}
	}

	private class Obfuscator {

		private Map<String, String> literalIdMap = new HashMap<String, String>();
		private Map<String, String> literalIdInverseMap = new HashMap<String, String>();

		private int literalIdCount = 0;

		private Obfuscator() {
		}

		public String encodeLiteral(String name) {
			if (name.startsWith("&&")) {
				if (!literalIdMap.containsKey(name)) {
					literalIdMap.put(name, 'o' + new Integer(literalIdCount++)
							.toString());
					literalIdInverseMap.put(literalIdMap.get(name), name);
				}
				return literalIdMap.get(name);
			} else {
				return name;
			}
		}

		public String decodeLiteral(String name) {
			if (literalIdInverseMap.containsKey(name)) {
				String literal = literalIdInverseMap.get(name);
				if (literal.length() > 2 && literal.startsWith("&&"))
					literal = literal.substring(2);
				return literal;
			}
			return name;
		}
	}

}
