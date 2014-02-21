Socio-technical-Security-Requirements
=====================================

Short description
-----------------
STS-Tool (Socio-Technical Security modelling Tool) is a standalone application that enables the creation of security requirements models in STS-ml. STS-Tool supports the validation of a diagram via consistency analysis and security analysis.  The tool provides a feature for the automatic derivation of security requirements, threats propagation analysis, the generation of a security requirements document and integrates a plug-in for utilising an online threat repository service. STS-Tool is written in Java and is based on the Eclipse RCP Engine. The current version of STS-Tool (v1.3.3) is ready for public use.

Overview 
--------
STS-ml (Socio-Technical Security modelling language) is an actor-and goal-oriented security requirements modelling language. STS-ml includes high-level organisational concepts such as actor, goal, delegation, etc. Importantly, it relates security requirements to interaction, allowing actors to express their expectations regarding security over the interactions they have with other actors. 

STS-Tool is the modelling and analysis support for STS-ml. As such, it enables the creation of STS-ml diagrams. 

Moreover, it:
1. allows the automated derivation of security requirements from a STS-ml diagram; 
2. supports *automated reasoning* in terms of 
	* consistency  analysis: well-formedness analysis of the STS-ml diagram designed 
security analysis: verify (i) if the security requirements specification is consistent, that is, no requirements are potentially conflicting; (ii) if the diagram 	
	* allows the satisfaction of the specified security requirements. ; 
	* threats analysis: identify threat propagation over actors’ assets

In the Aniketos approach, STS-Tool is used to conduct security requirements engineering that precedes the design of secure service compositions.

Requirements
------------
STS-Tool is available for Windows (XP, Vista and 7), Linux (Gtk) and Apple OS X operating systems. Java 6 needs to be installed.

Features
--------
STS-Tool comes with the following features:
* 	multi-view modelling of STS-ml diagrams
*	ensure diagram validity on-the-fly
*	export diagrams to different file formats (png, jpeg, pdf, svg, etc.)
*	automated derivation of security requirements
*	automated reasoning, in terms of (1) well-formedness analysis, (2) security analysis, and (3) threat analysis 
*	automated generation of security requirements document
*	exporting security requirements and threats to STS-ml XML format
* 	search and import threats from an online threat repository


How to get started / Usage manual / Examples
--------------------------------------------
Practical information on how to get started and tutorials can be found at http://www.sts-tool.eu/Tutorial.php. Comprehensive manuals on language and tool can be found at http://www.sts-tool.eu/Documentation.php.

Contributing (guide)
--------------------
STS-Tool is an open source application. The source code is freely available, and changes can be made according to the AGPL licence. A bug tracking system is also available:
www.sts-tool.eu/mantis/

Installation
------------
In order to use STS-Tool, download and extract the archive, then execute the executable file named “StsTool”. For further information, please visit:	 
http://www.sts-tool.eu/Documentation.php.

Credits
-------
STS-tool is developed by members Software Engineering and Formal Methods Group, Department of Information Engineering and Computer science, at University of Trento, Italy.

Lead developer: Mauro Poggianella
Contributors: Paolo Giorgini, Elda Paja, Fabiano Dalpiaz

Threat Repository service is provided by Search-Lab (lead Balazs Kiss), and made available in STS-Tool with the threats plug-in developed by SINTEF (Erlend Andreas Gjære).

Official site, external resources
---------------------------------
The official website of STS-Tool is: http://www.sts-tool.eu/

Updates and list of known issues
--------------------------------
Information about differences between STS-Tool versions can be found at http://www.sts-tool.eu/Downloads.php. Current bugs are listed in the public bug tracking system https://www.sts-tool.eu/mantis/.
