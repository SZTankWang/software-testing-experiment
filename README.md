Fault Localization Research 

This repo contains the implementations of two fault localization tools (GZoltar and Tarantula), 
as well as a test program (Triangle).

Test Program: Triangle
----------------------
Triangle.java is a short java program, there is a fault in line 18 of the program.
TestSuite.java is a suite with 33 tests for triangle, the command mvn test should report 4 failed tests.

GZoltar
-------
GZoltar is one of the fault localization tool in this repo, it can be used as a plugin on Eclipse, for this project 
the GZoltar jar file which will calculate the suspiciousness of lines, it is found in the lib dir. 

Tarantula
---------
Tarantula is the second fault localization tool in this repo. For this project tacoco is used to obtain a Json format cov matrix of the program
and then calculate suspiciousness of lines using TarantulaMain.java.

TarantulaMain requires several arguments to be run:
	(1) The cov-matrix in Json format
	(2) The class file of the Test program


Getting started
---------------
1. Test and install primitive hamcrest onto the local repo (needed for Tarantula)
	- cd primitive-hamcrest
	- mvn test
	- mvn install

2. Compile tacoco (needed for Tarantula)
	- cd tacoco
	- mvn compile

3. Build triangle
	- cd triangle
	- mvn clean package

Evaluating bugs using GZoltar
-----------------------------
1. Enter the gzoltar directory
	- cd gzoltar

2. Run Gzoltar: the Gzoltar jar file can be run with the following command
	- ./run-gzoltar ../triangle triangle target/classes/:target/test-classes
	- (Optional) ./run-gzoltar ../triangle triangle target/classes/:target/test-classes | grep "Triangle.java" will print only the suspiciousness for lines in Triangle

3. Generalize; the general form is 
	- ./run-gzoltar project dir test package to execute class/path


Evaluating bugs using Tarantula
-------------------------------
1. Enter the fl-scripts dir: All the scripts to run tacoco are held here
	- cd fl-scripts

1. Run Tacoco: The run-tacoco script runs tacoco and creates cp.txt files in both the system-under-test dir and the tacoco dir 
	- ./run-tacoco /absoluteolute/path/to/repo/triangle /absolute/path/to/repo/tacoco

2. Run Jacoco.exec Analyzer: The run-jacoco script creates the jacoco.exec file and the .json files in the tacoco dir, the script will have to be run inside the tacoco dir so you can cp it into the tacoco directory
	- cp -i run-jacoco ../tacoco
	- cd /tacoco/
	- ./run-jacoco /absolute/path/to/repo/triangle triangle /absolute/path/to/repo/tacoco

3. Run TarantulaMain: TarantulaMain requires two args(see the file for more details), the first is the absoluteolute path to the cov-matrix.json file, the second is the name of the Test class, if the test program belongs to a package make sure to specify the package, ex: Triangle.TestSuite
	- java TarantulaMain /absolute/path/to/repo/tacoco/triange-compact-cov-matrix.json TestSuite

4, Clean the tacoco dir: Run clean-tacoco in order to clean the dir so it can be reused, this means tacoco will have to be recompiled
	- ./clean-tacoco
	- mvn compile (in tacoco)

Directory Structure
-------------------
The directory structure is as follows
	
	fault-localization-research
		|
		|--- fl-scripts:		Scripts that run tacoco and create the jacoco.exec
		|
		|--- gzoltar:			Scripts that run the gzoltar jar file
		|
		|--- lib:			Libraries used in the repo
		|
		|--- primitive-hamcrest:	Repo, used by tacoco
		|
		|--- tacoco:			Repo, used to obtain per-test line coverage information
		|
		|--- tarantula:			Source files to obtain the suspiciousness for lines in a program
		|
		|---triangle:			Test program


	



