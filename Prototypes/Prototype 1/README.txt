Title: Prototype 1 README
Author: Fiona Lyons
Class: Foundations of Software Engineering
Date: 7/2/2018

JAVA INFORMATION
	Compiler:	JDK 1.8.0_131
	IDE:		Eclipse Kepler Service Release 1, Build id: 20130919-0819
	
FILES INCLUDED
	This directory contains both the source code and the compiled java code in
	the src/ directory.
	
	Main startup file: Prototype1.java
	
	A sample input file is also included in the input/ directory.
	
INPUT FILES
	Input files are required to be COMMA DELIMITED and thus questions and answers
	cannot contain commas. 
	
	Input files are of the form:
	Category Name (category 1)
	Question 1, Answer 1
	Question 2, Answer 2
	...
	Question 5, Answer 5
	Category Name (category 2)
	Question 1, Answer 1
	Question 2, Answer 2
	...
	
	Questions are inputted into the file in descending order of value (i.e. 
	question 1 is worth the most, and should be the most challenging.)
	
RUNNING PROTOTYPE 1
	This program can be run by moving to the source directory and running the 
	following command in the windows command line:
		java Prototype1 INPUT_FILE
		
	INPUT_FILE is the relative path to the file containing questions and answers.
	An example file is provided in the input directory, so an example command
	executed in the src/ directory would be:
		java Prototype1 "../input/sample_questions.txt"
		
MODIFYING PROTOTYPE 1
	Any modifications made to the Java source code will require the code to be 
	recompiled, which can be done with the following command:
		javac Prototype1.java