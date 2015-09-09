Fault Localization Research 
---------------------------
This repo contains the implementations of two fault localization tools (GZoltar and Tarantula), 
as well as a test program (Triangle).

Test Program: Triangle
----------------------
Triangle.java is a short java program, there is a fault in line 18 of the program.
TestSuite.java is a suite with 33 tests for triangle, running the tests should report 4 failed tests.
There is a tarantula dir within the Triangle program which contains the same code as the src dir 
but the files are under a different package name. For some reason the Tarantula implmentation was unable 
to access this classes in the src dir(and it's associated packages). Until this problem is fixed the 
temporary solution will be to have the seperate tarantula dir in triangle.

GZoltar
-------
GZoltar is one of the fault localization tool in this repo, it can be used as a plugin on Eclipse(which includes the visualization component), 
for this project the GZoltar jar file calculates the suspiciousness of lines, it is found in the lib dir. 

Tarantula
---------
Tarantula is the second fault localization tool in this repo. For this project, tacoco is used to obtain a json format coverage matrix of the program.
Next Main.java uses the coverage matrix and the test results to calculate suspiciousness of lines.

(Note: Test programs should have their test headers named using the following format test1, test2, test3 ...test33 ect. The Runner.java class in the tarantula
dir will not be able to work unless the headers have the format test#, see the src code in Runner.java for more information)

The github projects for tacoco and primitive hamcrest can be found at https://github.com/spideruci/tacoco & https://github.com/inf295uci-2015/primitive-hamcrest 
respectively although the repos are copied into this project for convenience

Main requires several arguments to be run:
	- (1) The cov-matrix in Json format
	- (2) The class file of the Test program


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

2. Run Gzoltar: the Gzoltar script can be run with the following command
	- ./run-gzoltar ../triangle triangle target/classes/:target/test-classes
	- (Optional) ./run-gzoltar ../triangle triangle target/classes/:target/test-classes | grep "Triangle.java" will print only the suspiciousness for lines in Triangle

3. Generalize; the general form is 
	- ./run-gzoltar project dir test-package-to-execute class/path


Evaluating bugs using Tarantula
-------------------------------
1. Enter the tacoco-scripts dir: All the scripts to run tacoco are held here
	- cd tacoco-scripts

2. Run Tacoco: The run-tacoco script runs tacoco and creates cp.txt files in both the system-under-test dir and the tacoco dir the script requires the absolute paths of the
the dir to be evaluated and the tacoco directory, see the script for more information 
	- ./run-tacoco /absoluteolute/path/to/repo/triangle /absolute/path/to/repo/tacoco

3. Run Jacoco.exec Analyzer: The run-jacoco script creates the jacoco.exec file and the .json files in the tacoco dir, the script will have to be run inside the tacoco dir so you can copy it into the tacoco directory, see the script for more information 
	- cp -i run-jacoco ../tacoco
	- cd /tacoco/
	- ./run-jacoco /absolute/path/to/repo/triangle triangle /absolute/path/to/repo/tacoco

4. Compile the Tarantula classes and the test classes(triangle/tarantula classes).
	- javac -cp /lib/\* tarantula/\*.java
	- javac -cp /lib/\* triangle/tarantula\*.java

5. Run Tarantula's Main: Main requires two args(see the file for more details), the first is the absolute path to the cov-matrix.json file, the second is the name of the Test class, if the test program belongs to a package make sure to specify the package, ex: Triangle.TestSuite.  
	- java -cp .:lib/junit-4.11.jar:lib/json-simple-1.1.1.jar tarantula.Main /abs/path/to/triangle-mvn-compact-cov-matrix.json triangle.tarantula.TestSuite

	- Main can also be run in eclipse simply copy all the code tarantula dir into a new java project and use the Run Configurations option to allow for command line arguments, another option is to
hard code the files and classes. The needed jars, (junit and json-simple) are found in the lib repo so  eclipse can easily add them. 

6. Clean the tacoco dir: Run clean-tacoco in order to clean the dir so it can be reused, this means tacoco will have to be recompiled
	- ./clean-tacoco
	- mvn clean package (in tacoco dir)

Directory Structure
-------------------
The directory structure is as follows
	
	fault-localization-research
		|
		|--- tacoco-scripts:		Scripts that run tacoco and create the jacoco.exec
		|
		|--- gzoltar:			Scripts that run the gzoltar jar file
		|
		|--- lib:			Additional jars or files used in the repo
		|
		|--- primitive-hamcrest:	Repo, used by tacoco
		|
		|--- tacoco:			Repo, used to obtain per-test line coverage information
		|
		|--- tarantula:			Source files to obtain the suspiciousness for lines in a program
		|
		|--- triangle:			Test program in src, seperate version of test program in tarantula, maven build


	



